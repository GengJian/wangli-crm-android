package com.control.Interface;

import android.app.Dialog;

import com.control.widget.dialog.JiuyiDialogBase;

/**
 * ****************************************************************
 * 文件名称 : JiuyiIDialogInterface
 * 作   者 : Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述 : dialog弹框interface
 * ****************************************************************
 */
public interface JiuyiIDialogInterface {
    /**
     * 弹出Dialog
     * @param nAction   唯一标示是哪个Dialog
     * @param sTitle    要显示Dialog的标题
     * @param sContent  要显示Dialog的内容
     * @param nBtnType  按钮类型，值主要有
     * 		                MyDialog.Dialog_Type_YesNo:确定返回按钮
     * 		                MyDialog.Dialog_Type_Yes:确定按钮
     * 		                MyDialog.Dialog_Type_No：返回按钮
     * 		                MyDialog.Dialog_Type_Cancle：取消
     */
    void startDialog(int nAction, String sTitle, String sContent, int nBtnType, JiuyiDialogBase.DialogStruct struct);
    /**
     * 处理弹出Dialog的事件
     * @param nAction   与startDialog时的nAction相同，唯一标示是哪个Dialog
     * @param nKeyCode  确定(Pub.SOFT1)和返回(Pub.SOFT2)按钮
     * @param url       需要处理的Url
     * @param pDialog  pDialog弹框，用于后续处理关闭弹框
     */
    void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog);
}
