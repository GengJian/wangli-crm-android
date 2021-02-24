package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.view.DynamicUrlTextView;
import dynamic.Utils.DynamicUtils;
import dynamic.entity.DyActivityBean;
import dynamic.entity.DyInteligenceBean;


public class DynamicActivityAdapter extends BaseQuickAdapter<DyActivityBean.ContentBean,BaseViewHolder>  {
    private List<String> lookedHistory;

    public String getBackPageType() {
        return backPageType;
    }

    public void setBackPageType(String backPageType) {
        this.backPageType = backPageType;
    }

    private String backPageType="";


    public DynamicActivityAdapter(int layoutResId, List<DyActivityBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DyActivityBean.ContentBean item) {
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
        title+="类型:";
        if(item.getActivityTypeValue()!=null){
            title+=item.getActivityTypeValue();
        }

        tv_title.setText(title);
        TextView tv_content = holder.getView(R.id.tv_content);
        if(item.getContent()!=null){
            tv_content.setText(item.getContent());
        }
        TextView tv_status = holder.getView(R.id.tv_status);
        if(item.getApproveStatusValue()!=null){
            tv_status.setText(item.getApproveStatusValue());
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
        if(!Func.IsStringEmpty(item.getImportantValue())){
            label+= "| 重要性:"+ item.getImportantValue();
        }
        tv_label.setText(label);
        TextView tv_time = holder.getView(R.id.tv_time);
        if(item.getCreatedDate()!=null){
              tv_time.setText(JiuyiDateUtil.handleCreateDate(item.getCreatedDate()));
        }

    }

    private DynamicActivityAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicActivityAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DynamicActivityAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyActivityBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<DyActivityBean.ContentBean> newList) {
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
