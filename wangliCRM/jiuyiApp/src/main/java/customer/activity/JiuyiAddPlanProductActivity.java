package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;

import java.util.List;

import customer.Utils.FastUtils;
import customer.entity.NewSpecialProductBean;

import static customer.Utils.ViewOperatorType.EDIT;
import static customer.adapter.NewSpecialProductAdapter.VIEW_TYPE_MENU_DELETE;

/**
 * ****************************************************************
 * 文件名称 : JiuyiAddPlanProductActivity
 * 作       者 : zhengss
 * 创建时间:2018/8/9 14:43
 * 文件描述 : 计划外要货产品信息
 *****************************************************************
 */
public class JiuyiAddPlanProductActivity extends JiuyiActivityBase {
    private TextView tv_patchnumber,tv_level,mtvcomplete;
    private ImageView mLeftView;
    private JiuyiButton mbtnsave;
    private JiuyiEditText et_count;
    private NewSpecialProductBean newSpecialProductBean,newSpecialProductOldBean;
    private String billType="";

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_addplanproduct_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
         setContentView(mBodyLayout);
        if(mBundle!=null){
            newSpecialProductBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN);
            billType= mBundle.getString(JiuyiBundleKey.PARAM_BILLTYPE);
            if(billType!=null && billType.equals(EDIT)){
                newSpecialProductOldBean=newSpecialProductBean;
            }
        }
        setTitle();
        if(newSpecialProductBean==null){
            newSpecialProductBean=new NewSpecialProductBean();
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
                    if(newSpecialProductBean==null){
                        newSpecialProductBean=new NewSpecialProductBean();
                    }
                    newSpecialProductBean.setViewType(VIEW_TYPE_MENU_DELETE);
                    newSpecialProductBean.setBatchNum(tv_patchnumber.getText().toString());
                    newSpecialProductBean.setLevelName(tv_level.getText().toString());
                    newSpecialProductBean.setCount(Double.parseDouble(et_count.getText().toString().toString()));
                    setBackActivityBundle();
                    backPage();

                }
            });
        }
        tv_patchnumber = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_patchnumber"));
        tv_patchnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FastUtils.isFastClick()) {
                    Bundle mBundle=new Bundle();
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"specialproduct");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                    changePage(mBundle,Pub.CustomerSelect,true);
                }
            }
        });
        tv_level = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_level"));
        tv_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FastUtils.isFastClick()) {
                    final List<DictBean> priceBeanList= DictUtils.getDictList("product_grade");
                    if(priceBeanList!=null &&priceBeanList.size()>0){
                        AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiAddPlanProductActivity.this);
                        buidler.setTitle("等级选择");
                        final String[] data=new String[priceBeanList.size()] ;
                        for(int i=0;i<priceBeanList.size();i++){
                            data[i]=priceBeanList.get(i).getRemark();
                        }
                        buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tv_level.setText(data[which].trim());
                                newSpecialProductBean.setLevelName(tv_level.getText().toString());
                                newSpecialProductBean.setLevelCode(priceBeanList.get(which).getValue());
                                dialog.dismiss();
                            }
                        });
                        buidler.show();
                    }

                }
            }
        });
        et_count= (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_count"));
        if(newSpecialProductBean!=null){
            if(newSpecialProductBean.getBatchNum()!=null){
                tv_patchnumber.setText(newSpecialProductBean.getBatchNum());
            }
            if(newSpecialProductBean.getLevelName()!=null){
                tv_level.setText(newSpecialProductBean.getLevelName());
            }
            if(newSpecialProductBean.getCount()!=null){
                et_count.setText(newSpecialProductBean.getCount()+"");
            }
        }
    }


    public void setTitle(){
        if(billType!=null && billType.equals(EDIT)){
            mTitle = "修改计划外要货产品信息";
        }else{
            mTitle = "添加计划外要货产品信息";
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
        bundle.putParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN, newSpecialProductBean);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiAddPlanProductActivity.this.setResult(1, intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent){
        if(intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle bundle = intent.getExtras();
        String batchNum= bundle.getString(JiuyiBundleKey.PARAM_BATCHNUM);
        if(!Func.IsStringEmpty(batchNum)){
            tv_patchnumber.setText(batchNum);
        }
    }

    public boolean check(){
        if(TextUtils.isEmpty(tv_patchnumber.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择批号！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tv_level.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择等级！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_count.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入数量！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }

        return true;

    }


}
