package com.control.newdialog;

import com.control.callback.DialogCallBack;
import com.control.utils.Rc;

/**
 * ****************************************************************
 * 文件名称:DialogBulid.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:
 * ****************************************************************
 */

public class DialogBulid {

    DialogStuct ds;
    DialogCallBack dc;
    public   DialogBulid() {
        ds  = new DialogStuct();

    }
    public void startDialog(){
        if(dc!=null){
            DialogActivity.startDialog(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(),ds,dc);
        }else{
            DialogActivity.startDialog(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(),ds);
        }
    }

    public DialogBulid setCallBack(DialogCallBack callBack){
        dc = callBack;
        return this;
    }

    public DialogBulid setTitle(String title){
        ds.title =title;
        return this;
    }
    public DialogBulid setContent(String content){
        ds.content =content;
        return this;
    }
    public DialogBulid setLeftText(String leftText){
        ds.leftText =leftText;
        return this;
    }
    public DialogBulid setRightText(String rightText){
        ds.rightText =rightText;
        return this;
    }
    public DialogBulid setLeftAction(int leftAction){
        ds.leftAction =leftAction;
        return this;
    }
    public DialogBulid setRightAction(int rightAction){
        ds.rightAction =rightAction;
        return this;
    }

}
