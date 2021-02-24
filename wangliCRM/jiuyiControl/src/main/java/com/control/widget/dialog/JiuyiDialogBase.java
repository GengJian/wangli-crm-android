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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.Interface.JiuyiIDialogInterface;
import com.control.Interface.JiuyiISendReqInterface;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.canvas.Graphics;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiMaxHeightScrollView;

/**
 * ****************************************************************
 * 文件名称:JiuyiDialogBase.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:自定义dialog的基类
 * ****************************************************************
 */

public class JiuyiDialogBase {
    /** 模仿全局的dialog */
    public static SimulateSystemAlertInfo mSimulateSystemAlertInfo = null;

    /** 有确定返回按钮 */
    public static final int Dialog_Type_YesNo = 0;
    /** 有确定按钮 */
    public static final int Dialog_Type_Yes = 1;
    /** 有返回按钮 */
    public static final int Dialog_Type_No = 2;
    /** 关闭按钮 */
    public static final int Dialog_Type_Cancle = 3;

    /** 点击确定按钮 */
    public static final int ActionDlg_Yes = 0;
    /** 点击返回按钮 */
    public static final int ActionDlg_No = 1;
    /** 点击关闭按钮 */
    public static final int ActionDlg_Cancle = 2;
    /** 点击关闭按钮 */
    public static final int ActionDlg_Later = 3;

    public JiuyiIDialogInterface _vView;
    public Dialog _vDialog;

    public int _nCurAction;
    public int nBtnType;// 按钮类别

    private int mDensityDpi = -1;// 屏幕密度，单位为dpi
    public int mDlgViewWidth;//宽度
    public boolean isDismissed;//是否已经处理过onDialogClick事件

    public JiuyiMaxHeightScrollView mScrollView;
    public TextView mTitleTextView;
    public TextView mContentTextView;
    public JiuyiButton mConfirmButton;
    public JiuyiButton mCancelButton;

    public JiuyiDialogBase(){

    }
    /**
     * dialog的父类（不带自定义按钮的文字和背景，使用默认的）
     * @param context   上下文
     * @param view      JiuyiISendReqInterface
     * @param nAction   Dialog的标识
     * @param sTitle    标题
     * @param sMessage  内容
     * @param nBtnType    按钮类型
     */
    public void startDialog(Context context, JiuyiISendReqInterface view, int nAction, String sTitle, final String sMessage, int nBtnType)
    {
        startDialog(context, view, nAction, sTitle, sMessage, nBtnType, null);
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
        View vDlgBodView = LayoutInflater.from(context).inflate(Res.getLayoutID(context, "jiuyi_dialog_layout"), null);
        mTitleTextView = (TextView)vDlgBodView.findViewById(Res.getViewID(context, "dialog_title_textview"));
        mScrollView = (JiuyiMaxHeightScrollView)vDlgBodView.findViewById(Res.getViewID(context, "dialog_scrollview"));
        mContentTextView = (TextView)vDlgBodView.findViewById(Res.getViewID(context, "dialog_content_textview"));
        mConfirmButton = (JiuyiButton)vDlgBodView.findViewById(Res.getViewID(context, "dialog_toolbar_button_confirm"));
        mCancelButton = (JiuyiButton)vDlgBodView.findViewById(Res.getViewID(context, "dialog_toolbar_button_cancel"));

        mConfirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onDialogClick(_nCurAction, ActionDlg_Yes, url);
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onDialogClick(_nCurAction, ActionDlg_Cancle, url);
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
        boolean isButtonGone = false;
        if(struct != null){
            //(判断是否有内容，没有text则不显示)
            if(!Func.IsStringEmpty(struct.getButtonConfirmName())){
                mConfirmButton.setText(struct.getButtonConfirmName());
                mConfirmButton.setVisibility(View.VISIBLE);
            }else{
                mConfirmButton.setVisibility(View.GONE);
                isButtonGone = true;
            }
            if(!Func.IsStringEmpty(struct.getButtonCancelName())){
                mCancelButton.setText(struct.getButtonCancelName());
                mCancelButton.setVisibility(View.VISIBLE);
            }else{
                mCancelButton.setVisibility(View.GONE);
                isButtonGone = true;
            }
            if(struct.getButtonConfirmBgResid() != 0){
                mConfirmButton.setBackgroundResource(struct.getButtonConfirmBgResid());
            }
            if(struct.getButtonCcancelBgResid() != 0){
                mCancelButton.setBackgroundResource(struct.getButtonCcancelBgResid());
            }

            if(struct.getButtonConfirmFontColorResid() != 0){
                mConfirmButton.setTextColor(struct.getButtonConfirmFontColorResid());
            }
            if(struct.getButtonCancelFontColorResid() != 0){
                mCancelButton.setTextColor(struct.getButtonCancelFontColorResid());
            }

            if(struct.isCancelable()){
                _vDialog.setCanceledOnTouchOutside(false);
                _vDialog.setCancelable(false);
            }
        }else {
            _vDialog.setCanceledOnTouchOutside(true);
            _vDialog.setCancelable(true);
            if(nBtnType != Dialog_Type_YesNo) {
                //默认不显示取消)
                isButtonGone = true;
                mCancelButton.setVisibility(View.GONE);
            }
        }
        //如果只显示一个按钮则按钮宽度做限制
        if(isButtonGone){
            LinearLayout btnLayout = (LinearLayout)vDlgBodView.findViewById(Res.getViewID(context, "dialog_toolbar_layout"));
            if(btnLayout != null){
                btnLayout.setPadding(mDlgViewWidth/5, 0, mDlgViewWidth/5, 0);
            }
        }
        //设置并显示
        _vDialog.setContentView(vDlgBodView);
    }


    public Dialog getDialog(){
        return _vDialog;
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

        if(_vView == null || !(_vView instanceof JiuyiIDialogInterface))
            return;

        String url = "";
        //应该判断当前功能号，而不是弹框按钮功能号)
        if(nCurAction == DialogID.DialogOnJsAlert){
            url = message;
        }

        if (DlgAction == ActionDlg_Yes)
        {
            switch (nCurAction)
            {
                case DialogID.DialogSystemQiut: {
                    if(Rc.getIns().getBaseCallTopCallBack() != null)
                        Rc.getIns().getBaseCallTopCallBack().doAppExit();
                    break;
                }
                case DialogID.DialogForbiddenUse: {
                    if(Rc.getIns().getBaseCallTopCallBack() != null)
                        Rc.getIns().getBaseCallTopCallBack().doAppExit();
                    break;
                }
                default:
                {
                    _vView.dealDialogAction(nCurAction, KeyEvent.KEYCODE_ENTER, url, null);
                }
                break;
            }
        }
        else
        {
            _vView.dealDialogAction(nCurAction, KeyEvent.KEYCODE_BACK, url, null);
        }
    }
    public void show()
    {
        try {
            if (_vDialog != null) {
                //这些_nCurAction只允许点击按钮才能消失
                switch(_nCurAction){
                    case DialogID.DialogQuitTradeState:
                    case DialogID.DialogQuitTrade:
                    case DialogID.DialogAdviceUpdate:
                    case DialogID.DialogForceUpdate:
                    case DialogID.DialogwarningMsg:
                    case DialogID.DialogForbiddenUse:
                        _vDialog.setCancelable(false);
                        //mi4上会出问题,多次操作后，网页上点击无响应（全局dialog网页解决，网页弹出dialog，客户端点击确定后网页才做相应的动作）
                        //只有几个关键的弹出才使用全局的(先不用全局dialog)
//                        _vDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//指定会全局,可以在后台弹出
                        break;
                }
                _vDialog.show();
            }
        }catch(Exception e){
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
    }

    public boolean getisDismissed(){
        return isDismissed;
    }

    public void setisDismissed(boolean bValue){
        isDismissed =bValue;
    }
    /**
     * 是否是处理Dialog退出登陆的功能号统一判断
     * 一般在dealDialogAction里判断使用
     * @param nAction
     * @return
     */

    /**
     * 自定义按钮的文字和背景
     */
    public static class DialogStruct {
        /** 确定按钮的文字 */
        private String mButtonConfirmName;
        /** 确定按钮的背景Resid */
        private int mButtonConfirmBgResid = 0;
        /** 确定按钮文字的颜色Resid */
        private int mButtonConfirmFontColorResid = 0;
        /** 取消按钮的文字 */
        private String mButtonCancelName;
        /** 取消按钮的背景Resid */
        private int mButtonCcancelBgResid = 0;
        /** 取消按钮文字的颜色Resid */
        private int mButtonCcancelFontColorResid = 0;
        /** 是否是模态dialog */
        private boolean mCancelable;
        /** 要跳转的url */
        private String mUrl;

        public DialogStruct() {
        }

        public DialogStruct(String mUrl) {
            this.mUrl = mUrl;
        }

        public DialogStruct(boolean mCancelable) {
            this.mCancelable = mCancelable;
        }

        public DialogStruct(String mUrl, boolean mCancelable, int nBtnType) {
            this.mCancelable = mCancelable;
            this.mUrl = mUrl;
        }

        public DialogStruct(String mUrl, boolean mCancelable, String mButtonConfirmName, String mButtonCancelName) {
            this(mButtonConfirmName, mButtonCancelName);
            this.mCancelable = mCancelable;
            this.mUrl = mUrl;
        }

        public DialogStruct(String mButtonConfirmName, String mButtonCancelName){
            this.mButtonConfirmName = mButtonConfirmName;
            this.mButtonCancelName = mButtonCancelName;
        }

        public DialogStruct(String mButtonConfirmName, int mButtonConfirmBgResid, String mButtonCancelName, int mButtonCcancelBgResid){
            this(mButtonConfirmName, mButtonCancelName);
            this.mButtonConfirmBgResid = mButtonConfirmBgResid;
            this.mButtonCcancelBgResid = mButtonCcancelBgResid;
        }

        public DialogStruct(String mButtonConfirmName, int mButtonConfirmBgResid, String mButtonCancelName, int mButtonCcancelBgResid, boolean mCancelable){
            this(mButtonConfirmName, mButtonConfirmBgResid, mButtonCancelName, mButtonCcancelBgResid);
            this.mCancelable = mCancelable;
        }

        public DialogStruct(String mButtonConfirmName, int mButtonConfirmBgResid, int mButtonConfirmFontColorResid, String mButtonCancelName, int mButtonCcancelBgResid, int mButtonCcancelFontColorResid, boolean mCancelable){
            this(mButtonConfirmName, mButtonConfirmBgResid, mButtonCancelName, mButtonCcancelBgResid);
            this.mCancelable = mCancelable;
            this.mButtonConfirmFontColorResid = mButtonConfirmFontColorResid;
            this.mButtonCcancelFontColorResid = mButtonCcancelFontColorResid;
        }

        /** 确定按钮的文字 */
        public String getButtonConfirmName() {
            return mButtonConfirmName;
        }
        /** 确定按钮的背景Resid */
        public int getButtonConfirmBgResid() {
            return mButtonConfirmBgResid;
        }
        /** 确定按钮文字的颜色Resid */
        public int getButtonConfirmFontColorResid() { return mButtonConfirmFontColorResid; }
        /** 取消按钮的文字 */
        public String getButtonCancelName() {
            return mButtonCancelName;
        }
        /** 取消按钮的背景Resid */
        public int getButtonCcancelBgResid() {
            return mButtonCcancelBgResid;
        }
        /** 取消按钮文字的颜色Resid */
        public int getButtonCancelFontColorResid() { return mButtonCcancelFontColorResid; }
        /** 是否是模态dialog */
        public boolean isCancelable() {
            return mCancelable;
        }
        /** 要跳转的url */
        public String getUrl() {
            return mUrl;
        }
    }
    /**
     * 模仿全局的dialog
     *      2、在Activity的onResume里检测，如果不空就弹出dialog
     */
    public static class SimulateSystemAlertInfo {
        /** 唯一标示是哪个Dialog */
        private int nAction;
        /** 标题 */
        private String title;
        /** 内容 */
        private String content;
        /** 按钮类型 */
        private int nBtnType = Dialog_Type_Cancle;
        /**
         * 在哪个PageType上弹出的Dialog
         * 一般来说是在首页上，不需要强制判断
         */
        private int alertPageType;

        public SimulateSystemAlertInfo(int nAction, String content, int alertPageType) {
            this.content = content;
            this.alertPageType = alertPageType;
            this.nAction = nAction;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getAlertPageType() {
            return alertPageType;
        }

        public void setAlertPageType(int alertPageType) {
            this.alertPageType = alertPageType;
        }
        public int getAction() {
            return nAction;
        }

        public void setAction(int nAction) {
            this.nAction = nAction;
        }

        public int getnBtnType() {
            return nBtnType;
        }

        public void setnBtnType(int nBtnType) {
            this.nBtnType = nBtnType;
        }
        public void startDialog(Context context, JiuyiISendReqInterface view){
            JiuyiDialogBase dialog = new JiuyiDialogBase();
            dialog.startDialog(context, view, nAction, title, content, nBtnType, null);
            dialog.show();
        }
    }
}
