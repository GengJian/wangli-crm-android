package customer.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bobomee.android.mentions.edit.MentionEditText;
import com.common.GsonUtil;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.wanglicrm.android.R;
import com.dalong.recordlib.RecordVideoActivity;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiMainApplication;
import com.nanchen.compresshelper.CompressHelper;
import com.recyclerview.swipe.Closeable;
import com.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.recyclerview.swipe.SwipeMenu;
import com.recyclerview.swipe.SwipeMenuCreator;
import com.recyclerview.swipe.SwipeMenuItem;
import com.recyclerview.swipe.SwipeMenuRecyclerView;
import com.tencent.qcloud.sdk.Constant;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import customer.Utils.BitmapUtils;
import customer.Utils.CommonUtils;
import customer.Utils.FastUtils;
import customer.Utils.FileUtils;
import customer.Utils.ViewOperatorType;
import customer.adapter.IntelligenceDetailPictureAdapter;
import customer.adapter.IntelligenceDetailVideoAdapter;
import customer.adapter.IntelligenceDetailVoiceAdapter;
import customer.adapter.NewInfomationDetailMutiAdapter;
import customer.adapter.NewNeedPlanMutiAdapter;
import customer.adapter.VisitItemListAdapter;
import customer.entity.AttachmentBean;
import customer.entity.BatchTransBean;
import customer.entity.ImageBean;
import customer.entity.Media;
import customer.entity.MemberUpdateBean;
import customer.entity.PersonSelectBean;
import customer.entity.User;
import customer.entity.VisitActivityListBean;
import customer.entity.VisitIntelligenceBean;
import customer.listener.OnItemClickListener;
import customer.listener.PickerConfig;
import customer.view.DrawableTextView;
import customer.view.JiuyiCommunicationBar;
import customer.view.SelectPicPopupWindow;
import customer.view.SelectVideoPopupWindow;
import customer.view.SelectVoicePopupWindow;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.Utils.StringUtils;

import static customer.Utils.ViewOperatorType.EDIT;
import static customer.activity.JiuyiCustomerInfomationActivity.SELECT_PICTURE;
import static customer.activity.JiuyiCustomerInfomationActivity.SELECT_Video;
import static customer.activity.JiuyiCustomerInfomationActivity.TAKE_PICTURE;
import static customer.activity.JiuyiCustomerInfomationActivity.TAKE_Video;
import static customer.adapter.NewSpecialProductAdapter.VIEW_TYPE_MENU_DELETE;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCommunicationRecordNewActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 新建沟通界面
 *****************************************************************
 */
public class JiuyiCommunicationRecordNewActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private String operatorType, sTitle, s_returnvalue;
    private long customerid = -1;
    private ImageView iv_back;
    private RelativeLayout rvNew;
    private JiuyiItemGroup igClient;
    private MentionEditText edtContent;
    private RecyclerView rv_piclist;
    private RecyclerView rv_videolist;
    private RecyclerView rv_voicelist;
    private JiuyiCommunicationBar cobBar;
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private NewInfomationDetailMutiAdapter menuAdapter;
    List<BatchTransBean> mViewTypeBeanList;
    private VisitActivityListBean visitActivityListBean;
    private List<PersonSelectBean> mPersonBeanSelectList;
    IntelligenceDetailVideoAdapter intelligenceDetailVideoAdapter;
    IntelligenceDetailPictureAdapter intelligenceDetailPictureAdapter;
    IntelligenceDetailVoiceAdapter intelligenceDetailVoiceAdapter;
    private DrawableTextView tv_voice;
    private DrawableTextView tvAt;
    private DrawableTextView tvPicture;
    private DrawableTextView tvVideo;
    protected String filePath, videoPath;
    private String oriContent = "";
    private File[]  filesTotal;
    private ArrayList<File>  fileArrayList;
    private String extData="";
    protected SelectVideoPopupWindow menuVideoWindow;
    protected SelectVoicePopupWindow menuVoiceWindow;
    protected SelectPicPopupWindow menuWindow;
    private static final int PERSON_CODE = 800;
    private List<AttachmentBean> attachmentsBeanslist,attachmentsEditBeanslist;
    private ArrayList<Media> picMediaList,videoMediaList,voiceMediaList;
    private String sContent="";
    private List<VisitIntelligenceBean> visitIntelligenceBeanList;

    private  File[] files;
    private Boolean needUpload=false;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_communicationrecordnew_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        visitActivityListBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        sContent = mBundle.getString(JiuyiBundleKey.PARAM_COMMONNAME);
        if(visitActivityListBean!=null &&visitActivityListBean.getMember()!=null ){
            customerid=visitActivityListBean.getMember().getId();
        }
        operatorType = mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        sTitle = mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if (Func.IsStringEmpty(sTitle)) {
            sTitle = "";
        }

        setTitle();
        iv_back = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                    fileArrayList=new ArrayList<>();
                    extData="";
                    attachmentsEditBeanslist=new ArrayList<>();
                    if(intelligenceDetailPictureAdapter!=null &&intelligenceDetailPictureAdapter.getMviewBeanList().size()>0){
                        for(int i=0;i<intelligenceDetailPictureAdapter.getMviewBeanList().size();i++){
                            Media media=intelligenceDetailPictureAdapter.getMviewBeanList().get(i);
                            if (media.getPath() != null ) {
                                File file1 = new File(media.getPath());
                                fileArrayList.add(file1);
                                extData+=",";
                                needUpload=true;
                            }else{
                                if(media.getUrl()!=null && visitActivityListBean!=null && visitActivityListBean.getAttachmentList()!=null && visitActivityListBean.getAttachmentList().size()>0  ){
                                    for(int j=0;j<visitActivityListBean.getAttachmentList().size();j++){
                                        AttachmentBean attachmentBean=visitActivityListBean.getAttachmentList().get(j);
                                        if(attachmentBean.getQiniuKey()!=null){
                                            String qiuniukey=media.getQiniuKey();
                                            if( qiuniukey!=null && qiuniukey.equals(attachmentBean.getQiniuKey())){
                                                attachmentsEditBeanslist.add(attachmentBean);
                                            }
                                        }
                                    }

                                }


                            }
                        }
                    }
                    if(intelligenceDetailVoiceAdapter!=null &&intelligenceDetailVoiceAdapter.getMviewBeanList().size()>0){
                        for(int i=0;i<intelligenceDetailVoiceAdapter.getMviewBeanList().size();i++){
                            Media media=intelligenceDetailVoiceAdapter.getMviewBeanList().get(i);
                            if (media.getPath() != null ) {
                                File file1 = new File(media.getPath());
                                fileArrayList.add(file1);
                                if(!Func.IsStringEmpty(media.getFileTime())){
                                    extData+=","+media.getFileTime();
                                }else{
                                    extData+=",";
                                }

                                needUpload=true;
                            }else{
                                if(media.getUrl()!=null && visitActivityListBean!=null && visitActivityListBean.getAttachmentList()!=null && visitActivityListBean.getAttachmentList().size()>0  ){
                                    for(int j=0;j<visitActivityListBean.getAttachmentList().size();j++){
                                        AttachmentBean attachmentBean=visitActivityListBean.getAttachmentList().get(j);
                                        if(attachmentBean.getQiniuKey()!=null){
                                            String qiuniukey=media.getQiniuKey();
                                            if( qiuniukey!=null && qiuniukey.equals(attachmentBean.getQiniuKey())){
                                                attachmentsEditBeanslist.add(attachmentBean);
                                            }
                                        }
                                    }

                                }


                            }
                        }

                    }
                    if(intelligenceDetailVideoAdapter!=null &&intelligenceDetailVideoAdapter.getMviewBeanList().size()>0){
                        for(int i=0;i<intelligenceDetailVideoAdapter.getMviewBeanList().size();i++){
                            Media media=intelligenceDetailVideoAdapter.getMviewBeanList().get(i);
                            if (media.getPath() != null ) {
                                File file1 = new File(media.getPath());
                                fileArrayList.add(file1);
                                extData+=",";
                                needUpload=true;
                            }else{
                                if(media.getUrl()!=null && visitActivityListBean!=null && visitActivityListBean.getAttachmentList()!=null && visitActivityListBean.getAttachmentList().size()>0  ){
                                    for(int j=0;j<visitActivityListBean.getAttachmentList().size();j++){
                                        AttachmentBean attachmentBean=visitActivityListBean.getAttachmentList().get(j);
                                        if(attachmentBean.getQiniuKey()!=null){
                                            String qiuniukey=media.getQiniuKey();
                                            if( qiuniukey!=null && qiuniukey.equals(attachmentBean.getQiniuKey())){
                                                attachmentsEditBeanslist.add(attachmentBean);
                                            }
                                        }
                                    }

                                }


                            }
                        }
                    }
                    if(needUpload){
                        if(fileArrayList!=null && fileArrayList.size()>0){
                            filesTotal=new File[fileArrayList.size()];
                            for(int i=0;i<fileArrayList.size();i++){
                                filesTotal[i]=fileArrayList.get(i);
                            }
                            upload(filesTotal);
                        }

                    }else{
                        updateInfo();
                    }
                }
            });

        }
        rvNew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_new);
        rvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCustomerInfomationActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_TITLE,"新情报");
                intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN2,visitActivityListBean);
                startActivityForResult(intent, 1);

            }
        });
        tvAt = (DrawableTextView) mBodyLayout.findViewById(R.id.tv_at);
        tvAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FastUtils.isFastClick()) {
                    Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
                    intent.putExtras(mBundle);
                    startActivityForResult(intent, 200);
                }
            }
        });
        tv_voice = (DrawableTextView) mBodyLayout.findViewById(R.id.tv_voice);
        tv_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intelligenceDetailVoiceAdapter!=null && intelligenceDetailVoiceAdapter.getItemCount()>=9){
                    Toast.makeText(JiuyiCommunicationRecordNewActivity.this, "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (FastUtils.isFastClick()) {
                    selectVoice();
                }
            }
        });
        tvPicture = (DrawableTextView) mBodyLayout.findViewById(R.id.tv_picture);
        tvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intelligenceDetailPictureAdapter!=null && intelligenceDetailPictureAdapter.getItemCount()>=9){
                    Toast.makeText(JiuyiCommunicationRecordNewActivity.this, "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (FastUtils.isFastClick()) {
                    selectImgs();
                }
            }
        });
        tvVideo = (DrawableTextView) mBodyLayout.findViewById(R.id.tv_video);
        tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intelligenceDetailVideoAdapter!=null && intelligenceDetailVideoAdapter.getItemCount()>=PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT){
                    Toast.makeText(JiuyiCommunicationRecordNewActivity.this, "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (FastUtils.isFastClick()) {
                    selectVideos();
                }
            }
        });


        igClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.ig_client);
        if (visitActivityListBean.getMember() != null) {
            igClient.setText(visitActivityListBean.getMember().getOrgName());
            igClient.setEnabled(false);
            igClient.setClickable(false);
        }

        edtContent = (MentionEditText) mBodyLayout.findViewById(R.id.edt_content);
        edtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1 && !TextUtils.isEmpty(s)) {
                    char mentionChar = s.toString().charAt(start);
                    int selectionStart = edtContent.getSelectionStart();
                    if (mentionChar == '@') {
                        Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
                        intent.putExtras(mBundle);
                        startActivityForResult(intent, 200);
                        edtContent.getText().delete(selectionStart - 1, selectionStart);
                    }
                }
            }

            @Override public void afterTextChanged(Editable s) {
            }
        });
        if(sContent!=null){
            StringUtils.insertSelectedContent(JiuyiMainApplication.getIns(),edtContent,sContent);
        }

        rv_piclist = mBodyLayout.findViewById(R.id.rv_piclist);
        GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(), 5);
        rv_piclist.setNestedScrollingEnabled(false);
        rv_piclist.setLayoutManager(mgr);
        rv_piclist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));


        rv_videolist = mBodyLayout.findViewById(R.id.rv_videolist);
        GridLayoutManager mgr2 = new GridLayoutManager(JiuyiMainApplication.getIns(), 5);
        rv_videolist.setLayoutManager(mgr2);
        rv_videolist.setNestedScrollingEnabled(false);
        rv_videolist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));

        rv_voicelist = mBodyLayout.findViewById(R.id.rv_voicelist);
        GridLayoutManager mgr3 = new GridLayoutManager(JiuyiMainApplication.getIns(), 5);
        rv_voicelist.setLayoutManager(mgr3);
        rv_voicelist.setNestedScrollingEnabled(false);
        rv_voicelist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));

        picMediaList=new ArrayList<>();
        videoMediaList=new ArrayList<>();
        voiceMediaList=new ArrayList<>();
        if(visitActivityListBean.getAttachmentList()!=null && visitActivityListBean.getAttachmentList().size()>0){
            for(int i=0;i<visitActivityListBean.getAttachmentList().size();i++){
                AttachmentBean attachmentBean=visitActivityListBean.getAttachmentList().get(i);
                if(attachmentBean.getFileType().toLowerCase().equals("jpg")){
                    Media media=new Media();
                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    media.setExtension(".jpg");
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    media.setMediaType(0);
                    picMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp4")){
                    Media media=new Media();
                    media.setExtension(".mp4");
                    media.setName(attachmentBean.getFileName());
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    media.setMediaType(3);
                    media.setUrl(CommonUtils.getAttachUrl(attachmentBean));
                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    videoMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp3")){
                    Media media=new Media();
                    media.setExtension(".mp3");
                    media.setMediaType(2);
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    media.setFileTime(attachmentBean.getExtData());
                    media.setUrl(CommonUtils.getAttachUrl(attachmentBean));
                    voiceMediaList.add(media);
                }
            }

        }
        intelligenceDetailPictureAdapter = new IntelligenceDetailPictureAdapter(JiuyiMainApplication.getIns(), picMediaList);
        rv_piclist.setAdapter(intelligenceDetailPictureAdapter);
        intelligenceDetailPictureAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailPictureAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                intent.putExtra(PickerConfig.PRE_RAW_LIST, intelligenceDetailPictureAdapter.getMviewBeanList());
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT, 9);  //最大选择数量，默认40（非必填参数）
                intent.putExtra(PickerConfig.CURRENT_POSITION, position);  //最大选择数量，默认40（非必填参数）
                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 1500);
            }
        });
        intelligenceDetailPictureAdapter.setOnCLickItemListener(new IntelligenceDetailPictureAdapter.onCLickItemListener() {
            @Override
            public void click(int position, String colname, View view) {
                if (colname.equals("img_delete")) {
                    intelligenceDetailPictureAdapter.getMviewBeanList().remove(position);
                    intelligenceDetailPictureAdapter.notifyDataSetChanged();
                }

            }
        });

        intelligenceDetailVideoAdapter = new IntelligenceDetailVideoAdapter(JiuyiMainApplication.getIns(), videoMediaList);
        rv_videolist.setAdapter(intelligenceDetailVideoAdapter);
        intelligenceDetailVideoAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailVideoAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                intent.putExtra(PickerConfig.PRE_RAW_LIST, intelligenceDetailVideoAdapter.getMviewBeanList());
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT, PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT);  //最大选择数量，默认9（非必填参数）
                intent.putExtra(PickerConfig.CURRENT_POSITION, position);  //最大选择数量，默认40（非必填参数）
                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 200);
            }
        });
        intelligenceDetailVideoAdapter.setOnCLickItemListener(new IntelligenceDetailVideoAdapter.onCLickItemListener() {
            @Override
            public void click(int position, String colname, View view) {
                if (colname.equals("img_delete")) {
                    intelligenceDetailVideoAdapter.getMviewBeanList().remove(position);
                    intelligenceDetailVideoAdapter.notifyDataSetChanged();
                }

            }
        });



        intelligenceDetailVoiceAdapter = new IntelligenceDetailVoiceAdapter(JiuyiMainApplication.getIns(), voiceMediaList);
        rv_voicelist.setAdapter(intelligenceDetailVoiceAdapter);
        intelligenceDetailVoiceAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailVoiceAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Bundle mBundle=new Bundle();
                mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,intelligenceDetailVoiceAdapter.getMviewBeanList().get(position));
                changePage(mBundle,Pub.Customer_RecordPlay,true);
            }
        });
        intelligenceDetailVoiceAdapter.setOnCLickItemListener(new IntelligenceDetailVoiceAdapter.onCLickItemListener() {
            @Override
            public void click(int position, String colname, View view) {
                if (colname.equals("img_delete")) {
                    intelligenceDetailVoiceAdapter.getMviewBeanList().remove(position);
                    intelligenceDetailVoiceAdapter.notifyDataSetChanged();
                }

            }
        });
        cobBar = (JiuyiCommunicationBar) mBodyLayout.findViewById(R.id.cob_bar);

        swipeMenuRecyclerView = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.recycler_view);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(JiuyiCommunicationRecordNewActivity.this));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiCommunicationRecordNewActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        swipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        swipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        getVisitIntelligencerList();


    }

    @Override
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE, edtContent.getFormatCharSequence().toString());
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiCommunicationRecordNewActivity.this.setResult(200, intent);
    }

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if (nAction == DialogID.DialogTrue) {
            backPage();
        }
    }
    protected void selectVideos() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuVideoWindow = new SelectVideoPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuVideoWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected void selectVoice() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuVoiceWindow = new SelectVoicePopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuVoiceWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected void selectImgs() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuWindow = new SelectPicPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
            }
            if (menuVideoWindow != null) {
                menuVideoWindow.dismiss();
            }
            if (menuVoiceWindow != null) {
                menuVoiceWindow.dismiss();
            }

            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
            switch (v.getId()) {
                case com.wanglicrm.android.R.id.item_popupwindows_camera:        //点击拍照按钮
                    String[] list = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            goCamera();
                        }
                    });

                    break;
                case com.wanglicrm.android.R.id.item_popupwindows_Photo:       //点击从相册中选择按钮
                    String[] list4 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list4, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Intent intent =new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PickerActivity.class);
                            intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
                            intent.putExtra(PickerConfig.MAX_SELECT_COUNT,9);  //最大选择数量，默认40（非必填参数）
                            if(intelligenceDetailPictureAdapter!=null  && intelligenceDetailPictureAdapter.getMviewBeanList()!=null ){
                                ArrayList<Media> defaultSelect = intelligenceDetailPictureAdapter.getMviewBeanList();//可以设置默认选中的照片，比如把select刚刚选择的list设置成默认的。
                                intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,defaultSelect); //可以设置默认选中的照片(非必填参数)
                            }
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,SELECT_PICTURE);
                        }
                    });


                    break;
                case R.id.ll_sure:       //点击从相册中选择按钮
                    Intent intent =new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), JiuyiRecordActivity.class);
                    if(intelligenceDetailVoiceAdapter!=null  && intelligenceDetailVoiceAdapter.getMviewBeanList()!=null ){
                        ArrayList<Media> defaultSelect = intelligenceDetailVoiceAdapter.getMviewBeanList();//可以设置默认选中的照片，比如把select刚刚选择的list设置成默认的。
                        intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,defaultSelect); //可以设置默认选中的照片(非必填参数)
                    }
                    Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,150);
//                    Rc.getIns().getBaseCallTopCallBack().ch(null, Pub.Customer_RecordNew,true);
                    break;
                case R.id.item_popupwindows_video:        //点击录视频按钮
                    String[] list3 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list3, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            startRecordVideo();
                        }
                    });

                    break;
                case R.id.item_popupwindows_videocenter:       //点击从相册中选择按钮

                    String[] list5 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list5, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Intent intent =new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PickerActivity.class);
                            intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_VIDEO);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
                            long maxSize=188743680L;//long long long long类型
                            intent.putExtra(PickerConfig.MAX_SELECT_SIZE,PickerConfig.DEFAULT_SELECTED_MAX_SIZE); //最大选择大小，默认180M（非必填参数）
                            intent.putExtra(PickerConfig.MAX_SELECT_COUNT,PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT);  //最大选择数量，默认40（非必填参数）
                            if(intelligenceDetailVideoAdapter!=null  && intelligenceDetailVideoAdapter.getMviewBeanList()!=null ){
                                ArrayList<Media> defaultSelect = intelligenceDetailVideoAdapter.getMviewBeanList();//可以设置默认选中的照片，比如把select刚刚选择的list设置成默认的。
                                intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,defaultSelect); //可以设置默认选中的照片(非必填参数)
                            }
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,SELECT_Video);
                        }
                    });
                    break;
                default:
                    break;
            }
        }

    };
    protected void goCamera() {
        filePath = FileUtils.SDPATH + String.valueOf(System.currentTimeMillis())+"photo.jpg";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            File vDirPath = file1.getParentFile();
            vDirPath.mkdirs();
        }
        Uri uri = null;
        Rc.getIns().picVideoUrl=filePath;

        if (Build.VERSION.SDK_INT < 24) {
            // 从文件中创建uri
            uri = Uri.fromFile(file1);
        } else {
            //兼容android7.0 使用共享文件的形式
            uri = FileProvider.getUriForFile(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "com.wanglicrm.android.fileProvider", file1 );
        }
        // 启动Camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, TAKE_PICTURE);
    }

    public void startRecordVideo() {
        File path=new File(Environment.getExternalStorageDirectory(),
                "jiuyivideo");
        if (!path.exists()) {
            path.mkdirs();
        }
        videoPath=path.getAbsolutePath()+File.separator+System.currentTimeMillis()+".mp4";
        Rc.getIns().picVideoUrl=videoPath;
        Intent intent=new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), RecordVideoActivity.class);
        intent.putExtra(RecordVideoActivity.RECORD_VIDEO_PATH,videoPath);
        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,TAKE_Video);
    }

    public void setTitle() {
        if (!Func.IsStringEmpty(sTitle)) {
            mTitle = sTitle;
        } else {
            mTitle = "新建沟通记录";
        }
        setTitle(mTitle);
    }

    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (viewType == VIEW_TYPE_MENU_DELETE) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(JiuyiCommunicationRecordNewActivity.this)
                        .setBackgroundDrawable(R.drawable.tzt_red)
                        .setText("删除")
                        .setTextColor(Res.getColor(null, "jiuyi_white_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);

            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if (menuAdapter != null) {
                    VisitIntelligenceBean contentBean = menuAdapter.mViewTypeBeanList.get(adapterPosition);
                    if (contentBean != null) {
                        menuAdapter.mViewTypeBeanList.remove(adapterPosition);
                        deleteLinkIntelligence(contentBean.getId());
                        menuAdapter.notifyDataSetChanged();
                    }

                }
            }
        }
    };

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if (menuAdapter != null) {
                VisitIntelligenceBean contentBean = menuAdapter.mViewTypeBeanList.get(position);
                if (contentBean != null) {
                    Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCustomerInfomationActivity.class);
                    intent.putExtra(JiuyiBundleKey.PARAM_TITLE,"修改情报");
                    intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SINGLEEDIT);
                    intent.putExtra(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                    startActivityForResult(intent, 1);
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle = null;
        if((data == null || data.getExtras() == null)&& requestCode!=TAKE_PICTURE) {
            return;
        }
        switch (requestCode) {
            case 200:
                bundle = data.getExtras();
                long Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                String CustomerName = bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                if (Customerid > 0 && CustomerName != null) {
                    User user = new User(Customerid+"",CustomerName);
                    edtContent.insert(user);
                }
                break;
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK) { //
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  // 检测sd是否可用
                        Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    if (!Func.IsStringEmpty(Rc.getIns().picVideoUrl)) {
                        filePath = Rc.getIns().picVideoUrl;
                        Rc.getIns().picVideoUrl = "";
                    }
                    Bitmap bm = BitmapUtils.getCompressedBitmap(JiuyiCommunicationRecordNewActivity.this, filePath);
                    ImageBean takePhoto = new ImageBean();
                    takePhoto.setBitmap(bm);

                    //优化压缩图片
                    Uri uri = null;
                    File file1 = new File(filePath);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                    takePhoto.setPath(newFile.getPath());
                    ArrayList<Media> picList = intelligenceDetailPictureAdapter.getMviewBeanList();
                    Media media = new Media();
                    media.setExtension(".jpg");
                    media.setMediaType(0);
                    media.setPath(filePath);
                    picList.add(media);
                    intelligenceDetailPictureAdapter.setMviewBeanList(picList);
                    intelligenceDetailPictureAdapter.notifyDataSetChanged();
                }
                break;
            case SELECT_PICTURE:
                ArrayList<Media> selects = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                intelligenceDetailPictureAdapter.setMviewBeanList(selects);
                intelligenceDetailPictureAdapter.notifyDataSetChanged();
                break;
            case TAKE_Video:
                if (!Func.IsStringEmpty(videoPath)) {
                    ArrayList<Media> picList = intelligenceDetailVideoAdapter.getMviewBeanList();
                    Media media = new Media();
                    media.setPath(videoPath);
                    media.setMediaType(3);
                    media.setExtension(".mp4");
                    picList.add(media);
                    intelligenceDetailVideoAdapter.setMviewBeanList(picList);
                    intelligenceDetailVideoAdapter.notifyDataSetChanged();
                }
                break;
            case SELECT_Video:
                ArrayList<Media> selects2 = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                intelligenceDetailVideoAdapter.setMviewBeanList(selects2);
                intelligenceDetailVideoAdapter.notifyDataSetChanged();
                break;
            case 150:
                ArrayList<Media> selectsVoice = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                intelligenceDetailVoiceAdapter.setMviewBeanList(selectsVoice);
                intelligenceDetailVoiceAdapter.notifyDataSetChanged();
                break;
            case 1:
                getVisitIntelligencerList();
                break;
            default:
                break;
        }
    }
    public void SetTextChanged(final MentionEditText edittext) {
        edittext.addTextChangedListener(new TextWatcher() {
            private int beforlong,bhlong;
            private CharSequence temp;
            private int editStart;
            private int editEnd;
            private String delText;
            private String beforString="";
            private boolean bDeling=false;

            @Override
            public void afterTextChanged(Editable s) {
                bhlong = s.toString().length();
                editStart = edittext.getSelectionStart();
                editEnd = edittext.getSelectionEnd();
                int pos=-1;

                if (beforlong>bhlong && !bDeling && editStart>=0){//判断是否是清除状态
                    delText=beforString.substring(editStart,editStart+1);
                    if(delText.contains("#")||delText.contains("@")||delText.contains("$")){
                        pos=s.toString().lastIndexOf(delText);
                        editStart=pos;
                        bDeling=true;
                        s.delete(editStart, editEnd);
                        int tempSelection = editStart;
                        edittext.setText(s);
                        edittext.setSelection(tempSelection);
                    }
                }
                bDeling=false;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforlong = s.toString().length();
                temp = s;
                beforString= s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    public void updateInfo(){
        HashMap beanMap= new HashMap<String, Object>();
        if(visitActivityListBean.getMember()!=null){
            beanMap.put("id",visitActivityListBean.getId());
            if(attachmentsEditBeanslist!=null && attachmentsEditBeanslist.size()>0){
                beanMap.put("attachmentList",attachmentsEditBeanslist);
            }
            beanMap.put("communicateRecord",edtContent.getFormatCharSequence().toString());
        }

        JiuyiHttp.PUT("visit-activity/update-communicateRecord-mobile")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(beanMap))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressLoadingDialog!=null){
                            progressLoadingDialog.dismiss();
                        }
                        Toast.makeText(JiuyiCommunicationRecordNewActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
                        Rc.mBackfresh=true;
                        EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
                        setBackActivityBundle();
                        backPage();
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressLoadingDialog!=null){
                            progressLoadingDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);

                    }
                });
    }




    private void upload(File[] files)
    {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                if(!Func.IsStringEmpty(extData) && extData.length()>0){
                    extData= extData.substring(1,extData.length());
                }
                JiuyiHttp.UPLOAD("file/upload-multi")
                        .tag("upload-multi")
                        .addFiles2("file",files)
                        .addParam("extData",extData)
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<ArrayList<AttachmentBean>>() {
                            @Override
                            public void onSuccess(ArrayList<AttachmentBean> data) {
                                if(data!=null && data.size()>0){
                                    attachmentsBeanslist=data;
                                    for(int i=0;i<attachmentsBeanslist.size();i++){
                                        attachmentsEditBeanslist.add(attachmentsBeanslist.get(i));
                                    }
                                    updateInfo();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                if(progressDialog!=null){
                                    progressDialog.dismiss();
                                }
                                startDialog(DialogID.DialogTrue, "", "附件上传失败！", JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });
            }
        };

        thread.start();
    }


    public  void  getVisitIntelligencerList(){
        JiuyiHttp.GET("link-intelligence-item/list-fkid-fktype/"+visitActivityListBean.getId()+"/VISIT_ACTIVITY")
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<List<VisitIntelligenceBean>>() {
                    @Override
                    public void onSuccess(List<VisitIntelligenceBean> data) {
                        if(data!=null && data.size()>0){
                            visitIntelligenceBeanList=data;
                            for(int i=0;i<visitIntelligenceBeanList.size();i++){
                                visitIntelligenceBeanList.get(i).setViewType(VIEW_TYPE_MENU_DELETE);
                            }
                            menuAdapter = new NewInfomationDetailMutiAdapter(visitIntelligenceBeanList);
                            swipeMenuRecyclerView.setAdapter(menuAdapter);
                            menuAdapter.setOnItemClickListener(onItemClickListener);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

    public void deleteLinkIntelligence(long id){
        JiuyiHttp.DELETE("link-intelligence-item/unlink/"+id+"/VISIT_ACTIVITY")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
//                            Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "取消收藏成功!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }





}
