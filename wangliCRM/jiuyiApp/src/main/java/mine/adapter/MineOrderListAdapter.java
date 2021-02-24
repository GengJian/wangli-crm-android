package mine.adapter;

import android.view.View;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;
import java.util.Timer;

import customer.entity.NeedPlanBean;


public class MineOrderListAdapter extends BaseQuickAdapter<NeedPlanBean,BaseViewHolder>  {

    private Timer timer33;
    public MineOrderListAdapter(int layoutResId, List<NeedPlanBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final NeedPlanBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());

    }

    private MineOrderListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(MineOrderListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, MineOrderListAdapter receiveAddress, View view);
    }

}
