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
 * 文件名称:JiuyiWebTextSizeShared.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:网页设置字体
 * ****************************************************************
 */

public class JiuyiWebTextSizeShared extends JiuyiSharedBase {
    private static JiuyiWebTextSizeShared ins;
    private int mTextSize = 100;

    public JiuyiWebTextSizeShared(){
        if(ins == null)
            ins = JiuyiWebTextSizeShared.this;
        onGetData(Rc.getApplication());
    }

    public static JiuyiWebTextSizeShared getIns(){
        if(ins == null)
            new JiuyiWebTextSizeShared();
        return ins;
    }

    public int getTextSize(){
        return mTextSize;
    }
    /**
     * 保存字号
     * @param context
     * @param nTextSize
     */
    public void onSaveData(Context context, int nTextSize)
    {
        if(context == null)
            return;
        mTextSize = nTextSize;
        try {
            JSONObject json = new JiuyiJSONObject();
            json.put("DetailWebTextSize", nTextSize);
            super.onSaveData(context, jiuyiSharedStruct.WebTextSize.name(), json.toString());
        } catch (JSONException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
    }

    public void onGetData(Context context)
    {
        String data = super.onGetData(context, jiuyiSharedStruct.WebTextSize.name());
        if(!Func.IsStringEmpty(data)){
            try {
                JSONObject json = new JiuyiJSONObject(data);
                mTextSize = json.optInt("DetailWebTextSize", mTextSize);
            } catch (JSONException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
                mTextSize = 100;
            }
        }
    }

}
