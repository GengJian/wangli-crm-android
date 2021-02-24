package mine.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.tools.jiuyiCustomerViewPager;

import java.util.ArrayList;
import java.util.List;

import mine.fragment.JiuyiMineCollectionAllFragment;
import mine.fragment.JiuyiMineCollectionCustomerFragment;
import mine.fragment.JiuyiMineCollectionNoticeFragment;
import mine.fragment.JiuyiMineCollectionOrderFragment;
import mine.fragment.JiuyiMineCollectionProductFragment;
import mine.fragment.JiuyiMineCollectionTaskFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineCollectionActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的收藏信息
 *****************************************************************
 */
public class JiuyiMineCollectionActivity extends JiuyiActivityBase {
    protected TabLayout mTablayout;//标题栏tablayout
    protected jiuyiCustomerViewPager pViewPageBar;//下方viewpager
    private CollectionGroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_minecollection_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        setContentView(mBodyLayout);
        mTablayout = (TabLayout) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_risk_tablayout"));
        pViewPageBar = (jiuyiCustomerViewPager) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_customer_riskviewpager"));
        //绑定数据
        onIinitViewPage();


        setTitle();

    }

    /**
     * 初始化viewpager，更新数据源
     */
    protected void onIinitViewPage() {
        //创建子fragment
        ArrayList<Fragment> fragments = new ArrayList<>();//子fragment
        //读取配置信息
        String strData = Res.getString(null, "jiuyiminecollectiontabbar");
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
                case Pub.Mine_CollectionAll:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiMineCollectionAllFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiMineCollectionAllFragment.newInstance(nPageType, null);
                    }
                    break;
                case Pub.Mine_CollectionProduct:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiMineCollectionProductFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiMineCollectionProductFragment.newInstance(nPageType, null);
                    }
                    break;
                case Pub.Mine_CollectionCustomer:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiMineCollectionCustomerFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiMineCollectionCustomerFragment.newInstance(nPageType, null);
                    }
                    break;
                case Pub.Mine_CollectionOrder:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiMineCollectionOrderFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiMineCollectionOrderFragment.newInstance(nPageType, null);
                    }
                    break;
                case Pub.Mine_CollectionNotice:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiMineCollectionNoticeFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiMineCollectionNoticeFragment.newInstance(nPageType, null);
                    }
                    break;
                case Pub.Mine_CollectionTask:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiMineCollectionTaskFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiMineCollectionTaskFragment.newInstance(nPageType, null);
                    }
                    break;


            }
            if (itemFragment != null) {
                itemFragment.setCallBack(mCallActivityCallBack);
                fragments.add(itemFragment);
            }
        }
        //设置数据源
        mFragmentPagerAdapter = new CollectionGroupFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pViewPageBar.setAdapter(mFragmentPagerAdapter);
        //绑定 ViewPager & Tablayout
        mTablayout.setupWithViewPager(pViewPageBar);

    }
    public void setTitle(){
        mTitle="我的收藏";
        super.setTitle(mTitle);
    }
    /**
     * 处理NavigationBar显示隐藏
     */
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange){
        Fragment mCurrFragment = mFragmentPagerAdapter.getCurrItem();
        if(mCurrFragment instanceof JiuyiFragmentBase)
            ((JiuyiFragmentBase)mCurrFragment).dealNavigationBarVisiableChange(nCurrBodyHeight, nDeltaChange);
    }

    public class CollectionGroupFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments = null;

        public CollectionGroupFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
            super(fragmentManager);
            this.fragments = fragments;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        public Fragment getCurrItem() {
            return fragments.get(mTablayout.getSelectedTabPosition());
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