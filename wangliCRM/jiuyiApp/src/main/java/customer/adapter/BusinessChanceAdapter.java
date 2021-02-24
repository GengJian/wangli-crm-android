package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.BusinessChanceBean;
import customer.entity.PainPointBean;


public class BusinessChanceAdapter extends BaseQuickAdapter<BusinessChanceBean.ContentBean,BaseViewHolder>  {

    public BusinessChanceAdapter(int layoutResId, List<BusinessChanceBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final BusinessChanceBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_put = holder.getView(R.id.tv_put);
        TextView tv_creator = holder.getView(R.id.tv_creator);
        TextView tv_createdate = holder.getView(R.id.tv_createdate);
        if(item.getContent()!=null){
            tv_title.setText(item.getContent());
        }

        if(item.getCustomerContact()!=null){
            tv_put.setText(item.getCustomerContact());
        }
        if(item.getOperatorName()!=null){
            tv_creator.setText(item.getOperatorName());
        }
        if(item.getSubmitDate()!=null){
            tv_createdate.setText(item.getSubmitDate().substring(0,10));
        }

//        TextView tv_reply = holder.getView(R.id.tv_reply);


    }



    private BusinessChanceAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(BusinessChanceAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<BusinessChanceBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<BusinessChanceBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
