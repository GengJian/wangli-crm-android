package customer.fragment;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;

import java.util.ArrayList;
import java.util.List;

import customer.Utils.ViewOperatorType;
import customer.adapter.AcountinfoAdapter;
import customer.entity.MemberAuthorityBean;
import customer.entity.MemberReadBean;
import customer.view.jiuyiRecycleViewDivider;
/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerBaseInfoFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户基本信息
 *****************************************************************
 */

public class JiuyiCustomerBaseInfoFragment extends JiuyiFragmentBase {
    TextView tv_accountinfo,tv_businessinfo;
    RecyclerView rv_account,rv_businessinfo;
    private List<MemberReadBean.ContentBean.DataBean> beanList,businessinfoList;
    private AcountinfoAdapter acountinfoAdapter,businessinfoAdapter;
    private long Customerid=-1;
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
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerbaseinfofragment_layout"), null);
            setOnRefreshListener();
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
        beanList=new ArrayList<>();
        businessinfoList=new ArrayList<>();
        ll_content=(LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_content"));
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        tv_accountinfo=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_accountinfo"));
        rv_account=(RecyclerView)mRootView.findViewById(Res.getViewID(getContext(), "rv_account"));
        rv_account.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity()));
        rv_account.setHasFixedSize(true);
        rv_account.setItemAnimator(new DefaultItemAnimator());
        rv_account.addItemDecoration(new jiuyiRecycleViewDivider(getCallBack().getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rv_businessinfo=(RecyclerView)mRootView.findViewById(Res.getViewID(getContext(), "rv_businessinfo"));
        rv_businessinfo.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity()));
        rv_businessinfo.setHasFixedSize(true);
        rv_businessinfo.setItemAnimator(new DefaultItemAnimator());
        rv_businessinfo.addItemDecoration(new jiuyiRecycleViewDivider(getCallBack().getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));
//        if(!MemberAuthorityBean.getIns().base){
//            tv_no_authoritytext.setVisibility(View.VISIBLE);
//            iv_no_authority.setVisibility(View.VISIBLE);
//            ll_content.setVisibility(View.GONE);
//        }else {
//            showProcessBar(0);
//            getBaseInfoList();
//        }
        showProcessBar(0);
        getBaseInfoList();

    }

    private void getBaseInfoList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("member/find-read-bean/"+Customerid)
                        .tag("request_get_member")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<MemberReadBean>() {
                            @Override
                            public void onSuccess(MemberReadBean data) {
                                JiuyiLog.e("getsig","request onSuccess!");
                                List<MemberReadBean.ContentBean>  contentBeanList=data.getContent();
                                if(contentBeanList!=null &&contentBeanList.size()>0){
                                    for(MemberReadBean.ContentBean contentBean :contentBeanList){
                                        if(contentBean.getTitle().equals("账户信息")){
                                            beanList=contentBean.getData();
                                            acountinfoAdapter = new AcountinfoAdapter(R.layout.customer_baseinfo_item, beanList);
                                            acountinfoAdapter.setOnCLickItemListener(new AcountinfoAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(colname.equals("rightContent")){
                                                        if(beanList!=null && beanList.size()>0 && beanList.get(position).isChange()){
                                                            mBundle.putParcelable(JiuyiBundleKey.PARAM_CUSTOMERCOLBEAN, beanList.get(position));
                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.CustomerupdatecolInfo);
                                                            changePage(mBundle, Pub.CustomerupdatecolInfo,true);
                                                        }

                                                    }
                                                }
                                            });
                                            rv_account.setAdapter(acountinfoAdapter);
                                        }else if(contentBean.getTitle().equals("业务信息")){
                                            businessinfoList=contentBean.getData();
                                            businessinfoAdapter = new AcountinfoAdapter(R.layout.customer_baseinfo_item, businessinfoList);
                                            rv_businessinfo.setAdapter(businessinfoAdapter);
                                            businessinfoAdapter.setOnCLickItemListener(new AcountinfoAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(businessinfoList!=null && businessinfoList.size()>0 && businessinfoList.get(position).isChange()){
                                                        mBundle.putParcelable(JiuyiBundleKey.PARAM_CUSTOMERCOLBEAN, businessinfoList.get(position));
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.CustomerupdatecolInfo);
                                                        changePage(mBundle, Pub.CustomerupdatecolInfo,true);
                                                    }
                                                }
                                            });

                                        }
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


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerBaseInfoFragment newInstance(int nPageType) {
        JiuyiCustomerBaseInfoFragment f = new JiuyiCustomerBaseInfoFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerBaseInfoFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerBaseInfoFragment f = new JiuyiCustomerBaseInfoFragment();
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
            getBaseInfoList();
        }

    }
    @Override
    public void createReq(boolean IsBg) {
        getBaseInfoList();
    }

}
