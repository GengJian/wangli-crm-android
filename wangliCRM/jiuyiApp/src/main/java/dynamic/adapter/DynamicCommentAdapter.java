package dynamic.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.utils.Res;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.jiuyi.app.GlideApp;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.tools.jiuyiViewUtil;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.List;

import customer.Utils.CommonUtils;
import customer.activity.ImageBrowseActivity;
import customer.entity.AttachmentBean;
import customer.entity.UserViewInfo;
import customer.view.NineGridVideoLayout;
import customer.view.NineGridVoiceLayout;
import dynamic.Utils.StringUtils;
import dynamic.entity.CommentBean;
import dynamic.entity.DynamicBean;


public class DynamicCommentAdapter extends BaseQuickAdapter<CommentBean,BaseViewHolder>  {

    public DynamicCommentAdapter(int layoutResId, List<CommentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CommentBean item) {
        holder.setIsRecyclable(false);
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_content= holder.getView(R.id.tv_content);
        TextView tv_title= holder.getView(R.id.tv_title);
        TextView tv_icon= holder.getView(R.id.tv_icon);
        SimpleDraweeView iv_icon= holder.getView(R.id.iv_icon);
        iv_icon.setVisibility(View.VISIBLE);
        tv_icon.setVisibility(View.GONE);
        iv_icon.setImageResource(R.drawable.m_avatar_def);
        String title="";

        if(item.getJobTitle()!=null){
            title+=item.getJobTitle();
        }
        if(!Func.IsStringEmpty(item.getUsername())){
            title+=item.getUsername();
        }
        tv_title.setText(title);
        if(!Func.IsStringEmpty(item.getContent())){
            tv_content.setText(item.getContent());
        }
        TextView tv_date= holder.getView(R.id.tv_date);
        if(!Func.IsStringEmpty(item.getOperationTime())){
            tv_date.setText(item.getOperationTime());
        }
    }




    private DynamicCommentAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicCommentAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<CommentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<CommentBean> newList) {
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
