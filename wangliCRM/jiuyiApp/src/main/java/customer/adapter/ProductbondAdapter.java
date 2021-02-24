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

import customer.entity.ProductbondBean;



public class ProductbondAdapter extends BaseQuickAdapter<ProductbondBean.ContentBean,BaseViewHolder>  {

    public ProductbondAdapter(int layoutResId, List<ProductbondBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ProductbondBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_bondnum = holder.getView(R.id.tv_bondnum);
        TextView tv_name = holder.getView(R.id.tv_name);
        TextView tv_date = holder.getView(R.id.tv_date);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        tv_name.setText(item.getBondName());
        if(item.getPublishTimeStr()!=null){
            tv_date.setText(item.getPublishTimeStr().replace("-",""));
        }

        if(item.getBondNum()!=null){
            if(!Func.IsStringEmpty(item.getBondNum())){
                String type=item.getBondNum();
                SpannableString spanText=new SpannableString(type);
                spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_bondnum.setText(spanText);
                tv_bondnum.setTextColor(Res.getColor(null,"jiuyi_link_color"));
            }
        }

        tv_bondnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_bondnum",v);
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



    private ProductbondAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ProductbondAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ProductbondBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ProductbondBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
