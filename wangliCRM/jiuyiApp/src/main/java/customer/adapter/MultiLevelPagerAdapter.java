package customer.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;

import java.util.List;

import customer.view.MultiSelectViewPager;

/**
 * ****************************************************************
 * 文件名称:MultiLevelPagerAdapter.java
 * 作    者:Created by zhengss
 * 创建时间:2018/12/11 15:23
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/12/11 1.00 初始版本
 * ****************************************************************
 */

public class MultiLevelPagerAdapter extends PagerAdapter {
    private List<View> viewList;

    public MultiLevelPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (viewList != null) {
            return viewList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return (arg0 == arg1);
    }

    @Override
    public Object instantiateItem(View container, int position) {
        // TODO Auto-generated method stub
        ((MultiSelectViewPager) container).addView(viewList.get(position), 0);

        return viewList.get(position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        // TODO Auto-generated method stub
        ((MultiSelectViewPager) container).removeView(viewList.get(position));
    }

    @Override
    public float getPageWidth(int position) {
        Log.d("d", "pagewidth = " + super.getPageWidth(position));
        return 0.5f;
    }
}
