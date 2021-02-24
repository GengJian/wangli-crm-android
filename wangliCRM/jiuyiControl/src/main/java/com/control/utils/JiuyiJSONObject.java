package com.control.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ****************************************************************
 * 文件名称:JiuyiJSONObject.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:
 * ****************************************************************
 */

public class JiuyiJSONObject extends JSONObject {
    public JiuyiJSONObject(){
        super();
    }

    public JiuyiJSONObject(String json) throws JSONException {
        super(json);
        JiuyiLog.e("JiuyiJSONObject", json);
    }

}
