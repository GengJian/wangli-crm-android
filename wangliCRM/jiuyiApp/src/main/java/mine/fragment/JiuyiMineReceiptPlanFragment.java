package mine.fragment;

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
import com.control.widget.JiuyiEditText;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
//import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.CustomerSelMenuAdapter;
import customer.adapter.ReceiptPlanAdapter;
import customer.entity.GatheringPlanBean;
import customer.entity.GatheringplanChartBean;
import customer.view.SFPopupWindow;
import customer.view.jiuyiRecycleViewDivider;
import mine.bean.FindSortBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineReceiptPlanFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的收款计划
 *****************************************************************
 */
public class JiuyiMineReceiptPlanFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private ListView popListView;
    private List<Map<String, Object>> menuData1, menuData2, menuData3;
    private SFPopupWindow popMenu;
    private CustomerSelMenuAdapter menuAdapter1, menuAdapter2, menuAdapter3;

    private LinearLayout mLinemonthsort, mLinebirthplacesort, mLinecount;
    private TextView mTvDate, mTvSort, mTvstatus;

    private String currentProduct, currentActivity;
    private int menuIndex = 0;

    private int[] imageids = { R.drawable.client_selected };
    private JiuyiEditText medsearchplan;

    private  int year=0,month=0;
    RecyclerView swipeMenuRecyclerView;
    private  int mpage=0;
    private String msStatus="",msSort="";
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pageSize = 20;
    private int totalPage=0;

    List<GatheringplanChartBean.ListBean> listBeanList;
    private ReceiptPlanAdapter adapter;
    List<FindSortBean.ContentBean> sortList;
    private  int sortSelectPostion=-1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_minedeliveryplanfragment_layout"), null);
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
    public static JiuyiMineReceiptPlanFragment newInstance(int nPageType) {
        JiuyiMineReceiptPlanFragment f = new JiuyiMineReceiptPlanFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineReceiptPlanFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineReceiptPlanFragment f = new JiuyiMineReceiptPlanFragment();
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
        getSortList();
        listBeanList = new ArrayList<>();
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

    private void initMenuData() {

        String[] menuStr2 = new String[] { "不限", "未完成","已完成"};
        menuData2 =  new ArrayList<Map<String, Object>>();
        Map<String, Object>  map1;
        if(menuStr2!=null && menuStr2.length>0){
            for (int i = 0, len =menuStr2.length; i < len; ++i) {
                map1 = new HashMap<String, Object>();
                map1.put("name", menuStr2[i]);
                map1.put("select", imageids[0]);
                menuData2.add(map1);
            }
        }
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
                        mTvDate.setText("日期");
                        year=0;
                        month=0;
                        getGatheringPlanList(0);
                    }
                });
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        mTvDate.setText( new SimpleDateFormat("yyyy-MM").format(date));
                        if(mTvDate.getText().toString()!=null){
                            year=Integer.parseInt(mTvDate.getText().toString().substring(0,4));
                            month=Integer.parseInt(mTvDate.getText().toString().substring(5,7));
                        }

                        getGatheringPlanList(0);
                    }
                });
                dialog.show();
                break;
            case R.id.ll_place:
                mTvSort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter3);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 2;
                break;
            case R.id.ll_status:
                mTvstatus.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter2);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 1;
                break;
        }
    }
    protected void findView() {
        mLinemonthsort = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_monthsort"));
        mLinebirthplacesort = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_birthplace_sort"));
        mLinecount = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_countsort"));
        mTvDate = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_monthsort"));
        mTvDate.setText("日期");
        mTvSort = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_birthplacesort"));
        mTvSort.setText("排序");
        mTvstatus = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_countsort"));
        mTvstatus.setText("状态");

        mLinemonthsort.setOnClickListener(this);
        mLinebirthplacesort.setOnClickListener(this);
        mLinecount.setOnClickListener(this);
        LinearLayout ll_search= (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_search"));
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"ReceiptPlan");
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customerproduct_search);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customerproduct_search,true);
            }
        });



    }
    private void initPopMenu() {
        initMenuData();
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
                mTvDate.setTextColor(Color.parseColor("#5a5959"));
                mTvSort.setTextColor(Color.parseColor("#5a5959"));
                mTvstatus.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });
        menuAdapter2=new CustomerSelMenuAdapter(JiuyiMainApplication.getIns(), menuData2);
        menuAdapter3=new CustomerSelMenuAdapter(JiuyiMainApplication.getIns(), menuData3);
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                 if (menuIndex == 1) {
                    //改变选中状态
                    menuAdapter2.setCurrentItem(pos);
                    //通知ListView改变状态
                    menuAdapter2.notifyDataSetChanged();
                } else {
                    //改变选中状态
                    menuAdapter3.setCurrentItem(pos);
                    //通知ListView改变状态
                    menuAdapter3.notifyDataSetChanged();
                }
                popMenu.dismiss();
               if (menuIndex == 1) {
                    msStatus = menuData2.get(pos).get("name").toString();
                    mTvstatus.setText(msStatus);
                   getGatheringPlanList(0);
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), msStatus, Toast.LENGTH_SHORT).show();
                }else if (menuIndex == 2){
                    msSort = menuData3.get(pos).get("name").toString();
                    mTvSort.setText(msSort);
                   getGatheringPlanList(0);
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), msSort, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void  getGatheringPlanList(final int page){
        if(page==0){
            pageIndex=1;
        }
        final QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        if(!Func.IsStringEmpty(msStatus)  && !msStatus.equals("不限")){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            if(msStatus.equals("未完成")){
                rulesBean2.setField("actualShip");
                rulesBean2.setOption("LT");
                value2.add("'99,999%'");
            }else  if(msStatus.equals("已完成")){
                rulesBean2.setField("actualShip");
                rulesBean2.setOption("IN");
                value2.add("100%");
            }
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(msSort) && !msSort.equals("不限") && sortSelectPostion>0){
            if(sortList!=null && sortList.size()>0){
                String direction="",property="";
                direction=sortList.get(sortSelectPostion-1).getOption();
                property=sortList.get(sortSelectPostion-1).getValue();
                queryConditionBean.setDirection(direction);
                queryConditionBean.setProperty(property);
            }
        }else{
            queryConditionBean.setDirection("DESC");
            queryConditionBean.setProperty("createdDate");
        }
        if(year>0 ){
            List<Integer> value2 = new ArrayList<Integer>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("year");
            rulesBean2.setOption("EQ");
            value2.add(year);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(month>0 ){
            List<Integer> value2 = new ArrayList<Integer>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("month");
            rulesBean2.setOption("EQ");
            value2.add(month);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        queryConditionBean.setRules(rulesBeanList);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("gathering_plan/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<GatheringPlanBean>() {
                            @Override
                            public void onSuccess(GatheringPlanBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<GatheringPlanBean.ContentBean> contentBeanList=data.getContent();
                                    if(page==0){
                                        adapter = new ReceiptPlanAdapter(R.layout.customer_item_receiptplan, contentBeanList);
//                                        adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
                                        swipeMenuRecyclerView.setAdapter(adapter);
                                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                GatheringPlanBean.ContentBean contentBean=(GatheringPlanBean.ContentBean)adapter.getData().get(position);
                                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newReceiptplan);
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                if(contentBean.getStatus().equals("APPROVALED")){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }else{
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }
                                                String msOwetotal="",msDuetotal="",customerName,customerId;
//                                                if(contentBean.getMember()!=null){
//                                                    if(contentBean.getMember().getOwedTotalAmount()!=null){
//                                                        msOwetotal="￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(contentBean.getMember().getOwedTotalAmount()+""),0,2,true);
//                                                    }else{
//                                                        msOwetotal="￥"+"0";
//                                                    }
//                                                    if(contentBean.getMember().getDueTotalAmount()!=null){
//                                                        msDuetotal="￥"+Func.GetFormatVolumeStringByUnit2(Double.parseDouble(contentBean.getMember().getDueTotalAmount()+""),0,2,true);
//                                                    }else{
//                                                        msDuetotal="￥"+"0";
//                                                    }
//                                                    if(contentBean.getMember().getOrgName()!=null){
//                                                        mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, contentBean.getMember().getOrgName());
//                                                    }
//                                                    if(contentBean.getMember().getId()>0){
//                                                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, contentBean.getMember().getId());
//                                                    }
//                                                    mBundle.putString(JiuyiBundleKey.PARAM_OWETOTAL, msOwetotal);
//                                                    mBundle.putString(JiuyiBundleKey.PARAM_DUETOTAL, msDuetotal);
//                                                }


                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newReceiptplan,true);
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
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });

            }
        };
        thread.start();

    }

    private void getSortList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("gathering_plan/find_sort")
                        .tag("request_find_spec")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<FindSortBean>() {
                            @Override
                            public void onSuccess(FindSortBean data) {
                                if(data!=null){
                                    sortList=data.getContent();
                                    menuData3 =  new ArrayList<Map<String, Object>>();
                                    Map<String, Object>  map1;
                                    map1 = new HashMap<String, Object>();
                                    map1.put("name", "不限");
                                    map1.put("select", imageids[0]);
                                    menuData3.add(map1);
                                    Map<String, Object>  map3;
                                    if(sortList!=null && sortList.size()>0){
                                        for (int i = 1, len =sortList.size(); i <=len; ++i) {
                                            map3 = new HashMap<String, Object>();
                                            map3.put("name", sortList.get(i-1).getKey());
                                            map3.put("select", imageids[0]);
                                            menuData3.add(map3);
                                        }
                                    }
                                }
                                menuAdapter3=new CustomerSelMenuAdapter(JiuyiMainApplication.getIns(), menuData3);
                                popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                                            long arg3) {
                                        if (menuIndex == 1) {
                                            //改变选中状态
                                            menuAdapter2.setCurrentItem(pos);
                                            //通知ListView改变状态
                                            menuAdapter2.notifyDataSetChanged();
                                        }else if(menuIndex == 2){
                                            menuAdapter3.setCurrentItem(pos);
                                            //通知ListView改变状态
                                            menuAdapter3.notifyDataSetChanged();
                                        }
                                        popMenu.dismiss();
                                        if (menuIndex == 1) {
                                            msStatus=menuData2.get(pos).get("name").toString();
                                            mTvstatus.setText(menuData2.get(pos).get("name").toString());
                                            Toast.makeText(JiuyiMainApplication.getIns(), msStatus, Toast.LENGTH_SHORT).show();
                                            getGatheringPlanList(0);
                                        }else if(menuIndex == 2){
                                            sortSelectPostion=pos;
                                            msSort=menuData3.get(pos).get("name").toString();
                                            mTvSort.setText(menuData3.get(pos).get("name").toString());
                                            Toast.makeText(JiuyiMainApplication.getIns(), msSort, Toast.LENGTH_SHORT).show();
                                            getGatheringPlanList(0);
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
