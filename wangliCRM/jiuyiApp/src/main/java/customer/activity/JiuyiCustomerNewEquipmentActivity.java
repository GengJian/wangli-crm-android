package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
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
import com.control.utils.JiuyiDateUtil;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
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

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.entity.AttachmentBean;
import customer.entity.EquipmentBean;
import customer.entity.FactoryBean;
import customer.entity.FactoryFindBean;
import customer.entity.ImageBean;
import customer.entity.MemberBeanID;
import customer.entity.UploadTokenBean;
import customer.view.Bimp;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewEquipmentActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 工厂设备新增页面
 *****************************************************************
 */
public class JiuyiCustomerNewEquipmentActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel;
    private TextView tvFatory,tvDate;
    private TextView tvRegion;
    private TextView tvEquipmenttype;
    private JiuyiEditText etEquipmentname;
    private JiuyiEditText etBrand;
    private JiuyiEditText etSize;
    private JiuyiEditText etBuyyear;
    private JiuyiEditText etEquipmentcount,etDirectType,etRemark;
    private TextView tvCount;


    private TextView tv_productType;
    private JiuyiEditText et_compositionContent,et_gauze,et_gramWeight,et_dyeing;

    private long sFatory =-1, sRegion =-1,sEquipmenttype=-1,sProducttype;
    private long Customerid=-1;
    private String viewOperatorType;
    private long beanid=-1;
    private EquipmentBean.ContentBean updateBean;
    private EquipmentBean.ContentBean productinfoBean;
    private List<AttachmentBean> attachmentsBeanslist;
    private List<FactoryFindBean.ContentBean> factoryBeanList=new ArrayList<>();

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        getFactoryList();
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewequipment_layout"), null);
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
                                                        productinfoBean=new EquipmentBean.ContentBean();
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
                                                                                productinfoBean=new EquipmentBean.ContentBean();
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
                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerNewEquipmentActivity.this);
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
        tvFatory = (TextView) mBodyLayout.findViewById(R.id.tv_fatory);
        tvRegion = (TextView) mBodyLayout.findViewById(R.id.tv_region);
        tvEquipmenttype = (TextView) mBodyLayout.findViewById(R.id.tv_equipmenttype);
        etEquipmentname = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_equipmentname);
        etBrand = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_brand);
        etSize = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_size);
        etBuyyear = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_buyyear);
        etEquipmentcount = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_equipmentcount);
        etDirectType = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_directtype);
        etRemark = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_remark);
        tvCount = (TextView) mBodyLayout.findViewById(R.id.tv_count);
        tvRegion = (TextView) mBodyLayout.findViewById(R.id.tv_region);
        tv_productType = (TextView) mBodyLayout.findViewById(R.id.tv_productType);
        et_compositionContent = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_compositionContent);
        et_gauze = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_gauze);
        et_gramWeight = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_gramWeight);
        et_dyeing = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_dyeing);
        tvFatory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewEquipmentActivity.this);
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
                            tvFatory.setText(data[which].trim());
                            sFatory =factoryBeanList.get(which).getId();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tvRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewEquipmentActivity.this);
                buidler.setTitle("领域类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("member_field");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvRegion.setText(data[which].trim());
                            tvEquipmenttype.setText("");
                            sRegion =dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tvEquipmenttype = (TextView) mBodyLayout.findViewById(R.id.tv_equipmenttype);
        tvEquipmenttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Func.IsStringEmpty(tvRegion.getText().toString())){
                    startDialog(DialogID.DialogDoNothing, "", "请先选择领域", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewEquipmentActivity.this);
                buidler.setTitle("设备类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("equipment_type",tvRegion.getText().toString().trim());
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvEquipmenttype.setText(data[which].trim());
                            sEquipmenttype =dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }else{
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "该领域没有可选设备类型!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_productType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewEquipmentActivity.this);
                buidler.setTitle("产品类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("product_type");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tv_productType.setText(data[which].trim());
                            sProducttype =dictBeansList.get(which).getOriginalid();
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
            mTitle = "工厂设备信息";
        }else {
            mTitle = "新建工厂设备信息";
        }
        setTitle(mTitle);
    }


    public void submit(){
        JiuyiHttp.POST("equipment/create")
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
        if(TextUtils.isEmpty(tvFatory.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择工厂！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvRegion.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入领域！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvEquipmenttype.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择设备类型！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(etEquipmentname.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入设备名称！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(etEquipmentcount.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入设备台数！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Integer.parseInt(etEquipmentcount.getText().toString())<=0){
            startDialog(DialogID.DialogDoNothing, "", "设备台数必须大于0", JiuyiDialogBase.Dialog_Type_Yes, null);
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
    JiuyiHttp.GET("equipment/detail/"+id)
            .tag("request_get_equipment")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<EquipmentBean.ContentBean>() {
                @Override
                public void onSuccess(EquipmentBean.ContentBean contentBean) {
                    if(contentBean!=null){
                        updateBean=contentBean;
                        if(contentBean.getFactory()!=null){
                            tvFatory.setText(contentBean.getFactory().getName());
                        }
                        if(contentBean.getField()!=null){
                            tvRegion.setText(contentBean.getField().getValue());
                        }
                        if(contentBean.getType()!=null){
                            tvEquipmenttype.setText(contentBean.getType().getValue());
                        }
                        if(contentBean.getName()!=null){
                            etEquipmentname.setText(contentBean.getName());
                        }
                        if(contentBean.getBrands()!=null){
                            etBrand.setText(contentBean.getBrands());
                        }
                        if(contentBean.getSize()!=null){
                            etSize.setText(contentBean.getSize());
                        }
                        etBuyyear.setText(contentBean.getPurchaseYears()+"");
                        etEquipmentcount.setText(contentBean.getQuantity()+"");

                        if(contentBean.getRecordDate()!=null){
                            tvDate.setText(contentBean.getRecordDate());
                        }
                        if(contentBean.getGuideMethod()!=null){
                            etDirectType.setText(contentBean.getGuideMethod());
                        }
                        if(contentBean.getRemark()!=null){
                            etRemark.setText(contentBean.getRemark());
                        }

//                        if(contentBean.getProductType()!=null){
//                            tv_productType.setText(contentBean.getProductType().getValue());
//                        }
//                        if(contentBean.getCompositionContent()!=null){
//                            et_compositionContent.setText(contentBean.getCompositionContent());
//                        }
//                        if(contentBean.getGauze()!=null){
//                            et_gauze.setText(contentBean.getGauze());
//                        }
//                        if(contentBean.getGramWeight()!=null){
//                            et_gramWeight.setText(contentBean.getGramWeight());
//                        }
//                        if(contentBean.getDyeing()!=null){
//                            et_dyeing.setText(contentBean.getDyeing());
//                        }
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
            productinfoBean=new EquipmentBean.ContentBean();
        }
        if(!Func.IsStringEmpty(tvFatory.getText().toString())){
            FactoryBean factoryBean=new FactoryBean();
            factoryBean.setId(sFatory);
            productinfoBean.setFactory(factoryBean);
        }
        if(!Func.IsStringEmpty(tvDate.getText().toString())){
            productinfoBean.setRecordDate(tvDate.getText().toString());
        }
        if(sRegion>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sRegion);
            productinfoBean.setField(contentBean);
        }
        if(sEquipmenttype>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sEquipmenttype);
            productinfoBean.setType(contentBean);
        }
        if(!Func.IsStringEmpty(etEquipmentname.getText().toString())){
            productinfoBean.setName(etEquipmentname.getText().toString());
        }
        if(!Func.IsStringEmpty(etBrand.getText().toString())){
            productinfoBean.setBrands(etBrand.getText().toString());
        }
        if(!Func.IsStringEmpty(etSize.getText().toString())){
            productinfoBean.setSize(etSize.getText().toString());
        }
        if(!Func.IsStringEmpty(etBuyyear.getText().toString())){
            productinfoBean.setPurchaseYears(Integer.parseInt(etBuyyear.getText().toString()));
        }
        if(!Func.IsStringEmpty(etEquipmentcount.getText().toString())){
            productinfoBean.setQuantity(Integer.parseInt(etEquipmentcount.getText().toString()));
        }
        if(!Func.IsStringEmpty(etDirectType.getText().toString())){
            productinfoBean.setGuideMethod(etDirectType.getText().toString());
        }
        if(!Func.IsStringEmpty(etRemark.getText().toString())){
            productinfoBean.setRemark(etRemark.getText().toString());
        }
//        if(sProducttype>0){
//            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//            contentBean.setId(sProducttype);
//            productinfoBean.setProductType(contentBean);
//        }
//        if(!Func.IsStringEmpty(et_compositionContent.getText().toString())){
//            productinfoBean.setCompositionContent(et_compositionContent.getText().toString());
//        }
//        if(!Func.IsStringEmpty(et_gauze.getText().toString())){
//            productinfoBean.setGauze(et_gauze.getText().toString());
//        }
//        if(!Func.IsStringEmpty(et_gramWeight.getText().toString())){
//            productinfoBean.setGramWeight(et_gramWeight.getText().toString());
//        }
//        if(!Func.IsStringEmpty(et_dyeing.getText().toString())){
//            productinfoBean.setDyeing(et_dyeing.getText().toString());
//        }
        MemberBeanID memberBean=new MemberBeanID();
        memberBean.setId(Customerid);
        productinfoBean.setMember(memberBean);
        submit();
    }

    public void updateProductInfo(){
        if(updateBean==null){
            updateBean =new EquipmentBean.ContentBean();;
            updateBean.setId(beanid);
        }
        if(!Func.IsStringEmpty(tvFatory.getText().toString())){
            FactoryBean factoryBean=new FactoryBean();
            factoryBean.setId(sFatory);
            updateBean.setFactory(factoryBean);
        }
        if(!Func.IsStringEmpty(tvDate.getText().toString())){
            updateBean.setRecordDate(tvDate.getText().toString());
        }
        if(sRegion>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sRegion);
            updateBean.setField(contentBean);
        }
        if(sEquipmenttype>0){
            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
            contentBean.setId(sEquipmenttype);
            updateBean.setType(contentBean);
        }
        if(!Func.IsStringEmpty(etEquipmentname.getText().toString())){
            updateBean.setName(etEquipmentname.getText().toString());
        }
        if(!Func.IsStringEmpty(etBrand.getText().toString())){
            updateBean.setBrands(etBrand.getText().toString());
        }
        if(!Func.IsStringEmpty(etSize.getText().toString())){
            updateBean.setSize(etSize.getText().toString());
        }
        if(!Func.IsStringEmpty(etBuyyear.getText().toString())){
            updateBean.setPurchaseYears(Integer.parseInt(etBuyyear.getText().toString()));
        }
        if(!Func.IsStringEmpty(etEquipmentcount.getText().toString())){
            updateBean.setQuantity(Integer.parseInt(etEquipmentcount.getText().toString()));
        }
        if(!Func.IsStringEmpty(etDirectType.getText().toString())){
            updateBean.setGuideMethod(etDirectType.getText().toString());
        }
        if(!Func.IsStringEmpty(etRemark.getText().toString())){
            updateBean.setRemark(etRemark.getText().toString());
        }
//        if(sProducttype>0){
//            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//            contentBean.setId(sProducttype);
//            updateBean.setProductType(contentBean);
//        }
//        if(!Func.IsStringEmpty(et_compositionContent.getText().toString())){
//            updateBean.setCompositionContent(et_compositionContent.getText().toString());
//        }
//        if(!Func.IsStringEmpty(et_gauze.getText().toString())){
//            updateBean.setGauze(et_gauze.getText().toString());
//        }
//        if(!Func.IsStringEmpty(et_gramWeight.getText().toString())){
//            updateBean.setGramWeight(et_gramWeight.getText().toString());
//        }
//        if(!Func.IsStringEmpty(et_dyeing.getText().toString())){
//            updateBean.setDyeing(et_dyeing.getText().toString());
//        }
        if(updateBean.getMember()==null){
            MemberBeanID memberBean=new MemberBeanID();
            memberBean.setId(Customerid);
            updateBean.setMember(memberBean);
        }
        JiuyiHttp.PUT("equipment/update")
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
                                        tvFatory.setText(factoryBeanList.get(i).getName());
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
}
