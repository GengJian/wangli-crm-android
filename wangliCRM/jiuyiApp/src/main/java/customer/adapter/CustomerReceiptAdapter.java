package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerBillBean;
import customer.entity.CustomerReceiptBean;


public class CustomerReceiptAdapter extends BaseQuickAdapter<CustomerReceiptBean.ContentBean,BaseViewHolder>  {

    public CustomerReceiptAdapter(int layoutResId, List<CustomerReceiptBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerReceiptBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        String title="",client="",number="";
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_year = holder.getView(R.id.tv_year);
        TextView tv_receiptTypeValue = holder.getView(R.id.tv_receiptTypeValue);
        TextView tv_client = holder.getView(R.id.tv_client);
        TextView tv_amt = holder.getView(R.id.tv_amt);
        if(item.getNumber()!=null){
            title+=item.getNumber();
        }
        if(item.getCompanyValue()!=null){
            title+="|"+item.getMemberName();
        }
        tv_title.setText(title);

        if(item.getReceiptDate()!=null){
            tv_year.setText(item.getReceiptDate().substring(0,4));
            tv_month.setText(item.getReceiptDate().substring(5,7));
        }
        if(item.getReceiptTypeValue()!=null){
            tv_receiptTypeValue.setText(item.getReceiptTypeValue());
        }
        if(item.getMemberName()!=null){
            tv_client.setText(item.getMemberName());
        }
        if(item.getAmount()>=0){
            tv_amt.setText(item.getAmount()+"");
        }


    }



    private CustomerReceiptAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CustomerReceiptAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CustomerReceiptBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CustomerReceiptBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
