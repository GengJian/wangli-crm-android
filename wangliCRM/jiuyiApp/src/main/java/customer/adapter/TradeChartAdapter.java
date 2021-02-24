package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Rc;
import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;
import com.zqx.chart.anim.Anim;
import com.zqx.chart.data.LineChartData;

import java.util.List;

import customer.entity.TradePandectBean;


public class TradeChartAdapter extends BaseQuickAdapter<TradePandectBean.AverageBean,BaseViewHolder>  {

    public TradeChartAdapter(int layoutResId, List<TradePandectBean.AverageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TradePandectBean.AverageBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView leftContent = holder.getView(R.id.tv_productcode);
        TextView tv_charttitle = holder.getView(R.id.tv_charttitle);
        if(item.getField()!=null){
            leftContent.setText(item.getField());
        }
        if(item.getFieldTitle()!=null){
            tv_charttitle.setText(item.getFieldTitle());
        }
        com.zqx.chart.view.LineChart lineChart = holder.getView((R.id.linechart));
        if(item.getFieldValue()!=null && item.getFieldValue().size()>0){
            final String[] xdata = new String[item.getFieldValue().size()];
            float[] ydata = new float[item.getFieldValue().size()];
            for(int i=0;i<item.getFieldValue().size();i++){
                if(i%2==0){
                    xdata[i]=item.getFieldValue().get(i).getField();
                }else {
                    xdata[i]="";
                }
                ydata[i]=Float.parseFloat(item.getFieldValue().get(i).getFieldValue()+"");
            }
            final LineChartData lineChartData = LineChartData.builder()
                    .setXdata(xdata)
                    .setYdata(ydata)
                    .setPointColor(Rc.getApplication().getResources().getColor(R.color.jiuyi_blue))
                    .setLineColor(Rc.getApplication().getResources().getColor(R.color.jiuyi_blue))
                    .setCoordinatesColor(Rc.getApplication().getResources().getColor(R.color.jiuyi_info_color))
                    .setXpCount(item.getFieldValue().size())
                    .setYpCount(5)
                    .setPointSize(20)
                    .setAnimType(Anim.ANIM_ALPHA)
                    .build();
            lineChart.setChartData(lineChartData);

        }
    }


    private TradeChartAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TradeChartAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

}
