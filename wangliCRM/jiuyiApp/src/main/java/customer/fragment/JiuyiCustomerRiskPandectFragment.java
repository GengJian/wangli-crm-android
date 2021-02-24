package customer.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFragmentBase;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;


import customer.adapter.RiskWarnAdapter;
import customer.entity.RiskWarnBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerRiskPandectFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 风险预警总览
 *****************************************************************
 */


public class JiuyiCustomerRiskPandectFragment extends JiuyiFragmentBase {
    private long Customerid=-1;
    private RecyclerView rv_riskwarn;
    private RiskWarnAdapter riskWarnAdapter;
    ImageButton mibadd;
    private String Customername="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerriskpandectfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    public void onInit() {
        rv_riskwarn= (RecyclerView) mRootView.findViewById(R.id.rv_riskwarn);

        GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(), 3);
        rv_riskwarn.setLayoutManager(mgr);
        rv_riskwarn.setHasFixedSize(true);
        rv_riskwarn.setItemAnimator(new DefaultItemAnimator());

        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        getRiskWarnList();
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getRiskWarnList();
                refreshlayout.finishRefresh(2000);
            }
        });
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newrisk);
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newrisk,true);
                }
            });
        }
    }


    private void getRiskWarnList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("risk-warn/statistics/"+Customerid+"?fromClientType=android")
                        .tag("request_get_member")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<RiskWarnBean>() {
                            @Override
                            public void onSuccess(final RiskWarnBean data) {
                                if(data.getContent()!=null &&data.getContent().size()>0){
                                    final List<RiskWarnBean.ContentBean> contentBeansList=data.getContent();
                                    riskWarnAdapter = new RiskWarnAdapter(JiuyiMainApplication.getIns(), data.getContent());
                                    rv_riskwarn.setAdapter(riskWarnAdapter);
                                    riskWarnAdapter.setOnRecyclerViewItemListener(new RiskWarnAdapter.OnRecyclerViewItemListener(){
                                        @Override
                                        public void onItemClickListener(View view, int position) {
                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Sales);
                                            mBundle.putInt(JiuyiBundleKey.PARAM_TRADEPAGETYPE,Pub.Customer_Sales);
                                            if(contentBeansList.get(position).getFieldValue().equals("所有")){
                                                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE,"all");
                                                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERRISKTYPENAME,"所有");
                                            }else{
                                                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE,contentBeansList.get(position).getField()+"");
                                                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERRISKTYPENAME,contentBeansList.get(position).getFieldValue()+"");
                                            }
                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_Sales,false);
                                        }

                                        @Override
                                        public void onItemLongClickListener(View view, int position) {

                                        }
                                    });
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
    public static JiuyiCustomerRiskPandectFragment newInstance(int nPageType) {
        JiuyiCustomerRiskPandectFragment f = new JiuyiCustomerRiskPandectFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerRiskPandectFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerRiskPandectFragment f = new JiuyiCustomerRiskPandectFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
}
