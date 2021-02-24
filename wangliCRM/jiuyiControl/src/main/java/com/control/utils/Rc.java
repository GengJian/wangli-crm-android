package com.control.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.control.callback.JiuyiBaseCallTopCallBack;
import com.control.shared.JiuyiMobileRegistShared;
import com.control.shared.JiuyiRcParamShared;

import java.util.HashMap;
import java.util.Map;


/**
 * ****************************************************************
 * 文件名称:Rc.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:保存的全局属性、全局变量、设置、用户数据等
 * ****************************************************************
 */

public class Rc {
    /** 内部版本号 */
    public String version = "[20180711]001";//
    /** Application对象 */
    private static Application m_pMainApplication;
    /** Rc实例 */
    private static Rc record;
    /** 总配置Config的实例 */
    public static Config cfg;
    /** Rc里需要保持的参数保存类 */
    public JiuyiRcParamShared mRcParamShared;

    /** 通用Canvas字号 */
    private int mCanvasMainFont;//
    /** Canvas字号 */
    private int mCanvasHqFont;//
    /** 通用原生Widget控件字号 */
    private int mWidgetMainFont;//

    public int getmWidgetCommonFont() {
        return mWidgetCommonFont;
    }

    public void setmWidgetCommonFont(int mWidgetCommonFont) {
        this.mWidgetCommonFont = mWidgetCommonFont;
    }

    /** 原生Widget控件字号 */
    private int mWidgetCommonFont;//
    /** 标题栏的高度 */
    private int mTitleHeight;//
    /** 底部工具栏的高度 */
    private int mToolHeight;//
    /** 默认每行高度 */
    private int mLineHeight;//
    /** 手机顶部状态栏高度 */
    private int mTopStatusBarHeight;//
    /** 手机底部虚拟导航栏高度 */
    private int mBottomNaviNarHeight;//
    public static String mUpVersion;//升级版本号
    public static String mSysVersion;//软件版本号，显示在设置里

    /** 是否混淆过；如果混淆过，HttpServer要启用缓存，不输出LOG 如果未混淆过，否则HttpServer不启用缓存方便调试，输出LOG信息 */
    private boolean m_bPackageWithProguard = true;

    /** 底层调用上层的值或者方法的回调 */
    private JiuyiBaseCallTopCallBack mBaseCallTopCallBack;


    /** 未读数量 */
    public int mNoReadMsgCount;
    /** 设置以及运行次数 */
    public int mRunCount;
    /** 的Pid */
    public String mGjscPID = ""; //
    /** 上次输入的url，记录下来下次也可以使用 */
    public String mInputAjaxUrl;

    public static String name = ""; // 账号
    public static long id;//用户id
    public static String userName = ""; // 用户名
    public static String deptName = ""; // 部门
    public static String id_token = ""; // 全局token
    public static String id_tokenparam = ""; // 全局入参token
    public static String tim_signature = ""; // 消息sign
    public static String tim_identifier = ""; // 消息identify
    public static long deptid = -1; // 部门id
    public static Boolean mInitAddresslist =false; // 是否初始化公司联系人信息
    public static Boolean mCheckInitDict =false; // 是否初始化字典信息
    public static CustomerQueryConditionBean queryConditionBean;//客户360全局条件
    public static String sortcondition="";//排序条件
    public static String quicksortcondition="";//快速排序条件
    public static Boolean mBackfresh =false; // 返回需要刷新
    public static int mPageType; // 当前功能号
    public static int mCurrentPageType; // 当前功能号
    public static String mobilecode="";
    public static Boolean mUpdate =false; // 客户新增是否更新
    public static String sapNumber="";//sap编号
    public static Map<String, String> map_ekv=new HashMap<String, String>();//友盟客户手工筛选id记录
    public static String picVideoUrl="";//图片视频地址
    public static int currentPostion; // 当前位置

    public static String serviceDate="";//服务日期
    public static String todayOnDutyTime="";//上班时间



    public Rc(){

    }

    public static Rc getIns(){
        if(record == null){
            record = new Rc();
        }
        return record;
    }

    /**
     * 判断是否Rc是否为空
     * @return
     */
    public static boolean IsRcEmpty()
    {
        return record == null;
    }
    /**
     * 设置Application
     * @param app
     */
    public static void SetApplication(Application app) {
        if(app != null)
            m_pMainApplication = app;
    }
    /**
     * 获取Application
     * @return Application
     */
    public static Application getApplication() {
        return m_pMainApplication;
    }

    /**
     * 设置BaseCallTopCallBack回调
     * @param callBack
     */
    public void setBaseCallTopCallBack(JiuyiBaseCallTopCallBack callBack){
        mBaseCallTopCallBack = callBack;
    }

    /**
     * 获取jiuyiBaseCallTopCallBack回调
     */
    public JiuyiBaseCallTopCallBack getBaseCallTopCallBack(){
        return mBaseCallTopCallBack;
    }
    /**
     * 初始化全局属性、全局变量、设置、用户数据等
     */
    public void initRc(Config config){
        boolean isCfgEmpty = cfg==null;

        if(isCfgEmpty) {
            //用户业务配置
            cfg = config;

            //Rc里需要保持的参数保存类
            mRcParamShared = new JiuyiRcParamShared(this);
            //初始化一些回调
            initCallBack();
            //初始化一些参数
            initParam();
            //是否混淆过了
            initProguard();

        }
    }
    public void initRc(){
        if(cfg != null)
            return;

        //用户业务配置
        initRc(new Config(getApplication()));
    }

    /**
     * 初始化一些参数
     */
    public void initParam(){
        mCanvasMainFont = Res.getDimenValue(getApplication(), "jiuyi_font_main_canvas");
        mWidgetMainFont = Res.getDimenValue(getApplication(), "jiuyi_font_main_xml");
        mWidgetCommonFont = Res.getDimenValue(getApplication(), "jiuyi_font_common_xml");
        mTitleHeight = Res.getDimenValue(getApplication(), "jiuyi_titlebar_height");
        mToolHeight = Res.getDimenValue(getApplication(), "jiuyi_toolbar_height");
        mLineHeight = Res.getDimenValue(getApplication(), "jiuyi_tableline_height");


        Pub.fontColor = Res.getColor(getApplication(), "tzt_v23_comm_text_color");//
        Pub.BgColor = Res.getColor(getApplication(), "tzt_v23_comm_background_color");//
        Pub.hintcolor = Res.getColor(getApplication(), "tzt_v23_edit_hint_color");//
        mUpVersion = Res.getString(getApplication(), "jiuyi_upversion");
        mSysVersion = Res.getString(getApplication(), "jiuyi_sysversion");
    }

    /**
     * 获取通用字号
     * @return
     */
    public int getCanvasMainFont() {
        return mCanvasMainFont;
    }
    /**
     * 获取字号
     * @return
     */
    public int getCanvasHqFont() {
        return mCanvasHqFont;
    }



    /**
     * 获取标题栏的高度
     * @return
     */
    public int getTitleHeight() {
        return mTitleHeight;
    }
    /**
     * 获取底部工具栏的高度
     * @return
     */
    public int getToolHeight() {
        return mToolHeight;
    }
    /**
     * 获取默认每行高度
     * @return
     */
    public int getLineHeight() {
        return mLineHeight;
    }


    /**
     * 手机顶部状态栏高度
     * @return
     */
    public int getTopStatusBarHeight(Activity activity) {
        if(mTopStatusBarHeight <= 0){
            initStatusNaviNarHeight(activity);
        }
        return mTopStatusBarHeight;
    }

    /**
     * 手机底部虚拟导航栏高度
     * @return
     */
    public int getBottomNaviNarHeight(Activity activity) {
        if(mBottomNaviNarHeight <= 0){
            initStatusNaviNarHeight(activity);
        }
        return mBottomNaviNarHeight;
    }
    /**
     * 初始化手机顶部状态栏和底部虚拟导航栏高度
     * @return
     */
    public void initStatusNaviNarHeight(Activity activity){
        int[] naviHeight = Func.getNaviStatusBarSize(activity);
        mTopStatusBarHeight = naviHeight[0];
        mBottomNaviNarHeight = naviHeight[1];
    }
    /**
     * 获取popwindow的ypos
     * @return
     */
    public int getPopWindowYPos(int yPos){
        //系统5.1及其以上 不计算系统状态栏
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return yPos - mTopStatusBarHeight;
        }else{
            return yPos;
        }
    }

    /**
     * 初始化一些回调
     */
    private void initCallBack(){
    }

    /**
     * 判断包是否混淆过
     * 如果混淆过，HttpServer要启用缓存，不输出LOG 如果未混淆过，否则HttpServer不启用缓存方便调试，输出LOG信息
     * tips 默认的状态是混淆的
     */
    private void initProguard(){
        Rc myPub = null;// 非空即未加密
        try {
            String pubclass = ".Rc";
            myPub = (Rc) Class.forName("com.control.utils" + pubclass).newInstance();
            m_bPackageWithProguard = (myPub == null);
        } catch (Exception e) {
            m_bPackageWithProguard = true;
        }
        JiuyiLog.e("bPackageWithProguard", "initProguard。bPackageWithProguard=" + m_bPackageWithProguard);
    }

    public boolean isPackageWithProguard(){
        return m_bPackageWithProguard;
    }



    /**
     * 强制保存Rc里的参数
     */
    public void saveConfig(){
        if(mRcParamShared != null)
            mRcParamShared.onSaveData(getApplication());
    }


    /**
     * 切换皮肤
     */
    public void changeSkinType(int skintype){

        //(切换皮肤需要保存，不然在切换皮肤界面后置关闭app 就没有切换的效果了)
        saveConfig();

        initParam();

    }

    public static void ResetLogin(Context context) {
        name = ""; // 账号
         id=0;//用户id
         userName = ""; // 用户名
         deptName = ""; // 部门
         id_token = ""; // 全局token
         id_tokenparam = ""; // 全局入参token
         tim_signature = ""; // 消息sign
         tim_identifier = ""; // 消息identify
         deptid = -1; // 部门id
         mCheckInitDict=false;
         sapNumber="";
         map_ekv.clear();
//        JiuyiMobileRegistShared.getIns().mMobileCode="";
        JiuyiMobileRegistShared.getIns().mToken="";
//        JiuyiMobileRegistShared.getIns().mTimIdentify="";
        JiuyiMobileRegistShared.getIns().mTimSignature="";
        JiuyiMobileRegistShared.getIns().mName="";
        JiuyiMobileRegistShared.getIns().mUsername="";
        JiuyiMobileRegistShared.getIns().mId=0;
        JiuyiMobileRegistShared.getIns().onSaveData(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity());
    }

}
