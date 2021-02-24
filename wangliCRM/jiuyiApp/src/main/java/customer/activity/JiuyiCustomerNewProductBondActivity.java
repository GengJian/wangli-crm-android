package customer.activity;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;

import java.util.List;

import customer.Utils.ViewOperatorType;
import customer.entity.AttachmentBean;
import customer.entity.ProductbondBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewProductCheckActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 抽查检查新增页面
 *****************************************************************
 */

public class JiuyiCustomerNewProductBondActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel;
    private TextView tvType;
    private JiuyiEditText etTitle;
    private TextView tvDate;
    private TextView tvBondNum;
    private TextView tvBondName;
    private TextView tvBondTradeTimeStr;
    private TextView tvPublishTimeStr;
    private TextView tvPublishExpireTimeStr;
    private TextView tvBondTimeLimit;
    private TextView tvBondStopTimeStr;
    private TextView tvPlanIssuedQuantity;
    private TextView tvRealIssuedQuantity;
    private TextView tvBondType;
    private TextView tvCalInterestType;
    private TextView tvCreditRatingGov;
    private TextView tvDebtRating;
    private TextView tvEscrowAgent;
    private TextView tvExeRightTimeStr;
    private TextView tvExeRightType;
    private TextView tvFaceInterestRate;
    private TextView tvFaceValue;
    private TextView tvFlowRange;
    private TextView tvInterestDiff;
    private TextView tvIssuedPrice;
    private TextView tvPayInterestHZ;
    private TextView tvRefInterestRate;
    private TextView tvDeleted;
    private TextView tvRemark;
    private TextView tvCount;
    private ToggleButton tb;
    private JiuyiEditText etContent;

    private long sType =-1;
    private long Customerid=-1;
    private String viewOperatorType;
    private long beanid=-1;
    private ProductbondBean.ContentBean updateBean;
    private ProductbondBean.ContentBean productinfoBean;
    private List<AttachmentBean> attachmentsBeanslist;

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewproductbond_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        if(beanid>0 && !viewOperatorType.equals(ViewOperatorType.ADD)){
            getProductinfoList(beanid);
        }
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
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
    @Override
    public void initViews(){
        super.initViews();
        tvType = (TextView) mBodyLayout.findViewById(R.id.tv_type);
        etTitle = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_title);
        tvDate = (TextView) mBodyLayout.findViewById(R.id.tv_date);
        etContent = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_content);
        tvBondNum = (TextView) mBodyLayout.findViewById(R.id.tv_bondNum);
        tvBondName = (TextView) mBodyLayout.findViewById(R.id.tv_bondName);
        tvBondTradeTimeStr = (TextView) mBodyLayout.findViewById(R.id.tv_bondTradeTimeStr);
        tvPublishTimeStr = (TextView) mBodyLayout.findViewById(R.id.tv_publishTimeStr);
        tvPublishExpireTimeStr = (TextView) mBodyLayout.findViewById(R.id.tv_publishExpireTimeStr);
        tvBondTimeLimit = (TextView) mBodyLayout.findViewById(R.id.tv_bondTimeLimit);
        tvBondStopTimeStr = (TextView) mBodyLayout.findViewById(R.id.tv_bondStopTimeStr);
        tvPlanIssuedQuantity = (TextView) mBodyLayout.findViewById(R.id.tv_planIssuedQuantity);
        tvRealIssuedQuantity = (TextView) mBodyLayout.findViewById(R.id.tv_realIssuedQuantity);
        tvBondType = (TextView) mBodyLayout.findViewById(R.id.tv_bondType);
        tvCalInterestType = (TextView) mBodyLayout.findViewById(R.id.tv_calInterestType);
        tvCreditRatingGov = (TextView) mBodyLayout.findViewById(R.id.tv_creditRatingGov);
        tvDebtRating = (TextView) mBodyLayout.findViewById(R.id.tv_debtRating);
        tvEscrowAgent = (TextView) mBodyLayout.findViewById(R.id.tv_escrowAgent);
        tvExeRightTimeStr = (TextView) mBodyLayout.findViewById(R.id.tv_exeRightTimeStr);
        tvExeRightType = (TextView) mBodyLayout.findViewById(R.id.tv_exeRightType);
        tvFaceInterestRate = (TextView) mBodyLayout.findViewById(R.id.tv_faceInterestRate);
        tvFaceValue = (TextView) mBodyLayout.findViewById(R.id.tv_faceValue);
        tvFlowRange = (TextView) mBodyLayout.findViewById(R.id.tv_flowRange);
        tvInterestDiff = (TextView) mBodyLayout.findViewById(R.id.tv_interestDiff);
        tvIssuedPrice = (TextView) mBodyLayout.findViewById(R.id.tv_issuedPrice);
        tvPayInterestHZ = (TextView) mBodyLayout.findViewById(R.id.tv_payInterestHZ);
        tvRefInterestRate = (TextView) mBodyLayout.findViewById(R.id.tv_refInterestRate);
        tb = (ToggleButton) mBodyLayout.findViewById(Res.getViewID(null, "toggleButton"));
        tvRemark = (TextView) mBodyLayout.findViewById(R.id.tv_remark);
        tvCount = (TextView) mBodyLayout.findViewById(R.id.tv_count);


    }
    @Override
    public void setTitle(){
        mTitle = "债券信息";
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

   private void getProductinfoList(long id) {
    JiuyiHttp.GET("bond_info/detail/"+id)
            .tag("request_get_spot_check")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<ProductbondBean.ContentBean>() {
                @Override
                public void onSuccess(ProductbondBean.ContentBean contentBean) {
                    if(contentBean!=null){
                        if(contentBean.getBondNum()!=null){
                            tvBondNum.setText(contentBean.getBondNum());
                        }
                        if(contentBean.getBondName()!=null){
                            tvBondName.setText(contentBean.getBondName());
                        }
                        if(contentBean.getBondTradeTimeStr()!=null){
                            tvBondTradeTimeStr.setText(contentBean.getBondTradeTimeStr());
                        }
                        if(contentBean.getPublishTimeStr()!=null){
                            tvPublishTimeStr.setText(contentBean.getPublishTimeStr());
                        }
                        if(contentBean.getPublishExpireTimeStr()!=null){
                            tvPublishExpireTimeStr.setText(contentBean.getPublishExpireTimeStr());
                        }
                        if(contentBean.getBondTimeLimit()!=null){
                            tvBondTimeLimit.setText(contentBean.getBondTimeLimit());
                        }
                        if(contentBean.getBondStopTimeStr()!=null){
                            tvBondStopTimeStr.setText(contentBean.getBondStopTimeStr());
                        }
                        tvPlanIssuedQuantity.setText(Func.addComma(contentBean.getPlanIssuedQuantity()+""));
                        tvRealIssuedQuantity.setText(Func.addComma(contentBean.getRealIssuedQuantity()+""));
                        if(contentBean.getBondType()!=null){
                            tvBondType.setText(contentBean.getBondType());
                        }
                        if(contentBean.getCalInterestType()!=null){
                            tvCalInterestType.setText(contentBean.getCalInterestType());
                        }
                        if(contentBean.getCreditRatingGov()!=null){
                            tvCreditRatingGov.setText(contentBean.getCreditRatingGov());
                        }
                        if(contentBean.getDebtRating()!=null){
                            tvDebtRating.setText(contentBean.getDebtRating());
                        }
                        if(contentBean.getEscrowAgent()!=null){
                            tvEscrowAgent.setText(contentBean.getEscrowAgent());
                        }
                        if(contentBean.getExeRightTimeStr()!=null){
                            tvExeRightTimeStr.setText(contentBean.getExeRightTimeStr());
                        }
                        if(contentBean.getExeRightType()!=null){
                            tvExeRightType.setText(contentBean.getExeRightType());
                        }
                        tvFaceInterestRate.setText(contentBean.getFaceInterestRate()+"");
                        tvFaceValue.setText(Func.addComma(contentBean.getFaceValue()+""));
                        if(contentBean.getFlowRange()!=null){
                            tvFlowRange.setText(contentBean.getFlowRange());
                        }
                        tvInterestDiff.setText(contentBean.getInterestDiff()+"");
                        tvIssuedPrice.setText(contentBean.getIssuedPrice()+"");
                        if(contentBean.getPayInterestHZ()!=null){
                            tvPayInterestHZ.setText(contentBean.getPayInterestHZ());
                        }
                        tvRefInterestRate.setText(contentBean.getRefInterestRate()+"");
                        if(contentBean.getBondNum()!=null){
                            tvBondNum.setText(contentBean.getBondNum());
                        }
                        if(contentBean.isDeleted()){
                            tb.setChecked(true);
                        }else{
                            tb.setChecked(false);
                        }
                        if(contentBean.getRemark()!=null){
                            tvRemark.setText(contentBean.getRemark());
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


}
