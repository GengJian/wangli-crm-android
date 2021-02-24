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
import customer.entity.SaleSystemBean;


public class SaleSystemAdapter extends BaseQuickAdapter<SaleSystemBean.ContentBean,BaseViewHolder>  {

    public SaleSystemAdapter(int layoutResId, List<SaleSystemBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final SaleSystemBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getFileName()!=null){
            tv_title.setText(item.getFileName());
        }
        TextView tv_filetype = holder.getView(R.id.tv_filetype);
        if(item.getFileTypeValue()!=null){
            tv_filetype.setText(item.getFileTypeValue());
        }
        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getCreatedDate()!=null){
            tv_date.setText(item.getCreatedDate().substring(0,10));
        }
        holder.addOnClickListener(R.id.tv_title);
    }



    private SaleSystemAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(SaleSystemAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<SaleSystemBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<SaleSystemBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
