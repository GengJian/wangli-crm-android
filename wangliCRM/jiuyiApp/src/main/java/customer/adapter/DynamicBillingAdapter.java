package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import dynamic.entity.DyBillingBean;
import dynamic.entity.DyOrderBean;


public class DynamicBillingAdapter extends BaseQuickAdapter<DyBillingBean.ContentBean,BaseViewHolder>  {

    public DynamicBillingAdapter(int layoutResId, List<DyBillingBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DyBillingBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_client = holder.getView(R.id.tv_client);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_month = holder.getView(R.id.tv_month);
        TextView tv_year = holder.getView(R.id.tv_year);
        TextView tv_desp = holder.getView(R.id.tv_desp);

        TextView tv_no = holder.getView(R.id.tv_no);
        TextView tv_amt=holder.getView(R.id.tv_amt);

        String title="";
        if(item.getMemberNumber()!=null){
            title+=item.getMemberNumber();
        }
        if(item.getAbbreviation()!=null){
            title+="|"+item.getAbbreviation();
        }
        tv_client.setText(title);
        tv_status.setVisibility(View.GONE);
//        if(item.gets()!=null){
//            tv_status.setText(item.getStatus());
//        }

        if(item.getCreatedDate()!=null){
            tv_year.setText(item.getCreatedDate().substring(0,4));
            tv_month.setText(item.getCreatedDate().substring(5,7));
        }
//        if(item.getArktx()!=null){
//            tv_desp.setText(item.getArktx());
//        }
        if(!Func.IsStringEmpty(item.getMaterialDespForMobile())){
            tv_desp.setText(item.getMaterialDespForMobile());
        }else{
            tv_desp.setText("暂无信息");
        }

        String sNum="";
        if(item.getNumber()!=null){
            sNum+="NO."+item.getNumber();
        }
        if(item.getLineNumber()!=null){
            sNum+="/"+item.getLineNumber();
        }
        if(item.getCharg()!=null){
            sNum+="|"+item.getCharg();
        }
        tv_no.setText(sNum);

        tv_amt.setText(item.getNetwr()+"");
        tv_amt.setVisibility(View.INVISIBLE);


    }

    private DynamicBillingAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicBillingAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DynamicBillingAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyBillingBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<DyBillingBean.ContentBean> newList) {
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
