package customer.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.control.utils.Func;
import com.control.utils.Res;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.ProductdynamicBean;


public class ProductdynamicAdapter extends BaseQuickAdapter<ProductdynamicBean.ContentBean,BaseViewHolder>  {

    public ProductdynamicAdapter(int layoutResId, List<ProductdynamicBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProductdynamicBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_equipmenttype = holder.getView(R.id.tv_equipmenttype);
        TextView tv_equipmentname = holder.getView(R.id.tv_equipmentname);
        TextView tv_machineopenrate = holder.getView(R.id.tv_machineopenrate);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        if(item.getEquipment().getName()!=null){
            tv_equipmentname.setText(item.getEquipment().getName());
        }
        if(item.getBootedRatio()>=0){
            tv_machineopenrate.setText(item.getBootedRatio()+"%");
        }
        if(!Func.IsStringEmpty(item.getEquipment().getType().getName())){
            String type=item.getEquipment().getType().getValue();
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



    private ProductdynamicAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProductdynamicAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProductdynamicBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProductdynamicBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
}
