package customer.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
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

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.ClientListAdapter;
import customer.adapter.ImportExportProductAdapter;
import customer.adapter.SaleSystemAdapter;
import customer.adapter.StandardPriceAdapter;
import customer.adapter.TradedeliveryAdapter;
import customer.entity.AbstractSummaryBean;
import customer.entity.ClientListBean;
import customer.entity.ImportExportProductBean;
import customer.entity.SaleSystemBean;
import customer.entity.StandardPriceBean;
import customer.entity.TradedeliveryBean;
import customer.view.jiuyiRecycleViewDivider;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerSalesStatusFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 采购动态
 *****************************************************************
 */
public class JiuyiCustomerSalesStatusFragment extends JiuyiFragmentBase {

    private long Customerid=-1;
    private String Customername="";
    private String mRisktype="";
    RecyclerView rvInfoList;
    private List<TradedeliveryBean.ContentBean> mTradedeliveryBeanList;
    private TradedeliveryAdapter mTradedeliveryAdapter;

    private List<SaleSystemBean.ContentBean> mSaleSystemBeanList;
    private SaleSystemAdapter saleSystemAdapter;

    private List<ClientListBean.ContentBean> mClientListBeanList;
    private ClientListAdapter clientListAdapter;

    private List<StandardPriceBean.ContentBean> mStandardPriceBeanList;
    private StandardPriceAdapter standardPriceAdapter;

    private List<ImportExportProductBean.ContentBean> mImportExportProductBeanList;
    private ImportExportProductAdapter importExportProductAdapter;
    QueryConditionBean queryConditionBean;
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pagesize = 20;
    private int totalPage=0;
    private ImageView ivNew;
    private LinearLayout llSaleSystem;
    private TextView tvSaleSystem;
    private LinearLayout llCustomerDirectory;
    private TextView tvCustomerDirectory;
    private LinearLayout llSalesQuotation;
    private TextView tvSalesQuotation;
    private LinearLayout llImportsAndExports;
    private TextView tvImportsAndExports;
    private TextView tvTitle;
    private String currentBillType="",backPageType="";
    private String sTitle="";
    private LinearLayout ll_title;
    public String getBusinessTypeKey() {
        return businessTypeKey;
    }

    public void setBusinessTypeKey(String businessTypeKey) {
        this.businessTypeKey = businessTypeKey;
    }

    private String businessTypeKey="";


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
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customersalesstatusfragment_layout"), null);
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
        llSaleSystem = (LinearLayout) mRootView.findViewById(R.id.ll_sale_system);
        llSaleSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="SaleSystem";
                backPageType="sales-system";
                refreshCurrenPage(0);
                sTitle="销售体系文件";
//                tvTitle.setText("销售体系文件");
            }
        });
        tvSaleSystem = (TextView) mRootView.findViewById(R.id.tv_sale_system);
        llCustomerDirectory = (LinearLayout) mRootView.findViewById(R.id.ll_customer_directory);
        llCustomerDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="CustomerDirectory";
                backPageType="client-list";
                refreshCurrenPage(0);

                sTitle="客户名录";
//                tvTitle.setText("客户名录");
            }
        });
        tvCustomerDirectory = (TextView) mRootView.findViewById(R.id.tv_customer_directory);
        llSalesQuotation = (LinearLayout) mRootView.findViewById(R.id.ll_sales_quotation);
        llSalesQuotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="SalesQuotation";
                backPageType="standard-price";
                refreshCurrenPage(0);
                sTitle="销售报价";
//                tvTitle.setText("销售报价");
            }
        });
        tvSalesQuotation = (TextView) mRootView.findViewById(R.id.tv_sales_quotation);
        llImportsAndExports = (LinearLayout) mRootView.findViewById(R.id.ll_imports_and_exports);
        llImportsAndExports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="ImportsAndExports";
                backPageType="import-and-export-quotation";
                refreshCurrenPage(0);
                sTitle="进出口产品";
//                tvTitle.setText("进出口产品");
            }
        });
        tvImportsAndExports = (TextView) mRootView.findViewById(R.id.tv_imports_and_exports);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        ivNew=(ImageView) mRootView.findViewById(R.id.iv_new);
        ll_title=(LinearLayout) mRootView.findViewById(R.id.ll_title);
        ll_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ivNew.getVisibility()==View.GONE){
                    return;
                }
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, Customername);
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                if(!Func.IsStringEmpty(sTitle)){
                    mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建"+sTitle);
                }

                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newDynamicForm,true);
            }
        });
//        ivNew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
//                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, Customername);
//                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
//                if(!Func.IsStringEmpty(sTitle)){
//                    mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
//                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建"+sTitle);
//                }
//
//                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newDynamicForm,true);
//            }
//        });
        rvInfoList=(RecyclerView)mRootView.findViewById(R.id.rv_infolist);
        rvInfoList.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        //分割线
//        MultiItemDivider itemDivider = new MultiItemDivider(JiuyiMainApplication.getIns(), MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
//        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
//        rvInfoList.addItemDecoration(itemDivider);
        rvInfoList.setItemAnimator(new DefaultItemAnimator());
        rvInfoList.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

        rvInfoList.setNestedScrollingEnabled(false);
        rvInfoList.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        rvInfoList.setFocusable(false);
        currentBillType="SaleSystem";
        backPageType="sales-system";
        refreshCurrenPage(0);
        sTitle="销售体系文件";
//        tvTitle.setText("销售体系文件");
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


        showProcessBar(0);
//        if(Rc.mCurrentPageType==Pub.Customer_Contract){
//            showDialog();
//        }
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerSalesStatusFragment newInstance(int nPageType) {
        JiuyiCustomerSalesStatusFragment f = new JiuyiCustomerSalesStatusFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerSalesStatusFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerSalesStatusFragment f = new JiuyiCustomerSalesStatusFragment();
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
    public  void getClientListList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ClientListBean>() {
                    @Override
                    public void onSuccess(ClientListBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mClientListBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("客户名录("+data.getTotalElements()+")");
                                clientListAdapter = new ClientListAdapter(R.layout.jiuyi_customer_sales_item, mClientListBeanList);
                                rvInfoList.setAdapter(clientListAdapter);
                                clientListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        ClientListBean.ContentBean contentBean=(ClientListBean.ContentBean)clientListAdapter.getData().get(position);
                                        if(contentBean!=null){
                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                            if(!Func.IsStringEmpty(sTitle)){
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, sTitle);
                                            }

                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
                                        }
                                    }
                                });
                                clientListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(view.getId()==R.id.title){
//                                            ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
//                                            if(contentBean!=null){
//                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
//                                                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
//                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
//                                                if(!Func.IsStringEmpty(tvTitle.getText().toString())){
//                                                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, tvTitle.getText().toString());
//                                                }
//
//                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
//                                            }
                                        }

                                    }
                                });

                                if(mClientListBeanList==null||mClientListBeanList.size()==0){
                                    clientListAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                clientListAdapter.add(mClientListBeanList);
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

    public  void getSaleSystemList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<SaleSystemBean>() {
                    @Override
                    public void onSuccess(SaleSystemBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mSaleSystemBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("销售体系文件("+data.getTotalElements()+")");
                                saleSystemAdapter = new SaleSystemAdapter(R.layout.customer_item_salesystem, mSaleSystemBeanList);
                                rvInfoList.setAdapter(saleSystemAdapter);
                                saleSystemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        SaleSystemBean.ContentBean contentBean=(SaleSystemBean.ContentBean)saleSystemAdapter.getData().get(position);
                                        if(contentBean!=null){
                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                            if(!Func.IsStringEmpty(sTitle)){
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, sTitle);
                                            }

                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
                                        }
                                    }
                                });
                                saleSystemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(view.getId()==R.id.title){
//                                            ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
//                                            if(contentBean!=null){
//                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
//                                                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
//                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
//                                                if(!Func.IsStringEmpty(tvTitle.getText().toString())){
//                                                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, tvTitle.getText().toString());
//                                                }
//
//                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
//                                            }
                                        }

                                    }
                                });

                                if(mSaleSystemBeanList==null||mSaleSystemBeanList.size()==0){
                                    saleSystemAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                saleSystemAdapter.add(mSaleSystemBeanList);
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
    public  void getStandardPriceList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<StandardPriceBean>() {
                    @Override
                    public void onSuccess(StandardPriceBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mStandardPriceBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("销售报价("+data.getTotalElements()+")");
                                standardPriceAdapter = new StandardPriceAdapter(R.layout.customer_item_standardprice, mStandardPriceBeanList);
                                rvInfoList.setAdapter(standardPriceAdapter);
                                standardPriceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        StandardPriceBean.ContentBean contentBean=(StandardPriceBean.ContentBean)standardPriceAdapter.getData().get(position);
                                        if(contentBean!=null){
                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                            if(!Func.IsStringEmpty(sTitle)){
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, sTitle);
                                            }

                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
                                        }
                                    }
                                });
                                standardPriceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(view.getId()==R.id.title){
//                                            ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
//                                            if(contentBean!=null){
//                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
//                                                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
//                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
//                                                if(!Func.IsStringEmpty(tvTitle.getText().toString())){
//                                                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, tvTitle.getText().toString());
//                                                }
//
//                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
//                                            }
                                        }

                                    }
                                });

                                if(mStandardPriceBeanList==null||mStandardPriceBeanList.size()==0){
                                    standardPriceAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                standardPriceAdapter.add(mStandardPriceBeanList);
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

    public  void getImportExportProductList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ImportExportProductBean>() {
                    @Override
                    public void onSuccess(ImportExportProductBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mImportExportProductBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("进出口产品("+data.getTotalElements()+")");
                                importExportProductAdapter = new ImportExportProductAdapter(R.layout.customer_item_importexportproduct, mImportExportProductBeanList);
                                rvInfoList.setAdapter(importExportProductAdapter);
                                importExportProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        ImportExportProductBean.ContentBean contentBean=(ImportExportProductBean.ContentBean)importExportProductAdapter.getData().get(position);
                                        if(contentBean!=null){
                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
                                            if(!Func.IsStringEmpty(sTitle)){
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, sTitle);
                                            }

                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
                                        }
                                    }
                                });
                                importExportProductAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(view.getId()==R.id.title){
//                                            ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
//                                            if(contentBean!=null){
//                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
//                                                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
//                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
//                                                if(!Func.IsStringEmpty(tvTitle.getText().toString())){
//                                                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, tvTitle.getText().toString());
//                                                }
//
//                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
//                                            }
                                        }

                                    }
                                });

                                if(mImportExportProductBeanList==null||mImportExportProductBeanList.size()==0){
                                    importExportProductAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                importExportProductAdapter.add(mImportExportProductBeanList);
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
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member.id");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        if(!Func.IsStringEmpty(businessTypeKey)){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("businessTypeKey");
            rulesBean2.setOption("EQ");
            value2.add(businessTypeKey);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        queryConditionBean.setRules(rulesBeanList);
        return queryConditionBean;
    }
    public void refreshCurrenPage(int page){
        if(currentBillType.equals("SaleSystem")){
            getSaleSystemList(page);
        }else if(currentBillType.equals("CustomerDirectory")){
            getClientListList(page);
        }else if(currentBillType.equals("SalesQuotation")){
            getStandardPriceList(page);
        }else if(currentBillType.equals("ImportsAndExports")){
            getImportExportProductList(page);
        }
        if(page==0){
            getAbstractSummaryList();
        }
    }

    private void getAbstractSummaryList() {
        String apiUrl="sales-system/abstract-summary/"+Customerid;
        if(!Func.IsStringEmpty(businessTypeKey)){
            apiUrl+="/"+businessTypeKey;
        }
        JiuyiHttp.GET(apiUrl+"?fromClientType=android")
                .tag("request_get_market-trend")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<AbstractSummaryBean>>() {
                    @Override
                    public void onSuccess(List<AbstractSummaryBean> data) {
                        if(data!=null && data.size()>0){
                            for(int i=0;i<data.size();i++){
                                AbstractSummaryBean abstractSummaryBean=data.get(i);
                                if(abstractSummaryBean.getField().equals("SalesSystem")){
                                    if(tvSaleSystem!=null){
                                        tvSaleSystem.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("ClientList")){
                                    if(tvCustomerDirectory!=null){
                                        tvCustomerDirectory.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("StandardPrice")){
                                    if(tvSalesQuotation!=null){
                                        tvSalesQuotation.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("ImportAndExportQuotation")){
                                    if(tvImportsAndExports!=null){
                                        tvImportsAndExports.setText(abstractSummaryBean.getCount()+"");
                                    }
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
