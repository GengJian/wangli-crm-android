package commonlyused.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.common.GsonUtil;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.ContactBean;
import commonlyused.bean.LinkmanBean;
import commonlyused.bean.LinkmanGreenBean;
import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.QueryConditionBean;
import commonlyused.db.DbHelper;
import commonlyused.db.LinkmanGreenBeanDao;
import customer.Utils.ViewOperatorType;
import customer.entity.LinkmanNewBean;
import customer.entity.SexEnum;
import customer.view.DrawableTextView;


public class JiuyiCustomerContactsInfoActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,tv_birthday,tv_tel,tv_name;
    private DrawableTextView tv_sendmessage,tv_dial;
    LinkmanBean linkmanBean;
    String linkmanBeanid="";
    private LinearLayout ll_contacttypeinfo;
    private String bill_type="";

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customercontactsinfo_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();

        linkmanBeanid=mBundle.getString(JiuyiBundleKey.PARAM_LINKMANBEANID);
        bill_type=mBundle.getString(JiuyiBundleKey.PARAM_BILLTYPE);
        if(bill_type==null){
            bill_type="";
        }
        if(!Func.IsStringEmpty(linkmanBeanid)){
            if(bill_type.equals("Contact")){
                getContactInfo(linkmanBeanid);
            }else{
                getCompanyAddressList(linkmanBeanid);
            }

        }else{
            linkmanBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN);
            OnitView();
        }


    }
    public void OnitView(){
        mtvcomplete = (TextView)findViewById(R.id.jiuyi_titlebar_complete);
        if(mtvcomplete!=null){
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newlinkman);
                    mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID,linkmanBean.getId());
                    changePage(mBundle,Pub.Customer_newlinkman,false);
                }
            });
        }

        ll_contacttypeinfo= (LinearLayout)findViewById(R.id.ll_contacttypeinfo);
        if(bill_type.equals("Contact")){
            if(mtvcomplete!=null){
                mtvcomplete.setText("编辑");
            }
            ll_contacttypeinfo.setVisibility(View.VISIBLE);
        }else{
            if(mtvcomplete!=null){
                mtvcomplete.setVisibility(View.INVISIBLE);
            }
            ll_contacttypeinfo.setVisibility(View.GONE);
        }
        tv_name = (TextView)findViewById(R.id.tv_name);
        if (tv_name != null) {
            String name = linkmanBean==null ? "" : linkmanBean.getName();
            tv_name.setText(name);
        }
        TextView tv_sex = (TextView)findViewById(R.id.tv_sex);
        if (tv_sex != null && linkmanBean!=null && linkmanBean.getSex()!=null) {
            tv_sex.setText(linkmanBean.getSex().getDesp());
        }
        tv_birthday = (TextView)findViewById(R.id.tv_birthday);
        if (tv_birthday != null) {
            String birthday = linkmanBean==null ? "" : linkmanBean.getBirthday();
            if(birthday!=null && birthday.toUpperCase().contains("T")){
                birthday=birthday.substring(0,birthday.indexOf("T"));
            }
            tv_birthday.setText(birthday);
        }
        tv_tel = (TextView)findViewById(R.id.tv_tel);
        if (tv_tel != null) {
            String tel = linkmanBean==null ? "" : linkmanBean.getTelOne();
            tv_tel.setText(tel);
        }
        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        if (tv_email != null) {
            String email = linkmanBean==null ? "" : linkmanBean.getEmail();
            tv_email.setText(email);
        }
        TextView tv_address = (TextView)findViewById(R.id.tv_address);
        if (tv_address != null) {
            String address = linkmanBean==null ? "" : linkmanBean.getAddress();
            tv_address.setText(address);
        }
        TextView tv_office = (TextView)findViewById(R.id.tv_office);
        if (tv_office != null) {
            if(linkmanBean.getOffice()!=null){
                tv_office.setText(linkmanBean.getOffice());
            }else if(linkmanBean.getDept()!=null){
                tv_office.setText(linkmanBean.getDept());
            }
        }
        TextView tv_duty = (TextView)findViewById(R.id.tv_duty);
        if (tv_duty != null) {
            String duty = linkmanBean==null ? "" : linkmanBean.getDuty();
            tv_duty.setText(duty);
        }
        TextView tv_client = (TextView) findViewById(R.id.tv_client);
        String client = linkmanBean==null ? "" : linkmanBean.getOrg();
        tv_client.setText(client);
        ToggleButton tb = (ToggleButton)findViewById(R.id.toggleButton);
        tb.setChecked(linkmanBean.isImportant());
        tb.setEnabled(false);
        tv_sendmessage = (DrawableTextView) findViewById(R.id.tv_sendmessage);
        if (tv_sendmessage != null) {
            final String timIdentifier = linkmanBean==null ? "" : linkmanBean.getTimIdentifier();
            if(Func.IsStringEmpty(timIdentifier)){
                tv_sendmessage.setEnabled(false);
                tv_sendmessage.setTextColor(Res.getColor(null, "jiuyi_info_color"));
                Drawable drawable = JiuyiCustomerContactsInfoActivity.this.getResources().getDrawable(R.drawable.send_message_n);
                tv_sendmessage.setDrawable(DrawableTextView.LEFT, drawable,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            }
            tv_sendmessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBundle.putString(JiuyiBundleKey.PARAM_IDENTIFY,timIdentifier);
                    changePage(mBundle, Pub.MSG_chat,true);

                }
            });
        }
        tv_dial = (DrawableTextView) findViewById(R.id.tv_dial);
        if (tv_dial != null) {
            String tel = linkmanBean==null ? "" : linkmanBean.getTelOne();
            if(Func.IsStringEmpty(tel)){
                tv_dial.setEnabled(false);
                tv_dial.setTextColor(Res.getColor(null, "jiuyi_info_color"));
                Drawable drawable = JiuyiCustomerContactsInfoActivity.this.getResources().getDrawable(R.drawable.phone_n);
                tv_dial.setDrawable(DrawableTextView.LEFT, drawable,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            }
            tv_dial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //检测权限
                    new JiuyiHiPermissionUtil(JiuyiCustomerContactsInfoActivity.this).checkPermission(Manifest.permission.CALL_PHONE, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tv_tel.getText().toString().trim()));
                            Bundle bundle = new Bundle();
                            intent.putExtras(bundle);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            JiuyiCustomerContactsInfoActivity.this.startActivity(intent);
                        }
                    });
                }
            });
        }
        if(linkmanBean!=null){
            LinkmanGreenBean linkmanGreenBean=new LinkmanGreenBean();
            if(linkmanBean.getName()!=null){
                linkmanGreenBean.setName(linkmanBean.getName());
            }
            if(linkmanBean.getSex()!=null){
                if(linkmanBean.getSex().equals("FEMALE")){
                    linkmanGreenBean.setSex("女");
                }else if(linkmanBean.getSex().equals("MALE")){
                    linkmanGreenBean.setSex("男");
                }
            }
            if(linkmanBean.getBirthday()!=null){
                linkmanGreenBean.setBirthday(linkmanBean.getBirthday());
            }
            if(linkmanBean.getTelOne()!=null){
                linkmanGreenBean.setTelOne(linkmanBean.getTelOne());
            }
            if(linkmanBean.getEmail()!=null){
                linkmanGreenBean.setEmail(linkmanBean.getEmail());
            }
            if(linkmanBean.getAddress()!=null){
                linkmanGreenBean.setAddress(linkmanBean.getAddress());
            }
            if(linkmanBean.getDuty()!=null){
                linkmanGreenBean.setDuty(linkmanBean.getDuty());
            }
            if(linkmanBean.getTimIdentifier()!=null){
                linkmanGreenBean.setTimIdentifier(linkmanBean.getTimIdentifier());
            }
            if(linkmanBean.getName()!=null && linkmanBean.getTelOne()!=null){
                LinkmanGreenBean itemBean = DbHelper.getInstance().linkmanGreenBeanLongDBManager().queryBuilder()
                        .where(LinkmanGreenBeanDao.Properties.Name.eq(linkmanBean.getName()))
                        .where(LinkmanGreenBeanDao.Properties.TelOne.eq(linkmanBean.getTelOne())).build().unique();
                if(itemBean!=null){
                    DbHelper.getInstance().linkmanGreenBeanLongDBManager().delete(itemBean);
                }
                DbHelper.getInstance().linkmanGreenBeanLongDBManager().insert(linkmanGreenBean);
            }

        }

    }


    public  void getCompanyAddressList(String id){
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("id");
        List<Long> value = new ArrayList<Long>();
        List<QueryConditionBean.RulesBean> rulesBeanList;
        rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(linkmanBeanid)&&Func.IsIntFormat(linkmanBeanid,true) ){
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("id");
            rulesBean.setOption("EQ");
            value.add(Func.parseLong(linkmanBeanid));
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("operator/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<NormalOperatorBean>() {
                    @Override
                    public void onSuccess(NormalOperatorBean data) {
                        if(data!=null){
                            List<NormalOperatorBean.ContentBean> ContentBeanlist=data.getContent();
                            if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                                for(NormalOperatorBean.ContentBean contentBean :ContentBeanlist){
                                    linkmanBean=new LinkmanBean();
                                    linkmanBean.setId(contentBean.getId());
                                    linkmanBean.setName(contentBean.getName());
                                    linkmanBean.setSex(contentBean.getSex());
                                    linkmanBean.setBirthday(contentBean.getBirthday());
                                    linkmanBean.setTelOne(contentBean.getTelOne());
                                    linkmanBean.setEmail(contentBean.getEmail());
                                    linkmanBean.setAddress(contentBean.getAddress());
//                                    NormalOperatorBean.ContentBean.DepartmentBeanX  departmentBeanX=contentBean.getDepartment();
                                    if(contentBean.getDepartment()!=null){
                                        linkmanBean.setDept(contentBean.getDepartment().getName());
                                    }
//                                    NormalOperatorBean.ContentBean.PositionBean positionBean=contentBean.getPosition();
                                    if(contentBean.getTitle()!=null){
                                        linkmanBean.setDuty(contentBean.getTitle());
                                    }
                                    linkmanBean.setTimIdentifier(contentBean.getTimIdentifier());
                                }
                            }
                            OnitView();

                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
//                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                        OnitView();
                    }
                });
    }

    public  void getContactInfo(String id){
        JiuyiHttp.GET("link-man/detail/"+id)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<LinkmanNewBean>() {
                    @Override
                    public void onSuccess(LinkmanNewBean data) {
                        if(data!=null){
                            LinkmanNewBean contentBean=data;
                            if(contentBean!=null){
                                linkmanBean=new LinkmanBean();
                                linkmanBean.setId(contentBean.getId());
                                linkmanBean.setName(contentBean.getName());
                                if(contentBean.getSex()!=null){
                                    if(contentBean.getSex().equals("男")){
                                        linkmanBean.setSex(SexEnum.MALE);
                                    }else if(contentBean.getSex().equals("女")){
                                        linkmanBean.setSex(SexEnum.FEMALE);
                                    }
                                }
                                if(contentBean.getBirthday()!=null){
                                    linkmanBean.setBirthday(contentBean.getBirthday());
                                }
                                if(contentBean.getPhone()!=null){
                                    linkmanBean.setTelOne(contentBean.getPhone());
                                }
                                if(contentBean.getEmail()!=null){
                                    linkmanBean.setEmail(contentBean.getEmail());
                                }
                                if(contentBean.getAddress()!=null){
                                    linkmanBean.setAddress(contentBean.getAddress());
                                }
                                if(contentBean.getOffice()!=null && contentBean.getOffice().getName()!=null ){
                                    linkmanBean.setOffice(contentBean.getOffice().getName());
                                }
                                if(contentBean.getDuty()!=null){
                                    linkmanBean.setDuty(contentBean.getDuty().toString());
                                }
                                if(contentBean.getMember()!=null){
                                    linkmanBean.setOrg(contentBean.getMember().getOrgName());
                                }
                            }
                            OnitView();

                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
//                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                        OnitView();
                    }
                });
    }


    public void setTitle(){
        mTitle = "联系人详情";
        setTitle(mTitle);
    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserEvent(JiuyiEventBusEvent event) {
        if (event == null)
            return;

        switch (event.getEventType()){
            default:
                super.onUserEvent(event);
                break;
        }
    }
    public void createReq(final boolean IsBg) {
        getContactInfo(linkmanBeanid);
    }
}
