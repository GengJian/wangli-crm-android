package com.jiuyi.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;

import com.control.callback.JiuyiBaseCallTopCallBack;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.blurbehind.BlurBehind;
import com.control.utils.blurbehind.OnBlurCompleteListener;

import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.JiuyiFastToast;
import com.jiuyi.activity.common.activity.JiuyiChangePasswordActivity;
import com.jiuyi.activity.common.activity.JiuyiCommonFormActivity;
import com.jiuyi.activity.common.activity.JiuyiLoginActivity;
import com.jiuyi.activity.common.activity.JiuyiRootActivity;

import com.jiuyi.activity.common.activity.JiuyiWebviewActivity;

import com.jiuyi.activity.common.activity.JiuyiWebviewPictureActivity;
import com.jiuyi.activity.common.activity.TbsReaderActivity;
import com.jiuyi.activity.passwordlock.activity.JiuyiPasswordLockActivity;
import com.jiuyi.activity.passwordlock.activity.JiuyiPasswordLockSetActivity;
import com.jiuyi.activity.passwordlock.activity.JiuyiPasswordLockTimeSetActivity;
import com.jiuyi.activity.passwordlock.activity.JiuyiPasswordUnLockedActivity;
import com.jiuyi.tools.jiuyiOperateApp;

import java.util.Stack;


import commonlyused.activity.JiuyiChannelDevelopNewActivity;
import commonlyused.activity.JiuyiContactSearchActivity;
import commonlyused.activity.JiuyiDirectSalesNewActivity;
import commonlyused.activity.JiuyiFourMarketPlanNewActivity;
import commonlyused.activity.JiuyiHuaJueMarketNewActivity;
import commonlyused.activity.JiuyiJinMumenMarketNewActivity;
import commonlyused.activity.JiuyiMarketEngineerNewActivity;
import commonlyused.activity.JiuyiNengChengMarketNewActivity;
import commonlyused.activity.JiuyiNormalCompanyAddressListActivity;
import commonlyused.activity.JiuyiNormalCustomerContactActivity;
import commonlyused.activity.JiuyiNormalDeptAddressListActivity;
import commonlyused.activity.JiuyiNormalImportantContactActivity;
import commonlyused.activity.JiuyiNormalNewFeedbackActivity;
import commonlyused.activity.JiuyiNormalNewTaskActivity;
import commonlyused.activity.JiuyiNormalOrgAddressListActivity;
import commonlyused.activity.JiuyiNormalsubordinateContactsActivity;
import commonlyused.activity.JiuyiNormalTaskListActivity;
import commonlyused.activity.JiuyiStrategicEngineeringNewActivity;
import commonlyused.activity.JiuyiTaskSearchActivity;
import customer.activity.ImageBrowseActivity;
import customer.activity.JiuyiAddPlanProductActivity;
import customer.activity.JiuyiAddProductActivity;
import customer.activity.JiuyiAssistantNewActivity;
import customer.activity.JiuyiCommonInputInfoActivity;
import customer.activity.JiuyiCommunicationRecordNewActivity;
import customer.activity.JiuyiCustomerAssistantActivity;
import customer.activity.JiuyiCustomerClaimActivity;
import commonlyused.activity.JiuyiCustomerContactsInfoActivity;
import customer.activity.JiuyiCustomerDetailActivity;
import customer.activity.JiuyiCustomerInfomationActivity;
import customer.activity.JiuyiCustomerMainActivity;
//import customer.activity.JiuyiCustomerManualSortActivity;
import customer.activity.JiuyiPaintPointNewActivity;
import customer.activity.JiuyiRecordPlayActivity;
import dynamic.activity.JiuyiCustomerNewBusinessActivity;
import dynamic.activity.JiuyiCustomerNewClueActivity;
import customer.activity.JiuyiCustomerNewCompeteInfoActivity;
import customer.activity.JiuyiCustomerNewComplaintActivity;
import customer.activity.JiuyiCustomerNewConsultationActivity;
import customer.activity.JiuyiCustomerNewEquipmentActivity;
import customer.activity.JiuyiCustomerNewLabelActivity;
import customer.activity.JiuyiCustomerNewLinkmanActivity;
import customer.activity.JiuyiCustomerNewMarketComplaintActivity;
import customer.activity.JiuyiCustomerNewMarketDynamicActivity;
import customer.activity.JiuyiCustomerNewNeedPlanActivity;
import commonlyused.activity.JiuyiRetailChannelNewActivity;
import customer.activity.JiuyiCustomerNewProductBondActivity;
import customer.activity.JiuyiCustomerNewProductCheckActivity;
import customer.activity.JiuyiCustomerNewProductDynamicActivity;
import customer.activity.JiuyiCustomerNewProductImportActivity;
import customer.activity.JiuyiCustomerNewProductInfoActivity;
import customer.activity.JiuyiCustomerNewProductLandActivity;
import customer.activity.JiuyiCustomerNewProductLicenseActivity;
import customer.activity.JiuyiCustomerNewProductMaterialActivity;
import customer.activity.JiuyiCustomerNewProductPurchaseActivity;
import customer.activity.JiuyiCustomerNewProductTaxActivity;
import customer.activity.JiuyiCustomerNewReceiptAddressActivity;
import customer.activity.JiuyiCustomerNewReceiptPlanActivity;
import customer.activity.JiuyiCustomerNewRecruitmentActivity;
import customer.activity.JiuyiCustomerNewReplyActivity;
import customer.activity.JiuyiCustomerNewRiskActivity;
import customer.activity.JiuyiCustomerNewTradeContactActivity;
import customer.activity.JiuyiCustomerPictureActivity;
import customer.activity.JiuyiCustomerProductSearchActivity;
import customer.activity.JiuyiCustomerProductStatusActivity;
import customer.activity.JiuyiCustomerReceiptDetailActivity;
import customer.activity.JiuyiCustomerReleaseActivity;
import customer.activity.JiuyiCustomerSearchActivity;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.activity.JiuyiCustomerTradeActivity;
import customer.activity.JiuyiCustomerTradeDetailInfo;
import customer.activity.JiuyiCustomerTransferActivity;
import customer.activity.JiuyiCustomerUpdateColInfoActivity;
import customer.activity.JiuyiCustomerUpdateCreditActivity;
import customer.activity.JiuyiCustomerVisitDetailActivity;
import customer.activity.JiuyiNewCustomerActivity;
import customer.activity.JiuyiNewPlanMultiActivity;
import customer.activity.JiuyiNewPlanMutiProductActivity;
import customer.activity.JiuyiNewPlanOrderActivity;
import customer.activity.JiuyiNewSpecialPriceActivity;


import customer.activity.JiuyiRecordActivity;
import dynamic.activity.JiuyiDynamicDetailActivity;
import commonlyused.activity.JiuyiPlanListActivity;
import messages.timchat.ui.JiuyiChatActivity;
import messages.timchat.ui.JiuyiMessageDetailActivity;
import messages.timchat.ui.JiuyiPopStartActivity;
import mine.activity.JiuyiMineAboutusActivity;
import mine.activity.JiuyiMineCollectionActivity;
import mine.activity.JiuyiMineDeliveryReceiptPlanActivity;
import mine.activity.JiuyiMineNewFeedbackActivity;
import mine.activity.JiuyiMineSignRecordActivity;
import mine.activity.JiuyiMineSettingActivity;
import mine.activity.JiuyiMineSignActivity;
import dynamic.activity.JiuyiOrderSearchActivity;


/**
 * 管理所有Activity 当启动一个Activity时，就将其保存到Stack中， 退出时，从Stack中删除 
 * 导出javadoc，要配置-encoding utf-8 -charset utf-8
 * @version v1.0
 * 创建时间:2018/3/26 14:43
 */  
public class JiuyiActivityManager
{
    private static JiuyiActivityManager ins;
    private JiuyiBaseCallTopCallBack mBaseCallTopCallBack;



    public void setBaseCallTopCallBack(){
        if(mBaseCallTopCallBack == null) {
            mBaseCallTopCallBack = new JiuyiBaseCallTopCallBack() {
                @Override
                public void doAppExit() {
                    new jiuyiOperateApp().doExit();
                }
                @Override
                public void gotoUpVersion(Activity activity, String mUpdateUrl, int updateType) {
                    if(activity == null)
                        activity = getCurrentActivity();
                    //打开升级链接
                    if (!Func.IsStringEmpty(mUpdateUrl) && activity != null){
                        try {
                            Uri uri = Uri.parse(mUpdateUrl);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            activity.startActivity(intent);//startWeb
                        } catch (Exception e) {
                            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
                        }
                    }
                }
                @Override
                public String getPushMsgUniqueFlag() {
                    return null;
                }
                @Override
                public boolean getIsAutoPushMsg() {
                    return true;
                }
                @Override
                public void setIsAutoPushMsg(boolean isAutoPush) {
                    return ;
                }




                /**
                 * 获得当前栈顶的Activity
                 */
                @Override
                public Activity getCurrentActivity() {
                    return JiuyiActivityManager.getCurrentActivity();
                }



                /**
                 * 获得当前栈顶的Activity的RelativeLayout
                 */
                @Override
                public JiuyiRelativeLayout getRelativeLayout() {
                    return getLastRelativeLayout();
                }


                /**
                 * 跳转到指定界面，一般是退回到RootActivity
                 * 如果bstartPageTypeActivity为flase，则只是吧nPageType之前的Activity都finish掉，否则就是要退到这个Activity
                 * @nPageType 	要退到的activity的pagetype，如果要返回到首页，则参数为Pub.mStartHomePage即可
                 * @bstartPageTypeActivity	是否要打开要退到的activity的pagetype的Activity
                 * @return 是否成功跳转
                 */
                @Override
                public void popBackToActivity(int nPageType, boolean bstartPageTypeActivity) {
                    popBackToActivityNew(nPageType, bstartPageTypeActivity);
                }

                /**
                 * @param activity    要移除的activity
                 * @param bTransition 是否带动画
                 */
                @Override
                public void popActivity(Activity activity, boolean bTransition) {
                    JiuyiActivityManager.popActivity(activity,bTransition);
                }

                /**
                 * 是否需要密码锁屏
                 */
                @Override
                public boolean isNeedPasswordLockPage() {
                    //跳转锁屏界面，
                    Activity activity = JiuyiActivityManager.getCurrentActivity();
                    if(activity!=null && activity instanceof JiuyiActivityBase){
                        //非后置状态并且当前是界面的才跳转锁屏
                        if(((JiuyiActivityBase)activity).mPageType >0){
                            int nPageType = ((JiuyiActivityBase)activity).mPageType;
                            if(JiuyiPasswordLockShared.getIns().IsNeedPasswordLockPage(nPageType)) {
                                return true;
                            }
                        }
                    }
                    return true;
                }

                /**
                 * 当前显示activity是否被后置
                 * @return
                 */
                @Override
                public boolean isCurrActivityStop() {
                    Activity activity = JiuyiActivityManager.getCurrentActivity();
                    if(activity!=null && activity instanceof JiuyiActivityBase){
                        return ((JiuyiActivityBase)activity).currActivityStop();
                    }
                    return false;
                }

                /**
                 * 切换界面
                 *
                 * @param nPageType 要跳转的界面号
                 * @param bSavePage 是否要保存当前界面，保存后可以在返回到这个界面
                 */
                @Override
                public void changePage(Bundle mBundle, int nPageType, boolean bSavePage) {
                    Activity activity = JiuyiActivityManager.getCurrentActivity();
                    if(activity!=null){
                        if(mBundle == null){
                            mBundle = new Bundle();
                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
                        }else{
                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
                        }
                        startNextActivity(activity, null, mBundle, bSavePage);
                    }
                }

                /**
                 * 重新设置首页标题头（切换时使用）
                 */
                @Override
                public void resetRootActivityTitle() {
                    Activity activity = JiuyiActivityManager.getCurrentActivity();
                    if(activity != null && activity instanceof JiuyiRootActivity){
                        ((JiuyiRootActivity)activity).changeTitleLayout();
                    }
                }

                /**
                 * 请求推送登录
                 */
                @Override
                public void createReqPushMsgTradeLogin(String account, String khbranch, Context context) {

                }

                @Override
                public void startLoginPage() {
                    changePage(null,Pub.ResetLogin,false);
                }
            };
            Rc.getIns().setBaseCallTopCallBack(mBaseCallTopCallBack);
        }
    }

    public static JiuyiActivityManager getIns(){
        if(ins == null)
            ins = new JiuyiActivityManager();
        return ins;
    }

    /**
     * 保存所有Activity
     */
    private static volatile Stack<Activity> mActivityStack = new Stack<Activity>();


    /**
     * 保存的Activity是否为空
     * 判断程序是否在后台运行
     */
    public static boolean isStackEmpty(){
    	return mActivityStack ==null || mActivityStack.size()<=0;
    }

    /**
     * add by zhengss
     * 退出时判断栈中小于等于1个Activity，且不是首页。需要跳转到首页
     * @return
     */
    public static boolean needBackToHomePage(int mPageType) {
        if ((isStackEmpty() || mActivityStack.size() == 1) && mPageType != Pub.m_nStartHomePage) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 如果存在则删除Activity
     * 如果Stack里包含该Activity，极有可能是动态调整了分辨率
     * 所以需要重新初始化与分辨率有关的变量
     * @param activity Activity
     */
    public static boolean removeActivityWhenContain(Activity activity, boolean bTransition) {
        if(activity == null)
            return false;
        if(mActivityStack.contains(activity)){
            if(Res.getDensityChanged()) {
                Res.GetDisplayParam();
                Rc.getIns().initParam();
                Rc.getIns().initStatusNaviNarHeight(activity);
            }
            mActivityStack.remove(activity);
            return true;
        }else{
            return false;
        }
    }

    /** 
     * 退出Activity 
     *  
     * @param activity Activity 
     */  
    public static void popActivity(Activity activity, boolean bTransition)  
    {  
        if (activity != null && mActivityStack !=null)
        {
            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
        	//关闭某个activity就要关闭，否则很可能不能完全退出
        	if(activity instanceof JiuyiActivityBase){
        		((JiuyiActivityBase)activity).finishWithAnim(bTransition);
        	}

        	if(mActivityStack.contains(activity)){
        		mActivityStack.remove(activity);
        	}
        }  
    }   
      
    /** 
     * 将当前Activity推入栈中 
     *  
     * @param activity Activity 
     */  
    public static void pushActivity(Activity activity)  
    {  
    	if(activity == null)
    		return;
    	
    	//当前的Activity的功能号
    	int pushpagetype = -1;
    	if(activity instanceof JiuyiActivityBase){
    		if(activity != null)
    			pushpagetype = ((JiuyiActivityBase)activity).getPageType();
    	}
    	//当前的Activity是jiuyiActivityBase时
    	if(pushpagetype > 0){
	        for (int i = 0; i< mActivityStack.size(); i++)
	        {  
	        	Activity tmpactivity = mActivityStack.get(i);
	        	//activity和cls的比较是activity.getClass().equals(cls)
	        	//activity和activity的比较是activity.equals(activity)
	            if (tmpactivity != null) {
	                if(tmpactivity instanceof JiuyiActivityBase){
	                	//临时Activity的功能号
	                	int tmpPageType = ((JiuyiActivityBase)tmpactivity).getPageType();
	                	if(tmpPageType > 0){
	    	            	if(pushpagetype == tmpPageType){
	    	            		//如果功能号相同，需要移除老的界面，但是如果是网页的话就不需要移除老的界面
	    	            		if(!(tmpactivity instanceof JiuyiWebviewActivity) && !(tmpactivity instanceof JiuyiWebviewActivity)){
	    	            			popActivity(tmpactivity, false);
	    	            			//这里做的是i-1，是因为pop掉一个activity导致activityStack的size减少了1
	    	            			i--;
	    	            		}
	    	            	}
	                	}
	                }
	            }
	        }  
    	}
        mActivityStack.add(activity);
    }  
    
    
    /** 
     * 获得当前栈顶的Activity
     * @return Activity Activity 
     */  
    public static Activity getCurrentActivity()  
    {  
        Activity activity = null;  
        if (!mActivityStack.empty())
        {  
            activity = mActivityStack.lastElement();
        }  
        return activity;  
    }

    /**
     * 获得当前栈的最后一个jiuyiActivityBase
     * @return JiuyiActivityBase Activity
     */
    public static JiuyiRelativeLayout getLastRelativeLayout()
    {
        if(null == mActivityStack)
        {
            return null;
        }
        //倒序移除，最新打开的activity在最后
        Stack<Activity> pactivityStack = mActivityStack;
        for (int i=pactivityStack.size()-1; i>=0;i--) {
            if (pactivityStack.get(i) instanceof JiuyiActivityBase) {
                return ((JiuyiActivityBase)pactivityStack.get(i)).mBodyLayout;
            }
        }
        return null;
    }



      
    /**
     * 退出除首页之外的其他的Activity 
     * nPageType 	要退到的activity的pagetype，如果要返回到首页，则参数为Pub.mStartHomePage即可
     * bstartPageTypeActivity	是否要打开要退到的activity的pagetype的Activity
     * 如果bstartPageTypeActivity为flase，则只是吧nPageType之前的Activity都finish掉，否则就是要退到这个Activity
     */
    /**
     * 回退到指定Activity!
     * @param nPageType              目标Activity界面号
     * @param bstartPageTypeActivity 回退后是否onResume
     * @return 是否可以回退
     */
    public static boolean popBackToActivityNew(int nPageType, boolean bstartPageTypeActivity) {
        Class<?> newActivity = null;
        if(nPageType == Pub.m_nStartHomePage){
            newActivity = JiuyiRootActivity.class;
        }

        //判断是否可以跳转
        Activity activity = getCurrentActivity();
        if (activity == null || newActivity == null || activity.getClass() == newActivity){
            return false;
        }else {
            while (true){
                activity = getCurrentActivity();
                if(activity == null)
                    break;
                if(activity.getClass() == newActivity) {
                    if (activity instanceof JiuyiActivityBase && bstartPageTypeActivity) {
                        ((JiuyiActivityBase) activity).onResume();
                    }
                    return true;
                }
                popActivity(activity, false);
            }
            return false;
        }
    }

    /**
     * 退出栈中所有Activity
     *
     */
    public static void popAllActivity()
    {
        while (mActivityStack !=null && !mActivityStack.empty())
        {  
            Activity activity = getCurrentActivity();  
            if (activity == null)  
            {  
                break;  
            }  
             
            popActivity(activity, false);  
        }  
    }
    /**
     * 获取Intent
     * @param curActivity 当前显示的curActivity
     * @param newActivity 要显示的Activity的名称
     * @param bundle 参数
     * @param bSaveCurrActivity 是否要保存当前的activity而不是新的activity
     */
    private static Intent doGetActivityIntent(Activity curActivity, Class<?> newActivity, Bundle bundle, boolean bSaveCurrActivity)
    {
        if(!bSaveCurrActivity){
            popActivity(curActivity, false);
        }
        int npagetype = -1;
        if(newActivity == null){
            if(bundle != null){
                npagetype = bundle.getInt(JiuyiBundleKey.PARAM_PAGETYPE);
                if(npagetype <= 0)
                    npagetype = Func.parseInt(bundle.getString(JiuyiBundleKey.PARAM_PAGETYPE));
            }
            newActivity = getActivityByPageType(bundle, npagetype);

        }

        if(newActivity == null){
            JiuyiFastToast.show(Rc.getApplication(), "该功能尚未开放，敬请期待！");
            return null;
        }

        Intent intent = new Intent(Rc.getApplication(), newActivity);
        if(bundle!=null){
            //（这里需要更改为转换后的页面功能好，在后台线程任务检测是到锁屏的时候，会获取当前界面的功能号来判定是否需要跳转锁屏）
            intent.putExtras(bundle);
            if(npagetype == Pub.PasswordLock_Lock){
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        }
        return intent;
    }
    /**
     * 打开activity
     * @param curActivity 当前显示的curActivity
     * @param newActivity 要显示的Activity的名称
     * @param bundle 参数
     * @param bSaveCurrActivity 是否要保存当前的activity而不是新的activity
     */
    public static void startNextActivity(final Activity curActivity, Class<?> newActivity, Bundle bundle, boolean bSaveCurrActivity)
    {
        if(curActivity == null){
            return;
        }
        final Intent intent = doGetActivityIntent(curActivity, newActivity, bundle, bSaveCurrActivity);
        if(intent != null) {
            if(intent.toString().indexOf(JiuyiPasswordUnLockedActivity.class.getSimpleName()) > 0){
                //密码解锁需要背景做雾化效果
                BlurBehind.getInstance().execute(curActivity, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {
                        curActivity.startActivity(intent);
                    }
                });
            }else {
                curActivity.startActivity(intent);
            }
        }
        JiuyiLog.e("zss", "ActivityManager--start:"+ SystemClock.currentThreadTimeMillis());
        if(!bSaveCurrActivity){
            curActivity.finish();
        }
    }

    /**
     * 打开activity
     * @param curActivity 当前显示的curActivity
     * @param newActivity 要显示的Activity的名称
     * @param bundle 参数
     * @param bSaveCurrActivity 是否要保存当前的activity而不是新的activity
     * @param requestCode
     */
    public static void startNextActivityForResult(final Activity curActivity, Class<?> newActivity, Bundle bundle, boolean bSaveCurrActivity, int requestCode)
    {  
        if(curActivity == null)
        	return;

        final Intent intent = doGetActivityIntent(curActivity, newActivity, bundle, bSaveCurrActivity);
        if(intent != null) {
            if(intent.toString().indexOf(JiuyiPasswordUnLockedActivity.class.getSimpleName()) > 0){
                //密码解锁需要背景做雾化效果
                BlurBehind.getInstance().execute(curActivity, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {
                        curActivity.startActivity(intent);
                    }
                });
            }else{
                curActivity.startActivityForResult(intent, requestCode);
            }
        }
        
        if(!bSaveCurrActivity){
        	curActivity.finish();
        }
    }

    /**
     * nPageType对应的Activity的名称
     */
    public static Class<?> getActivityByPageType(Bundle bundle, int nPageType){


        if( bundle != null){
            bundle.putInt(JiuyiBundleKey.PARAM_NEXTPAGERTYPE,nPageType);

        }
        switch(nPageType){
            default: {
                popAllActivity();
                return JiuyiRootActivity.class;
            }
            //RootLayout的界面，所以要执行popAllActivity();
            case Pub.MENU_Message:
            case Pub.MENU_Trip:
            case Pub.MENU_Normal:
            case Pub.MENU_Mine:
            case Pub.MENU_Customer:
                popAllActivity();
                return JiuyiRootActivity.class;
            case Pub.JY_MENU_OpenWebInfoContent:
            case Pub.HttpServer:
                return JiuyiWebviewActivity.class;
            case Pub.JY_MENU_OpenPicture:
                return JiuyiWebviewPictureActivity.class;
            case Pub.JY_MENU_OpenPictureGroup:
                return ImageBrowseActivity.class;
            case Pub.JY_MENU_OpenReqFile:
                return TbsReaderActivity.class;
//                return JiuyiPdfViewActivity.class;
            case Pub.ResetLogin:
                return JiuyiLoginActivity.class;
            case Pub.JY_MENU_PasswordLock:
                return JiuyiPasswordLockActivity.class;
            case Pub.PasswordLock_Open:
            case Pub.PasswordLock_Close:
            case Pub.PasswordLock_ResetPassword:
            case Pub.PasswordLock_OpenFingerLock:
            case Pub.PasswordLock_CloseFingerLock:
                return JiuyiPasswordLockSetActivity.class;
            case Pub.PasswordLock_Lock:
                return JiuyiPasswordUnLockedActivity.class;
            case Pub.PasswordLock_SetLockTime:
                return JiuyiPasswordLockTimeSetActivity.class;
            case Pub.MSG_detail:
                return JiuyiMessageDetailActivity.class;
            case Pub.MSG_chat:
                return JiuyiChatActivity.class;
            case Pub.Customer_detail:
            case Pub.Customer_baseinfo:
            case Pub.Customer_PersonnelOrganization:
            case Pub.Customer_FinancialRisk:
            case Pub.Customer_Purchase:
            case Pub.Customer_ProductionStatus:
            case Pub.Customer_Sales:
            case Pub.Customer_Research:
            case Pub.Customer_Visit:
            case Pub.Customer_Business:
            case Pub.Customer_Contract:
            case Pub.Customer_Service:
            case Pub.Customer_Cost:
            case Pub.Customer_SystemInfo:
                return JiuyiCustomerDetailActivity.class;
            case Pub.Customer_transactioncontract:
            case Pub.Customer_tradeorder:
            case Pub.Customer_tradedelivery:
            case Pub.Customer_tradeinvoice:
            case Pub.Customer_tradetelemoney:
            case Pub.Customer_tradelogistics:
            case Pub.Customer_tradebankstatement:
                return JiuyiCustomerTradeActivity.class;
            case Pub.Customer_productinfo:
            case Pub.Customer_productequipment:
            case Pub.Customer_productdynamic:
            case Pub.Customer_productmaterial:
            case Pub.Customer_productcompetition:
            case Pub.Customer_productrecruitmentts:
            case Pub.Customer_productlicense:
            case Pub.Customer_productpurchase:
            case Pub.Customer_productimport:
            case Pub.Customer_productrating:
            case Pub.Customer_productcheck:
            case Pub.Customer_productbond:
            case Pub.Customer_productland:
                return JiuyiCustomerProductStatusActivity.class;
            case Pub.Customer_main:
                return JiuyiCustomerMainActivity.class;
            case Pub.Customer_newlinkman:
                return JiuyiCustomerNewLinkmanActivity.class;
            case Pub.Customer_new:
                return JiuyiNewCustomerActivity.class;
            case Pub.Customer_newreceiptaddress:
                return JiuyiCustomerNewReceiptAddressActivity.class;
            case Pub.Customer_newrisk:
                return JiuyiCustomerNewRiskActivity.class;
            case Pub.Customer_newReceiptplan:
                return JiuyiCustomerNewReceiptPlanActivity.class;
            case Pub.Customer_newneedplan:
                return JiuyiCustomerNewNeedPlanActivity.class;
            case Pub.Customer_newneedplanmulti:
                return JiuyiNewPlanMultiActivity.class;
            case Pub.Customer_newneedplanmultiProduct:
                return JiuyiNewPlanMutiProductActivity.class;
            case Pub.Customer_newLabel:
                return JiuyiCustomerNewLabelActivity.class;
            case Pub.Customer_newproductinfo:
                return JiuyiCustomerNewProductInfoActivity.class;
            case Pub.Customer_newproductequipment:
                return JiuyiCustomerNewEquipmentActivity.class;
            case Pub.Customer_newcompeteinfo:
                return JiuyiCustomerNewCompeteInfoActivity.class;
            case Pub.Customer_newproductcheck:
                return JiuyiCustomerNewProductCheckActivity.class;
            case Pub.Customer_newproductbond:
                return JiuyiCustomerNewProductBondActivity.class;
            case Pub.Customer_newproductland:
                return JiuyiCustomerNewProductLandActivity.class;
            case Pub.Customer_newproductdynamic:
                return JiuyiCustomerNewProductDynamicActivity.class;
            case Pub.Customer_newproductimport:
                return JiuyiCustomerNewProductImportActivity.class;
            case Pub.Customer_newproductlicense:
                return JiuyiCustomerNewProductLicenseActivity.class;
            case Pub.Customer_newproductmaterial:
                return JiuyiCustomerNewProductMaterialActivity.class;
            case Pub.Customer_newproductpurchase:
                return JiuyiCustomerNewProductPurchaseActivity.class;
            case Pub.Customer_newproducttax:
                return JiuyiCustomerNewProductTaxActivity.class;
            case Pub.Customer_newrecruitment:
                return JiuyiCustomerNewRecruitmentActivity.class;
            case Pub.Customer_newmarketdynamic:
                return JiuyiCustomerNewMarketDynamicActivity.class;
            case Pub.Customer_newmarketcomplain:
                return JiuyiCustomerNewMarketComplaintActivity.class;
            case Pub.Customer_newtradecontact:
                return JiuyiCustomerNewTradeContactActivity.class;
            case Pub.Customer_transfer:
                return JiuyiCustomerTransferActivity.class;
            case Pub.Customer_release:
                return JiuyiCustomerReleaseActivity.class;
            case Pub.Customer_claim:
                return JiuyiCustomerClaimActivity.class;
            case Pub.Customer_newspecialprice:
                return JiuyiNewSpecialPriceActivity.class;
            case Pub.Customer_newspecialpriceproduct:
                return JiuyiAddProductActivity.class;
            case Pub.Customer_newplanorder:
                return JiuyiNewPlanOrderActivity.class;
            case Pub.Customer_newplanorderproduct:
                return JiuyiAddPlanProductActivity.class;
            case Pub.CustomerChangeCredit:
                return JiuyiCustomerUpdateCreditActivity.class;
            case Pub.Customer_search:
                return JiuyiCustomerSearchActivity.class;
            case Pub.Customerproduct_search:
                return JiuyiCustomerProductSearchActivity.class;
            case Pub.Customerpicture:
                return JiuyiCustomerPictureActivity.class;
            case Pub.CustomerupdatecolInfo:
                return JiuyiCustomerUpdateColInfoActivity.class;
            case Pub.Customer_tradeDetail:
                return JiuyiCustomerTradeDetailInfo.class;
            case Pub.CustomerSelect:
                return JiuyiCustomerSelectActivity.class;
            case Pub.MSG_PopStart:
                return JiuyiPopStartActivity.class;
            case Pub.Mine_deliveryReceiptPlan:
            case Pub.Mine_deliveryPlan:
            case Pub.Mine_ReceiptPlan:
            case Pub.Mine_deliveryPlanTotal:
                return JiuyiMineDeliveryReceiptPlanActivity.class;
            case Pub.Mine_Collection:
                return JiuyiMineCollectionActivity.class;
            case Pub.Mine_NewFeedback:
                return JiuyiMineNewFeedbackActivity.class;
            case Pub.Mine_Setting:
                return JiuyiMineSettingActivity.class;
            case Pub.Mine_Aboutus:
                return JiuyiMineAboutusActivity.class;
            case Pub.Mine_Sign:
                return JiuyiMineSignActivity.class;
            case Pub.Normal_OrgAddresslist:
                return JiuyiNormalOrgAddressListActivity.class;
            case Pub.Normal_DeptAddresslist:
                return JiuyiNormalDeptAddressListActivity.class;
            case Pub.Normal_subordinateContacts:
                return JiuyiNormalsubordinateContactsActivity.class;
            case Pub.Normal_ImportantContacts:
                return JiuyiNormalImportantContactActivity.class;
            case Pub.Normal_CustomerContacts:
                return JiuyiNormalCustomerContactActivity.class;
            case Pub.Normal_CompanyAddresslist:
                return JiuyiNormalCompanyAddressListActivity.class;
            case Pub.Normal_ContactsInfo:
                return JiuyiCustomerContactsInfoActivity.class;
            case Pub.Normal_TaskList:
                return JiuyiNormalTaskListActivity.class;
            case Pub.Normal_NewTask:
                return JiuyiNormalNewTaskActivity.class;
            case Pub.Normal_NewFeedback:
                return JiuyiNormalNewFeedbackActivity.class;
            case Pub.Orders_search:
                return JiuyiOrderSearchActivity.class;
            case Pub.Normal_ContactSearch:
                return JiuyiContactSearchActivity.class;
            case Pub.Normal_TaskSearch:
                return JiuyiTaskSearchActivity.class;
            case Pub.JY_COMMON_NEWACTIVITY:

            case Pub.Customer_newDynamicForm:
            case Pub.Customer_newpurchase:
            case Pub.Customer_newstandard:
            case Pub.Customer_newevaluation:
            case Pub.Customer_NewProductionStandard:
            case Pub.Customer_NewPoductInformation:
            case Pub.Customer_NewPoductEquipment:
            case Pub.Customer_NewPoductCapacity:
            case Pub.Customer_NewPoductQualityStandard:
            case Pub.Customer_NewIqc:
            case Pub.Customer_NewPoductDynamic:
            case Pub.Customer_NewCtm:
            case Pub.Customer_NewSaleSystem:
            case Pub.Customer_NewCustomerDirectory:
            case Pub.Customer_NewSalesQuotation:
            case Pub.Customer_NewImportsAndExports:
            case Pub.Customer_NewResearchStandards:
            case Pub.Customer_NewPatentAuthentication:
            case Pub.Customer_NewTechnologyRoadmaps:
            case Pub.Customer_NewLaboratory:
            case Pub.Customer_NewVisit:
            case Pub.Dy_IntelligenceNew:
            case Pub.Dy_ActivityNew:
            case Pub.Dy_SampleNew:
                return JiuyiCommonFormActivity.class;
            case Pub.Customer_newlinkmanneed:
                return JiuyiPaintPointNewActivity.class;
            case Pub.Customer_VisitDetail:
                return JiuyiCustomerVisitDetailActivity.class;
            case Pub.Customer_ReceiptDetail:
                return JiuyiCustomerReceiptDetailActivity.class;
            case Pub.Customer_CommonInput:
                return JiuyiCommonInputInfoActivity.class;
            case Pub.Customer_CommuniactionRecordNew:
                return JiuyiCommunicationRecordNewActivity.class;
            case Pub.Customer_InformationNew:
                return JiuyiCustomerInfomationActivity.class;
            case Pub.Customer_RecordNew:
                return JiuyiRecordActivity.class;
            case Pub.Customer_RecordPlay:
                return JiuyiRecordPlayActivity.class;
            case Pub.Customer_NewConsultation:
                return JiuyiCustomerNewConsultationActivity.class;
            case Pub.Customer_NewReply:
                return JiuyiCustomerNewReplyActivity.class;
            case Pub.Customer_NewComplaint:
                return JiuyiCustomerNewComplaintActivity.class;
            case Pub.Dy_BusinessNew:
                return JiuyiCustomerNewBusinessActivity.class;
            case Pub.Dy_OfferNew:
                return JiuyiRetailChannelNewActivity.class;
            case Pub.Dy_ClueNew:
                return JiuyiCustomerNewClueActivity.class;
            case Pub.Normal_RetailChannel:
            case Pub.Normal_ChannelDevelopemnt:
            case Pub.Normal_MarketEngineering:
            case Pub.Normal_StrategicEngineering:
            case Pub.Normal_DirectSalesEngineering:
            case Pub.Normal_NengChengSales:
            case Pub.Normal_HuaJueSales:
            case Pub.Normal_JinMumenSales:
            case Pub.Normal_MumenSales:
            case Pub.Normal_LvMumenSales:
            case Pub.Normal_CopperSales:
            case Pub.Normal_IntelligentLock:
                return JiuyiPlanListActivity.class;
            case Pub.Normal_RetailChannelNew:
                return JiuyiRetailChannelNewActivity.class;
            case Pub.Normal_ChannelDevelopemntNew:
                return JiuyiChannelDevelopNewActivity.class;
            case Pub.Normal_MarketEngineeringNew:
                return JiuyiMarketEngineerNewActivity.class;
            case Pub.Normal_StrategicEngineeringNew:
                return JiuyiStrategicEngineeringNewActivity.class;
            case Pub.Normal_DirectSalesEngineeringNew:
                return JiuyiDirectSalesNewActivity.class;
            case Pub.Normal_NengChengSalesNew:
                return JiuyiNengChengMarketNewActivity.class;
            case Pub.Normal_HuaJueSalesNew:
                return JiuyiHuaJueMarketNewActivity.class;
            case Pub.Normal_JinMumenSalesNew:
//                return JiuyiJinMumenMarketNewActivity.class;
            case Pub.Normal_MumenSalesNew:
            case Pub.Normal_LvMumenSalesNew:
            case Pub.Normal_CopperSalesNew:
            case Pub.Normal_IntelligentLockNew:
                return JiuyiFourMarketPlanNewActivity.class;
            case Pub.Dynamic_detail:
                return JiuyiDynamicDetailActivity.class;
            case Pub.Mine_Date:
                return JiuyiMineSignRecordActivity.class;
            case Pub.PasswordChange:
                return JiuyiChangePasswordActivity.class;
            case Pub.Customer_AssistantList:
                return JiuyiCustomerAssistantActivity.class;
            case Pub.Customer_NewAssistant:
                return JiuyiAssistantNewActivity.class;
        }
    }
} 