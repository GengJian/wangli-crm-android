package com.jiuyi.layout.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditText;
import com.control.widget.JiuyiTitleBarBase;


/**
 * ****************************************************************
 * 文件名称:jiuyiRootTitleBar.java
 * 作    者:Created by zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述:软件首页的标题栏
 * ****************************************************************
 */

public class JiuyiRootTitleBar extends JiuyiTitleBarBase {
    public int nColorTitleSelect;//标题被选中
    public int nColorTitleNotSelect;//标题未被选中
    private JiuyiEditText mEditText;

    public JiuyiRootTitleBar(Context context) {
        super(context);
    }

    public JiuyiRootTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JiuyiRootTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化view
     */
    public void onInit(){
        onInitTitleColor();
        setOnClickListener();
    }

    /**
     * 设置点击事件
     * 如果标题栏的事件不是这样的，则在子类中实现
     */
    public void setOnClickListener(){
        //
        if(mLeftView != null){
            mLeftView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                }
            });
        }
    }

    /**
     * 设置title的颜色
     */
    private void onInitTitleColor() {
        nColorTitleNotSelect = Res.getColor(getContext(), "tzt_v23_segment_text_color");
        nColorTitleSelect = Res.getColor(getContext(), "tzt_v23_segment_select_text_color");
    }

    public void changeTitleLayout(int nPageType) {
       /* if (nPageType == Pub.MENU_Customer) {//客户



        } else if(nPageType == Pub.MENU_Trip){//订单

            searchTitle.

        }else if (nPageType == Pub.MENU_Message) {//消息

        } else if (nPageType == Pub.MENU_Normal) {//常用

        } else if(nPageType == Pub.MENU_Mine){//我的

        }*/
    }




    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void setBackgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getWindow().setAttributes(lp);
    }
}
