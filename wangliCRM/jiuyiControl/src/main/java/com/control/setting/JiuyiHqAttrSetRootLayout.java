package com.control.setting;
/**
 * ****************************************************************
 * 文件名称 : JiuyiHqAttrSetRootLayout.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/2 17:01
 * 
 * 文件描述 : 首页的配置
 *****************************************************************
 */
public class JiuyiHqAttrSetRootLayout {

    /**
	 * 是否显示左侧栏
	 * true	显示左侧栏，点击标题栏左上角的按钮，打开左侧栏
	 * false不显示左侧栏，点击标题栏左上角的按钮
	 */
    private boolean mIsShowLeftView = false;


	/**
	 * 标题下方是否显示阴影
	 * true 显示
	 * false 不显示（默认）
	 */
    private boolean mIsShowTitleBottomShowShadow =false;
	
	/**
	 * 标题是否显示切换皮肤按钮
	 * true 显示 （默认）
	 * false 不显示
	 */
    private boolean mTitleShowDayOrNeight =true;
	
	/**
	 * 标题栏是xml布局
	 * true (默认)
	 * false
	 */
    private boolean mIsTitleBarXmlStyle =true;
	



    /**
     * 当该window在进行显示的时候，不允许截屏。
     * true 显示（默认）
     * false 不显示
     */
    private boolean mIsWindowFLAG_SECURE = false;

	/**
	 * 是否使用固定的首页
	 * true 使用固定的首页
	 * false 支持首页设置
	 */
	private boolean bStableHomeIndex = false;


    public void setIsShowLeftView(boolean mIsShowLeftView) {
        this.mIsShowLeftView = mIsShowLeftView;
    }

    public void setIsShowTitleBottomShowShadow(boolean mIsShowTitleBottomShowShadow) {
        this.mIsShowTitleBottomShowShadow = mIsShowTitleBottomShowShadow;
    }

    public void setTitleShowDayOrNeight(boolean mTitleShowDayOrNeight) {
        this.mTitleShowDayOrNeight = mTitleShowDayOrNeight;
    }

    public void setIsTitleBarXmlStyle(boolean mIsTitleBarXmlStyle) {
        this.mIsTitleBarXmlStyle = mIsTitleBarXmlStyle;
    }



	public void setIsStableHomeIndex(boolean mIsStableHomeIndex) {
		this.bStableHomeIndex = mIsStableHomeIndex;
	}


    public boolean isShowLeftView(){
		return mIsShowLeftView;
	}

	public boolean isTitleBottomShowShadow(){
		return mIsShowTitleBottomShowShadow;
	}
	
	public boolean isTitleShowDayOrNeight(){
		return mTitleShowDayOrNeight;
	}
	
	public boolean isTitleBarXmlStyle(){
		return mIsTitleBarXmlStyle;
	}
	

    public boolean isWindowFLAG_SECURE(){
        return mIsWindowFLAG_SECURE;
    }

	public boolean isStableHomeIndex(){
		return bStableHomeIndex;
	}

}
