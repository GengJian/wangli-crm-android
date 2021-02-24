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

import com.recyclerview.swipe.SwipeMenuAdapter;
import com.wanglicrm.android.R;

import java.util.List;

import commonlyused.bean.MarketEngineeringBean;
import customer.listener.OnItemClickListener;

/**
 *
 */
public class NewMarketClientMutiAdapter extends SwipeMenuAdapter<NewMarketClientMutiAdapter.DefaultViewHolder> {

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    private  String viewType="";

    public static final int VIEW_TYPE_MENU_DELETE = 1;


    public List<MarketEngineeringBean.MarketEngineeringItemsBean> getmViewTypeBeanList() {
        return mViewTypeBeanList;
    }

    public void setmViewTypeBeanList(List<MarketEngineeringBean.MarketEngineeringItemsBean> mViewTypeBeanList) {
        this.mViewTypeBeanList = mViewTypeBeanList;
    }

    public List<MarketEngineeringBean.MarketEngineeringItemsBean> mViewTypeBeanList;

    private OnItemClickListener mOnItemClickListener;

    public NewMarketClientMutiAdapter(List<MarketEngineeringBean.MarketEngineeringItemsBean> viewTypeBeanList) {
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
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.jiuyi_newmarketclientmulti_item, parent, false);
    }

    @Override
    public NewMarketClientMutiAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(NewMarketClientMutiAdapter.DefaultViewHolder holder, int position) {
        holder.setData(mViewTypeBeanList.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
        if(viewType.equals("VIEW")){
            holder.setdisable();
        }

    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_client,tv_handlingMatters;
        ToggleButton tb_isvisit;

        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_client = (TextView) itemView.findViewById(R.id.tv_client);
            tb_isvisit = (ToggleButton) itemView.findViewById(R.id.tb_isvisit);
            tv_handlingMatters = (TextView) itemView.findViewById(R.id.tv_handlingMatters);

        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(MarketEngineeringBean.MarketEngineeringItemsBean viewTypeBean) {

            if(viewTypeBean.getMember()!=null && viewTypeBean.getMember().getOrgName()!=null ){
                tv_client.setText(viewTypeBean.getMember().getOrgName());
            }
            tb_isvisit.setChecked(viewTypeBean.isVisit());
            if(viewTypeBean.getHandlingMatters()!=null ){
                tv_handlingMatters.setText(viewTypeBean.getHandlingMatters());
            }

        }
        public void setdisable() {
            tb_isvisit.setEnabled(false);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<MarketEngineeringBean.MarketEngineeringItemsBean> addCustomerList) {
        //增加数据
        int position = mViewTypeBeanList.size();
        mViewTypeBeanList.addAll(position, addCustomerList);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void refresh(List<MarketEngineeringBean.MarketEngineeringItemsBean> newList) {
        //刷新数据
        mViewTypeBeanList.removeAll(mViewTypeBeanList);
        mViewTypeBeanList.addAll(newList);
        notifyDataSetChanged();
    }
}