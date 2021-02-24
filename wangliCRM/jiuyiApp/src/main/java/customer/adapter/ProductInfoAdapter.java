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

import customer.entity.ProductinfoBean;


public class ProductInfoAdapter extends BaseQuickAdapter<ProductinfoBean.ContentBean,BaseViewHolder>  {

    public ProductInfoAdapter(int layoutResId, List<ProductinfoBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProductinfoBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_productname = holder.getView(R.id.tv_productname);
        TextView tv_productcomponent = holder.getView(R.id.tv_productcomponent);
        TextView tv_weight = holder.getView(R.id.tv_weight);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        if(item.getName()!=null &&!Func.IsStringEmpty(item.getName())){
            SpannableString spanText=new SpannableString(item.getName());
            spanText.setSpan(new UnderlineSpan(), 0, item.getName().length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_productname.setText(spanText);
            tv_productname.setTextColor(Res.getColor(null,"jiuyi_link_color"));
        }
        if(item.getIngredientValue()!=null){
            tv_productcomponent.setText(item.getIngredientValue());
        }
        if(item.getWeightValue()!=null){
            tv_weight.setText(item.getWeightValue());
//            if(!Func.IsStringEmpty(item.getCategory().getValue())){
//
//                SpannableString spanText=new SpannableString(item.getCategory().getValue());
//                spanText.setSpan(new UnderlineSpan(), 0, item.getCategory().getValue().length(),
//                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                tv_producttype.setText(spanText);
//                tv_producttype.setTextColor(Res.getColor(null,"jiuyi_link_color"));
//            }
        }

        tv_productname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_productname",v);
                }
            }
        });
//        tv_producttype.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null)
//                {
//                    listener.click(holder.getLayoutPosition(),"tv_producttype",v);
//                }
//            }
//        });
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



    private ProductInfoAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProductInfoAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProductinfoBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProductinfoBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
}
