package customer.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import customer.adapter.BusinessTransferAdapter;
import customer.entity.AbstractSummaryBean;
import customer.entity.CommonLabelValueBean;
import customer.entity.TradePandectBean;
import customer.view.JiuyiFunnelView;
import customer.view.SpaceItemDecoration;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerBusinessPandectFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 商机总览
 *****************************************************************
 */
public class JiuyiCustomerBusinessPandectFragment extends JiuyiFragmentBase {
    private LinearLayout llClew;
    private TextView tvClew;
    private LinearLayout llBusiness;
    private TextView tvBusiness;
    private LinearLayout llOffer;
    private TextView tvOffer;
    private LinearLayout llSample;
    private TextView tvSample;
    private JiuyiFunnelView funnelview;
    private CardView cvbusinesstran;
    private TextView tvtitle;
    private RecyclerView rvBusinesstrans;
    private long Customerid=-1;
    private BusinessTransferAdapter businessTransferAdapter;
    private List<CommonLabelValueBean> commonLabelValueBeanList;
    RefreshLayout refreshLayout;
    private List<AbstractSummaryBean> abstractSummaryBeanList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerbusinesspandectfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);

        llClew = (LinearLayout) mRootView.findViewById(R.id.ll_clew);
        llClew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
//                if(abstractSummaryBeanList!=null && abstractSummaryBeanList.size()>0){
//                    for(int i=0;i<abstractSummaryBeanList.size();i++){
//                        AbstractSummaryBean abstractSummaryBean=abstractSummaryBeanList.get(i);
//                        if(abstractSummaryBean.getField().equals("Clue")){
//                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERSMALLTYPE,abstractSummaryBean.getBusinessId());
//                        }
//                    }
//                }
//                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE,"BUSINESS_FOLLOW");
//                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_Business,false);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"线索大全");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dy_ClueWhole,true);

            }
        });
        tvClew = (TextView) mRootView.findViewById(R.id.tv_clew);
        llBusiness = (LinearLayout) mRootView.findViewById(R.id.ll_business);
        llBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
//                if(abstractSummaryBeanList!=null && abstractSummaryBeanList.size()>0){
//                    for(int i=0;i<abstractSummaryBeanList.size();i++){
//                        AbstractSummaryBean abstractSummaryBean=abstractSummaryBeanList.get(i);
//                         if(abstractSummaryBean.getField().equals("BusinessChance")){
//                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERSMALLTYPE,abstractSummaryBean.getBusinessId());
//                        }
//                    }
//                }
//                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE,"BUSINESS_FOLLOW");
//                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_Business,false);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"商机大全");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dy_BusinessWhole,true);

            }
        });
        tvBusiness = (TextView) mRootView.findViewById(R.id.tv_business);
        llOffer = (LinearLayout) mRootView.findViewById(R.id.ll_offer);
        llOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"报价大全");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dy_OfferWhole,true);
//                if(abstractSummaryBeanList!=null && abstractSummaryBeanList.size()>0){
//                    for(int i=0;i<abstractSummaryBeanList.size();i++){
//                        AbstractSummaryBean abstractSummaryBean=abstractSummaryBeanList.get(i);
//                       if(abstractSummaryBean.getField().equals("QuotedPrice")){
//                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERSMALLTYPE,abstractSummaryBean.getBusinessId());
//                       }
//                    }
//                }
//                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE,"BUSINESS_FOLLOW");
//                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_Business,false);
            }
        });
        tvOffer = (TextView) mRootView.findViewById(R.id.tv_offer);
        llSample = (LinearLayout) mRootView.findViewById(R.id.ll_sample);
        llSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"样品申请");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dy_SampleWhole,true);
//                if(abstractSummaryBeanList!=null && abstractSummaryBeanList.size()>0){
//                    for(int i=0;i<abstractSummaryBeanList.size();i++){
//                        AbstractSummaryBean abstractSummaryBean=abstractSummaryBeanList.get(i);
//                        if(abstractSummaryBean.getField().equals("Sample")){
//                            mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERSMALLTYPE,abstractSummaryBean.getBusinessId());
//                        }
//                    }
//                }
//                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE,"BUSINESS_FOLLOW");
//                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_Business,false);
            }
        });
        tvSample = (TextView) mRootView.findViewById(R.id.tv_sample);
        funnelview = (JiuyiFunnelView) mRootView.findViewById(R.id.funnelview);
        cvbusinesstran = (CardView) mRootView.findViewById(R.id.cvbusinesstran);
        tvtitle = (TextView) mRootView.findViewById(R.id.tvtitle);
        rvBusinesstrans = (RecyclerView) mRootView.findViewById(R.id.rv_businesstrans);
        rvBusinesstrans.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns(), 1, false));
        rvBusinesstrans.setHasFixedSize(true);
        rvBusinesstrans.setItemAnimator(new DefaultItemAnimator());

        rvBusinesstrans.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0,Res.getColor(JiuyiMainApplication.getIns(), "divider_dark")));
        rvBusinesstrans.addItemDecoration(new SpaceItemDecoration(-3));
        rvBusinesstrans.setNestedScrollingEnabled(false);
        rvBusinesstrans.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        rvBusinesstrans.setFocusable(false);
/*        rvBusinesstrans.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        //分割线
        MultiItemDivider itemDivider = new MultiItemDivider(getContext(), MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
        rvBusinesstrans.addItemDecoration(itemDivider);*/
        initData();
        businessTransferAdapter=new BusinessTransferAdapter(R.layout.customer_businesstransfer_item, commonLabelValueBeanList);
        rvBusinesstrans.setAdapter(businessTransferAdapter);

        getAbstractSummaryList();
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getAbstractSummaryList();
//                getTradeInfo();
                refreshlayout.finishRefresh(2000);
            }
        });

    }

    public void initData(){
        List<Integer> moneys = new ArrayList<>();
        int x1 = 20000;
        moneys.add(x1);
        int x2 = 100000;
        moneys.add(x2);
        int x3 = 80000;
        moneys.add(x3);
//        int x4 = 100000;
//        moneys.add(x4);
        int x5 = 90000;
        moneys.add(x5);
        funnelview.setData(moneys, x1 + x2 + x3 /*+ x4*/ + x5);
        funnelview.animateY();
        commonLabelValueBeanList=new ArrayList<>();
        CommonLabelValueBean commonLabelValueBean=new CommonLabelValueBean();
        commonLabelValueBean.setName("线索转商机");
        commonLabelValueBean.setValue("40%（20/50）");
        commonLabelValueBeanList.add(commonLabelValueBean);
        CommonLabelValueBean commonLabelValueBean2=new CommonLabelValueBean();
        commonLabelValueBean2.setName("商机转合同");
        commonLabelValueBean2.setValue("40%（20/50）");
        commonLabelValueBeanList.add(commonLabelValueBean2);

    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerBusinessPandectFragment newInstance(int nPageType) {
        JiuyiCustomerBusinessPandectFragment f = new JiuyiCustomerBusinessPandectFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerBusinessPandectFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerBusinessPandectFragment f = new JiuyiCustomerBusinessPandectFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }

    private void getTradeInfo() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("tracking/situation/"+Customerid+"?fromClientType=android")
                        .tag("request_get_member")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<TradePandectBean>() {
                            @Override
                            public void onSuccess(TradePandectBean data) {
                                JiuyiLog.e("getsig","request onSuccess!");
                                if(data!=null){
                                    if(data.getContent()!=null){
                                        List<TradePandectBean.ContentBeanX.ContentBean> content=data.getContent().getContent();
                                        if(content!=null && content.size()>0){
                                            for(int i=0;i<content.size();i++){
                                                TradePandectBean.ContentBeanX.ContentBean contentBean=content.get(i);
                                                if(contentBean!=null){

                                                }
                                            }
                                        }
                                    }
                                    if(data.getAverage()!=null && data.getAverage().size()>0){
//                                        rvChart.setVisibility(View.VISIBLE);
//                                        tv_emptytext.setVisibility(View.GONE);
//                                        iv_empty.setVisibility(View.GONE);
//                                        tradeChartAdapter = new TradeChartAdapter(R.layout.customer_tradechart_item, data.getAverage());
//                                        rvBusinesstrans.setAdapter(tradeChartAdapter);
                                     }else{
//                                        tv_emptytext.setVisibility(View.VISIBLE);
//                                        iv_empty.setVisibility(View.VISIBLE);
//                                        rvChart.setVisibility(View.GONE);
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
        };
        thread.start();

    }
    private void getAbstractSummaryList() {
        JiuyiHttp.GET("clue/abstract-summary/"+Customerid+"?fromClientType=android")
                .tag("request_get_market-trend")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<AbstractSummaryBean>>() {
                    @Override
                    public void onSuccess(List<AbstractSummaryBean> data) {
                        if(data!=null && data.size()>0){
                            abstractSummaryBeanList=data;
                            for(int i=0;i<data.size();i++){
                                AbstractSummaryBean abstractSummaryBean=data.get(i);
                                if(abstractSummaryBean.getField().equals("Clue")){
                                    if(tvClew!=null){
                                        tvClew.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("BusinessChance")){
                                    if(tvBusiness!=null){
                                        tvBusiness.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("QuotedPrice")){
                                    if(tvOffer!=null){
                                        tvOffer.setText(abstractSummaryBean.getCount()+"");
                                    }
                                }else  if(abstractSummaryBean.getField().equals("Sample")){
                                    if(tvSample!=null){
                                        tvSample.setText(abstractSummaryBean.getCount()+"");
                                    }
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

}
