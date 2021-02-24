package mine.fragment;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDialog;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnCancelLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiDateUtil;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.KeyBoardUtils;
import customer.adapter.CustomerSelMenuAdapter;
import customer.view.SFPopupWindow;
import customer.view.jiuyiRecycleViewDivider;
import mine.adapter.NeedPlanTotalAdapter;
import mine.bean.MineDeliveryPlanBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineDeliveryPlanFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的发货计划
 *****************************************************************
 */
public class JiuyiMineDeliveryPlanTotalFragment extends JiuyiFragmentBase implements
        View.OnClickListener {

    private LinearLayout mLinemonthsort, ll_batchNum, ll_customer,ll_factory;
    private TextView mTvdate, mTvBatchNum, mTvCustomer,tv_factory;
    private  NeedPlanTotalAdapter adapter;

    private  int year=0,month=0;
    RecyclerView swipeMenuRecyclerView;
    private  int mpage=0;
    private String msPlace="",mspec="",msDate="";
    RefreshLayout refreshLayout;
    private int pageIndex = 1;
    private int pageSize = 20;
    private int totalPage=0;
    private String planType;
    private JiuyiEditText etSearch;
    private LoadingDialog dialog1;
    List<String> menuStr1;
    private List<Map<String, Object>> menuData1;
    private int[] imageids = { R.drawable.client_selected };
    private CustomerSelMenuAdapter menuAdapter1;
    private ListView popListView;
    private SFPopupWindow popMenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_minedeliveryplantotalfragment_layout"), null);
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
    public static JiuyiMineDeliveryPlanTotalFragment newInstance(int nPageType) {
        JiuyiMineDeliveryPlanTotalFragment f = new JiuyiMineDeliveryPlanTotalFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineDeliveryPlanTotalFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineDeliveryPlanTotalFragment f = new JiuyiMineDeliveryPlanTotalFragment();
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
        Calendar m_Curdate = Calendar.getInstance();
        year = m_Curdate.get(Calendar.YEAR);// 获取当前年份
        month= m_Curdate.get(Calendar.MONTH)+1;// 获取当前年份
        findView();
        initPopMenu();
        LoadingDialog.Builder builder1=new LoadingDialog.Builder(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity())
                .setMessage("加载中...")
                .setCancelable(false);
        dialog1=builder1.create();
        dialog1.show();
        planType="BATCHNUMBER";
        mTvBatchNum.setTextColor(Res.getColor(null, "jiuyi_blue"));

        getGatheringPlanList(0);
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                getGatheringPlanList(0);
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
                    getGatheringPlanList(pageIndex);
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
                dialog.setType(DateType.TYPE_YM);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                dialog.setOnCancelListener(new OnCancelLisener() {
                    @Override
                    public void onCancel(String s) {
                        mTvdate.setText("日期");
                        msDate="日期";
                        year=0;
                        month=0;
                        getGatheringPlanList(0);
                    }
                });
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        mTvdate.setText( new SimpleDateFormat("yyyy-MM").format(date));
                        getGatheringPlanList(0);
                    }
                });
                dialog.show();
                break;
            case R.id.ll_factory:
                tv_factory.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter1);
                popMenu.showAsDropDown(tv_factory, 0, 2);
//                menuIndex = 0;
                break;


        }
    }
    protected void findView() {
        etSearch=(JiuyiEditText)mRootView.findViewById(Res.getViewID(null, "et_search"));

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyBoardUtils.hideSoftInput(etSearch);
                    getGatheringPlanList(0);
                    return true;
                }
                return false;
            }
        });

        mLinemonthsort = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_monthsort"));
        ll_factory = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_factory"));
        ll_factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFactoryList();
            }
        });
        tv_factory = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_factory"));
        ll_batchNum = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_batchNum"));
        ll_customer = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_customer"));
        mTvdate = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_monthsort"));
        mTvdate.setText(JiuyiDateUtil.getNowMonth());
        mTvBatchNum = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_batchNum"));
        mTvCustomer = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_customer"));

        mLinemonthsort.setOnClickListener(this);
        ll_batchNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog1!=null){
                    dialog1.show();
                }
                planType="BATCHNUMBER";
                mTvBatchNum.setTextColor(Res.getColor(null, "jiuyi_blue"));
                mTvCustomer.setTextColor(Res.getColor(null, "jiuyi_next_color"));
                etSearch.setText("");
                getGatheringPlanList(0);
            }
        });
        ll_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog1!=null){
                    dialog1.show();
                }
                planType="MEMBER";
                mTvCustomer.setTextColor(Res.getColor(null, "jiuyi_blue"));
                mTvBatchNum.setTextColor(Res.getColor(null, "jiuyi_next_color"));
                etSearch.setText("");
                getGatheringPlanList(0);
            }
        });
        LinearLayout ll_search= (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_search"));
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Mine");
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customerproduct_search);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customerproduct_search,true);
            }
        });
    }


    public  void  getGatheringPlanList(final int page){
        if(page==0){
            pageIndex=1;
        }
//        Rc.mCustomerSearch=false;
        final QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
//        queryConditionBean.setDirection("DESC");
//        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
         if(!Func.IsStringEmpty(planType) ){
             List<String> specialConditionslist=new ArrayList<>();
             specialConditionslist.add(planType);
             queryConditionBean.setSpecialConditions(specialConditionslist);
        }
        msDate=mTvdate.getText().toString();
        if(!Func.IsStringEmpty(msDate) && !msDate.equals("日期")){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("date");
            rulesBean2.setOption("EQ");
            value2.add(msDate);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("searchValue");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(tv_factory.getText().toString().trim()) && !tv_factory.getText().toString().trim().equals("工厂")
                && !tv_factory.getText().toString().trim().equals("不限")){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("factory");
            rulesBean2.setOption("EQ");
            value2.add(tv_factory.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(rulesBeanList!=null && rulesBeanList.size()>0){
            queryConditionBean.setRules(rulesBeanList);
        }
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("demand_plan/find_operator_collect_two?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<MineDeliveryPlanBean>() {
                            @Override
                            public void onSuccess(MineDeliveryPlanBean data) {
                                if(data!=null){
                                    dialog1.dismiss();
                                    totalPage=data.getTotalPages();
                                    List<MineDeliveryPlanBean.ContentBean> contentBeanList=data.getContent();
                                    if(page==0){
                                        adapter = new NeedPlanTotalAdapter(R.layout.customer_item_needplantotal, contentBeanList,planType);
                                        swipeMenuRecyclerView.setAdapter(adapter);
//                                        adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
                                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                MineDeliveryPlanBean.ContentBean contentBean=(MineDeliveryPlanBean.ContentBean)adapter.getData().get(position);
                                                if(contentBean!=null){
//                                                        mBundle.putInt(JiuyiBundleKey.PARAM_NEEDPLANID, contentBean.getId());
                                                    mBundle.putParcelable(JiuyiBundleKey.PARAM_PLANEAN, contentBean);
                                                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Mine_deliveryPlanTotal);
                                                    mBundle.putInt(JiuyiBundleKey.PARAM_PLANNEXTPAGERTYPE,Pub.Mine_deliveryPlan);
                                                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Mine_deliveryPlanTotal,true);
                                                }
                                            }
                                        });
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
                                dialog1.dismiss();
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });
            }
        };
        thread.start();
    }


    private void getFactoryList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("batch-number-weight/find_factory")
                        .tag("request_batch-number-weight/find_factory")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<List<String>>() {
                            @Override
                            public void onSuccess(List<String> data) {
                                if(data!=null && data.size()>0){

                                    menuData1 =  new ArrayList<Map<String, Object>>();
                                    Map<String, Object>  map1;
                                    map1 = new HashMap<String, Object>();
                                    map1.put("name", "不限");
                                    map1.put("select", imageids[0]);
                                    menuData1.add(map1);
                                    for (int i = 1, len =data.size(); i <=len; ++i) {
                                        map1 = new HashMap<String, Object>();
                                        map1.put("name", data.get(i-1));
                                        map1.put("select", imageids[0]);
                                        menuData1.add(map1);
                                    }
                                    menuAdapter1=new CustomerSelMenuAdapter(JiuyiMainApplication.getIns(), menuData1);
                                    tv_factory.setTextColor(Res.getColor(null, "jiuyi_blue"));
                                    popListView.setAdapter(menuAdapter1);
                                    popMenu.showAsDropDown(tv_factory, 0, 2);
                                }
                                popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                                            long arg3) {
                                        //改变选中状态
                                        menuAdapter1.setCurrentItem(pos);
                                        //通知ListView改变状态
                                        menuAdapter1.notifyDataSetChanged();
                                        popMenu.dismiss();
                                        tv_factory.setText(menuData1.get(pos).get("name").toString());
//                                            mTvcharsort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                                        Toast.makeText(JiuyiMainApplication.getIns(), menuData1.get(pos).get("name").toString(), Toast.LENGTH_SHORT).show();
                                        getGatheringPlanList(0);
                                    }
                                });
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

    private void initPopMenu() {
        View contentView = View.inflate(JiuyiMainApplication.getIns(), R.layout.popwin_supplier_list,
                null);
        popMenu = new SFPopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                tv_factory.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });
    }




}
