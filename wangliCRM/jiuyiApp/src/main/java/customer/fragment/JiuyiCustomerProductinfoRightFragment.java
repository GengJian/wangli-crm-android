package customer.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ProductBillType;
import customer.Utils.ViewOperatorType;
import customer.adapter.CompeteInfoAdapter;
import customer.adapter.EquipmentAdapter;
import customer.adapter.ProductInfoAdapter;
import customer.adapter.ProductbondAdapter;
import customer.adapter.ProductcheckAdapter;
import customer.adapter.ProductdynamicAdapter;
import customer.adapter.ProductimportAdapter;
import customer.adapter.ProductlandAdapter;
import customer.adapter.ProductlicenseAdapter;
import customer.adapter.ProductmaterialAdapter;
import customer.adapter.ProductpurchaseAdapter;
import customer.adapter.ProducttaxAdapter;
import customer.adapter.RecruitmentAdapter;
import customer.entity.CompeteInfoBean;
import customer.entity.EquipmentBean;
import customer.entity.ProductbondBean;
import customer.entity.ProductcheckBean;
import customer.entity.ProductdynamicBean;
import customer.entity.ProductimportBean;
import customer.entity.ProductinfoBean;
import customer.entity.ProductlandBean;
import customer.entity.ProductlicenseBean;
import customer.entity.ProductmaterialBean;
import customer.entity.ProductpurchaseBean;
import customer.entity.ProducttaxBean;
import customer.entity.RecruitmentBean;
import customer.view.MultiItemDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerProductinfoRightFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户生产状况右边部分信息
 *****************************************************************
 */
public class JiuyiCustomerProductinfoRightFragment extends JiuyiFragmentBase {
    RecyclerView rv_productlist;
    private List<ProductinfoBean.ContentBean> mProductinfoBeanList;
    private ProductInfoAdapter adapter;
    private List<EquipmentBean.ContentBean> equipmentBeanList;
    private EquipmentAdapter equipmentadapter;
    private List<ProductdynamicBean.ContentBean> mProductdynamicBeanList;
    private ProductdynamicAdapter productdynamicadapter;
    private List<ProductmaterialBean.ContentBean> mProductmaterialBeanList;
    private ProductmaterialAdapter productmaterialadapter;
    private List<CompeteInfoBean.ContentBean> mcompeteinfoBeanList;
    private CompeteInfoAdapter competeInfoAdapter;
    private List<RecruitmentBean.ContentBean> mrecruitmentBeanList;
    private RecruitmentAdapter recruitmentAdapter;
    private List<ProductlicenseBean.ContentBean> mproductlicenseBeanList;
    private ProductlicenseAdapter productlicenseAdapter;
    private List<ProductpurchaseBean.ContentBean> mproductpurchaseBeanList;
    private ProductpurchaseAdapter productpurchaseAdapter;
    private List<ProductimportBean.ContentBean> mproductimportBeanList;
    private ProductimportAdapter productimportAdapter;
    private List<ProducttaxBean.ContentBean> mproducttaxBeanList;
    private ProducttaxAdapter producttaxAdapter;
    private List<ProductcheckBean.ContentBean> mproductcheckBeanList;
    private ProductcheckAdapter productcheckAdapter;
    private List<ProductbondBean.ContentBean> mproductbondBeanList;
    private ProductbondAdapter productbondAdapter;
    private List<ProductlandBean.ContentBean> mproductlandBeanList;
    private ProductlandAdapter productlandAdapter;
    private TextView tv_operation,tv_newrecord,tv_title,tv_producttype,tv_productname,tv_productcomponent;
    private String producttype;
    private  int pageIndex=1,pagesize=20,totalPage=0;
    RefreshLayout refreshLayout;
    private long Customerid=-1;
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
//        initImmersionBar();
        return mRootView;
    }
    /**
     * 初始化
     */
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
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
        tv_newrecord = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_newrecord"));
        tv_newrecord.setVisibility(View.VISIBLE);
        pageIndex=1;
        if(producttype.equals("产品信息")){
            mProductinfoBeanList = new ArrayList<>();
            tv_producttype.setText("产品名称");
            tv_productname.setText("成分含量");
            tv_productcomponent.setText("产品克重");
            getProductInfoList(0);

        }else if(producttype.equals("工厂设备")){
            equipmentBeanList=new ArrayList<>();
            tv_producttype.setText("设备类型");
            tv_productname.setText("设备名称");
            tv_productcomponent.setText("设备数量");
            equipmentBeanList = new ArrayList<>();
            getequipmentInfoList(0);


        }else if(producttype.equals("生产动态")){
            tv_producttype.setText("设备类型");
            tv_productname.setText("设备名称");
            tv_productcomponent.setText("开机率");
            mProductdynamicBeanList = new ArrayList<>();
            getProductdynamicInfoList(0);

        }else if(producttype.equals("原料信息")){
            tv_producttype.setText("原料类型");
            tv_productname.setText("原料名称");
            tv_productcomponent.setText("年采购量");
            mProductmaterialBeanList = new ArrayList<>();
            getProductmaterialInfoList(0);

        }else if(producttype.equals("竞品信息")){
            tv_producttype.setText("竞品企业");
            tv_productname.setText("竞品品牌");
            tv_productcomponent.setText("吨/月平均");
            mcompeteinfoBeanList = new ArrayList<>();
            getCompeteinfoInfoList(0);
        }else if(producttype.equals("工厂招工")){
            tv_producttype.setText("类型");
            tv_productname.setText("标题");
            tv_productcomponent.setText("日期");
            mrecruitmentBeanList = new ArrayList<>();
            getRecruitmentInfoList(0);
        }else if(producttype.equals("生产许可")){
            tv_producttype.setText("许可类型");
            tv_productname.setText("标题");
            tv_productcomponent.setText("日期");
            mproductlicenseBeanList = new ArrayList<>();
            getProductlicenseInfoList(0);
        }else if(producttype.equals("采购招标")){
            tv_producttype.setText("类型");
            tv_productname.setText("标题");
            tv_productcomponent.setText("日期");
            mproductpurchaseBeanList = new ArrayList<>();
            getProductpurchaseInfoList(0);
        }else if(producttype.equals("进出口信息")){
            tv_producttype.setText("类型");
            tv_productname.setText("标题");
            tv_productcomponent.setText("日期");
            mproductimportBeanList = new ArrayList<>();
           getProductimportInfoList(0);
        }else if(producttype.equals("税务评级")){
            tv_producttype.setText("类型");
            tv_productname.setText("标题");
            tv_productcomponent.setText("日期");
            mproducttaxBeanList = new ArrayList<>();
            getProducttaxInfoList(0);
        }else if(producttype.equals("抽查检查")){
            tv_producttype.setText("类型");
            tv_productname.setText("标题");
            tv_productcomponent.setText("日期");
            mproductcheckBeanList = new ArrayList<>();
            getProductcheckInfoList(0);
        }else if(producttype.equals(ProductBillType.PRODUCTBOND)){
            tv_producttype.setText("债券编号");
            tv_productname.setText("债券名称");
            tv_productcomponent.setText("发行日");
            mproductbondBeanList = new ArrayList<>();
            getProductbondInfoList(0);
            tv_newrecord.setVisibility(View.INVISIBLE);
        }else if(producttype.equals(ProductBillType.PRODUCTLAND)){
            tv_producttype.setText("crm编号");
            tv_productname.setText("行政区");
            tv_productcomponent.setText("签订日期");
            mproductlandBeanList = new ArrayList<>();
            getProductlandInfoList(0);
            tv_newrecord.setVisibility(View.INVISIBLE);
        }
        if(tv_newrecord!=null){
            tv_newrecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.ADD);
                    if(producttype.equals("产品信息")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductinfo);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductinfo,true);
                    }else if(producttype.equals("工厂设备")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductequipment);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductequipment,true);
                    }else if(producttype.equals("生产动态")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductdynamic);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductdynamic,true);
                    }else if(producttype.equals("原料信息")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductmaterial);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductmaterial,true);
                    }else if(producttype.equals("竞品信息")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newcompeteinfo);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newcompeteinfo,true);
                    }else if(producttype.equals("工厂招工")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newrecruitment);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newrecruitment,true);
                    }else if(producttype.equals("生产许可")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductlicense);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductlicense,true);
                    }else if(producttype.equals("采购招标")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductpurchase);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductpurchase,true);
                    }else if(producttype.equals("进出口信息")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductimport);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductimport,true);
                    }else if(producttype.equals("税务评级")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproducttax);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproducttax,true);
                    }else if(producttype.equals("抽查检查")){
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductcheck);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductcheck,true);
                    }

                }
            });
        }
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
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
        });
        showProcessBar(0);
//        initImmersionBar();
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


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerProductinfoRightFragment newInstance(int nPageType) {
        JiuyiCustomerProductinfoRightFragment f = new JiuyiCustomerProductinfoRightFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerProductinfoRightFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerProductinfoRightFragment f = new JiuyiCustomerProductinfoRightFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }

    public  void  getProductInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("product/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<ProductinfoBean>() {
                            @Override
                            public void onSuccess(ProductinfoBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mProductinfoBeanList=data.getContent();
                                    if(page==0){
                                        if(adapter==null){
                                            adapter = new ProductInfoAdapter(R.layout.customer_productinfo_item, mProductinfoBeanList);
                                            rv_productlist.setAdapter(adapter);
                                            adapter.setOnCLickItemListener(new ProductInfoAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(int position, String colname, View view) {
                                                    if(colname.equals("tv_operation")){
                                                        curpostion=position;
                                                        startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                    }else if(colname.equals("tv_productname")){
                                                        ProductinfoBean.ContentBean contentBean=adapter.getData().get(position);
                                                        if(contentBean!=null){
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductinfo);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductinfo,true);
                                                        }

                                                    }
                                                }
                                            });
                                        }else{
                                            adapter.refresh(mProductinfoBeanList);
                                        }
                                        if(mProductinfoBeanList.size()==0||mProductinfoBeanList==null){
                                            adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        adapter.add(mProductinfoBeanList);
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
    public  void  getequipmentInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("equipment/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<EquipmentBean>() {
                    @Override
                    public void onSuccess(EquipmentBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            equipmentBeanList=data.getContent();
                            if(equipmentBeanList!=null){
                                if(page==0){
                                    if(equipmentadapter==null){
                                        equipmentadapter = new EquipmentAdapter(R.layout.customer_equipment_item, equipmentBeanList);
                                        rv_productlist.setAdapter(equipmentadapter);
                                        equipmentadapter.setOnCLickItemListener(new EquipmentAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_equipmenttype")){
                                                    EquipmentBean.ContentBean contentBean=equipmentadapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductequipment);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductequipment,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        equipmentadapter.refresh(equipmentBeanList);
                                    }
                                    if(equipmentBeanList.size()==0||equipmentBeanList==null){
                                        equipmentadapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }

                                    showProcessBar(100);
                                }else{
                                    equipmentadapter.add(equipmentBeanList);
                                    showProcessBar(100);
                                }
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
    public  void  getProductdynamicInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("performance/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductdynamicBean>() {
                    @Override
                    public void onSuccess(ProductdynamicBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mProductdynamicBeanList=data.getContent();
                            if(mProductdynamicBeanList!=null){
                                if(page==0){
                                    if(productdynamicadapter==null){
                                        productdynamicadapter = new ProductdynamicAdapter(R.layout.customer_productdynamic_item, mProductdynamicBeanList);
                                        rv_productlist.setAdapter(productdynamicadapter);
                                        productdynamicadapter.setOnCLickItemListener(new ProductdynamicAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_equipmenttype")){
                                                    ProductdynamicBean.ContentBean contentBean=productdynamicadapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductdynamic);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductdynamic,true);
                                                    }
                                                }
                                            }
                                        });
                                    }else{
                                        productdynamicadapter.refresh(mProductdynamicBeanList);
                                    }
                                    if(mProductdynamicBeanList.size()==0||mProductdynamicBeanList==null){
                                        productdynamicadapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                }else{
                                    productdynamicadapter.add(mProductdynamicBeanList);
                                }
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

    public  void  getProductmaterialInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("row_material/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductmaterialBean>() {
                    @Override
                    public void onSuccess(ProductmaterialBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mProductmaterialBeanList=data.getContent();
                            if(mProductmaterialBeanList!=null){
                                if(page==0){
                                    if(productmaterialadapter==null){
                                        productmaterialadapter = new ProductmaterialAdapter(R.layout.customer_productmaterial_item, mProductmaterialBeanList);
                                        rv_productlist.setAdapter(productmaterialadapter);
                                        productmaterialadapter.setOnCLickItemListener(new ProductmaterialAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_materialtype")){
                                                    ProductmaterialBean.ContentBean contentBean=productmaterialadapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductmaterial);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductmaterial,true);
                                                    }
                                                }
                                            }
                                        });
                                    }else{
                                        productmaterialadapter.refresh(mProductmaterialBeanList);
                                    }
                                    if(mProductmaterialBeanList.size()==0||mProductmaterialBeanList==null){
                                        productmaterialadapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                }else{
                                    productmaterialadapter.add(mProductmaterialBeanList);
                                }
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
    public  void  getCompeteinfoInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("competition_product/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<CompeteInfoBean>() {
                    @Override
                    public void onSuccess(CompeteInfoBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mcompeteinfoBeanList=data.getContent();
                            if(mcompeteinfoBeanList!=null){
                                if(page==0){
                                    if(competeInfoAdapter==null){
                                        competeInfoAdapter = new CompeteInfoAdapter(R.layout.customer_competeinfo_item, mcompeteinfoBeanList);
                                        rv_productlist.setAdapter(competeInfoAdapter);
                                        competeInfoAdapter.setOnCLickItemListener(new CompeteInfoAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_competecompany")){
                                                    CompeteInfoBean.ContentBean contentBean=competeInfoAdapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newcompeteinfo);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newcompeteinfo,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        competeInfoAdapter.refresh(mcompeteinfoBeanList);
                                    }
                                    if(mcompeteinfoBeanList.size()==0||mcompeteinfoBeanList==null){
                                        competeInfoAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                    showProcessBar(100);
                                }else{
                                    competeInfoAdapter.add(mcompeteinfoBeanList);
                                    showProcessBar(100);
                                }
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

    public  void  getRecruitmentInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("recruitment/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<RecruitmentBean>() {
                    @Override
                    public void onSuccess(RecruitmentBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mrecruitmentBeanList=data.getContent();
                            if(mrecruitmentBeanList!=null){
                                if(page==0){
                                    if(recruitmentAdapter==null){
                                        recruitmentAdapter = new RecruitmentAdapter(R.layout.customer_recruitment_item, mrecruitmentBeanList);
                                        rv_productlist.setAdapter(recruitmentAdapter);
                                        recruitmentAdapter.setOnCLickItemListener(new RecruitmentAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_recruitmenttype")){
                                                    RecruitmentBean.ContentBean contentBean=recruitmentAdapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newrecruitment);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newrecruitment,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        recruitmentAdapter.refresh(mrecruitmentBeanList);
                                    }
                                    if(mrecruitmentBeanList.size()==0||mrecruitmentBeanList==null){
                                        recruitmentAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                }else{
                                    recruitmentAdapter.add(mrecruitmentBeanList);
                                }
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


    public  void  getProductlicenseInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("production_license/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductlicenseBean>() {
                    @Override
                    public void onSuccess(ProductlicenseBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mproductlicenseBeanList=data.getContent();
                            if(mproductlicenseBeanList!=null){
                                if(page==0){
                                    if(productlicenseAdapter==null){
                                        productlicenseAdapter = new ProductlicenseAdapter(R.layout.customer_productlicense_item, mproductlicenseBeanList);
                                        rv_productlist.setAdapter(productlicenseAdapter);
                                        productlicenseAdapter.setOnCLickItemListener(new ProductlicenseAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_licensetype")){
                                                    ProductlicenseBean.ContentBean contentBean=productlicenseAdapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductlicense);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductlicense,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        productlicenseAdapter.refresh(mproductlicenseBeanList);
                                    }
                                    if(mproductlicenseBeanList.size()==0||mproductlicenseBeanList==null){
                                        productlicenseAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                    showProcessBar(100);
                                }else{
                                    productlicenseAdapter.add(mproductlicenseBeanList);
                                    showProcessBar(100);
                                }
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


    public  void  getProductpurchaseInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("purchase_tender/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductpurchaseBean>() {
                    @Override
                    public void onSuccess(ProductpurchaseBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mproductpurchaseBeanList=data.getContent();
                            if(mproductpurchaseBeanList!=null){
                                if(page==0){
                                    if(productpurchaseAdapter==null){
                                        productpurchaseAdapter = new ProductpurchaseAdapter(R.layout.customer_productpurchase_item, mproductpurchaseBeanList);
                                        rv_productlist.setAdapter(productpurchaseAdapter);
                                        productpurchaseAdapter.setOnCLickItemListener(new ProductpurchaseAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_purchasetype")){
                                                    ProductpurchaseBean.ContentBean contentBean=productpurchaseAdapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductpurchase);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductpurchase,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        productpurchaseAdapter.refresh(mproductpurchaseBeanList);
                                    }
                                    if(mproductpurchaseBeanList.size()==0||mproductpurchaseBeanList==null){
                                        productpurchaseAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                    showProcessBar(100);
                                }else{
                                    productpurchaseAdapter.add(mproductpurchaseBeanList);
                                    showProcessBar(100);
                                }
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

    public  void  getProductimportInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("in_ex_port_info/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductimportBean>() {
                    @Override
                    public void onSuccess(ProductimportBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mproductimportBeanList=data.getContent();
                            if(mproductimportBeanList!=null){
                                if(page==0){
                                    if(productimportAdapter==null){
                                        productimportAdapter = new ProductimportAdapter(R.layout.customer_productimport_item, mproductimportBeanList);
                                        rv_productlist.setAdapter(productimportAdapter);
                                        productimportAdapter.setOnCLickItemListener(new ProductimportAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_importtype")){
                                                    ProductimportBean.ContentBean contentBean=productimportAdapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductimport);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductimport,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        productimportAdapter.refresh(mproductimportBeanList);
                                    }
                                    if(mproductimportBeanList.size()==0||mproductimportBeanList==null){
                                        productimportAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                }else{
                                    productimportAdapter.add(mproductimportBeanList);
                                }
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


    public  void  getProducttaxInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("tax_rating/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProducttaxBean>() {
                    @Override
                    public void onSuccess(ProducttaxBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mproducttaxBeanList=data.getContent();
                            if(mproducttaxBeanList!=null){
                                if(page==0){
                                    if(producttaxAdapter==null){
                                        producttaxAdapter = new ProducttaxAdapter(R.layout.customer_producttax_item, mproducttaxBeanList);
                                        rv_productlist.setAdapter(producttaxAdapter);
                                        producttaxAdapter.setOnCLickItemListener(new ProducttaxAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_taxtype")){
                                                    ProducttaxBean.ContentBean contentBean=producttaxAdapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproducttax);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproducttax,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        producttaxAdapter.refresh(mproducttaxBeanList);
                                    }
                                    if(mproducttaxBeanList.size()==0||mproducttaxBeanList==null){
                                        producttaxAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                    showProcessBar(100);
                                }else{
                                    producttaxAdapter.add(mproducttaxBeanList);
                                    showProcessBar(100);
                                }
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


    public  void  getProductcheckInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("spot_check/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductcheckBean>() {
                    @Override
                    public void onSuccess(ProductcheckBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mproductcheckBeanList=data.getContent();
                            if(mproductcheckBeanList!=null){
                                if(page==0){
                                    if(productcheckAdapter==null){
                                        productcheckAdapter = new ProductcheckAdapter(R.layout.customer_productcheck_item, mproductcheckBeanList);
                                        rv_productlist.setAdapter(productcheckAdapter);
                                        productcheckAdapter.setOnCLickItemListener(new ProductcheckAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_operation")){
                                                    curpostion=position;
                                                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                }else if(colname.equals("tv_checktype")){
                                                    ProductcheckBean.ContentBean contentBean=productcheckAdapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductcheck);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductcheck,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        productcheckAdapter.refresh(mproductcheckBeanList);
                                    }
                                    if(mproductcheckBeanList.size()==0||mproductcheckBeanList==null){
                                        productcheckAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                }else{
                                    productcheckAdapter.add(mproductcheckBeanList);
                                }
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
    public  void  getProductbondInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("bond_info/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductbondBean>() {
                    @Override
                    public void onSuccess(ProductbondBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mproductbondBeanList=data.getContent();
                            if(mproductbondBeanList!=null){
                                if(page==0){
                                    if(productbondAdapter==null){
                                        productbondAdapter = new ProductbondAdapter(R.layout.customer_productbond_item, mproductbondBeanList);
                                        rv_productlist.setAdapter(productbondAdapter);
                                        productbondAdapter.setOnCLickItemListener(new ProductbondAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                 if(colname.equals("tv_bondnum")||colname.equals("tv_operation")){
                                                    ProductbondBean.ContentBean contentBean=productbondAdapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductbond);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductbond,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        productbondAdapter.refresh(mproductbondBeanList);
                                    }
                                    if(mproductbondBeanList.size()==0||mproductbondBeanList==null){
                                        productbondAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                }else{
                                    productbondAdapter.add(mproductbondBeanList);
                                }
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
    public  void  getProductlandInfoList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("purchase_land/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<ProductlandBean>() {
                    @Override
                    public void onSuccess(ProductlandBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mproductlandBeanList=data.getContent();
                            if(mproductlandBeanList!=null){
                                if(page==0){
                                    if(productlandAdapter==null){
                                        productlandAdapter = new ProductlandAdapter(R.layout.customer_productland_item, mproductlandBeanList);
                                        rv_productlist.setAdapter(productlandAdapter);
                                        productlandAdapter.setOnCLickItemListener(new ProductlandAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_crmnum")||colname.equals("tv_operation")){
                                                    ProductlandBean.ContentBean contentBean=productlandAdapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newproductland);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID, contentBean.getId());
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newproductland,true);
                                                    }

                                                }
                                            }
                                        });
                                    }else{
                                        productlandAdapter.refresh(mproductlandBeanList);
                                    }
                                    if(mproductlandBeanList.size()==0||mproductlandBeanList==null){
                                        productlandAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
                                    }
                                }else{
                                    productlandAdapter.add(mproductlandBeanList);
                                }
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

    public void deleteProduct(long id){
        JiuyiHttp.DELETE("product/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(adapter!=null && curpostion!=-1){
                                adapter.remove(curpostion);
                                adapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(adapter.getData()==null||adapter.getData().size()==0){
                                    adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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
    public void deleteEquipment(long id){
        JiuyiHttp.DELETE("equipment/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(equipmentadapter!=null && curpostion!=-1){
                                equipmentadapter.remove(curpostion);
                                equipmentadapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(equipmentadapter.getData()==null||equipmentadapter.getData().size()==0){
                                    equipmentadapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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

    public void deleteProductdynamic(long id){
        JiuyiHttp.DELETE("performance/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(productdynamicadapter!=null && curpostion!=-1){
                                productdynamicadapter.remove(curpostion);
                                productdynamicadapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(productdynamicadapter.getData()==null||productdynamicadapter.getData().size()==0){
                                    productdynamicadapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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
    public void deleteProductmaterial(long id){
        JiuyiHttp.DELETE("row_material/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(productmaterialadapter!=null && curpostion!=-1){
                                productmaterialadapter.remove(curpostion);
                                productmaterialadapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(productmaterialadapter.getData()==null||productmaterialadapter.getData().size()==0){
                                    productmaterialadapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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
    public void deleteCompeteInfo(long id){
        JiuyiHttp.DELETE("competition_product/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(competeInfoAdapter!=null && curpostion!=-1){
                                competeInfoAdapter.remove(curpostion);
                                competeInfoAdapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(competeInfoAdapter.getData()==null||competeInfoAdapter.getData().size()==0){
                                    competeInfoAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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
    public void deleteRecruitment(long id){
        JiuyiHttp.DELETE("recruitment/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(recruitmentAdapter!=null && curpostion!=-1){
                                recruitmentAdapter.remove(curpostion);
                                recruitmentAdapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(recruitmentAdapter.getData()==null||recruitmentAdapter.getData().size()==0){
                                    recruitmentAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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
    public void deleteProductlicense(long id){
        JiuyiHttp.DELETE("production_license/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(productlicenseAdapter!=null && curpostion!=-1){
                                productlicenseAdapter.remove(curpostion);
                                productlicenseAdapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(productlicenseAdapter.getData()==null||productlicenseAdapter.getData().size()==0){
                                    productlicenseAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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
    public void deleteProductpurchase(long id){
        JiuyiHttp.DELETE("purchase_tender/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(productpurchaseAdapter!=null && curpostion!=-1){
                                productpurchaseAdapter.remove(curpostion);
                                productpurchaseAdapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(productpurchaseAdapter.getData()==null||productpurchaseAdapter.getData().size()==0){
                                    productpurchaseAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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
    public void deleteProductimport(long id){
        JiuyiHttp.DELETE("in_ex_port_info/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(productimportAdapter!=null && curpostion!=-1){
                                productimportAdapter.remove(curpostion);
                                productimportAdapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(productimportAdapter.getData()==null||productimportAdapter.getData().size()==0){
                                    productimportAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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
    public void deleteProducttax(long id){
        JiuyiHttp.DELETE("tax_rating/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(producttaxAdapter!=null && curpostion!=-1){
                                producttaxAdapter.remove(curpostion);
                                producttaxAdapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(producttaxAdapter.getData()==null||producttaxAdapter.getData().size()==0){
                                    producttaxAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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
    public void deleteProductcheck(long id){
        JiuyiHttp.DELETE("spot_check/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(productcheckAdapter!=null && curpostion!=-1){
                                productcheckAdapter.remove(curpostion);
                                productcheckAdapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(productcheckAdapter.getData()==null||productcheckAdapter.getData().size()==0){
                                    productcheckAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rv_productlist.getParent());
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

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction== DialogID.DialogDoNothing)
        {
            if(nKeyCode == KeyEvent.KEYCODE_BACK ){
              return;
            }
        }else if(nAction== DialogID.DialogDeleteTrue){
            if(nKeyCode == KeyEvent.KEYCODE_ENTER){
                if(ProductBillType.PRODUCTINFO.equals(producttype)){
                    if(adapter!=null && curpostion!=-1){
                        deleteProduct(adapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.EQUIPMENT.equals(producttype)){
                    if(equipmentadapter!=null && curpostion!=-1){
                        deleteEquipment(equipmentadapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.COMPETEINFO.equals(producttype)){
                    if(competeInfoAdapter!=null && curpostion!=-1){
                        deleteCompeteInfo(competeInfoAdapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.PRODUCTCHECK.equals(producttype)){
                    if(productcheckAdapter!=null && curpostion!=-1){
                        deleteProductcheck(productcheckAdapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.PRODUCTDYNAMIC.equals(producttype)){
                    if(productdynamicadapter!=null && curpostion!=-1){
                        deleteProductdynamic(productdynamicadapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.PRODUCTIMPORT.equals(producttype)){
                    if(productimportAdapter!=null && curpostion!=-1){
                        deleteProductimport(productimportAdapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.PRODUCTLICENSE.equals(producttype)){
                    if(productlicenseAdapter!=null && curpostion!=-1){
                        deleteProductlicense(productlicenseAdapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.PRODUCTMATERIAL.equals(producttype)){
                    if(productmaterialadapter!=null && curpostion!=-1){
                        deleteProductmaterial(productmaterialadapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.PRODUCTTAX.equals(producttype)){
                    if(producttaxAdapter!=null && curpostion!=-1){
                        deleteProducttax(producttaxAdapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.RECRUITMENT.equals(producttype)){
                    if(recruitmentAdapter!=null && curpostion!=-1){
                        deleteRecruitment(recruitmentAdapter.getData().get(curpostion).getId());
                    }
                }else if(ProductBillType.PRODUCTPURCHASE.equals(producttype)){
                    if(productpurchaseAdapter!=null && curpostion!=-1){
                        deleteProductpurchase(productpurchaseAdapter.getData().get(curpostion).getId());
                    }
                }
            }
        }
    }
    public void refreshCurrenPage(int page){
        if(ProductBillType.PRODUCTINFO.equals(producttype)){
            getProductInfoList(page);
        }else if(ProductBillType.EQUIPMENT.equals(producttype)){
            getequipmentInfoList(page);
        }else if(ProductBillType.COMPETEINFO.equals(producttype)){
            getCompeteinfoInfoList(page);
        }else if(ProductBillType.PRODUCTCHECK.equals(producttype)){
            getProductcheckInfoList(page);
        }else if(ProductBillType.PRODUCTDYNAMIC.equals(producttype)){
            getProductdynamicInfoList(page);
        }else if(ProductBillType.PRODUCTIMPORT.equals(producttype)){
            getProductimportInfoList(page);
        }else if(ProductBillType.PRODUCTLICENSE.equals(producttype)){
            getProductlicenseInfoList(page);
        }else if(ProductBillType.PRODUCTMATERIAL.equals(producttype)){
            getProductmaterialInfoList(page);
        }else if(ProductBillType.PRODUCTTAX.equals(producttype)){
            getProducttaxInfoList(page);
        }else if(ProductBillType.RECRUITMENT.equals(producttype)){
            getRecruitmentInfoList(page);
        }else if(ProductBillType.PRODUCTBOND.equals(producttype)){
            getProductbondInfoList(page);
        }else if(ProductBillType.PRODUCTLAND.equals(producttype)){
            getProductlandInfoList(page);
        }
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
