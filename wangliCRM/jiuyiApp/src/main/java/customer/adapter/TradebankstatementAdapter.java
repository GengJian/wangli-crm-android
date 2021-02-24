package customer.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.control.utils.Res;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.TradebankstatementBean;


public class TradebankstatementAdapter extends BaseQuickAdapter<TradebankstatementBean.ContentBean,BaseViewHolder>  {

    public TradebankstatementAdapter(int layoutResId, List<TradebankstatementBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TradebankstatementBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_statementno = holder.getView(R.id.tv_statementno);
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        if(item.getYearMonth()!=null){
            tv_month.setText(item.getYearMonth()+"");
        }

        if(item.getStatus()!=null){
            tv_status.setText(item.getStatus().getValue());
        }
        if(item.getAccountNumber()!=null){
            String type=item.getAccountNumber().toString();
            SpannableString spanText=new SpannableString(type);
            spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_statementno.setText(spanText);
            tv_statementno.setTextColor(Res.getColor(null,"jiuyi_link_color"));
        }
        tv_statementno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_statementno",v);
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



    private TradebankstatementAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TradebankstatementAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TradebankstatementBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TradebankstatementBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
