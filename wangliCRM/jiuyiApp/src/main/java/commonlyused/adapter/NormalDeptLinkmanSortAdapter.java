package commonlyused.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wanglicrm.android.R;

import java.util.List;

import commonlyused.bean.LinkmanBean;

/**
 * @author:
 * @date: 2017/7/19
 */

public class NormalDeptLinkmanSortAdapter extends RecyclerView.Adapter<NormalDeptLinkmanSortAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<LinkmanBean> mData;
    private Context mContext;
    private boolean showTag=true;

    public NormalDeptLinkmanSortAdapter(Context context, List<LinkmanBean> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;
        this.mContext = context;
    }
    public NormalDeptLinkmanSortAdapter(Context context, List<LinkmanBean> data, boolean showTag) {
        mInflater = LayoutInflater.from(context);
        mData = data;
        this.mContext = context;
        this.showTag=showTag;
    }

    @Override
    public NormalDeptLinkmanSortAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.normal_linkman_dept_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvTag = (TextView) view.findViewById(R.id.tag);
        viewHolder.tvName = (TextView) view.findViewById(R.id.name);
        viewHolder.tvDuty = (TextView) view.findViewById(R.id.tv_duty);
        viewHolder.tvOrgName = (TextView) view.findViewById(R.id.tv_company);
        viewHolder.tvDept = (TextView) view.findViewById(R.id.tv_dept);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NormalDeptLinkmanSortAdapter.ViewHolder holder, final int position) {
        int section = getSectionForPosition(position);
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            holder.tvTag.setVisibility(View.VISIBLE);
            holder.tvTag.setText(mData.get(position).getLetters());
        } else {
            holder.tvTag.setVisibility(View.GONE);
        }
        if(!showTag){
            holder.tvTag.setVisibility(View.GONE);
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }

        holder.tvName.setText(this.mData.get(position).getName());
        holder.tvDuty.setText(this.mData.get(position).getDuty());
        holder.tvDept.setText(this.mData.get(position).getDept());
        holder.tvOrgName.setText(this.mData.get(position).getOrg());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //**********************itemClick************************
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    //**************************************************************

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTag, tvName,tvDuty,tvDept,tvOrgName;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 提供给Activity刷新数据
     * @param list
     */
    public void updateList(List<LinkmanBean> list){
        this.mData = list;
        notifyDataSetChanged();
    }

    public Object getItem(int position) {
        return mData.get(position);
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return mData.get(position).getLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mData.get(i).getLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
    public void clear(){
        mData.clear();
        notifyDataSetChanged();
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<LinkmanBean> addList) {
        //增加数据
        int position = mData.size();
        mData.addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<LinkmanBean> newList) {
        //刷新数据
        mData.removeAll(mData);
        mData.addAll(newList);
        notifyDataSetChanged();
    }

}
