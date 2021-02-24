package com.iflytek;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.iflytek.utils.IflytekFunc;
import com.iflytek.utils.IflytekRes;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 语音播报主界面
 */
public class IflytekActivity extends Activity {
//	private static String TAG = IflytekActivity.class.getSimpleName();
	public int blueColor = 0xff00a9f8;
	public int blackColor = 0xff212021;
	public Text2Speech mtext2Speech;// 资讯朗读类
	public String message = "";// 资讯内容
	
	public TextView tv_offline_play, tv_online_play;// 离线 在线播放
	public TextView tv_start_pause;// 开始 暂停 继续播放
	public ImageView iv_start_pause;// 开始 暂停 继续播放
	public ProgressBar progress_Speak;
	public Timer m_tTimer;
	
	/** 显示时的动画 */
	private Animation animShow;
	/** 隐藏时的动画 */
	private Animation animHide;
	private LinearLayout layout_bottom;//下方主体
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 状态栏设为透明
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
		setContentView(IflytekRes.getLayoutID(IflytekActivity.this, "tzt_newxml_iflytek_pop"));
		// 朗读内容
		Intent intent = getIntent();
		String tempStr = intent.getStringExtra("content");
		if (!IflytekFunc.IsStringEmpty(tempStr)) {
			Map<String, String> valueMap = new HashMap<>();
			IflytekFunc.GetMapValue(tempStr, null, valueMap, "&", false);
			message = valueMap.get("content");
		}
		initView();

		initData();
		
		
	}
	
	private void initAnims() {
		animShow = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 1,
				Animation.RELATIVE_TO_SELF, 0);
		animShow.setDuration(300);

		animHide = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 1);
		animHide.setDuration(300);
	}
	
	@Override
	public void onDestroy(){
//      退出时需要销毁语音合成对象
//		if(mtext2Speech != null){
//			mtext2Speech.tts_destroy();
//		}
		if (m_tTimer != null) {
			m_tTimer.purge();
			m_tTimer = null;
		}
		
		if(layout_bottom != null){
			layout_bottom.clearAnimation();
			layout_bottom.setAnimation(animHide);
		}
		super.onDestroy();
	}

	public void initView() {
		initAnims();
		
		// 在线播放 离线播放
		tv_online_play = (TextView) findViewById(IflytekRes.getViewID(IflytekActivity.this, "tv_online_play"));
		tv_offline_play = (TextView) findViewById(IflytekRes.getViewID(IflytekActivity.this, "tv_offline_play"));
		tv_start_pause = (TextView) findViewById(IflytekRes.getViewID(IflytekActivity.this, "tv_start_pause"));
		iv_start_pause = (ImageView) findViewById(IflytekRes.getViewID(IflytekActivity.this, "iv_start_pause"));
		progress_Speak = (ProgressBar) findViewById(IflytekRes.getViewID(IflytekActivity.this, "progress_Speak"));
		
		
		//根据后台播放状态更新界面
		if (Text2Speech.get_local_or_cloud() == 0) {// 在线
			tv_online_play.setTextColor(blueColor);
			tv_offline_play.setTextColor(blackColor);
		} else {// 离线
			tv_online_play.setTextColor(blackColor);
			tv_offline_play.setTextColor(blueColor);
		}
		if (Text2Speech.speakState == 0){
			iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_start"));
			tv_start_pause.setText("开始播放");
		}else if (Text2Speech.speakState == 1){
			iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_pause"));
			tv_start_pause.setText("暂停播放");
		}else if (Text2Speech.speakState == 2){
			iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_start"));
			tv_start_pause.setText("继续播放");
		}
		
		progress_Speak.setProgress(Text2Speech.mPercentForPlaying);//播放进度
		
		
		
		// 上方空白
		findViewById(IflytekRes.getViewID(IflytekActivity.this, "layout_top")).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(0, 0);//无动画效果
			}
		});
		
		//下方主体
		layout_bottom = (LinearLayout)findViewById(IflytekRes.getViewID(IflytekActivity.this, "layout_bottom"));
		layout_bottom.setAnimation(animShow);
		
		// 在线播放
		findViewById(IflytekRes.getViewID(IflytekActivity.this, "tv_online_play")).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mtext2Speech.set_local_or_cloud(0);
				tv_online_play.setTextColor(blueColor);
				tv_offline_play.setTextColor(blackColor);
			}
		});
		// 离线播放
		findViewById(IflytekRes.getViewID(IflytekActivity.this, "tv_offline_play")).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mtext2Speech.set_local_or_cloud(1);
				tv_online_play.setTextColor(blackColor);
				tv_offline_play.setTextColor(blueColor);
			}
		});

		// 选择发音人
		findViewById(IflytekRes.getViewID(IflytekActivity.this, "tv_voicer")).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Text2Speech.get_local_or_cloud() == 0) {// 在线
					mtext2Speech.tts_cloud_person_select();
				} else {// 离线
					mtext2Speech.tts_local_person_select();
				}
			}
		});
		// 开始/暂停播放
		findViewById(IflytekRes.getViewID(IflytekActivity.this, "layout_start_pause"))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (Text2Speech.speakState == 0 && !mtext2Speech.getMts().isSpeaking()) {// 0完成播放或还未播放
							if(IflytekFunc.IsStringEmpty(message)){
								mtext2Speech.showTip("朗读内容为空");
							}else{
								mtext2Speech.tts_play(message, true);
							}
						} else if (Text2Speech.speakState == 1 && mtext2Speech.getMts().isSpeaking()) {// 正在播放
							Text2Speech.speakState = 2;
							mtext2Speech.tts_pause();
							iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_start"));
							tv_start_pause.setText("继续播放");
						} else if (Text2Speech.speakState == 2 && mtext2Speech.getMts().isSpeaking()) {// 暂停播放
							Text2Speech.speakState = 1;
							mtext2Speech.tts_resume();
							iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_pause"));
							tv_start_pause.setText("暂停播放");
						} else {
							mtext2Speech.showTip("播放状态异常！");// 当做取消播放处理
							Text2Speech.speakState = 0;
							mtext2Speech.tts_cancel();
							iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_start"));
							tv_start_pause.setText("开始播放");
							Text2Speech.mPercentForPlaying = 0;// 播放进度
							progress_Speak.setProgress(0);
						}
					}
				});
		// 取消播放
		findViewById(IflytekRes.getViewID(IflytekActivity.this, "layout_cancel")).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Text2Speech.speakState = 0;
				mtext2Speech.tts_cancel();
				iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_start"));
				tv_start_pause.setText("开始播放");
				Text2Speech.mPercentForPlaying = 0;//播放进度
				progress_Speak.setProgress(0);
			}
		});
	}

	public void initData() {

		mtext2Speech = new Text2Speech(IflytekActivity.this);// 初始化讯飞sdk
		//定时更新UI
		m_tTimer = new Timer(true);//
		m_tTimer.schedule(new TimerTask() {
			int tempState = Text2Speech.speakState;//当前的状态
			int tempPercent = Text2Speech.mPercentForPlaying;//当前的播放进度
			@Override
			public void run() {				
				if(tempPercent != Text2Speech.mPercentForPlaying){
					tempPercent = Text2Speech.mPercentForPlaying;
					runOnUiThread(new Runnable() {
						public void run() {
							progress_Speak.setProgress(tempPercent);
						}
					});
				}
				if(tempState != Text2Speech.speakState){
					tempState = Text2Speech.speakState;
					runOnUiThread(new Runnable() {
						public void run() {
							if(Text2Speech.speakState == 0){
								iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_start"));
								tv_start_pause.setText("开始播放");
							}else if(Text2Speech.speakState == 1){
								iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_pause"));
								tv_start_pause.setText("暂停播放");
							}else if(Text2Speech.speakState == 2){
								iv_start_pause.setImageResource(IflytekRes.getDrawabelID(IflytekActivity.this, "media_start"));
								tv_start_pause.setText("继续播放");
							}
						}
					});					
				}
			}
		}, 0, 1000);
		
		
		
		
		
		
	}

}
