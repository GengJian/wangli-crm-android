package customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.wanglicrm.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanHailong on 15/10/19.
 */
public class TagAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<String> mDataList;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        String t = mDataList.get(position);
        textView.setText(t);
        view.setTag(t);//便于将文字取出来
        return view;
    }

    public void onlyAddAll(List<String> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void onlyAdd(String str) {
        if(mDataList.contains(str)){
            mDataList.remove(str);
        }
        mDataList.add(0,str);
        notifyDataSetChanged();
    }
    public void onlyDelete(String str) {
        if(mDataList.contains(str)){
            mDataList.remove(str);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        mDataList.clear();
    }

    /**
     * 这个方法可以提供击删除这个标签的方法
     *
     * @param datas
     */
    public void clearAndAddAll(List<String> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }
}
