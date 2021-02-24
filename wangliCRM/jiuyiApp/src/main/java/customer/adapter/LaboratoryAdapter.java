package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.LaboratoryBean;
import customer.entity.PatentCertificationBean;


public class LaboratoryAdapter extends BaseQuickAdapter<LaboratoryBean.ContentBean,BaseViewHolder>  {

    public LaboratoryAdapter(int layoutResId, List<LaboratoryBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final LaboratoryBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getName()!=null){
            tv_title.setText(item.getName());
        }
        TextView tv_type = holder.getView(R.id.tv_type);
        if(item.getTypeValue()!=null){
            tv_type.setText(item.getTypeValue()+"");
        }
        TextView tv_region = holder.getView(R.id.tv_region);
        if(item.getArea()!=null){
            tv_region.setText(item.getArea()+"");
        }
        TextView tv_count = holder.getView(R.id.tv_count);
        if(item.getResearchCount()>=0){
            tv_count.setText(item.getResearchCount()+"");
        }

        TextView tv_cost = holder.getView(R.id.tv_cost);
        if(item.getYearPutCost()>=0){
            tv_cost.setText(item.getYearPutCost()+"");
        }
    }



    private LaboratoryAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(LaboratoryAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<LaboratoryBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<LaboratoryBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
