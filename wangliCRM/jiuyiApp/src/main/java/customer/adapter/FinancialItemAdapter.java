package customer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.utils.Func;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wanglicrm.android.R;
import com.loader.ILoader;
import com.loader.LoaderManager;

import java.util.List;

import customer.entity.RiskWarnBean;
import customer.entity.ThirdModuleBean;

public class FinancialItemAdapter extends RecyclerView.Adapter<FinancialItemAdapter.ViewHolder> {
    private Context mContext;
    private String[] data;
    private String[] imgdata;
    private String[] countdata;
    private List<ThirdModuleBean.BeanListBean> contentBeanlist;
    private LayoutInflater inf;
    //子view是否充满了手机屏幕
    private boolean isCompleteFill = false;

    public interface OnRecyclerViewItemListener {
        public void onItemClickListener(View view, int position);

        public void onItemLongClickListener(View view, int position);
    }

    private OnRecyclerViewItemListener mOnRecyclerViewItemListener;

    public void setOnRecyclerViewItemListener(OnRecyclerViewItemListener listener) {
        mOnRecyclerViewItemListener = listener;
    }

    public FinancialItemAdapter(Context mContext, String[] data, String[] imgdata) {
        this.mContext = mContext;
        this.data = data;
        this.imgdata = imgdata;
        inf = LayoutInflater.from(mContext);

    }
    public FinancialItemAdapter(Context mContext, String[] data, String[] imgdata, String[] countdata) {
        this.mContext = mContext;
        this.data = data;
        this.countdata=countdata;
        this.imgdata = imgdata;
        inf = LayoutInflater.from(mContext);

    }
    public FinancialItemAdapter(Context mContext, List<ThirdModuleBean.BeanListBean> contentBeanlist) {
        this.mContext = mContext;
        this.contentBeanlist=contentBeanlist;
        inf = LayoutInflater.from(mContext);

    }


    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = inf.inflate(R.layout.customer_financial_item, viewGroup, false);
        return new ViewHolder(v);
    }

    //将数据绑定到子View，会自动复用View
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder == null) {
            return;
        }
        if (mOnRecyclerViewItemListener != null) {
            itemOnClick(viewHolder);
            itemOnLongClick(viewHolder);
        }
        if(contentBeanlist.get(i).getField()!=null){
            viewHolder.tv_field.setText(contentBeanlist.get(i).getField());
        }
        viewHolder.tv_fieldValue.setText(contentBeanlist.get(i).getFieldValue()+"");
        if(contentBeanlist.get(i).getField()!=null){
            viewHolder.tv_field.setText(contentBeanlist.get(i).getField());
        }
        if(contentBeanlist.get(i).getUnit()!=null){
            viewHolder.tv_unit.setText(contentBeanlist.get(i).getUnit());
        }
        if( !Func.IsStringEmpty(contentBeanlist.get(i).getFieldTitle())){
            viewHolder.tv_fieldTitle.setText(contentBeanlist.get(i).getFieldTitle());
            viewHolder.tv_fieldTitle.setVisibility(View.VISIBLE);
        }else{
            viewHolder.tv_fieldTitle.setVisibility(View.GONE);
        }
        if(contentBeanlist.get(i).getIconUrl()!=null){
            LoaderManager.getLoader().loadNet(viewHolder.leftAvatar, contentBeanlist.get(i).getIconUrl(),/*null, */new ILoader.Options(R.drawable.c_decision, R.drawable.c_decision));
        }

    }

    //RecyclerView显示数据条数
    @Override
    public int getItemCount() {
        return contentBeanlist.size();
    }

    //自定义的ViewHolder,减少findViewById调用次数
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_field,tv_fieldTitle,tv_fieldValue,tv_unit;
        ImageView imageView;
        SimpleDraweeView leftAvatar;

        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
            tv_field = (TextView) itemView.findViewById(R.id.tv_field);
            tv_fieldTitle = (TextView) itemView.findViewById(R.id.tv_fieldTitle);
            tv_fieldValue = (TextView) itemView.findViewById(R.id.tv_fieldValue);
            tv_unit = (TextView) itemView.findViewById(R.id.tv_unit);
            leftAvatar = (SimpleDraweeView) itemView.findViewById(R.id.leftAvatar);
        }
    }
    //单机事件
    private void itemOnClick(final RecyclerView.ViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                mOnRecyclerViewItemListener.onItemClickListener(holder.itemView, position);
            }
        });
    }
    //长按事件
    private void itemOnLongClick(final RecyclerView.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getLayoutPosition();
                mOnRecyclerViewItemListener.onItemLongClickListener(holder.itemView, position);
                //返回true是为了防止触发onClick事件
                return true;
            }
        });
    }

}