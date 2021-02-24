package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.ProductStandardBean;
import customer.entity.TradedeliveryBean;


public class ProductStandardAdapter extends BaseQuickAdapter<ProductStandardBean.ContentBean,BaseViewHolder>  {

    public ProductStandardAdapter(int layoutResId, List<ProductStandardBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProductStandardBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getContent()!=null){
            tv_title.setText(item.getContent());
        }
        TextView tv_creator = holder.getView(R.id.tv_creator);
        TextView tv_createdate = holder.getView(R.id.tv_createdate);
        if(item.getCreatedBy()!=null){
            tv_creator.setText(item.getCreatedBy());
        }
        if(item.getCreatedDate()!=null){
            tv_createdate.setText(item.getCreatedDate().substring(0,10));
        }

    }



    private ProductStandardAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProductStandardAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProductStandardBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProductStandardBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
