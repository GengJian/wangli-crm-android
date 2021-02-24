package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Res;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.VisitActivityListBean;
import dynamic.entity.OrderBean;


public class VisitItemListAdapter extends BaseQuickAdapter<VisitActivityListBean,BaseViewHolder>  {

      public VisitItemListAdapter(int layoutResId, List<VisitActivityListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final VisitActivityListBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_dot = holder.getView(R.id.tv_dot);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_creator = holder.getView(R.id.tv_creator);
        if(item.getPurpose()!=null){
            tv_title.setText(item.getPurpose());
        }
        if(item.getBeginDate()!=null){
            tv_time.setText(item.getBeginDate());
        }
        if(item.getCreatedBy()!=null){
            tv_creator.setText(item.getCreatedBy());
        }

        if(item.getStatus()!=null){
            if(item.getStatus().equals("end")){
                tv_status.setTextColor(Res.getColor(null,"jiuyi_info2_color"));
                tv_title.setTextColor(Res.getColor(null,"jiuyi_info2_color"));
                tv_dot.setBackgroundResource(R.drawable.customer_undodot);
                if(item.getStatusValue()!=null){
                    tv_status.setText(item.getStatusValue());
                }
            }else{
                tv_dot.setBackgroundResource(R.drawable.customer_dodot);
                tv_status.setTextColor(Res.getColor(null,"jiuyi_red_color"));
                tv_title.setTextColor(Res.getColor(null,"jiuyi_title_color"));
                tv_status.setText("未完成");
            }
        }
    }

    private VisitItemListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(VisitItemListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, VisitItemListAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<VisitActivityListBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<VisitActivityListBean> newList) {
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
