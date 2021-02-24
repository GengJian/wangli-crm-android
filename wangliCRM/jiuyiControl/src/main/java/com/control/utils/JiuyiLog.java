package com.control.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public final class JiuyiLog {

    public static boolean m_blog = false;
    public static String m_strLog = "";
    
    public static void e(String strTag, String strLog) {
//    	if(Rc.bPackageWithProguard())
//    		return;
    	if (m_blog) {
	        Log.e(strTag, formatstrLog(strTag, strLog));
    	}
    }
    
    public static void e(String strTag, String strLog, byte buffer[]) {
//    	if(Rc.bPackageWithProguard())
//    		return;
    	if (m_blog && strTag!=null) {
    		String data = "";
    		for(byte code : buffer){
    			data += code + ",";
    		}
	        Log.e(strTag, formatstrLog(strTag, strLog+data));
    	}
    }
    
    public static void i(String strTag, String strLog) {
//    	if(Rc.bPackageWithProguard())
//    		return;
        if (m_blog) {
            Log.i(strTag, formatstrLog(strTag, strLog));
        }
    }

    public static void w(String strTag, String strLog) {
//    	if(Rc.bPackageWithProguard())
//    		return;
        if (m_blog) {
            Log.w(strTag, formatstrLog(strTag, strLog));
        }
    }

    public static void d(String strTag, String strLog) {
//    	if(Rc.bPackageWithProguard())
//    		return;
        if (m_blog) {
            Log.d(strTag, formatstrLog(strTag, strLog));
        }
    }

    public static void v(String strTag, String strLog) {
//    	if(Rc.bPackageWithProguard())
//    		return;
        if (m_blog) {
            Log.v(strTag, formatstrLog(strTag, strLog));
        }
    }

    private static String formatstrLog(String strTag, String strLog){
    	if(strTag == null)
    		return "";
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
    	Date now = new Date();

        //zhengss 代码混淆后不打数据收发日志，密码都显示为******
    	if(strTag.toLowerCase().indexOf("password") >= 0 || strTag.toLowerCase().indexOf("checkkey") >= 0){
    		return sdf.format(now) + "******";
    	}else{
    		return sdf.format(now) + strLog;
    	}
    }
    
    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }
}
