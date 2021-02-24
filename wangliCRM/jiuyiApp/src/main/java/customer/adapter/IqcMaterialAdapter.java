package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.IqcMaterialBean;
import customer.entity.QualityStandardBean;


public class IqcMaterialAdapter extends BaseQuickAdapter<IqcMaterialBean.ContentBean,BaseViewHolder>  {

    public IqcMaterialAdapter(int layoutResId, List<IqcMaterialBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final IqcMaterialBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_orderno = holder.getView(R.id.tv_orderno);
        if(item.getIqcMaterialItemSet()!=null && item.getIqcMaterialItemSet().size()>0 ){
            IqcMaterialBean.IqcMaterialItemSetBean bean=item.getIqcMaterialItemSet().get(0);
            if(bean!=null && bean.getIqcMaterialNumber()!=null){
                tv_title.setText(bean.getIqcMaterialNumber());
            }

            if(bean.getIqcMaterialNumber()!=null){
                tv_orderno.setText(bean.getSapinvoiceNumber());
            }
        }
        TextView tv_type = holder.getView(R.id.tv_type);
        if(item.getBusinessTypeValue()!=null){
            tv_type.setText(item.getBusinessTypeValue());
        }
        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getCheckDate()!=null){
            tv_date.setText(item.getCheckDate());
        }
        TextView tv_factory = holder.getView(R.id.tv_factory);
        if(item.getMemberFactory()!=null){
            tv_factory.setText(item.getMemberFactory());
        }



    }



    private IqcMaterialAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(IqcMaterialAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<IqcMaterialBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<IqcMaterialBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
