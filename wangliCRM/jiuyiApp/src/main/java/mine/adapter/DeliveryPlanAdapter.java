package mine.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.RoundlProgresWithNum;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.NeedPlanBean;


public class DeliveryPlanAdapter extends BaseQuickAdapter<NeedPlanBean,BaseViewHolder>  {

    public DeliveryPlanAdapter(int layoutResId, List<NeedPlanBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final NeedPlanBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_year = holder.getView(R.id.tv_year);
        TextView tv_planno = holder.getView(R.id.tv_planno);
        TextView tv_plansend = holder.getView(R.id.tv_plansend);
        TextView tv_realsend=holder.getView(R.id.tv_realsend);
        RoundlProgresWithNum Progress_1 =holder.getView(R.id.Progress_1);


        tv_month.setText("11");
        tv_year.setText(item.getDate());
        tv_planno.setText(item.getPlanno());

        tv_plansend.setText(item.getPlansend());
        tv_realsend.setText(item.getRealsend());
        Progress_1.setProgress(Integer.parseInt(item.getProgress()));

    }

    private DeliveryPlanAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DeliveryPlanAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DeliveryPlanAdapter receiveAddress, View view);
    }

}
