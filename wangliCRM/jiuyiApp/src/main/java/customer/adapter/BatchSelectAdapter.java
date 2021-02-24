package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.BatchNumberWeightBean;


public class BatchSelectAdapter extends BaseQuickAdapter<BatchNumberWeightBean.ContentBean,BaseViewHolder>  {

    public BatchSelectAdapter(int layoutResId, List<BatchNumberWeightBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final BatchNumberWeightBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
        String showInfo="";
        if(item.getBatchNumber()!=null){
            showInfo+=item.getBatchNumber();
        }
        if(item.getSpec()!=null){
            showInfo+="-"+item.getSpec();
        }
        if(item.getWeight()>=0){
            showInfo+="-"+item.getWeight();
        }else {
            showInfo+="-0.0";
        }
        if(item.getProductLevelName()!=null){
            showInfo+="-"+item.getProductLevelName();
        }
        listview_popwind_tv.setText(showInfo);
        imageViewSelect.setImageResource(R.drawable.client_selected);
    }



    private BatchSelectAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(BatchSelectAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<BatchNumberWeightBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<BatchNumberWeightBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
