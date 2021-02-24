package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.BGAProgressBar;
import com.jiuyi.tools.jiuyiViewUtil;
import com.wanglicrm.android.R;

import java.util.List;

import customer.entity.CustomerPlanBean;
import customer.entity.GatheringPlanBean;


public class CustomerPlanAdapter extends BaseQuickAdapter<CustomerPlanBean.ContentBean,BaseViewHolder>  {

    public CustomerPlanAdapter(int layoutResId, List<CustomerPlanBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerPlanBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title=holder.getView(R.id.tv_title);
        if(item.getMember()!=null && item.getMember().getOrgName()!=null){
            tv_title.setText(item.getMember().getOrgName());
        }
        TextView tv_type=holder.getView(R.id.tv_type);
        if(item.getDealerType()==0){
            tv_type.setText("零售");
        }else if(item.getDealerType()==1){
            tv_type.setText("工程");
        }else if(item.getDealerType()==2){
            tv_type.setText("零售/工程");
        }
        TextView tv_totalForTheWholeYear=holder.getView(R.id.tv_totalForTheWholeYear);
        tv_totalForTheWholeYear.setText(Func.addCommaChart(item.getTotalForTheWholeYear()+""));
        TextView tv_content=holder.getView(R.id.tv_content);
        String sContent="";
        if(item.getProvince()!=null && item.getProvince().getProvinceName()!=null){
            sContent+= item.getProvince().getProvinceName()+" | ";
        }
        if(item.getBrand()!=null && item.getBrand().getBrandName()!=null){
            sContent+= item.getBrand().getBrandName()+" | ";
        }
        if(item.getDealerPlanCode()!=null ){
            sContent+= item.getDealerPlanCode();
        }
        tv_content.setText(sContent);



    }

    private CustomerPlanAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CustomerPlanAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, CustomerPlanAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CustomerPlanBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<CustomerPlanBean.ContentBean> newList) {
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
