package customer.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDialog;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.QueryConditionBean;
import customer.entity.InformationBean;
import customer.entity.UpdateAssistantBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerSystemInfoFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户360系统信息
 *****************************************************************
 */
public class JiuyiCustomerSystemInfoFragment extends JiuyiFragmentBase {
    private long Customerid=-1;
    private TextView tvSapaccount;
    private TextView tvCreator;
    private TextView tvCreatordate;
    private TextView tvLastmodifyman;
    private TextView tvModifydate;
    private TextView tvAssistant;
    List<InformationBean.MemberAssistSetBean> operators;
    private List<NormalOperatorBean.ContentBean> assistantList =new ArrayList<>();
    private long assistantID;
    private String[] assistantData;
    private ArrayList<UpdateAssistantBean> assistantSelectList;
    private LoadingDialog dialog1,dialog2;
    private LinearLayout ll_content;
    private TextView tv_no_authoritytext;
    private ImageView iv_no_authority;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customersysteminfofragment_layout"), null);
            onInit();
        } else {
            checkRootViewParent();
        }
        return mRootView;
    }
    /**
     * 初始化
     */
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        ll_content=(LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_content"));
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        tvSapaccount = (TextView) mRootView.findViewById(R.id.tv_sapaccount);
        tvCreator = (TextView) mRootView.findViewById(R.id.tv_creator);
        tvCreatordate = (TextView) mRootView.findViewById(R.id.tv_creatordate);
        tvLastmodifyman = (TextView) mRootView.findViewById(R.id.tv_lastmodifyman);
        tvModifydate = (TextView) mRootView.findViewById(R.id.tv_modifydate);
        tvAssistant = (TextView) mRootView.findViewById(R.id.tv_assistant);
        tvAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_AssistantList,true);
            }
        });
        getBaseInfoList();


    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerSystemInfoFragment newInstance(int nPageType) {
        JiuyiCustomerSystemInfoFragment f = new JiuyiCustomerSystemInfoFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerSystemInfoFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerSystemInfoFragment f = new JiuyiCustomerSystemInfoFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    private void getBaseInfoList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("member/find_system_information/"+Customerid)
                        .tag("find_system_information")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<InformationBean>() {
                            @Override
                            public void onSuccess(InformationBean data) {
                                JiuyiLog.e("getsig","request onSuccess!");
                                if(data!=null){
                                    if(data.getSapNumber()!=null){
                                        tvSapaccount.setText(data.getSapNumber());
                                    }
                                    if(data.getCreateOperator()!=null){
                                        tvCreator.setText(data.getCreateOperator());
                                    }
                                    if(data.getCreateDate()!=null){
                                        tvCreatordate.setText(data.getCreateDate());
                                    }
                                    if(data.getLastModify()!=null){
                                        tvLastmodifyman.setText(data.getLastModify().toString());
                                    }
                                    if(data.getModifyDate()!=null){
                                        tvModifydate.setText(data.getModifyDate());
                                    }

                                    if(data.getAssistNumber()>=0 && (data!=null && data.getMemberAssistSet().size()>0) ){
                                        String sLists="";
                                        operators=data.getMemberAssistSet();
                                        for(int i=0;i<operators.size();i++){
                                            InformationBean.MemberAssistSetBean operatorBean=operators.get(i);
                                            if(operatorBean!=null && operatorBean.getOperator()!=null && operatorBean.getOperator().getName()!=null){
                                                sLists+=operatorBean.getOperator().getName()+",";
                                            }

                                        }
                                        if(!Func.IsStringEmpty(sLists)){
                                            tvAssistant.setText(sLists.substring(0,sLists.length()-1));
                                        }
//                                        tvAssistant.setText(data.getAssistNumber()+"");
                                    }

                                }
                                showProcessBar(100);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                showProcessBar(100);
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });
            }
        };
        thread.start();
    }


    public void submit(){
        String sjson="[\n" +                "   \n" +                "]";
        if(assistantSelectList!=null && assistantSelectList.size()>0){
            sjson=GsonUtil.gson().toJson(assistantSelectList);
        }

        JiuyiHttp.POST("member/change_assist/"+Customerid)
                .setJson(sjson)
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        dialog2.dismiss();
                        Toast.makeText(getCallBack().getActivity(), "更新成功！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        dialog2.dismiss();
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh){
            Rc.mBackfresh=false;
            getBaseInfoList();
        }

    }
}
