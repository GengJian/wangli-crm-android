package com.control.widget.dialog;

import android.view.KeyEvent;

import com.control.Interface.JiuyiISendReqInterface;

/**
 * ****************************************************************
 * 文件名称:JiuyiAppUpdataDialog.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:软件升级的dialog
 * 注意事项:全局的模态的dialog
 * ****************************************************************
 */

public class JiuyiAppUpdataDialog extends JiuyiDialogBase {
    public JiuyiAppUpdataDialog(){

    }

    protected void onDialogClick(int nCurAction, int DlgAction, String message)
    {
        if(isDismissed)
            return;
        isDismissed = true;

        if (_vDialog != null)
        {
            _vDialog.dismiss();
        }

        if(_vView == null || !(_vView instanceof JiuyiISendReqInterface))
            return;

        if (DlgAction == ActionDlg_Yes)
        {
            switch (nCurAction)
            {
                default:
                {
                    _vView.dealDialogAction(nCurAction, KeyEvent.KEYCODE_ENTER, message, null);
                }
                break;
            }
        }
        else
        {
            _vView.dealDialogAction(nCurAction, KeyEvent.KEYCODE_BACK, message, null);
        }
    }
}
