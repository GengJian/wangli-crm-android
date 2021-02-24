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
import customer.entity.TechnicalRoadBean;


public class TechnicalRoadAdapter extends BaseQuickAdapter<TechnicalRoadBean.ContentBean,BaseViewHolder>  {

    public TechnicalRoadAdapter(int layoutResId, List<TechnicalRoadBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TechnicalRoadBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getTypeValue()!=null){
            tv_title.setText(item.getTypeValue());
        }
        TextView tv_content = holder.getView(R.id.tv_content);
        if(item.getContent()!=null){
            tv_content.setText(item.getContent());
        }
        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getCreatedDate()!=null){
            tv_date.setText(item.getCreatedDate());
        }
    }



    private TechnicalRoadAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TechnicalRoadAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TechnicalRoadBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TechnicalRoadBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
