package customer.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.ReceiveAddressBean;


public class ReceiveAddressAdapter extends BaseQuickAdapter<ReceiveAddressBean.ContentBean,BaseViewHolder>  {

    public ReceiveAddressAdapter(int layoutResId, List<ReceiveAddressBean.ContentBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(final BaseViewHolder holder,final ReceiveAddressBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_receiveaddress_name = holder.getView(R.id.tv_addressname);
        TextView tv_receiveaddress_telephone = holder.getView(R.id.tv_receiveaddress_tel);
        TextView tv_receiveaddress_address = holder.getView(R.id.tv_receiveaddress);
        TextView tv_defaultaddress=holder.getView(R.id.tv_defaultaddress);
        TextView tv_receiveaddress_personname=holder.getView(R.id.tv_receiveaddress_personname);
        TextView tv_edit=holder.getView(R.id.tv_edit);
        TextView tv_del=holder.getView(R.id.tv_del);
        LinearLayout ll_address=holder.getView(R.id.ll_address);
        tv_receiveaddress_name.setText(item.getAddress());
        tv_receiveaddress_telephone.setText(item.getPhoneOne()+"");
        String address="";
        if(item.getProvinceName()!=null){
            address+=item.getProvinceName();
        }
        if(item.getCityName()!=null){
            address+=item.getCityName();
        }
        if(item.getAreaName()!=null){
            address+=item.getAreaName();
        }
        tv_receiveaddress_address.setText(address);
        tv_receiveaddress_personname.setText(item.getReceiver());

        if(mData.get(holder.getLayoutPosition()).isSelected()){
            ll_address.setSelected(true);
        }else {
            ll_address.setSelected(false);
        }
        if(item.isDefaults()){
            tv_defaultaddress.setText("默认");
            tv_defaultaddress.setVisibility(View.VISIBLE);
        }else{
            tv_defaultaddress.setVisibility(View.INVISIBLE);
        }
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_edit",v);
                }
            }
        });
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_del",v);
                }
            }
        });
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout ll_address=holder.getView(R.id.ll_address);
                ll_address.setSelected(true);
                *//*TextView tv_defaultaddress=holder.getView(R.id.tv_defaultaddress);
                if(tv_defaultaddress.getText().equals("默认")){
                    tv_defaultaddress.setVisibility(View.VISIBLE);
                }else{
                    tv_defaultaddress.setVisibility(View.INVISIBLE);
                }*//*

            }
        });*/
    }



    private ReceiveAddressAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ReceiveAddressAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

}
