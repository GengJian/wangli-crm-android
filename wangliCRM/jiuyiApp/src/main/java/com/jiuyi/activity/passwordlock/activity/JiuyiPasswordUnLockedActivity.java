
package com.jiuyi.activity.passwordlock.activity;

import android.app.Dialog;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.callback.JiuyiFingerBefor6MyCallBack;
import com.control.callback.JiuyiFingerCompatMyCallBack;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.canvas.CRect;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.spinner.JiuyiSpinnerSelPopwindow;
import com.control.widget.JiuyiEditText;
import com.control.widget.webview.JiuyiWebView;
import com.jiuyi.app.JiuyiActivityBase;


/**
 * ****************************************************************
 * 文件名称 : JiuyiPasswordUnLockedActivity
 * 作    者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 解锁
 *****************************************************************
 */
public class JiuyiPasswordUnLockedActivity extends JiuyiActivityBase {
	JiuyiSpinnerSelPopwindow m_pPopupWindow;//悬浮窗口
	private final int SHOWWINDOW=1;
	private final int ClOSEWINDOW=2;

	private final int DELPASSWORD=1;
	private final int CANCELPASSWORD=2;

	TextView m_pPasswordTextView;
	JiuyiEditText m_pPasswordEditText;
	LinearLayout m_pAnimationLayout;
	TextView m_pDelTextView;

	private JiuyiWebView m_pWebView;

	private int m_nCheckPasswordCount=0;//检测密码次数（最多5次，第5次检测还是失败，则退出交易回到首页并且关闭密码锁定，需要用户重新设置）

	TextView m_pfingetlockTouchView;
	TextView m_pfingetlockLockView;

	private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case SHOWWINDOW:
					//弹出指纹解锁
					if(JiuyiPasswordLockShared.getIns().getOpenFingerLock(JiuyiPasswordUnLockedActivity.this)){
						try {
							CRect defRect = new CRect(mBodyLayout.getWidth()/2-Res.Dip2Pix(150), mBodyLayout.getHeight()/2-Res.Dip2Pix(120), mBodyLayout.getWidth()/2+Res.Dip2Pix(150),mBodyLayout.getHeight()/2+Res.Dip2Pix(120));
							m_pPopupWindow = new JiuyiSpinnerSelPopwindow(JiuyiPasswordUnLockedActivity.this);
							LinearLayout pLinearLayout=OninitFingerLockLayout(mBodyLayout.getContext(),defRect);
					    	WindowManager wm1 = getWindowManager();
					    	int width1 = wm1.getDefaultDisplay().getWidth();
					    	int height1 = wm1.getDefaultDisplay().getHeight();
					    	CRect mainRect=new CRect(0, 0, width1, height1);
					    	m_pPopupWindow.startPopwindow(JiuyiPasswordUnLockedActivity.this, defRect, pLinearLayout,mainRect,false);
					    	m_pPopupWindow.setCallback(new JiuyiSpinnerSelPopwindow.SpinnerPopupWindowCallback(){
								@Override
								public void onItemClick(int nAction, String[][] Items, int nSelindex) {
									try {
										//判断是否验证成功，成功则后退
									} catch (Exception e) {
									}
								}
							});
					    	JiuyiFingerCompatMyCallBack pFingerMyCallBack=new JiuyiFingerCompatMyCallBack(){
					    		// 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
					    	       @Override
									public void onAuthenticationError(int errMsgId, CharSequence errString) {
					    	    	   if(errMsgId==5)//5==关闭指纹；7==操作过多
	  				    	    		   return;
										// 但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
										m_nCheckPasswordCount++;
										JiuyiPasswordLockShared.getIns().setFingerCheckErrorCountADD(1);
										m_pfingetlockTouchView.setText("再试一次");
										Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordUnLockedActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
										m_pfingetlockTouchView.startAnimation(anim);
										if(errMsgId==7){
											if(m_pPopupWindow!=null){
												m_pPopupWindow.dismiss();
												m_pPopupWindow=null;
											}
											JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(6);
											JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
											startDialog(DialogID.DialogDoNothing, "", "连续操作过于频繁，指纹识别被禁用，请30秒之后重试", JiuyiDialogBase.Dialog_Type_Cancle);
											return;
										}
										if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4 || m_nCheckPasswordCount > 4){
											//关闭指纹弹框，启动监听指纹30秒，
											if(m_pPopupWindow!=null){
												m_pPopupWindow.dismiss();
												m_pPopupWindow=null;
											}
											JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
											startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle);
										}else{
										}
									}

					    	       // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
					    	       @Override
					    	       public void onAuthenticationFailed() {
					    	    	   //单次验证失败
					    	    	   m_nCheckPasswordCount++;
					    	    	   JiuyiPasswordLockShared.getIns().setFingerCheckErrorCountADD(1);
										m_pfingetlockTouchView.setText("再试一次");
										Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordUnLockedActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
										m_pfingetlockTouchView.startAnimation(anim);
										if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4 || m_nCheckPasswordCount > 4){
											//关闭指纹弹框，启动监听指纹30秒，
											if(m_pPopupWindow!=null){
												m_pPopupWindow.dismiss();
												m_pPopupWindow=null;
											}
											JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
											startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Yes);
										}else{

										}
					    	       }

					    	       @Override
					    	       public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
					    	       }

					    	       // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
					    	       @Override
					    	       public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
					    	    	   //验证成功
					    	    	   JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(0);
					    	    	   CheckSuccess();
					    	       }
					    	};
					    	JiuyiFingerBefor6MyCallBack pFingerBefor6MyCallBack = new JiuyiFingerBefor6MyCallBack(){
					    		// 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
					    	       @Override
									public void onAuthenticationError(int errMsgId, CharSequence errString) {
					    	    	   if(errMsgId==5)//5==关闭指纹；7==操作过多
	  				    	    		   return;
										// 但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
										m_nCheckPasswordCount++;
										JiuyiPasswordLockShared.getIns().setFingerCheckErrorCountADD(1);
										m_pfingetlockTouchView.setText("再试一次");
										Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordUnLockedActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
										m_pfingetlockTouchView.startAnimation(anim);

										if(errMsgId==7){
											if(m_pPopupWindow!=null){
												m_pPopupWindow.dismiss();
												m_pPopupWindow=null;
											}
											JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(6);
											JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
											startDialog(DialogID.DialogDoNothing, "", "连续操作过于频繁，指纹识别被禁用，请30秒之后重试", JiuyiDialogBase.Dialog_Type_Cancle);
											return;
										}
										if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4 || m_nCheckPasswordCount > 4){
											//关闭指纹弹框，启动监听指纹30秒，
											if(m_pPopupWindow!=null){
												m_pPopupWindow.dismiss();
												m_pPopupWindow=null;
											}
											JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
											startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle);
										}else{
										}
									}

					    	       // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
					    	       @Override
					    	       public void onAuthenticationFailed() {
					    	    	   //单次验证失败
					    	    	   m_nCheckPasswordCount++;
					    	    	   JiuyiPasswordLockShared.getIns().setFingerCheckErrorCountADD(1);
										m_pfingetlockTouchView.setText("再试一次");
										Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordUnLockedActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
										m_pfingetlockTouchView.startAnimation(anim);
										if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4 || m_nCheckPasswordCount > 4){
											//关闭指纹弹框，启动监听指纹30秒，
											if(m_pPopupWindow!=null){
												m_pPopupWindow.dismiss();
												m_pPopupWindow=null;
											}
											startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Yes);
											JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
										}else{
										}
					    	       }

					    	       @Override
					    	       public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
					    	       }

					    	       // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
					    	       @Override
					    	       public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
					    	    	   //验证成功
					    	    	   JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(0);
					    	    	   CheckSuccess();
					    	       }
					    	};
					    	JiuyiPasswordLockShared.getIns().setCallback(pFingerMyCallBack,pFingerBefor6MyCallBack);
					    	JiuyiPasswordLockShared.getIns().StartFingerprint(mBodyLayout.getContext());
						} catch (Exception e) {
                            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
						}
					}
                    break;
                case ClOSEWINDOW:
                    break;
            }
        }


    };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

    //完全沉浸式
    public void setSystemBarTint(boolean isFullBarTint) {
        super.setSystemBarTint(true);
    }

    /**
     * 根据皮肤设置activity主题
     */
    public void setActivityTheme(){

    }

    @Override
	public void onInit() {
		mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_v23_activity_passwordlockunlocked_layout"), null);
		mBodyLayout.findTitleToolBars(this, this);
		setContentView(mBodyLayout);

		m_pAnimationLayout = (LinearLayout)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_passwordlayout"));

		m_pPasswordEditText = (JiuyiEditText)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_edittext"));
		SetTextChanged(m_pPasswordEditText);

		m_pPasswordTextView=(TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_passwordtext"));

		Button pButton1=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button1"));
		pButton1.setOnClickListener(m_pButtonClick);
		Button pButton2=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button2"));
		pButton2.setOnClickListener(m_pButtonClick);
		Button pButton3=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button3"));
		pButton3.setOnClickListener(m_pButtonClick);
		Button pButton4=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button4"));
		pButton4.setOnClickListener(m_pButtonClick);
		Button pButton5=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button5"));
		pButton5.setOnClickListener(m_pButtonClick);
		Button pButton6=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button6"));
		pButton6.setOnClickListener(m_pButtonClick);
		Button pButton7=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button7"));
		pButton7.setOnClickListener(m_pButtonClick);
		Button pButton8=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button8"));
		pButton8.setOnClickListener(m_pButtonClick);
		Button pButton9=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button9"));
		pButton9.setOnClickListener(m_pButtonClick);
		Button pButton0=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_button0"));
		pButton0.setOnClickListener(m_pButtonClick);

		m_pDelTextView=(TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_delbtn"));
		m_pDelTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(v==null || v.getTag()==null)
					return;
				if(Func.parseInt(v.getTag().toString())==DELPASSWORD){
					String stextaVlue=m_pPasswordEditText.getText().toString();
					if(stextaVlue.length()>0){
						stextaVlue=stextaVlue.substring(0, stextaVlue.length()-1);
						m_pPasswordEditText.setText(stextaVlue);
					}else{
						m_pPasswordEditText.setText("");
					}
				}else{
					return;
				}
			}
		});


		m_pWebView=new JiuyiWebView(mBodyLayout.getContext());

		if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4){

		}else{
			mHandler.sendEmptyMessageDelayed(SHOWWINDOW, 200);
		}

	}

	private LinearLayout OninitFingerLockLayout(Context context,CRect defRect){
		LinearLayout pMenuLayout = (LinearLayout) LayoutInflater.from(context).inflate(Res.getLayoutID(context, "jiuyi_v23_passwordlock_fingerlocklayout"), null);
		pMenuLayout.setLayoutParams(new LayoutParams(defRect.Width(), defRect.Height()));

		m_pfingetlockTouchView=(TextView)pMenuLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_fingetlock_touchlable"));
		m_pfingetlockLockView=(TextView)pMenuLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_fingetlock_locklable"));;

		m_pfingetlockTouchView.setText("“"+getApplicationContext().getString(Res.getStringID(getApplicationContext(), "jiuyi_app_name"))+"”"+"的Touch ID");

		TextView pCancelView=(TextView)pMenuLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_fingetlock_cancel"));
		pCancelView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(m_pPopupWindow!=null){
					m_pPopupWindow.dismiss();
					m_pPopupWindow=null;
				}
			}
		});

		return pMenuLayout;
	}

	public void SetTextChanged(final JiuyiEditText edittext) {
		edittext.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable v) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String str = edittext.getText().toString();
				if(Func.IsStringEmpty(str)){
					m_pDelTextView.setVisibility(View.INVISIBLE);
				}else{
					m_pDelTextView.setVisibility(View.VISIBLE);
					m_pDelTextView.setText("删除");
					m_pDelTextView.setTag(DELPASSWORD);
				}
				setPassword(str);
			}
		});
	}

	private void setPassword(String password){
		String sValue="";
		for (int i=0;i<password.length();i++){
			sValue+=".   ";
		}
		if(password.length()<4){
			for (int i=0;i<4-password.length();i++){
				sValue+="—   ";
			}
		}
		m_pPasswordTextView.setText(sValue);
		if(password.length()==4){
			//比对密码
			byte[] buffer = password.getBytes();
			if(JiuyiPasswordLockShared.getIns().checkPassword(buffer, JiuyiPasswordLockShared.getIns().getPassword())){
				CheckSuccess();
			}else{
				//提示错误
				m_nCheckPasswordCount++;
				m_pPasswordEditText.setText("");
				m_pDelTextView.setVisibility(View.INVISIBLE);
				Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordUnLockedActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
				m_pAnimationLayout.startAnimation(anim);
				JiuyiPasswordLockShared.getIns().StartVibrator(this);
				if (m_nCheckPasswordCount > 4) {
					// 5次错误之后不允许再次使用指纹密码
					startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Yes);
				}
			}
		}
	}

	private void CheckSuccess(){
		JiuyiPasswordLockShared.getIns().setIsLockTrade(false);
		JiuyiPasswordLockShared.getIns().stopFingerprint();
		String url= mPassworkUnLockNextPageUrl;
		String sAction=mPassworkUnLockNextPageType;
		int nNextAction=Func.parseInt(sAction);
		if(!Func.IsStringEmpty(url) && nNextAction>0){
			if(nNextAction== Pub.JY_MENU_WebLogin){
				if(m_pWebView != null){
					m_pWebView.loadUrl("http://action:"+Pub.JY_MENU_WebLogin +"/?"+url);
				}
			}else{
				Bundle bundle =new Bundle();
				bundle.putString(JiuyiBundleKey.PARAM_UNLOCKPASSWORDNEXTPAGETYPE, nNextAction+"");
				bundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);

				changePage(bundle, nNextAction, false);
			}
		}else if(nNextAction>0){
			changePage(null, nNextAction, false);
		}else{
			BackPage();
		}
	}

	private OnClickListener m_pButtonClick=new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v==null || v.getTag()==null || !(v instanceof Button)){
				return;
			}
			String stextaVlue=m_pPasswordEditText.getText().toString();
			if(stextaVlue.length()>=4){
			  return;
			}
			m_pPasswordEditText.setText(stextaVlue+v.getTag().toString());
		}
	};

	public void BackPage() {
		finish();
	}

	@Override
	public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
		if(nKeyCode== KeyEvent.KEYCODE_ENTER){
			JiuyiPasswordLockShared.getIns().checkPasswordError();
		}
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		//拦截返回键
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK){
			//判断触摸UP事件才会进行返回事件处理
			if (event.getAction() == KeyEvent.ACTION_UP) {
				onBackPressed();
			}
			//只要是返回事件，直接返回true，表示消费掉
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
	@Override
	public void onBackPressed() {
		return;
	}



}
