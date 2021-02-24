package customer.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.control.utils.Func;
import com.control.utils.Res;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.TradelogisticsBean;


public class TradelogisticsAdapter extends BaseQuickAdapter<TradelogisticsBean.ContentBean,BaseViewHolder>  {

    public TradelogisticsAdapter(int layoutResId, List<TradelogisticsBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TradelogisticsBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_logisticsno = holder.getView(R.id.tv_logisticsno);
        TextView tv_contactno = holder.getView(R.id.tv_contactno);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        if(item.getSalesContracts()!=null && item.getSalesContracts().size()>0 ){
            tv_contactno.setText(item.getSalesContracts().get(0).getNumber()+"");
        }
        if(item.getStatus()!=null){
            String status="";
            if(item.getStatus().equals("INVOICE")){
                status="发货";
            }else if(item.getStatus().equals("BOOKING")){
                status="贸带订舱";
            }else if(item.getStatus().equals("LOADING")){
                status="装柜";
            }else if(item.getStatus().equals("CUSTOMS")){
                status="报关";
            }else if(item.getStatus().equals("SHIPMENT")){
                status="出运";
            }else if(item.getStatus().equals("LOADINGBILL")){
                status="接受提单";
            }else if(item.getStatus().equals("PAYMENT")){
                status="收款";
            }else if(item.getStatus().equals("DELIVERY")){
                status="交单(客户接受)";
            }else if(item.getStatus().equals("PICKUP")){
                status="提货";
            }
            tv_status.setText(status);
        }

        if(!Func.IsStringEmpty(item.getNumber())){
            String type=item.getNumber();
            SpannableString spanText=new SpannableString(type);
            spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_logisticsno.setText(spanText);
            tv_logisticsno.setTextColor(Res.getColor(null,"jiuyi_link_color"));
        }
        tv_logisticsno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_logisticsno",v);
                }
            }
        });
        tv_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_operation",v);
                }
            }
        });
    }



    private TradelogisticsAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TradelogisticsAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TradelogisticsBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TradelogisticsBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
