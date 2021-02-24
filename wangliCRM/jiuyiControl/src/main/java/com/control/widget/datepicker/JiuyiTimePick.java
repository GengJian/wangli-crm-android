package com.control.widget.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.control.widget.datepicker.wheelview.OnWheelScrollListener;
import com.control.widget.datepicker.wheelview.WheelView;
import com.control.widget.datepicker.wheelview.adapter.NumericWheelAdapter;

import java.util.Calendar;

public class JiuyiTimePick extends JiuyiDatePickBase {
	private WheelView hour;
	private WheelView min;

	int mHour;
	int mMin;
	

	public JiuyiTimePick(Context context, int tztskintype) {
		super(context, tztskintype);

		//设置时间
        Calendar m_Curdate = Calendar.getInstance();

        mHour = m_Curdate.get(Calendar.HOUR_OF_DAY) - 1;
        mMin = m_Curdate.get(Calendar.MINUTE) - 1;
	}
	
	public JiuyiTimePick(Context context, int tztskintype, int mHour, int mMin) {
		super(context, tztskintype);

		this.mHour = mHour - 1;
		this.mMin = mMin - 1;
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
				int[] value = new int[]{hour.getCurrentItem()+1, min.getCurrentItem()+1};
				OnWheelScrollFinishedData(value);
			}
		};
	}
	
	public View initView() {
		
		initResources();

		view = LayoutInflater.from(context).inflate(getLayoutID(context, "jiuyi_datepick_wheel_time_picker"), null);


		// time= (WheelView) view.findViewById(R.id.time);
		// String[] times = {"上午","下午"} ;
		// ArrayWheelAdapter<String> arrayWheelAdapter=new
		// ArrayWheelAdapter<String>(MainActivity.context,times );
		// time.setViewAdapter(arrayWheelAdapter);
		// time.setCyclic(false);
		// time.addScrollingListener(scrollListener);

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


		// time.setVisibleItems(DEFAULT_LISTVIEW_SIZE);
		hour.setVisibleItems(DEFAULT_LISTVIEW_SIZE);
		min.setVisibleItems(DEFAULT_LISTVIEW_SIZE);

		hour.setCurrentItem(mHour);
		min.setCurrentItem(mMin);
		return view;
	}

	@Override
	public void OnWheelScrollFinishedData(int[] value) {
		// TODO Auto-generated method stub
		
	}


}
