package customer.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
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
import customer.Utils.ViewOperatorType;
import customer.adapter.StepViewAdapter;
import customer.entity.AttachmentBean;
import customer.entity.FeedFlowBean;
import customer.entity.StepViewBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerMarketTrackFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户市场动态跟踪
 *****************************************************************
 */
public class JiuyiCustomerMarketTrackFragment extends JiuyiFragmentBase {
    private List<StepViewBean> traceList ;
    ImageButton mibadd;
    private long Customerid=-1;
    private String Customername="";
    private String mBigtype ="";

    public String getmSmalltype() {
        return mSmalltype;
    }

    public void setmSmalltype(String mSmalltype) {
        this.mSmalltype = mSmalltype;
    }

    private String mSmalltype="";
    ListView lvTrace;
    StepViewAdapter adapter;
    int pageIndex = 1;
    int pageSize = 20;
    private int totalPage=0;
    private TextView tv_emptytext;
    private ImageView iv_empty;
    private int curpostion=-1;

    public String getmBigtype() {
        return mBigtype;
    }

    public void setmBigtype(String mBigtype) {
        this.mBigtype = mBigtype;
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
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        mBigtype =mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE);
        mSmalltype =mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERSMALLTYPE);

         lvTrace= (ListView) mRootView.findViewById(R.id.lvTrace);
        tv_emptytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_emptytext"));
        iv_empty=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_empty"));
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
        if(Rc.mCurrentPageType==Pub.Customer_Contract){
            showDialog();
        }
        getRiskWarnList(0);
//        registerForContextMenu(lvTrace);
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新情报");
                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME,Customername);
                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SINGLEADD);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_InformationNew, true);
                }
            });
        }
        if(!Func.IsStringEmpty(mBigtype) && (mBigtype.equals("contract")||mBigtype.equals("BUSINESS_FOLLOW")) ){
            mibadd.setVisibility(View.GONE);
        }else{
            mibadd.setVisibility(View.VISIBLE);
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
        rulesBean.setField("member.id");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        if(!Func.IsStringEmpty(mSmalltype) && !mSmalltype.toLowerCase().equals("all")){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("childCategoryId");
            rulesBean2.setOption("EQ");
            value2.add(mSmalltype);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(mBigtype) && !mBigtype.toLowerCase().equals("all")){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("bigCategory");
            rulesBean2.setOption("EQ");
            value2.add(mBigtype);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
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
//                                            if(contentBean.getUrl()!=null){
//                                                stepViewBean.setUrl(contentBean.getUrl());
//                                            }
                                            if(contentBean.getOperatorName()!=null){
                                                stepViewBean.setCreator(contentBean.getOperatorName());
                                            }
                                            if(contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0){
                                                for(int i=0;i<contentBean.getAttachmentList().size();i++){
                                                    AttachmentBean attachmentBean=contentBean.getAttachmentList().get(i);
                                                    if(attachmentBean.getQiniuKey()!=null && attachmentBean.getFileType().toLowerCase().equals("jpg") ){
                                                        contentBean.getAttachmentList().get(i).setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                                                        contentBean.getAttachmentList().get(i).setThumbnail(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                                                    }
                                                }
                                                stepViewBean.setAttachurl(contentBean.getAttachmentList());
                                            }
                                            traceList.add(stepViewBean);
                                        }
                                        if(page==0){
                                            adapter = new StepViewAdapter(JiuyiMainApplication.getIns(), traceList);
                                            lvTrace.setAdapter(adapter);
                                            lvTrace.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                                                @Override
                                                public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                                               int position, long id) {
                                                    curpostion=position;
                                                    return false;
                                                }

                                            });
                                        }else {
                                            adapter.add(traceList);
                                            lvTrace.setAdapter(adapter);
                                        }
                                    }else {
                                        if(page==0){
                                            adapter = new StepViewAdapter(JiuyiMainApplication.getIns(), traceList);
                                            if(lvTrace!=null){
                                                lvTrace.setAdapter(adapter);
                                            }
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
    public static JiuyiCustomerMarketTrackFragment newInstance(int nPageType) {
        JiuyiCustomerMarketTrackFragment f = new JiuyiCustomerMarketTrackFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerMarketTrackFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerMarketTrackFragment f = new JiuyiCustomerMarketTrackFragment();
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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(0, 1, Menu.NONE, "删除");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        startDialog(DialogID.DialogDoNothing, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
        return true;
    }

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction== DialogID.DialogDoNothing)
        {
            if(nKeyCode == KeyEvent.KEYCODE_BACK ){
                return;
            }else if(nKeyCode == KeyEvent.KEYCODE_ENTER){
                if(adapter!=null && curpostion!=-1){
                    deleteFlow(adapter.getItem(curpostion).getId());
                }
            }


        }
    }

    public void deleteFlow(long id){
        JiuyiHttp.DELETE("market-trend/delete-feed/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(adapter!=null && curpostion!=-1){
                                traceList.remove(curpostion);
                                adapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(adapter==null||adapter.getCount()==0){
                                    tv_emptytext.setVisibility(View.GONE);
                                    iv_empty.setVisibility(View.GONE);
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
