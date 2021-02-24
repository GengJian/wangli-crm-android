package com.control.widget.spinner;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.canvas.CRect;
import com.control.widget.JiuyiButton;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * 文件名称 : JiuyiSpinnerSelPopwindow.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述 : 界面的菜单等弹出框，选择、底部按钮弹框等
 *****************************************************************
 */
public class JiuyiSpinnerSelPopwindow {
	PopupWindow m_pPopupWindow;
	SpinnerPopupWindowCallback pCallBack;
	OnDismissListener mOnDismissListener;

	List<String> pMenuList;
	
	public JiuyiSpinnerSelPopwindow(Activity content){
	}

	public void setCallback(SpinnerPopupWindowCallback callback){
		pCallBack = callback;
	}

	public void setOnDismissListener(OnDismissListener mOnDismissListener){
		this.mOnDismissListener = mOnDismissListener;
	}
	
	public void dismiss(){
		if(m_pPopupWindow != null){
			m_pPopupWindow.dismiss();
			m_pPopupWindow = null;
		}
	}
	
	public void startPopwindow(Activity content, CRect defRect, String strButtons){
		String[][] pMoreButton = Func.SplitStr2Array(strButtons);
		startPopwindow(content, defRect, pMoreButton);
	}
	
	public void startPopwindow(Activity content, CRect defRect, String[][] Items){
		LinearLayout vPop = menuPopListview(content, defRect, Items);
		m_pPopupWindow = new PopupWindow(vPop, defRect.Width(), defRect.Height(), true);
		m_pPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		m_pPopupWindow.showAtLocation(vPop, Gravity.LEFT | Gravity.TOP, defRect.left, defRect.top);
		m_pPopupWindow.setOutsideTouchable(true);
		m_pPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				m_pPopupWindow = null;
			}
		});
	}
	
	/**
	 * 此方法支持屏幕变暗并且支持弹框是否带有箭头
	 * @param defRect
	 * @param Items
	 * @param nSanJiaoFlag 处理三角：-1不显示，1上方，2下方（箭头显示都居中）
	 */
	public void startPopwindow(Activity content, CRect defRect, String[][] Items, int nSanJiaoFlag){
        int width1 = Res.getWidthPixels();
        int height1 = Res.getHeightPixels();
    	CRect mainRect=new CRect(0, 0, width1, height1);
    	if(defRect.bottom>=height1)
    		defRect.bottom=height1- Res.Dip2Pix(10);
		Configuration mConfiguration = content.getResources().getConfiguration(); //获取设置的配置信息
		if((mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE)){
			//横屏反向
			mainRect=new CRect(0, 0, height1, width1);
		}
		//窗口显示
		LinearLayout vPop = menuPopSanJiaoListview(content, defRect, Items, nSanJiaoFlag, "");
		RelativeLayout.LayoutParams pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height()+Res.Dip2Pix(12));
		if(nSanJiaoFlag==2) {
			if (android.os.Build.VERSION.SDK_INT < 23) {
				//6.0以下系统
				pLayoutParams.setMargins(defRect.left, defRect.top - Res.Dip2Pix(12), 0, 0);
			} else {
				pLayoutParams.setMargins(defRect.left, defRect.top - Res.Dip2Pix(12) - Rc.getIns().getTopStatusBarHeight(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()), 0, 0);
			}
		}else {
			if (android.os.Build.VERSION.SDK_INT < 23) {
				//6.0以下系统
				pLayoutParams.setMargins(defRect.left, defRect.top, 0, 0);
			} else {
				pLayoutParams.setMargins(defRect.left, defRect.top - Rc.getIns().getTopStatusBarHeight(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()), 0, 0);
			}
		}
		vPop.setLayoutParams(pLayoutParams);
		
		//黑色半透明背景
		LinearLayout vPopAlphaBg=new LinearLayout(content);
		vPopAlphaBg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		vPopAlphaBg.setBackgroundColor(Color.BLACK);
		vPopAlphaBg.setAlpha(0);
		vPopAlphaBg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				m_pPopupWindow.dismiss();
			}
		});
		
		RelativeLayout pRelativeLayout=new RelativeLayout(content);
		pRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(mainRect.Width(),mainRect.Height()));
		pRelativeLayout.addView(vPopAlphaBg);
		pRelativeLayout.addView(vPop);
		
		m_pPopupWindow = new PopupWindow(pRelativeLayout, mainRect.Width(),mainRect.Height(), true);
		m_pPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		m_pPopupWindow.showAtLocation(pRelativeLayout, Gravity.LEFT | Gravity.TOP, 0, mainRect.top);
		m_pPopupWindow.setOutsideTouchable(true);
//		backgroundAlpha(0.8f,pCallBack);
		m_pPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				m_pPopupWindow = null;
			}
		});
	}
	
	/**
	 * 此方法支持屏幕变暗并且支持弹框是否带有箭头
	 * @param defRect
	 * @param Items
	 * @param nSanJiaoFlag 处理三角：-1不显示，1上方，2下方（箭头显示都居中）
	 */
	public void startPopwindow(Activity content, CRect defRect, String[][] Items,int nSanJiaoFlag, String lastSelectValue){
        int width1 = Res.getWidthPixels();
        int height1 = Res.getHeightPixels();
    	CRect mainRect=new CRect(0, 0, width1, height1);
    	if(defRect.bottom>=height1)
    		defRect.bottom=height1-Res.Dip2Pix(10);
		Configuration mConfiguration = content.getResources().getConfiguration(); //获取设置的配置信息
		if((mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE)){
			//横屏反向
			mainRect=new CRect(0, 0, height1, width1);
		}
		//窗口显示
		LinearLayout vPop = menuPopSanJiaoListview(content, defRect, Items, nSanJiaoFlag, lastSelectValue);
		RelativeLayout.LayoutParams pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height()+Res.Dip2Pix(12));
		if(nSanJiaoFlag==2) {
			if (android.os.Build.VERSION.SDK_INT < 23) {
				//6.0以下系统
				pLayoutParams.setMargins(defRect.left, defRect.top - Res.Dip2Pix(12), 0, 0);
			} else {
				pLayoutParams.setMargins(defRect.left, defRect.top - Res.Dip2Pix(12) - Rc.getIns().getTopStatusBarHeight(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()), 0, 0);
			}
		}else {
			if(android.os.Build.VERSION.SDK_INT<23) {
				//6.0以下系统
				pLayoutParams.setMargins(defRect.left, defRect.top, 0, 0);
			}else{
				pLayoutParams.setMargins(defRect.left, defRect.top - Rc.getIns().getTopStatusBarHeight(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()), 0, 0);
			}
		}
		vPop.setLayoutParams(pLayoutParams);
		
		//黑色半透明背景
		LinearLayout vPopAlphaBg=new LinearLayout(content);
		vPopAlphaBg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		vPopAlphaBg.setBackgroundColor(Color.BLACK);
		vPopAlphaBg.setAlpha(0);
		vPopAlphaBg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				m_pPopupWindow.dismiss();
			}
		});
		
		RelativeLayout pRelativeLayout=new RelativeLayout(content);
		pRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(mainRect.Width(),mainRect.Height()));
		pRelativeLayout.addView(vPopAlphaBg);
		pRelativeLayout.addView(vPop);
		
		m_pPopupWindow = new PopupWindow(pRelativeLayout, mainRect.Width(),mainRect.Height(), true);
		m_pPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		m_pPopupWindow.showAtLocation(pRelativeLayout, Gravity.LEFT | Gravity.TOP, 0, mainRect.top);
		m_pPopupWindow.setOutsideTouchable(true);
//		backgroundAlpha(0.8f,pCallBack);
		m_pPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				m_pPopupWindow = null;
			}
		});
	}
	
	/**
	 * 此方法支持屏幕变暗并且支持弹框是否带有箭头
	 * @param defRect
	 * @param Items
	 * @param nSanJiaoFlag 处理三角：-1不显示，1上方，2下方（箭头显示都居中），3左方，4右方
	 * @Param nSanjiaoCenterPostion nSanJiaoFlag=1或2时， 三角的水平位置;nSanJiaoFlag=3或4时， 三角的中心至弹窗顶的距离,
	 */
    
	public void startPopwindow(Activity content, CRect defRect, String[][] Items,int nSanJiaoFlag, String lastSelectValue,int nSanjiaoCenterPostion){
    	int width1 = Res.getWidthPixels();
    	int height1 = Res.getHeightPixels();
    	CRect mainRect=new CRect(0, 0, width1, height1);
		Configuration mConfiguration = content.getResources().getConfiguration(); //获取设置的配置信息
		if((mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE)){
			//横屏反向
			mainRect=new CRect(0, 0, height1, width1);
		}
    	//（判断是否到手机屏幕底部）
    	if(defRect.bottom>=height1)
    		defRect.bottom=height1-Res.Dip2Pix(10);
		//窗口显示
    	int ncenter = 0;
    	if(nSanJiaoFlag == 1 || nSanJiaoFlag == 2){
    		ncenter=nSanjiaoCenterPostion-defRect.left-Res.Dip2Pix(12);
    	}else if(nSanJiaoFlag == 3 || nSanJiaoFlag == 4){
    		ncenter=nSanjiaoCenterPostion -Res.Dip2Pix(12);//-defRect.left-Res.Dip2Pix(12);
    	}
		LinearLayout vPop = menuPopSanJiaoListview(content, defRect, Items, nSanJiaoFlag, lastSelectValue,ncenter);
		
		// 支持左右显示箭头
		RelativeLayout.LayoutParams pLayoutParams;//=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height()+Res.Dip2Pix(12));
		if (nSanJiaoFlag == 3 || nSanJiaoFlag == 4) {//左右显示箭头
			pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width()+Res.Dip2Pix(12), defRect.Height());
			if(android.os.Build.VERSION.SDK_INT<23){
				//6.0以下系统
				pLayoutParams.setMargins(defRect.left, defRect.top, 0, 0);
			}else{
				pLayoutParams.setMargins(defRect.left, defRect.top - Rc.getIns().getTopStatusBarHeight(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()), 0, 0);
			}
		}else if(nSanJiaoFlag==2){
			pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height()+Res.Dip2Pix(12));
			if(android.os.Build.VERSION.SDK_INT<23){
				//6.0以下系统
				pLayoutParams.setMargins(defRect.left, defRect.top - Res.Dip2Pix(12), 0, 0);
			}else{
				pLayoutParams.setMargins(defRect.left, defRect.top - Res.Dip2Pix(12) - Rc.getIns().getTopStatusBarHeight(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()), 0, 0);
			}
		}else{
			pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height()+Res.Dip2Pix(12));
			if(android.os.Build.VERSION.SDK_INT<23){
				//6.0以下系统
				pLayoutParams.setMargins(defRect.left, defRect.top, 0, 0);
			}else{
				pLayoutParams.setMargins(defRect.left, defRect.top - Rc.getIns().getTopStatusBarHeight(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()), 0, 0);
			}
		}
		
		
		vPop.setLayoutParams(pLayoutParams);
		
		//黑色半透明背景
		LinearLayout vPopAlphaBg=new LinearLayout(content);
		vPopAlphaBg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		vPopAlphaBg.setBackgroundColor(Color.BLACK);
		vPopAlphaBg.setAlpha(0);
		vPopAlphaBg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				m_pPopupWindow.dismiss();
			}
		});
		
		RelativeLayout pRelativeLayout=new RelativeLayout(content);
		pRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(mainRect.Width(),mainRect.Height()));
		pRelativeLayout.addView(vPopAlphaBg);
		pRelativeLayout.addView(vPop);
		
		m_pPopupWindow = new PopupWindow(pRelativeLayout, mainRect.Width(),mainRect.Height(), true);
		m_pPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		m_pPopupWindow.showAtLocation(pRelativeLayout, Gravity.LEFT | Gravity.TOP, 0, mainRect.top);
		m_pPopupWindow.setOutsideTouchable(true);
//		backgroundAlpha(0.8f,pCallBack);
		m_pPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				m_pPopupWindow = null;
			}
		});
	}

	/**
	 * 悬浮框内容有外层控制
	 * @param defRect
	 * @param vPop
	 */
	public void startPopwindow(Activity content, CRect defRect, LinearLayout vPop){
        int width1 = Res.getWidthPixels();
        int height1 = Res.getHeightPixels();
    	CRect mainRect=new CRect(0, 0, width1, height1);
		Configuration mConfiguration = content.getResources().getConfiguration(); //获取设置的配置信息
		if((mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE)){
			//横屏反向
			mainRect=new CRect(0, 0, height1, width1);
		}
    	
		RelativeLayout.LayoutParams pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height());
		pLayoutParams.setMargins(defRect.left, defRect.top, 0, 0);
		vPop.setLayoutParams(pLayoutParams);
		
		//黑色半透明背景
		LinearLayout vPopAlphaBg=new LinearLayout(content);
		vPopAlphaBg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		vPopAlphaBg.setBackgroundColor(Color.BLACK);
		vPopAlphaBg.setAlpha(0.5f);
		vPopAlphaBg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				m_pPopupWindow.dismiss();
			}
		});
		
		RelativeLayout pRelativeLayout=new RelativeLayout(content);
		pRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(mainRect.Width(),mainRect.Height()));
		pRelativeLayout.addView(vPopAlphaBg);
		pRelativeLayout.addView(vPop);
				
		m_pPopupWindow = new PopupWindow(pRelativeLayout, mainRect.Width(), mainRect.Height(), true);
		m_pPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		m_pPopupWindow.showAtLocation(vPop, Gravity.LEFT | Gravity.TOP, 0, 0);
		m_pPopupWindow.setOutsideTouchable(true);
		m_pPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				m_pPopupWindow = null;
			}
		});
	}
	
	/**
	 * 悬浮框内容有外层控制
	 * @param content
	 * @param defRect
	 * @param vPop
	 */
	public void startPopwindow(Activity content, CRect defRect, LinearLayout vPop,CRect mainRect,boolean bOutsideTouchable){
		RelativeLayout.LayoutParams pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height());
		pLayoutParams.setMargins(defRect.left, defRect.top, 0, 0);
		vPop.setLayoutParams(pLayoutParams);
		
		//黑色半透明背景
		LinearLayout vPopAlphaBg=new LinearLayout(content);
		vPopAlphaBg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		vPopAlphaBg.setBackgroundColor(Color.BLACK);
		vPopAlphaBg.setAlpha(0.5f);
		if(bOutsideTouchable){
			vPopAlphaBg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					m_pPopupWindow.dismiss();
				}
			});
		}
		
		RelativeLayout pRelativeLayout=new RelativeLayout(content);
		pRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(mainRect.Width(),mainRect.Height()));
		pRelativeLayout.addView(vPopAlphaBg);
		pRelativeLayout.addView(vPop);
				
		m_pPopupWindow = new PopupWindow(pRelativeLayout, mainRect.Width(), mainRect.Height(), true);
		m_pPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		m_pPopupWindow.showAtLocation(vPop, Gravity.LEFT | Gravity.TOP, 0, 0);
		if(bOutsideTouchable){
			m_pPopupWindow.setOutsideTouchable(true);
		}
		m_pPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				m_pPopupWindow = null;
				if(pCallBack!=null){
					pCallBack.onItemClick(0, null, 0);
				}
			}
		});
	}
	
	/**
	 * 悬浮框内容有外层控制
	 * @param defRect
	 * @param vPop
	 */
	public void startPopwindow(Activity content, CRect defRect, LinearLayout vPop, boolean bCanOutsideDismiss){
        int width1 = Res.getWidthPixels();
        int height1 = Res.getHeightPixels();
    	CRect mainRect=new CRect(0, 0, width1, height1);
		Configuration mConfiguration = content.getResources().getConfiguration(); //获取设置的配置信息
		if((mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE)){
			//横屏反向
			mainRect=new CRect(0, 0, height1, width1);
		}
    	
		RelativeLayout.LayoutParams pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height());
		pLayoutParams.setMargins(defRect.left, defRect.top, 0, 0);
		vPop.setLayoutParams(pLayoutParams);
		
		//黑色半透明背景
		LinearLayout vPopAlphaBg=new LinearLayout(content);
		vPopAlphaBg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		vPopAlphaBg.setBackgroundColor(Color.BLACK);
		vPopAlphaBg.setAlpha(0.5f);
		if(bCanOutsideDismiss){
			vPopAlphaBg.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					m_pPopupWindow.dismiss();
				}
			});
		}
		
		RelativeLayout pRelativeLayout=new RelativeLayout(content);
		pRelativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(mainRect.Width(),mainRect.Height()));
		pRelativeLayout.addView(vPopAlphaBg);
		pRelativeLayout.addView(vPop);
				
		m_pPopupWindow = new PopupWindow(pRelativeLayout, mainRect.Width(), mainRect.Height(), true);
		m_pPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		m_pPopupWindow.showAtLocation(vPop, Gravity.LEFT | Gravity.TOP, 0, 0);
		if(bCanOutsideDismiss)
			m_pPopupWindow.setOutsideTouchable(true);
		m_pPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				m_pPopupWindow = null;
			}
		});
	}

	/**
	 * 长按弹窗
	 * @param defRect
	 * @param vPop
	 */
	public void startPopwindow2(Activity content, CRect defRect, LinearLayout vPop){
		m_pPopupWindow = new PopupWindow(vPop, defRect.Width(), defRect.Height(), true);
		m_pPopupWindow.setBackgroundDrawable(new ColorDrawable());
		m_pPopupWindow.showAtLocation(vPop, Gravity.TOP, defRect.left, defRect.top);
		m_pPopupWindow.setOutsideTouchable(true);
		m_pPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				m_pPopupWindow = null;
				if(mOnDismissListener != null)
					mOnDismissListener.onDismiss();
			}
		});
	}

    /**
     * TopBar按钮配置显示
     * @param content
     * @param defRect
     * @param pMenuButton
     * @return
     */
	private LinearLayout menuPopListview(final Activity content, CRect defRect, final String[][] pMenuButton)//LinearLayout MoreTechCycleLayout
	{
		if (pMenuButton == null || pMenuButton.length <= 0)
		{
			return null;
		}
		
		//listview
		LinearLayout pMenuLayout = (LinearLayout) LayoutInflater.from(content).inflate(Res.getLayoutID(content, "tzt_trendmenu_listview"), null);
		pMenuLayout.setLayoutParams(new LayoutParams(defRect.Width(), defRect.Height()));
		//
		pMenuList = new ArrayList<String>();
		for (int i = 0; i < pMenuButton.length; i++) {
			pMenuList.add(pMenuButton[i][0]);
		}
		
		//
		final int lineheight = ((defRect.Height()) - Res.Dip2Pix(10)) / pMenuList.size();
		BaseAdapter simpAdapter = new BaseAdapter() {
			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				convertView = (LinearLayout) LayoutInflater.from(content).inflate(Res.getLayoutID(content, "tzt_trendmenu_listitem"), null);

				TextView acctext = (TextView) convertView.findViewById(Res.getViewID(content, "tzt_trendmenu_item"));
				acctext.setText(pMenuList.get(position));
				
				convertView.setMinimumHeight(lineheight);
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return pMenuList.get(position);
			}

			@Override
			public int getCount() {
				return pMenuList.size();
			}
		};
		
		ListView listview = (ListView) pMenuLayout.findViewById(Res.getViewID(content, "tzt_trendmenu_listView"));
		listview.setAdapter(simpAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if(pCallBack != null){
					int nAction = Func.parseInt(pMenuButton[arg2][1]);
					pCallBack.onItemClick(nAction, pMenuButton, arg2);
				}
				
				dismiss();
			}
		});
		simpAdapter.notifyDataSetChanged();

		return pMenuLayout;
	}
	
	/**
	 * popwindows 支持是否显示三角
	 * @param content
	 * @param defRect
	 * @param pMenuButton
	 * @param nSanJiaoFlag 处理三角：-1不显示，1上方，2下方（箭头显示都居中）
	 * @param lastSelectValue 最后一次选择内容(显示选中标签)
	 * @return
	 */
	private LinearLayout menuPopSanJiaoListview(final Activity content, CRect defRect, final String[][] pMenuButton,int nSanJiaoFlag,final String lastSelectValue)//LinearLayout MoreTechCycleLayout
	{
		if (pMenuButton == null || pMenuButton.length <= 0)
		{
			return null;
		}
		

		ImageView pImageView=new ImageView(content);
		LayoutParams pLayoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		pLayoutParams.gravity=Gravity.CENTER;
		pImageView.setScaleType(ScaleType.CENTER);
		pImageView.setLayoutParams(pLayoutParams);
		if(nSanJiaoFlag==1)
			pImageView.setImageResource(Res.getDrawabelID(content, "tzt_trendmenysanjiaoup"));
		else if(nSanJiaoFlag==2)
			pImageView.setImageResource(Res.getDrawabelID(content, "tzt_trendmenysanjiaodown"));

		
		LinearLayout pLinearLayout=new LinearLayout(content);
		LayoutParams pLayoutParamsex;
		if(nSanJiaoFlag!=-1)
			pLayoutParamsex=new LayoutParams(defRect.Width(), defRect.Height()+Res.Dip2Pix(12));
		else
			 pLayoutParamsex=new LayoutParams(defRect.Width(), defRect.Height());
		pLayoutParams.gravity=Gravity.CENTER;
		pLinearLayout.setLayoutParams(pLayoutParamsex);
		pLinearLayout.setOrientation(LinearLayout.VERTICAL);
		
		//listview
		LinearLayout pMenuLayout = (LinearLayout) LayoutInflater.from(content).inflate(Res.getLayoutID(content, "tzt_popwindow_listview"), null);
		pMenuLayout.setLayoutParams(new LayoutParams(defRect.Width(), defRect.Height()));
		//
		pMenuList = new ArrayList<String>();
		for (int i = 0; i < pMenuButton.length; i++) {
			pMenuList.add(pMenuButton[i][0]);
		}
		
		final int lineheight =  Res.Dip2Pix(40);//Rc.getTitleHeight();//((defRect.Height()) - Res.Dip2Pix(10)) / pMenuList.size();
		BaseAdapter simpAdapter = new BaseAdapter() {
			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				convertView = (LinearLayout) LayoutInflater.from(content).inflate(Res.getLayoutID(content, "tzt_popwindow_listitem"), null);

				TextView acctext = (TextView) convertView.findViewById(Res.getViewID(content, "tzt_trendmenu_item"));
				acctext.setText(pMenuList.get(position));
				
				if(lastSelectValue.equals(pMenuButton[position][1]) && !Func.IsStringEmpty(lastSelectValue)){
					acctext.setTextColor(Res.getColor(content, "tzt_buttonbar_trend_select_color"));
				}
				
				convertView.setMinimumHeight(lineheight);
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return pMenuList.get(position);
			}

			@Override
			public int getCount() {
				return pMenuList.size();
			}
		};
		
		ListView listview = (ListView) pMenuLayout.findViewById(Res.getViewID(content, "tzt_trendmenu_listView"));
		listview.setAdapter(simpAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if(pCallBack != null){
					int nAction = Func.parseInt(pMenuButton[arg2][1]);
					pCallBack.onItemClick(nAction, pMenuButton, arg2);
				}
				
				dismiss();
			}
		});
		simpAdapter.notifyDataSetChanged();

		 if(nSanJiaoFlag==1){
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(0, Res.Dip2Pix(15)/2, 0, 0);
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			
			LayoutParams pLayoutParamsNewEx = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			pLayoutParamsNewEx.setMargins(0, - defRect.Height()-Res.Dip2Pix(15)/2+2, 0, 0);
			pLayoutParamsNewEx.gravity=Gravity.CENTER;
			pImageView.setLayoutParams(pLayoutParamsNewEx);
			pLinearLayout.addView(pImageView);
		}else if(nSanJiaoFlag==2){
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(0, 0, 0, -Res.Dip2Pix(1));//update by zhengss 边框加粗后修改margin
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			pLinearLayout.addView(pImageView);
		}else
		{
			pLinearLayout.addView(pMenuLayout);
		}

		return pLinearLayout;
	}
	
	private LinearLayout menuPopSanJiaoListview(final Activity content, CRect defRect, final String[][] pMenuButton,int nSanJiaoFlag,final String lastSelectValue,int nSanjiaoCenterpostion)//LinearLayout MoreTechCycleLayout
	{
		if (pMenuButton == null || pMenuButton.length <= 0)
		{
			return null;
		}
		

		ImageView pImageView=new ImageView(content);
		LayoutParams pLayoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		pImageView.setScaleType(ScaleType.CENTER);
		pImageView.setLayoutParams(pLayoutParams);
		if(nSanJiaoFlag==1)
			pImageView.setImageResource(Res.getDrawabelID(content, "tzt_trendmenysanjiaoup"));
		else if(nSanJiaoFlag==2)
			pImageView.setImageResource(Res.getDrawabelID(content, "tzt_trendmenysanjiaodown"));
		else if(nSanJiaoFlag==3)//update by zhengss 支持左右显示箭头
			pImageView.setImageResource(Res.getDrawabelID(content, "tzt_trendmenysanjiaoleft"));
		else if(nSanJiaoFlag==4)
			pImageView.setImageResource(Res.getDrawabelID(content, "tzt_trendmenysanjiaoright"));
		
		
		LinearLayout pLinearLayout=new LinearLayout(content);
		LayoutParams pLayoutParamsex;
		
		if (nSanJiaoFlag == 0 || nSanJiaoFlag == 1 || nSanJiaoFlag == 2) {//上下显示箭头
			pLayoutParamsex = new LayoutParams(defRect.Width(), defRect.Height() + Res.Dip2Pix(12));
			pLinearLayout.setOrientation(LinearLayout.VERTICAL);
		} else if (nSanJiaoFlag == 3 || nSanJiaoFlag == 4) {//左右显示箭头
			pLayoutParamsex = new LayoutParams(defRect.Width() + Res.Dip2Pix(12), defRect.Height());
			pLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		} else {
			pLayoutParamsex = new LayoutParams(defRect.Width(), defRect.Height());
			pLinearLayout.setOrientation(LinearLayout.VERTICAL);
		}		
		pLinearLayout.setLayoutParams(pLayoutParamsex);
		
		//listview
		LinearLayout pMenuLayout = (LinearLayout) LayoutInflater.from(content).inflate(Res.getLayoutID(content, "tzt_popwindow_listview"), null);
		pMenuLayout.setLayoutParams(new LayoutParams(defRect.Width(), defRect.Height()));
		//
		pMenuList = new ArrayList<String>();
		for (int i = 0; i < pMenuButton.length; i++) {
			pMenuList.add(pMenuButton[i][0]);
		}
		
		//修改item高度
		final int lineheight = Res.Dip2Pix(40);
		//final int lineheight = Rc.getTitleHeight();//((defRect.Height()) - Res.Dip2Pix(10)) / pMenuList.size();
		BaseAdapter simpAdapter = new BaseAdapter() {
			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				convertView = (LinearLayout) LayoutInflater.from(content).inflate(Res.getLayoutID(content, "tzt_popwindow_listitem"), null);

				TextView acctext = (TextView) convertView.findViewById(Res.getViewID(content, "tzt_trendmenu_item"));
				acctext.setText(pMenuList.get(position));
				
				if(lastSelectValue.equals(pMenuButton[position][1]) && !Func.IsStringEmpty(lastSelectValue)){
					acctext.setTextColor(Res.getColor(content, "tzt_buttonbar_trend_select_color"));
				}
				
				convertView.setMinimumHeight(lineheight);
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return pMenuList.get(position);
			}

			@Override
			public int getCount() {
				return pMenuList.size();
			}
		};
		
		ListView listview = (ListView) pMenuLayout.findViewById(Res.getViewID(content, "tzt_trendmenu_listView"));
		listview.setAdapter(simpAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if(pCallBack != null){
					int nAction = Func.parseInt(pMenuButton[arg2][1]);
					pCallBack.onItemClick(nAction, pMenuButton, arg2);
				}
				
				dismiss();
			}
		});
		simpAdapter.notifyDataSetChanged();

		 if(nSanJiaoFlag==1){
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(0, Res.Dip2Pix(15)/2, 0, 0);
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			
			LayoutParams pLayoutParamsNewEx = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			pLayoutParamsNewEx.setMargins(nSanjiaoCenterpostion, - defRect.Height()-Res.Dip2Pix(15)/2+3, 0, 0);
			pLayoutParamsNewEx.gravity=Gravity.LEFT;
			pImageView.setLayoutParams(pLayoutParamsNewEx);
			pLinearLayout.addView(pImageView);
		}else if(nSanJiaoFlag==2){
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(0, 0, 0, -Res.Dip2Pix(2)+2);
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			// 修改向下箭头位置
			LayoutParams pLayoutParamsNewEx = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			pLayoutParamsNewEx.setMargins(nSanjiaoCenterpostion, 0, 0, 0);
			pLayoutParamsNewEx.gravity=Gravity.LEFT;
			pImageView.setLayoutParams(pLayoutParamsNewEx);
			pLinearLayout.addView(pImageView);
		}else if(nSanJiaoFlag==3){// 支持左右的三角
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(Res.Dip2Pix(12), 0, 0, 0);
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			
			LayoutParams pLayoutParamsNewEx = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			pLayoutParamsNewEx.setMargins(- defRect.Width()-Res.Dip2Pix(15)/2+3, nSanjiaoCenterpostion, 0, 0);
			pLayoutParamsNewEx.gravity=Gravity.TOP;
			pImageView.setLayoutParams(pLayoutParamsNewEx);
			pLinearLayout.addView(pImageView);
		}else if(nSanJiaoFlag==4){
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(0, 0, 0, 0);
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			
			LayoutParams pLayoutParamsNewEx = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			pLayoutParamsNewEx.setMargins(-Res.Dip2Pix(1), nSanjiaoCenterpostion, 0, 0);
			pLayoutParamsNewEx.gravity=Gravity.TOP;
			pImageView.setLayoutParams(pLayoutParamsNewEx);
			pLinearLayout.addView(pImageView);
		}else
		{
			pLinearLayout.addView(pMenuLayout);
		}

		return pLinearLayout;
	}


    /**
     * TopBar按钮配置显示
     * @param content
     * @param defRect
     * @param pMoreButton
     * @return
     */
	private LinearLayout MoreTechCyclePopMenu(Activity content, CRect defRect, final String[][] pMoreButton)//LinearLayout MoreTechCycleLayout
	{
		int margin =  Rc.getIns().getTitleHeight() / 5;
		int lineheight = Rc.getIns().getLineHeight();
		
		LinearLayout MoreTechCycleLayout = new LinearLayout(content);
		LayoutParams lp2 = new LayoutParams(defRect.Width(), defRect.Height());
		lp2.topMargin = margin;
		lp2.bottomMargin = margin;
		MoreTechCycleLayout.setLayoutParams(lp2);
		MoreTechCycleLayout.setOrientation(LinearLayout.VERTICAL);

		if (pMoreButton == null || pMoreButton.length <= 0 || MoreTechCycleLayout == null)
		{
			return null;
		}
		
		MoreTechCycleLayout.setBackgroundResource(Res.getDrawabelID(content, "tzt_gjsc_searchstock_shape"));
		MoreTechCycleLayout.removeAllViews();
		
		for (int i = 0 ; i < pMoreButton.length; i ++)
		{
			String[] pData = pMoreButton[i];
			if (pData == null || pData.length < 2)
			{
				continue;
			}
			
			int nPageType = Func.parseInt(pData[1]);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, lineheight-1);
			lp.weight = 1;
			
			int nMargin = Math.max(1, Res.Dip2Pix(1));
			lp.topMargin = nMargin;
			if(i == 0)
			{
				lp.topMargin = nMargin;
			}
			else if (i == pMoreButton.length - 1)
			{
				lp.bottomMargin = nMargin;
			}
			else
			{
				lp.topMargin = nMargin;
			}
			lp.leftMargin = nMargin;
			lp.rightMargin = nMargin;
			lp.gravity = Gravity.CENTER;
			final Button button = newButton(content, pData[0], LayoutParams.MATCH_PARENT, 0);
			button.setLayoutParams(lp);
			button.setBackgroundDrawable(null);
			button.setTag(nPageType);
			button.setId(i);
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(pCallBack != null){
						int nAction = Func.parseInt(v.getTag().toString());
						int nSelIndex = button.getId();
						pCallBack.onItemClick(nAction, pMoreButton, nSelIndex);
					}
				}
			});
			MoreTechCycleLayout.addView(button);
		}
		return MoreTechCycleLayout;
	}
	
	public JiuyiButton newButton(Context context, String text, int nWidth, int nXStart) {
		JiuyiButton m_btnConfim = new JiuyiButton(context);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, Res.Dip2Pix(Rc.getIns().getLineHeight()));
		lp.setMargins(Res.Dip2Pix(nXStart), 0, 0, 0);
		m_btnConfim.setLayoutParams(lp);
		m_btnConfim.setPadding(0, 0, 0, 0);
		m_btnConfim.setGravity(Gravity.CENTER);
		m_btnConfim.setTextSize(Rc.getIns().getCanvasMainFont());

		m_btnConfim.setText(text);
		return m_btnConfim;
	}

    /**
     * 按钮的callback
     * @author zhengss
     */
    public interface SpinnerPopupWindowCallback {

        public void onItemClick(int nAction, String[][] Items, int nSelindex);

    }
    
    public void setOutsideTouchable(boolean bValue){
    	if(m_pPopupWindow!=null)
    		m_pPopupWindow.setOutsideTouchable(bValue);
    }
}
