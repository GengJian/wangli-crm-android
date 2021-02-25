package commonlyused.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jiuyi.app.JiuyiMainApplication;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.tencent.qcloud.sdk.Constant;
import com.umeng.analytics.MobclickAgent;
import com.wanglicrm.android.R;

import java.util.List;

import commonlyused.bean.AppCategoryBean;
import commonlyused.bean.AppItemBean;
import commonlyused.db.AppItemBeanDao;
import commonlyused.db.DbHelper;

public class AppCategoryDetailAdapter extends RecyclerView.Adapter<AppCategoryDetailAdapter.ViewHolder> {
    private Context mContext;
    private String[] data;
    private int[] imgdata;
    private LayoutInflater inf;
    //子view是否充满了手机屏幕
    private boolean isCompleteFill = false;
    public List<AppCategoryBean.ItemsBean> mviewBeanList;

    public interface OnRecyclerViewItemListener {
        public void onItemClickListener(View view, int position);

        public void onItemLongClickListener(View view, int position);
    }

    private OnRecyclerViewItemListener mOnRecyclerViewItemListener;

    public void setOnRecyclerViewItemListener(OnRecyclerViewItemListener listener) {
        mOnRecyclerViewItemListener = listener;
    }

    public AppCategoryDetailAdapter(Context mContext, String[] data, int[] imgdata) {
        this.mContext = mContext;
        this.data = data;
        this.imgdata = imgdata;
        inf = LayoutInflater.from(mContext);

    }
    public AppCategoryDetailAdapter(Context mContext, List<AppCategoryBean.ItemsBean> viewBeanList) {
        this.mContext = mContext;
        this.mviewBeanList = viewBeanList;
        inf = LayoutInflater.from(mContext);

    }


    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = inf.inflate(R.layout.fragment_internetlog_recycler_item, viewGroup, false);
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
        viewHolder.textView.setText(mviewBeanList.get(i).getName());
        if(mviewBeanList.get(i).getAndroidIconUrl()!=null){
            LoaderManager.getLoader().loadNet(viewHolder.iv_icon, Constant.BaseUrl+"file/"+mviewBeanList.get(i).getAndroidIconUrl().toString(), new ILoader.Options(R.drawable.default_icon, R.drawable.default_icon));
        }else{
            viewHolder.iv_icon.setBackgroundResource(R.drawable.default_icon);
        }
    }

    //RecyclerView显示数据条数
    @Override
    public int getItemCount() {
        return mviewBeanList == null ? 0 : mviewBeanList.size();
    }

    //自定义的ViewHolder,减少findViewById调用次数
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
//        ImageView imageView;
        SimpleDraweeView iv_icon;

        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.fragment__internetlog_recycler_item_textview);
            iv_icon = (SimpleDraweeView) itemView.findViewById(R.id.fragment__internetlog_recycler_item_iamgeview);
        }
    }
    //单机事件
    private void itemOnClick(final RecyclerView.ViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                mOnRecyclerViewItemListener.onItemClickListener(holder.itemView, position);
                int actionid=0;
                if(mviewBeanList.get(position).getUrl()!=null){
                    Rc.mBackfresh=true;
                    AppItemBean appItemBean =new AppItemBean();
                    appItemBean.setName(mviewBeanList.get(position).getName());
                    appItemBean.setAndroidIconUrl(mviewBeanList.get(position).getAndroidIconUrl());
                    appItemBean.setUrl(mviewBeanList.get(position).getUrl());
                    AppItemBean itemBean =  DbHelper.getInstance().appItemBeanLongDBManager().queryBuilder()
                            .where(AppItemBeanDao.Properties.Name.eq(appItemBean.getName()))
                            .build().unique();
                    if(itemBean!=null){
                        DbHelper.getInstance().appItemBeanLongDBManager().delete(itemBean);
                    }

                    DbHelper.getInstance().appItemBeanLongDBManager().insert(appItemBean);

                    MobclickAgent.onEvent(JiuyiMainApplication.getIns(), mviewBeanList.get(position).getId()+"");
                    if(mviewBeanList.get(position).getUrl().toLowerCase().startsWith("action")){
                        String url=mviewBeanList.get(position).getUrl().toString().trim();
                        if(url.indexOf(":")>0){
                            url= url.substring(url.indexOf(":")+1,url.length());
                            actionid=Integer.parseInt(url);
                            if(actionid>0){
                                Bundle mBundle = new Bundle();
                                if(mviewBeanList.get(position).getName()!=null){
                                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,mviewBeanList.get(position).getName());
                                }
//                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, actionid);
                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, actionid,true);
                            }
                        }
                    }else{
                        Bundle mBundle = new Bundle();
                        String url=mviewBeanList.get(position).getUrl().trim();
                        if(!Func.IsStringEmpty(url)){
                            url+="?token="+Rc.id_tokenparam;
                            mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.JY_MENU_OpenWebInfoContent);
                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE,mviewBeanList.get(position).getName());
                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                        }
                    }
                }

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