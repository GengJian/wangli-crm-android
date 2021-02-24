package messages.timchat.utils;

import android.content.Context;
import android.util.Log;

import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushToken;
import com.tencent.qcloud.sdk.Constant;

/**
 * Created by forrestluo on 2018/3/16.
 */

public class MeizuPushMessageReceiver extends MzPushMessageReceiver {

    private static final String TAG = MeizuPushMessageReceiver.class.getSimpleName();
    //uat
//    private static final long busiid = 4412;
    //prd
    private static final long busiid = 4413;

    /**
     * 已废弃接口，不建议使用
     */
    @Override
    public void onRegister(Context context, String pushId) {

    }

    /**
     * 已废弃接口，不建议使用
     */
    @Override
    public void onUnRegister(Context context, boolean success) {

    }

    /**
     * Push开关状态回调
     */
    @Override
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {

    }

    /**
     * 订阅状态回调, 可以在这个接口获取PushId，并进行上报
     */
    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        Log.d(TAG, "pushId: " + registerStatus.getPushId() + "|Expiretime: " + registerStatus.getExpireTime() + "|str: " + registerStatus.toString());


        //上报busiid和pushid到腾讯云，需要在登录成功后进行上报
        /*TIMOfflinePushToken token = new TIMOfflinePushToken();
        token.setBussid(busiid);
        token.setToken(registerStatus.getPushId());
        TIMManager.getInstance().setOfflinePushToken(token, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "setOfflinePushToken failed, code: " + i + "|msg: " + s);
            }

            @Override
            public void onSuccess() {
                Log.i(TAG, "setOfflinePushToken succ");
            }
        });*/
        TIMOfflinePushToken param = new TIMOfflinePushToken(Constant.MeizuBussId, registerStatus.getPushId());
        TIMManager.getInstance().setOfflinePushToken(param, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.d(TAG,"revoke error " + i+s);
//                Toast.makeText(JiuyiMainApplication.getIns(), "魅族注册失败"+ i+s+"pushId: " + registerStatus.getPushId() + "|Expiretime: " + registerStatus.getExpireTime() + "|str: " + registerStatus.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess() {
                Log.d(TAG,"revoke success");
//                Toast.makeText(JiuyiMainApplication.getIns(), "pushId: " + registerStatus.getPushId() + "|Expiretime: " + registerStatus.getExpireTime() + "|str: " + registerStatus.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 反订阅回调
     */
    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {

    }

    /**
     * 标签状态回调
     */
    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {

    }

    /**
     * 别名状态回调
     */
    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {

    }

    /**
     * 通知栏点击回调
     */
    @Override
    public void onNotificationClicked(Context context, MzPushMessage mzPushMessage) {

        // 消息正文内容
        String content = mzPushMessage.getContent();

        // 消息扩展内容
        String ext = mzPushMessage.getSelfDefineContentString();

        Log.i(TAG, "onNotificationClicked content: " + content + "|selfDefined ext: " + ext);
    }

}
