package com.jiuyi.tools;

import android.view.View;
import android.view.ViewGroup;

import com.control.widget.recyclerView.BaseViewHolder;
import com.control.utils.Res;

/**
 * Created by 74234 on 2017/8/1.
 */

public class jiuyiViewUtil {
    public static void initCutOff(BaseViewHolder helper, int layoutPosition) {
        if(layoutPosition==0)
        {
            View view =  helper.getConvertView();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams)
            {
                ViewGroup.MarginLayoutParams params= (ViewGroup.MarginLayoutParams) layoutParams;
                params.setMargins(0, Res.Dip2Pix(5),0, Res.Dip2Pix(1));
                view.setLayoutParams(params);
            }
        }else
        {
            View view =  helper.getConvertView();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams)
            {
                ViewGroup.MarginLayoutParams params= (ViewGroup.MarginLayoutParams) layoutParams;
                params.setMargins(0,0,0,Res.Dip2Pix(1));
                view.setLayoutParams(params);
            }
        }
    }
}
