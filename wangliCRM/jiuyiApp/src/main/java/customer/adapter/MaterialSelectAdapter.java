package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.MaterialBean;


public class MaterialSelectAdapter extends BaseQuickAdapter<MaterialBean.ContentBean,BaseViewHolder>  {

    public MaterialSelectAdapter(int layoutResId, List<MaterialBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final MaterialBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
        String showInfo="";
        if(item.getBatchNumber()!=null){
            showInfo+=item.getBatchNumber();
//            listview_popwind_tv.setText(item.getBatchNumber());
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



    private MaterialSelectAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(MaterialSelectAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<MaterialBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<MaterialBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
