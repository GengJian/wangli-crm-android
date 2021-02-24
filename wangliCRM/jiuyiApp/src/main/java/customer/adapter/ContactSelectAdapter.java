package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import commonlyused.bean.ContactBean;


public class ContactSelectAdapter extends BaseQuickAdapter<ContactBean.ContentBean,BaseViewHolder>  {

    public ContactSelectAdapter(int layoutResId, List<ContactBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final ContactBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
        String name="";
        if(item.getMember()!=null){
            if(item.getMember().getAbbreviation()!=null){
                name= item.getMember().getAbbreviation()+"-";
            }
        }
        if(item.getName()!=null){
            name+=item.getName();
        }
        listview_popwind_tv.setText(name);
        imageViewSelect.setImageResource(R.drawable.client_selected);
    }



    private ContactSelectAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(ContactSelectAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<ContactBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<ContactBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
