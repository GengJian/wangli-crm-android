package com.control.widget.toolbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.control.callback.JiuyiICallActivityCallBack;
import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;


import java.util.Vector;

/**
 * ****************************************************************
 * 文件名称:JiuyiNewToolBarCallBack.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:手机版底部工具栏
 * ****************************************************************
 */
public class JiuyiNewToolBar extends RelativeLayout {
    public JiuyiICallActivityCallBack mActivitycallback;
    public JiuyiNewToolBarCallBack mNewToolBarCallBack;
	public Vector<LinearLayout> mLayoutVector;
    /**
     * 选中按钮的功能号
     */
    public int mCurrViewType = -1;
    public String mButtonArray[][];
    public String mButtonHotsArray[];
	public int mButtonIndex = 0;
    /** 配置项的名称 */
	public String mSysName = "newcomtoolbar";
    /** 是否默认选择第一个按钮 */
	public boolean mIsAutoSelectFirstButton = true;//
	public String mLast1901Str = "";
	

	public int nButtonLength;

	public int nUnselectColor = Res.getColor(getContext(), "tzt_v23_toolbar_text_unselect_color");
	public int nSelectColor = Res.getColor(getContext(), "tzt_v23_toolbar_text_select_color");
    public int mBorderPadding = Res.Dip2Pix(5);

    public JiuyiNewToolBar(Context context) {
        super(context);
    }

    public JiuyiNewToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JiuyiNewToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

	/**
	 * @param activityCallBack
	 * @param settingName
	 * @param nHomePage 	    首页的界面号
	 * @param callBack
	 */
	public void setResParam(JiuyiICallActivityCallBack activityCallBack, String settingName, int nHomePage, JiuyiNewToolBarCallBack callBack) {
        mActivitycallback = activityCallBack;
        mSysName = settingName;
        mNewToolBarCallBack = callBack;
        initColor();

        onInit();

        extracted();

		//计算首页的index
		for(int i = 0; i < mButtonArray.length; i++){
			if((nHomePage + "").equals(mButtonArray[i][1])){
				mButtonIndex = i;
				break;
			}
		}

		if(mButtonIndex >= 0){
			if(callBack.isSelectFirstAction()) {
				mIsAutoSelectFirstButton = true;
				//默认选择
				int nPageType = Func.parseInt(mButtonArray[mButtonIndex][1]);
				int nForceSkinType = mButtonArray[mButtonIndex].length>=3 ? Func.parseInt(mButtonArray[mButtonIndex][2]) : -1;
				onButtonClick(null, null, nPageType, nForceSkinType);
				onDoSelectByAction(nPageType);
			}
		}
	}
	
	/**
	 * 切换皮肤
	 */
	public void changeSkinType(){
		extracted();
		initColor();
		
		onInit();
	}
	
	public void initColor() {
		nUnselectColor = Res.getColor(getContext(), "tzt_v23_toolbar_text_unselect_color");
		nSelectColor = Res.getColor(getContext(), "tzt_v23_toolbar_text_select_color");
	}

	public void extracted() {
        setBackgroundResource(Res.getDrawabelID(getContext(), "tzt_v23_newtoolbar_background_selector"));
	}

    /**
     * 推送时，在底部工具栏显示提示"数量"的
     * http://action:1902/?tab1=0|&&tab2="+Rc.getIns().mNoReadMsgCount+"|&&tab3=0|
     */
    public String getShowNotificationTabs() {
//		return "tab2="+Rc.getIns().mNoReadMsgCount+"|";
        return "";
    }

	public String getNotificationCountByTabs(String tabs,int nIndex) {
		String count = Func.GetStringByName(tabs,"tab"+(nIndex+1));
		if(count.indexOf("|") >= 0)
			count = count.substring(0, count.indexOf("|"));
		return count;
	}

	/**
	 * 通过推送设置标题栏的有新推送消息
	 */
    public void setHotNotification() {
    	setHotButtonBy1901(Pub.JY_MENU_ToHotToolBarImage, "");
    }
    /**
     * 显示的是推送的数字
     */
    public void setHotButtonBy1901(int nPageAction, String tabs) {
    	if(mLast1901Str.equals(tabs))
    		return;
    	if(nPageAction == Pub.JY_MENU_ResetToolBarImage){
	    	//tab1=1&&tab2=1&&tab3=0
	    	if(Func.IsStringEmpty(tabs)/* || mButtonHotsArray!=null*/)
	    		return;
	    	tabs = tabs.toLowerCase();
	    	tabs = tabs.replace("&", "\r\n");
	    	mButtonHotsArray = new String[mLayoutVector.size()];
	    	
	    	for (int j = 0; j< mButtonHotsArray.length; j++){
	    		mButtonHotsArray[j] = "1".equals(Func.GetStringByName(tabs,"tab"+(j+1)))?"_hot":"";
				setToolSel1902(mLayoutVector.elementAt(j),((ImageView) mLayoutVector.elementAt(j).getChildAt(0)),Rc.getIns().mNoReadMsgCount,((TextView) mLayoutVector.elementAt(j).getChildAt(1)),j,mButtonArray[j][1].equals(mCurrViewType +""));
	    	}
    	}else if(nPageAction == Pub.JY_MENU_ToHotToolBarImage){
	    	//tab1=1&&tab2=1&&tab3=0;tab1=1|表示Badge显示为1，竖线为后续扩展使用，其他依此类推，tab1-tab5按照各个券商可以对应不一样
	    	if(Func.IsStringEmpty(tabs)/* || mButtonHotsArray!=null*/)
	    		return;
	    	tabs = tabs.toLowerCase();
	    	tabs = tabs.replace("&", "\r\n");
	    	mButtonHotsArray = new String[mLayoutVector.size()];
	    	
	    	for (int j = 0; j< mButtonHotsArray.length; j++){
	    		String count = getNotificationCountByTabs(tabs,j);
	    		setToolSel1902(mLayoutVector.elementAt(j),((ImageView) mLayoutVector.elementAt(j).getChildAt(0)),Func.parseInt(count) ,((TextView) mLayoutVector.elementAt(j).getChildAt(1)),j,mButtonArray[j][1].equals(mCurrViewType +""));
	    	}
    	}
    	mLast1901Str = tabs;
    }

	public void setToolSel1902(LinearLayout layout,ImageView pIamge,int numLable, TextView lable,int nSelIndex,boolean select){
	    String tabs = getShowNotificationTabs();
	    String count = getNotificationCountByTabs(tabs,nSelIndex);
	    numLable = Func.IsStringEmpty(count) ? -1 : numLable;
		String btnhot = (mButtonHotsArray ==null || mButtonHotsArray[nSelIndex]==null) ? "" : mButtonHotsArray[nSelIndex];

		if(Func.parseInt(mButtonArray[nSelIndex][1]) > 0){//处理页面功能的按钮，只有图片没有文字的非页面功能的按钮不处理
			if(select){
				mButtonIndex = nSelIndex;
				layout.setBackgroundResource(Res.getDrawabelID(getContext(), "tzt_footbarselectbg"));
				pIamge.setImageBitmap(Func.addImageViewBadge(pIamge, "jiuyi_toolbar_on"+btnhot+"_" + mButtonArray[nSelIndex][1],numLable,0,0));
				lable.setTextColor(nSelectColor);
			}else{
				layout.setBackgroundDrawable(null);
				pIamge.setImageBitmap(Func.addImageViewBadge(pIamge, "jiuyi_toolbar"+btnhot+"_" + mButtonArray[nSelIndex][1],numLable,0,0));
				lable.setTextColor(nUnselectColor);
			}
		}
	}

	public void onInit() {
		removeAllViews();
		
		mLayoutVector = new Vector<LinearLayout>();
		mButtonArray = Func.SplitStr2Array(Res.getString(null, mSysName));
		if(nButtonLength <= 0 && mButtonArray != null)
			nButtonLength = mButtonArray.length;
		if (mButtonArray == null || mButtonArray.length <= 0)
		{
			return;
		}
		

				
		int width = (Res.getWidthPixels()) / nButtonLength;
		final int height = Rc.getIns().getToolHeight() - Res.Dip2Pix(Rc.getIns().getCanvasMainFont() + 2);
		int ImageWidth = height;//默认是方形的
		//
		LinearLayout m_ButtonLayout = new LinearLayout(getContext());
		m_ButtonLayout.setLayoutParams(new LayoutParams(Res.getWidthPixels(), LayoutParams.MATCH_PARENT));
		m_ButtonLayout.setOrientation(LinearLayout.HORIZONTAL);
		if(nButtonLength == mButtonArray.length)
			m_ButtonLayout.setGravity(Gravity.CENTER);
		else
			m_ButtonLayout.setGravity(Gravity.RIGHT);
		m_ButtonLayout.setPadding(0, 0, 0, 0);
		for (int i=0;i<mButtonArray.length;i++){
			final LinearLayout m_Layout = new LinearLayout(getContext());
			LayoutParams lpLayoutParams = new LayoutParams(width, Rc.getIns().getToolHeight());
			m_Layout.setLayoutParams(lpLayoutParams);
//			m_Layout.setPadding(0, mBorderPadding, 0, 0);
			m_Layout.setOrientation(LinearLayout.VERTICAL);
			m_Layout.setGravity(Gravity.CENTER);
			m_Layout.setTag(i);
				String btnhot = (mButtonHotsArray ==null || mButtonHotsArray[i]==null) ? "" : mButtonHotsArray[i];
				if(i == 0){
					int rsid = Res.getDrawabelID(getContext(), "jiuyi_toolbar"+btnhot+"_" + mButtonArray[i][1]);
					if(rsid > 0){
						Drawable mDrawable = getResources().getDrawable(rsid);
						if(mDrawable != null){
							int imageHeight = mDrawable.getIntrinsicHeight() ;//一行的高度
							int imageWidht = mDrawable.getIntrinsicWidth();
							ImageWidth = height * imageWidht / imageHeight;
						}
					}
				}
				// 图片
				ImageView pIamge = new ImageView(getContext());
				pIamge.setScaleType(ScaleType.CENTER_INSIDE);
				if (Func.IsStringEmpty(mButtonArray[i][0])) {
					pIamge.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				} else {
					pIamge.setLayoutParams(new LayoutParams(ImageWidth, height));
				}
				pIamge.setPadding(Res.Dip2Pix(1), Res.Dip2Pix(1), Res.Dip2Pix(1), 0);
				if (Res.getDrawabelID(getContext(), "jiuyi_toolbar"+btnhot+"_" + mButtonArray[i][1]) > 0)
				{
					pIamge.setImageResource(Res.getDrawabelID(getContext(), "jiuyi_toolbar"+btnhot+"_" + mButtonArray[i][1]));
				}
				else
				{
					pIamge.setVisibility(View.GONE);
				}
				pIamge.setId(i);

                // 提示字
                TextView lable= newTextView(mButtonArray[i][0], -1,
                        pIamge.getVisibility() != View.GONE ? 12 : Rc.getIns().getCanvasHqFont(),
                        LayoutParams.WRAP_CONTENT, -1);
				if (Func.IsStringEmpty(mButtonArray[i][0])){
					lable.setVisibility(View.GONE);
				}
				lable.setGravity(Gravity.TOP);
				lable.setTextColor(nUnselectColor);
				setToolSel1902(m_Layout,pIamge,Rc.getIns().mNoReadMsgCount,lable,i,(i == mButtonIndex && mIsAutoSelectFirstButton));
			m_Layout.addView(pIamge);
			m_Layout.addView(lable);
			m_Layout.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					int nAction = -1;
					boolean bCleatNoReadMsgCount = false;
					for (int j = 0; j < mLayoutVector.size(); j++) {
						String count = getNotificationCountByTabs(getShowNotificationTabs(),j);
						if (!m_Layout.getTag().toString().equals(j + "")) {
//							setToolSel((LinearLayout)mLayoutVector.elementAt(j),((ImageView)mLayoutVector.elementAt(j).getChildAt(0)),((TextView)mLayoutVector.elementAt(j).getChildAt(1)),j,false);
				    		setToolSel1902(mLayoutVector.elementAt(j),((ImageView) mLayoutVector.elementAt(j).getChildAt(0)),Func.parseInt(count) ,((TextView) mLayoutVector.elementAt(j).getChildAt(1)),j,false);
						} else {
							nAction = Func.parseInt(mButtonArray[j][1]);
							if (nAction != Pub.MENU_Mine)
							{
								Rc.cfg.onStopGPS();
							}
							if(Func.parseInt(count) > 0){
								bCleatNoReadMsgCount = true;
							}
				    		setToolSel1902(mLayoutVector.elementAt(j),((ImageView) mLayoutVector.elementAt(j).getChildAt(0)),Func.parseInt(count) ,((TextView) mLayoutVector.elementAt(j).getChildAt(1)),j,true);
						}
					}

					if(bCleatNoReadMsgCount){
						Rc.getIns().mNoReadMsgCount = 0;
                        if(mActivitycallback != null)
                            mActivitycallback.getAjaxWebClientUrlLis().ActionNotification();
					}

					if(nAction > 0){
                        int nForceSkinType = mButtonArray[mButtonIndex].length>=3 ? Func.parseInt(mButtonArray[mButtonIndex][2]) : -1;
						onButtonClick(arg0,m_Layout,nAction, nForceSkinType);
					}
				}
			});
			m_ButtonLayout.addView(m_Layout);
			mLayoutVector.addElement(m_Layout);
		}
		addView(m_ButtonLayout);
	}

    /**
     * 按钮根据action默认选择
     * @param action
     */
	public void onDoSelectByAction(int action) {
		int tmpAction = -1;
		
		for (int j = 0; j < mLayoutVector.size(); j++) {
			String count = getNotificationCountByTabs(getShowNotificationTabs(),j);
			tmpAction = Func.parseInt(mButtonArray[j][1]);
			if (tmpAction != action) {
	    		setToolSel1902(mLayoutVector.elementAt(j),((ImageView) mLayoutVector.elementAt(j).getChildAt(0)),Func.parseInt(count) ,((TextView) mLayoutVector.elementAt(j).getChildAt(1)),j,false);
			} else {
				if (mCurrViewType != Pub.MENU_Mine)
				{
					Rc.cfg.onStopGPS();
				}
//				setToolSel((LinearLayout)mLayoutVector.elementAt(j),((ImageView)mLayoutVector.elementAt(j).getChildAt(0)),((TextView)mLayoutVector.elementAt(j).getChildAt(1)),j,true);
				//点击到收件箱时，提示数字要去掉
				if(Func.parseInt(count) > 0)
					Rc.getIns().mNoReadMsgCount = 0;
				
	    		setToolSel1902(mLayoutVector.elementAt(j),((ImageView) mLayoutVector.elementAt(j).getChildAt(0)),Func.parseInt(count) ,((TextView) mLayoutVector.elementAt(j).getChildAt(1)),j,true);
			}
		}
	}

	public void onButtonClick(View arg0, LinearLayout m_Layout,int nAction, int forceSkinType) {
        if(mNewToolBarCallBack != null){
            mNewToolBarCallBack.onClickToolBarItem(arg0, nAction, forceSkinType);
        }else if(mActivitycallback != null) {
            mActivitycallback.getActivityCanvasInterface().changePage(null, nAction, true);
        }
	}

    public String getLabByPageType(int pageType){
        for (int i = 0; i < mButtonArray.length; i++) {
            if(Func.parseInt(mButtonArray[i][1]) == pageType){
                return mButtonArray[i][0];
            }
        }
        return "";
    }

    /**
     * 设置按钮集合配置key
     * @param value
     */
    public void setButtonNames(String value){
        mSysName=value;
        nButtonLength = 0;
    }

    /**
     * 获取当前 按钮集合key
     * @return
     */
    public String getButtonNames(){
        if(mSysName == null){
            return "";
        }else {
            return mSysName;
        }
    }





    public TextView newTextView(String text, int bgrsid, int font, int nWidth, int nPadHor) {
        TextView m_TextView = new TextView(getContext());
        LayoutParams layoutp = new LayoutParams(nWidth, LayoutParams.WRAP_CONTENT);
        m_TextView.setLayoutParams(layoutp);
        if (bgrsid > 0) {
            m_TextView.setBackgroundResource(bgrsid);
            m_TextView.setPadding(mBorderPadding, Res.Dip2Pix(nPadHor), mBorderPadding, Res.Dip2Pix(nPadHor));
        } else {
            m_TextView.setPadding(0, Res.Dip2Pix(nPadHor), 0, Res.Dip2Pix(nPadHor));
        }
        m_TextView.setSingleLine();
        if (font > 0)
            m_TextView.setTextSize(font);
        m_TextView.setGravity(Gravity.CENTER_VERTICAL);
        m_TextView.setText(text);
        m_TextView.setTextColor(Pub.fontColor);
        return m_TextView;
    }

}