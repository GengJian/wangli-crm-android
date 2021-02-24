package customer.activity;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;

import customer.entity.CustomerReplyBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewConsultationActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 新建咨询
 *****************************************************************
 */
public class JiuyiCustomerNewReplyActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,tv_title;
    private JiuyiButton mbtnsave;
    private JiuyiEditTextField edt_content;
    private String customername;
    private long customerid=-1;
    private CustomerReplyBean customerReplyBean;
    private String sTitle="";
    private long beanID=0;
    private String sFeild="";

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newrepay_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        beanID=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        sFeild=mBundle.getString(JiuyiBundleKey.PARAM_FEILD);
//        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        customerReplyBean=new CustomerReplyBean();

        if(!Func.IsStringEmpty(sFeild)){
            customerReplyBean.setField(sFeild);
        }

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        tv_title= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_title"));
        if(!Func.IsStringEmpty(sTitle)){
            tv_title.setText(sTitle);
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
                    customerReplyBean.setId(beanID);
                    customerReplyBean.setValue(edt_content.getOriginText().toString());
                    submit();

                }
            });
        }
        edt_content = (JiuyiEditTextField) mBodyLayout.findViewById(Res.getViewID(null, "edt_content"));

    }


    public void setTitle(){
        mTitle = "新建回复";
        setTitle(mTitle);
    }
    public void submit(){
        JiuyiHttp.PUT("pain-point/updateMessageBean")
                .setJson(GsonUtil.gson().toJson(customerReplyBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            startDialog(DialogID.DialogTrue, "", "回复成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }
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
            Rc.mBackfresh=true;
            backPage();
        }
    }
    public boolean check(){
        if(TextUtils.isEmpty(edt_content.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入回复！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }

        return true;
    }
}
