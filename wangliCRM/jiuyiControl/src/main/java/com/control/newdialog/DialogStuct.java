package com.control.newdialog;

import java.io.Serializable;

/**
 * ****************************************************************
 * 文件名称:DialogStuct.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:
 * ****************************************************************
 */

public class DialogStuct implements Serializable {

        public String title="";
        public String content="";
        public String leftText="";
        public String rightText="";
        public int leftAction=0;
        public int rightAction=0;



        public static DialogBulid bulid(){
            DialogBulid bulid = new DialogBulid();
            return bulid;
        }

}
