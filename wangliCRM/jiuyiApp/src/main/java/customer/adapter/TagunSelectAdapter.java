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

import customer.entity.MemberCenterBean;

/**
 */
public class TagunSelectAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<MemberCenterBean.LabelsBean> mDataList;

    public TagunSelectAdapter(Context context) {
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
        String t = mDataList.get(position).getDesp();
        textView.setText(t);
        view.setTag(t);//便于将文字取出来
        return view;
    }
    public void onlyAdd(MemberCenterBean.LabelsBean str) {
        if(mDataList!=null && mDataList.size()>0 ){
            for(int i=0;i<mDataList.size();i++){
                if(mDataList.get(i).getId()==str.getId()){
                    mDataList.remove(i);
                }
            }
        }
//        if(mDataList.contains(str)){
//            mDataList.remove(str);
//        }
        mDataList.add(0,str);
        notifyDataSetChanged();
    }
    public void onlyAddAll(List<MemberCenterBean.LabelsBean> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void onlyDelete(MemberCenterBean.LabelsBean str) {
        if(mDataList!=null && mDataList.size()>0 ){
            for(int i=0;i<mDataList.size();i++){
                if(mDataList.get(i).getId()==str.getId()){
                    mDataList.remove(str);
                }
            }
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
    public void clearAndAddAll(List<MemberCenterBean.LabelsBean> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }
}
