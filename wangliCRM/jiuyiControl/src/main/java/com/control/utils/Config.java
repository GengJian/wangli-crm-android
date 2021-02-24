package com.control.utils;

import android.content.Context;
import android.content.Intent;

import com.control.Interface.JiuyiICanvasInterface;
import com.control.Interface.JiuyiIConfigInterface;
import com.control.Interface.JiuyiIGpsInterface;
import com.control.Interface.JiuyiISpeechInterface;
import com.control.Interface.JiuyiIUiIntentInterface;
import com.control.Interface.JiuyiSpeech2TextListener;
import com.control.setting.JiuyiHqAttrSet;


/**
 * ****************************************************************
 * 文件名称:Config.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述:获取项目的是有配置和功能的设置
 * ****************************************************************
 */
public class Config {
	/**
	 * 部分界面显示的方式配置,均在此结构中添加
	 */
	public JiuyiHqAttrSet pHqAttrSet = null;
    /**
     * 相应的接口
     */
    private JiuyiIConfigInterface config = null;
	private JiuyiIUiIntentInterface _ptztIUiIntentInterface = null;
	private JiuyiIGpsInterface _pTztGpsInterface = null;
	private JiuyiISpeechInterface _pSpeechInterface = null;


    /**
     * jiuyi_systermsetting.xml中的配置
     */
    private String mUpVersion;//升级版本号
    private String mSysVersion;//软件版本号，显示在设置里


    private String mCFrom;//CFrom
    private String mTFrom;//TFrom
    private int mRootPageType;//首页界面号PageType
//    private int merpClientID;//ID


    //dialog默认的标题头
    private String mDialogTitle = "系统提示";

    public Config(Context context) {
        this(context, null);
    }
    public Config(Context context, String filepath) {
        if (context == null)
            return;
		Class<?> tmpCls = null;
		try {
		    if(Func.IsStringEmpty(filepath))
			    filepath = "com.jiuyi.config."+Res.getString(context, "jiuyi_uiname")+".Config";
			JiuyiLog.e("configfile", "Config.filepath"+filepath);
			tmpCls = Class.forName(filepath);

			config = (JiuyiIConfigInterface)tmpCls.newInstance();
			if(config instanceof JiuyiIUiIntentInterface)
			    _ptztIUiIntentInterface = (JiuyiIUiIntentInterface) config;
            if(config instanceof JiuyiIGpsInterface)
                _pTztGpsInterface = (JiuyiIGpsInterface) config;
            if(config instanceof JiuyiISpeechInterface)
                _pSpeechInterface = (JiuyiISpeechInterface) config;


		} catch (Exception e) {
			JiuyiLog.e("configfile", "Config.filepath"+filepath);
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
		}

		pHqAttrSet = config.getHqAttrSet();
        //初始化全局参数
        initSystermSetting(context);
        //初始化第三方的控件
        doInitInApplication();
	}
    public JiuyiIUiIntentInterface getTztUIIntentInterface()
    {
        return _ptztIUiIntentInterface;
    }

    /**
     * 初始化全局参数（tzt_systermsetting.xml中的）
     */
    private void initSystermSetting(Context context){
        mUpVersion = Res.getString(context, "jiuyi_upversion");
        mSysVersion = Res.getString(context, "jiuyi_sysversion");


        mCFrom = Res.getString(context, "jiuyi_cfrom");
        mTFrom = Res.getString(context, "jiuyi_tfrom");
        mRootPageType = Func.parseInt(Res.getString(context, "jiuyi_rootpage"));
//        merpClientID = Func.parseInt(Res.getString(context, "jiuyi_clientid"));
        mDialogTitle = Res.getString(context, "jiuyidefdialogtitle");

    }

    public String getUpVersion() {
        return mUpVersion;
    }

    public String getSysVersion() {
        return mSysVersion;
    }



    public String getCFrom() {
        return mCFrom;
    }

    public String getTFrom() {
        return mTFrom;
    }

    public int getRootPageType() {
        return mRootPageType;
    }

 /*   public int getQuanShangID() {
        return merpClientID;
    }
*/

    public String getDefDialogTitle() {
        return mDialogTitle;
    }




    public JiuyiIUiIntentInterface gettztIUiIntentInterface()
    {
        return _ptztIUiIntentInterface;
    }

    /**
     * View使用ViewFlow左右滑动的功能号
     * @param nPageType
     * @return
     */
    public boolean actionWithViewFlow(int nPageType){
        return config.actionWithViewFlow(nPageType);
    }
    /*
     * 不需要登录就能看的界面
     */
    public boolean actionWithoutLogin(int nPageType){
    	return config.actionWithoutLogin(nPageType);
    }

    public Class<?> getActivityByPageType(int nPageType){
    	return config.getActivityByPageType(nPageType);
    }





	public void onStartGPS() {
		if(_pTztGpsInterface == null)
			return;
		_pTztGpsInterface.onStartGPS();
	}

	public void onStopGPS() {
		if(_pTztGpsInterface == null)
			return;
		_pTztGpsInterface.onStopGPS();
	}

	public double getGPSY() {
		if(_pTztGpsInterface == null)
			return 0;
		return _pTztGpsInterface.getGPSY();
	}

	public double getGPSX() {
		if(_pTztGpsInterface == null)
			return 0;
		return _pTztGpsInterface.getGPSX();
	}

	public void InitSpeech(Context context, String msg) {
		if (_pSpeechInterface == null)
			return;
		_pSpeechInterface.initSpeech(context, msg);
	}

	public void InitmSpeech2Text(Context context, JiuyiSpeech2TextListener mSpeech2TextListener) {
        if (_pSpeechInterface == null)
            return;
        _pSpeechInterface.initmSpeech2Text(context, mSpeech2TextListener);
    }

	public void DestroySpeech2Text() {
		if (_pSpeechInterface == null)
			return;
		_pSpeechInterface.destroySpeech2Text();
	}

	public boolean IsListeningSpeech2Text() {
		if (_pSpeechInterface == null)
			return false;
		return _pSpeechInterface.isListeningSpeech2Text();
	}

	public void RecognizeSpeech2Text() {
		if (_pSpeechInterface == null)
			return;
		_pSpeechInterface.recognizeSpeech2Text();
	}

	public void StopSpeech2Text(){
		if (_pSpeechInterface == null)
			return;
		_pSpeechInterface.stopSpeech2Text();
	}

	public void DestroyText2Speech() {
		if (_pSpeechInterface == null)
			return;
		_pSpeechInterface.destroyText2Speech();
	}

	public boolean IsSpeakingText2Speech() {
		if (_pSpeechInterface == null)
			return false;
		return _pSpeechInterface.isSpeakingText2Speech();
	}

	public void StartIflytekActivity(Context context, String msg) {
		if (_pSpeechInterface == null)
			return;
		_pSpeechInterface.startIflytekActivity(context, msg);
	}

	/**
	 * 界面需要锁屏的功能号
	 * @param nPageType
	 * @return
	 */
	public boolean getOtherNeedPasswordLock(int nPageType){
		return config.getOtherNeedPasswordLock(nPageType);
	}

    /**
     * 初始化第三方的控件
     * 即把在application里初始化的放在config里面
     */
    public void doInitInApplication() {
        try {
            config.doInitInApplication();
        }catch (Exception e){
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }
    /**
     * 发起三方登录
     * @param object 上下文
     * @bindType 个人中心账号绑定类型 1[微信] 2[QQ] 3[微博]
     */
    public void threeAccountLogin(Object object, String bindType) {

    }

    public Intent getIntentTo(JiuyiICanvasInterface canvasInterface, int nPageType, Object object){
        if(_ptztIUiIntentInterface == null)
            return null;
        return _ptztIUiIntentInterface.getIntentTo(canvasInterface, nPageType, object);
    }

}
