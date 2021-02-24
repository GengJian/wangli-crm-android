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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanglicrm.android.R;
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
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.keyboard.JiuyiKeyBoardManager;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.huawei.android.pushagent.PushManager;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiActivityManager;
import com.jiuyi.model.AuthenticateBean;
import com.jiuyi.model.ChangePasswordBean;
import com.jiuyi.model.OperatorLoginBean;
import com.jiuyi.model.SuccessResult;
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
import messages.timchat.model.UserInfo;
import messages.timchat.utils.PushUtil;

//import com.peng.one.push.OnePush;


/**
 * ****************************************************************
 * 文件名称 : JiuyiLoginActivity
 * 作    者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 登录界面
 * ****************************************************************
 */

public class JiuyiChangePasswordActivity extends JiuyiActivityBase {

    private LoadingDialog dialog1;
    private JiuyiEditText etOldpassword;
    private JiuyiEditText etNewpassword;
    private JiuyiEditText etConfirm;
    private JiuyiButton btSure;
    private TextView mtvcomplete;
    private ImageView jiuyi_titlebar_navbarbackbg;
    private String oldPassword,newPassword,confirmPassword;
    private ChangePasswordBean changePasswordBean;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_changepassword_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        mtvcomplete.setVisibility(View.INVISIBLE);
        jiuyi_titlebar_navbarbackbg = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        jiuyi_titlebar_navbarbackbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });

        etOldpassword = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_oldpassword);
        etNewpassword = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_newpassword);
        etConfirm = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_confirm);
        btSure = (JiuyiButton) mBodyLayout.findViewById(R.id.bt_sure);
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPassword=etOldpassword.getText().toString();
                newPassword=etNewpassword.getText().toString();
                confirmPassword=etConfirm.getText().toString();
                if(TextUtils.isEmpty(oldPassword)){
                    startDialog(DialogID.DialogDoNothing, "", "请输入原密码！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                if(TextUtils.isEmpty(newPassword)){
                    startDialog(DialogID.DialogDoNothing, "", "请输入密码！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                if(TextUtils.isEmpty(newPassword)){
                    startDialog(DialogID.DialogDoNothing, "", "请输入确认密码！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                if(!newPassword.equals(confirmPassword)){
                    startDialog(DialogID.DialogDoNothing, "", "两次密码不一致！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                if(oldPassword.equals(newPassword)){
                    startDialog(DialogID.DialogDoNothing, "", "新密码和原密码一样！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                if(newPassword.length()<6){
                    startDialog(DialogID.DialogDoNothing, "", "新密码长度必须大于5位！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                changePasswordBean=new ChangePasswordBean();
                changePasswordBean.setOldPwd(oldPassword);
                changePasswordBean.setNewPwd(newPassword);
                showDialog();
                UpdatePassword();
            }
        });
    }


    public void setTitle(){
        mTitle="修改登录密码";
        setTitle(mTitle);
    }

    public void UpdatePassword(){
        JiuyiHttp.PUT("operator/update-password")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(changePasswordBean))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        if(data!=null){
                            Toast.makeText(JiuyiChangePasswordActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                            backPage();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }


}
