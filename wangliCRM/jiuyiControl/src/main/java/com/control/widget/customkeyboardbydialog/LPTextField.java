package com.control.widget.customkeyboardbydialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

@SuppressLint("NewApi") 
public class LPTextField extends android.support.v7.widget.AppCompatEditText {
    private static final int BORDER_INDENT = 2;

    private boolean shouldCache_;

    boolean isPassword = false ;
    /**
     * TextField of this LPTextField for inputing.
     */

    // private Form form_;
    /**
     * Font of text.
     */
    private Paint font_;

    /**
     * Temporary string for inputing.
     */
    private String temp_;

    // 标志该控件是否是密码输入控件，广发新增要求要求对密码输入控件内容作一定校验
    private boolean isPasswordWidget_;
    /**
     * Indicator of setting label and input field in ONE row.
     */
    boolean isLabelIndianFile_;

    /**
     * Indicate the inputting is not empty.
     */
    boolean required_;
    String group;
    String ref;
    int inputFieldWidth_;

    boolean editable_ = true;
    /**
     * minimum of the characters
     */
    int minSize_;
    /**
     * maxmum of the characters
     */
    int maxSize_ = 18;

    double maxValue_ = -1;
    double minValue_ = -1;

    String alartInfo = "";

    // 输入框的输入限制类型
    private int inputType_;

    String TextType = "";
    /* input not support "." */
    Paint form_;

    String attrName_;
    String attrValue_;
    boolean attrSave_;
    String isEncrypt_;
    String hint_;
    private int height_;

    private int width_;
//    MainActivity bv_;

    public LPTextField(Context context) {
        super(context);
    }

    public LPTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setTransformationMethod(new PasswordTransformationMethod());//是否为密码控件
//        bv_ = (MainActivity) context;
//        bv_.inputManager_= (InputMethodManager)bv_.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    String password_ = "password";

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        //		if(this.isFocused() && isPassword){
        //			if(null != password_ && password_.equalsIgnoreCase("password")){
        //				bv_.inputManager_.hideSoftInputFromWindow(this.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //				if(bv_.dlg == null || !bv_.dlg.isShowing()){
        //	        		if(this.isEnabled()){
        //	        			bv_.OnCreateInputWindow(this);
        //	        		}
        //	        	}
        //			}
        //		}else{
        //
        //		}
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

//    public boolean dispatchTouchEvent(MotionEvent event) {
//        this.requestFocus();
//        boolean rt = false;
//        if(null != password_ && password_.equalsIgnoreCase("password")){
//            bv_.inputManager_.hideSoftInputFromWindow(this.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            if(bv_.dlg == null || !bv_.dlg.isShowing()){
//                if(this.isEnabled()){
//                    bv_.OnCreateInputWindow(this);
//                    rt = true;
//                }
//            }
//        }
//
//        return super.dispatchTouchEvent(event);
//    }



 
    public String getContentText() {
        return super.getText().toString();
    }

    public String getLabel() {
        // TODO Auto-generated method stub
        // 由于国航项目取消了输入框的label。
        // 改为将label作hint显示，嵌入到输入框中，所以作此改动
        String str = "";
        if (null != this.getHint()) {
            str = this.getHint().toString();
            if ((null == str) || (str.equals("")))
                str = "";
        }
        return str;
    }

    /**
     *
     * */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
    //	@Override
    //	public boolean onTouchEvent(MotionEvent event) {
    //		Intent ipIntent = new Intent();
    //		ipIntent.setClass(bv_, KeyBoardActivity.class);
    //		bv_.startActivity(ipIntent);
    //
    //		return super.onTouchEvent(event);
    //	}

    @Override
    protected void onTextChanged(CharSequence text, int start,
                                 int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    String checkText(String text) {

        if (minValue_ == -1 && maxValue_ == -1)
            return text;
        if (text == null || text.equals(""))
            return text;
        Double sour = Double.parseDouble(text);
        if ((maxValue_ != -1) && sour >= maxValue_) {
            alartInfo = hint_ + "不能大于" + maxValue_;
            sour = maxValue_;
        }
        if ((minValue_ != -1) && sour <= minValue_) {
            alartInfo = hint_ + "不能小于" + minValue_;
            sour = minValue_;
        }
        String destext = null;
        // 判断输入的内容是否有小数。
        if (sour % 1 < 0.001) {
            destext = (sour + "").substring(0, (sour + "").indexOf("."));
        }
        if (sour % 1 > 0.001) {
            destext = sour.toString();
        }
        return destext;
    }

    public boolean getPasswordMark() {
        return isPasswordWidget_;
    }

    // 设置密码输入框标志，如果控件是密码输入框，则该方法一定要执行
    public void setPasswordMark(boolean iswork) {
        isPasswordWidget_ = iswork;
    }

    public void setContentText(String text) {
        // TODO Auto-generated method stub
        this.setText(text);
    }

    public void setEditable_(boolean editable_) {
        this.editable_ = editable_;
    }

    public void shrinkWidth() {
        // TODO Auto-generated method stub

    }

    public void cleanText() {
        // TODO Auto-generated method stub

    }


    //	public int getInputType() {
    //		// TODO Auto-generated method stub
    //		return inputType_;
    //	}
    //
    //	public void setInputType(int inputType) {
    //		inputType_ = inputType;
    //	}

    public void setLPHeidht(int height) {
        // TODO Auto-generated method stub

    }

    public void setLPWidth(int width) {
        // TODO Auto-generated method stub

    }

    public View getLPView() {
        return this;
    }

    public int getLPHeight() {
        return height_;
    }

    public int getLPWidth() {
        return width_;
    }

    public void setInTable(boolean inTable) {
        // TODO Auto-generated method stub
    }

    public boolean isInTable() {
        // TODO Auto-generated method stub
        return false;
    }
}