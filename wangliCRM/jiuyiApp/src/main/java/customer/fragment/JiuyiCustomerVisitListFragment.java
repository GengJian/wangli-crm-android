package customer.fragment;

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

import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
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
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.tools.CustomPopWindow;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.Utils.ViewOperatorType;
import customer.adapter.CustomerBusinessReceiptAdapter;
import customer.adapter.VisitItemListAdapter;
import customer.entity.CustomerBusinessReceiptBean;
import customer.entity.VisitActivityBean;
import customer.entity.VisitActivityListBean;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.Utils.DynamicConstant;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerVisitListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 商务拜访
 *****************************************************************
 */
public class JiuyiCustomerVisitListFragment extends JiuyiFragmentBase implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener
         {

    private VisitItemListAdapter adapter;
    private CustomerBusinessReceiptAdapter customerBusinessReceiptAdapter;
    private List<VisitActivityListBean> mVisitActivityListBeanList;
    private List<CustomerBusinessReceiptBean.ContentBean> mReceiptListBeanList;
    RecyclerView rvInfolist;
    ImageButton mibadd;

    TextView mTextMonthDay,tv_title;
    TextView mTextYear;
    TextView mTextLunar;
    TextView mTextCurrentDay;
    CalendarView mCalendarView;
    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;
    private long Customerid=-1;
    private String Customername="";
    private String currentDate="";
    private CustomPopWindow mCustomPopWindow;
    private int curpostion=-1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customervisitlistfragment_layout"), null);
//        setOnRefreshListener();
        onInit();
        return mRootView;
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerVisitListFragment newInstance(int nPageType) {
        JiuyiCustomerVisitListFragment f = new JiuyiCustomerVisitListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerVisitListFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerVisitListFragment f = new JiuyiCustomerVisitListFragment();
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
        initView();
        mVisitActivityListBeanList = new ArrayList<>();
        rvInfolist = (RecyclerView) mRootView.findViewById(R.id.rv_infolist);
        rvInfolist.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns(), 1, false));
        rvInfolist.setHasFixedSize(true);
        rvInfolist.setItemAnimator(new DefaultItemAnimator());
        rvInfolist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));
        if(mPageType==Pub.Customer_VisitInfo){
            getVisitActivityList(currentDate);
            getVisitActivityByCalendarList(currentDate);
        }else if(mPageType==Pub.Customer_ReceiptInfo){
            getReceptionActivityList(currentDate);
            getReceptionActivityByCalendarList(currentDate);
        }

        ImageButton mibadd;
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            if((mPageType==Pub.Customer_ReceiptInfo)){
                mibadd.setVisibility(View.GONE);
            }
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle mBundle=new Bundle();
                    if(mPageType==Pub.Customer_VisitInfo){
                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
                        mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, Customername);
                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                        mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"visit-activity");
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建拜访活动");
                    }else if(mPageType==Pub.Customer_ReceiptInfo){
                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
                        mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, Customername);
                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                        mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"visit-activity");
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建接待活动");
                    }
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newDynamicForm,true);

                }
            });
        }
        showProcessBar(0);
    }
    protected void initView() {
        tv_title= (TextView) mRootView.findViewById(R.id.tv_title);
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
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        currentDate=mCalendarView.getCurYear()+"-"+mCalendarView.getCurMonth()+"-"+mCalendarView.getCurDay();
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
        JiuyiHttp.GET("visit-activity/calendar-list-mobile/"+Customerid+"/"+date)
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<VisitActivityBean>() {
                    @Override
                    public void onSuccess(VisitActivityBean data) {
                        showProcessBar(100);
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
     public  void  getVisitActivityByCalendarList(String date){
         JiuyiHttp.GET("visit-activity/activity-list-by-calendar/"+Customerid+"/"+date)
                 .addHeader("Authorization", Rc.getIns().id_tokenparam)
                 .request(new ACallback<List<VisitActivityListBean>>() {
                     @Override
                     public void onSuccess(List<VisitActivityListBean> data) {
                         showProcessBar(100);
                         if(data!=null){
                             mVisitActivityListBeanList =data;
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
//                                                    startDialog(DialogID.DialogDoNothing, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                     return false;
                                 }
                             });
                             if(mVisitActivityListBeanList==null||mVisitActivityListBeanList.size()==0){
                                 adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfolist.getParent());
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

     public void  getReceptionActivityList(String date){
         JiuyiHttp.GET("customer-base-reception/calendar-list-mobile/"+Customerid+"/"+date)
                 .addHeader("Authorization", Rc.getIns().id_tokenparam)
                 .request(new ACallback<VisitActivityBean>() {
                     @Override
                     public void onSuccess(VisitActivityBean data) {
                         showProcessBar(100);
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
                                     map.put(getSchemeCalendar(year, month, day, 0xFFdf1356, "接待").toString(),
                                             getSchemeCalendar(year, month, day, 0xFFdf1356, "接待"));
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

     public  void  getReceptionActivityByCalendarList(String date){
         JiuyiHttp.GET("customer-base-reception/reception-list-by-calendar/"+Customerid+"/"+date)
                 .addHeader("Authorization", Rc.getIns().id_tokenparam)
                 .request(new ACallback<List<CustomerBusinessReceiptBean.ContentBean>>() {
                     @Override
                     public void onSuccess(List<CustomerBusinessReceiptBean.ContentBean> data) {
                         showProcessBar(100);
                         if(data!=null){
                             mReceiptListBeanList =data;
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
        String date="";
        date=calendar.getYear()+"-"+calendar.getMonth()+"-"+calendar.getDay();
        tv_title.setText(date);
        if(mPageType==Pub.Customer_VisitInfo){
            getVisitActivityList(date);
            getVisitActivityByCalendarList(date);
        }else if(mPageType==Pub.Customer_ReceiptInfo){
            getReceptionActivityList(date);
            getReceptionActivityByCalendarList(date);
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
             if(mPageType==Pub.Customer_VisitInfo){
                 getVisitActivityList(currentDate);
                 getVisitActivityByCalendarList(currentDate);
             }else if(mPageType==Pub.Customer_ReceiptInfo){
                 getReceptionActivityList(currentDate);
                 getReceptionActivityByCalendarList(currentDate);
             }
         }

     }
     @Override
     public void createReq(boolean IsBg) {
         if(mPageType==Pub.Customer_VisitInfo){
             getVisitActivityList(currentDate);
             getVisitActivityByCalendarList(currentDate);
         }else if(mPageType==Pub.Customer_ReceiptInfo){
             getReceptionActivityList(currentDate);
             getReceptionActivityByCalendarList(currentDate);
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


