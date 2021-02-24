package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.model.DictBean;
import com.jiuyi.model.DictResultBean;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import commonlyused.db.DbHelper;
import commonlyused.db.DictBeanDao;
import customer.Utils.FastUtils;
import customer.entity.ImageBean;
import customer.entity.MemberBeanID;
import customer.entity.MemberSelectBean;
import customer.entity.RiskWarnInfoBean;
import customer.entity.UploadTokenBean;
import customer.view.Bimp;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewRiskActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 风险预警新增页面
 *****************************************************************
 */

public class JiuyiCustomerNewRiskActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel,mtvdate,tv_risktype,tv_clientname;
    private JiuyiEditText et_title,et_content;
    private RiskWarnInfoBean.ContentBean riskWarnBean;
    private List<RiskWarnInfoBean.ContentBean.AttachmentListBean> attachmentsBeanslist;
    String risktypekey="";
    long riskDictorigid =-1;
    private long Customerid=-1;
    private String viewOperatortype="",CustomerName="";
    private  long riskid=-1;
    private List<MemberSelectBean.ContentBean> memberBeanIDList=new ArrayList<>();

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        CustomerName=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        riskid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERRISKID);
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewrisk_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        tv_clientname= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_clientname"));
        if(!Func.IsStringEmpty(CustomerName)){
            tv_clientname.setText(CustomerName);
        }
        tv_clientname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FastUtils.isFastClick()) {
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                    changePage(mBundle, Pub.CustomerSelect,true);
                }
            }
        });


        et_title = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_title"));
        et_content = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_content"));

        if(riskid>0){
            getRiskDetailinfo(riskid);
        }

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    riskWarnBean=new RiskWarnInfoBean.ContentBean();
                    MemberBeanID memberBean=new MemberBeanID();
                    memberBean.setId(Customerid);
                    riskWarnBean.setMember(memberBean);
                    riskWarnBean.setTitle(et_title.getText().toString().trim());
                    DictResultBean.ContentBean dictBean=new DictResultBean.ContentBean();
                    dictBean.setId(riskDictorigid);
                    riskWarnBean.setType(dictBean);
                    riskWarnBean.setTypeValue(tv_risktype.getText().toString().trim());
                    riskWarnBean.setFromClientType("android");
                    riskWarnBean.setCreatedDate(mtvdate.getText().toString());
                    riskWarnBean.setContent(et_content.getText().toString().trim());
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
                    showDialog();
                    if (Bimp.tempSelectBitmap.size()> 0) {
                        upload();
                    }else{
                        submit();
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
        tv_risktype= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_risktype"));
        tv_risktype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewRiskActivity.this);
                buidler.setTitle("预警类型");
                final String[] data;
                final List<DictBean> dictBeansList = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                        .where(DictBeanDao.Properties.Name.eq("risk_type")).where(DictBeanDao.Properties.Key.notEq("all"))
                        .where(DictBeanDao.Properties.Key.notEq("All"))
                        .where(DictBeanDao.Properties.Key.notEq("ALL")).build().list();
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tv_risktype.setText(data[which].trim());
                            riskDictorigid =dictBeansList.get(which).getOriginalid();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }

            }
        });
        mtvdate = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_date"));
        if (mtvdate != null) {
            mtvdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerNewRiskActivity.this);
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
                            mtvdate.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        }
                    });
                    dialog.show();
                }
            });

        }
        Calendar calendar=Calendar.getInstance();
        int year = Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        int month = Integer.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
        int day = Integer.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        if(year>0 && month>0 && day>0){
            String date="",smonth="";
            if(month<10){
                smonth="0"+month;
            }else{
                smonth=month+"";
            }
            date=String.valueOf(year)+"-"+smonth+"-"+String.valueOf(day);
            mtvdate.setText(date);
        }
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
                                attachmentsBeanslist=new ArrayList<RiskWarnInfoBean.ContentBean.AttachmentListBean>();
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
                                                        RiskWarnInfoBean.ContentBean.AttachmentListBean attachmentsBean=new RiskWarnInfoBean.ContentBean.AttachmentListBean();
                                                        String qiniukey="";
                                                        if(res!=null){
                                                            qiniukey=((JSONObject) res).opt("key").toString();
                                                            attachmentsBean.setQiniuKey(qiniukey);
                                                            attachmentsBeanslist.add(attachmentsBean);
                                                        }
                                                        //附件上传成功
                                                        if(attachmentsBeanslist!=null && attachmentsBeanslist.size()>0){
                                                            if(attachmentsBeanslist.size()==Bimp.tempSelectBitmap.size()){
                                                                riskWarnBean.setAttachmentList(attachmentsBeanslist);
                                                                submit();                                                            }
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
    public void submit(){
        JiuyiHttp.POST("risk-warn/create")
                .setJson(GsonUtil.gson().toJson(riskWarnBean))
                .addHeader("Authorization",Rc.id_tokenparam)

                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        JiuyiLog.e("httplogin","request onSuccess!" + result.toString());
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
        if(TextUtils.isEmpty(tv_risktype.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择预警类型！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_title.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入标题！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_content.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入内容！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;
    }

    public void setTitle(){
        mTitle = "新建风险预警";
        setTitle(mTitle);
    }

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }

    private void getRiskDetailinfo(long id) {
        JiuyiHttp.GET("risk-warn/detail/"+id)
                .tag("request_risk-warn")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<RiskWarnInfoBean.ContentBean>() {
                    @Override
                    public void onSuccess(RiskWarnInfoBean.ContentBean contentBean) {
                        if(contentBean!=null){

                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if(intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle bundle = intent.getExtras();
        Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        if(Customerid>0 && !Func.IsStringEmpty(CustomerName) ){
            tv_clientname.setText(CustomerName);
        }
    }

}
