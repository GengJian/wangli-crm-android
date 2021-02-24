package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CtmReportBean;
import customer.entity.PutIntoProductBean;


public class CtmReportAdapter extends BaseQuickAdapter<CtmReportBean.ContentBean,BaseViewHolder>  {

    public CtmReportAdapter(int layoutResId, List<CtmReportBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CtmReportBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_type = holder.getView(R.id.tv_type);
        if(item.getProductType()!=null){
            tv_type.setText(item.getProductType());
        }
        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getPutDate()!=null){
            tv_date.setText(item.getPutDate());
        }
    }



    private CtmReportAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CtmReportAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CtmReportBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CtmReportBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
