package customer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.wanglicrm.android.R;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import commonlyused.bean.LinkmanBean;
import commonlyused.bean.NormalOperatorBean;
import customer.Utils.ViewOperatorType;
import customer.entity.CustomerLinkmanBean;
import customer.entity.CustomerPaintPointBean;
import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCommonInputInfoActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 录入界面
 *****************************************************************
 */
public class JiuyiPaintPointNewActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiEditTextField content_edt;
    private String operatorType,sTitle,s_returnvalue;
    private  long customerid=-1;
    private ImageView   iv_back;
    private JiuyiItemGroup jigClient;
    private JiuyiItemGroup jigPaintpoint,jig_person,jig_incharge;
    private ToggleButton tbNeedreply;
    private JiuyiItemGroup jigDate;
    private CustomerPaintPointBean createBean;
    private Boolean bdefautl=false;
    private String Customername="";
    private long customerID2;
    private CustomerLinkmanBean.ContentBean contentBean;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_paintpoint_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        operatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        contentBean=mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }
        if(Func.IsStringEmpty(operatorType)){
            operatorType="";
        }
        if(operatorType.equals(ViewOperatorType.ADD)){
            createBean=new  CustomerPaintPointBean();
        }

        setTitle();

        jigClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        if(customerid>0 && Customername!=null){
            MemberBeanID memberBeanID=new MemberBeanID();
            memberBeanID.setId(customerid);
            memberBeanID.setOrgName(Customername);
            jigClient.setText(Customername);
            jigClient.setItemOnClickListener(null);
            createBean.setMember(memberBeanID);
        }
        jigClient.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 201);

            }
        });
        jigPaintpoint = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_paintpoint);
        tbNeedreply = (ToggleButton) mBodyLayout.findViewById(R.id.tb_needreply);
        tbNeedreply.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    bdefautl=true;
                    createBean.setNeedFeedBack(true);
                    jigDate.setVisibility(View.VISIBLE);
                    jig_person.setVisibility(View.VISIBLE);
                }else{
                    bdefautl=false;
                    createBean.setNeedFeedBack(false);
                    jigDate.setVisibility(View.GONE);
                    jig_person.setVisibility(View.GONE);
                }
            }
        });
        jig_person = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_person);
        jig_person.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 200);

            }
        });
        jig_incharge = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_incharge);
        jig_incharge.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                long id=-1;
                if(customerid>0){
                    id=customerid;
                }
                if(customerID2>0){
                    id=customerID2;
                }
                if(customerid<=0 && customerID2<=0 ){
                    Toast.makeText(JiuyiPaintPointNewActivity.this, "请先选择客户", Toast.LENGTH_SHORT).show();
                    return;
                }
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,id);
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CUSTOMERPERSON");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 202);

            }
        });
        if(contentBean!=null){
            jig_incharge.setText(contentBean.getName());
            CustomerPaintPointBean.LinkManBean linkmanBean=new CustomerPaintPointBean.LinkManBean();
            linkmanBean.setId(contentBean.getId());
            createBean.setLinkMan(linkmanBean);
        }
        jigDate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_date);
        jigDate.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                DatePickDialog dialog = new DatePickDialog(JiuyiPaintPointNewActivity.this);
                //设置上下年分限制
                dialog.setYearLimt(60);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        jigDate.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        createBean.setClosingDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
                    }
                });
                dialog.show();

            }
        });


        iv_back = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
                    if(operatorType.equals(ViewOperatorType.EDIT)){
//                        UpdateMemberInfo();
                    }else if(operatorType.equals(ViewOperatorType.ADD)){
                        createBean();
                    }


                }
            });

        }

    }
    public boolean check(){
        if(TextUtils.isEmpty(jig_incharge.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择关键人！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(jigPaintpoint.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入需求痛点！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;
    }

    public void createBean(){

        createBean.setDesp(jigPaintpoint.getText());



        submit();
    }
    public void submit(){
        JiuyiHttp.POST("pain-point/create")
                .setJson(GsonUtil.gson().toJson(createBean))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        backPage();
                        Toast.makeText(JiuyiPaintPointNewActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
//                        if(progressDialog!=null){
//                            progressDialog.dismiss();
//                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }


    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }

    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else{
            mTitle = "关键需求";
        }
        setTitle(mTitle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || data.getExtras() == null) {
            return;
        }
        Bundle bundle;
        switch (requestCode) {
            case 200:
                bundle = data.getExtras();
                s_returnvalue=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long valueId=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(valueId>0 && s_returnvalue!=null ){
                    jig_person.setText(s_returnvalue);
                    NormalOperatorBean.OperatorBeanID operatorBeanID=new NormalOperatorBean.OperatorBeanID();
                    operatorBeanID.setId(valueId);
                    createBean.setOperator(operatorBeanID);
                }
                break;
            case 201:
                bundle = data.getExtras();
                String customerName2=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                 customerID2=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(customerID2>0 && customerName2!=null ){
                    jigClient.setText(customerName2);
                    MemberBeanID memberBeanID=new MemberBeanID();
                    memberBeanID.setId(customerID2);
                    memberBeanID.setOrgName(customerName2);
                    createBean.setMember(memberBeanID);
                }
                jig_incharge.setText("");
                createBean.setLinkMan(null);
                break;
            case 202:
                bundle = data.getExtras();
                s_returnvalue=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long valueId2=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(valueId2>0 && s_returnvalue!=null ){
                    jig_incharge.setText(s_returnvalue);
                    CustomerPaintPointBean.LinkManBean contentBean=new CustomerPaintPointBean.LinkManBean();
                    contentBean.setId(valueId2);
                    createBean.setLinkMan(contentBean);
                }
                break;

            default:
                break;

        }
    }

}
