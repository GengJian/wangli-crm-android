package commonlyused.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDialog;
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
import com.control.widget.JiuyiEditText;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.nanchen.compresshelper.CompressHelper;
import com.wanglicrm.android.R;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.TaskBean;
import commonlyused.db.DbHelper;
import commonlyused.db.DictBeanDao;
import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.entity.AttachmentBean;
import customer.entity.Media;
import customer.entity.PersonSelectBean;
import customer.entity.UpdateAssistantBean;
import customer.listener.PickerConfig;
import customer.view.JiuyiAttachment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNormalNewTaskActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用任务新增界面
 *****************************************************************
 */
public class JiuyiNormalNewTaskActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,mtvcancel;
    private TextView tvType;
    private JiuyiEditText etName;
    private TextView tvStatus,tvRemindTime;
    private TextView tvInchargeman;
    private TextView tvAssistant;
    private TextView tvStartdate;
    private TextView tvEnddate;
    private TextView tvCreator;
    private JiuyiEditText etRemark;
    private TextView tvCount;

    private long sType =-1,sStatus=-1,sRemindTime=-1;
    private String viewOperatorType;
    private long beanid=-1;
    private TaskBean.ContentBean updateBean;
    private TaskBean.ContentBean productinfoBean;
    private List<AttachmentBean> attachmentsBeanslist;
    private List<NormalOperatorBean.ContentBean> assistantList=new ArrayList<>();
    private String[] assistantData;
    private long assistantID;
    private Date dateStart, dateEnd,dateNow;
    private List<NormalOperatorBean.ContentBean> inchargeList=new ArrayList<>();
    private String[] inchargeData;
    private long inchargeID;
    private ArrayList<UpdateAssistantBean> assistantSelectList;
    private ArrayList<UpdateAssistantBean> inchargeSelectList;
    private LoadingDialog dialog1,dialog2;
    private String taskType="";
    private LinearLayout ll_inchargeman,ll_assistant;
    private View view_assistant,view_inchargeman;
    private static final int PERSON_CODE = 800;
    private static final int ASSISTANT_CODE = 900;
    private List<PersonSelectBean> mPersonBeanSelectList,mAssistantBeanSelectList,mBeanSelectList;
    private  File[] files;
    protected static final int TAKE_PICTURE = 1000;
    protected static final int SELECT_PICTURE = 1500;
    private String filePath;
    private JiuyiAttachment acAttach;

    @Override
    public void onInit() {
//        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        if(viewOperatorType==null){
            viewOperatorType="";
        }
        if(productinfoBean==null){
            productinfoBean=new TaskBean.ContentBean();
        }
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_normalnewtask_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);

        setTitle();
        initViews();

//        if(beanid>0 && !viewOperatorType.equals(ViewOperatorType.ADD)){
//            getProductinfoList(beanid);
//        }
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
                    showDialog();
                    if(acAttach.getMediaArrayList().size()>0) {
                        files = new File[acAttach.getMediaArrayList().size()];
                        for (int i = 0; i < acAttach.getMediaArrayList().size(); i++) {
                            if (acAttach.getMediaArrayList().get(i).getPath() != null) {
                                File file1 = new File(acAttach.getMediaArrayList().get(i).getPath());
                                files[i] = file1;
                            }

                        }
                        upload();
                    }else{
                        if(viewOperatorType.equals(ViewOperatorType.EDIT)){
                            attachmentsBeanslist=new ArrayList<AttachmentBean>();
                            updateBean.setAttachmentList(attachmentsBeanslist);
//                            updateProductInfo();
                        }else {
                            //新增
                            createProduct();
                        }
                    }

                }
            });

        }
        mtvcancel = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_cancel"));
        if (mtvcancel != null) {
            mtvcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
                }
            });

        }
//        mtvcount= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_count"));

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
                                    productinfoBean.setAttachmentList(attachmentsBeanslist);
                                    createProduct();

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
        ll_inchargeman = (LinearLayout) mBodyLayout.findViewById(R.id.ll_inchargeman);
        ll_assistant = (LinearLayout) mBodyLayout.findViewById(R.id.ll_assistant);
        view_inchargeman = (View) mBodyLayout.findViewById(R.id.view_inchargeman);
        view_assistant = (View) mBodyLayout.findViewById(R.id.view_assistant);
        tvType = (TextView) mBodyLayout.findViewById(R.id.tv_type);
        etName = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_name);
        tvStatus = (TextView) mBodyLayout.findViewById(R.id.tv_status);
        tvRemindTime = (TextView) mBodyLayout.findViewById(R.id.tv_remindTime);
        tvInchargeman = (TextView) mBodyLayout.findViewById(R.id.tv_inchargeman);
        tvAssistant = (TextView) mBodyLayout.findViewById(R.id.tv_assistant);
        tvStartdate = (TextView) mBodyLayout.findViewById(R.id.tv_startdate);
        tvEnddate = (TextView) mBodyLayout.findViewById(R.id.tv_enddate);
        tvCreator = (TextView) mBodyLayout.findViewById(R.id.tv_creator);
        etRemark = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_remark);
        acAttach= (JiuyiAttachment) mBodyLayout.findViewById(R.id.ah_attach);
        acAttach.setAdapter();
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiNormalNewTaskActivity.this);
                buidler.setTitle("任务类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("task_type");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvType.setText(data[which].trim());
                            sType =dictBeansList.get(which).getOriginalid();
                            productinfoBean.setStatusKey(dictBeansList.get(which).getKey());
                            productinfoBean.setStatusValue(dictBeansList.get(which).getValue());
                            if(dictBeansList.get(which).getKey().toLowerCase().equals("remind")){
                                ll_inchargeman.setVisibility(View.GONE);
                                ll_assistant.setVisibility(View.GONE);
                                view_assistant.setVisibility(View.GONE);
                                view_inchargeman.setVisibility(View.GONE);
                                taskType="remind";
                            }else{
                                ll_inchargeman.setVisibility(View.VISIBLE);
                                ll_assistant.setVisibility(View.VISIBLE);
                                view_assistant.setVisibility(View.VISIBLE);
                                view_inchargeman.setVisibility(View.VISIBLE);
                            }
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });

        DictBean itemBean = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                .where(DictBeanDao.Properties.Key.eq("assingn"))
                .where(DictBeanDao.Properties.Name.eq("task_type")).build().unique();
        if(itemBean!=null){
            tvType.setText(itemBean.getValue());
            sType=itemBean.getOriginalid();
            productinfoBean.setTypeKey(itemBean.getKey());
            productinfoBean.setTypeValue(itemBean.getValue());
        }
        final List<DictBean> dictBeansList = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                .where(DictBeanDao.Properties.Name.eq("task_status")).where(DictBeanDao.Properties.Key.eq("distributed")).build().list();
        if(dictBeansList!=null &&dictBeansList.size()>0){
            tvStatus.setText(dictBeansList.get(0).getValue());
            productinfoBean.setStatusKey(dictBeansList.get(0).getKey());
            productinfoBean.setStatusValue(dictBeansList.get(0).getValue());
            sStatus=dictBeansList.get(0).getOriginalid();
        }
        tvRemindTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiNormalNewTaskActivity.this);
                buidler.setTitle("提醒时间段类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("task_remind_time");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getDesp();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvRemindTime.setText(data[which].trim());
                            sRemindTime =dictBeansList.get(which).getOriginalid();
                            productinfoBean.setRemindTimeKey(dictBeansList.get(which).getKey());
                            productinfoBean.setRemindTimeValue(dictBeansList.get(which).getValue());
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });

        DictBean itemBean2 = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                .where(DictBeanDao.Properties.Key.eq("half_day"))
                .where(DictBeanDao.Properties.Name.eq("task_remind_time")).build().unique();
        if(itemBean2!=null){
            tvRemindTime.setText(itemBean2.getDesp());
            sRemindTime=itemBean2.getOriginalid();
            productinfoBean.setRemindTimeKey(itemBean2.getKey());
            productinfoBean.setRemindTimeValue(itemBean2.getValue());
        }
        if (tvStartdate != null) {
            tvStartdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiNormalNewTaskActivity.this);
                    //设置上下年分限制
                    dialog.setYearLimt(60);
                    //设置标题
                    dialog.setTitle("选择时间");
                    //设置类型
                    dialog.setType(DateType.TYPE_ALL);
                    //设置消息体的显示格式，日期格式
                    dialog.setMessageFormat("yyyy-MM-dd HH:mm:ss");
                    //设置选择回调
                    dialog.setOnChangeLisener(null);
                    //设置点击确定按钮回调
                    dialog.setOnSureLisener(new OnSureLisener() {
                        @Override
                        public void onSure(Date date) {
                            tvStartdate.setText( new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
                        }
                    });
                    dialog.show();
                }
            });
            tvStartdate.setText(JiuyiDateUtil.getNowTime4());
        }
        if (tvEnddate != null) {
            tvEnddate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiNormalNewTaskActivity.this);
                    //设置上下年分限制
                    dialog.setYearLimt(60);
                    //设置标题
                    dialog.setTitle("选择时间");
                    //设置类型
                    dialog.setType(DateType.TYPE_ALL);
                    //设置消息体的显示格式，日期格式
                    dialog.setMessageFormat("yyyy-MM-dd HH:mm:ss");
                    //设置选择回调
                    dialog.setOnChangeLisener(null);
                    //设置点击确定按钮回调
                    dialog.setOnSureLisener(new OnSureLisener() {
                        @Override
                        public void onSure(Date date) {
                            tvEnddate.setText( new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
                        }
                    });
                    dialog.show();
                }
            });

        }
        tvInchargeman = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_inchargeman"));
        tvInchargeman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                if(mPersonBeanSelectList!=null && mPersonBeanSelectList.size()>0){
                    mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mPersonBeanSelectList);
                }
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Person");
                intent.putExtras(mBundle);
                startActivityForResult(intent, PERSON_CODE);
            }
        });

        tvAssistant = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_assistant"));
        tvAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                if(mAssistantBeanSelectList!=null && mAssistantBeanSelectList.size()>0){
                    mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mAssistantBeanSelectList);
                }
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Person");
                intent.putExtras(mBundle);
                startActivityForResult(intent, ASSISTANT_CODE);

            }
        });
        tvCreator.setText(Rc.name);

    }
    public void setTitle(){
        if(viewOperatorType!=null && viewOperatorType.equals(ViewOperatorType.EDIT)){
            mTitle = "任务协助信息";
        }else {
            mTitle = "新建任务协助";
        }
        setTitle(mTitle);
    }


    public void submit(){
        JiuyiHttp.POST("task/create")
                .setJson(GsonUtil.gson().toJson(productinfoBean))
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
        if(TextUtils.isEmpty(tvType.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择任务类型！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(etName.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入任务名称！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(!taskType.equals("remind")){
            if(TextUtils.isEmpty(tvInchargeman.getText().toString().trim())){
                startDialog(DialogID.DialogDoNothing, "", "请选择责任人！", JiuyiDialogBase.Dialog_Type_Yes, null);
                return false;
            }
        }

        if(TextUtils.isEmpty(tvStatus.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择状态！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvStartdate.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择开始日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvEnddate.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择结束日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            try {
                dateStart = dateFormat.parse(tvStartdate.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateEnd = dateFormat.parse(tvEnddate.getText().toString());
            dateNow= dateFormat.parse(JiuyiDateUtil.getNowTime2());
            if (dateStart.getTime() > dateEnd.getTime()) {
                startDialog(DialogID.DialogDoNothing, "", "开始日期需要小于结束日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
                return false;
            }
            if (dateNow.getTime() > dateEnd.getTime()) {
                startDialog(DialogID.DialogDoNothing, "", "结束时间需要晚于当前时间！", JiuyiDialogBase.Dialog_Type_Yes, null);
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(JiuyiNormalNewTaskActivity.this, "数据格式有误！", Toast.LENGTH_SHORT)
                    .show();
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


    public void createProduct(){
        if(productinfoBean==null){
            productinfoBean=new TaskBean.ContentBean();
        }

//        if(sType>0){
//            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//            contentBean.setId(sType);
//            productinfoBean.setType(contentBean);
//        }
//        if(sStatus>0){
//            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//            contentBean.setId(sStatus);
//            productinfoBean.setStatus(contentBean);
//        }
//
//        if(sRemindTime>0){
//            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//            contentBean.setId(sRemindTime);
//            productinfoBean.setRemindTime(contentBean);
//        }
        if(!Func.IsStringEmpty(etName.getText().toString())){
            productinfoBean.setTitle(etName.getText().toString());
        }
        if(inchargeSelectList!=null && inchargeSelectList.size()>0) {
            productinfoBean.setReceiverSet(inchargeSelectList);
        }
        if(assistantSelectList!=null && assistantSelectList.size()>0) {
            productinfoBean.setCollaboratorSet(assistantSelectList);
        }

        if(!Func.IsStringEmpty(tvStartdate.getText().toString())){
            productinfoBean.setStartTime(tvStartdate.getText().toString()+":00");
        }
        if(!Func.IsStringEmpty(tvEnddate.getText().toString())){
            productinfoBean.setEndTime(tvEnddate.getText().toString()+":00");
        }
        if(!Func.IsStringEmpty(etRemark.getText().toString())){
            productinfoBean.setRemark(etRemark.getText().toString());
        }

        submit();
    }

   /* public void updateProductInfo(){
        if(updateBean==null){
            updateBean =new TaskBean.ContentBean();;
            updateBean.setId(beanid);
        }
        if(sType>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sType);
            updateBean.setType(contentBean);
        }
    }*/

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
                acAttach.adapter.getMviewBeanList().add(media);
                acAttach.refreshAttach();
            }
            break;
        case SELECT_PICTURE:
            if(data == null || data.getExtras() == null) {
                return;
            }
            ArrayList<Media> selects = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
            acAttach.setMediaArrayList(selects);
            acAttach.adapter.setMviewBeanList(selects);
            acAttach.refreshAttach();
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
                inchargeSelectList = new ArrayList<>();
                for (int i = 0; i < mPersonBeanSelectList.size(); i++) {
                    selectNames += mPersonBeanSelectList.get(i).getName() + "，";
                    UpdateAssistantBean updateAssistantBean = new UpdateAssistantBean();
                    updateAssistantBean.setId(mPersonBeanSelectList.get(i).getId());
                    inchargeSelectList.add(updateAssistantBean);
                }
                if (!Func.IsStringEmpty(selectNames)) {
                    tvInchargeman.setText(selectNames.substring(0, selectNames.length() - 1));
                }
            } else {
                inchargeSelectList = new ArrayList<>();
                tvInchargeman.setText("");
            }
            break;
        case ASSISTANT_CODE:
            if (data == null || data.getExtras() == null) {
                return;
            }
            bundle = data.getExtras();
            if (mAssistantBeanSelectList == null) {
                mAssistantBeanSelectList = new ArrayList<>();
            }

            mAssistantBeanSelectList = bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
            if (mAssistantBeanSelectList != null && mAssistantBeanSelectList.size() > 0) {
                String selectNames = "";
                assistantSelectList = new ArrayList<>();
                for (int i = 0; i < mAssistantBeanSelectList.size(); i++) {
                    selectNames += mAssistantBeanSelectList.get(i).getName() + "，";
                    UpdateAssistantBean updateAssistantBean = new UpdateAssistantBean();
                    updateAssistantBean.setId(mAssistantBeanSelectList.get(i).getId());
                    assistantSelectList.add(updateAssistantBean);
                }
                if (!Func.IsStringEmpty(selectNames)) {
                    tvAssistant.setText(selectNames.substring(0, selectNames.length() - 1));
                }
            } else {
                assistantSelectList = new ArrayList<>();
                tvAssistant.setText("");
            }
            break;

    }
}


}
