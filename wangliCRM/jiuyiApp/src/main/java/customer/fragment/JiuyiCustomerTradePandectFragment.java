package customer.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFragmentBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiMainApplication;
import com.wanglicrm.android.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import customer.adapter.TradeChartAdapter;
import customer.entity.TradePandectBean;
import customer.view.MultiItemDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerTradePandectFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户360交易跟踪信息总览
 *****************************************************************
 */
public class JiuyiCustomerTradePandectFragment extends JiuyiFragmentBase {
    LinearLayout ll_transaction_contract,ll_tradeorder,ll_tradedelivery,ll_tradeinvoice,ll_tradetelemoney,ll_tradelogistics,ll_bankstatement;

    private TextView tvTransactionContract;
    private TextView tvTransactionOrder;
    private TextView tvTransactionShip;
    private TextView tvTransactionInvoice;
    private TextView tvTransactionWireTransfer;
    private TextView tvTransactionLogistics;
    private TextView tvBankStatement;
    private RecyclerView rvChart;
    private long Customerid=-1;
    private TradeChartAdapter tradeChartAdapter;
    RefreshLayout refreshLayout;
    private TextView tv_emptytext;
    private ImageView iv_empty;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customertradepandectfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    public void onInit() {
//        lineChart = (LeafLineChart) mRootView.findViewById(R.id.leaf_chart);
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        tvTransactionContract = (TextView) mRootView.findViewById(R.id.tv_transaction_contract);
        tvTransactionOrder = (TextView) mRootView.findViewById(R.id.tv_transaction_order);
        tvTransactionShip = (TextView) mRootView.findViewById(R.id.tv_transaction_ship);
        tvTransactionInvoice = (TextView) mRootView.findViewById(R.id.tv_transaction_invoice);
        tvTransactionWireTransfer = (TextView) mRootView.findViewById(R.id.tv_transaction_wire_transfer);
        tvTransactionLogistics = (TextView) mRootView.findViewById(R.id.tv_transaction_logistics);
        tvBankStatement = (TextView) mRootView.findViewById(R.id.tv_bankstatement);
        rvChart = (RecyclerView) mRootView.findViewById(R.id.rv_chart);
        rvChart.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        tv_emptytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_emptytext"));
        iv_empty=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_empty"));

        //分割线
        MultiItemDivider itemDivider = new MultiItemDivider(getContext(), MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
        rvChart.addItemDecoration(itemDivider);

        ll_transaction_contract=(LinearLayout) mRootView.findViewById(R.id.ll_transaction_contract);
        if(ll_transaction_contract!=null){
            ll_transaction_contract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBundle==null){
                        mBundle = new Bundle();
                    }
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_transactioncontract);
                    mBundle.putInt(JiuyiBundleKey.PARAM_TRADEPAGETYPE,Pub.Customer_transactioncontract);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_transactioncontract,true);
                }
            });
        }
        ll_tradeorder=(LinearLayout) mRootView.findViewById(R.id.ll_tradeorder);
        if(ll_tradeorder!=null){
            ll_tradeorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBundle==null){
                        mBundle = new Bundle();
                    }
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradeorder);
                    mBundle.putInt(JiuyiBundleKey.PARAM_TRADEPAGETYPE, Pub.Customer_tradeorder);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradeorder,true);
                }
            });
        }
        ll_tradedelivery=(LinearLayout) mRootView.findViewById(R.id.ll_tradedelivery);
        if(ll_tradedelivery!=null){
            ll_tradedelivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBundle==null){
                        mBundle = new Bundle();
                    }
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradedelivery);
                    mBundle.putInt(JiuyiBundleKey.PARAM_TRADEPAGETYPE, Pub.Customer_tradedelivery);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradedelivery,true);
                }
            });
        }
        ll_tradeinvoice=(LinearLayout) mRootView.findViewById(R.id.ll_tradeinvoice);
        if(ll_tradeinvoice!=null){
            ll_tradeinvoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBundle==null){
                        mBundle = new Bundle();
                    }
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradeinvoice);
                    mBundle.putInt(JiuyiBundleKey.PARAM_TRADEPAGETYPE, Pub.Customer_tradeinvoice);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradeinvoice,true);
                }
            });
        }
        ll_tradetelemoney=(LinearLayout) mRootView.findViewById(R.id.ll_tradetelemoney);
        if(ll_tradetelemoney!=null){
            ll_tradetelemoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBundle==null){
                        mBundle = new Bundle();
                    }
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradetelemoney);
                    mBundle.putInt(JiuyiBundleKey.PARAM_TRADEPAGETYPE, Pub.Customer_tradetelemoney);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradetelemoney,true);
                }
            });
        }
        ll_tradelogistics=(LinearLayout) mRootView.findViewById(R.id.ll_tradelogistics);
        if(ll_tradelogistics!=null){
            ll_tradelogistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBundle==null){
                        mBundle = new Bundle();
                    }
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradelogistics);
                    mBundle.putInt(JiuyiBundleKey.PARAM_TRADEPAGETYPE, Pub.Customer_tradelogistics);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradelogistics,true);
                }
            });
        }
        ll_bankstatement=(LinearLayout) mRootView.findViewById(R.id.ll_bankstatement);
        if(ll_bankstatement!=null){
            ll_bankstatement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBundle==null){
                        mBundle = new Bundle();
                    }
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradebankstatement);
                    mBundle.putInt(JiuyiBundleKey.PARAM_TRADEPAGETYPE, Pub.Customer_tradebankstatement);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_tradebankstatement,true);
                }
            });
        }

        getTradeInfo();
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getTradeInfo();
                refreshlayout.finishRefresh(2000);
            }
        });

    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerTradePandectFragment newInstance(int nPageType) {
        JiuyiCustomerTradePandectFragment f = new JiuyiCustomerTradePandectFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerTradePandectFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerTradePandectFragment f = new JiuyiCustomerTradePandectFragment();
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
                                                    if(contentBean.getFieldValue().equals("商务合同")){
                                                        tvTransactionContract.setText(contentBean.getCount()+"");
                                                    }else if(contentBean.getFieldValue().equals("销售订单")){
                                                        tvTransactionOrder.setText(contentBean.getCount()+"");
                                                    }else if(contentBean.getFieldValue().equals("发货")){
                                                        tvTransactionShip.setText(contentBean.getCount()+"");
                                                    }else if(contentBean.getFieldValue().equals("发票")){
                                                        tvTransactionInvoice.setText(contentBean.getCount()+"");
                                                    }else if(contentBean.getFieldValue().equals("电汇/承兑")){
                                                        tvTransactionWireTransfer.setText(contentBean.getCount()+"");
                                                    }else if(contentBean.getFieldValue().equals("外贸物流")){
                                                        tvTransactionLogistics.setText(contentBean.getCount()+"");
                                                    }else if(contentBean.getFieldValue().equals("对账单")){
                                                        tvBankStatement.setText(contentBean.getCount()+"");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if(data.getAverage()!=null && data.getAverage().size()>0){
                                        rvChart.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
                                        iv_empty.setVisibility(View.GONE);
                                        tradeChartAdapter = new TradeChartAdapter(R.layout.customer_tradechart_item, data.getAverage());
                                        rvChart.setAdapter(tradeChartAdapter);
                                     }else{
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        iv_empty.setVisibility(View.VISIBLE);
                                        rvChart.setVisibility(View.GONE);
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

}
