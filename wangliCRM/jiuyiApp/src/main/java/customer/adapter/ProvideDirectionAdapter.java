package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jiuyi.tools.jiuyiViewUtil;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.tencent.qcloud.sdk.Constant;

import java.util.List;

import customer.entity.ProductDirectoryBean;
import customer.entity.ProvideDirectoryBean;


public class ProvideDirectionAdapter extends BaseQuickAdapter<ProvideDirectoryBean.ContentBean,BaseViewHolder>  {

    public ProvideDirectionAdapter(int layoutResId, List<ProvideDirectoryBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProvideDirectoryBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_orgname = holder.getView(R.id.tv_orgname);
        SimpleDraweeView iv_icon= holder.getView(R.id.iv_icon);
        if(item.getSupplier()!=null &&item.getSupplier().getAvatarUrl()!=null){
            LoaderManager.getLoader().loadNet(iv_icon, Constant.BaseUrl+"file/"+item.getSupplier().getAvatarUrl().toString(), new ILoader.Options(R.drawable.client_directsales, R.drawable.client_directsales));
        }
        if(item.getSupplier()!=null && item.getSupplier().getAbbreviation()!=null){
            tv_orgname.setText(item.getSupplier().getAbbreviation());
        }
        TextView tv_clienttype = holder.getView(R.id.tv_clienttype);
        String clienttype="";
        if(item.getSupplier()!=null && item.getSupplier().getCooperationTypeValue()!=null){
            clienttype+=item.getSupplier().getCooperationTypeValue();
        }
        if(item.getSupplier()!=null && item.getSupplier().getEmployeeSizeValue()!=null){
            clienttype+="|"+item.getSupplier().getEmployeeSizeValue();
        }
        if(item.getSupplier()!=null && item.getSupplier().getProvinceName()!=null){
            clienttype+="|"+item.getSupplier().getProvinceName();
        }
        tv_clienttype.setText(clienttype);
        TextView tv_business = holder.getView(R.id.tv_business);
        String sBusiness="业务范围:";
        if(item.getSupplier().getBusinessScope()!=null ){
            sBusiness+=item.getSupplier().getBusinessScope();
        }else{
            sBusiness+="暂无相关信息";
        }
        tv_business.setText(sBusiness);
        TextView tv_rank = holder.getView(R.id.tv_rank);
        tv_rank.setText("第"+item.getRank()+"名");

        TextView tv_ranklabel = holder.getView(R.id.tv_ranklabel);
        if(!Func.IsStringEmpty(item.getBusinessTypeValue())){
            tv_ranklabel.setText(item.getBusinessTypeValue()+"排名");
        }


        TextView tv_purchasecount = holder.getView(R.id.tv_purchasecount);
        tv_purchasecount.setText(item.getPurchaseCount()+"");
        TextView tv_productcount = holder.getView(R.id.tv_productcount);
        tv_productcount.setText(item.getProductCount()+"");
        TextView tv_salecount = holder.getView(R.id.tv_salecount);
        tv_salecount.setText(item.getSalesCount()+"");
    }



    private ProvideDirectionAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProvideDirectionAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProvideDirectoryBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProvideDirectoryBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
