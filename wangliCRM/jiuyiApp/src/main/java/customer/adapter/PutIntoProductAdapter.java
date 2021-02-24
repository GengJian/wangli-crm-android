package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.IqcMaterialBean;
import customer.entity.PutIntoProductBean;


public class PutIntoProductAdapter extends BaseQuickAdapter<PutIntoProductBean.ContentBean,BaseViewHolder>  {

    public PutIntoProductAdapter(int layoutResId, List<PutIntoProductBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final PutIntoProductBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_type = holder.getView(R.id.tv_type);
        if(item.getProductType()!=null){
            tv_type.setText(item.getProductType());
        }
        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getPutDate()!=null){
            tv_date.setText(item.getPutDate());
        }
    }



    private PutIntoProductAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(PutIntoProductAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<PutIntoProductBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<PutIntoProductBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
