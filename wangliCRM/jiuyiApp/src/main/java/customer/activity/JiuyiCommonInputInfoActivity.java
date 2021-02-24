package customer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;

import java.util.HashMap;

import customer.Utils.ViewOperatorType;
import customer.entity.MemberUpdateBean;
import customer.entity.VisitActivityListBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCommonInputInfoActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 录入界面
 *****************************************************************
 */
public class JiuyiCommonInputInfoActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiEditTextField content_edt;
    private String operatorType,sTitle,s_returnvalue;
    private  long customerid=-1;
    private ImageView   iv_back;
    private String content="";
    private String colKey="";

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_commoninputinfo_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        colKey=mBundle.getString(JiuyiBundleKey.PARAM_COMMONKEY);
        content=mBundle.getString(JiuyiBundleKey.PARAM_COMMONNAME);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        operatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }
        if(Func.IsStringEmpty(operatorType)){
            operatorType="";
        }

        setTitle();
        content_edt = (JiuyiEditTextField) mBodyLayout.findViewById(Res.getViewID(null, "content_edt"));
        if(content!=null){
            content_edt.setText(content);
        }
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
                    if(operatorType.equals(ViewOperatorType.EDIT)){
//                        UpdateMemberInfo();
                    }else if(operatorType.equals(ViewOperatorType.ADD)){

                        setBackActivityBundle();
                        backPage();
                    }else {
                        updateInfo();
                        setBackActivityBundle();
                        backPage();
                    }


                }
            });

        }

    }

    @Override
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE,content_edt.getText().toString());
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiCommonInputInfoActivity.this.setResult(300, intent);
    }

    public void updateInfo(){
        HashMap beanMap= new HashMap<String, Object>();
        beanMap.put("id",customerid);
        beanMap.put(colKey,content_edt.getText().toString());

        JiuyiHttp.PUT("visit-activity/update-mobile")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(beanMap))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        setBackActivityBundle();
                        backPage();
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        backPage();
//                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
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
            mTitle = "输入信息";
        }
        setTitle(mTitle);
    }
}
