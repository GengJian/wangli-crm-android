package com.control.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.control.utils.JiuyiLog;

import java.util.Random;

/**
 * ****************************************************************
 * 文件名称:jiuyiValidateImageView.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:生成验证码图片
 * ****************************************************************
 */
public class JiuyiValidateImageView extends View{
    /** TAG */
	private String TAG = "ValidateImageView";
    /** 绘制的Paint */
	private Paint mPaint = new Paint();
    /**  */
	private String[] mContent = null;
    /** 验证码图 */
	private Bitmap mBitmap = null;
    /** 宽 */
	private int mWidth;
    /** 高 */
	private int mHeight;
    /** 验证码数字 */
	private String mIdentifyingCode ="";//
	
	public JiuyiValidateImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public JiuyiValidateImageView(Context context) {
		super(context);
	}

    /**
     * 系统绘制方法draw
     * @param canvas 画布
     */
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (mBitmap != null) {
			canvas.drawBitmap(mBitmap, 0, 0, mPaint);
		} else {
			mPaint.setColor(Color.GRAY);
			mPaint.setTextSize(20);
			canvas.drawText("点击换一换", 10, 30, mPaint);
		}
		super.draw(canvas);
	}

    /**
     * 为数组赋值1~9的随机数
     * @return 随机数数组
     */
	public String[] getRandomInteger(){
		String[] reuestArray = new String[4];
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			reuestArray[i] = String.valueOf((random.nextInt(10) + i) % 10);
		}
		return reuestArray;

	}

    /**
     * 设置区域大小
     * @param w
     * @param h
     */
	public void setSize(int w, int h){
		mWidth = w;
		mHeight = h;
	}
	
	/**
	 * 得到验证码；设置图片
	 * @return 验证码
	 */
	public String getValidataAndSetImage() {
		String[] strContent = getRandomInteger();
		
		mContent = strContent;
		//产生随机数，并返回
		String [] strRes = generageRadom(strContent);
		JiuyiLog.i(TAG, "generate validate code: " + strRes[0] + strRes[1] + strRes[2] + strRes[3]);
		//传随机串和随机数
		mBitmap = generateValidate(mContent,strRes);
		invalidate();

		return strRes[0] + strRes[1] + strRes[2] + strRes[3];
	}

    /**
     * 绘制随机数，得到Bitmap
     * @param strContent
     * @param strRes
     * @return
     */
	private Bitmap generateValidate(String[] strContent,String [] strRes) {
		int isRes = isStrContent(strContent);
		if (isRes == 0) {
			return null;
		}
		
		Bitmap sourceBitmap = Bitmap.createBitmap(mWidth, mHeight, Config.ARGB_8888);
		Canvas canvas = new Canvas(sourceBitmap);
		Paint p = new Paint();
		p.setTextSize(mHeight / 2);
		p.setFakeBoldText(true);
		
		p.setColor(0xFFD0CCC7);
		canvas.drawRect(0, 0, mWidth, mHeight, p);

		p.setColor(getRandColor(150, 150, 150));
		canvas.drawText(strRes[0], mWidth /10, mHeight / 2, p);
		Matrix m1 = new Matrix();
		m1.setRotate(15);
		canvas.setMatrix(m1);

		p.setColor(getRandColor(150, 150, 150));
		canvas.drawText(strRes[1], mWidth *2/5, mHeight / 2, p);
		m1.setRotate(10);
		canvas.setMatrix(m1);

		p.setColor(getRandColor(150, 150, 150));
		canvas.drawText(strRes[2], mWidth *3/5, mHeight / 2 - 10, p);
		m1.setRotate(15);
		canvas.setMatrix(m1);

		p.setColor(getRandColor(150, 150, 150));
		canvas.drawText(strRes[3], mWidth *4/5, mHeight / 2 - 15, p);
		m1.setRotate(20);
		canvas.setMatrix(m1);
		
		//障碍设置
		int startX = 0,startY = 0,stopX = 0,stopY = 0;
		for (int i = 0; i < 55; i++) {
			startX = pointRadom(mWidth);
			startY = pointRadom(mHeight);
			stopX = pointRadom(15);
			stopY = pointRadom(15);
			p.setColor(getRandColor(200, 230, 220));
			canvas.drawLine(startX, startY - 20, startX + stopX, startY + stopY - 20, p);
		}
		
		canvas.save();
		return sourceBitmap;
	}

    /**
     * 数组是否为空
     * @param strContent
     * @return
     */
	private int isStrContent(String[] strContent) {
		if (strContent == null || strContent.length <= 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * 从指定数组中随机取出4个字符(数组)
	 * @param strContent
	 * @return
	 */
	private String[] generageRadom(String[] strContent){
		mIdentifyingCode ="";
		// 生成4个随机数
		String[] str = new String[4];
		int count = 9;
		Random random = new Random();
		int randomResFirst = onCheckRadom(random.nextInt(count),count);
		int randomResSecond = onCheckRadom(random.nextInt(count),count);
		int randomResThird = onCheckRadom(random.nextInt(count),count);
		int randomResFourth = onCheckRadom(random.nextInt(count),count);
		
		str[0] = randomResFirst+"";
		str[1] = randomResSecond+"";
		str[2] = randomResThird+"";
		str[3] = randomResFourth+"";
		return str;
	}

    /**
     * 检查随机数不要重复
     * @param nValue
     * @param nCount
     * @return
     */
	private int onCheckRadom(int nValue, int nCount)
	{
		Random random = new Random();
		boolean bVlaue=true;
		while (bVlaue) {
			if(mIdentifyingCode.contains(nValue+""))
				nValue=random.nextInt(nCount);
			else
				bVlaue=false;
		}
		mIdentifyingCode +=nValue;
		return nValue;
	}
	
	/**
	 * 从指定数组中随机取出4个字符(字符串)
	 * @param strContent
	 * @return
	 */
	private String generageRadomStr(String[] strContent){
		StringBuilder str = new StringBuilder();
		// 随机串的个数
		int count = strContent.length;
		// 生成4个随机数
		Random random = new Random();
		int randomResFirst = random.nextInt(count);
		int randomResSecond = random.nextInt(count);
		int randomResThird = random.nextInt(count);
		int randomResFourth = random.nextInt(count);
		
		str.append(strContent[randomResFirst].toString().trim());
		str.append(strContent[randomResSecond].toString().trim());
		str.append(strContent[randomResThird].toString().trim());
		str.append(strContent[randomResFourth].toString().trim());
		return str.toString();
	}

    /**
     * 获取一个随机数
     * @param n
     * @return
     */
	private int pointRadom(int n){
		Random r = new Random();
		return r.nextInt(n);
	}

	/**
	 * 给定范围获得随机颜色
	 * @param rc 0-255
	 * @param gc 0-255
	 * @param bc 0-255
	 * @return colorValue 颜色值，使用setColor(colorValue)
	 */
	public int getRandColor(int rc, int gc, int bc) {
		Random random = new Random();
		if (rc > 255)
			rc = 255;
		if (gc > 255)
			gc = 255;
		if (bc > 255)
			bc = 255;

		int r = random.nextInt(rc);
		int g = random.nextInt(gc);
		int b = random.nextInt(bc);
		return Color.rgb(r, g, b);
	}

}