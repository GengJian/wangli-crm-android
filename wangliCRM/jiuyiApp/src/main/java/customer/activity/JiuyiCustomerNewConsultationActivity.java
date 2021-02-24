package customer.activity;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.jiuyi.app.JiuyiActivityBase;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewConsultationActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 新建咨询
 *****************************************************************
 */
public class JiuyiCustomerNewConsultationActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,tv_day,tv_newincharge,tv_name;
    private JiuyiButton mbtnsave;
    private  JiuyiEditText et_man;
    private JiuyiEditTextField edt_content;
    private String customername;
    private long customerid=-1;

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newconsultation_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);

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

                    submit();

                }
            });
        }
        et_man = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_man"));
        edt_content = (JiuyiEditTextField) mBodyLayout.findViewById(Res.getViewID(null, "edt_content"));

    }


    public void setTitle(){
        mTitle = "新建咨询";
        setTitle(mTitle);
    }
    public void submit(){
        JiuyiHttp.POST("member_process/claim")
//                .setJson(GsonUtil.gson().toJson(memberClaimBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
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
            startDialog(DialogID.DialogDoNothing, "", "请输入问题！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_man.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入要咨询的客户/部门！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }

        return true;
    }
}
