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

import customer.entity.TradedeliveryBean;


public class TradedeliveryAdapter extends BaseQuickAdapter<TradedeliveryBean.ContentBean,BaseViewHolder>  {

    public TradedeliveryAdapter(int layoutResId, List<TradedeliveryBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TradedeliveryBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
//        TextView tv_deliveryno = holder.getView(R.id.tv_deliveryno);
//        TextView tv_orderno = holder.getView(R.id.tv_orderno);
//        TextView tv_deliverydate = holder.getView(R.id.tv_deliverydate);
//        TextView tv_operation = holder.getView(R.id.tv_operation);
//        if(item.getOrderSapNumber()!=null){
//            tv_orderno.setText(item.getOrderSapNumber().toString());
//        }
//        if(item.getDeliveryDate()!=null){
//            tv_deliverydate.setText(item.getDeliveryDate().toString().replace("-",""));
//        }
//        if(item.getInvoiceNumber()!=null){
//            if(!Func.IsStringEmpty(item.getInvoiceNumber().toString())){
//                String type=item.getInvoiceNumber().toString();
//                SpannableString spanText=new SpannableString(type);
//                spanText.setSpan(new UnderlineSpan(), 0, type.length(),
//                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                tv_deliveryno.setText(spanText);
//                tv_deliveryno.setTextColor(Res.getColor(null,"jiuyi_link_color"));
//            }
//        }
//
//        tv_deliveryno.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null)
//                {
//                    listener.click(holder.getLayoutPosition(),"tv_deliveryno",v);
//                }
//            }
//        });
//        tv_operation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null)
//                {
//                    listener.click(holder.getLayoutPosition(),"tv_operation",v);
//                }
//            }
//        });
    }



    private TradedeliveryAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TradedeliveryAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TradedeliveryBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TradedeliveryBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
