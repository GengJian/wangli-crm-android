package com.control.widget.canvas;



import android.graphics.Paint;

public class MyPaint extends Paint {
	public static final float DefaultTextSize = 32;
	public static final int nDefaultTextSize = 32;
    public static float TextSize = DefaultTextSize;
    int m_nAlpha = 0xFF000000;

    public MyPaint() {
        super.setTextSize(TextSize);
    }

    @Override
    public void setAlpha(int nAlpha) {
        m_nAlpha = (((nAlpha & 0xFF) << 24) & 0xFF000000);
        super.setAlpha(nAlpha);
        super.setTextSize(TextSize);
    }

    @Override
    public void setColor(int nColor) {
        super.setColor(nColor | m_nAlpha/*0xFF000000*/);
    }

    public int getZZTextSize() {
        return (int) (super.getTextSize());
    }

    public void setTextSize(int textsize) {
        super.setTextSize(textsize);
        TextSize = textsize;
    }
    /**
     * 
     */
    public float getTextSize(){
    	return TextSize;
    }
    /*
     * @see android.graphics.Paint#getTextSize()
     */
    public float getDefaultTextSize() {
        return DefaultTextSize;
    }
}
