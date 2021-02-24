package customer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import customer.Utils.FastUtils;
import customer.entity.BatchTransBean;

import static customer.Utils.ViewOperatorType.EDIT;
import static customer.adapter.NewSpecialProductAdapter.VIEW_TYPE_MENU_DELETE;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNewSpecialPriceActivity
 * 作       者 : zhengss
 * 创建时间:2018/8/9 14:43
 * 文件描述 : 特价
 *****************************************************************
 */
public class JiuyiNewPlanMutiProductActivity extends JiuyiActivityBase {
    private TextView tv_batchNumber,tv_level,tv_spec,tv_prescription,mtvcomplete;
    private JiuyiButton mbtnsave;
    private JiuyiEditText et_count;
    private BatchTransBean newBean, oldBean;
    private String billType="";
    private long Customerid=-1;
    private String bill_type="";
    private TextView tvWeight,tvFactory;
    private String sGrade="",sSpec="",sFactory,sFactoryCode,sLevelName,sLevelCode,sWeight,sPrescription;
    private Double dWeight;
    private String batchNum="";
    private long batchId=0;



    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newplanmultiproduct_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
         setContentView(mBodyLayout);
        if(mBundle!=null){
            newBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN);
            billType= mBundle.getString(JiuyiBundleKey.PARAM_BILLTYPE);
            if(billType!=null && billType.equals(EDIT)){
                oldBean = newBean;
            }
        }
        setTitle();
        if(newBean ==null){
            newBean =new BatchTransBean();
        }
        if(billType==null){
            billType="";
        }

        tv_batchNumber = (TextView) mBodyLayout.findViewById(R.id.tv_batchNum);
        tv_batchNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(billType.equals(EDIT)){
                    return;
                }
                if (FastUtils.isFastClick()) {
                    bill_type="Batchnum";
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "Batchnum");
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                    changePage(mBundle, Pub.CustomerSelect, true);
                }
            }
        });

        tv_level = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_level"));
        tv_prescription = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_prescription"));
        tv_spec = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_spec"));
        tvWeight = (TextView) mBodyLayout.findViewById(R.id.tv_weight);
        tv_prescription = (TextView) mBodyLayout.findViewById(R.id.tv_prescription);
        tvFactory = (TextView) mBodyLayout.findViewById(R.id.tv_factory);
        et_count = (JiuyiEditText) mBodyLayout.findViewById(R.id.et_count);
        if(newBean !=null){
            batchId=newBean.getId();
            if(newBean.getBatchNumber()!=null){
                tv_batchNumber.setText(newBean.getBatchNumber());
            }
            if(newBean.getProductLevelName()!=null){
                tv_level.setText(newBean.getProductLevelName());
            }
            if(newBean.getSpec()!=null){
                tv_spec.setText(newBean.getSpec());
            }
            if(newBean.getPrescription()!=null){
                tv_prescription.setText(newBean.getPrescription());
            }
            if(newBean.getWeight()>0){
                tvWeight.setText(newBean.getWeight()+"");
            }
            if(newBean.getFactoryName()!=null){
                tvFactory.setText(newBean.getFactoryName());
            }
            if(newBean.getQuantity()>0){
                et_count.setText(newBean.getQuantity()+"");
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
                    if(newBean ==null){
                        newBean =new BatchTransBean();
                    }
                    newBean.setViewType(VIEW_TYPE_MENU_DELETE);
                    newBean.setId(batchId);
                    newBean.setBatchNumber(tv_batchNumber.getText().toString());
                    newBean.setProductLevelName(tv_level.getText().toString());
                    newBean.setSpec(tv_spec.getText().toString());
                    newBean.setPrescription(tv_prescription.getText().toString());
                    newBean.setWeight(Double.parseDouble(tvWeight.getText().toString()));
                    newBean.setFactoryName(tvFactory.getText().toString());
                    newBean.setQuantity(Double.parseDouble(et_count.getText().toString().toString()));
                    setBackActivityBundle();
                    backPage();

                }
            });
        }



    }


    public void setTitle(){
        if(billType!=null && billType.equals(EDIT)){
            mTitle = "修改计划批号";
        }else{
            mTitle = "添加计划批号";
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
        bundle.putParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN, newBean);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiNewPlanMutiProductActivity.this.setResult(1, intent);
    }
  @Override
  public void onActivityResult(int requestCode, int resultCode, final Intent intent){
      if(intent == null || intent.getExtras() == null) {
          return;
      }
      Bundle bundle = intent.getExtras();
      if(bill_type.equals("Batchnum")){
          batchNum= bundle.getString(JiuyiBundleKey.PARAM_BATCHNUM);
          batchId= bundle.getLong(JiuyiBundleKey.PARAM_NEEDPLANID);
          sFactory= bundle.getString(JiuyiBundleKey.PARAM_FACTORYNAME);
          sFactoryCode= bundle.getString(JiuyiBundleKey.PARAM_COMMONCODE);
          sLevelName= bundle.getString(JiuyiBundleKey.PARAM_LEVELNAME);
          sLevelCode=bundle.getString(JiuyiBundleKey.PARAM_LEVELCODE);
          sSpec=bundle.getString(JiuyiBundleKey.PARAM_SPEC);
          sWeight=bundle.getString(JiuyiBundleKey.PARAM_WEIGHT);
          sPrescription=bundle.getString(JiuyiBundleKey.PARAM_PRESCRIPTION);
          if(!Func.IsStringEmpty(batchNum)){
              tv_batchNumber.setText(batchNum);
          }
          if(!Func.IsStringEmpty(sSpec)){
              tv_spec.setText(sSpec);
          }
          if(!Func.IsStringEmpty(sWeight)){
              dWeight=Double.parseDouble(sWeight);
              tvWeight.setText(sWeight);
          }
          if(!Func.IsStringEmpty(sLevelName)){
              tv_level.setText(sLevelName);
          }
          if(!Func.IsStringEmpty(sPrescription)){
              tv_prescription.setText(sPrescription);
          }
          sGrade=sLevelCode;
          if(!Func.IsStringEmpty(sFactory)){
              tvFactory.setText(sFactory);
          }

      }


  }


    public boolean check(){
        if(TextUtils.isEmpty(tv_batchNumber.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择批号！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_count.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入数量！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }

        return true;

    }


}
