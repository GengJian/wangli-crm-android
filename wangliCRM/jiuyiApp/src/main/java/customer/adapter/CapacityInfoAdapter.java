package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CapacityInfoBean;
import customer.entity.FactoryEquipmentBean;


public class CapacityInfoAdapter extends BaseQuickAdapter<CapacityInfoBean.ContentBean,BaseViewHolder>  {

    public CapacityInfoAdapter(int layoutResId, List<CapacityInfoBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CapacityInfoBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getBusinessTypeValue()!=null){
            tv_title.setText(item.getBusinessTypeValue());
        }
        TextView tv_productType = holder.getView(R.id.tv_productType);
        if(item.getProductType()!=null){
            tv_productType.setText(item.getProductType());
        }
        TextView tv_workshop = holder.getView(R.id.tv_workshop);
        if(item.getWorkshop()!=null){
            tv_workshop.setText(item.getWorkshop());
        }
        TextView tv_theoryYield = holder.getView(R.id.tv_theoryYield);
        tv_theoryYield.setText(item.getTheoryYield()+"");
        TextView tv_actualYield = holder.getView(R.id.tv_actualYield);
        tv_actualYield.setText(item.getActualYield()+"");



    }



    private CapacityInfoAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CapacityInfoAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CapacityInfoBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CapacityInfoBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
