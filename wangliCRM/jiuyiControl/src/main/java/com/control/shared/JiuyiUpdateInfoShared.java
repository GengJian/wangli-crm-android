package com.control.shared;

import android.content.Context;

import com.control.utils.Func;
import com.control.utils.JiuyiJSONObject;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ****************************************************************
 * 文件名称:JiuyiMobileRegistShared.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:版本升级信息
 * ****************************************************************
 */

public class JiuyiUpdateInfoShared extends JiuyiSharedBase {
    private static JiuyiUpdateInfoShared ins;

    public String mNoAlertTime="";
    public String mCancelUpdate="";

    /**
     * 构造函数，并获取保存的数据
     */
    public JiuyiUpdateInfoShared(){
        onGetData(Rc.getApplication());
    }
    /**
     * 获取tztMobileRegist实例
     * @return
     */
    public static JiuyiUpdateInfoShared getIns(){
        if(ins == null)
            ins = new JiuyiUpdateInfoShared();
        return ins;
    }

    /**
     * 清空数据
     */
    public void clearData(){
        mNoAlertTime="";
        mCancelUpdate="";
        onSaveData(Rc.getApplication());
    }

    /**
     * 保存启动页更新信息
     * @param context
     */
    public void onSaveData(Context context)
    {
        if(context == null)
            return;

        super.onSaveData(context, jiuyiSharedStruct.UpdateInfoData.name(), getJsonString());
    }

    /**
     * 获取手机注册信息
     * @param context
     */
    public void onGetData(Context context)
    {
        if(context == null)
            return;

        String data = super.onGetData(context, jiuyiSharedStruct.UpdateInfoData.name());
        if(!Func.IsStringEmpty(data)){
            try {
                JSONObject json = new JiuyiJSONObject(data);
                mNoAlertTime = json.optString("noalertTime", "");
                mCancelUpdate = json.optString("cancelUpdate", "");
            } catch (JSONException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            }
        }

    }

    /**
     * 把参数转换为JSON格式保存
     * @return JSON格式数据
     */
    public String getJsonString(){
        try {
            JSONObject json = new JiuyiJSONObject();
            json.put("noalertTime", mNoAlertTime);
            json.put("cancelUpdate", mCancelUpdate);
            return json.toString();
        } catch (JSONException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
        return "";
    }
}
