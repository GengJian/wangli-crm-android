package com.jiuyi.activity.common.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnCancelLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiDateUtil;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.google.gson.Gson;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.jiuyi.adapter.JiuyiCommonFormAdapter;
import com.jiuyi.model.TripStandardBean;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.CommonFormBean;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.nanchen.compresshelper.CompressHelper;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.bean.NormalOperatorBean;
import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.entity.AttachmentBean;
import customer.entity.BrandBean;
import customer.entity.CitySearchBean;
import customer.entity.CustomerStoreInfoBean;
import customer.entity.DepartmentBean;
import customer.entity.Media;
import customer.entity.MemberBean;
import customer.entity.MemberBeanID;
import customer.entity.PersonSelectBean;
import customer.entity.ProvinceBean;
import customer.entity.RegionSearchBean;
import customer.listener.PickerConfig;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.Utils.DynamicConstant;

import static com.jiuyi.model.CommonFormBean.INPUT_OPERATOR;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCommonFormActivity
 * 作       者 : zhengss
 * 创建时间:2018/12/03 14:43
 * 文件描述 : 通用新增页面
 *****************************************************************
 */

public class JiuyiCommonFormActivity extends JiuyiActivityBase {
    private TextView tvComplete, tvCancel;
    private String viewOperatorType;
    private long beanid=-1;
    private List<CommonFormBean> commonFormBeanList;
    private List<AttachmentBean> attachmentsBeanslist,attachmentsBeansEditList;
    private RecyclerView rvContent;
    private JiuyiCommonFormAdapter jiuyiCommonFormAdapter;
    protected static final int TAKE_PICTURE = 1000;
    protected static final int SELECT_PICTURE = 1500;

    protected String filePath;
    private  String sTitle;
    private String backPageType="";
    Map<String, Object> beanMap;
    TextView tvValue;
    private int currentPos=-1;
    private  File[] files;
    private List<PersonSelectBean> mPersonBeanSelectList,mBeanSelectList;
    private long customerId;
    private String customerName;
    private String cityId="",provinceId="";
    private List<BrandBean.ContentBean> mProductTypeSelectList;
    Handler handler = new Handler();

    @Override
    public void onInit() {
        customerId=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        customerName=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        backPageType=mBundle.getString(JiuyiBundleKey.PARAM_BACKPAGETYPE);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_commonnew_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        if(viewOperatorType==null){
            viewOperatorType="";
        }
        if(viewOperatorType.equals(ViewOperatorType.ADD)){
            getFormItemInfo();
        }

        if(beanid>0 && !viewOperatorType.equals(ViewOperatorType.ADD)){
            updateFormItemInfo(beanid);
        }
        tvComplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (tvComplete != null) {
            tvComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                boolean mbcheck=false;
                mbcheck=check();
                if(!mbcheck){
                    return;
                }
                showDialog();
                if(viewOperatorType.equals(ViewOperatorType.EDIT)){
                    attachmentsBeansEditList=new ArrayList<AttachmentBean>();
                    if(jiuyiCommonFormAdapter!=null && jiuyiCommonFormAdapter.adapter!=null && jiuyiCommonFormAdapter.getAttachmentBeanArrayList()!=null ){
                        attachmentsBeansEditList=jiuyiCommonFormAdapter.getAttachmentBeanArrayList();
                    }
                    if(jiuyiCommonFormAdapter!=null && jiuyiCommonFormAdapter.adapter!=null &&jiuyiCommonFormAdapter.adapter.getMviewBeanList()!=null && jiuyiCommonFormAdapter.adapter.getMviewBeanList().size()>0){
                        boolean ibUpload=false;
                        files=new File[jiuyiCommonFormAdapter.adapter.getMviewBeanList().size()];
                        for(int i=0;i<jiuyiCommonFormAdapter.adapter.getMviewBeanList().size();i++){
                            if(jiuyiCommonFormAdapter.adapter.getMviewBeanList().get(i).getPath()!=null){
                                File file1 = new File(jiuyiCommonFormAdapter.adapter.getMviewBeanList().get(i).getPath());
                                files[i]=file1;
                                ibUpload=true;
                            }

                        }
                        if(ibUpload){
                            upload();
                        }else{
                            attachmentsBeanslist=attachmentsBeansEditList;
                            updateInfo();
                        }

                    }else {
                        //新增
                        updateInfo();
                    }

                }else {
                    if(jiuyiCommonFormAdapter.adapter!=null && jiuyiCommonFormAdapter.adapter.getMviewBeanList()!=null && jiuyiCommonFormAdapter.adapter.getMviewBeanList().size()>0){
                        files=new File[jiuyiCommonFormAdapter.adapter.getMviewBeanList().size()];
                        for(int i=0;i<jiuyiCommonFormAdapter.adapter.getMviewBeanList().size();i++){
                            File file1 = new File(jiuyiCommonFormAdapter.adapter.getMviewBeanList().get(i).getPath());
                            files[i]=file1;
                        }

                       upload();
                    }else {
                        //新增
                        createInfo();
                    }

                }

                }
            });

        }
        if(viewOperatorType.equals(ViewOperatorType.VIEW)){
            tvComplete.setVisibility(View.INVISIBLE);
        }
        if(viewOperatorType.equals(ViewOperatorType.ADD) && customerId>0 && backPageType.equals( "storme-manage-entity" )){
            getCustomerDetail( customerId );
        }
        tvCancel = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_cancel"));
        if (tvCancel != null) {
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
                }
            });

        }
        beanMap= new HashMap<String, Object>();
    }
    public void SetTextChanged(final JiuyiEditTextField edittext) {
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable v) {
                String str = edittext.getText().toString();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String str = edittext.getText().toString();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = edittext.getText().toString();

            }
        });
    }
    private void upload()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.UPLOAD("file/upload-multi")
                .tag("upload-multi")
                .addFiles2("file",files)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ArrayList<AttachmentBean>>() {
                    @Override
                    public void onSuccess(ArrayList<AttachmentBean> data) {
                        if(data!=null && data.size()>0){
                            attachmentsBeanslist=data;
                            if(viewOperatorType.equals(ViewOperatorType.EDIT)){
                                if(attachmentsBeansEditList!=null && attachmentsBeansEditList.size()>0){
                                    for(int i=0;i<attachmentsBeansEditList.size();i++){
                                        attachmentsBeanslist.add(attachmentsBeansEditList.get(i));
                                    }
                                }
                                updateInfo();
                            }else if(viewOperatorType.equals(ViewOperatorType.ADD)){
                                createInfo();
                            }

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

    public void initViews(){
        rvContent= (RecyclerView) mBodyLayout.findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(JiuyiCommonFormActivity.this));
        rvContent.setNestedScrollingEnabled(false);
        rvContent.setHasFixedSize(true);
        rvContent.setItemAnimator(new DefaultItemAnimator());
        rvContent.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiCommonFormActivity.this, LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

    }
    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else{
            mTitle = "新建记录";
        }
        setTitle(mTitle);
    }
    public boolean check(){
        if(jiuyiCommonFormAdapter!=null && jiuyiCommonFormAdapter.getData().size()>0 ){
            String stravelDate ="",sendDate= "",sboardTime= "",sdropTime= "";
            for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                CommonFormBean commonFormBean=jiuyiCommonFormAdapter.getData().get(i);
                if(commonFormBean.getKey()!=null){
                    if( commonFormBean.getKey().equals("attachmentList") && backPageType.equals("storme-manage-entity")){
                        if(jiuyiCommonFormAdapter.adapter!=null && (jiuyiCommonFormAdapter.adapter.getMviewBeanList()==null||jiuyiCommonFormAdapter.adapter.getMviewBeanList().size()==0)){
                            startDialog(DialogID.DialogDoNothing, "", commonFormBean.getRightContent()+commonFormBean.getLeftContent()+"!", JiuyiDialogBase.Dialog_Type_Yes, null);
                            return false;
                        }
                    }else{
                        if(!commonFormBean.isNullAble() && (commonFormBean.getValue()==null|| Func.IsStringEmpty(commonFormBean.getValue().toString())) && !commonFormBean.getRowType().equals(CommonFormBean.INPUT_TITLE)){
                            startDialog(DialogID.DialogDoNothing, "", commonFormBean.getRightContent()+commonFormBean.getLeftContent()+"!", JiuyiDialogBase.Dialog_Type_Yes, null);
                            return false;
                        }
                    }
                    if(backPageType.equals("travel-business") && (commonFormBean.getValue()!=null)){
                        if(commonFormBean.getKey().equals("travelDate")){
                            stravelDate=commonFormBean.getValue().toString();
                        }else  if(commonFormBean.getKey().equals("endDate")){
                            sendDate=commonFormBean.getValue().toString();
                        } if(commonFormBean.getKey().equals("boardTime")){
                            sboardTime=commonFormBean.getValue().toString();
                        } if(commonFormBean.getKey().equals("dropTime")){
                            sdropTime=commonFormBean.getValue().toString();
                        }
                    }
                }
            }
            if(backPageType.equals("travel-business")){
                if(JiuyiDateUtil.DateCompare(stravelDate,sendDate )){
                    startDialog(DialogID.DialogDoNothing, "", "出差日期不能晚于结束日期", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return false;
                }
                if(!Func.IsStringEmpty( sboardTime) &&!Func.IsStringEmpty(  sdropTime) &&JiuyiDateUtil.TimeCompare2(sboardTime,sdropTime )){
                    startDialog(DialogID.DialogDoNothing, "", "上车时间不能晚于下车时间", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return false;
                }

            }
        }

        return true;
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            Rc.mBackfresh=true;
            backPage();
        }
    }
    public void getFormItemInfo(){
        JiuyiHttp.GET(backPageType+"/create-form/")
                .tag("request_get__check"+backPageType)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<CommonFormBean>>() {
                    @Override
                    public void onSuccess(List<CommonFormBean> contentBeanList) {
                        if(contentBeanList!=null && contentBeanList.size()>0){
                            commonFormBeanList=new ArrayList<>();
                            for(int i=0;i<contentBeanList.size();i++){
                                CommonFormBean commonFormBean=contentBeanList.get(i);
                                if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_TEXT)||commonFormBean.getRowType().equals(CommonFormBean.INPUT_SELECT)
                                        ||commonFormBean.getRowType().equals(CommonFormBean.DATE_SELECT)
                                        ||commonFormBean.getRowType().equals(CommonFormBean.INPUT_MEMBER)
                                        ||commonFormBean.getRowType().equals( INPUT_OPERATOR)
                                        ||commonFormBean.getRowType().equals(CommonFormBean.INPUT_DEPARTMENT)
                                        ||commonFormBean.getRowType().equals(CommonFormBean.INPUT_BRAND_SELECT)){
                                    commonFormBean.setItemType(CommonFormBean.IT_INPUT_TEXT);
                                    if(commonFormBean.getRowType().equals(CommonFormBean.DATE_SELECT)){
                                        commonFormBean.setInputType(CommonFormBean.DATE_SELECT);
                                    }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_SELECT)){
                                        commonFormBean.setInputType(CommonFormBean.INPUT_SELECT);
                                    }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_MEMBER)){
                                        commonFormBean.setInputType(CommonFormBean.INPUT_MEMBER);
                                    }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_DEPARTMENT)){
                                        commonFormBean.setInputType(CommonFormBean.INPUT_DEPARTMENT);
                                    }else if(commonFormBean.getRowType().equals( INPUT_OPERATOR)){
                                        commonFormBean.setInputType( INPUT_OPERATOR);
                                    }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_BRAND_SELECT)){
                                        commonFormBean.setInputType(CommonFormBean.INPUT_BRAND_SELECT);
                                    }else{
                                        if(commonFormBean.getInputType().equals(CommonFormBean.LONG_TEXT)){
                                            commonFormBean.setItemType(CommonFormBean.IT_BIG_INPUT);
                                        }else if(commonFormBean.getInputType().equals(CommonFormBean.NUMBWE_PRICE_TEXT)){
                                            commonFormBean.setKeyBoardType(CommonFormBean.KBDOUBLE);
                                            commonFormBean.setInputType(CommonFormBean.SHORT_TEXT);
                                        }else if(commonFormBean.getInputType().equals(CommonFormBean.PHONE_TEXT)){
                                            commonFormBean.setInputType(CommonFormBean.PHONE_TEXT);
                                        }else if(commonFormBean.getInputType().equals(CommonFormBean.EMAIL_TEXT)){
                                            commonFormBean.setInputType(CommonFormBean.EMAIL_TEXT);
                                        }else {
                                            if(commonFormBean.getKeyBoardType()!=null &&commonFormBean.getKeyBoardType().equals(CommonFormBean.KBDOUBLE) ){
                                                commonFormBean.setKeyBoardType(CommonFormBean.KBDOUBLE);
                                            }
                                            if(commonFormBean.getKeyBoardType()!=null &&commonFormBean.getKeyBoardType().equals(CommonFormBean.KBDEFAULT) ){
                                                commonFormBean.setKeyBoardType(CommonFormBean.KBDEFAULT);
                                            } if(commonFormBean.getKeyBoardType()!=null &&commonFormBean.getKeyBoardType().equals(CommonFormBean.KBINTEGER) ){
                                                commonFormBean.setKeyBoardType(CommonFormBean.KBINTEGER);
                                            }
                                            commonFormBean.setInputType(CommonFormBean.SHORT_TEXT);
                                        }
                                    }

                                }else if(commonFormBean.getRowType().equals(CommonFormBean.FILE_INPUT)){
                                    commonFormBean.setItemType(CommonFormBean.IT_FILE_INPUT);
                                }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_TITLE)){
                                    commonFormBean.setItemType(CommonFormBean.IT_TITLE_INPUT);
                                }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_TOGGLEBUTTON)){
                                    commonFormBean.setItemType(CommonFormBean.IT_TOGGLEBUTTON_INPUT);
                                }
                                commonFormBeanList.add(commonFormBean);
                            }

                            jiuyiCommonFormAdapter = new JiuyiCommonFormAdapter(commonFormBeanList);
                            if(!Func.IsStringEmpty(viewOperatorType)){
                                jiuyiCommonFormAdapter.setViewOperatorType(viewOperatorType);
                            }
                            jiuyiCommonFormAdapter.bindToRecyclerView(rvContent);
                            if(customerId>0 && customerName!=null){
                                MemberBeanID memberBeanID=new MemberBeanID();
                                memberBeanID.setId(customerId);
                                memberBeanID.setOrgName(customerName);
                                jiuyiCommonFormAdapter.setMemberBeanID(memberBeanID);
                            }
                            jiuyiCommonFormAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    CommonFormBean contentFormBean= (CommonFormBean) adapter.getData().get(position);
                                    tvValue=(TextView)view.findViewById(R.id.tv_value);
                                    currentPos=position;
                                    if(contentFormBean!=null && contentFormBean.getInputType()!=null){
                                        if(contentFormBean.getInputType().equals(CommonFormBean.DATE_SELECT)){
                                            DatePickDialog dialog = new DatePickDialog(JiuyiCommonFormActivity.this);
                                            //设置上下年分限制
                                            dialog.setYearLimt(60);
                                            //设置标题
                                            dialog.setTitle("选择时间");
                                            if(contentFormBean.getPattern()!=null ){
                                                if(contentFormBean.getPattern().toLowerCase().equals("yyyy-mm-dd hh:mm:ss")){
                                                    dialog.setType(DateType.TYPE_ALL);
                                                }else if(contentFormBean.getPattern().toLowerCase().equals("yyyy-mm-dd hh:mm")){
                                                    dialog.setType(DateType.TYPE_YMDHM);
                                                }else if(contentFormBean.getPattern().toLowerCase().equals("yyyy-mm-dd hh")){
                                                    dialog.setType(DateType.TYPE_YMDH);
                                                }else if(contentFormBean.getPattern().toLowerCase().equals("yyyy-mm-dd")){
                                                    dialog.setType(DateType.TYPE_YMD);
                                                }else if(contentFormBean.getPattern().toLowerCase().equals("hh:mm")){
                                                    dialog.setType(DateType.TYPE_HM);
                                                }else if(contentFormBean.getPattern().toLowerCase().equals("yyyyMM")){
                                                    dialog.setType(DateType.TYPE_YM);
                                                }

                                            }else {
                                                //设置类型
                                                dialog.setType(DateType.TYPE_YMD);
                                            }
//                                            设置消息体的显示格式，日期格式
                                            if(contentFormBean.getPattern()!=null){
                                                dialog.setMessageFormat(contentFormBean.getPattern().toString());
                                            }else {
                                                dialog.setMessageFormat("yyyy-MM-dd");
                                            }

                                            //设置选择回调
                                            dialog.setOnChangeLisener(null);
                                            //设置点击确定按钮回调
                                            dialog.setOnSureLisener(new OnSureLisener() {
                                                @Override
                                                public void onSure(Date date) {
                                                    if(contentFormBean.getPattern()!=null){
                                                        tvValue.setText( new SimpleDateFormat(contentFormBean.getPattern()).format(date));
                                                    }else {
                                                        tvValue.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                                                    }

                                                    contentFormBean.setValue(tvValue.getText().toString());
                                                }
                                            });
                                            dialog.setOnCancelListener(new OnCancelLisener() {
                                                @Override
                                                public void onCancel(String s) {
                                                    tvValue.setText("");
                                                    contentFormBean.setValue("");
                                                }
                                            });
                                            dialog.show();
                                        }else if(contentFormBean.getInputType().equals(CommonFormBean.INPUT_SELECT) ){
                                            if(contentFormBean.getKey()!=null ){
                                                currentPos=position;
                                                if(contentFormBean.getKey().equals("reimbursementCity")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"BXCITY");
                                                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                                                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "300");
                                                    changePage(mBundle, Pub.CustomerSelect,true);
                                                }else if(contentFormBean.getKey().equals("city")){
                                                    if(Func.IsStringEmpty(provinceId)){
                                                        Toast.makeText(JiuyiCommonFormActivity.this, "省份为空", Toast.LENGTH_SHORT).show();
                                                        return;
                                                    }
                                                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CITY");
                                                    mBundle.putString(JiuyiBundleKey.PARAM_PROVINCESELECT,provinceId);
                                                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "301");
                                                    changePage(mBundle, Pub.CustomerSelect,true);
                                                }else if(contentFormBean.getKey().equals("area")){
                                                    if(Func.IsStringEmpty(cityId)){
                                                        Toast.makeText(JiuyiCommonFormActivity.this, "地区为空", Toast.LENGTH_SHORT).show();
                                                        return;
                                                    }
                                                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"AREA");
                                                    mBundle.putString(JiuyiBundleKey.PARAM_BILLID,cityId);
                                                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "302");
                                                    changePage(mBundle, Pub.CustomerSelect,true);
                                                }
                                            }
                                            if(contentFormBean.getDictName()!=null){
                                                if(!contentFormBean.isMutiAble()){
                                                    AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCommonFormActivity.this);
                                                    buidler.setTitle(contentFormBean.getLeftContent());
                                                    final String[] data;
                                                    final List<DictBean> dictBeansList= DictUtils.getDictList(contentFormBean.getDictName());
                                                    if(dictBeansList!=null &&dictBeansList.size()>0){
                                                        data=new String[dictBeansList.size()];
                                                        for(int i=0;i<dictBeansList.size();i++){
                                                            data[i]=dictBeansList.get(i).getValue();
                                                        }
                                                        buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                tvValue.setText(data[which].trim());
                                                                contentFormBean.setValue(dictBeansList.get(which).getKey());
                                                                contentFormBean.setRightContent(dictBeansList.get(which).getValue());
                                                                if(contentFormBean.getKey()!=null && contentFormBean.getKey().equals( "storeType" ) ){
                                                                    if( contentFormBean.getValue().equals( "0" )){
                                                                        for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                                                                            if(jiuyiCommonFormAdapter.getData().get( i ).getKey()!=null && jiuyiCommonFormAdapter.getData().get( i ).getKey().equals( "whetherToMonopolize") ){
                                                                                jiuyiCommonFormAdapter.getData().get( i ).setValue(false);
                                                                                jiuyiCommonFormAdapter.getData().get( i ).setEditAble( false );
                                                                            }
                                                                        }
                                                                    }else if(contentFormBean.getValue().equals( "1" )){
                                                                        for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                                                                            if(jiuyiCommonFormAdapter.getData().get( i ).getKey()!=null && jiuyiCommonFormAdapter.getData().get( i ).getKey().equals( "whetherToMonopolize") ){
                                                                                jiuyiCommonFormAdapter.getData().get( i ).setEditAble( true );
                                                                            }
                                                                        }
                                                                    }
                                                                    handler.post(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            jiuyiCommonFormAdapter.notifyDataSetChanged();
                                                                        }
                                                                    });

                                                                }
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                        buidler.show();
                                                    }
                                                }else{
                                                    AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCommonFormActivity.this);
                                                    buidler.setTitle(contentFormBean.getLeftContent());

                                                    final boolean b[];
                                                    final String[] data;
                                                    final List<DictBean> dictBeansList= DictUtils.getDictList(contentFormBean.getDictName());
                                                    if (dictBeansList!=null && dictBeansList.size()>0) {
                                                        data = new String[dictBeansList.size()];
                                                        b = new boolean[dictBeansList.size()];
                                                        for (int i = 0; i < dictBeansList.size(); i++) {
                                                            data[i] = dictBeansList.get(i).getValue();
                                                            b[i] = false;
                                                        }
                                                        buidler.setMultiChoiceItems(data, b, new DialogInterface.OnMultiChoiceClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                                                //    将客户是否被勾选的记录保存到集合中
                                                                b[which] = isChecked;  //保存客户选择的属性是否被勾选
                                                            }
                                                        });
                                                        //设置一个确定和取消按钮
                                                        buidler.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                            //保存数据
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                String item = "";
                                                                //取出被勾选中的内容
                                                                ArrayList selectList = new ArrayList<>();
                                                                for (int i = 0; i < dictBeansList.size(); i++) {
                                                                    if (b[i]) {             //如果被勾线则保存数据
                                                                        item += data[i] + " ,";
                                                                        selectList.add(dictBeansList.get(i).getKey());
                                                                    }
                                                                }
                                                                if (!Func.IsStringEmpty(item)) {
                                                                    String sValue = item.substring(0, item.length() - 1);
                                                                    tvValue.setText(sValue);
                                                                    contentFormBean.setValue(selectList);
                                                                }
                                                                //对话框消失
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                        buidler.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                        buidler.show();
                                                    }
                                            }

                                            }
                                        }else if(contentFormBean.getInputType().equals(CommonFormBean.INPUT_MEMBER)){
                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CUSTOMER");
                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "100");
                                            changePage(mBundle, Pub.CustomerSelect,true);
                                        }else if(contentFormBean.getInputType().equals(CommonFormBean.INPUT_DEPARTMENT)){
                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"DEPARTMENT");
                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "101");
                                            changePage(mBundle, Pub.CustomerSelect,true);
                                        }else if(contentFormBean.getInputType().equals( INPUT_OPERATOR)){
                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "200");
                                            changePage(mBundle, Pub.CustomerSelect,true);
                                        }else if(contentFormBean.getInputType().equals(CommonFormBean.INPUT_BRAND_SELECT)){
                                            Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                                            Bundle mBundle = new Bundle();
                                            if(mProductTypeSelectList==null){
                                                mProductTypeSelectList=new ArrayList<>();
                                            }
                                            mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mProductTypeSelectList);
                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"BRAND");
                                            intent.putExtras(mBundle);
                                            startActivityForResult(intent, 206);
                                        }
                                    }
                                }
                            });

                            if(backPageType.equals("travel-business") && jiuyiCommonFormAdapter.getData().size()>0 ){
                                for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                                    if(jiuyiCommonFormAdapter.getData().get(i).getKey()!=null && jiuyiCommonFormAdapter.getData().get(i).getKey().equals("commit")){
                                        jiuyiCommonFormAdapter.getData().get(i).setValue(true);
                                    }
                                }
                            }
                            rvContent.setAdapter(jiuyiCommonFormAdapter);
                        }
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
    public void createInfo(){
//        beanMap= new HashMap<String, Object>();
        if(jiuyiCommonFormAdapter!=null && jiuyiCommonFormAdapter.getData().size()>0 ){
            for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                if(jiuyiCommonFormAdapter.getData().get(i).getKey()!=null){
                    if(jiuyiCommonFormAdapter.getData().get(i).getInputType()!=null &&jiuyiCommonFormAdapter.getData().get(i).getInputType().equals(INPUT_OPERATOR )&&jiuyiCommonFormAdapter.getData().get(i).getValue()!=null && jiuyiCommonFormAdapter.getData().get(i).getValue().equals("无")){
                        beanMap.put(jiuyiCommonFormAdapter.getData().get(i).getKey(),null);
                    }else {
                        if(!(jiuyiCommonFormAdapter.getData().get(i).getKey().equals("btcc") && backPageType.equals("travel-business"))){
                            beanMap.put(jiuyiCommonFormAdapter.getData().get(i).getKey(),jiuyiCommonFormAdapter.getData().get(i).getValue());
                        }

                    }
                    if(jiuyiCommonFormAdapter.getData().get(i).getItemType()==CommonFormBean.IT_FILE_INPUT && attachmentsBeanslist!=null && attachmentsBeanslist.size()>0 ){
                        beanMap.put(jiuyiCommonFormAdapter.getData().get(i).getKey(),attachmentsBeanslist);
                    }
                }

            }
        }

        JiuyiHttp.POST(backPageType+"/create")
                .setJson(GsonUtil.gson().toJson(beanMap))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        Rc.mBackfresh=true;
                        EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
                        startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    public void updateFormItemInfo(long id){
        JiuyiHttp.GET(backPageType+"/update-form/"+id)
                .tag("request_get__check"+backPageType)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<CommonFormBean>>() {
                    @Override
                    public void onSuccess(List<CommonFormBean> contentBeanList) {
                        if(contentBeanList!=null && contentBeanList.size()>0){
                            commonFormBeanList=new ArrayList<>();
                            for(int i=0;i<contentBeanList.size();i++){
                                CommonFormBean commonFormBean=contentBeanList.get(i);
                                if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_TEXT)||commonFormBean.getRowType().equals(CommonFormBean.INPUT_SELECT)
                                        ||commonFormBean.getRowType().equals(CommonFormBean.DATE_SELECT)
                                        ||commonFormBean.getRowType().equals(CommonFormBean.INPUT_MEMBER)
                                        ||commonFormBean.getRowType().equals(CommonFormBean.INPUT_DEPARTMENT)
                                        ||commonFormBean.getRowType().equals( INPUT_OPERATOR)
                                        ||commonFormBean.getRowType().equals(CommonFormBean.INPUT_BRAND_SELECT)){
                                    commonFormBean.setItemType(CommonFormBean.IT_INPUT_TEXT);
                                    if(commonFormBean.getRowType().equals(CommonFormBean.DATE_SELECT)){
                                        commonFormBean.setInputType(CommonFormBean.DATE_SELECT);
                                    }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_SELECT)){
                                        commonFormBean.setInputType(CommonFormBean.INPUT_SELECT);
                                    }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_MEMBER)){
                                        commonFormBean.setInputType(CommonFormBean.INPUT_MEMBER);
                                    }else if(commonFormBean.getRowType().equals( INPUT_OPERATOR)){
                                        commonFormBean.setInputType( INPUT_OPERATOR);
                                    }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_DEPARTMENT)){
                                        commonFormBean.setInputType(CommonFormBean.INPUT_DEPARTMENT);
                                    }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_BRAND_SELECT)){
                                        commonFormBean.setInputType(CommonFormBean.INPUT_BRAND_SELECT);
                                    }else{
                                        if(commonFormBean.getInputType().equals(CommonFormBean.LONG_TEXT)){
                                            commonFormBean.setItemType(CommonFormBean.IT_BIG_INPUT);
                                        }else if(commonFormBean.getInputType().equals(CommonFormBean.NUMBWE_PRICE_TEXT)){
                                            commonFormBean.setKeyBoardType(CommonFormBean.KBDOUBLE);
                                            commonFormBean.setInputType(CommonFormBean.SHORT_TEXT);
                                        }else {
                                            if(commonFormBean.getKeyBoardType()!=null &&commonFormBean.getKeyBoardType().equals(CommonFormBean.KBDOUBLE) ){
                                                commonFormBean.setKeyBoardType(CommonFormBean.KBDOUBLE);
                                            }
                                            if(commonFormBean.getKeyBoardType()!=null &&commonFormBean.getKeyBoardType().equals(CommonFormBean.KBDEFAULT) ){
                                                commonFormBean.setKeyBoardType(CommonFormBean.KBDEFAULT);
                                            } if(commonFormBean.getKeyBoardType()!=null &&commonFormBean.getKeyBoardType().equals(CommonFormBean.KBINTEGER) ){
                                                commonFormBean.setKeyBoardType(CommonFormBean.KBINTEGER);
                                            }
                                            commonFormBean.setInputType(CommonFormBean.SHORT_TEXT);
                                        }
                                    }

                                }else if(commonFormBean.getRowType().equals(CommonFormBean.FILE_INPUT)){
                                    commonFormBean.setItemType(CommonFormBean.IT_FILE_INPUT);
                                }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_TITLE)){
                                    commonFormBean.setItemType(CommonFormBean.IT_TITLE_INPUT);
                                }else if(commonFormBean.getRowType().equals(CommonFormBean.INPUT_TOGGLEBUTTON)){
                                    commonFormBean.setItemType(CommonFormBean.IT_TOGGLEBUTTON_INPUT);
                                }
                                commonFormBeanList.add(commonFormBean);
                            }

                            jiuyiCommonFormAdapter = new JiuyiCommonFormAdapter(commonFormBeanList);
                            if(!Func.IsStringEmpty(viewOperatorType)){
                                jiuyiCommonFormAdapter.setViewOperatorType(viewOperatorType);
                            }
                            jiuyiCommonFormAdapter.bindToRecyclerView(rvContent);
                            jiuyiCommonFormAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    CommonFormBean contentFormBean= (CommonFormBean) adapter.getData().get(position);
                                    tvValue=(TextView)view.findViewById(R.id.tv_value);
                                    currentPos=position;
                                    if(contentFormBean!=null && contentFormBean.getInputType()!=null){
                                        if(contentFormBean.getInputType().equals(CommonFormBean.DATE_SELECT)){
                                            DatePickDialog dialog = new DatePickDialog(JiuyiCommonFormActivity.this);
                                            //设置上下年分限制
                                            dialog.setYearLimt(60);
                                            //设置标题
                                            dialog.setTitle("选择时间");
                                            //设置类型
                                             if(contentFormBean.getPattern()!=null ){
                                                 if(contentFormBean.getPattern().toLowerCase().equals("yyyy-mm-dd hh:mm:ss")){
                                                     dialog.setType(DateType.TYPE_ALL);
                                                 }else if(contentFormBean.getPattern().toLowerCase().equals("yyyy-mm-dd hh:mm")){
                                                     dialog.setType(DateType.TYPE_YMDHM);
                                                 }else if(contentFormBean.getPattern().toLowerCase().equals("yyyy-mm-dd hh")){
                                                     dialog.setType(DateType.TYPE_YMDH);
                                                 }else if(contentFormBean.getPattern().toLowerCase().equals("yyyy-mm-dd")){
                                                     dialog.setType(DateType.TYPE_YMD);
                                                 }else if(contentFormBean.getPattern().toLowerCase().equals("hh:mm")){
                                                     dialog.setType(DateType.TYPE_HM);
                                                 }else if(contentFormBean.getPattern().toLowerCase().equals("yyyyMM")){
                                                     dialog.setType(DateType.TYPE_YM);
                                                 }

                                            }else {
                                                //设置类型
                                                dialog.setType(DateType.TYPE_YMD);
                                            }
//                                            设置消息体的显示格式，日期格式
                                            if(contentFormBean.getPattern()!=null){
                                                dialog.setMessageFormat(contentFormBean.getPattern().toString());
                                            }else {
                                                dialog.setMessageFormat("yyyy-MM-dd");
                                            }

                                            //设置选择回调
                                            dialog.setOnChangeLisener(null);
                                            //设置点击确定按钮回调
                                            dialog.setOnSureLisener(new OnSureLisener() {
                                                @Override
                                                public void onSure(Date date) {
                                                    if(contentFormBean.getPattern()!=null){
                                                        tvValue.setText( new SimpleDateFormat(contentFormBean.getPattern()).format(date));
                                                    }else {
                                                        tvValue.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                                                    }

                                                    contentFormBean.setValue(tvValue.getText().toString());
                                                }
                                            });
                                            dialog.setOnCancelListener(new OnCancelLisener() {
                                                @Override
                                                public void onCancel(String s) {
                                                    tvValue.setText("");
                                                    contentFormBean.setValue("");
                                                }
                                            });
                                            dialog.show();
                                        }else if(contentFormBean.getInputType().equals(CommonFormBean.INPUT_SELECT) ){
                                            if(contentFormBean.getKey()!=null  ){
                                                currentPos=position;
                                                if(contentFormBean.getKey().equals("reimbursementCity")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"BXCITY");
                                                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "300");
                                                    changePage(mBundle, Pub.CustomerSelect,true);
                                                }
                                                else if(contentFormBean.getKey().equals("city")){
                                                    if(contentFormBean.getValue()!=null){
                                                        String jsonData = GsonUtil.gson().toJson(contentFormBean.getValue());
                                                        if(Func.isJsonObject(jsonData) ){
                                                            Gson gs = new Gson();
                                                            CustomerStoreInfoBean.ContentBean.CityBean bean = gs.fromJson(jsonData, CustomerStoreInfoBean.ContentBean.CityBean.class);
                                                            if(bean!=null){
                                                                provinceId=bean.getProvinceId();
                                                            }
                                                        }

                                                    }
                                                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CITY");
                                                    mBundle.putString(JiuyiBundleKey.PARAM_PROVINCESELECT,provinceId);
                                                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "301");
                                                    changePage(mBundle, Pub.CustomerSelect,true);
                                                }else if(contentFormBean.getKey().equals("area")){
                                                    if(currentPos>0 && jiuyiCommonFormAdapter.getData().get(currentPos-1).getValue()!=null){
                                                        String jsonData = GsonUtil.gson().toJson(jiuyiCommonFormAdapter.getData().get(currentPos-1).getValue());
                                                        if(Func.isJsonObject(jsonData) ){
                                                            Gson gs = new Gson();
                                                            CustomerStoreInfoBean.ContentBean.CityBean bean = gs.fromJson(jsonData, CustomerStoreInfoBean.ContentBean.CityBean.class);
                                                            if(bean!=null){
                                                                cityId=bean.getCityId();
                                                            }
                                                        }

                                                    }
                                                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"AREA");
                                                    mBundle.putString(JiuyiBundleKey.PARAM_BILLID,cityId);
                                                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "302");
                                                    changePage(mBundle, Pub.CustomerSelect,true);
                                                }
                                            }
                                            if(contentFormBean.getDictName()!=null){
                                                if(!contentFormBean.isMutiAble()){
                                                    AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCommonFormActivity.this);
                                                    buidler.setTitle(contentFormBean.getLeftContent());
                                                    final String[] data;
                                                    final List<DictBean> dictBeansList= DictUtils.getDictList(contentFormBean.getDictName());
                                                    if(dictBeansList!=null &&dictBeansList.size()>0){
                                                        data=new String[dictBeansList.size()];
                                                        for(int i=0;i<dictBeansList.size();i++){
                                                            data[i]=dictBeansList.get(i).getValue();
                                                        }
                                                        buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                tvValue.setText(data[which].trim());
                                                                contentFormBean.setValue(dictBeansList.get(which).getKey());
                                                                contentFormBean.setRightContent(dictBeansList.get(which).getValue());
                                                                if(contentFormBean.getKey()!=null && contentFormBean.getKey().equals( "storeType" ) ){
                                                                    if( contentFormBean.getValue().equals( "0" )){
                                                                        for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                                                                            if(jiuyiCommonFormAdapter.getData().get( i ).getKey()!=null && jiuyiCommonFormAdapter.getData().get( i ).getKey().equals( "whetherToMonopolize") ){
                                                                                jiuyiCommonFormAdapter.getData().get( i ).setValue(false);
                                                                                jiuyiCommonFormAdapter.getData().get( i ).setEditAble( false );
                                                                            }
                                                                        }
                                                                    }else if(contentFormBean.getValue().equals( "1" )){
                                                                        for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                                                                            if(jiuyiCommonFormAdapter.getData().get( i ).getKey()!=null && jiuyiCommonFormAdapter.getData().get( i ).getKey().equals( "whetherToMonopolize") ){
                                                                                jiuyiCommonFormAdapter.getData().get( i ).setEditAble( true );
                                                                            }
                                                                        }
                                                                    }
                                                                    handler.post(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            jiuyiCommonFormAdapter.notifyDataSetChanged();
                                                                        }
                                                                    });

                                                                }
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                        buidler.show();
                                                    }
                                                }else {
                                                    AlertDialog.Builder buidler = new AlertDialog.Builder( JiuyiCommonFormActivity.this );
                                                    buidler.setTitle( contentFormBean.getLeftContent() );

                                                    final boolean b[];
                                                    final String[] data;
                                                    final List<DictBean> dictBeansList = DictUtils.getDictList( contentFormBean.getDictName() );
                                                    if (dictBeansList != null && dictBeansList.size() > 0) {
                                                        data = new String[dictBeansList.size()];
                                                        b = new boolean[dictBeansList.size()];
                                                        for (int i = 0; i < dictBeansList.size(); i++) {
                                                            data[i] = dictBeansList.get( i ).getValue();
                                                            b[i] = false;
                                                        }
                                                        buidler.setMultiChoiceItems( data , b , new DialogInterface.OnMultiChoiceClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog , int which , boolean isChecked) {
                                                                //    将客户是否被勾选的记录保存到集合中
                                                                b[which] = isChecked;  //保存客户选择的属性是否被勾选
                                                            }
                                                        } );
                                                        //设置一个确定和取消按钮
                                                        buidler.setPositiveButton( "确定" , new DialogInterface.OnClickListener() {
                                                            //保存数据
                                                            @Override
                                                            public void onClick(DialogInterface dialog , int which) {
                                                                String item = "";
                                                                //取出被勾选中的内容
                                                                ArrayList selectList = new ArrayList<>();
                                                                for (int i = 0; i < dictBeansList.size(); i++) {
                                                                    if (b[i]) {             //如果被勾线则保存数据
                                                                        item += data[i] + " ,";
                                                                        selectList.add( dictBeansList.get( i ).getKey() );
                                                                    }
                                                                }
                                                                if (!Func.IsStringEmpty( item )) {
                                                                    String sValue = item.substring( 0 , item.length() - 1 );
                                                                    tvValue.setText( sValue );
                                                                    contentFormBean.setValue( selectList );
                                                                }
                                                                //对话框消失
                                                                dialog.dismiss();
                                                            }
                                                        } );
                                                        buidler.setNegativeButton( "取消" , new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog , int which) {
                                                                dialog.dismiss();
                                                            }
                                                        } );
                                                        buidler.show();
                                                    }
                                                }
                                            }

                                        }else if(contentFormBean.getInputType().equals(CommonFormBean.INPUT_MEMBER)){
                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CUSTOMER");
                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "100");
                                            changePage(mBundle, Pub.CustomerSelect,true);
                                        }else if(contentFormBean.getInputType().equals(CommonFormBean.INPUT_DEPARTMENT)){
                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"DEPARTMENT");
                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "101");
                                            changePage(mBundle, Pub.CustomerSelect,true);
                                        }else if(contentFormBean.getInputType().equals( INPUT_OPERATOR)){
                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "200");
                                            changePage(mBundle, Pub.CustomerSelect,true);
                                        }else if(contentFormBean.getInputType().equals(CommonFormBean.INPUT_BRAND_SELECT)){
                                            Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                                            Bundle mBundle = new Bundle();
                                            if(mProductTypeSelectList==null){
                                                mProductTypeSelectList=new ArrayList<>();
                                            }
                                            mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mProductTypeSelectList);
                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"BRAND");
                                            intent.putExtras(mBundle);
                                            startActivityForResult(intent, 206);
                                        }
                                    }
                                }
                            });
                            if(!Func.IsStringEmpty(viewOperatorType)){
                                jiuyiCommonFormAdapter.setViewOperatorType(viewOperatorType);
                            }
                            rvContent.setAdapter(jiuyiCommonFormAdapter);
                        }
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


    public void updateInfo(){
        if(jiuyiCommonFormAdapter!=null && jiuyiCommonFormAdapter.getData().size()>0 ){
            for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                if(jiuyiCommonFormAdapter.getData().get(i).getKey()!=null){
                    if(jiuyiCommonFormAdapter.getData().get(i).getInputType()!=null &&jiuyiCommonFormAdapter.getData().get(i).getInputType().equals(INPUT_OPERATOR )
                            &&jiuyiCommonFormAdapter.getData().get(i).getValue()!=null && jiuyiCommonFormAdapter.getData().get(i).getValue().equals("无")){
                        beanMap.put(jiuyiCommonFormAdapter.getData().get(i).getKey(),null);
                    }else {
                        if(!(jiuyiCommonFormAdapter.getData().get(i).getKey().equals("btcc") && backPageType.equals("travel-business"))){
                            beanMap.put(jiuyiCommonFormAdapter.getData().get(i).getKey(),jiuyiCommonFormAdapter.getData().get(i).getValue());
                        }
                    }

                    if(jiuyiCommonFormAdapter.getData().get(i).getItemType()==CommonFormBean.IT_FILE_INPUT /*&& attachmentsBeanslist!=null && attachmentsBeanslist.size()>0 */){
                        beanMap.put(jiuyiCommonFormAdapter.getData().get(i).getKey(),attachmentsBeanslist);
                    }
                }


            }
            beanMap.put("id",beanid);
        }
//        if(backPageType.equals("travel-business")){
//            beanMap.put("commit",true);
//        }
        JiuyiHttp.PUT(backPageType+"/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(beanMap))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        Rc.mBackfresh=true;
                        EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
                        if(data!=null){
                            startDialog(DialogID.DialogTrue, "", "更新成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle;
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK) { //
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  // 检测sd是否可用
                        Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    if(!Func.IsStringEmpty(Rc.picVideoUrl)){
                        filePath=Rc.picVideoUrl;
                    }

                    //优化压缩图片
                    Uri uri = null;
                    File file1 = new File(filePath);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                    Media media=new Media();
                    media.setExtension(".jpg");
                    media.setMediaType(0);
                    media.setPath(newFile.getPath());
                    jiuyiCommonFormAdapter.adapter.getMviewBeanList().add(media);
                    jiuyiCommonFormAdapter.adapter.notifyDataSetChanged();
                    jiuyiCommonFormAdapter.refreshCount();
                }
                break;
            case SELECT_PICTURE:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                ArrayList<Media> selects = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                jiuyiCommonFormAdapter.setMediaArrayList(selects);
                jiuyiCommonFormAdapter.adapter.setMviewBeanList(selects);
                jiuyiCommonFormAdapter.adapter.notifyDataSetChanged();
                jiuyiCommonFormAdapter.refreshCount();
                break;
            case 100:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                MemberBean.ContentBean contentBean=bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
                if(contentBean!=null && contentBean.getOrgName()!=null ){
                    setDefaultData(contentBean);
                }
                break;
            case 101:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                String name=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long id=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(!Func.IsStringEmpty(name) && id>0 ){
                    tvValue.setText(name);
                    DepartmentBean.DepartmentBeanID departmentBeanID=new DepartmentBean.DepartmentBeanID ();
                    departmentBeanID.setId(id);
                    departmentBeanID.setName(name);
                    if(currentPos>=0){
                        jiuyiCommonFormAdapter.getData().get(currentPos).setValue(departmentBeanID);
                    }
                }

                break;
            case 103:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                tvValue.setText(bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME));
//                MemberBeanID memberBeanID=new MemberBeanID();
//                memberBeanID.setId(bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID));
//                if(currentPos>=0){
//                    jiuyiCommonFormAdapter.getData().get(currentPos).setValue(memberBeanID);
//                }
                break;
            case 200:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                String name2=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long valueId=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                String sOAcode=bundle.getString(JiuyiBundleKey.PARAM_COMMONCODE);
                if(!Func.IsStringEmpty(name2) && valueId>0 && currentPos>=0){
                    tvValue.setText(name2);
                    NormalOperatorBean.ContentBean operatorBeanID=new NormalOperatorBean.ContentBean();
                    operatorBeanID.setName(name2);
                    operatorBeanID.setId(valueId);
                    operatorBeanID.setOaCode(sOAcode);
                    jiuyiCommonFormAdapter.getData().get(currentPos).setValue(operatorBeanID);
                    jiuyiCommonFormAdapter.getData().get(currentPos).setSingleValue(sOAcode);
                }else{
                    tvValue.setText("无");
                    jiuyiCommonFormAdapter.getData().get(currentPos).setRightContent( "无" );
                    jiuyiCommonFormAdapter.getData().get(currentPos).setValue("无");
                    jiuyiCommonFormAdapter.getData().get(currentPos).setSingleValue(null);
                }
                String city="";
                for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                    if(jiuyiCommonFormAdapter.getData().get( i ).getKey()!=null && jiuyiCommonFormAdapter.getData().get( i ).getKey().equals( "reimbursementCity" )
                            &&! Func.IsStringEmpty(jiuyiCommonFormAdapter.getData().get( i ).getValue().toString() )){
                        city=jiuyiCommonFormAdapter.getData().get( i ).getValue().toString().trim();
                    }
                }
                getStandard(city,Rc.mobilecode,sOAcode);
                break;
            case 300:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                String name3=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                if(!Func.IsStringEmpty(name3)  && currentPos>=0){
                    tvValue.setText(name3);
                    jiuyiCommonFormAdapter.getData().get(currentPos).setValue(name3);
                }else{
                    tvValue.setText("");
                    jiuyiCommonFormAdapter.getData().get(currentPos).setRightContent( "必填" );
                    jiuyiCommonFormAdapter.getData().get(currentPos).setValue("");
                }
                String sOAcode2="";
                for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                    if(jiuyiCommonFormAdapter.getData().get( i ).getKey()!=null && jiuyiCommonFormAdapter.getData().get( i ).getKey().equals( "cohabitOperator" )
                            && jiuyiCommonFormAdapter.getData().get( i ).getValue()!=null ){
                        String jsonData = GsonUtil.gson().toJson(jiuyiCommonFormAdapter.getData().get( i ).getValue());
                        Gson gs = new Gson();
                        if(Func.isJsonObject(jsonData) ){
                            NormalOperatorBean.ContentBean bean = gs.fromJson(jsonData, NormalOperatorBean.ContentBean.class);//把JSON字符串转为对象
                            if(bean!=null){
                                sOAcode2=bean.getOaCode();
                            }
                        }
                        break;
                      }
                }

                getStandard(name3,Rc.mobilecode,sOAcode2);
                break;
            case 301:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                CitySearchBean.ContentBean citySearchBean=bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
                if(citySearchBean!=null && currentPos>=0){
                    jiuyiCommonFormAdapter.getData().get(currentPos).setRightContent(citySearchBean.getCityName() );
                    jiuyiCommonFormAdapter.getData().get(currentPos).setValue(citySearchBean);
                    jiuyiCommonFormAdapter.getData().get(currentPos+1).setRightContent("请选择" );
                    jiuyiCommonFormAdapter.getData().get(currentPos+1).setValue(null);
                    cityId=citySearchBean.getCityId();
                    jiuyiCommonFormAdapter.notifyDataSetChanged();
                }
                break;
            case 302:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                RegionSearchBean.ContentBean areaBean=bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
                if(areaBean!=null && currentPos>=0){
                    jiuyiCommonFormAdapter.getData().get(currentPos).setRightContent(areaBean.getAreaName() );
                    jiuyiCommonFormAdapter.getData().get(currentPos).setValue(areaBean);
                    jiuyiCommonFormAdapter.notifyDataSetChanged();
                }
                break;
            case 206:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                BrandBean.ContentBean brandBean=bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
                if(brandBean!=null && currentPos>=0){
                    jiuyiCommonFormAdapter.getData().get(currentPos).setRightContent(brandBean.getBrandName());
                    jiuyiCommonFormAdapter.getData().get(currentPos).setValue(brandBean.getBrandName());
                    jiuyiCommonFormAdapter.notifyDataSetChanged();
                }
              /*  mProductTypeSelectList=bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
                String brandName="";
                String brandID="";
                if(mProductTypeSelectList!=null && mProductTypeSelectList.size()>0 && currentPos>=0){
                    for(int i=0;i<mProductTypeSelectList.size();i++){
                        brandName+=mProductTypeSelectList.get(i).getBrandName()+",";
                        brandID+=mProductTypeSelectList.get(i).getId()+"-";
                    }
                    beanMap.put("brandId",brandID.substring(0,brandID.length()-1));
                    jiuyiCommonFormAdapter.getData().get(currentPos).setValue(brandName.substring(0,brandName.length()-1));
                    jiuyiCommonFormAdapter.getData().get(currentPos).setRightContent(brandName.substring(0,brandName.length()-1));
                    jiuyiCommonFormAdapter.notifyDataSetChanged();
//                    jiuyiCommonFormAdapter.notifyItemChanged(currentPos);
                }*/

                break;

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // 刷新数据
     if(jiuyiCommonFormAdapter!=null){
         jiuyiCommonFormAdapter.attachRefresh();
     }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void getProvinceInfo(String code,int i) {
        JiuyiHttp.GET("region/findProvinceByProvinceId/"+code)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ProvinceBean.ContentBean>() {
                    @Override
                    public void onSuccess(ProvinceBean.ContentBean contentBean) {
                        if(contentBean!=null){
                            jiuyiCommonFormAdapter.getData().get(i).setValue(contentBean);
                            jiuyiCommonFormAdapter.notifyDataSetChanged();
//                            beanMap.put("province",contentBean);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                    }
                });

    }

    private void getCityInfo(String code,int i) {
        JiuyiHttp.GET("region/findCityByCityId/"+code)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<CustomerStoreInfoBean.ContentBean.CityBean>() {
                    @Override
                    public void onSuccess(CustomerStoreInfoBean.ContentBean.CityBean contentBean) {
                        if(contentBean!=null){
                            jiuyiCommonFormAdapter.getData().get(i).setValue(contentBean);
                            jiuyiCommonFormAdapter.notifyDataSetChanged();
//                            beanMap.put("city",contentBean);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                    }
                });

    }
    private void getAreaInfo(String code,int i) {
        JiuyiHttp.GET("region/findAreaByAreaId/"+code)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ProvinceBean.AreaBean>() {
                    @Override
                    public void onSuccess(ProvinceBean.AreaBean contentBean) {
                        if(contentBean!=null){
                            jiuyiCommonFormAdapter.getData().get(i).setValue(contentBean);
                            jiuyiCommonFormAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                    }
                });

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
                            for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                                if(jiuyiCommonFormAdapter.getData().get( i ).getKey()!=null){
                                    if( jiuyiCommonFormAdapter.getData().get( i ).getKey().equals( "stayReimbursementStandard" )
                                           ){
                                        if(!Func.IsStringEmpty(tripStandardBean.getZsbz()  )){
                                            jiuyiCommonFormAdapter.getData().get( i ).setValue(Double.parseDouble( tripStandardBean.getZsbz() )+"");
                                        }else{
                                            jiuyiCommonFormAdapter.getData().get( i ).setValue("0");
                                        }

                                    }else if( jiuyiCommonFormAdapter.getData().get( i ).getKey().equals( "cityTrafficReimbursementStandard" )
                                            ){
                                        if(!Func.IsStringEmpty(tripStandardBean.getJtbz()  )){
                                            jiuyiCommonFormAdapter.getData().get( i ).setValue(Double.parseDouble( tripStandardBean.getJtbz())+"");
                                        }else{
                                            jiuyiCommonFormAdapter.getData().get( i ).setValue("0");
                                        }

                                    } else if( jiuyiCommonFormAdapter.getData().get( i ).getKey().equals( "mealAllowance" )
                                            ){
                                        if(!Func.IsStringEmpty(tripStandardBean.getCbbz()  )){
                                            jiuyiCommonFormAdapter.getData().get( i ).setValue(Double.parseDouble( tripStandardBean.getCbbz())+"");
                                        }else{
                                            jiuyiCommonFormAdapter.getData().get( i ).setValue("0");
                                        }

                                    }
                                }

                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    jiuyiCommonFormAdapter.notifyDataSetChanged();
                                }
                            });

                        }
                    }
                    @Override
                    public void onFail(int errCode, String errMsg) {
                        JiuyiLog.e("getsig","request errorCode:" + errCode + ",errorMsg:" + errMsg);
                    }
                });
    }

    public void getCustomerDetail(long id){
        JiuyiHttp.GET("member/detail/"+id)
                .tag("request_get__check"+backPageType)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<MemberBean.ContentBean>() {
                    @Override
                    public void onSuccess(MemberBean.ContentBean memberBean) {
                        if(memberBean!=null){
                            setDefaultData(memberBean);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        JiuyiLog.e("getsig","request errorCode:" + errCode + ",errorMsg:" + errMsg);
                    }
                });

    }
    public void setDefaultData(MemberBean.ContentBean contentBean){
        if(contentBean==null){
            return;
        }
        if(backPageType.equals("storme-manage-entity") && jiuyiCommonFormAdapter!=null && jiuyiCommonFormAdapter.getData()!=null && jiuyiCommonFormAdapter.getData().size()>0 ){
            for(int i=0;i<jiuyiCommonFormAdapter.getData().size();i++){
                if(jiuyiCommonFormAdapter.getData().get(i).getKey()!=null ){
                    if(jiuyiCommonFormAdapter.getData().get(i).getKey().equals("customer") &&contentBean.getOrgName()!=null
                            ){
                        MemberBeanID memberBeanID=new MemberBeanID();
                        memberBeanID.setOrgName(contentBean.getOrgName());
                        if(contentBean.getAbbreviation()!=null){
                            memberBeanID.setAbbreviation(contentBean.getAbbreviation());
                        }
                        memberBeanID.setId(contentBean.getId());
                        jiuyiCommonFormAdapter.getData().get(i).setValue(memberBeanID);

                    }
                    if(jiuyiCommonFormAdapter.getData().get(i).getKey().equals("province") &&contentBean.getProvinceName()!=null
                            &&contentBean.getProvinceNumber()!=null  ){
                        jiuyiCommonFormAdapter.getData().get(i).setRightContent(contentBean.getProvinceName());
                        provinceId=contentBean.getProvinceNumber();
                        getProvinceInfo(contentBean.getProvinceNumber(),i);

                    }
                    if(jiuyiCommonFormAdapter.getData().get(i).getKey().equals("city")){
                        if(contentBean.getCityName()!=null && contentBean.getCityNumber()!=null){
                            jiuyiCommonFormAdapter.getData().get(i).setRightContent(contentBean.getCityName());
                            cityId=contentBean.getCityNumber();
                            getCityInfo(contentBean.getCityNumber(),i);
                        }else{
                            jiuyiCommonFormAdapter.getData().get(i).setRightContent("请选择");
                            jiuyiCommonFormAdapter.getData().get(i).setValue( null );
                            cityId="";
                        }

                    }
                    if(jiuyiCommonFormAdapter.getData().get(i).getKey().equals("area") ){
                        if(contentBean.getAreaName()!=null && contentBean.getAreaNumber()!=null){
                            jiuyiCommonFormAdapter.getData().get(i).setRightContent(contentBean.getAreaName());
                            getAreaInfo(contentBean.getAreaNumber(),i);
                        }else {
                            jiuyiCommonFormAdapter.getData().get(i).setRightContent("请选择");
                            jiuyiCommonFormAdapter.getData().get(i).setValue( null );
                        }

                    }
                    if(jiuyiCommonFormAdapter.getData().get(i).getKey().equals("forPublicHouseholds") &&contentBean.getOrgName()!=null
                            ){
                        jiuyiCommonFormAdapter.getData().get(i).setValue(contentBean.getOrgName());
                        jiuyiCommonFormAdapter.getData().get(i).setRightContent(contentBean.getOrgName());
                    }
                    if( jiuyiCommonFormAdapter.getData().get(i).getKey().equals("legalName") &&contentBean.getLegalName()!=null
                            ){
                        jiuyiCommonFormAdapter.getData().get(i).setValue(contentBean.getLegalName());
                        jiuyiCommonFormAdapter.getData().get(i).setRightContent(contentBean.getLegalName());
                    }
                    if(jiuyiCommonFormAdapter.getData().get(i).getKey().equals("contactNumber") &&contentBean.getCompanyPhone()!=null
                            ){
                        jiuyiCommonFormAdapter.getData().get(i).setValue(contentBean.getCompanyPhone());
                        jiuyiCommonFormAdapter.getData().get(i).setRightContent(contentBean.getCompanyPhone());
                    }
                    if( jiuyiCommonFormAdapter.getData().get(i).getKey().equals("faxNumber") &&contentBean.getFaxNumber()!=null
                            ){
                        jiuyiCommonFormAdapter.getData().get(i).setValue(contentBean.getFaxNumber());
                        jiuyiCommonFormAdapter.getData().get(i).setRightContent(contentBean.getFaxNumber());
                    }
                    if( jiuyiCommonFormAdapter.getData().get(i).getKey().equals("afterSalesCall") &&contentBean.getAfterSalesNumber()!=null
                            ){
                        jiuyiCommonFormAdapter.getData().get(i).setValue(contentBean.getAfterSalesNumber());
                        jiuyiCommonFormAdapter.getData().get(i).setRightContent(contentBean.getAfterSalesNumber());
                    }
                }


            }
            jiuyiCommonFormAdapter.notifyDataSetChanged();
        }

    }


}
