package dynamic.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import dynamic.Utils.DynamicUtils;
import commonlyused.bean.JiuyiRetailChannelBean;


public class DynamicQuotePriceAdapter extends BaseQuickAdapter<JiuyiRetailChannelBean.ContentBean,BaseViewHolder>  {
    private List<String> lookedHistory;

    public String getBackPageType() {
        return backPageType;
    }

    public void setBackPageType(String backPageType) {
        this.backPageType = backPageType;
    }

    private String backPageType="";


    public DynamicQuotePriceAdapter(int layoutResId, List<JiuyiRetailChannelBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final JiuyiRetailChannelBean.ContentBean item) {
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
//        if(item.getMember()!=null && item.getMember().getAbbreviation()!=null ){
//            title+=item.getMember().getAbbreviation();
//        }
//        tv_title.setText(title);
//        String sContent="";
//        TextView tv_content = holder.getView(R.id.tv_content);
//
//        List<JiuyiRetailChannelBean.QuotedPriceItemBean> quotedPriceItemBeanList=new ArrayList<>();
//        quotedPriceItemBeanList=item.getQuotedPriceItem();
//        if(quotedPriceItemBeanList!=null && quotedPriceItemBeanList.size()>0){
//            for(int i=0;i<quotedPriceItemBeanList.size();i++){
//                JiuyiRetailChannelBean.QuotedPriceItemBean bean=quotedPriceItemBeanList.get(i);
//                if(bean!=null){
//                    if(bean.getMaterialType()!=null && bean.getMaterialType().getName()!=null){
//                        sContent+=bean.getMaterialType().getName();
//                    }
//                }
//                if(bean.getGears()!=null){
//                    sContent+=",挡位 ( "+bean.getGears()+" )";
//                }
//                sContent+=",数量 ( "+bean.getQuantity()+" )";
//                sContent+=",单价:"+bean.getPrice();
//                if(bean.getUnitValue()!=null){
//
//                    sContent+="/"+bean.getUnitValue();
//                }
//                sContent+="\n";
////                sContent+=","+"报价"+bean.getPrice()+"\n";
//
//            }
//            if(item.getConditionValue()!=null){
//                sContent+="付款条件:"+item.getConditionValue();
//            }
//            if(item.getPayWayValue()!=null){
//                sContent+="\n付款方式:"+item.getPayWayValue();
//            }
//            if(item.getCurrencyValue()!=null){
//                sContent+="\n报价币种:"+item.getCurrencyValue();
//            }
//            tv_content.setText(sContent);
//        }
//        TextView tv_status = holder.getView(R.id.tv_status);
//        if(item.getStatusValue()!=null){
//            tv_status.setText(item.getStatusValue());
//        }
//        String label="";
//        TextView tv_label = holder.getView(R.id.tv_label);
//        if(item.getOperator()!=null){
//            if(item.getOperator().getDepartment()!=null){
//                if(item.getOperator().getDepartment().getName()!=null){
//                    label+=  item.getOperator().getDepartment().getName()+":";
//                }
//
//            }
//            if(item.getOperator().getName()!=null){
//                label+=  item.getOperator().getName();
//            }
//        }
//        tv_label.setText(label);
//        TextView tv_time = holder.getView(R.id.tv_time);
//        if(item.getCreatedDate()!=null){
//              tv_time.setText(JiuyiDateUtil.handleCreateDate(item.getCreatedDate()));
//        }

    }

    private DynamicQuotePriceAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicQuotePriceAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DynamicQuotePriceAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<JiuyiRetailChannelBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<JiuyiRetailChannelBean.ContentBean> newList) {
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
