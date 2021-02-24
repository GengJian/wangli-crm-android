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
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.recyclerview.swipe.SwipeMenuAdapter;
import com.tencent.qcloud.sdk.Constant;

import java.util.List;

import customer.entity.MemberListBean;
import customer.listener.OnItemClickListener;

/**
 *
 */
public class CustomerListAdapter extends SwipeMenuAdapter<CustomerListAdapter.DefaultViewHolder> {

    public static final int VIEW_TYPE_MENU_NONE = 1;
    public static final int VIEW_TYPE_MENU_SINGLE = 2;
    public static final int VIEW_TYPE_MENU_MULTI = 3;
    public static final int VIEW_TYPE_MENU_NODIRECT = 4;
    public static final int VIEW_TYPE_MENU_TRANSFER = 5;
    public static final int VIEW_TYPE_MENU_RELEASE = 6;

    public List<MemberListBean.ContentBean> mViewTypeBeanList;

    private OnItemClickListener mOnItemClickListener;

    public CustomerListAdapter(List<MemberListBean.ContentBean> viewTypeBeanList) {
        this.mViewTypeBeanList = viewTypeBeanList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public void clear() {
        mViewTypeBeanList.clear();
    }

//    @Override
//    public int getItemViewType(int position) {
//        return mViewTypeBeanList.get(position).getViewType();
//    }

    @Override
    public int getItemCount() {
        return mViewTypeBeanList == null ? 0 : mViewTypeBeanList.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.jiuyi_customer_item, parent, false);
    }

    @Override
    public CustomerListAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(CustomerListAdapter.DefaultViewHolder holder, int position) {
        holder.setData(mViewTypeBeanList.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvOrgname,tv_type,tv_clienttype,tv_businessScope,tv_levelvalue,tv_operation,tv_band,tv_icon;
        SimpleDraweeView iv_icon;


        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvOrgname = (TextView) itemView.findViewById(R.id.tv_orgname);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            tv_clienttype = (TextView) itemView.findViewById(R.id.tv_clienttype);
            tv_businessScope = (TextView) itemView.findViewById(R.id.tv_businessScope);
            tv_levelvalue = (TextView) itemView.findViewById(R.id.tv_levelvalue);
            iv_icon= (SimpleDraweeView) itemView.findViewById(R.id.iv_icon);
            tv_icon= (TextView)itemView.findViewById(R.id.tv_icon);
            tv_operation = (TextView) itemView.findViewById(R.id.tv_operation);
            tv_band = (TextView) itemView.findViewById(R.id.tv_band);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(MemberListBean.ContentBean viewTypeBean) {
            if(!Func.IsStringEmpty(viewTypeBean.getOrgName())){
                this.tvOrgname.setText(viewTypeBean.getOrgName());

            }
            if(!Func.IsStringEmpty(viewTypeBean.getStatusValue())){
                this.tv_type.setText(viewTypeBean.getStatusValue());

            }
            if(!Func.IsStringEmpty(viewTypeBean.getStraightShow())){
                this.tv_clienttype.setText(viewTypeBean.getStraightShow());
            }
            if(!Func.IsStringEmpty(viewTypeBean.getLegalName())){
                this.tv_businessScope.setText(viewTypeBean.getLegalName());
            }

            if(viewTypeBean.getAvatarUrl()!=null){
                iv_icon.setVisibility(View.VISIBLE);
                tv_icon.setVisibility(View.GONE);
                LoaderManager.getLoader().loadNet(iv_icon, Constant.BaseUrl+"file/"+viewTypeBean.getAvatarUrl().toString(), new ILoader.Options(R.drawable.client_directsales, R.drawable.client_directsales));
            }else{

                tv_icon.setVisibility(View.VISIBLE);
                iv_icon.setVisibility(View.GONE);
                if(!Func.IsStringEmpty(viewTypeBean.getCooperationTypeKey())){
                    if(viewTypeBean.getCooperationTypeKey().equals("straight_pin")){
                        tv_icon.setText("直销");
                        tv_icon.setBackgroundResource(R.drawable.client_direct_selling);
                    }else if(viewTypeBean.getCooperationTypeKey().equals("opponent")){
                        tv_icon.setText("友商");
                        tv_icon.setBackgroundResource(R.drawable.client_friend_dealer);
                    }else if(viewTypeBean.getCooperationTypeKey().equals("two_level_distributor")){
                        tv_icon.setText("分销商");
                        tv_icon.setBackgroundResource(R.drawable.client_supplier);
                    }else if(viewTypeBean.getCooperationTypeKey().equals("terminal")){
                        tv_icon.setText("终端");
                        tv_icon.setBackgroundResource(R.drawable.client_terminal);
                    }else if(viewTypeBean.getCooperationTypeKey().equals("dealer")){
                        tv_icon.setText("经销商");
                        tv_icon.setBackgroundResource(R.drawable.client_double_distribution);
                    }
                }

            }

            if(!Func.IsStringEmpty(viewTypeBean.getCreditLevelValue())){
                this.tv_levelvalue.setText(viewTypeBean.getCreditLevelValue());
            }
            if(!Func.IsStringEmpty(viewTypeBean.getOperatorName())){
                this.tv_operation.setText(viewTypeBean.getOperatorName());
            }
            if(!Func.IsStringEmpty(viewTypeBean.getBrand())){
                this.tv_band.setText(viewTypeBean.getBrand());
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
    public void add(List<MemberListBean.ContentBean> addCustomerList) {
        //增加数据
        int position = mViewTypeBeanList.size();
        mViewTypeBeanList.addAll(position, addCustomerList);
        notifyItemInserted(position);
        notifyDataSetChanged();
//        notifyListDataSetChanged();
    }

    public void refresh(List<MemberListBean.ContentBean> newList) {
        //刷新数据
        mViewTypeBeanList.removeAll(mViewTypeBeanList);
        mViewTypeBeanList.addAll(newList);
        notifyDataSetChanged();
    }
}
