package customer.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnCancelLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.CustomerBillAdapter;
import customer.adapter.CustomerInvoiceAdapter;
import customer.adapter.CustomerReceiptAdapter;
import customer.adapter.DynamicContractAdapter;
import customer.adapter.DynamicOrderAdapter;
import customer.adapter.TradedeliveryAdapter;
import customer.entity.AbstractSummaryBean;
import customer.entity.CustomerBillBean;
import customer.entity.CustomerInvoiceBean;
import customer.entity.CustomerReceiptBean;
import customer.entity.GatheringPlanBean;
import customer.entity.TradedeliveryBean;
import customer.view.MultiItemDivider;
import dynamic.entity.DyContractBean;
import dynamic.entity.DyOrderBean;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerContractPandectFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 合同总览
 *****************************************************************
 */
public class JiuyiCustomerContractPandectFragment extends JiuyiFragmentBase {

    private long Customerid=-1;
    private String Customername="";
    private String mRisktype="";
    RecyclerView rvInfoList;
    private List<TradedeliveryBean.ContentBean> mTradedeliveryBeanList;
    private TradedeliveryAdapter mTradedeliveryAdapter;

    private List<CustomerInvoiceBean.ContentBean> mCustomerInvoiceBeanList;
    private CustomerInvoiceAdapter customerInvoiceAdapter;

    private List<CustomerBillBean.ContentBean> mCustomerBillBeanList;
    private CustomerBillAdapter customerBillAdapter;
    private List<CustomerReceiptBean.ContentBean> mCustomerReceiptBeanList;
    private CustomerReceiptAdapter customerReceiptAdapter;
    QueryConditionBean queryConditionBean;
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pagesize = 20;
    private int totalPage=0;
    private ImageView ivNew;
    private LinearLayout llContract;
    private TextView tvContract;
    private LinearLayout llOrder;
    private TextView tvOrder;
    private LinearLayout llDelivery;
    private TextView tvDelivery;
    private LinearLayout llInvoice;
    private TextView tvInvoice;
    private LinearLayout llReceivables;
    private TextView tvReceivables;
    private LinearLayout llReconciliation;
    private TextView tvReconciliation;
    private TextView tvTitle;
    private String currentBillType;
    private LinearLayout llCondition;
    private LinearLayout llStatus;
    private TextView tvType;
    private LinearLayout llOrg;
    private TextView tvOrg;
    private LinearLayout llDate;
    private TextView tvDate;
    private DynamicContractAdapter contractAdapter;
    private DynamicOrderAdapter dynamicOrderAdapter;

    private String backPageType="";


    public String getmRisktype() {
        return mRisktype;
    }

    public void setmRisktype(String mRisktype) {
        this.mRisktype = mRisktype;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customercontractpandectfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        mRisktype=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE);
        llContract = (LinearLayout) mRootView.findViewById(R.id.ll_contract);
        llContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"合同清单");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dy_ContractWhole,true);
//                currentBillType="Contract";
//                backPageType="contract";
//                refreshCurrenPage(0);
//                tvTitle.setText("合同");
            }
        });
        tvContract = (TextView) mRootView.findViewById(R.id.tv_contract);
        llOrder = (LinearLayout) mRootView.findViewById(R.id.ll_order);
        llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"订单清单");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dy_OrderWhole,true);
//                currentBillType="Order";
//                backPageType="order";
//                refreshCurrenPage(0);
//                tvTitle.setText("订单");
            }
        });
        tvOrder = (TextView) mRootView.findViewById(R.id.tv_order);
        llDelivery = (LinearLayout) mRootView.findViewById(R.id.ll_delivery);
        llDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"发货清单");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dy_deliveryWhole,true);
//                currentBillType="Delivery";
//                backPageType="invoice";
//                refreshCurrenPage(0);
//                tvTitle.setText("发货");
            }
        });
        tvDelivery = (TextView) mRootView.findViewById(R.id.tv_delivery);
        llInvoice = (LinearLayout) mRootView.findViewById(R.id.ll_invoice);
        llInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"发票清单");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dy_invoiceWhole,true);
//                currentBillType="Invoice";
//                backPageType="billing";
//                refreshCurrenPage(0);
//                tvTitle.setText("发票");
            }
        });
        tvInvoice = (TextView) mRootView.findViewById(R.id.tv_invoice);
        llReceivables = (LinearLayout) mRootView.findViewById(R.id.ll_receivables);
        llReceivables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"收款清单");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dy_receivablesWhole,true);
//                currentBillType="Receivables";
//                backPageType="receipt";
//                refreshCurrenPage(0);
//                tvTitle.setText("收款");
            }
        });
        tvReceivables = (TextView) mRootView.findViewById(R.id.tv_receivables);
        llReconciliation = (LinearLayout) mRootView.findViewById(R.id.ll_reconciliation);
        llReconciliation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
//                currentBillType="Reconciliation";
//                backPageType="contract";
//                refreshCurrenPage(0);
//                tvTitle.setText("对账");
            }
        });
        llReconciliation.setVisibility(View.INVISIBLE);
        tvReconciliation = (TextView) mRootView.findViewById(R.id.tv_reconciliation);

        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        llCondition = (LinearLayout) mRootView.findViewById(R.id.ll_condition);
        llStatus = (LinearLayout) mRootView.findViewById(R.id.ll_status);
        tvType = (TextView) mRootView.findViewById(R.id.tv_type);
        llOrg = (LinearLayout) mRootView.findViewById(R.id.ll_org);
        tvOrg = (TextView) mRootView.findViewById(R.id.tv_org);
        llDate = (LinearLayout) mRootView.findViewById(R.id.ll_date);
        llDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialog dialog = new DatePickDialog(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity());
                //设置上下年分限制
                dialog.setYearLimt(60);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                dialog.setOnCancelListener(new OnCancelLisener() {
                    @Override
                    public void onCancel(String s) {
                        tvDate.setText("日期");
                        refreshCurrenPage(0);
                    }
                });
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        tvDate.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
//                        if(mTvDate.getText().toString()!=null){
//                            year=Integer.parseInt(mTvDate.getText().toString().substring(0,4));
//                            month=Integer.parseInt(mTvDate.getText().toString().substring(5,7));
//                        }
                        refreshCurrenPage(0);
                    }
                });
                dialog.show();
            }
        });

        tvDate = (TextView) mRootView.findViewById(R.id.tv_date);
        rvInfoList=(RecyclerView)mRootView.findViewById(R.id.rv_infolist);
        rvInfoList.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        //分割线
        MultiItemDivider itemDivider = new MultiItemDivider(JiuyiMainApplication.getIns(), MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
        rvInfoList.addItemDecoration(itemDivider);
        rvInfoList.setNestedScrollingEnabled(false);
        rvInfoList.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        rvInfoList.setFocusable(false);
        currentBillType="Contract";
//        refreshCurrenPage(0);
        getAbstractSummaryList();
        tvTitle.setText("合同");
        TwinklingRefreshLayout  refreshLayout = (TwinklingRefreshLayout)mRootView.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex=1;
                        refreshCurrenPage(0);
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
                            refreshCurrenPage(pageIndex);
                            pageIndex++;

                        }
                        refreshLayout.finishLoadmore();


                    }
                },2000);
            }
        });


//        showProcessBar(0);
//        if(Rc.mCurrentPageType==Pub.Customer_Contract){
//            showDialog();
//        }
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerContractPandectFragment newInstance(int nPageType) {
        JiuyiCustomerContractPandectFragment f = new JiuyiCustomerContractPandectFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerContractPandectFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerContractPandectFragment f = new JiuyiCustomerContractPandectFragment();
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
            refreshCurrenPage(0);;
        }

    }

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction== DialogID.DialogDoNothing)
        {
            if(nKeyCode == KeyEvent.KEYCODE_BACK ){
                return;
            }
        }
    }

    public  void getContractList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("contract/page-mobile")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DyContractBean>() {
                    @Override
                    public void onSuccess(DyContractBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<DyContractBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                contractAdapter = new DynamicContractAdapter(R.layout.dynamic_item_contract, contentBeanList);
                                rvInfoList.setAdapter(contractAdapter);
                                contractAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        DyContractBean.ContentBean contentBean=(DyContractBean.ContentBean)adapter.getData().get(position);
                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newReceiptplan);
                                        mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getFkId());

                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newReceiptplan,true);
                                    }
                                });
                                if(contentBeanList!=null || contentBeanList.size()==0){
                                    contractAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }

                            }else{
                                contractAdapter.add(contentBeanList);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }
    public  void getOrderList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("order/page-mobile")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DyOrderBean>() {
                    @Override
                    public void onSuccess(DyOrderBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<DyOrderBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                dynamicOrderAdapter = new DynamicOrderAdapter(R.layout.dynamic_item_order, contentBeanList);
                                rvInfoList.setAdapter(dynamicOrderAdapter);
                                dynamicOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        DyOrderBean.ContentBean contentBean=(DyOrderBean.ContentBean)adapter.getData().get(position);
                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newReceiptplan);
                                        mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getFkId());
                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newReceiptplan,true);
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    dynamicOrderAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }

                            }else{
                                dynamicOrderAdapter.add(contentBeanList);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }


    public  void getCustomerBillList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<CustomerBillBean>() {
                    @Override
                    public void onSuccess(CustomerBillBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mCustomerBillBeanList=data.getContent();
                            if(page==0){
                                customerBillAdapter = new CustomerBillAdapter(R.layout.dynamic_item_order, mCustomerBillBeanList);
                                rvInfoList.setAdapter(customerBillAdapter);
                                customerBillAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        CustomerBillBean.ContentBean contentBean=(CustomerBillBean.ContentBean)customerBillAdapter.getData().get(position);
                                        if(contentBean!=null){
                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                            if(!Func.IsStringEmpty(tvTitle.getText().toString())){
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, tvTitle.getText().toString());
                                            }

                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
                                        }
                                    }
                                });

                                if(mCustomerBillBeanList==null||mCustomerBillBeanList.size()==0){
                                    customerBillAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                customerBillAdapter.add(mCustomerBillBeanList);
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
    public  void getCustomerReceiptList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<CustomerReceiptBean>() {
                    @Override
                    public void onSuccess(CustomerReceiptBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mCustomerReceiptBeanList=data.getContent();
                            if(page==0){
                                customerReceiptAdapter = new CustomerReceiptAdapter(R.layout.dynamic_item_receipt, mCustomerReceiptBeanList);
                                rvInfoList.setAdapter(customerReceiptAdapter);
                                customerReceiptAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        CustomerReceiptBean.ContentBean contentBean=(CustomerReceiptBean.ContentBean)customerReceiptAdapter.getData().get(position);
                                        if(contentBean!=null){
                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                            if(!Func.IsStringEmpty(tvTitle.getText().toString())){
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, tvTitle.getText().toString());
                                            }

                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
                                        }
                                    }
                                });

                                if(mCustomerReceiptBeanList==null||mCustomerReceiptBeanList.size()==0){
                                    customerReceiptAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                customerReceiptAdapter.add(mCustomerReceiptBeanList);
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


    public  void getCustomerInvoiceList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<CustomerInvoiceBean>() {
                    @Override
                    public void onSuccess(CustomerInvoiceBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mCustomerInvoiceBeanList=data.getContent();
                            if(page==0){
                                customerInvoiceAdapter = new CustomerInvoiceAdapter(R.layout.dynamic_item_order, mCustomerInvoiceBeanList);
                                rvInfoList.setAdapter(customerInvoiceAdapter);
                                customerInvoiceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        CustomerInvoiceBean.ContentBean contentBean=(CustomerInvoiceBean.ContentBean)customerInvoiceAdapter.getData().get(position);
                                        if(contentBean!=null){
                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                            if(!Func.IsStringEmpty(tvTitle.getText().toString())){
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, tvTitle.getText().toString());
                                            }

                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
                                        }
                                    }
                                });

                                if(mCustomerInvoiceBeanList==null||mCustomerInvoiceBeanList.size()==0){
                                    customerInvoiceAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                customerInvoiceAdapter.add(mCustomerInvoiceBeanList);
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
//        List<String> value = new ArrayList<String>();
//        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
//        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
//        rulesBean.setField("member");
//        rulesBean.setOption("EQ");
//        value.add(Customerid+"");
//        rulesBean.setValues(value);
//        rulesBeanList.add(rulesBean);
//        queryConditionBean.setRules(rulesBeanList);
        return queryConditionBean;
    }
    public void refreshCurrenPage(int page){
        if(currentBillType.equals("Contract")){
            tvType.setText("合同类型");
            tvOrg.setText("销售组织");
            tvDate.setText("合同日期");
            getContractList(page);
        }else if(currentBillType.equals("Order")){
            tvType.setText("订单类型");
            tvOrg.setText("销售组织");
            tvDate.setText("订单日期");
            getOrderList(page);
        }else if(currentBillType.equals("Delivery")){
            tvType.setText("订单类型");
            tvOrg.setText("销售组织");
            tvDate.setText("发货日期");
            getCustomerBillList(page);
        }else if(currentBillType.equals("Invoice")){
            tvType.setText("订单类型");
            tvOrg.setText("销售组织");
            tvDate.setText("订单日期");
            getCustomerInvoiceList(page);
        }else if(currentBillType.equals("Receivables")){
            tvType.setText("收款方式");
            tvOrg.setText("收款币种");
            tvDate.setText("收款日期");
            getCustomerReceiptList(page);
        }else if(currentBillType.equals("Reconciliation")){
            tvType.setText("对账公司");
            tvOrg.setText("收款币种");
            tvDate.setText("对账日期");
            getCustomerReceiptList(page);
        }
    }

    private void getAbstractSummaryList() {
        String apiUrl="contract/abstract-summary/"+Customerid;
        JiuyiHttp.GET(apiUrl+"?fromClientType=android")
                .tag("request_get_market-trend")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<AbstractSummaryBean>>() {
                    @Override
                    public void onSuccess(List<AbstractSummaryBean> data) {
                        if(data!=null && data.size()>0){
                            for(int i=0;i<data.size();i++){
                                AbstractSummaryBean abstractSummaryBean=data.get(i);
                                if(abstractSummaryBean.getField().equals("Contract")){
                                    tvContract.setText(abstractSummaryBean.getCount()+"");
                                }else  if(abstractSummaryBean.getField().equals("Order")){
                                    tvOrder.setText(abstractSummaryBean.getCount()+"");
                                }else  if(abstractSummaryBean.getField().equals("Invoice")){
                                    tvDelivery.setText(abstractSummaryBean.getCount()+"");
                                }else  if(abstractSummaryBean.getField().equals("SalesBilling")){
                                    tvInvoice.setText(abstractSummaryBean.getCount()+"");
                                }else  if(abstractSummaryBean.getField().equals("ReceiptTracking")){
                                    tvReceivables.setText(abstractSummaryBean.getCount()+"");
                                }
                            }


                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }


}
