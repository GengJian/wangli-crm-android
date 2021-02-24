package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.jiuyi.tools.BGAProgressBar;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.DemandPlanBean;


public class NeedPlanAdapter extends BaseQuickAdapter<DemandPlanBean.ContentBean,BaseViewHolder>  {

    public NeedPlanAdapter(int layoutResId,  List<DemandPlanBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DemandPlanBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_year = holder.getView(R.id.tv_year);
        TextView tv_planno = holder.getView(R.id.tv_planno);
        TextView tv_place=holder.getView(R.id.tv_place);
        TextView tv_level=holder.getView(R.id.tv_level);
        TextView tv_weight=holder.getView(R.id.tv_weight);
        TextView tv_plansend = holder.getView(R.id.tv_plansend);
        TextView tv_realsend=holder.getView(R.id.tv_realsend);
        TextView tv_chk=holder.getView(R.id.tv_chk);
        BGAProgressBar Progress_1 =holder.getView(R.id.Progress_1);


        tv_month.setText(item.getMonth()+"");
        tv_year.setText(item.getYear()+"");
        if(item.getBatchNumber()!=null){
            tv_planno.setText(item.getBatchNumber()+"");
        }
        if(item.getMember()!=null){
            if(item.getMember().getAbbreviation()!=null){
                tv_place.setText(item.getMember().getAbbreviation());
            }

        }
        if(item.getProductLevelName()!=null){
            tv_level.setText(item.getProductLevelName());
        }
        if(item.getWeight()>0){
            tv_weight.setText(item.getWeight()+"KG");
        }
      /*  String sWeight="";
        if(item.getWeight()>0){
            sWeight=item.getWeight()+"";
        }
        if(item.getProductLevelName()!=null){
            if(item.getWeight()>0){
                sWeight+="/";
            }
            sWeight+=item.getProductLevelName();
        }*/
/*        if(item.getProductPlace()!=null){
            tv_place.setText(item.getProductPlace());
        }*/
        /*if(item.getMaterial()!=null){
            if(item.getMaterial().getBatchNumber()!=null){
                tv_planno.setText(item.getMaterial().getBatchNumber()+"");
            }
            String sPlace="";
            if(item.getMaterial().getProductPlace()!=null){
                sPlace+=item.getMaterial().getProductPlace();
            }
            if(item.getMaterial().getSpec()!=null){
                sPlace+="/"+item.getMaterial().getSpec();
            }
            tv_place.setText(sPlace);
        }*/
        if(item.getShowQuantity()!=null){
            tv_plansend.setText(item.getShowQuantity()+"KG");
        }
        tv_realsend.setText(item.getIssuedQuantity()+"KG");

        if(item.getActualShip()!=null){
            String actualship=item.getActualShip();
            if(actualship.indexOf('.')>0){
                actualship=actualship.substring(0,actualship.indexOf('.'));

            }else if(actualship.indexOf('%')>0){
                actualship=actualship.substring(0,actualship.indexOf('%'));
            }
            Progress_1.setProgress(Integer.parseInt(actualship));

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
    }

    private NeedPlanAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(NeedPlanAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, NeedPlanAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DemandPlanBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<DemandPlanBean.ContentBean> newList) {
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
