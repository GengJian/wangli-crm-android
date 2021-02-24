package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiDateUtil;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.model.DictBean;
import com.jiuyi.model.DictResultBean;
import com.jiuyi.tools.DictUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import customer.Utils.ViewOperatorType;
import customer.entity.AttachmentBean;
import customer.entity.CompeteInfoBean;
import customer.entity.ImageBean;
import customer.entity.MemberBeanID;
import customer.entity.UploadTokenBean;
import customer.view.Bimp;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewCompeteInfoActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 竞品信息新增页面
 *****************************************************************
 */

public class JiuyiCustomerNewCompeteInfoActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel;
    private TextView tvCompanyType;
    private TextView tvIndustryRank;
    private JiuyiEditText etCompanyName,et_productname,et_price,et_paytype,et_debtperiod,et_stock,et_remark;
    private TextView tvDate;
    private TextView tvBrand;
    private TextView tvBrandlocation;
    private JiuyiEditText etDunpermonth;
    private JiuyiEditText etSpecial;
    private TextView tvMarket;
    private JiuyiEditText etServicetype;
//    private jiuyiEditText etCompanybackgroud;
    private TextView tvCount;
    private JiuyiEditText et_spandexNumber,et_spandexBackoffSpeed,et_spandexBackoffTension,et_drawRation,et_productUsingCondition,et_airPressure;


    private long sCompanyType =-1, sIndustryRank =-1,sBrand=-1,sBrandlocation=-1,sMarket;
    private long Customerid=-1;
    private String viewOperatorType;
    private long beanid=-1;
    private CompeteInfoBean.ContentBean updateBean;
    private CompeteInfoBean.ContentBean productinfoBean;
    private List<AttachmentBean> attachmentsBeanslist;

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewcompeteinfo_layout"), null);
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
                                if (Func.IsStringEmpty(qimiuToken)) {

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
                                                        productinfoBean=new CompeteInfoBean.ContentBean();
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
                                                                                productinfoBean=new CompeteInfoBean.ContentBean();
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
        tvDate = (TextView) mBodyLayout.findViewById(R.id.tv_date);
        tvDate.setText(JiuyiDateUtil.getNowTime3());
        if (tvDate != null) {
            tvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerNewCompeteInfoActivity.this);
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
        tvCompanyType = (TextView) mBodyLayout.findViewById(R.id.tv_companyType);
        tvIndustryRank = (TextView) mBodyLayout.findViewById(R.id.tv_industryRank);
        etCompanyName = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_companyName);
        et_productname = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_productname);
        et_price = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_price);
        et_paytype = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_paytype);
        et_debtperiod = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_debtperiod);
        et_stock = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_stock);
        et_remark = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_remark);
        tvBrand = (TextView) mBodyLayout.findViewById(R.id.tv_brand);
        tvBrandlocation = (TextView) mBodyLayout.findViewById(R.id.tv_brandlocation);
        etDunpermonth = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_dunpermonth);
        etSpecial = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_special);
        tvMarket = (TextView) mBodyLayout.findViewById(R.id.tv_market);
        etServicetype = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_servicetype);
//        etCompanybackgroud = (jiuyiEditText) mBodyLayout.findViewById(R.id.et_companybackgroud);
        tvCount = (TextView) mBodyLayout.findViewById(R.id.tv_count);
        et_spandexNumber = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_spandexNumber);
        et_spandexBackoffSpeed = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_spandexBackoffSpeed);
        et_spandexBackoffTension = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_spandexBackoffTension);
        et_drawRation = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_drawRation);
        et_productUsingCondition = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_productUsingCondition);
        et_airPressure = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_airPressure);
        tvCompanyType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewCompeteInfoActivity.this);
                buidler.setTitle("竞企类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("competing_companies_type");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvCompanyType.setText(data[which].trim());
                            sCompanyType =dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tvIndustryRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewCompeteInfoActivity.this);
                buidler.setTitle("行业地位");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("industry_status");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvIndustryRank.setText(data[which].trim());
                            sIndustryRank =dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tvBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewCompeteInfoActivity.this);
                buidler.setTitle("竞企品牌");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("competing_goods_brand");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvBrand.setText(data[which].trim());
                            sBrand =dictBeansList.get(which).getOriginalid();
                            if(!Func.IsStringEmpty(dictBeansList.get(which).getRemark())){
                                etCompanyName.setText(dictBeansList.get(which).getRemark());
                            }
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tvBrandlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewCompeteInfoActivity.this);
                buidler.setTitle("品牌定位");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("brand_position");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvBrandlocation.setText(data[which].trim());
                            sBrandlocation =dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tvMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewCompeteInfoActivity.this);
                buidler.setTitle("竞品营销策略");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("distribution_channel");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvMarket.setText(data[which].trim());
                            sMarket =dictBeansList.get(which).getOriginalid();
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
            mTitle = "竞品信息";
        }else {
            mTitle = "新建竞品信息";
        }

        setTitle(mTitle);
    }


    public void submit(){
        JiuyiHttp.POST("competition_product/create")
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
        if(TextUtils.isEmpty(tvCompanyType.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择品牌类型！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(etCompanyName.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入竞企名称！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
//        if(TextUtils.isEmpty(tvBrand.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请选择竞品品牌！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
//        if(TextUtils.isEmpty(etDunpermonth.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请输入吨/月平均！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }

        if(TextUtils.isEmpty(et_spandexNumber.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入现用氨纶批号！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_productname.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入竞品生产产品名称！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_price.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入价格！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
//        if(TextUtils.isEmpty(et_spandexBackoffSpeed.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请输入氨纶退绕速度！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
        if(TextUtils.isEmpty(et_drawRation.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入牵伸比！", JiuyiDialogBase.Dialog_Type_Yes, null);
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
    JiuyiHttp.GET("competition_product/detail/"+id)
            .tag("request_get_competition_product")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<CompeteInfoBean.ContentBean>() {
                @Override
                public void onSuccess(CompeteInfoBean.ContentBean contentBean) {
                    if(contentBean!=null){
                        updateBean=contentBean;
                        if(contentBean.getCompanyType()!=null){
                            tvCompanyType.setText(contentBean.getCompanyType().getValue());
                        }
                        if(contentBean.getIndustryRank()!=null){
                            tvIndustryRank.setText(contentBean.getIndustryRank().getValue());
                        }
                        if(contentBean.getCompanyName()!=null){
                            etCompanyName.setText(contentBean.getCompanyName());
                        }
                        if(contentBean.getBrand()!=null){
                            tvBrand.setText(contentBean.getBrand().getValue());
                        }
                        if(contentBean.getBrandPosition()!=null){
                            tvBrandlocation.setText(contentBean.getBrandPosition().getValue());
                        }
                        if(contentBean.getSalesVolume()!=null && contentBean.getSalesVolume()>0){
                            etDunpermonth.setText(contentBean.getSalesVolume()+"");
                        }
                        if(contentBean.getFeatures()!=null){
                            etSpecial.setText(contentBean.getFeatures());
                        }
                        if(contentBean.getMarketStrategy()!=null){
                            tvMarket.setText(contentBean.getMarketStrategy());
                        }
                        if(contentBean.getServiceMode()!=null){
                            etServicetype.setText(contentBean.getServiceMode());
                        }
//                        if(contentBean.getBackground()!=null){
//                            etCompanybackgroud.setText(contentBean.getBackground());
//                        }
                        if(contentBean.getSpandexNumber()!=null){
                            et_spandexNumber.setText(contentBean.getSpandexNumber());
                        }
                        if(contentBean.getSpandexBackoffSpeed()!=null){
                            et_spandexBackoffSpeed.setText(contentBean.getSpandexBackoffSpeed());
                        }
                        if(contentBean.getSpandexBackoffTension()!=null){
                            et_spandexBackoffTension.setText(contentBean.getSpandexBackoffTension());
                        }
                        if(contentBean.getDrawRation()!=null){
                            et_drawRation.setText(contentBean.getDrawRation());
                        }
                        if(contentBean.getAirPressure()!=null){
                            et_airPressure.setText(contentBean.getAirPressure());
                        }
                        if(contentBean.getProductUsingCondition()!=null){
                            et_productUsingCondition.setText(contentBean.getProductUsingCondition());
                        }

                        if(contentBean.getRecordDate()!=null){
                            tvDate.setText(contentBean.getRecordDate());
                        }
                        if(contentBean.getName()!=null){
                            et_productname.setText(contentBean.getName());
                        }
                        et_price.setText(contentBean.getPriceVolume()+"");
                        if(contentBean.getPayMethod()!=null){
                            et_paytype.setText(contentBean.getPayMethod());
                        }
                        if(contentBean.getAccountPeriod()!=null){
                            et_debtperiod.setText(contentBean.getAccountPeriod());
                        }
                        if(contentBean.getStockQuantity()!=null){
                            et_stock.setText(contentBean.getStockQuantity());
                        }
                        if(contentBean.getRemark()!=null){
                            et_remark.setText(contentBean.getRemark());
                        }
                        if(contentBean.getDistributionChannel()!=null){
                            tvMarket.setText(contentBean.getDistributionChannel().getValue());
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
            productinfoBean=new CompeteInfoBean.ContentBean();
        }
        if(sCompanyType>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sCompanyType);
            productinfoBean.setCompanyType(contentBean);
        }
        if(sIndustryRank>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sIndustryRank);
            productinfoBean.setIndustryRank(contentBean);
        }
        if(sBrand>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sBrand);
            productinfoBean.setBrand(contentBean);
        }
        if(sBrandlocation>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sBrandlocation);
            productinfoBean.setBrandPosition(contentBean);
        }
        if(!Func.IsStringEmpty(tvDate.getText().toString())){
            productinfoBean.setRecordDate(tvDate.getText().toString());
        }
        if(!Func.IsStringEmpty(et_productname.getText().toString())){
            productinfoBean.setName(et_productname.getText().toString());
        }
        if(!Func.IsStringEmpty(et_price.getText().toString())){
            productinfoBean.setPriceVolume(Double.parseDouble(et_price.getText().toString()));
        }
        if(!Func.IsStringEmpty(et_paytype.getText().toString())){
            productinfoBean.setPayMethod(et_paytype.getText().toString());
        }
        if(!Func.IsStringEmpty(et_debtperiod.getText().toString())){
            productinfoBean.setAccountPeriod(et_debtperiod.getText().toString());
        }
        if(!Func.IsStringEmpty(et_stock.getText().toString())){
            productinfoBean.setStockQuantity(et_stock.getText().toString());
        }
        if(!Func.IsStringEmpty(et_remark.getText().toString())){
            productinfoBean.setRemark(et_remark.getText().toString());
        }

        if(!Func.IsStringEmpty(etCompanyName.getText().toString())){
            productinfoBean.setCompanyName(etCompanyName.getText().toString());
        }
        if(!Func.IsStringEmpty(etDunpermonth.getText().toString())){
            productinfoBean.setSalesVolume(Double.parseDouble(etDunpermonth.getText().toString()));
        }
        if(!Func.IsStringEmpty(etSpecial.getText().toString())){
            productinfoBean.setFeatures(etSpecial.getText().toString());
        }
        if(!Func.IsStringEmpty(tvMarket.getText().toString())){
            productinfoBean.setMarketStrategy(tvMarket.getText().toString());
        }
        if(!Func.IsStringEmpty(etServicetype.getText().toString())){
            productinfoBean.setServiceMode(etServicetype.getText().toString());
        }
//        if(!Func.IsStringEmpty(etCompanybackgroud.getText().toString())){
//            productinfoBean.setBackground(etCompanybackgroud.getText().toString());
//        }

        if(!Func.IsStringEmpty(et_spandexNumber.getText().toString())){
            productinfoBean.setSpandexNumber(et_spandexNumber.getText().toString());
        }
        if(!Func.IsStringEmpty(et_spandexBackoffSpeed.getText().toString())){
            productinfoBean.setSpandexBackoffSpeed(et_spandexBackoffSpeed.getText().toString());
        }
        if(!Func.IsStringEmpty(et_spandexBackoffTension.getText().toString())){
            productinfoBean.setSpandexBackoffTension(et_spandexBackoffTension.getText().toString());
        }
        if(!Func.IsStringEmpty(et_drawRation.getText().toString())){
            productinfoBean.setDrawRation(et_drawRation.getText().toString());
        }
        if(!Func.IsStringEmpty(et_airPressure.getText().toString())){
            productinfoBean.setAirPressure(et_airPressure.getText().toString());
        }
        if(!Func.IsStringEmpty(et_productUsingCondition.getText().toString())){
            productinfoBean.setProductUsingCondition(et_productUsingCondition.getText().toString());
        }

        if(sMarket>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sMarket);
            productinfoBean.setDistributionChannel(contentBean);
        }

        MemberBeanID memberBean=new MemberBeanID();
        memberBean.setId(Customerid);
        productinfoBean.setMember(memberBean);
        submit();
    }

    public void updateProductInfo(){
        if(updateBean==null){
            updateBean =new CompeteInfoBean.ContentBean();;
            updateBean.setId(beanid);
        }
        if(sCompanyType>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sCompanyType);
            updateBean.setCompanyType(contentBean);
        }
        if(sIndustryRank>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sIndustryRank);
            updateBean.setIndustryRank(contentBean);
        }
        if(sBrand>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sBrand);
            updateBean.setBrand(contentBean);
        }
        if(sBrandlocation>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sBrandlocation);
            updateBean.setBrandPosition(contentBean);
        }
        if(!Func.IsStringEmpty(etCompanyName.getText().toString())){
            updateBean.setCompanyName(etCompanyName.getText().toString());
        }
        if(!Func.IsStringEmpty(etDunpermonth.getText().toString())){
            updateBean.setSalesVolume(Double.parseDouble(etDunpermonth.getText().toString()));
        }
        if(!Func.IsStringEmpty(etSpecial.getText().toString())){
            updateBean.setFeatures(etSpecial.getText().toString());
        }
        if(!Func.IsStringEmpty(tvMarket.getText().toString())){
            updateBean.setMarketStrategy(tvMarket.getText().toString());
        }
        if(!Func.IsStringEmpty(etServicetype.getText().toString())){
            updateBean.setServiceMode(etServicetype.getText().toString());
        }
//        if(!Func.IsStringEmpty(etCompanybackgroud.getText().toString())){
//            updateBean.setBackground(etCompanybackgroud.getText().toString());
//        }
        if(!Func.IsStringEmpty(et_spandexNumber.getText().toString())){
            updateBean.setSpandexNumber(et_spandexNumber.getText().toString());
        }
        if(!Func.IsStringEmpty(et_spandexBackoffSpeed.getText().toString())){
            updateBean.setSpandexBackoffSpeed(et_spandexBackoffSpeed.getText().toString());
        }
        if(!Func.IsStringEmpty(et_spandexBackoffTension.getText().toString())){
            updateBean.setSpandexBackoffTension(et_spandexBackoffTension.getText().toString());
        }
        if(!Func.IsStringEmpty(et_drawRation.getText().toString())){
            updateBean.setDrawRation(et_drawRation.getText().toString());
        }
        if(!Func.IsStringEmpty(et_airPressure.getText().toString())){
            updateBean.setAirPressure(et_airPressure.getText().toString());
        }
        if(!Func.IsStringEmpty(et_productUsingCondition.getText().toString())){
            updateBean.setProductUsingCondition(et_productUsingCondition.getText().toString());
        }
        if(updateBean.getMember()==null){
            MemberBeanID memberBean=new MemberBeanID();
            memberBean.setId(Customerid);
            updateBean.setMember(memberBean);
        }
        if(!Func.IsStringEmpty(tvDate.getText().toString())){
            updateBean.setRecordDate(tvDate.getText().toString());
        }
        if(!Func.IsStringEmpty(et_productname.getText().toString())){
            updateBean.setName(et_productname.getText().toString());
        }
        if(!Func.IsStringEmpty(et_price.getText().toString())){
            updateBean.setPriceVolume(Double.parseDouble(et_price.getText().toString()));
        }
        if(!Func.IsStringEmpty(et_paytype.getText().toString())){
            updateBean.setPayMethod(et_paytype.getText().toString());
        }
        if(!Func.IsStringEmpty(et_debtperiod.getText().toString())){
            updateBean.setAccountPeriod(et_debtperiod.getText().toString());
        }
        if(!Func.IsStringEmpty(et_stock.getText().toString())){
            updateBean.setStockQuantity(et_stock.getText().toString());
        }
        if(!Func.IsStringEmpty(et_remark.getText().toString())){
            updateBean.setRemark(et_remark.getText().toString());
        }
        JiuyiHttp.PUT("competition_product/update")
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
