package com.control.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * **************************************************************** 
 * 文件名称 : JiuyiVerticalImageSpan.java
 * 创建时间:2018/5/16 13:45
 * 文件描述 : 竖直方向居中的ImageSpan
 *****************************************************************
 */
public class JiuyiVerticalImageSpan extends ImageSpan {
	public JiuyiVerticalImageSpan(Drawable drawable) {
		super(drawable);
	}
	public JiuyiVerticalImageSpan(Drawable drawable, int verticalAlignment) {
		super(drawable, verticalAlignment);
	}

    /**
     * 竖直方向居中的ImageSpan的宽
     * @param paint 绘制类
     * @param fontMetricsInt Paint.FontMetricsInt
     * @return
     */
	public int getSize(Paint paint, Paint.FontMetricsInt fontMetricsInt) {
		Drawable drawable = getDrawable();
		Rect rect = drawable.getBounds();
		if (fontMetricsInt != null) {
			Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
			int fontHeight = fmPaint.bottom - fmPaint.top;
			int drHeight = rect.bottom - rect.top;

			int top = drHeight / 2 - fontHeight / 4;
			int bottom = drHeight / 2 + fontHeight / 4;

			fontMetricsInt.ascent = -bottom;
			fontMetricsInt.top = -bottom;
			fontMetricsInt.bottom = top;
			fontMetricsInt.descent = top;
		}
		return rect.right;
	}

    /**
     * 绘制竖直方向居中的ImageSpan的Drawable
     * @param canvas
     * @param text
     * @param start
     * @param end
     * @param x
     * @param top
     * @param y
     * @param bottom
     * @param paint
     */
	@Override
	public void draw(Canvas canvas, CharSequence text, int start, int end,
			float x, int top, int y, int bottom, Paint paint) {
		Drawable drawable = getDrawable();
		canvas.save();
		int transY;
		transY = ((bottom - top) - drawable.getBounds().bottom) / 2 + top;
		canvas.translate(x, transY);
		drawable.draw(canvas);
		canvas.restore();
	}
}
