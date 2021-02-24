package customer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.CustomerQueryConditionBean;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;


import java.util.ArrayList;
import java.util.List;

import commonlyused.adapter.TaskListAdapter;
import commonlyused.bean.ContactBean;
import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.QueryConditionBean;
import commonlyused.bean.TaskBean;
import customer.Utils.KeyBoardUtils;
import customer.adapter.ActivitySelectAdapter;
import customer.adapter.AreaSelectAdapter;
import customer.adapter.BXCitySelectAdapter;
import customer.adapter.BandSelectAdapter;
import customer.adapter.BatchSelectAdapter;
import customer.adapter.BusinessSelectAdapter;
import customer.adapter.CitySelectAdapter;
import customer.adapter.ClueSelectAdapter;
import customer.adapter.ContactSelectAdapter;
import customer.adapter.CountrySelectAdapter;
import customer.adapter.CustomerDepartmentSelectAdapter;
import customer.adapter.CustomerPersonSelectAdapter;
import customer.adapter.CustomerSelectAdapter;
import customer.adapter.CustomerSelectNewAdapter;
import customer.adapter.DepartmentSelectAdapter;
import customer.adapter.IntelligenceItemSelectAdapter;
import customer.adapter.MaterialSelectAdapter;
import customer.adapter.PersonSelectAdapter;
import customer.adapter.ProductBigTypeSelectAdapter;
import customer.adapter.ProvinceSelectAdapter;
import customer.adapter.SinglePersonSelectAdapter;
import customer.adapter.SingleProductBigTypeSelectAdapter;
import customer.adapter.SpecialProductAdapter;
import customer.entity.BXCityBean;
import customer.entity.BatchNumberWeightBean;
import customer.entity.BrandBean;
import customer.entity.CitySearchBean;
import customer.entity.CountryBean;
import customer.entity.CustomerLinkmanBean;
import customer.entity.DepartmentBean;
import customer.entity.MarketIntelligenceItemBean;
import customer.entity.MaterialBean;
import customer.entity.MaterialTypeBean;
import customer.entity.MemberBean;
import customer.entity.MemberListBean;
import customer.entity.OfficeBean;
import customer.entity.PersonSelectBean;
import customer.entity.ProductBatchSelectBean;
import customer.entity.ProvinceBean;
import customer.entity.RegionSearchBean;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.adapter.OrderListAdapter;
import dynamic.entity.DyActivityBean;
import dynamic.entity.DyBusinessBean;
import dynamic.entity.DyClueBean;
import dynamic.entity.OrderBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerSelectActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 客户信息修改页面
 *****************************************************************
 */
public class JiuyiCustomerSelectActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,mtvcancel;

    private String operatorType;

    RecyclerView swipeMenuRecyclerView;
    CustomerSelectAdapter menuAdapter;
    CustomerSelectNewAdapter customerSelectNewAdapter;
    RefreshLayout refreshLayout;
    private TextView tv_emptytext;
    int pageIndex = 1;
    int pageSize = 20;
    private int totalPage=0;
    private List<MemberListBean.ContentBean> mCustomerListBeanList;
    private List<MemberBean.ContentBean> mCustomerNewListBeanList;
    private JiuyiEditText etSearch;
    private String billType="";
    private int currentPosition=-1;
    private String selectName="",spec,weight,levelName,levelcode,factoryName, SelectCode,sOACode, msDuetotal,prescription;
    private String productPlace="";
    private long selectId=0;
    private long batchID=0,orderID=0;
    private long Customerid=0;
    private String billID;
    private List<MaterialBean.ContentBean> mMaterialBeanList;
   MaterialSelectAdapter materialAdapter;
   SpecialProductAdapter specialProductAdapter;

    private List<ContactBean.ContentBean> mContactBeanList;
    ContactSelectAdapter contactSelectAdapter;
    private List<OrderBean.ContentBean> mOrderBeanList;
    OrderListAdapter orderSelectAdapter;
    private List<TaskBean.ContentBean> mTaskBeanList;
    TaskListAdapter taskSelectAdapter;
    private List<PersonSelectBean> mPersonBeanList;
    PersonSelectAdapter personSelectAdapter;
    private String identify;
    private List<PersonSelectBean> mPersonBeanSelectList;

    private List<BatchNumberWeightBean.ContentBean> mBatchnumList;
    BatchSelectAdapter batchSelectAdapter;

    ActivitySelectAdapter  dynamicActivityAdapter;
    ClueSelectAdapter clueSelectAdapter;
    BusinessSelectAdapter businessSelectAdapter;
    IntelligenceItemSelectAdapter intelligenceItemSelectAdapter;
    ProductBigTypeSelectAdapter productBigTypeSelectAdapter;
    BandSelectAdapter bandSelectAdapter;
    SingleProductBigTypeSelectAdapter singleProductBigTypeSelectAdapter;
    DepartmentSelectAdapter departmentSelectAdapter;
    CustomerDepartmentSelectAdapter customerDepartmentSelectAdapter;
    CustomerPersonSelectAdapter customerPersonSelectAdapter;
    SinglePersonSelectAdapter singlePersonSelectAdapter;

    CountrySelectAdapter countrySelectAdapter;
    ProvinceSelectAdapter provinceSelectAdapter;
    CitySelectAdapter citySelectAdapter;
    AreaSelectAdapter areaSelectAdapter;
    BXCitySelectAdapter bxCitySelectAdapter;
    private List<BrandBean.ContentBean> brandBeanSelectList;
    private MemberBean.ContentBean contentBean;
    private String specialCondition="",msProvince="",msProvinceSelect;
    private CitySearchBean.ContentBean cityBean;
    private RegionSearchBean.ContentBean areaBean;
    private BrandBean.ContentBean bandBean;

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customerselect_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        if(mBundle!=null){
            billType=mBundle.getString(JiuyiBundleKey.PARAM_BILLTYPE);
            specialCondition=mBundle.getString(JiuyiBundleKey.PARAM_SPECIALCONDITION);
            if(specialCondition==null){
                specialCondition="";
            }
            msProvince=mBundle.getString(JiuyiBundleKey.PARAM_PROVINCE);
            if(msProvince==null){
                msProvince="";
            }
            msProvinceSelect=mBundle.getString(JiuyiBundleKey.PARAM_PROVINCESELECT);
            if(msProvinceSelect==null){
                msProvinceSelect="";
            }
            billID=mBundle.getString(JiuyiBundleKey.PARAM_BILLID);
            if(billID==null){
                billID="";
            }
            if(billType==null){
                billType="";
            }
            Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
            identify=mBundle.getString(JiuyiBundleKey.PARAM_IDENTIFY);
            if(billType.equals("BRAND")){
                brandBeanSelectList =new ArrayList<>();
                brandBeanSelectList =mBundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
            }else{
                mPersonBeanSelectList=new ArrayList<>();
                mPersonBeanSelectList=mBundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
            }

        }


        if(identify==null){
            identify="";
        }
        setTitle();
        tv_emptytext=(TextView)mBodyLayout.findViewById(Res.getViewID(null, "tv_emptytext"));
        etSearch=(JiuyiEditText)mBodyLayout.findViewById(Res.getViewID(null, "et_search"));
//        etSearch.addTextChangedListener(  );
        SetTextChanged(etSearch);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyBoardUtils.hideSoftInput(etSearch);
                    if(billType.equals("material")){
                        getMaterialList(0);
                    }else if(billType.equals("specialproduct")){
                        getSpecialList(0);
                    }else if(billType.equals("CLIENT")){
                        getClientList(0);
                    }else if(billType.equals("CUSTOMER")){
                        getCustomer2List(0);
                    }else if(billType.equals("CONTACTS")){
                        getContactList(0);
                    }else if(billType.equals("ORDER")){
                        getOrderList(0);
                    }else if(billType.equals("TASK")){
                        getTaskList(0);
                    }else if(billType.equals("Person")){
                        getPersonList(0);
                    }else if(billType.equals("Batchnum")){
                        getBatchNumList(0);
                    }else if(billType.equals("ACTIVITY")){
                        getActivityList(0);
                    }else if(billType.equals("CLUE")){
                        getClueList(0);
                    }else if(billType.equals("BUSINESS")){
                        getBusinessList(0);
                    }else if(billType.equals("INTELLIGENCE")){
                        getIntelgenceList(0);
                    }else if(billType.equals("BRAND")){
                        getBrandList(0);
                    }else if(billType.equals("SINGLEBRAND")){
                        getSingleProductBigTypeList(0);
                    }else if(billType.equals("DEPARTMENT")){
                        getDepartmentList(0);
                    }else if(billType.equals("CUSTOMERDEPARTMENT")){
                        getCustomerDepartmentList(0);
                    }else if(billType.equals("CUSTOMERPERSON")){
                        getContractByMemberIdList(0);
                    }else if(billType.equals("SINGLEPERSON")){
                        getSinglePersonList(0);
                    }else if(billType.equals("COUNTRY")){
                        getCountryList(0);
                    }else if(billType.equals("BXCITY")){
                        getBXCityList();
                    }else if(billType.equals("CITY")){
                        getCityList(0);
                    }else if(billType.equals("AREA")){
                        getAreaList(0);
                    }else if(billType.equals("PROVINCE")){
                        getProvinceList(0);
                    }else {
                        getCustomerList(0);
                    }
                    return true;
                }
                return false;
            }
        });


        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            if(!billType.equals("Person")/*&&!billType.equals("BRAND")*/&&!billType.equals("PROVINCE")&&!billType.equals("SINGLEPERSON")){
                mtvcomplete.setVisibility(View.INVISIBLE);
            }
            if(billType.equals("PROVINCE")){
                mtvcomplete.setText("重置");
            }
            if(billType.equals("SINGLEPERSON")){
                mtvcomplete.setText("置空");
            }

            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(billType.equals("Person")||billType.equals("BRAND")||billType.equals("PROVINCE")||billType.equals("SINGLEPERSON")){
                        setBackActivityBundle();
                        backPage();
                    }
                }
            });
        }
        mtvcancel = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_cancel"));
        if (mtvcancel != null) {
            mtvcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
                }
            });

        }
        swipeMenuRecyclerView = (RecyclerView) mBodyLayout.findViewById(R.id.recycler_view);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(JiuyiCustomerSelectActivity.this));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiCustomerSelectActivity.this, LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

        if(billType.equals("material")){
            etSearch.setHint("搜索批号");
            getMaterialList(0);
        }else if(billType.equals("specialproduct")){
            etSearch.setHint("搜索批号");
            getSpecialList(0);
        }else if(billType.equals("CLIENT")){
            etSearch.setHint("搜索客户");
            getClientList(0);
        }else if(billType.equals("CUSTOMER")){
            etSearch.setHint("搜索客户");
            getCustomer2List(0);
        }else if(billType.equals("CONTACTS")){
            etSearch.setHint("搜索客户或者联系人");
            getContactList(0);
        }else if(billType.equals("ORDER")){
            etSearch.setHint("搜索订单");
            getOrderList(0);
        }else if(billType.equals("TASK")){
            etSearch.setHint("搜索任务");
            getTaskList(0);
        }else if(billType.equals("Person")){
            etSearch.setHint("请输入姓名/部门名称/职务");
            getPersonList(0);
        }else if(billType.equals("Batchnum")){
            etSearch.setHint("请输入批号/规格/等级");
            getBatchNumList(0);
        }else if(billType.equals("ACTIVITY")){
            etSearch.setHint("搜索活动");
            getActivityList(0);
        }else if(billType.equals("CLUE")){
            etSearch.setHint("搜索线索");
            getClueList(0);
        }else if(billType.equals("BUSINESS")){
            etSearch.setHint("搜索商机");
            getBusinessList(0);
        }else if(billType.equals("INTELLIGENCE")){
            etSearch.setHint("搜索情报");
            getIntelgenceList(0);
        }else if(billType.equals("BRAND")){
            etSearch.setHint("搜索品牌");
            getBrandList(0);
        }else if(billType.equals("SINGLEBRAND")){
            etSearch.setHint("搜索产品大类");
            getSingleProductBigTypeList(0);
        }else if(billType.equals("DEPARTMENT")){
            etSearch.setHint("搜索部门");
            getDepartmentList(0);
        }else if(billType.equals("CUSTOMERDEPARTMENT")){
            etSearch.setHint("搜索部门");
            getCustomerDepartmentList(0);
        }else if(billType.equals("CUSTOMERPERSON")){
            etSearch.setHint("搜索人员");
            getContractByMemberIdList(0);
        }else if(billType.equals("SINGLEPERSON")){
            etSearch.setHint("搜索人员");
            getSinglePersonList(0);
        }else if(billType.equals("BXCITY")){
            etSearch.setHint("搜索城市");
            getBXCityList();
        }else if(billType.equals("COUNTRY")){
            etSearch.setHint("搜索国家");
            getCountryList(0);
        }else if(billType.equals("PROVINCE")){
            etSearch.setHint("搜索省份");
            getProvinceList(0);
        }else if(billType.equals("CITY")){
            etSearch.setHint("搜索地区");
            getCityList(0);
        }else if(billType.equals("AREA")){
            etSearch.setHint("搜索县镇或地辖区");
            getAreaList(0);
        }else {
            getCustomerList(0);
        }

        RefreshLayout refreshLayout = (RefreshLayout)mBodyLayout.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                if(billType.equals("material")){
                    getMaterialList(0);
                }else if(billType.equals("specialproduct")){
                    getSpecialList(0);
                }else if(billType.equals("CLIENT")){
                    getClientList(0);
                }else if(billType.equals("CONTACTS")){
                    getContactList(0);
                }else if(billType.equals("CUSTOMER")){
                    getCustomer2List(0);
                }else if(billType.equals("ORDER")){
                    getOrderList(0);
                }else if(billType.equals("TASK")){
                    getTaskList(0);
                }else if(billType.equals("Person")){
                    getPersonList(0);
                }else if(billType.equals("Batchnum")){
                    getBatchNumList(0);
                }else if(billType.equals("ACTIVITY")){
                    getActivityList(0);
                }else if(billType.equals("CLUE")){
                    getClueList(0);
                }else if(billType.equals("BUSINESS")){
                    getBusinessList(0);
                }else if(billType.equals("INTELLIGENCE")){
                    getIntelgenceList(0);
                }else if(billType.equals("BRAND")){
                    getBrandList(0);
                }else if(billType.equals("SINGLEBRAND")){
                    getSingleProductBigTypeList(0);
                }else if(billType.equals("DEPARTMENT")){
                    getDepartmentList(0);
                }else if(billType.equals("CUSTOMERDEPARTMENT")){
                    getCustomerDepartmentList(0);
                }else if(billType.equals("CUSTOMERPERSON")){
                    getContractByMemberIdList(0);
                }else if(billType.equals("SINGLEPERSON")){
                    getSinglePersonList(0);
                }else if(billType.equals("BXCITY")){
                    getBXCityList();
                }else if(billType.equals("COUNTRY")){
                    getCountryList(0);
                }else if(billType.equals("PROVINCE")){
                    getProvinceList(0);
                }else if(billType.equals("CITY")){
                    getCityList(0);
                }else if(billType.equals("AREA")){
                    getAreaList(0);
                }else {
                    getCustomerList(0);
                }
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
                    if(billType.equals("material")){
                        getMaterialList(pageIndex);
                    }else if(billType.equals("specialproduct")){
                        getSpecialList(pageIndex);
                    }else if(billType.equals("CLIENT")){
                        getClientList(pageIndex);
                    }else if(billType.equals("CUSTOMER")){
                        getCustomer2List(pageIndex);
                    }else if(billType.equals("CONTACTS")){
                        getContactList(pageIndex);
                    }else if(billType.equals("ORDER")){
                        getOrderList(pageIndex);
                    }else if(billType.equals("TASK")){
                        getTaskList(pageIndex);
                    }else if(billType.equals("Person")){
                        getPersonList(pageIndex);
                    }else if(billType.equals("Batchnum")){
                        getBatchNumList(pageIndex);
                    }else if(billType.equals("ACTIVITY")){
                        getActivityList(pageIndex);
                    }else if(billType.equals("CLUE")){
                        getClueList(pageIndex);
                    }else if(billType.equals("BUSINESS")){
                        getBusinessList(pageIndex);
                    }else if(billType.equals("INTELLIGENCE")){
                        getIntelgenceList(pageIndex);
                    }else if(billType.equals("BRAND")){
                        getBrandList(pageIndex);
                    }else if(billType.equals("SINGLEBRAND")){
                        getSingleProductBigTypeList(pageIndex);
                    }else if(billType.equals("DEPARTMENT")){
                        getDepartmentList(pageIndex);
                    }else if(billType.equals("CUSTOMERDEPARTMENT")){
                        getCustomerDepartmentList(pageIndex);
                    }else if(billType.equals("CUSTOMERPERSON")){
                        getContractByMemberIdList(pageIndex);
                    }else if(billType.equals("SINGLEPERSON")){
                        getSinglePersonList(pageIndex);
                    }else if(billType.equals("BXCITY")){
                        return;
                    }else if(billType.equals("COUNTRY")){
                        getCountryList(pageIndex);
                    }else if(billType.equals("PROVINCE")){
                        getProvinceList(pageIndex);
                    }else if(billType.equals("CITY")){
                        getCityList(pageIndex);
                    }else if(billType.equals("AREA")){
                        getAreaList(pageIndex);
                    }else {
                        getCustomerList(pageIndex);
                    }
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;
            }
        });
        showProcessBar(0);

    }

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }

    public void setTitle(){
        if(billType.equals("material")||billType.equals("specialproduct")){
            mTitle = "选择产品批号";
        }else if(billType.equals("CLIENT")){
            mTitle = "选择客户";
        }else if(billType.equals("CUSTOMER")){
            mTitle = "选择客户";
        }else if(billType.equals("CONTACTS")){
            mTitle = "选择联系人";
        }else if(billType.equals("ORDER")){
            mTitle = "选择订单";
        }else if(billType.equals("TASK")){
            mTitle = "选择任务";
        }else if(billType.equals("Person")){
            mTitle = "选择人员";
        }else if(billType.equals("Batchnum")){
            mTitle = "选择批号";
        }else if(billType.equals("ACTIVITY")){
            mTitle = "选择活动";
        }else if(billType.equals("CLUE")){
            mTitle = "选择线索";
        }else if(billType.equals("BUSINESS")){
            mTitle = "选择商机";
        }else if(billType.equals("INTELLIGENCE")){
            mTitle = "选择情报";
        }else if(billType.equals("BRAND")){
            mTitle = "选择品牌";
        }else if(billType.equals("SINGLEBRAND")){
            mTitle = "选择产品大类";
        }else if(billType.equals("DEPARTMENT")){
            mTitle = "选择部门";
        }else if(billType.equals("CUSTOMERDEPARTMENT")){
            mTitle = "选择部门";
        }else if(billType.equals("SINGLEPERSON")){
            mTitle = "选择人员";
        }else if(billType.equals("BXCITY")){
            mTitle = "选择城市";
        }else if(billType.equals("CUSTOMERPERSON")){
            mTitle = "选择人员";
        }else if(billType.equals("COUNTRY")){
            mTitle = "选择国家";
        }else if(billType.equals("PROVINCE")){
            mTitle = "选择省份";
        }else if(billType.equals("CITY")){
            mTitle = "选择地区";
        }else if(billType.equals("AREA")){
            mTitle = "选择县级镇或地辖区";
        }else{
            mTitle = "选择客户";
        }
        setTitle(mTitle);
    }

    public  void  getCustomerList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");

        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();

        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("orgName");
            rulesBean.setOption("LIKE_ANYWHERE");
            List<String> value = new ArrayList<String>();
            value.add(etSearch.getText().toString().trim());
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }

//        if(!Func.IsStringEmpty(billType)){
//            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
//            rulesBean.setField("salesman.id");
//            rulesBean.setOption("IS_NOT_NULL");
//            rulesBeanList.add(rulesBean);
//
//            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
//            rulesBean2.setField("sapNumber");
//            rulesBean2.setOption("IS_NOT_NULL");
//            rulesBeanList.add(rulesBean2);
//            QueryConditionBean.RulesBean rulesBean3=new QueryConditionBean.RulesBean();
//            rulesBean3.setField("knkli");
//            rulesBean3.setOption("IS_NOT_NULL");
//            rulesBeanList.add(rulesBean3);
//        }
        queryConditionBean.setRules(rulesBeanList);
        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("member/find-page")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<MemberListBean>() {
                            @Override
                            public void onSuccess(MemberListBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<MemberListBean.ContentBean> ContentBeanlist =data.getContent();
                                    mCustomerListBeanList =data.getContent();
                                    if(page==0){
                                        if(menuAdapter==null){
                                            menuAdapter=new CustomerSelectAdapter(R.layout.jiuyi_select_item_listview, mCustomerListBeanList);
                                            swipeMenuRecyclerView.setAdapter(menuAdapter);
                                            menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    selectName=menuAdapter.getData().get(position).getOrgName();
                                                    selectId=menuAdapter.getData().get(position).getId();
                                                    if(billType.equals("order")){
                                                        Bundle mBundle = new Bundle();
                                                        String urladd=Res.getString(null, "jiuyineworder");
                                                        String url="";
                                                        if(!Func.IsStringEmpty(urladd)){
                                                            url= Constant.BaseH5Url+urladd+"?id="+selectId+"&token="+ Rc.id_tokenparam;
                                                        }
                                                        mBundle.putString(JiuyiBundleKey.PARAM_HIDECLIENTTITLE, "1");
                                                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"新订单");
                                                        changePage(mBundle,10061,false);
                                                    }else {
                                                        setBackActivityBundle();
                                                        backPage();
                                                    }
                                                }
                                            });
                                        }else{
                                            menuAdapter.refresh(mCustomerListBeanList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        menuAdapter.add(ContentBeanlist);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);

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

    public  void  getCustomer2List(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();


        if(!Func.IsStringEmpty(msProvince) ){
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("provinceNumber");
            rulesBean.setOption("EQ");
            List<String> value = new ArrayList<String>();
            value.add(msProvince);
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("orgName");
            rulesBean.setOption("LIKE_ANYWHERE");
            List<String> value = new ArrayList<String>();
            value.add(etSearch.getText().toString().trim());
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
//        if(!Func.IsStringEmpty(specialCondition) && specialCondition.equals("Y")){
//            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
//            rulesBean.setField("cooperationTypeKey");
//            rulesBean.setOption("IN");
//            List<String> value = new ArrayList<String>();
//            value.add("direct_selling");
//            value.add("double_distribution");
//            rulesBean.setValues(value);
//            rulesBeanList.add(rulesBean);
//        }
        queryConditionBean.setRules(rulesBeanList);
        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("member/page")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<MemberBean>() {
                            @Override
                            public void onSuccess(MemberBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<MemberBean.ContentBean> ContentBeanlist =data.getContent();
                                    mCustomerNewListBeanList =data.getContent();
                                    if(page==0){
                                        if(customerSelectNewAdapter==null){
                                            customerSelectNewAdapter=new CustomerSelectNewAdapter(R.layout.jiuyi_select_item_listview, mCustomerNewListBeanList);
                                            swipeMenuRecyclerView.setAdapter(customerSelectNewAdapter);
                                            customerSelectNewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    contentBean=customerSelectNewAdapter.getData().get(position);
                                                    setBackActivityBundle();
                                                    backPage();
                                                }
                                            });
                                        }else{
                                            customerSelectNewAdapter.refresh(mCustomerNewListBeanList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        customerSelectNewAdapter.add(ContentBeanlist);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);

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

    public  void  getClientList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();

        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("orgName");
            rulesBean.setOption("LIKE_ANYWHERE");
            List<String> value = new ArrayList<String>();
            value.add(etSearch.getText().toString().trim());
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }

        queryConditionBean.setRules(rulesBeanList);
        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("member/send_able/"+identify)
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<MemberListBean>() {
                            @Override
                            public void onSuccess(MemberListBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<MemberListBean.ContentBean> ContentBeanlist =data.getContent();
                                    mCustomerListBeanList =data.getContent();
                                    if(page==0){
                                        if(menuAdapter==null){
                                            menuAdapter=new CustomerSelectAdapter(R.layout.jiuyi_select_item_listview, mCustomerListBeanList);
                                            swipeMenuRecyclerView.setAdapter(menuAdapter);
                                            menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    selectName=menuAdapter.getData().get(position).getOrgName();
                                                    selectId=menuAdapter.getData().get(position).getId();
                                                    if(billType.equals("order")){
                                                        Bundle mBundle = new Bundle();
                                                        String urladd=Res.getString(null, "jiuyineworder");
                                                        String url="";
                                                        if(!Func.IsStringEmpty(urladd)){
                                                            url=Constant.BaseH5Url+urladd+"?id="+selectId+"&token="+ Rc.id_tokenparam;
                                                        }
                                                        mBundle.putString(JiuyiBundleKey.PARAM_HIDECLIENTTITLE, "1");
                                                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"新订单");
                                                        changePage(mBundle,10061,false);
                                                    }else {
//                                                        if(menuAdapter.getData().get(position).getOwedTotalAmount()!=null){
//                                                            msOwetotal="￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(menuAdapter.getData().get(position).getOwedTotalAmount()+""),0,2,true);
//                                                        }else{
//                                                            msOwetotal="￥"+"0";
//                                                        }
//                                                        if(menuAdapter.getData().get(position).getDueTotalAmount()!=null){
//                                                            msDuetotal="￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(menuAdapter.getData().get(position).getDueTotalAmount()+""),0,2,true);
//                                                        }else{
//                                                            msDuetotal="￥"+"0";
//                                                        }
                                                        setBackActivityBundle();
                                                        backPage();
                                                    }
                                                }
                                            });
                                        }else{
                                            menuAdapter.refresh(mCustomerListBeanList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        menuAdapter.add(ContentBeanlist);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
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

    public  void  getMaterialList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
//        List<String> specialConditionslist=new ArrayList<>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
       /*     specialConditionslist.add(etSearch.getText().toString().trim());
            queryConditionBean.setSpecialConditions(specialConditionslist);*/
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("batchNumber");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        queryConditionBean.setRules(rulesBeanList);

        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("material/batch-number/page/"+Customerid)
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<MaterialBean>() {
                            @Override
                            public void onSuccess(MaterialBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<MaterialBean.ContentBean> ContentBeanlist =data.getContent();
                                    mMaterialBeanList =data.getContent();
                                    if(page==0){
                                        if(materialAdapter==null){
                                            materialAdapter=new MaterialSelectAdapter(R.layout.jiuyi_select_item_listview, mMaterialBeanList);
                                            swipeMenuRecyclerView.setAdapter(materialAdapter);
                                            materialAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    selectName=materialAdapter.getData().get(position).getBatchNumber();
                                                    factoryName=materialAdapter.getData().get(position).getFactoryName();
                                                    SelectCode =materialAdapter.getData().get(position).getFactoryCode();
                                                    levelName=materialAdapter.getData().get(position).getProductLevelName();
                                                    levelcode=materialAdapter.getData().get(position).getProductLevel();
                                                    weight=materialAdapter.getData().get(position).getWeight()+"";
                                                    if(materialAdapter.getData().get(position).getPrescription()!=null){
                                                        prescription=materialAdapter.getData().get(position).getPrescription();
                                                    }
                                                    setBackActivityBundle();
                                                    backPage();
                                                }
                                            });
                                        }else{
                                            materialAdapter.refresh(mMaterialBeanList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        materialAdapter.add(ContentBeanlist);
//                                        swipeMenuRecyclerView.setAdapter(materialAdapter);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
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

    public  void  getBatchNumList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("batchNumber-spec-productLevelName");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        queryConditionBean.setRules(rulesBeanList);

        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("batch-number-weight/page")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<BatchNumberWeightBean>() {
                            @Override
                            public void onSuccess(BatchNumberWeightBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<BatchNumberWeightBean.ContentBean> ContentBeanlist =data.getContent();
                                    mBatchnumList =data.getContent();
                                    if(page==0){
                                        if(batchSelectAdapter==null){
                                            batchSelectAdapter=new BatchSelectAdapter(R.layout.jiuyi_select_item_listview, mBatchnumList);
                                            swipeMenuRecyclerView.setAdapter(batchSelectAdapter);
                                            batchSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    selectName=batchSelectAdapter.getData().get(position).getBatchNumber();
                                                    batchID=batchSelectAdapter.getData().get(position).getId();
                                                    factoryName=batchSelectAdapter.getData().get(position).getFactoryName();
                                                    SelectCode =batchSelectAdapter.getData().get(position).getFactoryCode();
                                                    levelName=batchSelectAdapter.getData().get(position).getProductLevelName();
                                                    levelcode=batchSelectAdapter.getData().get(position).getProductLevel();
                                                    spec=batchSelectAdapter.getData().get(position).getSpec();
                                                    weight=batchSelectAdapter.getData().get(position).getWeight()+"";
                                                    if(batchSelectAdapter.getData().get(position).getPrescription()!=null){
                                                        prescription=batchSelectAdapter.getData().get(position).getPrescription();
                                                    }
                                                    setBackActivityBundle();
                                                    backPage();
                                                }
                                            });
                                        }else{
                                            batchSelectAdapter.refresh(mBatchnumList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        batchSelectAdapter.add(ContentBeanlist);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
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


    public  void  getSpecialList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
//        List<String> specialConditionslist=new ArrayList<>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
       /*     specialConditionslist.add(etSearch.getText().toString().trim());
            queryConditionBean.setSpecialConditions(specialConditionslist);*/
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("batchNumber");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        queryConditionBean.setRules(rulesBeanList);

        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("material/find-batch")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<ProductBatchSelectBean>() {
                            @Override
                            public void onSuccess(ProductBatchSelectBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<String> ContentBeanlist =data.getContent();
                                    if(page==0){
                                        if(specialProductAdapter==null){
                                            specialProductAdapter=new SpecialProductAdapter(R.layout.jiuyi_select_item_listview, ContentBeanlist);
                                            swipeMenuRecyclerView.setAdapter(specialProductAdapter);
                                            specialProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    selectName=specialProductAdapter.getData().get(position);
                                                    setBackActivityBundle();
                                                    backPage();
                                                }
                                            });
                                        }else{
                                            specialProductAdapter.refresh(ContentBeanlist);
                                        }
                                        showProcessBar(100);
                                    }else{
                                        specialProductAdapter.add(ContentBeanlist);
//                                        swipeMenuRecyclerView.setAdapter(specialProductAdapter);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
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
    public  void getBXCityList(){
        String url="";
        List<DictBean> dictBeansList= DictUtils.getDictList("travel_api");
        for(DictBean dictBean :dictBeansList){
            if(dictBean.getValue()!=null){
                url= dictBean.getValue();
            }
        }
        if(Func.IsStringEmpty( url )){
            url="http://112.17.59.161:10000/api/travel/getCity";
        }else{
            url+="/api/travel/getCity";
        }
        BXCityBean bxCityBean=new BXCityBean();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            bxCityBean.setCityname(etSearch.getText().toString().trim());
//            url+="?cityname="+etSearch.getText().toString().trim();
        }
        JiuyiHttp.POST(url)
                .addHeader("Authorization", Rc.id_tokenparam)
                .setJson(GsonUtil.gson().toJson(bxCityBean))
                .request(new ACallback<List<BXCityBean>>() {
                    @Override
                    public void onSuccess(List<BXCityBean> data) {
                        if(data!=null){
                            List<BXCityBean> contentBeanList=data;
                            bxCitySelectAdapter = new BXCitySelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                            swipeMenuRecyclerView.setAdapter(bxCitySelectAdapter);
                            bxCitySelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    selectName="";
                                    selectName=bxCitySelectAdapter.getData().get(position).getCityname();
                                    setBackActivityBundle();
                                    backPage();
                                }
                            });
                            if(contentBeanList.size()==0||contentBeanList==null){
                                bxCitySelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                            }

                            showProcessBar(100);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }


    public  void  getContactList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
//        List<String> specialConditionslist=new ArrayList<>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
       /*     specialConditionslist.add(etSearch.getText().toString().trim());
            queryConditionBean.setSpecialConditions(specialConditionslist);*/
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("name-member.orgName-member.abbreviation");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        queryConditionBean.setRules(rulesBeanList);

        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("contact/send_able/"+identify)
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<ContactBean>() {
                            @Override
                            public void onSuccess(ContactBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<ContactBean.ContentBean> ContentBeanlist =data.getContent();
                                    mContactBeanList =data.getContent();
                                    if(page==0){
                                        if(contactSelectAdapter==null){
                                            contactSelectAdapter=new ContactSelectAdapter(R.layout.jiuyi_select_item_listview, mContactBeanList);
                                            swipeMenuRecyclerView.setAdapter(contactSelectAdapter);
                                            contactSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    selectName="";
                                                    if(contactSelectAdapter.getData().get(position).getMember()!=null){
                                                        if(contactSelectAdapter.getData().get(position).getMember().getAbbreviation()!=null){
                                                            selectName+= contactSelectAdapter.getData().get(position).getMember().getAbbreviation()+"-";
                                                        }
                                                    }
                                                    if(contactSelectAdapter.getData().get(position).getName()!=null){
                                                        selectName+=contactSelectAdapter.getData().get(position).getName();
                                                    }
                                                    selectId=contactSelectAdapter.getData().get(position).getId();
                                                    setBackActivityBundle();
                                                    backPage();
                                                }
                                            });
                                        }else{
                                            contactSelectAdapter.refresh(mContactBeanList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        contactSelectAdapter.add(ContentBeanlist);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
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
    public  void getActivityList(final int page){
        String type="";
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");


        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("title-content");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("market-activity/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DyActivityBean>() {
                    @Override
                    public void onSuccess(DyActivityBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<DyActivityBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                dynamicActivityAdapter = new ActivitySelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(dynamicActivityAdapter);
                                dynamicActivityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        selectName="";
                                        selectName=dynamicActivityAdapter.getData().get(position).getTitle();
                                        selectId=dynamicActivityAdapter.getData().get(position).getId();
                                        setBackActivityBundle();
                                        backPage();
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    dynamicActivityAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                dynamicActivityAdapter.add(contentBeanList);
                            }
                            showProcessBar(100);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

    public  void getClueList(final int page){
        String type="";
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");


        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("title-content");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("clue/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DyClueBean>() {
                    @Override
                    public void onSuccess(DyClueBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<DyClueBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                clueSelectAdapter = new ClueSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(clueSelectAdapter);
                                clueSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        selectName="";
                                        selectName=clueSelectAdapter.getData().get(position).getTitle();
                                        selectId=clueSelectAdapter.getData().get(position).getId();
                                        setBackActivityBundle();
                                        backPage();
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    clueSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                clueSelectAdapter.add(contentBeanList);
                            }
                            showProcessBar(100);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

    public  void getBusinessList(final int page){
        String type="";
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");


        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("title-content");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("business-chance/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DyBusinessBean>() {
                    @Override
                    public void onSuccess(DyBusinessBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<DyBusinessBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                businessSelectAdapter = new BusinessSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(businessSelectAdapter);
                                businessSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        selectName="";
                                        selectName=businessSelectAdapter.getData().get(position).getTitle();
                                        selectId=businessSelectAdapter.getData().get(position).getId();
                                        setBackActivityBundle();
                                        backPage();
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    businessSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                businessSelectAdapter.add(contentBeanList);
                            }
                            showProcessBar(100);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }



    public  void getIntelgenceList(final int page){
        String type="";
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");


        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("member.orgName-content-operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("market-intelligence-item/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<MarketIntelligenceItemBean>() {
                    @Override
                    public void onSuccess(MarketIntelligenceItemBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<MarketIntelligenceItemBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                intelligenceItemSelectAdapter = new IntelligenceItemSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(intelligenceItemSelectAdapter);
                                intelligenceItemSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        selectName="";
                                        selectName=intelligenceItemSelectAdapter.getData().get(position).getContent();
                                        selectId=intelligenceItemSelectAdapter.getData().get(position).getId();
                                        setBackActivityBundle();
                                        backPage();
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    intelligenceItemSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                intelligenceItemSelectAdapter.add(contentBeanList);
                            }
                            showProcessBar(100);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

    public  void getCountryList(final int page){
        String type="";
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(200);
        queryConditionBean.setDirection("ASC");
        queryConditionBean.setProperty("land1");
        queryConditionBean.setFromClientType("android");


        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("landx");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("region/country-page")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<CountryBean>() {
                    @Override
                    public void onSuccess(CountryBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<CountryBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                countrySelectAdapter = new CountrySelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(countrySelectAdapter);
                                countrySelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        selectName="";
                                        selectName=countrySelectAdapter.getData().get(position).getLandx();
                                        SelectCode=countrySelectAdapter.getData().get(position).getLand1();
                                        setBackActivityBundle();
                                        backPage();
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    countrySelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                countrySelectAdapter.add(contentBeanList);
                            }
                            showProcessBar(100);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

    public  void getProvinceList(final int page){
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(200);
        queryConditionBean.setDirection("ASC");
        queryConditionBean.setProperty("id");
        queryConditionBean.setFromClientType("android");


        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("provinceName");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("region/queryProvinceInfoByPage")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ProvinceBean>() {
                    @Override
                    public void onSuccess(ProvinceBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<ProvinceBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                provinceSelectAdapter = new ProvinceSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(provinceSelectAdapter);
                                provinceSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        selectName="";
                                        selectName=provinceSelectAdapter.getData().get(position).getProvinceName();
                                        SelectCode=provinceSelectAdapter.getData().get(position).getProvinceId();
                                        setBackActivityBundle();
                                        backPage();
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    provinceSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                provinceSelectAdapter.add(contentBeanList);
                            }
                            showProcessBar(100);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    public  void getCityList(final int page){
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(200);
        queryConditionBean.setDirection("ASC");
        queryConditionBean.setProperty("id");
        queryConditionBean.setFromClientType("android");
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(msProvinceSelect) ){
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("provinceId");
            rulesBean.setOption("EQ");
            List<String> value = new ArrayList<String>();
            value.add(msProvinceSelect);
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("cityName");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("region/queryCityInfoByPage")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<CitySearchBean>() {
                    @Override
                    public void onSuccess(CitySearchBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<CitySearchBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                citySelectAdapter = new CitySelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(citySelectAdapter);
                                citySelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        cityBean=citySelectAdapter.getData().get(position);
                                        setBackActivityBundle();
                                        backPage();
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    citySelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                citySelectAdapter.add(contentBeanList);
                            }
                            showProcessBar(100);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

    public  void getAreaList(final int page){
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(200);
        queryConditionBean.setDirection("ASC");
        queryConditionBean.setProperty("id");
        queryConditionBean.setFromClientType("android");
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(billID) ){
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("cityId");
            rulesBean.setOption("EQ");
            List<String> value = new ArrayList<String>();
            value.add(billID);
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("areaName");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("region/queryAreaInfoByPage")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<RegionSearchBean>() {
                    @Override
                    public void onSuccess(RegionSearchBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<RegionSearchBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                areaSelectAdapter = new AreaSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(areaSelectAdapter);
                                areaSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        areaBean=areaSelectAdapter.getData().get(position);
                                        setBackActivityBundle();
                                        backPage();
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    areaSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                areaSelectAdapter.add(contentBeanList);
                            }
                            showProcessBar(100);
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }


    public  void getBrandList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("name");
            rulesBean.setOption("LIKE_ANYWHERE");
            rulesBeanList.add(rulesBean);
            List<String> value = new ArrayList<String>();
            value.add(etSearch.getText().toString().trim());
            rulesBean.setValues(value);
            queryConditionBean.setRules(rulesBeanList);
        }
        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("brand/page?")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<BrandBean>() {
                            @Override
                            public void onSuccess(BrandBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<BrandBean.ContentBean> contentBeanList=data.getContent();
                                    if(page==0){
                                        bandSelectAdapter = new BandSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                        swipeMenuRecyclerView.setAdapter(bandSelectAdapter);
                                        bandSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                bandBean=bandSelectAdapter.getData().get(position);
                                                setBackActivityBundle();
                                                backPage();
                                            }
                                        });
                                        if(contentBeanList.size()==0||contentBeanList==null){
                                            bandSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                        }

                                    }else{
                                        bandSelectAdapter.add(contentBeanList);
                                    }
                                    showProcessBar(100);
                                }
/*
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<BrandBean.ContentBean> contentBeanList=data.getContent();
                                    brandBeanSelectList =new ArrayList<>();
                                    if(page==0){
                                        productBigTypeSelectAdapter = new ProductBigTypeSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList, brandBeanSelectList);
                                        swipeMenuRecyclerView.setAdapter(productBigTypeSelectAdapter);
                                        if(contentBeanList.size()==0||contentBeanList==null){
                                            productBigTypeSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                        }

                                    }else{
                                        productBigTypeSelectAdapter.add(contentBeanList);
                                    }
                                    showProcessBar(100);
                                }
*/
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
    public  void  getSingleProductBigTypeList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(100);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("name");
            rulesBean.setOption("LIKE_ANYWHERE");
            rulesBeanList.add(rulesBean);
            List<String> value = new ArrayList<String>();
            value.add(etSearch.getText().toString().trim());
            rulesBean.setValues(value);
            queryConditionBean.setRules(rulesBeanList);
        }
        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("material-type/page?")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<MaterialTypeBean>() {
                            @Override
                            public void onSuccess(MaterialTypeBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<MaterialTypeBean.ContentBean> contentBeanList=data.getContent();
                                    if(page==0){
                                        singleProductBigTypeSelectAdapter = new SingleProductBigTypeSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                        swipeMenuRecyclerView.setAdapter(singleProductBigTypeSelectAdapter);
                                        singleProductBigTypeSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                selectName="";
                                                selectName=singleProductBigTypeSelectAdapter.getData().get(position).getName();
                                                selectId=singleProductBigTypeSelectAdapter.getData().get(position).getId();
                                                setBackActivityBundle();
                                                backPage();
                                            }
                                        });
                                        if(contentBeanList.size()==0||contentBeanList==null){
                                            singleProductBigTypeSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                        }

                                    }else{
                                        singleProductBigTypeSelectAdapter.add(contentBeanList);
                                    }
                                    showProcessBar(100);
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


    public  void  getOrderList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> specialConditionslist=new ArrayList<>();
            specialConditionslist.add(etSearch.getText().toString().trim());
            queryConditionBean.setSpecialConditions(specialConditionslist);
        }
        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("order/page-operator/android/"+identify)
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<OrderBean>() {
                            @Override
                            public void onSuccess(OrderBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<OrderBean.ContentBean> ContentBeanlist =data.getContent();
                                    mOrderBeanList =data.getContent();
                                    if(page==0){
                                        if(orderSelectAdapter==null){
                                            orderSelectAdapter=new OrderListAdapter(R.layout.jiuyi_orders_item, mOrderBeanList);
                                            swipeMenuRecyclerView.setAdapter(orderSelectAdapter);
                                            orderSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    selectName=orderSelectAdapter.getData().get(position).getCrmNumber();
                                                    orderID=orderSelectAdapter.getData().get(position).getId();
                                                    setBackActivityBundle();
                                                    backPage();
                                                }
                                            });
                                        }else{
                                            orderSelectAdapter.refresh(mOrderBeanList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        orderSelectAdapter.add(ContentBeanlist);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
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

    public  void  getTaskList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("title");
            rulesBean.setOption("LIKE_ANYWHERE");
            rulesBeanList.add(rulesBean);
            List<String> value = new ArrayList<String>();
            value.add(etSearch.getText().toString().trim());
            rulesBean.setValues(value);
            queryConditionBean.setRules(rulesBeanList);
        }



        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("task/page-two?")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<TaskBean>() {
                            @Override
                            public void onSuccess(TaskBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<TaskBean.ContentBean> ContentBeanlist =data.getContent();
                                    mTaskBeanList =data.getContent();
                                    if(page==0){
                                        if(taskSelectAdapter==null){
                                            taskSelectAdapter=new TaskListAdapter(R.layout.jiuyi_task_item, mTaskBeanList);
                                            swipeMenuRecyclerView.setAdapter(taskSelectAdapter);
                                            taskSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    selectName=taskSelectAdapter.getData().get(position).getTitle();
                                                    orderID=taskSelectAdapter.getData().get(position).getId();
                                                    setBackActivityBundle();
                                                    backPage();
                                                }
                                            });
                                        }else{
                                            taskSelectAdapter.refresh(mTaskBeanList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        taskSelectAdapter.add(ContentBeanlist);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
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

    public  void getDepartmentList(final int page){
        String type="";
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");


        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.POST("department/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DepartmentBean>() {
                    @Override
                    public void onSuccess(DepartmentBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<DepartmentBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                departmentSelectAdapter = new DepartmentSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(departmentSelectAdapter);
                                departmentSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        selectName="";
                                        selectName=departmentSelectAdapter.getData().get(position).getName();
                                        selectId=departmentSelectAdapter.getData().get(position).getId();
                                        setBackActivityBundle();
                                        backPage();
                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    departmentSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                departmentSelectAdapter.add(contentBeanList);
                            }
                            showProcessBar(100);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

    public  void getCustomerDepartmentList(final int page){
        String type="";
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");


        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);

        JiuyiHttp.GET("link-man-office/findVoByMemberIdOfIos/"+Customerid)
                .tag("request_get_member")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<OfficeBean>>() {
                    @Override
                    public void onSuccess(List<OfficeBean> data) {
                        if(data!=null ){
                            List<OfficeBean> contentBeanList=data;
                            customerDepartmentSelectAdapter = new CustomerDepartmentSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                            swipeMenuRecyclerView.setAdapter(customerDepartmentSelectAdapter);
                            customerDepartmentSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    selectName="";
                                    selectName=customerDepartmentSelectAdapter.getData().get(position).getName();
                                    selectId=customerDepartmentSelectAdapter.getData().get(position).getId();
                                    setBackActivityBundle();
                                    backPage();
                                }
                            });
                            if(contentBeanList.size()==0||contentBeanList==null){
                                customerDepartmentSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
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


    public  void getContractByMemberIdList(final int page){
        String type="";
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<Long> value = new ArrayList<Long>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member.id");
        rulesBean.setOption("EQ");
        value.add(Customerid);
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        queryConditionBean.setRules(rulesBeanList);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("link-man/find-pager-memberId")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<CustomerLinkmanBean>() {
                            @Override
                            public void onSuccess(CustomerLinkmanBean data) {
                                if(data!=null) {
                                    List<CustomerLinkmanBean.ContentBean> contentBeanList= data.getContent();
                                    customerPersonSelectAdapter = new CustomerPersonSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                    swipeMenuRecyclerView.setAdapter(customerPersonSelectAdapter);
                                    customerPersonSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            selectName="";
                                            selectName=customerPersonSelectAdapter.getData().get(position).getName();
                                            selectId=customerPersonSelectAdapter.getData().get(position).getId();
                                            setBackActivityBundle();
                                            backPage();
                                        }
                                    });
                                    if(contentBeanList.size()==0||contentBeanList==null){
                                        customerPersonSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
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




    public  void  getSinglePersonList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("name-department.name-position.desp");
            rulesBean.setOption("LIKE_ANYWHERE");
            rulesBeanList.add(rulesBean);
            List<String> value = new ArrayList<String>();
            value.add(etSearch.getText().toString().trim());
            rulesBean.setValues(value);
            queryConditionBean.setRules(rulesBeanList);
        }
        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("operator/page?")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<NormalOperatorBean>() {
                            @Override
                            public void onSuccess(NormalOperatorBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<NormalOperatorBean.ContentBean> contentBeanList =data.getContent();
                                    if(page==0){
                                        singlePersonSelectAdapter = new SinglePersonSelectAdapter(R.layout.jiuyi_select_item_listview, contentBeanList);
                                        swipeMenuRecyclerView.setAdapter(singlePersonSelectAdapter);
                                        singlePersonSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                selectName="";
                                                selectName=singlePersonSelectAdapter.getData().get(position).getName();
                                                selectId=singlePersonSelectAdapter.getData().get(position).getId();
                                                sOACode=singlePersonSelectAdapter.getData().get( position ).getOaCode();
                                                setBackActivityBundle();
                                                backPage();
                                            }
                                        });
                                        if(contentBeanList.size()==0||contentBeanList==null){
                                            singlePersonSelectAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                        }

                                    }else{
                                        singlePersonSelectAdapter.add(contentBeanList);
                                    }
                                    showProcessBar(100);
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



    public  void  getPersonList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("name-department.name-position.desp");
            rulesBean.setOption("LIKE_ANYWHERE");
            rulesBeanList.add(rulesBean);
            List<String> value = new ArrayList<String>();
            value.add(etSearch.getText().toString().trim());
            rulesBean.setValues(value);
            queryConditionBean.setRules(rulesBeanList);
        }
        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("operator/page?")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<NormalOperatorBean>() {
                            @Override
                            public void onSuccess(NormalOperatorBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<NormalOperatorBean.ContentBean> ContentBeanlist =data.getContent();
                                    mPersonBeanList=new ArrayList<>();
                                    if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                                        for(int i=0;i<ContentBeanlist.size();i++){
                                            NormalOperatorBean.ContentBean contentBean=  ContentBeanlist.get(i);
                                            PersonSelectBean personSelectBean=new PersonSelectBean();
                                            if(contentBean!=null){
                                                personSelectBean.setId(contentBean.getId());
                                                if(contentBean.getName()!=null){
                                                    personSelectBean.setName(contentBean.getName());
                                                }
                                                if(contentBean.getDepartment()!=null){
                                                    if(contentBean.getDepartment().getName()!=null){
                                                        personSelectBean.setDeptName(contentBean.getDepartment().getName());
                                                    }
                                                }
                                                if(contentBean.getTitle()!=null){
                                                    personSelectBean.setTitle(contentBean.getTitle());
                                                }
                                                personSelectBean.setCheck(false);

                                                mPersonBeanList.add(personSelectBean);
                                            }
                                        }
                                    }
                                    if(page==0){
                                        if(personSelectAdapter==null){
                                            personSelectAdapter=new PersonSelectAdapter(R.layout.jiuyi_select_item_listview, mPersonBeanList,mPersonBeanSelectList);
                                            swipeMenuRecyclerView.setAdapter(personSelectAdapter);
                                        }else{
                                            personSelectAdapter.refresh(mPersonBeanList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        personSelectAdapter.add(mPersonBeanList);
//                                        swipeMenuRecyclerView.setAdapter(personSelectAdapter);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
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
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        if(billType.equals("material")){
            bundle.putString(JiuyiBundleKey.PARAM_BATCHNUM,selectName);
            bundle.putString(JiuyiBundleKey.PARAM_PRODUCTPLACE,productPlace);
            bundle.putString(JiuyiBundleKey.PARAM_FACTORYNAME,factoryName);
            bundle.putString(JiuyiBundleKey.PARAM_COMMONCODE, SelectCode);
            bundle.putString(JiuyiBundleKey.PARAM_LEVELNAME,levelName);
            bundle.putString(JiuyiBundleKey.PARAM_LEVELCODE,levelcode);
            bundle.putString(JiuyiBundleKey.PARAM_WEIGHT,weight);
            bundle.putString(JiuyiBundleKey.PARAM_PRESCRIPTION,prescription);
        }else if(billType.equals("Batchnum")){
            bundle.putString(JiuyiBundleKey.PARAM_BATCHNUM,selectName);
            bundle.putLong(JiuyiBundleKey.PARAM_NEEDPLANID,batchID);
            bundle.putString(JiuyiBundleKey.PARAM_PRODUCTPLACE,productPlace);
            bundle.putString(JiuyiBundleKey.PARAM_FACTORYNAME,factoryName);
            bundle.putString(JiuyiBundleKey.PARAM_COMMONCODE, SelectCode);
            bundle.putString(JiuyiBundleKey.PARAM_LEVELNAME,levelName);
            bundle.putString(JiuyiBundleKey.PARAM_SPEC,spec);
            bundle.putString(JiuyiBundleKey.PARAM_WEIGHT,weight);
            bundle.putString(JiuyiBundleKey.PARAM_PRESCRIPTION,prescription);
        }else if(billType.equals("specialproduct")){
            bundle.putString(JiuyiBundleKey.PARAM_BATCHNUM,selectName);
        }else if(billType.equals("CONTACTS")){
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
            bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,selectId);
        }else if(billType.equals("ORDER")||billType.equals("TASK")||billType.equals("TASK")){
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
            bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,orderID);
        }else if(billType.equals("BRAND")){
            bundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,bandBean);
            /*if(productBigTypeSelectAdapter.getmProductTypeBeanSelectList()!=null && productBigTypeSelectAdapter.getmProductTypeBeanSelectList().size()>0){
                bundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) productBigTypeSelectAdapter.getmProductTypeBeanSelectList());
            }*/

        }else if(billType.equals("ACTIVITY")){
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
            bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,selectId);
        }else if(billType.equals("CLUE")||billType.equals("BUSINESS")|| billType.equals("CUSTOMERDEPARTMENT")){
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
            bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,selectId);
        }else if(billType.equals("INTELLIGENCE")){
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
            bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,selectId);
        }else if(billType.equals("Person")){
            if(personSelectAdapter.getmPersonBeanSelectList()!=null && personSelectAdapter.getmPersonBeanSelectList().size()>0){
                bundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) personSelectAdapter.getmPersonBeanSelectList());
            }

        }else if(billType.equals("DEPARTMENT")||billType.equals("SINGLEPRODUCTBIGTYPE")
                ||billType.equals("CUSTOMERPERSON")
               ){
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
            bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,selectId);
        }else if(billType.equals("COUNTRY")||billType.equals("PROVINCE")){
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
            bundle.putString(JiuyiBundleKey.PARAM_COMMONCODE,SelectCode);
        }else if(billType.equals("CITY")){
            bundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,cityBean);
        }else if(billType.equals("AREA")){
            bundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,areaBean);
        }else if(billType.equals("SINGLEPERSON")){
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
            bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,selectId);
            bundle.putString(JiuyiBundleKey.PARAM_COMMONCODE,sOACode);
        }else if(billType.equals("BXCITY")){
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
        }else if(billType.equals("CUSTOMER")){
            bundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,contentBean);
        }else {
            bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,selectName);
            bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,selectId);
            bundle.putString(JiuyiBundleKey.PARAM_DUETOTAL, msDuetotal);
        }

        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiCustomerSelectActivity.this.setResult(1, intent);
    }
    public void SetTextChanged(final JiuyiEditText edittext) {
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable v) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = etSearch.getText().toString();
                if(!Func.IsStringEmpty(str) && billType.equals("BXCITY")){
                    getBXCityList();
                }

            }
        });
    }

}
