package messages.timchat.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Func;
import com.control.utils.Rc;
import com.ibeiliao.badgenumberlibrary.BadgeNumberManager;
import com.ibeiliao.badgenumberlibrary.BadgeNumberManagerXiaoMi;
import com.ibeiliao.badgenumberlibrary.MobileBrand;
import com.jiuyi.activity.common.activity.JiuyiHeadPageActivity;
import com.wanglicrm.android.R;
import com.jiuyi.activity.common.activity.JiuyiRootActivity;
import com.jiuyi.app.JiuyiMainApplication;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.ext.message.TIMConversationExt;
import com.tencent.qcloud.presentation.event.MessageEvent;


import java.util.Observable;
import java.util.Observer;

import messages.timchat.model.CustomMessage;
import messages.timchat.model.FriendProfile;
import messages.timchat.model.FriendshipInfo;
import messages.timchat.model.Message;
import messages.timchat.model.MessageFactory;

/**
 * 在线消息通知展示
 */
public class PushUtil implements Observer {

    private static final String TAG = PushUtil.class.getSimpleName();

    private static int pushNum=0;
    private int mCount = 1;

    private final int pushId=1;

    private static PushUtil instance = new PushUtil();

    private PushUtil() {
        MessageEvent.getInstance().addObserver(this);
    }

    public static PushUtil getInstance(){
        return instance;
    }



    private void PushNotify(TIMMessage msg){
        //系统消息，自己发的消息，程序在前台的时候不通知
        if (msg==null||Foreground.get().isForeground()||
                (msg.getConversation().getType()!= TIMConversationType.Group&&
                        msg.getConversation().getType()!= TIMConversationType.C2C)||
                msg.isSelf()||
                msg.getRecvFlag() == TIMGroupReceiveMessageOpt.ReceiveNotNotify) /*||
                MessageFactory.getMessage(msg) instanceof CustomMessage*/ return;

        String senderStr,contentStr="";
        Message message = MessageFactory.getMessage(msg);
        if (message == null) return;
        if (message instanceof CustomMessage && ((((CustomMessage) message).getType() == CustomMessage.Type.TYPING /*||(((CustomMessage) message).getType() == CustomMessage.Type.INVALID )*/))){
            return;
        }
        senderStr = message.getSender();
        if(!Func.IsStringEmpty(senderStr) && !Func.IsStringEmpty(message.getSummary())){
            if(MessageFactory.getMessage(msg) instanceof CustomMessage){
                contentStr =((CustomMessage) message).getsubtitle(message.getSummary().toString());
            }else{
                contentStr = message.getSummary();
            }
            FriendProfile profile = FriendshipInfo.getInstance().getProfile(senderStr);
            senderStr=profile == null?senderStr:profile.getName();
        }
        JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);

        Log.d(TAG, "recv msg " + contentStr);

        NotificationManager mNotificationManager = (NotificationManager) JiuyiMainApplication.getIns().getSystemService(JiuyiMainApplication.getIns().NOTIFICATION_SERVICE);
        // 兼容 8.0 系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(JiuyiMainApplication.getIns(), mNotificationManager);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(JiuyiMainApplication.getIns(),"IM");
        Intent notificationIntent;
        if(!Func.IsStringEmpty(Rc.getIns().tim_signature)){
            notificationIntent = new Intent(JiuyiMainApplication.getIns(), JiuyiRootActivity.class);
        }else{
            notificationIntent = new Intent(JiuyiMainApplication.getIns(), JiuyiHeadPageActivity.class);
        }
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(JiuyiMainApplication.getIns(), 1,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentTitle(senderStr)//设置通知栏标题
                .setContentText(contentStr)
                .setContentIntent(PendingIntent.getActivity(JiuyiMainApplication.getIns(), 1, new Intent(JiuyiMainApplication.getIns(), JiuyiHeadPageActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
//                .setContentIntent(intent) //设置通知栏点击意图
                .setTicker(senderStr+":"+contentStr) //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setDefaults(Notification.DEFAULT_ALL)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                .setSmallIcon(R.mipmap.ic_launcher);//设置通知小ICON
        Notification notify = mBuilder.build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(pushId, notify);

        //设置应用在桌面上显示的角标
        TIMConversation con = TIMManager.getInstance().getConversation(TIMConversationType.C2C, message.getMessage().getSender());
        TIMConversationExt conExt = new TIMConversationExt(con);
        //获取会话未读数
        long num = conExt.getUnreadMessageNum();
        BadgeNumberManager.from(JiuyiMainApplication.getIns()).setBadgeNumber(Integer.parseInt(num+""));
    }

    public static void resetPushNum(){
        pushNum=0;
        //设置应用在桌面上显示的角标,小米机型只要用户点击了应用图标进入应用，会自动清除掉角标
        if (!Build.MANUFACTURER.equalsIgnoreCase(MobileBrand.XIAOMI)) {
            BadgeNumberManager.from(JiuyiMainApplication.getIns()).setBadgeNumber(0);
//            Toast.makeText(MainActivity.this, "清除桌面角标成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(){
        NotificationManager notificationManager = (NotificationManager) JiuyiMainApplication.getIns().getSystemService(JiuyiMainApplication.getIns().NOTIFICATION_SERVICE);
        notificationManager.cancel(pushId);
        if (!Build.MANUFACTURER.equalsIgnoreCase(MobileBrand.XIAOMI)) {
            BadgeNumberManager.from(JiuyiMainApplication.getIns()).setBadgeNumber(0);
//            Toast.makeText(MainActivity.this, "清除桌面角标成功", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method is called if the specified {@code Observable} object's
     * {@code notifyObservers} method is called (because the {@code Observable}
     * object has been updated.
     *
     * @param observable the {@link Observable} object.
     * @param data       the data passed to {@link Observable#notifyObservers(Object)}.
     */
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MessageEvent){
            if (data instanceof TIMMessage) {
                TIMMessage msg = (TIMMessage) data;
                if (msg != null){
                    PushNotify(msg);
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(Context context, NotificationManager notificationManager) {
        // 通知渠道
        NotificationChannel mChannel = new NotificationChannel("IM", "product_01", NotificationManager.IMPORTANCE_HIGH);
        // 开启指示灯，如果设备有的话。
        mChannel.enableLights(true);
        // 开启震动
        mChannel.enableVibration(true);
        //  设置指示灯颜色
        mChannel.setLightColor(Color.RED);
        // 设置是否应在锁定屏幕上显示此频道的通知
        mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        // 设置是否显示角标
        mChannel.setShowBadge(true);
        //  设置绕过免打扰模式
        mChannel.setBypassDnd(true);
        // 设置震动频率
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400});
        //最后在notificationmanager中创建该通知渠道
        notificationManager.createNotificationChannel(mChannel);
    }


    private void setXiaomiBadgeNumber() {
        NotificationManager notificationManager = (NotificationManager) JiuyiMainApplication.getIns().
                getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(JiuyiMainApplication.getIns())
                .setSmallIcon(JiuyiMainApplication.getIns().getApplicationInfo().icon)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("推送标题")
                .setContentText("我是推送内容")
                .setTicker("ticker")
                .setAutoCancel(true)
                .build();
        //相邻的两次角标设置如果数字相同的话，好像下一次会不生效
        BadgeNumberManagerXiaoMi.setBadgeNumber(notification,mCount++);
        notificationManager.notify(1000, notification);
//        Toast.makeText(MainActivity.this, "设置桌面角标成功", Toast.LENGTH_SHORT).show();

    }

}
