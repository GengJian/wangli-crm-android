package com.jiuyi.config.jiuyiuiphone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;


import com.control.Interface.JiuyiICanvasInterface;
import com.control.Interface.JiuyiIConfigInterface;
import com.control.Interface.JiuyiIGpsInterface;
import com.control.Interface.JiuyiISpeechInterface;
import com.control.Interface.JiuyiIUiIntentInterface;
import com.control.Interface.JiuyiSpeech2TextListener;
import com.control.setting.JiuyiHqAttrSet;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.JiuyiJSONObject;

import com.iflytek.IflytekActivity;
import com.iflytek.Speech2Text;
import com.iflytek.Text2Speech;
import com.iflytek.cloud.SpeechUtility;
import com.jiuyi.app.JiuyiActivityManager;
import com.jiuyi.tools.jiuyiLoadStartApk;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;


/**
 * ****************************************************************
 * 文件名称:Config.java
 * 作    者:Created by zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述:配置
 * ****************************************************************
 */
public class Config implements JiuyiIConfigInterface, JiuyiIUiIntentInterface, JiuyiIGpsInterface, JiuyiISpeechInterface {
    /**语音搜索*/
	private Speech2Text mSpeech2Text;

	public Config(){
    }


    @Override
    public boolean actionWithViewFlow(int nPageType) {
        return false;
    }

    /**
	 * View使用ViewFlow左右滑动的功能号
	 * @param nPageType
	 * @return
	 */

	/*
	 *
	 */
	public boolean actionWithoutLogin(int nPageType) {
		switch (nPageType) {
            case Pub.MENU_SYS_SetStartPage:
                return true;
            default:
                return false;
		}
	}


    /**
     * 根据功能号获取私有的Activity，此Activity要继承ActivityBase，否则使用默认的Activity
     * @param nPageType  界面号
     * @return            该界面号对应的Activity
     */
	@Override
	public Class<?> getActivityByPageType(int nPageType) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public JiuyiHqAttrSet getHqAttrSet() {
		JiuyiHqAttrSet pJiuyiHqAttrSet = new JiuyiHqAttrSet();
		pJiuyiHqAttrSet.mJiuyiHqAttrSetRootLayout.setIsTitleBarXmlStyle(true);
		return pJiuyiHqAttrSet;
    }

    /**
     * 与Intent相关或者调用第三方控件的操作
     * @param base
     * @param nPageType 当前界面号
     * @param object   附加参数
     * @return
     */
	@Override
	public Intent getIntentTo(final JiuyiICanvasInterface base, int nPageType, Object object) {
		final Context context = JiuyiActivityManager.getCurrentActivity();
		Intent intent = null;
		switch (nPageType) {

			case Pub.JY_MENU_OpenOtherSoft:
				// 这里来检测版本是否需要更新
				jiuyiLoadStartApk mJiuyiLoadStartApk = new jiuyiLoadStartApk(context, object);
				mJiuyiLoadStartApk.checkUpdateInfo();
				break;
			case Pub.JY_MENU_Speech://资讯朗读
				startIflytekActivity(context , (String)object);
				break;

        }
		return intent;
	}

    @Override
    public String getTabChildSystem(int nPageType) {
        return null;
    }


    @Override
	public void setSoftUpdateTipView(LinearLayout vParentLayout, int nPageType) {

	}
	private HashMap<String, String> toMap(String jsonStr)
	{
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			JSONObject jsonObj = new JiuyiJSONObject(jsonStr);
			Iterator<String> keys = jsonObj.keys();
			String key = null;
			Object value = null;
			while (keys.hasNext())
			{
				key = keys.next();
				value = jsonObj.get(key);
				result.put(key, (String)value);
			}
		} catch (JSONException e) {
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		}
		return result;
	}


	/**
     * 开启GPS功能
     */
	@Override
	public void onStartGPS() {
		// TODO Auto-generated method stub
		
	}
    /**
     * 关闭GPS功能
     */
	@Override
	public void onStopGPS() {
		// TODO Auto-generated method stub
		
	}
    /**
     * 获取当前定位的y坐标
     */
	@Override
	public double getGPSY() {
		// TODO Auto-generated method stub
		return 0;
	}
    /**
     * 获取当前定位的x坐标
     */
	@Override
	public double getGPSX() {
		// TODO Auto-generated method stub
		return 0;
	}
    /**
     * 弹窗口的确定和返回的位置是否切换
     * @return 弹窗口的确定和返回的位置是否切换
     */
	@Override
	public boolean changeDialogBtnPos() {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * @param context 上下文
     * @param msg 注册ip
     * 初始化引擎
     */
	@Override
	public void initSpeech(Context context, String msg) {//讯飞sdk初始化
		SpeechUtility.createUtility(context, msg);
	}
    /**
     * 初始化语音听写对象 - 语音搜索
     * @param context                上下文
     */
	@Override
	public void initmSpeech2Text(Context context, final JiuyiSpeech2TextListener mJiuyiSpeech2TextListener) {//语音合成类初始化 - 语音搜素界面
		if (mSpeech2Text == null){
			// 语音搜索的回调
			Speech2Text.Speech2TextListener mSpeech2TextListener = new Speech2Text.Speech2TextListener() {

				@Override
				public void onResult(String results) {// 完成识别
					mJiuyiSpeech2TextListener.onResult(results);
				}

				@Override
				public void onError(int errorCode, String errorMsg) {// 识别异常
					mJiuyiSpeech2TextListener.onError(errorCode, errorMsg);
				}
			};
			mSpeech2Text = new Speech2Text(context, mSpeech2TextListener);
		}
	}
    /**
     * 销毁语音听写引擎
     */
	@Override
	public void destroySpeech2Text() {//
		if(mSpeech2Text != null){
			mSpeech2Text.tts_destroy();
			mSpeech2Text = null;
		}
	}
    /**
     * 语音听写-是否正在听
     */
	@Override
	public boolean isListeningSpeech2Text() {//
		if(mSpeech2Text == null)
			return false;
		return mSpeech2Text.mIat.isListening();
	}
    /**
     * 语音听写-开始识别
     */
	@Override
	public void recognizeSpeech2Text() {//
		if(mSpeech2Text == null)
			return;
		mSpeech2Text.iat_recognize(true);
	}
    /**
     * 语音听写-停止识别
     */
	@Override
	public void stopSpeech2Text() {//
		if(mSpeech2Text == null)
			return;
		mSpeech2Text.iat_stop();
	}
    /**
     * 销毁语音合成引擎
     */
	@Override
	public void destroyText2Speech() {//
		try{
			Text2Speech.tts_destroy();
		}catch(Exception e){
			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
		}
	}
    /**
     * 语音合成-是否正在读
     */
	@Override
	public boolean isSpeakingText2Speech() {//
        return Text2Speech.mTts != null && Text2Speech.mTts.isSpeaking();
    }
    /**
     * 语音合成-打开语音合成界面
     */
	@Override
	public void startIflytekActivity(Context context, String msg) {//
		if(context == null || !Rc.cfg.pHqAttrSet.mJiuyiHqAttrSetTztSpeech.IsTztText2Speech())
			return;
		Intent intent = new Intent(context, IflytekActivity.class);
		intent.putExtra("content", msg);
		context.startActivity(intent);
		((Activity)context).overridePendingTransition(0, 0);//无动画效果
	}
	/**
     * 除正常界面，其他界面需要锁屏的功能号
     * @param nPageType   界面号
     * @return             其他界面需要锁屏的功能号
     */
	@Override
	public boolean getOtherNeedPasswordLock(int nPageType) {
		return false;
	}
    /**
     * 初始化第三方的控件
     * 即把在application里初始化的放在config里面
     */
    @Override
    public void doInitInApplication() {

    }

}