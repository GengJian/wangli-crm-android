package customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.jiuyi.app.GlideApp;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.tools.jiuyiViewUtil;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.lzh.easythread.EasyThread;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.List;

import customer.Utils.CommonUtils;
import customer.activity.ImageBrowseActivity;
import customer.entity.AttachmentBean;
import customer.entity.Media;
import customer.entity.UserViewInfo;
import customer.view.DynamicUrlTextView;
import customer.view.NineGridVideoLayout;
import customer.view.NineGridVoiceLayout;
import dynamic.Utils.DynamicUtils;
import dynamic.Utils.StringUtils;
import dynamic.entity.DyInteligenceBean;
import dynamic.entity.DyViewBean;


public class DynamicIntelligenceAdapter extends BaseQuickAdapter<DyInteligenceBean.ContentBean,BaseViewHolder>  {
    private List<String> lookedHistory;
    IntelligenceDetailPictureAdapter intelligenceDetailPictureAdapter;
    private ArrayList<Media> picMediaList;
    EasyThread executor = null;
    DyViewBean dyViewBean;

    public String getBackPageType() {
        return backPageType;
    }

    public void setBackPageType(String backPageType) {
        this.backPageType = backPageType;
    }

    private String backPageType="";

    public DynamicIntelligenceAdapter(int layoutResId, List<DyInteligenceBean.ContentBean> data) {
        super(layoutResId, data);
        // 创建一个独立的实例进行使用
        executor = EasyThread.Builder
                .createFixed(4)
                .setPriority(Thread.MAX_PRIORITY)
                .build();
    }
    // 启动普通Runnable任务
    public void runnableTask() {
        executor.setName("Runnable task")
                .execute(new NormalTask());
    }
    private class NormalTask implements Runnable {
        @Override
        public void run() {
            JiuyiHttp.POST("view-record/create-multiple")
                    .setJson(GsonUtil.gson().toJson(dyViewBean))
                    .addHeader("Authorization", Rc.id_tokenparam)
                    .request(new ACallback<Object>() {
                        @Override
                        public void onSuccess(Object data) {
                            if(data!=null){

                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });
        }


    }

    @Override
    protected void convert(final BaseViewHolder holder,final DyInteligenceBean.ContentBean item) {
        customer.listener.OnItemClickListener mOnItemClickListener;
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_dot = holder.getView(R.id.tv_dot);
        NineGridImageView noScrollgridview = holder.getView(R.id.noScrollgridview);

        if(item.getAttachmentList()!=null && item.getAttachmentList().size()>0){
            List<UserViewInfo> imgUrls = new ArrayList<>();
            for(int i=0;i<item.getAttachmentList().size();i++){
                AttachmentBean attachmentBean=item.getAttachmentList().get(i);
                if(!Func.IsStringEmpty(attachmentBean.getQiniuKey()) && attachmentBean.getFileType().toLowerCase().equals("jpg") ){
                    UserViewInfo userViewInfo;
                    String url="";
                    userViewInfo=new UserViewInfo(item.getId(),Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey(),Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    imgUrls.add(userViewInfo);
                }

            }
            noScrollgridview.setAdapter(mAdapter);
            noScrollgridview.setImagesData(imgUrls);

        }else {
            noScrollgridview.setVisibility(View.GONE);
        }
      /*  RecyclerView rv_piclist= holder.getView(R.id.rv_piclist);
        GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(), 5);
        rv_piclist.setNestedScrollingEnabled(false);
        rv_piclist.setLayoutManager(mgr);
        rv_piclist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));
        picMediaList=new ArrayList<>();
        if(item.getAttachments()!=null && item.getAttachments().size()>0){
            for(int i=0;i<item.getAttachments().size();i++){
                AttachmentBean attachmentBean=item.getAttachments().get(i);
                if(attachmentBean.getFileType().toLowerCase().equals("jpg")){
                    Media media=new Media();
                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    media.setExtension(".jpg");
                    media.setShowIcon(false);
                    media.setMediaType(0);
                    picMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp4")){
                    Media media=new Media();
                    media.setExtension(".mp4");
                    media.setMediaType(3);
                    media.setShowIcon(false);
                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
//                    videoMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp3")){
                    Media media=new Media();
                    media.setExtension(".mp3");
                    media.setShowIcon(false);
                    media.setMediaType(2);
                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
//                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
//                    voiceMediaList.add(media);
                }
            }

        }
        intelligenceDetailPictureAdapter = new IntelligenceDetailPictureAdapter(JiuyiMainApplication.getIns(), picMediaList);
        rv_piclist.setAdapter(intelligenceDetailPictureAdapter);
        intelligenceDetailPictureAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailPictureAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                intent.putExtra("OPERTYPE","VIEW");  //查看
                intent.putExtra(PickerConfig.PRE_RAW_LIST, intelligenceDetailPictureAdapter.getMviewBeanList());
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 9);  //最大选择数量，默认40（非必填参数）
                intent.putExtra(PickerConfig.CURRENT_POSITION, position);  //最大选择数量，默认40（非必填参数）
                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 1500);
            }
        });*/
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

        lookedHistory= DynamicUtils.getInstance().getSearchList();

        if(lookedHistory!=null &&lookedHistory.contains(backPageType+item.getId())){
            tv_dot.setVisibility(View.GONE);
        }else {
            tv_dot.setVisibility(View.VISIBLE);
        }
        TextView tv_icon= holder.getView(R.id.tv_icon);
        SimpleDraweeView iv_icon= holder.getView(R.id.iv_icon);
        if(item.getOperator()!=null &&item.getOperator().getAvatarUrl()!=null){
            LoaderManager.getLoader().loadNet(iv_icon, Constant.BaseUrl+"file/"+item.getOperator().getAvatarUrl().toString(), new ILoader.Options(R.drawable.m_avatar_def, R.drawable.m_avatar_def));
            tv_icon.setVisibility(View.GONE);
            iv_icon.setVisibility(View.VISIBLE);
        }else{
            tv_icon.setVisibility(View.VISIBLE);
            iv_icon.setVisibility(View.GONE);
        }


        String name="";
        TextView tv_name = holder.getView(R.id.tv_name);
        if(item.getOperator()!=null){
            if(item.getOperator().getDepartment()!=null){
                if(item.getOperator().getDepartment().getName()!=null){
                    name+=  item.getOperator().getDepartment().getName()+":";
                }

            }
            if(item.getOperator().getName()!=null){
                name+=  item.getOperator().getName();
                String sname=item.getOperator().getName();
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
        }
        tv_name.setText(name);



        TextView tv_content = holder.getView(R.id.tv_content);
        if(!Func.IsStringEmpty(item.getContent())){
            SpannableString dynamicContent = StringUtils.getDynamicContent(JiuyiMainApplication.getIns(), tv_content, item.getContent().replace("<br/>",""));
            tv_content.setText(dynamicContent);
            tv_content.setLineSpacing(3,1.3F);
        }
        String label="";
        TextView tv_label = holder.getView(R.id.tv_label);
        label+="来源:";
        if(item.getMember()!=null && item.getMember().getAbbreviation()!=null){
            label+=item.getMember().getAbbreviation();
        }
        if(item.getIntelligence()!=null &&item.getIntelligence().getBusinessTypeValue()!=null ){
            label+="/"+item.getIntelligence().getBusinessTypeValue();
        }
        if(item.getIntelligence()!=null &&item.getIntelligenceInfoValue()!=null ){
            label+="/"+item.getIntelligenceInfoValue();
        }
        if(item.getIntelligence()!=null &&item.getIntelligenceTypeValue()!=null ){
            label+="/"+item.getIntelligenceTypeValue();
        }

        if(item.getIntelligence()!=null && (item.getIntelligence().getBigCategoryKey().equals("industry_policy")
                || item.getIntelligence().getBigCategoryKey().equals("technology_trend"))){
            tv_label.setVisibility(View.GONE);
        }
        tv_label.setText(label);
        TextView tv_date = holder.getView(R.id.tv_date);
        if(item.getCreatedDate()!=null){
            tv_date.setText(item.getCreatedDate());
        }
        TextView tv_looked = holder.getView(R.id.tv_looked);
        tv_looked.setText(item.getViewedCount()+"");
//        Drawable drawableLeft = null;
//        drawableLeft = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.viewed);
//        ColorMatrix cm = new ColorMatrix();
//        cm.setSaturation(0); // 设置饱和度
//        ColorMatrixColorFilter grayColorFilter = new ColorMatrixColorFilter(cm);
//        drawableLeft.setColorFilter(grayColorFilter); // 如果想恢复彩色显示，设置为null即可
//        tv_looked.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
//        tv_looked.setCompoundDrawablePadding(Res.Dip2Pix(8));
        List<Long> idList=new ArrayList<>();
        idList.add(item.getId());
        dyViewBean=new DyViewBean();
        dyViewBean.setFkIds(idList);
        dyViewBean.setFkType("INTELLIGENCE_ITEM");
        runnableTask();
    }

    private DynamicIntelligenceAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(DynamicIntelligenceAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, DynamicIntelligenceAdapter receiveAddress, View view);
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<DyInteligenceBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }
    public void refresh(List<DyInteligenceBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
    public void clear(){
        getData().clear();
        notifyDataSetChanged();
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

}
