package com.jiuyi.activity.common.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.CustomerQueryConditionBean;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditText;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.toolbar.JiuyiNewToolBar;
import com.control.widget.toolbar.JiuyiNewToolBarCallBack;

import com.ibeiliao.badgenumberlibrary.BadgeNumberManager;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.layout.titlebar.JiuyiRootTitleBar;
import com.jiuyi.tools.CustomPopWindow;

import cn.jzvd.Jzvd;
import commonlyused.fragment.JiuyiNormalFragment;
import customer.Utils.KeyBoardUtils;
import customer.adapter.SearchMutiConditionGridViewAdapter;
import customer.entity.MemberchooseBean;
import customer.fragment.JiuyiCustomerListFragment;

import com.jiuyi.tools.ImageUtil;
import com.tencent.qcloud.sdk.Constant;
import com.umeng.analytics.MobclickAgent;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import customer.shared.MemberchooseConditionShared;
import com.control.widget.NoScrollGridView;

import messages.timchat.ui.JiuyiConversationFragment;
import mine.activity.JiuyiMineSignActivity;
import mine.fragment.JiuyiMineFragment;
import orders.fragment.JiuyiBusinessTripListFragment;


/**
 * ****************************************************************
 * 文件名称:JiuyiRootActivity.java
 * 作    者:Created by zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述:软件首页
 * ****************************************************************
 */

public class JiuyiRootActivity extends JiuyiActivityBase {
    private DrawerLayout mDrawerLayout;
    private JiuyiNewToolBar mNewToolBar;
    private JiuyiRootTitleBar pRootTitleBar;
    /**
     * 当前的jiuyiFragmentBase
     */
    private JiuyiFragmentBase mCurrFragment;
    //消息界面
    private JiuyiConversationFragment mconversationFragment;
    //客户界面
    private JiuyiCustomerListFragment mcustomrFragment;
    //订单界面
    private JiuyiBusinessTripListFragment mordersFragment;
    //常用界面
    private JiuyiNormalFragment mnormalFragment;
    //我的界面
    private JiuyiMineFragment mmineFragment;

    private int mLastPageType;//点击底部按钮、标题栏导航栏的最后的PageTyoe


    private LinearLayout drawer_content;

    /** 搜索按钮 */
    public View mRightView;//
    private CustomPopWindow mCustomPopWindow;
    private  TextView mtvregion,mtvreset,mtvconfirm;
    private LinearLayout mllsearch;
    public LinearLayout ll_searchcondition;
    int selectorPosition = -1;
    private MemberchooseBean mMemberchooseBean;
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;
    Boolean firstInit=true;
    TextView searchTitle;
    private JiuyiEditText etSearch;
    private int menuId=-1;
    private TextView main_tab_new_message;
    private boolean havePermission = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.openActivityDurationTrack(false);

        // 设置为U-APP场景
        MobclickAgent.setScenarioType(JiuyiRootActivity.this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                if(Jzvd.backPress()){
                    return true;
                }

                if(mDrawerLayout != null) {
                    if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    } else if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                        mDrawerLayout.closeDrawer(GravityCompat.END);
                        return true;
                    }
                }
                if (mBodyLayout != null) {
                    mBodyLayout.StartSystemQiutDialog();
                }
                return true;
            }
            default: {
                return super.onKeyUp(keyCode, event);
            }
        }
    }
    @Override
    public void createReq(boolean IsBg) {

    }

    @Override
    public void onResume() {
        boolean isFirstResume = bIsFirstOnResume;
        super.onResume();


        // 全局dialog,退出登录后弹出的dialog
        if(JiuyiDialogBase.mSimulateSystemAlertInfo != null){
            if(JiuyiDialogBase.mSimulateSystemAlertInfo.getAlertPageType() == mPageType){
                JiuyiDialogBase.mSimulateSystemAlertInfo.startDialog(this, this);
            }
            JiuyiDialogBase.mSimulateSystemAlertInfo = null;
        }
    }

    /**
     * 初始化ui
     */
    public void onInit(){
       if(mBundle!=null){
           menuId=mBundle.getInt(JiuyiBundleKey.PARAM_MENU);
       }
        firstInit=true;
        mDrawerLayout = (DrawerLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_root_layout"), null);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mBodyLayout = (JiuyiRelativeLayout)mDrawerLayout.findViewById(Res.getViewID(null, "jiuyi_relative_layout"));
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mDrawerLayout);
        searchTitle = (TextView) mDrawerLayout.findViewById(Res.getViewID(null, "jiuyi_searchtitle"));
        etSearch= (JiuyiEditText) mDrawerLayout.findViewById(R.id.tv_search);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyBoardUtils.hideSoftInput(etSearch);
                    if(mCurrFragment instanceof JiuyiBusinessTripListFragment){
                        ((JiuyiBusinessTripListFragment)mCurrFragment).setsSearch(etSearch.getText().toString().trim());
                        ((JiuyiBusinessTripListFragment)mCurrFragment).getInfoList(0);
                    }
                    return true;
                }
                return false;
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mCurrFragment instanceof JiuyiBusinessTripListFragment){
                    ((JiuyiBusinessTripListFragment)mCurrFragment).setsSearch(s.toString());
                }
            }
        });

        drawer_content = (LinearLayout) mDrawerLayout.findViewById(Res.getViewID(null, "drawer_content"));
        ll_searchcondition=(LinearLayout) mDrawerLayout.findViewById(Res.getViewID(null, "ll_searchcondition"));

        mRightView=mDrawerLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarmore"));
        //扫一扫
        if(mRightView != null){
            mRightView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    return;
//                    showPopMenu();
                }
            });
        }

        mllsearch=(LinearLayout) mDrawerLayout.findViewById(Res.getViewID(null, "jiuyi_searchcustomer_inlayout"));
        if(mllsearch!=null){
            mllsearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(mCurrFragment instanceof JiuyiDynamicFragment){
//                        changePage(null,Pub.Orders_search,true);
//                    }else {
//                        changePage(null,Pub.Customer_search,true);
//                    }
                    changePage(null,Pub.Customer_search,true);

                }
            });
        }
        main_tab_new_message= (TextView) mDrawerLayout.findViewById(Res.getViewID(null, "main_tab_new_message"));
        if(mBodyLayout.getTitleBar() instanceof JiuyiRootTitleBar)
            pRootTitleBar = (JiuyiRootTitleBar)mBodyLayout.getTitleBar();

        mNewToolBar = (JiuyiNewToolBar)mBodyLayout.findViewById(Res.getViewID(null, "bottom_navigation_bar"));

        if(menuId<=0){
            menuId=Pub.MENU_Customer;
        }

        mNewToolBar.setResParam(mCallActivityCallBack, "newcomtoolbar", menuId, new JiuyiNewToolBarCallBack(){
            @Override
            public boolean onClickToolBarItem(View view, int nCurAction, int forceSkinType) {

                return JiuyiRootActivity.this.onClickToolBarItem(view, nCurAction, forceSkinType);
            }
            @Override
            public boolean isSelectFirstAction() {
                return true;
            }

            /**
             * 点击按钮显示浮框
             * @param nButtonCount 按钮总个数
             * @param sButtonImageFlag 要显示浮框的标签
             * @param nButtonSelIndex 当前要显示浮框的位置索引
             */
            public void setPopWindowToolBarValue(int nButtonCount, String sButtonImageFlag, int nButtonSelIndex){

            }

            /**
             * 获取是否为画线状态
             * @return
             */
            @Override
            public boolean isDrawLine(){
                return false;
            }
        });
        havePermission = checkRecordPermission();
    }

    public void addSearchConditon(boolean reset) {
        mMemberchooseBean= MemberchooseConditionShared.getIns().getmMemberchooseBean();
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        List<String> specialConditions;
        specialConditions=new ArrayList<String>();
        queryConditionBean.setSpecialConditions(specialConditions);
        List<CustomerQueryConditionBean.RulesBean> rulesBeanList;
        rulesBeanList=new ArrayList<CustomerQueryConditionBean.RulesBean>();
        queryConditionBean.setRules(rulesBeanList);
        Rc.getIns().queryConditionBean=queryConditionBean;
        if(mMemberchooseBean!=null){
            List<MemberchooseBean.ContentBean> mContentBeanList=mMemberchooseBean.getContent();
            if(mContentBeanList!=null && mContentBeanList.size()>0){
                for(int i=0;i<mContentBeanList.size();i++){
                    final MemberchooseBean.ContentBean contentBean=mContentBeanList.get(i);
                    if(contentBean!=null){
                        if(contentBean.getTypeName().equals("手工筛选")){
                            List<MemberchooseBean.ContentBean.MemberChooseBeansBean> mMemberChooseBeansBeanList=contentBean.getMemberChooseBeans();
                            if(mMemberChooseBeansBeanList!=null &&mMemberChooseBeansBeanList.size()>0){
                                for (int m=0;m<mMemberChooseBeansBeanList.size();m++){
                                    final MemberchooseBean.ContentBean.MemberChooseBeansBean contentchooseBean=mMemberChooseBeansBeanList.get(m);
                                    if(contentchooseBean!=null){
                                        if(contentchooseBean.isHidden()){
                                            LinearLayout ll_commoncondition_item =(LinearLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "customer_commoncondition_item"), null);;
                                            ll_searchcondition.addView(ll_commoncondition_item);
                                            TextView tv_label=(TextView)ll_commoncondition_item.findViewById(R.id.tv_label);
                                            tv_label.setText(contentchooseBean.getName());
                                            final TextView tv_value=(TextView)ll_commoncondition_item.findViewById(R.id.tv_value);
                                            List<MemberchooseBean.ContentBean.MemberChooseBeansBean.ChooseBeansBean> chooseBeanslist=contentchooseBean.getChooseBeans();
                                            if(chooseBeanslist!=null && chooseBeanslist.size()>0){
                                                final String[] data= new String[chooseBeanslist.size()];;
                                                for(int k=0;k<chooseBeanslist.size();k++){
                                                    MemberchooseBean.ContentBean.MemberChooseBeansBean.ChooseBeansBean chooseBeans=chooseBeanslist.get(k);
                                                    data[k]=chooseBeans.getKey();
                                                }
                                                tv_value.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiRootActivity.this);
                                                        buidler.setTitle(contentchooseBean.getName()/*+"选择"*/);
                                                        buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                tv_value.setText(data[which].trim());
                                                                tv_value.setTextColor(Res.getColor(null, "jiuyi_blue"));
                                                                CustomerQueryConditionBean.RulesBean rulesBean=new CustomerQueryConditionBean.RulesBean();
                                                                rulesBean.setField(contentchooseBean.getMemberFeild());
//                                                                rulesBean.setOption("EQ");
                                                                List<String> valuelist = new ArrayList<String>();
                                                                MemberchooseBean.ContentBean.MemberChooseBeansBean.ChooseBeansBean chooseBeans=contentchooseBean.getChooseBeans().get(which);
                                                                String svalue =chooseBeans.getValue();
                                                                valuelist.add(svalue);
                                                                rulesBean.setOption(chooseBeans.getOption());
                                                                rulesBean.setValues(valuelist);
                                                                if(Rc.getIns().queryConditionBean.getRules()!=null && Rc.getIns().queryConditionBean.getRules().size()>0){
                                                                    for(int i=0;i<Rc.getIns().queryConditionBean.getRules().size();i++){
                                                                        CustomerQueryConditionBean.RulesBean selectrulesBean= Rc.getIns().queryConditionBean.getRules().get(i);
                                                                        if(selectrulesBean.getField().equals(contentchooseBean.getMemberFeild())){
                                                                            Rc.getIns().queryConditionBean.getRules().remove(i);
                                                                        }
                                                                    }
                                                                }
                                                                Rc.getIns().queryConditionBean.getRules().add(rulesBean);
                                                                if(contentchooseBean!=null && contentchooseBean.getMemberFeild()!=null && contentchooseBean.getName()!=null){
                                                                    if(!Rc.getIns().map_ekv.containsKey(contentchooseBean.getMemberFeild().replace(".",""))){
                                                                        Rc.getIns().map_ekv.put(contentchooseBean.getMemberFeild().replace(".",""),contentchooseBean.getName());
                                                                    }
                                                                }
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                        buidler.show();

                                                    }
                                                });
                                            }
                                        }else {
                                            LinearLayout ll_gridviewconditon_item =(LinearLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "customer_gridviewcondition_item"), null);;
                                            ll_searchcondition.addView(ll_gridviewconditon_item);
                                            TextView tv_label=(TextView)ll_gridviewconditon_item.findViewById(R.id.tv_label);
                                            tv_label.setText(contentchooseBean.getName());
                                            NoScrollGridView mgridview=(NoScrollGridView) ll_gridviewconditon_item.findViewById(Res.getViewID(null, "noScrollgridview"));
                                            mgridview.setId(R.id.mgridview);//设置 id
                                            if(mgridview!=null){
                                                final SearchMutiConditionGridViewAdapter mAdapter = new SearchMutiConditionGridViewAdapter(this, contentchooseBean,contentchooseBean.isMultiSelect());
                                                mgridview.setAdapter(mAdapter);
                                                //gridView的点击事件
                                                mgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                        //把点击的position传递到adapter里面去
                                                        mAdapter.changeState(position);
                                                        selectorPosition = position;
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                        }else  if(contentBean.getTypeName().equals("快速检索")&&!reset){
                            List<MemberchooseBean.ContentBean.MemberChooseBeansBean> mMemberChooseBeansBeanList=contentBean.getMemberChooseBeans();
                            if(mMemberChooseBeansBeanList!=null &&mMemberChooseBeansBeanList.size()>0){
                                String[] quicksort=new String[mMemberChooseBeansBeanList.size()];
                                for (int m=0;m<mMemberChooseBeansBeanList.size();m++){
                                    final MemberchooseBean.ContentBean.MemberChooseBeansBean contentchooseBean=mMemberChooseBeansBeanList.get(m);
                                    quicksort[m]=contentchooseBean.getName();
                                }
                                MemberchooseConditionShared.getIns().setQuicksortlist(mMemberChooseBeansBeanList);
                                MemberchooseConditionShared.getIns().setQuicksort(quicksort);
                            }

                        }else  if(contentBean.getTypeName().equals("排序")&&!reset){
                            List<MemberchooseBean.ContentBean.MemberChooseBeansBean> mMemberChooseBeansBeanList=contentBean.getMemberChooseBeans();
                            if(mMemberChooseBeansBeanList!=null &&mMemberChooseBeansBeanList.size()>0){
                                String[] quicksort=new String[mMemberChooseBeansBeanList.size()];
                                for (int m=0;m<mMemberChooseBeansBeanList.size();m++){
                                    final MemberchooseBean.ContentBean.MemberChooseBeansBean contentchooseBean=mMemberChooseBeansBeanList.get(m);
                                    quicksort[m]=contentchooseBean.getName();
                                }
                                MemberchooseConditionShared.getIns().setSortlist(mMemberChooseBeansBeanList);
                                MemberchooseConditionShared.getIns().setSort(quicksort);
                            }
                        }

                    }
                }

            }

            mtvreset=(TextView) mDrawerLayout.findViewById(Res.getViewID(null,"tv_reset"));
            mtvreset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll_searchcondition.removeAllViews();
                    addSearchConditon(true);
                    if(mcustomrFragment!=null){
                        mcustomrFragment.setManualUnSelect();
                    }
                    Rc.getIns().map_ekv.clear();
                    MobclickAgent.onEvent(JiuyiRootActivity.this, "Manual_reset");
                }
            });
            mtvconfirm=(TextView) mDrawerLayout.findViewById(Res.getViewID(null,"tv_confirm"));
            mtvconfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mDrawerLayout != null) {
                        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                            mDrawerLayout.closeDrawer(GravityCompat.START);
                        } else if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                            mDrawerLayout.closeDrawer(GravityCompat.END);
                        }
                    }
                    if(mcustomrFragment!=null){
                        if(!Func.IsStringEmpty(Rc.getIns().quicksortcondition)){
                            if(Rc.getIns().queryConditionBean.getSpecialConditions().contains(Rc.getIns().quicksortcondition)){
                                Rc.getIns().queryConditionBean.getSpecialConditions().remove(Rc.getIns().quicksortcondition) ;
                                Rc.getIns().quicksortcondition="";
                            }
                        }
                        if(Rc.getIns().map_ekv!=null &&Rc.getIns().map_ekv.size()>0){
                            MobclickAgent.onEvent(JiuyiRootActivity.this, "Manual_screening", Rc.getIns().map_ekv);
                        }
                        mcustomrFragment.setManualSelect();
                        mcustomrFragment.getCustomerList(0);

                    }


                }
            });
        }
//        设置当前页面的布局是LinearLayout
        setContentView(mDrawerLayout);
    }

    private void showPopMenu(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.jiuyi_pop_menu,null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .showAsDropDown(mRightView,20,20);
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
                        String[] list = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                        //检测权限
                        new JiuyiHiPermissionUtil(JiuyiRootActivity.this).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                            @Override
                            public void onGuarantee(String permission, int position) {
                                Intent intent = new Intent(getApplication(), JiuyiCaptureActivity.class);
                                startActivityForResult(intent, REQUEST_CODE);
                            }
                        });
                        break;


                }

            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
    }
    public void OpenDrawer(){
        mDrawerLayout.openDrawer(drawer_content);
    }
    /**
     * 底部工具栏点击的回调
     * @param view
     * @param nCurAction
     * @return
     */
    public boolean onClickToolBarItem(View view, int nCurAction, int forceSkinType) {
        forceSkinType=1;

        // 上方Tab的背景，文字颜色
        if (nCurAction == Pub.MENU_Mine) {
            pRootTitleBar.setVisibility(View.GONE);
        }else{
            pRootTitleBar.setVisibility(View.VISIBLE);
        }
        if(nCurAction == Pub.MENU_Customer &&firstInit){
            addSearchConditon(false);
            firstInit=false;
        }
        mTitle = mNewToolBar.getLabByPageType(nCurAction);
        setTitle();
        onTabSelected(mBundle, nCurAction, true, forceSkinType);
        if (pRootTitleBar != null) {
            pRootTitleBar.changeTitleLayout(nCurAction);
        }
        if(nCurAction == Pub.MENU_Trip){
            etSearch.setVisibility(View.VISIBLE);
            searchTitle.setVisibility(View.GONE);
        }else{
            etSearch.setVisibility(View.GONE);
            searchTitle.setVisibility(View.VISIBLE);
        }
        hideInput();

        return false;
    }
    /**
     * 根据nPageType打开相应的界面
     * @param nPageType
     * @param bIsNeedRefesh 强制使用此功能号下的界面
     * @param bIsNeedRefesh forceSkinType=0或者1，强制使用这个风格
     */
    public void onTabSelected(Bundle mBundle, int nPageType, boolean bIsNeedRefesh, int forceSkinType) {
        if(mLastPageType == nPageType){
            return;
        }
        mLastPageType = nPageType;

        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        //先detach旧的fragment，然后add 或 attach新的fragment。如果使用replace，子fragment中使用viewpager会有问题
        final FragmentTransaction transaction = fm.beginTransaction();
        if(mCurrFragment != null) {
            transaction.detach(mCurrFragment);
        }
        switch (nPageType) {
            case Pub.MENU_Message://消息
                if(mconversationFragment==null){
                    mconversationFragment = JiuyiConversationFragment.newInstance(nPageType);
                    transaction.add(Res.getViewID(null, "jiuyi_fragment_container"), mconversationFragment);
                    mconversationFragment.setCallBack(mCallActivityCallBack);
                }else{
                    transaction.attach(mconversationFragment);
                }
                mCurrFragment = mconversationFragment;
                break;
            case Pub.MENU_Mine://我的
                if(mmineFragment ==null){
                    mmineFragment = JiuyiMineFragment.newInstance(nPageType);
                    mmineFragment.setCallBack(mCallActivityCallBack);
                    transaction.add(Res.getViewID(null, "jiuyi_fragment_container"), mmineFragment);
                }else{
                    transaction.attach(mmineFragment);
                }
                mCurrFragment = mmineFragment;
                break;
            case Pub.MENU_Normal://常用
                if(mnormalFragment ==null){
                    mnormalFragment = JiuyiNormalFragment.newInstance(nPageType);
                    mnormalFragment.setCallBack(mCallActivityCallBack);
                    transaction.add(Res.getViewID(null, "jiuyi_fragment_container"), mnormalFragment);
                }else{
                    transaction.attach(mnormalFragment);
                }
                mCurrFragment = mnormalFragment;
                MobclickAgent.onEvent(JiuyiRootActivity.this, "normal");
                break;
            case Pub.MENU_Customer://客户
                if(mcustomrFragment ==null){
                    mcustomrFragment = JiuyiCustomerListFragment.newInstance(nPageType);
                    mcustomrFragment.setCallBack(mCallActivityCallBack);
                    transaction.add(Res.getViewID(null, "jiuyi_fragment_container"), mcustomrFragment);
                }else{
                    transaction.attach(mcustomrFragment);
                }
                mCurrFragment = mcustomrFragment;
                MobclickAgent.onEvent(JiuyiRootActivity.this, "client");
                break;
            case Pub.MENU_Trip://动态
                if(mordersFragment==null){
                    mordersFragment= JiuyiBusinessTripListFragment.newInstance(nPageType);
                    mordersFragment.setCallBack(mCallActivityCallBack);
                    transaction.add(Res.getViewID(null, "jiuyi_fragment_container"), mordersFragment);

                }else{
                    transaction.attach(mordersFragment);
                }
                mCurrFragment = mordersFragment;
                MobclickAgent.onEvent(JiuyiRootActivity.this, "order");
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    public void setTitle(){
        super.setTitle(mTitle);
    }




    /**
     * 更改标题头显示
     */
    public void changeTitleLayout(){
    }





    //使用tablayout，需要appcompat的主题
    @Override
    public void setActivityTheme() {
        setTheme(Res.getStyleID(getApplicationContext(), "jiuyi_ThemeCompat.White"));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    if(!Func.IsStringEmpty(result)){
                        String urladd=Res.getString(null, "jiuyibatchnum");
                        String url="";
                        if(!Func.IsStringEmpty(urladd)){
                            url= Constant.BaseH5Url+urladd+"?code="+result+"&token="+ Rc.id_tokenparam;
                        }
                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"批次查询");
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                    }

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(JiuyiRootActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(JiuyiRootActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(JiuyiRootActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 设置未读tab显示
     */
    public void setMsgUnread(long noUnread){
        if(noUnread == 0){
            main_tab_new_message.setVisibility(View.GONE);
        }else {
            main_tab_new_message.setVisibility(View.VISIBLE);
            String unReadCount="";
            if(noUnread>99){
                unReadCount="99+";
            }else{
                unReadCount=noUnread+"";
            }

            if (noUnread < 10){
                main_tab_new_message.setBackground(JiuyiRootActivity.this.getResources().getDrawable(R.drawable.point1));
            }else{
                main_tab_new_message.setBackground(JiuyiRootActivity.this.getResources().getDrawable(R.drawable.point2));

            }
            main_tab_new_message.setText(unReadCount);
        }
        if(noUnread>=0){
            BadgeNumberManager.from(JiuyiMainApplication.getIns()).setBadgeNumber(Integer.parseInt(noUnread+""));
        }
    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        Jzvd.releaseAllVideos();
//    }
private boolean checkRecordPermission() {
    if (Build.VERSION.SDK_INT >= 23) {
        List<String> permissions = new ArrayList<>();
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(JiuyiRootActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (permissions.size() != 0) {
            ActivityCompat.requestPermissions(JiuyiRootActivity.this, permissions.toArray(new String[0]), 0);
            return false;
        }
    }
    return true;
}
}
