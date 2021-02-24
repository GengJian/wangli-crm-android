package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.view.DynamicUrlTextView;
import dynamic.Utils.DynamicUtils;
import dynamic.entity.DyInteligenceBean;
import dynamic.entity.DyReceiptBean;


public class DynamicReceiptAdapter extends BaseQuickAdapter<DyReceiptBean.ContentBean,BaseViewHolder>  {
    private List<String> lookedHistory;

    public String getBackPageType() {
        return backPageType;
    }

    public void setBackPageType(String backPageType) {
        this.backPageType = backPageType;
    }

    private String backPageType="";

    public DynamicReceiptAdapter(int layoutResId, List<DyReceiptBean.ContentBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(final BaseViewHolder holder,final DyReceiptBean.ContentBean item) {
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
        if(item.getMemberNumber()!=null ){
            title+=item.getMemberNumber();
        }
        if(item.getMember()!=null && item.getMember().getAbbreviation()!=null){
            title+="|"+item.getMember().getAbbreviation();
        }
//        if(item.getIntelligence()!=null &&item.getIntelligence().getBusinessTypeValue()!=null ){
//            title+="/"+item.getIntelligence().getBusinessTypeValue();
//        }
//        if(item.getIntelligence()!=null &&item.getIntelligenceInfoValue()!=null ){
//            title+="/"+item.getIntelligenceInfoValue();
//        }
//        if(item.getIntelligence()!=null &&item.getIntelligenceTypeValue()!=null ){
//            title+="/"+item.getIntelligenceTypeValue();
//        }
        tv_title.setText(title);
        TextView tv_content = holder.getView(R.id.tv_content);
        String sContent="";
        if(item.getCompanyValue()!=null){
            sContent+=item.getCompanyValue();
        }
        if(item.getReceiptTypeValue()!=null){
            sContent+="|"+item.getReceiptTypeValue();
        }
        if(item.getCurrencyValue()!=null){
            sContent+="|"+item.getCurrencyValue();
        }
        sContent+=" "+item.getAmount();
        tv_content.setText(sContent);

        String label="";
        TextView tv_label = holder.getView(R.id.tv_label);
        if(item.getTypeDesp()!=null){
            label+= item.getTypeDesp();
        }
//        if(item.getCreatedBy()!=null){
//            label+=  item.getCreatedBy();
//        }
        tv_label.setText(label);
        TextView tv_time = holder.getView(R.id.tv_time);
        if(item.getCreatedDate()!=null){
              tv_time.setText(JiuyiDateUtil.handleCreateDate(item.getCreatedDate()));
        }

    }

    private DynamicReceiptAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicReceiptAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DynamicReceiptAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyReceiptBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<DyReceiptBean.ContentBean> newList) {
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
