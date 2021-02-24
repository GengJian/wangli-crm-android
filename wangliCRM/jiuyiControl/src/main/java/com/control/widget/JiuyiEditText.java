package com.control.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.control.callback.JiuyiIEditTextCallBack;
import com.control.utils.Func;
import com.control.utils.JiuyiLog;
import com.control.utils.Res;
import com.control.utils.SDKVersion;
import com.control.widget.keyboard.JiuyiKeyBoardManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ****************************************************************
 * 文件名称 : tztEditText.java
 * 作 者 :   zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述 : 自定义的EditText
 * 			1、键盘问题
 * 			2、清理输入框文本的按钮对应的黑色白色界面问题
 * 				有的输入框是黑色背景的有的是白色，客户端根据软件颜色风格来设置这个图片；
 * 				如果某数据入口直接指定一个图片，则在此控件设置tag="skin_0"(黑色)或者tag="skin_1"(白色)
 *****************************************************************
 */
public class JiuyiEditText extends android.support.v7.widget.AppCompatEditText{
    /** tztEditTexts */
	JiuyiEditText ins;
    /** Touch的监听器 */
	OnTouchListener mOnTouchListener;
    /** 使用他们的布局 */
	private JiuyiIEditTextCallBack mCallBack;
    /** 输入类型 */
	int mInputType;
    /** hint */
	String hint = "";
    /** 是否是系统键盘 */
	public boolean bIsSystemKeyBoardType = false;//
    /** 是否是密码键盘（随机）*/
	public boolean bIsPasswordKeyBoardType = false;//
    /**  */
	private boolean m_bRightCleanImgTouchable;
    /**  */
	private boolean m_bLeftImgTouchable;
    /**  */
	private boolean m_bRightImgTouchable;
    /**  */
	private boolean m_bTopImgTouchable;
    /**  */
	private boolean m_bBottomImgTouchable;
    /**  */
	public long mKeyReleaseOneTime;
    /**  */
	public long mKeyReleaseTwoTime;
    /**  */
	private long lastClickTime = 0;
    /**  */
	private boolean bCheckFastDoubleClick = false;//是否要检查快速点击

	public JiuyiEditText(Context context, JiuyiIEditTextCallBack callBack) {
		super(context);
		super.setOnTouchListener(o);
		super.setOnClickListener(mEditClk);
        mCallBack = callBack;
		ins = this;
		setRightCleanImg();
	}
    public JiuyiEditText(Context context) {
        super(context);
        super.setOnTouchListener(o);
        super.setOnClickListener(mEditClk);
        ins = this;
        setRightCleanImg();
    }
//增加ins=this 防止在xml中定义时报空指针异常
	public JiuyiEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setOnTouchListener(o);
		super.setOnClickListener(mEditClk);
		ins = this;
		setRightCleanImg();
	}

	public JiuyiEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		super.setOnTouchListener(o);
		super.setOnClickListener(mEditClk);
		ins = this;
		setRightCleanImg();
	}


	/**
	 * 设置为系统键盘
	 * @param bSystem
	 */
	public void setSystemKeyBoard(boolean bSystem){
		bIsSystemKeyBoardType = bSystem;
	}
	
	/**
	 * 设置为密码键盘
	 * @param bPassword
	 */
	public void setPasswordKeyBoard(boolean bPassword){
		bIsPasswordKeyBoardType = bPassword;
	}
	
	OnTouchListener o = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
//			Rc.mCustomerSearch=true;
			JiuyiEditText.this.setFocusableInTouchMode(true);
			JiuyiEditText.this.setFocusable(true);
			int inputback = ins.getInputType();
			
			toShowKeyboard(inputback, event.getAction());

			
			if (event.getAction() == MotionEvent.ACTION_UP && (m_bRightImgTouchable) && JiuyiEditText.this.getCompoundDrawables()[2] != null)
			{
				int rightPad = JiuyiEditText.this.getPaddingRight();
				int imgWidth = JiuyiEditText.this.getCompoundDrawables()[2].getIntrinsicWidth();
				imgWidth = imgWidth + imgWidth / 2;
				int viewWidth = JiuyiEditText.this.getWidth();
				if (event.getAction() == MotionEvent.ACTION_UP && event.getX() > viewWidth - rightPad - imgWidth)
				{
					if (m_vDrawImgClk != null)
					{
						m_vDrawImgClk.OnClickRightImage();
					}
					
					if(mCallBack != null){
						if(!JiuyiKeyBoardManager.getIns().IsKeyBoardEmpty(mCallBack.getKeyBoardView()))
						{
                            mCallBack.getKeyBoardView().hideKeyboard();
						}
					}
					return false;
				}
			}
			if (event.getAction() == MotionEvent.ACTION_UP && (m_bRightCleanImgTouchable && ins.hasFocus()) && JiuyiEditText.this.getCompoundDrawables()[2] != null)
			{
				int rightPad = JiuyiEditText.this.getPaddingRight();
				int imgWidth = JiuyiEditText.this.getCompoundDrawables()[2].getIntrinsicWidth();
				imgWidth = imgWidth + imgWidth / 2;
				int viewWidth = JiuyiEditText.this.getWidth();
				if (event.getAction() == MotionEvent.ACTION_UP && event.getX() > viewWidth - rightPad - imgWidth)
				{
					if (m_vDrawImgClk != null)
					{
						m_vDrawImgClk.OnClickRightImage();
					}
					return false;
				}
			}
			if (event.getAction() == MotionEvent.ACTION_UP && m_bLeftImgTouchable && JiuyiEditText.this.getCompoundDrawables()[0] != null)
			{
				int leftPad = JiuyiEditText.this.getPaddingLeft();
				int imgWidth = JiuyiEditText.this.getCompoundDrawables()[0].getIntrinsicWidth();
				if (event.getAction() == MotionEvent.ACTION_UP && event.getX() < leftPad + imgWidth)
				{
					if (m_vDrawImgClk != null)
					{
						JiuyiEditText.this.setFocusableInTouchMode(false);
						JiuyiEditText.this.setFocusable(false);
						m_vDrawImgClk.OnClickLeftImage();
					}
                    if(mCallBack != null){
                        if(!JiuyiKeyBoardManager.getIns().IsKeyBoardEmpty(mCallBack.getKeyBoardView()))
                        {
                            mCallBack.getKeyBoardView().hideKeyboard();
                        }
                    }
					return false;
				}
			}
			if(android.os.Build.VERSION.SDK_INT <= SDKVersion.SDK_VERSION13 ){
				if(mOnTouchListener != null)
				{
					return mOnTouchListener.onTouch(v, event);
				}
			}
			return false;
		}
	};
	
	public void hideSoftInputMethod(EditText ed){
		mCallBack.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
         
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if(currentVersion >= 16){
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        }
        else if(currentVersion >= 14){
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }
         
        if(methodName == null){
            ed.setInputType(InputType.TYPE_NULL);  
        }
        else{
            Class<EditText> cls = EditText.class;  
            Method setShowSoftInputOnFocus;  
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);  
                setShowSoftInputOnFocus.invoke(ed, false); 
            } catch (NoSuchMethodException e) {
                ed.setInputType(InputType.TYPE_NULL);
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            } catch (IllegalAccessException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            } catch (IllegalArgumentException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            } catch (InvocationTargetException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            }  
        }  
    }
	/**
	 * 点击网页的输入框(input)时打开自定义键盘
	 * @param inputback
	 */
	public void toShowWebViewKeyboard(int inputback, String inputId, String jsfunname){
	    if(mCallBack.getKeyBoardView() == null){
	        return;
        }
        mCallBack.getKeyBoardView().setWebviewCallBack(inputId, jsfunname);
		
		toShowKeyboard(inputback, MotionEvent.ACTION_DOWN);
		toShowKeyboard(inputback, MotionEvent.ACTION_UP);
	}
	/**
	 * 点击时打开自定义键盘
	 * @param inputback
	 */
	public void toShowKeyboard(int inputback, int getAction){
		if (mCallBack != null) {
			hideSoftInputMethod(ins);
			
			if(getAction == MotionEvent.ACTION_UP){//ACTION_UP事件才触发
				if(!JiuyiKeyBoardManager.getIns().IsKeyBoardEmpty(mCallBack.getKeyBoardView())){//不需要或无KeyBoard才触发
					if((inputback & InputType.TYPE_CLASS_NUMBER) == InputType.TYPE_CLASS_NUMBER)
						mInputType = inputback;
					if ((mInputType & InputType.TYPE_CLASS_NUMBER) == InputType.TYPE_CLASS_NUMBER)
					{
						mInputType = inputback;
						ins.setKeyListener(new NumberKeyListener()
						{
							@Override
							public int getInputType() {
								return 0;
							}
							@Override
							protected char[] getAcceptedChars() {
								char[] chars = {'0','1','2','3','4','5','6','7','8','9','.'};
								return chars;
							}
						});
					}
					if (isPasswordInputType(inputback))
					{
						setTransformationMethod(PasswordTransformationMethod.getInstance());
					}
					
					if (inputback > 0) {
						mCallBack.getKeyBoardView().showKeyboard(ins, mCallBack);
                        mCallBack.getRelativeLayout().setOnTouchListener(mLayoutOnTouch);
					}
				}
			}else if(getAction == MotionEvent.ACTION_DOWN){
				if(bIsSystemKeyBoardType && ins!=null){
                    JiuyiKeyBoardManager.getIns().hideSoftInputFromWindow(ins.getWindowToken(), 0);
				}
			}
			ins.setInputType(inputback);  //
			
			if(ins!=null){
                JiuyiKeyBoardManager.getIns().hideSoftInputFromWindow(ins.getWindowToken(), 0);
			}
		}

	}
	
	public boolean isPasswordInputType(int inputType) 
	{
        final int variation = inputType & (InputType.TYPE_MASK_CLASS | InputType.TYPE_MASK_VARIATION);
        return variation == (EditorInfo.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
	
	public void setInputType(int type) 
	{
		super.setInputType(type);
		mInputType = type;
	}

    /**
	 * 此方法隐藏键盘
	 */
	OnFocusChangeListener onFocus = new OnFocusChangeListener() {
	    @Override
	    public void onFocusChange(View v, boolean hasFocus) {
			if(hasFocus){
				v.requestFocus();
			}
	    }
	};
	
	OnClickListener mEditClk = new OnClickListener()
	{
		
		@Override
		public void onClick(View v) 
		{
		}
	};
	
	OnTouchListener mLayoutOnTouch = new OnTouchListener()
	{
		@Override
		public boolean onTouch(View v, MotionEvent event) 
		{
			if(mCallBack==null || JiuyiKeyBoardManager.getIns().IsKeyBoardEmpty(mCallBack.getKeyBoardView()))
				return false;
			if (mCallBack == null || !mCallBack.getKeyBoardView().isShowing())
				return false;
			if (event.getAction() == MotionEvent.ACTION_UP)
			{
                mCallBack.getKeyBoardView().hideKeyboard();
			}
			return true;
		}
	};
	
	public void setEditTextCallBack(JiuyiIEditTextCallBack callBack, JiuyiEditText edittext)
	{
		if (edittext != null)
		{
			ins = edittext;
		}
        if (callBack != null)
        {
            mCallBack = callBack;
        }
	}
	
	public void setOnTouchListener(OnTouchListener l) {
		mOnTouchListener = l;
	}
	
	/**
	 * 清空edittext的按钮k
	 */
	public void SetAddRightCleanImg(Drawable rightImg, boolean IsAllowClk) 
	{
		this.setCompoundDrawablesWithIntrinsicBounds(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], rightImg, this.getCompoundDrawables()[3]);
		m_bRightCleanImgTouchable = IsAllowClk;
	}
	
	public void SetAddTopImg(Drawable topImg, boolean IsAllowClk) 
	{
		this.setCompoundDrawablesWithIntrinsicBounds(this.getCompoundDrawables()[0], topImg, this.getCompoundDrawables()[2], this.getCompoundDrawables()[3]);
		m_bTopImgTouchable = IsAllowClk;
	}
	
	public void SetAddLeftImg(Drawable leftImg, boolean IsAllowClk) 
	{
		this.setCompoundDrawablesWithIntrinsicBounds(leftImg, this.getCompoundDrawables()[1], this.getCompoundDrawables()[2], this.getCompoundDrawables()[3]);
		m_bLeftImgTouchable = IsAllowClk;
	}
	
	public void SetAddRightImg(Drawable rightImg, boolean IsAllowClk) 
	{
		this.setCompoundDrawablesWithIntrinsicBounds(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], rightImg, this.getCompoundDrawables()[3]);
		m_bRightImgTouchable = IsAllowClk;
		m_bRightCleanImgTouchable = !IsAllowClk;//如果有右侧的的选择图标，则 clean不显
	}
	
	public void SetAddBottomImg(Drawable bottomImg, boolean IsAllowClk) 
	{
		this.setCompoundDrawablesWithIntrinsicBounds(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], this.getCompoundDrawables()[2], bottomImg);
		m_bBottomImgTouchable = IsAllowClk;
	}
	
	public void SetAddImg(Drawable leftImg, Drawable topImg, Drawable rightImg, Drawable bottomImg) 
	{
		this.setCompoundDrawablesWithIntrinsicBounds(leftImg, topImg, rightImg, bottomImg);
		m_bBottomImgTouchable = false;
		m_bRightImgTouchable = false;
		m_bLeftImgTouchable = false;
		m_bTopImgTouchable = false;
		m_bRightCleanImgTouchable = false;
	}
	
	public void SetAddImg(Drawable leftImg, Drawable topImg, Drawable rightImg, Drawable bottomImg, boolean bLeftIsClk, boolean bTopIsClk, boolean bRightIsClk, boolean bBottomIsClk) 
	{
		this.setCompoundDrawablesWithIntrinsicBounds(leftImg, topImg, rightImg, bottomImg);
		m_bLeftImgTouchable = bLeftIsClk;
		m_bTopImgTouchable = bTopIsClk;
		m_bRightImgTouchable = bRightIsClk;
		m_bBottomImgTouchable = bBottomIsClk;
		m_bRightCleanImgTouchable = false;
	}
	
	public void setOnCompoundImageClickListener(OnEditCompoundImageClickListener pOnCompoundImageClickListener)
	{
		m_vDrawImgClk = pOnCompoundImageClickListener;
	}

	public OnEditCompoundImageClickListener m_vDrawImgClk = new OnEditCompoundImageClickListener()
	{
		@Override
		public void OnClickRightImage() 
		{
			ins.setText("");
		}
		
		@Override
		public void OnClickLeftImage() {
		}
	};
	
	public boolean isRightImgTouchable(){
		return m_bRightImgTouchable;
	}

	
	/**
	 * 初始化右侧清空按钮，添加FocusChangeListener和TextChangedListener
	 */
	public void setRightCleanImg() {
        //默认上下居中
        setGravity(Gravity.CENTER_VERTICAL);

		initRightCleanImg();
		
		if (res_clean_id > 0) {
			SetAddRightCleanImg(getResources().getDrawable(res_blank_id), true);
			setOnCompoundImageClickListener(m_vDrawImgClk);
			
			setOnFocusChangeListener(new OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					setRightCleanImg(ins, ins.getText().toString());
				}
			});
			
			addTextChangedListener(new TextWatcher() {
				@Override
				public void afterTextChanged(Editable v) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					setRightCleanImg(ins, s.toString());
				}
			});
		}
	}
	
	public void setRightCleanImg(JiuyiEditText ins, String str){
		//清除按钮
		if(!ins.isRightImgTouchable()){
			if(ins.hasFocus() && !Func.IsStringEmpty(str.toString())){
				ins.addRightCleanImg();
			}else{
				ins.removeRightCleanImg();
			}
		}
	}

	int res_clean_id;
	int res_blank_id;
	private void initRightCleanImg(){
		String tag = getTag()!=null ? getTag().toString() : "";
		int forceskintype = -1;
		if(tag != null){
			if(tag.indexOf("skin_1") >= 0){
				forceskintype = 1;
			}else if(tag.indexOf("skin_0") >= 0){
				forceskintype = 0;
			}else if(tag.indexOf("none") >= 0 || Func.IsStringEmpty(tag)){//不显示
				return;
			}
		}

		res_clean_id =  Res.getDrawabelID(getContext(), "jiuyi_edittext_rightcleanimage_onfocus", forceskintype);
		res_blank_id = Res.getDrawabelID(getContext(), "jiuyi_edittext_rightcleanimage");
	}
	
	private void addRightCleanImg() {
		if (m_bRightCleanImgTouchable && res_clean_id>0) {
			SetAddRightCleanImg(getResources().getDrawable(res_clean_id), true);
		}
	}

	private void removeRightCleanImg() {
		if (m_bRightCleanImgTouchable && res_clean_id>0) {
			SetAddRightCleanImg(getResources().getDrawable(res_blank_id), true);
		}
	}
	public void setCheckFastDoubleClick(boolean bCheck){
		bCheckFastDoubleClick = bCheck;
	}

    public boolean isFastDoubleClick() {  
    	if(!bCheckFastDoubleClick)
    		return false;  
    	
        long time = System.currentTimeMillis();  
        long timeD = time - lastClickTime;  
        if ( 0 < timeD && timeD < 800) {       //500毫秒内按钮无效，这样可以控制快速点击，自己调整频率
            return true;     
        }     
        lastClickTime = time;     
        return false;     
    } 
    /**
     * 左右两端图片的点击事件
     */
    public interface OnEditCompoundImageClickListener
    {
    	void OnClickRightImage();
    	void OnClickLeftImage();
    }
    /**
     * 自定义键盘和具体的
     */
    public interface OnBridgeListener
    {
    	void OnClickRightImage();
    	void OnClickLeftImage();
    }


    //===============================================================================================

}
