package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.AccessStandardBean;
import customer.entity.ProductDirectoryBean;


public class AcessStandardAdapter extends BaseQuickAdapter<AccessStandardBean.ContentBean,BaseViewHolder>  {

    public AcessStandardAdapter(int layoutResId, List<AccessStandardBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final AccessStandardBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getName()!=null){
            tv_title.setText(item.getName());
        }
        TextView tv_fileType = holder.getView(R.id.tv_fileType);
        if(item.getTypeValue()!=null){
            tv_fileType.setText(item.getTypeValue());
        }
        TextView tv_updatedate = holder.getView(R.id.tv_updatedate);
        if(item.getLastModifiedDate()!=null){
            tv_updatedate.setText(item.getLastModifiedDate().substring(0,10));
        }

        holder.addOnClickListener(R.id.tv_title);
    }



    private AcessStandardAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(AcessStandardAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<AccessStandardBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<AccessStandardBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
