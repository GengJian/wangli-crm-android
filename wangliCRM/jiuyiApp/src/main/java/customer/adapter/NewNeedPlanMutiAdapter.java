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
import customer.listener.OnItemClickListener;

/**
 *
 */
public class NewNeedPlanMutiAdapter extends SwipeMenuAdapter<NewNeedPlanMutiAdapter.DefaultViewHolder> {

    public static final int VIEW_TYPE_MENU_DELETE = 1;


    public List<BatchTransBean> mViewTypeBeanList;

    private OnItemClickListener mOnItemClickListener;

    public NewNeedPlanMutiAdapter(List<BatchTransBean> viewTypeBeanList) {
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
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.jiuyi_newneedplan_item, parent, false);
    }

    @Override
    public NewNeedPlanMutiAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(NewNeedPlanMutiAdapter.DefaultViewHolder holder, int position) {
        holder.setData(mViewTypeBeanList.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_batchNum,tv_fatory,tv_level,tv_spec,tv_weight,tv_count;
        SimpleDraweeView iv_icon;


        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_batchNum = (TextView) itemView.findViewById(R.id.tv_batchNum);
            tv_fatory = (TextView) itemView.findViewById(R.id.tv_fatory);
            tv_spec = (TextView) itemView.findViewById(R.id.tv_spec);
            tv_weight = (TextView) itemView.findViewById(R.id.tv_weight);
            tv_level = (TextView) itemView.findViewById(R.id.tv_level);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);

        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(BatchTransBean viewTypeBean) {
            if(!Func.IsStringEmpty(viewTypeBean.getBatchNumber())){
                this.tv_batchNum.setText(viewTypeBean.getBatchNumber());
            }
            if(!Func.IsStringEmpty(viewTypeBean.getFactoryName())){
                this.tv_fatory.setText(viewTypeBean.getFactoryName());
            }
            if(!Func.IsStringEmpty(viewTypeBean.getProductLevelName())){
                this.tv_level.setText(viewTypeBean.getProductLevelName());
            }
            if(!Func.IsStringEmpty(viewTypeBean.getSpec())){
                this.tv_spec.setText("规格:"+viewTypeBean.getSpec());
            }
            if(viewTypeBean.getWeight()>0){
                this.tv_weight.setText("件重:"+viewTypeBean.getWeight()+"");
            }
            if(viewTypeBean.getQuantity()>0){
                this.tv_count.setText("数量: "+viewTypeBean.getQuantity());
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
    public void add(List<BatchTransBean> addCustomerList) {
        //增加数据
        int position = mViewTypeBeanList.size();
        mViewTypeBeanList.addAll(position, addCustomerList);
        notifyItemInserted(position);
        notifyDataSetChanged();
//        notifyListDataSetChanged();
    }

    public void refresh(List<BatchTransBean> newList) {
        //刷新数据
        mViewTypeBeanList.removeAll(mViewTypeBeanList);
        mViewTypeBeanList.addAll(newList);
        notifyDataSetChanged();
    }
}
