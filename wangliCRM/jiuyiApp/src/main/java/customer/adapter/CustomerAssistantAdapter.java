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
package customer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import customer.entity.CustomerAssistantBean;
import customer.entity.VisitIntelligenceBean;
import customer.listener.OnItemClickListener;

/**
 *
 */
public class CustomerAssistantAdapter extends SwipeMenuAdapter<CustomerAssistantAdapter.DefaultViewHolder> {

    public static final int VIEW_TYPE_MENU_DELETE = 1;


    public List<CustomerAssistantBean> mViewTypeBeanList;

    private OnItemClickListener mOnItemClickListener;

    public CustomerAssistantAdapter(List<CustomerAssistantBean> viewTypeBeanList) {
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
        return VIEW_TYPE_MENU_DELETE;
    }

    @Override
    public int getItemCount() {
        return mViewTypeBeanList == null ? 0 : mViewTypeBeanList.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.normal_linkman_dept_item, parent, false);
    }

    @Override
    public CustomerAssistantAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(CustomerAssistantAdapter.DefaultViewHolder holder, int position) {
        holder.setData(mViewTypeBeanList.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTag,tvName,tvDuty,tvOrgName,tvDept;

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTag = (TextView) itemView.findViewById(R.id.tag);
            tvTag.setVisibility(View.GONE);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvDuty = (TextView) itemView.findViewById(R.id.tv_duty);
            tvOrgName = (TextView) itemView.findViewById(R.id.tv_company);
            tvDept = (TextView) itemView.findViewById(R.id.tv_dept);

        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(CustomerAssistantBean viewTypeBean) {
            if(viewTypeBean.getOperator()!=null &&viewTypeBean.getOperator().getName()!=null){
                this.tvName.setText(viewTypeBean.getOperator().getName());
            }
            if(viewTypeBean.getOperator()!=null &&viewTypeBean.getOperator().getTitle()!=null){
                this.tvDuty.setText(viewTypeBean.getOperator().getTitle());
            }
            this.tvOrgName.setText("中国王力集团");
            if(viewTypeBean.getOperator()!=null && viewTypeBean.getOperator().getDepartment()!=null && viewTypeBean.getOperator().getDepartment().getName()!=null ){
                this.tvDept.setText(viewTypeBean.getOperator().getDepartment().getName());
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
    public void add(List<CustomerAssistantBean> addCustomerList) {
        //增加数据
        int position = mViewTypeBeanList.size();
        mViewTypeBeanList.addAll(position, addCustomerList);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void refresh(List<CustomerAssistantBean> newList) {
        //刷新数据
        mViewTypeBeanList.removeAll(mViewTypeBeanList);
        mViewTypeBeanList.addAll(newList);
        notifyDataSetChanged();
    }
}
