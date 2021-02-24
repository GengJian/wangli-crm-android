package customer.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.utils.CustomerQueryConditionBean;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFragmentBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.activity.common.activity.JiuyiRootActivity;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityManager;
import com.jiuyi.app.JiuyiMainApplication;
import com.recyclerview.swipe.Closeable;
import com.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.recyclerview.swipe.SwipeMenu;
import com.recyclerview.swipe.SwipeMenuCreator;
import com.recyclerview.swipe.SwipeMenuItem;
import com.recyclerview.swipe.SwipeMenuRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.adapter.CustomerListAdapter;
import customer.adapter.CustomerSelMenuAdapter;
import customer.entity.MemberListBean;
import customer.entity.MemberchooseBean;
import customer.listener.OnItemClickListener;
import customer.shared.MemberchooseConditionShared;
import customer.view.SFPopupWindow;
import customer.view.jiuyiRecycleViewDivider;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户列表信息
 *****************************************************************
 */

public class JiuyiCustomerListFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private ListView  popListView;
    private List<Map<String, Object>>  menuData1, menuData2, menuData3;
    private SFPopupWindow popMenu;
    private CustomerSelMenuAdapter menuAdapter1, menuAdapter2, menuAdapter3;

    private LinearLayout mLinecharsort, mLinequicksort, mLinemanual;
    private TextView mTvcharsort, mTvquicksort, mTvmanual, titleTv;

    private String currentProduct, currentSort, currentActivity;
    private int menuIndex = 0;
    private List<MemberListBean.ContentBean> mViewTypeBeanList;
    private List<MemberListBean.ContentBean> mCustomerListBeanList;
    private int[] imageids = { R.drawable.client_selected };
    SwipeMenuRecyclerView swipeMenuRecyclerView;
    CustomerListAdapter menuAdapter;
    RefreshLayout refreshLayout;
    private long[] customerids=null;
    int pageIndex = 1;
    int pageSize = 20;
    private int totalPage=0;
    private TextView tv_emptytext;
    private ImageView iv_empty,iv_manualsort,iv_charsort,iv_quicksort;
    ImageButton mibadd;
    private Boolean firstStart=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerfragment_layout"), null);
            onInit();
        }else{
            checkRootViewParent();
        }

        return mRootView;
    }
    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerListFragment newInstance(int nPageType) {
        JiuyiCustomerListFragment f = new JiuyiCustomerListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    @Override
    public void onInit() {
        findView();
        initPopMenu();
        swipeMenuRecyclerView = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.recycler_view);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(mCallBack.getActivity()));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

        swipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        swipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);

        tv_emptytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_emptytext"));
        iv_empty=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_empty"));
        iv_manualsort=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_manualsort"));
        iv_charsort=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_charsort"));
        iv_quicksort=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_quicksort"));
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null, Pub.MSG_PopStart,true);
                }
            });
        }
        getCustomerList(0);

        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                getCustomerList(0);
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
                    getCustomerList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;
            }
        });
        showProcessBar(0);
    }
    public void setManualSelect(){
        mTvmanual.setTextColor(Res.getColor(null, "jiuyi_blue"));
        iv_manualsort.setImageResource(R.drawable.client_filter_s);
        mTvquicksort.setText("快速检索");
        mTvquicksort.setTextColor(Color.parseColor("#5a5959"));
        iv_quicksort.setImageResource(R.mipmap.client_down_n);
    }
    public void setManualUnSelect(){
        mTvmanual.setTextColor(Color.parseColor("#5a5959"));
        iv_manualsort.setImageResource(R.mipmap.client_filter_n);
    }

    public  void  getCustomerList(final int page){
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        if(Rc.getIns().queryConditionBean!=null ){
            queryConditionBean=Rc.getIns().queryConditionBean;
        }
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
//        queryConditionBean.setModuleNumber("002");
        if(page==0){
            pageIndex=1;
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("member/find-page")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<MemberListBean>() {
                            @Override
                            public void onSuccess(MemberListBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<MemberListBean.ContentBean> ContentBeanlist =data.getContent();
                                    mCustomerListBeanList =data.getContent();
//                                    if(ContentBeanlist !=null && ContentBeanlist.size()>0){
//                                        for(int i=0;i<mCustomerListBeanList.size();i++){
//                                            MemberListBean.ContentBean contentBean=mCustomerListBeanList.get(i);
//                                            if(contentBean.isClaim()){
//                                                contentBean.setViewType(CustomerListAdapter.VIEW_TYPE_MENU_NODIRECT);
//                                            }else if(!contentBean.isRelease() && contentBean.isTransfer() ){
//                                                contentBean.setViewType(CustomerListAdapter.VIEW_TYPE_MENU_TRANSFER);
//                                            }else if(contentBean.isRelease() && !contentBean.isTransfer() ){
//                                                contentBean.setViewType(CustomerListAdapter.VIEW_TYPE_MENU_RELEASE);
//                                            }else if(contentBean.isRelease() && contentBean.isTransfer()){
//                                                contentBean.setViewType(CustomerListAdapter.VIEW_TYPE_MENU_MULTI);
//                                            }
//                                        }
//                                    }
                                    if(page==0){
                                        if(menuAdapter==null){
                                            menuAdapter = new CustomerListAdapter(mCustomerListBeanList);
                                            menuAdapter.setOnItemClickListener(onItemClickListener);
                                            swipeMenuRecyclerView.setAdapter(menuAdapter);
                                        }else{
                                            menuAdapter.refresh(mCustomerListBeanList);
                                        }
                                        showProcessBar(100);
                                    }else{
                                        menuAdapter.add(ContentBeanlist);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
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


    private void initMenuData() {
        menuData1 =  new ArrayList<Map<String, Object>>();
        String[] menuStr1=MemberchooseConditionShared.getIns().getSort();
        Map<String, Object>  map1;
        if(menuStr1!=null && menuStr1.length>0){
            for (int i = 0, len = menuStr1.length; i < len; ++i) {
                map1 = new HashMap<String, Object>();
                map1.put("name", menuStr1[i]);
                map1.put("select", imageids[0]);
                menuData1.add(map1);
            }
        }


        menuData2 = new ArrayList<Map<String, Object>>();
        String[] menuStr2 = MemberchooseConditionShared.getIns().getQuicksort();
        Map<String, Object>map2;
        if(menuStr2!=null && menuStr2.length>0){
            for (int i = 0, len = menuStr2.length; i < len; ++i) {
                map2 = new HashMap<String, Object>();
                map2.put("name", menuStr2[i]);
                map2.put("select", imageids[0]);
                menuData2.add(map2);
            }
        }

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_charsort:
                mTvcharsort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                iv_charsort.setImageResource(R.drawable.drop_down_s);
                popListView.setAdapter(menuAdapter1);
                popMenu.showAsDropDown(mLinecharsort, 0, 2);
                menuIndex = 0;
                break;
            case R.id.ll_quick_sort:
                mTvquicksort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                iv_quicksort.setImageResource(R.drawable.drop_down_s);
                popListView.setAdapter(menuAdapter2);
                popMenu.showAsDropDown(mLinecharsort, 0, 2);
                menuIndex = 1;
                break;
            case R.id.ll_manualsort:
                Activity activity = JiuyiActivityManager.getCurrentActivity();
                if(activity != null && activity instanceof JiuyiRootActivity){
                    if(Rc.getIns().queryConditionBean!=null){
                        if( Rc.getIns().queryConditionBean.getRules()==null && !Func.IsStringEmpty(Rc.getIns().quicksortcondition)){
                            if(((JiuyiRootActivity)activity).ll_searchcondition!=null){
                                ((JiuyiRootActivity)activity).ll_searchcondition.removeAllViews();
                            }
                            ((JiuyiRootActivity)activity).addSearchConditon(true);
                        }
                        ((JiuyiRootActivity)activity).OpenDrawer();
                    }else{
                        ((JiuyiRootActivity)activity).addSearchConditon(true);
                        ((JiuyiRootActivity)activity).OpenDrawer();
                    }

                }
                break;

        }
    }
    protected void findView() {
        mLinecharsort = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_charsort"));
        mLinequicksort = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_quick_sort"));
        mLinemanual = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_manualsort"));
        mTvcharsort = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_charsort"));
        mTvquicksort = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_quicksort"));
        mTvmanual = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_manualsort"));

        mLinecharsort.setOnClickListener(this);
        mLinequicksort.setOnClickListener(this);
        mLinemanual.setOnClickListener(this);
    }
    private void initPopMenu() {
        initMenuData();
        View contentView = View.inflate(mCallBack.getActivity(), R.layout.popwin_supplier_list,
                null);
        popMenu = new SFPopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                mTvcharsort.setTextColor(Color.parseColor("#5a5959"));
                iv_charsort.setImageResource(R.mipmap.client_down_n);
                mTvquicksort.setTextColor(Color.parseColor("#5a5959"));
                iv_quicksort.setImageResource(R.mipmap.client_down_n);
                mTvmanual.setTextColor(Color.parseColor("#5a5959"));
                iv_manualsort.setImageResource(R.mipmap.client_filter_n);
            }
        });

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        popMenu.dismiss();
                    }
                });



        menuAdapter1=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData1);
        menuAdapter2=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData2);
//        menuAdapter3=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData3);
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                if (menuIndex == 0) {
                    //改变选中状态
                    menuAdapter1.setCurrentItem(pos);
                    //通知ListView改变状态
                    menuAdapter1.notifyDataSetChanged();
                } else if (menuIndex == 1) {
                    //改变选中状态
                    menuAdapter2.setCurrentItem(pos);
                    //通知ListView改变状态
                    menuAdapter2.notifyDataSetChanged();
                } else {
                    //改变选中状态
                    menuAdapter3.setCurrentItem(pos);
                    //通知ListView改变状态
                    menuAdapter3.notifyDataSetChanged();
                }
                popMenu.dismiss();
                if (menuIndex == 0) {
                    currentProduct = menuData1.get(pos).get("name").toString();
                    if(currentProduct.equals("不限")){
                        Rc.getIns().queryConditionBean=null;
                    }else{
                        MemberchooseBean.ContentBean.MemberChooseBeansBean contentchooseBean=MemberchooseConditionShared.getIns().getSortlist().get(pos);
                        if(contentchooseBean!=null){
                            if(Rc.getIns().queryConditionBean!=null){
                                Rc.getIns().queryConditionBean.setSpecialDirection(contentchooseBean.getMemberFeild());
                            }else{
                                CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
                                queryConditionBean.setNumber(0);
                                queryConditionBean.setSize(20);
                                queryConditionBean.setDirection("DESC");
                                queryConditionBean.setProperty("createdDate");
                                Rc.getIns().queryConditionBean=queryConditionBean;
                                Rc.getIns().queryConditionBean.setSpecialDirection(contentchooseBean.getMemberFeild());
                            }
                            MobclickAgent.onEvent(JiuyiMainApplication.getIns(), contentchooseBean.getMemberFeild());
                        }
                    }
                    getCustomerList(0);
                    mTvcharsort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                    iv_charsort.setImageResource(R.drawable.c_down_s);
                    mTvcharsort.setText(currentProduct);
                    Toast.makeText(mCallBack.getActivity(), currentProduct, Toast.LENGTH_SHORT).show();
                } else if (menuIndex == 1) {
                    currentSort = menuData2.get(pos).get("name").toString();
                    if(currentSort.equals("不限")){
                        if(Rc.getIns().queryConditionBean!=null){
                            Rc.getIns().queryConditionBean.setRules(null);
                            Rc.getIns().queryConditionBean.setSpecialConditions(null);
                        }

                    }else{
                        MemberchooseBean.ContentBean.MemberChooseBeansBean contentchooseBean=MemberchooseConditionShared.getIns().getQuicksortlist().get(pos);
                        if(contentchooseBean!=null){

                            Rc.getIns().quicksortcondition=contentchooseBean.getMemberFeild();
                            if(Rc.getIns().queryConditionBean!=null){
                                Rc.getIns().queryConditionBean.setRules(null);
                                Rc.getIns().queryConditionBean.setSpecialConditions(null);
                                List<String> value = new ArrayList<String>();
                                value.add(contentchooseBean.getMemberFeild());
                                Rc.getIns().queryConditionBean.setSpecialConditions(value);
                                Rc.getIns().sortcondition=contentchooseBean.getMemberFeild();
                            }else {
                                CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
                                queryConditionBean.setNumber(0);
                                queryConditionBean.setSize(20);
                                queryConditionBean.setDirection("DESC");
                                queryConditionBean.setProperty("createdDate");
                                Rc.getIns().queryConditionBean=queryConditionBean;
                                List<String> value = new ArrayList<String>();
                                value.add(contentchooseBean.getMemberFeild());
                                Rc.getIns().queryConditionBean.setSpecialConditions(value);
                                Rc.getIns().sortcondition=contentchooseBean.getMemberFeild();
                            }
                            MobclickAgent.onEvent(JiuyiMainApplication.getIns(), contentchooseBean.getMemberFeild());
                        }
                    }

                    getCustomerList(0);
                    mTvquicksort.setText(currentSort);
                    mTvquicksort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                    iv_quicksort.setImageResource(R.drawable.c_down_s);
                    Toast.makeText(mCallBack.getActivity(), currentSort, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_MULTI) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("转移")
                        .setTextColor(Res.getColor(null, "jiuyi_info2_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);
                SwipeMenuItem viewLine = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("|")
                        .setTextColor(Res.getColor(null, "color_line"))
                        .setWidth(Res.Dip2Pix(2))
                        .setHeight(height);
                swipeRightMenu.addMenuItem(viewLine);

                SwipeMenuItem addItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("释放")
                        .setTextColor(Res.getColor(null, "jiuyi_red_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem);
            }else if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_NODIRECT) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("认领")
                        .setTextColor(Res.getColor(null, "jiuyi_blue"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);
            }else if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_TRANSFER) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("转移")
                        .setTextColor(Res.getColor(null, "jiuyi_info2_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);
            }else if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_RELEASE) { // 是需要添加多个菜单的Item。
                SwipeMenuItem addItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("释放")
                        .setTextColor(Res.getColor(null, "jiuyi_red_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem);
            }
        }
    };


    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if(menuAdapter!=null){
                    MemberListBean.ContentBean  contentBean=menuAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, contentBean.getOrgName());
                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,contentBean.getId());
//                        if(contentBean.isClaim()){
//                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_claim);
//                            changePage(mBundle, Pub.Customer_claim,true);
//                        }else if(!contentBean.isRelease() && contentBean.isTransfer() ){
//                            mBundle.putString(JiuyiBundleKey.PARAM_INCHARGENAME, contentBean.getSalesmanName());
//                            mBundle.putLong(JiuyiBundleKey.PARAM_INCHARGEID, contentBean.getSalesmanId());
//                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_transfer);
//                            changePage(mBundle, Pub.Customer_transfer,true);
//                        }else if(contentBean.isRelease() && !contentBean.isTransfer() ){
//                            mBundle.putString(JiuyiBundleKey.PARAM_INCHARGENAME, contentBean.getSalesmanName());
//                            mBundle.putLong(JiuyiBundleKey.PARAM_INCHARGEID, contentBean.getSalesmanId());
//                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_release);
//                            changePage(mBundle, Pub.Customer_release,true);
//                        }else if(contentBean.isRelease() && contentBean.isTransfer()){
//                            mBundle.putString(JiuyiBundleKey.PARAM_INCHARGENAME, contentBean.getSalesmanName());
//                            mBundle.putLong(JiuyiBundleKey.PARAM_INCHARGEID, contentBean.getSalesmanId());
//                            if(menuPosition==2){
//                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_release);
//                                changePage(mBundle, Pub.Customer_release,true);
//                            }else if(menuPosition==0){
//                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_transfer);
//                                changePage(mBundle, Pub.Customer_transfer,true);
//                            }
//                        }

                    }
                }
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            finish();
        }
        return true;
    }
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(menuAdapter!=null && menuAdapter.mViewTypeBeanList.size()>0){
                customerids=new long[menuAdapter.mViewTypeBeanList.size()];
                for(int i=0;i<menuAdapter.mViewTypeBeanList.size();i++){
                    MemberListBean.ContentBean contentBean=menuAdapter.mViewTypeBeanList.get(i);
                    if(contentBean!=null){
                        customerids[i]=contentBean.getId();
                    }
                }
                mBundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerids);
                mBundle.putInt(JiuyiBundleKey.PARAM_CUSTOMERPOSITION, position);
                mBundle.putInt(JiuyiBundleKey.PARAM_CUSTOMERPAGEINDEX,pageIndex);
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, menuAdapter.mViewTypeBeanList.get(position).getId());
            }
            changePage(mBundle, Pub.Customer_main,true);
        }
    };

    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh){
            Rc.mBackfresh=false;
            getCustomerList(0);
        }

    }
}
