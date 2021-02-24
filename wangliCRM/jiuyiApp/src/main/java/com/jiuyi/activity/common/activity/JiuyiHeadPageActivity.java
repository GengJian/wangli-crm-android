package com.jiuyi.activity.common.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.shared.JiuyiMobileRegistShared;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Func;
import com.control.utils.JiuyiImage;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.webview.JiuyiWebView;
import com.gyf.barlibrary.ImmersionBar;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.huawei.android.pushagent.PushManager;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityManager;
import com.jiuyi.model.DictBean;
import com.jiuyi.model.DictResultBean;
import com.jiuyi.tools.jiuyiDealRootInit;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushSettings;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.qcloud.presentation.business.InitBusiness;
import com.tencent.qcloud.presentation.business.LoginBusiness;
import com.tencent.qcloud.presentation.event.FriendshipEvent;
import com.tencent.qcloud.presentation.event.GroupEvent;
import com.tencent.qcloud.presentation.event.MessageEvent;
import com.tencent.qcloud.presentation.event.RefreshEvent;
import com.tencent.qcloud.tlslibrary.service.TLSService;
import com.tencent.qcloud.tlslibrary.service.TlsBusiness;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import commonlyused.db.DbHelper;
import customer.request.RequestMemberChoose;
import customer.request.RequestServiceDate;
import messages.timchat.model.UserInfo;
import messages.timchat.utils.PushUtil;


/**
 * ****************************************************************
 * 文件名称 : JiuyiHeadPageActivity.java
 * 作 者 :   zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 启动页
 * ****************************************************************
 */
public class JiuyiHeadPageActivity extends Activity  {
    private jiuyiDealRootInit pJiuyiDealRootInit;
    private RelativeLayout pRelativeLayout;
    private ImageView pWelcomeImage;
    private TextView pTimeVlalue;
    private RelativeLayout pTimeLayout;

    private String mHeadPageImage = "jiuyi_welcomeimg";
    private int nScreenWidth;//屏幕宽度
    private int nScreenHeight;//屏幕高度


    private int nMaxAdvTimes = 3;//广告显示的最长时间
    private boolean isChangePaged;//是否已经切换界面了
    private JiuyiWebView mWebAjaxProxy = null;//防止被劫持而预先加载的webview
    private Bundle mBundle = new Bundle();

    private int LOGIN_RESULT_CODE = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Rc.SetApplication(getApplication());
        //获取上一界面的Bundle，供初始化，滚动广告页等使用
        if (getIntent() != null) {
            Bundle temmBundle = getIntent().getExtras();
            if (temmBundle != null) {
                mBundle = temmBundle;
            }
        }
        clearNotification();

        setContentView(Res.getLayoutID(Rc.getApplication(), "jiuyi_activity_headpage_layout"));


        getDictList();
        //防止过度绘制，因为布局里有个大图片，就不需要window的背景了
        getWindow().setBackgroundDrawable(null);

        pRelativeLayout = (RelativeLayout) findViewById(Res.getViewID(Rc.getApplication(), "tzt_headpage_parent"));
        pWelcomeImage = (ImageView) findViewById(Res.getViewID(Rc.getApplication(), "tzt_headpage_welcomeimage"));
        pTimeVlalue = (TextView) findViewById(Res.getViewID(Rc.getApplication(), "tzt_headpage_timevalue"));
        pTimeLayout = (RelativeLayout) findViewById(Res.getViewID(Rc.getApplication(), "tzt_headpage_time_layout"));
        ImmersionBar.with(this)
                .transparentBar()
                .init();

        //获取初始化尺寸线程
        new initRectThread().start();
    }

    @Override
    public void onDestroy() {
        if (mWebAjaxProxy != null) {
            mWebAjaxProxy.destroyAllWebViews();
        }
        ImmersionBar.with(this).destroy();
        super.onDestroy();
        JiuyiLog.e("onDestroy", "onDestroy");
    }

    private void onSetImage() {
        Bitmap m_bitmap = null;
        try {
            if (m_bitmap == null) {
                pWelcomeImage.setImageResource(Res.getDrawabelID(null, mHeadPageImage));
            } else {
                if (m_bitmap != null) {
                    int finalw, finalh;
                    JiuyiImage img = new JiuyiImage(m_bitmap);
                    int[] size = CaltWelcomeSize(img, nScreenWidth, nScreenHeight);
                    finalw = size[0];
                    finalh = size[1];

                    FrameLayout.LayoutParams lpParent = new FrameLayout.LayoutParams(finalw, finalh);
                    lpParent.leftMargin = (nScreenWidth - finalw) / 2;
                    lpParent.topMargin = (nScreenHeight - finalh) / 2;
                    pRelativeLayout.setLayoutParams(lpParent);

                    RelativeLayout.LayoutParams lpWelcome = new RelativeLayout.LayoutParams(finalw, finalh);
                    pWelcomeImage.setLayoutParams(lpWelcome);

                    pWelcomeImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    pWelcomeImage.setImageBitmap(m_bitmap);

                    RelativeLayout.LayoutParams timeLp = (RelativeLayout.LayoutParams) pTimeLayout.getLayoutParams();
                    timeLp.rightMargin = timeLp.rightMargin - lpParent.leftMargin;
                    pTimeLayout.setLayoutParams(timeLp);
                }
            }
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        } finally {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 初始化预先请求
                    dealPreReq();
                    //软件启动时清空队列，防止关闭后快速启动导致没来得及清空
                    JiuyiActivityManager.popAllActivity();
                    //软件启动时删除网页缓存
//                    CacheManager.cleanAppWebCacheFiles(Rc.getApplication());

                }
            }).start();
        }
    }

    /**
     * 处理预先请求的功能号
     */
    public void dealPreReq() {
        HeadPageCallBack pcallback = new HeadPageCallBack() {

            @Override
            public int getWidth() {
                return nScreenWidth;
            }

            @Override
            public int getHeight() {
                return nScreenHeight;
            }

            @Override
            public Activity getActivity() {
                return JiuyiHeadPageActivity.this;
            }

            @Override
            public View getView() {
                return pRelativeLayout;
            }

            @Override
            public int getUpVersionChangePageType() {
                return pJiuyiDealRootInit.getmUpVersionChangePageType();
            }

            @Override
            public jiuyiDealRootInit getTztDealRootInit() {
                return pJiuyiDealRootInit;
            }

            @Override
            public Bundle getBundle() {
                return mBundle;
            }
        };
        //处理与启动相关的请求和操作
        pJiuyiDealRootInit = new jiuyiDealRootInit(pcallback);
        pJiuyiDealRootInit.onInitAll();
    }

    /**
     * 计算Welcome图片的size
     */
    public int[] CaltWelcomeSize(JiuyiImage img, int w, int h) {
        int finalw, finalh;

        float imgbl = (float) img.getHeight() / (float) img.getWidth();
        float bodybl = (float) nScreenHeight / (float) nScreenWidth;

        if (imgbl >= bodybl) {
            finalw = nScreenWidth;
            finalh = (int) (finalw * imgbl);
        } else {
            finalh = nScreenHeight;
            finalw = (int) (finalh / imgbl);
        }

        return new int[]{finalw, finalh};
    }


    /**
     * 获取初始化尺寸后显示pWelcomeImage
     *
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    onSetImage();
                    break;
                case 1:
                    isChangePaged = true;
                    pJiuyiDealRootInit.ChangeRootPage(JiuyiHeadPageActivity.this);
                    break;
            }
        }
    };
    /**
     * 获取初始化尺寸线程
     *
     */
    private class initRectThread extends Thread {
        public void run() {
            readScreenSize();

            if (nScreenWidth > 0 && nScreenHeight > 0) {
                handler.sendMessage(Message.obtain(handler, 0));
            } else {
                while (true) {
                    try {
                        synchronized (this) {
                            nScreenWidth = pRelativeLayout.getWidth();
                            nScreenHeight = pRelativeLayout.getHeight();
                            if (nScreenWidth > 0 && nScreenHeight > 0) {
                                handler.sendMessage(Message.obtain(handler, 0));
                                saveScreenSize();
                                break;
                            }
                        }
                    } catch (Exception e) {
                        JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
                    }
                }
            }
        }
        /**
         * 保存屏幕的尺寸
         */
        private void saveScreenSize() {
            //实例化SharedPreferences对象（第一步）
            SharedPreferences mySharedPreferences = getSharedPreferences("screensizeconfig", Activity.MODE_PRIVATE);
            //实例化SharedPreferences.Editor对象（第二步）
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            //用putString的方法保存数据
            editor.putInt("screenwidth", nScreenWidth);
            editor.putInt("screenheight", nScreenHeight);

            DisplayMetrics dm = JiuyiHeadPageActivity.this.getResources().getDisplayMetrics();
            editor.putFloat("density", dm.density);

            //提交当前数据
            editor.commit();
        }

        /**
         * 获取屏幕的尺寸
         */
        private void readScreenSize() {
            DisplayMetrics dm = JiuyiHeadPageActivity.this.getResources().getDisplayMetrics();
            float density = dm.density;

            //实例化SharedPreferences对象（第一步）
            SharedPreferences mySharedPreferences = getSharedPreferences("screensizeconfig", Activity.MODE_PRIVATE);
            float fLastDestiney = mySharedPreferences.getFloat("density", 0);
            if (density == fLastDestiney) {
                nScreenWidth = mySharedPreferences.getInt("screenwidth", 0);
                nScreenHeight = mySharedPreferences.getInt("screenheight", 0);
            }
        }
    }



    /**
     * 正在弹出升级对话框或者其他对话框供用户选择，待选择后再跳转
     * 按确定键时是不允许在跳转到首页去的，直接退出软件
     * 安返回建，如果是强制升级则退出软件；如果是建议升级则继续请求并正常跳转
     * false表示当前没有升级dialog或已经关闭，true表示已经弹出了升级dialog但是尚没有关闭
     */
    public void setUpVersionDialogForbiddenChangePage() {
        // 允许跳转
        pJiuyiDealRootInit.setUpVersionDialogForbiddenChangePage();

        if (pJiuyiDealRootInit.getmUpVersionChangePageType() == 1||pJiuyiDealRootInit.getmUpVersionChangePageType() == 0) {
            pJiuyiDealRootInit.ChangeRootPage(this);
        } else if (pJiuyiDealRootInit.getmUpVersionChangePageType() > 0) {
            pJiuyiDealRootInit.ChangePage(this, pJiuyiDealRootInit.getmUpVersionChangePageType());
        }
    }

    public void setUpVersionDialogNotAllowChangePage() {
        // 不允许跳转
        isChangePaged = false;
        pJiuyiDealRootInit.setUpVersionDialogNotAllowChangePage();
    }


    private void init(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //跳转登陆
                Intent intent = new Intent(getApplicationContext(), JiuyiLoginActivity.class);
                startActivityForResult(intent, LOGIN_RESULT_CODE);
                JiuyiHeadPageActivity.this.finish();

            }
        }, 1000);
    }
    public  void  getDictList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                QueryConditionBean queryConditionBean=new QueryConditionBean();
                queryConditionBean.setNumber(0);
                queryConditionBean.setSize(10000);
                queryConditionBean.setDirection("ASC");
                queryConditionBean.setProperty("id");
                List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
                List<Integer> value2 = new ArrayList<Integer>();
                QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
                rulesBean2.setField("cached");
                rulesBean2.setOption("EQ");
                value2.add(1);
                rulesBean2.setValues(value2);
                rulesBeanList.add(rulesBean2);
                queryConditionBean.setRules(rulesBeanList);
                JiuyiHttp.POST("dict/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .request(new ACallback<DictResultBean>() {
                            @Override
                            public void onSuccess(DictResultBean data) {
                                if(data!=null){
                                    List<DictResultBean.ContentBean> ContentBeanlist=data.getContent();
                                    List<DictBean> dictBeanList=new ArrayList<>();
                                    if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                                        DbHelper.getInstance().dictBeanLongDBManager().deleteAll();
                                        for(DictResultBean.ContentBean contentBean:ContentBeanlist){
                                            DictBean dictBean =new DictBean();
                                            dictBean.setOriginalid(contentBean.getId());
                                            dictBean.setDesp(contentBean.getDesp());
                                            dictBean.setName(contentBean.getName());
                                            dictBean.setKey(contentBean.getKey());
                                            dictBean.setRemark(contentBean.getRemark());
                                            dictBean.setValue(contentBean.getValue());
                                            dictBeanList.add(dictBean);
                                        }
                                    }
                                    if(dictBeanList!=null && dictBeanList.size()>0){
                                        DbHelper.getInstance().dictBeanLongDBManager().insertInTx(dictBeanList);
                                    }
                                }

                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {

                            }
                        });


            }
        }).start();

    }
    public void changeLogin(){
        Intent intent = new Intent(getApplicationContext(), JiuyiLoginActivity.class);
        startActivityForResult(intent, LOGIN_RESULT_CODE);
        JiuyiHeadPageActivity.this.finish();
    }

    public void getLoginInfo(){
        if(!Func.IsStringEmpty(JiuyiMobileRegistShared.getIns().mToken)){
            Rc.id_tokenparam= JiuyiMobileRegistShared.getIns().mToken;
        }
        if(!Func.IsStringEmpty(JiuyiMobileRegistShared.getIns().mName)){
            Rc.name= JiuyiMobileRegistShared.getIns().mName;
        }
        if(!Func.IsStringEmpty(JiuyiMobileRegistShared.getIns().mUsername)){
            Rc.userName= JiuyiMobileRegistShared.getIns().mUsername;
        }
        if(JiuyiMobileRegistShared.getIns().mId>0){
            Rc.id= JiuyiMobileRegistShared.getIns().mId;
        }
        if(!Func.IsStringEmpty(JiuyiMobileRegistShared.getIns().mTimIdentify)){
            Rc.tim_identifier= JiuyiMobileRegistShared.getIns().mTimIdentify;
        }
        if(!Func.IsStringEmpty(JiuyiMobileRegistShared.getIns().mTimSignature)){
            Rc.tim_signature= JiuyiMobileRegistShared.getIns().mTimSignature;
        }
        if(!Func.IsStringEmpty(JiuyiMobileRegistShared.getIns().mMobileCode)){
            Rc.mobilecode= JiuyiMobileRegistShared.getIns().mMobileCode;
        }
        if(JiuyiMobileRegistShared.getIns().mDeptid>0){
            Rc.deptid= JiuyiMobileRegistShared.getIns().mDeptid;
        }
        if(!Func.IsStringEmpty(JiuyiMobileRegistShared.getIns().mDeptname)){
            Rc.deptName= JiuyiMobileRegistShared.getIns().mDeptname;
        }
    }

    public void autoLogin(){
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        int loglvl = pref.getInt("loglvl", TIMLogLevel.DEBUG.ordinal());
        //初始化IMSDK
        InitBusiness.start(getApplicationContext(),loglvl);
        //初始化TLS
        TlsBusiness.init(getApplicationContext());
        String id =  TLSService.getInstance().getLastUserIdentifier();
        if(!Func.IsStringEmpty(Rc.tim_identifier)){
            UserInfo.getInstance().setId(Rc.tim_identifier);
        }
        if(!Func.IsStringEmpty(Rc.tim_signature)){
            UserInfo.getInstance().setUserSig(Rc.tim_signature);
        }
        RequestMemberChoose.getIns().getMemberchooseList();
        RequestServiceDate.getIns().getServerDate();
        Rc.mInitAddresslist=true;
        JiuyiActivityManager.startNextActivity(JiuyiHeadPageActivity.this, JiuyiActivityManager.getActivityByPageType(mBundle, Pub.m_nStartHomePage), mBundle, false);
        JiuyiPasswordLockShared.getIns().setM_bLockNeed(true);
        if(JiuyiPasswordLockShared.getIns().getOpenPasswordLock() && JiuyiPasswordLockShared.getIns().isM_bLockNeed()){
            JiuyiActivityManager.startNextActivity(JiuyiHeadPageActivity.this, JiuyiActivityManager.getActivityByPageType(mBundle, Pub.PasswordLock_Lock), mBundle, false);
        }
       /* if(!Func.IsStringEmpty(Rc.tim_identifier) && !Func.IsStringEmpty(Rc.tim_signature)){
            imLogin();//先屏蔽登录
        }else{
            Rc.mInitAddresslist=true;
            JiuyiActivityManager.startNextActivity(JiuyiHeadPageActivity.this, JiuyiActivityManager.getActivityByPageType(mBundle, Pub.m_nStartHomePage), mBundle, false);
            JiuyiPasswordLockShared.getIns().setM_bLockNeed(true);
            if(JiuyiPasswordLockShared.getIns().getOpenPasswordLock() && JiuyiPasswordLockShared.getIns().isM_bLockNeed()){
                JiuyiActivityManager.startNextActivity(JiuyiHeadPageActivity.this, JiuyiActivityManager.getActivityByPageType(mBundle, Pub.PasswordLock_Lock), mBundle, false);
            }
        }*/

    }



    /**
     * 清楚所有通知栏通知
     */
    private void clearNotification(){
        NotificationManager notificationManager = (NotificationManager) this
                .getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        MiPushClient.clearNotification(getApplicationContext());
    }

    public interface HeadPageCallBack {
        int getWidth();

        int getHeight();

        Activity getActivity();

        View getView();

        //弹出升级提示框过程中，界面的跳转类型
        int getUpVersionChangePageType();

        jiuyiDealRootInit getTztDealRootInit();

        Bundle getBundle();
    }

    public void imLogin() {
        //登录之前要初始化群和好友关系链缓存
        TIMUserConfig userConfig = new TIMUserConfig();
        userConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {

                Toast.makeText(JiuyiHeadPageActivity.this, "您的账号已在另外一台设备登录！", Toast.LENGTH_SHORT).show();
                Rc.ResetLogin(JiuyiHeadPageActivity.this);
                if(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()!=null){
                    Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.ResetLogin,false);
                }else{
                    changeLogin();
                }
            }

            @Override
            public void onUserSigExpired() {
                //票据过期，需要重新登录
                Toast.makeText(JiuyiHeadPageActivity.this, "票据过期，需要重新登录！", Toast.LENGTH_SHORT).show();
                Rc.ResetLogin(JiuyiHeadPageActivity.this);
                if(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()!=null){
                    Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.ResetLogin,false);
                }else{
                    changeLogin();
                }

            }
        })
                .setConnectionListener(new TIMConnListener() {
                    @Override
                    public void onConnected() {
                        Log.i("imlogin", "onConnected");
                    }

                    @Override
                    public void onDisconnected(int code, String desc) {
                        Log.i("imlogin", "onDisconnected");
                    }

                    @Override
                    public void onWifiNeedAuth(String name) {
                        Log.i("imlogin", "onWifiNeedAuth");
                    }
                });

        //设置刷新监听
        RefreshEvent.getInstance().init(userConfig);
        userConfig = FriendshipEvent.getInstance().init(userConfig);
        userConfig = GroupEvent.getInstance().init(userConfig);
        userConfig = MessageEvent.getInstance().init(userConfig);
        TIMManager.getInstance().setUserConfig(userConfig);
        LoginBusiness.loginIm(UserInfo.getInstance().getId(), UserInfo.getInstance().getUserSig(), new TIMCallBack() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onError(int code, String desc) {
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                Log.d("imloginin", "login failed. code: " + code + " errmsg: " + desc);

                switch (code) {
                    case 6208:
                        //离线状态下被其他终端踢下线
//                        NotifyDialog dialog = new NotifyDialog();
//                        dialog.show(getString(R.string.kick_logout), getSupportFragmentManager(), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
////                                navToHome();
//                            }
//                        });
                        break;
                    case 6200:
                        Toast.makeText(JiuyiHeadPageActivity.this, getString(R.string.login_error_timeout), Toast.LENGTH_SHORT).show();
                        Rc.ResetLogin(JiuyiHeadPageActivity.this);
                        if(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()!=null){
                            Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.ResetLogin,false);
                        }else{
                            changeLogin();
                        }
                        break;
                    default:
                        Toast.makeText(JiuyiHeadPageActivity.this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                        Rc.ResetLogin(JiuyiHeadPageActivity.this);
                        if(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()!=null){
                            Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.ResetLogin,false);
                        }else{
                            changeLogin();
                        }
                        break;
                }

            }

            @Override
            public void onSuccess() {
                Log.d("imloginin", "login succ");
                //初始化程序后台后消息推送
                PushUtil.getInstance();
                //初始化消息监听
                MessageEvent.getInstance();
                String deviceMan = android.os.Build.MANUFACTURER;
                //注册小米和华为推送
                if (deviceMan.equals("Xiaomi") && shouldMiInit()){
                    //小米开发账号
                    MiPushClient.registerPush(getApplicationContext(), "2882303761517753570", "5751775360570");
                }else if (deviceMan.equals("HUAWEI")){
                    PushManager.requestToken(JiuyiHeadPageActivity.this);
                }
                //魅族推送只适用于Flyme系统,因此可以先行判断是否为魅族机型，再进行订阅，避免在其他机型上出现兼容性问题
                if(MzSystemUtils.isBrandMeizu(getApplicationContext())){
                    com.meizu.cloud.pushsdk.PushManager.register(getApplicationContext(), "116116", "10491c7d5230462e9e1e73f95b6729ab");
                }
                TIMOfflinePushSettings settings = new TIMOfflinePushSettings();
                settings.setEnabled(true);
                TIMManager.getInstance().setOfflinePushSettings(settings);
                Rc.mInitAddresslist=true;
                JiuyiActivityManager.startNextActivity(JiuyiHeadPageActivity.this, JiuyiActivityManager.getActivityByPageType(mBundle, Pub.m_nStartHomePage), mBundle, false);
                JiuyiPasswordLockShared.getIns().setM_bLockNeed(true);
                if(JiuyiPasswordLockShared.getIns().getOpenPasswordLock() && JiuyiPasswordLockShared.getIns().isM_bLockNeed()){
                    JiuyiActivityManager.startNextActivity(JiuyiHeadPageActivity.this, JiuyiActivityManager.getActivityByPageType(mBundle, Pub.PasswordLock_Lock), mBundle, false);
                }
            }
        });
    }

    /**
     * 判断小米推送是否已经初始化
     */
    private boolean shouldMiInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}