package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanglicrm.android.R;
import com.common.GsonUtil;
import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiBigTextGroup;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import customer.Utils.ViewOperatorType;
import customer.entity.CustomerAssistantBean;
import customer.entity.CustomerAssistantCreatBean;
import customer.entity.MemberBeanID;
import customer.entity.PersonSelectBean;
import customer.entity.UpdateAssistantBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCommonInputInfoActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 录入界面
 *****************************************************************
 */
public class JiuyiAssistantNewActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiEditTextField content_edt;
    private String operatorType,sTitle,s_returnvalue;
    private  long customerid=-1;
    private ImageView   iv_back;
    private JiuyiItemGroup jigClient;
    private JiuyiItemGroup jig_assistant,jig_role;
    private JiuyiBigTextGroup jig_reason;

    private CustomerAssistantBean contentBean;
    private long customerId=-1;
    private static final int ASSISTANT_CODE = 900;
    private List<PersonSelectBean> mAssistantBeanSelectList;
    private ArrayList<UpdateAssistantBean> assistantSelectList;
    private CustomerAssistantCreatBean customerAssistantCreatBean;


    @Override
    public void onInit() {
        customerId=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newassistant_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
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
            customerAssistantCreatBean=new  CustomerAssistantCreatBean();
        }

        setTitle();
        if(customerid>0 && operatorType.equals(ViewOperatorType.ADD) ){
            MemberBeanID memberBeanID=new MemberBeanID();
            memberBeanID.setId(customerid);
            customerAssistantCreatBean.setMember(memberBeanID);
        }

        jig_assistant = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_assistant);
        jig_assistant.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                if(mAssistantBeanSelectList!=null && mAssistantBeanSelectList.size()>0){
                    mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mAssistantBeanSelectList);
                }
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Person");
                intent.putExtras(mBundle);
                startActivityForResult(intent, ASSISTANT_CODE);

            }
        });
        jig_role = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_role);
        jig_role.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiAssistantNewActivity.this);
                buidler.setTitle("协助角色");
                String[] data = new String[]{"AR","FR","SR","销售支持"};
                buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jig_role.setText(data[which].trim());
                        dialog.dismiss();
                    }
                });
                buidler.show();

            }
        });
        jig_reason = (JiuyiBigTextGroup) mBodyLayout.findViewById(R.id.jig_reason);





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
        if(operatorType.equals(ViewOperatorType.EDIT)){
            if(contentBean!=null){
                if(contentBean.getOperator()!=null && contentBean.getOperator().getName()!=null){
                    jig_assistant.setText(contentBean.getOperator().getName());
                    jig_assistant.setItemOnClickListener(null);
                }
                if(contentBean.getAssistRole()!=null){
                    jig_role.setText(contentBean.getAssistRole());
                    jig_role.setItemOnClickListener(null);
                }
                if(contentBean.getAssistReason()!=null){
                    jig_reason.setText(contentBean.getAssistReason());
                }
            }
            mtvcomplete.setVisibility(View.INVISIBLE);
        }

    }
    public boolean check(){
        if(TextUtils.isEmpty(jig_assistant.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择协助人！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(jig_role.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择角色！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(jig_reason.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入原因！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;
    }

    public void createBean(){
        customerAssistantCreatBean.setAssistRole(jig_role.getText().toString());
        customerAssistantCreatBean.setAssistReason(jig_reason.getText().toString());
        if(assistantSelectList!=null && assistantSelectList.size()>0){
            customerAssistantCreatBean.setOperators(assistantSelectList);
        }
        submit();
    }
    public void submit(){
        JiuyiHttp.POST("member-assist/create-app")
                .setJson(GsonUtil.gson().toJson(customerAssistantCreatBean))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
                        Toast.makeText(JiuyiAssistantNewActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        backPage();

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
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
            mTitle = "新增协助人";
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
            case ASSISTANT_CODE:
            bundle = data.getExtras();
            if (mAssistantBeanSelectList == null) {
                mAssistantBeanSelectList = new ArrayList<>();
            }

            mAssistantBeanSelectList = bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
            if (mAssistantBeanSelectList != null && mAssistantBeanSelectList.size() > 0) {
                String selectNames = "";
                assistantSelectList = new ArrayList<>();
                for (int i = 0; i < mAssistantBeanSelectList.size(); i++) {
                    selectNames += mAssistantBeanSelectList.get(i).getName() + "，";
                    UpdateAssistantBean updateAssistantBean = new UpdateAssistantBean();
                    updateAssistantBean.setId(mAssistantBeanSelectList.get(i).getId());
                    assistantSelectList.add(updateAssistantBean);
                }
                if (!Func.IsStringEmpty(selectNames)) {
                    jig_assistant.setText(selectNames.substring(0, selectNames.length() - 1));
                }
            } else {
                assistantSelectList = new ArrayList<>();
                jig_assistant.setText("");
            }
            break;
            default:
                break;
        }
    }

}
