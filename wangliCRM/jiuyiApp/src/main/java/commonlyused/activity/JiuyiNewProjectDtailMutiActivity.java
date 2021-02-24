package commonlyused.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Res;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;
import com.wanglicrm.android.R;

import commonlyused.bean.ChannelDevelopBean;
import commonlyused.bean.MarketEngineeringBean;
import customer.Utils.ViewOperatorType;
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
public class JiuyiNewProjectDtailMutiActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String billType="";
    private long Customerid=-1;
    private String bill_type="";
    private JiuyiItemGroup jig_project,jig_followUpResults;
    private JiuyiToggleButtonGroup jig_isfollowup;

    private JiuyiButton btnSave;
    private String operType="",billOperType="";
    private MarketEngineeringBean.ReportingProjectsBean retailChannelItemsBean;
    private LinearLayout ll_content;




    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newprojectmulti_layout"), null);
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
            retailChannelItemsBean =new MarketEngineeringBean.ReportingProjectsBean();
        }else{
            retailChannelItemsBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        }

        jig_project = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_project);
        jig_isfollowup = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_isfollowup);

        jig_followUpResults = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_followUpResults);


        ll_content= (LinearLayout) mBodyLayout.findViewById(R.id.ll_content);

        btnSave = (JiuyiButton) mBodyLayout.findViewById(R.id.btn_save);
        if(retailChannelItemsBean !=null) {
            if (retailChannelItemsBean.getEngineering() != null ) {
                jig_project.setText(retailChannelItemsBean.getEngineering());
            }
            jig_isfollowup.tb_type.setChecked(retailChannelItemsBean.isFollowUp());
            if (retailChannelItemsBean.getFollowUpResults() != null ) {
                jig_followUpResults.setText(retailChannelItemsBean.getFollowUpResults());
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
                    retailChannelItemsBean.setFollowUp(jig_isfollowup.bdefautl);
                    if(!Func.IsStringEmpty(jig_project.getText())){
                        retailChannelItemsBean.setEngineering(jig_project.getText());
                    }
                    if(!Func.IsStringEmpty(jig_followUpResults.getText())){
                        retailChannelItemsBean.setFollowUpResults(jig_followUpResults.getText());
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
            jig_isfollowup.tb_type.setEnabled(true);
            jig_followUpResults.contentEdt.setEnabled(true);
        }else {
            jig_isfollowup.tb_type.setEnabled(false);
            jig_followUpResults.contentEdt.setEnabled(false);
        }

    }


    public void setTitle(){
        if(operType!=null ){
            if(operType.equals(EDIT)){
                mTitle = "修改报备工程跟进";
            }else if(operType.equals(ViewOperatorType.VIEW)){
                mTitle = "查看报备工程跟进";
            }else if(operType.equals(ViewOperatorType.ADD)){
                mTitle = "添加报备工程跟进";
            }
        }else{
            mTitle = "添加报备工程跟进";
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
        JiuyiNewProjectDtailMutiActivity.this.setResult(1, intent);
    }

    public boolean check(){
        if(TextUtils.isEmpty(jig_project.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入工程！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(billOperType.equals(ViewOperatorType.SPECIAL)&&jig_isfollowup.bdefautl && Func.IsStringEmpty(jig_followUpResults.getText())){
            startDialog(DialogID.DialogDoNothing, "", "请输入跟进结果！", JiuyiDialogBase.Dialog_Type_Yes, null);
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
