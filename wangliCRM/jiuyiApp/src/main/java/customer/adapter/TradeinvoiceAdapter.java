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

import customer.entity.TradeinvoiceBean;


public class TradeinvoiceAdapter extends BaseQuickAdapter<TradeinvoiceBean.ContentBean,BaseViewHolder>  {

    public TradeinvoiceAdapter(int layoutResId, List<TradeinvoiceBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TradeinvoiceBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_invoiceno = holder.getView(R.id.tv_invoiceno);
        TextView tv_taxno = holder.getView(R.id.tv_taxno);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        if(item.getPrice()>=0){
            tv_taxno.setText(item.getPrice()+"");
//            tv_taxno.setText(item.getGoldenTaxNumber());
        }

        if(item.getStatusDesp()!=null){
//            String status="";
//            if(item.getStatus().equals("SUSPENDING")){
//                status="待处理";
//            }else if(item.getStatus().equals("READY")){
//                status="待邮寄";
//            }else if(item.getStatus().equals("HANDLING")){
//                status="邮寄中";
//            }else if(item.getStatus().equals("SOLVED")){
//                status="已完成";
//            }
            tv_status.setText(item.getStatusDesp());
        }
        if(!Func.IsStringEmpty(item.getNumber())){
            String type=item.getNumber();
            SpannableString spanText=new SpannableString(type);
            spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_invoiceno.setText(spanText);
            tv_invoiceno.setTextColor(Res.getColor(null,"jiuyi_link_color"));
        }
        tv_invoiceno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_invoiceno",v);
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



    private TradeinvoiceAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TradeinvoiceAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TradeinvoiceBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TradeinvoiceBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
