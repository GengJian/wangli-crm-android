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

import customer.entity.CompeteInfoBean;


public class CompeteInfoAdapter extends BaseQuickAdapter<CompeteInfoBean.ContentBean,BaseViewHolder>  {

    public CompeteInfoAdapter(int layoutResId, List<CompeteInfoBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CompeteInfoBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_competecompany = holder.getView(R.id.tv_competecompany);
        TextView tv_competebrand = holder.getView(R.id.tv_competebrand);
        TextView tv_dunpermonth = holder.getView(R.id.tv_dunpermonth);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        if(item.getBrand()!=null){
            tv_competebrand.setText(item.getBrand().getValue());
        }
        tv_dunpermonth.setText(item.getSalesVolume()+"");
        if(item.getCompanyType()!=null){
            if(!Func.IsStringEmpty(item.getCompanyType().getValue())){
                String type=item.getCompanyType().getValue();
                SpannableString spanText=new SpannableString(type);
                spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_competecompany.setText(spanText);
                tv_competecompany.setTextColor(Res.getColor(null,"jiuyi_link_color"));
            }
        }

        tv_competecompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_competecompany",v);
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



    private CompeteInfoAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CompeteInfoAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CompeteInfoBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CompeteInfoBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
