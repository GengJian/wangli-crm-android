package com.control.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * ****************************************************************
 * 文件名称 : JiuyiBmpBase64Change
 * 作    者 : zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述 : 图片与base64 之间的转换
 * ****************************************************************
 */
public class JiuyiBmpBase64Change {

    /**
     * 图片名称转base64
     * @param drawableName
     * @return
     */
    public static String drawableNameToBase64(String drawableName){
        if(TextUtils.isEmpty(drawableName))
            return "";
        int id = Res.getDrawabelID(null, drawableName);
        try {
            Bitmap bmp= BitmapFactory.decodeResource(Rc.getApplication().getResources(), id);
            return bitmapToBase64(bmp);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * bitmap转为base64
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
