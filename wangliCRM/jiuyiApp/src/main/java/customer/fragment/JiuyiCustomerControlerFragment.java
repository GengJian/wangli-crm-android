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
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFragmentBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import customer.Utils.ViewOperatorType;
import customer.adapter.AcountinfoAdapter;
import customer.entity.MemberAuthorityBean;
import customer.entity.MemberReadBean;
import customer.view.jiuyiRecycleViewDivider;
/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerControlerFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户实控人信息
 *****************************************************************
 */

public class JiuyiCustomerControlerFragment extends JiuyiFragmentBase {
    RecyclerView rv_account,rv_businessinfo;
    private List<MemberReadBean.ContentBean.DataBean> beanList,otherinfoList;
    private AcountinfoAdapter controlerinfoAdapter,otherinfoAdapter;
    private long Customerid=-1;
    private String controlid="";
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
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customercontrolerfragment_layout"), null);
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
        otherinfoList=new ArrayList<>();
        ll_content=(LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_content"));
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
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

//        if(!MemberAuthorityBean.getIns().actrol){
//            tv_no_authoritytext.setVisibility(View.VISIBLE);
//            iv_no_authority.setVisibility(View.VISIBLE);
//            ll_content.setVisibility(View.GONE);
//        }else {
//            showProcessBar(0);
//            getBaseInfoList();
//        }
    }
    private void getBaseInfoList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("actual/find_read_bean/"+Customerid)
                        .tag("request_find_read_bean")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<MemberReadBean>() {
                            @Override
                            public void onSuccess(MemberReadBean data) {
                                List<MemberReadBean.ContentBean>  contentBeanList=data.getContent();
                                if(contentBeanList!=null &&contentBeanList.size()>0){
                                    for(MemberReadBean.ContentBean contentBean :contentBeanList){
                                        if(contentBean.getTitle().equals("个人信息")){
                                            beanList=contentBean.getData();
                                            if(beanList!=null && beanList.size()>0){
                                                for(int i=0;i<beanList.size();i++){
                                                    MemberReadBean.ContentBean.DataBean dataBean=beanList.get(i);
                                                    if(dataBean.getField().equals("id")){
                                                        controlid=dataBean.getRightContent();
                                                        beanList.remove(dataBean);
                                                    }
                                                }
                                            }
                                            controlerinfoAdapter = new AcountinfoAdapter(R.layout.customer_baseinfo_item, beanList);
                                            controlerinfoAdapter.setOnCLickItemListener(new AcountinfoAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(beanList!=null && beanList.size()>0 && beanList.get(position).isChange()){
                                                        mBundle.putParcelable(JiuyiBundleKey.PARAM_CUSTOMERCOLBEAN, beanList.get(position));
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Long.parseLong(controlid));
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.CustomerupdatecolInfo);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_UPDATECONTROL,"1");
                                                        changePage(mBundle, Pub.CustomerupdatecolInfo,true);
                                                    }
                                                }
                                            });
                                            rv_account.setAdapter(controlerinfoAdapter);
                                        }else if(contentBean.getTitle().equals("履历及其它")){
                                            otherinfoList=contentBean.getData();
                                            otherinfoAdapter = new AcountinfoAdapter(R.layout.customer_otherinfo_item, otherinfoList);
                                            rv_businessinfo.setAdapter(otherinfoAdapter);
                                            otherinfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    if(otherinfoList!=null && otherinfoList.size()>0 && otherinfoList.get(position).isChange()){
                                                        mBundle.putParcelable(JiuyiBundleKey.PARAM_CUSTOMERCOLBEAN, otherinfoList.get(position));
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Long.parseLong(controlid));
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.CustomerupdatecolInfo);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_UPDATECONTROL,"1");
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
    public static JiuyiCustomerControlerFragment newInstance(int nPageType) {
        JiuyiCustomerControlerFragment f = new JiuyiCustomerControlerFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerControlerFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerControlerFragment f = new JiuyiCustomerControlerFragment();
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
