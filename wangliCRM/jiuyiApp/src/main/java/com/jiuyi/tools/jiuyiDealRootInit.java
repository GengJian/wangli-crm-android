package com.jiuyi.tools;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.control.shared.JiuyiMobileRegistShared;
import com.control.shared.JiuyiUpdateInfoShared;
import com.control.tools.JiuyiInitRc;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;

import com.jiuyi.activity.common.activity.JiuyiHeadPageActivity;
import com.jiuyi.activity.common.activity.JiuyiHeadPageActivity.HeadPageCallBack;
import com.jiuyi.app.JiuyiActivityManager;

import java.util.Vector;

import customer.request.RequestServiceDate;

/**
 * ****************************************************************
 * 文件名称 : jiuyiDealRootInit.java
 * 作 者 :   zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 处理与启动相关的请求和操作
 * 主要任务 :	1、初始化Rc；
 * 2、预先请求数据
 * 注意事项: bIsChangedPage是为了标识是否已经跳出了HeadPageActivity；如果跳出了，弹出对话框和升级就再最新的activity显示

 * ****************************************************************
 */
public class jiuyiDealRootInit {
    /**
     * Rc对象
     */
    private Rc record;

    /**
     * 请求列表
     */
    private Vector<int[]> arrAction = new Vector<int[]>();//
    /**
     * 预先请求的对象
     */
    private jiuyiDealRootReq pJiuyiDealRootReq;
    /**
     * 启动页的回调
     */
    private HeadPageCallBack pCallBack;
    /**
     * 弹出升级提示框过程中，记录要跳转的界面号;如果是1则ChangeRootPage否则是ChangePage
     */
    private int mUpVersionChangePageType;//
    /**
     * 是否已经成功的进行了界面跳转，如果为true表示升级弹出框等要在已经调到的界面显示（防止界面已经跳转了，升级标志才请求到就关闭dialog的情况）
     */
    private boolean mIsChangedPage;//
    /**
     * Bundle
     */
    private Bundle mBundle = new Bundle();


    public jiuyiDealRootInit(HeadPageCallBack callback) {
        pCallBack = callback;
        mBundle = callback.getBundle();
    }

    /**
     * 初始化请求
     */
    public void onInitAll() {
        //初始化Rc
        onInitPreReq();
    }

    /**
     * 初始化请求之前初始化Rc
     */
    public void onInitPreReq() {

        //初始化Rc
        initRc(pCallBack.getActivity().getApplication());

        //预先请求
        pJiuyiDealRootReq = new jiuyiDealRootReq(pCallBack, arrAction);
    }

    /**
     * 初始化请求之前初始化Rc
     */
    private void initRc(Application application) {
        //实现一些底层调用上层的回调
        JiuyiActivityManager.getIns().setBaseCallTopCallBack();

        new JiuyiInitRc(application);
        record = Rc.getIns();

        //预先读取配置
        try{

            JiuyiMobileRegistShared.getIns();
            JiuyiUpdateInfoShared.getIns();

        }catch (Exception e){
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }

    /**
     * 是否已经成功的进行了界面跳转
     */
    public boolean isChangedPage() {
        return mIsChangedPage;
    }

    /**
     * 升级弹出升级提示框过程中，界面的跳转类型;如果是1则ChangeRootPage否则是ChangePage
     *
     * @return
     */
    public int getmUpVersionChangePageType() {
        // TODO Auto-generated method stub
        return mUpVersionChangePageType;
    }

    /**
     * 正在弹出升级对话框供用户选择，待选择后再跳转
     * 按确定键时是不允许在跳转到首页去的，直接退出软件
     * 安返回建，如果是强制升级则退出软件；如果是建议升级则继续请求并正常跳转
     * false表示当前没有升级dialog或已经关闭，true表示已经弹出了升级dialog但是尚没有关闭
     */
    public void setUpVersionDialogForbiddenChangePage() {
        // 允许跳转
        if(pJiuyiDealRootReq != null)
            pJiuyiDealRootReq.bUpVersionDialogForbiddenChangePage = false;
    }

    public void setUpVersionDialogNotAllowChangePage() {
        // 不允许跳转
        if(pJiuyiDealRootReq != null)
            pJiuyiDealRootReq.bUpVersionDialogForbiddenChangePage = true;
    }

    /**
     * 切换到首页
     */
    public void ChangeRootPage(Activity curActivity) {
        int nPageType = Pub.MENU_Customer;
        mUpVersionChangePageType = 1;
        //正在弹出升级对话框供用户选择，待选择后再跳转
        if (pJiuyiDealRootReq != null && pJiuyiDealRootReq.bUpVersionDialogForbiddenChangePage) {
            return;
        }
        if (isChangedPage())
            return;
        if(curActivity instanceof JiuyiHeadPageActivity){
            ((JiuyiHeadPageActivity) curActivity).getLoginInfo();
            if(!Func.IsStringEmpty(Rc.tim_signature) &&!Func.IsStringEmpty(Rc.tim_identifier) && !Func.IsStringEmpty(Rc.id_tokenparam)){
                ((JiuyiHeadPageActivity) curActivity).autoLogin();
            }else{
                //跳转登陆
                ((JiuyiHeadPageActivity) curActivity).changeLogin();
            }
        }


        //已经跳转了
        mIsChangedPage = true;
        //如果已经选择了升级，要在打开的界面后再升级，防止升级界面被跳转后的界面覆盖
        if(pJiuyiDealRootReq != null) {
            pJiuyiDealRootReq.gotoUpVersion();
        }
        //设置以及运行次数
        record.mRunCount++;
    }

    /**
     * 切换界面
     */
    public void ChangePage(Activity curActivity, int nPageType) {
        mUpVersionChangePageType = nPageType;
        //正在弹出升级对话框供用户选择，待选择后再跳转
        //（完善判断条件）
        if (pJiuyiDealRootReq != null && pJiuyiDealRootReq.bUpVersionDialogForbiddenChangePage) {
            return;
        }
        if (isChangedPage())
            return;

        //(需要把页面功能号传参进去，不然后面页面获取不到这个pagetype，跳转出错)
        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        JiuyiActivityManager.startNextActivity(curActivity, JiuyiActivityManager.getActivityByPageType(mBundle, nPageType), mBundle, false);
        //已经跳转了
        mIsChangedPage = true;
        //如果已经选择了升级，要在打开的界面后再升级，防止升级界面被跳转后的界面覆盖
        pJiuyiDealRootReq.gotoUpVersion();
        //设置以及运行次数
        record.mRunCount++;
    }

}
