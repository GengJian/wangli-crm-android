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

import customer.entity.ProductmaterialBean;


public class ProductmaterialAdapter extends BaseQuickAdapter<ProductmaterialBean.ContentBean,BaseViewHolder>  {

    public ProductmaterialAdapter(int layoutResId, List<ProductmaterialBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProductmaterialBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_materialtype = holder.getView(R.id.tv_materialtype);
        TextView tv_materialname = holder.getView(R.id.tv_materialname);
        TextView tv_yearbuyamount = holder.getView(R.id.tv_yearbuyamount);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        tv_materialname.setText(item.getName());
        tv_yearbuyamount.setText(item.getAnnualPurchaseVolume()+"");
        if(item.getTypeValue()!=null){
            if(!Func.IsStringEmpty(item.getTypeValue())){
                String type=item.getTypeValue();
                SpannableString spanText=new SpannableString(type);
                spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_materialtype.setText(spanText);
                tv_materialtype.setTextColor(Res.getColor(null,"jiuyi_link_color"));
            }
        }

        tv_materialtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_materialtype",v);
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



    private ProductmaterialAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProductmaterialAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProductmaterialBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProductmaterialBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
