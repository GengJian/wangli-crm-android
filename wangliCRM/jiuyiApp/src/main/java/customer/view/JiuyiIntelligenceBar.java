package customer.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bobomee.android.mentions.edit.MentionEditText;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.List;

import customer.activity.JiuyiCustomerSelectActivity;
import customer.activity.PreviewActivity;
import customer.adapter.IntelligenceDetailPictureAdapter;
import customer.adapter.IntelligenceDetailVideoAdapter;
import customer.adapter.IntelligenceDetailVoiceAdapter;
import customer.entity.AttachmentBean;
import customer.entity.MarketIntelligenceBean;
import customer.entity.Media;
import customer.listener.PickerConfig;
import dynamic.Utils.StringUtils;
import lib.demo.spinner.MaterialSpinner;


/**
 * ****************************************************************
 * 文件名称:JiuyiAttachment.java
 * 作    者:Created by zhengss
 * 创建时间:2018/11/27 11:50
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/11/27 1.00 初始版本
 * ****************************************************************
 */

public class JiuyiIntelligenceBar extends FrameLayout implements View.OnClickListener {
    private LinearLayout itemGroupLayout; //组合控件的布局
    private ItemOnClickListener itemOnClickListener; //Item的点击事件
    public MentionEditText edtContent;

    public Boolean getRefreshData() {
        return refreshData;
    }

    public void setRefreshData(Boolean refreshData) {
        this.refreshData = refreshData;
    }

    private Boolean refreshData=false;

    public List<DictBean> getDictBeansList() {
        return dictBeansList;
    }

    public void setDictBeansList(List<DictBean> dictBeansList) {
        this.dictBeansList = dictBeansList;
    }

    public List<DictBean> getDictBeansList2() {
        return dictBeansList2;
    }

    public void setDictBeansList2(List<DictBean> dictBeansList2) {
        this.dictBeansList2 = dictBeansList2;
    }

    private List<DictBean> dictBeansList,dictBeansList2;

    public String getBigCategory() {
        return bigCategory;
    }

    public void setBigCategory(String bigCategory) {
        this.bigCategory = bigCategory;
    }

    private String bigCategory="";

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    private String businessType="";

    public String getIntelligenceTypeKey() {
        return intelligenceTypeKey;
    }

    public void setIntelligenceTypeKey(String intelligenceTypeKey) {
        this.intelligenceTypeKey = intelligenceTypeKey;
    }

    public String getIntelligenceTypeValue() {
        return intelligenceTypeValue;
    }

    public void setIntelligenceTypeValue(String intelligenceTypeValue) {
        this.intelligenceTypeValue = intelligenceTypeValue;
    }

    public String getIntelligenceInfoKey() {
        return intelligenceInfoKey;
    }

    public void setIntelligenceInfoKey(String intelligenceInfoKey) {
        this.intelligenceInfoKey = intelligenceInfoKey;
    }

    public String getIntelligenceInfoValue() {
        return intelligenceInfoValue;
    }

    public void setIntelligenceInfoValue(String intelligenceInfoValue) {
        this.intelligenceInfoValue = intelligenceInfoValue;
    }

    private String intelligenceTypeKey;
    private String intelligenceTypeValue;
    private String intelligenceInfoKey;
    private String intelligenceInfoValue;

    public int getCurrentBarPosition() {
        return currentBarPosition;
    }

    public void setCurrentBarPosition(int currentBarPosition) {
        this.currentBarPosition = currentBarPosition;
    }

    private int currentBarPosition =-1;

    public MarketIntelligenceBean.IntelligenceItemSetBean getContentBean() {
        return contentBean;
    }

    public void setContentBean(MarketIntelligenceBean.IntelligenceItemSetBean contentBean) {
        this.contentBean = contentBean;

        if(contentBean.getDictBeansList()!=null){
            ArrayList<String> dataInfo=new ArrayList<>() ;
            for (int j= 0; j < contentBean.getDictBeansList().size(); j++) {
                dataInfo.add(contentBean.getDictBeansList().get(j).getValue());
            }
            setSpinner1(dataInfo);
        }else{
            type1.setItems((Object) null);
        }
        dictBeansList=contentBean.getDictBeansList();
        if(contentBean.getDictBeansList2()!=null){
            ArrayList<String> dataInfo=new ArrayList<>() ;
            for (int j= 0; j < contentBean.getDictBeansList2().size(); j++) {
                dataInfo.add(contentBean.getDictBeansList2().get(j).getValue());
            }
            setSpinner2(dataInfo);
        }else{
            type2.setItems((Object) null);
        }
        dictBeansList2=contentBean.getDictBeansList2();
        if(contentBean.getIntelligenceInfoValue()==null){
            type1.setText("");

        }else{

            type1.setText(contentBean.getIntelligenceInfoValue());
        }
        if(contentBean.getIntelligenceTypeValue()==null){
            type2.setText("");

        }else{
            type2.setText(contentBean.getIntelligenceTypeValue());
        }
        if(!Func.IsStringEmpty(contentBean.getOrginalContent())){
            refreshData=true;
            StringUtils.insertSelectedContent(JiuyiMainApplication.getIns(),edtContent,contentBean.getOrginalContent());
        }else{
            edtContent.setText("");
            if(dictBeansList2!=null && dictBeansList2.size()>0 && dictBeansList2.get(0).getDesp()!=null){
                edtContent.setHint("请输入"+dictBeansList2.get(0).getDesp());
            }else {
                edtContent.setHint("请输入");
            }
        }
        if(contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0){
            picMediaList=new ArrayList<>();
            videoMediaList=new ArrayList<>();
            voiceMediaList=new ArrayList<>();
            for(int i=0;i<contentBean.getAttachmentList().size();i++){
                AttachmentBean attachmentBean=contentBean.getAttachmentList().get(i);
                if(attachmentBean.getFileType().toLowerCase().equals("jpg")){
                    Media media=new Media();
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                        media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    }else if(attachmentBean.getUrl()!=null){
                        media.setPath(attachmentBean.getUrl());
                    }
                    media.setExtension(".jpg");
                    media.setMediaType(0);
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    picMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp4")){
                    Media media=new Media();
                    media.setExtension(".mp4");
                    media.setMediaType(3);
                    media.setName(attachmentBean.getFileName());
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                        media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    }else if(attachmentBean.getUrl()!=null){
                        media.setPath(attachmentBean.getUrl());
                    }
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    videoMediaList.add(media);
                }else if (attachmentBean.getFileType().toLowerCase().equals("mp3")){
                    Media media=new Media();
                    media.setExtension(".mp3");
                    media.setFileTime(attachmentBean.getExtData());
                    media.setMediaType(2);
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                        media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    }else if(attachmentBean.getUrl()!=null){
                        media.setPath(attachmentBean.getUrl());
                    }
                    if(attachmentBean.getExtData()!=null){
                        media.setFileTime(attachmentBean.getExtData());
                    }
                    if(attachmentBean.getQiniuKey()!=null){
                        media.setQiniuKey(attachmentBean.getQiniuKey());
                    }
                    voiceMediaList.add(media);
                }
            }
            setAdapter();

        }else{
            picMediaList=new ArrayList<>();
            videoMediaList=new ArrayList<>();
            voiceMediaList=new ArrayList<>();
            setAdapter();
        }




    }

    private MarketIntelligenceBean.IntelligenceItemSetBean contentBean;

    /*图片适配器*/
    IntelligenceDetailVideoAdapter intelligenceDetailVideoAdapter;
    IntelligenceDetailPictureAdapter intelligenceDetailPictureAdapter;
    IntelligenceDetailVoiceAdapter intelligenceDetailVoiceAdapter;

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
    RecyclerView rv_piclist,rv_videolist,rv_voicelist;
    CheckBox ck_select;
    public MaterialSpinner type1,type2;



    public JiuyiIntelligenceBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public JiuyiIntelligenceBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public JiuyiIntelligenceBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }


    //初始化View
    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.jiuyi_intelligencebar_layout, null);
        itemGroupLayout = (LinearLayout) view.findViewById(R.id.item_group_layout);
        edtContent= (MentionEditText) view.findViewById(R.id.content_edt);
        edtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (count == 1 && !TextUtils.isEmpty(s)) {
//                    char mentionChar = s.toString().charAt(start);
//                    int selectionStart = edtContent.getSelectionStart();
//                    if (mentionChar == '@') {
//                        Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCustomerSelectActivity.class);
//                        Bundle mBundle = new Bundle();
//                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
//                        intent.putExtras(mBundle);
//                        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 300);
//                        edtContent.getText().delete(selectionStart - 1, selectionStart);
//                    }
//                }
            }

            @Override public void afterTextChanged(Editable s) {
                if(refreshData){
                    return;
                }
                contentBean.setOrginalContent(edtContent.getFormatCharSequence().toString());
            }
        });

//        SetTextChanged(edtContent);
        ck_select = (CheckBox)view.findViewById(R.id.ck_select);
        type1 = (MaterialSpinner)view.findViewById(R.id.type1);

        type1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int pos, long id, String item) {
                intelligenceInfoKey=dictBeansList.get(pos).getKey();
                intelligenceInfoValue=dictBeansList.get(pos).getValue();
                contentBean.setIntelligenceInfoKey(dictBeansList.get(pos).getKey());
                contentBean.setIntelligenceInfoValue(dictBeansList.get(pos).getValue());
                ArrayList<String> data2=new ArrayList<>() ;
                dictBeansList2= DictUtils.getDictLikeList("intelligence_type","%"+dictBeansList.get(pos).getKey()+"%");
                if(dictBeansList2!=null &&dictBeansList2.size()>0) {
                    for (int i = 0; i < dictBeansList2.size(); i++) {
                        data2.add(dictBeansList2.get(i).getValue());
                    }

                }
                type2.setItems(data2);
                if(data2!=null && data2.size()>0 ){
                    type2.setSelectedIndex(0);
                    contentBean.setIntelligenceTypeKey(dictBeansList2.get(0).getKey());
                    contentBean.setIntelligenceTypeValue(dictBeansList2.get(0).getValue());
                    if(dictBeansList2.get(0).getDesp()!=null){
                        edtContent.setHint("请输入"+dictBeansList2.get(0).getDesp());
                    }

                }

            }
        });

        type1.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                spinner.getSelectedIndex();
            }
        });

        type2 = (MaterialSpinner)view.findViewById(R.id.type2);
        type2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int pos, long id, String item) {
                intelligenceTypeKey=dictBeansList2.get(pos).getKey();
                intelligenceTypeValue=dictBeansList2.get(pos).getValue();
                contentBean.setIntelligenceTypeKey(dictBeansList2.get(pos).getKey());
                contentBean.setIntelligenceTypeValue(dictBeansList2.get(pos).getValue());
                if(dictBeansList2.get(pos).getDesp()!=null){
                    edtContent.setHint("请输入"+dictBeansList2.get(pos).getDesp());
                }
            }
        });

        type2.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                spinner.getSelectedIndex();
            }
        });

        rv_piclist = view.findViewById(R.id.rv_piclist);
        GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(),5);
        rv_piclist.setLayoutManager(mgr);
        rv_piclist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));


        rv_videolist = view.findViewById(R.id.rv_videolist);
        GridLayoutManager mgr2 = new GridLayoutManager(JiuyiMainApplication.getIns(),5);
        rv_videolist.setLayoutManager(mgr2);
        rv_videolist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));

        rv_voicelist = view.findViewById(R.id.rv_voicelist);
        GridLayoutManager mgr3 = new GridLayoutManager(JiuyiMainApplication.getIns(), 5);
        rv_voicelist.setLayoutManager(mgr3);
        rv_voicelist.setNestedScrollingEnabled(false);
        rv_voicelist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));


        addView(view); //把自定义的这个组合控件的布局加入到当前FramLayout

        itemGroupLayout.setOnClickListener(this);
    }



    /**
     * 初始化相关属性，引入相关属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        //通过obtainStyledAttributes方法获取到一个TypedArray对象，然后通过TypedArray对象就可以获取到相对应定义的属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemGroup);
        float paddingLeft = typedArray.getDimension(R.styleable.ItemGroup_paddingLeft, 15);
        float paddingRight = typedArray.getDimension(R.styleable.ItemGroup_paddingRight, 15);
        float paddingTop = typedArray.getDimension(R.styleable.ItemGroup_paddingTop, 5);
        float paddingBottom = typedArray.getDimension(R.styleable.ItemGroup_paddingTop, 5);
        typedArray.recycle();

        //设置数据
        //设置item的内边距
        itemGroupLayout.setPadding((int) paddingLeft, (int) paddingTop, (int) paddingRight, (int) paddingBottom);
    }

    //Item点击事件监听
    public interface ItemOnClickListener {
        void onItemClick(View v);
    }

    /**
     * 供外部调用的方法，设置Item的点击事件
     *
     * @param itemOnClickListener
     */
    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }



    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.item_group_layout) {//点击了Item的布局
            if (itemOnClickListener != null) {
                itemOnClickListener.onItemClick(this);
            }

        }
    }
    public void setAdapter(){
        intelligenceDetailPictureAdapter = new IntelligenceDetailPictureAdapter(JiuyiMainApplication.getIns(),picMediaList);
        rv_piclist.setAdapter(intelligenceDetailPictureAdapter);
        intelligenceDetailPictureAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailPictureAdapter.OnRecyclerViewItemListener(){
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                intent.putExtra(PickerConfig.PRE_RAW_LIST, picMediaList);
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT,PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT);  //最大选择数量，默认40（非必填参数）
                intent.putExtra(PickerConfig.CURRENT_POSITION,position);  //最大选择数量，默认40（非必填参数）
                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 1500);
            }
        });
        intelligenceDetailPictureAdapter.setOnCLickItemListener(new IntelligenceDetailPictureAdapter.onCLickItemListener() {
            @Override
            public void click(int position, String colname, View view) {
                if(colname.equals("img_delete")){
                    String sPath="",sUrl="";
                    sPath=picMediaList.get(position).getPath();
                    if(!Func.IsStringEmpty(sPath) && contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0 ){
                        for(int i=0;i<contentBean.getAttachmentList().size();i++){
                           if(contentBean.getAttachmentList().get(i).getUrl()!=null && contentBean.getAttachmentList().get(i).getUrl().equals(sPath)){
                               contentBean.getAttachmentList().remove(i);
                           }
                        }
                    }
                    sUrl=picMediaList.get(position).getUrl();
                   if(!Func.IsStringEmpty(sUrl) && sUrl.lastIndexOf("/")>0 && contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0){
                       sUrl=sUrl.substring(sUrl.lastIndexOf("/")+1,sUrl.length());
                       for(int i=0;i<contentBean.getAttachmentList().size();i++){
                           if(contentBean.getAttachmentList().get(i).getQiniuKey()!=null && contentBean.getAttachmentList().get(i).getQiniuKey().equals(sUrl)){
                               contentBean.getAttachmentList().remove(i);
                           }
                       }
                   }
                    picMediaList.remove(position);

                    intelligenceDetailPictureAdapter.notifyDataSetChanged();
                }

            }
        });

        intelligenceDetailVideoAdapter = new IntelligenceDetailVideoAdapter(JiuyiMainApplication.getIns(),videoMediaList);
        rv_videolist.setAdapter(intelligenceDetailVideoAdapter);
        intelligenceDetailVideoAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailVideoAdapter.OnRecyclerViewItemListener(){
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                intent.putExtra(PickerConfig.PRE_RAW_LIST, videoMediaList);
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT,PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT);  //最大选择数量，默认9（非必填参数）
                intent.putExtra(PickerConfig.CURRENT_POSITION,position);  //最大选择数量，默认40（非必填参数）
                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 200);
            }
        });
        intelligenceDetailVideoAdapter.setOnCLickItemListener(new IntelligenceDetailVideoAdapter.onCLickItemListener() {
            @Override
            public void click(int position, String colname, View view) {
                if(colname.equals("img_delete")){
//                    videoMediaList.remove(position);
//                    intelligenceDetailVideoAdapter.notifyDataSetChanged();
                    String sPath="",sUrl="";
                    sPath=videoMediaList.get(position).getPath();
                    if(!Func.IsStringEmpty(sPath) && contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0 ){
                        for(int i=0;i<contentBean.getAttachmentList().size();i++){
                            if(contentBean.getAttachmentList().get(i).getUrl()!=null && contentBean.getAttachmentList().get(i).getUrl().equals(sPath)){
                                contentBean.getAttachmentList().remove(i);
                            }
                        }
                    }
                    sUrl=videoMediaList.get(position).getUrl();
                    if(!Func.IsStringEmpty(sUrl)  && contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0){
                        sUrl=videoMediaList.get(position).getQiniuKey();
                        for(int i=0;i<contentBean.getAttachmentList().size();i++){
                            if(contentBean.getAttachmentList().get(i).getQiniuKey()!=null &&sUrl!=null  && contentBean.getAttachmentList().get(i).getQiniuKey().equals(sUrl)){
                                contentBean.getAttachmentList().remove(i);
                            }
                        }
                    }
                    videoMediaList.remove(position);
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
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_RecordPlay,true);
            }
        });
        intelligenceDetailVoiceAdapter.setOnCLickItemListener(new IntelligenceDetailVoiceAdapter.onCLickItemListener() {
            @Override
            public void click(int position, String colname, View view) {
                if (colname.equals("img_delete")) {
                    String sPath="",sUrl="";
                    sPath=voiceMediaList.get(position).getPath();
                    if(!Func.IsStringEmpty(sPath) && contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0 ){
                        for(int i=0;i<contentBean.getAttachmentList().size();i++){
                            if(contentBean.getAttachmentList().get(i).getUrl()!=null && contentBean.getAttachmentList().get(i).getUrl().equals(sPath)){
                                contentBean.getAttachmentList().remove(i);
                            }
                        }
                    }
                    sUrl=voiceMediaList.get(position).getUrl();
                    if(!Func.IsStringEmpty(sUrl)  && contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0){
                        sUrl=voiceMediaList.get(position).getQiniuKey();
                        for(int i=0;i<contentBean.getAttachmentList().size();i++){
                            if(contentBean.getAttachmentList().get(i).getQiniuKey()!=null  && sUrl!=null && contentBean.getAttachmentList().get(i).getQiniuKey().equals(sUrl)){
                                contentBean.getAttachmentList().remove(i);
                            }
                        }
                    }
                    voiceMediaList.remove(position);
                    intelligenceDetailVoiceAdapter.notifyDataSetChanged();
                }

            }
        });
        ck_select.setChecked(contentBean.isSelect);
        ck_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    contentBean.setSelect(true);
                }else{
                    contentBean.setSelect(false);
                }
            }
        });
    }
    public void setSpinner1(ArrayList data) {
        type1.setItems(data);
        type1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int pos, long id, String item) {
                intelligenceInfoKey = dictBeansList.get(pos).getKey();
                intelligenceInfoValue = dictBeansList.get(pos).getValue();
                contentBean.setIntelligenceInfoKey(dictBeansList.get(pos).getKey());
                contentBean.setIntelligenceInfoValue(dictBeansList.get(pos).getValue());
                ArrayList<String> data2 = new ArrayList<>();
                dictBeansList2 = DictUtils.getDictLikeList("intelligence_type", "%" + dictBeansList.get(pos).getKey() + "%");
                if (dictBeansList2 != null && dictBeansList2.size() > 0) {
                    for (int i = 0; i < dictBeansList2.size(); i++) {
                        data2.add(dictBeansList2.get(i).getValue());
                    }

                }
                type2.setItems(data2);
                if (data2 != null && data2.size() > 0) {
                    type2.setSelectedIndex(0);
                    contentBean.setIntelligenceTypeKey(dictBeansList2.get(0).getKey());
                    contentBean.setIntelligenceTypeValue(dictBeansList2.get(0).getValue());
                    if (dictBeansList2.get(0).getDesp() != null) {
                        edtContent.setHint("请输入" + dictBeansList2.get(0).getDesp());
                    }

                }

            }
        });
    }
    public void setSpinner2(ArrayList data2){
        type2.setItems(data2);
        type2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int pos, long id, String item) {
                if(dictBeansList2!=null && dictBeansList2.size()>0){
                    intelligenceTypeKey=dictBeansList2.get(pos).getKey();
                    intelligenceTypeValue=dictBeansList2.get(pos).getValue();
                    contentBean.setIntelligenceTypeKey(dictBeansList2.get(pos).getKey());
                    contentBean.setIntelligenceTypeValue(dictBeansList2.get(pos).getValue());
                    if(dictBeansList2.get(pos).getDesp()!=null){
                        edtContent.setHint("请输入"+dictBeansList2.get(pos).getDesp());
                    }
                }
            }
        });
    }



}
