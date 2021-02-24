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


public class DynamicBusinessAdapter extends BaseQuickAdapter<DyBusinessBean.ContentBean,BaseViewHolder>  {
    private List<String> lookedHistory;

    public String getBackPageType() {
        return backPageType;
    }

    public void setBackPageType(String backPageType) {
        this.backPageType = backPageType;
    }

    private String backPageType="";


    public DynamicBusinessAdapter(int layoutResId, List<DyBusinessBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DyBusinessBean.ContentBean item) {
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
        title+="来源:";
        if(item.getResourceValue()!=null){
            title+=item.getResourceValue();
        }

        tv_title.setText(title);
        TextView tv_content = holder.getView(R.id.tv_content);
        String sContent="#";
//        if(item.getTitle()!=null){
//            sContent+=item.getTitle();
//        }
        if(item.getMaterialTypes()!=null && item.getMaterialTypes().size()>0 ){
            for(int i=0;i<item.getMaterialTypes().size();i++){
                if(item.getMaterialTypes().get(i).getName()!=null){
                    sContent+=item.getMaterialTypes().get(i).getName();
                }
                if(i!=item.getMaterialTypes().size()-1){
                    sContent+=",";
                }
            }
            sContent+="#,金额:"+item.getAmount()+"\n";
        }

        if(item.getContent()!=null){
            sContent+="备注:"+item.getContent();
        }
        tv_content.setText(sContent);
        TextView tv_status = holder.getView(R.id.tv_status);
        if(item.getStatusValue()!=null){
            tv_status.setText(item.getStatusValue());
        }
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
        String label="创建人：";
        TextView tv_label = holder.getView(R.id.tv_label);

        if(item.getOperator()!=null){
            if(item.getOperator().getTitle()!=null){
                label+=  item.getOperator().getTitle();
            }
            if(item.getOperator().getName()!=null){
                label+=  item.getOperator().getName();
            }
        }
//        if(item.getImportantValue()!=null){
//            label+= "| 重要性:"+ item.getImportantValue();
//        }
        tv_label.setText(label);
        TextView tv_time = holder.getView(R.id.tv_time);
        if(item.getCreatedDate()!=null){
              tv_time.setText(JiuyiDateUtil.handleCreateDate(item.getCreatedDate()));
        }

    }

    private DynamicBusinessAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicBusinessAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DynamicBusinessAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyBusinessBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<DyBusinessBean.ContentBean> newList) {
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
