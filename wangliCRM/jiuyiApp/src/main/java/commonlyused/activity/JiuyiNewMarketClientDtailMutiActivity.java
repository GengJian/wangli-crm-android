package commonlyused.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiDateUtil;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;
import com.wanglicrm.android.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import commonlyused.bean.JiuyiRetailChannelBean;
import commonlyused.bean.MarketEngineeringBean;
import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.entity.MemberBean;
import customer.entity.MemberBeanID;
import customer.view.JiuyiToggleButtonGroup;

import static customer.Utils.ViewOperatorType.EDIT;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNewRetailChannelDtailMutiActivity
 * 作       者 : zhengss
 * 创建时间:2018/8/9 14:43
 * 文件描述 : 客户明细
 *****************************************************************
 */
public class JiuyiNewMarketClientDtailMutiActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String billType="";
    private long Customerid=-1;
    private String bill_type="";
    private JiuyiItemGroup jig_client,jig_handlingMatters;
    private JiuyiToggleButtonGroup jig_isvisit;

    private JiuyiButton btnSave;
    private String operType="",billOperType="";
    private MarketEngineeringBean.MarketEngineeringItemsBean retailChannelItemsBean;
    private LinearLayout ll_content;
    private String msProvince="";




    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newmarketclientmulti_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
         setContentView(mBodyLayout);
        if(mBundle!=null){
            operType= mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
            billOperType= mBundle.getString(JiuyiBundleKey.PARAM_BILLOPERATORTYPE);
            msProvince= mBundle.getString(JiuyiBundleKey.PARAM_PROVINCE);
        }
        setTitle();
        if(operType==null){
            operType="";
        }
        if(billOperType==null){
            billOperType="";
        }
        if(msProvince==null){
            msProvince="";
        }
        if(operType.equals(ViewOperatorType.ADD)){
            retailChannelItemsBean =new MarketEngineeringBean.MarketEngineeringItemsBean();
        }else{
            retailChannelItemsBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        }

        jig_client = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        jig_client.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_SPECIALCONDITION,"Y");
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CUSTOMER");
                mBundle.putString(JiuyiBundleKey.PARAM_PROVINCE,msProvince);
                intent.putExtras(mBundle);
                startActivityForResult(intent, 201);

            }
        });
        jig_isvisit = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_isvisit);

        jig_handlingMatters = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_handlingMatters);


        ll_content= (LinearLayout) mBodyLayout.findViewById(R.id.ll_content);

        btnSave = (JiuyiButton) mBodyLayout.findViewById(R.id.btn_save);
        if(retailChannelItemsBean !=null) {
            if (retailChannelItemsBean.getMember() != null && retailChannelItemsBean.getMember().getOrgName()!=null ) {
                jig_client.setText(retailChannelItemsBean.getMember().getOrgName());
            }
            jig_isvisit.tb_type.setChecked(retailChannelItemsBean.isVisit());
            if (retailChannelItemsBean.getHandlingMatters() != null ) {
                jig_handlingMatters.setText(retailChannelItemsBean.getHandlingMatters());
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
                    retailChannelItemsBean.setVisit(jig_isvisit.bdefautl);
//                    if(!Func.IsStringEmpty(jig_client.getText())){
//                        retailChannelItemsBean.setMember(jig_project.getText());
//                    }
                    if(!Func.IsStringEmpty(jig_handlingMatters.getText())){
                        retailChannelItemsBean.setHandlingMatters(jig_handlingMatters.getText());
                    }
                    setBackActivityBundle();
                    backPage();
                }
            });
        }

        if(operType.equals(ViewOperatorType.VIEW)){
            disableSubControls(ll_content);
        }
//        if(operType.equals(ViewOperatorType.ADD)){
//            jig_isvisit.tb_type.setEnabled(false);
//        }
        if(billOperType.equals(ViewOperatorType.SPECIAL)){
            jig_isvisit.tb_type.setEnabled(true);
        }else {
            jig_isvisit.tb_type.setEnabled(false);
        }

    }


    public void setTitle(){
        if(operType!=null ){
            if(operType.equals(EDIT)){
                mTitle = "修改未履行工程";
            }else if(operType.equals(ViewOperatorType.VIEW)){
                mTitle = "查看未履行工程";
            }else if(operType.equals(ViewOperatorType.ADD)){
                mTitle = "添加未履行工程";
            }
        }else{
            mTitle = "添加未履行工程";
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
        JiuyiNewMarketClientDtailMutiActivity.this.setResult(1, intent);
    }

    public boolean check(){
        if(TextUtils.isEmpty(jig_client.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
//        if(jig_isvisit.bdefautl && Func.IsStringEmpty(jig_handlingMatters.getText())){
//            startDialog(DialogID.DialogDoNothing, "", "请输入处理事项！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null || data.getExtras() == null) {
            return;
        }
        Bundle bundle;
        switch (requestCode) {

            case 201:
                bundle = data.getExtras();
                MemberBean.ContentBean contentBean=bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
                if(contentBean!=null && contentBean.getOrgName()!=null ){
                    String customerName3=contentBean.getOrgName();
                    long activityId=contentBean.getId();

                    if(activityId>0 && customerName3!=null ){
                        jig_client.setText(customerName3);
                        MemberBeanID memberBeanID=new MemberBeanID();
                        memberBeanID.setId(activityId);
                        memberBeanID.setOrgName(customerName3);
                        retailChannelItemsBean.setMember(memberBeanID);
                    }
                }

                break;
            default:
                break;

        }
    }

}
