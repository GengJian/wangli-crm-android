package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Res;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerBusinessReceiptBean;
import customer.entity.VisitActivityListBean;


public class CustomerBusinessReceiptAdapter extends BaseQuickAdapter<CustomerBusinessReceiptBean.ContentBean,BaseViewHolder>  {

      public CustomerBusinessReceiptAdapter(int layoutResId, List<CustomerBusinessReceiptBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerBusinessReceiptBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_dot = holder.getView(R.id.tv_dot);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_creator = holder.getView(R.id.tv_creator);
        if(item.getTitle()!=null){
            tv_title.setText(item.getTitle());
        }
        if(item.getBeginDate()!=null){
            tv_time.setText(item.getBeginDate());
        }
        if(item.getCreatedBy()!=null){
            tv_creator.setText(item.getCreatedBy());
        }

        if(item.getReceptionStatusKey()!=null){
            if(item.getReceptionStatusKey().equals("have_completed")||item.getReceptionStatusKey().equals("have_canceled")){
                tv_status.setTextColor(Res.getColor(null,"jiuyi_info2_color"));
                tv_title.setTextColor(Res.getColor(null,"jiuyi_info2_color"));
                tv_dot.setBackgroundResource(R.drawable.customer_undodot);
                if(item.getReceptionStatusValue()!=null){
                    tv_status.setText(item.getReceptionStatusValue());
                }
            }else{
                tv_dot.setBackgroundResource(R.drawable.customer_dodot);
                tv_status.setTextColor(Res.getColor(null,"jiuyi_red_color"));
                tv_title.setTextColor(Res.getColor(null,"jiuyi_title_color"));
                tv_status.setText("未完成");
            }
        }
    }

    private CustomerBusinessReceiptAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CustomerBusinessReceiptAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, CustomerBusinessReceiptAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CustomerBusinessReceiptBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CustomerBusinessReceiptBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
    public void clear(){
        getData().clear();
        notifyDataSetChanged();
    }

}
