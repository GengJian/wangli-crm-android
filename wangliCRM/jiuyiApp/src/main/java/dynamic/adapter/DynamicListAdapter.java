package dynamic.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.Res;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
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
import dynamic.entity.DynamicBean;


public class DynamicListAdapter extends BaseQuickAdapter<DynamicBean.ContentBean,BaseViewHolder>  {

    public DynamicListAdapter(int layoutResId, List<DynamicBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final DynamicBean.ContentBean item) {
        holder.setIsRecyclable(false);
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView dynamicUrlTextView= holder.getView(R.id.tv_content);
        dynamicUrlTextView.setLineSpacing(3,1.3F);

        if(!Func.IsStringEmpty(item.getContent())){
            SpannableString dynamicContent = StringUtils.getDynamicContent(JiuyiMainApplication.getIns(), dynamicUrlTextView, item.getContent().replace("<br/>",""));
            dynamicUrlTextView.setText(dynamicContent);
        }

        TextView tv_title= holder.getView(R.id.tv_title);
        TextView tv_icon= holder.getView(R.id.tv_icon);
        SimpleDraweeView iv_icon= holder.getView(R.id.iv_icon);
        if(!Func.IsStringEmpty(item.getAvatarUrl())){
            LoaderManager.getLoader().loadNet(iv_icon, Constant.BaseUrl+"file/"+item.getAvatarUrl().toString(), new ILoader.Options(R.drawable.m_avatar_def, R.drawable.m_avatar_def));
            tv_icon.setVisibility(View.GONE);
            iv_icon.setVisibility(View.VISIBLE);
        }else{
            tv_icon.setVisibility(View.VISIBLE);
            iv_icon.setVisibility(View.GONE);
        }
        if(item.getOperatorNameSrFrAr()!=null){
            tv_title.setText(item.getOperatorNameSrFrAr());
            String sname=item.getOperatorNameSrFrAr();
            if(sname.length()>2){
                if(sname.contains("(") && sname.indexOf("(")>0){
                    String sSubname=sname.substring(0,sname.indexOf("("));
                    if(sSubname.length()>1){
                        tv_icon.setText(sSubname.substring(sSubname.length()-2,sSubname.length()));
                    }

                }else{
                    tv_icon.setText(sname.substring(sname.length()-2,sname.length()));
                }

            }else if(sname.length()>0){
                tv_icon.setText(sname.substring(0,sname.length()));
            }
        }

        TextView tv_date= holder.getView(R.id.tv_date);
        if(item.getCreatedDate()!=null){
            tv_date.setText(item.getCreatedDate());
        }



        TextView tv_type= holder.getView(R.id.tv_type);
        if(item.getFkTypeValue()!=null){
            tv_type.setText(item.getFkTypeValue());
        }
        TextView tv_collection= holder.getView(R.id.tv_collection);
        tv_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_collection",v);
                }
            }
        });
        Drawable drawableLeft = null;
        if(item.getFavorited()>0){
            drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.orderassistant_collection_s);
            tv_collection.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
            tv_collection.setCompoundDrawablePadding(Res.Dip2Pix(8));
        }else{
            drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.dynamiccollection);
            tv_collection.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
            tv_collection.setCompoundDrawablePadding(Res.Dip2Pix(8));
        }
        NineGridImageView noScrollgridview=holder.getView(R.id.noScrollgridview);
        if(item.getAttachmentList()!=null && item.getAttachmentList().size()>0){
            List<UserViewInfo> imgUrls = new ArrayList<>();
            for(int i=0;i<item.getAttachmentList().size();i++){
                UserViewInfo userViewInfo;
                String url="";
                AttachmentBean attachmentBean=item.getAttachmentList().get(i);
                if(attachmentBean.getQiniuKey()!=null && attachmentBean.getFileType().toLowerCase().equals("jpg") ){
                    userViewInfo=new UserViewInfo(item.getId(),Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey(),Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    imgUrls.add(userViewInfo);
                }

            }
            noScrollgridview.setAdapter(mAdapter);
            noScrollgridview.setImagesData(imgUrls);

        }

        NineGridVideoLayout noScrollVideogridview=holder.getView(R.id.noScrollVideogridview);
        if(item.getAttachmentList()!=null && item.getAttachmentList().size()>0){
            List<UserViewInfo> imgUrls = new ArrayList<>();
            for(int i=0;i<item.getAttachmentList().size();i++){
                UserViewInfo userViewInfo;
                AttachmentBean attachmentBean=item.getAttachmentList().get(i);
                if(attachmentBean.getQiniuKey()!=null && attachmentBean.getFileType().toLowerCase().equals("mp4") ){
                    userViewInfo=new UserViewInfo(item.getId(), CommonUtils.getAttachUrl(attachmentBean),CommonUtils.getAttachUrl(attachmentBean)+"/_thumbnail");
                    userViewInfo.setUser(attachmentBean.getFileName());
                    imgUrls.add(userViewInfo);
                }

            }
            noScrollVideogridview.setIsShowAll(true);
            noScrollVideogridview.setUrlList(imgUrls);

        }else {
            noScrollVideogridview.setVisibility(View.GONE);
        }

        NineGridVoiceLayout noScrollVoicegridview=holder.getView(R.id.noScrollVoicegridview);
        if(item.getAttachmentList()!=null && item.getAttachmentList().size()>0){
            List<UserViewInfo> imgUrls = new ArrayList<>();
            for(int i=0;i<item.getAttachmentList().size();i++){
                UserViewInfo userViewInfo;
                AttachmentBean attachmentBean=item.getAttachmentList().get(i);
                if(attachmentBean.getQiniuKey()!=null && attachmentBean.getFileType().toLowerCase().equals("mp3") ){
                    userViewInfo=new UserViewInfo(item.getId(),CommonUtils.getAttachUrl(attachmentBean),CommonUtils.getAttachUrl(attachmentBean)+"/_thumbnail");
                    userViewInfo.setUser(attachmentBean.getExtData());
                    imgUrls.add(userViewInfo);
                }

            }
            noScrollVoicegridview.setIsShowAll(true);
            noScrollVoicegridview.setUrlList(imgUrls);

        }else {
            noScrollVoicegridview.setVisibility(View.GONE);
        }

        TextView tv_good= holder.getView(R.id.tv_good);
        tv_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_good",v);
                }
            }
        });
        if(item.getLiked()>0){
            drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.gooded);
            tv_good.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
            tv_good.setCompoundDrawablePadding(Res.Dip2Pix(8));
        }else{
            drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.dynamicungood);
            tv_good.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
            tv_good.setCompoundDrawablePadding(Res.Dip2Pix(8));
        }
        if(item.getLikedCount()>=0){
            tv_good.setText(item.getLikedCount()+"");
        }
//        TextView tv_comment= holder.getView(R.id.tv_comment);
//        tv_comment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener!=null)
//                {
//                    listener.click(holder.getLayoutPosition(),"tv_comment",v);
//                }
//            }
//        });
//        if(item.getViewedCount()>0){
//            tv_comment.setText(item.getViewedCount()+"");
//        }


    }
    NineGridImageViewAdapter<UserViewInfo> mAdapter = new NineGridImageViewAdapter<UserViewInfo>() {
        @Override
        protected void onDisplayImage(Context context, ImageView imageView, UserViewInfo s) {
            GlideApp.with(context).load(s.getThumbnail()).placeholder(R.drawable.ic_default_image).into(imageView);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            return super.generateImageView(context);
        }

        @Override
        protected void onItemImageClick(Context context, ImageView imageView, int index, List<UserViewInfo> list) {
            ArrayList<String> imageList = new ArrayList<>();
            if(list!=null && list.size()>0){
                for(int i=0;i<list.size();i++){
                    UserViewInfo userViewInfo=list.get(i);
                    imageList.add(userViewInfo.getUrl());
                }
            }

            Intent intent = new Intent(context,ImageBrowseActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("param_flag_enum",ImageBrowseActivity.FLAG_ENUM[0]);
            bundle.putInt("param_position",index);
            bundle.putStringArrayList("image_url_group",imageList);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    };




    private DynamicListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DynamicBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<DynamicBean.ContentBean> newList) {
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
