
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
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.canvas.CRect;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.spinner.JiuyiSpinnerSelPopwindow;
import com.control.widget.JiuyiEditText;

import com.jiuyi.app.JiuyiActivityBase;

/**
 * ****************************************************************
 * 文件名称 : JiuyiPasswordLockSetActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 开启密码锁定
 *****************************************************************
 */
public class JiuyiPasswordLockSetActivity extends JiuyiActivityBase {

	TextView m_pPasswordLableTextView;
	TextView m_pPasswordTextView;
	JiuyiEditText m_pPasswordEditText;
	String m_sPassword="";
	String m_sConfirmPassword="";
	boolean m_bIsConfirmStep=false;
	boolean m_bIsCheckPassword=false;//是否检测过密码（输入的密码与保存的密码是否一致）
	JiuyiSpinnerSelPopwindow m_pPopupWindow;//悬浮窗口
	private final int SHOWWINDOW=1;
	private final int ClOSEWINDOW=2;
	private final int RESTARTFINGERCHECK=3;//重启指纹

	private int m_nCheckPasswordCount=0;//检测密码次数（最多5次，第5次检测还是失败，则退出交易回到首页并且关闭密码锁定，需要用户重新设置）

	private TextView m_pErrorPasswordView;//错误标签
	private TextView m_pErrorPasswordResultView;//错误提示

	TextView m_pfingetlockTouchView;
	TextView m_pfingetlockLockView;

	JiuyiFingerCompatMyCallBack m_pFingerMyCallBack;//6.0及以上系统使用
	JiuyiFingerBefor6MyCallBack m_pFingerBefor6MyCallBack;//6.0一下系统使用

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		m_bIsConfirmStep=false;
		m_nCheckPasswordCount=0;
	}
	

	private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case SHOWWINDOW:
    				if(mPageType == Pub.PasswordLock_ResetPassword || mPageType == Pub.PasswordLock_Close){
    					//（重置密码需要线输入旧密码）
    					if(mPageType == Pub.PasswordLock_ResetPassword){
    						m_pPasswordLableTextView.setText("请输入旧密码");
    					}else if(mPageType == Pub.PasswordLock_Close){
    						m_pPasswordLableTextView.setText("请输入密码");
    					}
    					//弹出指纹解锁
    					if(JiuyiPasswordLockShared.getIns().getOpenFingerLock(JiuyiPasswordLockSetActivity.this)){
    						try {
    							CRect defRect = new CRect(mBodyLayout.getWidth()/2-Res.Dip2Pix(150), mBodyLayout.getHeight()/2-Res.Dip2Pix(120), mBodyLayout.getWidth()/2+Res.Dip2Pix(150),mBodyLayout.getHeight()/2+Res.Dip2Pix(120));
    							m_pPopupWindow = new JiuyiSpinnerSelPopwindow(JiuyiPasswordLockSetActivity.this);
    							LinearLayout pLinearLayout=OninitFingerLockLayout(mBodyLayout.getContext(),defRect);
    					    	WindowManager wm1 = getWindowManager();
    					    	int width1 = wm1.getDefaultDisplay().getWidth();
    					    	int height1 = wm1.getDefaultDisplay().getHeight();
    					    	CRect mainRect=new CRect(0, 0, width1, height1);
    					    	m_pPopupWindow.startPopwindow(JiuyiPasswordLockSetActivity.this, defRect, pLinearLayout,mainRect,false);
    					    	m_pPopupWindow.setCallback(new JiuyiSpinnerSelPopwindow.SpinnerPopupWindowCallback(){
    								@Override
    								public void onItemClick(int nAction, String[][] Items, int nSelindex) {
    									try {
    									} catch (Exception e) {
    									}
    								}
    							});
    					    	m_pFingerMyCallBack = new JiuyiFingerCompatMyCallBack(){
   					    		 // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
   					    	       @Override
   					    	       public void onAuthenticationError(int errMsgId, CharSequence errString) {
   					    	    	 if(errMsgId==5)//5==关闭指纹；7==操作过多
  				    	    		   return;
   					    	        //但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
									m_nCheckPasswordCount++;
								    JiuyiPasswordLockShared.getIns().setFingerCheckErrorCountADD(1);
								    m_pfingetlockTouchView.setText("再试一次");
									Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordLockSetActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
									m_pfingetlockTouchView.startAnimation(anim);
									if(errMsgId==7){
										if(m_pPopupWindow!=null){
											m_pPopupWindow.dismiss();
											m_pPopupWindow=null;
										}
										JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(6);
										JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
										startDialog(DialogID.DialogDoNothing, "", "操作过于频繁，指纹识别被禁用，请30秒之后重试", JiuyiDialogBase.Dialog_Type_Cancle);
										return;
									}
									if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount() > 4 || m_nCheckPasswordCount>4 ){
										JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(0);
										final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct("", 0, 0, "确定", Res.getDrawabelID(null,"jiuyi_dialog_buttonbg_confirm"), Res.getColor(null, "tzt_white"), false);
										startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle, struct);
									}
   					    	       }

   					    	       // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
   					    	       @Override
   					    	       public void onAuthenticationFailed() {
   					    	    	 //单次验证失败
					    	    	   m_nCheckPasswordCount++;
									   JiuyiPasswordLockShared.getIns().setFingerCheckErrorCountADD(1);
										m_pfingetlockTouchView.setText("再试一次");
										Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordLockSetActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
										m_pfingetlockTouchView.startAnimation(anim);
										if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4 || m_nCheckPasswordCount > 4){
											//关闭指纹弹框，启动监听指纹30秒，
											if(m_pPopupWindow!=null){
												m_pPopupWindow.dismiss();
												m_pPopupWindow=null;
											}
											JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
											final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct("", 0, 0, "确定", Res.getDrawabelID(null,"jiuyi_dialog_buttonbg_confirm"), Res.getColor(null, "tzt_white"), false);
											startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle, struct);
										}else{
										}
   					    	       }

   					    	       @Override
   					    	       public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
   					    	    	//显示确认密码界面
   									m_bIsConfirmStep=true;
   									m_pPasswordEditText.setText("");
   									m_pPasswordLableTextView.setText("重新输入密码");
   					    	       }

   					    	       // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
   					    	       @Override
   					    	       public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
	   					    	      //验证成功
									   JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(0);
					    	    	   //关闭浮框
	   					    	    	if(m_pPopupWindow!=null){
	   					    				m_pPopupWindow.dismiss();
	   					    				m_pPopupWindow=null;
	   					    			}
	   					    	    	if(mPageType == Pub.PasswordLock_ResetPassword){
	   					    	    		m_bIsCheckPassword=true;
	   										m_pPasswordEditText.setText("");
	   										m_pPasswordLableTextView.setText("请输入密码");
	   					    	    	}else if(mPageType == Pub.PasswordLock_Close){
	   										//关闭密码锁并退出界面
											JiuyiPasswordLockShared.getIns().setOpenPasswordLock(false, "");
	   										BackPage();
	   					    	    	}
   					    	       }
   					    	};

   					    	m_pFingerBefor6MyCallBack = new JiuyiFingerBefor6MyCallBack(){
  					    		 // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
					    	       @Override
					    	       public void onAuthenticationError(int errMsgId, CharSequence errString) {
					    	    	   if(errMsgId==5)//5==关闭指纹；7==操作过多
	  				    	    		   return;
					    	    	// 但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
									m_nCheckPasswordCount++;
								    JiuyiPasswordLockShared.getIns().setFingerCheckErrorCountADD(1);
									m_pfingetlockTouchView.setText("再试一次");
									Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordLockSetActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
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
									if (JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount() > 4 || m_nCheckPasswordCount > 4){
										JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(0);
										final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct("", 0, 0, "确定", Res.getDrawabelID(null,"jiuyi_dialog_buttonbg_confirm"), Res.getColor(null, "tzt_white"), false);
										startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle, struct);
									}
					    	       }

					    	       // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
					    	       @Override
					    	       public void onAuthenticationFailed() {
					    	    	//单次验证失败
									m_nCheckPasswordCount++;
								    JiuyiPasswordLockShared.getIns().setFingerCheckErrorCountADD(1);
									m_pfingetlockTouchView.setText("再试一次");
									Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordLockSetActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
									m_pfingetlockTouchView.startAnimation(anim);
									if (JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount() > 4 || m_nCheckPasswordCount > 4) {
										// 关闭指纹弹框，启动监听指纹30秒，
										if (m_pPopupWindow != null) {
											m_pPopupWindow.dismiss();
											m_pPopupWindow = null;
										}
										// "指纹识别被禁用，30秒之后", Toast.LENGTH_LONG);
										JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();// 监听30秒是否允许使用指纹
										final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct("", 0, 0, "确定", Res.getDrawabelID(null,"jiuyi_dialog_buttonbg_confirm"), Res.getColor(null, "tzt_white"), false);
										startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle, struct);
									} else {
									}
					    	       }

					    	       @Override
					    	       public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
					    	    	//显示确认密码界面
									m_bIsConfirmStep=true;
									m_pPasswordEditText.setText("");
									m_pPasswordLableTextView.setText("重新输入密码");
					    	       }

					    	       // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
					    	       @Override
					    	       public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
					    	    	   //验证成功
									   JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(0);
					    	    	   //关闭浮框
	   					    	    	if(m_pPopupWindow!=null){
	   					    				m_pPopupWindow.dismiss();
	   					    				m_pPopupWindow=null;
	   					    			}
	   					    	    	if(mPageType == Pub.PasswordLock_ResetPassword){
	   					    	    		m_bIsCheckPassword=true;
	   										m_pPasswordEditText.setText("");
	   										m_pPasswordLableTextView.setText("请输入密码");
	   					    	    	}else if(mPageType == Pub.PasswordLock_Close){
	   										//关闭密码锁并退出界面
											JiuyiPasswordLockShared.getIns().setOpenPasswordLock(false, "");
	   										BackPage();
	   					    	    	}
					    	       }
					    	};
							JiuyiPasswordLockShared.getIns().setCallback(m_pFingerMyCallBack,m_pFingerBefor6MyCallBack);
							JiuyiPasswordLockShared.getIns().StartFingerprint(mBodyLayout.getContext());
    						} catch (Exception e) {
                                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
    						}
    					}
    				}
                    break;
                case ClOSEWINDOW:
                    break;
                case RESTARTFINGERCHECK:
					JiuyiPasswordLockShared.getIns().setCallback(m_pFingerMyCallBack,m_pFingerBefor6MyCallBack);
					JiuyiPasswordLockShared.getIns().StartFingerprint(mBodyLayout.getContext());
                	break;
            }
        }


    };

	@Override
	public void onInit() {
		mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_v23_activity_passwordlockset_layout"), null);
		mBodyLayout.findTitleToolBars(this, this);
		mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
		setContentView(mBodyLayout);

		m_pPasswordEditText=(JiuyiEditText)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_edittext"));
		SetTextChanged(m_pPasswordEditText);

		m_pPasswordLableTextView=(TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_passwordlabletext"));
		m_pPasswordTextView=(TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_passwordtext"));

		m_pErrorPasswordView = (TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_errorpasswordview"));;
		m_pErrorPasswordResultView = (TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_errorpasswordresultview"));;
		m_pErrorPasswordView.setVisibility(View.INVISIBLE);
		m_pErrorPasswordResultView.setVisibility(View.INVISIBLE);


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

		Button pDelTextView=(Button)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_delbtn"));
		pDelTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(v==null)
					return;
				String stextaVlue=m_pPasswordEditText.getText().toString();
				if(stextaVlue.length()>0){
					stextaVlue=stextaVlue.substring(0, stextaVlue.length()-1);
					m_pPasswordEditText.setText(stextaVlue);
				}else{
					m_pPasswordEditText.setText("");
				}
			}
		});

		if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4){
			//是否要提示用指纹识别被禁用？

		}else{
			mHandler.sendEmptyMessageDelayed(SHOWWINDOW, 200);
		}
		setTitle();
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
				if(!Func.IsStringEmpty(str)){
					m_pErrorPasswordView.setVisibility(View.INVISIBLE);
					m_pErrorPasswordResultView.setVisibility(View.INVISIBLE);
				}
				if(m_bIsConfirmStep){
					setConfirmPassword(str);
				}else{
					setPassword(str);
				}
			}
		});
	}

	private void setPassword(String password){
		m_sPassword=password;
		String sValue="";
		for (int i=0;i<m_sPassword.length();i++){
			sValue+=".   ";
		}
		if(m_sPassword.length()<4){
			for (int i=0;i<4-m_sPassword.length();i++){
				sValue+="—   ";
			}
		}
		m_pPasswordTextView.setText(sValue);
		if(m_pPasswordEditText.length()==4){
			//比对密码
			byte[] buffer = m_sPassword.getBytes();

			if(mPageType == Pub.PasswordLock_Open){
				//显示确认密码界面
				m_bIsConfirmStep=true;
				//清空
				m_pPasswordEditText.setText("");
				m_pPasswordLableTextView.setText("重新输入密码");
			}else if(mPageType == Pub.PasswordLock_Close){
				if(JiuyiPasswordLockShared.getIns().checkPassword(buffer, JiuyiPasswordLockShared.getIns().getPassword())){
					//关闭密码锁并退出界面
					JiuyiPasswordLockShared.getIns().setOpenPasswordLock(false, "");
					BackPage();
				}else{
					//提示错误
					m_nCheckPasswordCount++;

					m_pErrorPasswordView.setVisibility(View.VISIBLE);
					m_pErrorPasswordResultView.setVisibility(View.VISIBLE);
					m_pErrorPasswordView.setText("密码错误");
					m_pErrorPasswordResultView.setText("注销前你还有"+(5-m_nCheckPasswordCount)+"次机会！");

					m_pPasswordEditText.setText("");
					if(m_nCheckPasswordCount>4){
						 //5次错误之后不允许再次使用指纹密码
						final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct("", 0, 0, "确定", Res.getDrawabelID(null,"jiuyi_dialog_buttonbg_confirm"), Res.getColor(null, "tzt_white"), false);
						startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle, struct);
					}
				}
			}else if(mPageType == Pub.PasswordLock_ResetPassword){
				if(m_bIsCheckPassword){
					//清空
					//显示确认密码界面
					m_bIsConfirmStep=true;
					m_pPasswordEditText.setText("");
					m_pPasswordLableTextView.setText("重新输入新密码");
				}else{
					if(JiuyiPasswordLockShared.getIns().checkPassword(buffer, JiuyiPasswordLockShared.getIns().getPassword())){
						m_bIsCheckPassword=true;
						m_pPasswordEditText.setText("");
						m_pPasswordLableTextView.setText("请输入新密码");
					}else{
						//提示错误
						m_nCheckPasswordCount++;

						m_pErrorPasswordView.setVisibility(View.VISIBLE);
						m_pErrorPasswordResultView.setVisibility(View.VISIBLE);
						m_pErrorPasswordView.setText("密码错误");
						m_pErrorPasswordResultView.setText("注销前你还有"+(5-m_nCheckPasswordCount)+"次机会！");

						m_pPasswordEditText.setText("");

						if(m_nCheckPasswordCount>4){
							 //5次错误之后不允许再次使用指纹密码
							final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct("", 0, 0, "确定", Res.getDrawabelID(null,"jiuyi_dialog_buttonbg_confirm"), Res.getColor(null, "tzt_white"), false);
							startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle, struct);
						}
					}
				}
			}else if(mPageType == Pub.PasswordLock_OpenFingerLock){
				//密码锁定
				if(JiuyiPasswordLockShared.getIns().checkPassword(buffer, JiuyiPasswordLockShared.getIns().getPassword())){
					//开启指纹锁定
					JiuyiPasswordLockShared.getIns().setOpenFignerLock(true);
					BackPage();
				}else{
					//提示错误
					m_nCheckPasswordCount++;
//					startDialog(m_nPageType, "", "密码输入错误，请重新输入！", MyDialog.Dialog_Type_Yes);

					m_pErrorPasswordView.setVisibility(View.VISIBLE);
					m_pErrorPasswordResultView.setVisibility(View.VISIBLE);
					m_pErrorPasswordView.setText("密码错误");
					m_pErrorPasswordResultView.setText("注销前你还有"+(5-m_nCheckPasswordCount)+"次机会！");

					m_pPasswordEditText.setText("");
					if(m_nCheckPasswordCount>4){
						 //5次错误之后不允许再次使用指纹密码
						final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct("", 0, 0, "确定", Res.getDrawabelID(null,"jiuyi_dialog_buttonbg_confirm"), Res.getColor(null, "tzt_white"), false);
						startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle, struct);
					}
				}
			}else if(mPageType==Pub.PasswordLock_CloseFingerLock){
				//密码锁定
				if(JiuyiPasswordLockShared.getIns().checkPassword(buffer, JiuyiPasswordLockShared.getIns().getPassword())){
					//开启指纹锁定
					JiuyiPasswordLockShared.getIns().setOpenFignerLock(false);
					BackPage();
				}else{
					//提示错误
					m_nCheckPasswordCount++;
					m_pErrorPasswordView.setVisibility(View.VISIBLE);
					m_pErrorPasswordResultView.setVisibility(View.VISIBLE);
					m_pErrorPasswordView.setText("密码错误");
					m_pErrorPasswordResultView.setText("注销前你还有"+(5-m_nCheckPasswordCount)+"次机会！");

					m_pPasswordEditText.setText("");
					if(m_nCheckPasswordCount>4){
						 //5次错误之后不允许再次使用指纹密码
						final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct("", 0, 0, "确定", Res.getDrawabelID(null,"jiuyi_dialog_buttonbg_confirm"), Res.getColor(null, "tzt_white"), false);
						startDialog(mPageType, "", Res.getString(null,"jiuyi_v23_passwordlockInputErrorMsg"), JiuyiDialogBase.Dialog_Type_Cancle, struct);
					}
				}
			}
		}
	}

	private void setConfirmPassword(String password){
		m_sConfirmPassword=password;
		String sValue="";
		for (int i=0;i<m_sConfirmPassword.length();i++){
			sValue+=".   ";
		}
		if(m_sConfirmPassword.length()<4){
			for (int i=0;i<4-m_sConfirmPassword.length();i++){
				sValue+="—   ";
			}
		}
		m_pPasswordTextView.setText(sValue);
		if(m_sConfirmPassword.length()==4){
			//比对密码
			if(m_sPassword.equals(m_sConfirmPassword)){
				if(mPageType == Pub.PasswordLock_ResetPassword){
					startDialog(mPageType, "", "密码修改成功！！", JiuyiDialogBase.Dialog_Type_Yes);
				}else{
					//开启密码锁并退出界面
					JiuyiPasswordLockShared.getIns().setOpenPasswordLock(true, m_sConfirmPassword);
					BackPage();
				}
			}else{
				//提示错误
				m_pErrorPasswordView.setVisibility(View.VISIBLE);
				m_pErrorPasswordResultView.setVisibility(View.VISIBLE);
				m_pErrorPasswordView.setText("无效密码");
				m_pErrorPasswordResultView.setText("前后密码输入不一致，请重新输入！");
				m_pPasswordEditText.setText("");
			}
		}
	}

	@Override
	public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
		if(nKeyCode== KeyEvent.KEYCODE_ENTER){
			if(mPageType == Pub.PasswordLock_ResetPassword){
				//开启密码锁并退出界面
				JiuyiPasswordLockShared.getIns().setOpenPasswordLock(true, m_sConfirmPassword);
				BackPage();
			}else{
				m_pPasswordEditText.setText("");
			}
		}else if(nKeyCode== KeyEvent.KEYCODE_BACK){
			if(mPageType != DialogID.DialogDoNothing){
				JiuyiPasswordLockShared.getIns().checkPasswordError();
			}
		}
	}

	/**
	 * 本UI中要求的Title样式
	 */
	public void setTitle() {
		if(mPageType == Pub.PasswordLock_Open){
			setTitle("设置锁屏密码");
		}else if(mPageType == Pub.PasswordLock_Close){
			setTitle("开启锁屏密码");
		}else if(mPageType == Pub.PasswordLock_ResetPassword){
			setTitle("更改锁屏密码");
		}else if(mPageType == Pub.PasswordLock_OpenFingerLock || mPageType == Pub.PasswordLock_CloseFingerLock){
			setTitle("密码验证");
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

	public void BackPage()
	{
		JiuyiPasswordLockShared.getIns().stopFingerprint();
		if(m_pPopupWindow!=null){
			m_pPopupWindow.dismiss();
			m_pPopupWindow=null;
		}
		finish();
	}


}
