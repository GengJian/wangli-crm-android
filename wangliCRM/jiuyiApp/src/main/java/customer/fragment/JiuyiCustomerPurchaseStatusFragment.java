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
import customer.adapter.AcessStandardAdapter;
import customer.adapter.EvaluationSystemAdapter;
import customer.adapter.ProductDirectionAdapter;
import customer.adapter.ProvideDirectionAdapter;
import customer.adapter.TradedeliveryAdapter;
import customer.entity.AbstractSummaryBean;
import customer.entity.AccessStandardBean;
import customer.entity.EvaluationSystemBean;
import customer.entity.ProductDirectoryBean;
import customer.entity.ProvideDirectoryBean;
import customer.entity.TradedeliveryBean;
import customer.view.jiuyiRecycleViewDivider;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerPurchaseStatusFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 采购动态
 *****************************************************************
 */
public class JiuyiCustomerPurchaseStatusFragment extends JiuyiFragmentBase {

    private long Customerid=-1;
    private String Customername="";
    private String mRisktype="";
    RecyclerView rvInfoList;
    private List<TradedeliveryBean.ContentBean> mTradedeliveryBeanList;
    private TradedeliveryAdapter mTradedeliveryAdapter;


    private List<ProvideDirectoryBean.ContentBean> mProvideDirectoryBeanList;
    private ProvideDirectionAdapter provideDirectionAdapter;

    private List<ProductDirectoryBean.ContentBean> mProductDirectoryBeanList;
    private ProductDirectionAdapter productDirectionAdapter;
    private List<AccessStandardBean.ContentBean> mAccessStandardBeanList;
    private AcessStandardAdapter acessStandardAdapter;

    private List<EvaluationSystemBean.ContentBean> mEvaluationSystemBeanList;
    private EvaluationSystemAdapter evaluationSystemAdapter;

    QueryConditionBean queryConditionBean;
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pagesize = 20;
    private int totalPage=0;
    private ImageView ivNew;
    private LinearLayout llSupplierDirectory;
    private TextView tvSupplierDirectory;
    private LinearLayout llPurchasingCatalogue;
    private TextView tvPurchasingCatalogue;
    private LinearLayout llStandard;
    private TextView tvStandard;
    private LinearLayout llCheck;
    private TextView tvCheck,tvTitle;
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
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerpurchasestatusfragment_layout"), null);
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
        llSupplierDirectory = (LinearLayout) mRootView.findViewById(R.id.ll_supplier_directory);
        llSupplierDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="SupplierDirectory";
                backPageType="supplier-directory";
                refreshCurrenPage(0);
                sTitle="供应商目录";
//                tvTitle.setText("供应商目录");
            }
        });
        tvSupplierDirectory = (TextView) mRootView.findViewById(R.id.tv_supplier_directory);
        llPurchasingCatalogue = (LinearLayout) mRootView.findViewById(R.id.ll_purchasing_catalogue);
        llPurchasingCatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="PurchasingCatalogue";
                backPageType="product-directory";
                refreshCurrenPage(0);
                sTitle="采购目录";
//                tvTitle.setText("采购目录");
            }
        });
        tvPurchasingCatalogue = (TextView) mRootView.findViewById(R.id.tv_purchasing_catalogue);
        llStandard = (LinearLayout) mRootView.findViewById(R.id.ll_standard);
        llStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="Standard";
                backPageType="access-standard";
                refreshCurrenPage(0);
                sTitle="准入、技检标准";
//                tvTitle.setText("准入、技检标准");
            }
        });
        tvStandard = (TextView) mRootView.findViewById(R.id.tv_standard);
        llCheck = (LinearLayout) mRootView.findViewById(R.id.ll_check);
        llCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="Check";
                backPageType="evaluation-system";
                refreshCurrenPage(0);
                sTitle="评价考核体系";
//                tvTitle.setText("评价考核体系");
            }
        });
        tvCheck = (TextView) mRootView.findViewById(R.id.tv_check);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
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
        ivNew=(ImageView) mRootView.findViewById(R.id.iv_new);
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
        currentBillType="SupplierDirectory";
        backPageType="supplier-directory";
        refreshCurrenPage(0);
        sTitle="供应商目录";
//        tvTitle.setText("供应商目录");
        refreshCurrenPage(0);

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
        if(Rc.mCurrentPageType==Pub.Customer_Contract){
            showDialog();
        }
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerPurchaseStatusFragment newInstance(int nPageType) {
        JiuyiCustomerPurchaseStatusFragment f = new JiuyiCustomerPurchaseStatusFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerPurchaseStatusFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerPurchaseStatusFragment f = new JiuyiCustomerPurchaseStatusFragment();
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
            refreshCurrenPage(0);
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
    public  void getSupplierDirectoryList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("ASC");
        queryConditionBean.setProperty("rank");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member");
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
//        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProvideDirectoryBean>() {
                    @Override
                    public void onSuccess(ProvideDirectoryBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mProvideDirectoryBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("供应商目录("+data.getTotalElements()+")");
                                provideDirectionAdapter = new ProvideDirectionAdapter(R.layout.jiuyi_customer_supplier_item, mProvideDirectoryBeanList);
                                rvInfoList.setAdapter(provideDirectionAdapter);
                                provideDirectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        ProvideDirectoryBean.ContentBean contentBean=(ProvideDirectoryBean.ContentBean)provideDirectionAdapter.getData().get(position);
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
                                provideDirectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(view.getId()==R.id.tv_down){
                                            ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
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

                                    }
                                });

                                if(mProvideDirectoryBeanList==null||mProvideDirectoryBeanList.size()==0){
                                    provideDirectionAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                provideDirectionAdapter.add(mProvideDirectoryBeanList);
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

    public  void getProductDirectoryList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductDirectoryBean>() {
                    @Override
                    public void onSuccess(ProductDirectoryBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mProductDirectoryBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("采购目录("+data.getTotalElements()+")");
                                productDirectionAdapter = new ProductDirectionAdapter(R.layout.customer_item_productdirectory, mProductDirectoryBeanList);
                                rvInfoList.setAdapter(productDirectionAdapter);
                                productDirectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
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
                                productDirectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(view.getId()==R.id.tv_down){
                                            ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
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

                                    }
                                });

                                if(mProductDirectoryBeanList==null||mProductDirectoryBeanList.size()==0){
                                    productDirectionAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                productDirectionAdapter.add(mProductDirectoryBeanList);
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

    public  void getEvaluationSystemList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<EvaluationSystemBean>() {
                    @Override
                    public void onSuccess(EvaluationSystemBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mEvaluationSystemBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("评价考核体系("+data.getTotalElements()+")");
                                evaluationSystemAdapter = new EvaluationSystemAdapter(R.layout.customer_item_evaluationsystem, mEvaluationSystemBeanList);
                                rvInfoList.setAdapter(evaluationSystemAdapter);
                                evaluationSystemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        EvaluationSystemBean.ContentBean contentBean=(EvaluationSystemBean.ContentBean)evaluationSystemAdapter.getData().get(position);
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
                                evaluationSystemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(view.getId()==R.id.title){
                                            ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
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

                                    }
                                });

                                if(mEvaluationSystemBeanList==null||mEvaluationSystemBeanList.size()==0){
                                    evaluationSystemAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                evaluationSystemAdapter.add(mEvaluationSystemBeanList);
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

    public  void getAcessStandardList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<AccessStandardBean>() {
                    @Override
                    public void onSuccess(AccessStandardBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mAccessStandardBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("准入、技检标准("+data.getTotalElements()+")");
                                acessStandardAdapter = new AcessStandardAdapter(R.layout.customer_item_evaluationsystem, mAccessStandardBeanList);
                                rvInfoList.setAdapter(acessStandardAdapter);
                                acessStandardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        AccessStandardBean.ContentBean contentBean=(AccessStandardBean.ContentBean)acessStandardAdapter.getData().get(position);
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
                                acessStandardAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(view.getId()==R.id.title){
                                            ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
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

                                    }
                                });

                                if(mAccessStandardBeanList==null||mAccessStandardBeanList.size()==0){
                                    acessStandardAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                acessStandardAdapter.add(mAccessStandardBeanList);
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
        rulesBean.setField("member");
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
        if(currentBillType.equals("SupplierDirectory")){
//            getSupplierDirectoryList(page);
            getSupplierList();
        }else if(currentBillType.equals("PurchasingCatalogue")){
            getProductDirectoryList(page);
        }else if(currentBillType.equals("Standard")){
            getAcessStandardList(page);
        }else if(currentBillType.equals("Check")){
            getEvaluationSystemList(page);
        }
        if(page==0){
            getAbstractSummaryList();
        }
    }

    private void getAbstractSummaryList() {
        String apiUrl="supplier-directory/abstract-summary/"+Customerid;
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
                                if(abstractSummaryBean.getField().equals("SupplierDirectory")){
                                    if(tvSupplierDirectory!=null){
                                        tvSupplierDirectory.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("ProductDirectory")){
                                    if(tvPurchasingCatalogue!=null){
                                        tvPurchasingCatalogue.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("AccessStandard")){
                                    if(tvStandard!=null){
                                        tvStandard.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("EvaluationSystem")){
                                    if(tvCheck!=null){
                                        tvCheck.setText(abstractSummaryBean.getCount()+"");
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
    private void getSupplierList() {
        String apiUrl="supplier-directory/supplier-list-mobile/"+Customerid;
        if(!Func.IsStringEmpty(businessTypeKey)){
            apiUrl+="/"+businessTypeKey;
        }
        JiuyiHttp.GET(apiUrl+"?fromClientType=android")
                .tag("request_get_market-trend")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<ProvideDirectoryBean.ContentBean>>() {
                    @Override
                    public void onSuccess(List<ProvideDirectoryBean.ContentBean> data) {
                        if(data!=null){
                            tvTitle.setText("供应商目录("+data.size()+")");
                            mProvideDirectoryBeanList=data;
                            provideDirectionAdapter = new ProvideDirectionAdapter(R.layout.jiuyi_customer_supplier_item, mProvideDirectoryBeanList);
                            rvInfoList.setAdapter(provideDirectionAdapter);
                            provideDirectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    ProvideDirectoryBean.ContentBean contentBean=(ProvideDirectoryBean.ContentBean)provideDirectionAdapter.getData().get(position);
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
                            provideDirectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    if(view.getId()==R.id.tv_down){
                                        ProductDirectoryBean.ContentBean contentBean=(ProductDirectoryBean.ContentBean)productDirectionAdapter.getData().get(position);
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

                                }
                            });

                            if(mProvideDirectoryBeanList==null||mProvideDirectoryBeanList.size()==0){
                                provideDirectionAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                            }
                            showProcessBar(100);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }


}
