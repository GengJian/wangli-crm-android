package com.control.widget;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;

import com.android.tu.loadingdialog.LoadingDialog;
import com.control.Interface.JiuyiICanvasInterface;
import com.control.R;
import com.control.callback.JiuyiICallActivityCallBack;
import com.control.callback.JiuyiIEditTextCallBack;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.keyboard.JiuyiKeyBoardView;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.webview.JiuyiWebView;
import com.gyf.barlibrary.ImmersionBar;

/**
 * ****************************************************************
 * 文件名称:JiuyiFragmentBase.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:Fragment的基类，实现请求和界面操作事件
 * 注意事项:如果想调用jiuyiICanvasInterface里的函数，可以通过mCallBack对象调用
 *          引用jiuyiFragmentBase及其子类的Fragment，需要调用setCallBack方法设置jiuyiICallActivityCallBack，否则可能会出现为空的问题
 * ****************************************************************
 */

public class JiuyiFragmentBase extends Fragment implements JiuyiICanvasInterface, JiuyiIEditTextCallBack {
    /** Rc对象 */
    public Rc record = Rc.getIns();
    /** 调用Activity的回调 */
    public JiuyiICallActivityCallBack mCallBack;
    /** 从getActivity()获取Bundle */
    public Bundle mBundle = new Bundle();
    /** FragmentBase的根view */
    public View mRootView;//
    /**
     * 界面号
     * 一般而言，每个Fragment都有一个确定的mPageType；设置的位置在onCreate里的
     * super.onCreate(savedInstanceState)之后设置mPageType = Pub.MENU_Customer;
     * 这样设置的好处是子类可以覆盖父类的赋值，即以子类设置的值为准
     *
     * 有的界面是多个功能号公用的，则可以通过FragmentBase里setCallBack方法mPageType = mCallBack.getActivityCanvasInterface().getPageType();获取
     */
    public int mPageType;
    /**
     * 中间标题栏内容
     * 在onCreate里设置标题的内容
     * 在FragmentBase里setCallBack里调用setTitle设置
     * 对于可能会变化的获取是不确定的标题，可以动态的设置；比方说网页界面，就是在stopProgress里调用设置标题
     */
    public String mTitle = "";
    /** 标题栏上的交易图片类型 */
    public String mTitleType;
    /** 界面的皮肤类型 */
    public int mSkinType =1;
    /** 是否是第一次执行OnResume，如果是第一次，则是正常的onResume，如果不是第一次则是返回 */
    public boolean bIsFirstOnResume = true;
    /** Fragment是否可见 */
//    private boolean bIsVisibleToUser = false;
    public LoadingDialog loadingDialog;
    protected ImmersionBar mImmersionBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取上一界面的Bundle，供初始化，滚动广告页等使用
        mBundle = getArguments();
        if(mBundle == null) {
            Intent intent = getActivity().getIntent();
            if(intent != null) {
                mBundle = intent.getExtras();
            }
        }
        if(mBundle != null){
            mPageType = mBundle.getInt(JiuyiBundleKey.PARAM_PAGETYPE);
            mTitle = mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        }else{
            //保存mBundle不等于null，子类就不判断他是否为空了
            mBundle = new Bundle();
        }
    }

    /**
     * 在FragmentPagerAdapter中调用
     * 当fragment被用户可见时，setUserVisibleHint()会调用且传入true值，当fragment不被用户可见时，setUserVisibleHint()则得到false值
     * 这个方法仅仅工作在FragmentPagerAdapter中
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    /**
     * 一个Activity内多个Fragmnt切换时调用
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.statusBarColor(R.color.title_background_color);
            mImmersionBar.init();


    }

    public String getCurrPageString() {
        if(getActivity() != null) {
            return getActivity().getClass().getSimpleName() + "_" + getClass().getSimpleName() + "_" + mPageType;
        }else{
            return "";
        }
    }

    /**
     * 设置SwipeRefreshLayout的刷新监听
     */
    public void setOnRefreshListener(){
        if(mRootView instanceof JiuyiSwipeRefreshLayout){
            ((JiuyiSwipeRefreshLayout)mRootView).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
                @Override
                public void onRefresh() {
                    //隐藏刷新按钮
                    ((JiuyiSwipeRefreshLayout)mRootView).setRefreshing(false);
                    //刷新数据
                    createReq(false);
                }
            });
        }
    }

    /**
     * 获取调用Activity的回调
     * @return
     */
    public JiuyiICallActivityCallBack getCallBack() {
        return mCallBack;
    }

    /**
     * 设置调用Activity的回调
     * @param mCallBack
     */
    public void setCallBack(JiuyiICallActivityCallBack mCallBack) {
        if(mCallBack == null)
            return;

        this.mCallBack = mCallBack;

        //这个时候初始化完成了，可以设置标题了
        setTitle(mTitle);
        //如果mPageType为空则通过CanvasInterface获取
        if(mPageType <= 0 && mCallBack.getActivityCanvasInterface() != null)
            mPageType = mCallBack.getActivityCanvasInterface().getPageType();
    }

    /**
     * 缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
     */
    public void checkRootViewParent() {
        if(mRootView == null)
            return;
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
    }

    /**
     * 如果是第一次，则是正常的onResume，
     * 如果不是第一次则是执行createBackReq
     */
    @Override
    public void onResume() {
        if(bIsFirstOnResume) {
            bIsFirstOnResume = false;
        }else{
            createBackReq(true);
        }
        super.onResume();
    }

    /**
     * 只是处理界面返回时的事物
     * @param IsBg
     */
    public void createBackReq(boolean IsBg) {

    }

    /**
     * 销毁自己
     */
    public void onBackPressed() {
        if(getActivity() != null)
            getActivity().onBackPressed();
    }
    /**
     * 设置界面号
     *
     * @param pageType 界面号
     */
    public void setPageType(int pageType) {
        mPageType = pageType;
    }


    /**
     * 获取当前Fragment的界面号
     * @return
     */
    public int getPageType(){
        return mPageType;
    }

    /**
     * 获取当前Fragment的界面字符串
     * @return
     */
    public String getSimpleName(){
        return getClass().getSimpleName();
    }

    /**
     * 设置中间标题栏文字
     * 如果不知道执行通用的标题规则，请子类重写
     * @param title
     */
    public void setTitle(String title) {
        setTitle(title, mTitleType);
    }
    public void setTitle(String title, String titleType) {
        if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null){
            if(titleType != null)
                mTitleType = titleType;
            if(!Func.IsStringEmpty(title) && title.equals(mTitle))
                mTitle = title;
            if(!Func.IsStringEmpty(mTitle))
                mCallBack.getActivityCanvasInterface().setTitle(mTitle, mTitleType);
        }
    }
    /**
     * 设置进度条的状态
     *
     * @param barProcess 进度
     */
    @Override
    public void showProcessBar(final int barProcess) {
        if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null){
            mCallBack.getActivityCanvasInterface().showProcessBar(barProcess);
        }
    }

    /**
     * 界面初始化view等
     * 如果需要才实现
     * @return
     */
    @Override
    public void onInit() {
        //子类实现
    }



    /**
     * 错误信息用Toast显示提示信息
     *
     * @param msg    错误信息
     */
    @Override
    public void showErrorMessage(final String msg) {
        if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null){
            mCallBack.getActivityCanvasInterface().showErrorMessage(msg);
        }
    }

    /**
     * 页面回退事件
     */
    @Override
    public void backPage() {
        if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null){
            mCallBack.getActivityCanvasInterface().backPage();
        }
    }


    /**
     * 弹出Dialog
     *
     * @param nAction  唯一标示是哪个Dialog
     * @param sTitle   要显示Dialog的标题
     * @param sContent 要显示Dialog的内容
     * @param nBtnType 按钮类型，值主要有
     *                 MyDialog.Dialog_Type_YesNo:确定返回按钮
     *                 MyDialog.Dialog_Type_Yes:确定按钮
     *                 MyDialog.Dialog_Type_No：返回按钮
     * @param struct
     */
    @Override
    public void startDialog(final int nAction, final String sTitle, final String sContent, final int nBtnType, final JiuyiDialogBase.DialogStruct struct) {
        if (mRootView != null) {
            mRootView.post(new Runnable() {
                @Override
                public void run() {
                    JiuyiDialogBase dialog = new JiuyiDialogBase();
                    dialog.startDialog(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), JiuyiFragmentBase.this, nAction, sTitle, sContent, nBtnType, struct);
                    dialog.show();
                }
            });
        }
    }
    public void startDialog(final int nAction, final String sTitle, final String sContent, final int nBtnType) {
        startDialog(nAction, sTitle, sContent, nBtnType, null);
    }

    /**
     * 处理弹出Dialog的事件
     * @param nAction   与startDialog时的nAction相同，唯一标示是哪个Dialog
     * @param nKeyCode  确定(Pub.SOFT1)和返回(Pub.SOFT2)按钮
     * @param url       需要处理的Url
     * @param pDialog  pDialog弹框，用于后续处理关闭弹框
     */
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog){
        //子类实现
    }

    /**
     *
     * @param IsBg 是否显示进度条，true不显示，false显示；
     */
    @Override
    public void createReq(boolean IsBg) {
        //子类实现
    }

    /**
     * 根据功能号加界面统计信息
     *
     * @param nPrePageType 上一界面
     * @param nPageType    本次要跳转的界面
     * @param bPageStart   页面开始还是页面结束
     * @return 是否成功的处理此请求
     */
    @Override
    public boolean actionWithTCAgentPage(int nPrePageType, int nPageType, boolean bPageStart) {
        //子类实现
        return false;
    }

    /**
     * 切换皮肤
     */
    public void changeSkinType(){
        //子类实现 fragment 没有背景色，使用activity的背景色
    }

    /**
     * 消息推送过来需要客户端具体界面处理的事物，调用此方法
     *
     * @param type    消息代码
     * @param subtype 消息子代码
     * @param title   消息的标题
     * @param message 消息的信息
     * @return 是否成功的处理此请求
     */
    @Override
    public boolean loadJsByMsgPush(int type, int subtype, String title, String message) {
        //子类实现
        return false;
    }

    /**
     * 当前的Activity是否关闭了
     *
     * @return
     */
    @Override
    public boolean currActivityStop() {
        if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null){
            return mCallBack.getActivityCanvasInterface().currActivityStop();
        }
        return true;
    }

    @Override
    public void onKeyboardClick(int primaryCode) {

    }



    /**
     * 切换界面
     * 
     * @param nPageType 要跳转的界面号
     * @param bSavePage 是否要保存当前界面，保存后可以在返回到这个界面
     */
    @Override
    public void changePage(Bundle mBundle, int nPageType, boolean bSavePage) {
        if(mCallBack != null && mCallBack.getActivityCanvasInterface() != null){
            mCallBack.getActivityCanvasInterface().changePage(mBundle, nPageType, bSavePage);
        }
    }

    @Override
    public JiuyiKeyBoardView getKeyBoardView() {
        if(getRelativeLayout() == null){
            return null;
        }else {
            return getRelativeLayout().getKeyBoardView();
        }
    }

    @Override
    public JiuyiRelativeLayout getRelativeLayout() {
        if(mCallBack == null || mCallBack.getAjaxWebClientUrlLis() == null || mCallBack.getAjaxWebClientUrlLis().getAjaxWebClientUrlLisCallBack() == null){
            return null;
        }else {
            return mCallBack.getAjaxWebClientUrlLis().getAjaxWebClientUrlLisCallBack().getRelativeLayout();
        }
    }

    @Override
    public JiuyiWebView getWebView() {
        return null;
    }

    @Override
    public void setValueByKeyBoard(JiuyiEditText edit, int keycode) {

    }

    @Override
    public void keyBoardShowCallBack(int height) {

    }

    @Override
    public void keyBoardHideCallBack(int height) {

    }

    /**
     * 跳转到指定界面，一般是退回到RootActivity
     * 如果bstartPageTypeActivity为flase，则只是吧nPageType之前的Activity都finish掉，否则就是要退到这个Activity
     * @nPageType 	要退到的activity的pagetype，如果要返回到首页，则参数为Pub.mStartHomePage即可
     * @bstartPageTypeActivity	是否要打开要退到的activity的pagetype的Activity
     * @return 是否成功跳转
     */
    @Override
    public boolean popBackToActivity(int nPageType, boolean bstartPageTypeActivity){
        if(mCallBack == null || mCallBack.getActivityCanvasInterface() == null){
            return false;
        }else {
            return mCallBack.getActivityCanvasInterface().popBackToActivity(nPageType, bstartPageTypeActivity);
        }
    }



    /**
     * 处理NavigationBar显示隐藏
     *
     * @param nCurrBodyHeight
     * @param nDeltaChange
     */
    @Override
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange) {

    }

    public void showDialog(){
        if(loadingDialog==null){
            LoadingDialog.Builder builder1=new LoadingDialog.Builder(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity())
                    .setMessage("加载中...")
                    .setCancelable(false);
            loadingDialog=builder1.create();
        }
        loadingDialog.show();
    }
    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.title_background_color);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isAdded()){
            if (loadingDialog != null)
                loadingDialog.dismiss();
        }

    }
}
