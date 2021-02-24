package com.iflytek.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * 文件名称 : IflytekPopwindow.java
 *****************************************************************
 */
public class IflytekPopwindow {
	PopupWindow m_pPopupWindow;
	IflytekSpinnerPopupWindowCallback pCallBack;

	List<String> pMenuList;
	
	public IflytekPopwindow(Context context){
	}

	public void setCallback(IflytekSpinnerPopupWindowCallback callback){
		pCallBack = callback;
	}
	
	public void dismiss(){
		if(m_pPopupWindow != null){
			m_pPopupWindow.dismiss();
			m_pPopupWindow = null;
		}
	}

	/**
	 * 此方法支持屏幕变暗并且支持弹框是否带有箭头
	 * @param defRect
	 * @param Items
	 * @param nSanJiaoFlag 处理三角：-1不显示，1上方，2下方（箭头显示都居中），3左方，4右方
	 * @Param nSanjiaoCenterPostion nSanJiaoFlag=1或2时， 三角的水平位置;nSanJiaoFlag=3或4时， 三角的中心至弹窗顶的距离,
	 */
	public void startPopwindow(Context content, IflytekCRect defRect, String[][] Items,int nSanJiaoFlag, String lastSelectValue,int nSanjiaoCenterPostion){
    	int width1 = IflytekRes.getWidthPixels();
    	int height1 = IflytekRes.getHeightPixels();
		IflytekCRect mainRect=new IflytekCRect(0, 0, width1, height1);
		Configuration mConfiguration = content.getResources().getConfiguration(); //获取设置的配置信息
		if((mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE)){
			//横屏反向
			mainRect=new IflytekCRect(0, 0, height1, width1);
		}
    	//update by zhengss20160804（判断是否到手机屏幕底部）
    	if(defRect.bottom>=height1)
    		defRect.bottom=height1-IflytekRes.Dip2Pix(10);
		//窗口显示
    	int ncenter = 0;
    	if(nSanJiaoFlag == 1 || nSanJiaoFlag == 2){
    		ncenter=nSanjiaoCenterPostion-defRect.left-IflytekRes.Dip2Pix(12);
    	}else if(nSanJiaoFlag == 3 || nSanJiaoFlag == 4){
    		ncenter=nSanjiaoCenterPostion -IflytekRes.Dip2Pix(12);//-defRect.left-Res.Dip2Pix(12);
    	}
		LinearLayout vPop = menuPopSanJiaoListview(content, defRect, Items, nSanJiaoFlag, lastSelectValue,ncenter);
		
		//update by zhengss 支持左右显示箭头
		RelativeLayout.LayoutParams pLayoutParams;//=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height()+Res.Dip2Pix(12));
		if (nSanJiaoFlag == 3 || nSanJiaoFlag == 4) {//左右显示箭头
			pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width()+IflytekRes.Dip2Pix(12), defRect.Height());
			pLayoutParams.setMargins(defRect.left, defRect.top, 0, 0);
		}else if(nSanJiaoFlag==2){
			pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height()+IflytekRes.Dip2Pix(12));
			pLayoutParams.setMargins(defRect.left, defRect.top-IflytekRes.Dip2Pix(12), 0, 0);
		}else{
			pLayoutParams=new RelativeLayout.LayoutParams(defRect.Width(), defRect.Height()+IflytekRes.Dip2Pix(12));
			pLayoutParams.setMargins(defRect.left, defRect.top, 0, 0);
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
	
	private LinearLayout menuPopSanJiaoListview(final Context content, IflytekCRect defRect, final String[][] pMenuButton,int nSanJiaoFlag,final String lastSelectValue,int nSanjiaoCenterpostion)//LinearLayout MoreTechCycleLayout
	{
		if (pMenuButton == null || pMenuButton.length <= 0)
		{
			return null;
		}
		

		ImageView pImageView=new ImageView(content);
		LayoutParams pLayoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		if(nSanJiaoFlag==3 || nSanJiaoFlag==4){//update by zhengss 支持左右显示箭头
//			pLayoutParams.gravity=Gravity.TOP;
//			pLayoutParams.setMargins(0, nSanjiaoCenterpostion, 0, 0);
//		}else{
//			pLayoutParams.gravity=Gravity.LEFT;
//			pLayoutParams.setMargins(nSanjiaoCenterpostion, 0, 0, 0);
//		}
		pImageView.setScaleType(ScaleType.CENTER);
		pImageView.setLayoutParams(pLayoutParams);
		if(nSanJiaoFlag==1)
			pImageView.setImageResource(IflytekRes.getDrawabelID(content, "tzt_trendmenysanjiaoup"));
		else if(nSanJiaoFlag==2)
			pImageView.setImageResource(IflytekRes.getDrawabelID(content, "tzt_trendmenysanjiaodown"));
		else if(nSanJiaoFlag==3)//update by zhengss 支持左右显示箭头
			pImageView.setImageResource(IflytekRes.getDrawabelID(content, "tzt_trendmenysanjiaoleft"));
		else if(nSanJiaoFlag==4)
			pImageView.setImageResource(IflytekRes.getDrawabelID(content, "tzt_trendmenysanjiaoright"));
		
		
		LinearLayout pLinearLayout=new LinearLayout(content);
		LayoutParams pLayoutParamsex;
		
		if (nSanJiaoFlag == 0 || nSanJiaoFlag == 1 || nSanJiaoFlag == 2) {//上下显示箭头
			pLayoutParamsex = new LayoutParams(defRect.Width(), defRect.Height() + IflytekRes.Dip2Pix(12));
			pLinearLayout.setOrientation(LinearLayout.VERTICAL);
		} else if (nSanJiaoFlag == 3 || nSanJiaoFlag == 4) {//左右显示箭头
			pLayoutParamsex = new LayoutParams(defRect.Width() + IflytekRes.Dip2Pix(12), defRect.Height());
			pLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
		} else {
			pLayoutParamsex = new LayoutParams(defRect.Width(), defRect.Height());
			pLinearLayout.setOrientation(LinearLayout.VERTICAL);
		}		
		pLinearLayout.setLayoutParams(pLayoutParamsex);
		
		//listview
		LinearLayout pMenuLayout = (LinearLayout) LayoutInflater.from(content).inflate(IflytekRes.getLayoutID(content, "tzt_popwindow_listview"), null);
		pMenuLayout.setLayoutParams(new LayoutParams(defRect.Width(), defRect.Height()));
		//
		pMenuList = new ArrayList<String>();
		for (int i = 0; i < pMenuButton.length; i++) {
			pMenuList.add(pMenuButton[i][0]);
		}
		
		//update by zhengss 修改item高度
		final int lineheight = IflytekRes.Dip2Pix(40);
		//final int lineheight = Rc.getTitleHeight();//((defRect.Height()) - Res.Dip2Pix(10)) / pMenuList.size();
		BaseAdapter simpAdapter = new BaseAdapter() {
			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				convertView = (LinearLayout) LayoutInflater.from(content).inflate(IflytekRes.getLayoutID(content, "tzt_popwindow_listitem"), null);

				TextView acctext = (TextView) convertView.findViewById(IflytekRes.getViewID(content, "tzt_trendmenu_item"));
				acctext.setText(pMenuList.get(position));
				
				if(lastSelectValue.equals(pMenuButton[position][1]) && !IflytekFunc.IsStringEmpty(lastSelectValue)){
					acctext.setTextColor(IflytekRes.getColor(content, "tzt_buttonbar_trend_select_color"));
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
		
		ListView listview = (ListView) pMenuLayout.findViewById(IflytekRes.getViewID(content, "tzt_trendmenu_listView"));
		listview.setAdapter(simpAdapter);
		listview.setSelector(IflytekRes.getDrawabelID(content,"tzt_white"));
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if(pCallBack != null){
					int nAction = IflytekFunc.parseInt(pMenuButton[arg2][1]);
					pCallBack.onItemClick(nAction, pMenuButton, arg2);
				}
				
				dismiss();
			}
		});
		simpAdapter.notifyDataSetChanged();

		 if(nSanJiaoFlag==1){
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(0, IflytekRes.Dip2Pix(15)/2, 0, 0);
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			
			LayoutParams pLayoutParamsNewEx = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			pLayoutParamsNewEx.setMargins(nSanjiaoCenterpostion, - defRect.Height()-IflytekRes.Dip2Pix(15)/2+3, 0, 0);
			pLayoutParamsNewEx.gravity=Gravity.LEFT;
			pImageView.setLayoutParams(pLayoutParamsNewEx);
			pLinearLayout.addView(pImageView);
		}else if(nSanJiaoFlag==2){
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(0, 0, 0, -IflytekRes.Dip2Pix(2)+2);
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			//update by zhengss 20161031 修改向下箭头位置
			LayoutParams pLayoutParamsNewEx = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			pLayoutParamsNewEx.setMargins(nSanjiaoCenterpostion, 0, 0, 0);
			pLayoutParamsNewEx.gravity=Gravity.LEFT;
			pImageView.setLayoutParams(pLayoutParamsNewEx);
			pLinearLayout.addView(pImageView);
		}else if(nSanJiaoFlag==3){//add by zhengss 支持左右的三角
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(IflytekRes.Dip2Pix(12), 0, 0, 0);
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			
			LayoutParams pLayoutParamsNewEx = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			pLayoutParamsNewEx.setMargins(- defRect.Width()-IflytekRes.Dip2Pix(15)/2+3, nSanjiaoCenterpostion, 0, 0);
			pLayoutParamsNewEx.gravity=Gravity.TOP;
			pImageView.setLayoutParams(pLayoutParamsNewEx);
			pLinearLayout.addView(pImageView);
		}else if(nSanJiaoFlag==4){
			LayoutParams pLayoutParamsNew=new LayoutParams(defRect.Width(), defRect.Height());
			pLayoutParamsNew.setMargins(0, 0, 0, 0);
			pMenuLayout.setLayoutParams(pLayoutParamsNew);
			pLinearLayout.addView(pMenuLayout);
			
			LayoutParams pLayoutParamsNewEx = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			pLayoutParamsNewEx.setMargins(-IflytekRes.Dip2Pix(1), nSanjiaoCenterpostion, 0, 0);
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
     * 按钮的callback
     * @author zhengss
     */
    public interface IflytekSpinnerPopupWindowCallback {

        public void onItemClick(int nAction, String[][] Items, int nSelindex);

    }
}
