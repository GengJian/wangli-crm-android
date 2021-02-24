package com.iflytek;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.utils.IflytekCRect;
import com.iflytek.utils.IflytekPopwindow;
import com.iflytek.utils.IflytekRes;

/**
 * @author zhengss
 */
/**
 * **************************************************************** 
 * 文件名称 : Speech2Text.java 
 * 文件描述 : 语音合成
 * 支持功能：开始播放、暂停播放、继续播放、结束播放、结束播放并释放连接、选择在线发音人、
 * 选择离线发音人、设置语音合成方式(离线/在线)
 *****************************************************************
 */
public class Text2Speech{
	private static String TAG = Text2Speech.class.getSimpleName(); 	
	
	public static int speakState = 0;//播放状态： 1正在播放  2暂停播放 0完成播放或还未播放
	public static int selectedNum = 0;//在线发音人序号
	public static String mEngineType = SpeechConstant.TYPE_CLOUD;// 引擎类型

	public static SpeechSynthesizer mTts;// 语音合成对象

	// 默认发音人
	private String voicer = "xiaoyan";
	
	private String[] mCloudVoicersEntries;
	private String[] mCloudVoicersValue ;
	private String[][] voicers;
	
	// 缓冲进度
	private int mPercentForBuffering = 0;
	// 播放进度
	public static int mPercentForPlaying = 0;
	
	// 语记安装助手类
	ApkInstaller mInstaller ;
	
	private Toast mToast;
	private SharedPreferences mSharedPreferences;
	private Context context;
	private String txtNeedToSpeech = "";//仍然需要合成的文字,确保每次只读1k的文字
	
	public Text2Speech(Context context) {
		this.context = context;
		if(mTts == null)
			mTts = SpeechSynthesizer.createSynthesizer(context, mTtsInitListener);//本质Context.getApplicationContext()
		// 云端发音人名称列表
		mCloudVoicersEntries = new String[] { "小燕—女青、中英、普通话", "小宇—男青、中英、普通话", "凯瑟琳—女青、英", "亨利—男青、英", "玛丽—女青、英", "小研—女青、中英、普通话", "小琪—女青、中英、普通话", "小峰—男青、中英、普通话", "小梅—女青、中英、粤语", "小莉—女青、中英、台湾普通话",
				"小蓉—女青、中、四川话", "小芸—女青、中、东北话", "小坤—男青、中、河南话", "小强—男青、中、湖南话", "小莹—女青、中、陕西话", "小新—男童、中、普通话", "楠楠—女童、中、普通话", "老孙—男老、中、普通话" };
		mCloudVoicersValue = new String[] { "xiaoyan", "xiaoyu", "catherine", "henry", "vimary", "vixy", "xiaoqi", "vixf", "xiaomei", "xiaolin", "xiaorong", "xiaoqian", "xiaokun", "xiaoqiang",
				"vixying", "xiaoxin", "nannan", "vils" };
		voicers = new String[mCloudVoicersValue.length][2];
		for (int i = 0; i < mCloudVoicersValue.length; i++){
			voicers[i][0] = mCloudVoicersEntries[i];
			voicers[i][1] = mCloudVoicersValue[i];
		}
		
		mSharedPreferences = context.getSharedPreferences("com.iflytek.setting", Context.MODE_PRIVATE);//默认 私有模式
		mToast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
		
		mInstaller = new  ApkInstaller(context);
	}
	
	public void setContext(Context context){
		this.context = context;
	}	

	/**
	 * 开始播放
	 * @param text 播放内容
	 * @param defaultParams 是否需要更新配置参数
	 */
	public void tts_play(String text, boolean defaultParams){		
		// 设置参数
		if(defaultParams)
			setParam();
		String textNowToRead = "";//当前需要读的 
		if(text.length() > 1000){
			textNowToRead = text.substring(0, 1000);
			txtNeedToSpeech = text.substring(1000,text.length());
		}else{
			textNowToRead = text;
			txtNeedToSpeech = "";
		}
		
		int code = mTts.startSpeaking(textNowToRead, mTtsListener);	
		if (code == ErrorCode.SUCCESS) {
			//播放开始
			Text2Speech.speakState = 1;
		}else{
			//播放失败
			Text2Speech.speakState = 0;
			tts_destroy();//销毁语音合成对象
			((Activity)context).finish();
			if(code == ErrorCode.ERROR_COMPONENT_NOT_INSTALLED){
				//未安装则跳转到提示安装页面
				mInstaller.install();
			} else if(code == 21003){
				//初始化失败
				showTip("请先启动讯飞语记，或者允许关联启动讯飞语记");	
			}else {
				showTip("语音合成失败,错误码: " + code);	
			}
		}
	}
	
	/**
	 * 结束播放
	 */
	public void tts_cancel(){
		mTts.stopSpeaking();
	}
	
	/**
	 * 暂停播放
	 */
	public void tts_pause(){
		mTts.pauseSpeaking();
	}
	
	/**
	 * 继续播放
	 */
	public void tts_resume(){
		mTts.resumeSpeaking();
	}
	
	/**
	 * 退出时释放连接
	 */
	public static void tts_destroy(){
		speakState = 0;
		mPercentForPlaying = 0;
		if(mTts != null){
			mTts.stopSpeaking();
			mTts.destroy();
			mTts = null;
		}
	}

	/**
	 * 弹窗选择在线合成发音人
	 */
	public void tts_cloud_person_select(){
//		new AlertDialog.Builder(context).setTitle("在线合成发音人选项")
//		.setSingleChoiceItems(mCloudVoicersEntries, // 单选框有几项,各是什么名字
//				selectedNum, // 默认的选项
//				new DialogInterface.OnClickListener() { // 点击单选框后的处理
//			public void onClick(DialogInterface dialog,
//					int which) { // 点击了哪一项
//				voicer = mCloudVoicersValue[which];
//				if ("catherine".equals(voicer) || "henry".equals(voicer) || "vimary".equals(voicer)) {
//					 //英文发音人
//				}else {
//					//中文发音人
//				}
//				selectedNum = which;
//				dialog.dismiss();
//			}
//		}).show();

		final IflytekPopwindow pPopupWindow = new IflytekPopwindow(context);
		IflytekRes.GetDisplayParam(context);
		int bottomHeight = IflytekRes.Dip2Pix(70);//底部工具高度
		int width = IflytekRes.Dip2Pix(120);
		int height = IflytekRes.Dip2Pix(250);
   		int margin = IflytekRes.Dip2Pix(10);
		int statusHeight = IflytekRes.getStatusBarHeight(context);//上方状态栏高度
		IflytekCRect defRect = new IflytekCRect(IflytekRes.getWidthPixels() - margin - width, IflytekRes.getHeightPixels() - margin - height - bottomHeight - statusHeight, IflytekRes.getWidthPixels() - margin, IflytekRes.getHeightPixels() - margin - bottomHeight - statusHeight);
		pPopupWindow.startPopwindow(context, defRect, voicers, 2, voicer, IflytekRes.getWidthPixels() - margin - width/2);
		pPopupWindow.setCallback(new IflytekPopwindow.IflytekSpinnerPopupWindowCallback() {
			@Override
			public void onItemClick(int nAction, String[][] Items, int nSelindex) {
				try {
					if (Items == null || Items.length < 1 || nSelindex < 0 || nSelindex >= Items.length) {
						pPopupWindow.dismiss();
						return;
					}
					voicer = Items[nSelindex][1];

				} catch (Exception e) {
				}
			}
		});
		
	}
	
	/**
	 * 选择离线合成发音人
	 */
	public void tts_local_person_select(){
		if (!SpeechUtility.getUtility().checkServiceInstalled()) {
			mInstaller.install();
		}else {
			SpeechUtility.getUtility().openEngineSettings(SpeechConstant.ENG_TTS);				
		}
	}
	
	/**
	 * 设置语音合成方式
	 * @param num 0:在线,1离线
	 */
	public void set_local_or_cloud(int num){
		if(num == 0){
			mEngineType = SpeechConstant.TYPE_CLOUD;
		}else{
			mEngineType = SpeechConstant.TYPE_LOCAL;
			/**
			 * 选择本地合成
			 * 判断是否安装语记,未安装则跳转到提示安装页面
			 */
			if (!SpeechUtility.getUtility().checkServiceInstalled()) {
				mInstaller.install();
			}
		}
	}
	
	/**
	 * 获取语音合成方式
	 * 0:在线,1离线
	 */
	public static int get_local_or_cloud(){
		if(mEngineType.equals(SpeechConstant.TYPE_CLOUD)){
			return 0;
		}else{
			return 1;
		}
	}
	
	/**
	 * 初始化监听。
	 */
	private InitListener mTtsInitListener = new InitListener() {
		@Override
		public void onInit(int code) {
			//tztLog.d(TAG, "InitListener init() code = " + code);
			if(code == ErrorCode.SUCCESS){
				//showTip("初始化成功");
			}else if (code != ErrorCode.SUCCESS) {
        		showTip("初始化失败,错误码："+code);
        	} else {
        		showTip("初始化失败,错误码："+code);
				// 初始化成功，之后可以调用startSpeaking方法
        		// 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
        		// 正确的做法是将onCreate中的startSpeaking调用移至这里
			}		
			//int code2 = mTts.startSpeaking("出生时的速度是多少方式方法", mTtsListener);
		}
	};

	/**
	 * 合成回调监听。
	 */
	private SynthesizerListener mTtsListener = new SynthesizerListener() {

		@Override
		public void onSpeakBegin() {
			//showTip("开始播放");
			//tztLog.e(TAG, "开始播放");
		}

		@Override
		public void onSpeakPaused() {
			//showTip("暂停播放");
			//tztLog.e(TAG, "暂停播放");
		}

		@Override
		public void onSpeakResumed() {
			//showTip("继续播放");
			//tztLog.e(TAG, "继续播放");
		}

		@Override
		public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
			// 合成进度
			mPercentForBuffering = percent;
			//showTip(String.format("缓冲进度为%d%%，播放进度为%d%%", mPercentForBuffering, mPercentForPlaying));
		}

		@Override
		public void onSpeakProgress(int percent, int beginPos, int endPos) {
			// 播放进度
			mPercentForPlaying = percent;
			//showTip(String.format("缓冲进度为%d%%，播放进度为%d%%", mPercentForBuffering, mPercentForPlaying));
		}

		@Override
		public void onCompleted(SpeechError error) {
			String result = "";
			if (error == null) {
				if(txtNeedToSpeech.length() > 0){//如果有未读完文字，继续读
					tts_play(txtNeedToSpeech,true);
				}else{
					//tztLog.e(TAG, "播放完成");
					Text2Speech.speakState = 0;
				}
			} else if (error != null) {
				Text2Speech.speakState = 0;
				//showTip(error.getPlainDescription(true));
			}

		}

		@Override
		public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
			// 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
			// 若使用本地能力，会话id为null
			// if (SpeechEvent.EVENT_SESSION_ID == eventType) {
			// String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
			// Log.d(TAG, "session id =" + sid);
			// }
		}
	};

	public void showTip(final String str) {
		mToast.setText(str);
		mToast.show();
	}

	/**
	 * 参数设置
	 * @return
	 */
	public void setParam(){
		// 清空参数
		mTts.setParameter(SpeechConstant.PARAMS, null);
		// 根据合成引擎设置相应参数
		if(mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
			mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
			// 设置在线合成发音人
			mTts.setParameter(SpeechConstant.VOICE_NAME, voicer);
			//设置合成语速
			mTts.setParameter(SpeechConstant.SPEED, mSharedPreferences.getString("speed_preference", "50"));
			//设置合成音调
			mTts.setParameter(SpeechConstant.PITCH, mSharedPreferences.getString("pitch_preference", "50"));
			//设置合成音量
			mTts.setParameter(SpeechConstant.VOLUME, mSharedPreferences.getString("volume_preference", "50"));
		}else {
			mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
			// 设置本地合成发音人 voicer为空，默认通过语记界面指定发音人。
			mTts.setParameter(SpeechConstant.VOICE_NAME, "");
			/**
			 * 本地合成不设置语速、音调、音量，默认使用语记设置
			 * 开发者如需自定义参数，请参考在线合成参数设置
			 */
		}
		//设置播放器音频流类型
		mTts.setParameter(SpeechConstant.STREAM_TYPE, mSharedPreferences.getString("stream_preference", "3"));
		// 设置播放合成音频打断音乐播放，默认为true
		mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
		
		// 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
		// 注：AUDIO_FORMAT参数语记需要更新版本才能生效
//		mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
//		mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/tts.wav");
	}

	/**
	 * 语音合成对象
	 */
	public SpeechSynthesizer getMts(){
		return mTts;
	}
}
