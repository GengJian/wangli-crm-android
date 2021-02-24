package orders.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jiuyi.tools.jiuyiViewUtil;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.wanglicrm.android.R;

import java.util.List;

import orders.entity.BusinessTripBean;
import orders.entity.OrderBean;


public class BusinessTripListAdapter extends BaseQuickAdapter<BusinessTripBean.ContentBean,BaseViewHolder>  {

      public BusinessTripListAdapter(int layoutResId, List<BusinessTripBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final BusinessTripBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_date = holder.getView(R.id.tv_date);
        String sDate="";
        if(item.getTravelDate()!=null){
            sDate+=item.getTravelDate()+"   ";
        }
        if(item.getPlaceArrival()!=null){
            sDate+=item.getPlaceArrival();
//            tv_placeArrival.setText(item.getPlaceArrival());
        }
        tv_date.setText(sDate);
        TextView tv_type = holder.getView(R.id.tv_type);
        if(item.getTravelStatusDesp()!=null){
            tv_type.setText(item.getTravelStatusDesp());
        }
        TextView tv_placeArrival = holder.getView(R.id.tv_placeArrival);
        if(item.getTitle()!=null){
            tv_placeArrival.setText(item.getTitle());
        }
        TextView tv_info = holder.getView(R.id.tv_info);
        String sInfo="";
        if(item.getPlaceDeparture()!=null){
            sInfo+="出发地:"+item.getPlaceDeparture()+"|";
        }
        if(item.getPlaceArrival()!=null){
            sInfo+="到达地:"+item.getPlaceArrival();
        }
        tv_info.setText(sInfo);

    }

    private BusinessTripListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(BusinessTripListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, BusinessTripListAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<BusinessTripBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<BusinessTripBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
    public void clear(){
        getData().clear();
        notifyDataSetChanged();
    }

}
