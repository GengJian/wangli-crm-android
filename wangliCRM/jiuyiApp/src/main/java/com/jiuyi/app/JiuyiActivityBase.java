package com.jiuyi.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.android.tu.loadingdialog.LoadingDialog;
import com.control.Interface.JiuyiICanvasInterface;
import com.control.callback.JiuyiICallActivityCallBack;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.tools.JiuyiEventBusEvent;
import com.control.tools.JiuyiInitRc;
import com.control.tools.JiuyiNavigationBarVisiableChange;
import com.control.utils.AjaxWebClientUrlLis;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;

import com.control.utils.JiuyiCpuMemoryTool;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.JiuyiFastToast;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.keyboard.JiuyiKeyBoardManager;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.jiuyi.activity.common.activity.JiuyiWebviewActivity;
import com.jiuyi.activity.passwordlock.activity.JiuyiPasswordUnLockedActivity;
import com.recyclerview.swipe.SwipeMenuRecyclerView;
import com.wanglicrm.android.R;
import com.jiuyi.model.DictBean;
import com.umeng.analytics.MobclickAgent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.jzvd.Jzvd;
import commonlyused.db.DbHelper;
import commonlyused.request.RequestCompanyAddressList;
import customer.request.RequestDictList;
import customer.view.JiuyiToggleButtonGroup;


/**
 * ****************************************************************
 * 文件名称 : ActivityBase.java
 * 作 者 :   zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 所有activity的基类，处理与生命周期和android系统有关的事务
 *****************************************************************
 */
public abstract class JiuyiActivityBase extends FragmentActivity implements JiuyiICanvasInterface {
    /** 初始化UI */
    public abstract void onInit();
    /** 根布局RelativeLayout */
	public JiuyiRelativeLayout mBodyLayout;
    /** 调用Activity的回调 */
    public JiuyiICallActivityCallBack mCallActivityCallBack;
    //导航栏显示隐藏
    public JiuyiNavigationBarVisiableChange mNaviChange;


    /**
     * 是否是第一次执行OnResume，如果是第一次，则是正常的onResume，如果不是第一次则是返回
     * 待验证：防止Performing stop of activity that is not resumed（在Activity A 中在oncreate中启动了另一个activity B）
     */
    public boolean bIsFirstOnResume = true;
    /** H5传参控制
     * 是否需要锁屏，true：需要锁屏，不开启密码锁定检测；false：不需要锁屏，开启密码锁定检测
     */
    private boolean mIsNeedLockScreen =false;//
	/**
     * 是否停止了运行
	 * m_bCurrActivityStop表示Activity是暂停停止甚至销毁，如果暂停停止则不刷新数据和不注册推送信息
	 * Activity在onResume设置为不关闭，在onPause设置为关闭
	 * 这样，Activity的切换，最小化，返回，接电话，打开其他的软件时，本Activity会执行onPause，当恢复时执行onResume
	 * 就保障了本app的正在前台显示的Activity处于m_bCurrActivityStop=false的状态
	 */
	public boolean mCurrActivityStop = false;//
    /** 传递参数 */
	public Bundle mBundle;
    /** 界面号 */
	public int mPageType;
    /** 中间标题栏内容 */
    public String mTitle = "";
    /** 中间标题栏前方的融图片 */
    public String mTitleType = "";



    //H5传参控制
    private boolean m_bNeedLockScreen=false;//是否需要锁屏，true：需要锁屏，不开启密码锁定检测；false：不需要锁屏，开启密码锁定检测
    protected String mPassworkUnLockNextPageType="";//密码锁定解锁成功需要跳转的功能号
    protected String mPassworkUnLockNextPageUrl ="";//密码锁定解锁成功需要跳转的H5地址
    protected LoadingDialog progressDialog,progressLoadingDialog;
    protected ImmersionBar mImmersionBar;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        //zhengss 漏帧
//        JiuyiFrameCallback.getIns().start();

		onInitCreate();
        EventBus.getDefault().register(this);


		//锁屏功能号不为空，则认为是需要锁屏界面
		mIsNeedLockScreen =false;
        //初始化公司通讯录数据
        if (Rc.mInitAddresslist) {
            Rc.mInitAddresslist=false;
            new TimeCount(5000, 1000).start();
        }
        if (!Rc.mCheckInitDict) {
            List<DictBean> dictBeansList = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder().build().list();
            if(dictBeansList==null || dictBeansList.size()==0){
                new TimeCount2(3000, 1000).start();
            }

        }

		//设置态栏颜色（沉浸式状态栏）
        if (Build.VERSION.SDK_INT >= 23) {
            initImmersionBar();
        }else{
            setSystemBarTint(false);
        }
    }

    /**
     * 监听是否点击了home键将客户端推到后台
     */
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
        String SYSTEM_REASON = "reason";
        String SYSTEM_HOME_KEY = "homekey";
        String SYSTEM_HOME_KEY_LONG = "recentapps";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    //表示按了home键,程序到了后台
                    onBackGround();
                }else if(TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)){
                    //表示长按home键,显示最近使用的程序列表
                }
            }
        }
    };

    public void onBackGround(){

    }


    /**
	 * 初始化onCreate里的参数
	 */
	public void onInitCreate(){
		//初始化Rc
        if(Rc.cfg == null) {
            //实现一些底层调用上层的回调
            JiuyiActivityManager.getIns().setBaseCallTopCallBack();
            new JiuyiInitRc(getApplication());
        }

		//获取Intent传值
		getIntentData();

        //根据设置activity theme，放在这里，是因为先取得bundle在根据参数判断theme
        setActivityTheme();

        //设置Window的Feature和Flags
		setWindowFlags();


        //初始化ICallActivityCallBack
        initCallActivityCallBack();

        //当前的activity入毡，一定放在onInitCreate之后才可以，否则无法做入栈判断
        JiuyiActivityManager.pushActivity(this);

        //zhengss 先初始化导航栏监听类，getIntentData里会使用
        mNaviChange = new JiuyiNavigationBarVisiableChange(this);

        onInit();

        //zhengss 导航栏添加监听，
        mNaviChange.addListener(mBodyLayout);
	}


    public boolean isScreenChange() {
        Configuration mConfiguration = getResources().getConfiguration();
        //获取设置的配置信息
        int ori = mConfiguration.orientation;
        //获取屏幕方向
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            return false;
        }
        return false;
    }

    /**
	 * 设置Window的Feature和Flags
	 */
	public void setWindowFlags(){
		if(Rc.cfg.pHqAttrSet.mJiuyiHqAttrSetRootLayout.isWindowFLAG_SECURE()){
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);//当该window在进行显示的时候，不允许截屏。
		}
	}



    /**
     * 初始化ICallActivityCallBack
     */
	public void initCallActivityCallBack(){
        mCallActivityCallBack = new JiuyiICallActivityCallBack(){

            /**
             * 获取当前的ActivityBase里的CanvasInterface
             */
            @Override
            public JiuyiICanvasInterface getActivityCanvasInterface() {
                return JiuyiActivityBase.this;
            }

            /**
             * 获取处理网页url的类
             */
            @Override
            public AjaxWebClientUrlLis getAjaxWebClientUrlLis() {
                return mBodyLayout != null ? mBodyLayout.getAjaxWebClientUrlLis() : null;
            }

            /**
             */
            @Override
            public void setBackBundle() {
                setBackActivityBundle();
            }
			/**
			 * 获取Activity
			 * @return
			 */
			@Override
			public Activity getActivity() {
				return JiuyiActivityBase.this;
			}

		};
    }
	/**
	 * Called when the current Window of the activity gains or loses focus
	 * 当Activity得到或者失去焦点的时候 就会调用
	 */
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}

    /**
     * Activity里的onResume不进行请求数据，数据的请求是在Fragment里发起
     */
	@Override
	public void onResume() {
		mCurrActivityStop = false;


        //u（activity恢复的时候要判断有没有开启密码锁定）
		//1.开启密码锁定并且已经处于锁定状态：判断恢复的界面是否需要锁屏：需要-跳转锁屏界面；不需要-开启锁屏检测
		//2.开启密码锁定并且未处于锁定状态：则判断回复界面是否为界面：界面-关闭锁屏检测；其他界面并切登录-开启锁屏检测
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					//第一次加载不判断密码锁定
					if(bIsFirstOnResume) {
						bIsFirstOnResume = false;
						return;
					}

                    if(JiuyiPasswordLockShared.getIns().getOpenPasswordLock() && JiuyiPasswordLockShared.getIns().isM_bLockNeed()
                        && !(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity() instanceof JiuyiPasswordUnLockedActivity)){
                        boolean bChangePage= JiuyiPasswordLockShared.getIns().ChangeLockPage(mIsNeedLockScreen);
                        if(!bChangePage){
                            JiuyiPasswordLockShared.getIns().startCheckLock();
                        }else{
                            //跳转了锁屏界面，则不需要刷新界面
                            return;
                        }
                    }
                } catch (Exception e) {
                    finish();
                    JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
                }
			}
		});

		super.onResume();
        MobclickAgent.onResume(JiuyiActivityBase.this);

        if(mBodyLayout!=null){
            mBodyLayout.onResume();//必不可少
        }

        JiuyiPasswordLockShared.getIns().setM_bLockNeed(true);
	}

	@Override
	public void onPause() {

		mCurrActivityStop = true;

		super.onPause();
        MobclickAgent.onPause(JiuyiActivityBase.this);
        if(mBodyLayout!=null){
            mBodyLayout.onPause();//必不可少
        }
        Jzvd.releaseAllVideos();


	}

	@Override
	public void onStop() {
		mCurrActivityStop = true;

		if(mBodyLayout!=null){
			mBodyLayout.onStop();//必不可少
		}
		super.onStop();


	}

	@Override
	public void onDestroy() {
		mCurrActivityStop = true;
		if(mBodyLayout!=null){
			mBodyLayout.onDestroy();//必不可少
			mBodyLayout = null;
		}
        EventBus.getDefault().unregister(this);
        JiuyiActivityManager.removeActivityWhenContain(this, true);
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
		super.onDestroy();
		JiuyiLog.e("onDestroy", "onDestroy");
	}

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(Jzvd.backPress()){
                    return true;
                }
                if(mBodyLayout!=null){
                    if (!JiuyiKeyBoardManager.getIns().IsKeyBoardEmpty(mBodyLayout.getKeyBoardView()))
                    {
                        if (mBodyLayout.getKeyBoardView().isShowing())
                        {
                            mBodyLayout.getKeyBoardView().hideKeyboard();
                            return true;
                        }
                    }

                    if(mBodyLayout != null)
                        mBodyLayout.stopProgressBar();
                    JiuyiKeyBoardManager.getIns().closeKeyBoard();


                    if(mBodyLayout != null){
						// 是否需要跳转到主界面
                        if(JiuyiActivityManager.needBackToHomePage(mPageType)){
                            changePage(null, Pub.m_nStartHomePage, false);
                        }else{
                            JiuyiActivityManager.popActivity(this, isNeedTransition(mPageType));
                        }
                        return true;
                    }
                }
                return false;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    /**
     * 请求权限
     */
    public void requestPermission(){
        //子类实现
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //注释即可解决问题
        //super.onSaveInstanceState(outState);
    }
	@Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

	/**
	 * overridePendingTransition统一设置软件的Activity切换和回退的风格
	 * 注意如下：
	 * 1、不需要设置<item name="android:windowAnimationStyle">@style/noAnimation</item>因为在部分小米3手机上没有效果
	 * 2、theme的style里不能加<item name="android:windowIsTranslucent">true</item>否则会出现手机的底部工具栏的背景显示问题
	 */
	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
	}
	

	@SuppressLint("NewApi")
	@Override
	public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
		super.startActivityForResult(intent, requestCode, options);
		overridePendingTransition(intent, true);
	}
	
	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
	}
	
	@Override
	public void finish() {
		JiuyiLog.e("TestFinish", mPageType +";"+this.getLocalClassName());
		super.finish();
	}

    /**
     * 带动画的finish()
     * @param bTransition
     */
	public void finishWithAnim(boolean bTransition) {
		finish();
		
		overridePendingTransition(null, bTransition);
	}
	
	/**
	 * 
	 * @param intent 不为null是startActivity
	 * @param bTransition
	 */
	protected void overridePendingTransition(Intent intent, boolean bTransition){
		if(!JiuyiCpuMemoryTool.isSupport(Rc.getApplication())){
			overridePendingTransition(0, 0);
			return;
		}
		
		if(intent != null){
			Bundle bundle = intent.getExtras();
			if(bundle != null){
				int mPageType = bundle.getInt(JiuyiBundleKey.PARAM_PAGETYPE);
                if(mPageType <= 0)
                    mPageType = Func.parseInt(bundle.getString(JiuyiBundleKey.PARAM_PAGETYPE));
				bTransition = isNeedTransition(mPageType);
			}
		}
		if(bTransition && intent != null)
			overridePendingTransition(Res.getAnimationID(this, "jiuyi_slide_right_in"), Res.getAnimationID(this, "jiuyi_slide_left_out"));
		else if(bTransition)
			overridePendingTransition(Res.getAnimationID(this, "jiuyi_slide_left_in"), Res.getAnimationID(this, "jiuyi_slide_right_out"));
		else
			overridePendingTransition(0, 0);
	}
	
	

	protected boolean isNeedTransition(int nPageType){

		boolean bTransition = true;
		return bTransition;
	}


    /**
     *
     *倒计时内部类
     */
    class TimeCount extends CountDownTimer {
        private TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {//计时完毕时触发
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RequestCompanyAddressList.getIns().getDeptAddressList();
                }
            }).start();
        }
        @Override
        public void onTick(long millisUntilFinished){//计时过程显示
            //倒计时不显示出来
        }
    }

    /**
     *
     *倒计时内部类
     */
    class TimeCount2 extends CountDownTimer {
        private TimeCount2(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {//计时完毕时触发
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RequestDictList.getIns().getDictList();
                }
            }).start();
        }
        @Override
        public void onTick(long millisUntilFinished){//计时过程显示
            //倒计时不显示出来
        }
    }


	/**
	 * 设置activity主题
	 */
    public void setActivityTheme() {
        setTheme(Res.getStyleID(getApplicationContext(), "jiuyi_ThemeCompat.White"));
    }

    /**
     * 获取Intent传值
     * 页面号和BodyRect
     * 如果没有传递过来此参数，当已经获取郭屏幕的尺寸时则取屏幕的尺寸，否则取null
     */
    public void getIntentData(){
        if(getIntent() != null){
            mBundle = getIntent().getExtras();
            if(mBundle != null){
                mPageType = mBundle.getInt(JiuyiBundleKey.PARAM_PAGETYPE);
                if(mPageType <= 0)
                    mPageType = Func.parseInt(mBundle.getString(JiuyiBundleKey.PARAM_PAGETYPE));
                mTitle = mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
                mTitleType = mBundle.getString(JiuyiBundleKey.PARAM_TITLETYPE);
                //锁屏功能号不为空，则认为是需要锁屏界面
                m_bNeedLockScreen = false;
                mPassworkUnLockNextPageType = "";
                mPassworkUnLockNextPageUrl = "";
                mPassworkUnLockNextPageType = mBundle.getString(JiuyiBundleKey.PARAM_UNLOCKPASSWORDNEXTPAGETYPE);
                mPassworkUnLockNextPageUrl = mBundle.getString(JiuyiBundleKey.PARAM_UNLOCKPASSWORDNEXTPAGEURL);
                if(!Func.IsStringEmpty(mPassworkUnLockNextPageType) && mPageType!=Pub.PasswordLock_Lock){
                    m_bNeedLockScreen=true;
                }
            }
        }
    }

    /**
     * 获取当前Activity的界面号
     * @return
     */
    public int getPageType(){
        return mPageType;
    }

    /**
     * 订阅事件
     * JiuyiEventBusEvent：传送的事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserEvent(JiuyiEventBusEvent event) {
        if (event == null)
            return;

        switch (event.getEventType()){
            case EventType_ChangeSkinType:
                changeSkinType();
                break;
            case EventType_CloseSysKeyBoard:
                if(mBodyLayout!=null && mBodyLayout.getKeyBoardView() != null)
                    mBodyLayout.getKeyBoardView().hideKeyboard();
                hideInput();
                break;

            case EventType_SetBaseCallTopCallBack:
                //实现一些底层调用上层的回调
                JiuyiActivityManager.getIns().setBaseCallTopCallBack();
                break;
            case EventType_Refresh:
                if (JiuyiActivityBase.this instanceof JiuyiWebviewActivity)
                    ((JiuyiWebviewActivity) JiuyiActivityBase.this).reload();
                else
                    createReq(true);
                break;
        }
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
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
        if(mBodyLayout != null && !Func.IsStringEmpty(title)){
            if(titleType != null)
                mTitleType = titleType;
            if(!title.equals(mTitle))
                mTitle = title;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBodyLayout.setTitle(mTitle, "", "", mTitleType);
                }
            });
        }
    }

    /**
     * 设置进度条的状态
     *
     * @param barProcess 进度
     */
    @Override
    public void showProcessBar(final int barProcess) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mBodyLayout != null) {
                    if (barProcess == 100) {
                        mBodyLayout.stopProgressBar();
                    } else {
                        mBodyLayout.startProgressBar();
                    }
                }
            }
        });
    }



    /**
     * 错误信息用Toast显示提示信息
     *
     * @param msg    错误信息
     */
    @Override
    public void showErrorMessage(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JiuyiFastToast.show(JiuyiActivityBase.this, msg);
            }
        });
    }

    /**
     * 页面回退事件
     */
    @Override
    public void backPage() {
		//update by zhengss 和点击返回按钮一样的逻辑
		onKeyUp(KeyEvent.KEYCODE_BACK, null);
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JiuyiDialogBase dialog = new JiuyiDialogBase();
                dialog.startDialog(JiuyiActivityBase.this, JiuyiActivityBase.this, nAction, sTitle, sContent, nBtnType, struct);
                dialog.show();
            }
        });
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
        //切换皮肤，需要重新设置主题
        setActivityTheme();

        if(mBodyLayout != null){
            //zhengss 切换背景后，设置背景颜色
            mBodyLayout.setBackgroundColor(Res.getColor(this, "tzt_v23_background_color"));

            mBodyLayout.changeSkinType();
            //标题栏
            if(mBodyLayout.getTitleBar() != null)
                mBodyLayout.getTitleBar().changeSkinType();


        }
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
        return mCurrActivityStop;
    }



    /**
     * 切换界面
     *
     * @param nPageType 要跳转的界面号
     * @param bSavePage 是否要保存当前界面，保存后可以在返回到这个界面
     */
    @Override
    public void changePage(Bundle mBundle, int nPageType, boolean bSavePage) {
        if(bIsFirstOnResume)
            return;

        if(mBundle == null)
            mBundle = new Bundle();
        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        //是否执行startNextActivityForResult传参不为空则执行startNextActivityForResult方法；反之执行startNextActivity
        String sDostartNextActivityForResult = mBundle.getString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT);
        if(!Func.IsStringEmpty(sDostartNextActivityForResult)){
            JiuyiActivityManager.startNextActivityForResult(this, null, mBundle, bSavePage, Integer.parseInt(sDostartNextActivityForResult));
//            JiuyiActivityManager.startNextActivityForResult(this, null, mBundle, bSavePage, 0);
        }else{
            JiuyiActivityManager.startNextActivity(this, null, mBundle, bSavePage);
        }
    }

	/**
	 * 键盘按钮点击的回调
	 */
	public void onKeyboardClick(int primaryCode){

	}
    /**
     * 处理NavigationBar显示隐藏
     */
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange){

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
        return JiuyiActivityManager.popBackToActivityNew(nPageType, bstartPageTypeActivity);
    }

    /**
     * 设置后退activity传参（有时候传参的数据是在activity层，比如个股详情横屏）
     */
    public void setBackActivityBundle() {
        //有子类实现
    }








	/**
	 * 沉浸式状态栏-设置状态栏颜色
	 *
	 */
	public void setSystemBarTint(boolean isFullBarTint){
		Rc.cfg.pHqAttrSet.mJiuyiHqAttrSetSystemBarTint.showSystemBarTint(this, mBodyLayout, mBodyLayout.getTitleBar(), null, null, isFullBarTint);
	}
    public void showDialog(){
        if(progressDialog==null){
            LoadingDialog.Builder builder1=new LoadingDialog.Builder(JiuyiActivityBase.this)
                    .setMessage("提交中...")
                    .setCancelable(false);
            progressDialog=builder1.create();
        }
        progressDialog.show();
    }
    public void showLoadingDialog(){
        if(progressLoadingDialog==null){
            LoadingDialog.Builder builder1=new LoadingDialog.Builder(JiuyiActivityBase.this)
                    .setMessage("加载中...")
                    .setCancelable(false);
            progressLoadingDialog=builder1.create();
        }
        progressLoadingDialog.show();
    }
    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.fitsSystemWindows(true);  //使用该属性,必须指定状态栏颜色
        mImmersionBar.statusBarColor(R.color.title_background_color);
        mImmersionBar.init();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    public  void disableSubControls(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                if (v instanceof Spinner) {
                    Spinner spinner = (Spinner) v;
                    spinner.setClickable(false);
                    spinner.setEnabled(false);
                } else if (v instanceof ListView) {
                    ((ListView) v).setClickable(false);
                    ((ListView) v).setEnabled(false);
                }else if (v instanceof RecyclerView) {
                    ((RecyclerView) v).setClickable(false);
                    ((RecyclerView) v).setEnabled(false);
                } else if (v instanceof JiuyiItemGroup) {
                    ((JiuyiItemGroup) v).setEnabled(false);
                    ((JiuyiItemGroup) v).setClickable(false);
                    ((JiuyiItemGroup) v).contentEdt.setEnabled(false);
                    ((JiuyiItemGroup) v).clearIv.setClickable(false);
                    ((JiuyiItemGroup) v).clearIv.setEnabled(false);
                    ((JiuyiItemGroup) v).setItemOnClickListener(null);
                }  else {
                    disableSubControls((ViewGroup) v);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setEnabled(false);
                ((EditText) v).setClickable(false);
            } else if (v instanceof JiuyiButton) {
                ((JiuyiButton) v).setEnabled(false);
            } else if (v instanceof Button) {
                ((Button) v).setEnabled(false);
            } else if (v instanceof ToggleButton) {
                ((ToggleButton) v).setEnabled(false);
                ((ToggleButton) v).setClickable(false);
                ((ToggleButton) v).setOnCheckedChangeListener(null);
            }else if (v instanceof JiuyiEditTextField) {
                ((JiuyiEditTextField) v).setEnabled(false);
                ((JiuyiEditTextField) v).setClickable(false);
            }else if (v instanceof ImageView) {
                ((ImageView) v).setEnabled(false);
                ((ImageView) v).setClickable(false);
            }
        }
    }

}
