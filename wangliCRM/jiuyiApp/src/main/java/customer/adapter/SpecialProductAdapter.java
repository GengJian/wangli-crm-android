package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;


public class SpecialProductAdapter extends BaseQuickAdapter<String,BaseViewHolder>  {

    public SpecialProductAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final String item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
        String showInfo="";
        if(item!=null){
//            showInfo+=item.getBatchNumber();
            listview_popwind_tv.setText(item);
        }
//        if(item.getWeight()>=0){
//            showInfo+="-"+item.getWeight();
//        }else {
//            showInfo+="-0.0";
//        }
//        if(item.getProductLevelName()!=null){
//            showInfo+="-"+item.getProductLevelName();
//        }
//        listview_popwind_tv.setText(showInfo);
        imageViewSelect.setImageResource(R.drawable.client_selected);
    }



    private SpecialProductAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(SpecialProductAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<String> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<String> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
