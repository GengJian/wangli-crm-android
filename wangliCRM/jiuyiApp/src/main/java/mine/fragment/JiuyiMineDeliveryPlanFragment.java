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
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.Utils.ViewOperatorType;
import customer.adapter.CustomerSelMenuAdapter;
import customer.adapter.NeedPlanAdapter;
import customer.entity.DemandPlanBean;
import customer.view.jiuyiRecycleViewDivider;
import mine.bean.MineDeliveryPlanBean;
import mine.bean.PlanFindDetailCondition;
import dynamic.entity.SpecBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineDeliveryPlanFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的发货计划
 *****************************************************************
 */
public class JiuyiMineDeliveryPlanFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private ListView popListView;
    private List<Map<String, Object>> menuData1, menuData2, menuData3;
    private PopupWindow popMenu;
    private CustomerSelMenuAdapter menuAdapter1, menuAdapter2, menuAdapter3;

    private LinearLayout mLinemonthsort, mLinebirthplacesort, mLinecount,ll_condition;
    private TextView mTvcharsort, mTvbirthPlace, mTvspec;

    private int menuIndex = 0;
    private int[] imageids = { R.drawable.client_selected };
    private  NeedPlanAdapter adapter;

    private  int year=0,month=0;
    RecyclerView swipeMenuRecyclerView;
    private  int mpage=0;
    private String msPlace="",mspec="";
    RefreshLayout refreshLayout;
    private int pageIndex = 1;
    private int pageSize = 20;
    private List<String> menuStr2;
    private int totalPage=0;
    MineDeliveryPlanBean.ContentBean contentBean;
    PlanFindDetailCondition planFindDetailCondition;

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
    public static JiuyiMineDeliveryPlanFragment newInstance(int nPageType) {
        JiuyiMineDeliveryPlanFragment f = new JiuyiMineDeliveryPlanFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineDeliveryPlanFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineDeliveryPlanFragment f = new JiuyiMineDeliveryPlanFragment();
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
/*        Calendar m_Curdate = Calendar.getInstance();
        year = m_Curdate.get(Calendar.YEAR);// 获取当前年份
        month= m_Curdate.get(Calendar.MONTH)+1;// 获取当前年份*/
        contentBean= mBundle.getParcelable(JiuyiBundleKey.PARAM_PLANEAN);
        planFindDetailCondition=new PlanFindDetailCondition();
        if(contentBean!=null){
            planFindDetailCondition.setOperatorCollectBean(contentBean);
        }
        findView();
        initMenuData();
        initPopMenu();
        getSpecList();
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
        menuData3 = new ArrayList<Map<String, Object>>();
        Map<String, Object>map3;
        String[] menuStr3;
        final List<DictBean> dictBeansList= DictUtils.getDictList("product-place");
        if(dictBeansList!=null &&dictBeansList.size()>0){
            menuStr3=new String[dictBeansList.size()+1];
            map3 = new HashMap<String, Object>();
            map3.put("name", "不限");
            map3.put("select", imageids[0]);
            menuData3.add(map3);
            for(int i=1;i<=dictBeansList.size();i++){
                menuStr3[i]=dictBeansList.get(i-1).getValue();
                map3 = new HashMap<String, Object>();
                map3.put("name", menuStr3[i]);
                map3.put("select", imageids[0]);
                menuData3.add(map3);
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
                        mTvcharsort.setText("日期");
                        year=0;
                        month=0;
                        getGatheringPlanList(0);
                    }
                });
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        mTvcharsort.setText( new SimpleDateFormat("yyyyMM").format(date));
                        if(mTvcharsort.getText().toString()!=null){
                            year=Integer.parseInt(mTvcharsort.getText().toString().substring(0,4));
                            month=Integer.parseInt(mTvcharsort.getText().toString().substring(5,6));
                        }

                        getGatheringPlanList(0);
                    }
                });
                dialog.show();
                break;
            case R.id.ll_place:
                mTvbirthPlace.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter3);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 2;
                break;
            case R.id.ll_status:
                mTvspec.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter2);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 1;
                break;

        }
    }
    protected void findView() {
        ll_condition = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_condition"));
        ll_condition.setVisibility(View.GONE);
        mLinemonthsort = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_monthsort"));
        mLinebirthplacesort = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_birthplace_sort"));
        mLinecount = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_countsort"));
        mTvcharsort = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_monthsort"));
        mTvbirthPlace = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_birthplacesort"));
        mTvspec = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_countsort"));

        mLinemonthsort.setOnClickListener(this);
        mLinebirthplacesort.setOnClickListener(this);
        mLinecount.setOnClickListener(this);
        LinearLayout ll_search= (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_search"));
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Mine");
                if(contentBean!=null){
                    mBundle.putParcelable(JiuyiBundleKey.PARAM_PLANEAN,contentBean);
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customerproduct_search);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customerproduct_search,true);
            }
        });
    }
    private void initPopMenu() {
        initMenuData();
        View contentView = View.inflate(JiuyiMainApplication.getIns(), R.layout.popwin_supplier_list,
                null);
        popMenu = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                mTvcharsort.setTextColor(Color.parseColor("#5a5959"));
                mTvbirthPlace.setTextColor(Color.parseColor("#5a5959"));
                mTvspec.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });
        menuAdapter1=new CustomerSelMenuAdapter(JiuyiMainApplication.getIns(), menuData1);
        menuAdapter3=new CustomerSelMenuAdapter(JiuyiMainApplication.getIns(), menuData3);
    }

    public  void  getGatheringPlanList(final int page){
        if(page==0){
            pageIndex=1;
        }
        PlanFindDetailCondition.PagerBean pager =new PlanFindDetailCondition.PagerBean();
        pager.setSize(20);
        pager.setDirection("DESC");
        pager.setProperty("createdDate");
        pager.setNumber(0);
        planFindDetailCondition.setPager(pager);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("demand_plan/find_detail_plan?")
                        .setJson(GsonUtil.gson().toJson(planFindDetailCondition))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<DemandPlanBean>() {
                            @Override
                            public void onSuccess(DemandPlanBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<DemandPlanBean.ContentBean> contentBeanList=data.getContent();
                                    if(page==0){
                                        if(adapter==null){
                                            adapter = new NeedPlanAdapter(R.layout.customer_item_needplan, contentBeanList);
                                            swipeMenuRecyclerView.setAdapter(adapter);
//                                            adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
                                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    DemandPlanBean.ContentBean contentBean=(DemandPlanBean.ContentBean)adapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        if(contentBean.getStatus()!=null){
                                                            if(contentBean.getStatus().equals("APPROVALED")){
                                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                            }else{
                                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                            }
                                                        }
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_NEEDPLANID, contentBean.getId());
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newneedplan);
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newneedplan,true);
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
                                    menuStr2=data.getContent();
                                    menuData2 =  new ArrayList<Map<String, Object>>();
                                    Map<String, Object>  map1;
                                    map1 = new HashMap<String, Object>();
                                    map1.put("name", "不限");
                                    map1.put("select", imageids[0]);
                                    menuData2.add(map1);
                                    if(menuStr2!=null && menuStr2.size()>0){
                                        for (int i = 1, len =menuStr2.size(); i <=len; ++i) {
                                            map1 = new HashMap<String, Object>();
                                            map1.put("name", menuStr2.get(i-1));
                                            map1.put("select", imageids[0]);
                                            menuData2.add(map1);
                                        }
                                    }
                                }
                                menuAdapter2=new CustomerSelMenuAdapter(JiuyiMainApplication.getIns(), menuData2);
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
                                            mspec=menuData2.get(pos).get("name").toString();
                                            mTvspec.setText(menuData2.get(pos).get("name").toString());
                                            Toast.makeText(JiuyiMainApplication.getIns(), mspec, Toast.LENGTH_SHORT).show();
                                            getGatheringPlanList(0);
                                        }else if(menuIndex == 2){
                                            msPlace=menuData3.get(pos).get("name").toString();
                                            mTvbirthPlace.setText(menuData3.get(pos).get("name").toString());
                                            Toast.makeText(JiuyiMainApplication.getIns(), msPlace, Toast.LENGTH_SHORT).show();
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
