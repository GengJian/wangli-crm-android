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
import customer.Utils.ViewOperatorType;
import customer.adapter.CapacityInfoAdapter;
import customer.adapter.ComponentReliabilityAdapter;
import customer.adapter.CtmReportAdapter;
import customer.adapter.CustomerProductInfoAdapter;
import customer.adapter.FactoryEquipmentAdapter;
import customer.adapter.IqcMaterialAdapter;
import customer.adapter.ProductStandardAdapter;
import customer.adapter.PutIntoProductAdapter;
import customer.adapter.QualityStandardAdapter;
import customer.entity.AbstractSummaryBean;
import customer.entity.CapacityInfoBean;
import customer.entity.ComponentReliabilityBean;
import customer.entity.CtmReportBean;
import customer.entity.CustomerProductInfoBean;
import customer.entity.FactoryEquipmentBean;
import customer.entity.IqcMaterialBean;
import customer.entity.ProductStandardBean;
import customer.entity.PutIntoProductBean;
import customer.entity.QualityStandardBean;
import customer.view.MultiItemDivider;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerPurchaseStatusFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 采购动态
 *****************************************************************
 */
public class JiuyiCustomerProductStatusFragment extends JiuyiFragmentBase {

    private long Customerid=-1;
    private String Customername="";
    private String mRisktype="";
    RecyclerView rvInfoList;

    private List<ProductStandardBean.ContentBean> mProductStandardBeanList;
    private ProductStandardAdapter productStandardAdapter;
    private List<CustomerProductInfoBean.ContentBean> mCustomerProductInfoBeanList;
    private CustomerProductInfoAdapter customerProductInfoAdapter;
    private List<FactoryEquipmentBean.ContentBean> mFactoryEquipmentBeanList;
    private FactoryEquipmentAdapter factoryEquipmentAdapter;

    private List<CapacityInfoBean.ContentBean> mCapacityInfoBeanist;
    private CapacityInfoAdapter capacityInfoAdapter;

    private List<QualityStandardBean.ContentBean> mQualityStandardBeanList;
    private QualityStandardAdapter qualityStandardAdapter;


    private List<IqcMaterialBean.ContentBean> mIqcMaterialBeanList;
    private IqcMaterialAdapter iqcMaterialAdapter;
    private List<PutIntoProductBean.ContentBean> mPutIntoProductBeanList;
    private PutIntoProductAdapter putIntoProductAdapter;
    private List<CtmReportBean.ContentBean> mCtmReportBeanList;
    private CtmReportAdapter ctmReportAdapter;
    private List<ComponentReliabilityBean.ContentBean> mComponentReliabilityBeanList;
    private ComponentReliabilityAdapter componentReliabilityAdapter;

    QueryConditionBean queryConditionBean;
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pagesize = 20;
    private int totalPage=0;
    private ImageView ivNew;
    private LinearLayout llProductionStandard;
    private TextView tvProductionStandard;
    private LinearLayout llPoductInformation;
    private TextView tvPoductInformation;
    private LinearLayout llPoductEquipment;
    private TextView tvPoductEquipment;
    private LinearLayout llPoductCapacity;
    private TextView tvPoductCapacity;
    private LinearLayout llPoductQualityStandard;
    private TextView tvPoductQualityStandard;
    private LinearLayout llIqc;
    private TextView tvIqc;
    private LinearLayout llPoductDynamic;
    private TextView tvPoductDynamic;
    private LinearLayout llCtm;
    private TextView tvCtm;
    private LinearLayout llpoduct_component_reliability;
    private TextView tvpoduct_component_reliability;
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
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerproductstatusfragment_layout"), null);
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
        llProductionStandard = (LinearLayout) mRootView.findViewById(R.id.ll_production_standard);
        llProductionStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivNew.setVisibility(View.VISIBLE);
                currentBillType="ProductionStandard";
                backPageType="product-standard";
                refreshCurrenPage(0);
                sTitle="生产标准";
//                tvTitle.setText("生产标准");
            }
        });
        tvProductionStandard = (TextView) mRootView.findViewById(R.id.tv_production_standard);
        llPoductInformation = (LinearLayout) mRootView.findViewById(R.id.ll_poduct_information);
        llPoductInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivNew.setVisibility(View.VISIBLE);
                currentBillType="PoductInformation";
                backPageType="product-info";
                refreshCurrenPage(0);
                sTitle="产品信息";
//                tvTitle.setText("产品信息");
            }
        });
        tvPoductInformation = (TextView) mRootView.findViewById(R.id.tv_poduct_information);
        llPoductEquipment = (LinearLayout) mRootView.findViewById(R.id.ll_poduct_equipment);
        llPoductEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivNew.setVisibility(View.VISIBLE);
                currentBillType="PoductEquipment";
                backPageType="factory-equipment";
                refreshCurrenPage(0);
                sTitle="工厂设备";
//                tvTitle.setText("工厂设备");
            }
        });
        tvPoductEquipment = (TextView) mRootView.findViewById(R.id.tv_poduct_equipment);
        llPoductCapacity = (LinearLayout) mRootView.findViewById(R.id.ll_poduct_capacity);
        llPoductCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivNew.setVisibility(View.VISIBLE);
                currentBillType="PoductCapacity";
                backPageType="capacity-info";
                refreshCurrenPage(0);
                sTitle="产能信息";
//                tvTitle.setText("产能信息");
            }
        });
        tvPoductCapacity = (TextView) mRootView.findViewById(R.id.tv_poduct_capacity);
        llPoductQualityStandard = (LinearLayout) mRootView.findViewById(R.id.ll_poduct_quality_standard);
        llPoductQualityStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivNew.setVisibility(View.VISIBLE);
                currentBillType="PoductQualityStandard";
                backPageType="quality-standard";
                refreshCurrenPage(0);
                sTitle="品质标准";
//                tvTitle.setText("品质标准");
            }
        });
        tvPoductQualityStandard = (TextView) mRootView.findViewById(R.id.tv_poduct_quality_standard);
        llIqc = (LinearLayout) mRootView.findViewById(R.id.ll_iqc);
        llIqc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivNew.setVisibility(View.GONE);
                currentBillType="Iqc";
                backPageType="iqc-material";
                refreshCurrenPage(0);
                sTitle="IQC来料";
//                tvTitle.setText("IQC来料");
            }
        });
        tvIqc = (TextView) mRootView.findViewById(R.id.tv_iqc);
        llPoductDynamic = (LinearLayout) mRootView.findViewById(R.id.ll_poduct_dynamic);
        llPoductDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivNew.setVisibility(View.GONE);
                currentBillType="PoductDynamic";
                backPageType="put-into-product";
                refreshCurrenPage(0);
                sTitle="投产状况";
//                tvTitle.setText("投产状况");
            }
        });
        tvPoductDynamic = (TextView) mRootView.findViewById(R.id.tv_poduct_dynamic);
        llCtm = (LinearLayout) mRootView.findViewById(R.id.ll_ctm);
        llCtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivNew.setVisibility(View.GONE);
                currentBillType="Ctm";
                backPageType="ctm-report";
                refreshCurrenPage(0);
                sTitle="CTM报告";
//                tvTitle.setText("CTM报告");
            }
        });
        tvpoduct_component_reliability = (TextView) mRootView.findViewById(R.id.tv_poduct_component_reliability);
        llpoduct_component_reliability = (LinearLayout) mRootView.findViewById(R.id.ll_poduct_component_reliability);
        llpoduct_component_reliability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivNew.setVisibility(View.VISIBLE);
                currentBillType="PoductComponentReliability";
                backPageType="component-reliability";
                refreshCurrenPage(0);
                sTitle="组件可靠性认证";
//                tvTitle.setText("组件可靠性认证");
            }
        });
        tvCtm = (TextView) mRootView.findViewById(R.id.tv_ctm);
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
        MultiItemDivider itemDivider = new MultiItemDivider(JiuyiMainApplication.getIns(), MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
        rvInfoList.addItemDecoration(itemDivider);
//        rvInfoList.setItemAnimator(new DefaultItemAnimator());
//        rvInfoList.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

        rvInfoList.setNestedScrollingEnabled(false);
        rvInfoList.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        rvInfoList.setFocusable(false);
        currentBillType="ProductionStandard";
        backPageType="product-standard";
        sTitle="生产标准";
//        tvTitle.setText("生产标准");
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
//        if(Rc.mCurrentPageType==Pub.Customer_Contract){
//            showDialog();
//        }
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerProductStatusFragment newInstance(int nPageType) {
        JiuyiCustomerProductStatusFragment f = new JiuyiCustomerProductStatusFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerProductStatusFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerProductStatusFragment f = new JiuyiCustomerProductStatusFragment();
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

    public  void getInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<CustomerProductInfoBean>() {
                    @Override
                    public void onSuccess(CustomerProductInfoBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mCustomerProductInfoBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("产品信息("+data.getTotalElements()+")");
                                customerProductInfoAdapter = new CustomerProductInfoAdapter(R.layout.customer_item_productinfo, mCustomerProductInfoBeanList);
                                rvInfoList.setAdapter(customerProductInfoAdapter);
                                customerProductInfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        CustomerProductInfoBean.ContentBean contentBean=(CustomerProductInfoBean.ContentBean)customerProductInfoAdapter.getData().get(position);
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
                                if(mCustomerProductInfoBeanList==null||mCustomerProductInfoBeanList.size()==0){
                                    customerProductInfoAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                customerProductInfoAdapter.add(mCustomerProductInfoBeanList);
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
    public  void getFactoryEquipmentList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<FactoryEquipmentBean>() {
                    @Override
                    public void onSuccess(FactoryEquipmentBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mFactoryEquipmentBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("工厂设备("+data.getTotalElements()+")");
                                factoryEquipmentAdapter = new FactoryEquipmentAdapter(R.layout.customer_item_factoryequipment, mFactoryEquipmentBeanList);
                                rvInfoList.setAdapter(factoryEquipmentAdapter);
                                factoryEquipmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        FactoryEquipmentBean.ContentBean contentBean=(FactoryEquipmentBean.ContentBean)factoryEquipmentAdapter.getData().get(position);
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
                                if(mFactoryEquipmentBeanList==null||mFactoryEquipmentBeanList.size()==0){
                                    factoryEquipmentAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                factoryEquipmentAdapter.add(mFactoryEquipmentBeanList);
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

    public  void getCapacityInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<CapacityInfoBean>() {
                    @Override
                    public void onSuccess(CapacityInfoBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mCapacityInfoBeanist=data.getContent();
                            if(page==0){
                                tvTitle.setText("产能信息("+data.getTotalElements()+")");
                                capacityInfoAdapter = new CapacityInfoAdapter(R.layout.customer_item_capableinfo, mCapacityInfoBeanist);
                                rvInfoList.setAdapter(capacityInfoAdapter);
                                capacityInfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        CapacityInfoBean.ContentBean contentBean=(CapacityInfoBean.ContentBean)capacityInfoAdapter.getData().get(position);
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
                                if(mCapacityInfoBeanist==null||mCapacityInfoBeanist.size()==0){
                                    capacityInfoAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                capacityInfoAdapter.add(mCapacityInfoBeanist);
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
    public  void getQualityStandardList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<QualityStandardBean>() {
                    @Override
                    public void onSuccess(QualityStandardBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mQualityStandardBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("品质标准("+data.getTotalElements()+")");
                                qualityStandardAdapter = new QualityStandardAdapter(R.layout.customer_item_qualitystandard, mQualityStandardBeanList);
                                rvInfoList.setAdapter(qualityStandardAdapter);
                                qualityStandardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        QualityStandardBean.ContentBean contentBean=(QualityStandardBean.ContentBean)qualityStandardAdapter.getData().get(position);
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
                                if(mQualityStandardBeanList==null||mQualityStandardBeanList.size()==0){
                                    qualityStandardAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                qualityStandardAdapter.add(mQualityStandardBeanList);
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

    public  void getIqcMaterialList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<IqcMaterialBean>() {
                    @Override
                    public void onSuccess(IqcMaterialBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mIqcMaterialBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("IQC来料("+data.getTotalElements()+")");
                                iqcMaterialAdapter = new IqcMaterialAdapter(R.layout.customer_item_iqcmaterial, mIqcMaterialBeanList);
                                rvInfoList.setAdapter(iqcMaterialAdapter);
                                iqcMaterialAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        IqcMaterialBean.ContentBean contentBean=(IqcMaterialBean.ContentBean)iqcMaterialAdapter.getData().get(position);
//                                        if(contentBean!=null){
//                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
//                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
//                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
//                                            if(!Func.IsStringEmpty(sTitle)){
//                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, sTitle);
//                                            }
//
//
//                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
//                                        }
                                    }
                                });
                                if(mIqcMaterialBeanList==null||mIqcMaterialBeanList.size()==0){
                                    iqcMaterialAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                iqcMaterialAdapter.add(mIqcMaterialBeanList);
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

    public  void getPutIntoProductList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<PutIntoProductBean>() {
                    @Override
                    public void onSuccess(PutIntoProductBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mPutIntoProductBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("投产状况("+data.getTotalElements()+")");
                                putIntoProductAdapter = new PutIntoProductAdapter(R.layout.customer_item_putintoproduct, mPutIntoProductBeanList);
                                rvInfoList.setAdapter(putIntoProductAdapter);
                                putIntoProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        PutIntoProductBean.ContentBean contentBean=(PutIntoProductBean.ContentBean)putIntoProductAdapter.getData().get(position);
//                                        if(contentBean!=null){
//                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
//                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
//                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
//                                            if(!Func.IsStringEmpty(sTitle)){
//                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, sTitle);
//                                            }
//
//
//                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
//                                        }
                                    }
                                });
                                if(mPutIntoProductBeanList==null||mPutIntoProductBeanList.size()==0){
                                    putIntoProductAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                putIntoProductAdapter.add(mPutIntoProductBeanList);
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

    public  void getCtmReportList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<CtmReportBean>() {
                    @Override
                    public void onSuccess(CtmReportBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mCtmReportBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("CTM报告("+data.getTotalElements()+")");
                                ctmReportAdapter = new CtmReportAdapter(R.layout.customer_item_putintoproduct, mCtmReportBeanList);
                                rvInfoList.setAdapter(ctmReportAdapter);
                                ctmReportAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        CtmReportBean.ContentBean contentBean=(CtmReportBean.ContentBean)ctmReportAdapter.getData().get(position);
//                                        if(contentBean!=null){
//                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
//                                            mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
//                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
//                                            if(!Func.IsStringEmpty(sTitle)){
//                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, sTitle);
//                                            }
//
//
//                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
//                                        }
                                    }
                                });
                                if(mCtmReportBeanList==null||mCtmReportBeanList.size()==0){
                                    ctmReportAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                ctmReportAdapter.add(mCtmReportBeanList);
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

    public  void getComponentReliabilityList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ComponentReliabilityBean>() {
                    @Override
                    public void onSuccess(ComponentReliabilityBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mComponentReliabilityBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("组件可靠性认证("+data.getTotalElements()+")");
                                componentReliabilityAdapter = new ComponentReliabilityAdapter(R.layout.customer_item_componentreliability, mComponentReliabilityBeanList);
                                rvInfoList.setAdapter(componentReliabilityAdapter);
                                componentReliabilityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        ComponentReliabilityBean.ContentBean contentBean=(ComponentReliabilityBean.ContentBean)componentReliabilityAdapter.getData().get(position);
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
                                if(mComponentReliabilityBeanList==null||mComponentReliabilityBeanList.size()==0){
                                    componentReliabilityAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                componentReliabilityAdapter.add(mComponentReliabilityBeanList);
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




    public  void getProductStandardList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductStandardBean>() {
                    @Override
                    public void onSuccess(ProductStandardBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mProductStandardBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("生产标准("+data.getTotalElements()+")");
                                productStandardAdapter = new ProductStandardAdapter(R.layout.customer_item_productstardard, mProductStandardBeanList);
                                rvInfoList.setAdapter(productStandardAdapter);
                                productStandardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        ProductStandardBean.ContentBean contentBean=(ProductStandardBean.ContentBean)productStandardAdapter.getData().get(position);
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

                                if(mProductStandardBeanList==null||mProductStandardBeanList.size()==0){
                                    productStandardAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                productStandardAdapter.add(mProductStandardBeanList);
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
        if(currentBillType.equals("ProductionStandard")){
            getProductStandardList(page);
        }else if(currentBillType.equals("PoductInformation")){
            getInfoList(page);
        }else if(currentBillType.equals("PoductEquipment")){
            getFactoryEquipmentList(page);
        }else if(currentBillType.equals("PoductCapacity")){
            getCapacityInfoList(page);
        }else if(currentBillType.equals("PoductQualityStandard")){
            getQualityStandardList(page);
        }else if(currentBillType.equals("Iqc")){
            getIqcMaterialList(page);
        }else if(currentBillType.equals("PoductDynamic")){
            getPutIntoProductList(page);
        }else if(currentBillType.equals("Ctm")){
            getCtmReportList(page);
        }else if(currentBillType.equals("PoductComponentReliability")){
            getComponentReliabilityList(page);
        }
        if(page==0){
            getAbstractSummaryList();
        }

    }
    private void getAbstractSummaryList() {
        String apiUrl="product-standard/abstract-summary/"+Customerid;
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
                                if(abstractSummaryBean.getField().equals("ProductStandard")){
                                    if(tvProductionStandard!=null){
                                        tvProductionStandard.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("ProductInfo")){
                                    if(tvPoductInformation!=null){
                                        tvPoductInformation.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("FactoryEquipment")){
                                    if(tvPoductEquipment!=null){
                                        tvPoductEquipment.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("CapacityInfo")){
                                    if(tvPoductCapacity!=null){
                                        tvPoductCapacity.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("ComponentReliability")){
                                    if(tvpoduct_component_reliability!=null){
                                        tvpoduct_component_reliability.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("QualityStandard")){
                                    if(tvPoductQualityStandard!=null){
                                        tvPoductQualityStandard.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("IqcMaterial")){
                                    if(tvIqc!=null){
                                        tvIqc.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("PutIntoProduct")){
                                    if(tvPoductDynamic!=null){
                                        tvPoductDynamic.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("CtmReport")){
                                    if(tvCtm!=null){
                                        tvCtm.setText(abstractSummaryBean.getCount()+"");
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
