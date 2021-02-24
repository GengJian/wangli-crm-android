package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.ClientListBean;
import customer.entity.SaleSystemBean;


public class ClientListAdapter extends BaseQuickAdapter<ClientListBean.ContentBean,BaseViewHolder>  {

    public ClientListAdapter(int layoutResId, List<ClientListBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ClientListBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_orgname = holder.getView(R.id.tv_orgname);
        TextView tv_clienttype = holder.getView(R.id.tv_clienttype);
        TextView tv_purchasecount = holder.getView(R.id.tv_purchasecount);
        TextView tv_productcount = holder.getView(R.id.tv_productcount);
        TextView tv_business = holder.getView(R.id.tv_business);
        if(item.getMember()!=null && item.getMember().getAbbreviation()!=null){
            tv_orgname.setText(item.getMember().getAbbreviation());
        }
        if(item.getMemberOfMember()!=null){
            if(item.getMemberOfMember().getAbbreviation()!=null){
                tv_orgname.setText(item.getMemberOfMember().getAbbreviation());
            }

            String clienttype="";
            if(item.getMemberOfMember().getCooperationTypeValue()!=null){
                clienttype+=item.getMemberOfMember().getCooperationTypeValue();
            }
            if( item.getMemberOfMember().getEmployeeSizeValue()!=null){
                clienttype+="|"+item.getMemberOfMember().getEmployeeSizeValue();
            }
            if( item.getMemberOfMember().getProvinceName()!=null){
                clienttype+="|"+item.getMemberOfMember().getProvinceName();
            }
            tv_clienttype.setText(clienttype);

            String sBusiness="业务范围:";
            if(item.getMemberOfMember().getBusinessScope()!=null ){
                sBusiness+=item.getMemberOfMember().getBusinessScope();
            }else{
                sBusiness+="暂无相关信息";
            }
            tv_business.setText(sBusiness);
//            TextView tv_rank = holder.getView(R.id.tv_rank);
//            tv_rank.setText("第"+item.getRank()+"名");
//

            tv_purchasecount.setText(item.getPurchaseCount()+"");
            tv_productcount.setText(item.getProductCount()+"");
            TextView tv_salecount = holder.getView(R.id.tv_salecount);
            tv_salecount.setText(item.getSalesCount()+"");
        }

        holder.addOnClickListener(R.id.tv_title);
    }



    private ClientListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ClientListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ClientListBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ClientListBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
