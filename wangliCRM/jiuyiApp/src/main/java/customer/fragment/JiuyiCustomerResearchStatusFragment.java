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
import customer.adapter.LaboratoryAdapter;
import customer.adapter.PatentCertiicaitonAdapter;
import customer.adapter.SystemStandardAdapter;
import customer.adapter.TechnicalRoadAdapter;
import customer.entity.AbstractSummaryBean;
import customer.entity.LaboratoryBean;
import customer.entity.PatentCertificationBean;
import customer.entity.SystemStandardBean;
import customer.entity.TechnicalRoadBean;
import customer.view.MultiItemDivider;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerResearchStatusFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 采购动态
 *****************************************************************
 */
public class JiuyiCustomerResearchStatusFragment extends JiuyiFragmentBase {

    private long Customerid=-1;
    private String Customername="";
    private String mRisktype="";
    RecyclerView rvInfoList;

    private List<SystemStandardBean.ContentBean> mSystemStandardBeanList;
    private SystemStandardAdapter systemStandardAdapter;

    private List<PatentCertificationBean.ContentBean> mPatentCertificationBeanList;
    private PatentCertiicaitonAdapter patentCertiicaitonAdapter;

    private List<TechnicalRoadBean.ContentBean> mTechnicalRoadBeanList;
    private TechnicalRoadAdapter technicalRoadAdapter;

    private List<LaboratoryBean.ContentBean> mLaboratoryBeanList;
    private LaboratoryAdapter laboratoryAdapter;
    QueryConditionBean queryConditionBean;
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pagesize = 20;
    private int totalPage=0;
    private ImageView ivNew;
    private LinearLayout llResearchStandards;
    private TextView tvResearchStandards;
    private LinearLayout llPatentAuthentication;
    private TextView tvPatentAuthentication;
    private LinearLayout llTechnologyRoadmaps;
    private TextView tvTechnologyRoadmaps;
    private LinearLayout llLaboratory;
    private TextView tvLaboratory;
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
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerresearchstatusfragment_layout"), null);
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
        llResearchStandards = (LinearLayout) mRootView.findViewById(R.id.ll_research_standards);
        llResearchStandards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="ResearchStandards";
                backPageType="system-standard";
                refreshCurrenPage(0);
                sTitle="研发标准";
//                tvTitle.setText("研发标准");
            }
        });
        tvResearchStandards = (TextView) mRootView.findViewById(R.id.tv_research_standards);
        llPatentAuthentication = (LinearLayout) mRootView.findViewById(R.id.ll_patent_authentication);
        llPatentAuthentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="PatentAuthentication";
                backPageType="patent-certification";
                refreshCurrenPage(0);
                sTitle="专利认证";
//                tvTitle.setText("专利认证");
            }
        });
        tvPatentAuthentication = (TextView) mRootView.findViewById(R.id.tv_patent_authentication);
        llTechnologyRoadmaps = (LinearLayout) mRootView.findViewById(R.id.ll_technology_Roadmaps);
        llTechnologyRoadmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="TechnologyRoadmaps";
                backPageType="technical-road-sign";
                refreshCurrenPage(0);
                sTitle="技术路标";
//                tvTitle.setText("技术路标");
            }
        });
        tvTechnologyRoadmaps = (TextView) mRootView.findViewById(R.id.tv_technology_Roadmaps);
        llLaboratory = (LinearLayout) mRootView.findViewById(R.id.ll_laboratory);
        llLaboratory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="Laboratory";
                backPageType="laboratory";
                refreshCurrenPage(0);
                sTitle="实验室";
//                tvTitle.setText("实验室");
            }
        });
        tvLaboratory = (TextView) mRootView.findViewById(R.id.tv_laboratory);
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
//                if(!Func.IsStringEmpty(tvTitle.getText().toString())){
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
        currentBillType="ResearchStandards";
        backPageType="product-standard";
        refreshCurrenPage(0);
        sTitle="研发标准";
//        tvTitle.setText("研发标准");
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
    public static JiuyiCustomerResearchStatusFragment newInstance(int nPageType) {
        JiuyiCustomerResearchStatusFragment f = new JiuyiCustomerResearchStatusFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerResearchStatusFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerResearchStatusFragment f = new JiuyiCustomerResearchStatusFragment();
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

    public  void getSystemStandardList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<SystemStandardBean>() {
                    @Override
                    public void onSuccess(SystemStandardBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mSystemStandardBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("研发标准("+data.getTotalElements()+")");
                                systemStandardAdapter = new SystemStandardAdapter(R.layout.customer_item_systemstardard, mSystemStandardBeanList);
                                rvInfoList.setAdapter(systemStandardAdapter);
                                systemStandardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        SystemStandardBean.ContentBean contentBean=(SystemStandardBean.ContentBean)systemStandardAdapter.getData().get(position);
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
                                systemStandardAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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

                                if(mSystemStandardBeanList==null||mSystemStandardBeanList.size()==0){
                                    systemStandardAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                systemStandardAdapter.add(mSystemStandardBeanList);
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

    public  void getPatentCertificationList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<PatentCertificationBean>() {
                    @Override
                    public void onSuccess(PatentCertificationBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mPatentCertificationBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("专利认证("+data.getTotalElements()+")");
                                patentCertiicaitonAdapter = new PatentCertiicaitonAdapter(R.layout.customer_item_patentcertification, mPatentCertificationBeanList);
                                rvInfoList.setAdapter(patentCertiicaitonAdapter);
                                patentCertiicaitonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        PatentCertificationBean.ContentBean contentBean=(PatentCertificationBean.ContentBean)patentCertiicaitonAdapter.getData().get(position);
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
                                patentCertiicaitonAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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

                                if(mPatentCertificationBeanList==null||mPatentCertificationBeanList.size()==0){
                                    patentCertiicaitonAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                patentCertiicaitonAdapter.add(mPatentCertificationBeanList);
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

    public  void getTechnicalRoadList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<TechnicalRoadBean>() {
                    @Override
                    public void onSuccess(TechnicalRoadBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mTechnicalRoadBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("技术路线("+data.getTotalElements()+")");
                                technicalRoadAdapter = new TechnicalRoadAdapter(R.layout.customer_item_technolyroad, mTechnicalRoadBeanList);
                                rvInfoList.setAdapter(technicalRoadAdapter);
                                technicalRoadAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        TechnicalRoadBean.ContentBean contentBean=(TechnicalRoadBean.ContentBean)technicalRoadAdapter.getData().get(position);
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
                                technicalRoadAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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

                                if(mTechnicalRoadBeanList==null||mTechnicalRoadBeanList.size()==0){
                                    technicalRoadAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                technicalRoadAdapter.add(mTechnicalRoadBeanList);
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

    public  void getLaboratoryList(final int page){
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<LaboratoryBean>() {
                    @Override
                    public void onSuccess(LaboratoryBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mLaboratoryBeanList=data.getContent();
                            if(page==0){
                                tvTitle.setText("实验室("+data.getTotalElements()+")");
                                laboratoryAdapter = new LaboratoryAdapter(R.layout.customer_item_laboratory, mLaboratoryBeanList);
                                rvInfoList.setAdapter(laboratoryAdapter);
                                laboratoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        LaboratoryBean.ContentBean contentBean=(LaboratoryBean.ContentBean)laboratoryAdapter.getData().get(position);
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

                                if(mLaboratoryBeanList==null||mLaboratoryBeanList.size()==0){
                                    laboratoryAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                laboratoryAdapter.add(mLaboratoryBeanList);
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
        if(currentBillType.equals("ResearchStandards")){
            getSystemStandardList(page);
        }else if(currentBillType.equals("PatentAuthentication")){
            getPatentCertificationList(page);
        }else if(currentBillType.equals("TechnologyRoadmaps")){
            getTechnicalRoadList(page);
        }else if(currentBillType.equals("Laboratory")){
            getLaboratoryList(page);
        }
        if(page==0){
            getAbstractSummaryList();
        }
    }

    private void getAbstractSummaryList() {
        String apiUrl="system-standard/abstract-summary/"+Customerid;
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
                                if(abstractSummaryBean.getField().equals("SystemStandard")){
                                    if(tvResearchStandards!=null){
                                        tvResearchStandards.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("PatentCertification")){
                                    if(tvPatentAuthentication!=null){
                                        tvPatentAuthentication.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("TechnicalRoadSign")){
                                    if(tvTechnologyRoadmaps!=null){
                                        tvTechnologyRoadmaps.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("Laboratory")){
                                    if(tvLaboratory!=null){
                                        tvLaboratory.setText(abstractSummaryBean.getCount()+"");
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
