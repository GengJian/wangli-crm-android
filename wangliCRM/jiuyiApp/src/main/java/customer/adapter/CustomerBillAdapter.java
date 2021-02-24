package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerBillBean;
import customer.entity.CustomerInvoiceBean;


public class CustomerBillAdapter extends BaseQuickAdapter<CustomerBillBean.ContentBean,BaseViewHolder>  {

    public CustomerBillAdapter(int layoutResId, List<CustomerBillBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerBillBean.ContentBean item) {
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
//        if(item.get()!=null){
//            client+="|"+item.getDeliveryNumber();
//        }
        tv_client.setText(client);

        if(item.getFkdat()!=null){
            tv_year.setText(item.getFkdat().substring(0,4));
            tv_month.setText(item.getFkdat().substring(5,7));
        }
        if(item.getArktx()!=null){
            tv_plansend.setText(item.getArktx());
        }
        if(item.getNumber()!=null){
            number+=item.getNumber();
        }
//        if(item.getOrderNumber()!=null){
//            number+="|"+item.getOrderNumber();
//        }
        tv_no.setText(number);
        if(item.getFkimg()>=0){
            tv_amt.setText(item.getFkimg()+"");
        }

    }



    private CustomerBillAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CustomerBillAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CustomerBillBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CustomerBillBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
