package customer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.control.utils.JiuyiDateUtil;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import customer.Utils.FastUtils;
import customer.Utils.ViewOperatorType;
import customer.entity.CustDeptAccountBean;
import customer.entity.GatheringPlanBean;
import customer.entity.MemberBeanID;
import customer.entity.MemberCenterBean;
import customer.entity.MemberSelectBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewReceiptPlanActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 收款计划新增页面
 *****************************************************************
 */
public class JiuyiCustomerNewReceiptPlanActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,mtvcancel,mtvdate,tv_clientname,tv_Owetotal, tv_plantotal;
    private String startDate;
    private JiuyiEditText et_planreceipt,et_remark;
    private GatheringPlanBean.ContentBean contentBean;
    private GatheringPlanBean.ContentBean updateBean;
    private long Customerid=-1;
    private String viewOperatortype="",CustomerName="";
    private List<MemberSelectBean.ContentBean> memberBeanIDList=new ArrayList<>();
    private long beanid=-1;
    private int year=0,month=0;
    private LoadingDialog dialog1;
    private String msOwetotal,msDuetotal;
    private TextView tv_0day,tv_90day,tv_above90day,tv_receivables;
    private String msdebtsum,mszeroToAccount,msaccountToNinety,msmoreThanNinety,msreceivables,msplantotal;
    private BigDecimal bdzeroToAccount,bdaccountToNinety,bdmoreThanNinety,bdreceivables,bdplantotal,bdplantotalSum;

    @Override
    public void onInit() {
        Calendar m_Curdate = Calendar.getInstance();
        year = m_Curdate.get(Calendar.YEAR);// 获取当前年份
        month= m_Curdate.get(Calendar.MONTH)+2;// 获取当前月份
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        CustomerName=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        msOwetotal=mBundle.getString(JiuyiBundleKey.PARAM_OWETOTAL);
        msDuetotal=mBundle.getString(JiuyiBundleKey.PARAM_DUETOTAL);
        viewOperatortype=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
        if(viewOperatortype==null){
            viewOperatortype="";
        }
        if(beanid>0 && (viewOperatortype.equals(ViewOperatorType.EDIT)||viewOperatortype.equals(ViewOperatorType.VIEW))){
            getPlanInfo(beanid);
        }
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewreceptplan_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        tv_clientname= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_clientname"));
        if(!Func.IsStringEmpty(CustomerName)){
            tv_clientname.setText(CustomerName);
        }
        tv_Owetotal= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_Owetotal"));
        if(!Func.IsStringEmpty(msOwetotal)){
            tv_Owetotal.setText(msOwetotal);
        }
        tv_plantotal = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_plantotal"));
        tv_0day= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_0day"));
        tv_90day= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_90day"));
        tv_above90day= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_above90day"));
        tv_receivables= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_receivables"));
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            if(viewOperatortype.equals(ViewOperatorType.VIEW)){
                mtvcomplete.setVisibility(View.INVISIBLE);
            }else{
                mtvcomplete.setVisibility(View.VISIBLE);
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
        if(Customerid<1){
            tv_clientname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FastUtils.isFastClick()) {
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"plan");
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                        mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                        changePage(mBundle, Pub.CustomerSelect,true);
                    }
                }
            });
        }
        et_planreceipt= (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_planreceipt"));
        et_planreceipt.addTextChangedListener(new TextWatcher() {
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
                    if(!Func.IsStringEmpty(et_planreceipt.getText().toString())){
                        BigDecimal planreceipt=new BigDecimal(et_planreceipt.getText().toString());
                        BigDecimal plantotal=BigDecimal.ZERO;
                        BigDecimal receivables=BigDecimal.ZERO;
                        if(bdreceivables!=null){
                            receivables=bdreceivables;
                        }
                        if(planreceipt!=null && receivables!=null){
                            plantotal=planreceipt.add(receivables);
                        }
                        bdplantotalSum=plantotal;
                        tv_plantotal.setText("￥"+Func.addComma(plantotal+""));
                    }

                }else{
                    bdplantotalSum=bdplantotal;
                    tv_plantotal.setText("￥"+Func.addComma(bdplantotal+""));
                }
            }
        });
        et_remark= (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_remark"));
        mtvdate = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_date"));
        mtvdate.setText(JiuyiDateUtil.getNowMonth());
        if(Customerid>0 && !Func.IsStringEmpty(mtvdate.getText().toString().trim()) &&viewOperatortype.equals(ViewOperatorType.ADD) ){
            getaccountInfo(Customerid,mtvdate.getText().toString().trim());
        }
        if (mtvdate != null) {
            mtvdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerNewReceiptPlanActivity.this);
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
                            mtvdate.setText( new SimpleDateFormat("yyyy-MM").format(date));
                            if(Customerid>0){
                                getaccountInfo(Customerid,mtvdate.getText().toString().trim());
                            }

                        }
                    });
                    dialog.show();
                }
            });
        }

        if(viewOperatortype.equals(ViewOperatorType.VIEW)){
            mtvdate.setEnabled(false);
            mtvdate.setClickable(false);
            tv_clientname.setEnabled(false);
            et_planreceipt.setEnabled(false);
            et_remark.setEnabled(false);
        }
        if(viewOperatortype.equals(ViewOperatorType.EDIT)){
            mtvdate.setEnabled(false);
            mtvdate.setClickable(false);
            tv_clientname.setEnabled(false);
        }

    }

    public void submit(){
        boolean mbcheck=false;
        mbcheck=check();
        if(!mbcheck){
            return;
        }
        showDialog();
        contentBean=new GatheringPlanBean.ContentBean();
        contentBean.setYear(Integer.parseInt(mtvdate.getText().toString().substring(0,4)));
        contentBean.setMonth(Integer.parseInt(mtvdate.getText().toString().substring(5,7)));
        if(Customerid>0){
            MemberBeanID memberBean =new MemberBeanID();
            memberBean.setId(Customerid);
//            contentBean.setMember(memberBean);
        }
        if(!Func.IsStringEmpty(et_planreceipt.getText().toString())){
            contentBean.setQuantity(Double.parseDouble(et_planreceipt.getText().toString()));
        }
        if(!Func.IsStringEmpty(et_remark.getText().toString())){
            contentBean.setRemark(et_remark.getText().toString());
        }
        if(bdzeroToAccount!=null){
            contentBean.setZeroToAccount(bdzeroToAccount);
        }
        if(bdaccountToNinety!=null){
            contentBean.setAccountToNinety(bdaccountToNinety);
        }
        if(bdmoreThanNinety!=null){
            contentBean.setMoreThanNinety(bdmoreThanNinety);
        }
        if(bdreceivables!=null){
            contentBean.setReceivedAmount(bdreceivables);
        }
        if(bdplantotalSum!=null){
            contentBean.setPlanTotalAmount(bdplantotalSum);
        }
        JiuyiHttp.POST("gathering_plan/create")
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
            startDialog(DialogID.DialogDoNothing, "", "请选择客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_planreceipt.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入计划回款金额！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;
    }


    public void setTitle(){
        if(viewOperatortype!=null){
            if(viewOperatortype.equals(ViewOperatorType.ADD)){
                mTitle = "新建收款计划";
            }else if(viewOperatortype.equals(ViewOperatorType.EDIT)){
                mTitle = "修改收款计划信息";
            }else if(viewOperatortype.equals(ViewOperatorType.VIEW)){
                mTitle = "查看收款计划信息";
            }
        }
        setTitle(mTitle);
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            Rc.mBackfresh=true;
            backPage();
        }
    }
    public void updateInfo(){
        if(updateBean==null){
            updateBean =new GatheringPlanBean.ContentBean();;
            updateBean.setId(beanid);
        }
        if(!Func.IsStringEmpty(et_planreceipt.getText().toString().trim())){
            updateBean.setQuantity(Double.parseDouble(et_planreceipt.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(et_remark.getText().toString().trim())){
            updateBean.setRemark(et_remark.getText().toString().trim());
        }
        if(bdreceivables!=null){
            updateBean.setReceivedAmount(bdreceivables);
        }
        if(bdplantotalSum!=null){
            updateBean.setPlanTotalAmount(bdplantotalSum);
        }
        JiuyiHttp.PUT("gathering_plan/update")
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

    private void getPlanInfo(long id) {
        JiuyiHttp.GET("gathering_plan/detail/"+id)
                .tag("request_get_gathering_plan")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<GatheringPlanBean.ContentBean>() {
                    @Override
                    public void onSuccess(GatheringPlanBean.ContentBean contentBean) {
                        if(contentBean!=null){
                            updateBean=contentBean;
//                            if(contentBean.getMember()!=null){
//                                tv_clientname.setText(contentBean.getMember().getOrgName());
//                            }
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
                            if(contentBean.getStatus().equals("APPROVALED")){
                                if(contentBean.getAdjustedQuantity()>=0){
                                    et_planreceipt.setText(Func.formatFloatNumber(contentBean.getAdjustedQuantity()));
                                }
                            }else {
                                if(contentBean.getQuantity()>0){
                                    et_planreceipt.setText(Func.formatFloatNumber(contentBean.getQuantity()));
                                }
                            }
                            if(contentBean.getRemark()!=null){
                                et_remark.setText(contentBean.getRemark());
                            }
                            if(contentBean.getZeroToAccount()!=null){
                                mszeroToAccount=Func.formatFloatNumber(Double.parseDouble(contentBean.getZeroToAccount()+""));
                                bdzeroToAccount=contentBean.getZeroToAccount();

                            }else {
                                mszeroToAccount="0.0";
                            }
                            tv_0day.setText("￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(contentBean.getZeroToAccount()+""),0,2,true));
                            if(contentBean.getAccountToNinety()!=null){
                                msaccountToNinety=Func.formatFloatNumber(Double.parseDouble(contentBean.getAccountToNinety()+""));
                                bdaccountToNinety=contentBean.getAccountToNinety();
                            }else {
                                msaccountToNinety="0.0";
                            }
                            tv_90day.setText("￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(contentBean.getAccountToNinety()+""),0,2,true));
                            if(contentBean.getMoreThanNinety()!=null){
                                bdmoreThanNinety=contentBean.getMoreThanNinety();
                                msmoreThanNinety=Func.formatFloatNumber(Double.parseDouble(contentBean.getMoreThanNinety()+""));

                            }else {
                                msmoreThanNinety="0.0";
                            }
                            tv_above90day.setText("￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(contentBean.getMoreThanNinety()+""),0,2,true));
                            if(contentBean.getReceivedAmount()!=null){
                                msreceivables=Func.formatFloatNumber(Double.parseDouble(contentBean.getReceivedAmount()+""));
                                bdreceivables=contentBean.getReceivedAmount();

                            }else {
                                msreceivables="0.0";
                            }
                            tv_receivables.setText("￥"+Func.addComma(contentBean.getReceivedAmount()+""));
                            if(contentBean.getPlanTotalAmount()!=null){
                                msplantotal=Func.formatFloatNumber(Double.parseDouble(contentBean.getPlanTotalAmount()+""));
                                bdplantotal=contentBean.getPlanTotalAmount();
                                bdplantotalSum=bdplantotal;
                            }else {
                                msplantotal="0.0";
                            }
                            if(!Func.IsStringEmpty(et_planreceipt.getText().toString())){
                                BigDecimal planreceipt=new BigDecimal(et_planreceipt.getText().toString());
                                BigDecimal plantotal=BigDecimal.ZERO;
                                BigDecimal receivables=BigDecimal.ZERO;
                                if(bdreceivables!=null){
                                    receivables=bdreceivables;
                                }
                                if(planreceipt!=null && receivables!=null){
                                    plantotal=planreceipt.add(receivables);
                                    bdplantotalSum=bdplantotal;
                                }
                                tv_plantotal.setText("￥"+Func.addComma(plantotal+""));
                            }else{
                                tv_plantotal.setText("￥"+Func.addComma(contentBean.getPlanTotalAmount()+""));
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }


    private void getaccountInfo(long id,String date) {
        JiuyiHttp.GET("gathering_plan/find_account_received_amount/"+id+"/"+date)
                .tag("request_get_gathering_plan")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<CustDeptAccountBean>() {
                    @Override
                    public void onSuccess(CustDeptAccountBean contentBean) {
                        if(contentBean!=null){
                            if(contentBean.getZeroToAccount()!=null){
                                mszeroToAccount=Func.formatFloatNumber(Double.parseDouble(contentBean.getZeroToAccount()+""));
                                bdzeroToAccount=contentBean.getZeroToAccount();

                            }else {
                                mszeroToAccount="0.0";
                            }
                            tv_0day.setText("￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(contentBean.getZeroToAccount()+""),0,2,true));
                            if(contentBean.getAccountToNinety()!=null){
                                msaccountToNinety=Func.formatFloatNumber(Double.parseDouble(contentBean.getAccountToNinety()+""));
                                bdaccountToNinety=contentBean.getAccountToNinety();
                            }else {
                                msaccountToNinety="0.0";
                            }
                            tv_90day.setText("￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(contentBean.getAccountToNinety()+""),0,2,true));
                            if(contentBean.getMoreThanNinety()!=null){
                                bdmoreThanNinety=contentBean.getMoreThanNinety();
                                msmoreThanNinety=Func.formatFloatNumber(Double.parseDouble(contentBean.getMoreThanNinety()+""));

                            }else {
                                msmoreThanNinety="0.0";
                            }
                            tv_above90day.setText("￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(contentBean.getMoreThanNinety()+""),0,2,true));
                            if(contentBean.getReceivedAmount()!=null){
                                msreceivables=Func.formatFloatNumber(Double.parseDouble(contentBean.getReceivedAmount()+""));
                                bdreceivables=contentBean.getReceivedAmount();

                            }else {
                                msreceivables="0.0";
                            }
                            tv_receivables.setText("￥"+Func.addComma(contentBean.getReceivedAmount()+""));
                            if(contentBean.getPlanTotalAmount()!=null){
                                msplantotal=Func.formatFloatNumber(Double.parseDouble(contentBean.getPlanTotalAmount()+""));
                                bdplantotal=contentBean.getPlanTotalAmount();
                                bdplantotalSum=bdplantotal;
                            }else {
                                msplantotal="0.0";
                            }
                            if(!Func.IsStringEmpty(et_planreceipt.getText().toString())){
                                BigDecimal planreceipt=new BigDecimal(et_planreceipt.getText().toString());
                                BigDecimal plantotal=BigDecimal.ZERO;
                                BigDecimal receivables=BigDecimal.ZERO;
                                if(bdreceivables!=null){
                                    receivables=bdreceivables;
                                }
                                if(planreceipt!=null && receivables!=null){
                                    plantotal=planreceipt.add(receivables);
                                    bdplantotalSum=bdplantotal;
                                }
                                tv_plantotal.setText("￥"+Func.addComma(plantotal+""));
                            }else{
                                tv_plantotal.setText("￥"+Func.addComma(contentBean.getPlanTotalAmount()+""));
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        tv_0day.setText("￥0.0");
                        tv_90day.setText("￥0.0");
                        tv_above90day.setText("￥0.0");
                        tv_receivables.setText("￥0.0");
                        tv_plantotal.setText("￥0.0");
                        tv_Owetotal.setText("￥0.0");
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
        Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        msOwetotal= bundle.getString(JiuyiBundleKey.PARAM_OWETOTAL);
        msDuetotal= bundle.getString(JiuyiBundleKey.PARAM_DUETOTAL);
        if(Customerid>0 && !Func.IsStringEmpty(CustomerName) ){
            getaccountInfo(Customerid,mtvdate.getText().toString().trim());
            tv_clientname.setText(CustomerName);
        }
        if(!Func.IsStringEmpty(msOwetotal)){
            tv_Owetotal.setText(msOwetotal);
        }
    }
}
