package com.control.widget.keyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.control.Interface.JiuyiICanvasInterface;
import com.control.callback.JiuyiIEditTextCallBack;
import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditText;

import java.util.List;
import java.util.Random;



public class JiuyiKeyBoardView extends KeyboardView {
	private boolean mIsPwd;// 是否是密码
	private boolean bClickSystem = false;// 是否点击了系统键盘
    public int mDefaultKeyBoardHeight = Res.Dip2Pix(200);
	
	private int m_nKeyBoardType = 0;// 键盘类型
	private final int KEYBOARDTYPE_Num = 0;
	private final int KEYBOARDTYPE_ABCSMALL = 1;
	private final int KEYBOARDTYPE_ABCBIG = 2;
	private final int KEYBOARDTYPE_Trade = 3;
	private final int KEYBOARDTYPE_PASSWORD = 4;
	private final int KEYBOARDTYPE_System = 9;
	
	private String inputId;//网页的input，input的id
	private String jsfunname;//网页的input，要执行的函数
	private JiuyiEditText mEditText;
	private LatinKeyboard keyboardNum;
	private LatinKeyboard keyboardABCBIG;
	private LatinKeyboard keyboardABCSMALL;
	private LatinKeyboard keyboardTrade;
	private LatinKeyboard keyboardPassword;
	private Context mContext;
    private JiuyiIEditTextCallBack mCallBack;
	private boolean mScrollTo = false;
	private int mScrollToValue = -1;

	/** 空格 **/
	// private final int KEYBOARD_KEYCODE_599 = 66599;
	/** 00 **/
	private final int KEYBOARD_KEYCODE_00 = 66594;
	/** 全部 **/
	public static final int KEYBOARD_KEYCODE_ALL = 66595;
	/** 1/2 **/
	public static final int KEYBOARD_KEYCODE_2F1 = 66596;
	/** 1/3 **/
	public static final int KEYBOARD_KEYCODE_3F1 = 66597;
	/** 1/4 **/
	public static final int KEYBOARD_KEYCODE_4F1 = 66598;
	/** 601 **/
	private final int KEYBOARD_KEYCODE_601 = 66599;
	/** 600 **/
	private final int KEYBOARD_KEYCODE_600 = 66600;
	/** "." **/
	private final int KEYBOARD_KEYCODE_POINT = 66601;
	/** 300 **/
	private final int KEYBOARD_KEYCODE_300 = 66602;
	/** 002 **/
	private final int KEYBOARD_KEYCODE_002 = 66603;
	/** 000 **/
	private final int KEYBOARD_KEYCODE_000 = 66604;
	/** 数字切换到字母 **/
	private final int KEYBOARD_KEYCODE_SWITCHCHAR = 66605;
	/** 切换系统键盘 **/
	private final int KEYBOARD_KEYCODE_SYSTEM = 66606;
	/** 确定 **/
	public static final int KEYBOARD_KEYCODE_OK = 66607;
	/** 切换到小写 **/
	private final int KEYBOARD_KEYCODE_TOUP = 66608;
	/** 隐藏 **/
	private final int KEYBOARD_KEYCODE_HIDE = 66609;
	/** 删除 **/
	private final int KEYBOARD_KEYCODE_DEL = 66610;
	/** 1 **/
	private final int KEYBOARD_KEYCODE_1 = 66611;
	/** 2 **/
	private final int KEYBOARD_KEYCODE_2 = 66612;
	/** 3 **/
	private final int KEYBOARD_KEYCODE_3 = 66613;
	/** 4 **/
	private final int KEYBOARD_KEYCODE_4 = 66614;
	/** 5 **/
	private final int KEYBOARD_KEYCODE_5 = 66615;
	/** 6 **/
	private final int KEYBOARD_KEYCODE_6 = 66616;
	/** 7 **/
	private final int KEYBOARD_KEYCODE_7 = 66617;
	/** 8 **/
	private final int KEYBOARD_KEYCODE_8 = 66618;
	/** 9 **/
	private final int KEYBOARD_KEYCODE_9 = 66619;
	/** 0 **/
	private final int KEYBOARD_KEYCODE_0 = 66620;
	/** 切换到123 **/
	private final int KEYBOARD_KEYCODE_123 = 66621;
	/** 切换到大写 **/
	private final int KEYBOARD_KEYCODE_SHIFT = 66622;
	/** 小写a **/
	private final int KEYBOARD_KEYCODE_SMALL_A = 66623;
	/** 小写b **/
	private final int KEYBOARD_KEYCODE_SMALL_B = 66624;
	/** 小写c **/
	private final int KEYBOARD_KEYCODE_SMALL_C = 66625;
	/** 小写d **/
	private final int KEYBOARD_KEYCODE_SMALL_D = 66626;
	/** 小写e **/
	private final int KEYBOARD_KEYCODE_SMALL_E = 66627;
	/** 小写f **/
	private final int KEYBOARD_KEYCODE_SMALL_F = 66628;
	/** 小写g **/
	private final int KEYBOARD_KEYCODE_SMALL_G = 66629;
	/** 小写h **/
	private final int KEYBOARD_KEYCODE_SMALL_H = 66630;
	/** 小写i **/
	private final int KEYBOARD_KEYCODE_SMALL_I = 66631;
	/** 小写j **/
	private final int KEYBOARD_KEYCODE_SMALL_J = 66632;
	/** 小写k **/
	private final int KEYBOARD_KEYCODE_SMALL_K = 66633;
	/** 小写l **/
	private final int KEYBOARD_KEYCODE_SMALL_L = 66634;
	/** 小写m **/
	private final int KEYBOARD_KEYCODE_SMALL_M = 66635;
	/** 小写n **/
	private final int KEYBOARD_KEYCODE_SMALL_N = 66636;
	/** 小写o **/
	private final int KEYBOARD_KEYCODE_SMALL_O = 66637;
	/** 小写p **/
	private final int KEYBOARD_KEYCODE_SMALL_P = 66638;
	/** 小写q **/
	private final int KEYBOARD_KEYCODE_SMALL_Q = 66639;
	/** 小写r **/
	private final int KEYBOARD_KEYCODE_SMALL_R = 66640;
	/** 小写s **/
	private final int KEYBOARD_KEYCODE_SMALL_S = 66641;
	/** 小写t **/
	private final int KEYBOARD_KEYCODE_SMALL_T = 66642;
	/** 小写u **/
	private final int KEYBOARD_KEYCODE_SMALL_U = 66643;
	/** 小写v **/
	private final int KEYBOARD_KEYCODE_SMALL_V = 66644;
	/** 小写w **/
	private final int KEYBOARD_KEYCODE_SMALL_W = 66645;
	/** 小写x **/
	private final int KEYBOARD_KEYCODE_SMALL_X = 66646;
	/** 小写y **/
	private final int KEYBOARD_KEYCODE_SMALL_Y = 66647;
	/** 小写z **/
	private final int KEYBOARD_KEYCODE_SMALL_Z = 66648;
	/** 大写A **/
	private final int KEYBOARD_KEYCODE_BIGER_A = 66649;
	/** 大写B **/
	private final int KEYBOARD_KEYCODE_BIGER_B = 66650;
	/** 大写C **/
	private final int KEYBOARD_KEYCODE_BIGER_C = 66651;
	/** 大写D **/
	private final int KEYBOARD_KEYCODE_BIGER_D = 66652;
	/** 大写E **/
	private final int KEYBOARD_KEYCODE_BIGER_E = 66653;
	/** 大写F **/
	private final int KEYBOARD_KEYCODE_BIGER_F = 66654;
	/** 大写G **/
	private final int KEYBOARD_KEYCODE_BIGER_G = 66655;
	/** 大写H **/
	private final int KEYBOARD_KEYCODE_BIGER_H = 66656;
	/** 大写I **/
	private final int KEYBOARD_KEYCODE_BIGER_I = 66657;
	/** 大写J **/
	private final int KEYBOARD_KEYCODE_BIGER_J = 66658;
	/** 大写K **/
	private final int KEYBOARD_KEYCODE_BIGER_K = 66659;
	/** 大写L **/
	private final int KEYBOARD_KEYCODE_BIGER_L = 66660;
	/** 大写M **/
	private final int KEYBOARD_KEYCODE_BIGER_M = 66661;
	/** 大写N **/
	private final int KEYBOARD_KEYCODE_BIGER_N = 66662;
	/** 大写O **/
	private final int KEYBOARD_KEYCODE_BIGER_O = 66663;
	/** 大写P **/
	private final int KEYBOARD_KEYCODE_BIGER_P = 66664;
	/** 大写Q **/
	private final int KEYBOARD_KEYCODE_BIGER_Q = 66665;
	/** 大写R **/
	private final int KEYBOARD_KEYCODE_BIGER_R = 66666;
	/** 大写S **/
	private final int KEYBOARD_KEYCODE_BIGER_S = 66667;
	/** 大写T **/
	private final int KEYBOARD_KEYCODE_BIGER_T = 66668;
	/** 大写U **/
	private final int KEYBOARD_KEYCODE_BIGER_U = 66669;
	/** 大写V **/
	private final int KEYBOARD_KEYCODE_BIGER_V = 66670;
	/** 大写W **/
	private final int KEYBOARD_KEYCODE_BIGER_W = 66671;
	/** 大写X **/
	private final int KEYBOARD_KEYCODE_BIGER_X = 66672;
	/** 大写Y **/
	private final int KEYBOARD_KEYCODE_BIGER_Y = 66673;
	/** 大写Z **/
	private final int KEYBOARD_KEYCODE_BIGER_Z = 66674;

	public JiuyiKeyBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		onListenerKeyBoard();
	}

	public JiuyiKeyBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		onListenerKeyBoard();
	}

	public JiuyiKeyBoardView(Context context) {
		// super(context);
		super(context, null);
	}

	/**
	 * 
	 * @param nKeyBoardType
	 */
	public void setKeyBoardType(int nKeyBoardType) {
		m_nKeyBoardType = nKeyBoardType;

		if (keyboardNum == null) {
			keyboardNum = new LatinKeyboard(getContext(), Res.getLayoutID(getContext(), "tzt_v23_keynumcontent"));
			keyboardABCBIG = new LatinKeyboard(getContext(), Res.getLayoutID(getContext(), "tzt_v23_keyabcbigcontent"));
			keyboardABCSMALL = new LatinKeyboard(getContext(), Res.getLayoutID(getContext(), "tzt_v23_keyabcsmallcontent"));
			keyboardTrade = new LatinKeyboard(getContext(), Res.getLayoutID(getContext(), "tzt_v23_keytradecontent"));
			keyboardPassword = new LatinKeyboard(getContext(), Res.getLayoutID(getContext(), "tzt_v23_keynumcontent"));
		}

		switch (nKeyBoardType) {
			case KEYBOARDTYPE_Num:
				setKeyboard(keyboardNum);
				break;
			case KEYBOARDTYPE_ABCSMALL:
				setKeyboard(keyboardABCSMALL);
				break;
			case KEYBOARDTYPE_ABCBIG:
				setKeyboard(keyboardABCBIG);
				break;
			case KEYBOARDTYPE_Trade:
				setKeyboard(keyboardTrade);
				break;
			case KEYBOARDTYPE_PASSWORD:
				RandomKey(keyboardPassword);
				setKeyboard(keyboardPassword);
				break;
			case KEYBOARDTYPE_System:

				break;
			default:
				setKeyboard(keyboardNum);
				break;
		}
		setEnabled(true);
		setPreviewEnabled(true);

		if (mEditText != null) {
			mEditText.setSelection(mEditText.getSelectionEnd());
		}
	}

	public void RandomKey(LatinKeyboard pLatinKeyboard){
		int[] ayRandomKey={0,1,2,3,4,5,6,7,8,9};
		Random random=new Random();
		for (int i=0;i<ayRandomKey.length;i++){
			int a=random.nextInt(ayRandomKey.length);
			int temp=ayRandomKey[i];
			ayRandomKey[i]=ayRandomKey[a];
			ayRandomKey[a]=temp;
		}
		int[] ayKey={KEYBOARD_KEYCODE_0,KEYBOARD_KEYCODE_1,KEYBOARD_KEYCODE_2,KEYBOARD_KEYCODE_3,KEYBOARD_KEYCODE_4,KEYBOARD_KEYCODE_5
				,KEYBOARD_KEYCODE_6,KEYBOARD_KEYCODE_7,KEYBOARD_KEYCODE_8,KEYBOARD_KEYCODE_9};
		List<Key> pKeyLis=pLatinKeyboard.getKeys();
		int index=0;
		for (int i=0;i<pKeyLis.size();i++){
			switch (pKeyLis.get(i).codes[0]) {
			case KEYBOARD_KEYCODE_0:
			case KEYBOARD_KEYCODE_1:
			case KEYBOARD_KEYCODE_2:
			case KEYBOARD_KEYCODE_3:
			case KEYBOARD_KEYCODE_4:
			case KEYBOARD_KEYCODE_5:
			case KEYBOARD_KEYCODE_6:
			case KEYBOARD_KEYCODE_7:
			case KEYBOARD_KEYCODE_8:
			case KEYBOARD_KEYCODE_9:
				pKeyLis.get(i).label=ayRandomKey[index]+"";
				pKeyLis.get(i).codes[0]=ayKey[ayRandomKey[index]];
				index++;
				break;
			}
		}
	}
	
	public void setEditView(JiuyiEditText editText) {
		mEditText = editText;
		if (mEditText != null) {
			mEditText.setSelection(mEditText.getText().toString().length());
//			mEditText.setCursorVisible(true);
			
			if(m_nKeyBoardType != KEYBOARDTYPE_System){
				InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm != null) {
					imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
				}
			}
		}
	}

	private Handler pHandler = new Handler() {
		public synchronized void handleMessage(Message msg) {
			int action = msg.what;
			switch (action) {
			case 1: {
				mScrollTo = false;

				if(m_nKeyBoardType != KEYBOARDTYPE_System){
					setEditView(mEditText);
					setKeyBoardType(m_nKeyBoardType);
				}else{
					setKeyBoardType(m_nKeyBoardType);
				}
				int nKeyBoardToTopHeight = Res.getHeightPixels() - keyboardNum.getHeight();
				int visibility = getVisibility();
				if ((visibility == View.GONE || visibility == View.INVISIBLE) && !bClickSystem) {
					setVisibility(View.VISIBLE);
					int[] editLocal = new int[2];
					if(isWebviewCallBack()){
						editLocal = new int[]{mCallBack.getWebView().getKeyboardStruct().getWebViewTouchX(), (int) (mCallBack.getWebView().getKeyboardStruct().getWebViewTouchY() + Rc.getIns().getTitleHeight())};
					}else{
						mEditText.getLocationOnScreen(editLocal);// 获取在当前窗口内的绝对坐标,360,1308
					}
					if (editLocal[1] + mEditText.getHeight() > nKeyBoardToTopHeight) {
						if (mCallBack != null && !mScrollTo) {
							mScrollTo = true;
							
							int height = editLocal[1] - nKeyBoardToTopHeight;//1248,678
							
							mScrollToValue = height + mEditText.getHeight()*2;
							if(isWebviewCallBack()){
                                mCallBack.getWebView().scrollTo(0, mScrollToValue);//374
							}else{
                                mCallBack.getRelativeLayout().keyboardScrollTo(0, mScrollToValue);//374
							}
						}
					}
				}
				//关闭自定义键盘，打开系统键盘
				if(m_nKeyBoardType == KEYBOARDTYPE_System){
					setVisibility(View.GONE);
					mEditText.requestFocus();
					((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(mEditText, 0);
					mEditText.setSelection(mEditText.getText().length());
				}

                // 键盘显示时的回调
                if(mCallBack != null)
                    mCallBack.keyBoardShowCallBack(Math.max(getHeight(), mDefaultKeyBoardHeight));
			}
				break;
			case 2: {
                //键盘显示时的回调
                if(mCallBack != null)
                    mCallBack.keyBoardHideCallBack(Math.max(getHeight(), mDefaultKeyBoardHeight));

				doHideKeyboard();
			}
				break;
			}
		}
	};
	
	public void setWebviewCallBack(String inputId, String jsfunname){
		this.inputId = inputId;//网页的input，input的id
		this.jsfunname = jsfunname;//网页的input，要执行的函数
	}
	
	public void showKeyboard(JiuyiEditText editText, JiuyiIEditTextCallBack callBack) {
		if (bClickSystem) {
			bClickSystem = false;
		}
		if (isShowing())// 防止点击一个输入框后未隐藏键盘再点击另一个,界面滚动不上去
		{
			doOnlyHideKeyboard();
		}
		if(editText.bIsSystemKeyBoardType)
			m_nKeyBoardType = KEYBOARDTYPE_System;
		else if(editText.bIsPasswordKeyBoardType)
			m_nKeyBoardType = KEYBOARDTYPE_PASSWORD;
		else
			m_nKeyBoardType = KEYBOARDTYPE_Num;
		this.mEditText = editText;
		this.mCallBack = callBack;
		setEditView(editText);
		pHandler.sendMessage(Message.obtain(pHandler, 1));
	}

	public void hideKeyboard() {
		pHandler.sendMessage(Message.obtain(pHandler, 2));
	}

	public void doHideKeyboard() {
		doOnlyHideKeyboard();

		inputId = "";//网页的input，input的id
		jsfunname = "";//网页的input，要执行的函数
	}
	
	public void doOnlyHideKeyboard() {
		if(mCallBack == null)
			return;
		
		int visibility = getVisibility();
		if (visibility == View.VISIBLE) {
			if (mCallBack != null && mScrollTo) {
				mScrollTo = false;
				mScrollToValue = 0;
				if(isWebviewCallBack()){
                    mCallBack.getWebView().scrollTo(0, mScrollToValue);//374
				}else{
                    mCallBack.getRelativeLayout().keyboardScrollTo(0, mScrollToValue);//374
				}

                mCallBack.getRelativeLayout().invalidate();// 刷新不然可能有滑动不全的问题
			}
			setVisibility(View.GONE);
		}
	}
	
	public boolean isShowing() {
		int visibility = getVisibility();
        return visibility == View.VISIBLE;
	}

	public int getScrollToValue(){
		if(isWebviewCallBack())
			return mScrollToValue;
		else
			return 0;
	}
	
	public void onShowSystemKeyBoard() {
		hideKeyboard();
		post(new Runnable() {
			
			@Override
			public void run() {
				//（添加判空）
				if(mEditText!=null && mContext!=null){
					mEditText.requestFocus();
					((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(mEditText, 0);
					mEditText.setSelection(mEditText.getText().length());
				}
			}
		});
	}
	
	private boolean isWebviewCallBack(){
		return !Func.IsStringEmpty(inputId) && !Func.IsStringEmpty(jsfunname) && mCallBack != null && mCallBack.getActivity() instanceof JiuyiICanvasInterface;
	}


    /**
     * 网页如果调用自定义键盘，设置inputId的
     * @return
     */
    private void setWebviewInputValue(String inputId, String jsfunname, String primaryCode, String keyvalue, String inputvalue){
        try {
            String strUrl = "javascript:"+jsfunname+"('"+inputId+"', '"+keyvalue+"')";
            mCallBack.getWebView().loadUrl(strUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private void onListenerKeyBoard() {
		setOnKeyboardActionListener(new OnKeyboardActionListener() {

			@Override
			public void swipeUp() {
			}

			@Override
			public void swipeRight() {
			}

			@Override
			public void swipeLeft() {
			}

			@Override
			public void swipeDown() {
			}

			@Override
			public void onText(CharSequence text) {
			}

			@Override
			public void onRelease(int primaryCode) {
			}

			@Override
			public void onPress(int primaryCode) {
			}

			@Override
			public void onKey(int primaryCode, int[] keyCodes) {
				if (mEditText == null) {
					return;
				}

				if (mEditText.mKeyReleaseTwoTime != 0 && mEditText.mKeyReleaseTwoTime != mEditText.mKeyReleaseOneTime) {
					mEditText.mKeyReleaseOneTime = mEditText.mKeyReleaseTwoTime;
				}
				mEditText.mKeyReleaseTwoTime = System.currentTimeMillis();

				mIsPwd = mEditText.isPasswordInputType(mEditText.getInputType());

				onDealKey(primaryCode, keyCodes);
			}
		});
	}
	
	public void onDealKey(int primaryCode, int[] keyCodes){
		Editable editable = mEditText.getText();
		int start = mEditText.getSelectionStart();
		
		String keyvalue = onDealKey(editable, primaryCode, keyCodes, start);
		if(isWebviewCallBack()){
			setWebviewInputValue(inputId, jsfunname, primaryCode+"", keyvalue, mEditText.getText().toString());
		}else{
			//清除按钮
			mEditText.setRightCleanImg(mEditText, mEditText.getText().toString());
		}
	}
	
	public String onDealKey(Editable editable, int primaryCode, int[] keyCodes, int start){
		final String INITKEY = "-1";
		String keyvalue = INITKEY;
		switch (primaryCode) {
			case KEYBOARD_KEYCODE_00:// 00
				keyvalue = "00";
				break;
			case KEYBOARD_KEYCODE_ALL:// 全部
				if (mCallBack != null)
                    mCallBack.setValueByKeyBoard(mEditText, KEYBOARD_KEYCODE_ALL);
				break;
			case KEYBOARD_KEYCODE_2F1://
				if (mCallBack != null)
                    mCallBack.setValueByKeyBoard(mEditText, KEYBOARD_KEYCODE_2F1);
				break;
			case KEYBOARD_KEYCODE_3F1://
				if (mCallBack != null)
                    mCallBack.setValueByKeyBoard(mEditText, KEYBOARD_KEYCODE_3F1);
				break;
			case KEYBOARD_KEYCODE_4F1://
				if (mCallBack != null)
                    mCallBack.setValueByKeyBoard(mEditText, KEYBOARD_KEYCODE_4F1);
				break;
			case KEYBOARD_KEYCODE_601:// 601
				keyvalue = "601";
				break;
			case KEYBOARD_KEYCODE_600:// 600
				keyvalue = "600";
				break;
			case KEYBOARD_KEYCODE_POINT:// .
				keyvalue = ".";
				break;
			case KEYBOARD_KEYCODE_300:// 300
				keyvalue = "300";
				break;
			case KEYBOARD_KEYCODE_002:// 002
				keyvalue = "002";
				break;
			case KEYBOARD_KEYCODE_000:// 000
				keyvalue = "000";
				break;
			case KEYBOARD_KEYCODE_SWITCHCHAR:// ABC
				setKeyBoardType(KEYBOARDTYPE_ABCSMALL);
				break;
			case KEYBOARD_KEYCODE_SYSTEM:// 系统键盘
				//
				if(!isWebviewCallBack()){//网页调用的键盘不能切换到系统键盘
					bClickSystem = true;

					onShowSystemKeyBoard();
				}else{
					hideKeyboard();//应该隐藏并且网页自主打开系统键盘
				}
				break;
			case KEYBOARD_KEYCODE_OK:// 确定
				hideKeyboard();
				break;
			case KEYBOARD_KEYCODE_TOUP:// 切换字母
				setKeyBoardType(KEYBOARDTYPE_ABCSMALL);
				break;
			case KEYBOARD_KEYCODE_HIDE:// 隐藏
				hideKeyboard();
				break;
			case KEYBOARD_KEYCODE_DEL:// 删除
				if (editable != null && editable.length() > 0) {
					if (start > 0) {
						editable.delete(start - 1, start);
					}
				}
				break;
			case KEYBOARD_KEYCODE_1:
				keyvalue = "1";
				break;
			case KEYBOARD_KEYCODE_2:
				keyvalue = "2";
				break;
			case KEYBOARD_KEYCODE_3:
				keyvalue = "3";
				break;
			case KEYBOARD_KEYCODE_4:
				keyvalue = "4";
				break;
			case KEYBOARD_KEYCODE_5:
				keyvalue = "5";
				break;
			case KEYBOARD_KEYCODE_6:
				keyvalue = "6";
				break;
			case KEYBOARD_KEYCODE_7:
				keyvalue = "7";
				break;
			case KEYBOARD_KEYCODE_8:
				keyvalue = "8";
				break;
			case KEYBOARD_KEYCODE_9:
				keyvalue = "9";
				break;
			case KEYBOARD_KEYCODE_0:
				keyvalue = "0";
				break;
			case KEYBOARD_KEYCODE_123:// 123
				setKeyBoardType(KEYBOARDTYPE_Num);
				break;
			case KEYBOARD_KEYCODE_SHIFT:// 大写
				setKeyBoardType(KEYBOARDTYPE_ABCBIG);
				break;
			case KEYBOARD_KEYCODE_SMALL_A:
				keyvalue = "a";
				break;
			case KEYBOARD_KEYCODE_SMALL_B:
				keyvalue = "b";
				break;
			case KEYBOARD_KEYCODE_SMALL_C:
				keyvalue = "c";
				break;
			case KEYBOARD_KEYCODE_SMALL_D:
				keyvalue = "d";
				break;
			case KEYBOARD_KEYCODE_SMALL_E:
				keyvalue = "e";
				break;
			case KEYBOARD_KEYCODE_SMALL_F:
				keyvalue = "f";
				break;
			case KEYBOARD_KEYCODE_SMALL_G:
				keyvalue = "g";
				break;
			case KEYBOARD_KEYCODE_SMALL_H:
				keyvalue = "h";
				break;
			case KEYBOARD_KEYCODE_SMALL_I:
				keyvalue = "i";
				break;
			case KEYBOARD_KEYCODE_SMALL_J:
				keyvalue = "j";
				break;
			case KEYBOARD_KEYCODE_SMALL_K:
				keyvalue = "k";
				break;
			case KEYBOARD_KEYCODE_SMALL_L:
				keyvalue = "l";
				break;
			case KEYBOARD_KEYCODE_SMALL_M:
				keyvalue = "m";
				break;
			case KEYBOARD_KEYCODE_SMALL_N:
				keyvalue = "n";
				break;
			case KEYBOARD_KEYCODE_SMALL_O:
				keyvalue = "o";
				break;
			case KEYBOARD_KEYCODE_SMALL_P:
				keyvalue = "p";
				break;
			case KEYBOARD_KEYCODE_SMALL_Q:
				keyvalue = "q";
				break;
			case KEYBOARD_KEYCODE_SMALL_R:
				keyvalue = "r";
				break;
			case KEYBOARD_KEYCODE_SMALL_S:
				keyvalue = "s";
				break;
			case KEYBOARD_KEYCODE_SMALL_T:
				keyvalue = "t";
				break;
			case KEYBOARD_KEYCODE_SMALL_U:
				keyvalue = "u";
				break;
			case KEYBOARD_KEYCODE_SMALL_V:
				keyvalue = "v";
				break;
			case KEYBOARD_KEYCODE_SMALL_W:
				keyvalue = "w";
				break;
			case KEYBOARD_KEYCODE_SMALL_X:
				keyvalue = "x";
				break;
			case KEYBOARD_KEYCODE_SMALL_Y:
				keyvalue = "y";
				break;
			case KEYBOARD_KEYCODE_SMALL_Z:
				keyvalue = "z";
				break;
			case KEYBOARD_KEYCODE_BIGER_A:
				keyvalue = "A";
				break;
			case KEYBOARD_KEYCODE_BIGER_B:
				keyvalue = "B";
				break;
			case KEYBOARD_KEYCODE_BIGER_C:
				keyvalue = "C";
				break;
			case KEYBOARD_KEYCODE_BIGER_D:
				keyvalue = "D";
				break;
			case KEYBOARD_KEYCODE_BIGER_E:
				keyvalue = "E";
				break;
			case KEYBOARD_KEYCODE_BIGER_F:
				keyvalue = "F";
				break;
			case KEYBOARD_KEYCODE_BIGER_G:
				keyvalue = "G";
				break;
			case KEYBOARD_KEYCODE_BIGER_H:
				keyvalue = "H";
				break;
			case KEYBOARD_KEYCODE_BIGER_I:
				keyvalue = "I";
				break;
			case KEYBOARD_KEYCODE_BIGER_J:
				keyvalue = "J";
				break;
			case KEYBOARD_KEYCODE_BIGER_K:
				keyvalue = "K";
				break;
			case KEYBOARD_KEYCODE_BIGER_L:
				keyvalue = "L";
				break;
			case KEYBOARD_KEYCODE_BIGER_M:
				keyvalue = "M";
				break;
			case KEYBOARD_KEYCODE_BIGER_N:
				keyvalue = "N";
				break;
			case KEYBOARD_KEYCODE_BIGER_O:
				keyvalue = "O";
				break;
			case KEYBOARD_KEYCODE_BIGER_P:
				keyvalue = "P";
				break;
			case KEYBOARD_KEYCODE_BIGER_Q:
				keyvalue = "Q";
				break;
			case KEYBOARD_KEYCODE_BIGER_R:
				keyvalue = "R";
				break;
			case KEYBOARD_KEYCODE_BIGER_S:
				keyvalue = "S";
				break;
			case KEYBOARD_KEYCODE_BIGER_T:
				keyvalue = "T";
				break;
			case KEYBOARD_KEYCODE_BIGER_U:
				keyvalue = "U";
				break;
			case KEYBOARD_KEYCODE_BIGER_V:
				keyvalue = "V";
				break;
			case KEYBOARD_KEYCODE_BIGER_W:
				keyvalue = "W";
				break;
			case KEYBOARD_KEYCODE_BIGER_X:
				keyvalue = "X";
				break;
			case KEYBOARD_KEYCODE_BIGER_Y:
				keyvalue = "Y";
				break;
			case KEYBOARD_KEYCODE_BIGER_Z:
				keyvalue = "Z";
				break;
			default:
				keyvalue = Character.toString((char) primaryCode);
				break;
		}
		if(!INITKEY.equals(keyvalue)){
			editable.insert(start, keyvalue);
		}else{
			keyvalue = "";
		}
		return keyvalue;
	}

	/**
	 * 获取确定按钮 id
	 * @return
	 */
	public int getKEYBOARD_KEYCODE_OK(){
		return KEYBOARD_KEYCODE_OK;
	}
}
