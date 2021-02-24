package dynamic.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import dynamic.Utils.DynamicUtils;
import dynamic.entity.DyBusinessBean;
import dynamic.entity.DySampleBean;


public class DynamicSampleAdapter extends BaseQuickAdapter<DySampleBean.ContentBean,BaseViewHolder>  {
    private List<String> lookedHistory;

    public String getBackPageType() {
        return backPageType;
    }

    public void setBackPageType(String backPageType) {
        this.backPageType = backPageType;
    }

    private String backPageType="";


    public DynamicSampleAdapter(int layoutResId, List<DySampleBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DySampleBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_dot = holder.getView(R.id.tv_dot);
        lookedHistory= DynamicUtils.getInstance().getSearchList();

        if(lookedHistory!=null &&lookedHistory.contains(backPageType+item.getId())){
            tv_dot.setVisibility(View.GONE);
        }else {
            tv_dot.setVisibility(View.VISIBLE);
        }
        String title="";
        TextView tv_title = holder.getView(R.id.tv_title);
        title+="客户:";
        if(item.getMember()!=null && item.getMember().getOrgName()!=null){
            title+=item.getMember().getOrgName();
        }

        tv_title.setText(title);
        String sContent="";
        TextView tv_content = holder.getView(R.id.tv_content);
        if(item.getTitle()!=null ){
            sContent+=item.getTitle()+"\n";
        }
        if(item.getSampleItems()!=null && item.getSampleItems().size()>0){
            for(int i=0;i<item.getSampleItems().size();i++){
                DySampleBean.ContentBean.SampleItemsBean sampleItemsBean=item.getSampleItems().get(i);
                if(sampleItemsBean.getProductName()!=null){
                    sContent+=sampleItemsBean.getProductName();
                }
                if(sampleItemsBean.getEfficiencyConvert()!=null  ){
                    sContent+=",功率("+sampleItemsBean.getEfficiencyConvert().getWpc()+")";
                }
                if(sampleItemsBean.getEffectiveness()!=null){
                    sContent+=",效率("+sampleItemsBean.getEffectiveness()+")";
                }
                sContent+=",数量("+sampleItemsBean.getAmount()+")";
//                if(sampleItemsBean.getMaterialNum()!=null){
//                    sContent+=sampleItemsBean.getMaterialNum();
//                }
//                if(sampleItemsBean.getUnitValue()!=null){
//                    sContent+=sampleItemsBean.getUnitValue();
//                }
//                sContent+=sampleItemsBean.getAmount();
                sContent+="\n";

            }
            tv_content.setText(sContent);
        }
        TextView tv_status = holder.getView(R.id.tv_status);
        if(item.getStatusDesp()!=null){
            tv_status.setText(item.getStatusDesp());
        }
        String label="";
        TextView tv_label = holder.getView(R.id.tv_label);
        if(item.getOperator()!=null){
            if(item.getOperator().getDepartment()!=null){
                if(item.getOperator().getDepartment().getName()!=null){
                    label+=  item.getOperator().getDepartment().getName()+":";
                }

            }
            if(item.getOperator().getName()!=null){
                label+=  item.getOperator().getName();
            }
        }
        tv_label.setText(label);
        TextView tv_time = holder.getView(R.id.tv_time);
        if(item.getCreatedDate()!=null){
              tv_time.setText(JiuyiDateUtil.handleCreateDate(item.getCreatedDate()));
        }

    }

    private DynamicSampleAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicSampleAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DynamicSampleAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DySampleBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<DySampleBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
    public void clear(){
        getData().clear();
        notifyDataSetChanged();
    }

}
