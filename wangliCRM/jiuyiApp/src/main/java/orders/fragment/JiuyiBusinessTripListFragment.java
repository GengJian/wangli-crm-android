package orders.fragment;

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

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.wanglicrm.android.R;
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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
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
import customer.adapter.ReceiveAddressAdapter;
import customer.view.SFPopupWindow;
import customer.view.jiuyiRecycleViewDivider;
import orders.adapter.BusinessTripListAdapter;
import orders.entity.BusinessTripBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiBusinessTripListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 :差旅列表界面
 *****************************************************************
 */
public class JiuyiBusinessTripListFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private ListView popListView;
    private List<Map<String, Object>> menuData1, menuData2;
    private SFPopupWindow popMenu;
    private CustomerSelMenuAdapter menuAdapter1, menuAdapter2;

    private LinearLayout ll_date, ll_place, ll_status;
    private TextView tv_date, tv_place, tv_status;

    private String sStatus, currentSort;
    private int menuIndex = 0;
    private int[] imageids = { R.drawable.client_selected };

    QueryConditionBean queryConditionBean;
    List<QueryConditionBean.RulesBean> rulesBeanList;
    private BusinessTripListAdapter adapter;
    private List<BusinessTripBean.ContentBean> mTripBeanList;
    private  int pageIndex=1,pagesize=20,totalPage=0;
    RefreshLayout refreshLayout;
    RecyclerView swipeMenuRecyclerView;
    private String sDate ="";

    public String getsSearch() {
        return sSearch;
    }

    public void setsSearch(String sSearch) {
        this.sSearch = sSearch;
    }

    private String sSearch="";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_triplistfragment_layout"), null);
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
    public static JiuyiBusinessTripListFragment newInstance(int nPageType) {
        JiuyiBusinessTripListFragment f = new JiuyiBusinessTripListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiBusinessTripListFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiBusinessTripListFragment f = new JiuyiBusinessTripListFragment();
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

        mTripBeanList = new ArrayList<>();
        swipeMenuRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_orderlist);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity(), 1, false));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
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
        getInfoList(0);
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
        menuData1 = new ArrayList<Map<String, Object>>();
        Map<String, Object>map2;
        String[] menuStr2 = new String[] { "不限", "未提交","提交OA成功","提交OA失败" };
        for (int i = 0, len = menuStr2.length; i < len; ++i) {
            map2 = new HashMap<String, Object>();
            map2.put("name", menuStr2[i]);
            map2.put("select", imageids[0]);
            menuData1.add(map2);
        }
        menuAdapter1=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData1);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_status:
                popListView.setAdapter(menuAdapter1);
                popMenu.showAsDropDown(ll_status, 0, 2);
                menuIndex = 0;
                break;

            case R.id.ll_place:
                tv_place.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter2);
                popMenu.showAsDropDown(ll_date, 0, 2);
                menuIndex = 1;
                break;
            case R.id.ll_date:
                tv_date.setTextColor(Res.getColor(null, "jiuyi_blue"));
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
                        tv_date.setText("出差日期");
                        sDate ="";
                        getInfoList(0);
                    }
                });
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        tv_date.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        sDate= tv_date.getText().toString();
                        getInfoList(0);
                    }
                });
                dialog.show();
                break;

        }
    }
    protected void findView() {
        ll_date = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_date"));
        ll_place = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_place"));
        ll_status = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_status"));
        tv_date = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_date"));
        tv_place = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_place"));
        tv_status = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_status"));
        ll_date.setOnClickListener(this);
        ll_place.setOnClickListener(this);
        ll_status.setOnClickListener(this);
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
                tv_date.setTextColor(Color.parseColor("#5a5959"));
                tv_place.setTextColor(Color.parseColor("#5a5959"));
                tv_status.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                if (menuIndex == 0) {
                    //改变选中状态
                    menuAdapter1.setCurrentItem(pos);
                    //通知ListView改变状态
                    menuAdapter1.notifyDataSetChanged();
                }
                popMenu.dismiss();
                if (menuIndex == 0) {
                    sStatus=menuData1.get(pos).get("name").toString();
                    tv_status.setText(menuData1.get(pos).get("name").toString());
                    getInfoList(0);
                }
            }
        });

        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });
    }
    public  void getInfoList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("travel-business/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<BusinessTripBean>() {
                    @Override
                    public void onSuccess(BusinessTripBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mTripBeanList=data.getContent();
                            if(page==0){
                                if(adapter==null){
                                    adapter = new BusinessTripListAdapter(R.layout.jiuyi_trip_item, mTripBeanList);
                                    swipeMenuRecyclerView.setAdapter(adapter);
                                    adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
                                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            BusinessTripBean.ContentBean contentBean= (BusinessTripBean.ContentBean) adapter.getData().get(position);
                                            if(contentBean.getOperator()!=null){
                                                if( contentBean.getOperator().getId()==Rc.id && !contentBean.isCommit()){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                }else {
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                }
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"travel-business");
                                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "差旅详情");
                                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newDynamicForm,true);
                                            }


                                        }
                                    });

                                }else{
                                    adapter.refresh(mTripBeanList);
                                }
                                if(mTripBeanList.size()==0|| mTripBeanList ==null){
                                    adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                adapter.add(mTripBeanList);
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
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        if(!Func.IsStringEmpty(sDate) ){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("travelDate");
            rulesBean2.setOption("BTD");
            value2.add(sDate+" 00:00:00");
            value2.add(sDate+" 23:59:59");
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        if(!Func.IsStringEmpty(sStatus) && !sStatus.equals("不限") && !sStatus.equals("状态")){
            String sStatusKey="";
            if(sStatus.equals("未提交")){
                sStatusKey="NOCOMMIT";
            }else if(sStatus.equals("提交OA成功")){
                sStatusKey="SUCCESS";
            }else if(sStatus.equals("提交OA失败")){
                sStatusKey="ERROR";
            }
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("travelStatus");
            rulesBean2.setOption("EQ");
            value2.add(sStatusKey);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(sSearch)){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("searchContent");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(sSearch);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }


        queryConditionBean.setRules(rulesBeanList);
        return queryConditionBean;
    }
    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh){
            Rc.mBackfresh=false;
            getInfoList(0);
        }

    }
    @Override
    public void createReq(boolean IsBg) {
        getInfoList(0);
    }

}
