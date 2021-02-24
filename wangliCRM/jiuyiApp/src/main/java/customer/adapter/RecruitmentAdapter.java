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

import customer.entity.RecruitmentBean;


public class RecruitmentAdapter extends BaseQuickAdapter<RecruitmentBean.ContentBean,BaseViewHolder>  {

    public RecruitmentAdapter(int layoutResId, List<RecruitmentBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final RecruitmentBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_recruitmenttype = holder.getView(R.id.tv_recruitmenttype);
        TextView tv_recruitmenttitle = holder.getView(R.id.tv_recruitmenttitle);
        TextView tv_recruitmentdate = holder.getView(R.id.tv_recruitmentdate);
        TextView tv_operation = holder.getView(R.id.tv_operation);
        tv_recruitmenttitle.setText(item.getTitle());
        if(item.getInfoDate()!=null){
            tv_recruitmentdate.setText(item.getInfoDate().replace("-",""));
        }

        if(item.getType()!=null){
            if(!Func.IsStringEmpty(item.getType().getValue())){
                String type=item.getType().getValue();
                SpannableString spanText=new SpannableString(type);
                spanText.setSpan(new UnderlineSpan(), 0, type.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_recruitmenttype.setText(spanText);
                tv_recruitmenttype.setTextColor(Res.getColor(null,"jiuyi_link_color"));
            }
        }

        tv_recruitmenttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_recruitmenttype",v);
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



    private RecruitmentAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(RecruitmentAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<RecruitmentBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<RecruitmentBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
