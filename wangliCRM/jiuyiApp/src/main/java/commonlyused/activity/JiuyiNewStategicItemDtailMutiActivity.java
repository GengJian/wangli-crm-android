package commonlyused.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import commonlyused.bean.StategicEngineeringBean;
import customer.Utils.ViewOperatorType;
import customer.view.JiuyiToggleButtonGroup;

import static customer.Utils.ViewOperatorType.EDIT;
import static customer.Utils.ViewOperatorType.SPECIAL;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNewStategicItemDtailMutiActivity
 * 作       者 : zhengss
 * 创建时间:2018/8/9 14:43
 * 文件描述 : 客户明细
 *****************************************************************
 */
public class JiuyiNewStategicItemDtailMutiActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String billType="";
    private long Customerid=-1;
    private String bill_type="";
    private JiuyiItemGroup jigClient,jig_result;
    private JiuyiToggleButtonGroup jig_issign;

    private JiuyiButton btnSave;
    private String operType="",billOperType="";
    private StategicEngineeringBean.StrategicEngineeringItemsBean retailChannelItemsBean;
    private LinearLayout ll_content;




    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newstateitemmulti_layout"), null);
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
            retailChannelItemsBean =new StategicEngineeringBean.StrategicEngineeringItemsBean();
        }else{
            retailChannelItemsBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        }



        jigClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        jig_issign = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_issign);
        jig_result = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_result);
        ll_content= (LinearLayout) mBodyLayout.findViewById(R.id.ll_content);

        btnSave = (JiuyiButton) mBodyLayout.findViewById(R.id.btn_save);
        if(retailChannelItemsBean !=null) {
            if (retailChannelItemsBean.getConstructionSite() != null ) {
                jigClient.setText(retailChannelItemsBean.getConstructionSite());
            }
            jig_issign.tb_type.setChecked(retailChannelItemsBean.isVisit());
            if (retailChannelItemsBean.getAchieveResults() != null ) {
                jig_result.setText(retailChannelItemsBean.getAchieveResults());
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
                    retailChannelItemsBean.setVisit(jig_issign.bdefautl);
                    retailChannelItemsBean.setConstructionSite(jigClient.getText());
                    if(!Func.IsStringEmpty(jig_result.getText())){
                        retailChannelItemsBean.setAchieveResults(jig_result.getText());
                    }
                    setBackActivityBundle();
                    backPage();
                }
            });
        }

        if(operType.equals(ViewOperatorType.VIEW)){
            disableSubControls(ll_content);
        }
        if(billOperType.equals(ViewOperatorType.ADD)){
            jig_issign.tb_type.setEnabled(false);
        }
        if(billOperType.equals(SPECIAL)){
            jig_result.contentEdt.setEnabled(true);
        }else{
            jig_result.contentEdt.setEnabled(false);
        }
    }


    public void setTitle(){
        if(operType!=null ){
            if(operType.equals(EDIT)){
                mTitle = "修改走访工地/地产";
            }else if(operType.equals(ViewOperatorType.VIEW)){
                mTitle = "查看走访工地/地产";
            }else if(operType.equals(ViewOperatorType.ADD)){
                mTitle = "添加走访工地/地产";
            }
        }else{
            mTitle = "添加走访工地/地产";
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
        JiuyiNewStategicItemDtailMutiActivity.this.setResult(1, intent);
    }

    public boolean check(){
        if(TextUtils.isEmpty(jigClient.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入工地/地产！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(billOperType!=null && billOperType.equals(SPECIAL) && TextUtils.isEmpty(jig_result.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入达成结果！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;

    }

}
