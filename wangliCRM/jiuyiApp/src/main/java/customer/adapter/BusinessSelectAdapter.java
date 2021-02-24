package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import dynamic.entity.DyBusinessBean;
import dynamic.entity.DyClueBean;


public class BusinessSelectAdapter extends BaseQuickAdapter<DyBusinessBean.ContentBean,BaseViewHolder>  {

    public BusinessSelectAdapter(int layoutResId, List<DyBusinessBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DyBusinessBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
        String name="";
        if(item.getTitle()!=null){
            name= item.getTitle();

        }
        listview_popwind_tv.setText(name);
        imageViewSelect.setImageResource(R.drawable.client_selected);
    }



    private BusinessSelectAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(BusinessSelectAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyBusinessBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<DyBusinessBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
