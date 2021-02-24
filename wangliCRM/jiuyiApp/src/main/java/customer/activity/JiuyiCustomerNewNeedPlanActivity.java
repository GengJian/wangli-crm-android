package customer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDialog;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiDateUtil;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.Utils.FastUtils;
import customer.Utils.ViewOperatorType;
import customer.entity.BatchNumWeight;
import customer.entity.DemandPlanBean;
import customer.entity.MaterialBean;
import customer.entity.MemberBeanID;
import customer.entity.PriceBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewNeedPlanActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 要货计划新增页面
 *****************************************************************
 */

public class JiuyiCustomerNewNeedPlanActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,mtvcancel,mtvdate,tv_clientname;
    private TextView tvBatchNum;
    private TextView tvGrade,tvWeight,tvFactory,tv_spec,tv_prescription;
    private JiuyiEditText etCount;
    private TextView tvPlace;
    private TextView tvPrice;
    private String startDate;
    private long Customerid=-1;
    private String viewOperatortype="",CustomerName="";
    private DemandPlanBean.ContentBean contentBean;
    private DemandPlanBean.ContentBean updateBean;
    private long beanid=-1;
    private String[] batchNumberData;
    private List<MaterialBean.ContentBean> batchNumList=new ArrayList<>();
    private int materialID;
    private LinearLayout ll_content;
    private int year=0,month=0;
    private LoadingDialog dialog1,dialog2;
    private String bill_type="";
    private String batchNum="",productPlace="";
    private List<PriceBean.ContentBean> priceBeanList=new ArrayList<>();
    private String sGrade="",sFactory,sFactoryCode,sLevelName,sLevelCode,sWeight,sSpec;
    private Double dWeight;
    private List<BatchNumWeight.ContentBean> batchWeightList=new ArrayList<>();
    private LinearLayout ll_date;

    @Override
    public void onInit() {
        Calendar m_Curdate = Calendar.getInstance();
        year = m_Curdate.get(Calendar.YEAR);// 获取当前年份
        month= m_Curdate.get(Calendar.MONTH)+2;// 获取当前月份
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        CustomerName=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        viewOperatortype=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        if(viewOperatortype==null){
            viewOperatortype="";
        }
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_NEEDPLANID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewneedplan_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        if(beanid>0 && (viewOperatortype.equals(ViewOperatorType.EDIT)||viewOperatortype.equals(ViewOperatorType.VIEW))){
            getNeedPlanInfo(beanid);
        }
        ll_content= (LinearLayout) mBodyLayout.findViewById(Res.getViewID(null, "ll_content"));
        tv_clientname= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_clientname"));
        if(Customerid<1){
            tv_clientname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FastUtils.isFastClick()) {
                        bill_type="plan";
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"plan");
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                        mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                        changePage(mBundle,Pub.CustomerSelect,true);
                    }
                }
            });

        }
        if(!Func.IsStringEmpty(CustomerName)){
            tv_clientname.setText(CustomerName);
        }

        mtvdate = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_date"));
        ll_date= (LinearLayout) mBodyLayout.findViewById(Res.getViewID(null, "ll_date"));
        ll_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickDialog dialog = new DatePickDialog(JiuyiCustomerNewNeedPlanActivity.this);
                //设置上下年分限制
                dialog.setYearLimt(60);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YM);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        mtvdate.setText(new SimpleDateFormat("yyyy-MM").format(date));
                    }
                });
                dialog.show();
            }
        });
        mtvdate.setText(JiuyiDateUtil.getNextMonth());
        tvBatchNum = (TextView) mBodyLayout.findViewById(R.id.tv_batchNum);
        tvBatchNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Customerid<1){
                    startDialog(DialogID.DialogDoNothing, "", "请先选客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                if (FastUtils.isFastClick()) {
                    bill_type="material";
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "material");
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                    changePage(mBundle, Pub.CustomerSelect, true);
                }
            }
        });
        tvWeight = (TextView) mBodyLayout.findViewById(R.id.tv_weight);
        tvGrade = (TextView) mBodyLayout.findViewById(R.id.tv_grade);
        tv_spec = (TextView) mBodyLayout.findViewById(R.id.tv_spec);
        tv_prescription = (TextView) mBodyLayout.findViewById(R.id.tv_prescription);
        tvFactory = (TextView) mBodyLayout.findViewById(R.id.tv_factory);
        etCount = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_count);
        tvPlace = (TextView) mBodyLayout.findViewById(R.id.tv_place);
        tvPrice = (TextView) mBodyLayout.findViewById(R.id.tv_price);
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            if(viewOperatortype.equals(ViewOperatorType.VIEW)){
                mtvcomplete.setVisibility(View.INVISIBLE);
            }
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (FastUtils.isFastClick()) {
                        if(viewOperatortype.equals(ViewOperatorType.EDIT)){
                            boolean mbcheck=false;
                            mbcheck=check();
                            if(!mbcheck){
                                return;
                            }
                            showDialog();
                            updateInfo();
                        }else {
                            //新增
                            submit();
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
        if(viewOperatortype.equals(ViewOperatorType.VIEW)){
            mtvdate.setEnabled(false);
            mtvdate.setClickable(false);
            etCount.setEnabled(false);
            tv_clientname.setEnabled(false);
            tvBatchNum.setEnabled(false);
        }
        if(viewOperatortype.equals(ViewOperatorType.EDIT)){
            mtvdate.setEnabled(false);
            ll_date.setClickable(false);
            tv_clientname.setEnabled(false);
            tvBatchNum.setEnabled(false);
        }
    }
    public void submit(){
        boolean mbcheck=false;
        mbcheck=check();
        if(!mbcheck){
            return;
        }
        showDialog();
        contentBean=new DemandPlanBean.ContentBean();
        MemberBeanID memberBean=new MemberBeanID();
        memberBean.setId(Customerid);
        contentBean.setMember(memberBean);
        if(!Func.IsStringEmpty(mtvdate.getText().toString())){
            contentBean.setYear(Integer.parseInt(mtvdate.getText().toString().substring(0,4)));
            contentBean.setMonth(Integer.parseInt(mtvdate.getText().toString().substring(5,7)));
        }
        if(!Func.IsStringEmpty(tvBatchNum.getText().toString())){
            contentBean.setBatchNumber(tvBatchNum.getText().toString());
        }
        if(dWeight!=null && dWeight>=0){
            contentBean.setWeight(dWeight);
        }

        if(!Func.IsStringEmpty(etCount.getText().toString().trim())){
            contentBean.setQuantity(Double.parseDouble(etCount.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(sGrade)){
            contentBean.setProductLevel(sGrade);
        }
        if(!Func.IsStringEmpty(tvGrade.getText().toString())){
            contentBean.setProductLevelName(tvGrade.getText().toString());
        }
        if(!Func.IsStringEmpty(tvFactory.getText().toString())){
            contentBean.setFactory(tvFactory.getText().toString());
        }
        if(!Func.IsStringEmpty(sFactoryCode)){
            contentBean.setFactoryNumber(sFactoryCode);
        }
        JiuyiHttp.POST("demand_plan/create")
                .setJson(GsonUtil.gson().toJson(contentBean))
                .addHeader("Authorization", Rc.id_tokenparam)
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
        if(TextUtils.isEmpty(mtvdate.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择计划年份！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tv_clientname.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择客户名称！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvBatchNum.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择产品批号！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }

        if(TextUtils.isEmpty(etCount.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请填写数量！", JiuyiDialogBase.Dialog_Type_Yes, null);
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
    private void getNeedPlanInfo(long id) {
        JiuyiHttp.GET("demand_plan/detail/"+id)
                .tag("request_get_member")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DemandPlanBean.ContentBean>() {
                    @Override
                    public void onSuccess(DemandPlanBean.ContentBean contentBean) {
                        if(contentBean!=null){
                            updateBean=contentBean;
                            if(contentBean.getMember()!=null){
                                tv_clientname.setText(contentBean.getMember().getOrgName());
                            }
                            if(contentBean.getYear()>0 && contentBean.getMonth()>0){
                                int month=contentBean.getMonth();
                                String smonth="";
                                if(month<10){
                                    smonth="0"+month;

                                }else{
                                    smonth=month+"";
                                }
                                mtvdate.setText(contentBean.getYear()+"-"+smonth);
                            }
                            if(contentBean.getBatchNumber()!=null){
                                tvBatchNum.setText(contentBean.getBatchNumber());
                                if(!Func.IsStringEmpty(contentBean.getBatchNumber()) && viewOperatortype.equals(ViewOperatorType.EDIT)){
                                    batchNum=contentBean.getBatchNumber();
                                }
                            }
                            if(contentBean.getWeight()>0){
                                tvWeight.setText(contentBean.getWeight()+"");
                            }
                            if(contentBean.getProductLevelName()!=null){
                                tvGrade.setText(contentBean.getProductLevelName());
                            }
                            if(contentBean.getSpec()!=null){
                                tv_spec.setText(contentBean.getSpec());
                            }
                            if(contentBean.getPrescription()!=null){
                                tv_prescription.setText(contentBean.getPrescription());
                            }
                            if(contentBean.getFactory()!=null){
                                tvFactory.setText(contentBean.getFactory());
                            }
                            if(contentBean.getStatus().equals("APPROVALED")){
                                if(contentBean.getAdjustedQuantity()>=0){
                                    etCount.setText(contentBean.getAdjustedQuantity()+"");
                                }
                            }else {
                                if(contentBean.getQuantity()>0){
                                    etCount.setText(contentBean.getQuantity()+"");
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

    public void setTitle(){
        if(viewOperatortype!=null){
            if(viewOperatortype.equals(ViewOperatorType.VIEW)){
                mTitle = "查看要货计划";
            }else if(viewOperatortype.equals(ViewOperatorType.EDIT)){
                mTitle = "编辑要货计划";
            }else{
                mTitle = "新建要货计划";
            }
        }else{
            mTitle = "新建要货计划";
        }

        setTitle(mTitle);
    }
    public void updateInfo(){
        if(updateBean==null){
            updateBean =new DemandPlanBean.ContentBean();;
            updateBean.setId(beanid);
        }
        if(!Func.IsStringEmpty(etCount.getText().toString())){
            updateBean.setQuantity(Double.parseDouble(etCount.getText().toString().trim()));
        }
        Map<String, String> headMap= new HashMap<String, String>();
        headMap.put("Authorization",Rc.getIns().id_tokenparam);
        JiuyiHttp.PUT("demand_plan/update")
                .addHeaders(headMap)
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent){
        if(intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle bundle = intent.getExtras();
        if(bill_type.equals("material")){
            batchNum= bundle.getString(JiuyiBundleKey.PARAM_BATCHNUM);
            sFactory= bundle.getString(JiuyiBundleKey.PARAM_FACTORYNAME);
            sFactoryCode= bundle.getString(JiuyiBundleKey.PARAM_COMMONCODE);
            sLevelName= bundle.getString(JiuyiBundleKey.PARAM_LEVELNAME);
            sLevelCode=bundle.getString(JiuyiBundleKey.PARAM_LEVELCODE);
            sWeight=bundle.getString(JiuyiBundleKey.PARAM_WEIGHT);
            if(!Func.IsStringEmpty(batchNum)){
                tvBatchNum.setText(batchNum);

            }
            if(!Func.IsStringEmpty(sWeight)){
                dWeight=Double.parseDouble(sWeight);
            }
            tvWeight.setText(sWeight);
            tvGrade.setText(sLevelName);
            sGrade=sLevelCode;
            tvFactory.setText(sFactory);
        }else{
            Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
            CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
            if(Customerid>0 && !Func.IsStringEmpty(CustomerName) ){
                tv_clientname.setText(CustomerName);
            }
        }
    }

}
