package customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.wanglicrm.android.R;
import com.jiuyi.app.GlideApp;
//import com.kevin.photo_browse.ImageBrowseIntent;
//import com.kevin.photo_browse.main.GlideApp;
//import com.kevin.photo_browse.ui.ImageBrowseActivity;
import com.jiuyi.app.JiuyiMainApplication;
import com.loader.ILoader;
import com.loader.LoaderManager;

import java.util.ArrayList;
import java.util.List;

import customer.Utils.CommonUtils;
import customer.activity.ImageBrowseActivity;
import customer.entity.AttachmentBean;
import customer.entity.ImageViewBean;
import customer.entity.StepViewBean;
import customer.entity.UserViewInfo;
import customer.view.AlxUrlTextView;

import customer.view.DynamicUrlTextView;
import customer.view.NineGridVideoLayout;
import customer.view.NineGridVoiceLayout;
import customer.view.SelectPicPopupWindow;
import dynamic.Utils.StringUtils;


/**
 * 风险跟踪适配器
 */

public class StepViewAdapter extends BaseAdapter {
    private Context context;
    private List<StepViewBean> traceList = new ArrayList<>();
    int curpostion=-1;
    JiuyiPictureAdapter pictureAdapter;
    JiuyiPhotoViewAdapter photoAdapter;
    protected SelectPicPopupWindow menuWindow;
    StepViewBean trace=null;
    List<ImageViewBean> imageViewBeanList;
    boolean bCache=true;
    private NineGridImageViewAdapter<UserViewInfo> adapter;

    public StepViewAdapter(Context context, List<StepViewBean> traceList,boolean bCache) {
        this.context = context;
        this.traceList = traceList;
        this.bCache=bCache;
    }
    public StepViewAdapter(Context context, List<StepViewBean> traceList) {
        this.context = context;
        this.traceList = traceList;
    }
    public void clear() {
        traceList.clear();
    }
    @Override
    public int getCount() {
        return traceList.size();
    }

    @Override
    public StepViewBean getItem(int position) {
        return traceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        trace = getItem(position);
        if (convertView != null && bCache) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.stepview_adapter, parent, false);
            holder.tvTopLine = (TextView) convertView.findViewById(R.id.tvTopLine);
            holder.tvDot = (SimpleDraweeView) convertView.findViewById(R.id.tvDot);
            holder.tvLine = (TextView) convertView.findViewById(R.id.tvLine);
            holder.tvAcceptStation = (TextView) convertView.findViewById(R.id.step_tv_des);
            holder.tvAcceptTime = (TextView) convertView.findViewById(R.id.step_tv_time);
            holder.tvAcceptStationBelow = (TextView) convertView.findViewById(R.id.step_tv_des_below);
//            holder.tvAcceptStationBelow2 = (DynamicUrlTextView) convertView.findViewById(R.id.step_tv_des_below2);
            holder.step_tv_creator = (TextView) convertView.findViewById(R.id.step_tv_creator);
            holder.rlTimeline = (RelativeLayout) convertView.findViewById(R.id.rlTimeline);
//            holder.imageView = (SimpleDraweeView) convertView.findViewById(R.id.item_image);
//            holder.noScrollgridview = (RecyclerView) convertView.findViewById(R.id.rv_post_list);
            holder.noScrollgridview = (NineGridImageView) convertView.findViewById(R.id.noScrollgridview);
            holder.noScrollVideogridview = (NineGridVideoLayout) convertView.findViewById(R.id.noScrollVideogridview);
            holder.noScrollVoicegridview = (NineGridVoiceLayout) convertView.findViewById(R.id.noScrollVoicegridview);

            convertView.setTag(holder);
        }
//        holder.tvAcceptStationBelow2.setVisibility(View.VISIBLE);
//        holder.tvAcceptStationBelow.setVisibility(View.GONE);
//        if(!bCache){
//            holder.tvAcceptStationBelow2.setVisibility(View.VISIBLE);
//            holder.tvAcceptStationBelow.setVisibility(View.GONE);
//        }else{
//
//            holder.tvAcceptStationBelow2.setVisibility(View.GONE);
//            holder.tvAcceptStationBelow.setVisibility(View.VISIBLE);
//        }
        if (position == 0) {
            holder.tvTopLine.setVisibility(View.INVISIBLE);
        }
        if (position == traceList.size() - 1) {
            holder.tvLine.setVisibility(View.GONE);
        } else {
            holder.tvLine.setVisibility(View.VISIBLE);
        }
        if(trace.getCreator()!=null){
            holder.step_tv_creator.setText("创建人:"+trace.getCreator());
        }else{
            holder.step_tv_creator.setVisibility(View.GONE);
        }
        if(trace.getIconUrl()!=null){
            LoaderManager.getLoader().loadNet(holder.tvDot, trace.getIconUrl(), new ILoader.Options(R.drawable.feed_default_icon, R.drawable.feed_default_icon));
        }else {
            holder.tvDot.setBackgroundResource(R.drawable.feed_default_icon);
        }


        holder.tvAcceptTime.setText(trace.getAcceptTime());
        holder.tvAcceptStation.setText(trace.getAcceptStation());
        if (!TextUtils.isEmpty(trace.getAcceptStationBelow())) {
            SpannableString dynamicContent = StringUtils.getDynamicContent(JiuyiMainApplication.getIns(), holder.tvAcceptStationBelow, trace.getAcceptStationBelow());
            holder.tvAcceptStationBelow.setText(dynamicContent);
//            if(trace.getAcceptStationBelow()!=null){
//                try {
//                    holder.tvAcceptStationBelow2.setText(trace.getAcceptStationBelow());
//                }catch (Exception e){
//
//                }
//
//            }
        }
        holder.tvAcceptStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trace.getUrl()==null){
                    return;
                }
                String stitle="";
                if(!Func.IsStringEmpty(trace.getAcceptStation())){
                    stitle=trace.getAcceptStation();
                }else{
                    stitle="销售订单";
                }
                Bundle mBundle=new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, trace.getUrl()+"&token="+Rc.id_tokenparam);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,stitle);
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
            }
        });

        List<String> attchUrls=new ArrayList<>();
        imageViewBeanList=new ArrayList<>();
        if(trace.getAttachurl()!=null && trace.getAttachurl().size()>0){
            List<UserViewInfo> imgUrls = new ArrayList<>();
            for(int i=0;i<trace.getAttachurl().size();i++){
                AttachmentBean attachmentBean=trace.getAttachurl().get(i);
                if(attachmentBean.getQiniuKey()!=null && attachmentBean.getFileType().toLowerCase().equals("jpg") ){
                    UserViewInfo userViewInfo;
                    String url="";
                    userViewInfo=new UserViewInfo(trace.getId(),trace.getAttachurl().get(i).getUrl(),trace.getAttachurl().get(i).getThumbnail());
                    imgUrls.add(userViewInfo);
                }

            }
            holder.noScrollgridview.setAdapter(mAdapter);
            holder.noScrollgridview.setImagesData(imgUrls);

        }else {
            holder.noScrollgridview.setVisibility(View.GONE);
        }

        if(trace.getAttachurl()!=null && trace.getAttachurl().size()>0){
            List<UserViewInfo> imgUrls = new ArrayList<>();
            for(int i=0;i<trace.getAttachurl().size();i++){
                AttachmentBean attachmentBean=trace.getAttachurl().get(i);
                if(attachmentBean.getQiniuKey()!=null && attachmentBean.getFileType().toLowerCase().equals("mp4") ){
                    UserViewInfo userViewInfo;
                    String url="";
                    userViewInfo=new UserViewInfo(trace.getId(), CommonUtils.getAttachUrl(attachmentBean),CommonUtils.getAttachUrl(attachmentBean)+"/_thumbnail");
                    userViewInfo.setUser(attachmentBean.getFileName());
                    imgUrls.add(userViewInfo);

                }

            }
            holder.noScrollVideogridview.setIsShowAll(true);
            holder.noScrollVideogridview.setUrlList(imgUrls);

        }else {
            holder.noScrollVideogridview.setVisibility(View.GONE);
        }
        if(trace.getAttachurl()!=null && trace.getAttachurl().size()>0){
            List<UserViewInfo> imgUrls = new ArrayList<>();
            for(int i=0;i<trace.getAttachurl().size();i++){
                AttachmentBean attachmentBean=trace.getAttachurl().get(i);
                if(attachmentBean.getQiniuKey()!=null && attachmentBean.getFileType().toLowerCase().equals("mp3") ){
                    UserViewInfo userViewInfo;
                    String url="";
                    userViewInfo=new UserViewInfo(trace.getId(),CommonUtils.getAttachUrl(attachmentBean),CommonUtils.getAttachUrl(attachmentBean)+"/_thumbnail");
                    userViewInfo.setUser(attachmentBean.getExtData());
                    imgUrls.add(userViewInfo);
                }

            }
            holder.noScrollVoicegridview.setIsShowAll(true);
            holder.noScrollVoicegridview.setUrlList(imgUrls);

        }else {
            holder.noScrollVoicegridview.setVisibility(View.GONE);
        }


        return convertView;
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

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<StepViewBean> addMessageList) {
        //增加数据
        int position = traceList.size();
        traceList.addAll(position, addMessageList);
        notifyDataSetChanged();
    }


    static class ViewHolder {
        public TextView tvAcceptTime, tvAcceptStation, tvLine, tvAcceptStationBelow,step_tv_creator;
        public DynamicUrlTextView tvAcceptStationBelow2;
        public TextView tvTopLine;
        public RelativeLayout rlTimeline;
        public SimpleDraweeView /*imageView,*/tvDot;
        public NineGridImageView noScrollgridview;
        public NineGridVideoLayout noScrollVideogridview;
        public NineGridVoiceLayout noScrollVoicegridview;

//        public RecyclerView noScrollgridview;

    }

}
