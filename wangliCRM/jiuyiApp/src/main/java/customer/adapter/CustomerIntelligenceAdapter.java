package customer.adapter;

import android.view.View;
import android.widget.Toast;

import com.bobomee.android.mentions.edit.MentionEditText;
import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.tools.jiuyiViewUtil;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.List;

import customer.Utils.CommonUtils;
import customer.entity.AttachmentBean;
import customer.entity.MarketIntelligenceBean;
import customer.entity.Media;
import customer.view.JiuyiIntelligenceBar;
import dynamic.Utils.StringUtils;


public class CustomerIntelligenceAdapter extends BaseQuickAdapter<MarketIntelligenceBean.IntelligenceItemSetBean,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener  {
    private int currentPostion=-1;
    public JiuyiIntelligenceBar jiuyiIntelligenceBar;

    public ArrayList<Media> getPicMediaList() {
        return picMediaList;
    }

    public void setPicMediaList(ArrayList<Media> picMediaList) {
        this.picMediaList = picMediaList;
    }

    public ArrayList<Media> getVideoMediaList() {
        return videoMediaList;
    }

    public void setVideoMediaList(ArrayList<Media> videoMediaList) {
        this.videoMediaList = videoMediaList;
    }

    public ArrayList<Media> getVoiceMediaList() {
        return voiceMediaList;
    }

    public void setVoiceMediaList(ArrayList<Media> voiceMediaList) {
        this.voiceMediaList = voiceMediaList;
    }

    private ArrayList<Media> picMediaList,videoMediaList,voiceMediaList;

    public CustomerIntelligenceAdapter(int layoutResId, List<MarketIntelligenceBean.IntelligenceItemSetBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final MarketIntelligenceBean.IntelligenceItemSetBean item) {
        holder.setIsRecyclable(false);
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        currentPostion=holder.getLayoutPosition();
        holder.addOnClickListener(R.id.tv_at);
        holder.addOnClickListener(R.id.tv_voice);
        holder.addOnClickListener(R.id.tv_picture);
        holder.addOnClickListener(R.id.tv_video);
        jiuyiIntelligenceBar = holder.getView(R.id.cob_bar);
        jiuyiIntelligenceBar.setCurrentBarPosition(holder.getLayoutPosition());
        item.setPosition(holder.getLayoutPosition());
        jiuyiIntelligenceBar.setContentBean(item);
        picMediaList=new ArrayList<>();
        videoMediaList=new ArrayList<>();
        voiceMediaList=new ArrayList<>();
        if(item.getAttachmentList()!=null && item.getAttachmentList().size()>0){
            for(int i=0;i<item.getAttachmentList().size();i++){
                AttachmentBean attachmentBean=item.getAttachmentList().get(i);
                if(attachmentBean.getFileType().toLowerCase().equals("jpg")){
                    Media media=new Media();
                    if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                        media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                        media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    }else if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                        media.setPath(attachmentBean.getUrl());
                    }
                    media.setExtension(".jpg");
                    media.setMediaType(0);
                    if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    picMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp4")){
                    Media media=new Media();
                    media.setExtension(".mp4");
                    media.setName(attachmentBean.getFileName());
                    if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    media.setMediaType(3);
                    if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                        media.setUrl(CommonUtils.getAttachUrl(attachmentBean));
                        media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    }else if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                        media.setPath(attachmentBean.getUrl());
                    }
                    videoMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp3")){
                    Media media=new Media();
                    media.setExtension(".mp3");
                    media.setFileTime(attachmentBean.getExtData());
                    media.setMediaType(2);
                    if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                        media.setUrl(CommonUtils.getAttachUrl(attachmentBean));
//                        media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    }else if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                        media.setPath(attachmentBean.getUrl());
                    }
                    if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    if(!Func.IsStringEmpty(attachmentBean.getExtData())){
                        media.setFileTime(attachmentBean.getExtData());
                    }
                    voiceMediaList.add(media);
                }
            }

        }
        jiuyiIntelligenceBar.setPicMediaList(picMediaList);
        jiuyiIntelligenceBar.setVideoMediaList(videoMediaList);
        jiuyiIntelligenceBar.setVoiceMediaList(voiceMediaList);
        jiuyiIntelligenceBar.setAdapter();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        Toast.makeText(JiuyiMainApplication.getIns(), "确认删除", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(JiuyiMainApplication.getIns(), "确认删除2", Toast.LENGTH_SHORT).show();
    }


    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<MarketIntelligenceBean.IntelligenceItemSetBean> addList) {
        //增加数据
//        int position = getData().size();
        int position=0;
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<MarketIntelligenceBean.IntelligenceItemSetBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
}
