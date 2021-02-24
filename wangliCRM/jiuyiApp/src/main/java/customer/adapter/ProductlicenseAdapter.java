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

import customer.entity.ProductlicenseBean;


public class ProductlicenseAdapter extends BaseQuickAdapter<ProductlicenseBean.ContentBean,BaseViewHolder>  {

    public ProductlicenseAdapter(int layoutResId, List<ProductlicenseBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProductlicenseBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_licensetype = holder.getView(R.id.tv_licensetype);
        TextView tv_licensetitle = holder.getView(R.id.tv_licensetitle);
        TextView tv_licensedate = holder.getView(R.id.tv_licensedate);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        if(item.getTitle()!=null){
            tv_licensetitle.setText(item.getTitle());
        }
        if(item.getInfoDate()!=null){
            tv_licensedate.setText(item.getInfoDate().toString().replace("-",""));
        }
        if(item.getType()!=null){
            if(!Func.IsStringEmpty(item.getType().getValue())){
                String type=item.getType().getValue();
                SpannableString spanText=new SpannableString(type);
                spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_licensetype.setText(spanText);
                tv_licensetype.setTextColor(Res.getColor(null,"jiuyi_link_color"));
            }
        }


        tv_licensetype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_licensetype",v);
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



    private ProductlicenseAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProductlicenseAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProductlicenseBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProductlicenseBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
