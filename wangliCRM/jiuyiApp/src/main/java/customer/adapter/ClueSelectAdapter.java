package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import dynamic.entity.DyActivityBean;
import dynamic.entity.DyBusinessBean;
import dynamic.entity.DyClueBean;


public class ClueSelectAdapter extends BaseQuickAdapter<DyClueBean.ContentBean,BaseViewHolder>  {

    public ClueSelectAdapter(int layoutResId, List<DyClueBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DyClueBean.ContentBean item) {
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



    private ClueSelectAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ClueSelectAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyClueBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<DyClueBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
