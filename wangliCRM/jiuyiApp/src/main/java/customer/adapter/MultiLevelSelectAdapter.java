package customer.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.jiuyi.model.DictResultBean;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:MultiLevelSelectAdapter.java
 * 作    者:Created by zhengss
 * 创建时间:2018/12/11 14:55
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/12/11 1.00 初始版本
 * ****************************************************************
 */
public class MultiLevelSelectAdapter extends BaseAdapter {
    private Context mContext;
    private List<DictResultBean.ContentBean> mData;
    private int selectedPos = -1;
    private int mSelectedBackgroundResource;
    private int mNormalBackgroundResource;
    private boolean hasDivider = true;

    public void setNormalBackgroundResource(int normalBackgroundResource) {
        this.mNormalBackgroundResource = normalBackgroundResource;
    }

    public void setHasDivider(boolean hasDivider) {
        this.hasDivider = hasDivider;
    }

    public MultiLevelSelectAdapter(Context context, List<DictResultBean.ContentBean> data) {
        this.mContext = context;
        mData = data;
    }

    public void setSelectedBackground(int res) {
        mSelectedBackgroundResource = res;
    }


    public void setSelectedItem(int position) {
        selectedPos = position;
        notifyDataSetChanged();
    }


    public void setData(List<DictResultBean.ContentBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mData == null)
            return 0;
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        if (mData == null)
            return null;
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_dict_item, parent, false);

        }

        LinearLayout itemLayout = ViewHolder.get(convertView, R.id.dict_item_ly);

        TextView nameText = ViewHolder.get(convertView, R.id.dict_item_textview);

        TextView dividerTextView = ViewHolder.get(convertView, R.id.dict_item_divider);

        final DictResultBean.ContentBean dictUnit = mData.get(position);

        nameText.setText(dictUnit.getName());


        convertView.setSelected(selectedPos == position);
        nameText.setSelected(selectedPos == position);

        nameText.setTextColor(selectedPos == position ? 0xFF00B4C9 : 0xFF333333);

        if (mNormalBackgroundResource == 0)
            mNormalBackgroundResource = R.color.white;

        if (mSelectedBackgroundResource != 0)
            itemLayout.setBackgroundResource(selectedPos == position ? mSelectedBackgroundResource : mNormalBackgroundResource);

        dividerTextView.setVisibility(hasDivider ? View.VISIBLE : View.INVISIBLE);

        return convertView;
    }


}
