package com.control.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.control.utils.Func;
import com.control.utils.JiuyiLog;

/**
 * ****************************************************************
 * 文件名称:JiuyiSharedBase.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:保存数据到SharedPreferences和从SharedPreferences读取数据解密
 * 注意事项:若要创建内部类的实例，需要有外部类的实例才行，

 * ****************************************************************
 */

public class JiuyiSharedBase {
    /** 保存的编码 */
    private final String UTF8 = "UTF-8";

    /**
     * 保存SharedPreferences的文件名
     */
    public enum jiuyiSharedStruct {
        WebTextSize,//详细页设置字号
        RcParam,//Rc里需要保持的参数
        UUID,//IMEI不存在是使用UUID
        PasswordLock,//密码锁定
        WindowFlagParamShared,//屏幕参数，屏幕常亮
        MobileCodeRegist,//手机号码验证码
        MemberchooseCodition,//客户筛选条件
        UpdateInfoData,//升级信息
    }
    /**
     * 保存数据
     * @param context
     * @param sharednane   要保存的文件名
     * @param data          要保存的数据
     */
    public void onSaveData(final Context context, final String sharednane, final String data)
    {
        if(context == null || data == null || Func.IsStringEmpty(sharednane))
            return;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] encyptBytes = data.getBytes();
                    String encyptString=new String(Base64.encodeToString(encyptBytes, Base64.DEFAULT));

                    //第三步:将String保持至SharedPreferences
                    SharedPreferences sharedPreferences= context.getSharedPreferences(sharednane, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("data", encyptString);
                    editor.commit();
                    JiuyiLog.e("sharednaneJSON","set:"+sharednane+";data="+data);
                } catch (Exception e) {
                    JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
                    return;
                }
            }
        }).start();
    }

    /**
     * 获取保存的数据
     * @param context
     */
    public String onGetData(Context context, String sharednane)
    {
        if(context == null || Func.IsStringEmpty(sharednane))
            return "";

        try {
            SharedPreferences sharedPreferences=context.getSharedPreferences(sharednane, Context.MODE_PRIVATE);
            //第一步:取出字符串形式的Bitmap
            String encyptString=sharedPreferences.getString("data", "");
            //第二步:利用Base64将字符串转换为ByteArrayInputStream
            byte[] encyptBytes =Base64.decode(encyptString, Base64.DEFAULT);
            String data = new String(encyptBytes);
            JiuyiLog.e("sharednaneJSON","get:"+sharednane+";data="+data);
            return data;
        } catch (Exception e) {
            return "";
        }
    }
}
