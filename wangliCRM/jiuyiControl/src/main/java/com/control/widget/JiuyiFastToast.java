package com.control.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.control.utils.Rc;
import com.control.utils.Res;

/**
 * ****************************************************************
 * 文件名称:JiuyiFastToast.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * 文件描述:及时反映的Toast
 * ****************************************************************
 */

public class JiuyiFastToast {
    private static Toast toast;

    public static void show(Context context, String msg, int duration, int mGravityBottom) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);

            View view = toast.getView();
            view.setBackgroundResource(Res.getDrawabelID(Rc.getApplication(), "jiuyi_v23_textviewcyclebg"));
            int padding = Res.Dip2Pix(5);
            view.setPadding(padding, padding, padding, padding);
        } else {
            toast.setText(msg);
        }
        if (toast != null) {
            if(mGravityBottom > 0)
                toast.setGravity(Gravity.BOTTOM, 0, mGravityBottom);
            toast.show();
        }
    }

    public static void show(Context context, String msg, int duration) {
        show(context, msg, duration, -1);
    }

    public static void show(Context context, String msg) {
        show(context, msg, Toast.LENGTH_SHORT);
    }
}  
