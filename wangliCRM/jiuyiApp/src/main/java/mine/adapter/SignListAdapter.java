package mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.jiuyi.app.GlideApp;
import com.tencent.qcloud.sdk.Constant;
import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.TaskBean;
import customer.activity.ImageBrowseActivity;
import customer.entity.AttachmentBean;
import customer.entity.UserViewInfo;
import mine.bean.MineSignListBean;


public class SignListAdapter extends BaseQuickAdapter<MineSignListBean.ContentBean,BaseViewHolder>  {

    public SignListAdapter(int layoutResId, List<MineSignListBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final MineSignListBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        holder.setIsRecyclable(false);
        TextView tv_date = holder.getView(R.id.tv_date);
        String sDate="";
        if(!Func.IsStringEmpty(item.getSignInDate())){
            sDate+=item.getSignInDate();
        }
        if(item.getSignType()!=null){
            sDate+="("+item.getSignType().getValue()+")";
        }
        if(!Func.IsStringEmpty(sDate)){
            tv_date.setText(sDate);
        }
        TextView tv_address = holder.getView(R.id.tv_address);
        if(item.getAddress()!=null){
            tv_address.setText(item.getAddress());
        }
        TextView tv_remark = holder.getView(R.id.tv_remark);
        if(item.getRemark()!=null){
            tv_remark.setText(item.getRemark());
        }
        NineGridImageView noScrollgridview=holder.getView(R.id.noScrollgridview);
        if(item.getAttachments()!=null && item.getAttachments().size()>0){
            List<UserViewInfo> imgUrls = new ArrayList<>();
            for(int i=0;i<item.getAttachments().size();i++){
                UserViewInfo userViewInfo;
                String url="";
                AttachmentBean attachmentBean=item.getAttachments().get(i);
                if(attachmentBean.getQiniuKey()!=null && (attachmentBean.getFileType().toLowerCase().equals("jpg")||attachmentBean.getFileType().toLowerCase().equals("png")||attachmentBean.getFileType().toLowerCase().equals("jpeg"))){
                    userViewInfo=new UserViewInfo(item.getId(), Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey(),Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    imgUrls.add(userViewInfo);
                }

            }
            noScrollgridview.setAdapter(mAdapter);
            noScrollgridview.setImagesData(imgUrls);

        }

    }

    private SignListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(SignListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, SignListAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<MineSignListBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<MineSignListBean.ContentBean> newList) {
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
