package commonlyused.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.BGAProgressBar;
import com.jiuyi.tools.jiuyiViewUtil;
import com.wanglicrm.android.R;

import java.util.List;

import commonlyused.bean.JiuyiRetailChannelBean;


public class RetailChannelListAdapter extends BaseQuickAdapter<JiuyiRetailChannelBean.ContentBean,BaseViewHolder>  {


    public String getBackPageType() {
        return backPageType;
    }

    public void setBackPageType(String backPageType) {
        this.backPageType = backPageType;
    }

    private String backPageType="";


    public RetailChannelListAdapter(int layoutResId, List<JiuyiRetailChannelBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final JiuyiRetailChannelBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_name = holder.getView(R.id.tv_name);
        if(item.getOperator()!=null && item.getOperator().getName()!=null ){
            tv_name.setText(item.getOperator().getName());
        }
        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getWorkPlanDate()!=null  ){
            tv_date.setText(item.getWorkPlanDate());
        }
        TextView tv_salesTarget = holder.getView(R.id.tv_salesTarget);
        tv_salesTarget.setText(Func.addCommaChart(item.getSalesTarget()+""));
        TextView tv_cumulativeShipments = holder.getView(R.id.tv_cumulativeShipments);
        tv_cumulativeShipments.setText(Func.addCommaChart(item.getCumulativeShipments()+""));

        TextView tv_projectedShipment = holder.getView(R.id.tv_projectedShipment);
        tv_projectedShipment.setText(Func.addCommaChart(item.getProjectedShipment()+""));
        TextView tv_actualShipment = holder.getView(R.id.tv_actualShipment);
        tv_actualShipment.setText(Func.addCommaChart(item.getActualShipment()+""));
        BGAProgressBar Progress_1 =holder.getView(R.id.Progress_1);
        String actualship=item.getOmpletionRate()+"";
        if(actualship.indexOf('.')>0){
            actualship=actualship.substring(0,actualship.indexOf('.'));

        }else if(actualship.indexOf('%')>0){
            actualship=actualship.substring(0,actualship.indexOf('%'));
        }
        Progress_1.setProgress(Integer.parseInt(actualship));
    }

    private RetailChannelListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(RetailChannelListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, RetailChannelListAdapter receiveAddress, View view);
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
