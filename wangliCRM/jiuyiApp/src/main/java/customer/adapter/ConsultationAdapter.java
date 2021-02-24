package customer.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.TradedeliveryBean;
import dynamic.entity.DyActivityBean;


public class ConsultationAdapter extends BaseQuickAdapter<DyActivityBean.ContentBean,BaseViewHolder>  {

    public ConsultationAdapter(int layoutResId, List<DyActivityBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DyActivityBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
//        TextView tv_title = holder.getView(R.id.tv_title);
//        LinearLayout ll_repay = holder.getView(R.id.ll_repay);
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
//        tv_title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null)
//                {
//                    listener.click(holder.getLayoutPosition(),"tv_title",v);
//                }
//            }
//        });
//        ll_repay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null)
//                {
//                    listener.click(holder.getLayoutPosition(),"ll_repay",v);
//                }
//            }
//        });
    }



    private ConsultationAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ConsultationAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyActivityBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<DyActivityBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
