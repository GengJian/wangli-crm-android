package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerProductInfoBean;
import customer.entity.FactoryEquipmentBean;


public class FactoryEquipmentAdapter extends BaseQuickAdapter<FactoryEquipmentBean.ContentBean,BaseViewHolder>  {

    public FactoryEquipmentAdapter(int layoutResId, List<FactoryEquipmentBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final FactoryEquipmentBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getBusinessTypeValue()!=null){
            tv_title.setText(item.getBusinessTypeValue());
        }
        TextView tv_workshop = holder.getView(R.id.tv_workshop);
        if(item.getWorkshop()!=null){
            tv_workshop.setText(item.getWorkshop());
        }
        TextView tv_type = holder.getView(R.id.tv_type);
        if(item.getEquipmentTypeValue()!=null){
            tv_type.setText(item.getEquipmentTypeValue());
        }
        TextView tv_vendor = holder.getView(R.id.tv_vendor);
        if(item.getVendor()!=null){
            tv_vendor.setText(item.getVendor());
        }
        TextView tv_equipmentModel = holder.getView(R.id.tv_equipmentModel);
        if(item.getEquipmentModel()!=null){
            tv_equipmentModel.setText(item.getEquipmentModel());
        }



    }



    private FactoryEquipmentAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(FactoryEquipmentAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<FactoryEquipmentBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<FactoryEquipmentBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
