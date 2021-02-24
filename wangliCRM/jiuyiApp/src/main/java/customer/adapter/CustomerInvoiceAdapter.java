package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerInvoiceBean;
import customer.entity.TradedeliveryBean;


public class CustomerInvoiceAdapter extends BaseQuickAdapter<CustomerInvoiceBean.ContentBean,BaseViewHolder>  {

    public CustomerInvoiceAdapter(int layoutResId, List<CustomerInvoiceBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerInvoiceBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        String client="",number="";
        TextView tv_client = holder.getView(R.id.tv_client);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_year = holder.getView(R.id.tv_year);
        TextView tv_plansend = holder.getView(R.id.tv_plansend);
        TextView tv_no = holder.getView(R.id.tv_no);
        TextView tv_amt = holder.getView(R.id.tv_amt);
        if(item.getMemberName()!=null){
            client+=item.getMemberName();
        }
        if(item.getDeliveryNumber()!=null){
            client+="|"+item.getDeliveryNumber();
        }
        tv_client.setText(client);
        tv_status.setVisibility(View.GONE);
        if(item.getErdat()!=null){
            tv_year.setText(item.getErdat().substring(0,4));
            tv_month.setText(item.getErdat().substring(5,7));
        }
        if(item.getMaterialDesp()!=null){
            tv_plansend.setText(item.getMaterialDesp());
        }
        if(item.getMaterialNumber()!=null){
            number+=item.getMaterialNumber();
        }
        if(item.getOrderNumber()!=null){
            number+="|"+item.getOrderNumber();
        }
        tv_no.setText(number);
        if(item.getQuantity()>=0){
            tv_amt.setText(item.getQuantity()+"");
        }

    }



    private CustomerInvoiceAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CustomerInvoiceAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CustomerInvoiceBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CustomerInvoiceBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
