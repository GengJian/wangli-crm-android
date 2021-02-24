
package com.control.callback;

import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;

import com.control.utils.JiuyiLog;

/**
 * ****************************************************************
 * 文件名称 : JiuyiFingerCompatMyCallBack
 * 作       者 : zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述 : 6.0及以上系统指纹解锁回调
 *****************************************************************
 */
public class JiuyiFingerCompatMyCallBack extends FingerprintManagerCompat.AuthenticationCallback{
	   private static final String TAG = "FingerMyCallBack";

       // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
       @Override
       public void onAuthenticationError(int errMsgId, CharSequence errString) {
           JiuyiLog.e(TAG, "FingerMyCallBack.onAuthenticationError: " + errString);
           //但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
        if (errMsgId == 5) { //出错次数过多（小米5测试是5次）
            //5次错误之后不允许再次使用指纹密码
        }
       }

       // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
       @Override
       public void onAuthenticationFailed() {
              JiuyiLog.e(TAG, "FingerMyCallBack.onAuthenticationFailed: " + "验证失败");
       }

       @Override
       public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
              JiuyiLog.e(TAG, "FingerMyCallBack.onAuthenticationHelp: " + helpString);
       }

       // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
       @Override
       public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
              JiuyiLog.e(TAG, "FingerMyCallBack.onAuthenticationSucceeded: " + "验证成功");
       }
}