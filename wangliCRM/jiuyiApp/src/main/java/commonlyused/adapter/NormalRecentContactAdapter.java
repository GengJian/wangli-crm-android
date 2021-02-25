package commonlyused.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;
import com.wanglicrm.android.R;

import java.util.List;

import commonlyused.bean.LinkmanBean;


public class NormalRecentContactAdapter extends BaseQuickAdapter<LinkmanBean,BaseViewHolder>  {

    public NormalRecentContactAdapter(int layoutResId, List<LinkmanBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final LinkmanBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_name = holder.getView(R.id.tv_name);
        TextView tv_mobile = holder.getView(R.id.tv_mobile);
        TextView tv_duty = holder.getView(R.id.tv_duty);
        TextView tv_dept = holder.getView(R.id.tv_dept);
        tv_name.setText(item.getName());
        tv_mobile.setText(item.getTelOne());
        tv_duty.setText(item.getDuty());
        tv_dept.setText(item.getDept());
    }



    private NormalRecentContactAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(NormalRecentContactAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

}
