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

import customer.entity.TradeorderBean;


public class TradeorderAdapter extends BaseQuickAdapter<TradeorderBean.ContentBean,BaseViewHolder>  {

    public TradeorderAdapter(int layoutResId, List<TradeorderBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TradeorderBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_orderno = holder.getView(R.id.tv_orderno);
        TextView tv_orderdate = holder.getView(R.id.tv_orderdate);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        if(!Func.IsStringEmpty(item.getCreatedDate())){
            tv_orderdate.setText(item.getCreatedDate().replace("-",""));
        }
        if(!Func.IsStringEmpty(item.getStatus())){
            String status="";
            if(item.getStatus().equals("SAVED")){
                status="待确认";
            }else if(item.getStatus().equals("CANCEL")){
                status="已撤销";
            }else if(item.getStatus().equals("CRM_COMMIT")){
                status="已提交";
            }else if(item.getStatus().equals("OA_REVIEW")){
                status="价格审核中";
            }else if(item.getStatus().equals("OA_REJECT")){
                status="价格审核未通过";
            }else if(item.getStatus().equals("OA_APPROVE")){
                status="价格审核通过";
            }else if(item.getStatus().equals("SAP_COMMIT")){
                status="SAP提交";
            }else if(item.getStatus().equals("DELIVERYED")){
                status="已发货";
            }else if(item.getStatus().equals("RECEIVED")){
                status="已收货";
            }else if(item.getStatus().equals("CREDIT_REJECT")){
                status="信用审核未通过";
            }else if(item.getStatus().equals("SAP_DELETE")){
                status="已取消";
            }else if(item.getStatus().equals("OFFICE_REVIEW")){
                status="办事处确认中";
            }
            tv_status.setText(status);
        }


        if(!Func.IsStringEmpty(item.getCrmNumber())){
            String type=item.getCrmNumber();
            SpannableString spanText=new SpannableString(type);
            spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_orderno.setText(spanText);
            tv_orderno.setTextColor(Res.getColor(null,"jiuyi_link_color"));
        }
        tv_orderno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_orderno",v);
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



    private TradeorderAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TradeorderAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TradeorderBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TradeorderBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
}
