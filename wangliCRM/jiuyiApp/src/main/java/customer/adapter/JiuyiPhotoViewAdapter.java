package customer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wanglicrm.android.R;
import com.loader.ILoader;
import com.loader.LoaderManager;

import java.util.List;

import commonlyused.bean.AppTotalBean;

public class JiuyiPhotoViewAdapter extends RecyclerView.Adapter<JiuyiPhotoViewAdapter.ViewHolder> {
    private Context mContext;
    private String[] data;
    private int[] imgdata;
    private List<String> imgUrl;
    private LayoutInflater inf;
    //子view是否充满了手机屏幕
    private boolean isCompleteFill = false;
    public List<AppTotalBean.ContentBean> mviewBeanList;

    public interface OnRecyclerViewItemListener {
        public void onItemClickListener(View view, int position);

        public void onItemLongClickListener(View view, int position);
    }

    private OnRecyclerViewItemListener mOnRecyclerViewItemListener;

    public void setOnRecyclerViewItemListener(OnRecyclerViewItemListener listener) {
        mOnRecyclerViewItemListener = listener;
    }

    public JiuyiPhotoViewAdapter(Context mContext, String[] data, int[] imgdata) {
        this.mContext = mContext;
        this.data = data;
        this.imgdata = imgdata;
        inf = LayoutInflater.from(mContext);

    }
    public JiuyiPhotoViewAdapter(Context mContext, List<String> imgUrl) {
        this.mContext = mContext;
        this.imgUrl = imgUrl;
        inf = LayoutInflater.from(mContext);

    }
   /* public JiuyiPhotoViewAdapter(Context mContext, List<AppTotalBean.ContentBean> viewBeanList) {
        this.mContext = mContext;
        this.mviewBeanList = viewBeanList;
        inf = LayoutInflater.from(mContext);

    }*/


    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = inf.inflate(R.layout.jiuyi_photo_recycler_item, viewGroup, false);
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
        if(imgUrl.get(i)!=null){
            LoaderManager.getLoader().loadNet(viewHolder.sv_image,imgUrl.get(i).toString(), new ILoader.Options( R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        }
    }

    //RecyclerView显示数据条数
    @Override
    public int getItemCount() {
        return imgUrl == null ? 0 : imgUrl.size();
    }

    //自定义的ViewHolder,减少findViewById调用次数
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
//        ImageView imageView;
        SimpleDraweeView sv_image;

        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.fragment__internetlog_recycler_item_textview);
//            imageView = (ImageView) itemView.findViewById(R.id.fragment__internetlog_recycler_item_iamgeview);
            sv_image = (SimpleDraweeView) itemView.findViewById(R.id.sv_image);
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