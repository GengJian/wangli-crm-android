package com.control.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * **************************************************************** 
 * 文件名称 : tztButton.java
 * 创建时间:2018/5/16 13:45
 * 文件描述 : 带时间限制的button
 * 			500毫秒内按钮无效，这样可以控制快速点击，自己调整频率
 *****************************************************************
 */
public class JiuyiButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {
	private long lastClickTime = 0;
	private boolean bCheckFastDoubleClick = true;//是否要检查快速点击
	private int delay = 800;
    private OnClickListener onClickListener;

	public JiuyiButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public JiuyiButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public JiuyiButton(Context context) {
		super(context);
	}
	
	public JiuyiButton(Context context, boolean bCheck) {
		super(context);
		bCheckFastDoubleClick = bCheck;
	}

	public void setCheckFastDoubleClick(boolean bCheck){
		bCheckFastDoubleClick = bCheck;
	}
	
	public void setDelay(int delay){
		if(delay < 500)
			return;
		this.delay = delay;
	}

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        onClickListener = l;
        super.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(System.currentTimeMillis() - lastClickTime > delay){
            lastClickTime = System.currentTimeMillis();
            setClickable(false);

            if(onClickListener != null)
                onClickListener.onClick(v);

            postDelayed(new Runnable() {
                @Override
                public void run() {
                    setClickable(true);
                }
            }, delay);
        }
    }
}
