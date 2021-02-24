package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerProductInfoBean;
import customer.entity.ProductStandardBean;


public class CustomerProductInfoAdapter extends BaseQuickAdapter<CustomerProductInfoBean.ContentBean,BaseViewHolder>  {

    public CustomerProductInfoAdapter(int layoutResId, List<CustomerProductInfoBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerProductInfoBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getBusinessTypeValue()!=null){
            tv_title.setText(item.getBusinessTypeValue());
        }
        TextView tv_spec = holder.getView(R.id.tv_spec);
        if(item.getProcess()!=null){
            tv_spec.setText(item.getProcess());
        }
        TextView tv_cost = holder.getView(R.id.tv_cost);
        tv_cost.setText(item.getCost()+"");

        TextView tv_price = holder.getView(R.id.tv_price);
        tv_price.setText(item.getProductPrice()+"");


    }



    private CustomerProductInfoAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CustomerProductInfoAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CustomerProductInfoBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CustomerProductInfoBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
