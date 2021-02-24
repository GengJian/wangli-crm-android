package com.control.newdialog;

/**
 * ****************************************************************
 * 文件名称:NewDialogManager.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:
 * ****************************************************************
 */

public class NewDialogManager {

    private static NewDialogManager mDialogManager;

    public static NewDialogManager getIns(){
        if(mDialogManager==null){
            mDialogManager = new NewDialogManager();
        }
        return mDialogManager;
    }


}
