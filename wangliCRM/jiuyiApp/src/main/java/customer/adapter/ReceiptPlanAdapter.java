package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.wanglicrm.android.R;
import com.jiuyi.tools.BGAProgressBar;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.GatheringPlanBean;


public class ReceiptPlanAdapter extends BaseQuickAdapter<GatheringPlanBean.ContentBean,BaseViewHolder>  {

    public ReceiptPlanAdapter(int layoutResId, List<GatheringPlanBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final GatheringPlanBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_year = holder.getView(R.id.tv_year);
        TextView tv_planno = holder.getView(R.id.tv_planno);
        TextView tv_place=holder.getView(R.id.tv_place);
        TextView tv_plansend = holder.getView(R.id.tv_plansend);
        TextView tv_realsend=holder.getView(R.id.tv_realsend);
        TextView tv_chk=holder.getView(R.id.tv_chk);
        TextView tv_client=holder.getView(R.id.tv_client);
        BGAProgressBar Progress_1 =holder.getView(R.id.Progress_1);

        tv_month.setText(item.getMonth()+"");
        tv_year.setText(item.getYear()+"");
        if(item.getQuantity()>=0){
            tv_plansend.setText(Func.addComma(item.getQuantity()+"")+"元");
        }
/*        if(item.getAdjustedQuantity()>=0){
            tv_place.setText(item.getAdjustedQuantity()+"");
        }
        if(item.getAdjustedQuantity()>=0){
            tv_plansend.setText(item.getAdjustedQuantity()+"KG");
        }*/
        tv_realsend.setText("实收:"+Func.addComma(item.getIssuedQuantity()+"")+"元");
        if(item.getActualShip()!=null){
            String actualship=item.getActualShip();
            if(actualship.indexOf('.')>0){
                actualship=actualship.substring(0,actualship.indexOf('.'));

            }else if(actualship.indexOf('%')>0){
                actualship=actualship.substring(0,actualship.indexOf('%'));
            }
            Progress_1.setProgress(Integer.parseInt(actualship));
//            Progress_1.setProgress(Integer.parseInt(actualship)*36/10);
        }
        if(!Func.IsStringEmpty(item.getStatus())){
            if(item.getStatus().equals("APPROVALED")){
                tv_chk.setText("已审批");
            }else if(item.getStatus().equals("ONAPPLICATION")){
                tv_chk.setText("上报中");
            }else if(item.getStatus().equals("RETURNED")){
                tv_chk.setText("已退回");
            }
        }
//        if(item.getMember()!=null){
//            if(item.getMember().getOrgName()!=null){
//                tv_client.setText(item.getMember().getOrgName());
//            }
//        }
    }

    private ReceiptPlanAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ReceiptPlanAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, ReceiptPlanAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<GatheringPlanBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<GatheringPlanBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
    public void clear(){
        getData().clear();
        notifyDataSetChanged();
    }

}
