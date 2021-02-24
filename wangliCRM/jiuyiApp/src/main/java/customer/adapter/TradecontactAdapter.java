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

import customer.entity.TradecontactBean;


public class TradecontactAdapter extends BaseQuickAdapter<TradecontactBean.ContentBean,BaseViewHolder>  {

    public TradecontactAdapter(int layoutResId, List<TradecontactBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TradecontactBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_contacttype = holder.getView(R.id.tv_contacttype);
        TextView tv_contactdate = holder.getView(R.id.tv_contactdate);
        TextView tv_contactno = holder.getView(R.id.tv_contactno);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        if(!Func.IsStringEmpty(item.getInfoDate())){
            tv_contactdate.setText(item.getInfoDate().replace("-",""));
        }
        tv_contactno.setText(item.getNumber());
        if(item.getType()!=null){
            String type=item.getType().getValue();
            SpannableString spanText=new SpannableString(type);
            spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_contacttype.setText(spanText);
            tv_contacttype.setTextColor(Res.getColor(null,"jiuyi_link_color"));
        }
        tv_contacttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_contacttype",v);
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



    private TradecontactAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TradecontactAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }


    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TradecontactBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TradecontactBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
