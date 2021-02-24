package customer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ProductBillType;
import customer.Utils.ViewOperatorType;
import customer.adapter.TradebankstatementAdapter;
import customer.adapter.TradecontactAdapter;
import customer.adapter.TradedeliveryAdapter;
import customer.adapter.TradeinvoiceAdapter;
import customer.adapter.TradelogisticsAdapter;
import customer.adapter.TradeorderAdapter;
import customer.adapter.TradetelemoneyAdapter;
import customer.entity.TradebankstatementBean;
import customer.entity.TradecontactBean;
import customer.entity.TradedeliveryBean;
import customer.entity.TradeinvoiceBean;
import customer.entity.TradelogisticsBean;
import customer.entity.TradeorderBean;
import customer.entity.TradetelemoneyBean;
import customer.view.MultiItemDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerTradeinfoFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户360交易跟踪右边部分内容
 *****************************************************************
 */
public class JiuyiCustomerTradeinfoRightFragment extends JiuyiFragmentBase {
    RecyclerView rv_productlist;
    private List<TradecontactBean.ContentBean> mTradecontactBeanList;
    private TradecontactAdapter mTradecontactAdapter;
    private List<TradeorderBean.ContentBean> mTradeorderBeanList;
    private TradeorderAdapter mTradeorderAdapter;
    private List<TradebankstatementBean.ContentBean> mTradebankstatementBeanList;
    private TradebankstatementAdapter mTradebankstatementAdapter;
    private List<TradedeliveryBean.ContentBean> mTradedeliveryBeanList;
    private TradedeliveryAdapter mTradedeliveryAdapter;
    private List<TradeinvoiceBean.ContentBean> mTradeinvoiceBeanList;
    private TradeinvoiceAdapter mTradeinvoiceAdapter;
    private List<TradelogisticsBean.ContentBean> mTradelogisticsBeanList;
    private TradelogisticsAdapter mTradelogisticsAdapter;
    private List<TradetelemoneyBean.ContentBean> mTradetelemoneyBeanList;
    private TradetelemoneyAdapter mTradetelemoneyAdapter;
    private TextView tv_operation,tv_newrecord,tv_title,tv_producttype,tv_productname,tv_productcomponent;
    private String producttype;
    private  int pageIndex=1,pagesize=20,totalPage=0;
    RefreshLayout refreshLayout;
    private long Customerid=-1;
    private String Customername="";
    QueryConditionBean queryConditionBean;
    List<QueryConditionBean.RulesBean> rulesBeanList;
    private  int curpostion=-1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerproductrightfragment_layout"), null);
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
        //获取上一界面的Bundle，供初始化，滚动广告页等使用
        mBundle = getArguments();
        if(mBundle == null) {
            Intent intent = getActivity().getIntent();
            if(intent != null) {
                mBundle = intent.getExtras();
            }
        }
        if(mBundle != null){
            producttype = mBundle.getString(JiuyiBundleKey.PARAM_PRODUCTTYPE);
            Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
            Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        }else{
            //保存mBundle不等于null，子类就不判断他是否为空了
            mBundle = new Bundle();
        }
        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);

        tv_title=(TextView) mRootView.findViewById(R.id.tv_title);
        tv_producttype=(TextView) mRootView.findViewById(R.id.tv_producttype);
        tv_productname=(TextView) mRootView.findViewById(R.id.tv_productname);
        tv_productcomponent=(TextView) mRootView.findViewById(R.id.tv_productcomponent);

        rv_productlist=(RecyclerView)mRootView.findViewById(R.id.rv_productlist);
        rv_productlist.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        //分割线
        MultiItemDivider itemDivider = new MultiItemDivider(getContext(), MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
        rv_productlist.addItemDecoration(itemDivider);
        tv_title.setText(producttype);
        pageIndex=1;
        if(producttype.equals("商务合同")){
            tv_producttype.setText("合同类型");
            tv_productname.setText("合同日期");
            tv_productcomponent.setText("合同编号");
            mTradecontactBeanList = new ArrayList<>();
            getTradecontactInfoList(0);
        }else if(producttype.equals("销售订单")){
            tv_producttype.setText("订单编号");
            tv_productname.setText("订单日期");
            tv_productcomponent.setText("订单状态");
            mTradeorderBeanList = new ArrayList<>();
            getOrderInfoList(0);
        }else if(producttype.equals("发货")){
            tv_producttype.setText("订单号");
            tv_productname.setText("运单号");
            tv_productcomponent.setText("发货日期");
            mTradedeliveryBeanList = new ArrayList<>();
            getTradedeliveryInfoList(0);
        }else if(producttype.equals("发票")){
            tv_producttype.setText("发票号");
            tv_productname.setText("发票金额");
            tv_productcomponent.setText("状态");
            mTradeinvoiceBeanList = new ArrayList<>();
            getTradeinvoiceInfoList(0);
        }else if(producttype.equals("电汇/承兑")){
            tv_producttype.setText("编号");
            tv_productname.setText("类型");
            tv_productcomponent.setText("状态");
            mTradetelemoneyBeanList = new ArrayList<>();
            getTradetelemoneyInfoList(0);
        }else if(producttype.equals("外贸物流")){
            tv_producttype.setText("跟踪单号");
            tv_productname.setText("合同");
            tv_productcomponent.setText("状态");
            mTradelogisticsBeanList = new ArrayList<>();
            getTradelogisticsInfoList(0);
        }else if(producttype.equals("对账单")){
            tv_producttype.setText("编号");
            tv_productname.setText("月份");
            tv_productcomponent.setText("状态");
            mTradebankstatementBeanList = new ArrayList<>();
            getTradebankstatementInfoList(0);
        }
        tv_newrecord = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_newrecord"));
        if(producttype.equals("商务合同")){
            tv_newrecord.setVisibility(View.VISIBLE);
        }else{
            tv_newrecord.setVisibility(View.GONE);
        }
        if(tv_newrecord!=null){
            tv_newrecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(producttype.equals("商务合同")){
                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                        mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newtradecontact);
                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newtradecontact,true);
                     }

                }
            });
        }
       /* RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                refreshCurrenPage(0);
                refreshlayout.finishRefresh(2000);
                refreshlayout.setLoadmoreFinished(false);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(pageIndex>=totalPage){
                    refreshlayout.finishLoadmore(500);
                    refreshlayout.setLoadmoreFinished(true);
                }else{
                    refreshCurrenPage(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;

            }
        });*/
        showProcessBar(0);
    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerTradeinfoRightFragment newInstance(int nPageType) {
        JiuyiCustomerTradeinfoRightFragment f = new JiuyiCustomerTradeinfoRightFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerTradeinfoRightFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerTradeinfoRightFragment f = new JiuyiCustomerTradeinfoRightFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    public  QueryConditionBean  builderCondition(int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        queryConditionBean.setRules(rulesBeanList);
        return queryConditionBean;
    }

    public void refreshCurrenPage(int page){
        if(ProductBillType.TRADECONTACT.equals(producttype)){
            getTradecontactInfoList(page);
        }else if(ProductBillType.TRADEBANKSTATEMENT.equals(producttype)){
            getTradebankstatementInfoList(page);
        }else if(ProductBillType.TRADEDELIVERY.equals(producttype)){
            getTradedeliveryInfoList(page);
        }else if(ProductBillType.TRADEINVOICE.equals(producttype)){
            getTradeinvoiceInfoList(page);
        }else if(ProductBillType.TRADELOGISTICS.equals(producttype)){
            getTradelogisticsInfoList(page);
        }else if(ProductBillType.TRADEORDER.equals(producttype)){
            getOrderInfoList(page);
        }else if(ProductBillType.TRADETELEMONEY.equals(producttype)){
            getTradetelemoneyInfoList(page);
        }
    }

    public  void  getTradecontactInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("sales-contract/page-mobile?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<TradecontactBean>() {
                            @Override
                            public void onSuccess(TradecontactBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTradecontactBeanList=data.getContent();
                                    if(page==0){
                                        if(mTradecontactAdapter==null){
                                            mTradecontactAdapter = new TradecontactAdapter(R.layout.customer_tradecontact_item, mTradecontactBeanList);
                                            rv_productlist.setAdapter(mTradecontactAdapter);
                                            mTradecontactAdapter.setOnCLickItemListener(new TradecontactAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(colname.equals("tv_contacttype")||colname.equals("tv_operation")){
                                                        TradecontactBean.ContentBean contentBean=mTradecontactAdapter.getData().get(position);
                                                        if(contentBean!=null){
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newtradecontact);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newtradecontact,true);
                                                        }

                                                    }
                                                }
                                            });
                                        }else{
                                            mTradecontactAdapter.refresh(mTradecontactBeanList);
                                        }
                                        if(mTradecontactBeanList==null||mTradecontactBeanList.size()==0){
                                            mTradecontactAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                        }

                                        showProcessBar(100);
                                    }else{
                                        mTradecontactAdapter.add(mTradecontactBeanList);
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

    public  void  getOrderInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("order/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<TradeorderBean>() {
                            @Override
                            public void onSuccess(TradeorderBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTradeorderBeanList=data.getContent();
                                    if(page==0){
                                        if(mTradeorderAdapter==null){
                                            mTradeorderAdapter = new TradeorderAdapter(R.layout.customer_tradeorder_item, mTradeorderBeanList);
                                            rv_productlist.setAdapter(mTradeorderAdapter);
                                            mTradeorderAdapter.setOnCLickItemListener(new TradeorderAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(colname.equals("tv_orderno")||colname.equals("tv_operation")){
                                                        TradeorderBean.ContentBean contentBean=mTradeorderAdapter.getData().get(position);
                                                        if(contentBean!=null){
                                                            String urladd=Res.getString(null, "jiuyiorderdetail");
                                                            String url="";
                                                            if(!Func.IsStringEmpty(urladd)){
                                                                url=Constant.BaseH5Url+urladd+"?orderID="+contentBean.getId()+"&token="+ Rc.id_tokenparam;
                                                            }
                                                            mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"销售订单");
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                                                        }

                                                    }
                                                }
                                            });
                                        }else{
                                            mTradeorderAdapter.refresh(mTradeorderBeanList);
                                        }
                                        if(mTradeorderBeanList==null||mTradeorderBeanList.size()==0){
                                            mTradeorderAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        mTradeorderAdapter.add(mTradeorderBeanList);
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

    public  void  getTradedeliveryInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("sap-invoice/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<TradedeliveryBean>() {
                            @Override
                            public void onSuccess(TradedeliveryBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTradedeliveryBeanList=data.getContent();
                                    if(page==0){
                                        if(mTradedeliveryAdapter==null){
                                            mTradedeliveryAdapter = new TradedeliveryAdapter(R.layout.customer_tradedelivery_item, mTradedeliveryBeanList);
                                            rv_productlist.setAdapter(mTradedeliveryAdapter);
                                            mTradedeliveryAdapter.setOnCLickItemListener(new TradedeliveryAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(colname.equals("tv_deliveryno")||colname.equals("tv_operation")){
                                                        TradedeliveryBean.ContentBean contentBean=mTradedeliveryAdapter.getData().get(position);
                                                        if(contentBean!=null){
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradeDetail);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "Tradedelivery");
                                                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "发货信息");
                                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradeDetail,true);
                                                        }

                                                    }
                                                }
                                            });
                                            mTradedeliveryAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                                @Override public void onLoadMoreRequested() {
                                                    rv_productlist.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            if (pageIndex>=totalPage) {
                                                                //数据全部加载完毕
                                                                mTradedeliveryAdapter.loadMoreEnd();
                                                            } else {
                                                                refreshCurrenPage(pageIndex);
                                                                pageIndex++;
//                                                                mTradedeliveryAdapter.addData(refreshCurrenPage(pageIndex));
//                                                                mCurrentCounter = mTradedeliveryAdapter.getData().size();
                                                                mTradedeliveryAdapter.loadMoreComplete();
                                                                /*if (isErr) {
                                                                    //成功获取更多数据
                                                                    mTradedeliveryAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                                                                    mCurrentCounter = mTradedeliveryAdapter.getData().size();
                                                                    mTradedeliveryAdapter.loadMoreComplete();
                                                                } else {
                                                                    //获取更多数据失败
                                                                    isErr = true;
                                                                    Toast.makeText(JiuyiMainApplication.getIns(), "1111", Toast.LENGTH_LONG).show();
                                                                    mTradedeliveryAdapter.loadMoreFail();

                                                                }*/
                                                            }
                                                        }

                                                    }, 2000);
                                                }
                                            }, rv_productlist);


                                        }else{
                                            mTradedeliveryAdapter.refresh(mTradedeliveryBeanList);
                                        }
                                        if(mTradedeliveryBeanList==null||mTradedeliveryBeanList.size()==0){
                                            mTradedeliveryAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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


    public  void  getTradeinvoiceInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("sales-billing/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<TradeinvoiceBean>() {
                            @Override
                            public void onSuccess(TradeinvoiceBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTradeinvoiceBeanList=data.getContent();
                                    if(page==0){
                                        if(mTradeinvoiceAdapter==null){
                                            mTradeinvoiceAdapter = new TradeinvoiceAdapter(R.layout.customer_tradeinvoice_item, mTradeinvoiceBeanList);
                                            rv_productlist.setAdapter(mTradeinvoiceAdapter);
                                            mTradeinvoiceAdapter.setOnCLickItemListener(new TradeinvoiceAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(colname.equals("tv_invoiceno")||colname.equals("tv_operation")){
                                                        TradeinvoiceBean.ContentBean contentBean=mTradeinvoiceAdapter.getData().get(position);
                                                        if(contentBean!=null){
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradeDetail);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "Tradeinvoice");
                                                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "发票详情");
                                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradeDetail,true);
                                                        }

                                                    }
                                                }
                                            });
                                        }else{
                                            mTradeinvoiceAdapter.refresh(mTradeinvoiceBeanList);
                                        }
                                        if(mTradeinvoiceBeanList==null||mTradeinvoiceBeanList.size()==0){
                                            mTradeinvoiceAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        mTradeinvoiceAdapter.add(mTradeinvoiceBeanList);
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

    public  void  getTradetelemoneyInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("receipt-tracking/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<TradetelemoneyBean>() {
                            @Override
                            public void onSuccess(TradetelemoneyBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTradetelemoneyBeanList=data.getContent();
                                    if(page==0){
                                        if(mTradetelemoneyAdapter==null){
                                            mTradetelemoneyAdapter = new TradetelemoneyAdapter(R.layout.customer_tradetelemoney_item, mTradetelemoneyBeanList);
                                            rv_productlist.setAdapter(mTradetelemoneyAdapter);
                                            mTradetelemoneyAdapter.setOnCLickItemListener(new TradetelemoneyAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(colname.equals("tv_teleno")||colname.equals("tv_operation")){
                                                        TradetelemoneyBean.ContentBean contentBean=mTradetelemoneyAdapter.getData().get(position);
                                                        if(contentBean!=null){
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradeDetail);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "Tradetelemoney");
                                                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "电汇/承兑详情");
                                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradeDetail,true);
                                                        }

                                                    }
                                                }
                                            });
                                        }else{
                                            mTradetelemoneyAdapter.refresh(mTradetelemoneyBeanList);
                                        }
                                        if(mTradetelemoneyBeanList==null||mTradetelemoneyBeanList.size()==0){
                                            mTradetelemoneyAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        mTradetelemoneyAdapter.add(mTradetelemoneyBeanList);
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
    public  void  getTradelogisticsInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("foreign/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<TradelogisticsBean>() {
                            @Override
                            public void onSuccess(TradelogisticsBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTradelogisticsBeanList=data.getContent();
                                    if(page==0){
                                        if(mTradelogisticsAdapter==null){
                                            mTradelogisticsAdapter = new TradelogisticsAdapter(R.layout.customer_tradelogistics_item, mTradelogisticsBeanList);
                                            rv_productlist.setAdapter(mTradelogisticsAdapter);
                                            mTradelogisticsAdapter.setOnCLickItemListener(new TradelogisticsAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(colname.equals("tv_logisticsno")||colname.equals("tv_operation")){
                                                        TradelogisticsBean.ContentBean contentBean=mTradelogisticsAdapter.getData().get(position);
                                                        if(contentBean!=null){
                                                            String urladd=Res.getString(null, "jiuyiforeignorderdetail");
                                                            String url="";
                                                            if(!Func.IsStringEmpty(urladd)){
                                                                url= Constant.BaseH5Url+urladd+"?id="+contentBean.getId()+"&token="+ Rc.id_tokenparam;
                                                            }
                                                            mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"订单小助手");
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                                                        }

                                                    }
                                                }
                                            });
                                        }else{
                                            mTradelogisticsAdapter.refresh(mTradelogisticsBeanList);
                                        }
                                        if(mTradelogisticsBeanList==null||mTradelogisticsBeanList.size()==0){
                                            mTradelogisticsAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        mTradelogisticsAdapter.add(mTradelogisticsBeanList);
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

    public  void  getTradebankstatementInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("balance-account/page")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<TradebankstatementBean>() {
                            @Override
                            public void onSuccess(TradebankstatementBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTradebankstatementBeanList=data.getContent();
                                    if(page==0){
                                        if(mTradebankstatementAdapter==null){
                                            mTradebankstatementAdapter = new TradebankstatementAdapter(R.layout.customer_tradebankstatement_item, mTradebankstatementBeanList);
                                            rv_productlist.setAdapter(mTradebankstatementAdapter);
                                            mTradebankstatementAdapter.setOnCLickItemListener(new TradebankstatementAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(colname.equals("tv_statementno")||colname.equals("tv_operation")){
                                                        TradebankstatementBean.ContentBean contentBean=mTradebankstatementAdapter.getData().get(position);
                                                        if(contentBean!=null){
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradeDetail);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "Tradebankstatement");
                                                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "对账单");
                                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradeDetail,true);
                                                        }

                                                    }
                                                }
                                            });
                                        }else{
                                            mTradebankstatementAdapter.refresh(mTradebankstatementBeanList);
                                        }
                                        if(mTradebankstatementBeanList==null||mTradebankstatementBeanList.size()==0){
                                            mTradebankstatementAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        mTradebankstatementAdapter.add(mTradebankstatementBeanList);
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


    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh){
            Rc.mBackfresh=false;
            refreshCurrenPage(0);
        }

    }

}
