package customer.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.tools.jiuyiCustomerViewPager;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.BusinessChanceAdapter;
import customer.adapter.PainPointAdapter;
import customer.entity.BusinessChanceBean;
import customer.entity.CustomerLinkmanBean;
import customer.entity.PainPointBean;
import customer.view.DrawableTextView;
import customer.view.MultiItemDivider;
import customer.view.SelectReplyPopupWindow;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerLinkmanOrgFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 人事组织
 *****************************************************************
 */
public class JiuyiCustomerLinkmanOrgFragment extends JiuyiFragmentBase {
    private long Customerid=-1;
    private String Customername="";
    private String mRisktype="";
    protected jiuyiCustomerViewPager pViewPageBar;//下方viewpager
    private DetailPagerAdapter mAdapter;
    private List<CustomerLinkmanBean.ContentBean> mList;

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    private  int postion=-1;//当前页面
    private int TOTAL_SIZE = 40;
    private final int PAGE_SIZE = 10;

    int pageIndexContact = 0;
    int pagesizeContact = 20;
    private int totalPageContact=0;

    private int mCount = 0;
    private ImageView iv_pri,iv_next;
    private TextView tvBusiness,tv_emptytext;
    private TextView tvNeed;
    private RecyclerView rvList;
    private DrawableTextView dtvContact;
    private DrawableTextView dtvNeed;
    RecyclerView rvInfoList;

    private List<CustomerLinkmanBean.ContentBean> mCustomerLinkmanBeanList;

    private List<PainPointBean.ContentBean> mPainPointBeanList;
    private PainPointAdapter painPointAdapter;

    private List<BusinessChanceBean.ContentBean> mBusinessChanceBeanList;
    private BusinessChanceAdapter businessChanceAdapter;
    QueryConditionBean queryConditionBean;
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pagesize = 20;
    private int totalPage=0;
    private String currentBillType="";

    public long getDeptID() {
        return deptID;
    }

    public void setDeptID(long deptID) {
        this.deptID = deptID;
    }

    private long deptID=-1;
    protected SelectReplyPopupWindow menuWindow;
    PainPointBean.ContentBean contentBean;


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
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerlinkmanorgfragment_layout"), null);
        onInit();
        getContractByMemberIdList(0);
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
        pViewPageBar=(jiuyiCustomerViewPager)mRootView.findViewById(R.id.jiuyi_viewpager);
        pViewPageBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                postion=position;
                refreshCurrenPage(0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tv_emptytext = (TextView) mRootView.findViewById(R.id.tv_emptytext);
        tvBusiness = (TextView) mRootView.findViewById(R.id.tv_business);
        tvBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="BUSINESS";
                tvBusiness.setTextColor(Res.getColor(null, "jiuyi_blue"));
                tvNeed.setTextColor(Res.getColor(null, "jiuyi_title_color"));
                refreshCurrenPage(0);
            }
        });
        tvBusiness.setVisibility(View.INVISIBLE);
        tvNeed = (TextView) mRootView.findViewById(R.id.tv_need);
        tvNeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBillType="NEED";
                tvNeed.setTextColor(Res.getColor(null, "jiuyi_blue"));
                tvBusiness.setTextColor(Res.getColor(null, "jiuyi_title_color"));
                refreshCurrenPage(0);
            }
        });
        rvList = (RecyclerView) mRootView.findViewById(R.id.rv_list);
        dtvContact = (DrawableTextView) mRootView.findViewById(R.id.dtv_contact);
        dtvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newlinkman,true);

            }
        });
        dtvNeed = (DrawableTextView) mRootView.findViewById(R.id.dtv_need);
        dtvNeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, Customername);
                if(postion>=0 && mList!=null && mList.size()>0 && mList.get(postion)!=null){
                    mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN, mList.get(postion));
                }
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建需求");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newlinkmanneed,true);
            }
        });
        iv_pri=(ImageView)mRootView.findViewById(R.id.iv_pri);
        iv_pri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postion>0){
                    postion--;
                    pViewPageBar.setCurrentItem(postion);
                    refreshCurrenPage(0);
                }
            }
        });
        iv_next=(ImageView)mRootView.findViewById(R.id.iv_next);
        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postion!=mCount-1){
                    postion++;
                    pViewPageBar.setCurrentItem(postion);
                    refreshCurrenPage(0);
                }else {
                    if(pageIndexContact>=totalPageContact){
                        Toast.makeText(JiuyiMainApplication.getIns(), "没有更多数据", Toast.LENGTH_SHORT).show();
                    }else {
                        pageIndexContact++;
                        if(deptID>0){
                            getContractByDeptIdList(pageIndexContact);
                        }else{
                            getContractByMemberIdList(pageIndexContact);
                        }

                    }

                }
            }
        });
        rvInfoList=(RecyclerView)mRootView.findViewById(R.id.rv_infolist);
        rvInfoList.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        //分割线
        MultiItemDivider itemDivider = new MultiItemDivider(getContext(), MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
        rvInfoList.addItemDecoration(itemDivider);
        rvInfoList.setNestedScrollingEnabled(false);
        rvInfoList.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        rvInfoList.setFocusable(false);
        currentBillType="NEED";
        tvNeed.setTextColor(Res.getColor(null, "jiuyi_blue"));
        TwinklingRefreshLayout refreshLayout = (TwinklingRefreshLayout)mRootView.findViewById(R.id.refresh);
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
        showProcessBar(0);
    }



    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerLinkmanOrgFragment newInstance(int nPageType) {
        JiuyiCustomerLinkmanOrgFragment f = new JiuyiCustomerLinkmanOrgFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerLinkmanOrgFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerLinkmanOrgFragment f = new JiuyiCustomerLinkmanOrgFragment();
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
            getContractByMemberIdList(0);
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

    private class DetailPagerAdapter extends FragmentStatePagerAdapter {

        public DetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return JiuyiCustomerContactViewPageFragment.newInstance(mList.get(position));
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }
    }

    private class OnFragmentPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float offsetPerc,
                                   int offsetPixel) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageSelected(int position) {
            int size = mList.size();
            if (position == size - 1 && pageIndex>0/* && TOTAL_SIZE > size*/) {
                //实际项目中是网络请求下一页的列表数据
//                getMoreDetailList();
            }

        }
    }

    public  void getContractByMemberIdList(final int page){
        if(page==0){
            pageIndexContact=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<Long> value = new ArrayList<Long>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member.id");
        rulesBean.setOption("EQ");
        value.add(Customerid);
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        queryConditionBean.setRules(rulesBeanList);
//        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("link-man/find-pager-memberId")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<CustomerLinkmanBean>() {
                            @Override
                            public void onSuccess(CustomerLinkmanBean data) {
                                if(data!=null){
                                    totalPageContact=data.getTotalPages();
                                    if(page==0){
                                        mCustomerLinkmanBeanList=data.getContent();
                                        if(postion==-1){
                                            postion=0;
                                        }
                                        if(pViewPageBar!=null){
                                            pViewPageBar.setCurrentItem(postion);
                                        }
                                    }else{
                                        for(int i=0;i<data.getContent().size();i++){
                                            mCustomerLinkmanBeanList.add(data.getContent().get(i));
                                        }
                                    }
                                    mList = new ArrayList<>();
                                    if(mCustomerLinkmanBeanList!=null&&mCustomerLinkmanBeanList.size()>0 ){
                                        TOTAL_SIZE=mCustomerLinkmanBeanList.size();
                                        mCount=mCustomerLinkmanBeanList.size();
                                        mList=mCustomerLinkmanBeanList;
                                    }
                                    if(page==0){
                                        refreshCurrenPage(0);
                                    }
                                }
                                if (isAdded()) {
                                    mAdapter = new DetailPagerAdapter(getChildFragmentManager());
                                    pViewPageBar.setAdapter(mAdapter);
                                    pViewPageBar.addOnPageChangeListener(new OnFragmentPageChangeListener());
                                }
                                if(tv_emptytext!=null && pViewPageBar!=null ){
                                    if(mList==null ||mList.size()==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        pViewPageBar.setVisibility(View.GONE);
                                    }else{
                                        tv_emptytext.setVisibility(View.GONE);
                                        pViewPageBar.setVisibility(View.VISIBLE);
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

    public  void getContractByDeptIdList(final int page){
        if(page==0){
            pageIndexContact=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<Long> value = new ArrayList<Long>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("office.id");
        rulesBean.setOption("EQ");
        value.add(getDeptID());
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        queryConditionBean.setRules(rulesBeanList);
//        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("link-man/find-pager-memberId")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<CustomerLinkmanBean>() {
                    @Override
                    public void onSuccess(CustomerLinkmanBean data) {
                        if(data!=null){
                            totalPageContact=data.getTotalPages();
                            if(page==0){
                                mCustomerLinkmanBeanList=data.getContent();
                            }else{
                                for(int i=0;i<data.getContent().size();i++){
                                    mCustomerLinkmanBeanList.add(data.getContent().get(i));
                                }
                            }
                            mList = new ArrayList<>();
                            if(mCustomerLinkmanBeanList!=null&&mCustomerLinkmanBeanList.size()>0 ){
                                TOTAL_SIZE=mCustomerLinkmanBeanList.size();
                                mCount=mCustomerLinkmanBeanList.size();
                                mList=mCustomerLinkmanBeanList;
                            }
                        }
                        mAdapter = new DetailPagerAdapter(getChildFragmentManager());
                        pViewPageBar.setAdapter(mAdapter);
                        pViewPageBar.addOnPageChangeListener(new OnFragmentPageChangeListener());
                        if(mList==null ||mList.size()==0){
                            tv_emptytext.setVisibility(View.VISIBLE);
                            pViewPageBar.setVisibility(View.GONE);
                        }else{
                            if(postion==-1){
                                postion=0;
                            }
                            pViewPageBar.setCurrentItem(postion);
                            pViewPageBar.setVisibility(View.VISIBLE);
                            tv_emptytext.setVisibility(View.GONE);
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



    public  void getPainPointList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        if(postion>=0 && mList.size()>0 && mList.get(postion)!=null){
            List<Long> value = new ArrayList<Long>();
            List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("linkMan.id");
            rulesBean.setOption("EQ");
            value.add(mList.get(postion).getId());
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
            queryConditionBean.setRules(rulesBeanList);
            JiuyiHttp.POST("pain-point/find-pain-point-bean")
                    .setJson(GsonUtil.gson().toJson(queryConditionBean))
                    .addHeader("Authorization",Rc.getIns().id_tokenparam)
                    .request(new ACallback<PainPointBean>() {
                        @Override
                        public void onSuccess(PainPointBean data) {
                            if(data!=null){
                                totalPage=data.getTotalPages();
                                tvNeed.setText("关键需求("+data.getTotalElements()+")");
                                mPainPointBeanList=data.getContent();
                                if(page==0){
                                    painPointAdapter = new PainPointAdapter(R.layout.customer_item_importantneed, mPainPointBeanList);
                                    rvInfoList.setAdapter(painPointAdapter);
                                    painPointAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            contentBean=(PainPointBean.ContentBean)painPointAdapter.getData().get(position);
                                            if(contentBean.isNeedFeedBack()){
                                                ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                                                menuWindow = new SelectReplyPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
                                                //设置弹窗位置
                                                menuWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                            }

                                        }
                                    });
                                    if(mPainPointBeanList==null||mPainPointBeanList.size()==0){
                                        painPointAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                    }
                                    showProcessBar(100);
                                }else{
                                    painPointAdapter.add(mPainPointBeanList);
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

        }else {
            mPainPointBeanList=new ArrayList<>();
            painPointAdapter = new PainPointAdapter(R.layout.customer_item_importantneed, mPainPointBeanList);
            rvInfoList.setAdapter(painPointAdapter);
            painPointAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
        }



    }

    public  void getBusinessChanceList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        if(postion>=0&& mList!=null && mList.size()>0 &&mList.get(postion)!=null){
            List<String> value = new ArrayList<String>();
            List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("customerContact");
            rulesBean.setOption("EQ");
            value.add(mList.get(postion).getName());
            rulesBean.setValues(value);
            rulesBeanList.add(rulesBean);
            queryConditionBean.setRules(rulesBeanList);
            JiuyiHttp.POST("business-chance/find-business-chance-bean")
                    .setJson(GsonUtil.gson().toJson(queryConditionBean))
                    .addHeader("Authorization",Rc.getIns().id_tokenparam)
                    .request(new ACallback<BusinessChanceBean>() {
                        @Override
                        public void onSuccess(BusinessChanceBean data) {
                            if(data!=null){
                                totalPage=data.getTotalPages();
                                tvBusiness.setText("关联商机("+data.getTotalElements()+")");
                                mBusinessChanceBeanList=data.getContent();
                                if(page==0){
                                    businessChanceAdapter = new BusinessChanceAdapter(R.layout.customer_item_businesschance, mBusinessChanceBeanList);
                                    rvInfoList.setAdapter(businessChanceAdapter);
                                    businessChanceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            BusinessChanceBean.ContentBean contentBean=(BusinessChanceBean.ContentBean)businessChanceAdapter.getData().get(position);
//                                                if(contentBean!=null){
//                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
//                                                    mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,backPageType);
//                                                    mBundle.putLong(JiuyiBundleKey.PARAM_BILLID, contentBean.getId());
//                                                    if(!Func.IsStringEmpty(tvTitle.getText().toString())){
//                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, tvTitle.getText().toString());
//                                                    }
//
//                                                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newstandard,true);
//                                                }
                                        }
                                    });
                                    if(mBusinessChanceBeanList==null||mBusinessChanceBeanList.size()==0){
                                        businessChanceAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
                                    }
                                    showProcessBar(100);
                                }else{
                                    businessChanceAdapter.add(mBusinessChanceBeanList);
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

        }else{
            mBusinessChanceBeanList=new ArrayList<>();
            businessChanceAdapter = new BusinessChanceAdapter(R.layout.customer_item_businesschance, mBusinessChanceBeanList);
            rvInfoList.setAdapter(businessChanceAdapter);
            businessChanceAdapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfoList.getParent());
        }


    }
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
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("member");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        queryConditionBean.setRules(rulesBeanList);
        return queryConditionBean;
    }
    public void refreshCurrenPage(int page){
        if(currentBillType.equals("BUSINESS")){
            getBusinessChanceList(page);
        }else if(currentBillType.equals("NEED")){
            getPainPointList(page);
        }
    }



    @Override
    public void createReq(boolean IsBg) {
        getContractByMemberIdList(0);
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
                    if(contentBean!=null){
                        mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,contentBean.getDesp());
                        mBundle.putString(JiuyiBundleKey.PARAM_FEILD,"reply");
                    }
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_NewReply,true);
                    break;
                case R.id.item_popupwindows_reply_delete:        //点击拍照按钮
                    if(contentBean!=null){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,contentBean.getDesp());
                    }
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customer_NewReply,true);
                    break;
                default:
                    break;
            }
        }

    };


}
