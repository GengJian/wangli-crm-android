package com.jiuyi.app;


import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;


import com.http.JiuyiHttp;
import com.http.interceptor.HttpLogInterceptor;
import com.wanglicrm.android.R;
import com.jiuyi.interceptor.TokenInterceptor;
import com.jiuyi.tools.FrescoLoader;
import com.jiuyi.tools.jiuyiImageLoader;
import com.jiuyi.tools.jiuyiCrashHandler;
import com.jiuyi.tools.jiuyiResetLinkBackToForeground;
import com.loader.LoaderManager;
import com.previewlibrary.ZoomMediaLoader;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
//import com.squareup.leakcanary.LeakCanary;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qcloud.sdk.Constant;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;
import com.uuzuche.lib_zxing.DisplayUtil;

import commonlyused.db.DbHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tencent.bugly.crashreport.CrashReport;

import java.net.Proxy;


public class JiuyiMainApplication extends Application {
	private static JiuyiMainApplication instance;
    /** CrashHandler对象*/
	public jiuyiCrashHandler crashHandler = null;
    /** App进入后台的类 */
    private jiuyiResetLinkBackToForeground mNewLinkPressHome;
    /**token 名称*/
    public static final String AUTHORIZATION_KEY= "Authorization";

    //token过期状态码
    public static final int ERROR_UNAUTHORIZED = 401; //未授权的请求
    //刷新token 失败错误码
    public static final int ERROR_REFRESH_TOKEN_FAILED = 101;
    static {//static 代码段可以防止内存泄露



        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		crashHandler = jiuyiCrashHandler.getInstance();
		// 注册crashHandler
		crashHandler.init(getApplicationContext());
        ZoomMediaLoader.getInstance().init(new jiuyiImageLoader());
        if(Constant.ServerHJ.equals("PRD")){
            CrashReport.initCrashReport(getApplicationContext(), "1bf53e24d3", false);
        }
        if(MsfSdkUtils.isMainProcess(this)) {
            Log.d("MyApplication", "main process");

            // 设置离线推送监听器
            TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {
                @Override
                public void handleNotification(TIMOfflinePushNotification notification) {
                    Log.d("MyApplication", "recv offline push");
                    // 这里的 doNotify 是 ImSDK 内置的通知栏提醒，应用也可以选择自己利用回调参数 notification 来构造自己的通知栏提醒
                    notification.doNotify(getApplicationContext(), R.mipmap.ic_launcher);
                }
            });
        }
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        //测试
//        UMConfigure.init(this, "5bbb29e3b465f54f0800019f", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,null);
        if(Constant.ServerHJ.equals("PRD")){
            UMConfigure.init(this, "5d1f03473fc1959eca000687", Constant.Umeng, UMConfigure.DEVICE_TYPE_PHONE,null);
        }

        initNet();
        DbHelper.getInstance().init(this);
        LoaderManager.setLoader(new FrescoLoader());//外部定制图片加载库FrescoLoader
        LoaderManager.getLoader().init(this);
        initX5WebView();

        //初始化LeakCanary
  /*      if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);*/
        /**
         * 初始化尺寸工具类
         */
        initDisplayOpinion();
    }
    private void initNet() {
        JiuyiHttp.init(this);
        JiuyiHttp.CONFIG()
//                .proxy( Proxy.NO_PROXY)
                //配置请求主机地址
                .baseUrl(Constant.BaseUrl)
                .setCookie(true)
                .interceptor(new TokenInterceptor())   //添加token拦截器
                //配置日志拦截器
                .interceptor(new HttpLogInterceptor()
                        .setLevel(HttpLogInterceptor.Level.BODY));
    }

    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }

	public static JiuyiMainApplication getIns() {
		return instance;
	}

    /**
     * App进入后台的类
     * @return
     */
    public jiuyiResetLinkBackToForeground getResetLinkBackToForeground(){
        return mNewLinkPressHome;
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        exit();
    }

    public void exit() {
        System.exit(0);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private void initX5WebView() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
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

}