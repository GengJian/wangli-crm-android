package customer.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import java.util.ArrayList;
import java.util.List;

import customer.fragment.JiuyiCustomerBaseInfoFragment;
import customer.fragment.JiuyiCustomerBusinessFragment;
import customer.fragment.JiuyiCustomerCostPandectFragment;
import customer.fragment.JiuyiCustomerPersonnelOrganizationFragment;
import customer.fragment.JiuyiCustomerMarketFragment;
import customer.fragment.JiuyiCustomerPlanFragment;
import customer.fragment.JiuyiCustomerServiceFragment;
import customer.fragment.JiuyiCustomerStoreFragment;
import customer.fragment.JiuyiCustomerSystemInfoFragment;
import customer.fragment.JiuyiCustomerVisitReceiptFragment;

import com.jiuyi.tools.CustomPopWindow;
import com.jiuyi.tools.jiuyiCustomerViewPager;
import com.control.widget.magicindicator.MagicIndicator;
import com.control.widget.magicindicator.ViewPagerHelper;
import com.control.widget.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.control.widget.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.control.widget.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.control.widget.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.control.widget.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.control.widget.magicindicator.titles.ScaleTransitionPagerTitleView;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerDetailActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户360详情界面
 *****************************************************************
 */
public class JiuyiCustomerDetailActivity extends JiuyiActivityBase {
    protected jiuyiCustomerViewPager pViewPageBar;//下方viewpager
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected List<String> mTitleList = new ArrayList<>();//标题栏名称列表
    protected CustomerGroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    MagicIndicator magicIndicator;
    CommonNavigator commonNavigator;
    /** 搜索按钮 */
    public ImageView mRightView;//
    private CustomPopWindow mCustomPopWindow;
    public int mProductPageType=0;
    private long Customerid=-1;
    private String CustomerName="";
    private String msOwetotal,msDuetotal,msLevel;
    Bundle mBundleCustomerDetail ;

    /**
     * 初始化UI
     */
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        msOwetotal=mBundle.getString(JiuyiBundleKey.PARAM_OWETOTAL);
        msDuetotal=mBundle.getString(JiuyiBundleKey.PARAM_DUETOTAL);
        msLevel=mBundle.getString(JiuyiBundleKey.PARAM_LEVELNAME);
        CustomerName=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        mBundleCustomerDetail = new Bundle();
        mBundleCustomerDetail.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
        mBundleCustomerDetail.putString(JiuyiBundleKey.PARAM_OWETOTAL, msOwetotal);
        mBundleCustomerDetail.putString(JiuyiBundleKey.PARAM_DUETOTAL, msDuetotal);
        mBundleCustomerDetail.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, CustomerName);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customerdetail_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);

        mRightView=(ImageView) mBodyLayout.findViewById(Res.getViewID(this, "jiuyi_titlebar_more"));
        if (mRightView != null) {
            mRightView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    showPopMenu();
                }
            });
        }

        pViewPageBar = (jiuyiCustomerViewPager) mBodyLayout.findViewById(Res.getViewID(this, "jiuyi_customer_childviewpager"));
        //绑定数据
        onIinitViewPage();
        initMagicIndicator1();
        //根据action设置当前选中的item
        setCurrentItemByAction(mPageType);
        setTitle();
    }
    private void showPopMenu(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.jiuyi_customer_pop_menu,null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .showAsDropDown(mRightView,50,20);


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
                String showContent = "";
                switch (v.getId()){
                    case R.id.menu1:
                        setCurrentItemByAction(Pub.Customer_baseinfo);
                        break;
                    case R.id.menu2:
                        setCurrentItemByAction(Pub.Customer_PersonnelOrganization);
                        break;
                    case R.id.menu3:
                        setCurrentItemByAction(Pub.Customer_FinancialRisk);
                        break;
                    case R.id.menu4:
                        setCurrentItemByAction(Pub.Customer_Purchase);
                        break;
//                    case R.id.menu5:
//                        setCurrentItemByAction(Pub.Customer_ProductionStatus);
//                        break;
//                    case R.id.menu6:
//                        setCurrentItemByAction(Pub.Customer_Sales);
//                        break;
//                    case R.id.menu7:
//                        setCurrentItemByAction(Pub.Customer_Research);
//                        break;
//                    case R.id.menu8:
//                        setCurrentItemByAction(Pub.Customer_Visit);
//                        break;
//                    case R.id.menu9:
//                        setCurrentItemByAction(Pub.Customer_Business);
//                        break;
//                    case R.id.menu10:
//                        setCurrentItemByAction(Pub.Customer_Contract);
//                        break;
//                    case R.id.menu11:
//                        setCurrentItemByAction(Pub.Customer_Service);
//                        break;
//                    case R.id.menu12:
//                        setCurrentItemByAction(Pub.Customer_Cost);
//                        break;
                    case R.id.menu13:
                        setCurrentItemByAction(Pub.Customer_SystemInfo);
                        break;

                }
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
        contentView.findViewById(R.id.menu4).setOnClickListener(listener);
//        contentView.findViewById(R.id.menu5).setOnClickListener(listener);
//        contentView.findViewById(R.id.menu6).setOnClickListener(listener);
//        contentView.findViewById(R.id.menu7).setOnClickListener(listener);
//        contentView.findViewById(R.id.menu8).setOnClickListener(listener);
//        contentView.findViewById(R.id.menu9).setOnClickListener(listener);
//        contentView.findViewById(R.id.menu10).setOnClickListener(listener);
//        contentView.findViewById(R.id.menu11).setOnClickListener(listener);
//        contentView.findViewById(R.id.menu12).setOnClickListener(listener);
        contentView.findViewById(R.id.menu13).setOnClickListener(listener);
    }

    /**
     * 初始化viewpager，更新数据源
     */
    protected void onIinitViewPage() {
        //创建子fragment
        ArrayList<Fragment> fragments = new ArrayList<>();//子fragment
        //读取配置信息
        String strData = Res.getString(null, "jiuyicustomerdetailtabbar");
        String[][] strMenuData = Func.SplitStr2Array(strData);
        if (strMenuData == null || strMenuData.length < 1) {
            return;
        }
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
                case Pub.Customer_baseinfo:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerBaseInfoFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleCustomerDetail.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_baseinfo);
                        mBundleCustomerDetail.putInt(JiuyiBundleKey.PARAM_NEXTPAGERTYPE,Pub.Customer_baseinfo);
                        itemFragment = JiuyiCustomerBaseInfoFragment.newInstance(nPageType, mBundleCustomerDetail);
                    }
                    break;
                case Pub.Customer_PersonnelOrganization:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerPersonnelOrganizationFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleCustomerDetail.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_PersonnelOrganization);
                        itemFragment = JiuyiCustomerPersonnelOrganizationFragment.newInstance(nPageType, mBundleCustomerDetail);
                    }
                    break;
                case Pub.Customer_FinancialRisk:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerStoreFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleCustomerDetail.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_FinancialRisk);
                        mBundleCustomerDetail.putInt(JiuyiBundleKey.PARAM_NEXTPAGERTYPE,Pub.Customer_FinancialRisk);
                        itemFragment = JiuyiCustomerStoreFragment.newInstance(nPageType, mBundleCustomerDetail);
                    }
                    break;
                case Pub.Customer_Purchase:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerPlanFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Purchase);
                        mBundle.putInt(JiuyiBundleKey.PARAM_NEXTPAGERTYPE,Pub.Customer_Purchase);
                        itemFragment = JiuyiCustomerPlanFragment.newInstance(nPageType, mBundle);
                    }
                    break;
                case Pub.Customer_ProductionStatus:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerMarketFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_ProductionStatus);
                        mBundle.putInt(JiuyiBundleKey.PARAM_NEXTPAGERTYPE,Pub.Customer_ProductionStatus);
                        itemFragment = JiuyiCustomerMarketFragment.newInstance(nPageType, mBundle);
                    }
                    break;
                case Pub.Customer_Sales:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerMarketFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Sales);
                        mBundle.putInt(JiuyiBundleKey.PARAM_NEXTPAGERTYPE,Pub.Customer_Sales);
                        itemFragment = JiuyiCustomerMarketFragment.newInstance(nPageType, mBundle);
                    }
                    break;
                case Pub.Customer_Research:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerMarketFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Research);
                        mBundle.putInt(JiuyiBundleKey.PARAM_NEXTPAGERTYPE,Pub.Customer_Research);
                        itemFragment = JiuyiCustomerMarketFragment.newInstance(nPageType, mBundle);
                    }
                    break;
                case Pub.Customer_Visit:
                    if(nPageType == mPageType){
//                        itemFragment = JiuyiCustomerVisitListFragment.newInstance(nPageType, mBundle);
                        itemFragment = JiuyiCustomerVisitReceiptFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleCustomerDetail.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Visit);
                        itemFragment = JiuyiCustomerVisitReceiptFragment.newInstance(nPageType, mBundleCustomerDetail);
//                        itemFragment = JiuyiCustomerVisitListFragment.newInstance(nPageType, mBundleCustomerDetail);
                    }
                    break;

                case Pub.Customer_Business:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerBusinessFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleCustomerDetail.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Business);
                        itemFragment = JiuyiCustomerBusinessFragment.newInstance(nPageType, mBundleCustomerDetail);
                    }
                    break;
                case Pub.Customer_Contract:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerBusinessFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Contract);
                        itemFragment = JiuyiCustomerBusinessFragment.newInstance(nPageType, mBundle);
                    }
                    break;
                case Pub.Customer_Service:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerServiceFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiCustomerServiceFragment.newInstance(nPageType, mBundle);
                    }
                    break;
                case Pub.Customer_Cost:
                    itemFragment = JiuyiCustomerCostPandectFragment.newInstance(nPageType, mBundle);
                    break;
                case Pub.Customer_SystemInfo:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerSystemInfoFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_SystemInfo);
                        mBundle.putInt(JiuyiBundleKey.PARAM_NEXTPAGERTYPE,Pub.Customer_SystemInfo);
                        itemFragment = JiuyiCustomerSystemInfoFragment.newInstance(nPageType, mBundle);
                    }
                    break;
                case Pub.Customer_main:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerSystemInfoFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_Cost);
                        mBundle.putInt(JiuyiBundleKey.PARAM_NEXTPAGERTYPE,Pub.Customer_Cost);
                        itemFragment = JiuyiCustomerSystemInfoFragment.newInstance(nPageType, mBundle);
                    }
                    break;
            }
            if (itemFragment != null) {
                itemFragment.setCallBack(mCallActivityCallBack);
                if (!itemFragment.isAdded() ) {
                    fragments.add(itemFragment);
                }
            }
        }

        //设置数据源
        mFragmentPagerAdapter = new CustomerGroupFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pViewPageBar.setAdapter(mFragmentPagerAdapter);

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
    public void setTitle(){
        mTitle = "";
        setTitle(mTitle);
    }
    private void initMagicIndicator1() {

         magicIndicator = (MagicIndicator) mBodyLayout.findViewById(Res.getViewID(this, "magic_indicator1"));
         commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleList.get(index));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Res.getColor(JiuyiCustomerDetailActivity.this, "tzt_v23_customer_title_color"));
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pViewPageBar.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, pViewPageBar);
    }
    /**
     * 处理NavigationBar显示隐藏
     */
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange){
        Fragment mCurrFragment = mFragmentPagerAdapter.getCurrItem();
        if(mCurrFragment instanceof JiuyiFragmentBase)
            ((JiuyiFragmentBase)mCurrFragment).dealNavigationBarVisiableChange(nCurrBodyHeight, nDeltaChange);
    }

    /**
     * 动态的非固定的fragment使用 FragmentStatePagerAdapter
     */
    public class CustomerGroupFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments = null;

        public CustomerGroupFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
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

}
