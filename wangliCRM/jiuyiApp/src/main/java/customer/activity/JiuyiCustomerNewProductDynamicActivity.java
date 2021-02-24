package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiDateUtil;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.entity.AttachmentBean;
import customer.entity.EquipmentBean;
import customer.entity.FactoryBean;
import customer.entity.FactoryFindBean;
import customer.entity.ImageBean;
import customer.entity.MemberBeanID;
import customer.entity.ProductdynamicBean;
import customer.entity.UploadTokenBean;
import customer.view.Bimp;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewProductDynamicActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 生产动态新增页面
 *****************************************************************
 */
public class JiuyiCustomerNewProductDynamicActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel;
    private TextView tvFactory,tv_productdate;
    private TextView tvEquipmenttype,tvEquipmentname;
//    private jiuyiEditText etEquipmentname;
    private TextView tvDate;
    private TextView tvEquipmentcount;
    private JiuyiEditText et_productname,et_spec,et_anlunamt,et_stock,et_productstock,et_remark;
    private JiuyiEditText etBootquantity,et_spandexNumber,et_spandexBackoffSpeed,et_spandexBackoffTension,et_drawRation,et_productUsingCondition,et_airPressure;
    private TextView tvBootedRatio;
    private TextView tvCount;

    private long sFatory =-1, sEquipmentname;
    private long Customerid=-1;
    private String viewOperatorType;
    private long beanid=-1;
    private ProductdynamicBean.ContentBean updateBean;
    private ProductdynamicBean.ContentBean productinfoBean;
    private List<AttachmentBean> attachmentsBeanslist;
    private List<FactoryFindBean.ContentBean> factoryBeanList=new ArrayList<>();
    private List<EquipmentBean.ContentBean> equipmentBeanList=new ArrayList<>();

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewproductdynamic_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        if(viewOperatorType.equals(ViewOperatorType.ADD)||viewOperatorType.equals(ViewOperatorType.EDIT)){
            getFactoryList();
            getEquipmentList();
        }
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
                                                        productinfoBean=new ProductdynamicBean.ContentBean();
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
                                                                                productinfoBean=new ProductdynamicBean.ContentBean();
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
                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerNewProductDynamicActivity.this);
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
        tvFactory = (TextView) mBodyLayout.findViewById(R.id.tv_factory);
        tvEquipmenttype = (TextView) mBodyLayout.findViewById(R.id.tv_equipmenttype);
        tvEquipmentname = (TextView) mBodyLayout.findViewById(R.id.tv_equipmentname);
        tvEquipmentcount = (TextView) mBodyLayout.findViewById(R.id.tv_equipmentcount);
        et_spec = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_spec);
        et_productname = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_productname);
        et_spandexNumber = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_spandexNumber);
        tv_productdate = (TextView) mBodyLayout.findViewById(R.id.tv_productdate);
//        tv_productdate.setText(JiuyiDateUtil.getNowTime3());
        if (tv_productdate != null) {
            tv_productdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerNewProductDynamicActivity.this);
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
                            tv_productdate.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        }
                    });
                    dialog.show();
                }
            });

        }
        et_spandexBackoffSpeed = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_spandexBackoffSpeed);
        et_spandexBackoffTension = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_spandexBackoffTension);
        et_drawRation = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_drawRation);
        et_productUsingCondition = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_productUsingCondition);
        et_airPressure = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_airPressure);
        etBootquantity = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_bootquantity);
        etBootquantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("11", "输入文字后的状态");
                if(!Func.IsStringEmpty(s.toString())){
                    if(!Func.IsStringEmpty(tvEquipmentcount.getText().toString()) && Integer.parseInt(tvEquipmentcount.getText().toString())>0){
                        float num= (float)Integer.parseInt(s.toString())/Integer.parseInt(tvEquipmentcount.getText().toString());
                        DecimalFormat df = new DecimalFormat("0.00");
                        String sfor = df.format(num*100);
                        tvBootedRatio.setText(sfor +"%");
                    }

                }else{
                    tvBootedRatio.setText("0.00");
                }
            }
        });
        tvBootedRatio = (TextView) mBodyLayout.findViewById(R.id.tv_bootedRatio);
        tvFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductDynamicActivity.this);
                buidler.setTitle("工厂");
                final String[] data;
                if(factoryBeanList!=null &&factoryBeanList.size()>0){
                    data=new String[factoryBeanList.size()];
                    for(int i=0;i<factoryBeanList.size();i++){
                        data[i]=factoryBeanList.get(i).getName();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvFactory.setText(data[which].trim());
                            sFatory =factoryBeanList.get(which).getId();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });

        tvEquipmentname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductDynamicActivity.this);
                buidler.setTitle("设备名称");
                final String[] data;
                if(equipmentBeanList!=null &&equipmentBeanList.size()>0){
                    data=new String[equipmentBeanList.size()];
                    for(int i=0;i<equipmentBeanList.size();i++){
                        data[i]=equipmentBeanList.get(i).getName();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvEquipmentname.setText(data[which].trim());
                            if(equipmentBeanList.get(which).getType()!=null){
                                tvEquipmenttype.setText(equipmentBeanList.get(which).getType().getValue());
                            }
                            tvEquipmentcount.setText(equipmentBeanList.get(which).getQuantity()+"");
                            if(!Func.IsStringEmpty(etBootquantity.getText().toString()) && Integer.parseInt(tvEquipmentcount.getText().toString())>0 ){
                                float num= (float)Integer.parseInt(etBootquantity.getText().toString())/Integer.parseInt(tvEquipmentcount.getText().toString());
                                DecimalFormat df = new DecimalFormat("0.00");
                                String sfor = df.format(num*100);
                                tvBootedRatio.setText(sfor +"%");
                            }
                            sEquipmentname =equipmentBeanList.get(which).getId();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }else{
                    Toast.makeText(JiuyiCustomerNewProductDynamicActivity.this, "没有可选设备信息!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        et_anlunamt = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_anlunamt);
        et_stock = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_stock);
        et_productstock = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_productstock);
        et_remark = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_remark);
    }
    public void setTitle(){
        if(viewOperatorType!=null && viewOperatorType.equals(ViewOperatorType.EDIT)){
            mTitle = "生产动态信息";
        }else {
            mTitle = "新建生产动态";
        }

        setTitle(mTitle);
    }

    public void submit(){
        JiuyiHttp.POST("performance/create")
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
        if(TextUtils.isEmpty(tvFactory.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择工厂！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
//        if(TextUtils.isEmpty(tvEquipmentname.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请选择设备名称！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
//        if(TextUtils.isEmpty(tvEquipmenttype.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请选择设备类型！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }

        if(TextUtils.isEmpty(et_productname.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入客户生产产品名称！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }

        if(TextUtils.isEmpty(et_spandexNumber.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入现用氨纶批号！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tv_productdate.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入氨纶生产日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
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
    JiuyiHttp.GET("performance/detail/"+id)
            .tag("request_get_performance")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<ProductdynamicBean.ContentBean>() {
                @Override
                public void onSuccess(ProductdynamicBean.ContentBean contentBean) {
                    if(contentBean!=null){
                        updateBean=contentBean;
                        if(contentBean.getFactory()!=null){
                            tvFactory.setText(contentBean.getFactory().getName());
                        }
                        if(contentBean.getEquipment()!=null){
                            tvEquipmenttype.setText(contentBean.getEquipment().getType().getValue());
                            tvEquipmentcount.setText(contentBean.getEquipment().getQuantity()+"");
                            tvEquipmentname.setText(contentBean.getEquipment().getName()+"");
                        }
//                        if(contentBean.getMonth()>0 && contentBean.getYear()>0){
//                            StringBuilder sb=new StringBuilder();
//                            sb.append(contentBean.getYear());
//                            if(String.valueOf(contentBean.getMonth()).length()==1){
//                                sb.append("0"+contentBean.getMonth());
//                            }else{
//                                sb.append(contentBean.getMonth());
//                            }
//                            tvDate.setText(sb.toString());
//                        }

                        if(contentBean.getRecordDate()!=null){
                            tvDate.setText(contentBean.getRecordDate());
                        }
                        if(contentBean.getSize()!=null){
                            et_spec.setText(contentBean.getSize());
                        }
                        if(contentBean.getName()!=null){
                            et_productname.setText(contentBean.getName());
                        }
                        if(contentBean.getProductionDate()!=null){
                            tv_productdate.setText(contentBean.getProductionDate());
                        }
                        if(contentBean.getUsedQuantityExpected()!=null){
                            et_anlunamt.setText(contentBean.getUsedQuantityExpected());
                        }
                        if(contentBean.getStockQuantity()!=null){
                            et_stock.setText(contentBean.getStockQuantity());
                        }
                        if(contentBean.getStockDays()!=null){
                            et_productstock.setText(contentBean.getStockDays());
                        }
                        if(contentBean.getRemark()!=null){
                            et_remark.setText(contentBean.getRemark());
                        }

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
                        if(contentBean.getBootedQuantity()>0){
                            etBootquantity.setText(contentBean.getBootedQuantity()+"");
                        }
                        if(contentBean.getBootedRatio()>0){
                            tvBootedRatio.setText(contentBean.getBootedRatio()+"%");
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
            productinfoBean=new ProductdynamicBean.ContentBean();
        }
        if(!Func.IsStringEmpty(tvFactory.getText().toString())){
            FactoryBean factoryBean=new FactoryBean();
            factoryBean.setId(sFatory);
            productinfoBean.setFactory(factoryBean);
        }

        if(sEquipmentname>0){
            EquipmentBean.ContentBean contentBean=new EquipmentBean.ContentBean();
            contentBean.setId(sEquipmentname);
            productinfoBean.setEquipment(contentBean);
        }
        if(!Func.IsStringEmpty(tvDate.getText().toString())){
            productinfoBean.setRecordDate(tvDate.getText().toString());
        }
        if(!Func.IsStringEmpty(et_spec.getText().toString())){
            productinfoBean.setSize(et_spec.getText().toString());
        }
        if(!Func.IsStringEmpty(et_productname.getText().toString())){
            productinfoBean.setName(et_productname.getText().toString());
        }
        if(!Func.IsStringEmpty(tv_productdate.getText().toString())){
            productinfoBean.setProductionDate(tv_productdate.getText().toString());
        }
        if(!Func.IsStringEmpty(et_anlunamt.getText().toString())){
            productinfoBean.setUsedQuantityExpected(et_anlunamt.getText().toString());
        }
        if(!Func.IsStringEmpty(et_stock.getText().toString())){
            productinfoBean.setStockQuantity(et_stock.getText().toString());
        }
        if(!Func.IsStringEmpty(et_productstock.getText().toString())){
            productinfoBean.setStockDays(et_productstock.getText().toString());
        }
        if(!Func.IsStringEmpty(et_remark.getText().toString())){
            productinfoBean.setRemark(et_remark.getText().toString());
        }
//        if(!Func.IsStringEmpty(tvDate.getText().toString())){
//            productinfoBean.setYear(Integer.parseInt(tvDate.getText().toString().substring(0,4)));
//            productinfoBean.setMonth(Integer.parseInt(tvDate.getText().toString().substring(4,6)));
//        }
        if(!Func.IsStringEmpty(etBootquantity.getText().toString())){
            productinfoBean.setBootedQuantity(Integer.parseInt(etBootquantity.getText().toString()));
        }
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
        if(!Func.IsStringEmpty(tvBootedRatio.getText().toString())){
            String bootRatio="";
            int i=tvBootedRatio.getText().toString().indexOf("%");
            if(i>0){
                bootRatio=tvBootedRatio.getText().toString().substring(0,i);
            }
            productinfoBean.setBootedRatio(Double.parseDouble(bootRatio));
        }
        MemberBeanID memberBean=new MemberBeanID();
        memberBean.setId(Customerid);
        productinfoBean.setMember(memberBean);
        submit();
    }

    public void updateProductInfo(){
        if(updateBean==null){
            updateBean =new ProductdynamicBean.ContentBean();;
            updateBean.setId(beanid);
        }
        if(sFatory>0){
            FactoryBean factoryBean=new FactoryBean();
            factoryBean.setId(sFatory);
            updateBean.setFactory(factoryBean);
        }
        if(sEquipmentname>0){
            EquipmentBean.ContentBean contentBean=new EquipmentBean.ContentBean();
            contentBean.setId(sEquipmentname);
            updateBean.setEquipment(contentBean);
        }
        if(!Func.IsStringEmpty(tvDate.getText().toString())){
            updateBean.setRecordDate(tvDate.getText().toString());
        }
        if(!Func.IsStringEmpty(et_spec.getText().toString())){
            updateBean.setSize(et_spec.getText().toString());
        }
        if(!Func.IsStringEmpty(et_productname.getText().toString())){
            updateBean.setName(et_productname.getText().toString());
        }
        if(!Func.IsStringEmpty(tv_productdate.getText().toString())){
            updateBean.setProductionDate(tv_productdate.getText().toString());
        }
        if(!Func.IsStringEmpty(et_anlunamt.getText().toString())){
            updateBean.setUsedQuantityExpected(et_anlunamt.getText().toString());
        }
        if(!Func.IsStringEmpty(et_stock.getText().toString())){
            updateBean.setStockQuantity(et_stock.getText().toString());
        }
        if(!Func.IsStringEmpty(et_productstock.getText().toString())){
            updateBean.setStockDays(et_productstock.getText().toString());
        }
        if(!Func.IsStringEmpty(et_remark.getText().toString())){
            updateBean.setRemark(et_remark.getText().toString());
        }
        if(!Func.IsStringEmpty(etBootquantity.getText().toString())){
            updateBean.setBootedQuantity(Integer.parseInt(etBootquantity.getText().toString()));
        }
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
        if(!Func.IsStringEmpty(tvBootedRatio.getText().toString())){
            String bootRatio="";
            int i=tvBootedRatio.getText().toString().indexOf("%");
            if(i>0){
                bootRatio=tvBootedRatio.getText().toString().substring(0,i);
            }
            updateBean.setBootedRatio(Double.parseDouble(bootRatio));
        }
        if(updateBean.getMember()==null){
            MemberBeanID memberBean=new MemberBeanID();
            memberBean.setId(Customerid);
            updateBean.setMember(memberBean);
        }
        JiuyiHttp.PUT("performance/update")
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


    private void getFactoryList() {
        final QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("id");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member.id");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST("factory/page")
                .tag("request_assist_page-vo")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<FactoryFindBean>() {
                    @Override
                    public void onSuccess(FactoryFindBean data) {
                        if(data!=null){
                            if(data.getContent()!=null && data.getContent().size()>0){
                                factoryBeanList=data.getContent();
                                for(int i=0;i<factoryBeanList.size();i++){
                                    if(factoryBeanList.get(i).isDefaults()){
                                        tvFactory.setText(factoryBeanList.get(i).getName());
                                        sFatory=factoryBeanList.get(i).getId();
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

    private void getEquipmentList() {
        final QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("id");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member.id");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST("equipment/page")
                .tag("request_equipment-vo")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<EquipmentBean>() {
                    @Override
                    public void onSuccess(EquipmentBean data) {
                        if(data!=null){
                            equipmentBeanList=data.getContent();
                        }
                    }
                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
}
