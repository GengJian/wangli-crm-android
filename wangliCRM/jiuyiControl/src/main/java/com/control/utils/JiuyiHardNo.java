package com.control.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiMobileRegistShared;
import com.control.shared.JiuyiSharedBase;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ****************************************************************
 * 文件名称 : JiuyiHardNo.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述 : 硬件信息
 *            参考：http://blog.csdn.net/pigdreams/article/details/54426322
 *****************************************************************
 */
public class JiuyiHardNo {
    private jiuyiUUIDShared mUUIDShared = new jiuyiUUIDShared();
	private static JiuyiHardNo instance;
    /** imei */
	private String mImei = "";
    /** imsi */
	private String mImsi = "";
    /** 手机型号 */
	private String mPhoneBuildModel = "";//
    /** 操作系统版本 */
	private String mPhoneSysVersion = "";//
    /** ipv4的地址 */
	private String mIpaddress = "";
    /** MAC地址 */
	private String mMacaddress = "";
    /** 上下文 */
	private static Context mContext;
    /** ImeiImsi的拼接字符串 */
	private String mImeiImsi = "";
    /** ：多个硬件信息的拼接字符串 */
	private String mGuoLianHardNoString = "";
    /** 多个硬件信息的拼接字符串 */
	private String mDefaultHardNoString = "";
    /** 多个硬件信息的拼接字符串 */
	private String mChangChengHardNoString = "";
    /** 是wifi还是3G */
	private String mConnectWifiOr3GStr = "";//
    /** 卡1手机号 */
	private String mPhoneNum = "";//
    /** UUID */
	private String mUUID ="";
	/** APP名称 */
	private String mAppName = "";
	/** 运营商 */
	private String m_sServiceProvider = "";
	/** 网络类型 */
	private String mNetType = NETWORK_NO;

	//网络类型
	public static final String NETWORK_WIFI = "wifi";    // wifi network
	public static final String NETWORK_4G = "4g";    // "4G" networks
	public static final String NETWORK_3G = "3g";    // "3G" networks
	public static final String NETWORK_2G = "2g";    // "2G" networks
	public static final String NETWORK_UNKNOWN = "unknown";    // unknown network
	public static final String NETWORK_NO = "unknown";   // no network
	private static final int NETWORK_TYPE_GSM = 16;
	private static final int NETWORK_TYPE_TD_SCDMA = 17;
	private static final int NETWORK_TYPE_IWLAN = 18;

	/**
     * 获取Imei号
     * @return Imei号
     */
	public String getmImei() {
		if (Func.IsStringEmpty(mImei)) {
			getTelephonyManagerInfo();
		}
		return mImei;
	}
    /**
     * 获取Imsi号
     * @return Imsi号
     */
	public String getmImsi() {
		if (Func.IsStringEmpty(mImsi)) {
			getTelephonyManagerInfo();
		}
		return mImsi;
	}
    /**
     * 获取手机型号
     * @return 手机型号
     */
	public String getmPhoneBuildModel() {
		if (Func.IsStringEmpty(mPhoneBuildModel)) {
			getTelephonyManagerInfo();
		}
		return mPhoneBuildModel;
	}

    /**
     * 获取操作系统版本
     * @return 操作系统版本
     */
	public String getmPhoneSysVersion() {
		if (Func.IsStringEmpty(mPhoneSysVersion)) {
			getTelephonyManagerInfo();
		}
		return mPhoneSysVersion;
	}
    /**
     * 获取ipv4的地址
     * @return ipv4的地址
     */
	public String getmIpaddress() {
		if (Func.IsStringEmpty(mIpaddress)) {
			mIpaddress = getLocalIpAddress();
		}
		return mIpaddress;
	}
    /**
     * 获取MAC的地址
     * @return MAC的地址
     */
	public String getmMacaddress() {
		if (Func.IsStringEmpty(mMacaddress)) {
			mMacaddress = getLocalMacAddress();
		}
		return mMacaddress;
	}

    /**
     * 获取是wifi还是3G
     * @return wifi还是3G字符串
     */
	public String getWifiOr3GStr() {
		return mConnectWifiOr3GStr;
	}
    /**
     * 设置是wifi还是3G
     * @return 设置wifi还是3G字符串
     */
	public void setWifiOr3GStr(int type) {
		if(type == 3){
			mConnectWifiOr3GStr = "WIFI";
		}else if(type == 1 || type == 2){
			mConnectWifiOr3GStr = "3G/4G";
		}
	}

    /**
     * 单例模式
     * @return 实例
     */
	public static synchronized JiuyiHardNo getIns() {
		if (instance == null) {
            mContext = Rc.getApplication();
            instance = new JiuyiHardNo();
		}
		return instance;
	}

	/**
	 * 当 内存中有任何一个为空就读取TelephonyManager 反正就请求了 就都赋值一遍
	 * @return
	 */
	@SuppressLint("MissingPermission")
    private String getTelephonyManagerInfo() {
		TelephonyManager tm = null;
		try {
			String[] list = new String[]{Manifest.permission.READ_PHONE_STATE};
			new JiuyiHiPermissionUtil(mContext).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                @Override
                public void onGuarantee(String permission, int position) {
                    TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                    if (tm != null) {
                        try {
                            mImsi = tm.getSubscriberId();
                        } catch (Exception ex) {
                        }

                        try {
                            mImei = tm.getDeviceId();
                        } catch (Exception ex) {
                        }

                        try {
                            mPhoneBuildModel = Build.MODEL;
                        } catch (Exception ex) {
                        }

                        try {
                            mPhoneSysVersion = Build.VERSION.RELEASE;
                        } catch (Exception ex) {
                        }

                        try {
                            mPhoneNum = tm.getLine1Number();
                        } catch (Exception e) {
                        }
                    }
                }
            });
			//(系统生成失败后，上次保存uuid->生成uuid->推送唯一码->随机15位)
			if (mContext != null) {
				if(Func.IsStringEmpty(mUUID)) {
					mUUIDShared.onGetData(mContext);
					//判断读取的uuid是否符合规则，不符合规则置空
					if(Func.IsStringEmpty(mUUID) || checkUUID(mUUID)){
						mUUID = "";
					}
				}
				// 上一次生成的唯一码
				//（手机刷机之后会出现000000000000000，导致好多手机会出现重复）
                if (!checkImei()) {
					mImei = mUUID;
				}
				if (Func.IsStringEmpty(mImei)) {
					try {
						// 为空，生成uuid
						if (Func.IsStringEmpty(mUUID)) {
							mUUID = java.util.UUID.randomUUID().toString();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					// 为空使用推送唯一码
					if (Func.IsStringEmpty(mUUID)) {
                        if(Rc.getIns().getBaseCallTopCallBack() != null)
						    mUUID = Rc.getIns().getBaseCallTopCallBack().getPushMsgUniqueFlag();
					}
					// 为空，生成15位数字字母随机数+当前时间（毫秒）
					if (Func.IsStringEmpty(mUUID)) {
						mUUID = getStringRandom(15)+System.currentTimeMillis();
					}
					mImei = mUUID;
					// 加密保存到文件
					mUUIDShared.onSaveData(mContext);
				}
			}
		} catch (Exception ex) {
		    JiuyiLog.e("error", JiuyiLog.getStackTraceString(ex));
		}
		return null;

	}

    /**
     * 获取本机ipv4的地址
     * @return ipv4的地址
     */
	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					//需要确认：!inetAddress.isLinkLocalAddress()是为了得到的是ipv4的地址
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			JiuyiLog.e("WifiPreference IpAddress", ex.toString());
		}
		return null;
	}

    /**
     * 获取本机ipv4的地址,可以获取到明文
     * @return
     * @throws SocketException
     */
	public String getIpAddress() throws SocketException {
		String ipaddress = "";  
		Enumeration<NetworkInterface> netInterfaces = null;  
		try {  
			netInterfaces = NetworkInterface.getNetworkInterfaces();  
			while (netInterfaces.hasMoreElements()) {  
				NetworkInterface intf = netInterfaces.nextElement();  
				if (intf.getName().toLowerCase().equals("eth0") || intf.getName().toLowerCase().equals("wlan0")) {  
					for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {  
						InetAddress inetAddress = enumIpAddr.nextElement();  
						if (!inetAddress.isLoopbackAddress()) {  
							ipaddress = inetAddress.getHostAddress().toString();  
							if (!ipaddress.contains("::")) {// ipV6的地址  
								ipaddress = ipaddress;  
							}  
						}  
					}  
				} else {  
					continue;  
				}  
			}  
		} catch (Exception e) {  
		}  
		return ipaddress;
	}

    /**
     * 获取MAC地址
     * @return MAC地址
     */
	public String getLocalMacAddress() {
		try {
            String macStr = "";
            WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo.getMacAddress() != null) {
                macStr = wifiInfo.getMacAddress();// MAC地址
            } else {
                macStr = "null";
            }
            return macStr;
        } catch (Exception e) {
			return null;
		}
	}

    /**
     * ImeiImsi的拼接字符串
     * @return ImeiImsi的拼接字符串
     */
	public String getImeiImsi() {
		try {
			if (Func.IsStringEmpty(mImei)) {
				getTelephonyManagerInfo();
			}
			if (Func.IsStringEmpty(mImeiImsi)) {
				mImeiImsi = "imei=" + mImei + "#3imsi=" + mImsi + "#3";
			}
			return mImeiImsi;
		} catch (Exception e) {
			return "";
		}
	}


    /**
     * 生成随机数字和字母,
      * @param length 长度
     * @return 随机数字和字母,
     */
    public String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for (int i = 0; i < length; i++) {
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }


    public class jiuyiUUIDShared extends JiuyiSharedBase {
        /**
         * 保存已读的当前版本号
         * @param context
         */
        public void onSaveData(Context context)
        {
            if(context == null)
                return;

            try {
                JSONObject json = new JiuyiJSONObject();
                json.put("uuid", mUUID);
                super.onSaveData(context, jiuyiSharedStruct.UUID.name(), json.toString());
            } catch (JSONException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            }
        }

        /**
         * 获取已读的当前版本号
         * @param context
         */
        public void onGetData(Context context)
        {
            if(context == null)
                return;

            String data = super.onGetData(context, jiuyiSharedStruct.UUID.name());
            if(!Func.IsStringEmpty(data)){
                try {
                    JSONObject json = new JiuyiJSONObject(data);
                    if(json.has("uuid"))
                        mUUID = json.optString("uuid");
                } catch (JSONException e) {
                    JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
                }
            }
        }
    }
    /**
     * 获取券商的硬件信息的组合
     * @return
     */
    public String getHardNo() {
        return getHardNo(JiuyiMobileRegistShared.getIns().mMobileCode);
    }

    /**
     * 获取的硬件信息的组合
     * @param mobilecode 手机号码
     * @return
     */
    public String getHardNo(String mobilecode) {
        String tmp = "";

        return tmp;
    }

    /**
     * 拼接并返回国联证券多个硬件信息的拼接字符串
     * @param MOBILECODE 手机号码
     * @param ConnectType 连接类型
     * @return 多个硬件信息的拼接字符串
     */
    private String getGuoLianHardNo(String MOBILECODE, String ConnectType) {
        try {
            if (Func.IsStringEmpty(mPhoneSysVersion)) {
                getTelephonyManagerInfo();
            }
            if(Func.IsStringEmpty(mGuoLianHardNoString)){
                mGuoLianHardNoString = "mobilekind:android,imei:" + mImei + ",imsi:" + mImsi + ",phonekind:" + mPhoneBuildModel + ",phoneve:" + mPhoneSysVersion + ",ipadd:" + mIpaddress + ",phonemac:" + mMacaddress
                        + ",connectiontype:" + ConnectType + "," + "IP=" + MOBILECODE + "," + mIpaddress + "," + mMacaddress;
            }
            return mGuoLianHardNoString;
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 拼接并返回默认多个硬件信息的拼接字符串
     * @param MOBILECODE 手机号码
     * @param ConnectType 连接类型
     * @return 多个硬件信息的拼接字符串
     */
	public String getDefautHardNo(String MOBILECODE) {
        try {
            if (Func.IsStringEmpty(mPhoneSysVersion)) {
                getTelephonyManagerInfo();
            }
            if(Func.IsStringEmpty(mDefaultHardNoString)){
                mDefaultHardNoString = "mobilekind:android,imei:" + mImei + ",imsi:" + mImsi + ",phonekind:" + mPhoneBuildModel + ",phoneve:" + mPhoneSysVersion + ",ipadd:" + mIpaddress + ",phonemac:" + mMacaddress
                        + "," + "IP=" + MOBILECODE + ",";
            }
            return mDefaultHardNoString;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 拼接并返回长城证券多个硬件信息的拼接字符串
     * @param m_sCFrom CFrom
     * @param m_sUpVersion 升级版本号
     * @return 多个硬件信息的拼接字符串
     */
    private String getChangchengHardNo(String m_sCFrom, String m_sUpVersion) {
        try {
            if (Func.IsStringEmpty(mPhoneSysVersion)) {
                getTelephonyManagerInfo();
            }
            if(Func.IsStringEmpty(mChangChengHardNoString)){
                mChangChengHardNoString = "Android," + mPhoneBuildModel + "|" + mPhoneSysVersion + "," + m_sCFrom + "," + m_sUpVersion + "," + mImei + ","
                /*+ Rc.version*/;
            }
            return mChangChengHardNoString;
        } catch (Exception e) {
            return "";
        }
    }

	/**
	 * 获取应用名称
	 */
	public String getAppName() {
		if(Func.IsStringEmpty(mAppName)){
			try {
				PackageManager packManager = mContext.getPackageManager();
				ApplicationInfo appInfo = mContext.getApplicationInfo();
				mAppName = (String) packManager.getApplicationLabel(appInfo);
			} catch (Exception e) {
				JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
			}
		}
		return mAppName;
	}

    /**
     * 清空imei，并设置默认值
     * 清空imei，实际上是给他赋值一个新的UUID，防止跟老的一致
     */
    public void clearImei(){
        try {
            mUUID = java.util.UUID.randomUUID().toString();
            mUUIDShared.onSaveData(mContext);
        } catch (Exception e) {
        }
        mImei = (mUUID==null)?"":mUUID;
    }

    /**
     * Imei是否合法
     * 如果不合法，imei赋值为UUID
     * @return
     */
    private boolean checkImei(){
        return !Func.IsStringEmpty(mImei) && Func.parseLong(mImei)>0 && mImei.length()>= 14 && mImei.length() <= 17 && checkIMEIsum(mImei);
    }

    private boolean checkIMEIsum(String imei){
    	if(imei.length() > 14) {
			String imei14 = imei.substring(0, 14);
			String checkSum = imei.substring(14, 15);
			String validChecksum = getCheckSum(imei14);
			return checkSum.equals(validChecksum);
		}else{
    		if(Func.IsNumber(imei) && Func.parseLong(imei) == 0){
    			return false;
			}
    		return true;
		}
    }

    private String getCheckSum(String imei){
        if (imei.length() == 14) {
            char[] imeiChar=imei.toCharArray();
            int resultInt=0;
            for (int i = 0; i < imeiChar.length; i++) {
                int a=Integer.parseInt(String.valueOf(imeiChar[i]));
                i++;
                final int temp=Integer.parseInt(String.valueOf(imeiChar[i]))*2;
                final int b=temp<10?temp:temp-9;
                resultInt+=a+b;
            }
            resultInt%=10;
            resultInt=resultInt==0?0:10-resultInt;
            return resultInt + "";
        }else{
            return "";
        }
    }

	/**
	 * 	获取网络状态 如4g,3g,wifi
	 */
	public String getCurrNetWork() {
		String[] list = new String[]{Manifest.permission.ACCESS_NETWORK_STATE};
		new JiuyiHiPermissionUtil(mContext).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack(){
			@Override
			public void onGuarantee(String permission, int position) {
				ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = cm.getActiveNetworkInfo();
				String netType = "";
				if (info != null && info.isAvailable()) {
					if (info.getType() == ConnectivityManager.TYPE_WIFI) {
						netType = NETWORK_WIFI;
					} else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
						switch (info.getSubtype()) {
							case NETWORK_TYPE_GSM:
							case TelephonyManager.NETWORK_TYPE_GPRS:
							case TelephonyManager.NETWORK_TYPE_CDMA:
							case TelephonyManager.NETWORK_TYPE_EDGE:
							case TelephonyManager.NETWORK_TYPE_1xRTT:
							case TelephonyManager.NETWORK_TYPE_IDEN:
								netType = NETWORK_2G;
								break;

							case NETWORK_TYPE_TD_SCDMA:
							case TelephonyManager.NETWORK_TYPE_EVDO_A:
							case TelephonyManager.NETWORK_TYPE_UMTS:
							case TelephonyManager.NETWORK_TYPE_EVDO_0:
							case TelephonyManager.NETWORK_TYPE_HSDPA:
							case TelephonyManager.NETWORK_TYPE_HSUPA:
							case TelephonyManager.NETWORK_TYPE_HSPA:
							case TelephonyManager.NETWORK_TYPE_EVDO_B:
							case TelephonyManager.NETWORK_TYPE_EHRPD:
							case TelephonyManager.NETWORK_TYPE_HSPAP:
								netType = NETWORK_3G;
								break;

							case NETWORK_TYPE_IWLAN:
							case TelephonyManager.NETWORK_TYPE_LTE:
								netType = NETWORK_4G;
								break;
							default:
								String subtypeName = info.getSubtypeName();
								if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
									netType = NETWORK_3G;
								} else {
									netType = NETWORK_UNKNOWN;
								}
								break;
						}
					} else {
						netType = NETWORK_UNKNOWN;
					}
				}
				mNetType = netType;
			}
		});
		return mNetType;
	}

	/**
	 * 获取运营商
	 * @return
	 */
	public String getServiceProvider() {
		String m_imsi = getmImsi();
		if(!TextUtils.isEmpty(m_imsi)){
			if(m_imsi.startsWith("46000") || m_imsi.startsWith("46002")){//因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
				m_sServiceProvider = "中国移动";
			}else if(m_imsi.startsWith("46001")){
				m_sServiceProvider = "中国联通";
			}else if(m_imsi.startsWith("46003")){
				m_sServiceProvider = "中国电信";
			}else{
				m_sServiceProvider = "";
			}
		}
		return m_sServiceProvider;
	}

	/**
	 *
	 * @param uuid
	 * @return
	 */
	private boolean checkUUID(String uuid){
		if(Func.IsStringEmpty(uuid) || uuid.length() != 36){
			return false;
		}
		if(checkIsChartNum(uuid)){
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为数据字母组合
	 * @return
	 */
	private boolean checkIsChartNum(String uuid){
		// 邮箱验证规则
		String regEx = "[0-9A-Za-z-]*";
		// 编译正则表达式
		Pattern pattern = Pattern.compile(regEx);
		// 忽略大小写的写法
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(uuid);
		// 字符串是否与正则表达式相匹配
		return matcher.matches();
	}
}
