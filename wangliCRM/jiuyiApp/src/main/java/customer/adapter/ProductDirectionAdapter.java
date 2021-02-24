package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.ProductDirectoryBean;
import customer.entity.ProductStandardBean;


public class ProductDirectionAdapter extends BaseQuickAdapter<ProductDirectoryBean.ContentBean,BaseViewHolder>  {

    public ProductDirectionAdapter(int layoutResId, List<ProductDirectoryBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProductDirectoryBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_productName = holder.getView(R.id.tv_productName);
        if(item.getProductName()!=null){
            tv_productName.setText(item.getProductName());
        }

        TextView tv_productType = holder.getView(R.id.tv_productType);
        if(item.getProductType()!=null){
            tv_productType.setText(item.getProductType());
        }
        TextView tv_productSpec = holder.getView(R.id.tv_productSpec);
        if(item.getProductSpec()!=null){
            tv_productSpec.setText(item.getProductSpec());
        }
        TextView tv_monthQuantity = holder.getView(R.id.tv_monthQuantity);
        tv_monthQuantity.setText(item.getMonthQuantity()+"");
        TextView tv_seasonQuantity = holder.getView(R.id.tv_seasonQuantity);
        tv_seasonQuantity.setText(item.getSeasonQuantity()+"");
        TextView tv_technologyDemand = holder.getView(R.id.tv_technologyDemand);
        if(item.getTechnologyDemand()!=null){
            tv_technologyDemand.setText(item.getTechnologyDemand());
        }
        TextView tv_businessDemand = holder.getView(R.id.tv_businessDemand);
        if(item.getBusinessDemand()!=null){
            tv_businessDemand.setText(item.getBusinessDemand());
        }
        TextView tv_down = holder.getView(R.id.tv_down);
        holder.addOnClickListener(R.id.tv_down);
    }



    private ProductDirectionAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProductDirectionAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProductDirectoryBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProductDirectoryBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
