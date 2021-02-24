package customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;

import customer.activity.JiuyiCommonInputInfoActivity;
import customer.activity.JiuyiCommunicationRecordNewActivity;
import customer.activity.JiuyiCustomerInfomationActivity;
import customer.activity.JiuyiOrgDeptSelectActivity;
import customer.entity.MemberReadBean;
import customer.entity.VisitActivityListBean;
import customer.view.AlxUrlTextView;
import customer.view.DynamicUrlTextView;
import dynamic.Utils.StringUtils;
import mine.activity.JiuyiMineSignActivity;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerControlerFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户实控人信息
 *****************************************************************
 */

public class JiuyiCustomerVistProcessFragment extends JiuyiFragmentBase {
    private long Customerid=-1;
    private TextView subtitle,subtitle3;
    private TextView subtitle2;
    private ImageView ivSign,ivTalk,ivReport;
    private VisitActivityListBean visitActivityListBean;
    private String signLocation="",talkContent="",visitReport="";
    private String originTalkContent="";
    private LinearLayout ll_title,ll_title2,ll_title3;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customervisitprocessfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        visitActivityListBean=mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        subtitle = (TextView) mRootView.findViewById(R.id.subtitle);
        if(visitActivityListBean!=null && visitActivityListBean.getSignInInfo()!=null){
            subtitle.setText(visitActivityListBean.getSignInInfo());
        }
        subtitle2 = (TextView) mRootView.findViewById(R.id.subtitle2);
        if(visitActivityListBean!=null && visitActivityListBean.getCommunicateRecord()!=null){
            SpannableString dynamicContent = StringUtils.getDynamicContent(JiuyiMainApplication.getIns(), subtitle2, visitActivityListBean.getCommunicateRecord());
            originTalkContent=visitActivityListBean.getCommunicateRecord();
            subtitle2.setText(dynamicContent);
//            subtitle2.setText(visitActivityListBean.getCommunicateRecord());
        }
        subtitle3 = (TextView) mRootView.findViewById(R.id.subtitle3);
        if(visitActivityListBean!=null && visitActivityListBean.getVisitReport()!=null){
            subtitle3.setText(visitActivityListBean.getVisitReport());
        }
        ivSign = (ImageView) mRootView.findViewById(R.id.iv_sign);

//        ivSign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiMineSignActivity.class);
//                intent.putExtra(JiuyiBundleKey.PARAM_TITLE,"签到");
//                intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN,visitActivityListBean);
//                startActivityForResult(intent, 100);
//            }
//        });
        ll_title = (LinearLayout) mRootView.findViewById(R.id.ll_title);
        ll_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visitActivityListBean.getStatus()!=null && visitActivityListBean.getStatus().equals("end") ){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "已完成拜访，不允许修改！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(visitActivityListBean.getVisitor()!=null && visitActivityListBean.getVisitor().getId()!=Rc.id){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "只能修改自己创建的拜访记录！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiMineSignActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_TITLE,"签到");
                intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN,visitActivityListBean);
                startActivityForResult(intent, 100);
            }
        });
        ll_title2 = (LinearLayout) mRootView.findViewById(R.id.ll_title2);
        ll_title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visitActivityListBean.getStatus()!=null && visitActivityListBean.getStatus().equals("end") ){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "已完成拜访，不允许修改！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(visitActivityListBean.getVisitor()!=null && visitActivityListBean.getVisitor().getId()!=Rc.id){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "只能修改自己创建的拜访记录！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCommunicationRecordNewActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_TITLE,"新建沟通记录");
                intent.putExtra(JiuyiBundleKey.PARAM_COMMONNAME,originTalkContent);
                intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN,visitActivityListBean);
                startActivityForResult(intent, 200);
            }
        });
        ll_title3 = (LinearLayout) mRootView.findViewById(R.id.ll_title3);
        ll_title3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visitActivityListBean.getStatus()!=null && visitActivityListBean.getStatus().equals("end") ){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "已完成拜访，不允许修改！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(visitActivityListBean.getVisitor()!=null && visitActivityListBean.getVisitor().getId()!=Rc.id){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "只能修改自己创建的拜访记录！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCommonInputInfoActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_TITLE,"拜访报告");
                intent.putExtra(JiuyiBundleKey.PARAM_CUSTOMERID,visitActivityListBean.getId());
                intent.putExtra(JiuyiBundleKey.PARAM_COMMONKEY,"visitReport");
                intent.putExtra(JiuyiBundleKey.PARAM_COMMONNAME,subtitle3.getText().toString());
                startActivityForResult(intent, 300);
            }
        });
        ll_title3 = (LinearLayout) mRootView.findViewById(R.id.ll_title3);
        ivTalk = (ImageView) mRootView.findViewById(R.id.iv_talk);
//        ivTalk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCommunicationRecordNewActivity.class);
//                intent.putExtra(JiuyiBundleKey.PARAM_TITLE,"新建沟通记录");
//                intent.putExtra(JiuyiBundleKey.PARAM_COMMONNAME,subtitle2.getText().toString());
//                intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN,visitActivityListBean);
//                startActivityForResult(intent, 200);
//            }
//        });
        ivReport = (ImageView) mRootView.findViewById(R.id.iv_report);
//        ivReport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCommonInputInfoActivity.class);
//                intent.putExtra(JiuyiBundleKey.PARAM_TITLE,"拜访报告");
//                intent.putExtra(JiuyiBundleKey.PARAM_CUSTOMERID,visitActivityListBean.getId());
//                intent.putExtra(JiuyiBundleKey.PARAM_COMMONKEY,"visitReport");
//                intent.putExtra(JiuyiBundleKey.PARAM_COMMONNAME,subtitle3.getText().toString());
//                startActivityForResult(intent, 300);
//            }
//        });
    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerVistProcessFragment newInstance(int nPageType) {
        JiuyiCustomerVistProcessFragment f = new JiuyiCustomerVistProcessFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerVistProcessFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerVistProcessFragment f = new JiuyiCustomerVistProcessFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh){
            Rc.mBackfresh=false;
        }

    }
    @Override
    public void createReq(boolean IsBg) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 100:
                if(intent!=null){
                    Bundle bundle = intent.getExtras();
                    signLocation=bundle.getString(JiuyiBundleKey.PARAM_COMMONNAME);
                    if(signLocation!=null){
                        subtitle.setText(signLocation);
                    }

                }
                break;
            case 200:
                if(intent!=null){
                    Bundle bundle = intent.getExtras();
                    talkContent=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE);
                    if(talkContent!=null){
                        SpannableString dynamicContent = StringUtils.getDynamicContent(JiuyiMainApplication.getIns(), subtitle2, talkContent);
                        originTalkContent=talkContent;
                        subtitle2.setText(dynamicContent);
//                        subtitle2.setText(talkContent);
                    }
                }
                break;
            case 300:
                if(intent!=null){
                    Bundle bundle = intent.getExtras();
                    visitReport=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE);
                    if(visitReport!=null){
                        subtitle3.setText(visitReport);
                    }

                }
                break;


            default:
                break;
        }
    }
}
