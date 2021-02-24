package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.PatentCertificationBean;
import customer.entity.SystemStandardBean;


public class PatentCertiicaitonAdapter extends BaseQuickAdapter<PatentCertificationBean.ContentBean,BaseViewHolder>  {

    public PatentCertiicaitonAdapter(int layoutResId, List<PatentCertificationBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final PatentCertificationBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getContent()!=null){
            tv_title.setText(item.getContent());
        }
        TextView tv_type = holder.getView(R.id.tv_type);
        if(item.getBusinessTypeValue()!=null){
            tv_type.setText(item.getBusinessTypeValue()+"");
        }
        TextView tv_status = holder.getView(R.id.tv_status);
        if(item.getStatusValue()!=null){
            tv_type.setText(item.getStatusValue()+"");
        }

        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getApplyDate()!=null){
            tv_date.setText(item.getApplyDate());
        }
        holder.addOnClickListener(R.id.tv_title);
    }



    private PatentCertiicaitonAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(PatentCertiicaitonAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<PatentCertificationBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<PatentCertificationBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
