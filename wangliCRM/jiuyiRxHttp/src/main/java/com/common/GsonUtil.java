package com.common;

import com.google.gson.Gson;

/**
 * 文件描述: Gson单例操作
 */
public class GsonUtil {
    private static Gson gson;

    public static Gson gson() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }
}
