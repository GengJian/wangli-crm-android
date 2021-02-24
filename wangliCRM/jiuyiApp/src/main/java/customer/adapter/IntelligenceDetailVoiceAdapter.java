package customer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jiuyi.app.GlideApp;
import com.jiuyi.app.JiuyiMainApplication;

import java.util.ArrayList;

import customer.Utils.FileUtils;
import customer.entity.Media;

public class IntelligenceDetailVoiceAdapter extends RecyclerView.Adapter<IntelligenceDetailVoiceAdapter.ViewHolder> {
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
    }

    private OnRecyclerViewItemListener mOnRecyclerViewItemListener;

    public void setOnRecyclerViewItemListener(OnRecyclerViewItemListener listener) {
        mOnRecyclerViewItemListener = listener;
    }

    public IntelligenceDetailVoiceAdapter(Context mContext, ArrayList<Media> viewBeanList) {
        this.mContext = mContext;
        this.mviewBeanList = viewBeanList;
        inf = LayoutInflater.from(mContext);

    }
    private IntelligenceDetailVoiceAdapter.onCLickItemListener listener;

    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = inf.inflate(R.layout.published_singal_voice_item, viewGroup, false);
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
        }
        if (i == mviewBeanList.size()) {
            viewHolder.img_delete.setVisibility(View.GONE);
            viewHolder.image.setImageResource(R.drawable.enclosure);
            // 图片不能超过5张
            if (i == 9) {
                viewHolder.image.setVisibility(View.GONE);
            }
        }
//        if(!Func.IsStringEmpty(mviewBeanList.get(i).getPath())){
//            GlideApp.with(JiuyiMainApplication.getIns()).load(mviewBeanList.get(i).getPath()).placeholder(R.drawable.ic_default_image).into(viewHolder.image);
//        }
        if(!Func.IsStringEmpty(mviewBeanList.get(i).getFileTime())){
            viewHolder.tv_time.setText(FileUtils.getAudioDuration(Long.parseLong(mviewBeanList.get(i).getFileTime())));
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

        public ImageView image;
        private ImageView img_delete;
        private TextView tv_time;

        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
            // 照片
            image = (ImageView) itemView.findViewById(R.id.item_grida_image);
            // 删除图标
            img_delete = (ImageView) itemView.findViewById(R.id.img_delete);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);

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
    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    public void  setOnCLickItemListener(IntelligenceDetailVoiceAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

}