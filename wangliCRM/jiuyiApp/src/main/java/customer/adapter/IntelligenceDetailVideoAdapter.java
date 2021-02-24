package customer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.control.utils.Func;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wanglicrm.android.R;
import com.jiuyi.app.GlideApp;
import com.jiuyi.app.JiuyiMainApplication;
import com.loader.ILoader;
import com.loader.LoaderManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import customer.entity.Media;

public class IntelligenceDetailVideoAdapter extends RecyclerView.Adapter<IntelligenceDetailVideoAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater inf;
    //子view是否充满了手机屏幕
    private boolean isCompleteFill = false;

    public ArrayList<Media> getMviewBeanList() {
        return mviewBeanList;
    }

    public void setMviewBeanList(ArrayList<Media> mviewBeanList) {
        this.mviewBeanList = mviewBeanList;
    }

    public ArrayList<Media> mviewBeanList;

    public interface OnRecyclerViewItemListener {
        public void onItemClickListener(View view, int position);

//        public void onItemLongClickListener(View view, int position);
    }

    private OnRecyclerViewItemListener mOnRecyclerViewItemListener;

    public void setOnRecyclerViewItemListener(OnRecyclerViewItemListener listener) {
        mOnRecyclerViewItemListener = listener;
    }

    public IntelligenceDetailVideoAdapter(Context mContext, ArrayList<Media> viewBeanList) {
        this.mContext = mContext;
        this.mviewBeanList = viewBeanList;
        inf = LayoutInflater.from(mContext);

    }
    private IntelligenceDetailVideoAdapter.onCLickItemListener listener;

    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = inf.inflate(R.layout.published_singal_video_item, viewGroup, false);
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
//            itemOnLongClick(viewHolder);
        }
        if(!Func.IsStringEmpty(mviewBeanList.get(i).getPath())){
            File qemu_file = new File(mviewBeanList.get(i).getPath());
            if (qemu_file.exists()) {
                LoaderManager.getLoader().loadFile(viewHolder.image,qemu_file,new ILoader.Options(R.drawable.ic_default_image, R.drawable.ic_default_image));
            }
        } else if(!Func.IsStringEmpty(mviewBeanList.get(i).getThumbnailPath())){
            GlideApp.with(JiuyiMainApplication.getIns()).load(mviewBeanList.get(i).getThumbnailPath()).placeholder(R.drawable.ic_default_image).into(viewHolder.image);
//            File qemu_file = new File(mviewBeanList.get(i).getUrl());
//            if (qemu_file.exists()) {
//                LoaderManager.getLoader().loadFile(viewHolder.image,qemu_file,new ILoader.Options(R.drawable.ic_default_image, R.drawable.ic_default_image));
//            }
        }
        if (!mviewBeanList.get(i).isShowIcon()) {
            viewHolder.img_delete.setVisibility(View.GONE);
        } else {
            viewHolder.img_delete.setVisibility(View.VISIBLE);
        }
        viewHolder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(viewHolder.getLayoutPosition(),"img_delete",v);
                }
            }
        });
    }

    //RecyclerView显示数据条数
    @Override
    public int getItemCount() {
        return mviewBeanList == null ? 0 : mviewBeanList.size();
    }

    //自定义的ViewHolder,减少findViewById调用次数
    class ViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView image;
        private ImageView img_delete;

        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
            // 照片
            image = (SimpleDraweeView) itemView.findViewById(R.id.item_grida_image);
            // 删除图标
            img_delete = (ImageView) itemView.findViewById(R.id.img_delete);

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
//    //长按事件
//    private void itemOnLongClick(final RecyclerView.ViewHolder holder) {
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                int position = holder.getLayoutPosition();
//                mOnRecyclerViewItemListener.onItemLongClickListener(holder.itemView, position);
//                //返回true是为了防止触发onClick事件
//                return true;
//            }
//        });
//    }
    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    public void  setOnCLickItemListener(IntelligenceDetailVideoAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

}