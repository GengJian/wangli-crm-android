package customer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Res;
import com.control.widget.JiuyiBigTextGroup;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import customer.Utils.ViewOperatorType;
import customer.entity.MemberBeanID;
import customer.entity.MemberListBean;
import dynamic.entity.DyBusinessBean;

import static customer.Utils.ViewOperatorType.EDIT;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNewSpecialPriceActivity
 * 作       者 : zhengss
 * 创建时间:2018/8/9 14:43
 * 文件描述 : 特价
 *****************************************************************
 */
public class JiuyiNewBusinessDetailMutiActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String operType="";
    private long Customerid=-1;
    private JiuyiItemGroup jigCompete;
    private JiuyiItemGroup jigCompeteincharge;
    private JiuyiItemGroup jigTel;
    private JiuyiItemGroup jigDeptment;
    private JiuyiItemGroup jigDuty;
    private JiuyiBigTextGroup jigRemark;
    private DyBusinessBean.ContentBean.CompetitorBehavior competitorBehavior;








    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newbusinessdetailmulti_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
         setContentView(mBodyLayout);
        if(mBundle!=null){
            operType= mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        }
        setTitle();
        if(operType==null){
            operType="";
        }
        if(operType.equals(ViewOperatorType.ADD)){
            competitorBehavior=new DyBusinessBean.ContentBean.CompetitorBehavior();
        }else{
            competitorBehavior = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        }


        jigCompete = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_compete);
        jigCompete.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CUSTOMER");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 201);

            }
        });
        jigCompeteincharge = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_competeincharge);
        jigTel = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_tel);
        jigTel.contentEdt.setInputType(InputType.TYPE_CLASS_PHONE);
        jigDeptment = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_deptment);
        jigDuty = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_duty);
        jigRemark = (JiuyiBigTextGroup) mBodyLayout.findViewById(R.id.jig_remark);
        if(competitorBehavior!=null){
            if(competitorBehavior.getMember()!=null && competitorBehavior.getMember().getOrgName()!=null){
                jigCompete.setText(competitorBehavior.getMember().getOrgName());
            }
            if(competitorBehavior.getPrincipalName()!=null){
                jigCompeteincharge.setText(competitorBehavior.getPrincipalName());
            }
            if(competitorBehavior.getPrincipalTel()!=null){
                jigTel.setText(competitorBehavior.getPrincipalTel());
            }
            if(competitorBehavior.getPrincipalDepartment()!=null){
                jigDeptment.setText(competitorBehavior.getPrincipalDepartment());
            }
            if(competitorBehavior.getPrincipalJob()!=null){
                jigDuty.setText(competitorBehavior.getPrincipalJob());
            }
            if(competitorBehavior.getContent()!=null){
                jigRemark.setText(competitorBehavior.getContent());
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
                    competitorBehavior.setPrincipalName(jigCompeteincharge.getText());
                    competitorBehavior.setPrincipalTel(jigTel.getText());
                    competitorBehavior.setPrincipalDepartment(jigDeptment.getText());
                    competitorBehavior.setPrincipalJob(jigDuty.getText());
                    competitorBehavior.setContent(jigRemark.getText());

                    setBackActivityBundle();
                    backPage();

                }
            });
        }



    }


    public void setTitle(){
        if(operType!=null && operType.equals(EDIT)){
            mTitle = "修改友商明细";
        }else{
            mTitle = "添加友商明细";
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
        bundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2, competitorBehavior);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiNewBusinessDetailMutiActivity.this.setResult(1, intent);
    }
  @Override
  public void onActivityResult(int requestCode, int resultCode, final Intent intent){
      if(intent == null || intent.getExtras() == null) {
          return;
      }
      if(requestCode==201){
          Bundle bundle = intent.getExtras();
           MemberListBean.ContentBean contentBean;
            contentBean=bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);

          if(contentBean!=null && contentBean.getAbbreviation()!=null  ){
              jigCompete.setText(contentBean.getAbbreviation());
              MemberBeanID memberBeanID=new MemberBeanID();
              memberBeanID.setId(contentBean.getId());
              memberBeanID.setAbbreviation(contentBean.getAbbreviation());
              memberBeanID.setOrgName(contentBean.getOrgName());
              competitorBehavior.setMember(memberBeanID);
          }
      }
  }


    public boolean check(){
        if(TextUtils.isEmpty(jigCompete.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择友商！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(jigCompeteincharge.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入友商负责人！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(jigTel.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入电话！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }

        return true;

    }


}
