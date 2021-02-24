package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
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
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.QueryConditionBean;
import customer.entity.MemberBeanID;
import customer.entity.MemberClaimBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerTransferActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 转移客户
 *****************************************************************
 */
public class JiuyiCustomerTransferActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,tv_day,tv_newincharge,tv_name,tv_preincharge;
    private JiuyiButton mbtnsave;
    private String customername,inChargeName;
    private long customerid=-1;
    private String[] salemanData;
    private MemberClaimBean memberClaimBean;
    private JiuyiEditText et_remark;
    private List<NormalOperatorBean.ContentBean> salemanList=new ArrayList<>();
    private long salesmanID;
    private long orialSalesmanID;
    private LoadingDialog dialog1,dialog2;

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customertransfer_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        inChargeName=mBundle.getString(JiuyiBundleKey.PARAM_INCHARGENAME);
        orialSalesmanID=mBundle.getLong(JiuyiBundleKey.PARAM_INCHARGEID);
        tv_name= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_name"));
        if(!Func.IsStringEmpty(customername)){
            tv_name.setText(customername);
        }
        tv_preincharge= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_preincharge"));
        if(!Func.IsStringEmpty(inChargeName)){
            tv_preincharge.setText(inChargeName);
        }
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        mbtnsave= (JiuyiButton) mBodyLayout.findViewById(Res.getViewID(null, "btn_save"));
        if(mbtnsave!=null){
            mbtnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
                    memberClaimBean=new MemberClaimBean();
                    if(customerid>0){
                        MemberBeanID memberbean=new MemberBeanID();
                        memberbean.setId(customerid);
                        memberClaimBean.setMember(memberbean);
                    }
                    if(!Func.IsStringEmpty(tv_day.getText().toString())){
                        memberClaimBean.setInfoDate(tv_day.getText().toString());
                    }
                    if(!Func.IsStringEmpty(et_remark.getText().toString())){
                        memberClaimBean.setRemark(et_remark.getText().toString());
                    }
                    if(orialSalesmanID>0){
                        MemberClaimBean.SalemanBean salemanBean=new MemberClaimBean.SalemanBean();
                        salemanBean.setId(orialSalesmanID);
                        memberClaimBean.setOriginalSaleman(salemanBean);
                    }
                    if(salesmanID>0){
                        MemberClaimBean.SalemanBean salemanBean=new MemberClaimBean.SalemanBean();
                        salemanBean.setId(salesmanID);
                        memberClaimBean.setTargetSaleman(salemanBean);
                    }
                    LoadingDialog.Builder builder1=new LoadingDialog.Builder(JiuyiCustomerTransferActivity.this)
                            .setMessage("提交中...")
                            .setCancelable(false);
                    dialog2=builder1.create();
                    dialog2.show();
                    submit();

                }
            });
        }
        et_remark = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_remark"));
        tv_day = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_day"));
        if (tv_day != null) {
            tv_day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerTransferActivity.this);
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
                            tv_day.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        }
                    });
                    dialog.show();
                }
            });
            tv_day.setText(JiuyiDateUtil.getNowTime3());

        }
        tv_newincharge = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_newincharge"));
        tv_newincharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             LoadingDialog.Builder builder1=new LoadingDialog.Builder(JiuyiCustomerTransferActivity.this)
                        .setMessage("加载中...")
                        .setCancelable(false);
                dialog1=builder1.create();
                dialog1.show();
                getInchargeList();

            }
        });

    }


    public void setTitle(){
        mTitle = "转移客户";
        setTitle(mTitle);
    }
    public void submit(){
        JiuyiHttp.POST("member_process/transfer")
                .setJson(GsonUtil.gson().toJson(memberClaimBean))
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            Rc.mBackfresh=true;
            backPage();
        }
    }


    public  void  getInchargeList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                QueryConditionBean queryConditionBean = new QueryConditionBean();
                queryConditionBean.setNumber(0);
                queryConditionBean.setSize(10000);
                queryConditionBean.setDirection("DESC");
                queryConditionBean.setProperty("createdDate");
                JiuyiHttp.POST("operator/assist_list")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<NormalOperatorBean>() {
                            @Override
                            public void onSuccess(NormalOperatorBean data) {
                                if(data!=null){
                                    dialog1.dismiss();
                                    if(data.getContent()!=null && data.getContent().size()>0){
                                        salemanList=data.getContent();
                                        salemanData=new String[data.getContent().size()];
                                        for(int i=0;i<data.getContent().size();i++){
                                            salemanData[i]=data.getContent().get(i).getName();
                                        }
                                        AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerTransferActivity.this);
                                        buidler.setTitle("人员选择");
                                        final String[] data2 = salemanData;
                                        buidler.setSingleChoiceItems(data2, -1, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if(salemanList!=null && salemanList.size()>0){
                                                    salesmanID=salemanList.get(which).getId();
                                                }
                                                tv_newincharge.setText(data2[which].trim());
                                                dialog.dismiss();
                                            }
                                        });
                                        buidler.show();

                                    }

                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                dialog1.dismiss();
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });


            }
        }).start();
    }
    public boolean check(){
        if(TextUtils.isEmpty(tv_day.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tv_newincharge.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择现负责人！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;
    }

}
