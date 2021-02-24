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

import customer.entity.TradetelemoneyBean;


public class TradetelemoneyAdapter extends BaseQuickAdapter<TradetelemoneyBean.ContentBean,BaseViewHolder>  {

    public TradetelemoneyAdapter(int layoutResId, List<TradetelemoneyBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TradetelemoneyBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_teleno = holder.getView(R.id.tv_teleno);
        TextView tv_type = holder.getView(R.id.tv_type);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_operation = holder.getView(R.id.tv_operation);

        if(item.getZtext()!=null){
            tv_type.setText(item.getZtext());
        }
        if(item.getZtype()!=null){
            tv_status.setText(item.getZtype());
        }
       /* if(item.getReceiptStatus()!=null){
            String status="";
            if(item.getReceiptStatus().equals("ALREADY_PAID")){
                status="已打款";
            }else if(item.getReceiptStatus().equals("TO_BE_CLAIMED")){
                status="待认领";
            }else if(item.getReceiptStatus().equals("CLAIMED")){
                status="已认领";
            }else if(item.getReceiptStatus().equals("ALREADY_ARRIVED")){
                status="已到账";
            }else if(item.getReceiptStatus().equals("BOOKED")){
                status="已入账";
            }
            tv_status.setText(status);
        }
        if(item.getReceiptType()!=null){
            String status="";
            if(item.getReceiptType().equals("ELECTRONIC")){
                status="电子承兑";
            }else if(item.getReceiptType().equals("PAPER")){
                status="纸质承兑";
            }else if(item.getReceiptType().equals("WIRE_TRANSFER")){
                status="电汇";
            }
            tv_type.setText(status);
        }
*/
        if(!Func.IsStringEmpty(item.getNumber())){
            String type=item.getNumber();
            SpannableString spanText=new SpannableString(type);
            spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_teleno.setText(spanText);
            tv_teleno.setTextColor(Res.getColor(null,"jiuyi_link_color"));
        }
        tv_teleno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_teleno",v);
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



    private TradetelemoneyAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TradetelemoneyAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TradetelemoneyBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TradetelemoneyBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
