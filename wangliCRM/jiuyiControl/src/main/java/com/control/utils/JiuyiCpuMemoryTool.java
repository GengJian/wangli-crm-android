package com.control.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * ****************************************************************
 * 文件名称:JiuyiCpuMemoryTool.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:获取CPU信息和内存信息
 * ****************************************************************
 */

public class JiuyiCpuMemoryTool {
    /**
     * 最低的内存大小，2G,1905356，要大于等于
     * huawei p8		2858036	3G内存
     * huawei c8813q	1905356 2G
     * motoluola		1905356 2G
     * sansungsmc101	1270180
     */
	private static final long N_SUPPORT_MOMEY = 1270180;
    /** 最低的版本号，Android 4.3是18*/
	private static final int N_SUPPORT_SDKINT = 18;
    /* 获取CPU最大频率文件（单位KHZ） */
    private final static String kCpuInfoMaxFreqFilePath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
    /* 获取CPU最小频率文件（单位KHZ） */
    private final static String kCpuInfoMinFreqFilePath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";
    /* 实时获取CPU当前频率（单位KHZ） */
    private final static String kCpuInfoCurFreqFilePath = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";
    /** 总内存 */
	private static long nTotalMomey;
    /** 系统版本号 */
	private static int nSDKINT;
    /** 分时图延迟加载左右两屏的延迟时间 */
	private static long nTrendDelay = -1;//

    /**
     * 根据手机的内存和版本
     * @return 延时时间
     */
	public static long getTrendDelay(){
		if(nTrendDelay < 0){
			long nDelay = 600;
			long nCpuFreq = (JiuyiCpuMemoryTool.getMaxCpuFreq() / Pub.g_lMulti[5]);//1516800,1190400
			if(nCpuFreq >= 5 && nCpuFreq < 30){
				long tmpDelay = 1200000 / (nCpuFreq * nCpuFreq * nCpuFreq);
				if(tmpDelay >= 200 && tmpDelay < 1500){
					nDelay = tmpDelay;
				}
			}
			nTrendDelay = nDelay;
		}
		return nTrendDelay;
	}
	
	/**
	 * 是否
	 * @param app 上下文
	 * @return 是否支持
	 */
	public static boolean isSupport(Application app){
		if(nSDKINT == 0)
			getVersion(app);
		if(nTotalMomey == 0)
			getMem_TOLAL();
		
//		return !((nTotalMomey < N_SUPPORT_MOMEY && nTotalMomey>0) || nSDKINT < N_SUPPORT_SDKINT);
		return nTotalMomey > N_SUPPORT_MOMEY && nSDKINT > N_SUPPORT_SDKINT;
	}

    /**
     *  获取CPU最大频率
     * @return CPU最大频率
     */
	public static int getMaxCpuFreq() {
		int result = 0;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(kCpuInfoMaxFreqFilePath);
			br = new BufferedReader(fr);
			String text = br.readLine();
			result = Integer.parseInt(text.trim());
		} catch (FileNotFoundException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} catch (IOException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} finally {
			if (fr != null)
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
		}

		return result;
	}

    /**
     *  获取CPU最小频率
     * @return CPU最小频率
     */
	public static int getMinCpuFreq() {
		int result = 0;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(kCpuInfoMinFreqFilePath);
			br = new BufferedReader(fr);
			String text = br.readLine();
			result = Integer.parseInt(text.trim());
		} catch (FileNotFoundException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} catch (IOException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} finally {
			if (fr != null)
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
		}
		return result;
	}

    /**
     * 实时获取CPU当前频率（单位KHZ）
     * @return CPU当前频率
     */
	public static int getCurCpuFreq() {
		int result = 0;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(kCpuInfoCurFreqFilePath);
			br = new BufferedReader(fr);
			String text = br.readLine();
			result = Integer.parseInt(text.trim());
		} catch (FileNotFoundException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} catch (IOException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} finally {
			if (fr != null)
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
		}
		return result;
	}

    /**
     * 获取CPU名字
     * @return CPU名字
     */
	public static String getCpuName() {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("/proc/cpuinfo");
			br = new BufferedReader(fr);
			String text = br.readLine();
			String[] array = text.split(":\\s+", 2);
			for (int i = 0; i < array.length; i++) {
			}
			return array[1];
		} catch (FileNotFoundException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} catch (IOException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} finally {
			if (fr != null)
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
		}
		return null;
	}


    /**
     * 获得总内存
     */
	public static void getMem_TOLAL() {
		long mTotal;
		// /proc/meminfo读出的内核信息进行解释
		String path = "/proc/meminfo";
		String content = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path), 8);
			String line;
			if ((line = br.readLine()) != null) {
				content = line;
			}
		} catch (FileNotFoundException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} catch (IOException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
				}
			}
		}
		if(content != null && !content.isEmpty()){
			// beginIndex
			int begin = content.indexOf(':');
			// endIndex
			int end = content.indexOf('k');
			
			// 截取字符串信息
			if(begin>=0 && end>begin){
				content = content.substring(begin + 1, end).trim();
				nTotalMomey = Func.parseInt(content);
			}
		}else{
			nTotalMomey = -1;//表示失败
		}
	}

    /**
     * 系统版本号
     * @param app
     */
	public static void getVersion(Application app) {
		TelephonyManager tm = null;
		try {
			tm = (TelephonyManager) app.getSystemService(Context.TELEPHONY_SERVICE);
			if (tm != null) {
				try {
					nSDKINT = Build.VERSION.SDK_INT;
				} catch (Exception ex) {
				}
			}
		} catch (Exception ex) {
		}
	}
}
