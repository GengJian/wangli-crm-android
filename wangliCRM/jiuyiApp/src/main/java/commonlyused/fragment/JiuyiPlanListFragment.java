package commonlyused.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnCancelLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiDateUtil;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditText;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import commonlyused.adapter.ChannelDevelopListAdapter;
import commonlyused.adapter.DirectSalesEngineerListAdapter;
import commonlyused.adapter.MarketEngineerListAdapter;
import commonlyused.adapter.MarketHuaJueListAdapter;
import commonlyused.adapter.MarketJinMumenListAdapter;
import commonlyused.adapter.MarketNengChengListAdapter;
import commonlyused.adapter.MarketWorkPlanListAdapter;
import commonlyused.adapter.RetailChannelListAdapter;
import commonlyused.adapter.StategicEngineerListAdapter;
import commonlyused.bean.ChannelDevelopBean;
import commonlyused.bean.DirectSalesBean;
import commonlyused.bean.MarketEngineeringBean;
import commonlyused.bean.MarketHuaJueBean;
import commonlyused.bean.MarketJinMumenBean;
import commonlyused.bean.MarketNengChengBean;
import commonlyused.bean.MarketWorkPlanBean;
import commonlyused.bean.QueryConditionBean;
import commonlyused.bean.ServerDate;
import commonlyused.bean.StategicEngineeringBean;
import customer.Utils.KeyBoardUtils;
import customer.Utils.ViewOperatorType;
import customer.view.jiuyiRecycleViewDivider;
import commonlyused.bean.JiuyiRetailChannelBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiPlanListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 动态大全
 *****************************************************************
 */
public class JiuyiPlanListFragment extends JiuyiFragmentBase{
    private JiuyiEditText tv_search;
    private ImageView im_date;
    RecyclerView swipeMenuRecyclerView;
    int pageIndex = 1;
    int pageSize = 20;
    private int totalPage=0;
    QueryConditionBean queryConditionBean;
    private String currentBillType,backPageType="";
    private String sDate="";
    private RetailChannelListAdapter adapter;
    private ChannelDevelopListAdapter channelDevelopListAdapter;
    private MarketEngineerListAdapter marketEngineerListAdapter;
    private StategicEngineerListAdapter stategicEngineerListAdapter;
    private DirectSalesEngineerListAdapter directSalesEngineerListAdapter;
    private MarketNengChengListAdapter marketNengChengListAdapter;
    private MarketWorkPlanListAdapter marketWorkPlanListAdapter;
    private MarketHuaJueListAdapter marketHuaJueListAdapter;
    private MarketJinMumenListAdapter marketJinMumenListAdapter;
    private String sServerDate="";
    private String sServerDatetime="",sTodayOnDuty="";


    private Boolean isToday=false;
    private String workType="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_planfragment_layout"), null);
            onInit();
        } else {
            checkRootViewParent();
        }
        return mRootView;
    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiPlanListFragment newInstance(int nPageType) {
        JiuyiPlanListFragment f = new JiuyiPlanListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiPlanListFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiPlanListFragment f = new JiuyiPlanListFragment();
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
        getServerDate();
        findView();
        initMenuData();
        refreshCurrenPage(0);


        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                getServerDate();
                pageIndex=1;
                refreshCurrenPage(0);
                refreshlayout.finishRefresh(2000);
                refreshlayout.setLoadmoreFinished(false);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                getServerDate();
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

        swipeMenuRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_needplan);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns(), 1, false));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));
    }

    private void initMenuData() {
        switch (mPageType) {
            case Pub.Normal_RetailChannel:
                backPageType="retail-channel";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_ChannelDevelopemnt:
                backPageType="channel-development";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_MarketEngineering:
                backPageType="market-engineering";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_StrategicEngineering:
                backPageType="strategic-engineering";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_DirectSalesEngineering:
                backPageType="direct-sales-engineering";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_NengChengSales:
                backPageType="neng-cheng";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_HuaJueSales:
                backPageType="hua-jue";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_JinMumenSales:
                backPageType="workPlan";
                workType="GOLDWOOD";
                tv_search.setHint("请输入业务员姓名");
                break;

            case Pub.Normal_MumenSales:
                backPageType="workPlan";
                workType="TIMBER";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_LvMumenSales:
                backPageType="workPlan";
                workType="ALD";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_CopperSales:
                backPageType="workPlan";
                workType="CERART";
                tv_search.setHint("请输入业务员姓名");
                break;
            case Pub.Normal_IntelligentLock:
                backPageType="workPlan";
                workType="HYZNG";
                tv_search.setHint("请输入业务员姓名");
                break;

        }

    }

    protected void findView() {
        tv_search= (JiuyiEditText) mRootView.findViewById(R.id.tv_search);
        tv_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyBoardUtils.hideSoftInput(tv_search);
                    refreshCurrenPage(0);
                    return true;
                }
                return false;
            }
        });
        im_date= (ImageView) mRootView.findViewById(R.id.im_date);
        im_date.setOnClickListener(new View.OnClickListener() {
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
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        sDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
                        refreshCurrenPage(0);
                    }
                });
                dialog.setOnCancelListener(new OnCancelLisener() {
                    @Override
                    public void onCancel(String s) {
                        sDate="";
                        refreshCurrenPage(0);
                    }
                });
                dialog.show();
            }
        });
    }
    public  void getRetailChannelList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");

        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(tv_search.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_search.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(sDate)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanDate");
            rulesBean.setOption("BTD");
            value.add(sDate+" 00:00:00");
            value.add(sDate+" 23:59:59");
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<JiuyiRetailChannelBean>() {
                    @Override
                    public void onSuccess(JiuyiRetailChannelBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<JiuyiRetailChannelBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                adapter = new RetailChannelListAdapter(R.layout.normal_item_retailchannel, contentBeanList);
                                adapter.setBackPageType(backPageType);
                                swipeMenuRecyclerView.setAdapter(adapter);
                                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        JiuyiRetailChannelBean.ContentBean contentBean=(JiuyiRetailChannelBean.ContentBean)adapter.getData().get(position);
                                        if(contentBean!=null  ){
                                            if(!Func.IsStringEmpty(contentBean.getHandleStatus())){
                                                if(contentBean.getHandleStatus().equals("beforeWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else if(contentBean.getHandleStatus().equals("handleWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SPECIAL);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_RetailChannelNew, true);
                                            }else {
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_RetailChannelNew, true);
                                            }
                                        }

                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                adapter.add(contentBeanList);
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
    public  void getChannelDevelopList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");

        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(tv_search.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_search.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(sDate)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanDate");
            rulesBean.setOption("BTD");
            value.add(sDate+" 00:00:00");
            value.add(sDate+" 23:59:59");
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ChannelDevelopBean>() {
                    @Override
                    public void onSuccess(ChannelDevelopBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<ChannelDevelopBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                channelDevelopListAdapter = new ChannelDevelopListAdapter(R.layout.normal_item_retailchannel, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(channelDevelopListAdapter);
                                channelDevelopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        ChannelDevelopBean.ContentBean contentBean=(ChannelDevelopBean.ContentBean)adapter.getData().get(position);
                                        if(contentBean!=null){
                                            if(!Func.IsStringEmpty(contentBean.getHandleStatus())){
                                                if(contentBean.getHandleStatus().equals("beforeWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else if(contentBean.getHandleStatus().equals("handleWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SPECIAL);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_ChannelDevelopemntNew, true);
                                            }else {
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_ChannelDevelopemntNew, true);
                                            }
                                        }

                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    channelDevelopListAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                channelDevelopListAdapter.add(contentBeanList);
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

    public  void getMarketEngineeringList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");

        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(tv_search.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_search.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(sDate)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanDate");
            rulesBean.setOption("BTD");
            value.add(sDate+" 00:00:00");
            value.add(sDate+" 23:59:59");
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<MarketEngineeringBean>() {
                    @Override
                    public void onSuccess(MarketEngineeringBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<MarketEngineeringBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                marketEngineerListAdapter = new MarketEngineerListAdapter(R.layout.normal_item_retailchannel, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(marketEngineerListAdapter);
                                marketEngineerListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        MarketEngineeringBean.ContentBean contentBean=(MarketEngineeringBean.ContentBean)adapter.getData().get(position);
                                        if(contentBean!=null){
                                            if(!Func.IsStringEmpty(contentBean.getHandleStatus())){
                                                if(contentBean.getHandleStatus().equals("beforeWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else if(contentBean.getHandleStatus().equals("handleWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SPECIAL);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_MarketEngineeringNew, true);
                                            }else {
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_MarketEngineeringNew, true);
                                            }
                                        }

                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    marketEngineerListAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                marketEngineerListAdapter.add(contentBeanList);
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

    public  void getStategicEngineeringList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");

        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(tv_search.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_search.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(sDate)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanDate");
            rulesBean.setOption("BTD");
            value.add(sDate+" 00:00:00");
            value.add(sDate+" 23:59:59");
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<StategicEngineeringBean>() {
                    @Override
                    public void onSuccess(StategicEngineeringBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<StategicEngineeringBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                stategicEngineerListAdapter = new StategicEngineerListAdapter(R.layout.normal_item_retailchannel, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(stategicEngineerListAdapter);
                                stategicEngineerListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        StategicEngineeringBean.ContentBean contentBean=(StategicEngineeringBean.ContentBean)adapter.getData().get(position);
                                        if(contentBean!=null){
                                            if(!Func.IsStringEmpty(contentBean.getHandleStatus())){
                                                if(contentBean.getHandleStatus().equals("beforeWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else if(contentBean.getHandleStatus().equals("handleWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SPECIAL);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_StrategicEngineeringNew, true);
                                            }else {
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_StrategicEngineeringNew, true);
                                            }
                                        }

                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    stategicEngineerListAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                stategicEngineerListAdapter.add(contentBeanList);
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

    public  void getDirectSalesEngineeringList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");

        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(tv_search.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_search.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(sDate)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanDate");
            rulesBean.setOption("BTD");
            value.add(sDate+" 00:00:00");
            value.add(sDate+" 23:59:59");
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DirectSalesBean>() {
                    @Override
                    public void onSuccess(DirectSalesBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<DirectSalesBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                directSalesEngineerListAdapter = new DirectSalesEngineerListAdapter(R.layout.normal_item_retailchannel, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(directSalesEngineerListAdapter);
                                directSalesEngineerListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        DirectSalesBean.ContentBean contentBean=(DirectSalesBean.ContentBean)adapter.getData().get(position);
                                        if(contentBean!=null){
                                            if(!Func.IsStringEmpty(contentBean.getHandleStatus())){
                                                if(contentBean.getHandleStatus().equals("beforeWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else if(contentBean.getHandleStatus().equals("handleWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SPECIAL);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_DirectSalesEngineeringNew, true);
                                            }else {
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_DirectSalesEngineeringNew, true);
                                            }
                                        }

                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    directSalesEngineerListAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                directSalesEngineerListAdapter.add(contentBeanList);
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

    public  void getMarketNengChengList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");

        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(tv_search.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_search.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(sDate)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanDate");
            rulesBean.setOption("BTD");
            value.add(sDate+" 00:00:00");
            value.add(sDate+" 23:59:59");
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<MarketNengChengBean>() {
                    @Override
                    public void onSuccess(MarketNengChengBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<MarketNengChengBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                marketNengChengListAdapter = new MarketNengChengListAdapter(R.layout.normal_item_retailchannel, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(marketNengChengListAdapter);
                                marketNengChengListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        MarketNengChengBean.ContentBean contentBean=(MarketNengChengBean.ContentBean)adapter.getData().get(position);
                                        if(contentBean!=null){
                                            if(!Func.IsStringEmpty(contentBean.getHandleStatus())){
                                                if(contentBean.getHandleStatus().equals("beforeWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else if(contentBean.getHandleStatus().equals("handleWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SPECIAL);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_NengChengSalesNew, true);
                                            }else {
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_NengChengSalesNew, true);
                                            }
                                        }

                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    marketNengChengListAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                marketNengChengListAdapter.add(contentBeanList);
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

    public  void getMarketHuaJueList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");

        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(tv_search.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_search.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(sDate)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanDate");
            rulesBean.setOption("BTD");
            value.add(sDate+" 00:00:00");
            value.add(sDate+" 23:59:59");
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<MarketHuaJueBean>() {
                    @Override
                    public void onSuccess(MarketHuaJueBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<MarketHuaJueBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                marketHuaJueListAdapter = new MarketHuaJueListAdapter(R.layout.normal_item_retailchannel, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(marketHuaJueListAdapter);
                                marketHuaJueListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        MarketHuaJueBean.ContentBean contentBean=(MarketHuaJueBean.ContentBean)adapter.getData().get(position);
                                        if(contentBean!=null){
                                            if(!Func.IsStringEmpty(contentBean.getHandleStatus())){
                                                if(contentBean.getHandleStatus().equals("beforeWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else if(contentBean.getHandleStatus().equals("handleWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SPECIAL);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_HuaJueSalesNew, true);
                                            }else {
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_HuaJueSalesNew, true);
                                            }
                                        }

                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    marketHuaJueListAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                marketHuaJueListAdapter.add(contentBeanList);
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


    public  void getMarketJinMumenList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");

        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(tv_search.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_search.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(sDate)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanDate");
            rulesBean.setOption("BTD");
            value.add(sDate+" 00:00:00");
            value.add(sDate+" 23:59:59");
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<MarketJinMumenBean>() {
                    @Override
                    public void onSuccess(MarketJinMumenBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<MarketJinMumenBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                marketJinMumenListAdapter = new MarketJinMumenListAdapter(R.layout.normal_item_retailchannel, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(marketJinMumenListAdapter);
                                marketJinMumenListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        MarketJinMumenBean.ContentBean contentBean=(MarketJinMumenBean.ContentBean)adapter.getData().get(position);
                                        if(contentBean!=null){
                                            if(!Func.IsStringEmpty(contentBean.getHandleStatus())){
                                                if(contentBean.getHandleStatus().equals("beforeWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else if(contentBean.getHandleStatus().equals("handleWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SPECIAL);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_JinMumenSalesNew, true);
                                            }else {
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_JinMumenSalesNew, true);
                                            }
                                        }

                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    marketJinMumenListAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                marketJinMumenListAdapter.add(contentBeanList);
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


    public  void getMarketWorkPlanList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");

        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        if(!Func.IsStringEmpty(tv_search.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("operator.name");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_search.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(workType)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanType");
            rulesBean.setOption("EQ");
            value.add(workType);
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        if(!Func.IsStringEmpty(sDate)){
            List<String> value = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("workPlanDate");
            rulesBean.setOption("BTD");
            value.add(sDate+" 00:00:00");
            value.add(sDate+" 23:59:59");
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
        }
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST(backPageType+"/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<MarketWorkPlanBean>() {
                    @Override
                    public void onSuccess(MarketWorkPlanBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            List<MarketWorkPlanBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                marketWorkPlanListAdapter = new MarketWorkPlanListAdapter(R.layout.normal_item_retailchannel, contentBeanList);
                                swipeMenuRecyclerView.setAdapter(marketWorkPlanListAdapter);
                                marketWorkPlanListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        MarketWorkPlanBean.ContentBean contentBean=(MarketWorkPlanBean.ContentBean)adapter.getData().get(position);
                                        if(contentBean!=null){
                                            if(!Func.IsStringEmpty(contentBean.getHandleStatus())){
                                                if(contentBean.getHandleStatus().equals("beforeWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else if(contentBean.getHandleStatus().equals("handleWorkPlanDate")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SPECIAL);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, mPageType+1, true);
                                            }else {
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"计划详情");
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, mPageType+1, true);
                                            }
                                        }

                                    }
                                });
                                if(contentBeanList.size()==0||contentBeanList==null){
                                    marketWorkPlanListAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }

                            }else{
                                marketWorkPlanListAdapter.add(contentBeanList);
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






    public  QueryConditionBean  builderCondition(int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pageSize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        return queryConditionBean;
    }
    public void refreshCurrenPage(int page){
        showProcessBar(0);
        switch (mPageType) {
            case Pub.Normal_RetailChannel:
                getRetailChannelList(page);
                break;
            case Pub.Normal_ChannelDevelopemnt:
                getChannelDevelopList(page);
                break;
            case Pub.Normal_MarketEngineering:
                getMarketEngineeringList(page);
                break;
            case Pub.Normal_StrategicEngineering:
                getStategicEngineeringList(page);
                break;
            case Pub.Normal_DirectSalesEngineering:
                getDirectSalesEngineeringList(page);
                break;
            case Pub.Normal_NengChengSales:
                getMarketNengChengList(page);
                break;
            case Pub.Normal_HuaJueSales:
                getMarketHuaJueList(page);
                break;
            case Pub.Normal_JinMumenSales:
                workType="GOLDWOOD";
                getMarketWorkPlanList(page);
//                getMarketJinMumenList(page);
                break;

            case Pub.Normal_MumenSales:
                workType="TIMBER";
                getMarketWorkPlanList(page);
                break;
            case Pub.Normal_LvMumenSales:
                workType="ALD";
                getMarketWorkPlanList(page);
                break;
            case Pub.Normal_CopperSales:
                workType="CERART";
                getMarketWorkPlanList(page);
                break;
            case Pub.Normal_IntelligentLock:
                workType="HYZNG";
                getMarketWorkPlanList(page);
                break;


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
    @Override
    public void createReq(boolean IsBg) {
        refreshCurrenPage(0);
    }

    private void getServerDate() {
        JiuyiHttp.GET("date/today")
                .tag("request_get_")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ServerDate>() {
                    @Override
                    public void onSuccess(ServerDate data) {
                        if(data!=null){
                            sServerDate=data.getToday();
                            sServerDatetime=data.getTodayNow();
                            sTodayOnDuty=data.getTodayOnDutyTime();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

}
