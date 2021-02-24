package customer.fragment;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnCancelLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditText;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.tools.CustomPopWindow;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;
import com.wanglicrm.android.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.KeyBoardUtils;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.adapter.CustomerPlanAdapter;
import customer.adapter.CustomerStoreAdapter;
import customer.entity.CustomerPlanBean;
import customer.entity.CustomerStoreInfoBean;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.Utils.DynamicConstant;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerStoreFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户门店
 *****************************************************************
 */
public class JiuyiCustomerPlanFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private LinearLayout ll_date, ll_province,ll_condition;
    private View view_conditon;
    private TextView tv_date, tv_province;
    private JiuyiEditText etSearch;
    private int[] imageids = { R.drawable.client_selected };
    private CustomerPlanAdapter adapter;
    private long Customerid=-1;
    private String Customername="";
    private String sDate="";
    RecyclerView swipeMenuRecyclerView;
    private  int mpage=0;
    private String msPlace="",provineID="";
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pageSize = 20;
    private int totalPage=0;
    private LinearLayout ll_content;
    private TextView tv_no_authoritytext;
    private ImageView iv_no_authority;
    private ImageView iv_add;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerstorefragment_layout"), null);
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
    public static JiuyiCustomerPlanFragment newInstance(int nPageType) {
        JiuyiCustomerPlanFragment f = new JiuyiCustomerPlanFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerPlanFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerPlanFragment f = new JiuyiCustomerPlanFragment();
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
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);

        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        findView();
        getInfoList(0);
//        if(!MemberAuthorityBean.getIns().demandPlan){
//            tv_no_authoritytext.setVisibility(View.VISIBLE);
//            iv_no_authority.setVisibility(View.VISIBLE);
//            ll_content.setVisibility(View.GONE);
//        }else {
//            getInfoList(mpage);
//            getDenierList();
//            findView();
//            initMenuData();
//            initPopMenu();
//            mViewTypeBeanList = new ArrayList<>();
//        }


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_date:
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
                        tv_date.setText("创建日期");
                        sDate="";
                        getInfoList(0);
                    }
                });
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        tv_date.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        sDate=tv_date.getText().toString();
                        getInfoList(0);
                    }
                });
                dialog.show();
                break;
            case R.id.ll_province:
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"PROVINCE");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 200);
                break;


        }
    }
    protected void findView() {
        ll_content=(LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_content"));
        ll_condition=(LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_condition"));
        view_conditon=(View) mRootView.findViewById(Res.getViewID(getContext(), "view_conditon"));
        view_conditon.setVisibility(View.GONE);
        ll_condition.setVisibility(View.GONE);
        ll_date = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_date"));
        ll_province = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_province"));
        tv_date = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_date"));
        tv_province = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_province"));
        ll_date.setOnClickListener(this);
        ll_province.setOnClickListener(this);
        iv_add = (ImageView) mRootView.findViewById(Res.getViewID(getContext(), "iv_add"));
        iv_add.setVisibility(View.GONE);
        etSearch= (JiuyiEditText) mRootView.findViewById(R.id.tv_search);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyBoardUtils.hideSoftInput(etSearch);
                    getInfoList(0);
                    return true;
                }
                return false;
            }
        });
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                getInfoList(0);
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
                    getInfoList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;

            }
        });

        swipeMenuRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_needplan);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity(), 1, false));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));


    }

    public  void getInfoList(final int page){
        if(page==0){
            pageIndex=1;
        }

        final QueryConditionBean   queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        List<String> value = new ArrayList<String>();
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member.id");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        if(!Func.IsStringEmpty(sDate) ){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("createdDate");
            rulesBean2.setOption("BTD");
            value2.add(sDate+" 00:00:00");
            value2.add(sDate+" 23:59:59");
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(provineID)){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("province.provinceId");
            rulesBean2.setOption("EQ");
            value2.add(provineID);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("searchContent");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }


        queryConditionBean.setRules(rulesBeanList);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("dealer-plan/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<CustomerPlanBean>() {
                            @Override
                            public void onSuccess(CustomerPlanBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<CustomerPlanBean.ContentBean> contentBeanList=data.getContent();
                                    if(page==0){
                                        if(adapter==null){
                                            adapter = new CustomerPlanAdapter(R.layout.customer_item_plan, contentBeanList);
                                            swipeMenuRecyclerView.setAdapter(adapter);
                                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    CustomerPlanBean.ContentBean contentBean =(CustomerPlanBean.ContentBean)adapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        String url="";
                                                        url= Constant.BaseH5Url+ DynamicConstant.PLAN_URL+contentBean.getId()+"&token="+ Rc.id_tokenparam;
                                                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"经销商计划详情");
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                                                    }
                                                }
                                            });
                                        }else{
                                            adapter.refresh(contentBeanList);
                                        }
                                        if(contentBeanList.size()==0||contentBeanList==null){
                                            adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                        }
                                    }else{
                                        adapter.add(contentBeanList);
                                    }
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
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
        if(Rc.mBackfresh && Rc.mPageType==mPageType){
            Rc.mBackfresh=false;
            Rc.mPageType=0;
            getInfoList(0);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent){
        if(intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle bundle=null;
        switch (requestCode) {
            case 200:
                bundle = intent.getExtras();
                String customerName = bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                String code = bundle.getString(JiuyiBundleKey.PARAM_COMMONCODE);
                provineID=code;
                if (code != null && customerName != null) {
                    tv_province.setText(customerName);
                }else{
                    tv_province.setText("省份");
                }
                getInfoList(0);
                break;

            default:
                break;

        }
    }



}
