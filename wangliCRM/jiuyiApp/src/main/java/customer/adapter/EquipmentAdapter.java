package customer.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.control.utils.Res;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.EquipmentBean;


public class EquipmentAdapter extends BaseQuickAdapter<EquipmentBean.ContentBean,BaseViewHolder>  {

    public EquipmentAdapter(int layoutResId, List<EquipmentBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final EquipmentBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_equipmenttype = holder.getView(R.id.tv_equipmenttype);
        TextView tv_equipmentname = holder.getView(R.id.tv_equipmentname);
        TextView tv_equipmentcount = holder.getView(R.id.tv_equipmentcount);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        tv_equipmentname.setText(item.getName());
        tv_equipmentcount.setText(item.getQuantity()+"");
        if(item.getType()!=null){
            String type=item.getType().getValue();
            SpannableString spanText=new SpannableString(type);
            spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_equipmenttype.setText(spanText);
            tv_equipmenttype.setTextColor(Res.getColor(null,"jiuyi_link_color"));
        }
        tv_equipmenttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_equipmenttype",v);
                }
            }
        });
        tv_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_operation",v);
                }
            }
        });
    }



    private EquipmentAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(EquipmentAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<EquipmentBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<EquipmentBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
