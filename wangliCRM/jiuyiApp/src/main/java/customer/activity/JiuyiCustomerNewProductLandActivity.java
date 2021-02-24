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
import customer.entity.ProductlandBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewProductCheckActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 抽查检查新增页面
 *****************************************************************
 */

public class JiuyiCustomerNewProductLandActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel;
    private TextView tvType;
    private JiuyiEditText etTitle;
    private TextView tvDate;
    private TextView tvPurchaseLandNumber;
    private TextView tvAdminRegion;
    private TextView tvAssignee;
    private TextView tvSignedDateStr;
    private TextView tvDealPrice;
    private TextView tvElecSupervisorNo;
    private TextView tvEndTimeStr;
    private TextView tvLocation;
    private TextView tvMaxVolume;
    private TextView tvMinVolume;
    private TextView tvParentCompany;
    private TextView tvPurpose;
    private TextView tvStartTimeStr;
    private TextView tvSupplyWay;
    private TextView tvTotalArea;
    private TextView tvUpdateTimeStr;
    private TextView tvLinkUrl;

    private TextView tvCount;
    private ToggleButton tb;
    private JiuyiEditText etContent;

    private long sType =-1;
    private long Customerid=-1;
    private String viewOperatorType;
    private long beanid=-1;
    private ProductlandBean.ContentBean updateBean;
    private ProductlandBean.ContentBean productinfoBean;
    private List<AttachmentBean> attachmentsBeanslist;
    private String linkUrl="";

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewproductland_layout"), null);
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
        tvPurchaseLandNumber = (TextView) mBodyLayout.findViewById(R.id.tv_purchaseLandNumber);
        tvAdminRegion = (TextView) mBodyLayout.findViewById(R.id.tv_adminRegion);
        tvAssignee = (TextView) mBodyLayout.findViewById(R.id.tv_assignee);
        tvSignedDateStr = (TextView) mBodyLayout.findViewById(R.id.tv_signedDateStr);
        tvDealPrice = (TextView) mBodyLayout.findViewById(R.id.tv_dealPrice);
        tvElecSupervisorNo = (TextView) mBodyLayout.findViewById(R.id.tv_elecSupervisorNo);
        tvEndTimeStr = (TextView) mBodyLayout.findViewById(R.id.tv_endTimeStr);
        tvLocation = (TextView) mBodyLayout.findViewById(R.id.tv_location);
        tvMaxVolume = (TextView) mBodyLayout.findViewById(R.id.tv_maxVolume);
        tvMinVolume = (TextView) mBodyLayout.findViewById(R.id.tv_minVolume);
        tvParentCompany = (TextView) mBodyLayout.findViewById(R.id.tv_parentCompany);
        tvPurpose = (TextView) mBodyLayout.findViewById(R.id.tv_purpose);
        tvStartTimeStr = (TextView) mBodyLayout.findViewById(R.id.tv_startTimeStr);
        tvSupplyWay = (TextView) mBodyLayout.findViewById(R.id.tv_supplyWay);
        tvTotalArea = (TextView) mBodyLayout.findViewById(R.id.tv_totalArea);
        tvUpdateTimeStr = (TextView) mBodyLayout.findViewById(R.id.tv_updateTimeStr);
        tvLinkUrl = (TextView) mBodyLayout.findViewById(R.id.tv_linkUrl);
        tvLinkUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Func.IsStringEmpty(linkUrl)){
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, linkUrl);
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"中国土地市场网");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                    changePage(mBundle,10061,true);
                }

            }
        });
        tvCount = (TextView) mBodyLayout.findViewById(R.id.tv_count);


    }
    @Override
    public void setTitle(){
        mTitle = "购地信息";
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
    JiuyiHttp.GET("purchase_land/detail/"+id)
            .tag("request_get_spot_check")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<ProductlandBean.ContentBean>() {
                @Override
                public void onSuccess(ProductlandBean.ContentBean contentBean) {
                    if(contentBean!=null){
                        if(contentBean.getPurchaseLandNumber()!=null){
                            tvPurchaseLandNumber.setText(contentBean.getPurchaseLandNumber());
                        }
                        if(contentBean.getAdminRegion()!=null){
                            tvAdminRegion.setText(contentBean.getAdminRegion());
                        }
                        if(contentBean.getAssignee()!=null){
                            tvAssignee.setText(contentBean.getAssignee());
                        }
                        if(contentBean.getSignedDateStr()!=null){
                            tvSignedDateStr.setText(contentBean.getSignedDateStr());
                        }
                        if(contentBean.getDealPrice()!=null){
                            tvDealPrice.setText(contentBean.getDealPrice());
                        }
                        if(contentBean.getElecSupervisorNo()!=null){
                            tvElecSupervisorNo.setText(contentBean.getElecSupervisorNo());
                        }
                        if(contentBean.getEndTimeStr()!=null){
                            tvEndTimeStr.setText(contentBean.getEndTimeStr());
                        }

                        if(contentBean.getLocation()!=null){
                            tvLocation.setText(contentBean.getLocation());
                        }
                        if(contentBean.getMaxVolume()!=null){
                            tvMaxVolume.setText(contentBean.getMaxVolume());
                        }
                        if(contentBean.getMinVolume()!=null){
                            tvMinVolume.setText(contentBean.getMinVolume());
                        }
                        if(contentBean.getParentCompany()!=null){
                            tvParentCompany.setText(contentBean.getParentCompany());
                        }
                        if(contentBean.getPurpose()!=null){
                            tvPurpose.setText(contentBean.getPurpose());
                        }
                        if(contentBean.getSignedDateStr()!=null){
                            tvSignedDateStr.setText(contentBean.getSignedDateStr());
                        }
                        if(contentBean.getStartTimeStr()!=null){
                            tvStartTimeStr.setText(contentBean.getStartTimeStr());
                        }
                        if(contentBean.getSupplyWay()!=null){
                            tvSupplyWay.setText(contentBean.getSupplyWay());
                        }
                        if(contentBean.getTotalArea()!=null){
                            tvTotalArea.setText(contentBean.getTotalArea());
                        }
                        if(contentBean.getUpdateTimeStr()!=null){
                            tvUpdateTimeStr.setText(contentBean.getUpdateTimeStr());
                        }

                        if(contentBean.getLinkUrl()!=null){
                            linkUrl=contentBean.getLinkUrl();
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
