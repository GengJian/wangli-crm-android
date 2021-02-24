package customer.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDialog;
import com.bobomee.android.mentions.edit.MentionEditText;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.dalong.recordlib.RecordVideoActivity;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.model.DictBean;
import com.jiuyi.model.DictResultBean;
import com.jiuyi.tools.DictUtils;
import com.nanchen.compresshelper.CompressHelper;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.bean.NormalOperatorBean;
import commonlyused.db.DbHelper;
import commonlyused.db.DictBeanDao;
import customer.Utils.BitmapUtils;
import customer.Utils.FastUtils;
import customer.Utils.FileUtils;
import customer.Utils.ViewOperatorType;
import customer.adapter.IntelligenceDetailPictureAdapter;
import customer.adapter.IntelligenceDetailVideoAdapter;
import customer.entity.AttachmentBean;
import customer.entity.ClientTag;
import customer.entity.CustomerComplaintBean;
import customer.entity.DeptNewBean;
import customer.entity.ImageBean;
import customer.entity.MarketDynamicBean;
import customer.entity.Media;
import customer.entity.MemberBeanID;
import customer.entity.PersonSelectBean;
import customer.entity.UploadTokenBean;
import customer.entity.User;
import customer.listener.PickerConfig;
import customer.view.Bimp;
import customer.view.DrawableTextView;
import customer.view.SelectPicPopupWindow;
import customer.view.SelectVideoPopupWindow;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.entity.DynamicBean;

import static customer.activity.JiuyiCustomerInfomationActivity.SELECT_PICTURE;
import static customer.activity.JiuyiCustomerInfomationActivity.SELECT_Video;
import static customer.activity.JiuyiCustomerInfomationActivity.TAKE_PICTURE;
import static customer.activity.JiuyiCustomerInfomationActivity.TAKE_Video;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewMarketDynamicActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 市场动态新增页面
 *****************************************************************
 */

public class JiuyiCustomerNewMarketDynamicActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,mtvcancel;
    private TextView tvType;
    private MentionEditText etContent;
    private TextView tvVisibleDept;
    private long sType =-1, sStatus=-1;
    private long Customerid=-1;
    private String CustomerName="";
    private String viewOperatorType;
    private long beanid=-1;
    private List<AttachmentBean> attachmentsBeanslist,attachmentsBeansVideolist;
    private List<DeptNewBean.ContentBean> departmentVoList=new ArrayList<>();
    private List<DeptNewBean.ContentBean> deptList=new ArrayList<>();
    List<DeptNewBean.ContentBean> deptSelectList;
    private LoadingDialog dialog1;
    private String bill_type="";
    List<DictBean> complaintBeansList;
    private LinearLayout ll_dept;
    private View view_dept;
    private DrawableTextView tvClient;
    private DrawableTextView tvAt;
    private DrawableTextView tvPicture;
    private DrawableTextView tvVideo;
    private JiuyiButton btnSave;
    protected SelectVideoPopupWindow menuVideoWindow;
    protected SelectPicPopupWindow menuWindow;
    private List<PersonSelectBean> mPersonBeanSelectList;
    private static final int PERSON_CODE = 800;
    private RecyclerView rv_piclist,rv_videolist;
    IntelligenceDetailVideoAdapter intelligenceDetailVideoAdapter;
    IntelligenceDetailPictureAdapter intelligenceDetailPictureAdapter;
    protected String filePath,videoPath;
    private String oriContent="";
    private  File[] files,fileVideos,filesTotal;
    private DynamicBean.ContentBean dycreateBean;

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        CustomerName=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewmarketdynamic_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        complaintBeansList= DictUtils.getDictList("customer_complaints_status");
        if(viewOperatorType.equals(ViewOperatorType.ADD)){
            getDeptList();
        }
        if(beanid>0 && !viewOperatorType.equals(ViewOperatorType.ADD)){
            getProductinfoList(beanid);
        }
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        mtvcomplete.setText("新情报");
        mtvcomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新情报");
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SINGLEADD);
                changePage(mBundle, Pub.Customer_InformationNew, false);
            }
        });

        mtvcancel = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_cancel"));
        if (mtvcancel != null) {
            mtvcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
                }
            });
        }
    }
    private void upload(File[] files)
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
                                    attachmentsBeanslist=new ArrayList<>();
//                                    attachmentsBeansVideolist=new ArrayList<>();
                                    for(int i=0;i<data.size();i++){
                                        AttachmentBean attachmentBean=data.get(i);
                                        attachmentsBeanslist.add(attachmentBean);
                                    }
                                    dycreateBean.setAttachmentList(attachmentsBeanslist);
                                    submit();
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
        tvType = (TextView) mBodyLayout.findViewById(R.id.tv_type);
        ll_dept= (LinearLayout) mBodyLayout.findViewById(R.id.ll_dept);
        view_dept= (View) mBodyLayout.findViewById(R.id.view_dept);
        etContent = (MentionEditText) mBodyLayout.findViewById(R.id.et_content);
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1 && !TextUtils.isEmpty(s)) {
                    char mentionChar = s.toString().charAt(start);
                    int selectionStart = etContent.getSelectionStart();
                    if (mentionChar == '@') {
                        Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
                        intent.putExtras(mBundle);
                        startActivityForResult(intent, 200);
                        etContent.getText().delete(selectionStart - 1, selectionStart);
                    } else if (mentionChar == '$') {
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                        mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                        changePage(mBundle,Pub.CustomerSelect,true);
                        etContent.getText().delete(selectionStart - 1, selectionStart);
                    }
                }
            }

            @Override public void afterTextChanged(Editable s) {
            }
        });
        tvVisibleDept = (TextView) mBodyLayout.findViewById(R.id.tv_visibleDept);
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewMarketDynamicActivity.this);
                buidler.setTitle("市场动态类型");
                final String[] data;
                final List<DictBean> dictBeansList = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                        .where(DictBeanDao.Properties.Name.eq("market_trend_type")).where(DictBeanDao.Properties.Key.notEq("all"))
                        .where(DictBeanDao.Properties.Key.notEq("All"))
                        .where(DictBeanDao.Properties.Key.notEq("ALL")).build().list();
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvType.setText(data[which].trim());
                            sType=dictBeansList.get(which).getOriginalid();
                            bill_type=dictBeansList.get(which).getKey();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });

        rv_piclist = mBodyLayout.findViewById(R.id.rv_piclist);
        GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(),5);
        rv_piclist.setLayoutManager(mgr);
        rv_piclist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));


        rv_videolist = mBodyLayout.findViewById(R.id.rv_videolist);
        GridLayoutManager mgr2 = new GridLayoutManager(JiuyiMainApplication.getIns(),5);
        rv_videolist.setLayoutManager(mgr2);
        rv_videolist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));


        intelligenceDetailPictureAdapter = new IntelligenceDetailPictureAdapter(JiuyiMainApplication.getIns(),null/*contentBean.getImageList()*/);
        rv_piclist.setAdapter(intelligenceDetailPictureAdapter);
        intelligenceDetailPictureAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailPictureAdapter.OnRecyclerViewItemListener(){
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                intent.putExtra(PickerConfig.PRE_RAW_LIST, intelligenceDetailPictureAdapter.getMviewBeanList());
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT,9);  //最大选择数量，默认40（非必填参数）
                intent.putExtra(PickerConfig.CURRENT_POSITION,position);  //最大选择数量，默认40（非必填参数）
                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 1500);
            }
        });
        intelligenceDetailPictureAdapter.setOnCLickItemListener(new IntelligenceDetailPictureAdapter.onCLickItemListener() {
            @Override
            public void click(int position, String colname, View view) {
                if(colname.equals("img_delete")){
                    intelligenceDetailPictureAdapter.getMviewBeanList().remove(position);
                    intelligenceDetailPictureAdapter.notifyDataSetChanged();
                }

            }
        });

        intelligenceDetailVideoAdapter = new IntelligenceDetailVideoAdapter(JiuyiMainApplication.getIns(),null);
        rv_videolist.setAdapter(intelligenceDetailVideoAdapter);
        intelligenceDetailVideoAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailVideoAdapter.OnRecyclerViewItemListener(){
            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                intent.putExtra(PickerConfig.PRE_RAW_LIST, intelligenceDetailVideoAdapter.getMviewBeanList());
                intent.putExtra(PickerConfig.MAX_SELECT_COUNT,PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT);  //最大选择数量，默认9（非必填参数）
                intent.putExtra(PickerConfig.CURRENT_POSITION,position);  //最大选择数量，默认40（非必填参数）
                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 200);
            }
        });
        intelligenceDetailVideoAdapter.setOnCLickItemListener(new IntelligenceDetailVideoAdapter.onCLickItemListener() {
            @Override
            public void click(int position, String colname, View view) {
                if(colname.equals("img_delete")){
                    intelligenceDetailVideoAdapter.getMviewBeanList().remove(position);
                    intelligenceDetailVideoAdapter.notifyDataSetChanged();
                }

            }
        });

        tvClient = (DrawableTextView) mBodyLayout.findViewById(R.id.tv_client);
        tvClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FastUtils.isFastClick()) {
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                    changePage(mBundle,Pub.CustomerSelect,true);
                }
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
        tvPicture = (DrawableTextView) mBodyLayout.findViewById(R.id.tv_picture);
        tvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intelligenceDetailPictureAdapter!=null && intelligenceDetailPictureAdapter.getItemCount()>=9){
                    Toast.makeText(JiuyiCustomerNewMarketDynamicActivity.this, "已达到选择数量上限", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(JiuyiCustomerNewMarketDynamicActivity.this, "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (FastUtils.isFastClick()) {
                    selectVideos();
                }
            }
        });
        btnSave = (JiuyiButton) mBodyLayout.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean mbcheck=false;
                mbcheck=check();
                if(!mbcheck){
                    return;
                }
                showDialog();
                createBean();
            }
        });



        tvVisibleDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewMarketDynamicActivity.this);
                buidler.setTitle("可选部门");

                final String[] data;
                final boolean b[];
                if(departmentVoList==null||deptList.size()==0){
                    Toast.makeText(JiuyiCustomerNewMarketDynamicActivity.this, "没有可选部门", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (deptList!=null && deptList.size()>0){
                    data=new String[deptList.size()];
                    b=new boolean[deptList.size()];
                    for(int i=0;i<deptList.size();i++){
                        data[i]=deptList.get(i).getName();
                        b[i]=false;
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
                            String item="";
                            //取出被勾选中的内容
                            deptSelectList=new ArrayList<>();
                            for(int i=0;i<deptList.size();i++){
                                if(b[i]){             //如果被勾线则保存数据
                                    item+=data[i]+" ,";
                                    deptSelectList.add(deptList.get(i));
                                }
                            }
                            if(!Func.IsStringEmpty(item)){
                                tvVisibleDept.setText(item.substring(0,item.length()-1));
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
        });


    }
    public void setTitle(){
        if(viewOperatorType!=null && viewOperatorType.equals(ViewOperatorType.EDIT)){
            mTitle = "动态信息";
        }else {
            mTitle = "新建动态";
        }

        setTitle(mTitle);
    }



    public void submit(){
        String field[]=new String[6];
        field[0]="viewed";
        field[1]="liked";
        field[2]="favorited";
        field[3]="likedCount";
        field[4]="viewedCount";
        field[5]="serialVersionUID";


        Map<String,Object> mapResult=FileUtils.jsonObject2(dycreateBean,field);
        JiuyiHttp.POST("feed-flow/create-working-circle/all")
                .setJson(GsonUtil.gson().toJson(mapResult))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
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
    public boolean check(){
        if(TextUtils.isEmpty(etContent.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入内容！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
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

   private void getProductinfoList(long id) {
    JiuyiHttp.GET("market-trend/detail/"+id)
            .tag("request_get_market-trend")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<MarketDynamicBean.ContentBean>() {
                @Override
                public void onSuccess(MarketDynamicBean.ContentBean contentBean) {
                    if(contentBean!=null){
//                        updateBean=contentBean;
                        if(contentBean.getType()!=null){
                            tvType.setText(contentBean.getType().getValue());
                        }

                        if(contentBean.getContent()!=null){
                            etContent.setText(contentBean.getContent());
                        }
                        if(contentBean.getDepartmentVoList()!=null && contentBean.getDepartmentVoList().size()>0){
                            String deptNames="";
                            for(int i=0;i<contentBean.getDepartmentVoList().size();i++){

                                deptNames+=contentBean.getDepartmentVoList().get(i).getName();
                                if(contentBean.getDepartmentVoList().size()>1){
                                    deptNames+=";";
                                }
                            }
                            tvVisibleDept.setText(deptNames);
                        }

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


    public void createBean(){
        if(dycreateBean==null){
            dycreateBean=new DynamicBean.ContentBean();
        }
//        if(sType>0){
//            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//            contentBean.setId(sType);
//            productinfoBean.setType(contentBean);
//        }
//        if(Customerid>0){
//            MemberBeanID memberBeanID=new MemberBeanID();
//            memberBeanID.setId(Customerid);
//            productinfoBean.setMember(memberBeanID);
//        }
//        if(departmentVoList!=null &&departmentVoList.size()>0){
//            dycreateBean.setDepartmentSet(departmentVoList);
//        }
        if(deptSelectList!=null&& deptSelectList.size()>0){
            dycreateBean.setDepartmentSet(deptSelectList);
        }

        if(!Func.IsStringEmpty(etContent.getText().toString())){
            dycreateBean.setContent(etContent.getFormatCharSequence().toString());
        }
        if(intelligenceDetailPictureAdapter.getMviewBeanList()!=null && intelligenceDetailPictureAdapter.getMviewBeanList().size()>0 ){
            files=new File[intelligenceDetailPictureAdapter.getMviewBeanList().size()];
            for(int i=0;i<intelligenceDetailPictureAdapter.getMviewBeanList().size();i++){
                if(intelligenceDetailPictureAdapter.getMviewBeanList().get(i).getPath()!=null){
                    File file1 = new File(intelligenceDetailPictureAdapter.getMviewBeanList().get(i).getPath());
                    files[i]=file1;
                }

            }

        }
        if(intelligenceDetailVideoAdapter.getMviewBeanList()!=null && intelligenceDetailVideoAdapter.getMviewBeanList().size()>0 ){
            fileVideos=new File[intelligenceDetailVideoAdapter.getMviewBeanList().size()];
            for(int i=0;i<intelligenceDetailVideoAdapter.getMviewBeanList().size();i++){
                if(intelligenceDetailVideoAdapter.getMviewBeanList().get(i).getPath()!=null){
                    File file1 = new File(intelligenceDetailVideoAdapter.getMviewBeanList().get(i).getPath());
                    fileVideos[i]=file1;
                }

            }
        }
        if(files!=null &&files.length>0 && fileVideos!=null &&fileVideos.length>0){
            int fLen=0;
            filesTotal=new File[files.length+fileVideos.length];
            for(int i=0;i<files.length;i++){
                filesTotal[i]=files[i];
            }
            fLen=files.length;
            for(int j=0;j<fileVideos.length;j++){
                filesTotal[fLen+j]=fileVideos[j];
            }
            upload(filesTotal);
        }else if(files!=null && files.length>0){
            upload(files);
        }else if(fileVideos!=null && fileVideos.length>0){
            upload(fileVideos);
        }else {
            submit();
        }




//        submit();
    }

    private void getDeptList() {
         Map<String, String> memberMap= new HashMap<String, String>();
        memberMap.put("size","1000");
        JiuyiHttp.POST("department/page-vo")
                .tag("request_assist_page-vo")
                .setJson(GsonUtil.gson().toJson(memberMap))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DeptNewBean>() {
                    @Override
                    public void onSuccess(DeptNewBean data) {
                        if(data!=null){
                            deptList=data.getContent();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((data == null || data.getExtras() == null)&& requestCode!=TAKE_PICTURE) {
            return;
        }
        Bundle bundle = null;
        switch (requestCode) {
            case 200:
                bundle = data.getExtras();
                Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                CustomerName = bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                if (Customerid > 0 && CustomerName != null) {
                    User user = new User(Customerid+"",CustomerName);
                    etContent.insert(user);
                }
                break;
            case 1:
                bundle = data.getExtras();
                Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                CustomerName = bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                if (Customerid > 0 && CustomerName != null) {
                    ClientTag clientTag = new ClientTag(Customerid+"",CustomerName);
                    etContent.insert(clientTag);
                }
                break;
            case TAKE_PICTURE:
                if ( resultCode == RESULT_OK) { //
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  // 检测sd是否可用
                        Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    if(!Func.IsStringEmpty(Rc.getIns().picVideoUrl)){
                        filePath=Rc.getIns().picVideoUrl;
                        Rc.getIns().picVideoUrl="";
                    }
                    Bitmap bm = BitmapUtils.getCompressedBitmap(JiuyiCustomerNewMarketDynamicActivity.this, filePath);
                    ImageBean takePhoto = new ImageBean();
                    takePhoto.setBitmap(bm);

                    //优化压缩图片
                    Uri uri = null;
                    File file1 = new File(filePath);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                    takePhoto.setPath(newFile.getPath());
                    ArrayList<Media> picList=intelligenceDetailPictureAdapter.getMviewBeanList();
                    if(picList==null){
                        picList=new ArrayList<>();
                    }
                    Media media=new Media();
                    media.setExtension(".jpg");
                    media.setMediaType(0);
                    media.setPath(filePath);
                    picList.add(media);
                    intelligenceDetailPictureAdapter.setMviewBeanList(picList);
                    intelligenceDetailPictureAdapter.notifyDataSetChanged();
                }
                break;
            case SELECT_PICTURE:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                ArrayList<Media> selects = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                intelligenceDetailPictureAdapter.setMviewBeanList(selects);
                intelligenceDetailPictureAdapter.notifyDataSetChanged();
                break;
            case TAKE_Video:
                if(!Func.IsStringEmpty(videoPath)){
                    ArrayList<Media> picList=intelligenceDetailVideoAdapter.getMviewBeanList();
                    if(picList==null){
                        picList=new ArrayList<>();
                    }
                    Media media=new Media();
                    media.setPath(videoPath);
                    media.setMediaType(3);
                    media.setExtension(".mp4");
                    picList.add(media);
                    intelligenceDetailVideoAdapter.setMviewBeanList(picList);
                    intelligenceDetailVideoAdapter.notifyDataSetChanged();
                }
                break;
            case SELECT_Video:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                ArrayList<Media> selects2 = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);

                intelligenceDetailVideoAdapter.setMviewBeanList(selects2);
                intelligenceDetailVideoAdapter.notifyDataSetChanged();
                break;
            case PERSON_CODE:
                if (data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                mPersonBeanSelectList = new ArrayList<>();
                mPersonBeanSelectList = bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
                if (mPersonBeanSelectList != null && mPersonBeanSelectList.size() > 0) {
                    String selectNames = "";
                    PersonSelectBean personSelectBean=mPersonBeanSelectList.get(0);
                    int index2 = etContent.getSelectionStart();
//                    etContent.insertPerson(index2,"@"+personSelectBean.getName()+"@",personSelectBean.getId()+"");
                }
                break;
            default:
                break;
        }
    }


    protected void selectVideos() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuVideoWindow = new SelectVideoPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuVideoWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
                    Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.Customer_RecordNew,true);
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

}
