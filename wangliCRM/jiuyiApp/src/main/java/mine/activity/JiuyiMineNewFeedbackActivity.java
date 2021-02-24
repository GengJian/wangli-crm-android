package mine.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.nanchen.compresshelper.CompressHelper;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import customer.entity.AttachmentBean;
import customer.entity.ImageBean;
import customer.entity.Media;
import customer.entity.UploadTokenBean;
import customer.listener.PickerConfig;
import customer.view.Bimp;
import customer.view.JiuyiAttachment;
import mine.bean.FeedbackBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineNewFeedbackActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的意见反馈
 *****************************************************************
 */

public class JiuyiMineNewFeedbackActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,mtvcancel;
    private TextView tvType;
    private JiuyiEditText etTitle;
    private TextView tvDate;
    private JiuyiEditText etContent;
    private TextView tvCount;

    private long sType =-1;
    private FeedbackBean feedbackBean;
    private JiuyiButton mbtnSave;
    private  File[] files;
    private List<AttachmentBean> attachmentsBeanslist;
    private JiuyiAttachment acAttach;
    protected static final int TAKE_PICTURE = 1000;
    protected static final int SELECT_PICTURE = 1500;
    private String filePath;

    @Override
    public void onInit() {
        feedbackBean=new FeedbackBean();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_minenewfeedback_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        mbtnSave= (JiuyiButton) mBodyLayout.findViewById(Res.getViewID(null, "btn_save"));
        if(mbtnSave!=null){
            mbtnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
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
                        createFeedback();
                    }
//                    if(Bimp.tempSelectBitmap.size()>0){
//                        upload();
//                        feedbackBean.setAttachments(attachmentsBeanslist);
//                        createFeedback();
//                    }else{
//                        //新增
//                        createFeedback();
//                    }
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
                                    feedbackBean.setAttachmentList(attachmentsBeanslist);
                                    createFeedback();
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
        etTitle = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_title);
        tvDate = (TextView) mBodyLayout.findViewById(R.id.tv_date);
        etContent = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_content);
        tvCount = (TextView) mBodyLayout.findViewById(R.id.tv_count);
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiMineNewFeedbackActivity.this);
                buidler.setTitle("问题类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("app_question_feedback");
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
                            feedbackBean.setFeedTypeKey(dictBeansList.get(which).getKey());
                            feedbackBean.setFeedTypeValue(dictBeansList.get(which).getValue());
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        if (tvDate != null) {
            tvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiMineNewFeedbackActivity.this);
                    //设置上下年分限制
                    dialog.setYearLimt(60);
                    //设置标题
                    dialog.setTitle("选择时间");
                    //设置类型
                    dialog.setType(DateType.TYPE_YMD);
                    //设置消息体的显示格式，日期格式
                    dialog.setMessageFormat("yyyy-MM-dd");
                    //设置选择回调
                    dialog.setOnChangeLisener(null);
                    //设置点击确定按钮回调
                    dialog.setOnSureLisener(new OnSureLisener() {
                        @Override
                        public void onSure(Date date) {
                            tvDate.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        }
                    });
                    dialog.show();
                }
            });

        }
        acAttach= (JiuyiAttachment) mBodyLayout.findViewById(R.id.ah_attach);
        acAttach.setAdapter();

    }
    public void setTitle(){
        mTitle = "意见反馈";
        setTitle(mTitle);
    }


    public void submit(){
        JiuyiHttp.POST("feed-back/create")
                .setJson(GsonUtil.gson().toJson(feedbackBean))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    public boolean check(){
        if(TextUtils.isEmpty(tvType.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择问题类型！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(etContent.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入内容！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
    /*    if(TextUtils.isEmpty(tvDate.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }*/

        return true;
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }


    public void createFeedback(){

        if(!Func.IsStringEmpty(tvType.getText().toString().trim())){
            feedbackBean.setTitle(tvType.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(tvDate.getText().toString())){
            feedbackBean.setInfoDate(tvDate.getText().toString());
        }
        if(!Func.IsStringEmpty(etContent.getText().toString())){
            feedbackBean.setContent(etContent.getText().toString());
        }
        submit();
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

        }
    }




}
