package customer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wanglicrm.android.R;
import com.loader.ILoader;
import com.loader.LoaderManager;

import java.util.List;

import commonlyused.bean.AppTotalBean;

public class DynamicPopwindowAdapter extends RecyclerView.Adapter<DynamicPopwindowAdapter.ViewHolder> {
    private Context mContext;
    private String[] data;
    private int[] imgdata;
    private LayoutInflater inf;
    //子view是否充满了手机屏幕
    private boolean isCompleteFill = false;
    public List<String> mviewBeanList;
    private  int selectPosition=-1;
    public interface OnRecyclerViewItemListener {
        public void onItemClickListener(View view, int position);

        public void onItemLongClickListener(View view, int position);
    }

    private OnRecyclerViewItemListener mOnRecyclerViewItemListener;

    public void setOnRecyclerViewItemListener(OnRecyclerViewItemListener listener) {
        mOnRecyclerViewItemListener = listener;
    }

    public DynamicPopwindowAdapter(Context mContext, String[] data, int[] imgdata) {
        this.mContext = mContext;
        this.data = data;
        this.imgdata = imgdata;
        inf = LayoutInflater.from(mContext);

    }
    public DynamicPopwindowAdapter(Context mContext, List<String> viewBeanList) {
        this.mContext = mContext;
        this.mviewBeanList = viewBeanList;
        inf = LayoutInflater.from(mContext);

    }
    public DynamicPopwindowAdapter(Context mContext, List<String> viewBeanList,int selectPosition) {
        this.mContext = mContext;
        this.mviewBeanList = viewBeanList;
        this.selectPosition=selectPosition;
        inf = LayoutInflater.from(mContext);

    }
    private DynamicPopwindowAdapter.onCLickItemListener listener;


    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = inf.inflate(R.layout.dynamic_popwin_item, viewGroup, false);
        return new ViewHolder(v);
    }

    //将数据绑定到子View，会自动复用View
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder == null) {
            return;
        }
        if (mOnRecyclerViewItemListener != null) {
            itemOnClick(viewHolder);
            itemOnLongClick(viewHolder);
        }
        viewHolder.btn_name.setText(mviewBeanList.get(i));
        if(selectPosition==i){
            viewHolder.btn_name.setTextColor(R.color.jiuyi_blue);
        }
        viewHolder.btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(viewHolder.getLayoutPosition(),"btn_name",v);
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

        Button btn_name;

        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
            btn_name = (Button) itemView.findViewById(R.id.btn_name);
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

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    public void  setOnCLickItemListener(DynamicPopwindowAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

}