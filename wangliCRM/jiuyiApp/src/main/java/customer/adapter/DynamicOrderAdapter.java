package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.BGAProgressBar;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.GatheringPlanBean;
import dynamic.entity.DyContractBean;
import dynamic.entity.DyOrderBean;


public class DynamicOrderAdapter extends BaseQuickAdapter<DyOrderBean.ContentBean,BaseViewHolder>  {

    public DynamicOrderAdapter(int layoutResId, List<DyOrderBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DyOrderBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_client = holder.getView(R.id.tv_client);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_year = holder.getView(R.id.tv_year);
        TextView tv_desp = holder.getView(R.id.tv_desp);

        TextView tv_no = holder.getView(R.id.tv_no);
        TextView tv_amt=holder.getView(R.id.tv_amt);

        if(item.getMemberName()!=null){
            tv_client.setText(item.getMemberName());
        }
        if(item.getStatus()!=null){
            tv_status.setText(item.getStatus());
        }

        if(item.getCreateDate()!=null){
            tv_year.setText(item.getCreateDate().substring(0,4));
            tv_month.setText(item.getCreateDate().substring(5,7));
        }
        if(item.getMaterialDesp()!=null){
            tv_desp.setText(item.getMaterialDesp());
        }
        if(item.getMaterialDesp()!=null){
            tv_desp.setText(item.getMaterialDesp());
        }

        String sNum="";
        if(item.getNumber()!=null){
            sNum+=item.getNumber();
        }
        if(item.getAuart()!=null){
            sNum+="|"+item.getAuart();
        }
        tv_no.setText(sNum);
        if(item.getQuantity()>=0 &&item.getUnit()!=null ){
            tv_amt.setText(item.getQuantity()+item.getUnit());
        }
//        tv_plansend.setText(item.getTotalPlanQuantity()+"");
//        tv_actualsend.setText(item.getActualQuantity()+"");
//        if(item.getActualShipString()!=null){
//            String actualship=item.getActualShipString();
//            if(actualship.indexOf('.')>0){
//                actualship=actualship.substring(0,actualship.indexOf('.'));
//
//            }else if(actualship.indexOf('%')>0){
//                actualship=actualship.substring(0,actualship.indexOf('%'));
//            }
//            Progress_1.setProgress(Integer.parseInt(actualship));
//        }

    }

    private DynamicOrderAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicOrderAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DynamicOrderAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyOrderBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<DyOrderBean.ContentBean> newList) {
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
