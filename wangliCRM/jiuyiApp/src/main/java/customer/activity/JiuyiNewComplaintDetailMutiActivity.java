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
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
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
public class JiuyiNewComplaintDetailMutiActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String billType="";
    private long Customerid=-1;
    private String bill_type="";
    private JiuyiItemGroup jigComplaintype;
    private JiuyiItemGroup jigComplaintCount;
    private JiuyiItemGroup jigType;
    private JiuyiItemGroup jigRemark;






    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newcomplaintdetailmulti_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
         setContentView(mBodyLayout);
        if(mBundle!=null){
//            newBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN);
            billType= mBundle.getString(JiuyiBundleKey.PARAM_BILLTYPE);
            if(billType!=null && billType.equals(EDIT)){
//                oldBean = newBean;
            }
        }
        setTitle();
//        if(newBean ==null){
//            newBean =new BatchTransBean();
//        }
        if(billType==null){
            billType="";
        }


        jigComplaintype = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_complaintype);
        jigComplaintCount = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_complaint_count);
        jigType = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_type);
        jigRemark = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_remark);


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
//                    if(newBean ==null){
//                        newBean =new BatchTransBean();
//                    }
//                    newBean.setViewType(VIEW_TYPE_MENU_DELETE);
//                    newBean.setId(batchId);
//                    newBean.setBatchNumber(tv_batchNumber.getText().toString());
//                    newBean.setProductLevelName(tv_level.getText().toString());
//                    newBean.setSpec(tv_spec.getText().toString());
//                    newBean.setPrescription(tv_prescription.getText().toString());
//                    newBean.setWeight(Double.parseDouble(tvWeight.getText().toString()));
//                    newBean.setFactoryName(tvFactory.getText().toString());
//                    newBean.setQuantity(Double.parseDouble(et_count.getText().toString().toString()));
                    setBackActivityBundle();
                    backPage();

                }
            });
        }



    }


    public void setTitle(){
        if(billType!=null && billType.equals(EDIT)){
            mTitle = "修改客户投诉明细";
        }else{
            mTitle = "添加客户投诉明细";
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
//        bundle.putParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN, newBean);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiNewComplaintDetailMutiActivity.this.setResult(1, intent);
    }
  @Override
  public void onActivityResult(int requestCode, int resultCode, final Intent intent){
      if(intent == null || intent.getExtras() == null) {
          return;
      }
      Bundle bundle = intent.getExtras();
      if(bill_type.equals("Batchnum")){
//          batchNum= bundle.getString(JiuyiBundleKey.PARAM_BATCHNUM);
//          batchId= bundle.getLong(JiuyiBundleKey.PARAM_NEEDPLANID);
//          sFactory= bundle.getString(JiuyiBundleKey.PARAM_FACTORYNAME);
//          sFactoryCode= bundle.getString(JiuyiBundleKey.PARAM_COMMONCODE);

//          if(!Func.IsStringEmpty(batchNum)){
//              tv_batchNumber.setText(batchNum);
//          }
//          if(!Func.IsStringEmpty(sSpec)){
//              tv_spec.setText(sSpec);
//          }
//          if(!Func.IsStringEmpty(sWeight)){
//              dWeight=Double.parseDouble(sWeight);
//              tvWeight.setText(sWeight);
//          }
//          if(!Func.IsStringEmpty(sLevelName)){
//              tv_level.setText(sLevelName);
//          }


      }


  }


    public boolean check(){
//        if(TextUtils.isEmpty(tv_batchNumber.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请选择批号！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
//        if(TextUtils.isEmpty(et_count.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请输入数量！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }

        return true;

    }


}
