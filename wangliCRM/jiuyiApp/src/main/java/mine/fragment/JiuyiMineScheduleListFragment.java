package mine.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.common.GsonUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.tools.CustomPopWindow;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.CustomerBusinessReceiptAdapter;
import customer.adapter.VisitItemListAdapter;
import customer.entity.CustomerBusinessReceiptBean;
import customer.entity.VisitActivityBean;
import customer.entity.VisitActivityListBean;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.Utils.DynamicConstant;
import mine.bean.MineVisitBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineScheduleListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的日历
 *****************************************************************
 */
public class JiuyiMineScheduleListFragment extends JiuyiFragmentBase implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener
         {

    private VisitItemListAdapter adapter;
    private CustomerBusinessReceiptAdapter customerBusinessReceiptAdapter;
    private List<VisitActivityListBean> mVisitActivityListBeanList;
    private List<CustomerBusinessReceiptBean.ContentBean> mReceiptListBeanList;
    RecyclerView rvInfolist;
    ImageButton mibadd;

    TextView mTextMonthDay;
    TextView mTextYear;
    TextView mTextLunar;
    TextView mTextCurrentDay, tv_title;
    CalendarView mCalendarView;
    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    private String currentDate="",currentMonth="";
    private CustomPopWindow mCustomPopWindow;
    private int curpostion=-1;
    QueryConditionBean queryConditionBean;
    int pageIndex = 1;
    int pagesize = 20;
    private int totalPage=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_mineschedulefragment_layout"), null);
//        setOnRefreshListener();
        onInit();
        return mRootView;
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineScheduleListFragment newInstance(int nPageType) {
        JiuyiMineScheduleListFragment f = new JiuyiMineScheduleListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineScheduleListFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineScheduleListFragment f = new JiuyiMineScheduleListFragment();
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
        initView();
        mVisitActivityListBeanList = new ArrayList<>();
        rvInfolist = (RecyclerView) mRootView.findViewById(R.id.rv_infolist);
        rvInfolist.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns(), 1, false));
        rvInfolist.setHasFixedSize(true);
        rvInfolist.setItemAnimator(new DefaultItemAnimator());
        rvInfolist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));
        if(mPageType==Pub.Mine_Date_Visit){
            getVisitActivityList(currentDate);
            getScheduleList(0);
        }else if(mPageType==Pub.Mine_Date_Receipt){
            getReceiptActivityList(currentDate);
            getReceiptList(0);
        }

        ImageButton mibadd;
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            if((mPageType==Pub.Mine_Date_Receipt)){
                mibadd.setVisibility(View.GONE);
            }
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle mBundle=new Bundle();
                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                    mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"visit-activity");
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建拜访活动");
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newDynamicForm,true);

                }
            });
        }
        showProcessBar(0);
    }
    protected void initView() {
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                if(mPageType==Pub.Mine_Date_Visit){
                    getScheduleList(0);
                }else if(mPageType==Pub.Mine_Date_Receipt){
                    getReceiptList(0);
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
                    if(mPageType==Pub.Mine_Date_Visit){
                        getScheduleList(pageIndex);
                    }else if(mPageType==Pub.Mine_Date_Receipt){
                        getReceiptList(pageIndex);
                    }
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;
            }
        });
        tv_title = (TextView) mRootView.findViewById(R.id.tv_title);
        mTextMonthDay = (TextView) mRootView.findViewById(R.id.tv_month_day);
        mTextYear = (TextView) mRootView.findViewById(R.id.tv_year);
        mTextLunar = (TextView) mRootView.findViewById(R.id.tv_lunar);
        mRelativeTool = (RelativeLayout) mRootView.findViewById(R.id.rl_tool);
        mCalendarView = (CalendarView) mRootView.findViewById(R.id.calendarView);
        mTextCurrentDay = (TextView) mRootView.findViewById(R.id.tv_current_day);
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showYearSelectLayout(mYear);
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        mRootView.findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarLayout = (CalendarLayout) mRootView.findViewById(R.id.calendarLayout);
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnlyCurrentMode();
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        currentDate=mCalendarView.getCurYear()+"-"+mCalendarView.getCurMonth()+"-"+mCalendarView.getCurDay();
        currentMonth=mCalendarView.getCurYear()+"-"+mCalendarView.getCurMonth();
        tv_title.setText(currentDate);

    }
    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());
        calendar.addScheme(0xFF008800, "假");
        calendar.addScheme(0xFF008800, "节");
        return calendar;
    }

    public void  getVisitActivityList(String date){
        JiuyiHttp.GET("visit-activity/calendar-list-operator-mobile/"+Rc.id+"/"+date)
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<VisitActivityBean>() {
                    @Override
                    public void onSuccess(VisitActivityBean data) {
                        if(data!=null){
                            Map<String, Calendar> map = new HashMap<>();
                            if(data.getPre()!=null && data.getPre().size()>0){
                                for(int i=0;i<data.getPre().size();i++){
                                    int year = Integer.parseInt(data.getPre().get(i).getFieldValue().substring(0,4));
                                    int month = Integer.parseInt(data.getPre().get(i).getFieldValue().substring(5,7));
                                    int day= Integer.parseInt(data.getPre().get(i).getFieldValue().substring(8,10));

                                    map.put(getSchemeCalendar(year, month, day, 0xFFdf1356, "拜访").toString(),
                                            getSchemeCalendar(year, month, day, 0xFFdf1356, "拜访"));
                                }
                            }
                            if(data.getCurrent()!=null && data.getCurrent().size()>0){
                                for(int i=0;i<data.getCurrent().size();i++){
                                    int year = Integer.parseInt(data.getCurrent().get(i).getFieldValue().substring(0,4));
                                    int month = Integer.parseInt(data.getCurrent().get(i).getFieldValue().substring(5,7));
                                    int day= Integer.parseInt(data.getCurrent().get(i).getFieldValue().substring(8,10));
                                    map.put(getSchemeCalendar(year, month, day, 0xFFdf1356, "拜访").toString(),
                                            getSchemeCalendar(year, month, day, 0xFFdf1356, "拜访"));
                                }
                            }
                            if(data.getNext()!=null && data.getNext().size()>0){
                                for(int i=0;i<data.getNext().size();i++){
                                    int year = Integer.parseInt(data.getNext().get(i).getFieldValue().substring(0,4));
                                    int month = Integer.parseInt(data.getNext().get(i).getFieldValue().substring(5,7));
                                    int day= Integer.parseInt(data.getNext().get(i).getFieldValue().substring(8,10));
                                    map.put(getSchemeCalendar(year, month, day, 0xFFdf1356, "拜访").toString(),
                                            getSchemeCalendar(year, month, day, 0xFFdf1356, "拜访"));
                                }
                            }
                            mCalendarView.setSchemeDate(map);

                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

     public  void getScheduleList(final int page){
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
         if(!Func.IsStringEmpty(currentDate)){
             QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
             rulesBean.setField("dateStr");
             rulesBean.setOption("EQ");
             value.add(currentDate);
             rulesBean.setValues(value);
             rulesBeanList.add(rulesBean);
         }
         QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
         rulesBean2.setField("visitor.id");
         rulesBean2.setOption("EQ");
         List<Long> value2 = new ArrayList<Long>();
         value2.add(Rc.id);
         rulesBean2.setValues(value2);
         rulesBeanList.add(rulesBean2);

         queryConditionBean.setRules(rulesBeanList);
         JiuyiHttp.POST("visit-activity/page")
                 .setJson(GsonUtil.gson().toJson(queryConditionBean))
                 .addHeader("Authorization",Rc.getIns().id_tokenparam)
                 .request(new ACallback<MineVisitBean>() {
                     @Override
                     public void onSuccess(MineVisitBean data) {
                         if(data!=null){
                             mVisitActivityListBeanList =data.getContent();

                             if(page==0){
                                 adapter = new VisitItemListAdapter(R.layout.jiuyi_customer_visitlist_item, mVisitActivityListBeanList);
                                 rvInfolist.setAdapter(adapter);
                                 adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                     @Override
                                     public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                         Bundle mBundle=new Bundle();
                                         mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN, (VisitActivityListBean)adapter.getData().get(position));
                                         mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"拜访活动");
                                         Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_VisitDetail,true);

                                     }
                                 });
                                 adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                                     @Override
                                     public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                                         curpostion=position;
                                         showPopMenu(view);
                                         return false;
                                     }
                                 });
                                 if(mVisitActivityListBeanList==null||mVisitActivityListBeanList.size()==0){
                                     adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfolist.getParent());
                                 }
                                 showProcessBar(100);
                             }else{
                                 adapter.add(mVisitActivityListBeanList);
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

     public void  getReceiptActivityList(String date){
         JiuyiHttp.GET("customer-base-reception/calendar-list-operator-mobile/"+Rc.id+"/"+date)
                 .addHeader("Authorization", Rc.getIns().id_tokenparam)
                 .request(new ACallback<VisitActivityBean>() {
                     @Override
                     public void onSuccess(VisitActivityBean data) {
                         if(data!=null){
                             Map<String, Calendar> map = new HashMap<>();
                             if(data.getPre()!=null && data.getPre().size()>0){
                                 for(int i=0;i<data.getPre().size();i++){
                                     int year = Integer.parseInt(data.getPre().get(i).getFieldValue().substring(0,4));
                                     int month = Integer.parseInt(data.getPre().get(i).getFieldValue().substring(5,7));
                                     int day= Integer.parseInt(data.getPre().get(i).getFieldValue().substring(8,10));

                                     map.put(getSchemeCalendar(year, month, day, 0xFFdf1356, "接待").toString(),
                                             getSchemeCalendar(year, month, day, 0xFFdf1356, "接待"));
                                 }
                             }
                             if(data.getCurrent()!=null && data.getCurrent().size()>0){
                                 for(int i=0;i<data.getCurrent().size();i++){
                                     int year = Integer.parseInt(data.getCurrent().get(i).getFieldValue().substring(0,4));
                                     int month = Integer.parseInt(data.getCurrent().get(i).getFieldValue().substring(5,7));
                                     int day= Integer.parseInt(data.getCurrent().get(i).getFieldValue().substring(8,10));
                                     map.put(getSchemeCalendar(year, month, day, 0xFFdf1356, "接待").toString(),
                                             getSchemeCalendar(year, month, day, 0xFFdf1356, "接待"));
                                 }
                             }
                             if(data.getNext()!=null && data.getNext().size()>0){
                                 for(int i=0;i<data.getNext().size();i++){
                                     int year = Integer.parseInt(data.getNext().get(i).getFieldValue().substring(0,4));
                                     int month = Integer.parseInt(data.getNext().get(i).getFieldValue().substring(5,7));
                                     int day= Integer.parseInt(data.getNext().get(i).getFieldValue().substring(8,10));
                                     map.put(getSchemeCalendar(year, month, day, 0xFFdf1356, "拜访").toString(),
                                             getSchemeCalendar(year, month, day, 0xFFdf1356, "拜访"));
                                 }
                             }
                             mCalendarView.setSchemeDate(map);

                         }
                     }

                     @Override
                     public void onFail(int errCode, String errMsg) {
                         showProcessBar(100);
                         startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                     }
                 });


     }
     public  void getReceiptList(final int page){
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
         if(!Func.IsStringEmpty(currentDate)){
             QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
             rulesBean.setField("dateStr");
             rulesBean.setOption("EQ");
             value.add(currentDate);
             rulesBean.setValues(value);
             rulesBeanList.add(rulesBean);
         }
         QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
         rulesBean2.setField("createOperator.id");
         rulesBean2.setOption("EQ");
         List<Long> value2 = new ArrayList<Long>();
         value2.add(Rc.id);
         rulesBean2.setValues(value2);
         rulesBeanList.add(rulesBean2);

         queryConditionBean.setRules(rulesBeanList);
         JiuyiHttp.POST("customer-base-reception/page")
                 .setJson(GsonUtil.gson().toJson(queryConditionBean))
                 .addHeader("Authorization",Rc.getIns().id_tokenparam)
                 .request(new ACallback<CustomerBusinessReceiptBean>() {
                     @Override
                     public void onSuccess(CustomerBusinessReceiptBean data) {
                         if(data!=null){
                             mReceiptListBeanList =data.getContent();

                             if(page==0){
                                 customerBusinessReceiptAdapter = new CustomerBusinessReceiptAdapter(R.layout.jiuyi_customer_visitlist_item, mReceiptListBeanList);
                                 rvInfolist.setAdapter(customerBusinessReceiptAdapter);
                                 customerBusinessReceiptAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                     @Override
                                     public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                         CustomerBusinessReceiptBean.ContentBean contentBean =(CustomerBusinessReceiptBean.ContentBean)adapter.getData().get(position);
                                         if(contentBean!=null){
                                             String url="";
                                             url= Constant.BaseH5Url+ DynamicConstant.CUSTOMER_RECEIPT_URL+contentBean.getId()+"&token="+ Rc.id_tokenparam;
                                             mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                             mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"客户接待详情");
                                             Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                                         }



                                     }
                                 });
                                 if(mReceiptListBeanList==null||mReceiptListBeanList.size()==0){
                                     customerBusinessReceiptAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfolist.getParent());
                                 }
                                 showProcessBar(100);
                             }else{
                                 customerBusinessReceiptAdapter.add(mReceiptListBeanList);
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


             @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
        String date="",month="";
        month=calendar.getYear()+"-"+calendar.getMonth();
        currentDate=calendar.getYear()+"-"+calendar.getMonth()+"-"+calendar.getDay();
        tv_title.setText(currentDate);
        if(mPageType==Pub.Mine_Date_Visit){
            getVisitActivityList(currentDate);
            getScheduleList(0);
        }else if(mPageType==Pub.Mine_Date_Receipt){
            getReceiptActivityList(currentDate);
            getReceiptList(0);
        }

    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

     @Override
     public void createBackReq(boolean IsBg) {
         super.createBackReq(IsBg);
         if(Rc.mBackfresh){
             Rc.mBackfresh=false;
             if(mPageType==Pub.Mine_Date_Visit){
                 getVisitActivityList(currentDate);
                 getScheduleList(0);
             }else if(mPageType==Pub.Mine_Date_Receipt){
                 getReceiptActivityList(currentDate);
                 getReceiptList(0);
             }

         }

     }
     @Override
     public void createReq(boolean IsBg) {
         getVisitActivityList(currentDate);
         if(mPageType==Pub.Mine_Date_Visit){
             getVisitActivityList(currentDate);
             getScheduleList(0);
         }else if(mPageType==Pub.Mine_Date_Receipt){
             getReceiptActivityList(currentDate);
             getReceiptList(0);
         }
     }

     @Override
     public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
         if(nAction== DialogID.DialogDoNothing)
         {
             if(nKeyCode == KeyEvent.KEYCODE_BACK ){
                 return;
             }
         }else if(nAction== DialogID.DialogDeleteTrue){
             if(nKeyCode == KeyEvent.KEYCODE_ENTER){
                 if(adapter!=null && curpostion!=-1){
                     deleteVisit(adapter.getData().get(curpostion).getId());
                 }
             }
         }


     }
     public void deleteVisit(long id){
         JiuyiHttp.DELETE("visit-activity/delete/"+id)
                 .addHeader("Authorization",Rc.getIns().id_tokenparam)
                 .request(new ACallback<Object>() {
                     @Override
                     public void onSuccess(Object data) {
                         if(data!=null){
                             if(adapter!=null && curpostion!=-1){
                                 adapter.remove(curpostion);
                                 adapter.notifyDataSetChanged();
                                 curpostion=-1;
                                 if(adapter.getData()==null||adapter.getData().size()==0){
                                     adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfolist.getParent());
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
     private void showPopMenu(View v){
         View contentView = LayoutInflater.from(mCallBack.getActivity()).inflate(R.layout.jiuyi_pop_delete_menu,null);
         //处理popWindow 显示内容
         handleLogic(contentView);
         //创建并显示popWindow
         mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(mCallBack.getActivity())
                 .setView(contentView)
                 .create()
                 .showAsDropDown(v,0,0);
     }

     /**
      * 处理弹出显示内容、点击事件等逻辑
      * @param contentView
      */
     private void handleLogic(View contentView){
         View.OnClickListener listener = new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(mCustomPopWindow!=null){
                     mCustomPopWindow.dissmiss();
                 }
                 JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
                 switch (v.getId()){
                     case R.id.menu1:
                         startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                         break;
                 }

             }
         };
         contentView.findViewById(R.id.menu1).setOnClickListener(listener);
     }

     }


