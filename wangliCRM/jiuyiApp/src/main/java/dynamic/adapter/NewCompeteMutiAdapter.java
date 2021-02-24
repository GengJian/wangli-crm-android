/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dynamic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.facebook.drawee.view.SimpleDraweeView;
import com.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import customer.entity.BatchTransBean;
import customer.listener.OnItemClickListener;
import dynamic.entity.DyBusinessBean;

/**
 *
 */
public class NewCompeteMutiAdapter extends SwipeMenuAdapter<NewCompeteMutiAdapter.DefaultViewHolder> {

    public static final int VIEW_TYPE_MENU_DELETE = 1;


    public List<DyBusinessBean.ContentBean.CompetitorBehavior> getmViewTypeBeanList() {
        return mViewTypeBeanList;
    }

    public void setmViewTypeBeanList(List<DyBusinessBean.ContentBean.CompetitorBehavior> mViewTypeBeanList) {
        this.mViewTypeBeanList = mViewTypeBeanList;
    }

    public List<DyBusinessBean.ContentBean.CompetitorBehavior> mViewTypeBeanList;

    private OnItemClickListener mOnItemClickListener;

    public NewCompeteMutiAdapter(List<DyBusinessBean.ContentBean.CompetitorBehavior> viewTypeBeanList) {
        this.mViewTypeBeanList = viewTypeBeanList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public void clear() {
        mViewTypeBeanList.clear();
    }

    @Override
    public int getItemViewType(int position) {
//        return mViewTypeBeanList.get(position).getViewType();
        return VIEW_TYPE_MENU_DELETE;
    }

    @Override
    public int getItemCount() {
        return mViewTypeBeanList == null ? 0 : mViewTypeBeanList.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.jiuyi_newcompetemulti_item, parent, false);
    }

    @Override
    public NewCompeteMutiAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(NewCompeteMutiAdapter.DefaultViewHolder holder, int position) {
        holder.setData(mViewTypeBeanList.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_name,tv_inchargeman,tv_remark,tv_tel;

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_inchargeman = (TextView) itemView.findViewById(R.id.tv_inchargeman);
            tv_tel = (TextView) itemView.findViewById(R.id.tv_tel);
            tv_remark = (TextView) itemView.findViewById(R.id.tv_remark);

        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(DyBusinessBean.ContentBean.CompetitorBehavior viewTypeBean) {
            if(viewTypeBean.getMember()!=null && viewTypeBean.getMember().getAbbreviation()!=null ){
                tv_name.setText(viewTypeBean.getMember().getAbbreviation());
            }
            if(viewTypeBean.getPrincipalName()!=null ){
                tv_inchargeman.setText(viewTypeBean.getPrincipalName());
            }
            if(viewTypeBean.getPrincipalTel()!=null ){
                tv_tel.setText(viewTypeBean.getPrincipalTel());
            }
            if(viewTypeBean.getContent()!=null ){
                tv_remark.setText(viewTypeBean.getContent());
            }
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyBusinessBean.ContentBean.CompetitorBehavior> addCustomerList) {
        //增加数据
        int position = mViewTypeBeanList.size();
        mViewTypeBeanList.addAll(position, addCustomerList);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void refresh(List<DyBusinessBean.ContentBean.CompetitorBehavior> newList) {
        //刷新数据
        mViewTypeBeanList.removeAll(mViewTypeBeanList);
        mViewTypeBeanList.addAll(newList);
        notifyDataSetChanged();
    }
}
