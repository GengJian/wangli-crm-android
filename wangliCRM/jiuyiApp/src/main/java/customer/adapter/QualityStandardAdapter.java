package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.JiuyiDateUtil;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CapacityInfoBean;
import customer.entity.QualityStandardBean;


public class QualityStandardAdapter extends BaseQuickAdapter<QualityStandardBean.ContentBean,BaseViewHolder>  {

    public QualityStandardAdapter(int layoutResId, List<QualityStandardBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final QualityStandardBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getContent()!=null){
            tv_title.setText(item.getContent());
        }
        TextView tv_type = holder.getView(R.id.tv_type);
        if(item.getBusinessTypeValue()!=null){
            tv_type.setText(item.getBusinessTypeValue());
        }
        TextView tv_createdate = holder.getView(R.id.tv_createdate);
        if(item.getCreatedDate()!=null){
            tv_createdate.setText(item.getCreatedDate().substring(0,10));
        }




    }



    private QualityStandardAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(QualityStandardAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<QualityStandardBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<QualityStandardBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
