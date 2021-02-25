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
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Res;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;
import com.wanglicrm.android.R;

import commonlyused.bean.ChannelDevelopBean;
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
public class JiuyiNewSignIntentionDtailMutiActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String billType="";
    private long Customerid=-1;
    private String bill_type="";
    private JiuyiItemGroup jigClient;
    private JiuyiToggleButtonGroup jig_issign;

    private JiuyiButton btnSave;
    private String operType="",billOperType="";
    private ChannelDevelopBean.SignIntentionsBean retailChannelItemsBean;
    private LinearLayout ll_content;




    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newsignintentmulti_layout"), null);
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
            retailChannelItemsBean =new ChannelDevelopBean.SignIntentionsBean();
        }else{
            retailChannelItemsBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        }



        jigClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        jig_issign = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_issign);

        ll_content= (LinearLayout) mBodyLayout.findViewById(R.id.ll_content);

        btnSave = (JiuyiButton) mBodyLayout.findViewById(R.id.btn_save);
        if(retailChannelItemsBean !=null) {
            if (retailChannelItemsBean.getMember() != null ) {
                jigClient.setText(retailChannelItemsBean.getMember());
            }
            jig_issign.tb_type.setChecked(retailChannelItemsBean.isSign());

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
                    retailChannelItemsBean.setSign(jig_issign.bdefautl);
                    retailChannelItemsBean.setMember(jigClient.getText());
                    setBackActivityBundle();
                    backPage();
                }
            });
        }

        if(operType.equals(ViewOperatorType.VIEW)){
            disableSubControls(ll_content);
        }
        if(billOperType.equals(ViewOperatorType.SPECIAL)){
            jig_issign.tb_type.setEnabled(true);
        }else {
            jig_issign.tb_type.setEnabled(false);
        }
    }


    public void setTitle(){
        if(operType!=null ){
            if(operType.equals(EDIT)){
                mTitle = "修改签署意向客户";
            }else if(operType.equals(ViewOperatorType.VIEW)){
                mTitle = "查看签署意向客户";
            }else if(operType.equals(ViewOperatorType.ADD)){
                mTitle = "添加签署意向客户";
            }
        }else{
            mTitle = "添加签署意向客户";
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
        JiuyiNewSignIntentionDtailMutiActivity.this.setResult(1, intent);
    }

    public boolean check(){
        if(TextUtils.isEmpty(jigClient.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;

    }

}
