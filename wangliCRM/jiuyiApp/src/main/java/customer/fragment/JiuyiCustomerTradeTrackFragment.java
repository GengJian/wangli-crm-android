package customer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.webview.JiuyiWebView;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.adapter.StepViewAdapter;
import customer.entity.AttachmentBean;
import customer.entity.FeedFlowBean;
import customer.entity.StepViewBean;
/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerTradeTrackFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户360交易跟踪信息
 *****************************************************************
 */

public class JiuyiCustomerTradeTrackFragment extends JiuyiFragmentBase {
    private List<StepViewBean> traceList ;
    ImageButton mibadd;
    private long Customerid=-1;
    private String Customername="";
    private String mRisktype="";
    ListView lvTrace;
    StepViewAdapter adapter;
    int pageIndex = 1;
    int pageSize = 20;
    private  int totalPage=0;
    private TextView tv_emptytext;
    private ImageView iv_empty;
    /** 当前的WebView */
    public JiuyiWebView mWebView;
    /** webview要加载的url */
    public String mWebUrl;
    public FrameLayout fl_track;

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
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerrisktrackfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        mRisktype=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE);

         lvTrace= (ListView) mRootView.findViewById(R.id.lvTrace);
        tv_emptytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_emptytext"));
        iv_empty=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_empty"));
        mWebView = (JiuyiWebView)mRootView.findViewById(Res.getViewID(getContext(), "jiuyi_webview"));
        fl_track= (FrameLayout)mRootView.findViewById(Res.getViewID(getContext(), "fl_track"));
        String urladd=Res.getString(null, "jiuyitransactionTracking");
        String url="";
        if(!Func.IsStringEmpty(urladd)){
            url= Constant.BaseH5Url+urladd+"?memberId="+Customerid+"&token="+ Rc.id_tokenparam;
            mWebUrl=url;
        }
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                getRiskWarnList(0);
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
                    getRiskWarnList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;

            }
        });
        if(Rc.mCurrentPageType==Pub.Customer_Service){
            showDialog();
        }
        getRiskWarnList(0);
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setVisibility(View.GONE);
           /* mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newrisk);
                    mBundle.putInt(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newrisk,true);
                }
            });*/
        }

    }

    public  void  getRiskWarnList(final int page){
        if(page==0){
            pageIndex=1;
        }
        final QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(10);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("memberId");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        if(!Func.IsStringEmpty(mRisktype) && !mRisktype.toLowerCase().equals("all")){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("childCategoryId");
            rulesBean2.setOption("EQ");
            value2.add(mRisktype);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        List<String> value3 = new ArrayList<String>();
        QueryConditionBean.RulesBean rulesBean3=new QueryConditionBean.RulesBean();
        rulesBean3.setField("bigCategory");
        rulesBean3.setOption("EQ");
        value3.add("feed_transaction_tracking");
        rulesBean3.setValues(value3);
        rulesBeanList.add(rulesBean3);
        queryConditionBean.setRules(rulesBeanList);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("feed-flow/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<FeedFlowBean>() {
                            @Override
                            public void onSuccess(FeedFlowBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<FeedFlowBean.ContentBean> contentBeanList=data.getContent();
                                    traceList = new ArrayList<>();
                                    if(contentBeanList!=null && contentBeanList.size()>0){
                                        for(FeedFlowBean.ContentBean contentBean: contentBeanList){
                                            StepViewBean stepViewBean=new StepViewBean();
                                            List<AttachmentBean> attchUrl=new ArrayList<>();
                                            stepViewBean.setId(contentBean.getId());
                                            if(!Func.IsStringEmpty(contentBean.getCreatedDateStr())){
                                                stepViewBean.setAcceptTime(contentBean.getCreatedDateStr().toString());
                                            }
                                           if(!Func.IsStringEmpty(contentBean.getTitle())){
                                                stepViewBean.setAcceptStation(contentBean.getTitle());
                                            }
                                            if(!Func.IsStringEmpty(contentBean.getIconUrl())){
                                                stepViewBean.setIconUrl(contentBean.getIconUrl().toString());
                                            }
                                            if(!Func.IsStringEmpty(contentBean.getContent())){
                                                stepViewBean.setAcceptStationBelow(contentBean.getContent());
                                            }
                                            if(contentBean.getUrl()!=null){
                                                stepViewBean.setUrl(contentBean.getUrl());
                                            }
                                            if(contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0){
                                                for(int i=0;i<contentBean.getAttachmentList().size();i++){
                                                    if(contentBean.getAttachmentList().get(i).getUrl()!=null){
                                                        attchUrl.add(contentBean.getAttachmentList().get(i));
                                                    }
                                                }
                                                stepViewBean.setAttachurl(attchUrl);
                                            }
                                            traceList.add(stepViewBean);
                                        }
                                        if(page==0){
                                            adapter = new StepViewAdapter(JiuyiMainApplication.getIns(), traceList);
                                            lvTrace.setAdapter(adapter);
                                        }else {
                                            adapter.add(traceList);
                                        }


                                    }else {
                                        if(page==0){
                                            adapter = new StepViewAdapter(JiuyiMainApplication.getIns(), traceList);
                                            lvTrace.setAdapter(adapter);
                                            adapter.clear();
                                            adapter.notifyDataSetChanged();
                                        }

                                    }
                                    if((traceList==null|| traceList.size()==0)&& page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        iv_empty.setVisibility(View.VISIBLE);
                                    }else {
                                        tv_emptytext.setVisibility(View.GONE);
                                        iv_empty.setVisibility(View.GONE);
                                    }


                                }
                                if (isAdded()){
                                    if(loadingDialog!=null){
                                        loadingDialog.dismiss();
                                    }
                                }
                                JiuyiLog.e("httplogin","request onSuccess!" + data);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                if (isAdded()){
                                    if(loadingDialog!=null){
                                        loadingDialog.dismiss();
                                    }
                                }
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
    public static JiuyiCustomerTradeTrackFragment newInstance(int nPageType) {
        JiuyiCustomerTradeTrackFragment f = new JiuyiCustomerTradeTrackFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerTradeTrackFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerTradeTrackFragment f = new JiuyiCustomerTradeTrackFragment();
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
            getRiskWarnList(0);
        }

    }
    /**
     * 初始化加载url
     */
    public void onInitLoadUrl(){
        if(mWebUrl != null) {
            loadUrl(mWebUrl);
        }
    }
    /**
     * 加载网页url
     */
    public void loadUrl(final String url){
        if(mWebView != null) {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl(url);
                }
            });
        }
    }
    public void showContent(String type){
        if(type.equals("1")){
            fl_track.setVisibility(View.VISIBLE);
            setmRisktype("all");
            showDialog();
            getRiskWarnList(0);
            mWebView.setVisibility(View.GONE);
        }else if(type.equals("2")){
            fl_track.setVisibility(View.GONE);
            tv_emptytext.setVisibility(View.GONE);
            iv_empty.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
            onInitLoadUrl();

        }

    }

}
