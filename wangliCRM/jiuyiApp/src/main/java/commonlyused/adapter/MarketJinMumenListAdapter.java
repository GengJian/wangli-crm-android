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

import commonlyused.bean.MarketHuaJueBean;
import commonlyused.bean.MarketJinMumenBean;


public class MarketJinMumenListAdapter extends BaseQuickAdapter<MarketJinMumenBean.ContentBean,BaseViewHolder>  {


    public String getBackPageType() {
        return backPageType;
    }

    public void setBackPageType(String backPageType) {
        this.backPageType = backPageType;
    }

    private String backPageType="";


    public MarketJinMumenListAdapter(int layoutResId, List<MarketJinMumenBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final MarketJinMumenBean.ContentBean item) {
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
        TextView tv_salesTargetlabel = holder.getView(R.id.tv_salesTargetlabel);
        TextView tv_salesTarget = holder.getView(R.id.tv_salesTarget);
        tv_salesTarget.setText(Func.addCommaChart(item.getSalesTarget()+""));
        TextView tv_cumulativeShipmentslabel = holder.getView(R.id.tv_cumulativeShipmentslabel);
        tv_cumulativeShipmentslabel.setText("当月发货量");
        TextView tv_cumulativeShipments = holder.getView(R.id.tv_cumulativeShipments);
        tv_cumulativeShipments.setText(Func.addCommaChart(item.getCumulativeShipments()+""));
        TextView tv_projectedShipmentlabel = holder.getView(R.id.tv_projectedShipmentlabel);
        tv_projectedShipmentlabel.setText("计划发货");
        TextView tv_projectedShipment = holder.getView(R.id.tv_projectedShipment);
        tv_projectedShipment.setText(Func.addCommaChart(item.getProjectedShipment()+""));
        TextView tv_actualShipmentlabel = holder.getView(R.id.tv_actualShipmentlabel);
        tv_actualShipmentlabel.setText("实际发货");
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

    private MarketJinMumenListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(MarketJinMumenListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, MarketJinMumenListAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<MarketJinMumenBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<MarketJinMumenBean.ContentBean> newList) {
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
