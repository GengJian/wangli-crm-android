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
public class JiuyiNewUnfullProjectDtailMutiActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String billType="";
    private long Customerid=-1;
    private String bill_type="";
    private JiuyiItemGroup jig_project,jig_reasonsNoPerformance,jig_date;
    private JiuyiToggleButtonGroup jig_isfollowup;

    private JiuyiButton btnSave;
    private String operType="",billOperType="";
    private MarketEngineeringBean.UnfulfilledProjectsBean retailChannelItemsBean;
    private LinearLayout ll_content;




    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newunfullprojectmulti_layout"), null);
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
            retailChannelItemsBean =new MarketEngineeringBean.UnfulfilledProjectsBean();
        }else{
            retailChannelItemsBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        }

        jig_project = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_project);
        jig_isfollowup = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_isfollowup);

        jig_reasonsNoPerformance = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_reasonsNoPerformance);
        jig_date = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_date);
        jig_date.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                DatePickDialog dialog = new DatePickDialog(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity());
                //设置上下年分限制
                dialog.setYearLimt(60);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        String sDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
                        jig_date.setText(sDate);
                    }
                });
                dialog.show();
            }
        });
//        jig_date.setText(JiuyiDateUtil.getNowTime3());

        ll_content= (LinearLayout) mBodyLayout.findViewById(R.id.ll_content);

        btnSave = (JiuyiButton) mBodyLayout.findViewById(R.id.btn_save);
        if(retailChannelItemsBean !=null) {
            if (retailChannelItemsBean.getEngineering() != null ) {
                jig_project.setText(retailChannelItemsBean.getEngineering());
            }
            jig_isfollowup.tb_type.setChecked(retailChannelItemsBean.isEffective());
            if (retailChannelItemsBean.getReasonsNoPerformance() != null ) {
                jig_reasonsNoPerformance.setText(retailChannelItemsBean.getReasonsNoPerformance());
            }
            if (retailChannelItemsBean.getEstimatedOrderTime() != null ) {
                jig_date.setText(retailChannelItemsBean.getEstimatedOrderTime());
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
                    retailChannelItemsBean.setEffective(jig_isfollowup.bdefautl);
                    if(!Func.IsStringEmpty(jig_project.getText())){
                        retailChannelItemsBean.setEngineering(jig_project.getText());
                    }
                    if(!Func.IsStringEmpty(jig_reasonsNoPerformance.getText())){
                        retailChannelItemsBean.setReasonsNoPerformance(jig_reasonsNoPerformance.getText());
                    }
                    if(!Func.IsStringEmpty(jig_date.getText())){
                        retailChannelItemsBean.setEstimatedOrderTime(jig_date.getText());
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
            jig_project.contentEdt.setEnabled(true);
            jig_isfollowup.tb_type.setEnabled(true);
            jig_reasonsNoPerformance.contentEdt.setEnabled(true);
        }else {
            jig_project.contentEdt.setEnabled(false);
            jig_isfollowup.tb_type.setEnabled(false);
            jig_reasonsNoPerformance.contentEdt.setEnabled(false);
            jig_date.setItemOnClickListener(null);
        }
        if(operType.equals(ViewOperatorType.ADD)){
            jig_project.contentEdt.setEnabled(true);
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
        JiuyiNewUnfullProjectDtailMutiActivity.this.setResult(1, intent);
    }

    public boolean check(){
        if(TextUtils.isEmpty(jig_project.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入工程！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(billOperType.equals(ViewOperatorType.SPECIAL)&&Func.IsStringEmpty(jig_reasonsNoPerformance.getText())){
            startDialog(DialogID.DialogDoNothing, "", "请输入未履行原因！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(billOperType.equals(ViewOperatorType.SPECIAL)&&jig_isfollowup.bdefautl && Func.IsStringEmpty(jig_date.getText())){
            startDialog(DialogID.DialogDoNothing, "", "请输入预计下单时间！", JiuyiDialogBase.Dialog_Type_Yes, null);
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
