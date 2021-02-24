package customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;

import customer.activity.JiuyiCommonInputInfoActivity;
import customer.entity.MemberReadBean;
import customer.entity.VisitActivityListBean;
import customer.view.JiuyiAttachment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerVisitRemarkFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 拜访备注
 *****************************************************************
 */

public class JiuyiCustomerVisitRemarkFragment extends JiuyiFragmentBase {
    private long Customerid=-1;
    private TextView tvTitle,tvRemark;
    private ImageView iv_new;
    private CardView cvRemark,cvAttach;
    private JiuyiAttachment acAttach;
    private String sTitle="";
    private VisitActivityListBean visitActivityListBean;
    private String sRemark="";
    private LinearLayout ll_title;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customervisitremarkfragment_layout"), null);
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
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        if(!Func.IsStringEmpty(sTitle)){
            tvTitle.setText(sTitle);
        }
        tvRemark = (TextView) mRootView.findViewById(R.id.tv_remark);
        if(visitActivityListBean!=null && visitActivityListBean.getRemark()!=null){
            tvRemark.setText(visitActivityListBean.getRemark());
        }
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
                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCommonInputInfoActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_TITLE,sTitle);
                intent.putExtra(JiuyiBundleKey.PARAM_CUSTOMERID,visitActivityListBean.getId());
                intent.putExtra(JiuyiBundleKey.PARAM_COMMONKEY,"remark");
                intent.putExtra(JiuyiBundleKey.PARAM_COMMONNAME,tvRemark.getText().toString());
                startActivityForResult(intent, 300);
            }
        });
        iv_new = (ImageView) mRootView.findViewById(R.id.iv_new);
//        iv_new.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCommonInputInfoActivity.class);
//                intent.putExtra(JiuyiBundleKey.PARAM_TITLE,sTitle);
//                intent.putExtra(JiuyiBundleKey.PARAM_CUSTOMERID,visitActivityListBean.getId());
//                intent.putExtra(JiuyiBundleKey.PARAM_COMMONKEY,"remark");
//                intent.putExtra(JiuyiBundleKey.PARAM_COMMONNAME,tvRemark.getText().toString());
//                startActivityForResult(intent, 300);
//            }
//        });
        cvRemark= (CardView) mRootView.findViewById(R.id.cv_remark);
        cvAttach= (CardView) mRootView.findViewById(R.id.cv_attach);
        acAttach= (JiuyiAttachment) mRootView.findViewById(R.id.ah_attach);
        if(mPageType!=Pub.Customer_VisitAttach){
            cvRemark.setVisibility(View.VISIBLE);
            cvAttach.setVisibility(View.GONE);
        }else if(mPageType==Pub.Customer_VisitAttach){
            cvRemark.setVisibility(View.GONE);
            cvAttach.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // 刷新数据
//        if(acAttach!=null){
//            acAttach.refreshAttach();
//        }

    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerVisitRemarkFragment newInstance(int nPageType) {
        JiuyiCustomerVisitRemarkFragment f = new JiuyiCustomerVisitRemarkFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerVisitRemarkFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerVisitRemarkFragment f = new JiuyiCustomerVisitRemarkFragment();
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

    }
    @Override
    public void createReq(boolean IsBg) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 300:
                if(intent!=null){
                    Bundle bundle = intent.getExtras();
                    sRemark=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE);
                    if(sRemark!=null){
                        tvRemark.setText(sRemark);
                    }
                }
                break;
            default:
                break;
        }
    }
}
