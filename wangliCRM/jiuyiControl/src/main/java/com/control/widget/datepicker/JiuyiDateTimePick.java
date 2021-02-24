package com.control.widget.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.control.widget.datepicker.wheelview.OnWheelScrollListener;
import com.control.widget.datepicker.wheelview.WheelView;
import com.control.widget.datepicker.wheelview.adapter.NumericWheelAdapter;

import java.util.Calendar;


public class JiuyiDateTimePick extends JiuyiDatePickBase {
	public WheelView year;
	public WheelView month;
	public WheelView day;
	
	private WheelView hour;
	private WheelView min;

	public int mYear;//默认的年
	public int mMonth;//默认的月
	public int mDay;//默认的日
	
	int mHour;
	int mMin;
	
	private int lastYear;//上一次选择的年
	private int lastMonth;//上一次的最大月分
	private int lastDay;//上一次的最大日期
	
	public View view = null;
	public int wheelBackground;
	public int wheelForeground;
	public int centerRectColor;
	
	public boolean isMonthSetted = false, 
			isDaySetted = false;

	public JiuyiDateTimePick(Context context, int tztskintype, int nDateToType) {
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
		
		m_Curdate = Calendar.getInstance();
        mHour = m_Curdate.get(Calendar.HOUR_OF_DAY) - 1;
        mMin = m_Curdate.get(Calendar.MINUTE) - 1;
        
        if(nDateToType == DATEFORMAT_DateToYestoday || nDateToType == DATEFORMAT_DateToToday){
        	YEAR_END = mYear;
        	MONTH_END = mMonth;
        	DAY_END = mDay;
        }
        
    	lastYear = mYear;//上一次选择的年
    	lastMonth = mMonth;//上一次选择的月
	}
	
	public JiuyiDateTimePick(Context context, int tztskintype, int nDateToType, int mYear, int mMonth, int mDay, int mHour, int mMin) {
		super(context, tztskintype);

		this.mYear = mYear;
		this.mMonth = mMonth;
		this.mDay = mDay;
		
		this.mHour = mHour - 1;
		this.mMin = mMin - 1;
		
        if(nDateToType == DATEFORMAT_DateToYestoday || nDateToType == DATEFORMAT_DateToToday){
    		//设置时间
            Calendar m_Curdate = Calendar.getInstance();
            if(nDateToType == DATEFORMAT_DateToYestoday){
            	m_Curdate.add(Calendar.DAY_OF_MONTH, -1);
            } else if(nDateToType == DATEFORMAT_DateToToday){
            	m_Curdate.add(Calendar.DAY_OF_MONTH, 0);
            }

        	YEAR_END = m_Curdate.get(Calendar.YEAR);// 获取当前年份
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
		
		view = LayoutInflater.from(context).inflate(getLayoutID(context, "jiuyi_datepick_wheel_datetime_picker"), null);
		year = (WheelView) view.findViewById(getViewID(context, "year"));
		year.setBackgroundResources(wheelBackground, wheelForeground, centerRectColor);
		NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(context, YEAR_START, YEAR_END);
		numericWheelAdapter1.setLabel("年");
		year.setViewAdapter(numericWheelAdapter1);
		//year.setCyclic(true);// 是否可循环滑动
		year.addScrollingListener(scrollListener);

		month = (WheelView) view.findViewById(getViewID(context, "month"));
		month.setBackgroundResources(wheelBackground, wheelForeground, centerRectColor);
//		NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(context, MONTH_START, MONTH_END, "%02d");
//		numericWheelAdapter2.setLabel("月");
//		month.setViewAdapter(numericWheelAdapter2);
		initMonthByYear(curYear, true);
		month.setCyclic(true);
		month.addScrollingListener(scrollListener);

		day = (WheelView) view.findViewById(getViewID(context, "day"));
		day.setBackgroundResources(wheelBackground, wheelForeground, centerRectColor);
		day.setCyclic(true);
		initDayByMonth(curYear, curMonth, true);
		day.addScrollingListener(scrollListener);
		
		hour = (WheelView) view.findViewById(getViewID(context, "hour"));
		hour.setBackgroundResources(wheelBackground, wheelForeground, centerRectColor);
		NumericWheelAdapter numericWheelAdapter3 = new NumericWheelAdapter(context, 1, 23, "%02d");
		numericWheelAdapter3.setLabel("时");
		hour.setViewAdapter(numericWheelAdapter3);
		hour.setCyclic(true);
		hour.addScrollingListener(scrollListener);
		
		min = (WheelView) view.findViewById(getViewID(context, "min"));
		min.setBackgroundResources(wheelBackground, wheelForeground, centerRectColor);
		NumericWheelAdapter numericWheelAdapter4 = new NumericWheelAdapter(context, 1, 59, "%02d");
		numericWheelAdapter4.setLabel("分");
		min.setViewAdapter(numericWheelAdapter4);
		min.setCyclic(true);
		min.addScrollingListener(scrollListener);

		year.setVisibleItems(DEFAULT_LISTVIEW_SIZE);// 设置显示行数
		month.setVisibleItems(DEFAULT_LISTVIEW_SIZE);
		day.setVisibleItems(DEFAULT_LISTVIEW_SIZE);
		hour.setVisibleItems(DEFAULT_LISTVIEW_SIZE);
		min.setVisibleItems(DEFAULT_LISTVIEW_SIZE);

		year.setCurrentItem(curYear - YEAR_START);
		month.setCurrentItem(curMonth - 1);
		day.setCurrentItem(curDate - 1);
		hour.setCurrentItem(mHour);
		min.setCurrentItem(mMin);
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
				
				initMonthByYear(tmpYear, tmpYear == YEAR_END);
				if(tmpMonth > lastMonth){
					tmpMonth = lastMonth;
					month.setCurrentItem(lastMonth - 1);
				}
				initDayByMonth(tmpYear, tmpMonth, tmpMonth == MONTH_END);
				if(tmpDay > lastDay){
					day.setCurrentItem(lastDay - 1);
				}
				lastYear = tmpYear;
				
				int[] value = new int[]{year.getCurrentItem()+YEAR_START, month.getCurrentItem()+1, day.getCurrentItem()+1, hour.getCurrentItem()+1, min.getCurrentItem()+1};
				OnWheelScrollFinishedData(value);
			}
		};
	}
	/**
	 * 通过年份设置月数，因为当日之前的时间是1个月
	 */
	private void initMonthByYear(int arg1, boolean isLastYear) {
		lastMonth = isLastYear ? MONTH_END : 12;
		NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, MONTH_START, lastMonth, "%02d");
		numericWheelAdapter.setLabel("月");
		month.setViewAdapter(numericWheelAdapter);
	}
	/**
	 * 通过月份设置当月的天数
	 */
	private void initDayByMonth(int arg1, int arg2, boolean isLastMonth) {
//		NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, DAY_START, Math.min(DAY_END, getMonthDays(arg1, arg2)), "%02d");
		lastDay = (arg1 == YEAR_END && isLastMonth) ? DAY_END : getMonthDays(arg1, arg2);
		NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, DAY_START, lastDay, "%02d");
		numericWheelAdapter.setLabel("日");
		day.setViewAdapter(numericWheelAdapter);
	}
	
	@Override
	public void OnWheelScrollFinishedData(int[] value) {

	}
}
