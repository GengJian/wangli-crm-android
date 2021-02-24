package dynamic.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiDateUtil;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiBigTextGroup;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.nanchen.compresshelper.CompressHelper;
import com.tencent.qcloud.sdk.Constant;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.entity.AttachmentBean;
import customer.entity.MaterialTypeBean;
import customer.entity.Media;
import customer.entity.MemberBeanID;
import customer.entity.PersonSelectBean;
import customer.listener.PickerConfig;
import customer.view.JiuyiAttachment;
import dynamic.entity.DyActivityBean;
import dynamic.entity.DyClueBean;
import dynamic.entity.DyInteligenceBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewComplaintActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 客户投诉录入界面
 *****************************************************************
 */
public class JiuyiCustomerNewClueActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private String operatorType,sTitle,s_returnvalue;
    private  long customerid=-1;
    private ImageView   iv_back;
    private JiuyiItemGroup jigTitle;
    private JiuyiItemGroup jigResource;
    private JiuyiItemGroup jigInteligence;
    private JiuyiItemGroup jigActivity;
    private JiuyiItemGroup jigOldclient;
    private JiuyiItemGroup jigClient;
    private JiuyiItemGroup jigLinkman;
    private EditText jigLinkmantel;
    private JiuyiItemGroup jigSubmit;
    private JiuyiItemGroup jigSubmitdate;
    private JiuyiItemGroup jig_import,jig_bigtype;
    private JiuyiBigTextGroup jigContent;
    private JiuyiAttachment ahAttach;
    private String operType="";
    protected static final int TAKE_PICTURE = 1000;
    protected static final int SELECT_PICTURE = 1500;
    private  File[] files;
    private List<AttachmentBean> attachmentsBeanslist,attachmentsEditBeanslist;
    private String filePath;
    private DyClueBean.ContentBean createBean;
    private List<PersonSelectBean> mPersonBeanSelectList;
    private List<MaterialTypeBean.ContentBean> mProductTypeSelectList;
    private long billID=0;
    private Boolean needUpload=false;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newclue_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        operatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }
        if(Func.IsStringEmpty(operatorType)){
            operatorType="";
        }
        if(operatorType.equals(ViewOperatorType.ADD)){
            createBean=new DyClueBean.ContentBean();
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
                    Rc.mBackfresh=true;
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
                    showDialog();
                    if(ahAttach.getMediaArrayList().size()>0) {
                         if(operatorType.equals(ViewOperatorType.EDIT)){
                            attachmentsEditBeanslist=new ArrayList<>();
                        }

                        files = new File[ahAttach.getMediaArrayList().size()];
                        for (int i = 0; i < ahAttach.getMediaArrayList().size(); i++) {
                            Media media=ahAttach.getMediaArrayList().get(i);
                            if (media.getPath() != null ) {
                                File file1 = new File(media.getPath());
                                files[i] = file1;
                                needUpload=true;
                            }else{
                                if(media.getUrl()!=null && attachmentsBeanslist!=null && attachmentsBeanslist.size()>0  ){
                                    for(int j=0;j<attachmentsBeanslist.size();j++){
                                     AttachmentBean attachmentBean=attachmentsBeanslist.get(j);
                                     if(attachmentBean.getQiniuKey()!=null   ){
                                          int pos=media.getUrl().lastIndexOf("/");
                                         if(pos>0){
                                             String qiuniukey=media.getUrl().substring(pos+1,media.getUrl().length());
                                             if(qiuniukey.equals(attachmentBean.getQiniuKey())){
                                                 attachmentsEditBeanslist.add(attachmentBean);
                                             }
                                         }
                                     }
//                                        if(attachmentBean.getQiniuKey()!=null){
//                                            String qiuniukey=media.getQiniuKey();
//                                            if( qiuniukey!=null && qiuniukey.equals(attachmentBean.getQiniuKey())){
//                                                attachmentsEditBeanslist.add(attachmentBean);
//                                            }
//                                        }
                                    }

                                }


                            }
                        }
                        if(needUpload){
                            upload();
                        }else{
                            if(attachmentsEditBeanslist.size()>0){
                                createBean.setAttachments(attachmentsEditBeanslist);
                                updateInfo();
                            }
                        }

                    }else{
                        if(operatorType.equals(ViewOperatorType.ADD)){
                            createBean();
                        }else if(operatorType.equals(ViewOperatorType.EDIT)){
                            updateInfo();
                        }

                    }

                }
            });

        }
        initViews();
        if(operatorType.equals(ViewOperatorType.EDIT)){//从动态跳转
            billID=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
            if(billID>0){
                getDetailInfo(billID);
            }
        }

    }
    public void initViews(){
        jigTitle = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_title);
        jigResource = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_resource);
        jigResource.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewClueActivity.this);
                buidler.setTitle("线索来源");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("clue_resource");
                if(dictBeansList!=null &&dictBeansList.size()>0) {
                    data = new String[dictBeansList.size()];
                    for (int i = 0; i < dictBeansList.size(); i++) {
                        data[i] = dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            jigResource.setText(data[which].trim());
                            createBean.setResourceKey(dictBeansList.get(which).getKey());
                            createBean.setResourceValue(dictBeansList.get(which).getValue());
                            if(dictBeansList.get(which).getKey().equals("market_activity")){
                                jigActivity.setVisibility(View.VISIBLE);
                                jigOldclient.setVisibility(View.GONE);
                                jigInteligence.setVisibility(View.GONE);
                                createBean.setOldMember(null);
                                createBean.setIntelligenceItem(null);
                            }else if(dictBeansList.get(which).getKey().equals("customer_introduce")){
                                jigOldclient.setVisibility(View.VISIBLE);
                                jigActivity.setVisibility(View.GONE);
                                jigInteligence.setVisibility(View.GONE);
                                createBean.setMarketActivity(null);
                                createBean.setIntelligenceItem(null);
                            }else if(dictBeansList.get(which).getKey().equals("market_intelligence")){
                                jigInteligence.setVisibility(View.VISIBLE);
                                jigActivity.setVisibility(View.GONE);
                                jigOldclient.setVisibility(View.GONE);
                                createBean.setMarketActivity(null);
                                createBean.setOldMember(null);
                            }else {
                                jigInteligence.setVisibility(View.GONE);
                                jigActivity.setVisibility(View.GONE);
                                jigOldclient.setVisibility(View.GONE);
                                createBean.setIntelligenceItem(null);
                                createBean.setMarketActivity(null);
                                createBean.setOldMember(null);
                            }
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        jig_import = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_import);
        jig_import.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewClueActivity.this);
                buidler.setTitle("重要程度");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("importance");
                if(dictBeansList!=null &&dictBeansList.size()>0) {
                    data = new String[dictBeansList.size()];
                    for (int i = 0; i < dictBeansList.size(); i++) {
                        data[i] = dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            jig_import.setText(data[which].trim());
                            createBean.setImportantKey(dictBeansList.get(which).getKey());
                            createBean.setImportantValue(dictBeansList.get(which).getValue());

                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });



        jig_bigtype = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_bigtype);
        jig_bigtype.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                if(mProductTypeSelectList==null){
                    mProductTypeSelectList=new ArrayList<>();
                }
                mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mProductTypeSelectList);
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"PRODUCTBIGTYPE");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 206);

            }
        });
        jigInteligence = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_inteligence);
        jigInteligence.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"INTELLIGENCE");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 205);

            }
        });
        jigActivity = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_activity);
        jigActivity.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"ACTIVITY");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 204);

            }
        });
        jigOldclient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_oldclient);
        jigOldclient.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_SPECIALCONDITION,"Y");
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 200);

            }
        });
        jigClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        jigClient.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_SPECIALCONDITION,"Y");
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 201);

            }
        });
        jigLinkman = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_linkman);
        jigLinkmantel = (EditText) mBodyLayout.findViewById(R.id.jig_linkmantel);
        jigLinkmantel.setInputType(InputType.TYPE_CLASS_PHONE);
        jigSubmit = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_submit);
        jigSubmit.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Person");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 203);
            }
        });

        jigSubmitdate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_submitdate);
        jigSubmitdate.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                DatePickDialog dialog = new DatePickDialog(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity());
                //设置上下年分限制
                dialog.setYearLimt(60);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        jigSubmitdate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                        createBean.setSubmitDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
                    }
                });
                dialog.show();
            }
        });
        if(operatorType.equals(ViewOperatorType.ADD)){
            jig_import.setText("一般");
            createBean.setImportantKey("general");
            createBean.setImportantValue("一般");
            jigSubmitdate.setText(JiuyiDateUtil.getNowTime3());
        }


        jigContent = (JiuyiBigTextGroup) mBodyLayout.findViewById(R.id.jig_content);
        ahAttach = (JiuyiAttachment) mBodyLayout.findViewById(R.id.ah_attach);
        ahAttach.setAdapter();

    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }

    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else{
            mTitle = "新建线索";
        }
        setTitle(mTitle);
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
                                    if(operatorType.equals(ViewOperatorType.ADD)){
                                        createBean.setAttachments(attachmentsBeanslist);
                                        createBean();
                                    }else if(operatorType.equals(ViewOperatorType.EDIT)){
                                        for(int i=0;i<attachmentsBeanslist.size();i++){
                                            attachmentsEditBeanslist.add(attachmentsBeanslist.get(i));
                                        }
                                        createBean.setAttachments(attachmentsEditBeanslist);
                                        updateInfo();
                                    }
                                }else{
                                    if(progressLoadingDialog!=null){
                                        progressLoadingDialog.dismiss();
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
    public void createBean(){

        if(!Func.IsStringEmpty(jigTitle.getText().toString().trim())){
            createBean.setTitle(jigTitle.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigContent.getText().toString().trim())){
            createBean.setContent(jigContent.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigLinkman.getText().toString().trim())){
            createBean.setMemberContactor(jigLinkman.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigLinkmantel.getText().toString().trim())){
            createBean.setMemberContactorPhone(jigLinkmantel.getText().toString().trim());
        }
        submit();
    }

    public void submit(){
        JiuyiHttp.POST("clue/create")
                .setJson(GsonUtil.gson().toJson(createBean))
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
    public void updateInfo(){
        if(createBean==null){
            createBean =new DyClueBean.ContentBean();
            createBean.setId(billID);
        }
        createBean.setTitle(jigTitle.getText());
        createBean.setMemberContactor(jigLinkman.getText());
        createBean.setMemberContactorPhone(jigLinkmantel.getText().toString());
        createBean.setContent(jigContent.getText());
        JiuyiHttp.PUT("clue/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(com.common.GsonUtil.gson().toJson(createBean))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
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

    public boolean check(){
        if(Func.IsStringEmpty(jigTitle.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入标题！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jigResource.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择线索来源！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jigClient.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jigContent.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入线索内容！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jig_bigtype.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择产品大类！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle;
        if((data == null || data.getExtras() == null)&& requestCode!=TAKE_PICTURE) {
            return;
        }
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
                        Rc.picVideoUrl="";
                    }

                    //优化压缩图片
                    Uri uri = null;
                    File file1 = new File(filePath);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                    Media media=new Media();
                    media.setExtension(".jpg");
                    media.setMediaType(0);
                    media.setPath(newFile.getPath());
                    ahAttach.adapter.getMviewBeanList().add(media);
                    ahAttach.refreshAttach();
                }
                break;
            case SELECT_PICTURE:
                ArrayList<Media> selects = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                ahAttach.setMediaArrayList(selects);
                ahAttach.adapter.setMviewBeanList(selects);
                ahAttach.refreshAttach();
                break;
            case 200:
                bundle = data.getExtras();
                String customerName=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long customerID=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(customerID>0 && customerName!=null ){
                    jigOldclient.setText(customerName);
                    MemberBeanID memberBeanID=new MemberBeanID();
                    memberBeanID.setId(customerID);
                    memberBeanID.setOrgName(customerName);
                    createBean.setOldMember(memberBeanID);
                }
                break;
            case 201:
                bundle = data.getExtras();
                String customerName2=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long customerID2=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(customerID2>0 && customerName2!=null ){
                    jigClient.setText(customerName2);
                    MemberBeanID memberBeanID=new MemberBeanID();
                    memberBeanID.setId(customerID2);
                    memberBeanID.setOrgName(customerName2);
                    createBean.setMember(memberBeanID);
                }
                break;
            case 203:
                bundle = data.getExtras();
                mPersonBeanSelectList = new ArrayList<>();
                mPersonBeanSelectList = bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
                if (mPersonBeanSelectList != null && mPersonBeanSelectList.size() > 0) {
                    jigSubmit.setText(mPersonBeanSelectList.get(0).getName());
                    DyInteligenceBean.ContentBean.OperatorBean operatorBean = new DyInteligenceBean.ContentBean.OperatorBean();
                    operatorBean.setId(mPersonBeanSelectList.get(0).getId());
                    createBean.setSubmitter(operatorBean);
                }
                break;
            case 204:
                bundle = data.getExtras();
                String customerName3=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long activityId=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(activityId>0 && customerName3!=null ){
                    jigActivity.setText(customerName3);
                    DyActivityBean.DyActivityBeanID activityBeanID=new DyActivityBean.DyActivityBeanID();
                    activityBeanID.setId(activityId);
                    createBean.setMarketActivity(activityBeanID);
                }
                break;
            case 205:
                bundle = data.getExtras();
                String customerName4=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long inteligenceId=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(inteligenceId>0 && customerName4!=null ){
                    jigInteligence.setText(customerName4);
                    DyInteligenceBean.IntelligenceItemBeanID intelligenceItemBeanID=new DyInteligenceBean.IntelligenceItemBeanID();
                    intelligenceItemBeanID.setId(inteligenceId);
                    createBean.setIntelligenceItem(intelligenceItemBeanID);
                }
                break;
            case 206:
                bundle = data.getExtras();
                mProductTypeSelectList=bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
                List<DyClueBean.ProductBigCategory> bigCategoryList=new ArrayList<>();
                String name="";
                if(mProductTypeSelectList!=null && mProductTypeSelectList.size()>0){
                    for(int i=0;i<mProductTypeSelectList.size();i++){
                        name+=mProductTypeSelectList.get(i).getName()+",";
                        DyClueBean.ProductBigCategory productBigCategory=new DyClueBean.ProductBigCategory();
                        productBigCategory.setId(mProductTypeSelectList.get(i).getId());
                        bigCategoryList.add(productBigCategory);
                    }
                }
                createBean.setMaterialTypes(bigCategoryList);
                jig_bigtype.setText(name.substring(0,name.length()-1));

                break;

        }
    }

    private void getDetailInfo(long id) {
        JiuyiHttp.GET("clue/detail/"+id)
                .tag("request_get_market-clue")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DyClueBean.ContentBean>() {
                    @Override
                    public void onSuccess(DyClueBean.ContentBean contentBean) {
                        if(contentBean!=null){
//                            updateBean=contentBean;
                            createBean=contentBean;
                            if(contentBean.getTitle()!=null){
                                jigTitle.setText(contentBean.getTitle());
                            }
                            if(contentBean.getResourceValue()!=null){
                                jigResource.setText(contentBean.getResourceValue());
                            }
                            if(contentBean.getResourceKey()!=null){
                                if(contentBean.getResourceKey().equals("market_activity")){
                                    jigActivity.setVisibility(View.VISIBLE);
                                    jigOldclient.setVisibility(View.GONE);
                                    jigInteligence.setVisibility(View.GONE);
                                }else if(contentBean.getResourceKey().equals("customer_introduce")){
                                    jigOldclient.setVisibility(View.VISIBLE);
                                    jigActivity.setVisibility(View.GONE);
                                    jigInteligence.setVisibility(View.GONE);
                                }else if(contentBean.getResourceKey().equals("market_intelligence")){
                                    jigInteligence.setVisibility(View.VISIBLE);
                                    jigActivity.setVisibility(View.GONE);
                                    jigOldclient.setVisibility(View.GONE);
                                }
                            }

                            if(contentBean.getIntelligenceItem()!=null && contentBean.getIntelligenceItem().getContent()!=null){
                                jigInteligence.setText(contentBean.getIntelligenceItem().getContent());
                            }
                            if(contentBean.getMarketActivity()!=null && contentBean.getMarketActivity().getTitle()!=null){
                                jigActivity.setText(contentBean.getMarketActivity().getTitle());
                            }
                            if(contentBean.getOldMember()!=null && contentBean.getOldMember().getOrgName()!=null){
                                jigOldclient.setText(contentBean.getOldMember().getOrgName());
                            }
                            if(contentBean.getMember()!=null && contentBean.getMember().getOrgName()!=null){
                                jigClient.setText(contentBean.getMember().getOrgName());
                            }
                            if(contentBean.getMemberContactor()!=null){
                                jigLinkman.setText(contentBean.getMemberContactor());
                            }
                            if(contentBean.getMemberContactorPhone()!=null){
                                jigLinkmantel.setText(contentBean.getMemberContactorPhone());
                            }
                            if(contentBean.getSubmitter()!=null && contentBean.getSubmitter().getName()!=null){
                                jigSubmit.setText(contentBean.getSubmitter().getName());
                            }
                            if(contentBean.getSubmitDate()!=null){
                                jigSubmitdate.setText(contentBean.getSubmitDate());
                            }
                            if(contentBean.getImportantValue()!=null){
                                jig_import.setText(contentBean.getImportantValue());
                            }
                            if(contentBean.getMaterialTypes()!=null && contentBean.getMaterialTypes().size()>0){
                                String name="";
                                for(int i=0;i<contentBean.getMaterialTypes().size();i++){
                                    name+=contentBean.getMaterialTypes().get(i).getName()+",";
                                }
                                jig_bigtype.setText(name.substring(0,name.length()-1));
                            }
                            if(contentBean.getContent()!=null){
                                jigContent.setText(contentBean.getContent());
                            }
                            if(contentBean.getAttachments()!=null && contentBean.getAttachments().size()>0){
                                attachmentsBeanslist=contentBean.getAttachments();
                                ArrayList mediaArrayList=new ArrayList<>();
                                for(int i = 0; i<contentBean.getAttachments().size(); i++){

                                    AttachmentBean attachmentBean = contentBean.getAttachments().get(i);
                                    Media media=new Media();
                                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                                    media.setExtension(".jpg");
                                    media.setMediaType(0);
                                    mediaArrayList.add(media);
                                }
                                ahAttach.setMediaArrayList(mediaArrayList);
                                ahAttach.adapter.setMviewBeanList(mediaArrayList);
                                ahAttach.adapter.notifyDataSetChanged();
                                ahAttach.refreshAttach();
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



}
