package customer.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.magicindicator.MagicIndicator;
import com.control.widget.magicindicator.ViewPagerHelper;
import com.control.widget.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.control.widget.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.control.widget.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.control.widget.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.control.widget.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.control.widget.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;
import com.control.widget.viewpager.JiuyiNotSmoothViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.BarChart12View;
import com.control.widget.xclcharts.chart.BarData;
import com.jiuyi.tools.jiuyiCustomerViewPager;
import com.jiuyi.tools.jiuyiCustomerVisitViewPager;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.tencent.qcloud.sdk.Constant;
import com.zly.widget.CollapsedTextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import commonlyused.db.DbHelper;
import commonlyused.db.DictBeanDao;
import customer.Utils.FavoriteType;
import customer.activity.JiuyiCustomerMainActivity;
import customer.adapter.CompanyDynamicAdapter;
import customer.anim.Anim;
import customer.entity.FavoriteBean;
import customer.entity.FavoriteReturnBean;
import customer.entity.MemberAuthorityBean;
import customer.entity.MemberCenterBean;
import customer.listener.HistogramData;
import customer.view.Histogram;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerMainFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户360主界面信息
 *****************************************************************
 */
public class JiuyiCustomerMainFragment extends JiuyiFragmentBase {

    /** 标题栏 */
    private RelativeLayout mTitleBar;
    /** 标题栏 右侧图片*/
    protected ImageView m_vTitleRightImageView;
    private LinearLayout llBaseinfo;
    private TextView tvBaseinfo;
    private LinearLayout llPersonnelOrganization;
    private TextView tvPersonnelOrganization;
    private LinearLayout llFinancialRisk;
    private TextView tvFinancialRisk;
    private LinearLayout llPurchase;
    private TextView tvPurchase;
    private LinearLayout llProductionStatus;
    private TextView tvProductionStatus;
    private LinearLayout llSales;
    private TextView tvSales;
    private LinearLayout llResearch;
    private TextView tvResearch;
    private LinearLayout llVisit;
    private TextView tvRvisit;
    private LinearLayout llBusiness;
    private TextView tvBusiness;
    private LinearLayout llContract;
    private TextView tvContract;
    private LinearLayout llComplain;
    private TextView tvComplain;
    private LinearLayout llCost;
    private TextView tvCost,tv_creditlevel;

    private Button btnAttention;
    private SimpleDraweeView ivAvatar;
//    private BarChart12View barChart12View;
    private Histogram histogramChart;
    public MemberCenterBean memberCenterBean;
    private TextView  tvcompany,tvFollow,tv_clientstatus;
//    private ExpandableTextView tvlabel;
    private CollapsedTextView tvlabel;
    private TextView tv_linkman,tv_receiptaddress,tv_risk,tv_marketdynamics,tv_Tradetracking;
    private TextView tvrisklevelvalue,tv_emptytext,tv_datetitle;
    private RecyclerView rv_companydynamic;
    private CompanyDynamicAdapter companyDynamicAdapter;
    private TextView tv_totalPlanQuantity,tv_totalDeliveryQuantity,tv_totalAmountInvoiced,tv_paymentAmount,tv_infoper;
    private long Customerid=-1;
    Bundle mBundleCustomerMain ;
    private String msLevel;
    private FavoriteBean favoriteBean;
    private List<BarData> chartData2 = new LinkedList<BarData>();
    private long favorite=-1;
    private String msAvatar="";
    private NestedScrollView osv_main;
    private ImageView iv_labeledit;
    List<MemberCenterBean.LabelsBean> labelsBeanList;

    private LinearLayout llHelp;

    protected jiuyiCustomerVisitViewPager pViewPageBar;//下方viewpager
    private GroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表
    MagicIndicator magicIndicator;
    CommonNavigator commonNavigator;
    float[] ydata = new float[7];
    private int[] rectColor=new int[7];
    private String[] xdata = new String[]{"合同","发货"};
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customermainfragment_layout"), null);
            setOnRefreshListener();
            onInit();
        }else{
            checkRootViewParent();
        }

        return mRootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }
    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            getMemberCenterInfo();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;

        }
    }
    @Override
    public void onInit() {
        super.onInit();
        MemberAuthorityBean.getIns();
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
//        customerPostion=mBundle.getInt(JiuyiBundleKey.PARAM_CUSTOMERPOSITION);
        mBundleCustomerMain = new Bundle();
        mBundleCustomerMain.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
        mTitleBar = (RelativeLayout)mRootView.findViewById(Res.getViewID(null, "jiuyi_titlebar_layout"));
        osv_main = (NestedScrollView) mRootView.findViewById(Res.getViewID(null, "osv_main"));
        m_vTitleRightImageView = (ImageView) mRootView.findViewById(Res.getViewID(null, "jiuyi_titlebar_more"));
        if(m_vTitleRightImageView!=null){
            m_vTitleRightImageView.setVisibility(View.INVISIBLE);
        }
        iv_labeledit = (ImageView) mRootView.findViewById(Res.getViewID(null, "iv_labeledit"));
        iv_labeledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERICONURL,msAvatar);
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newLabel);
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, "ADD");

                if(labelsBeanList!=null && labelsBeanList.size()>0){
                    mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) labelsBeanList);
                }else{
                    mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) null);
                }
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"customer");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_newLabel,true);
            }
        });
        mTitleBar.setVisibility(View.GONE);
        ivAvatar=(SimpleDraweeView)mRootView.findViewById(Res.getViewID(null, "ivAvatar"));
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERICONURL,msAvatar);
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customerpicture);
                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"customer");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customerpicture,true);

            }
        });
        btnAttention = (Button) mRootView.findViewById(Res.getViewID(null, "btnAttention"));
        btnAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnAttention.isSelected()){
                    btnAttention.setText("收藏");
                    btnAttention.setSelected(false);
                    Drawable img_on, img_off;
                    Resources res = JiuyiMainApplication.getIns().getResources();
                    img_off = res.getDrawable(R.drawable.collect_360);
                    img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                    btnAttention.setCompoundDrawables(img_off, null, null, null); //设置左图标
                    if(favorite>0){
                        deleteFavorite(favorite);
                    }

                }else{
                    addFavorite();
                    Drawable img_on, img_off;
                    Resources res = JiuyiMainApplication.getIns().getResources();
                    img_off = res.getDrawable(R.drawable.orderassistant_collection_s);
                    img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                    btnAttention.setCompoundDrawables(img_off, null, null, null); //设置左图标
                    btnAttention.setText("取消");
                    btnAttention.setSelected(true);
                }
            }
        });
        tvcompany=(TextView) mRootView.findViewById(Res.getViewID(null, "tvcompany"));
        tvlabel = (CollapsedTextView)mRootView.findViewById(R.id.tvlabel);
        tvFollow=(TextView) mRootView.findViewById(Res.getViewID(null, "tvFollow"));
        tv_clientstatus=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_clientstatus"));
        tv_linkman=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_linkman"));
        tv_receiptaddress=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_receiptaddress"));
        tv_risk=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_risk"));
        tv_marketdynamics=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_marketdynamics"));
        tv_Tradetracking=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_Tradetracking"));
        tvrisklevelvalue=(TextView) mRootView.findViewById(Res.getViewID(null, "tvrisklevelvalue"));
        tv_emptytext=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_emptytext"));
        rv_companydynamic=(RecyclerView) mRootView.findViewById(Res.getViewID(null, "rv_companydynamic"));
        rv_companydynamic.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        rv_companydynamic.setHasFixedSize(true);
        tv_totalPlanQuantity=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_totalPlanQuantity"));
        tv_totalDeliveryQuantity=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_totalDeliveryQuantity"));
        tv_totalAmountInvoiced=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_totalAmountInvoiced"));
        tv_paymentAmount=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_paymentAmount"));
        tv_infoper=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_infoper"));
        llHelp=(LinearLayout) mRootView.findViewById(Res.getViewID(null, "ll_help"));
        tv_datetitle=(TextView) mRootView.findViewById(Res.getViewID(null, "tv_datetitle"));
        llHelp.setVisibility(View.INVISIBLE);
        llHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="";
                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, "http://192.168.1.200:9095/#/contract-details");
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"帮助");
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
            }
        });
        //链接跳转
        changeLink();
        histogramChart = (Histogram)mRootView.findViewById(R.id.histogramchart);
        pViewPageBar = (jiuyiCustomerVisitViewPager) mRootView.findViewById(Res.getViewID(null, "jiuyi_credit_viewpager"));
        //绑定数据
        onIinitViewPage();
        initMagicIndicator1();
    }




    public void getMemberCenterInfo() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                showProcessBar(0);
                JiuyiHttp.GET("member-center/index/"+Customerid+"?fromClientType=android")
                        .tag("request_get_center")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<MemberCenterBean>() {
                            @Override
                            public void onSuccess(MemberCenterBean data) {
                                JiuyiLog.e("getsig","request onSuccess!");
                                memberCenterBean=data;
                                if(memberCenterBean!=null){
                                    if(memberCenterBean.getAuthorityBean()!=null){
                                        MemberAuthorityBean.base=memberCenterBean.getAuthorityBean().getBase();
//                                        if(!MemberAuthorityBean.getIns().){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_basic_information);
//                                            mtvBaseinfo.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvBaseinfo.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.linkMan=memberCenterBean.getAuthorityBean().getLinkMan();
//                                        if(!MemberAuthorityBean.actrol){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_real_controller_data);
//                                            mtvrealcontrol.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvrealcontrol.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }

//                                        if(!MemberAuthorityBean.financialRisk){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.c_financial_risk);
//
//                                            ColorMatrix cm = new ColorMatrix();
//                                            cm.setSaturation(0); // 设置饱和度
//                                            ColorMatrixColorFilter grayColorFilter = new ColorMatrixColorFilter(cm);
//                                            drawableTop.setColorFilter(grayColorFilter); // 如果想恢复彩色显示，设置为null即可
//                                            tvFinancialRisk.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            tvFinancialRisk.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.procurementStatus=memberCenterBean.getAuthorityBean().getProcurementStatus();
//                                        if(!MemberAuthorityBean.address){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_address);
//                                            mtvreceiptaddress.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvreceiptaddress.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.productionStatus=memberCenterBean.getAuthorityBean().getProductionStatus();
//                                        if(!MemberAuthorityBean.debt){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_credit);
//                                            mtvcredit.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvcredit.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.salesStatus=memberCenterBean.getAuthorityBean().getSalesStatus();
//                                        if(!MemberAuthorityBean.riskWarning){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_risk_warning);
//                                            mtvrisk.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvrisk.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.developmentStatus=memberCenterBean.getAuthorityBean().getDevelopmentStatus();
//                                        if(!MemberAuthorityBean.demandPlan){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_demand_plan);
//                                            mtvneedplan.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvneedplan.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.businessVisit=memberCenterBean.getAuthorityBean().getBusinessVisit();
//                                        if(!MemberAuthorityBean.gatheringPlan){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_collection_plan);
//                                            mtvreceiplan.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvreceiplan.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.businessFollow=memberCenterBean.getAuthorityBean().getBusinessFollow();
//                                        if(!MemberAuthorityBean.productPerformance){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_production_status);
//                                            mtvproductionstatus.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvproductionstatus.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.contractTracking=memberCenterBean.getAuthorityBean().getContractTracking();
//                                        if(!MemberAuthorityBean.marketTrend){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_market_dynamics);
//                                            mtvmarketdynamics.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvmarketdynamics.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.serviceComplaint=memberCenterBean.getAuthorityBean().getServiceComplaint();
//                                        if(!MemberAuthorityBean.transactionTracking){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_transaction_tracking);
//                                            mtvTradetracking.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvTradetracking.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.costAnalysis=memberCenterBean.getAuthorityBean().getCostAnalysis();//
//                                        if(!MemberAuthorityBean.systemMessage){
//                                            Drawable drawableTop = null;
//                                            drawableTop = JiuyiMainApplication.getIns().getResources().getDrawable(R.drawable.no_system_message);
//                                            mtvsysteminfo.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
//                                            mtvsysteminfo.setCompoundDrawablePadding(Res.Dip2Pix(10));
//                                        }
                                        MemberAuthorityBean.system=memberCenterBean.getAuthorityBean().getSystem();//
                                        MemberAuthorityBean.topFlag=memberCenterBean.getAuthorityBean().getTopFlag();//
                                        int viewpagePostion=-1;
                                        long viewpageCustomerId=-1;
                                        if(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity() instanceof JiuyiCustomerMainActivity ){
                                            viewpagePostion=((JiuyiNotSmoothViewPager)((JiuyiCustomerMainActivity)Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).mViewPager).getCurrentItem();
                                            if(viewpagePostion>=0){
                                                viewpageCustomerId=((JiuyiCustomerMainActivity)Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).mList.get(viewpagePostion);
                                            }
                                            if(!memberCenterBean.getAuthorityBean().getTopFlag() && viewpageCustomerId==Customerid){
                                                Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
                                                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().onBackPressed();
                                            }
                                        }

                                    }
                                    if(memberCenterBean.getIntelligenceList()!=null &&memberCenterBean.getIntelligenceList().size()>0){
                                        companyDynamicAdapter = new CompanyDynamicAdapter(R.layout.customer_companydynamic_item, memberCenterBean.getIntelligenceList());
                                        companyDynamicAdapter.setOnCLickItemListener(new CompanyDynamicAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
//                                                if(colname.equals("tv_dyname")||colname.equals("tv_dyvalue")){
////                                                    if(!MemberAuthorityBean.riskWarning){
////                                                        Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
////                                                        return;
////                                                    }
//                                                    mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Sales);
//                                                    Rc.mCurrentPageType=Pub.Customer_Sales;
//                                                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_Sales,true);
//                                                }

                                            }

                                        });
                                        rv_companydynamic.setAdapter(companyDynamicAdapter);
                                    }else{
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        rv_companydynamic.setVisibility(View.GONE);
                                    }

                                    tvcompany.setText(memberCenterBean.getOrgName());
                                    labelsBeanList=memberCenterBean.getLabels();
                                    String label="";
                                    if(labelsBeanList!=null && labelsBeanList.size()>0){
                                        for(MemberCenterBean.LabelsBean bean :labelsBeanList){
                                            label+=bean.getDesp()+" ";
                                        }
                                        tvlabel.setText("标签:"+label);
                                    }else{
                                        tvlabel.setCollapsedLines(0);
                                        tvlabel.setText("标签:暂无");
                                    }
                                    tvFollow.setText(memberCenterBean.getCrmNumber()+"");
                                    if(!Func.IsStringEmpty(memberCenterBean.getStatusValue())){
                                        String status="";
                                        status=memberCenterBean.getStatusValue();
                                        SpannableString ss = new SpannableString(status);
                                        if(status.equals("潜在")){
                                            ss.setSpan(new ForegroundColorSpan(Color.parseColor("#336699")), 0, status.length(),
                                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                        }else if(status.equals("正式")){
                                            ss.setSpan(new ForegroundColorSpan(Color.parseColor("#edbb57")), 0, status.length(),
                                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                        }else if(status.equals("冻结")){
                                            ss.setSpan(new ForegroundColorSpan(Color.parseColor("#ec675d")), 0, status.length(),
                                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                        }
                                        tv_clientstatus.setText(ss);
                                    }

                                    String slinkman="人事组织("+memberCenterBean.getLinkManCount()+")";
                                    tvPersonnelOrganization.setText(getRedCountTitle(slinkman));
                                    String srisk="门店信息("+memberCenterBean.getStormeManageCount()+")";
                                    tvFinancialRisk.setText(getRedCountTitle(srisk));
                                    String spurchase="经销商计划("+memberCenterBean.getDealerPlanCount()+")";
                                    tvPurchase.setText(getRedCountTitle(spurchase));

//                                    String sprduct="生产及品质("+memberCenterBean.getProductStatusCount()+")";
//                                    tvProductionStatus.setText(getRedCountTitle(sprduct));
//                                    String ssale="销售状况("+memberCenterBean.getSalesStatusCount()+")";
//                                    tvSales.setText(getRedCountTitle(ssale));
//                                    String sresearch="研发状况("+memberCenterBean.getResearchStatusCount()+")";
//                                    tvResearch.setText(getRedCountTitle(sresearch));
//                                    String svisit="商务拜访("+memberCenterBean.getBusinessVisitCount()+")";
//                                    tvRvisit.setText(getRedCountTitle(svisit));
//                                    String sbusiness="商机跟进("+memberCenterBean.getBusinessChanceCount()+")";
//                                    tvBusiness.setText(getRedCountTitle(sbusiness));
//                                    String scontract="合同跟踪("+memberCenterBean.getContractCount()+")";
//                                    tvContract.setText(getRedCountTitle(scontract));
//                                    String sservice="服务投诉("+memberCenterBean.getServiceComplaintCount()+")";
//                                    tvComplain.setText(getRedCountTitle(sservice));
//                                    String scost="费用分析("+memberCenterBean.getCostAnalysisCount()+")";
//                                    tvCost.setText(getRedCountTitle(scost));

//                                    String screditlevel="信用等级:"+memberCenterBean.getCreditLevelValue();
//                                    SpannableString ss = new SpannableString(screditlevel);
//                                    ss.setSpan(new ForegroundColorSpan(Color.RED), screditlevel.indexOf(":")+1, screditlevel.length(),
//                                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                    tv_creditlevel.setText(ss);
//                                    tv_totalPlanQuantity.setText(memberCenterBean.getContractSumMonth()+"");
//                                    tv_totalDeliveryQuantity.setText(memberCenterBean.getOrderSumMounth()+"");
//                                    tv_totalAmountInvoiced.setText(Func.addComma(memberCenterBean.getBillingSumMonth()+"")+"元");
//                                    tv_paymentAmount.setText(Func.addComma(memberCenterBean.getReceivedSumMonth()+"")+"元");
//                                    mBundleCustomerMain.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, memberCenterBean.getOrgName());
//                                    mBundleCustomerMain.putString(JiuyiBundleKey.PARAM_LEVELNAME, memberCenterBean.getCreditLevelValue());
//                                    if(memberBean.getSapNumber()!=null){
//                                        Rc.sapNumber=memberBean.getSapNumber();
//                                        mBundleCustomerMain.putString(JiuyiBundleKey.PARAM_SAPNUMBER, memberBean.getSapNumber());
//                                    }


                                    if(!Func.IsStringEmpty(memberCenterBean.getAvatarUrl())){
                                        msAvatar=memberCenterBean.getAvatarUrl().toString();
                                        LoaderManager.getLoader().loadNet(ivAvatar, Constant.BaseUrl+"file/"+memberCenterBean.getAvatarUrl().toString(), new ILoader.Options(R.drawable.client_directsales, R.drawable.client_directsales));
                                    }

                                    favorite=memberCenterBean.getFavorite();
                                    if(favorite>0){
                                        btnAttention.setSelected(true);
                                        btnAttention.setText("取消");
                                        Drawable img_on, img_off;
                                        Resources res = JiuyiMainApplication.getIns().getResources();
                                        img_off = res.getDrawable(R.drawable.orderassistant_collection_s);
                                        img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                                        btnAttention.setCompoundDrawables(img_off, null, null, null); //设置左图标
                                    }else{
                                        btnAttention.setSelected(false);
                                        btnAttention.setText("收藏");
                                    }


//                                    if(memberCenterBean.getContractSumMonth()!=null && memberCenterBean.getOrderSumMounth()!=null){
//                                        for (int i=0;i<7;i++){
//                                            ydata[i] = 0;
//                                            rectColor[i]=R.color.jiuyi_cost_color;
//                                        }
//                                        ydata[0]=Float.parseFloat(memberCenterBean.getContractSumMonth()+"");
//                                        ydata[1]=Float.parseFloat(memberCenterBean.getOrderSumMounth()+"");
////                                    ydata[0]=Float.parseFloat(5+"");
////                                    ydata[1]=Float.parseFloat(20+"");
//                                        rectColor[0]=Res.getColor(null, "jiuyi_contract_color");
//                                        rectColor[1]=Res.getColor(null, "jiuyi_delivery_color");
//                                        if(!Func.IsStringEmpty(memberCenterBean.getTitle())){
//                                            tv_datetitle.setText(memberCenterBean.getTitle());
//                                        }
//                                        histogramChart = (Histogram)mRootView.findViewById(R.id.histogramchart);
//                                        HistogramData histogramData = HistogramData.builder()
//                                                .setXdata(xdata)
//                                                .setYdata(ydata)
//                                                .setXpCount(2)
//                                                .setYpCount(2)
//                                                .setRectColor(rectColor)
//                                                .setCoordinatesColor(R.color.jiuyi_cost_color)
//                                                .setAnimType(Anim.ANIM_ALPHA)
//                                                .build();
//                                        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                histogramChart.setChartData(histogramData);
//                                                histogramChart.update(histogramData);
//                                            }
//                                        });
//                                    }
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
        };
        thread.start();

    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerMainFragment newInstance(long customerid) {
        JiuyiCustomerMainFragment f = new JiuyiCustomerMainFragment();
        Bundle args = new Bundle();
        args.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, customerid);
        f.setArguments(args);
        return f;
    }

    public void changeLink(){

        llBaseinfo = (LinearLayout) mRootView.findViewById(R.id.ll_baseinfo);
        llBaseinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!MemberAuthorityBean.base){
//                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_baseinfo);
                Rc.mCurrentPageType=Pub.Customer_baseinfo;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_baseinfo,true);
            }
        });
        tvBaseinfo = (TextView) mRootView.findViewById(R.id.tv_baseinfo);
        llPersonnelOrganization = (LinearLayout) mRootView.findViewById(R.id.ll_personnel_organization);
        llPersonnelOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!MemberAuthorityBean.base){
//                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_PersonnelOrganization);
                Rc.mCurrentPageType=Pub.Customer_PersonnelOrganization;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_PersonnelOrganization,true);
            }
        });
        tvPersonnelOrganization = (TextView) mRootView.findViewById(R.id.tv_personnel_organization);
        llFinancialRisk = (LinearLayout) mRootView.findViewById(R.id.ll_financial_risk);
        llFinancialRisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MemberAuthorityBean.financialRisk){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_FinancialRisk);
                Rc.mCurrentPageType=Pub.Customer_FinancialRisk;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_FinancialRisk,true);
            }
        });
        tvFinancialRisk = (TextView) mRootView.findViewById(R.id.tv_financial_risk);
        llPurchase = (LinearLayout) mRootView.findViewById(R.id.ll_purchase);
        llPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!MemberAuthorityBean.base){
//                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Purchase);
                Rc.mCurrentPageType=Pub.Customer_Purchase;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_Purchase,true);
            }
        });
        tvPurchase = (TextView) mRootView.findViewById(R.id.tv_purchase);
        llProductionStatus = (LinearLayout) mRootView.findViewById(R.id.ll_production_status);
        llProductionStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!MemberAuthorityBean.base){
//                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_ProductionStatus);
//                Rc.mCurrentPageType=Pub.Customer_ProductionStatus;
//                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_ProductionStatus,true);
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_SystemInfo);
                Rc.mCurrentPageType=Pub.Customer_SystemInfo;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_SystemInfo,true);
            }
        });
        tvProductionStatus = (TextView) mRootView.findViewById(R.id.tv_production_status);
        llSales = (LinearLayout) mRootView.findViewById(R.id.ll_sales);
        llSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!MemberAuthorityBean.base){
//                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Sales);
                Rc.mCurrentPageType=Pub.Customer_Sales;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_Sales,true);
            }
        });
        tvSales = (TextView) mRootView.findViewById(R.id.tv_sales);
        llResearch = (LinearLayout) mRootView.findViewById(R.id.ll_research);
        llResearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!MemberAuthorityBean.base){
//                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Research);
                Rc.mCurrentPageType=Pub.Customer_Research;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_Research,true);
            }
        });
        tvResearch = (TextView) mRootView.findViewById(R.id.tv_research);
        llVisit = (LinearLayout) mRootView.findViewById(R.id.ll_visit);
        llVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!MemberAuthorityBean.base){
//                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Visit);
                Rc.mCurrentPageType=Pub.Customer_Visit;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_Visit,true);
            }
        });
        tvRvisit = (TextView) mRootView.findViewById(R.id.tv_rvisit);
        llBusiness = (LinearLayout) mRootView.findViewById(R.id.ll_business);
        llBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MemberAuthorityBean.businessFollow){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Business);
                Rc.mCurrentPageType=Pub.Customer_Business;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_Business,true);
            }
        });
        tvBusiness = (TextView) mRootView.findViewById(R.id.tv_business);
        llContract = (LinearLayout) mRootView.findViewById(R.id.ll_contract);
        llContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MemberAuthorityBean.contractTracking){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Contract);
                Rc.mCurrentPageType=Pub.Customer_Contract;
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_Contract,true);
            }
        });
        tvContract = (TextView) mRootView.findViewById(R.id.tv_contract);
        llComplain = (LinearLayout) mRootView.findViewById(R.id.ll_complain);
        llComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "该功能将于19年4月1日上线!", Toast.LENGTH_SHORT).show();
                return;
//                if(!MemberAuthorityBean.base){
//                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Service);
//                Rc.mCurrentPageType=Pub.Customer_Service;
//                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_Service,true);
            }
        });
        tvComplain = (TextView) mRootView.findViewById(R.id.tv_complain);
        llCost = (LinearLayout) mRootView.findViewById(R.id.ll_cost);
        llCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!MemberAuthorityBean.base){
//                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "暂无权限查看!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "该功能将于19年4月1日上线!", Toast.LENGTH_SHORT).show();
                return;
//                mBundleCustomerMain.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Cost);
//                Rc.mCurrentPageType=Pub.Customer_Cost;
//                Rc.getIns().getBaseCallTopCallBack().changePage(mBundleCustomerMain, Pub.Customer_Cost,true);
            }
        });
        tvCost = (TextView) mRootView.findViewById(R.id.tv_cost);
        tv_creditlevel = (TextView) mRootView.findViewById(R.id.tv_creditlevel);
    }
public void addFavorite(){
    favoriteBean=new FavoriteBean();
    favoriteBean.setFkId(Customerid);
    favoriteBean.setFkType(FavoriteType.MEMBER);
    favoriteBean.setFromClientType("android");
    JiuyiHttp.POST("favorite/create")
            .setJson(GsonUtil.gson().toJson(favoriteBean))
            .addHeader("Authorization",Rc.id_tokenparam)

            .request(new ACallback<FavoriteReturnBean>() {
                @Override
                public void onSuccess(FavoriteReturnBean result ) {
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "收藏成功!", Toast.LENGTH_SHORT).show();
                    if(result!=null){
                        favorite=result.getId();
                    }
                }

                @Override
                public void onFail(int errCode, String errMsg) {
                    startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                }
            });
}
    public void deleteFavorite(long id){
        JiuyiHttp.DELETE("favorite/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            favorite=-1;
                            Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "取消收藏成功!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }



    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh){
            Rc.mBackfresh=false;
            getMemberCenterInfo();
        }

    }
    @Override
    public void createReq(boolean IsBg) {
        getMemberCenterInfo();
//        getRadarInfo();
    }

    /**
     * 初始化viewpager，更新数据源
     */
    protected void onIinitViewPage() {
        //创建子fragment
        ArrayList<Fragment> fragments = new ArrayList<>();//子fragment
        //读取配置信息
        String strData = Res.getString(null, "jiuyicustomercredittabbar");
        String[][] strMenuData = Func.SplitStr2Array(strData);
        if (strMenuData == null || strMenuData.length < 1)
            return;
        //ArrayList<String> 标题清空
        mTitleList.clear();
        //ArrayList<Int> 功能号
        mPageTypeList.clear();
        for (int i = 0; i < strMenuData.length; i++) {
            String mTitle = strMenuData[i][0];
            int nPageType = Func.parseInt(strMenuData[i][1]);
            mTitleList.add(mTitle);
            mPageTypeList.add(nPageType);
        }
        for (int nPageType : mPageTypeList) {
            JiuyiFragmentBase itemFragment = null;
            switch (nPageType) {
                case Pub.Customer_ContributeDim:
                case Pub.Customer_demandanalyseDim:
                case Pub.Customer_strategyDim:
                case Pub.Customer_keyindexDim:
                case Pub.Customer_purchaseDim:
                case Pub.Customer_quanlityDim:
                case Pub.Customer_keydemandDim:
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    itemFragment = JiuyiCustomerDimensionFragment.newInstance(nPageType, mBundle);
                    break;


            }
            if (itemFragment != null) {
                itemFragment.setCallBack(mCallBack);
                fragments.add(itemFragment);
            }
        }
        //设置数据源
        mFragmentPagerAdapter = new GroupFragmentPagerAdapter(getChildFragmentManager(), fragments);
        pViewPageBar.setAdapter(mFragmentPagerAdapter);
        //绑定 ViewPager & Tablayout
        //根据action设置当前选中的item
        setCurrentItemByAction(mPageType);

    }

    public class GroupFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments = null;

        public GroupFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
            super(fragmentManager);
            this.fragments = fragments;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        public Fragment getCurrItem() {
            return fragments.get(commonNavigator.getSelectedTabPosition());
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public List<Fragment> getFragments() {
            return fragments;
        }

        public void clearFragments() {
            fragments.clear();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
    /**
     * 根据action设置当前选中的item
     * @param action
     */
    public void setCurrentItemByAction(int action){
        if (pViewPageBar != null && mPageTypeList != null && mPageTypeList.contains(action)){
            for (int i = 0; i < mPageTypeList.size(); i++){
                if(action == mPageTypeList.get(i)) {
                    pViewPageBar.setCurrentItem(i);
                    break;
                }
            }
        }
    }
    private void initMagicIndicator1() {
        magicIndicator=(MagicIndicator)mRootView.findViewById(Res.getViewID(null, "magic_indicator"));
        commonNavigator = new CommonNavigator(JiuyiMainApplication.getIns());
//        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdjustMode(false);
        commonNavigator.setSmoothScroll(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(JiuyiMainApplication.getIns());
                commonPagerTitleView.setContentView(R.layout.custom_dim_indicator_item);
                // 初始化
                final TextView titleText = (TextView) commonPagerTitleView.findViewById(R.id.title_text);
                titleText.setText(mTitleList.get(index));

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.setTextColor(Res.getColor(null, "jiuyi_blue"));
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setTextColor(Res.getColor(null, "jiuyi_title_color"));
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                    }
                });
                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pViewPageBar.setCurrentItem(index);
                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(JiuyiMainApplication.getIns());
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                //设置indicator的宽度
                indicator.setLineWidth(Res.Dip2Pix(30));
                indicator.setColors(Res.getColor(null, "jiuyi_blue"));
                return indicator;

            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, pViewPageBar);
    }

    public SpannableString getRedCountTitle(String title){
        SpannableString ss = new SpannableString(title);
        ss.setSpan(new ForegroundColorSpan(Color.RED), title.indexOf("(")+1, title.indexOf(")"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;
    }

}
