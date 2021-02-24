package com.control.shared;

import android.content.Context;

import com.control.utils.Func;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.control.utils.JiuyiJSONObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ****************************************************************
 * 文件名称:JiuyiMobileRegistShared.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:手机号码注册类
 * ****************************************************************
 */

public class JiuyiMobileRegistShared extends JiuyiSharedBase {
    private static JiuyiMobileRegistShared ins;
    /** 手机号码 */
    public String mMobileCode;
    public String mToken;
    public String mTimIdentify;
    public String mTimSignature;
    /** 验证码 */
    public String mCheckKey;
    /** 是否通过了验证 */
    public boolean mIsPWChecked;
    public boolean mIsPWCheckPass;
    /** 是否是自动登录 */
    public boolean mIsAutoLogin = true;

    public String mName;// 账号
    public String mUsername;// 用户名
    public String mDeptname; // 部门
    public long mId;//用户id
    public long mDeptid = -1;// 部门idU

    /**
     * 构造函数，并获取保存的数据
     */
    public JiuyiMobileRegistShared(){
        onGetData(Rc.getApplication());
    }
    /**
     * 获取MobileRegist实例
     * @return
     */
    public static JiuyiMobileRegistShared getIns(){
        if(ins == null)
            ins = new JiuyiMobileRegistShared();
        return ins;
    }

    /**
     * 清空数据
     */
    public void clearData(){
        mMobileCode = "";
        mToken="";
        mTimIdentify="";
        mTimSignature="";
        mCheckKey = "";
        mIsPWChecked = false;
        mIsPWCheckPass = false;
        mName="";
        mUsername="";
        mDeptname="";
        mId=-1;
        mDeptid = -1;
        onSaveData(Rc.getApplication());
    }

    /**
     * 保存登录信息
     * @param context
     */
    public void onSaveData(Context context)
    {
        if(context == null)
            return;

        super.onSaveData(context, jiuyiSharedStruct.MobileCodeRegist.name(), getJsonString());
    }

    /**
     * 获取手机注册信息
     * @param context
     */
    public void onGetData(Context context)
    {
        if(context == null)
            return;

        String data = super.onGetData(context, jiuyiSharedStruct.MobileCodeRegist.name());
        if(!Func.IsStringEmpty(data)){
            try {
                JSONObject json = new JiuyiJSONObject(data);
                mMobileCode = json.optString("mobilecode", "");
                mToken = json.optString("token", "");
                mTimIdentify = json.optString("timidentify", "");
                mTimSignature = json.optString("timsignature", "");
                mCheckKey = json.optString("checkkey", "");
                mIsPWCheckPass = json.optBoolean("ispwcheckpass", false);
                mIsAutoLogin = json.optBoolean("autologin", mIsAutoLogin);
                mName = json.optString("name", "");
                mUsername = json.optString("username", "");
                mDeptname = json.optString("deptname", "");
                mId = json.optLong("id", -1);
                mDeptid = json.optLong("deptid", -1);
            } catch (JSONException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            }
        }
        if (mIsAutoLogin && !Func.IsStringEmpty(mMobileCode))
            mIsPWChecked = true;
    }

    /**
     * 把参数转换为JSON格式保存
     * @return JSON格式数据
     */
    public String getJsonString(){
        try {
            JSONObject json = new JiuyiJSONObject();
            json.put("mobilecode", mMobileCode);
            json.put("token", mToken);
            json.put("timidentify", mTimIdentify);
            json.put("timsignature", mTimSignature);
            json.put("checkkey", mCheckKey);
            json.put("ispwcheckpass", mIsPWCheckPass);
            json.put("autologin", mIsAutoLogin);
            json.put("name", mName);
            json.put("username", mUsername);
            json.put("deptname", mDeptname);
            json.put("id",mId);
            json.put("deptid",mDeptid);
            return json.toString();
        } catch (JSONException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
        return "";
    }
}
