package com.control.widget.datepicker;

import android.content.Context;
import android.view.View;

import com.control.widget.datepicker.wheelview.OnWheelScrollListener;

public abstract class JiuyiDatePickBase {
	public static final int DATEFORMAT_DateToYestoday 	= 1;//日期控件限制，只能选择到昨天
	public static final int DATEFORMAT_DateToToday 		= 0;//日期控件限制，只能选择到今天
	public static final int DATEFORMAT_DateFuture 		= 2;//日期控件无限制
	public int mDateToType = DATEFORMAT_DateToToday;//日期控件限制类型
	
	public OnWheelScrollListener scrollListener;
	public abstract void OnWheelScrollFinishedData(int[] value);
	
	public final int DEFAULT_LISTVIEW_SIZE = 7;//记录默认显示几条
	public final int MIN_CYCLIC_SIZE = DEFAULT_LISTVIEW_SIZE / 2 + DEFAULT_LISTVIEW_SIZE % 2;//setCyclic为true最小数数
	public int YEAR_START 	= 2000;
	public int YEAR_END 	= 2100;
	public int MONTH_START 	= 1;
	public int MONTH_END 	= 12;
	public int DAY_START 	= 1;
	public int DAY_END 		= 31;
	
	public Context context;
	public int tztskintype = 0;

	public View view = null;
	public int wheelBackground;
	public int wheelForeground;
	public int centerRectColor;

	public JiuyiDatePickBase(Context context, int tztskintype) {
		this.context = context;
		this.tztskintype = tztskintype;
	}
	
	public void initResources(){
		wheelBackground = getDrawabelID(context, "jiuyi_datepick_wheel_bg");
		wheelForeground = getDrawabelID(context, "jiuyi_datepick_wheel_val");
		centerRectColor = getColor(context, "tzt_datepick_wheel_line_bordercolor");
	}

	public void setDateToType(int mDateToType){
		this.mDateToType = mDateToType;
	}

	public int getLayoutID(Context context, String name) {
		int id = context.getResources().getIdentifier(getResourcesNameBySkinType(name), "layout", context.getPackageName());
		if (id <= 0)
			id = context.getResources().getIdentifier(name, "layout", context.getPackageName());
		return id;

	}
	
	public int getViewID(Context context, String name) {
		int id = context.getResources().getIdentifier(getResourcesNameBySkinType(name), "id", context.getPackageName());
		if(id <= 0)
			id = context.getResources().getIdentifier(name, "id", context.getPackageName());
		return id;
	}
	
	public int getDrawabelID(Context context, String name, int nForceSkinType) {
		int id = 0;
		if (nForceSkinType == -1) {
			id = context.getResources().getIdentifier(getResourcesNameBySkinType(name), "drawable", context.getPackageName());
			if (id <= 0)
				id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
		} else {
			id = context.getResources().getIdentifier(name + (nForceSkinType == 1 ? "_1" : ""), "drawable", context.getPackageName());
		}
		return id;
	}
	
	public int getDrawabelID(Context context, String name) {
		return getDrawabelID(context, name, -1);
	}
	
	public int getColor (Context context, String name, int nForceSkinType){
		int id = 0;
		if (nForceSkinType == -1) {
			id = context.getResources().getIdentifier(getResourcesNameBySkinType(name), "color", context.getPackageName());
			if (id <= 0)
				id = context.getResources().getIdentifier(name, "color", context.getPackageName());
		} else {
			id = context.getResources().getIdentifier(name + (nForceSkinType == 1 ? "_1" : ""), "color", context.getPackageName());
		}
		return context.getResources().getColor(id);
	}
	
	public int getColor(Context context, String name) {
		return getColor(context, name, -1);
	}

	public String getResourcesNameBySkinType(String name) {
		if (tztskintype <= 0)
			return name;
		else
			return name + "_" + tztskintype;
	}
}
