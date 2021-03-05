package commonlyused.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiBigTextGroup;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.nanchen.compresshelper.CompressHelper;
import com.tencent.qcloud.sdk.Constant;
import com.wanglicrm.android.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.ChannelDevelopBean;
import commonlyused.bean.JiuyiRetailChannelBean;
import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.entity.AttachmentBean;
import customer.entity.Media;
import customer.entity.MemberBeanID;
import customer.listener.PickerConfig;
import customer.view.JiuyiAttachment;
import customer.view.JiuyiToggleButtonGroup;

import static commonlyused.activity.JiuyiRetailChannelNewActivity.SELECT_PICTURE;
import static commonlyused.activity.JiuyiRetailChannelNewActivity.TAKE_PICTURE;
import static customer.Utils.ViewOperatorType.EDIT;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNewRetailChannelDtailMutiActivity
 * 作       者 : zhengss
 * 创建时间:2018/8/9 14:43
 * 文件描述 : 客户明细
 *****************************************************************
 */
public class JiuyiNewVisitIntentDtailMutiActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String billType="";
    private long Customerid=-1;
    private String bill_type="";
    private JiuyiItemGroup jigClient,jig_managementBrand,jig_annualSalesVolume,jig_cooperationIntention;
    private JiuyiToggleButtonGroup jigIsvisit;

    private JiuyiButton btnSave;
    private String operType="",billOperType="";
    private ChannelDevelopBean.VisitIntentionsBean retailChannelItemsBean;
    private LinearLayout ll_content;




    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newvisitintentmulti_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
         setContentView(mBodyLayout);
        if(mBundle!=null){
            operType= mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
            billOperType= mBundle.getString(JiuyiBundleKey.PARAM_BILLOPERATORTYPE);
        }
        setTitle();
        if(operType==null){
            operType="";
        }
        if(billOperType==null){
            billOperType="";
        }
        if(operType.equals(ViewOperatorType.ADD)){
            retailChannelItemsBean =new ChannelDevelopBean.VisitIntentionsBean();
        }else{
            retailChannelItemsBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        }



        jigClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        jigIsvisit = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_isvisit);

        jig_managementBrand = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_managementBrand);
        jig_annualSalesVolume = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_annualSalesVolume);
        jig_annualSalesVolume.contentEdt.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);;
        jig_cooperationIntention = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_cooperationIntention);

        ll_content= (LinearLayout) mBodyLayout.findViewById(R.id.ll_content);

        btnSave = (JiuyiButton) mBodyLayout.findViewById(R.id.btn_save);
        if(retailChannelItemsBean !=null) {
            if (retailChannelItemsBean.getMember() != null ) {
                jigClient.setText(retailChannelItemsBean.getMember());
            }
            jigIsvisit.tb_type.setChecked(retailChannelItemsBean.isVisit());
            if (retailChannelItemsBean.getManagementBrand() != null ) {
                jig_managementBrand.setText(retailChannelItemsBean.getManagementBrand());
            }
            if(Double.parseDouble(retailChannelItemsBean.getAnnualSalesVolume()+"")>0){
                jig_annualSalesVolume.setText(retailChannelItemsBean.getAnnualSalesVolume()+"");
            }
            if (retailChannelItemsBean.getCooperationIntention() != null ) {
                jig_cooperationIntention.setText(retailChannelItemsBean.getCooperationIntention());
            }
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
                    retailChannelItemsBean.setVisit(jigIsvisit.bdefautl);
                    if(!Func.IsStringEmpty(jigClient.getText())){
                        retailChannelItemsBean.setMember(jigClient.getText());
                    }
                    if(!Func.IsStringEmpty(jig_managementBrand.getText())){
                        retailChannelItemsBean.setManagementBrand(jig_managementBrand.getText());
                    }
                    if(!Func.IsStringEmpty(jig_annualSalesVolume.getText())){
                        retailChannelItemsBean.setAnnualSalesVolume(Double.parseDouble(jig_annualSalesVolume.getText()));
                    }
                    if(!Func.IsStringEmpty(jig_cooperationIntention.getText())){
                        retailChannelItemsBean.setCooperationIntention(jig_cooperationIntention.getText());
                    }
                    setBackActivityBundle();
                    backPage();
                }
            });
        }

        if(operType.equals(ViewOperatorType.VIEW)){
            disableSubControls(ll_content);
        }
        if(billOperType.equals(ViewOperatorType.SPECIAL)){
            jigIsvisit.tb_type.setEnabled(true);
            jig_cooperationIntention.contentEdt.setEnabled(true);
        }else {
            jigIsvisit.tb_type.setEnabled(false);
            jig_cooperationIntention.contentEdt.setEnabled(false);
        }

    }


    public void setTitle(){
        if(operType!=null ){
            if(operType.equals(EDIT)){
                mTitle = "修改拜访意向客户";
            }else if(operType.equals(ViewOperatorType.VIEW)){
                mTitle = "查看拜访意向客户";
            }else if(operType.equals(ViewOperatorType.ADD)){
                mTitle = "添加拜访意向客户";
            }
        }else{
            mTitle = "添加拜访意向客户";
        }

        setTitle(mTitle);
    }

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }

    @Override
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2, retailChannelItemsBean);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiNewVisitIntentDtailMutiActivity.this.setResult(1, intent);
    }

    public boolean check(){
        if(TextUtils.isEmpty(jigClient.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
//        if(jigIscompare.bdefautl && (ahAttach.getMediaArrayList()==null||ahAttach.getMediaArrayList().size()==0)){
//            startDialog(DialogID.DialogDoNothing, "", "请选择附件！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
//        if(!jigIscompare.bdefautl && Func.IsStringEmpty(jigContent.getText())){
//            startDialog(DialogID.DialogDoNothing, "", "请输入内容！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
        return true;

    }

}
