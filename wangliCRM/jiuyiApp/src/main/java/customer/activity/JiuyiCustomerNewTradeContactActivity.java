package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDialog;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.model.DictBean;
import com.jiuyi.model.DictResultBean;
import com.jiuyi.tools.DictUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import customer.Utils.FastUtils;
import customer.Utils.ViewOperatorType;
import customer.entity.AttachmentBean;
import customer.entity.ImageBean;
import customer.entity.MemberBeanID;
import customer.entity.MemberSelectBean;
import customer.entity.TradecontactBean;
import customer.entity.UploadTokenBean;
import customer.view.Bimp;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewTradeContactActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 合同新增页面
 *****************************************************************
 */

public class JiuyiCustomerNewTradeContactActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel;
    private TextView tvType;
    private TextView tvClient;
    private TextView etContactno;
    private TextView tvDate;
    private TextView tvCount;

    private long sType =-1;
    private long Customerid=-1;
    private String Customername="";
    private String viewOperatorType;
    private long beanid=-1;
    private TradecontactBean.ContentBean updateBean;
    private TradecontactBean.ContentBean productinfoBean;
    private List<AttachmentBean> attachmentsBeanslist;
    private List <MemberSelectBean.ContentBean> memberBeanIDList=new ArrayList<>();
    private LoadingDialog dialog1;

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewtradecontact_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        if(beanid>0 && !viewOperatorType.equals(ViewOperatorType.ADD)){
            getProductinfoList(beanid);
        }

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            if(viewOperatorType!=null && viewOperatorType.equals(ViewOperatorType.VIEW)){
                mtvcomplete.setVisibility(View.INVISIBLE);
            }
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                boolean mbcheck=false;
                mbcheck=check();
                if(!mbcheck){
                    return;
                }
                showDialog();
                if(Bimp.tempSelectBitmap.size()>0){
                    upload();
                }else{
                    //新增
                    createProduct();
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
        mtvcount= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_count"));

    }
    private void upload()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("qiniu/upload-token")
                        .tag("request_upload-token_1")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<UploadTokenBean>() {
                            @Override
                            public void onSuccess(UploadTokenBean returndata) {
                                String qimiuToken="";
                                if(returndata!=null){
                                    qimiuToken=returndata.getContent();
                                }
                                if (Func.IsStringEmpty(qimiuToken)) {

                                }
                                if(qimiuToken.contains("\"")){
                                    qimiuToken=qimiuToken.replace("\"","");
                                }
                                attachmentsBeanslist=new ArrayList<AttachmentBean>();
                                for(int j=0;j<Bimp.tempSelectBitmap.size();j++){
                                    ImageBean imageBean=Bimp.tempSelectBitmap.get(j);
                                    String data = imageBean.getPath();
                                    String key = null;

                                    uploadManager.put(data, key, qimiuToken,
                                            new UpCompletionHandler() {
                                                @Override
                                                public void complete(String key, ResponseInfo info, JSONObject res) {
                                                    //res包含hash、key等信息，具体字段取决于上传策略的设置
                                                    if(info.isOK()) {
                                                        AttachmentBean attachmentsBean=new AttachmentBean();
                                                        String qiniukey="";
                                                        if(res!=null){
                                                            qiniukey=((JSONObject) res).opt("key").toString();
                                                            attachmentsBean.setQiniuKey(qiniukey);
                                                            attachmentsBeanslist.add(attachmentsBean);
                                                        }
                                                        //附件上传成功
                                                        if(attachmentsBeanslist!=null && attachmentsBeanslist.size()>0){
                                                            if(attachmentsBeanslist.size()==Bimp.tempSelectBitmap.size()){
                                                                //新增
                                                                if(productinfoBean==null){
                                                                    productinfoBean=new TradecontactBean.ContentBean();
                                                                }
                                                                productinfoBean.setAttachments(attachmentsBeanslist);
                                                                createProduct();
                                                            }
                                                        }


                                                    } else {
                                                        Log.i("qiniu", "Upload Fail");
                                                        //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                                    }
                                                    Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                                                }
                                            }, null);
                                }


                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                if(progressDialog!=null){
                                    progressDialog.dismiss();
                                }
                                JiuyiLog.e("getsig","request errorCode:" + errCode + ",errorMsg:" + errMsg);
                            }
                        });

            }
        };
        thread.start();
    }
    @Override
    public void initViews(){
        super.initViews();
        tvType = (TextView) mBodyLayout.findViewById(R.id.tv_type);
        tvClient = (TextView) mBodyLayout.findViewById(R.id.tv_client);
        etContactno = (TextView) mBodyLayout.findViewById(R.id.et_contactno);
        tvDate = (TextView) mBodyLayout.findViewById(R.id.tv_date);
        tvCount = (TextView) mBodyLayout.findViewById(R.id.tv_count);
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewTradeContactActivity.this);
                buidler.setTitle("合同类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("salescontract_type");
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
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        if(!Func.IsStringEmpty(Customername)){
            tvClient.setText(Customername);
        }
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

        if (tvDate != null) {
            tvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerNewTradeContactActivity.this);
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

    }
    public void setTitle(){
        if(viewOperatorType!=null && viewOperatorType.equals(ViewOperatorType.VIEW)){
            mTitle = "查看合同信息";
        }else {
            mTitle = "新建合同信息";
        }
        setTitle(mTitle);
    }


    public void submit(){
        JiuyiHttp.POST("sales-contract/create-mobile")
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
            startDialog(DialogID.DialogDoNothing, "", "请选择合同类型！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvClient.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvDate.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择合同日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
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
    JiuyiHttp.GET("sales-contract/detail-mobile/"+id)
            .tag("request_get_spot_check")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<TradecontactBean.ContentBean>() {
                @Override
                public void onSuccess(TradecontactBean.ContentBean contentBean) {
                    if(contentBean!=null){
                        updateBean=contentBean;
                        if(contentBean.getType()!=null){
                            tvType.setText(contentBean.getType().getValue());
                            tvType.setEnabled(false);
                        }
                        if(contentBean.getMember()!=null){
                            tvClient.setText(contentBean.getMember().getOrgName());
                            tvClient.setEnabled(false);
                        }
                        if(!Func.IsStringEmpty(contentBean.getInfoDate())){
                            tvDate.setText(contentBean.getInfoDate());
                            tvDate.setEnabled(false);
                        }
                        if(!Func.IsStringEmpty(contentBean.getNumber())){
                            etContactno.setText(contentBean.getNumber());
                            etContactno.setEnabled(false);
                        }
                        if(contentBean.getAttachments()!=null && contentBean.getAttachments().size()>0){
                            for(int i=0;i<contentBean.getAttachments().size();i++){
                                AttachmentBean attachmentBean=contentBean.getAttachments().get(i);
                                Bimp.tempSelectBitmap.clear();
                                if(!Func.IsStringEmpty(attachmentBean.getUrl()) && !Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                    saveImageFromUrl(attachmentBean.getUrl(),attachmentBean.getQiniuKey(),JiuyiCustomerNewTradeContactActivity.this);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFail(int errCode, String errMsg) {
                    startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                }
            });

    }

    public void createProduct(){
        if(productinfoBean==null){
            productinfoBean=new TradecontactBean.ContentBean();
        }

        if(sType>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sType);
            productinfoBean.setType(contentBean);
        }
        if(Customerid>0){
            MemberBeanID memberBeanID=new MemberBeanID();
            memberBeanID.setId(Customerid);
            productinfoBean.setMember(memberBeanID);
        }
        if(!Func.IsStringEmpty(tvDate.getText().toString())){
            productinfoBean.setInfoDate(tvDate.getText().toString());
        }


        submit();
    }


    protected void saveImageFromUrl(String url, final String qiniukey, Context context) {
        final ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url)).setProgressiveRenderingEnabled(true).build();

        DataSource<CloseableReference<CloseableImage>> dataSource = Fresco.getImagePipeline()
                .fetchDecodedImage(imageRequest, context);

        dataSource.subscribe(new BaseBitmapDataSubscriber() {

            @Override
            public void onNewResultImpl(Bitmap bitmap) {
                //形参的bitmap即Fresco缓存到内存的Bitmap
                //  saveBitmap(bitmap);
                //下面是获取fresco缓存到磁盘的cnt图片的路径
                CacheKey cacheKey = DefaultCacheKeyFactory.getInstance()
                        .getEncodedCacheKey(imageRequest, this);
                BinaryResource resource = ImagePipelineFactory.getInstance().getMainFileCache().getResource(cacheKey);
                if (resource == null) return;
                File file = ((FileBinaryResource) resource).getFile();

                if(file!=null){
                    ImageBean returnPhoto = new ImageBean();
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(file.getName());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap bi= BitmapFactory.decodeStream(fis);
                    returnPhoto.setBitmap(bi);
                    returnPhoto.setPath(file.getPath());
                    returnPhoto.setQiniuKey(qiniukey);
                    returnPhoto.setShowIcon(true);
                    Bimp.tempSelectBitmap.add(returnPhoto);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            if(mtvcount!=null){
                                mtvcount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
                            }
                        }
                    });
                }
                if (file == null) return;
                Log.i("wxbnbb", "saveImageFromUrl: " + file.getAbsolutePath());
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {
                Log.i("fail", "saveImageFromUrl: " );
            }
        }, CallerThreadExecutor.getInstance());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if(intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle bundle = intent.getExtras();
        Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
          Customername= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        if(Customerid>0 && !Func.IsStringEmpty(Customername) ){
            tvClient.setText(Customername);
        }
    }

}
