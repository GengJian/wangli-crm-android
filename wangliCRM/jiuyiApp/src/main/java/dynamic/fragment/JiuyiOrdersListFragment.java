package dynamic.fragment;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.bean.QueryConditionBean;
import customer.adapter.CustomerSelMenuAdapter;
import customer.adapter.ReceiveAddressAdapter;
import customer.view.SFPopupWindow;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.adapter.OrderListAdapter;
import dynamic.entity.OrderBean;
import dynamic.entity.SpecBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiBusinessTripListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 订单列表界面
 *****************************************************************
 */
public class JiuyiOrdersListFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private ListView popListView;
    private List<Map<String, Object>> menuData1, menuData2;
    private SFPopupWindow popMenu;
    private CustomerSelMenuAdapter menuAdapter1, menuAdapter2;

    private LinearLayout mLinemonthsort, mLinebirthplacesort, mLinecount;
    private TextView mTvcharsort, mTvquicksort, mTvmanual;

    private String currentProduct, currentSort, currentActivity;
    private int menuIndex = 0;
    private int[] imageids = { R.drawable.client_selected };

    QueryConditionBean queryConditionBean;
    List<QueryConditionBean.RulesBean> rulesBeanList;
    private OrderListAdapter adapter;
    private List<OrderBean.ContentBean> mOrderBeanList;
    private  int pageIndex=1,pagesize=20,totalPage=0;
    RefreshLayout refreshLayout;
    RecyclerView swipeMenuRecyclerView;
    private String orderdate="";
    List<String> menuStr1;
    private String spec="",grade="";
    ImageButton mibadd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_orderslistfragment_layout"), null);
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
    public static JiuyiOrdersListFragment newInstance(int nPageType) {
        JiuyiOrdersListFragment f = new JiuyiOrdersListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiOrdersListFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiOrdersListFragment f = new JiuyiOrdersListFragment();
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

        findView();
        initMenuData();
        initPopMenu();
        getSpecList();

        mOrderBeanList = new ArrayList<>();
        swipeMenuRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_orderlist);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity(), 1, false));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getOrderList(0);
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
                    getOrderList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;
            }
        });
        getOrderList(0);
        ImageButton mibadd;
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null, Pub.MSG_PopStart,true);
                }
            });
        }
        showProcessBar(0);
    }

    private void initMenuData() {
        menuData2 = new ArrayList<Map<String, Object>>();
        String[] menuStr2;
        Map<String, Object>map2;
        final List<DictBean> dictBeansList= DictUtils.getDictList("product_grade");
        if(dictBeansList!=null &&dictBeansList.size()>0){
            menuStr2=new String[dictBeansList.size()+1];
            map2 = new HashMap<String, Object>();
            map2.put("name", "不限");
            map2.put("select", imageids[0]);
            menuData2.add(map2);
            for(int i=1;i<=dictBeansList.size();i++){
                menuStr2[i]=dictBeansList.get(i-1).getRemark();
                map2 = new HashMap<String, Object>();
                map2.put("name", menuStr2[i]);
                map2.put("select", imageids[0]);
                menuData2.add(map2);
            }
        }
        menuAdapter2=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData2);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_date:
                mTvcharsort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter1);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 0;
                break;

            case R.id.ll_place:
                mTvquicksort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter2);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 1;
                break;
            case R.id.ll_status:
                DatePickDialog dialog = new DatePickDialog(getCallBack().getActivity());
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
                        mTvmanual.setText("订单日期");
                        orderdate="";
                        getOrderList(0);
                    }
                });
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        mTvmanual.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        if(mTvmanual.getText().toString()!=null){
                            orderdate=mTvmanual.getText().toString();
                            getOrderList(0);
                        }
                    }
                });
                dialog.show();
                break;

        }
    }
    protected void findView() {
        mLinemonthsort = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_monthsort"));
        mLinebirthplacesort = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_birthplace_sort"));
        mLinecount = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_countsort"));
        mTvcharsort = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_monthsort"));
        mTvquicksort = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_birthplacesort"));
        mTvmanual = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_countsort"));
        mLinemonthsort.setOnClickListener(this);
        mLinebirthplacesort.setOnClickListener(this);
        mLinecount.setOnClickListener(this);
    }
    private void initPopMenu() {
        View contentView = View.inflate(mCallBack.getActivity(), R.layout.popwin_supplier_list,
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
                mTvcharsort.setTextColor(Color.parseColor("#5a5959"));
                mTvquicksort.setTextColor(Color.parseColor("#5a5959"));
                mTvmanual.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });
    }
    public  void  getOrderList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("order/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.getIns().id_tokenparam)
                        .request(new ACallback<OrderBean>() {
                            @Override
                            public void onSuccess(OrderBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mOrderBeanList=data.getContent();
                                    if(page==0){
                                        if(adapter==null){
                                            adapter = new OrderListAdapter(R.layout.jiuyi_orders_item, mOrderBeanList);
                                            swipeMenuRecyclerView.setAdapter(adapter);
                                            adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
                                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    OrderBean.ContentBean contentBean= (OrderBean.ContentBean) adapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        String urladd=Res.getString(null, "jiuyiorderdetail");
                                                        String url="";
                                                        if(!Func.IsStringEmpty(urladd)){
                                                            url= Constant.BaseH5Url+urladd+"?orderID="+contentBean.getId()+"&token="+ Rc.id_tokenparam;
                                                        }
                                                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"销售订单");
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                                                    }
                                                }
                                            });

                                        }else{
                                            adapter.refresh(mOrderBeanList);
                                        }
                                        if(mOrderBeanList.size()==0||mOrderBeanList==null){
                                            adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        adapter.add(mOrderBeanList);
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
    public  QueryConditionBean  builderCondition(int page){
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
      /*     QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
         rulesBean.setField("operator");
        rulesBean.setOption("EQ");
        value.add(Rc.id+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);*/
        if(!Func.IsStringEmpty(spec) && !spec.equals("不限") ){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("spec");
            rulesBean2.setOption("EQ");
            value2.add(spec);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(grade)&& !grade.equals("不限")){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("grade");
            rulesBean2.setOption("EQ");
            value2.add(grade);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        if(getPageType()!=Pub.Orders_all){
            String status="";
            switch (mPageType) {
                case Pub.Orders_app:
                    status="PRICE_REVIEW";
                    break;
                case Pub.Orders_delivery:
                    status="DELIVERYED";
                    break;
                case Pub.Orders_completed:
                    status="RECEIVED";
                    break;

            }
            if(!Func.IsStringEmpty(status)){
                List<String> value2 = new ArrayList<String>();
                QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
                rulesBean2.setField("status");
                rulesBean2.setOption("EQ");
                value2.add(status);
                rulesBean2.setValues(value2);
                rulesBeanList.add(rulesBean2);
            }
        }
        if(!Func.IsStringEmpty(orderdate)){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("createdDate");
            rulesBean2.setOption("EQ");
            value2.add(orderdate);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);
        return queryConditionBean;
    }

    private void getSpecList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("material/find_spec")
                        .tag("request_find_spec")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<SpecBean>() {
                            @Override
                            public void onSuccess(SpecBean data) {
                                if(data!=null){
                                    menuStr1=data.getContent();
                                    menuData1 =  new ArrayList<Map<String, Object>>();
                                    Map<String, Object>  map1;
                                    map1 = new HashMap<String, Object>();
                                    map1.put("name", "不限");
                                    map1.put("select", imageids[0]);
                                    menuData1.add(map1);
                                    if(menuStr1!=null && menuStr1.size()>0){
                                        for (int i = 1, len =menuStr1.size(); i <=len; ++i) {
                                            map1 = new HashMap<String, Object>();
                                            map1.put("name", menuStr1.get(i-1));
                                            map1.put("select", imageids[0]);
                                            menuData1.add(map1);
                                        }
                                    }
                                    menuAdapter1=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData1);
                                }
                                popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                                            long arg3) {
                                        if (menuIndex == 0) {
                                            //改变选中状态
                                            menuAdapter1.setCurrentItem(pos);
                                            //通知ListView改变状态
                                            menuAdapter1.notifyDataSetChanged();
                                        }else if (menuIndex == 1) {
                                            //改变选中状态
                                            menuAdapter2.setCurrentItem(pos);
                                            //通知ListView改变状态
                                            menuAdapter2.notifyDataSetChanged();
                                        }
                                        popMenu.dismiss();
                                        if (menuIndex == 0) {
                                            currentProduct = menuData1.get(pos).get("name").toString();
                                            mTvcharsort.setText(currentProduct);
//                                            mTvcharsort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                                            Toast.makeText(mCallBack.getActivity(), currentProduct, Toast.LENGTH_SHORT).show();
                                            spec=currentProduct;
                                            getOrderList(0);
                                        }else if (menuIndex == 1) {
                                            currentSort = menuData2.get(pos).get("name").toString();
                                            mTvquicksort.setText(currentSort);
//                                            mTvquicksort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                                            Toast.makeText(mCallBack.getActivity(), currentSort, Toast.LENGTH_SHORT).show();
                                            grade=currentSort;
                                            getOrderList(0);
                                        }
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

}
