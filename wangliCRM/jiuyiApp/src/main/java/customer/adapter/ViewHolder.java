package customer.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * ****************************************************************
 * 文件名称:ViewHolder.java
 * 作    者:Created by zhengss
 * 创建时间:2018/12/11 15:03
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/12/11 1.00 初始版本
 * ****************************************************************
 */
public class ViewHolder {


    private ViewHolder(){}
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
