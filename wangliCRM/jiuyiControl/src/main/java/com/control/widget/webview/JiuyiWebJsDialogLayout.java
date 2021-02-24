package com.control.widget.webview;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JsResult;

import com.control.Interface.JiuyiISendReqInterface;
import com.control.widget.webview.JiuyiWebAlertStruct.JsAlertinterface;
import com.control.widget.dialog.JiuyiDialogBase;

public class JiuyiWebJsDialogLayout implements JiuyiISendReqInterface {
	private JsResult result;
    private String mMobileCode = "";
    private String dialogMessage = "";
    private JsAlertinterface pJsAlertinterface;
    private JiuyiWebViewContainerCallBack mJiuyiWebViewContainerCallBack;

	public JiuyiWebJsDialogLayout(JiuyiWebViewContainerCallBack callBack, JsAlertinterface jsalert, String dialogMessage){
        mJiuyiWebViewContainerCallBack = callBack;
		pJsAlertinterface = jsalert;
        this.dialogMessage = dialogMessage;
	}
	
	public void setJsResult(JsResult result){
		this.result = result;
	}
	public void setMobileCode(String code){
		mMobileCode = code;
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
     * @param struct
     */
    @Override
    public void startDialog(int nAction, String sTitle, String sContent, int nBtnType, JiuyiDialogBase.DialogStruct struct) {

    }

    @SuppressWarnings("MissingPermission")
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog){
        if(pJsAlertinterface != null){
            pJsAlertinterface.delectAlertStack(nAction, nKeyCode, dialogMessage);
        }

        if (nKeyCode == KeyEvent.KEYCODE_ENTER) {
            if(result != null)
                result.confirm();
        }else{
            if(result != null)
                result.cancel();
        }
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
     * @param mBundle   要带的参数
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

}