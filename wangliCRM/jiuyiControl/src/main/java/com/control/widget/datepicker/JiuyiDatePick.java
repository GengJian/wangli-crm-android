package com.control.widget.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.control.widget.datepicker.wheelview.OnWheelScrollListener;
import com.control.widget.datepicker.wheelview.WheelView;
import com.control.widget.datepicker.wheelview.adapter.NumericWheelAdapter;

import java.util.Calendar;


public class JiuyiDatePick extends JiuyiDatePickBase {
	public WheelView year;
	public WheelView month;
	public WheelView day;

	public int mYear;//默认的年
	public int mMonth;//默认的月
	public int mDay;//默认的日
	
	private int lastYear;//上一次选择的年
	private int lastMonth;//上一次的最大月分
	private int lastDay;//上一次的最大日期
	
	private int lastmaxYear;//实际可选的最大年份（lastYear<=lastmaxYear）
	
	public View view = null;
	public int wheelBackground;
	public int wheelForeground;
	public int centerRectColor;
	
	public boolean isMonthSetted = false, 
			isDaySetted = false;

	public JiuyiDatePick(Context context, int tztskintype, int nDateToType) {
		super(context, tztskintype);

		//设置时间
        Calendar m_Curdate = Calendar.getInstance();
        if(nDateToType == DATEFORMAT_DateToYestoday){
        	m_Curdate.add(Calendar.DAY_OF_MONTH, -1);
        } else if(nDateToType == DATEFORMAT_DateToToday){
        	m_Curdate.add(Calendar.DAY_OF_MONTH, 0);
        }
        	

        mYear = m_Curdate.get(Calendar.YEAR);// 获取当前年份
        mMonth = m_Curdate.get(Calendar.MONTH)+1;// 获取当前月份
        mDay = m_Curdate.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
        
        if(nDateToType == DATEFORMAT_DateToYestoday || nDateToType == DATEFORMAT_DateToToday){
        	lastmaxYear = mYear;
        	MONTH_END = mMonth;
        	DAY_END = mDay;
        }
        
    	lastYear = mYear;//上一次选择的年
    	lastMonth = mMonth;//上一次选择的月
	}
	
	public JiuyiDatePick(Context context, int tztskintype, int nDateToType, int mYear, int mMonth, int mDay) {
		super(context, tztskintype);

		this.mYear = mYear;
		this.mMonth = mMonth;
		this.mDay = mDay;
		
        if(nDateToType == DATEFORMAT_DateToYestoday || nDateToType == DATEFORMAT_DateToToday){
    		//设置时间
            Calendar m_Curdate = Calendar.getInstance();
            //update by zhengss20170421（不管是否包含当日，当日的时间是允许用户选择的，与ios保持一致）
//            if(nDateToType == DATEFORMAT_DateToYestoday){
//            	m_Curdate.add(Calendar.DAY_OF_MONTH, -1);
//            } else if(nDateToType == DATEFORMAT_DateToToday){
            	m_Curdate.add(Calendar.DAY_OF_MONTH, 0);
//            }

            lastmaxYear = m_Curdate.get(Calendar.YEAR);// 获取当前年份
        	MONTH_END = m_Curdate.get(Calendar.MONTH)+1;// 获取当前月份
        	DAY_END = m_Curdate.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
        }
        
    	lastYear = mYear;//上一次选择的年
    	lastMonth = mMonth;//上一次选择的月
	}
	


	public View initView() {
		initResources();

		int curYear = mYear;
		int curMonth = mMonth;
		int curDate = mDay;
		
		view = LayoutInflater.from(context).inflate(getLayoutID(context, "jiuyi_datepick_wheel_date_picker"), null);
		year = (WheelView) view.findViewById(getViewID(context, "year"));
		year.setBackgroundResources(wheelBackground, wheelForeground, centerRectColor);
		
		Calendar m_Curdate = Calendar.getInstance();
		lastYear = m_Curdate.get(Calendar.YEAR);
		year.setMaxValue(lastYear - YEAR_START);
		NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(context, YEAR_START, YEAR_END, lastYear, null);
		numericWheelAdapter1.setLabel("年");
		year.setViewAdapter(numericWheelAdapter1);
		year.setCyclic(true);// 是否可循环滑动
		year.addScrollingListener(scrollListener);

		month = (WheelView) view.findViewById(getViewID(context, "month"));
		month.setBackgroundResources(wheelBackground, wheelForeground, centerRectColor);
//		NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(context, MONTH_START, MONTH_END, "%02d");
//		numericWheelAdapter2.setLabel("月");
//		month.setViewAdapter(numericWheelAdapter2);
		initMonthByYear(curYear, curYear == YEAR_END);
		month.setCyclic(true);
		month.addScrollingListener(scrollListener);

		day = (WheelView) view.findViewById(getViewID(context, "day"));
		day.setBackgroundResources(wheelBackground, wheelForeground, centerRectColor);
		day.setCyclic(true);
		initDayByMonth(curYear, curMonth, true);
		day.addScrollingListener(scrollListener);

		year.setVisibleItems(7);// 设置显示行数
		month.setVisibleItems(7);
		day.setVisibleItems(7);

		year.setCurrentItem(curYear - YEAR_START);
		month.setCurrentItem(curMonth - 1);
		day.setCurrentItem(curDate - 1);

		return view;
	}

	/**
	 * 根据年月获取每月多少天
	 * @param year
	 * @param month
	 * @return
	 */
	private int getMonthDays(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
			case 0:
				flag = true;
				break;
			default:
				flag = false;
				break;
		}
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				day = 31;
				break;
			case 2:
				day = flag ? 29 : 28;
				break;
			default:
				day = 30;
				break;
		}
		return day;
	}

	public void initResources(){
		wheelBackground = getDrawabelID(context, "jiuyi_datepick_wheel_bg");
		wheelForeground = getDrawabelID(context, "jiuyi_datepick_wheel_val");
		centerRectColor = getColor(context, "tzt_datepick_wheel_line_bordercolor");
		
		scrollListener = new OnWheelScrollListener() {
			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				int tmpYear = year.getCurrentItem()+YEAR_START;
				int tmpMonth = month.getCurrentItem() + 1;		
				int tmpDay = day.getCurrentItem();
				
				lastYear = tmpYear;
				initMonthByYear(tmpYear, tmpYear == lastYear && lastmaxYear == lastYear);
				if(tmpMonth > lastMonth){
					tmpMonth = lastMonth;
					month.setCurrentItem(lastMonth - 1);
				}
				initDayByMonth(tmpYear, tmpMonth, tmpYear == lastYear && lastmaxYear == lastYear && tmpMonth == MONTH_END);
				if(tmpDay >= lastDay){
					day.setCurrentItem(lastDay - 1);
				}
				
				int[] value = new int[]{year.getCurrentItem()+YEAR_START, month.getCurrentItem()+1, day.getCurrentItem()+1};
				OnWheelScrollFinishedData(value);
			}
		};
	}
	/**
	 * 通过年份设置月数，因为当日之前的时间是1个月
	 */
	private void initMonthByYear(int arg1, boolean isLastYear) {
		lastMonth = isLastYear ? MONTH_END : 12;
		month.setCyclic(true);
		month.setMaxValue(lastMonth-1);
		NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, MONTH_START, 12, lastMonth-1, "%02d");
		numericWheelAdapter.setLabel("月");
		month.setViewAdapter(numericWheelAdapter);
	}
	/**
	 * 通过月份设置当月的天数
	 */
	private void initDayByMonth(int arg1, int arg2, boolean isLastMonth) {
		lastDay = (isLastMonth) ? DAY_END : getMonthDays(arg1, arg2);
		day.setCyclic(true);
		day.setMaxValue(lastDay-1);
		NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, DAY_START, getMonthDays(arg1, arg2), lastDay-1, "%02d");
		numericWheelAdapter.setLabel("日");
		day.setViewAdapter(numericWheelAdapter);
	}
	
	@Override
	public void OnWheelScrollFinishedData(int[] value) {

	}
}
