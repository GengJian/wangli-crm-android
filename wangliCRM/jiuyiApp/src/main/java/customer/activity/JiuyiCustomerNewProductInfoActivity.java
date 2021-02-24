package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.entity.AttachmentBean;
import customer.entity.FactoryBean;
import customer.entity.FactoryFindBean;
import customer.entity.ImageBean;
import customer.entity.ProductinfoBean;
import customer.entity.UploadTokenBean;
import customer.view.Bimp;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewProductInfoActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 产品信息新增页面
 *****************************************************************
 */
public class JiuyiCustomerNewProductInfoActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel;
    private ProductinfoBean.ContentBean productinfoBean;
    private List<AttachmentBean> attachmentsBeanslist;
    private TextView tvProducttype;
    private JiuyiEditText etProductname;
    private JiuyiEditText tv_ingredient;
    private TextView tvYarn;
    private JiuyiEditText tvWeight,tvWidth;
    private JiuyiEditText tvDyeingTechnology;
    private JiuyiEditText tvQualityStandard;
    private TextView tvPopularElement;
    private JiuyiEditText tvApplicableSession;
    private TextView tvPrintStyle;
    private TextView tvColor;
    private TextView tvMarketExpectation;
    private JiuyiEditText et_clientgroup/*,et_marketExpectation*/;
    private JiuyiEditText etMarketReferencePrice;
    private long sProducttype=-1,singredient=-1,sYarn=-1,sWeight=-1,sDyeingTechnology=-1,sQualityStandard=-1,sPopularElement=-1;
    private long sApplicableSession,sPrintStyle,sColor,sMarketExpectation;
    private long Customerid=-1;
    private String viewOperatorType;
    private long beanid=-1;
    private TextView tvFatory;
    private List<FactoryFindBean.ContentBean> factoryBeanList=new ArrayList<>();
    private long sFatory =-1;
    private ProductinfoBean.ContentBean updateBean;

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        getFactoryList();
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewproductinfo_layout"), null);
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
                        //更新产品信息
                        attachmentsBeanslist=new ArrayList<AttachmentBean>();
                        updateBean.setAttachmentBeansList(attachmentsBeanslist);
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
                                        //附件上传成功
                                        if(attachmentsBeanslist!=null && attachmentsBeanslist.size()>0){
                                            if(attachmentsBeanslist.size()==Bimp.tempSelectBitmap.size()){
                                                if(viewOperatorType.equals(ViewOperatorType.EDIT)){
                                                    //更新产品信息
                                                    updateBean.setAttachmentBeansList(attachmentsBeanslist);
                                                    updateProductInfo();
                                                }else {
                                                    //新增
                                                    if(productinfoBean==null){
                                                        productinfoBean=new ProductinfoBean.ContentBean();
                                                    }
                                                    productinfoBean.setAttachmentBeansList(attachmentsBeanslist);
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
                                                                            updateBean.setAttachmentBeansList(attachmentsBeanslist);
                                                                            updateProductInfo();
                                                                        }else {
                                                                            //新增
                                                                            if(productinfoBean==null){
                                                                                productinfoBean=new ProductinfoBean.ContentBean();
                                                                            }
                                                                            productinfoBean.setAttachmentBeansList(attachmentsBeanslist);
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
        tvFatory = (TextView) mBodyLayout.findViewById(R.id.tv_fatory);
        tvFatory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductInfoActivity.this);
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

        et_clientgroup = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_clientgroup);
        etMarketReferencePrice = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_marketReferencePrice);
        etProductname = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_productname);
        tvProducttype = (TextView) mBodyLayout.findViewById(R.id.tv_producttype);
        tvProducttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductInfoActivity.this);
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
                            tvProducttype.setText(data[which].trim());
                            sProducttype=dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tv_ingredient = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_ingredient);
        tvYarn = (TextView) mBodyLayout.findViewById(R.id.tv_yarn);
        tvYarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductInfoActivity.this);
                buidler.setTitle("纱支类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("product_yarn");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvYarn.setText(data[which].trim());
                            sYarn=dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tvWeight = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_weight);
        tvWidth = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_width);
        tvDyeingTechnology = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_dyeingTechnology);
        tvQualityStandard = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_qualityStandard);
        tvPopularElement = (TextView) mBodyLayout.findViewById(R.id.tv_popularElement);
        tvPopularElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductInfoActivity.this);
                buidler.setTitle("流行元素类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("product_popular_element");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvPopularElement.setText(data[which].trim());
                            sPopularElement=dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tvApplicableSession = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_applicableSession);
        tvPrintStyle = (TextView) mBodyLayout.findViewById(R.id.tv_printStyle);
        tvPrintStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductInfoActivity.this);
                buidler.setTitle("印花风格类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("product_print_style");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvPrintStyle.setText(data[which].trim());
                            sPrintStyle=dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });

        tvColor = (TextView) mBodyLayout.findViewById(R.id.tv_color);
        tvColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductInfoActivity.this);
                buidler.setTitle("颜色类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("product_color");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvColor.setText(data[which].trim());
                            sColor=dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        tvMarketExpectation = (TextView) mBodyLayout.findViewById(R.id.tv_marketExpectation);
        tvMarketExpectation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewProductInfoActivity.this);
                buidler.setTitle("市场预期类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("product_market_expectation");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvMarketExpectation.setText(data[which].trim());
                            sMarketExpectation=dictBeansList.get(which).getOriginalid();
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
            mTitle = "产品信息";
        }else {
            mTitle = "新建产品信息";
        }

        setTitle(mTitle);
    }

    public void submit(){
        JiuyiHttp.POST("product/create")
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
        if(TextUtils.isEmpty(etProductname.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入产品名称！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tv_ingredient.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入成分含量！", JiuyiDialogBase.Dialog_Type_Yes, null);
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
    JiuyiHttp.GET("product/detail/"+id)
            .tag("request_get_member")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<ProductinfoBean.ContentBean>() {
                @Override
                public void onSuccess(ProductinfoBean.ContentBean contentBean) {
                    if(contentBean!=null){
                        updateBean=contentBean;
//                        if(contentBean.getCategory()!=null){
//                            tvProducttype.setText(contentBean.getCategory().getValue());
//                        }
                        if(contentBean.getFactory()!=null){
                            tvFatory.setText(contentBean.getFactory().getName());
                        }
                        if(contentBean.getName()!=null){
                            etProductname.setText(contentBean.getName());
                        }
                        if(contentBean.getIngredientValue()!=null){
                            tv_ingredient.setText(contentBean.getIngredientValue());
                        }
//                        if(contentBean.getYarn()!=null){
//                            tvYarn.setText(contentBean.getYarn().getValue());
//                        }
                        if(contentBean.getWeightValue()!=null){
                            tvWeight.setText(contentBean.getWeightValue());
                        }
                        if(contentBean.getFinishedDoor()!=null){
                            tvWidth.setText(contentBean.getFinishedDoor());
                        }
                        if(contentBean.getDyeingTechnologyValue()!=null){
                            tvDyeingTechnology.setText(contentBean.getDyeingTechnologyValue());
                        }
                        if(contentBean.getQualityStandardValue()!=null){
                            tvQualityStandard.setText(contentBean.getQualityStandardValue());
                        }
//                        if(contentBean.getPopularElement()!=null){
//                            tvPopularElement.setText(contentBean.getPopularElement().getValue());
//                        }
                        if(contentBean.getApplicableSessionValue()!=null){
                            tvApplicableSession.setText(contentBean.getApplicableSessionValue());
                        }
//                        if(contentBean.getPrintStyle()!=null){
//                            tvPrintStyle.setText(contentBean.getPrintStyle().getValue());
//                        }
//                        if(contentBean.getColor()!=null){
//                            tvColor.setText(contentBean.getColor().getValue());
//                        }
//                        if(contentBean.getMarketExpectation()!=null){
//                            tvMarketExpectation.setText(contentBean.getMarketExpectation().getValue());
//                        }
//                        if(contentBean.getCustomerGroup()!=null){
//                            et_clientgroup.setText(contentBean.getCustomerGroup());
//                        }
//                        if(contentBean.getMarketExpectation()!=null){
//                            tvMarketExpectation.setText(contentBean.getMarketExpectation().getValue());
//                        }
                        etMarketReferencePrice.setText(contentBean.getMarketReferencePrice()+"");
                        if(contentBean.getAttachmentBeansList()!=null && contentBean.getAttachmentBeansList().size()>0){
                            Bimp.tempSelectBitmap.clear();
                            for(int i=0;i<contentBean.getAttachmentBeansList().size();i++){
                                AttachmentBean attachmentBean=contentBean.getAttachmentBeansList().get(i);
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
            productinfoBean=new ProductinfoBean.ContentBean();
        }
        if(!Func.IsStringEmpty(tvFatory.getText().toString())){
            FactoryBean factoryBean=new FactoryBean();
            factoryBean.setId(sFatory);
            productinfoBean.setFactory(factoryBean);
        }
//        DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//        contentBean.setId(sProducttype);;
//        productinfoBean.setCategory(contentBean);
        productinfoBean.setName(etProductname.getText().toString());
        if(!Func.IsStringEmpty(tv_ingredient.getText().toString())){
            productinfoBean.setIngredientValue(tv_ingredient.getText().toString());
        }
        if(!Func.IsStringEmpty(tvWeight.getText().toString())){
            productinfoBean.setWeightValue(tvWeight.getText().toString());
        }
        if(!Func.IsStringEmpty(tvWidth.getText().toString())){
            productinfoBean.setFinishedDoor(tvWidth.getText().toString());
        }
        if(!Func.IsStringEmpty(tvDyeingTechnology.getText().toString())){
            productinfoBean.setDyeingTechnologyValue(tvDyeingTechnology.getText().toString());
        }
        if(!Func.IsStringEmpty(tvQualityStandard.getText().toString())){
            productinfoBean.setQualityStandardValue(tvQualityStandard.getText().toString());
        }
        if(!Func.IsStringEmpty(tvApplicableSession.getText().toString())){
            productinfoBean.setApplicableSessionValue(tvApplicableSession.getText().toString());
        }
//        if(singredient>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(singredient);
//            productinfoBean.setIngredient(contentBean2);
//        }
//        if(sYarn>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sYarn);
//            productinfoBean.setYarn(contentBean2);
//        }
//        if(sWeight>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sWeight);
//            productinfoBean.setWeight(contentBean2);
//        }
//        if(sDyeingTechnology>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sDyeingTechnology);
//            productinfoBean.setDyeingTechnology(contentBean2);
//        }
//        if(sQualityStandard>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sQualityStandard);
//            productinfoBean.setQualityStandard(contentBean2);
//        }
//        if(sPopularElement>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sPopularElement);
//            productinfoBean.setPopularElement(contentBean2);
//        }
//        if(sApplicableSession>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sApplicableSession);
//            productinfoBean.setApplicableSession(contentBean2);
//        }
//        if(sPrintStyle>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sPrintStyle);
//            productinfoBean.setPrintStyle(contentBean2);
//        }
//        if(sColor>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sColor);
//            productinfoBean.setColor(contentBean2);
//        }
//        if(sMarketExpectation>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sMarketExpectation);
//            productinfoBean.setMarketExpectation(contentBean2);
//        }

//        if(!Func.IsStringEmpty(et_clientgroup.getText().toString())){
//            productinfoBean.setCustomerGroup(et_clientgroup.getText().toString());
//        }
        if(!Func.IsStringEmpty(etMarketReferencePrice.getText().toString())){
            productinfoBean.setMarketReferencePrice(Double.parseDouble(etMarketReferencePrice.getText().toString()));
        }
        ProductinfoBean.MemberBean memberBean=new ProductinfoBean.MemberBean();
        memberBean.setId(Customerid);
        productinfoBean.setMember(memberBean);
        submit();
    }

    public void updateProductInfo(){
        if(updateBean==null){
            updateBean =new ProductinfoBean.ContentBean();
            updateBean.setId(beanid);
        }
        if(!Func.IsStringEmpty(tvFatory.getText().toString())){
            FactoryBean factoryBean=new FactoryBean();
            factoryBean.setId(sFatory);
            updateBean.setFactory(factoryBean);
        }
        updateBean.setName(etProductname.getText().toString().trim());
        if(!Func.IsStringEmpty(tv_ingredient.getText().toString())){
            updateBean.setIngredientValue(tv_ingredient.getText().toString());
        }
        if(!Func.IsStringEmpty(tvWeight.getText().toString())){
            updateBean.setWeightValue(tvWeight.getText().toString());
        }
        if(!Func.IsStringEmpty(tvWidth.getText().toString())){
            updateBean.setFinishedDoor(tvWidth.getText().toString());
        }
        if(!Func.IsStringEmpty(tvDyeingTechnology.getText().toString())){
            updateBean.setDyeingTechnologyValue(tvDyeingTechnology.getText().toString());
        }
        if(!Func.IsStringEmpty(tvQualityStandard.getText().toString())){
            updateBean.setQualityStandardValue(tvQualityStandard.getText().toString());
        }
        if(!Func.IsStringEmpty(tvApplicableSession.getText().toString())){
            updateBean.setApplicableSessionValue(tvApplicableSession.getText().toString());
        }
//        if(sProducttype>0){
//            DictResultBean.ContentBean contentBean=new DictResultBean.ContentBean();
//            contentBean.setId(sProducttype);;
//            updateBean.setCategory(contentBean);
//        }
//        if(singredient>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(singredient);
//            updateBean.setIngredient(contentBean2);
//        }
//        if(sYarn>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sYarn);
//            updateBean.setYarn(contentBean2);
//        }
//        if(sWeight>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sWeight);
//            updateBean.setWeight(contentBean2);
//        }
//        if(sDyeingTechnology>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sDyeingTechnology);
//            updateBean.setDyeingTechnology(contentBean2);
//        }
//        if(sQualityStandard>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sQualityStandard);
//            updateBean.setQualityStandard(contentBean2);
//        }
//        if(sPopularElement>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sPopularElement);
//            updateBean.setPopularElement(contentBean2);
//        }
//
//        if(sApplicableSession>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sApplicableSession);
//            updateBean.setApplicableSession(contentBean2);
//        }
//        if(sPrintStyle>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sPrintStyle);
//            updateBean.setPrintStyle(contentBean2);
//        }
//        if(sColor>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sColor);
//            updateBean.setColor(contentBean2);
//        }
//        if(sMarketExpectation>0){
//            DictResultBean.ContentBean contentBean2=new DictResultBean.ContentBean();
//            contentBean2.setId(sMarketExpectation);
//            updateBean.setMarketExpectation(contentBean2);
//        }
//
//        if(!Func.IsStringEmpty(et_clientgroup.getText().toString())){
//            updateBean.setCustomerGroup(et_clientgroup.getText().toString());
//        }
        if(!Func.IsStringEmpty(etMarketReferencePrice.getText().toString())){
            updateBean.setMarketReferencePrice(Double.parseDouble(etMarketReferencePrice.getText().toString()));
        }
        if(updateBean.getMember()==null){
            ProductinfoBean.MemberBean memberBean=new ProductinfoBean.MemberBean();
            memberBean.setId(Customerid);
            updateBean.setMember(memberBean);
        }
        JiuyiHttp.PUT("product/update")
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
