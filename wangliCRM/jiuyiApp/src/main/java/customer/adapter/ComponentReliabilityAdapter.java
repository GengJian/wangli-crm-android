package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.ComponentReliabilityBean;
import customer.entity.CtmReportBean;


public class ComponentReliabilityAdapter extends BaseQuickAdapter<ComponentReliabilityBean.ContentBean,BaseViewHolder>  {

    public ComponentReliabilityAdapter(int layoutResId, List<ComponentReliabilityBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ComponentReliabilityBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getProjectValue()!=null){
            tv_title.setText(item.getProjectValue());
        }
        TextView tv_condition = holder.getView(R.id.tv_condition);
        if(item.getCondition()!=null){
            tv_condition.setText(item.getCondition());
        }
        TextView tv_standard = holder.getView(R.id.tv_standard);
        if(item.getStandard()!=null){
            tv_standard.setText(item.getStandard());
        }
    }



    private ComponentReliabilityAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ComponentReliabilityAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ComponentReliabilityBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ComponentReliabilityBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
