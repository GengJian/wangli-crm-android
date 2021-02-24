package com.jiuyi.activity.common.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDialog;
import com.common.GsonUtil;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiMobileRegistShared;
import com.control.tools.JiuyiHandler;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.keyboard.JiuyiKeyBoardManager;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.gyf.barlibrary.ImmersionBar;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.huawei.android.pushagent.PushManager;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiActivityManager;
import com.jiuyi.model.AuthenticateBean;
import com.jiuyi.model.OperatorLoginBean;
import com.jiuyi.model.SuccessResult;
//import com.peng.one.push.OnePush;
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
import com.tencent.qcloud.ui.NotifyDialog;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;
import commonlyused.db.DbHelper;
import customer.request.RequestMemberChoose;
import customer.request.RequestServiceDate;
import messages.timchat.model.UserInfo;
import messages.timchat.utils.PushUtil;


/**
 * ****************************************************************
 * 文件名称 : JiuyiLoginActivity
 * 作    者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 登录界面
 * ****************************************************************
 */

public class JiuyiLoginActivity extends JiuyiActivityBase {
    private JiuyiButton mBtnlogin;
    private String sUsername="",sPassword="";
    private TextView  mTvtelmanager;


    /** 能再次获取验证码的周期 */
    private int mCountDownTime = 60000;
    /** 获取验证码的周期的定时器 */
    private widgetCountDownTime mCountDownTimer;

    /** 手机号码输入框 */
    private JiuyiEditText mMobileEditText;

    /** 验证码输入框 */
    private JiuyiEditText mYanZhengMaEditText;
    /** 验证码的图片 */
    private TextView mImageYanZhengMa;
    /** 验证码的图片上的文字 */
    private String mYanZhengMaButtonHint = "获取验证码";
    /** 登录前的手机号码*/
    private String sMobileCode="";
    private LoadingDialog dialog1;

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_login_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        mCountDownTimer = new widgetCountDownTime(mCountDownTime, 1000);


        //手机号码
        mMobileEditText = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "edit_phonenumber"));
//        mMobileEditText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        if(!Func.IsStringEmpty(JiuyiMobileRegistShared.getIns().mMobileCode)){
            sMobileCode= JiuyiMobileRegistShared.getIns().mMobileCode;
        }
        mMobileEditText.setText(sMobileCode);
        SetTextChanged(mMobileEditText);
        mTvtelmanager=(TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_tel_manager2"));


        //验证码
        mYanZhengMaEditText = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "edit_password"));
//        mYanZhengMaEditText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        //验证码图片
        mImageYanZhengMa = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "image_yanzhengma"));
        //按钮是否可用
        if(Func.IsPhoneNum(sMobileCode) && sMobileCode.length() == 11){
            mImageYanZhengMa.setBackgroundResource(Res.getDrawabelID(null, "jiuyi_v23_button_bg_normal"));
        }else {
            mImageYanZhengMa.setBackgroundResource(Res.getDrawabelID(null, "jiuyi_loginbutton_bg_normal"));
        }

        mImageYanZhengMa.setOnClickListener(mYanZhengMaClickListener);
        SpannableString ss = new SpannableString("联系管理员");
        ss.setSpan(new ForegroundColorSpan(Color.WHITE), 0, ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvtelmanager.setText(ss);
        mTvtelmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
        mBtnlogin=(JiuyiButton)mBodyLayout.findViewById(Res.getViewID(null,"iamge_login"));
        mBtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sUsername=mMobileEditText.getText().toString();
                sPassword=mYanZhengMaEditText.getText().toString();
                if(TextUtils.isEmpty(sUsername)){
                    startDialog(DialogID.DialogDoNothing, "", "请输入账号！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                if(TextUtils.isEmpty(sPassword)){
                    startDialog(DialogID.DialogDoNothing, "", "请输入密码！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
//                if(sPassword.length()!=6){
//                    startDialog(DialogID.DialogDoNothing, "", "请输入6位验证码！", JiuyiDialogBase.Dialog_Type_Yes, null);
//                    return;
//                }
                LoadingDialog.Builder builder1=new LoadingDialog.Builder(JiuyiLoginActivity.this)
                        .setMessage("登录中...")
                        .setCancelable(false);
                 dialog1=builder1.create();
                dialog1.show();
                authenticate_post(sUsername,sPassword);
            }
        });
        setTitle();
        setContentView(mBodyLayout);
        ImmersionBar.with(this)
                .transparentBar()
                .init();

        //申请位置和存在权限
        mBodyLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                String[] list = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                new JiuyiHiPermissionUtil(JiuyiLoginActivity.this).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack(){
                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
            }
        }, 100);
    }
    public void imLogin() {
        //登录之前要初始化群和好友关系链缓存
        TIMUserConfig userConfig = new TIMUserConfig();
        userConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                Toast.makeText(JiuyiLoginActivity.this, "您的账号已在另外一台设备登录！", Toast.LENGTH_SHORT).show();
                Rc.ResetLogin(JiuyiLoginActivity.this);
                changePage(null,Pub.ResetLogin,false);
            }

            @Override
            public void onUserSigExpired() {
                //票据过期，需要重新登录
                new NotifyDialog().show(getString(R.string.tls_expire), getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Rc.ResetLogin(JiuyiLoginActivity.this);
                        changePage(null,Pub.ResetLogin,false);
                    }
                });
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
            @Override
            public void onError(int code, String desc) {
                //错误码code和错误描述desc，可用于定位请求失败原因
                //错误码code列表请参见错误码表
                Log.d("imloginin", "login failed. code: " + code + " errmsg: " + desc);
                dialog1.dismiss();

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
//                        Toast.makeText(this,getString(R.string.login_error_timeout),Toast.LENGTH_SHORT).show();
//                        navToLogin();
                        Toast.makeText(JiuyiLoginActivity.this, getString(R.string.login_error_timeout), Toast.LENGTH_SHORT).show();
                        Rc.ResetLogin(JiuyiLoginActivity.this);
                        Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.ResetLogin,false);
                        break;
                    default:
                        Toast.makeText(JiuyiLoginActivity.this, getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                        Rc.ResetLogin(JiuyiLoginActivity.this);
                        Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.ResetLogin,false);
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
                String id=MiPushClient.getRegId(JiuyiLoginActivity.this);
//                注册小米和华为推送
                if (deviceMan.equals("Xiaomi") && shouldMiInit()){
                    MiPushClient.registerPush(getApplicationContext(), "2882303761517966710", "5531796668710");
                }else if (deviceMan.equals("HUAWEI")){
                    PushManager.requestToken(JiuyiLoginActivity.this);
                }
                //魅族推送只适用于Flyme系统,因此可以先行判断是否为魅族机型，再进行订阅，避免在其他机型上出现兼容性问题
                if(MzSystemUtils.isBrandMeizu(getApplicationContext())){
                    com.meizu.cloud.pushsdk.PushManager.register(getApplicationContext(), "116116", "10491c7d5230462e9e1e73f95b6729ab");
                }
                TIMOfflinePushSettings settings = new TIMOfflinePushSettings();
                settings.setEnabled(true);
                TIMManager.getInstance().setOfflinePushSettings(settings);
                Rc.mInitAddresslist=true;
                dialog1.dismiss();
                JiuyiActivityManager.startNextActivity(JiuyiLoginActivity.this, JiuyiActivityManager.getActivityByPageType(mBundle, Pub.m_nStartHomePage), mBundle, false);

            }
        });
    }
    private void getAuthenticate(String telno) {
        String url="sms/login-sms/";
        if(!Func.IsStringEmpty(telno)){
            url+=telno;
        }
        JiuyiHttp.GET(url)
                .tag("request_get_2")
                 .request(new ACallback<SuccessResult>() {
                    @Override
                    public void onSuccess(SuccessResult successResult) {
                        JiuyiLog.e("getsig","request onSuccess!");
                        if (successResult == null) {
                        }
                    }
                    @Override
                    public void onFail(int errCode, String errMsg) {
                        JiuyiLog.e("getsig","request errorCode:" + errCode + ",errorMsg:" + errMsg);
                    }
                });
    }

    private void authenticate_post(final String username, String password) {
        AuthenticateBean authenticateBean=new AuthenticateBean();
        authenticateBean.setUsername(username);
        authenticateBean.setPassword(password);
        authenticateBean.setRememberMe(true);
        JiuyiHttp.POST("authenticate")
                .setJson(GsonUtil.gson().toJson(authenticateBean))
                .request(new ACallback<OperatorLoginBean>() {
                    @Override
                    public void onSuccess(OperatorLoginBean data) {
                        String timIdentify="";
                        String timSignature="";
                        if(data!=null){
                            timSignature=data.getTim_signature();
                            if(data.getOperator()!=null){
                                Rc.getIns().id_token=data.getId_token();
                                Rc.getIns().id_tokenparam="Bearer "+Rc.getIns().id_token;
                                Rc.getIns().name=data.getOperator().getName();
                                Rc.getIns().userName=data.getOperator().getUsername();
                                Rc.getIns().id=data.getOperator().getId();
                                Rc.getIns().tim_signature=data.getTim_signature();
                                Rc.getIns().tim_identifier=data.getOperator().getTimIdentifier();
                                if(JiuyiMobileRegistShared.getIns()!=null && JiuyiMobileRegistShared.getIns().mMobileCode!=null && !JiuyiMobileRegistShared.getIns().mMobileCode.equals(username)){
                                    DbHelper.getInstance().appItemBeanLongDBManager().deleteAll();
                                    DbHelper.getInstance().linkmanGreenBeanLongDBManager().deleteAll();
                                }
                                Rc.getIns().mobilecode=username;
                                timIdentify=data.getOperator().getTimIdentifier();
                                if(data.getOperator().getDepartment()!=null){
                                    OperatorLoginBean.OperatorBean.DepartmentBean departmentBean=data.getOperator().getDepartment();
                                    Rc.getIns().deptid=departmentBean.getId();
                                    Rc.getIns().deptName=departmentBean.getName();
                                    JiuyiMobileRegistShared.getIns().mDeptname=departmentBean.getName();
                                    JiuyiMobileRegistShared.getIns().mDeptid=departmentBean.getId();
                                }
                                JiuyiMobileRegistShared.getIns().mMobileCode=username;
                                JiuyiMobileRegistShared.getIns().mToken="Bearer "+Rc.getIns().id_token;
                                JiuyiMobileRegistShared.getIns().mTimIdentify=timIdentify;
                                JiuyiMobileRegistShared.getIns().mTimSignature=timSignature;
                                JiuyiMobileRegistShared.getIns().mName=data.getOperator().getName();
                                JiuyiMobileRegistShared.getIns().mUsername=data.getOperator().getUsername();
                                JiuyiMobileRegistShared.getIns().mId=data.getOperator().getId();
                                JiuyiMobileRegistShared.getIns().onSaveData(JiuyiLoginActivity.this);
                            }
                        }
                        JiuyiLog.e("httplogin","request onSuccess!" + data);
                        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                        int loglvl = pref.getInt("loglvl", TIMLogLevel.DEBUG.ordinal());
                        //初始化IMSDK
                        InitBusiness.start(getApplicationContext(),loglvl);
                        //初始化TLS
                        TlsBusiness.init(getApplicationContext());
                        String id =  TLSService.getInstance().getLastUserIdentifier();
                       if(!Func.IsStringEmpty(timIdentify)){
                           UserInfo.getInstance().setId(timIdentify);
                       }
                       if(!Func.IsStringEmpty(timSignature)){
                           UserInfo.getInstance().setUserSig(timSignature);
                       }
                        RequestMemberChoose.getIns().getMemberchooseList();
                        RequestServiceDate.getIns().getServerDate();
                        JiuyiActivityManager.startNextActivity(JiuyiLoginActivity.this, JiuyiActivityManager.getActivityByPageType(mBundle, Pub.m_nStartHomePage), mBundle, false);
                       /*if(!Func.IsStringEmpty(timIdentify) && !Func.IsStringEmpty(timSignature)){
                           imLogin();//先屏蔽登录
                       }else {
                           Toast.makeText(JiuyiLoginActivity.this, "您的Tim聊天账号还没创建，请联系管理员！", Toast.LENGTH_SHORT).show();
                           JiuyiActivityManager.startNextActivity(JiuyiLoginActivity.this, JiuyiActivityManager.getActivityByPageType(mBundle, Pub.m_nStartHomePage), mBundle, false);
                       }*/


                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        dialog1.dismiss();
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                        JiuyiLog.e("httplogin","request errorCode:" + errCode + ",errorMsg:" + errMsg);
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
    public void setTitle(){
        super.setTitle(mTitle);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                if (mBodyLayout != null) {
                    mBodyLayout.StartSystemQiutDialog();
                }
                return true;
            }
            default: {
                return super.onKeyUp(keyCode, event);
            }
        }
    }
    public void SetTextChanged(final JiuyiEditText edittext) {
        edittext.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable v) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edittext == mMobileEditText || edittext == mYanZhengMaEditText){
                    String strMobileCode = mMobileEditText.getText().toString();
                    String strYanZhengMa = mYanZhengMaEditText.getText().toString();
                    //手机号=11位，则获取验证码可用
                    if(Func.IsPhoneNum(strMobileCode) && strMobileCode.length() == 11){
                        sMobileCode = strMobileCode;
                        String bgname = Res.getDrawabelID(JiuyiLoginActivity.this, "jiuyi_v23_button_bg_normal") > 0 ? "jiuyi_v23_button_bg_normal" : "jiuyi_loginbutton_bg_normal";
                        mImageYanZhengMa.setBackgroundResource(Res.getDrawabelID(JiuyiLoginActivity.this, bgname));
                    }else{
                        sMobileCode = strMobileCode;
                        mImageYanZhengMa.setBackgroundResource(Res.getDrawabelID(JiuyiLoginActivity.this, "jiuyi_loginbutton_bg_normal"));
                    }
                    //手机号=11位且验证码不为空，则激活按钮可用
                    if(Func.IsPhoneNum(strMobileCode) && strMobileCode.length() == 11 && !TextUtils.isEmpty(strYanZhengMa)){
                        String bgname = Res.getDrawabelID(JiuyiLoginActivity.this, "jiuyi_v23_button_bg_normal") > 0 ? "jiuyi_v23_button_bg_normal" : "jiuyi_v23_button_bg_disable";
                    }
                }
            }
        });
    }

    /**
     * 1.本次弹出激活界面，发送短信功能只能点击一次
     * 2.在发送数据状态下，登入，激活按钮禁止点击。 避免重复发短信，造成的激活密码错误
     */
    public View.OnClickListener mYanZhengMaClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            sMobileCode = mMobileEditText.getText().toString();

            if(!(Func.IsPhoneNum(sMobileCode) && sMobileCode.length() == 11))
                return;
            if (!Func.IsPhoneNum(sMobileCode) || sMobileCode.length() != 11) {
                startDialog(DialogID.DialogDoNothing, "", "手机号码不正确", JiuyiDialogBase.Dialog_Type_Cancle, null);
                return;
            }

            JiuyiKeyBoardManager.getIns().closeKeyBoard();
            mCountDownTimer.setTextView(mImageYanZhengMa, mCountDownTime);
            mCountDownTimer.start();
            mImageYanZhengMa.setBackgroundResource(Res.getDrawabelID(JiuyiLoginActivity.this, "jiuyi_loginbutton_bg_normal"));
            getAuthenticate(sMobileCode);
        }
    };

    class widgetCountDownTime extends CountDownTimer {

        private TextView _vTextView;
        private long nDanWei;

        public widgetCountDownTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            nDanWei = countDownInterval;
        }

        public void setTextView(TextView textView, long nTime) {
            _vTextView = textView;
        }

        @Override
        public void onFinish() {
            _vTextView.setEnabled(true);
            _vTextView.setText(mYanZhengMaButtonHint);

            new JiuyiHandler(){
                @Override
                public void callBack() {
                    String str = mMobileEditText.getText().toString().trim();
                    String bgname = Res.getDrawabelID(JiuyiLoginActivity.this, "jiuyi_v23_button_bg_normal") > 0 ? "jiuyi_v23_button_bg_normal" : "jiuyi_v23_button_bg_disable";

                    mImageYanZhengMa.setBackgroundResource(Res.getDrawabelID(JiuyiLoginActivity.this, bgname));
                }
            }.post();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            _vTextView.setEnabled(false);
            int nNum = (int) (millisUntilFinished / nDanWei);
            _vTextView.setText("重新发送(" + nNum + "秒" + ")");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

}
