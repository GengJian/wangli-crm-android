package mine.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.jiuyi.tools.BGAProgressBar;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import mine.bean.MineDeliveryPlanBean;


public class NeedPlanTotalAdapter extends BaseQuickAdapter<MineDeliveryPlanBean.ContentBean,BaseViewHolder>  {
    private String type="";

    public NeedPlanTotalAdapter(int layoutResId, List<MineDeliveryPlanBean.ContentBean> data,String planType) {
        super(layoutResId, data);
        type=planType;
    }

    @Override
    protected void convert(final BaseViewHolder holder,final MineDeliveryPlanBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_year = holder.getView(R.id.tv_year);
        TextView tv_planno = holder.getView(R.id.tv_planno);

        TextView tv_plansend = holder.getView(R.id.tv_plansend);
        TextView tv_actualsend=holder.getView(R.id.tv_actualsend);
        BGAProgressBar Progress_1 =holder.getView(R.id.Progress_1);


        tv_month.setText(item.getMonth()+"");
        tv_year.setText(item.getYear()+"");
        if(item.getBatchNumber()!=null && type.equals("BATCHNUMBER")){
            tv_planno.setText(item.getBatchNumber()+"");
        }
        if(item.getName()!=null  && type.equals("MEMBER")){
            tv_planno.setText(item.getName());
        }
        tv_plansend.setText(item.getTotalPlanQuantity()+"");
        tv_actualsend.setText(item.getActualQuantity()+"");
        if(item.getActualShipString()!=null){
            String actualship=item.getActualShipString();
            if(actualship.indexOf('.')>0){
                actualship=actualship.substring(0,actualship.indexOf('.'));

            }else if(actualship.indexOf('%')>0){
                actualship=actualship.substring(0,actualship.indexOf('%'));
            }
            Progress_1.setProgress(Integer.parseInt(actualship));
        }
    }

    private NeedPlanTotalAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(NeedPlanTotalAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, NeedPlanTotalAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<MineDeliveryPlanBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<MineDeliveryPlanBean.ContentBean> newList) {
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
