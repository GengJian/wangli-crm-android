package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.BGAProgressBar;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import dynamic.entity.DyContractBean;


public class DynamicContractAdapter extends BaseQuickAdapter<DyContractBean.ContentBean,BaseViewHolder>  {
    private List<String> lookedHistory;

    public String getBackPageType() {
        return backPageType;
    }

    public void setBackPageType(String backPageType) {
        this.backPageType = backPageType;
    }

    private String backPageType="";


    public DynamicContractAdapter(int layoutResId, List<DyContractBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DyContractBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());

        TextView tv_client = holder.getView(R.id.tv_client);
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_year = holder.getView(R.id.tv_year);

        TextView tv_plansend = holder.getView(R.id.tv_plansend);
        TextView tv_plansendlable=holder.getView(R.id.tv_plansendlable);


        TextView tv_actualsend=holder.getView(R.id.tv_actualsend);
        TextView tv_actualsendlabel = holder.getView(R.id.tv_actualsendlabel);


        BGAProgressBar Progress_1 =holder.getView(R.id.Progress_1);

        if(item.getCreateDate()!=null){
            tv_year.setText(item.getCreateDate().substring(0,4));
            tv_month.setText(item.getCreateDate().substring(5,7));
        }
        String sClient="";
        if(!Func.IsStringEmpty(item.getMaterialDesp())){
            sClient+=item.getMaterialDesp();
        }else{
            if(item.getMemberName()!=null){
                sClient+=item.getMemberName();
            }
            if(item.getCrmNumber()!=null){
                sClient+="|"+item.getCrmNumber();
            }
        }
        tv_client.setText(sClient);

        if(item.getQuantity()>=0){
            tv_plansend.setText(item.getQuantity()+"");
        }
        if(item.getUnit()!=null){
            tv_plansendlable.setText("合同量("+item.getUnit()+")");
            tv_actualsendlabel.setText("实发("+item.getUnit()+")");
        }
        if(item.getActualQuantity()>=0){
            tv_actualsend.setText(item.getActualQuantity()+"");
        }
        if(item.getRate()!=null){
            String actualship=item.getRate();
            if(actualship.indexOf('.')>0){
                actualship=actualship.substring(0,actualship.indexOf('.'));

            }else if(actualship.indexOf('%')>0){
                actualship=actualship.substring(0,actualship.indexOf('%'));
            }
            Progress_1.setProgress(Integer.parseInt(actualship));
        }
    }

    private DynamicContractAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicContractAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DynamicContractAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyContractBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<DyContractBean.ContentBean> newList) {
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
