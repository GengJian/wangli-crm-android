package orders.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;
import com.loader.ILoader;
import com.loader.LoaderManager;

import java.util.List;

import orders.entity.OrderBean;


public class OrderListAdapter extends BaseQuickAdapter<OrderBean.ContentBean,BaseViewHolder>  {

      public OrderListAdapter(int layoutResId, List<OrderBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final OrderBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        SimpleDraweeView iv_icon= holder.getView(R.id.iv_icon);
        if(item.getAvatarUrl()!=null){
            LoaderManager.getLoader().loadNet(iv_icon,item.getAvatarUrl().toString(), new ILoader.Options(R.drawable.client_directsales, R.drawable.client_directsales));
        }
        TextView tv_date = holder.getView(R.id.tv_date);
        TextView tv_productstatus = holder.getView(R.id.tv_productstatus);
        if(item.getStatusDesp()!=null){
            tv_productstatus.setText(item.getStatusDesp());
        }
        TextView tv_clientname = holder.getView(R.id.tv_clientname);
        if(item.getAbbreviation()!=null){
            tv_clientname.setText(item.getAbbreviation());
        }else{
            tv_clientname.setText("");
        }
        TextView tv_address = holder.getView(R.id.tv_address);
        if(item.getAddress()!=null){
            tv_address.setText(item.getAddress());
        }

        TextView tv_producttype = holder.getView(R.id.tv_producttype);
        if(item.getOrderItem()!=null && item.getOrderItem().size()>0){
            String productype="";
            OrderBean.ContentBean.OrderItemBean orderItemBean=item.getOrderItem().get(0);
            if(orderItemBean!=null){
                if(orderItemBean.getSpec()!=null){
                    productype+=orderItemBean.getSpec()+"/";
                }
                if(orderItemBean.getBatchNumber()!=null){
                    productype+=orderItemBean.getBatchNumber()+"/";
                }
                if(orderItemBean.getGradeName()!=null){
                    productype+=orderItemBean.getGradeName();
                }
                tv_producttype.setText(productype);
            }
        }


        TextView tv_salername = holder.getView(R.id.tv_salername);
        if(item.getSalemanName()!=null){
            tv_salername.setText(item.getSalemanName());
        }
        if(item.getOrderDate()!=null){
            tv_date.setText(item.getOrderDate().toString());
        }
        TextView tv_amt = holder.getView(R.id.tv_amt);
        String wearsSign="";
        if(item.getWearsSign()!=null){
            wearsSign=item.getWearsSign();
        }else{
            wearsSign="￥";
        }
        tv_amt.setText(wearsSign+ Func.addComma(item.getFinalAmount()+""));
        if(item.getAuthBean()!=null){
            if(!item.getAuthBean().isPrice()){
                tv_amt.setVisibility(View.INVISIBLE);
            }else{
                tv_amt.setVisibility(View.VISIBLE);
            }
        }
    }

    private OrderListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(OrderListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, OrderListAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<OrderBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<OrderBean.ContentBean> newList) {
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
