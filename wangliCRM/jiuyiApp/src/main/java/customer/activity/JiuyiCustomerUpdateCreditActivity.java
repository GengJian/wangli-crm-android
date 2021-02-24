package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;

import java.util.List;

import customer.entity.MemberBeanID;
import customer.entity.UpdateCreditBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerTransferActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 转移客户
 *****************************************************************
 */
public class JiuyiCustomerUpdateCreditActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String customername;
    private long customerid=-1;
    private TextView tvClient;
    private TextView tvLimit;
    private TextView tvPaymentdays,tvNewpaymentdays;
    private JiuyiEditText etNewlimit;
//    private jiuyiEditText etNewpaymentdays;
    private JiuyiEditText etRemark;
    private UpdateCreditBean updateCreditBean;
    private int creditModifyDate;// 账期天数
    private double credit;// 信贷限额（信贷总额）


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customerupdatecredit_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        updateCreditBean=new UpdateCreditBean();
        customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        creditModifyDate=mBundle.getInt(JiuyiBundleKey.PARAM_CUSTOMERPAYMENTDAYS);
        credit=mBundle.getDouble(JiuyiBundleKey.PARAM_CUSTOMERLIMIT);

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        tvClient = (TextView) mBodyLayout.findViewById(R.id.tv_client);

        tvLimit = (TextView) mBodyLayout.findViewById(R.id.tv_limit);
        tvPaymentdays = (TextView) mBodyLayout.findViewById(R.id.tv_paymentdays);
        etNewlimit = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_newlimit);
        tvNewpaymentdays = (TextView) mBodyLayout.findViewById(R.id.tv_newpaymentdays);
        tvNewpaymentdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerUpdateCreditActivity.this);
                buidler.setTitle("新账期");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("new_account");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvNewpaymentdays.setText(data[which].trim());
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        etRemark = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_remark);
        if(!Func.IsStringEmpty(customername)){
            tvClient.setText(customername);
        }
        if(credit>=0){
            tvLimit.setText(Func.formatFloatNumber(credit));
            etNewlimit.setText(Func.formatFloatNumber(credit));
            updateCreditBean.setOldAmount(credit);
        }
        if(creditModifyDate>=0){
            tvPaymentdays.setText(creditModifyDate+"");
            tvNewpaymentdays.setText(creditModifyDate+"");
            updateCreditBean.setOldAccount(creditModifyDate);
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
                    if(customerid>0){
                        MemberBeanID memberbean=new MemberBeanID();
                        memberbean.setId(customerid);
                        updateCreditBean.setMember(memberbean);
                    }
                    updateCreditBean.setNewAccount(Integer.parseInt(tvNewpaymentdays.getText().toString()));
                    updateCreditBean.setNewAmount(Double.parseDouble(etNewlimit.getText().toString()));
                    if(!Func.IsStringEmpty(etRemark.getText().toString())){
                        updateCreditBean.setRemark(etRemark.getText().toString());
                    }
                    submit();
                }
            });
        }

    }


    public void setTitle(){
        mTitle = "修改信用额度";
        setTitle(mTitle);
    }

    public boolean check(){
        if(TextUtils.isEmpty(etNewlimit.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入信用额度！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvNewpaymentdays.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择信用账期！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }


        return true;
    }
    public void submit(){
        JiuyiHttp.POST("account-amount-flow/apply")
                .setJson(GsonUtil.gson().toJson(updateCreditBean))
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


}
