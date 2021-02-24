package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.ImportExportProductBean;
import customer.entity.StandardPriceBean;


public class ImportExportProductAdapter extends BaseQuickAdapter<ImportExportProductBean.ContentBean,BaseViewHolder>  {

    public ImportExportProductAdapter(int layoutResId, List<ImportExportProductBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ImportExportProductBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getProductName()!=null){
            tv_title.setText(item.getProductName());
        }
        TextView tv_filetype = holder.getView(R.id.tv_filetype);
        if(item.getBusinessTypeValue()!=null){
            tv_filetype.setText(item.getBusinessTypeValue()+"");
        }

        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getCreatedDate()!=null){
            tv_date.setText(item.getCreatedDate().substring(0,10));
        }

        holder.addOnClickListener(R.id.tv_title);
    }



    private ImportExportProductAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ImportExportProductAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ImportExportProductBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ImportExportProductBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
