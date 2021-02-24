package customer.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.PainPointBean;
import customer.entity.TradedeliveryBean;


public class PainPointAdapter extends BaseQuickAdapter<PainPointBean.ContentBean,BaseViewHolder>  {

    public PainPointAdapter(int layoutResId, List<PainPointBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final PainPointBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_status = holder.getView(R.id.tv_status);
        TextView tv_put = holder.getView(R.id.tv_put);
        TextView tv_creator = holder.getView(R.id.tv_creator);
        TextView tv_createdate = holder.getView(R.id.tv_createdate);
        TextView tv_reply = holder.getView(R.id.tv_reply);
        LinearLayout ll_repay= holder.getView(R.id.ll_repay);
        if(item.getDesp()!=null){
            tv_title.setText(item.getDesp());
        }
        if(item.isNeedFeedBack()){
            tv_status.setText("需响应");
        }else{
            tv_status.setText("无需响应");
        }
        if(item.getLinkManName()!=null){
            tv_put.setText(item.getLinkManName());
        }
        if(item.getCreateOperator()!=null){
            tv_creator.setText(item.getCreateOperator());
        }
        if(item.getCreatedDate()!=null){
            tv_createdate.setText(item.getCreatedDate().substring(0,10));
        }
        if(!Func.IsStringEmpty(item.getReply())){
            tv_reply.setText(item.getReply());
        }else {
            ll_repay.setVisibility(View.GONE);
        }


    }



    private PainPointAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(PainPointAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<PainPointBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<PainPointBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
