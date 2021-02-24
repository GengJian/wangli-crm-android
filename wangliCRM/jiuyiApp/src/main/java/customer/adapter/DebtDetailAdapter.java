package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerDebtBean;


public class DebtDetailAdapter extends BaseQuickAdapter<CustomerDebtBean.ContentBean.CompanyListBean,BaseViewHolder>  {

      public DebtDetailAdapter(int layoutResId, List<CustomerDebtBean.ContentBean.CompanyListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerDebtBean.ContentBean.CompanyListBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_customercode = holder.getView(R.id.tv_customercode);
        if(item.getCnumber()!=null){
            tv_customercode.setText(item.getCnumber());
        }
        TextView tv_customername = holder.getView(R.id.tv_customername);
        if(item.getAbbreviation()!=null){
            tv_customername.setText(item.getAbbreviation());
        }
        TextView tv_knkli = holder.getView(R.id.tv_knkli);
        if(item.getKnkli()!=null){
            tv_knkli.setText(item.getKnkli());
        }
        TextView tv_owedTotalAmount = holder.getView(R.id.tv_owedTotalAmount);
        if(item.getOwedTotalAmount()!=null){
            tv_owedTotalAmount.setText(item.getOwedTotalAmount()+"");
        }
    }

    private DebtDetailAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DebtDetailAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DebtDetailAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CustomerDebtBean.ContentBean.CompanyListBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CustomerDebtBean.ContentBean.CompanyListBean> newList) {
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
