package customer.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beiing.leafchart.LeafLineChart;
import com.beiing.leafchart.bean.Axis;
import com.beiing.leafchart.bean.AxisValue;
import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.bean.PointValue;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


import customer.adapter.ArrivemoneyAdapter;
import customer.adapter.BalanceInfoAdapter;
import customer.adapter.DebtListAdapter;
import customer.adapter.FinancialItemAdapter;
import customer.entity.CreditBalanceAmountChartBean;
import customer.entity.CustomerDebtBean;
import customer.entity.MemberAuthorityBean;
import customer.entity.MonthlyCreditBean;
import customer.entity.RiskWarnBean;
import customer.entity.ThirdModuleBean;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerCreditFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户信用及欠款信息
 *****************************************************************
 */
public class JiuyiCustomerCreditFragment extends JiuyiFragmentBase {
    private long Customerid=-1;
    Calendar calendars;
    String year="",msLevel="";
    private ArrivemoneyAdapter arrivemoneyAdapter;
    private TextView tvCharttitle,tv_refresh;
    private TextView tvDebtarrivelabel;
    private TextView tvSearchdebtdetail;
    private RecyclerView rvArrivemoney;
    LeafLineChart leafLineChart;
    float maxValue;
    float step=0;
    float maxScaling=0;
    List<CreditBalanceAmountChartBean.BeanListBean> listBeanList;
    private LinearLayout ll_content;
    private TextView tv_no_authoritytext,tv_credit;
    private ImageView iv_no_authority;
    private RecyclerView debtRecyclerView;
    private DebtListAdapter adapter;
    private RecyclerView rvList;
    private BalanceInfoAdapter balanceInfoAdapter;
    private FinancialItemAdapter riskWarnAdapter;
//    private ImageView iv_empty;
    private TextView tv_emptytext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customercreditfragment_layout"), null);
            setOnRefreshListener();
            onInit();
        } else {
            checkRootViewParent();
        }
        return mRootView;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        msLevel=mBundle.getString(JiuyiBundleKey.PARAM_LEVELNAME);
        calendars = Calendar.getInstance();
        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        year = String.valueOf(calendars.get(Calendar.YEAR)-1);
        initView();
        if(!MemberAuthorityBean.financialRisk){
            tv_no_authoritytext.setVisibility(View.VISIBLE);
            iv_no_authority.setVisibility(View.VISIBLE);
            ll_content.setVisibility(View.GONE);
        }else {
            showProcessBar(0);
            getCreditInfo();
            getRiskWarnList();
            getDebtList();
        }


    }
    private void initView(){
        ll_content=(LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_content"));
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        tvCharttitle = (TextView) mRootView.findViewById(R.id.tv_charttitle);
        tv_refresh = (TextView) mRootView.findViewById(R.id.tv_refresh);
        tv_credit = (TextView) mRootView.findViewById(R.id.tv_credit);
//        iv_empty= (ImageView) mRootView.findViewById(R.id.iv_empty);
        tv_emptytext= (TextView) mRootView.findViewById(R.id.tv_emptytext);
        String screditlevel="客户信用评级:"+msLevel;
        SpannableString ss = new SpannableString(screditlevel);
        ss.setSpan(new ForegroundColorSpan(Color.RED), screditlevel.indexOf(":")+1, screditlevel.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_credit.setText(ss);
        leafLineChart = (LeafLineChart) mRootView.findViewById(R.id.leaf_chart);
        tvDebtarrivelabel = (TextView) mRootView.findViewById(R.id.tv_debtarrivelabel);
        tvSearchdebtdetail = (TextView) mRootView.findViewById(R.id.tv_searchdebtdetail);
        rvArrivemoney = (RecyclerView) mRootView.findViewById(R.id.rv_arrivemoney);
        rvArrivemoney.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        rvArrivemoney.setHasFixedSize(true);
        tvSearchdebtdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urladd=Res.getString(null, "jiuyicreditdetail");
                String url="";
                if(!Func.IsStringEmpty(urladd)){
                    url= Constant.BaseH5Url+urladd+"?kunnr="+Customerid+"&token="+ Rc.id_tokenparam;
                }
                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"欠款明细表");
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);

            }
        });
        debtRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_debtlist);
        debtRecyclerView.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns(), 1, false));
        debtRecyclerView.setHasFixedSize(true);
        debtRecyclerView.setItemAnimator(new DefaultItemAnimator());
        debtRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

        //解决数据加载不完的问题
        debtRecyclerView.setNestedScrollingEnabled(false);
        debtRecyclerView.setHasFixedSize(true);
    //解决数据加载完成后, 没有停留在顶部的问题
        debtRecyclerView.setFocusable(false);
        rvList= (RecyclerView) mRootView.findViewById(R.id.rv_list);
        GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(), 2);
        rvList.setLayoutManager(mgr);
        rvList.setHasFixedSize(true);
        rvList.setItemAnimator(new DefaultItemAnimator());
        //解决数据加载不完的问题
        rvList.setNestedScrollingEnabled(false);
        rvList.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        rvList.setFocusable(false);
    }

    //走势图
    private void getCreditInfo() {
        JiuyiHttp.GET("finance/balance-amount-chart-mobile/"+Customerid)
                .tag("request_get_member")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<CreditBalanceAmountChartBean>() {
                    @Override
                    public void onSuccess(CreditBalanceAmountChartBean data) {
                        JiuyiLog.e("getsig","request onSuccess!");
                        if(data!=null){
                            tvCharttitle.setText(data.getTitle());
                            listBeanList =data.getBeanList();
                            initLineChart();
                        }

                        showProcessBar(100);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }


    private void getDebtList() {
        JiuyiHttp.GET("finance/bisd-group-by-bukrs/"+Customerid)
                .tag("request_get_member")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<CreditBalanceAmountChartBean.BeanListBean>>() {
                    @Override
                    public void onSuccess(List<CreditBalanceAmountChartBean.BeanListBean> data) {
                        JiuyiLog.e("getsig","request onSuccess!");
                        List<CreditBalanceAmountChartBean.BeanListBean>  contentBeanList=data;
                        if(contentBeanList!=null && contentBeanList.size()>0){
                            balanceInfoAdapter = new BalanceInfoAdapter(R.layout.customer_balanceinfo_item, contentBeanList);
                            debtRecyclerView.setAdapter(balanceInfoAdapter);
//                            iv_empty.setVisibility(View.GONE);
                            tv_emptytext.setVisibility(View.GONE);
                        }else{
//                            iv_empty.setVisibility(View.VISIBLE);
                            tv_emptytext.setVisibility(View.VISIBLE);
                        }


                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerCreditFragment newInstance(int nPageType) {
        JiuyiCustomerCreditFragment f = new JiuyiCustomerCreditFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerCreditFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerCreditFragment f = new JiuyiCustomerCreditFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    private void initLineChart() {
        Axis axisX = new Axis(getAxisValuesX());
        axisX.setAxisColor(Color.parseColor("#33B5E5")).setTextColor(Color.DKGRAY).setHasLines(true);
        Axis axisY = new Axis(getAxisValuesY());
        axisY.setAxisColor(Color.parseColor("#33B5E5")).setTextColor(Color.DKGRAY).setHasLines(true).setShowText(true);
        int len=(maxValue+"").length();
        if(len>=10){
            leafLineChart.setLeftPadding(Res.Dip2Pix(75));
        }else if(len>5){
            leafLineChart.setLeftPadding(Res.Dip2Pix(55));
        }else{
            leafLineChart.setLeftPadding(Res.Dip2Pix(30));
        }

        leafLineChart.setAxisX(axisX);
        leafLineChart.setAxisY(axisY);

        List<Line> lines = new ArrayList<>();
        lines.add(getFoldLine());
        leafLineChart.setChartData(lines);
        leafLineChart.showWithAnimation(1000);
    }

    private List<AxisValue> getAxisValuesX(){
        List<AxisValue> axisValues = new ArrayList<>();
        if(listBeanList!=null && listBeanList.size()>0){
            for (int i = 0; i <listBeanList.size(); i++) {
                AxisValue value = new AxisValue();
                if(i%2==0){
                    value.setLabel(listBeanList.get(i).getField() + "月");
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
            maxValue = Float.parseFloat(listBeanList.get(0).getFieldValue()+"");
            for (int i = 1; i < listBeanList.size(); i++) {
                if (Float.parseFloat(listBeanList.get(i).getFieldValue()+"") >= maxValue) {
                    maxValue = Float.parseFloat(listBeanList.get(i).getFieldValue()+"");
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
        if(!Func.addCommaChart(listBeanList.get(0).getFieldValue()+"").equals("0")){
            p.setLabel(Func.addCommaChart(listBeanList.get(0).getFieldValue()+""));
        }else{
            p.setLabel("");
        }
        p.setY((Float.valueOf(listBeanList.get(0).getFieldValue()+"")/maxScaling));

        pointValues.add(p);
        for (int i = 1; i <listBeanList.size(); i++) {
            PointValue pointValue = new PointValue();
            pointValue.setX(Float.valueOf(i) /(listBeanList.size()-1));
            pointValue.setLabel(Func.addCommaChart(listBeanList.get(i).getFieldValue()+""));
            pointValue.setY((Float.valueOf(listBeanList.get(i).getFieldValue()+"")/maxScaling));
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
    public void createReq(boolean IsBg) {
        getCreditInfo();
        getRiskWarnList();
        getDebtList();
    }

    private void getRiskWarnList() {
        JiuyiHttp.GET("finance/third-module-mobile/"+Customerid+"?fromClientType=android")
                .tag("request_get_member")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ThirdModuleBean>() {
                    @Override
                    public void onSuccess(final ThirdModuleBean data) {
                        if(data!=null){
                            riskWarnAdapter = new FinancialItemAdapter(JiuyiMainApplication.getIns(), data.getBeanList());
                            rvList.setAdapter(riskWarnAdapter);
                            if(data.getTitle()!=null){
                                tv_refresh.setText(data.getTitle());
                            }
                        }
                        showProcessBar(100);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

}
