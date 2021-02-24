package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.DepartmentBean;
import customer.entity.OfficeBean;


public class CustomerDepartmentSelectAdapter extends BaseQuickAdapter<OfficeBean,BaseViewHolder>  {

    public CustomerDepartmentSelectAdapter(int layoutResId, List<OfficeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final OfficeBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
        String name="";
        if(item.getName()!=null){
            listview_popwind_tv.setText(item.getName());
        }

        imageViewSelect.setImageResource(R.drawable.client_selected);
    }



    private CustomerDepartmentSelectAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CustomerDepartmentSelectAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<OfficeBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<OfficeBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
