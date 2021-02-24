package customer.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.Res;
import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.ProductlandBean;


public class ProductlandAdapter extends BaseQuickAdapter<ProductlandBean.ContentBean,BaseViewHolder>  {

    public ProductlandAdapter(int layoutResId, List<ProductlandBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProductlandBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_crmnum = holder.getView(R.id.tv_crmnum);
        TextView tv_region = holder.getView(R.id.tv_region);
        TextView tv_date = holder.getView(R.id.tv_date);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        tv_region.setText(item.getAdminRegion());
        if(item.getSignedDateStr()!=null){
            tv_date.setText(item.getSignedDateStr().replace("-",""));
        }

        if(item.getPurchaseLandNumber()!=null){
            if(!Func.IsStringEmpty(item.getPurchaseLandNumber())){
                String type=item.getPurchaseLandNumber();
                SpannableString spanText=new SpannableString(type);
                spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_crmnum.setText(spanText);
                tv_crmnum.setTextColor(Res.getColor(null,"jiuyi_link_color"));
            }
        }

        tv_crmnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_crmnum",v);
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



    private ProductlandAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProductlandAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProductlandBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProductlandBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
