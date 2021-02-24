package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.SaleSystemBean;
import customer.entity.StandardPriceBean;


public class StandardPriceAdapter extends BaseQuickAdapter<StandardPriceBean.ContentBean,BaseViewHolder>  {

    public StandardPriceAdapter(int layoutResId, List<StandardPriceBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final StandardPriceBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_productName = holder.getView(R.id.tv_productName);
        if(item.getProductName()!=null){
            tv_productName.setText(item.getProductName());
        }
        TextView tv_marketprice = holder.getView(R.id.tv_marketprice);
        tv_marketprice.setText(item.getPriceMaintenance()+"");
        TextView tv_clientprice = holder.getView(R.id.tv_clientprice);
        tv_clientprice.setText(item.getUnitPrice()+"");
        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getStartDate()!=null){
            tv_date.setText(item.getStartDate());
        }

        holder.addOnClickListener(R.id.tv_title);
    }



    private StandardPriceAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(StandardPriceAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<StandardPriceBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<StandardPriceBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
