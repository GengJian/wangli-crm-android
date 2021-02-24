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

import customer.entity.ProductcheckBean;


public class ProductcheckAdapter extends BaseQuickAdapter<ProductcheckBean.ContentBean,BaseViewHolder>  {

    public ProductcheckAdapter(int layoutResId, List<ProductcheckBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProductcheckBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_checktype = holder.getView(R.id.tv_checktype);
        TextView tv_checktitle = holder.getView(R.id.tv_checktitle);
        TextView tv_checkdate = holder.getView(R.id.tv_checkdate);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        tv_checktitle.setText(item.getTitle());
        if(item.getInfoDate()!=null){
            tv_checkdate.setText(item.getInfoDate().replace("-",""));
        }

        if(item.getType()!=null){
            if(!Func.IsStringEmpty(item.getType().getValue())){
                String type=item.getType().getValue();
                SpannableString spanText=new SpannableString(type);
                spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_checktype.setText(spanText);
                tv_checktype.setTextColor(Res.getColor(null,"jiuyi_link_color"));
            }
        }

        tv_checktype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_checktype",v);
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



    private ProductcheckAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProductcheckAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProductcheckBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProductcheckBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
