package commonlyused.fragment;

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
import android.widget.ImageView;
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
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;
import com.wanglicrm.android.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.adapter.TaskListAdapter;
import commonlyused.bean.QueryConditionBean;
import commonlyused.bean.TaskBean;
import commonlyused.bean.TaskCondtionBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.CustomerSelMenuAdapter;
import customer.adapter.ReceiveAddressAdapter;
import customer.view.SFPopupWindow;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiTaskListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用任务列表
 *****************************************************************
 */
public class JiuyiTaskListFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private ListView popListView;
    private List<Map<String, Object>> menuData1;
    private SFPopupWindow popMenu;
    private CustomerSelMenuAdapter menuAdapter1, menuAdapter3;

    private LinearLayout mLinemonthsort, ll_searchplan, mLinecount;
    private TextView mTvcharsort, mTvmanual;

    private String currentProduct, currentSort, currentActivity;
    private int menuIndex = 0;
    private int[] imageids = { R.drawable.client_selected };
    RecyclerView swipeMenuRecyclerView;
    private TaskListAdapter adapter;
    private String taskdate ="";
    QueryConditionBean queryConditionBean;
    private List<TaskBean.ContentBean> mTaskBeanList;
    RefreshLayout refreshLayout;
    private  int pageIndex=1,pagesize=20,totalPage=0;
    List<TaskCondtionBean.ContentBean.MemberChooseBeansBean> memberChooseBeans;
    private String smine="";
    private ImageView iv_addplan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_tasklistfragment_layout"), null);
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
    public static JiuyiTaskListFragment newInstance(int nPageType) {
        JiuyiTaskListFragment f = new JiuyiTaskListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiTaskListFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiTaskListFragment f = new JiuyiTaskListFragment();
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
        getConditionList();
        findView();
//        initMenuData();
        initPopMenu();
        ll_searchplan=(LinearLayout) mRootView.findViewById(Res.getViewID(getCallBack().getActivity(), "ll_searchplan"));
        if(ll_searchplan!=null){
            ll_searchplan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Normal_TaskSearch);
                    changePage(mBundle,Pub.Normal_TaskSearch,true);
                }
            });
        }
        iv_addplan = (ImageView) mRootView.findViewById(Res.getViewID(getContext(), "iv_addplan"));
        iv_addplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.Normal_NewTask);
                changePage(mBundle, Pub.Normal_NewTask,true);
            }
        });
        swipeMenuRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_orderlist);
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity(), 1, false));
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                getTaskList(0);
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
                    getTaskList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;

            }
        });
        getTaskList(0);
        showProcessBar(0);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_monthsort:
                mTvcharsort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter1);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 0;
                break;
            case R.id.ll_countsort:
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
                        mTvmanual.setText("任务截止日期");
                        taskdate="";
                        getTaskList(0);
                    }
                });

                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        mTvmanual.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        if(mTvmanual.getText().toString()!=null){
                            taskdate =mTvmanual.getText().toString();
                            getTaskList(0);
                        }
                    }
                });
                dialog.show();
                break;

        }
    }
    protected void findView() {
        mLinemonthsort = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_monthsort"));
        mLinecount = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_countsort"));
        mTvcharsort = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_monthsort"));
        mTvmanual = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_countsort"));
        mLinemonthsort.setOnClickListener(this);
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
                mTvmanual.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });

        menuAdapter1=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData1);
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
                    currentProduct = menuData1.get(pos).get("name").toString();
                    mTvcharsort.setText(currentProduct);
                    Toast.makeText(mCallBack.getActivity(), currentProduct, Toast.LENGTH_SHORT).show();
                } else {
                    mTvmanual.setText(currentActivity);
                    Toast.makeText(mCallBack.getActivity(), currentActivity, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void getTaskList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("task/page-two")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.getIns().id_tokenparam)
                        .request(new ACallback<TaskBean>() {
                            @Override
                            public void onSuccess(TaskBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTaskBeanList=data.getContent();
                                    if(page==0){
                                        if(adapter==null){
                                            adapter = new TaskListAdapter(R.layout.jiuyi_task_item, mTaskBeanList);
                                            swipeMenuRecyclerView.setAdapter(adapter);
                                            adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
                                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    TaskBean.ContentBean contentBean= (TaskBean.ContentBean) adapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        String urladd=Res.getString(null, "jiuyitaskdetail");
                                                        String url="";
                                                        if(!Func.IsStringEmpty(urladd)){
                                                            url= Constant.BaseH5Url+urladd+"?loginid="+Rc.id+"&id="+contentBean.getId()+"&token="+ Rc.id_tokenparam;
                                                        }
                                                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"任务详情");
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                                                    }
                                                }
                                            });

                                        }else{
                                            adapter.refresh(mTaskBeanList);
                                        }
                                        if(mTaskBeanList.size()==0||mTaskBeanList==null){
                                            adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        adapter.add(mTaskBeanList);
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
    public QueryConditionBean builderCondition(int page){
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        if(!Func.IsStringEmpty(smine) && !smine.equals("不限") ){
            List<String> specialConditions;
            specialConditions=new ArrayList<String>();
            specialConditions.add(smine);
            queryConditionBean.setSpecialConditions(specialConditions);
        }
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;

        if(getPageType()!=Pub.Normal_TaskAll){
            String status="";
            switch (mPageType) {
                case Pub.Normal_TaskUnreceipt:
                    status="distributed";
                    break;
                case Pub.Normal_TaskUndo:
                    status="received";
                    break;
                case Pub.Normal_Taskcompleted:
                    status="finished";
                    break;
                case Pub.Normal_TaskcompletedConfirm:
                    status="confirm_finished";
                    break;

            }
            if(!Func.IsStringEmpty(status)){
                List<String> value2 = new ArrayList<String>();
                QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
                rulesBean2.setField("statusKey");
                rulesBean2.setOption("EQ");
                value2.add(status);
                rulesBean2.setValues(value2);
                rulesBeanList.add(rulesBean2);
            }
        }
        if(!Func.IsStringEmpty(taskdate)){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("date");
            rulesBean2.setOption("EQ");
            value2.add(taskdate);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(rulesBeanList.size()>0){
            queryConditionBean.setRules(rulesBeanList);
        }
        return queryConditionBean;
    }
    private void getConditionList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("task/condition-list")
                        .tag("request_condition-list")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<TaskCondtionBean>() {
                            @Override
                            public void onSuccess(TaskCondtionBean data) {
                                if(data!=null){
                                     memberChooseBeans=data.getContent().get(0).getMemberChooseBeans();
                                    if(memberChooseBeans!=null && memberChooseBeans.size()>0){

                                        menuData1 =  new ArrayList<Map<String, Object>>();
                                        Map<String, Object>  map1;
                                        map1 = new HashMap<String, Object>();
                                        map1.put("name", "不限");
                                        map1.put("select", imageids[0]);
                                        menuData1.add(map1);
                                        for (int i = 1, len =memberChooseBeans.size(); i <= len; ++i) {
                                            map1 = new HashMap<String, Object>();
                                            map1.put("name", memberChooseBeans.get(i-1).getName());
                                            map1.put("select", imageids[0]);
                                            menuData1.add(map1);
                                        }
                                        menuAdapter1=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData1);
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
                                                    currentSort = menuData1.get(pos).get("name").toString();
                                                    mTvcharsort.setText(currentSort);
                                                    Toast.makeText(mCallBack.getActivity(), currentSort, Toast.LENGTH_SHORT).show();
                                                    if(pos>0){
                                                        smine=memberChooseBeans.get(pos-1).getMemberFeild();
                                                    }else{
                                                        smine="不限";
                                                    }
                                                    getTaskList(0);
                                                }
                                            }
                                        });
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
}
