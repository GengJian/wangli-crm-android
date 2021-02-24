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
package commonlyused.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.wanglicrm.android.R;
import com.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import customer.listener.OnItemClickListener;
import commonlyused.bean.JiuyiRetailChannelBean;

/**
 *
 */
public class NewRetailChannelMutiAdapter extends SwipeMenuAdapter<NewRetailChannelMutiAdapter.DefaultViewHolder> {

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    private  String viewType="";

    public static final int VIEW_TYPE_MENU_DELETE = 1;


    public List<JiuyiRetailChannelBean.RetailChannelItemsBean> getmViewTypeBeanList() {
        return mViewTypeBeanList;
    }

    public void setmViewTypeBeanList(List<JiuyiRetailChannelBean.RetailChannelItemsBean> mViewTypeBeanList) {
        this.mViewTypeBeanList = mViewTypeBeanList;
    }

    public List<JiuyiRetailChannelBean.RetailChannelItemsBean> mViewTypeBeanList;

    private OnItemClickListener mOnItemClickListener;

    public NewRetailChannelMutiAdapter(List<JiuyiRetailChannelBean.RetailChannelItemsBean> viewTypeBeanList) {
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
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.jiuyi_newretailchannelmulti_item, parent, false);
    }

    @Override
    public NewRetailChannelMutiAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(NewRetailChannelMutiAdapter.DefaultViewHolder holder, int position) {
        holder.setData(mViewTypeBeanList.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
        if(viewType.equals("VIEW")){
            holder.setdisable();
        }

    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_client;
        ToggleButton tb_visit,tb_monopoly,tb_complete,tb_comparativeSales,tb_policyProcessAdvocacy,tb_train;

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_client = (TextView) itemView.findViewById(R.id.tv_client);
            tb_visit = (ToggleButton) itemView.findViewById(R.id.tb_visit);
            tb_monopoly = (ToggleButton) itemView.findViewById(R.id.tb_monopoly);
            tb_complete = (ToggleButton) itemView.findViewById(R.id.tb_complete);
            tb_comparativeSales = (ToggleButton) itemView.findViewById(R.id.tb_comparativeSales);
            tb_policyProcessAdvocacy = (ToggleButton) itemView.findViewById(R.id.tb_policyProcessAdvocacy);
            tb_train = (ToggleButton) itemView.findViewById(R.id.tb_train);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(JiuyiRetailChannelBean.RetailChannelItemsBean viewTypeBean) {

            if(viewTypeBean.getMember()!=null && viewTypeBean.getMember().getOrgName()!=null ){
                tv_client.setText(viewTypeBean.getMember().getOrgName());
            }
            tb_visit.setChecked(viewTypeBean.isVisit());
            tb_monopoly.setChecked(viewTypeBean.isMonopoly());
            tb_complete.setChecked(viewTypeBean.isComplete());
            tb_comparativeSales.setChecked(viewTypeBean.isComparativeSales());
            tb_policyProcessAdvocacy.setChecked(viewTypeBean.isPolicyProcessAdvocacy());
            tb_train.setChecked(viewTypeBean.isTrain());


        }
        public void setdisable() {

            tb_visit.setEnabled(false);
            tb_monopoly.setEnabled(false);
            tb_complete.setEnabled(false);
            tb_comparativeSales.setEnabled(false);
            tb_policyProcessAdvocacy.setEnabled(false);
            tb_train.setEnabled(false);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<JiuyiRetailChannelBean.RetailChannelItemsBean> addCustomerList) {
        //增加数据
        int position = mViewTypeBeanList.size();
        mViewTypeBeanList.addAll(position, addCustomerList);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void refresh(List<JiuyiRetailChannelBean.RetailChannelItemsBean> newList) {
        //刷新数据
        mViewTypeBeanList.removeAll(mViewTypeBeanList);
        mViewTypeBeanList.addAll(newList);
        notifyDataSetChanged();
    }
}
