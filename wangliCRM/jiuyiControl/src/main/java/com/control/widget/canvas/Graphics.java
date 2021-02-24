package com.control.widget.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

import com.control.utils.Res;
import com.control.utils.JiuyiImage;
import com.control.utils.JiuyiLog;

/**Paint类常用方法
void  setARGB(int a, int r, int g, int b)  		设置Paint对象颜色，参数一为alpha透明通道
void  setAlpha(int a)  							设置alpha不透明度，范围为0~255
void  setAntiAlias(boolean aa)					是否抗锯齿
void  setColor(int color)						设置颜色，这里Android内部定义的有Color类包含了一些常见颜色定义
void  setFakeBoldText(boolean fakeBoldText)		设置伪粗体文本
void  setLinearText(boolean linearText)			设置线性文本
PathEffect  setPathEffect(PathEffect effect)	设置路径效果 
Rasterizer  setRasterizer(Rasterizer rasterizer)设置光栅化
Shader  setShader(Shader shader)				设置阴影 
void  setTextAlign(Paint.Align align)			设置文本对齐
void  setTextScaleX(float scaleX)				设置文本缩放倍数，1.0f为原始
void  setTextSize(float textSize)				设置字体大小
Typeface  setTypeface(Typeface typeface)		设置字体，Typeface包含了字体的类型，粗细，还有倾斜、颜色等。
void  setUnderlineText(boolean underlineText)	设置下划线
*/

public class Graphics {
    private Canvas m_canvas;
    private Font m_font;
    private MyPaint m_paint;
    public final static int nDefaultLineWidth = 3;
    

    public Graphics() {
        m_font = new Font();
        m_paint = new MyPaint();
        m_canvas = new Canvas();
        m_paint.setAntiAlias(true);//设置字体是否抗锯齿
        m_paint.setTypeface(m_font.mTypeface);//设置字体，Typeface包含了字体的类型，粗细，还有倾斜、颜色等。
        m_paint.setStrokeCap(Paint.Cap.ROUND); // dsw SQUARE
        m_paint.setStrokeJoin(Paint.Join.ROUND);// dsw 曲线连接处模式
        m_paint.setTextAlign(Paint.Align.LEFT);
        m_paint.setStrokeWidth(nDefaultLineWidth);
    }

    public void SetCanvas(Canvas canvas) {
        if (canvas != null && (canvas != m_canvas)) {
            m_canvas = canvas;
        }
    }

    public void setShader(Shader mShader) {
        m_paint.setShader(mShader);
    }
    /**
     * 透明度
     * @param Alpha
     */
    public void SetAlpha(int Alpha) {
        m_paint.setAlpha(Alpha);
    }

    public void setColor(int RGB) {
        m_paint.setColor(RGB);//Color.RED
    }

    public int getColor() {
        return m_paint.getColor();
    }

    public void setFont(Font font) {
        m_font = font;
    }

    public Font getFont() {
        return m_font;
    }
    
    public Canvas getCanvas() {
        return m_canvas;
    }

    public Paint getPaint() {
        return m_paint;
    }
    /**
     * 设置字体是否抗锯齿
     * @param bTrue
     */
    public void setAntiAlias(boolean bTrue) {
    	m_paint.setAntiAlias(bTrue);//设置字体是否抗锯齿
    }

    public float SetLineWidth(float fNewWidth) {
        float fOldWidht = m_paint.getStrokeWidth();
        m_paint.setStrokeWidth(fNewWidth);
        return fOldWidht;
    }

    public int SetTextSize(int nTextSize) {
        int nOldSize = (int) m_paint.getTextSize();
        
        m_paint.setTextSize(nTextSize);
        return nOldSize;
    }

    public int SelectObjectFont(int nTextSize) {
        int OldTextSize = (int) m_paint.getTextSize();
        m_paint.setTextSize(nTextSize);
        return OldTextSize;
    }

    public int GetTextSize() {
        return (int) m_paint.getTextSize();
    }

    public int GetDefaultTextSize() {
        return (int) m_paint.getDefaultTextSize();
    }
    
    public float GetFontYOffset() {
        FontMetrics myFont = m_paint.getFontMetrics();

        return Math.abs(myFont.ascent)/* - Math.abs(myFont.descent)*/;
    }


    public void setClip(int x, int y, int width, int height) {
    	if(m_canvas == null)
    		return;
        m_canvas.save();
        m_canvas.clipRect(x, y, x + width, y + height);
        m_canvas.restore();
    }
    
    /**
     * 填充Rect包含框线
     */
    public void fullFillRect(int x, int y, int width, int height) {
        m_paint.setStyle(Paint.Style.FILL);
        m_canvas.drawRect(x, y, width, height, m_paint);
    }
    
    /**
     * 填充Rect（不设置颜色）
     * @param 
     */
    public void fillRect(int x, int y, int width, int height) {
        FillRect(x, y, x + width, y + height, -1);
    }
    /**
     * 填充Rect（不设置颜色）
     * @param 
     */
    public void FillRect(int left, int top, int right, int bottom, int nColor) {
        if (nColor != -1) {
            m_paint.setColor(nColor);
        }

        m_paint.setStyle(Paint.Style.FILL);
        float inset = 0.5f;//m_paint.getStrokeWidth() * 0.5f;
        m_canvas.drawRect(left + inset, top + inset,
                right - inset, bottom - inset, m_paint);
    }
    
    /**
     * 填充Rect（不设置颜色）
     * @param 
     */
    public void FillAlphaRect(int left, int top, int right, int bottom, int alpha) {
        m_paint.setStyle(Paint.Style.FILL);
        m_paint.setAlpha(alpha);
        
        float inset = 0.5f;//m_paint.getStrokeWidth() * 0.5f;
        m_canvas.drawRect(left + inset, top + inset,
                right - inset, bottom - inset, m_paint);
        m_paint.setAlpha(0xff);
    }
    
    public void drawArgb(int r,int g,int b){
    	int alpha = 0;
    	m_canvas.drawARGB(alpha, r, g, b);
    }
    
    
    /**
     * 画字符串
     * @Param text	
     * @Param anchor
     */
    public void drawString(String text, int x, int y, Paint.Align anchor) {
    	try{
    		if(text != null && m_paint != null){
		        m_paint.setTextAlign(anchor);
		        m_canvas.drawText(text, x, y, m_paint);
    		}
    	}catch(Exception e){
    		JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
    	}
    }
    
    public CRect getBounds(String text) {
        Rect bounds = new Rect();
        m_paint.getTextBounds(text, 0, text.length(), bounds);
    	return new CRect(bounds);
    }

    public CRect drawString(String text, int x, int y) {
        return drawString(text, x, y, 0, 0, 0);
    }

    public CRect drawString(String text, float x, float y) {
        return drawString(text, x, y, 0, 0, 0);
    }

    public CRect drawString(String text, int x, int y, int nWidth, int nHeight, int anchor) {
        return drawString(text, (float)x, (float)y, nWidth, nHeight, anchor);
    }

    public CRect drawString(String text, float x, float y, int nWidth, int nHeight, int anchor) {
        Rect bounds = new Rect();

        float fXOffset = 0;
        float fYOffset = GetFontYOffset();
        m_paint.getTextBounds(text, 0, text.length(), bounds);
        m_paint.setTextAlign(Paint.Align.LEFT);
        m_canvas.drawText(text, x + fXOffset, y + fYOffset, m_paint);
        return new CRect(bounds);
    }

    public CRect drawSubstring(String str, int offset, int len, int x, int y, int nformat) {
        str = str.substring(offset, offset + len);
        return drawString(str, x, y, 0, 0, nformat);
    }

    public CRect drawChar(char character, int x, int y, int anchor) {
        String text = String.valueOf(character);
        return drawString(text, x, y, 0, 0, anchor);
    }

    public CRect drawChars(char[] data, int offset, int length, int x, int y, int align) {
        String tempstr = String.valueOf(data);
        tempstr = tempstr.substring(offset, offset + length);
        return drawString(tempstr, x, y, 0, 0, align);
    }

    public void drawImage(JiuyiImage img, int x, int y, int alpha1, int alpha2) {
    	try{
	    	if(img != null && img.bitmap != null){
	    		m_paint.setAlpha(alpha1);
	    		m_canvas.drawBitmap(img.bitmap, x, y, m_paint);
	    		m_paint.setAlpha(alpha2);
	    	}
    	}catch(Exception e){
    		JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }
    
    public void drawImage(JiuyiImage img, int x, int y, int anchor) {
    	try{
	    	if(img != null && img.bitmap != null)
	    		m_canvas.drawBitmap(img.bitmap, x, y, m_paint);
    	}catch(Exception e){
    		JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }

    public void drawImage(JiuyiImage img, int x, int y, Align anchor) {
    	try{
	    	if(img != null && img.bitmap != null){
	    		m_paint.setTextAlign(anchor);
	    		m_canvas.drawBitmap(img.bitmap, x, y, m_paint);
	    	}
    	}catch(Exception e){
    		JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }
    
    public void drawBitmap(Bitmap bitmap, int x, int y, int anchor) {
    	try{
	    	if(bitmap != null){
	    		m_canvas.drawBitmap(bitmap, x, y, m_paint);
	    	}
    	}catch(Exception e){
    		JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }
    
    public void drawSingleLine(int x1, int y1, int x2, int y2) {
        if(x1 == x2 && y1 == y2)
            return;

    	m_paint.setStrokeWidth(0);
        m_canvas.drawLine(x1, y1, x2, y2, m_paint);

    }
    
    public void drawLine(int x1, int y1, int x2, int y2) {
		if(x1 == x2 && y1 == y2)
			return;

        m_canvas.drawLine(x1, y1, x2, y2, m_paint);

    }
    public void drawdotLine(int x1, int y1, int x2, int y2, int width) {
    	if(x1 == x2 && y1 == y2)
    		return;
    	
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(width);

        mPaint.setColor(m_paint.getColor());
        Path mPath = makeFollowPath(x1, y1, x2, y2);

        PathEffect mEffects = new PathEffect();
        mEffects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);

        mPaint.setPathEffect(mEffects);
        m_canvas.drawPath(mPath, mPaint);
    }
    public void drawdotLine(int x1, int y1, int x2, int y2) {
        if(x1 == x2 && y1 == y2)
            return;

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
    	drawdotLine(x1, y1, x2, y2, (int)m_paint.getStrokeWidth());
    }

    public void drawdotLine(int x1, int y1, int x2, int y2, int width, float[] dotLineRule) {
        if(x1 == x2 && y1 == y2)
            return;

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(width);

        mPaint.setColor(m_paint.getColor());
        Path mPath = makeFollowPath(x1, y1, x2, y2);

        PathEffect mEffects = new PathEffect();
        mEffects = new DashPathEffect(dotLineRule, 1);

        mPaint.setPathEffect(mEffects);
        m_canvas.drawPath(mPath, mPaint);
    }
    public void drawdotLine(int x1, int y1, int x2, int y2, float[] dotLineRule) {
        if(x1 == x2 && y1 == y2)
            return;

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        drawdotLine(x1, y1, x2, y2, (int)m_paint.getStrokeWidth(), dotLineRule);
    }

    private Path makeFollowPath(int x1, int y1, int x2, int y2) {
        Path p = new Path();
        p.moveTo(x1, y1);
        //for (int i = 1; i <= 16; i++) {
        p.lineTo(x2, y2);
        //}
        return p;
    }

    public void drawLine(int x1, int y1, int x2, int y2, int bUser) {
		if(x1 == x2 && y1 == y2)
			return;
		
        int nCount;
        if (bUser > 0) {
            nCount = x1 - x2;
        } else {
            nCount = y1 - y2;
        }
        nCount = Math.abs(nCount);
        for (int i = 0; i < nCount; i += 5) {
            if (bUser > 0) {
                drawLine(x1 + i, y1, x1 + i, y2);
            } else {
                drawLine(x1, y1 + i, x2, y1 + i);
            }
        }
    }



    public static int GetWidth() {
        return stringWidth("A", (int) MyPaint.TextSize);
    }

    public static int GetHeight() {
        return stringHeight("国", (int) MyPaint.TextSize);
    }

    public static int stringWidth(String str, int textSize) {
        Rect bounds = stringRect(str, textSize);
        return bounds.width();
    }

    public static int GetHeightBySize(int textsize) {
        return stringHeight("国", textsize);
    }
    
    public static int stringHeight(String str, int textSize) {
        Rect bounds = stringRect(str, textSize);
        return bounds.height();
    }

    public static Rect stringRect(String str, int textSize) {
        MyPaint paint = new MyPaint();
        paint.setAntiAlias(true);
        Font font = new Font();
        paint.setTypeface(font.mTypeface);
        paint.setStrokeCap(Paint.Cap.ROUND); // dsw SQUARE
        paint.setStrokeJoin(Paint.Join.ROUND);// dsw 鏇茬嚎杩炴帴澶勬ā寮�
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStrokeWidth(Graphics.nDefaultLineWidth);
        paint.setTextSize(textSize);

        Rect bounds = new Rect();
        paint.getTextBounds(str, 0, str.length(), bounds);
        return bounds;
    }

    public int stringWidth(String str) {
        Rect bounds = new Rect();
        m_paint.getTextBounds(str, 0, str.length(), bounds);
        return bounds.width();
    }
    
    public float stringWidth_zhengss(String str,int textSize) {
        if(str == null || str.isEmpty())
            str = " ";
		float old = m_paint.getTextSize();
		m_paint.setTextSize(textSize);
		float txt = m_paint.measureText(str);
		m_paint.setTextSize(old);
        return txt; 
    }
    

    //鐢讳笁瑙�
    public void DrawTriangle(int x, int y, int iRadius, int nColor, boolean bUp) {
        Point ptThree[] = new Point[3];
        for (int i = 0; i < ptThree.length; i++) {
            ptThree[i] = new Point();
        }
        ptThree[0].x = x;
        ptThree[1].x = x + (int) (iRadius * 0.866);
        ptThree[2].x = x + (int) (iRadius * 0.866 * 2);

        if (bUp) {
            ptThree[0].y = y;
            ptThree[1].y = y - (int) (iRadius * 2 * 0.866);
            ptThree[2].y = y;
        } else {
            ptThree[0].y = y - (int) (iRadius * 2 * 0.866);
            ptThree[1].y = y;
            ptThree[2].y = y - (int) (iRadius * 2 * 0.866);
        }
        m_paint.setColor(nColor);
        m_canvas.drawLine(ptThree[0].x, ptThree[0].y, ptThree[1].x, ptThree[1].y, m_paint);
        m_canvas.drawLine(ptThree[1].x, ptThree[1].y, ptThree[2].x, ptThree[2].y, m_paint);
        m_canvas.drawLine(ptThree[0].x, ptThree[0].y, ptThree[2].x, ptThree[2].y, m_paint);
    }

    // dsw 2010.06.05 浣跨敤缁熶竴鍑芥暟缁樺埗鐭╁舰
    public void DrawRect(int left, int top, int right, int bottom, int nColor) {
        //m_canvas.save();//锟斤拷锟�

        if (nColor != -1) {
            m_paint.setColor(nColor);
        }

        Paint.Style nOldStyle = m_paint.getStyle();

        //m_paint.setStrokeCap(Paint.Cap.SQUARE); // dsw SQUARE
        //m_paint.setStrokeJoin(Paint.Join.ROUND);// dsw 鏇茬嚎杩炴帴澶勬ā寮�
        m_paint.setStyle(Paint.Style.STROKE);
        //float fOld = SetLineWidth(1.0f);

        float inset = 0.5f;
        m_canvas.drawRect(left + inset, top + inset,
                right - inset, bottom - inset, m_paint);

        //SetLineWidth(fOld);
        m_paint.setStyle(nOldStyle);

        //m_canvas.restore();//
    }

    

    public void Ellipse (CRect rect) {
        m_canvas.clipRect(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void drawRect(int x, int y, int width, int height) {
        // dsw 2010.06.05 浣跨敤缁熶竴鍑芥暟缁樺埗鐭╁舰
        DrawRect(x, y, x + width - 1, y + height - 1, -1);
        // m_paint.setStyle(Paint.Style.STROKE);
        // m_paint.setStrokeWidth(1);
        // m_canvas.drawRect(x,y,x+width-1,y+height-1,m_paint);
    }



    public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {// 锟斤拷
    	Style style = m_paint.getStyle();
    	m_paint.setStyle(Paint.Style.FILL);   
        Path path2=new Path();   
        path2.moveTo(x1, y1);   
        path2.lineTo(x2,y2);   
        path2.lineTo(x3,y3);   
        path2.close();   
        m_canvas.drawPath(path2, m_paint); //m_paint.setStyle(Paint.Style.STROKE);
        m_paint.setStyle(style);
    }

    public int DrawText(String str, CRect rc, long lFlag, int nColor) {
        m_paint.setColor(nColor);
        CRect rect = drawString(str, rc.left, rc.top, rc.Width(), rc.Height(), (int) lFlag);
        return rect.Width();
    }

    public void CRectangle (CRect rect, int nColor) {
        DrawRect(rect.left, rect.top, rect.right, rect.bottom, nColor);
        //m_paint.setColor(nColor);
        //m_paint.setStyle(Paint.Style.STROKE);
        //m_paint.setStrokeWidth(1);
        //m_canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, m_paint);
    }

    public void FillRect (CRect rect, int nColor) {
        FillRect(rect.left, rect.top, rect.right, rect.bottom, nColor);
        //m_paint.setColor(nColor);
        //m_paint.setStyle(Paint.Style.FILL);
        //m_canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, m_paint);
    }

    public void FillRectangle (CRect rc, int clFillColor, int nRectColor) {
        FillRect(rc.left, rc.top, rc.right, rc.bottom, clFillColor);
        DrawRect(rc.left, rc.top, rc.right, rc.bottom, nRectColor);
    }


    public void FillRoundAlphaRect (CRect rect, int alpha) {
        FillRoundAlphaRect(rect.left, rect.top, rect.right, rect.bottom, alpha);
    }


    //dsw 2010.06.08 淇敼FillRoundRect绠楁硶
    public void FillRoundAlphaRect(int nLeft, int nTop, int nRight, int nBottom,int alpha) {
        final short nFillWidht = (short) Res.Dip2Pix(2);

        m_paint.setAlpha(alpha);
        m_paint.setStyle(Paint.Style.FILL_AND_STROKE);

        float fOld = SetLineWidth((float) nFillWidht);

        int y = Math.min((nBottom - nTop), nFillWidht) / 2;
        int x = Math.min((nRight - nLeft), nFillWidht) / 2;

        m_canvas.drawRoundRect(new RectF(nLeft + x, nTop + y, nRight - x, nBottom - y),3.0f,3.0f, m_paint);

        SetLineWidth(fOld);

        m_paint.setStyle(Paint.Style.FILL);
        m_paint.setAlpha(0xff);
        //m_canvas.restore();//
    }
    
    public void DrawRoundRect(CRect rect) {
        DrawRoundRect(rect, 0);
    }
    
    public void DrawRoundRect(CRect rect, int linewidth) {
        final short nFillWidht = (short) Res.Dip2Pix(2);
        Paint.Style nOldStyle = m_paint.getStyle();

        m_paint.setStyle(Paint.Style.STROKE);
        SetLineWidth(linewidth);

        int y = 0;//Math.min((rect.bottom - rect.top), nFillWidht) / 2;
        int x = 0;//Math.min((rect.right - rect.left), nFillWidht) / 2;

        m_canvas.drawRoundRect(new RectF(rect.left + x, rect.top + y, rect.right - x, rect.bottom - y),nFillWidht,nFillWidht, m_paint);
        m_paint.setStyle(nOldStyle);
    }
    
    

    public void drawArc(CRect rect, int startAngle, int sweepAngle, boolean useCenter) {
    	
    	m_canvas.drawArc(new RectF(rect.left, rect.top, rect.right, rect.bottom), startAngle, sweepAngle, useCenter, m_paint);
    }
    
    public void drawCircle(int x,int y, int r, int alpha){
    	m_paint.setAlpha(alpha);
    	m_canvas.drawCircle(x, y, r, m_paint);
    	m_paint.setAlpha(0xff);
    }

    /**
     * 填充Rect（不设置颜色）
     * @param
     */
    public void fillRect(float x, float y, float width, float height) {
        FillRect(x, y, x + width, y + height, -1);
    }
    /**
     * 填充Rect（不设置颜色）
     * @param
     */
    public void FillRect(float left, float top, float right, float bottom, int nColor) {
        if (nColor != -1) {
            m_paint.setColor(nColor);
        }
        m_paint.setStyle(Paint.Style.FILL);
        m_canvas.drawRect(left, top,
                right, bottom, m_paint);
        //m_canvas.drawRect(left,top,right,bottom,m_paint);
        //m_canvas.restore();//
    }
    
}
