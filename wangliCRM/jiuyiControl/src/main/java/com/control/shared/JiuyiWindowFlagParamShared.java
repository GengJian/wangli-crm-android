package com.control.shared;

import android.text.TextUtils;

import com.control.utils.JiuyiJSONObject;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ****************************************************************
 * 文件名称 : JiuyiWindowFlagParamShared
 * 作    者 : zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述 : 屏幕参数，屏幕常亮
 * ****************************************************************
 */
public class JiuyiWindowFlagParamShared extends JiuyiSharedBase {
    /**
     * 是否保持屏幕常亮,1:开启，0关闭。默认开启
     */
    private static int mKeepScreenOnDefault = 1;

    /**
     * 保存
     * @param mKeepScreenOn 是否保持屏幕常亮
     */
    public void onSaveData(int mKeepScreenOn) {
        try {
            JSONObject json = new JiuyiJSONObject();
            json.put("keepscreenon", mKeepScreenOn);
            super.onSaveData(Rc.getApplication(), jiuyiSharedStruct.WindowFlagParamShared.name(), json.toString());
        } catch (JSONException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }

    /**
     * 获取是否保持屏幕常亮,1:开启，0关闭。默认开启
     */
    public int onGetData() {
        String data = super.onGetData(Rc.getApplication(), jiuyiSharedStruct.WindowFlagParamShared.name());
        if (!TextUtils.isEmpty(data)) {
            try {
                JSONObject json = new JiuyiJSONObject(data);
                if (json.has("keepscreenon"))
                    return json.getInt("keepscreenon");
            } catch (JSONException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
                return mKeepScreenOnDefault;
            }
        }
        return mKeepScreenOnDefault;
    }
}
