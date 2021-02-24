package customer.adapter;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CustomerDebtBean;
import customer.view.jiuyiRecycleViewDivider;


public class DebtListAdapter extends BaseQuickAdapter<CustomerDebtBean.ContentBean,BaseViewHolder>  {
    DebtDetailAdapter debtDetailAdapter;
      public DebtListAdapter(int layoutResId, List<CustomerDebtBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CustomerDebtBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        LinearLayout ll_debtdetial = holder.getView(R.id.ll_debtdetial);
        if(getData().size()>1){
            if(holder.getLayoutPosition()==0){
                ll_debtdetial.setBackgroundResource(Res.getDrawabelID(JiuyiMainApplication.getIns(), "jiuyi_debt_whitebg_top"));
            }else if(holder.getLayoutPosition()==getData().size()-1){
                ll_debtdetial.setBackgroundResource(Res.getDrawabelID(JiuyiMainApplication.getIns(), "jiuyi_debt_whitebg_bottom"));
            }else {
                ll_debtdetial.setBackgroundResource(Res.getDrawabelID(JiuyiMainApplication.getIns(), "jiuyi_debt_whitebg_mid"));
            }
        }

        TextView tv_companycode = holder.getView(R.id.tv_companycode);
        if(item.getCompanyCode()!=null){
            tv_companycode.setText("公司代码:"+item.getCompanyCode().getCompanyCode().toString());
        }
        TextView tv_total = holder.getView(R.id.tv_total);
        if(item.getCompanyCode()!=null && item.getCompanyCode()!=null){
            if(item.getCompanyCode().getAmount()!=null){
                tv_total.setText("汇总 | "+item.getCompanyCode().getCompanyCode()+" : "+ Func.addComma(item.getCompanyCode().getAmount().toString()));
            }

        }
        RecyclerView rv_debtdetail = holder.getView(R.id.rv_debtdetail);
        rv_debtdetail.setLayoutManager(new LinearLayoutManager(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), 1, false));
        rv_debtdetail.setHasFixedSize(true);
        rv_debtdetail.setItemAnimator(new DefaultItemAnimator());
        rv_debtdetail.addItemDecoration(new jiuyiRecycleViewDivider(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), LinearLayoutManager.VERTICAL, 0, Rc.getApplication().getResources().getColor(R.color.background)));
        debtDetailAdapter = new DebtDetailAdapter(R.layout.jiuyi_debtdetail_item, item.getCompanyList());
        rv_debtdetail.setAdapter(debtDetailAdapter);
    }

    private DebtListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DebtListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DebtListAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CustomerDebtBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CustomerDebtBean.ContentBean> newList) {
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
