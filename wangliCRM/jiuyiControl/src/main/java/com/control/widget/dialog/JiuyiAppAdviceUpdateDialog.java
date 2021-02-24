package com.control.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.control.Interface.JiuyiIDialogInterface;
import com.control.Interface.JiuyiISendReqInterface;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.canvas.Graphics;
import com.control.widget.JiuyiMaxHeightScrollView;

/**
 * ****************************************************************
 * 文件名称:JiuyiAppUpdataDialog.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:软件升级的dialog
 * 注意事项:全局的模态的dialog
 * ****************************************************************
 */

public class JiuyiAppAdviceUpdateDialog extends JiuyiDialogBase {
    public TextView tv_immediately,tv_cancel,tv_noalert;
    public JiuyiAppAdviceUpdateDialog(){

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
        }else if((DlgAction == ActionDlg_Later)){
            _vView.dealDialogAction(nCurAction, KeyEvent.KEYCODE_HOME, message, null);
        }
        else
        {
            _vView.dealDialogAction(nCurAction, KeyEvent.KEYCODE_BACK, message, null);
        }
    }


    /**
     * dialog的父类（不带自定义按钮的文字和背景，使用默认的）
     * @param context   上下文
     * @param view      JiuyiISendReqInterface
     * @param nAction   Dialog的标识
     * @param sTitle    标题
     * @param sMessage  内容
     * @param nBtnType    按钮类型
     * @param struct    自定义按钮的文字和背景
     */
    public void startDialog(Context context, JiuyiIDialogInterface view, int nAction, String sTitle, String sMessage, int nBtnType, DialogStruct struct)
    {
        if (Func.IsStringEmpty(sMessage))
            return;

        //获取宽度
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        mDlgViewWidth = dm.widthPixels * 4 / 5;

        //需要在onDialogClick处理的url
        final String url = struct == null ? null : struct.getUrl();

        //UI
        this._vView = view;
        this._nCurAction = nAction;
        this.nBtnType = nBtnType;

        _vDialog = new Dialog(context, Res.getStyleID(context,"jiuyidialogthemelog"));
        _vDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        _vDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface arg0) {
                switch(_nCurAction){
                    default:
                        onDialogClick(DialogID.DialogDoNothing, ActionDlg_Cancle, url);
                }
            }
        });

        //初始化view
        View vDlgBodView = LayoutInflater.from(context).inflate(Res.getLayoutID(context, "jiuyi_advicedialog_layout"), null);
        mTitleTextView = (TextView)vDlgBodView.findViewById(Res.getViewID(context, "dialog_title_textview"));
        mScrollView = (JiuyiMaxHeightScrollView)vDlgBodView.findViewById(Res.getViewID(context, "dialog_scrollview"));
        mContentTextView = (TextView)vDlgBodView.findViewById(Res.getViewID(context, "dialog_content_textview"));
        tv_immediately = (TextView)vDlgBodView.findViewById(Res.getViewID(context, "tv_immediately"));
        tv_immediately.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogClick(_nCurAction, ActionDlg_Yes, url);
            }
        });
        tv_cancel = (TextView)vDlgBodView.findViewById(Res.getViewID(context, "tv_cancel"));
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogClick(_nCurAction, ActionDlg_Cancle, url);
            }
        });
        tv_noalert = (TextView)vDlgBodView.findViewById(Res.getViewID(context, "tv_noalert"));
        tv_noalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogClick(_nCurAction, ActionDlg_Later, url);
            }
        });


        //设置属性
        if (mTitleTextView != null){
            mTitleTextView.setText((sTitle == null || sTitle.length() == 0) ?  Rc.cfg.getDefDialogTitle() : sTitle);
        }

        mScrollView.setMaxHeight(mDlgViewWidth);
        if (mContentTextView != null){
            mContentTextView.setText(sMessage);
        }


        //修改内容布局,不满一行居中显示，超过一行居左显示)
        int nWidth= Graphics.stringWidth(sMessage, Rc.getIns().getCanvasMainFont());
        if(sMessage.indexOf("\n") > 0 || nWidth > mDlgViewWidth){
            mContentTextView.setGravity(Gravity.LEFT|Gravity.TOP);
        }else{
            mContentTextView.setGravity(Gravity.CENTER);
        }
        mContentTextView.setPadding(mDlgViewWidth/10, mDlgViewWidth/20,mDlgViewWidth/10, mDlgViewWidth/20);

        //自定义view内容和背景
        if(struct != null){
            if(struct.isCancelable()){
                _vDialog.setCanceledOnTouchOutside(false);
                _vDialog.setCancelable(false);
            }
        }else {
            _vDialog.setCanceledOnTouchOutside(true);
            _vDialog.setCancelable(true);

        }

        //设置并显示
        _vDialog.setContentView(vDlgBodView);
    }
}
