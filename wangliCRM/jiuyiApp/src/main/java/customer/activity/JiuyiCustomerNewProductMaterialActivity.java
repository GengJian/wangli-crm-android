package customer.activity;

import android.app.Dialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import customer.Utils.ViewOperatorType;
import customer.entity.AttachmentBean;
import customer.entity.ImageBean;
import customer.entity.MemberBeanID;
import customer.entity.ProductmaterialBean;
import customer.entity.UploadTokenBean;
import customer.view.Bimp;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewProductMaterialActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 原料信息新增页面
 *****************************************************************
 */
public class JiuyiCustomerNewProductMaterialActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel;
    private JiuyiEditText etType;
    private JiuyiEditText etName;
    private JiuyiEditText etAnnualpurchasevolume;
    private JiuyiEditText etMonthlyUsage;
    private JiuyiEditText etPurchaseVolumeNextMonth;
    private TextView tvCount;

    private long sType =-1;
    private long Customerid=-1;
    private String viewOperatorType;
    private long beanid=-1;
    private ProductmaterialBean.ContentBean updateBean;
    private ProductmaterialBean.ContentBean productinfoBean;
    private List<AttachmentBean> attachmentsBeanslist;

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewproductmaterial_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        if(beanid>0 && !viewOperatorType.equals(ViewOperatorType.ADD)){
            getProductinfoList(beanid);
        }
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
                if(Bimp.tempSelectBitmap.size()>0){
                    upload();
                }else{
                    if(viewOperatorType.equals(ViewOperatorType.EDIT)){
                        attachmentsBeanslist=new ArrayList<AttachmentBean>();
                        updateBean.setAttachments(attachmentsBeanslist);
                        updateProductInfo();
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
                                if(qimiuToken.contains("\"")){
                                    qimiuToken=qimiuToken.replace("\"","");
                                }
                                attachmentsBeanslist=new ArrayList<AttachmentBean>();
                                for(int j=0;j<Bimp.tempSelectBitmap.size();j++){
                                    ImageBean imageBean=Bimp.tempSelectBitmap.get(j);
                                    String data="";
                                    String key = null;
                                    if(!Func.IsStringEmpty(imageBean.getQiniuKey())){
                                        AttachmentBean attachmentsBean=new AttachmentBean();
                                        attachmentsBean.setQiniuKey(imageBean.getQiniuKey());
                                        attachmentsBeanslist.add(attachmentsBean);
                                        if(attachmentsBeanslist!=null && attachmentsBeanslist.size()>0){
                                            if(attachmentsBeanslist.size()==Bimp.tempSelectBitmap.size()){
                                                if(viewOperatorType.equals(ViewOperatorType.EDIT)){
                                                    //更新产品信息
                                                    updateBean.setAttachments(attachmentsBeanslist);
                                                    updateProductInfo();
                                                }else {
                                                    //新增
                                                    if(productinfoBean==null){
                                                        productinfoBean=new ProductmaterialBean.ContentBean();
                                                    }
                                                    productinfoBean.setAttachments(attachmentsBeanslist);
                                                    createProduct();
                                                }
                                            }
                                        }

                                        continue;
                                    }else {
                                        if(!Func.IsStringEmpty(imageBean.getPath())){
                                            data = imageBean.getPath();
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
                                                                        if(viewOperatorType.equals(ViewOperatorType.EDIT)){
                                                                            //更新产品信息
                                                                            updateBean.setAttachments(attachmentsBeanslist);
                                                                            updateProductInfo();
                                                                        }else {
                                                                            //新增
                                                                            if(productinfoBean==null){
                                                                                productinfoBean=new ProductmaterialBean.ContentBean();
                                                                            }
                                                                            productinfoBean.setAttachments(attachmentsBeanslist);
                                                                            createProduct();
                                                                        }
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
        etType = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_type);
        etName = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_name);
        etAnnualpurchasevolume = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_annualpurchasevolume);
        etMonthlyUsage = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_monthlyUsage);
        etPurchaseVolumeNextMonth = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_purchaseVolumeNextMonth);
        tvCount = (TextView) mBodyLayout.findViewById(R.id.tv_count);
/*
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductMaterialActivity.this);
                buidler.setTitle("原料类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("raw_material_type");
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
*/

    }
    public void setTitle(){
        if(viewOperatorType!=null && viewOperatorType.equals(ViewOperatorType.EDIT)){
            mTitle = "原料信息";
        }else {
            mTitle = "新建原料信息";
        }
        setTitle(mTitle);
    }


    public void submit(){
        JiuyiHttp.POST("row_material/create")
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
        if(TextUtils.isEmpty(etType.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入原料类型！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(etName.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入原料名称！", JiuyiDialogBase.Dialog_Type_Yes, null);
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
    JiuyiHttp.GET("row_material/detail/"+id)
            .tag("request_get_row_material")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<ProductmaterialBean.ContentBean>() {
                @Override
                public void onSuccess(ProductmaterialBean.ContentBean contentBean) {
                    if(contentBean!=null){
                        updateBean=contentBean;
                        if(contentBean.getTypeValue()!=null){
                            etType.setText(contentBean.getTypeValue());
                        }
                        if(contentBean.getName()!=null){
                            etName.setText(contentBean.getName());
                        }
                        if(contentBean.getAnnualPurchaseVolume()>0){
                            etAnnualpurchasevolume.setText(contentBean.getAnnualPurchaseVolume()+"");
                        }
                        if(contentBean.getMonthlyUsage()>0){
                            etMonthlyUsage.setText(contentBean.getMonthlyUsage()+"");
                        }
                        if(contentBean.getPurchaseVolumeNextMonth()>0){
                            etPurchaseVolumeNextMonth.setText(contentBean.getPurchaseVolumeNextMonth()+"");
                        }

                        if(contentBean.getAttachments()!=null && contentBean.getAttachments().size()>0){
                            Bimp.tempSelectBitmap.clear();
                            for(int i=0;i<contentBean.getAttachments().size();i++){
                                AttachmentBean attachmentBean=contentBean.getAttachments().get(i);
                                ImageBean returnPhoto = new ImageBean();
                                if(!Func.IsStringEmpty(attachmentBean.getThumbnail())){
                                    returnPhoto.setThumbnailPath(attachmentBean.getThumbnail());
                                }
                                if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                    returnPhoto.setQiniuKey(attachmentBean.getQiniuKey());
                                }
                                if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                                    returnPhoto.setImgUrl(attachmentBean.getUrl());
                                }
                                Bimp.tempSelectBitmap.add(returnPhoto);
                            }
                            adapter.notifyDataSetChanged();
                            if(mtvcount!=null){
                                mtvcount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
                            }
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

    public void createProduct(){
        if(productinfoBean==null){
            productinfoBean=new ProductmaterialBean.ContentBean();
        }
//        if(sType>0){
//            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//            contentBean.setId(sType);
//            productinfoBean.setType(contentBean);
//        }
        if(!Func.IsStringEmpty(etType.getText().toString())){
            productinfoBean.setTypeValue(etType.getText().toString());
        }
        if(!Func.IsStringEmpty(etName.getText().toString())){
            productinfoBean.setName(etName.getText().toString());
        }
        if(!Func.IsStringEmpty(etAnnualpurchasevolume.getText().toString())){
            productinfoBean.setAnnualPurchaseVolume(Double.parseDouble(etAnnualpurchasevolume.getText().toString()));
        }
        if(!Func.IsStringEmpty(etMonthlyUsage.getText().toString())){
            productinfoBean.setMonthlyUsage(Double.parseDouble(etMonthlyUsage.getText().toString()));
        }
        if(!Func.IsStringEmpty(etPurchaseVolumeNextMonth.getText().toString())){
            productinfoBean.setPurchaseVolumeNextMonth(Double.parseDouble(etPurchaseVolumeNextMonth.getText().toString()));
        }
        if(productinfoBean.getMember()==null){
            MemberBeanID memberBean=new MemberBeanID();
            memberBean.setId(Customerid);
            productinfoBean.setMember(memberBean);
        }
        submit();
    }

    public void updateProductInfo(){
        if(updateBean==null){
            updateBean =new ProductmaterialBean.ContentBean();;
            updateBean.setId(beanid);
        }
//        if(sType>0){
//            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//            contentBean.setId(sType);
//            updateBean.setType(contentBean);
//        }
        if(!Func.IsStringEmpty(etType.getText().toString())){
            updateBean.setTypeValue(etType.getText().toString());
        }
        if(!Func.IsStringEmpty(etName.getText().toString())){
            updateBean.setName(etName.getText().toString());
        }
        if(!Func.IsStringEmpty(etAnnualpurchasevolume.getText().toString())){
            updateBean.setAnnualPurchaseVolume(Double.parseDouble(etAnnualpurchasevolume.getText().toString()));
        }
        if(!Func.IsStringEmpty(etMonthlyUsage.getText().toString())){
            updateBean.setMonthlyUsage(Double.parseDouble(etMonthlyUsage.getText().toString()));
        }
        if(!Func.IsStringEmpty(etPurchaseVolumeNextMonth.getText().toString())){
            updateBean.setPurchaseVolumeNextMonth(Double.parseDouble(etPurchaseVolumeNextMonth.getText().toString()));
        }
        if(updateBean.getMember()==null){
            MemberBeanID memberBean=new MemberBeanID();
            memberBean.setId(Customerid);
            updateBean.setMember(memberBean);
        }
        JiuyiHttp.PUT("row_material/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(com.common.GsonUtil.gson().toJson(updateBean))
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
}
