package customer.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiing.leafchart.LeafLineChart;
import com.beiing.leafchart.bean.Axis;
import com.beiing.leafchart.bean.AxisValue;
import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.bean.PointValue;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnCancelLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
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
import com.control.widget.JiuyiEditText;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.jiuyi.tools.CustomPopWindow;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.wanglicrm.android.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.CustomerSelMenuAdapter;
import customer.adapter.ReceiptPlanAdapter;
import customer.entity.GatheringPlanBean;
import customer.entity.GatheringplanChartBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerReceiptPlanFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 收款计划
 *****************************************************************
 */
public class JiuyiCustomerReceiptPlanFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private List<Map<String, Object>> menuData1;
    private CustomerSelMenuAdapter menuAdapter1;

    private LinearLayout mLinemonthsort;
    private TextView mTvcharsort, mTvaddplan;

    private String currentProduct;
    private int menuIndex = 0;
    private List<GatheringPlanBean.ContentBean> mViewTypeBeanList;
    private int[] imageids = { R.drawable.client_selected };
    private  ReceiptPlanAdapter adapter;
    private JiuyiEditText medsearchplan;
    private long Customerid=-1;
    private String Customername="",sapNumber="";
    RecyclerView receiptplanRecyclerView;
    ImageButton mibadd;
    private String msOwetotal="",msDuetotal="";
    private TextView tv_totaldebt,tv_arrive,tv_charttitle;
    private  int year=0,month=0,mYear;

    LeafLineChart lineChart;
    GatheringplanChartBean gatheringplanChartBean;
    List<GatheringplanChartBean.ListBean> listBeanList;
    float maxValue;
    float step=0;
    float maxScaling=0;
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pageSize = 20;
    private int totalPage=0;
    private int curpostion=-1;
    private LinearLayout ll_content;
    private TextView tv_no_authoritytext;
    private ImageView iv_no_authority;
    private CustomPopWindow mCustomPopWindow;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerreceiptplanfragment_layout"), null);
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
    public static JiuyiCustomerReceiptPlanFragment newInstance(int nPageType) {
        JiuyiCustomerReceiptPlanFragment f = new JiuyiCustomerReceiptPlanFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerReceiptPlanFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerReceiptPlanFragment f = new JiuyiCustomerReceiptPlanFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        msOwetotal=mBundle.getString(JiuyiBundleKey.PARAM_OWETOTAL);
        msDuetotal=mBundle.getString(JiuyiBundleKey.PARAM_DUETOTAL);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
//        sapNumber=mBundle.getString(JiuyiBundleKey.PARAM_SAPNUMBER);
        //设置时间
        Calendar m_Curdate = Calendar.getInstance();
        mYear = m_Curdate.get(Calendar.YEAR);// 获取当前年份
//        year= m_Curdate.get(Calendar.YEAR);// 获取当前年份
//        month= m_Curdate.get(Calendar.MONTH)+1;// 获取当前年份

        findView();
        tv_charttitle= (TextView) mRootView.findViewById(R.id.tv_charttitle);
        lineChart = (LeafLineChart) mRootView.findViewById(R.id.leaf_chart);
        tv_totaldebt= (TextView) mRootView.findViewById(R.id.tv_totaldebt);
        tv_totaldebt.setText(msOwetotal);
        tv_arrive= (TextView) mRootView.findViewById(R.id.tv_arrive);
        tv_arrive.setText(msDuetotal);
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Func.IsStringEmpty(Rc.sapNumber)){
                        startDialog(DialogID.DialogDoNothing, "", "非正式客户不允许创建收款计划！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        return;
                    }
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newReceiptplan);
                    mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, Customername);
                    mBundle.putString(JiuyiBundleKey.PARAM_OWETOTAL, msOwetotal);
                    mBundle.putString(JiuyiBundleKey.PARAM_DUETOTAL, msDuetotal);
                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                    Rc.mPageType=mPageType;
                   changePage(mBundle, Pub.Customer_newReceiptplan,true);
                }
            });
        }
//        if(!MemberAuthorityBean.getIns().gatheringPlan){
//            tv_no_authoritytext.setVisibility(View.VISIBLE);
//            iv_no_authority.setVisibility(View.VISIBLE);
//            ll_content.setVisibility(View.GONE);
//        }else {
//            getChartLineList();
//            getInfoList(0);
//            mViewTypeBeanList = new ArrayList<>();
//
//            receiptplanRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_receiptplan);
//            receiptplanRecyclerView.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity(), 1, false));
//            receiptplanRecyclerView.setHasFixedSize(true);
//            receiptplanRecyclerView.setItemAnimator(new DefaultItemAnimator());
//            receiptplanRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));
//            RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
//            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//                @Override
//                public void onRefresh(RefreshLayout refreshlayout) {
//                    pageIndex=1;
//                    getInfoList(0);
//                    refreshlayout.finishRefresh(2000);
//                    refreshlayout.setLoadmoreFinished(false);
//                }
//            });
//            refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//                @Override
//                public void onLoadmore(RefreshLayout refreshlayout) {
//                    if(pageIndex>=totalPage){
//                        refreshlayout.finishLoadmore(500);
//                        refreshlayout.setLoadmoreFinished(true);
//                    }else{
//                        getInfoList(pageIndex);
//                        refreshlayout.finishLoadmore(2000);
//                    }
//                    pageIndex++;
//                }
//            });
//            registerForContextMenu(receiptplanRecyclerView);
//        }

    }

    private void getChartLineList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("gathering_plan/find_line_chart/"+Customerid/*+"/"+mYear*/)
                        .tag("request_get_gathering_planr")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<GatheringplanChartBean>() {
                            @Override
                            public void onSuccess(GatheringplanChartBean contentBean) {
                                if(contentBean!=null){
                                    listBeanList=contentBean.getList();
                                    gatheringplanChartBean=contentBean;
                                    tv_charttitle.setText(contentBean.getContent());
                                    initLineChart();
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

    public  void  getGatheringPlanList(final int page){
        if(page==0){
            pageIndex=1;
        }
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
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
        JiuyiHttp.POST("gathering_plan/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<GatheringPlanBean>() {
                    @Override
                    public void onSuccess(GatheringPlanBean data) {
                        if(data!=null){
                            pageIndex=data.getTotalPages();
                            List<GatheringPlanBean.ContentBean> contentBeanList=data.getContent();
                            if(page==0){
                                if(adapter==null){
                                    adapter = new ReceiptPlanAdapter(R.layout.customer_item_receiptplan, contentBeanList);
//                                    adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
                                    receiptplanRecyclerView.setAdapter(adapter);
                                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            GatheringPlanBean.ContentBean contentBean=(GatheringPlanBean.ContentBean)adapter.getData().get(position);
                                            if(contentBean!=null){
                                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newReceiptplan);
                                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                if(contentBean.getStatus()!=null){
                                                    if(contentBean.getStatus().equals("APPROVALED")){
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                                    }else{
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                    }
                                                    Rc.mPageType=mPageType;
                                                    changePage(mBundle, Pub.Customer_newReceiptplan,true);
                                                }

                                            }

                                        }
                                    });
                                    adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                                        @Override
                                        public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                                            curpostion=position;
                                            showPopMenu(view);
//                                            startDialog(DialogID.DialogDoNothing, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
//                                                    startDialog(DialogID.DialogDoNothing, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                                            return false;
                                        }
                                    });
                                }else{
                                    adapter.refresh(contentBeanList);
                                }
                                if(contentBeanList==null||contentBeanList.size()==0){
                                    adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) receiptplanRecyclerView.getParent());
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

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_date:
                DatePickDialog dialog = new DatePickDialog(getCallBack().getActivity());
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
                        mTvcharsort.setText("月份");
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
                            month=Integer.parseInt(mTvcharsort.getText().toString().substring(4,6));
                        }
                        getGatheringPlanList(0);
                    }
                });
                dialog.show();
                break;


        }
    }
    protected void findView() {
        ll_content=(LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_content"));
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        mLinemonthsort = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_monthsort"));
        mTvcharsort = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_monthsort"));
        mLinemonthsort.setOnClickListener(this);

    }

    private void initLineChart() {
        Axis axisX = new Axis(getAxisValuesX());
        axisX.setAxisColor(Color.parseColor("#33B5E5")).setTextColor(Color.DKGRAY).setHasLines(true);
        Axis axisY = new Axis(getAxisValuesY());
        axisY.setAxisColor(Color.parseColor("#33B5E5")).setTextColor(Color.DKGRAY).setHasLines(true).setShowText(true);
        int len=(maxValue+"").length();
        if(len>=10){
            lineChart.setLeftPadding(Res.Dip2Pix(75));
        }else if(len>5){
            lineChart.setLeftPadding(Res.Dip2Pix(55));
        }else{
            lineChart.setLeftPadding(Res.Dip2Pix(30));
        }
        lineChart.setAxisX(axisX);
        lineChart.setAxisY(axisY);
        List<Line> lines = new ArrayList<>();
        lines.add(getFoldLine());
        lineChart.setChartData(lines);
        lineChart.showWithAnimation(3000);

    }

    private List<AxisValue> getAxisValuesX(){
        List<AxisValue> axisValues = new ArrayList<>();
        if(listBeanList!=null && listBeanList.size()>0){
            for (int i = 0; i <listBeanList.size(); i++) {
                AxisValue value = new AxisValue();
                if(i%2==0){
                    value.setLabel(listBeanList.get(i).getField() );
                }else{
                    value.setLabel(" ");
                }
                axisValues.add(value);
            }
        }
        return axisValues;
    }

    private List<AxisValue> getAxisValuesY(){
        List<AxisValue> axisValues = new ArrayList<>();
        if(listBeanList!=null && listBeanList.size()>0) {
            maxValue = Float.parseFloat(listBeanList.get(0).getFieldValue());
            for (int i = 1; i < listBeanList.size(); i++) {
                if (Float.parseFloat(listBeanList.get(i).getFieldValue()) >= maxValue) {
                    maxValue = Float.parseFloat(listBeanList.get(i).getFieldValue());
                }
            }
            if (maxValue > 0) {
                step = Func.initScaling(0,maxValue,11);
                maxScaling=step*10;
            }
        }
        for (int i = 0; i < 11; i++) {
            AxisValue value = new AxisValue();
            float yValue=i * step;
            value.setLabel(Func.formatFloatNumberChart(yValue));
            axisValues.add(value);
        }

        return axisValues;
    }

    private Line getFoldLine(){
        List<PointValue> pointValues = new ArrayList<>();

        PointValue p = new PointValue();
        p.setX( (1 - 1) / 11f);
        if(!Func.addCommaChart(listBeanList.get(0).getFieldValue()).equals("0")){
            p.setLabel(Func.addCommaChart(listBeanList.get(0).getFieldValue()));
        }else{
            p.setLabel("");
        }
        p.setY((Float.valueOf(listBeanList.get(0).getFieldValue())/maxScaling));

        pointValues.add(p);
        for (int i = 1; i <listBeanList.size(); i++) {
            PointValue pointValue = new PointValue();
            pointValue.setX(Float.valueOf(i) /(listBeanList.size()-1));
            pointValue.setLabel(Func.addCommaChart(listBeanList.get(i).getFieldValue()));
            pointValue.setY((Float.valueOf(listBeanList.get(i).getFieldValue())/maxScaling));
            pointValues.add(pointValue);
        }

        Line line = new Line(pointValues);
        line.setLineColor(Color.parseColor("#33B5E5"))
                .setLineWidth(3)
                .setPointColor(Color.YELLOW)
                .setCubic(true)
                .setPointRadius(3)
                .setFill(true)
                .setFillColor(Color.parseColor("#33B5E5"))
                .setHasLabels(true)
                .setLabelColor(Color.parseColor("#33B5E5"));
        return line;
    }

    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh && Rc.mPageType==mPageType){
            Rc.mBackfresh=false;
            Rc.mPageType=0;
            getGatheringPlanList(0);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
//        menu.add(0, 1, Menu.NONE, "删除");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        startDialog(DialogID.DialogDoNothing, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
        return true;
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
                    deleteDemandPlan(adapter.getData().get(curpostion).getId());
                }
            }
        }
    }

    public void deleteDemandPlan(long id){
        JiuyiHttp.DELETE("gathering_plan/delete/"+id)
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
                                    adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) receiptplanRecyclerView.getParent());
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
