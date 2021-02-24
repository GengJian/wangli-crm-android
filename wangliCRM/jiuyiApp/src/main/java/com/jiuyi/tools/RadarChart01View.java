/**
 * Copyright 2014  XCL-Charts
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 	
 * @Project XCL-Charts 
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.0
 */
package com.jiuyi.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


import com.control.widget.xclcharts.chart.RadarChart;
import com.control.widget.xclcharts.chart.RadarData;
import com.control.widget.xclcharts.common.DensityUtil;
import com.control.widget.xclcharts.common.IFormatterDoubleCallBack;
import com.control.widget.xclcharts.common.IFormatterTextCallBack;
import com.control.widget.xclcharts.event.click.PointPosition;
import com.control.widget.xclcharts.renderer.XEnum;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName RadarChart01View
 * @Description  蜘蛛雷达图
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class RadarChart01View extends baseChartView {

	private String TAG = "RadarChart01View";
	private RadarChart chart = new RadarChart();


	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	//标签集合
	private List<String> labels = new LinkedList<String>();

	public List<RadarData> getChartData() {
		return chartData;
	}

	public void setChartData(List<RadarData> chartData) {
		this.chartData = chartData;
	}

	private List<RadarData> chartData = new LinkedList<RadarData>();
	
	private Paint mPaintTooltips = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	
	public RadarChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		 initView();
	}
	
	public RadarChart01View(Context context, AttributeSet attrs){
        super(context, attrs);   
        initView();
	 }
	 
	 public RadarChart01View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {
		 	chartLabels();
			chartDataSet();	
			chartRender();
			
			//綁定手势滑动事件
//			this.bindTouch(this,chart);
	 }
	 
	 
	
	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w,h);
    }
	protected int[] getPieDefaultSpadding()
	{
		int [] ltrb = new int[4];
		ltrb[0] = DensityUtil.dip2px(getContext(), 50); //left
		ltrb[1] = DensityUtil.dip2px(getContext(), 25); //top
		ltrb[2] = DensityUtil.dip2px(getContext(), 50); //right
		ltrb[3] = DensityUtil.dip2px(getContext(), 0); //bottom
		return ltrb;
	}
	public void chartRender()
	{
		try{				
			//设置绘图区默认缩进px值
			int [] ltrb = getPieDefaultSpadding();
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);

			//设定数据源
			chart.setCategories(labels);								
			chart.setDataSource(chartData);
			
			//点击事件处理
			chart.ActiveListenItemClick();
			chart.extPointClickRange(50);
			chart.showClikedFocus();
			
			//数据轴最大值
			chart.getDataAxis().setAxisMax(100);
			//数据轴刻度间隔
			chart.getDataAxis().setAxisSteps(20);
			//主轴标签偏移50，以便留出空间用于显示点和标签
			chart.getDataAxis().setTickLabelMargin(50);
			
			//定义数据轴标签显示格式
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub		
					Double tmp = Double.parseDouble(value);
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(tmp).toString();
					return (label);
				}
				
			});
			
			//定义数据点标签显示格式
			chart.setDotLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					DecimalFormat df= new DecimalFormat("#0");
					String label = df.format(value).toString();
					return label;
				}});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
		
	}
	
	private void chartDataSet()
	{
		LinkedList<Double> dataSeriesA= new LinkedList<Double>();
		dataSeriesA.add(0d); //20d
		dataSeriesA.add(0d);
		dataSeriesA.add(0d);
		dataSeriesA.add(0d);
		dataSeriesA.add(0d);
		
		RadarData lineData1 = new RadarData("",dataSeriesA,
					Color.rgb(66, 144, 247), XEnum.DataAreaStyle.FILL);
		lineData1.setLabelVisible(true);
		lineData1.setDotStyle(XEnum.DotStyle.HIDE);
		chartData.add(lineData1);
	}
	
	private void chartLabels()
	{
		labels.add("信息完整度");
		labels.add("用量");
		labels.add("忠诚度");
		labels.add("信贷风险");
		labels.add("盈利贡献");
	}
	
	@Override
    public void render(Canvas canvas) {
        try{
            chart.render(canvas);
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction() == MotionEvent.ACTION_UP)
		{			
			triggerClick(event.getX(),event.getY());			
		}
		return true;
	}
	
	//触发监听
	private void triggerClick(float x,float y)
	{
		PointPosition record = chart.getPositionRecord(x,y);
		if( null == record) return;
			
		if(record.getDataID() < chartData.size())
		{
			RadarData lData = chartData.get(record.getDataID());
			Double lValue = lData.getLinePoint().get(record.getDataChildID());
									
			float r = record.getRadius();
			chart.showFocusPointF(record.getPosition(),r + r*0.5f);		
			chart.getFocusPaint().setStyle(Style.STROKE);
			chart.getFocusPaint().setStrokeWidth(3);		
			chart.getFocusPaint().setColor(Color.YELLOW);
			
			//在点击处显示tooltip
			mPaintTooltips.setColor(Color.RED);
			chart.getToolTip().setCurrentXY(x,y);
			chart.getToolTip().addToolTip(" 点击",mPaintTooltips);		
			chart.getToolTip().addToolTip(" Current Value:"+ Double.toString(lValue),mPaintTooltips);
			this.invalidate();
		}
	}

}
