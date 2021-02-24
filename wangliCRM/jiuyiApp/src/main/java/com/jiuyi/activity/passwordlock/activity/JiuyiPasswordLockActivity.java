
package com.jiuyi.activity.passwordlock.activity;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.control.callback.JiuyiFingerBefor6MyCallBack;
import com.control.callback.JiuyiFingerCompatMyCallBack;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.canvas.CRect;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.spinner.JiuyiSpinnerSelPopwindow;
import com.jiuyi.app.JiuyiActivityBase;

/**
 * ****************************************************************
 * 文件名称 : JiuyiPasswordLockActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 密码锁主页
 *****************************************************************
 */
public class JiuyiPasswordLockActivity extends JiuyiActivityBase {

	TextView m_pSetPasswordLockView;//设置密码锁
	TextView m_pSetPasswordLockLableView;//设置密码锁lable
	LinearLayout m_pSetfingerlocklayout;
	ImageView m_pSetFingerLock;//设置指纹锁
	TextView m_pChangePasswordTxt;
	TextView m_pSetLockPasswordTime;
	TextView m_pSetLockPasswordTimeLable;
	TextView m_pSetLockPasswordTimeFlag;
	
	LinearLayout m_pSplitLineLayout;
	LinearLayout m_pSetLockPasswordTimeLayout;
	
	JiuyiFingerCompatMyCallBack m_pFingerMyCallBack;
	JiuyiFingerBefor6MyCallBack m_pFingerBefor6MyCallBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onInit() {
		mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_v23_activity_passwordlock_layout"), null);
		mBodyLayout.findTitleToolBars(this,this);
		mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
		setContentView(mBodyLayout);
		
		m_pSetPasswordLockView=(TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_setpasswordlock"));
		m_pSetPasswordLockView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setPasswordLock();
			}
		});
		
		m_pChangePasswordTxt=(TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_changepassword"));
		m_pChangePasswordTxt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changePage(null, Pub.PasswordLock_ResetPassword, true);
			}
		});
		
		m_pSetFingerLock = (ImageView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_setfingerlock"));
		m_pSetFingerLock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!JiuyiPasswordLockShared.getIns().getOpenPasswordLock() && JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4){
					//没有开启密码锁则不能使用
					return;
				}
				
				if(JiuyiPasswordLockShared.getIns().getOpenFingerLock(JiuyiPasswordLockActivity.this)){
					//已经开启，则关闭
					m_pSetFingerLock.setImageResource(Res.getDrawabelID(getApplicationContext(), "tzt_v23_fingetlockunlock"));
				}else{
					//已经关闭，则开启
					m_pSetFingerLock.setImageResource(Res.getDrawabelID(getApplicationContext(), "tzt_v23_fingetlocklock"));
				}
				mHandler.sendEmptyMessageDelayed(SHOWWINDOW, 200);   
			}
		});
		
		
		m_pSetLockPasswordTimeLayout=(LinearLayout)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_setpasswordlocktimelayout"));
		m_pSetLockPasswordTime=(TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_setpasswordlocktime"));
		m_pSetLockPasswordTimeLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changePage(null, Pub.PasswordLock_SetLockTime, true);
			}
		});
		
		m_pSetfingerlocklayout = (LinearLayout)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_setfingerlocklayout"));
		m_pSetPasswordLockLableView = (TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_setfingerlocklable"));
		m_pSetLockPasswordTimeLable = (TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_setpasswordlocktimelable"));
		m_pSetLockPasswordTimeFlag = (TextView)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_setpasswordlocktimeflag"));
		
		m_pSplitLineLayout = (LinearLayout)mBodyLayout.findViewById(Res.getViewID(getApplicationContext(), "tzt_splitlinelayout"));
		
		SetPageShow();
		setTitle("APP锁屏密码");
	}
	
	private void SetPageShow(){

		if(JiuyiPasswordLockShared.getIns().getOpenPasswordLock()){
			m_pSetPasswordLockView.setText("关闭锁屏密码");
		}else{
			m_pSetPasswordLockView.setText("开启锁屏密码");
		}

		//时间
		//（判断时间是否有效）
		if(JiuyiPasswordLockShared.getIns().getOpenPasswordLock()){
			if(JiuyiPasswordLockShared.getIns().getPasswordLockTime()>0){
				m_pSetLockPasswordTime.setText(JiuyiPasswordLockShared.getIns().getPasswordLockTime()+"分钟后");
			}else if(JiuyiPasswordLockShared.getIns().getPasswordLockTime() == 0){
				m_pSetLockPasswordTime.setText("立即");
			}
			else{
				m_pSetLockPasswordTime.setText("");
			}
		}else{
			m_pSetLockPasswordTime.setText("");
		}

		if(!JiuyiPasswordLockShared.getIns().getOpenPasswordLock()){
			m_pChangePasswordTxt.setEnabled(false);
			m_pSetFingerLock.setEnabled(false);
			m_pSetLockPasswordTimeLayout.setEnabled(false);
			m_pSetFingerLock.setImageResource(Res.getDrawabelID(getApplicationContext(), "tzt_v23_fingetlockunlock"));
			m_pSetPasswordLockLableView.setEnabled(false);

			m_pSetLockPasswordTimeLable.setTextColor(Res.getColor(this, "tzt_v23_label_text_color"));
			m_pSetLockPasswordTimeFlag.setTextColor(Res.getColor(this, "tzt_v23_label_text_color"));
			m_pSetLockPasswordTime.setTextColor(Res.getColor(this, "tzt_v23_label_text_color"));

			m_pSetPasswordLockLableView.setTextColor(Res.getColor(this, "tzt_v23_label_text_color"));

			m_pChangePasswordTxt.setTextColor(Res.getColor(this, "tzt_v23_label_text_color"));


		}else{
			m_pSetLockPasswordTimeLable.setTextColor(Res.getColor(this, "tzt_v23_comm_text_color"));
			m_pSetLockPasswordTimeFlag.setTextColor(Res.getColor(this, "tzt_v23_comm_text_color"));
			m_pSetLockPasswordTime.setTextColor(Res.getColor(this, "tzt_v23_rise_color"));

			m_pChangePasswordTxt.setTextColor(Res.getColor(this, "tzt_v23_comm_text_color"));

			m_pChangePasswordTxt.setEnabled(true);
			if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4){
				m_pSetFingerLock.setEnabled(false);
				m_pSetPasswordLockLableView.setTextColor(Res.getColor(this, "tzt_v23_label_text_color"));
			}else{
				m_nCheckPasswordCount=0;
				m_pSetFingerLock.setEnabled(true);
				m_pSetPasswordLockLableView.setTextColor(Res.getColor(this, "tzt_v23_comm_text_color"));
			}
			m_pSetLockPasswordTimeLayout.setEnabled(true);
			if(JiuyiPasswordLockShared.getIns().getOpenFingerLock(this)){
				m_pSetFingerLock.setImageResource(Res.getDrawabelID(getApplicationContext(), "tzt_v23_fingetlocklock"));
			}else{
				m_pSetFingerLock.setImageResource(Res.getDrawabelID(getApplicationContext(), "tzt_v23_fingetlockunlock"));
			}
			m_pSetPasswordLockLableView.setEnabled(true);
		}


		if(JiuyiPasswordLockShared.getIns().isSupportFinger(this)){
			m_pSetfingerlocklayout.setVisibility(View.VISIBLE);
			m_pSplitLineLayout.setVisibility(View.GONE);
		}else{
			m_pSetfingerlocklayout.setVisibility(View.GONE);
			m_pSplitLineLayout.setVisibility(View.GONE);
		}
	}

	public void setTitle() {
		if(Func.IsStringEmpty(mTitle)){
			if(Func.IsStringEmpty(mTitle))
				mTitle = "APP锁屏密码";
		}
		setTitle(mTitle);
	}
	
	private void setPasswordLock(){
		
		if(JiuyiPasswordLockShared.getIns().getOpenPasswordLock()){
			//跳转输入密码
			changePage(null, Pub.PasswordLock_Close, true);
		}else{
			//跳转设置
			changePage(null, Pub.PasswordLock_Open, true);
		}
	}

	JiuyiSpinnerSelPopwindow m_pPopupWindow;//悬浮窗口
	private final int SHOWWINDOW=1;
	private final int ClOSEWINDOW=2;
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
					try {
						CRect defRect = new CRect(mBodyLayout.getWidth()/2-Res.Dip2Pix(150), mBodyLayout.getHeight()/2-Res.Dip2Pix(120), mBodyLayout.getWidth()/2+Res.Dip2Pix(150),mBodyLayout.getHeight()/2+Res.Dip2Pix(120));
						m_pPopupWindow = new JiuyiSpinnerSelPopwindow(JiuyiPasswordLockActivity.this);
						LinearLayout pLinearLayout=OninitFingerLockLayout(mBodyLayout.getContext(),defRect);
				    	WindowManager wm1 = getWindowManager();
				    	int width1 = wm1.getDefaultDisplay().getWidth();
				    	int height1 = wm1.getDefaultDisplay().getHeight();
				    	CRect mainRect=new CRect(0, 0, width1, height1);
				    	m_pPopupWindow.startPopwindow(JiuyiPasswordLockActivity.this, defRect, pLinearLayout,mainRect,false);
				    	m_pPopupWindow.setCallback(new JiuyiSpinnerSelPopwindow.SpinnerPopupWindowCallback(){
							@Override
							public void onItemClick(int nAction, String[][] Items, int nSelindex) {
								try {
									//关闭弹框
									if(JiuyiPasswordLockShared.getIns().getOpenFingerLock(JiuyiPasswordLockActivity.this)){
										//已经开启
										m_pSetFingerLock.setImageResource(Res.getDrawabelID(getApplicationContext(), "tzt_v23_fingetlocklock"));
									}else{
										//已经关闭
										m_pSetFingerLock.setImageResource(Res.getDrawabelID(getApplicationContext(), "tzt_v23_fingetlockunlock"));
									}

								} catch (Exception e) {
								}
							}
						});
				    	m_pFingerMyCallBack=new JiuyiFingerCompatMyCallBack(){
				    		 // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
				    	       @Override
								public void onAuthenticationError(int errMsgId, CharSequence errString) {
				    	    	   if(errMsgId==5)//5==关闭指纹；7==操作过多
				    	    		   return;
									// 但多次指纹密码验证错误后，进入此方法；并且，不能短时间内调用指纹验证
									m_nCheckPasswordCount++;
								   JiuyiPasswordLockShared.getIns().setFingerCheckErrorCountADD(1);
									m_pfingetlockTouchView.setText("再试一次");
									Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordLockActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
									m_pfingetlockTouchView.startAnimation(anim);

									if(errMsgId==7){
										if(m_pPopupWindow!=null){
											m_pPopupWindow.dismiss();
											m_pPopupWindow=null;
										}
//										Toast.makeText(Pub.getApplication(), "指纹识别被禁用，30秒之后", Toast.LENGTH_LONG);
										m_pSetFingerLock.setEnabled(false);
										m_pSetPasswordLockLableView.setTextColor(Res.getColor(Rc.getApplication(), "tzt_v23_label_text_color"));
										JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(6);
										JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
										startDialog(DialogID.DialogDoNothing, "", "连续操作过于频繁，指纹识别被禁用，请30秒之后重试", JiuyiDialogBase.Dialog_Type_Cancle);
										return;
									}
									if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4 || m_nCheckPasswordCount > 4){
										JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
										//设置指纹不能点击
										if(m_pPopupWindow!=null){
											m_pPopupWindow.dismiss();
											m_pPopupWindow=null;
										}
										m_pSetFingerLock.setEnabled(false);
										m_pSetPasswordLockLableView.setTextColor(Res.getColor(Rc.getApplication(), "tzt_v23_label_text_color"));
										JiuyiPasswordLockShared.getIns().stopFingerprint();
										startDialog(DialogID.DialogDoNothing, "", "指纹已禁用，请30秒之后重试", JiuyiDialogBase.Dialog_Type_Yes);
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
									Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordLockActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
									m_pfingetlockTouchView.startAnimation(anim);
									if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4 || m_nCheckPasswordCount > 4){
										//关闭指纹弹框，启动监听指纹30秒，
										if(m_pPopupWindow!=null){
											m_pPopupWindow.dismiss();
											m_pPopupWindow=null;
										}
										m_pSetFingerLock.setEnabled(false);
										m_pSetPasswordLockLableView.setTextColor(Res.getColor(Rc.getApplication(), "tzt_v23_label_text_color"));
//										Toast.makeText(Pub.getApplication(), "指纹识别被禁用，30秒之后", Toast.LENGTH_LONG);
										startDialog(DialogID.DialogDoNothing, "", "指纹已禁用，请30秒之后重试", JiuyiDialogBase.Dialog_Type_Yes);
										JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
									}
				    	       }

				    	       @Override
				    	       public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
				    	       }

				    	       // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
				    	       @Override
				    	       public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
				    	    	   //验证成功
				    	    	   m_nCheckPasswordCount=0;
				    	    	   CheckSuccess();
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
									Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordLockActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
									m_pfingetlockTouchView.startAnimation(anim);
									if(errMsgId==7){
										if(m_pPopupWindow!=null){
											m_pPopupWindow.dismiss();
											m_pPopupWindow=null;
										}
//										Toast.makeText(Pub.getApplication(), "指纹识别被禁用，30秒之后", Toast.LENGTH_LONG);
										m_pSetFingerLock.setEnabled(false);
										m_pSetPasswordLockLableView.setTextColor(Res.getColor(Rc.getApplication(), "tzt_v23_label_text_color"));
										JiuyiPasswordLockShared.getIns().setFingerCheckErrorCount(6);
										JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
										startDialog(DialogID.DialogDoNothing, "", "连续操作过于频繁，指纹识别被禁用，请30秒之后重试", JiuyiDialogBase.Dialog_Type_Cancle);
										return;
									}
									if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4 || m_nCheckPasswordCount > 4){
										JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
										//设置指纹不能点击
										if(m_pPopupWindow!=null){
											m_pPopupWindow.dismiss();
											m_pPopupWindow=null;
										}
										JiuyiPasswordLockShared.getIns().stopFingerprint();
										m_pSetFingerLock.setEnabled(false);
										m_pSetPasswordLockLableView.setTextColor(Res.getColor(Rc.getApplication(), "tzt_v23_label_text_color"));
										startDialog(DialogID.DialogDoNothing, "", "指纹已禁用，请30秒之后重试", JiuyiDialogBase.Dialog_Type_Yes);
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
									Animation anim = AnimationUtils.loadAnimation(JiuyiPasswordLockActivity.this, Res.getAnimationID(getApplicationContext(), "tzt_v23_passwordlockshake"));
									m_pfingetlockTouchView.startAnimation(anim);
									if(JiuyiPasswordLockShared.getIns().getFingerCheckErrorCount()>4||m_nCheckPasswordCount > 4){
										//关闭指纹弹框，启动监听指纹30秒，
										if(m_pPopupWindow!=null){
											m_pPopupWindow.dismiss();
											m_pPopupWindow=null;
										}
										m_pSetFingerLock.setEnabled(false);
										m_pSetPasswordLockLableView.setTextColor(Res.getColor(Rc.getApplication(), "tzt_v23_label_text_color"));
										startDialog(DialogID.DialogDoNothing, "", "指纹已禁用，请30秒之后重试", JiuyiDialogBase.Dialog_Type_Yes);
										JiuyiPasswordLockShared.getIns().startCheckFingerInvalid();//监听30秒是否允许使用指纹
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
						JiuyiPasswordLockShared.getIns().setCallback(m_pFingerMyCallBack,m_pFingerBefor6MyCallBack);
						JiuyiPasswordLockShared.getIns().StartFingerprint(mBodyLayout.getContext());
					} catch (Exception e) {
                        JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
					}
                    break;  
                case ClOSEWINDOW:
                    break;  
            }  
        }  
  
  
    };  
    
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
    
    private void CheckSuccess(){
    	//指纹解锁成功跳转密码验证
		JiuyiPasswordLockShared.getIns().stopFingerprint();
		if(m_pPopupWindow!=null){
			m_pPopupWindow.dismiss();
			m_pPopupWindow=null;
		}
		if(JiuyiPasswordLockShared.getIns().getOpenFingerLock(this)){
			changePage(null, Pub.PasswordLock_CloseFingerLock, true);
		}else{
			changePage(null, Pub.PasswordLock_OpenFingerLock, true);
		}
    	
    }

	@Override
	public void onDestroy() {
		JiuyiPasswordLockShared.getIns().stopFingerprint();
		super.onDestroy();
	}

	@Override
    public void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    }
    
    @Override
    public void onResume() {
    	super.onResume();

    	if(m_pSetPasswordLockView != null) {
            if (JiuyiPasswordLockShared.getIns().getOpenPasswordLock()) {
                m_pSetPasswordLockView.setText("关闭锁屏密码");
            } else {
                m_pSetPasswordLockView.setText("开启锁屏密码");
            }
        }

		SetPageShow();

    	if(m_pFingerMyCallBack!=null && m_pFingerBefor6MyCallBack!=null){
			JiuyiPasswordLockShared.getIns().setCallback(m_pFingerMyCallBack,m_pFingerBefor6MyCallBack);
    	}
    }
}
