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
 * 文件名称:JiuyiRcParamShared.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:Rc里需要保持的参数保存类
 * ****************************************************************
 */

public class JiuyiRcParamShared extends JiuyiSharedBase {
    Rc record;
    /**
     * 构造函数，并获取保存的数据
     */
    public JiuyiRcParamShared(Rc record){
        this.record = record;
        onGetData(Rc.getApplication());
    }

    /**
     * 保存启动页更新信息
     * @param context
     */
    public void onSaveData(Context context)
    {
        if(context == null)
            return;

        super.onSaveData(context, jiuyiSharedStruct.RcParam.name(), getJsonString());
    }

    /**
     * 获取启动页更新信息
     * @param context
     */
    public void onGetData(Context context)
    {
        if(context == null)
            return;

        String data = super.onGetData(context, jiuyiSharedStruct.RcParam.name());
        if(!Func.IsStringEmpty(data)){
            try {
                JSONObject json = new JiuyiJSONObject(data);
                record.mNoReadMsgCount = json.optInt("noreadmsgcount", 0);
                record.mRunCount = json.optInt("runcount", 0);
                record.mInputAjaxUrl = json.optString("inputajaxurl");
                record.mGjscPID = json.optString("pid");

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
            json.put("noreadmsgcount", record.mNoReadMsgCount);
            json.put("runcount", record.mRunCount);
            json.put("inputajaxurl", record.mInputAjaxUrl);
            json.put("pid", record.mGjscPID);
            return json.toString();
        } catch (JSONException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
        return "";
    }
}
