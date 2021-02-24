package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import customer.Utils.FastUtils;
import customer.Utils.ViewOperatorType;
import customer.entity.DepartmentBean;
import customer.entity.LinkmanNewBean;
import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewLinkmanActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 联系人新增页面
 *****************************************************************
 */
public class JiuyiCustomerNewLinkmanActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,tv_birthday;
    private ImageView tv_operation2,tv_operation3,tv_operation4;
    private ImageButton mibadd;
    //    定义EditText的数量
    private int count=0;
    //    定义LinearLayout线性布局
    private LinearLayout mlltel,ll_tel2,ll_tel3,ll_tel4;
    private  View view_tel2,view_tel3,view_tel4;

    private  LinkmanNewBean contentBean;
    private  TextView tv_sex,tv_client,tv_delcontact;
    private JiuyiEditText et_name,et_tel,et_tel2,et_tel3,et_tel4,et_email,et_address,et_office,et_duty;
    private ToggleButton tb;
    private Boolean bdefautl=true;
    private String ViewType="";
    private long beanid=0,Customerid=0,officeID=0;
    private String viewOperatorType="";
    private LinkmanNewBean updateBean;
    private String customerName="";
    private LinearLayout ll_delcontact;
    private View view_delcontact;
    private  TextView tv_office;
    private JiuyiEditText et_hobby,et_officeaddress;
    private ToggleButton tb_status;

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        customerName=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        viewOperatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        if(viewOperatorType.equals(ViewOperatorType.EDIT) && beanid>0){
            getinfoDetail(beanid);
        }
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewlinkman_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        mlltel=(LinearLayout)mBodyLayout.findViewById(Res.getViewID(null, "ll_tel"));
        setTitle();
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (FastUtils.isFastClick()) {
                        if(viewOperatorType.equals(ViewOperatorType.ADD)){
                            submit();
                        }else if(viewOperatorType.equals(ViewOperatorType.EDIT)){
                            updateProductInfo();
                        }
                    }
                }
            });
        }
        et_name = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_name"));
        et_tel = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_tel"));
        et_tel2 = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_tel2"));
        et_tel3 = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_tel3"));
        et_tel4 = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_tel4"));
        et_email = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_email"));
        et_address = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_address"));
        tv_office = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_office"));
        tv_office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Customerid<=0){
                    Toast.makeText(JiuyiCustomerNewLinkmanActivity.this, "请先选择客户", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CUSTOMERDEPARTMENT");
                if(Customerid>0){
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                }
                intent.putExtras(mBundle);
                startActivityForResult(intent, 204);
            }
        });
        et_hobby = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_hobby"));
        et_officeaddress = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_officeaddress"));
        et_duty = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_duty"));
        tv_sex = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_sex"));
        tv_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewLinkmanActivity.this);
                buidler.setTitle("人员类型");
                final String[] data={"男","女"};
                buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_sex.setText(data[which].trim());
                        dialog.dismiss();
                    }
                });
                buidler.show();

            }
        });
        tv_client = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_client"));
        tv_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FastUtils.isFastClick()) {
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                    changePage(mBundle,Pub.CustomerSelect,true);
                }
            }
        });
        tv_delcontact= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_delcontact"));
        tv_delcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FastUtils.isFastClick()) {
                    startDialog(DialogID.DialogDeleteTrue, "", "确定删除该条记录吗？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                }
            }
        });
        if(!Func.IsStringEmpty(customerName)){
            tv_client.setText(customerName);
        }
        /*tb = (ToggleButton) mBodyLayout.findViewById(Res.getViewID(null, "toggleButton"));
        if(tb!=null){
            tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        bdefautl=true;
                    }else{
                        bdefautl=false;
                    }
                }
            });
        }*/
        tb_status = (ToggleButton) mBodyLayout.findViewById(Res.getViewID(null, "tb_status"));
        if(tb!=null){
            tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        bdefautl=true;
                    }else{
                        bdefautl=false;
                    }
                }
            });
        }

        tv_birthday = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_birthday"));
        if (tv_birthday != null) {
            tv_birthday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerNewLinkmanActivity.this);
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
                            tv_birthday.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        }
                    });
                    dialog.show();
                }
            });

        }
        ll_tel2= (LinearLayout) mBodyLayout.findViewById(Res.getViewID(null, "ll_tel2"));
        view_tel2=(View) mBodyLayout.findViewById(Res.getViewID(null, "view_tel2"));
        ll_tel3= (LinearLayout) mBodyLayout.findViewById(Res.getViewID(null, "ll_tel3"));
        view_tel3=(View) mBodyLayout.findViewById(Res.getViewID(null, "view_tel3"));
        ll_tel4= (LinearLayout) mBodyLayout.findViewById(Res.getViewID(null, "ll_tel4"));
        view_tel4=(View) mBodyLayout.findViewById(Res.getViewID(null, "view_tel4"));
        mibadd= (ImageButton) mBodyLayout.findViewById(Res.getViewID(null, "ib_add"));
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if(ll_tel2.getVisibility()==View.GONE){
                        ll_tel2.setVisibility(View.VISIBLE);
                        view_tel2.setVisibility(View.VISIBLE);
                    }else if(ll_tel3.getVisibility()==View.GONE){
                        ll_tel3.setVisibility(View.VISIBLE);
                        view_tel3.setVisibility(View.VISIBLE);
                    }else if(ll_tel4.getVisibility()==View.GONE){
                        ll_tel4.setVisibility(View.VISIBLE);
                        view_tel4.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        tv_operation2= (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "tv_operation2"));
        tv_operation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_tel2.setVisibility(View.GONE);
                view_tel2.setVisibility(View.GONE);
            }
        });
        tv_operation3= (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "tv_operation3"));
        tv_operation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_tel3.setVisibility(View.GONE);
                view_tel3.setVisibility(View.GONE);
            }
        });
        tv_operation4= (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "tv_operation4"));
        tv_operation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_tel4.setVisibility(View.GONE);
                view_tel4.setVisibility(View.GONE);
            }
        });

        ll_delcontact = (LinearLayout) mBodyLayout.findViewById(Res.getViewID(null, "ll_delcontact"));
        view_delcontact= (View) mBodyLayout.findViewById(Res.getViewID(null, "view_delcontact"));
        if(viewOperatorType.equals(ViewOperatorType.EDIT)){
            ll_delcontact.setVisibility(View.VISIBLE);
            view_delcontact.setVisibility(View.VISIBLE);
        }

    }

    public void setTitle(){
        if(viewOperatorType.equals(ViewOperatorType.ADD)){
            mTitle = "新建联系人";
        }else if(viewOperatorType.equals(ViewOperatorType.EDIT)){
            mTitle = "编辑联系人信息";
        }
        setTitle(mTitle);
    }

    public void submit(){
        boolean mbcheck=false;
        mbcheck=check();
        if(!mbcheck){
            return;
        }
        contentBean=new LinkmanNewBean();
        contentBean.setName(et_name.getText().toString().trim());
        contentBean.setSex(tv_sex.getText().toString().trim());
        contentBean.setBirthday(tv_birthday.getText().toString().trim());
        contentBean.setPhone(et_tel.getText().toString().trim());
        if(ll_tel2.getVisibility()==View.VISIBLE){
            contentBean.setPhoneTwo(et_tel2.getText().toString().trim());
        }
        if(ll_tel3.getVisibility()==View.VISIBLE){
            contentBean.setPhoneThree(et_tel3.getText().toString().trim());
        }
        if(ll_tel4.getVisibility()==View.VISIBLE){
            contentBean.setPhoneFour(et_tel4.getText().toString().trim());
        }
        contentBean.setEmail(et_email.getText().toString().trim());
        contentBean.setAddress(et_address.getText().toString().trim());
//        contentBean.setOffice(et_office.getText().toString().trim());
        contentBean.setDuty(et_duty.getText().toString().trim());
        contentBean.setOfficeAddress(et_officeaddress.getText().toString().trim());
        contentBean.setFavorite(et_hobby.getText().toString().trim());
        if(officeID>0){
            DepartmentBean.DepartmentBeanID departmentBeanID=new DepartmentBean.DepartmentBeanID();
            departmentBeanID.setId(officeID);
            contentBean.setOffice(departmentBeanID);
        }
        contentBean.setIncumbency(bdefautl);
        if(Customerid>0){
            MemberBeanID memberBean=new MemberBeanID();
            memberBean.setId(Customerid);
            contentBean.setMember(memberBean);
        }

        JiuyiHttp.POST("link-man/create")
                .setJson(GsonUtil.gson().toJson(contentBean))
                .addHeader("Authorization", Rc.id_tokenparam)

                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result) {
                        Rc.mBackfresh=true;
                        startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }
    public boolean check(){
        if(TextUtils.isEmpty(et_name.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入姓名！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_tel.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入电话号码！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tv_client.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(!Func.IsStringEmpty(et_email.getText().toString().trim())){
            if(!Func.isEmail(et_email.getText().toString().trim())){
                startDialog(DialogID.DialogDoNothing, "", "邮箱格式不正确！", JiuyiDialogBase.Dialog_Type_Yes, null);
                return false;
            }
        }
        if(TextUtils.isEmpty(et_officeaddress.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入常用办公地址！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction== DialogID.DialogTrue)
        {
            backPage();

        }else if(nAction== DialogID.DialogDeleteTrue)
        {
            if(nKeyCode == KeyEvent.KEYCODE_BACK ){
                return;
            }else if(nKeyCode == KeyEvent.KEYCODE_ENTER){
                deleteContact(beanid);
            }
        }
    }

    /*public void deleteContact(long id){
        JiuyiHttp.RETROFIT().addHeader("Authorization", Rc.id_tokenparam)
                .create(CustomerService.class)
                .deleteContact(id+"")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("success", "response:" );
                        Rc.mBackfresh=true;
                        startDialog(DialogID.DialogTrue, "", "删除成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("fail", "response:" );
                    }
                });

    }*/

    public void deleteContact(long id){
        JiuyiHttp.DELETE("link-man/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            Rc.mBackfresh=true;
                            Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "删除成功!", Toast.LENGTH_SHORT).show();
                            backPage();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }


    private void getinfoDetail(long id) {
        JiuyiHttp.GET("link-man/detail/"+id)
                .tag("request_get_spot_contact")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<LinkmanNewBean>() {
                    @Override
                    public void onSuccess(LinkmanNewBean contentBean) {
                        if(contentBean!=null){
                            updateBean=contentBean;
                            if(updateBean.getName()!=null){
                                et_name.setText(updateBean.getName().toString());
                            }
                            if(updateBean.getSex()!=null){
                                tv_sex.setText(updateBean.getSex());
                            }
                            if(updateBean.getBirthday()!=null){
                                String birthday = updateBean.getBirthday();
                                if(birthday!=null && birthday.toUpperCase().contains("T")){
                                    birthday=birthday.substring(0,birthday.indexOf("T"));
                                }
                                tv_birthday.setText(birthday);
                            }
                            if(updateBean.getPhone()!=null){
                                et_tel.setText(updateBean.getPhone());
                            }
                            if(updateBean.getPhoneTwo()!=null && !Func.IsStringEmpty(updateBean.getPhoneTwo())){
                                ll_tel2.setVisibility(View.VISIBLE);
                                et_tel2.setText(updateBean.getPhoneTwo());
                            }
                            if(updateBean.getPhoneThree()!=null && !Func.IsStringEmpty(updateBean.getPhoneThree())){
                                ll_tel3.setVisibility(View.VISIBLE);
                                et_tel3.setText(updateBean.getPhoneThree());
                            }
                            if(updateBean.getPhoneFour()!=null && !Func.IsStringEmpty(updateBean.getPhoneFour())){
                                ll_tel4.setVisibility(View.VISIBLE);
                                et_tel4.setText(updateBean.getPhoneFour());
                            }
                            if(updateBean.getEmail()!=null){
                                et_email.setText(updateBean.getEmail());
                            }
                            if(updateBean.getAddress()!=null){
                                et_address.setText(updateBean.getAddress());
                            }
                            if(updateBean.getOffice()!=null && updateBean.getOffice().getName()!=null){
                                tv_office.setText(updateBean.getOffice().getName());
                            }
                            if(updateBean.getDuty()!=null){
                                et_duty.setText(updateBean.getDuty().toString());
                            }
                            if(updateBean.getMember()!=null && updateBean.getMember().getOrgName()!=null){
                                tv_client.setText(updateBean.getMember().getOrgName());
                            }
                            if(updateBean.getOffice()!=null && updateBean.getOffice().getName()!=null){
                                tv_office.setText(updateBean.getOffice().getName());
                            }

                            if(updateBean.getFavorite()!=null){
                                et_hobby.setText(updateBean.getFavorite());
                            }
                            if(updateBean.getOfficeAddress()!=null){
                                et_officeaddress.setText(updateBean.getOfficeAddress());
                            }
                            tb_status.setChecked(updateBean.isIncumbency());

                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

    public void updateProductInfo(){
        if(updateBean==null){
            updateBean =new LinkmanNewBean();;
            updateBean.setId(beanid);
        }
        updateBean.setName(et_name.getText().toString().trim());
        updateBean.setSex(tv_sex.getText().toString().trim());
        updateBean.setBirthday(tv_birthday.getText().toString().trim());
        updateBean.setPhone(et_tel.getText().toString().trim());
        if(ll_tel2.getVisibility()==View.VISIBLE){
            updateBean.setPhoneTwo(et_tel2.getText().toString().trim());
        }else{
            updateBean.setPhoneTwo("");
        }
        if(ll_tel3.getVisibility()==View.VISIBLE){
            updateBean.setPhoneThree(et_tel3.getText().toString().trim());
        }else{
            updateBean.setPhoneThree("");
        }
        if(ll_tel4.getVisibility()==View.VISIBLE){
            updateBean.setPhoneFour(et_tel4.getText().toString().trim());
        }else{
            updateBean.setPhoneFour("");
        }
        updateBean.setEmail(et_email.getText().toString().trim());
        updateBean.setAddress(et_address.getText().toString().trim());
//        updateBean.setOffice(et_office.getText().toString().trim());
        updateBean.setDuty(et_duty.getText().toString().trim());
        updateBean.setOfficeAddress(et_officeaddress.getText().toString().trim());
        updateBean.setFavorite(et_hobby.getText().toString().trim());
        if(officeID>0){
            DepartmentBean.DepartmentBeanID departmentBeanID=new DepartmentBean.DepartmentBeanID();
            departmentBeanID.setId(officeID);
            updateBean.setOffice(departmentBeanID);
        }
        updateBean.setIncumbency(bdefautl);

        if(Customerid>0){
            MemberBeanID memberBean=new MemberBeanID();
            memberBean.setId(Customerid);
            updateBean.setMember(memberBean);
        }

        JiuyiHttp.PUT("link-man/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(com.common.GsonUtil.gson().toJson(updateBean))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        Rc.mBackfresh=true;
                        startDialog(DialogID.DialogTrue, "", "更新成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent){
        if(intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle bundle = intent.getExtras();
        Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        String CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        if(Customerid>0 && !Func.IsStringEmpty(CustomerName) ){
            tv_client.setText(CustomerName);
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || data.getExtras() == null) {
            return;
        }
        Bundle bundle;
        switch (requestCode) {
            case 204:
                bundle = data.getExtras();
                String customerName3=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                officeID=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(officeID>0 && customerName3!=null ){
                    tv_office.setText(customerName3);
                  }
                break;


            default:
                bundle = data.getExtras();
                Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                String CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                if(Customerid>0 && !Func.IsStringEmpty(CustomerName) ){
                    tv_client.setText(CustomerName);
                    tv_office.setText("");
                    officeID=0;
                }
                break;

        }
    }
}
