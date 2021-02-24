package customer.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import android.widget.Toast;


import com.common.GsonUtil;
import com.control.utils.DialogID;

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
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import java.util.ArrayList;

import java.util.List;
import commonlyused.bean.QueryConditionBean;

import customer.adapter.ConsultationAdapter;
import customer.adapter.TradedeliveryAdapter;
import customer.entity.TradedeliveryBean;
import customer.view.MultiItemDivider;
import dynamic.entity.DyActivityBean;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCostListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 费用列表界面
 *****************************************************************
 */
public class JiuyiCostListFragment extends JiuyiFragmentBase {


    private  int pageIndex=1,pagesize=20,totalPage=0;
    ImageButton mibadd;
    RecyclerView rvInfoList;
    private List<DyActivityBean.ContentBean> mTradedeliveryBeanList;
    private ConsultationAdapter mTradedeliveryAdapter;
    QueryConditionBean queryConditionBean;
    RefreshLayout refreshLayout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_costlistfragment_layout"), null);
        onInit();
        return mRootView;
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCostListFragment newInstance(int nPageType) {
        JiuyiCostListFragment f = new JiuyiCostListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCostListFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCostListFragment f = new JiuyiCostListFragment();
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
    public void onInit() {
        rvInfoList=(RecyclerView)mRootView.findViewById(R.id.rv_infolist);
        rvInfoList.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        //分割线
        MultiItemDivider itemDivider = new MultiItemDivider(JiuyiMainApplication.getIns(), MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
        rvInfoList.addItemDecoration(itemDivider);
//        rvInfoList.setItemAnimator(new DefaultItemAnimator());
//        rvInfoList.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

        rvInfoList.setNestedScrollingEnabled(false);
        rvInfoList.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        rvInfoList.setFocusable(false);
        getInfoList(0);
        TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout)mRootView.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex=1;
                        getInfoList(0);
                        refreshLayout.finishRefreshing();
                    }
                },2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(pageIndex>=totalPage){
                            Toast.makeText(JiuyiMainApplication.getIns(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }else{
                            getInfoList(pageIndex);
                            pageIndex++;

                        }
                        refreshLayout.finishLoadmore();


                    }
                },2000);
            }
        });


        showProcessBar(0);

//        ImageButton mibadd;
//        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
//        if(mibadd!=null){
//            mibadd.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    changePage(null, Pub.MSG_PopStart,true);
//                }
//            });
//        }
    }

    public  void getInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("market-activity/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<DyActivityBean>() {
                            @Override
                            public void onSuccess(DyActivityBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTradedeliveryBeanList=data.getContent();
                                    if(page==0){
                                        mTradedeliveryAdapter = new ConsultationAdapter(R.layout.jiuyi_customer_cost_item, mTradedeliveryBeanList);
                                        rvInfoList.setAdapter(mTradedeliveryAdapter);
//                                            mTradedeliveryAdapter.setOnCLickItemListener(new TradedeliveryAdapter.onCLickItemListener() {
//                                                @Override
//                                                public void click(int position, String colname, View view) {
//                                                   /* if(colname.equals("tv_deliveryno")||colname.equals("tv_operation")){
//                                                        TradedeliveryBean.ContentBean contentBean=mTradedeliveryAdapter.getData().get(position);
//                                                        if(contentBean!=null){
//                                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
//                                                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
//                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradeDetail);
//                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
//                                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
//                                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "Tradedelivery");
//                                                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "发货信息");
//                                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradeDetail,true);
//                                                        }
//
//                                                    }*/
//                                                }
//                                            });
                                        if(mTradedeliveryBeanList==null||mTradedeliveryBeanList.size()==0){
                                            mTradedeliveryAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        mTradedeliveryAdapter.add(mTradedeliveryBeanList);
                                        showProcessBar(100);
                                    }
                                }
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
    public  QueryConditionBean  builderCondition(int page){
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;


        if(getPageType()!=Pub.Customer_CostAll){
            String status="";
            switch (mPageType) {
                case Pub.Customer_CostOaApping:
                    status="PRICE_REVIEW";
                    break;
                case Pub.Customer_CostOaApped:
                    status="DELIVERYED";
                    break;

            }
//            if(!Func.IsStringEmpty(status)){
//                List<String> value2 = new ArrayList<String>();
//                QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
//                rulesBean2.setField("status");
//                rulesBean2.setOption("EQ");
//                value2.add(status);
//                rulesBean2.setValues(value2);
//                rulesBeanList.add(rulesBean2);
//            }
        }
        queryConditionBean.setRules(rulesBeanList);
        return queryConditionBean;
    }


}
