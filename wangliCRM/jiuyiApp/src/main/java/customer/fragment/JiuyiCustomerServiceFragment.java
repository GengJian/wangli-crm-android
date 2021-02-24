package customer.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.ConsultationAdapter;
import customer.entity.TradedeliveryBean;
import customer.view.SelectDeleteReplyPopupWindow;
import customer.view.SelectReplyPopupWindow;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.entity.DyActivityBean;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerServiceFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 服务投诉
 *****************************************************************
 */
public class JiuyiCustomerServiceFragment extends JiuyiFragmentBase {

    private long Customerid=-1;
    private String Customername="";
    private String mRisktype="";
    RecyclerView rvInfoList;
    private List<DyActivityBean.ContentBean> mTradedeliveryBeanList;
    private ConsultationAdapter mTradedeliveryAdapter;
    QueryConditionBean queryConditionBean;
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pagesize = 20;
    private int totalPage=0;
    private ImageView ivNew;
    private LinearLayout llCustomerConsultation;
    private TextView tvCustomerConsultation;
    private LinearLayout llCustomerComplaints;
    private TextView tvCustomerComplaints;
    private LinearLayout llReturnedGoods;
    private TextView tvReturnedGoods;
    private LinearLayout llCommonProblem;
    private TextView tvCommonProblem;
    private TextView tvTitle;
    private String currentBillType;
    private int newPageType=0;
    protected SelectReplyPopupWindow menuWindow;
    protected SelectDeleteReplyPopupWindow selectDeleteReplyPopupWindow;
    private TextView tv_no_authoritytext;
    private ImageView iv_no_authority;
    private AppBarLayout app_bar;
    private NestedScrollView nsc_content;


    public String getmRisktype() {
        return mRisktype;
    }

    public void setmRisktype(String mRisktype) {
        this.mRisktype = mRisktype;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerservicefragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        mRisktype=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE);
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        app_bar=(AppBarLayout)mRootView.findViewById(Res.getViewID(getContext(), "app_bar"));
        nsc_content=(NestedScrollView)mRootView.findViewById(Res.getViewID(getContext(), "nsc_content"));
        llCustomerConsultation = (LinearLayout) mRootView.findViewById(R.id.ll_customer_consultation);
        llCustomerConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="CustomerConsultation";
                refreshCurrenPage(0);
                tvTitle.setText("客户咨询");
                newPageType=Pub.Customer_NewConsultation;
            }
        });
        tvCustomerConsultation = (TextView) mRootView.findViewById(R.id.tv_customer_consultation);
        llCustomerComplaints = (LinearLayout) mRootView.findViewById(R.id.ll_customer_complaints);
        llCustomerComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="CustomerComplaints";
                refreshCurrenPage(0);
                tvTitle.setText("客户投诉");
                newPageType=Pub.Customer_NewComplaint;
            }
        });
        tvCustomerComplaints = (TextView) mRootView.findViewById(R.id.tv_customer_complaints);
        llReturnedGoods = (LinearLayout) mRootView.findViewById(R.id.ll_returned_goods);
        llReturnedGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="ReturnedGoods";
                refreshCurrenPage(0);
                tvTitle.setText("退货-换货-补 片-降档申请");
                newPageType=Pub.Customer_NewTranferGoods;
            }
        });
        tvReturnedGoods = (TextView) mRootView.findViewById(R.id.tv_returned_goods);
        llCommonProblem = (LinearLayout) mRootView.findViewById(R.id.ll_common_problem);
        llCommonProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="CommonProblem";
                refreshCurrenPage(0);
                tvTitle.setText("常见问题");
                newPageType=Pub.Customer_NewProblem;
            }
        });
        tvCommonProblem = (TextView) mRootView.findViewById(R.id.tv_common_problem);

        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        ivNew=(ImageView) mRootView.findViewById(R.id.iv_new);
        ivNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                if(!Func.IsStringEmpty(tvTitle.getText().toString())){
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建"+tvTitle.getText().toString());
                }

                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, newPageType,true);
            }
        });
        rvInfoList=(RecyclerView)mRootView.findViewById(R.id.rv_infolist);
        rvInfoList.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        //分割线
//        MultiItemDivider itemDivider = new MultiItemDivider(JiuyiMainApplication.getIns(), MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
//        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
//        rvInfoList.addItemDecoration(itemDivider);
        rvInfoList.setItemAnimator(new DefaultItemAnimator());
        rvInfoList.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

        rvInfoList.setNestedScrollingEnabled(false);
        rvInfoList.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        rvInfoList.setFocusable(false);
        currentBillType="CustomerComplaints";
//        refreshCurrenPage(0);
        tvTitle.setText("客户投诉");
        newPageType=Pub.Customer_NewComplaint;
        TwinklingRefreshLayout  refreshLayout = (TwinklingRefreshLayout)mRootView.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex=1;
                        refreshCurrenPage(0);
                        refreshLayout.finishRefreshing();
                    }
                },2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(pageIndex>=totalPage){
                            Toast.makeText(JiuyiMainApplication.getIns(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }else{
                            refreshCurrenPage(pageIndex);
                            pageIndex++;

                        }
                        refreshLayout.finishLoadmore();


                    }
                },2000);
            }
        });


//        showProcessBar(0);
//        if(Rc.mCurrentPageType==Pub.Customer_Contract){
//            showDialog();
//        }
        tv_no_authoritytext.setVisibility(View.VISIBLE);
//        iv_no_authority.setVisibility(View.VISIBLE);
        app_bar.setVisibility(View.GONE);
        nsc_content.setVisibility(View.GONE);
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerServiceFragment newInstance(int nPageType) {
        JiuyiCustomerServiceFragment f = new JiuyiCustomerServiceFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerServiceFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerServiceFragment f = new JiuyiCustomerServiceFragment();
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
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh){
            Rc.mBackfresh=false;
            refreshCurrenPage(0);;
        }

    }

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction== DialogID.DialogDoNothing)
        {
            if(nKeyCode == KeyEvent.KEYCODE_BACK ){
                return;
            }
        }
    }

    public  void getInfoList(final int page){
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("market-activity/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<DyActivityBean>() {
                            @Override
                            public void onSuccess(DyActivityBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTradedeliveryBeanList=data.getContent();
                                    if(page==0){
                                        mTradedeliveryAdapter = new ConsultationAdapter(R.layout.customer_item_consultation, mTradedeliveryBeanList);
                                        rvInfoList.setAdapter(mTradedeliveryAdapter);
                                        mTradedeliveryAdapter.setOnCLickItemListener(new ConsultationAdapter.onCLickItemListener() {
                                            @Override
                                            public void click(int position, String colname, View view) {
                                                if(colname.equals("tv_title")){
                                                    ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                                                    menuWindow = new SelectReplyPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
                                                    //设置弹窗位置
                                                    menuWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                                                }else if(colname.equals("ll_repay")){
                                                    ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                                                    selectDeleteReplyPopupWindow = new SelectDeleteReplyPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
                                                    //设置弹窗位置
                                                    selectDeleteReplyPopupWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                                }

                                            }
                                        });
                                        if(mTradedeliveryBeanList==null||mTradedeliveryBeanList.size()==0){
                                            mTradedeliveryAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                        }
                                        showProcessBar(100);
                                    }else{
                                        mTradedeliveryAdapter.add(mTradedeliveryBeanList);
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

    protected View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
            }
            if (menuWindow != null) {
                menuWindow.dismiss();
            }
            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
            switch (v.getId()) {
                case R.id.item_popupwindows_reply:        //点击拍照按钮
                    Rc.getIns().getBaseCallTopCallBack().changePage(null,Pub.Customer_NewReply,true);

                    break;
                case R.id.item_popupwindows_reply_delete:        //点击拍照按钮
                    Rc.getIns().getBaseCallTopCallBack().changePage(null,Pub.Customer_NewReply,true);
                    break;
                default:
                    break;
            }
        }

    };

    public  QueryConditionBean  builderCondition(int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
//        List<String> value = new ArrayList<String>();
//        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
//        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
//        rulesBean.setField("member");
//        rulesBean.setOption("EQ");
//        value.add(Customerid+"");
//        rulesBean.setValues(value);
//        rulesBeanList.add(rulesBean);
//        queryConditionBean.setRules(rulesBeanList);
        return queryConditionBean;
    }
    public void refreshCurrenPage(int page){
        if(currentBillType.equals("CustomerConsultation")){
            getInfoList(page);
        }else if(currentBillType.equals("CustomerComplaints")){
            getInfoList(page);
        }else if(currentBillType.equals("ReturnedGoods")){
            getInfoList(page);
        }else if(currentBillType.equals("CommonProblem")){
            getInfoList(page);
        }
    }

}
