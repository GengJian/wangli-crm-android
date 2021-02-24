package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerTradeDetailBean;


public class TradeDetailInfoAdapter extends BaseQuickAdapter<CustomerTradeDetailBean.ContentBean.DataBean,BaseViewHolder>  {

    public TradeDetailInfoAdapter(int layoutResId, List<CustomerTradeDetailBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerTradeDetailBean.ContentBean.DataBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView leftContent = holder.getView(R.id.leftContent);
        TextView rightContent = holder.getView(R.id.rightContent);
        if(!Func.IsStringEmpty(item.getLeftContent())){
            leftContent.setText(item.getLeftContent());
        }
        if(!Func.IsStringEmpty(item.getRightContent())){
            rightContent.setText(item.getRightContent());
        }

        rightContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"rightContent",v);
                }
            }
        });
    }



    private TradeDetailInfoAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TradeDetailInfoAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

}
