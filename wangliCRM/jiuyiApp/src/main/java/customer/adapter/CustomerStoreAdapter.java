package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.BGAProgressBar;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerStoreInfoBean;
import customer.entity.DemandPlanBean;


public class CustomerStoreAdapter extends BaseQuickAdapter<CustomerStoreInfoBean.ContentBean,BaseViewHolder>  {

    public CustomerStoreAdapter(int layoutResId, List<CustomerStoreInfoBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerStoreInfoBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_storeno = holder.getView(R.id.tv_storeno);
        TextView tv_type = holder.getView(R.id.tv_type);

        TextView tv_customer = holder.getView(R.id.tv_customer);
        TextView tv_date=holder.getView(R.id.tv_date);
        TextView tv_name=holder.getView(R.id.tv_name);
        TextView tv_tel=holder.getView(R.id.tv_tel);
        String title="";
        if(item.getProvince()!=null && item.getProvince().getProvinceName()!=null){
            title+=item.getProvince().getProvinceName()+"/";
        }
        if(!Func.IsStringEmpty(item.getStoreName())){
            title+=item.getStoreName();
        }
        tv_title.setText(title);
        String status="";
        if(item.getDeleted()!=null && item.getDeleted()){
            status="已删除";
        }else{
            status="正常";
        }
        tv_type.setText( status );
        if(!Func.IsStringEmpty(item.getLegalName())){
            tv_name.setText(item.getLegalName());
        }
        if(item.getCustomer()!=null &&!Func.IsStringEmpty(item.getCustomer().getOrgName())){
            tv_customer.setText(item.getCustomer().getOrgName());
        }
        if(!Func.IsStringEmpty(item.getCreatedDate())){
            tv_date.setText(item.getCreatedDate().substring(0,10));
        }
        if(!Func.IsStringEmpty(item.getContactNumber())){
            tv_tel.setText(item.getContactNumber());
        }
    }

    private CustomerStoreAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CustomerStoreAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, CustomerStoreAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CustomerStoreInfoBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CustomerStoreInfoBean.ContentBean> newList) {
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
