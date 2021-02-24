package com.jiuyi.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.common.GsonUtil;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.recyclerView.BaseMultiItemQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.google.gson.Gson;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.model.DictBean;
import com.jiuyi.model.TripStandardBean;
import com.jiuyi.tools.DictUtils;
import com.wanglicrm.android.R;
import com.google.gson.reflect.TypeToken;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.model.CommonFormBean;
import com.jiuyi.model.DictResultBean;
import com.tencent.qcloud.sdk.Constant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.NormalOperatorBean;
import customer.Utils.FastUtils;
import customer.Utils.FileUtils;
import customer.Utils.ViewOperatorType;
import customer.activity.PickerActivity;
import customer.activity.PreviewActivity;
import customer.adapter.CommonPictureAdapter;
import customer.entity.AttachmentBean;
import customer.entity.BrandBean;
import customer.entity.CustomerStoreInfoBean;
import customer.entity.DepartmentBean;
import customer.entity.Media;
import customer.entity.MemberBeanID;
import customer.entity.ProvinceBean;
import customer.listener.PickerConfig;
import customer.view.Bimp;
import customer.view.SelectPicPopupWindow;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.Utils.DynamicConstant;

import static com.vise.utils.handler.HandlerUtil.runOnUiThread;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCommonFormAdapter
 * 作       者 : zhengss
 * 创建时间:2018/12/03 14:43
 * 文件描述 : 通用新增页面适配器
 *****************************************************************
 */

public class JiuyiCommonFormAdapter extends BaseMultiItemQuickAdapter<CommonFormBean, BaseViewHolder> {
    private LinearLayout itemGroupLayout; //组合控件的布局
    private TextView tvTitle,tvValue,tv_attchment_title; //标题
    public JiuyiEditTextField contentEdt; //输入框
    private ImageView jtRightIv; //向右的箭头
    private TextView tvCount; //附件数量
    protected RecyclerView noScrollGridView;
    /*图片适配器*/
    public CommonPictureAdapter adapter;

    public MemberBeanID getMemberBeanID() {
        return memberBeanID;
    }

    public void setMemberBeanID(MemberBeanID memberBeanID) {
        this.memberBeanID = memberBeanID;
    }

    private MemberBeanID memberBeanID;

//    /*图片适配器*/
//    public PictureAdapter adapter;
    protected SelectPicPopupWindow menuWindow;
    protected String filePath;
    private ToggleButton toggleButton;
    Handler handler = new Handler();

    public String getViewOperatorType() {
        return viewOperatorType;
    }

    public void setViewOperatorType(String viewOperatorType) {
        this.viewOperatorType = viewOperatorType;
    }

    private String viewOperatorType="";

    public ArrayList<AttachmentBean> getAttachmentBeanArrayList() {
        return attachmentBeanArrayList;
    }

    public void setAttachmentBeanArrayList(ArrayList<AttachmentBean> attachmentBeanArrayList) {
        this.attachmentBeanArrayList = attachmentBeanArrayList;
    }

    private ArrayList<AttachmentBean> attachmentBeanArrayList=new ArrayList<>();

    public ArrayList<Media> getMediaArrayList() {
        return mediaArrayList;
    }

    public void setMediaArrayList(ArrayList<Media> mediaArrayList) {
        this.mediaArrayList = mediaArrayList;
    }

    private ArrayList<Media> mediaArrayList=new ArrayList<>();

    protected static final int TAKE_PICTURE = 1000;
    protected static final int SELECT_PICTURE = 1500;

    public JiuyiCommonFormAdapter(List data) {
        super(data);
        addItemType(CommonFormBean.IT_INPUT_TEXT, R.layout.jiuyi_item_group_layout);
        addItemType(CommonFormBean.IT_BIG_INPUT, R.layout.jiuyi_item_bigtext_group_layout);
        addItemType(CommonFormBean.IT_FILE_INPUT, R.layout.jiuyi_attachment_layout);
        addItemType(CommonFormBean.IT_TITLE_INPUT, R.layout.jiuyi_title_group_layout);
        addItemType(CommonFormBean.IT_TOGGLEBUTTON_INPUT, R.layout.jiuyi_toggle_group_layout);
    }

    @Override
    protected void convert(BaseViewHolder holder, CommonFormBean item) {
//        holder.setIsRecyclable(false);
        switch (holder.getItemViewType()) {
            case CommonFormBean.IT_INPUT_TEXT:
                itemGroupLayout = holder.getView(R.id.item_group_layout);
                tvTitle = holder.getView(R.id.tv_title);
                tvValue= holder.getView(R.id.tv_value);
                tvTitle.setText(item.getLeftContent());
                contentEdt = holder.getView(R.id.content_edt);
                jtRightIv = holder.getView(R.id.jt_right_iv);
                contentEdt.addTextChangedListener(new MyTextWatcher(holder));
                if(item.getKey().equals("btcc")){
                    String sContent="贴发票标准";
                    SpannableString ss = new SpannableString(sContent);
                    ss.setSpan(new ClickableSpan() {
                        //在这里定义点击下划线文字的点击事件，不一定非要打开浏览器
                        @Override
                        public void onClick(View widget) {
                            //下面是打开系统默认浏览器的方法
                            Bundle mBundle=new Bundle();
                            String title="";
                            title=sContent;
                            if(title.length()>14){
                                title=title.substring(0,14)+"...";
                            }
                            String url="";
                            List<DictBean> dictBeansList= DictUtils.getDictKeyList( "invoice_standard","invoice_standard_key");
                            if(dictBeansList!=null &&dictBeansList.size()>0){
                                DictBean dictBean=dictBeansList.get(0);
                                if(dictBean!=null && dictBean.getValue()!=null){
                                    url=dictBean.getValue().toString();
                                }else{
                                    url="http://img.jiuyisoft.com/fpgf.pptx";
                                }

                            }else{
                                url="http://img.jiuyisoft.com/fpgf.pptx";
                            }
                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE,title+".pptx");
                            mBundle.putString(JiuyiBundleKey.PARAM_FILETYPE, "pptx");
                            mBundle.putString(JiuyiBundleKey.PARAM_HTTPPDF, url);
                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10054,true);
                        }
                    },0,sContent.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(new ForegroundColorSpan( Color.parseColor("#336699")), 0, sContent.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    tvTitle.setMovementMethod( LinkMovementMethod.getInstance());
                    tvTitle.setText(ss);
                    tvValue.setVisibility(View.GONE);
                    jtRightIv.setVisibility(View.GONE);
                    contentEdt.setVisibility(View.GONE);
                    return;
                }
                if(item.getInputType()!=null){
                    if(item.getInputType().equals(CommonFormBean.DATE_SELECT)||item.getInputType().equals(CommonFormBean.INPUT_SELECT)
                            ||item.getInputType().equals(CommonFormBean.INPUT_MEMBER)
                            ||item.getInputType().equals(CommonFormBean.INPUT_DEPARTMENT)
                            ||item.getInputType().equals(CommonFormBean.INPUT_OPERATOR)
                            ||item.getInputType().equals(CommonFormBean.INPUT_BRAND_SELECT)){
                        tvValue.setVisibility(View.VISIBLE);
                        contentEdt.setVisibility(View.GONE);
                        jtRightIv.setVisibility(View.VISIBLE);
                        if(item.getInputType().equals(CommonFormBean.INPUT_MEMBER)){
                            if(memberBeanID!=null &&!item.getKey().equals("memberOfMember")&&!item.getKey().equals("supplier")  ){
                                item.setValue(memberBeanID);
                                tvValue.setText(memberBeanID.getOrgName());
                            }
                        }
                        if(item.getValue()!=null ){
                            if(item.getInputType().equals(CommonFormBean.INPUT_SELECT) ){
                                if(item.getKey().equals( "province" )){
                                    String jsonData = GsonUtil.gson().toJson(item.getValue());
                                    if(Func.isJsonObject(jsonData) ){
                                        Gson gs = new Gson();
                                        ProvinceBean.ContentBean bean = gs.fromJson(jsonData, ProvinceBean.ContentBean.class);//把JSON字符串转为对象
                                        if(bean!=null){
                                            item.setValue(bean);
                                            item.setRightContent(bean.getProvinceName());
                                            tvValue.setText(bean.getProvinceName());
                                        }
                                    }else{
                                        tvValue.setText(item.getRightContent());
                                    }
                                }else if(item.getKey().equals( "city" )){
                                    String jsonData = GsonUtil.gson().toJson(item.getValue());
                                    if(Func.isJsonObject(jsonData) ){
                                        Gson gs = new Gson();
                                        CustomerStoreInfoBean.ContentBean.CityBean bean = gs.fromJson(jsonData, CustomerStoreInfoBean.ContentBean.CityBean.class);//把JSON字符串转为对象
                                        if(bean!=null){
                                            item.setValue(bean);
                                            item.setRightContent(bean.getCityName());
                                            tvValue.setText(bean.getCityName());
                                        }
                                    }else{
                                        tvValue.setText(item.getRightContent());
                                    }
                                }else if(item.getKey().equals( "area" )){
                                    String jsonData = GsonUtil.gson().toJson(item.getValue());
                                    if(Func.isJsonObject(jsonData) ){
                                        Gson gs = new Gson();
                                        ProvinceBean.AreaBean bean = gs.fromJson(jsonData, ProvinceBean.AreaBean.class);//把JSON字符串转为对象
                                        if(bean!=null){
                                            item.setValue(bean);
                                            item.setRightContent(bean.getAreaName());
                                            tvValue.setText(bean.getAreaName());
                                        }
                                    }else{
                                        tvValue.setText(item.getRightContent());
                                    }
                                }else if(item.getKey().equals( "reimbursementCity" )){
                                    item.setValue(item.getValue().toString());
                                    item.setRightContent(item.getValue().toString());
                                    tvValue.setText(item.getValue().toString());

                                }else{
                                    if( !item.isMutiAble()){
                                        String jsonData = GsonUtil.gson().toJson(item.getValue());
                                        if(Func.isJsonObject(jsonData) ){
                                            Gson gs = new Gson();
                                            DictResultBean.ContentBean bean = gs.fromJson(jsonData, DictResultBean.ContentBean.class);//把JSON字符串转为对象
                                            if(bean!=null){
                                                item.setValue(bean.getKey());
                                                item.setRightContent(bean.getValue());
                                                tvValue.setText(bean.getValue());
                                            }
                                        }else{
                                            tvValue.setText(item.getRightContent());
                                        }
                                    }else{
                                        String jsonList= GsonUtil.gson().toJson(item.getValue());
                                        Gson gson1=new Gson();
                                        if(Func.isJsonArray(jsonList) ){
                                            List<DictResultBean.ContentBean> list= gson1.fromJson(jsonList, new TypeToken<List<DictResultBean.ContentBean>>() {}.getType());
                                            String name="";
                                            List<String> keylist=new ArrayList<>();
                                            if(list!=null && list.size()>0 ){
                                                for(int i=0;i<list.size();i++){
                                                    name+=list.get(i).getValue()+",";
                                                    keylist.add(list.get(i).getKey());
                                                }
                                            }
                                            item.setValue(keylist);
                                            if(!Func.IsStringEmpty(name)){
                                                tvValue.setText(name.substring(0,name.length()));
                                            }

                                        }
                                    }
                                }

                            }else if(item.getInputType().equals(CommonFormBean.DATE_SELECT)){
                                tvValue.setText(item.getValue().toString());
                            }else if(item.getInputType().equals(CommonFormBean.INPUT_MEMBER)){
                                String jsonData = GsonUtil.gson().toJson(item.getValue());
                                Gson gs = new Gson();
                                MemberBeanID bean = gs.fromJson(jsonData, MemberBeanID.class);//把JSON字符串转为对象
                                if(bean!=null){
                                    item.setValue(bean);
                                    tvValue.setText(bean.getOrgName());
                                }
                            }else if(item.getInputType().equals(CommonFormBean.INPUT_DEPARTMENT)){
                                String jsonData = GsonUtil.gson().toJson(item.getValue());
                                Gson gs = new Gson();
                                DepartmentBean.DepartmentBeanID bean = gs.fromJson(jsonData, DepartmentBean.DepartmentBeanID.class);//把JSON字符串转为对象
                                if(bean!=null){
                                    item.setValue(bean);
                                    tvValue.setText(bean.getName());
                                }
                            }else if(item.getInputType().equals(CommonFormBean.INPUT_OPERATOR)){
                                String jsonData = GsonUtil.gson().toJson(item.getValue());
                                Gson gs = new Gson();
                                if(Func.isJsonObject(jsonData) ){
                                    NormalOperatorBean.ContentBean bean = gs.fromJson(jsonData, NormalOperatorBean.ContentBean.class);//把JSON字符串转为对象
                                    if(bean!=null){
                                        item.setValue(bean);
                                        item.setSingleValue(bean.getOaCode());
                                        tvValue.setText(bean.getName());
                                    }
                                }else{
                                    item.setValue("无");
                                    item.setSingleValue(null);
                                    tvValue.setText("无");
                                }
                            }else if(item.getInputType().equals(CommonFormBean.INPUT_BRAND_SELECT)){
                                String jsonList= GsonUtil.gson().toJson(item.getValue());
                                Gson gson1=new Gson();
                                if(Func.isJsonArray(jsonList) ){
                                    List<BrandBean.ContentBean> list= gson1.fromJson(jsonList, new TypeToken<List<BrandBean.ContentBean>>() {}.getType());
                                    String name="";
                                    List<String> keylist=new ArrayList<>();
                                    if(list!=null && list.size()>0 ){
                                        for(int i=0;i<list.size();i++){
                                            name+=list.get(i).getBrandName()+",";
                                            keylist.add(list.get(i).getId()+"");
                                        }
                                    }
                                    item.setValue(list);
                                    if(!Func.IsStringEmpty(name)){
                                        tvValue.setText(name.substring(0,name.length()));
                                    }

                                }else{
                                    if(!Func.IsStringEmpty(item.getValue().toString())){
                                        tvValue.setText(item.getValue().toString());
                                    }
                                }
                            }

                        }else{
                            if(item.getKey().equals( "cohabitOperator" ) && (viewOperatorType!=null && !viewOperatorType.equals( ViewOperatorType.ADD ) )){
                                item.setValue("无");
                                tvValue.setText("无");
                                tvValue.setHint( "无" );
                            }else{
                                tvValue.setText("");
                                tvValue.setHint( item.getRightContent() );
                            }


                        }
                    }else if(item.getInputType().equals(CommonFormBean.PHONE_TEXT)){
                        tvValue.setVisibility(View.GONE);
                        jtRightIv.setVisibility(View.GONE);
                        contentEdt.setVisibility(View.VISIBLE);
                        contentEdt.setHint(item.getRightContent());
                        if(item.getValue()!=null ){
                            contentEdt.setText(item.getValue().toString());
                        }else{
                            contentEdt.setText("");
                            contentEdt.setHint( item.getRightContent() );
                        }
                        contentEdt.setInputType(InputType.TYPE_CLASS_PHONE);
                    }else if(item.getInputType().equals(CommonFormBean.EMAIL_TEXT)){
                        tvValue.setVisibility(View.GONE);
                        jtRightIv.setVisibility(View.GONE);
                        contentEdt.setVisibility(View.VISIBLE);
                        contentEdt.setHint(item.getRightContent());
                        if(item.getValue()!=null ){
                            contentEdt.setText(item.getValue().toString());
                        }else{
                            contentEdt.setText("");
                            contentEdt.setHint( item.getRightContent() );
                        }
                        contentEdt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    }else{
                        tvValue.setVisibility(View.GONE);
                        jtRightIv.setVisibility(View.GONE);
                        contentEdt.setHint(item.getRightContent());
                        if(item.getValue()!=null && !Func.IsStringEmpty(item.getValue().toString()) ){
                            if(item.getKeyBoardType().equals(CommonFormBean.KBINTEGER)){
                                String sResultvalue="";
                                if(item.getValue().toString().contains(".")){
                                    sResultvalue=item.getValue().toString().substring(0,item.getValue().toString().indexOf("."));
                                }else{
                                    sResultvalue=item.getValue().toString();
                                }
                                if(!Func.IsStringEmpty( sResultvalue )){
                                    contentEdt.setText(Integer.parseInt(sResultvalue)+"");
                                }
                            }else{
                                contentEdt.setText(item.getValue().toString());
                            }

                        }else{
                            contentEdt.setText("");
                        }
                        contentEdt.setVisibility(View.VISIBLE);
                        if(item.getKeyBoardType()!=null){
                            if(item.getKeyBoardType().equals(CommonFormBean.KBINTEGER)){
                                contentEdt.setSingleLine();
                                contentEdt.setInputType(InputType.TYPE_CLASS_NUMBER );
                            }else if(item.getKeyBoardType().equals(CommonFormBean.KBDOUBLE)){
                                contentEdt.setSingleLine();
                                contentEdt.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
                            }else if(item.getKeyBoardType().equals(CommonFormBean.KBDEFAULT)){
                                contentEdt.setSingleLine();
                                contentEdt.setInputType(InputType.TYPE_CLASS_TEXT);
                            }
                        }

                        if(!item.isEditAble()){
//                            contentEdt.setVisibility(View.GONE);
                            contentEdt.setEnabled( false );
                        }else {
                            contentEdt.setEnabled( true );
                        }

                    }
                }
                break;
            case CommonFormBean.IT_BIG_INPUT:
                itemGroupLayout = holder.getView(R.id.item_group_layout);
                tvTitle = holder.getView(R.id.tv_title);
                tvTitle.setText(item.getLeftContent());
                contentEdt = holder.getView(R.id.content_edt);
                contentEdt.setHint(item.getRightContent());
                if(item.getValue()!=null ){
                    contentEdt.setText(item.getValue().toString());
                }else{
                    contentEdt.setText("");
                }
                contentEdt.addTextChangedListener(new MyTextWatcher(holder));
                break;
            case CommonFormBean.IT_FILE_INPUT:
                itemGroupLayout = holder.getView(R.id.item_group_layout);
                tv_attchment_title = holder.getView(R.id.tv_attchment_title);
                if(!Func.IsStringEmpty(item.getLeftContent())){
                    tv_attchment_title.setText(item.getLeftContent());
                }
                tvCount = holder.getView(R.id.tv_count);
                noScrollGridView = holder.getView(R.id.noScrollgridview);
                GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(),5);
                noScrollGridView.setLayoutManager(mgr);
                noScrollGridView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));
                adapter = new CommonPictureAdapter(JiuyiMainApplication.getIns(),mediaArrayList);
                adapter.setViewOperatorType(viewOperatorType);
                noScrollGridView.setAdapter(adapter);
                adapter.setOnRecyclerViewItemListener(new CommonPictureAdapter.OnRecyclerViewItemListener(){
                    @Override
                    public void onItemClickListener(View view, int position) {
                        if (position == mediaArrayList.size()) {
                            selectImgs();
                        } else {
                            Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                            intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
                            intent.putExtra(PickerConfig.PRE_RAW_LIST, mediaArrayList);
                            intent.putExtra(PickerConfig.MAX_SELECT_COUNT,9);  //最大选择数量，默认40（非必填参数）
                            intent.putExtra(PickerConfig.CURRENT_POSITION,position);  //最大选择数量，默认40（非必填参数）
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 1500);
                        }
                    }
                });
                adapter.setOnCLickItemListener(new CommonPictureAdapter.onCLickItemListener() {
                    @Override
                    public void click(int position, String colname, View view) {
                        if(colname.equals("img_delete")){
                            mediaArrayList.remove(position);
                            if(attachmentBeanArrayList!=null && attachmentBeanArrayList.size()>0){
                                attachmentBeanArrayList.remove(position);
                            }
                            adapter.notifyDataSetChanged();
                            refreshCount();
                        }

                    }
                });

                if(item.getValue()!=null){
                    attachmentBeanArrayList=new ArrayList<>();
                    mediaArrayList=new ArrayList<>();

                    ArrayList<AttachmentBean> result=(ArrayList)item.getValue();
                    if(result.size()>0){
                        for(int i=0;i<result.size();i++){
                            String jsonData = GsonUtil.gson().toJson(result.get(i));
                            Gson gs = new Gson();
                            AttachmentBean attachmentBean = gs.fromJson(jsonData, AttachmentBean.class);//把JSON字符串转为对象
                            attachmentBeanArrayList.add(attachmentBean);
                            Media media=new Media();
                            media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                            media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                            media.setExtension(".jpg");
                            media.setMediaType(0);
                            if(viewOperatorType.equals(ViewOperatorType.VIEW)) {
                                media.setShowIcon(false);
                            }
                            mediaArrayList.add(media);
                        }
                        adapter.setMviewBeanList(mediaArrayList);
                        adapter.notifyDataSetChanged();
                        refreshCount();
                    }
                }

                break;
            case CommonFormBean.IT_TITLE_INPUT:
                itemGroupLayout = holder.getView(R.id.item_group_layout);
                tvTitle = holder.getView(R.id.tv_title);
                tvTitle.setText(item.getLeftContent());
                break;
            case CommonFormBean.IT_TOGGLEBUTTON_INPUT:
                itemGroupLayout = holder.getView(R.id.item_group_layout);
                tvTitle = holder.getView(R.id.tv_title);
                tvTitle.setText(item.getLeftContent());
                toggleButton = holder.getView(R.id.tb_type);
                toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            mData.get(holder.getLayoutPosition()).setValue(true);
                        }else{
                            mData.get(holder.getLayoutPosition()).setValue(false);
                        }
                    }
                });
                if(item.getValue()!=null ){
                    toggleButton.setChecked((Boolean)item.getValue());
                }
                if(viewOperatorType.equals(ViewOperatorType.VIEW)) {
                    toggleButton.setEnabled(false);
                }
                if(!item.isEditAble()) {
                    toggleButton.setEnabled(false);
                }else{
                    toggleButton.setEnabled(true);
                }
                break;

        }
        if(viewOperatorType.equals(ViewOperatorType.VIEW) && itemGroupLayout!=null){
            itemGroupLayout.setOnClickListener(null);
            if(contentEdt!=null){
                contentEdt.setEnabled(false);
            }

        }
    }


    protected void selectImgs() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuWindow = new SelectPicPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
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

                    String[] list2 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list2, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Intent intent =new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PickerActivity.class);
                            intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
                            intent.putExtra(PickerConfig.MAX_SELECT_COUNT,9);  //最大选择数量，默认40（非必填参数）
                            if(mediaArrayList!=null && mediaArrayList.size()>0 ){
                                intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,mediaArrayList); //可以设置默认选中的照片(非必填参数)
                            }
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,SELECT_PICTURE);
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
        Rc.picVideoUrl=filePath;
        File file1 = new File(filePath);
        if (!file1.exists()) {
            File vDirPath = file1.getParentFile();
            vDirPath.mkdirs();
        }
        Uri uri = null;

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
    public void attachRefresh(){
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
        if(tvCount!=null){
            tvCount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
        }
    }
    class MyTextWatcher implements TextWatcher {
        public MyTextWatcher(BaseViewHolder holder) {
            mHolder = holder;
        }

        private BaseViewHolder mHolder;

        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            JiuyiLog.e("getsig","request errorCode:"+"1" );
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
//             if (s != null && !"".equals(s.toString())) {
//                mData.get(mHolder.getLayoutPosition()).setValue(s.toString());
//            }
            mData.get(mHolder.getLayoutPosition()).setValue(s.toString());
            if(viewOperatorType!=null && !viewOperatorType.equals( ViewOperatorType.VIEW ) ){
                if( mData.size()>0 &&!Func.IsStringEmpty(mData.get(mHolder.getLayoutPosition()).getKey()) && mData.get(mHolder.getLayoutPosition()).getKey().equals("reimbursementCity")){
                    String tzryWorkcode="";
                    for(int i=0;i<mData.size();i++){
                        if(mData.get(i).getKey().equals("cohabitOperator") && mData.get(i).getValue() !=null){
                            String jsonData = GsonUtil.gson().toJson(mData.get(i).getValue());
                            Gson gs = new Gson();
                            if(Func.isJsonObject(jsonData) ){
                                NormalOperatorBean.ContentBean bean = gs.fromJson(jsonData, NormalOperatorBean.ContentBean.class);//把JSON字符串转为对象
                                if(bean!=null){
                                    tzryWorkcode=bean.getOaCode();
                                }
                            }
                            break;
                        }
                    }
                    if(!Func.IsStringEmpty( s.toString() )){
                        if (FastUtils.isFastClick()) {
                            getStandard(s.toString().trim(),Rc.mobilecode,tzryWorkcode);
                        }
                    }

                }

                if( mData.size()>0 &&!Func.IsStringEmpty(mData.get(mHolder.getLayoutPosition()).getKey()) && mData.get(mHolder.getLayoutPosition()).getKey().equals("stayInvoiceAmount") && !Func.IsStringEmpty(s.toString())){
                    double standardAmt=0,invoiceAmount=0,amt=0;
                    for(int i=0;i<mData.size();i++){
                        if(mData.get(i).getKey().equals("stayReimbursementStandard")){
                            if(mData.get(i).getValue()!=null){
                                standardAmt=Double.parseDouble( mData.get(i).getValue().toString() );
                            }

                        }
                    }
                    invoiceAmount=Double.parseDouble( s.toString() ) ;
                    if(standardAmt>=invoiceAmount){
                        amt=invoiceAmount;
                    }else{
                        amt=standardAmt;
                    }
                    for(int i=0;i<mData.size();i++){
                        if(mData.get(i).getKey()!=null){
                            if(mData.get(i).getKey().equals("stayReimbursementAmount")){
                                mData.get(i).setValue( amt+"" );
                                mData.get(i).setRightContent( amt+"" );

                                if (getRecyclerView()!=null && (getRecyclerView().getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (getRecyclerView().isComputingLayout() == false))
                                        ) {
                                    int finalI = i;
                                    handler.post( new Runnable() {
                                        @Override
                                        public void run() {
                                            notifyItemChanged( finalI );
                                        }
                                    });

                                }
                            }
                        }
                    }
                }

                if( mData.size()>0 &&!Func.IsStringEmpty(mData.get(mHolder.getLayoutPosition()).getKey()) && mData.get(mHolder.getLayoutPosition()).getKey().equals("cityTrafficInvoiceAmount") && !Func.IsStringEmpty(s.toString())){
                    double standardAmt=0,invoiceAmount=0,amt=0;
                    for(int i=0;i<mData.size();i++){
                        if(mData.get(i).getKey().equals("cityTrafficReimbursementStandard")){
                            if(mData.get(i).getValue()!=null){
                                standardAmt=Double.parseDouble( mData.get(i).getValue().toString() );
                            }

                        }
                    }
                    invoiceAmount=Double.parseDouble( s.toString() ) ;
                    if(standardAmt>=invoiceAmount){
                        amt=invoiceAmount;
                    }else{
                        amt=standardAmt;
                    }
                    for(int i=0;i<mData.size();i++){
                        if(mData.get(i).getKey()!=null){
                            if(mData.get(i).getKey().equals("cityTrafficReimbursementAmount")){
                                mData.get(i).setValue( amt+"" );
                                mData.get(i).setRightContent( amt+"" );
                                if (getRecyclerView()!=null && (getRecyclerView().getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (getRecyclerView().isComputingLayout() == false))
                                        ) {
                                    int finalI = i;
                                    handler.post( new Runnable() {
                                        @Override
                                        public void run() {
                                            notifyItemChanged( finalI );
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
                if( mData.size()>0 &&!Func.IsStringEmpty(mData.get(mHolder.getLayoutPosition()).getKey()) && mData.get(mHolder.getLayoutPosition()).getKey().equals("kcTransport") ){
                    boolean canNull=true;
                    if(!Func.IsStringEmpty(s.toString()) && Double.parseDouble(s.toString())>0){
                        canNull=false;
                    }else{
                        canNull=true;
                    }
                    for(int i=0;i<mData.size();i++){
                        if(mData.get(i).getKey()!=null){
                            if(mData.get(i).getKey().equals("boardTime")||mData.get(i).getKey().equals("dropTime") ){
                                if(canNull){
                                    mData.get(i).setNullAble(true );
                                    mData.get(i).setRightContent("请选择");
                                }else{
                                    mData.get(i).setNullAble(false );
                                    mData.get(i).setRightContent("必填");
                                }

                                if (getRecyclerView()!=null && (getRecyclerView().getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (getRecyclerView().isComputingLayout() == false))
                                        ) {
                                    int finalI = i;
                                    handler.post( new Runnable() {
                                        @Override
                                        public void run() {
                                            notifyItemChanged( finalI );
                                        }
                                    });
                                }
                            }
                        }
                    }


                }
            }



        }
    }
    public void refreshCount(){
        if(tvCount!=null && mediaArrayList!=null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvCount.setText(mediaArrayList.size()+ "/" + 9 );
                }
            });

        }
    }

    private void getStandard(String city,String workcode,String tzryworkcode ) {
        String url="";
        List<DictBean> dictBeansList= DictUtils.getDictList("travel_api");
        for(DictBean dictBean :dictBeansList){
            if(dictBean.getValue()!=null){
                url= dictBean.getValue();
            }
        }
        if(Func.IsStringEmpty( url )){
            url="http://112.17.59.161:10000"+"/jiuyi/CRM/GetBxbzForCRM.jsp?cityname="+city+"&workcode="+workcode;;
        }else{
            url+="/jiuyi/CRM/GetBxbzForCRM.jsp?cityname="+city+"&workcode="+workcode;;
        }
//        String url= DynamicConstant.OA_URL+"/jiuyi/CRM/GetBxbzForCRM.jsp?cityname="+city+"&workcode="+workcode;
        if(!Func.IsStringEmpty( tzryworkcode )){
            url+="&tzryworkcode="+tzryworkcode;
        }
        JiuyiHttp.GET(url)
                .tag("request_get_2")
                .request(new ACallback<TripStandardBean>() {
                    @Override
                    public void onSuccess(TripStandardBean tripStandardBean) {
                        if (tripStandardBean != null) {
                            for(int i=0;i<mData.size();i++){
                                if(mData.get(i).getKey()!=null){
                                    if(mData.get(i).getKey().equals("stayReimbursementStandard")){
                                        if(!Func.IsStringEmpty(tripStandardBean.getZsbz()  )){
                                            mData.get(i).setValue( Double.parseDouble( tripStandardBean.getZsbz())+"" );
                                        }else{
                                            mData.get(i).setValue( "0" );
                                        }
                                        if (getRecyclerView()!=null && (getRecyclerView().getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (getRecyclerView().isComputingLayout() == false))
                                                ) {
                                            int finalI = i;
                                            handler.post( new Runnable() {
                                                @Override
                                                public void run() {
                                                    notifyItemChanged( finalI );
                                                }
                                            });
                                        }

                                    }else if(mData.get(i).getKey().equals("cityTrafficReimbursementStandard")){
                                        if(!Func.IsStringEmpty(tripStandardBean.getJtbz()  )){
                                            mData.get(i).setValue( Double.parseDouble( tripStandardBean.getJtbz())+"");
                                        }else{
                                            mData.get(i).setValue( "0" );
                                        }
                                        if (getRecyclerView()!=null && (getRecyclerView().getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (getRecyclerView().isComputingLayout() == false))
                                                ) {
                                            int finalI = i;
                                            handler.post( new Runnable() {
                                                @Override
                                                public void run() {
                                                    notifyItemChanged( finalI );
                                                }
                                            });
                                        }

                                    }else if(mData.get(i).getKey().equals("mealAllowance")){
                                        if(!Func.IsStringEmpty(tripStandardBean.getCbbz()  )){
                                            mData.get(i).setValue(Double.parseDouble( tripStandardBean.getCbbz())+"");
                                        }else{
                                            mData.get(i).setValue( "0" );
                                        }
                                        if (getRecyclerView()!=null && (getRecyclerView().getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (getRecyclerView().isComputingLayout() == false))
                                                ) {
                                            int finalI = i;
                                            handler.post( new Runnable() {
                                                @Override
                                                public void run() {
                                                    notifyItemChanged( finalI );
                                                }
                                            });
                                        }
                                    }
                                }

                             }
//                            if (getRecyclerView()!=null && (getRecyclerView().getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (getRecyclerView().isComputingLayout() == false))
//                                    ) {
//                                handler.post( new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        notifyDataSetChanged();
//                                    }
//                                });
//
//                            }
                        }
                    }
                    @Override
                    public void onFail(int errCode, String errMsg) {
                        JiuyiLog.e("getsig","request errorCode:" + errCode + ",errorMsg:" + errMsg);
                    }
                });
    }

}
