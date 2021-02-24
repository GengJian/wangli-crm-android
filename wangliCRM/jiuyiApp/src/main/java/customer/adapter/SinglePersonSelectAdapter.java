package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import commonlyused.bean.NormalOperatorBean;
import customer.entity.DepartmentBean;


public class SinglePersonSelectAdapter extends BaseQuickAdapter<NormalOperatorBean.ContentBean,BaseViewHolder>  {

    public SinglePersonSelectAdapter(int layoutResId, List<NormalOperatorBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final NormalOperatorBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
//        String name="";
//        if(item.getName()!=null){
//            listview_popwind_tv.setText(item.getName());
//        }
        String title="";
        if(item.getName()!=null ){
            title+=item.getName();

        }
        if( item.getDepartment()!=null && item.getDepartment().getName()!=null ){
            title+="-"+item.getDepartment().getName();
        }
        if(item.getTitle()!=null){
            title+="-"+item.getTitle();
        }
        listview_popwind_tv.setText(title);

        imageViewSelect.setImageResource(R.drawable.client_selected);
    }



    private SinglePersonSelectAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(SinglePersonSelectAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<NormalOperatorBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<NormalOperatorBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
