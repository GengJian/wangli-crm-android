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

import com.control.utils.Func;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wanglicrm.android.R;
import com.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import customer.entity.BatchTransBean;
import customer.entity.VisitIntelligenceBean;
import customer.listener.OnItemClickListener;

/**
 *
 */
public class NewInfomationDetailMutiAdapter extends SwipeMenuAdapter<NewInfomationDetailMutiAdapter.DefaultViewHolder> {

    public static final int VIEW_TYPE_MENU_DELETE = 1;


    public List<VisitIntelligenceBean> mViewTypeBeanList;

    private OnItemClickListener mOnItemClickListener;

    public NewInfomationDetailMutiAdapter(List<VisitIntelligenceBean> viewTypeBeanList) {
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
        return mViewTypeBeanList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return mViewTypeBeanList == null ? 0 : mViewTypeBeanList.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.jiuyi_newinformationdetail_item, parent, false);
    }

    @Override
    public NewInfomationDetailMutiAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(NewInfomationDetailMutiAdapter.DefaultViewHolder holder, int position) {
        holder.setData(mViewTypeBeanList.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_infobigtype,tv_customer,tv_category,tv_type;

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_infobigtype = (TextView) itemView.findViewById(R.id.tv_infobigtype);
            tv_customer = (TextView) itemView.findViewById(R.id.tv_customer);
            tv_category = (TextView) itemView.findViewById(R.id.tv_category);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);

        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(VisitIntelligenceBean viewTypeBean) {
            if(viewTypeBean.getIntelligence()!=null &&!Func.IsStringEmpty(viewTypeBean.getIntelligence().getBigCategoryValue())){
                this.tv_infobigtype.setText(viewTypeBean.getIntelligence().getBigCategoryValue());
            }
            if(viewTypeBean.getMember()!=null &&!Func.IsStringEmpty(viewTypeBean.getMember().getOrgName())){
                this.tv_customer.setText(viewTypeBean.getMember().getOrgName());
            }
            if(!Func.IsStringEmpty(viewTypeBean.getIntelligenceInfoValue())){
                this.tv_category.setText(viewTypeBean.getIntelligenceInfoValue());
            }
            if(!Func.IsStringEmpty(viewTypeBean.getIntelligenceTypeValue())){
                this.tv_type.setText(viewTypeBean.getIntelligenceTypeValue());
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
    public void add(List<VisitIntelligenceBean> addCustomerList) {
        //增加数据
        int position = mViewTypeBeanList.size();
        mViewTypeBeanList.addAll(position, addCustomerList);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void refresh(List<VisitIntelligenceBean> newList) {
        //刷新数据
        mViewTypeBeanList.removeAll(mViewTypeBeanList);
        mViewTypeBeanList.addAll(newList);
        notifyDataSetChanged();
    }
}
