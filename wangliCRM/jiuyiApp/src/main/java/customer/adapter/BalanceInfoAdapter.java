package customer.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.utils.Res;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CreditBalanceAmountChartBean;
import customer.entity.ProductinfoBean;


public class BalanceInfoAdapter extends BaseQuickAdapter<CreditBalanceAmountChartBean.BeanListBean,BaseViewHolder>  {

    public BalanceInfoAdapter(int layoutResId, List<CreditBalanceAmountChartBean.BeanListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CreditBalanceAmountChartBean.BeanListBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_code = holder.getView(R.id.tv_code);
        TextView tv_name = holder.getView(R.id.tv_name);
        if(!Func.IsStringEmpty(item.getUnit())){
            tv_name.setText(item.getUnit());
        }

        if(item.getField()!=null){
            tv_code.setText(item.getField());
        }
        TextView tv_currentmoney = holder.getView(R.id.tv_currentmoney);
        if(item.getFieldTitle()!=null){
            tv_currentmoney.setText(item.getFieldTitle().toString());
        }
        TextView tv_balance = holder.getView(R.id.tv_balance);
         tv_balance.setText(Func.addComma(item.getFieldValue()/10000+""));
    }



    private BalanceInfoAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(BalanceInfoAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CreditBalanceAmountChartBean.BeanListBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CreditBalanceAmountChartBean.BeanListBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
}
