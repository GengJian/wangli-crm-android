package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.MonthlyCreditBean;


public class ArrivemoneyAdapter extends BaseQuickAdapter<MonthlyCreditBean.ContentBean.ForthItemBean.ListBeanX,BaseViewHolder>  {

    public ArrivemoneyAdapter(int layoutResId, List<MonthlyCreditBean.ContentBean.ForthItemBean.ListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final MonthlyCreditBean.ContentBean.ForthItemBean.ListBeanX item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_dyname = holder.getView(R.id.tv_dyname);
        TextView tv_dyvalue = holder.getView(R.id.tv_dyvalue);
        if(!Func.IsStringEmpty(item.getField())){
            tv_dyname.setText(item.getField());
        }
        tv_dyvalue.setText(item.getFieldValue()+"");
    }



    private ArrivemoneyAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ArrivemoneyAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

}
