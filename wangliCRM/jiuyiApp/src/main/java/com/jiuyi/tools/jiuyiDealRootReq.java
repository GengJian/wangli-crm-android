package com.jiuyi.tools;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;


import com.control.Interface.JiuyiISendReqInterface;
import com.control.shared.JiuyiUpdateInfoShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiDateUtil;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;

import com.control.widget.dialog.JiuyiAppAdviceUpdateDialog;
import com.control.widget.dialog.JiuyiAppUpdataDialog;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.jiuyi.activity.common.activity.JiuyiHeadPageActivity.HeadPageCallBack;
import com.jiuyi.app.JiuyiActivityManager;
import com.jiuyi.model.CheckUpdateBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import customer.request.RequestServiceDate;


/**
 * ****************************************************************
 * 文件名称 : jiuyiDealRootReq.java
 * 作 者 :   zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 处理与启动相关的请求
 *****************************************************************
 */
public class jiuyiDealRootReq implements JiuyiISendReqInterface {
    /** 启动页回调*/
	private HeadPageCallBack pCallBack;
    /** 升级的URL*/
    private String mUpdateUrl;//升级的url
    private Map<String, String> queryMap= new HashMap<String, String>();
	

	/**
	 * 正在弹出升级对话框供用户选择，待选择后再跳转
	 * 按确定键时是不允许在跳转到首页去的，直接退出软件
	 * 安返回建，如果是强制升级则退出软件；如果是建议升级则继续请求并正常跳转
	 * false表示当前没有升级dialog或已经关闭，true表示已经弹出了升级dialog但是尚没有关闭
	 */
	public boolean bUpVersionDialogForbiddenChangePage = false;

    public boolean bRelogin = false;

    /** 构造函数*/
	public jiuyiDealRootReq(HeadPageCallBack callback, Vector<int[]> arrAction){
		pCallBack = callback;
        getUpdateInfo();
	}
    /**
     * 具体的处理预先请求
     */
    private void createAllReq(int start){

    }

    /**
     * 获取当前的显示Activity，用来显示diaolog
     * @return
     */
    private Activity getActivity(){
        Activity activity = JiuyiActivityManager.getCurrentActivity();
        if(activity != null){
            return activity;
        }
        return pCallBack.getActivity();
    }
	/**
	 * 处理dialog弹出框，继续往下走的处理
	 */
	private void dialogChangePage(){
		if(!pCallBack.getTztDealRootInit().isChangedPage()){
			bUpVersionDialogForbiddenChangePage = false;
			createAllReq(1);
			if(pCallBack.getUpVersionChangePageType() == 1|| pCallBack.getUpVersionChangePageType()==0){
				pCallBack.getTztDealRootInit().ChangeRootPage(pCallBack.getActivity());
			}else if(pCallBack.getUpVersionChangePageType() > 0){
				pCallBack.getTztDealRootInit().ChangePage(pCallBack.getActivity(), pCallBack.getUpVersionChangePageType());
			}
		}
	}

    /**
     * 设置进度条的状态
     *
     * @param barProcess 进度
     */
    @Override
    public void showProcessBar(int barProcess) {

    }



    /**
     * 错误信息用Toast显示提示信息
     *
     * @param msg    错误信息

     */
    @Override
    public void showErrorMessage(String msg) {

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
     */
    @Override
    public void startDialog(int nAction, String sTitle, String sContent, int nBtnType, JiuyiDialogBase.DialogStruct struct) {

    }

    /**
     * 处理弹出Dialog的事件
     * @param nAction   与startDialog时的nAction相同，唯一标示是哪个Dialog
     * @param nKeyCode  确定(Pub.SOFT1)和返回(Pub.SOFT2)按钮
     * @param url       需要处理的Url
     * @param pDialog  pDialog弹框，用于后续处理关闭弹框
     */
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog){
        switch(nAction){
            case DialogID.DialogAdviceUpdate:
            case DialogID.DialogForceUpdate:
            {
                if (nKeyCode == KeyEvent.KEYCODE_BACK) {
                    switch (nAction) {
                        case DialogID.DialogForceUpdate:
                            new jiuyiOperateApp().doExit();
                            pCallBack.getActivity().finish();
                            break;
                        case DialogID.DialogAdviceUpdate:
                            JiuyiUpdateInfoShared.getIns().mCancelUpdate="Y";
                            JiuyiUpdateInfoShared.getIns().onSaveData(getActivity());
                        default:
                            dialogChangePage();
                            break;
                    }
                } else if (nKeyCode == KeyEvent.KEYCODE_HOME) {//三日内不提醒
                    if(nAction == DialogID.DialogAdviceUpdate){
                        JiuyiUpdateInfoShared.getIns().mNoAlertTime= JiuyiDateUtil.getNowTime2();
                        JiuyiUpdateInfoShared.getIns().onSaveData(getActivity());
                        dialogChangePage();
                    }
                }else if (nKeyCode == KeyEvent.KEYCODE_ENTER) {
                    // 只有点击确定才需要赋值url
                    mUpdateUrl = url;
                    //建议升级要跳转
                    if(nAction == DialogID.DialogAdviceUpdate){
                        JiuyiUpdateInfoShared.getIns().clearData();
                        dialogChangePage();
                    }else if(nAction == DialogID.DialogForceUpdate){//强制升级要关闭软件
                        JiuyiUpdateInfoShared.getIns().clearData();
                        Rc.getIns().getBaseCallTopCallBack().gotoUpVersion(getActivity(), mUpdateUrl, -1);
                        new jiuyiOperateApp().doExit();
                        pCallBack.getActivity().finish();
                    }

                }
                break;
            }
        }

    }

    public void gotoUpVersion(){
        Rc.getIns().getBaseCallTopCallBack().gotoUpVersion(getActivity(), mUpdateUrl, -1);
    }

    /**
     *
     * @param IsBg 是否显示进度条，true不显示，false显示；
     */
    @Override
    public void createReq(boolean IsBg) {

    }




    /**
     * 切换界面
     *
     * @param nPageType 要跳转的界面号
     * @param bSavePage 是否要保存当前界面，保存后可以在返回到这个界面
     */
    @Override
    public void changePage(Bundle mBundle, int nPageType, boolean bSavePage) {

    }

    /**
     * 获取当前的界面号
     */
    @Override
    public int getPageType() {
        return 0;
    }


    public  void  getUpdateInfo(){
        queryMap.put("version",Rc.getIns().mUpVersion );
        queryMap.put("fromClientType","android" );
        JiuyiHttp.POST("android_version_check/check")
                .setJson(com.common.GsonUtil.gson().toJson(queryMap))
                .request(new ACallback<CheckUpdateBean>() {
                    @Override
                    public void onSuccess(CheckUpdateBean data) {
                       if(data!=null){
                           try {
                                final String sUpdateSign=data.getRemark();
                                // 升级标志UpdateSign 1建议升级，2强制升级
                               if(sUpdateSign==null||sUpdateSign.equals("0")){
                                   if(pCallBack != null) {
                                       if(!pCallBack.getTztDealRootInit().isChangedPage()){
                                           bUpVersionDialogForbiddenChangePage = false;
                                           pCallBack.getTztDealRootInit().ChangeRootPage(pCallBack.getActivity());
                                       }
                                   }
                               }else if (sUpdateSign.equals("1")||sUpdateSign.equals("2")) {
                                   final String sUpdateUrl=data.getAddress();
                                   String sDesp="";
                                   sDesp=data.getDesp();
                                   if(sDesp==null){
                                       sDesp="检查到新版本，是否升级？";
                                   }
                                   final String sUpdateMsg=sDesp;
                                   if(pCallBack != null) {
                                       if (sUpdateSign.equals("2")) {// 强制升级
                                           final JiuyiAppUpdataDialog dialog = new JiuyiAppUpdataDialog();
                                           final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct(sUpdateUrl, false, "升级", "取消");
                                           getActivity().runOnUiThread(new Runnable() {
                                               @Override
                                               public void run() {
                                                   bUpVersionDialogForbiddenChangePage = true;
                                                   dialog.startDialog(getActivity(), jiuyiDealRootReq.this, DialogID.DialogForceUpdate, "新版本升级", sUpdateMsg, JiuyiDialogBase.Dialog_Type_YesNo, struct);
                                                   dialog.show();
                                               }
                                           });
                                       }else if(sUpdateSign.equals("1")){// 建议升级
                                            //暂不升级和三天后再提醒
                                             if(Func.IsStringEmpty(JiuyiUpdateInfoShared.getIns().mNoAlertTime)||
                                                     (!Func.IsStringEmpty(JiuyiUpdateInfoShared.getIns().mNoAlertTime)&& JiuyiDateUtil.TimeCompare(JiuyiDateUtil.getNowTime2(), JiuyiUpdateInfoShared.getIns().mNoAlertTime))){
                                                 final JiuyiAppAdviceUpdateDialog dialog = new JiuyiAppAdviceUpdateDialog();
                                                 final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct(sUpdateUrl, false, "升级", "取消");
                                                 getActivity().runOnUiThread(new Runnable() {
                                                     @Override
                                                     public void run() {
                                                         bUpVersionDialogForbiddenChangePage = true;
                                                         dialog.startDialog(getActivity(), jiuyiDealRootReq.this, DialogID.DialogAdviceUpdate, "新版本升级", sUpdateMsg, JiuyiDialogBase.Dialog_Type_YesNo, struct);
                                                         dialog.show();

                                                     }
                                                 });
                                                }else{
                                                    if(pCallBack != null) {
                                                        if(!pCallBack.getTztDealRootInit().isChangedPage()){
                                                            bUpVersionDialogForbiddenChangePage = false;
                                                            pCallBack.getTztDealRootInit().ChangeRootPage(pCallBack.getActivity());
                                                        }
                                                    }
                                                }

                                       }
                                   }
                               }else{
                                   if(pCallBack != null) {
                                       if(!pCallBack.getTztDealRootInit().isChangedPage()){
                                           bUpVersionDialogForbiddenChangePage = false;
                                           pCallBack.getTztDealRootInit().ChangeRootPage(pCallBack.getActivity());
                                       }
                                   }
                               }

                           } catch (Exception e) {
                               JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
                           }

                       }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(pCallBack != null) {
                            if(!pCallBack.getTztDealRootInit().isChangedPage()){
                                bUpVersionDialogForbiddenChangePage = false;
                                pCallBack.getTztDealRootInit().ChangeRootPage(pCallBack.getActivity());
                            }
                        }
                    }
                });

    }
}
