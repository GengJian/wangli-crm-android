package com.control.shared;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;

import com.control.callback.JiuyiFingerBefor6MyCallBack;
import com.control.callback.JiuyiFingerCompatMyCallBack;
import com.control.utils.Func;
import com.control.utils.JiuyiRefreshDelayCycle;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiJSONObject;
import com.control.utils.JiuyiLog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ****************************************************************
 * 文件名称 : JiuyiPasswordLockShared
 * 作   者 : Created by zhengss
 * 创建时间:2018/5/10 15:01
 * 文件描述 : 密码锁定信息保存
 * ****************************************************************
 */
public class JiuyiPasswordLockShared extends JiuyiSharedBase {

    /** jiuyiPasswordLockShared实例 */
    private static JiuyiPasswordLockShared ins;

    public static JiuyiPasswordLockShared getIns(){
        if(ins == null)
            ins = new JiuyiPasswordLockShared();
        return ins;
    }


    private boolean m_bOpenPasswordLock=false;//是否开启密码锁
    private int m_nPasswordLockTimeIndex=-1;//锁定时间
    private int[] m_arrayPasswordLockTime=new int[]{0,1,5,15,25};
    private byte[] m_sPassword=null;//密码锁密码（从本地读取出来的是加密的，只有在使用的时候，把解密之后的数据传参）

    private boolean m_bOpenFignerLock=false;//是否开启指纹识别

    private FingerprintManagerCompat m_pFingerprintManager;//6.0系统及以上指纹密码
    private KeyguardManager m_pKeyManager;//锁屏管理

    private JiuyiRefreshDelayCycle pJiuyiRefreshDelayCycle;

    private boolean m_bLockTrade=false;//是否被锁

    public boolean isM_bLockNeed() {
        return m_bLockNeed;
    }

    public void setM_bLockNeed(boolean m_bLockNeed) {
        this.m_bLockNeed = m_bLockNeed;
    }

    private boolean m_bLockNeed=true;//是否被锁

    private android.os.CancellationSignal mBefor6CancellationSignal = new android.os.CancellationSignal();

    private CancellationSignal mCancellationSignal = new CancellationSignal();

    private Vibrator vibrator;

    private int m_nFingerCheckErrorCount=0;//使用指纹解锁失败次数

    private FingerprintManager m_pFingerprintManagerBefor6;//6.0系统一下指纹识别

    private int m_nOSVersion=23;//6.0系统

    JiuyiFingerCompatMyCallBack m_pFingerMyCallBack;
    JiuyiFingerBefor6MyCallBack m_pFingerBefor6MyCallBack;

    public JiuyiPasswordLockShared(){
        Oninit();
    }
    /**
     * 初始化
     */
    public void Oninit(){
        m_bOpenPasswordLock=false;
        m_nPasswordLockTimeIndex=1;
        m_sPassword=null;
        m_bOpenFignerLock=false;
        m_nFingerCheckErrorCount=0;
        m_nFingerCheckErrorCount=0;

        readPasswordLock();
    }

    /**
     * 获取是否开启密码锁
     * @return
     */
    public boolean getOpenPasswordLock(){
        return m_bOpenPasswordLock;
    }

    /**
     * 获取锁定时间选择序号
     * @return
     */
    public int getPasswordLockTimeIndex(){
        return m_nPasswordLockTimeIndex;
    }


    /**
     * 获取锁定时间
     * @return
     */
    public int getPasswordLockTime(){
        if(m_nPasswordLockTimeIndex>=0){
            if(m_nPasswordLockTimeIndex>=m_arrayPasswordLockTime.length){
                m_nPasswordLockTimeIndex = 1;
            }
            return m_arrayPasswordLockTime[m_nPasswordLockTimeIndex];
        }
        return -1;
    }

    public int[] getPasswordLockTimeList(){
        return m_arrayPasswordLockTime;
    }

    public void setPasswordLockTimeIndex(int nValue){
        m_nPasswordLockTimeIndex = nValue;
        savePasswordLock();
        //重新启用监听
        if(pJiuyiRefreshDelayCycle != null){
            pJiuyiRefreshDelayCycle.stop();
            pJiuyiRefreshDelayCycle = null;
        }
        //修改锁屏时间，把上一次锁屏
        m_bLockTrade=false;
        startCheckLock();
    }

    /**
     * 获取密码
     * @return
     */
    public byte[] getPassword(){
        return m_sPassword;
    }

    /**
     * 获取是否开启指纹锁
     * @return
     */
    public boolean getOpenFingerLock(Context pContext){
        if(!isSupportFinger(pContext)){
            return false;
        }
        return m_bOpenFignerLock;
    }


    /**
     * 读取配置
     */
    private void readPasswordLock(){
        String data = super.onGetData(Rc.getApplication(), jiuyiSharedStruct.PasswordLock.name());
        if(!Func.IsStringEmpty(data)){
            try {
                JSONObject json = new JiuyiJSONObject(data);
                m_bOpenPasswordLock = json.optBoolean("openpasswordlock");
                m_nPasswordLockTimeIndex = json.optInt("passwordlocktime");
                String sPassword = json.optString("password");
                m_sPassword=sPassword.getBytes();//得到密文
                m_bOpenFignerLock = json.optBoolean("openfingerlock");

            } catch (JSONException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            }
        }

    }

    public void savePasswordLock(){
        onSaveData(Rc.getApplication(), jiuyiSharedStruct.PasswordLock.name(), getJsonString());

    }

    @Override
    public void onSaveData(Context context, String sharednane, String data) {
        if(context == null)
            return;

        super.onSaveData(context, sharednane, getJsonString());
    }

    /**
     * 把参数转换为JSON格式保存
     * @return JSON格式数据
     */
    public String getJsonString(){
        try {
            JSONObject json = new JiuyiJSONObject();
            json.put("openpasswordlock", m_bOpenPasswordLock);
            json.put("passwordlocktime", m_nPasswordLockTimeIndex);

            if(m_sPassword==null || m_sPassword.length<1){
                json.put("password", "");
            }else{
                json.put("password", new String(m_sPassword));
            }
            json.put("openfingerlock", m_bOpenFignerLock);
            return json.toString();
        } catch (JSONException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
        return "";
    }

    /**
     * 设置是否开启密码锁
     * @param bOpenPassworLock 是否开启密码锁
     * @param sPassword 密码
     */
    public void setOpenPasswordLock(boolean bOpenPassworLock, String sPassword){
        m_bOpenPasswordLock = bOpenPassworLock;
        m_sPassword = sPassword.getBytes();
        m_nFingerCheckErrorCount=0;
        if(!m_bOpenPasswordLock){
            m_nPasswordLockTimeIndex=-1;
            m_sPassword=null;
            m_bOpenFignerLock=false;
            if(pJiuyiRefreshDelayCycle !=null){
                pJiuyiRefreshDelayCycle.stop();
                pJiuyiRefreshDelayCycle =null;
            }
        }else{
            if(m_nPasswordLockTimeIndex<0){
                m_nPasswordLockTimeIndex=1;
            }
        }
        savePasswordLock();
    }


    //指纹识别
    public void setOpenFignerLock(boolean bValue){
        m_bOpenFignerLock = bValue;
        savePasswordLock();
    }

    public FingerprintManagerCompat getFingerprintManager(Context pContext){
        if(m_pFingerprintManager==null)
            OninitFingerprintManager(pContext);
        return m_pFingerprintManager;
    }

    private void OninitFingerprintManager(Context pContext){
        if(m_pFingerprintManager == null)
            m_pFingerprintManager = FingerprintManagerCompat.from(pContext);
        //（初始化锁屏管理）
        try {
            m_pKeyManager= (KeyguardManager) pContext.getSystemService(Context.KEYGUARD_SERVICE);
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }

    }

    public FingerprintManager getBefor6FingerprintManager(Context pContext){
        if(m_pFingerprintManagerBefor6==null)
            isBefor6SupportFinger(pContext);
        return m_pFingerprintManagerBefor6;
    }

    /**
     * 是否支持指纹
     */
    public boolean isSupportFinger(Context pContext){
        try {
            if(android.os.Build.VERSION.SDK_INT<m_nOSVersion){
                return isBefor6SupportFinger(pContext);
            }else{
                if(m_pFingerprintManager==null){
                    OninitFingerprintManager(pContext);
                }
                if(m_pFingerprintManager==null)
                    return false;
                //（判断是否开启锁屏密码）
                if(m_pKeyManager!=null && !m_pKeyManager.isKeyguardSecure()){
                    return false;
                }
                if(!m_pFingerprintManager.isHardwareDetected()){
                    return false;
                }
                if (!m_pFingerprintManager.hasEnrolledFingerprints()) {
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
            return false;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isBefor6SupportFinger(Context pContext) {
        try {
            Class.forName("android.hardware.fingerprint.FingerprintManager"); // 通过反射判断是否存在该类
        } catch (ClassNotFoundException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            return false;
        }
        if(m_pFingerprintManagerBefor6==null)
            m_pFingerprintManagerBefor6 = (FingerprintManager)pContext.getSystemService(pContext.FINGERPRINT_SERVICE);
        if (ContextCompat.checkSelfPermission(pContext, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            m_pFingerprintManagerBefor6.hasEnrolledFingerprints();
        }
        KeyguardManager mKeyManager = (KeyguardManager)pContext.getSystemService(Context.KEYGUARD_SERVICE);
//        //android studio 上，没有这个会报错
//        if (ActivityCompat.checkSelfPermission(pContext, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
////            Toast.makeText(this, "没有指纹识别权限", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        //判断硬件是否支持指纹识别
        if (!m_pFingerprintManagerBefor6.isHardwareDetected()) {
            return false;
        }
        //判断 是否开启锁屏密码
        if (!mKeyManager.isKeyguardSecure()) {
            return false;
        }
        //判断是否有指纹录入
        if (!m_pFingerprintManagerBefor6.hasEnrolledFingerprints()) {
            return false;
        }
        return true;
    }

    public void setCallback(JiuyiFingerCompatMyCallBack pFingerMyCallBack, JiuyiFingerBefor6MyCallBack pFingerBefor6MyCallBack){
        m_pFingerMyCallBack = pFingerMyCallBack;
        m_pFingerBefor6MyCallBack = pFingerBefor6MyCallBack;
    }

    public void StartFingerprint(Context pContext){
        try {
            if(pContext==null)
                return;
            /**
             * 开始验证，什么时候停止由系统来确定，如果验证成功，那么系统会关系sensor，如果失败，则允许
             * 多次尝试，如果依旧失败，则会拒绝一段时间，然后关闭sensor，过一段时候之后再重新允许尝试
             *
             * 第四个参数为重点，需要传入一个FingerprintManagerCompat.AuthenticationCallback的子类
             * 并重写一些方法，不同的情况回调不同的函数
             */
            if(isSupportFinger(pContext)){
                if(android.os.Build.VERSION.SDK_INT<m_nOSVersion){
                    if (mBefor6CancellationSignal.isCanceled()) {
                        mBefor6CancellationSignal = new android.os.CancellationSignal();
                    }
                    if(m_pFingerprintManagerBefor6!=null){
                        m_pFingerprintManagerBefor6.authenticate(null, mBefor6CancellationSignal,0, m_pFingerBefor6MyCallBack, null);
                    }
                }else{
                    if (mCancellationSignal.isCanceled()) {
                        mCancellationSignal = new CancellationSignal();
                    }
                    if(m_pFingerprintManager!=null)
                        m_pFingerprintManager.authenticate(null, 0, mCancellationSignal, m_pFingerMyCallBack, null);
                }
            }
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }

    public void stopFingerprint(){
        if(android.os.Build.VERSION.SDK_INT<m_nOSVersion){
            if(mBefor6CancellationSignal !=null)
                mBefor6CancellationSignal.cancel();
        }else{
            if(mCancellationSignal !=null)
                mCancellationSignal.cancel();
        }

    }

    public void startCheckLock(){
        if(m_nPasswordLockTimeIndex<0){
            return;
        }
        if(getOpenPasswordLock() ){
            try {
                if(m_nPasswordLockTimeIndex==0){
                    //立即锁定
                    m_bLockTrade=true;
                    return;
                }
                if (pJiuyiRefreshDelayCycle == null) {
                    pJiuyiRefreshDelayCycle = new JiuyiRefreshDelayCycle(m_arrayPasswordLockTime[m_nPasswordLockTimeIndex]*60*1000, false) {
                        @Override
                        public void doDelayAction() {
                            if(pJiuyiRefreshDelayCycle != null){
                                pJiuyiRefreshDelayCycle.stop();
                                pJiuyiRefreshDelayCycle = null;
                            }
                            //锁屏
                            m_bLockTrade=true;
                        }
                    };
                    pJiuyiRefreshDelayCycle.start();
                }
            } catch (Exception e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
            }
        }
    }

    public void stopCheckLock(){
        try {
            if(pJiuyiRefreshDelayCycle != null){
                pJiuyiRefreshDelayCycle.stop();
                pJiuyiRefreshDelayCycle = null;
            }

        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }


    public boolean getIsLockTrade(){
        return m_bLockTrade;
    }

    public void setIsLockTrade(boolean bValue){
        m_bLockTrade=bValue;
    }

    /**
     * 跳转锁屏
     */
    public boolean ChangeLockPage(boolean bNeedPasswordLock){
        if (IsNeedPasswordLockPage() || bNeedPasswordLock) {
            // 非后置状态并且当前是才跳转锁屏
            if(!Rc.getIns().getBaseCallTopCallBack().isCurrActivityStop()){
                Bundle bundle = new Bundle();
                bundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.PasswordLock_Lock);
                Rc.getIns().getBaseCallTopCallBack().changePage(null,Pub.PasswordLock_Lock,true);
            }
        }
        return false;
    }

    /**
     * 界面是否需要锁屏（需要开启锁屏检测）
     * @return true:需要锁屏界面，开启检测
     */
    public boolean IsNeedPasswordLockPage(){
        return Rc.getIns().getBaseCallTopCallBack().isNeedPasswordLockPage();
    }

    public boolean IsNeedPasswordLockPage(int nPageType){
        //（锁屏几个界面认为，需要处理锁屏逻辑）
        if(nPageType == Pub.PasswordLock_Lock || nPageType == Pub.PasswordLock_Open ||
                nPageType == Pub.PasswordLock_Close || nPageType == Pub.PasswordLock_ResetPassword ||
                nPageType == Pub.PasswordLock_SetLockTime || nPageType == Pub.PasswordLock_OpenFingerLock ||
                nPageType == Pub.PasswordLock_CloseFingerLock){
            return true;
        }
        return false;
    }

    public void ExitRefreshDelayCycle(){
        m_bLockTrade=false;//没有锁定
        if(getOpenPasswordLock() ){

        }else{
            //关闭监听
            if(pJiuyiRefreshDelayCycle != null){
                pJiuyiRefreshDelayCycle.stop();
                pJiuyiRefreshDelayCycle = null;
            }
        }
    }

    public void checkPasswordError(){
        stopFingerprint();

        m_bLockTrade=false;//没有锁定

        setOpenPasswordLock(false, "");
        //跳转到注销activity
        Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.ResetLogin,false);
    }

    public void StartVibrator(Context pContext){
        if(vibrator==null){
            vibrator= (Vibrator)pContext.getSystemService(Context.VIBRATOR_SERVICE);
        }
        if(vibrator!=null){
            long [] pattern = {100,200,100,200};   // 停止 开启 停止 开启
            vibrator.vibrate(pattern,-1);           //重复两次上面的pattern 如果只想震动一次，index设为-1
        }
    }
    public void StoptVibrator(Context pContext){
        if(vibrator!=null){
            vibrator.cancel();
        }
    }

    public boolean checkPassword(byte[] newPassword, byte[] oldPassword) {
        if (newPassword == null || oldPassword == null) {
            return false;
        }
        if (newPassword == oldPassword) {
            return true;
        }
        boolean bEquals = true;
        int i;
        for (i = 0; i < newPassword.length && i < oldPassword.length; i++) {
            if (newPassword[i] != oldPassword[i]) {
                bEquals = false;
                break;
            }
        }
        return bEquals;
    }

    public int getFingerCheckErrorCount(){
        return m_nFingerCheckErrorCount;
    }

    public void setFingerCheckErrorCount(int nValue){
        m_nFingerCheckErrorCount=nValue;
    }

    public void setFingerCheckErrorCountADD(int nValue){
        m_nFingerCheckErrorCount+=nValue;
    }

    /**
     * 开始检测指纹失效
     */
    public void startCheckFingerInvalid(){
        JiuyiRefreshDelayCycle pCheck = new JiuyiRefreshDelayCycle(30000, false) {
            @Override
            public void doDelayAction() {
                m_nFingerCheckErrorCount=0;
            }
        };
        if(pCheck != null){
            pCheck.start();
        }
    }
}
