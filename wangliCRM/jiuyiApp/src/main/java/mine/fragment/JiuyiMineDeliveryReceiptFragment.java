package mine.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.widget.JiuyiFragmentBase;
import com.jiuyi.tools.jiuyiCustomerViewPager;

import java.util.ArrayList;
import java.util.List;

import mine.bean.MineDeliveryPlanBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineDeliveryReceiptFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的收款计划
 *****************************************************************
 */
public class JiuyiMineDeliveryReceiptFragment extends JiuyiFragmentBase {
    protected TabLayout mTablayout;//标题栏tablayout
    protected jiuyiCustomerViewPager pViewPageBar;//下方viewpager
    private RiskGroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表
    private int nextPageType=-1;
    MineDeliveryPlanBean.ContentBean contentBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_minedeliveryreceiptfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    public void onInit() {
        contentBean= mBundle.getParcelable(JiuyiBundleKey.PARAM_PLANEAN);
        if(contentBean!=null){
            mBundle.putParcelable(JiuyiBundleKey.PARAM_PLANEAN, contentBean);
        }
        nextPageType=mBundle.getInt(JiuyiBundleKey.PARAM_PLANNEXTPAGERTYPE);
        mTablayout = (TabLayout) mRootView.findViewById(Res.getViewID(null, "jiuyi_plan_tablayout"));
        pViewPageBar = (jiuyiCustomerViewPager) mRootView.findViewById(Res.getViewID(null, "jiuyi_customer_planviewpager"));
        //绑定数据
        onIinitViewPage();


    }

    /**
     * 初始化viewpager，更新数据源
     */
    protected void onIinitViewPage() {
        //创建子fragment
        ArrayList<Fragment> fragments = new ArrayList<>();//子fragment
        //读取配置信息
        String strData = Res.getString(null, "jiuyiminedeliveryreceipttabbar");
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
            if(i==0 && nextPageType>0){
                mTitle="发货计划明细";
            }
            mTitleList.add(mTitle);
            mPageTypeList.add(nPageType);
        }
        for (int nPageType : mPageTypeList) {
            JiuyiFragmentBase itemFragment = null;
            switch (nPageType) {
                case Pub.Mine_deliveryPlanTotal:
                    if(nextPageType>0){
                        itemFragment = JiuyiMineDeliveryPlanFragment.newInstance(nPageType, mBundle);
                    }else{
                        if(nPageType == mPageType){
                            itemFragment = JiuyiMineDeliveryPlanTotalFragment.newInstance(nPageType, mBundle);
                        }else {
                            itemFragment = JiuyiMineDeliveryPlanTotalFragment.newInstance(nPageType, null);
                        }
                    }

                    break;
                case Pub.Mine_ReceiptPlan:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiMineReceiptPlanFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiMineReceiptPlanFragment.newInstance(nPageType, null);
                    }
                    break;

            }
            if (itemFragment != null) {
                itemFragment.setCallBack(mCallBack);
                fragments.add(itemFragment);
            }
        }



        //设置数据源
        mFragmentPagerAdapter = new RiskGroupFragmentPagerAdapter(getChildFragmentManager(), fragments);
        pViewPageBar.setAdapter(mFragmentPagerAdapter);
        //绑定 ViewPager & Tablayout
        mTablayout.setupWithViewPager(pViewPageBar);

        //根据action设置当前选中的item
        setCurrentItemByAction(mPageType);
    }
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

    /**
     * 获取当前Fragment的界面号
     *
     * @return
     */
    public int getPageType() {
        try {
            return ((JiuyiFragmentBase)mFragmentPagerAdapter.getCurrItem()).getPageType();
        } catch (Exception e) {
            return super.getPageType();
        }
    }

    /**
     * 获取当前Fragment的界面字符串
     *
     * @return
     */
    public String getSimpleName() {
        return "";
    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineDeliveryReceiptFragment newInstance(int nPageType) {
        JiuyiMineDeliveryReceiptFragment f = new JiuyiMineDeliveryReceiptFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineDeliveryReceiptFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineDeliveryReceiptFragment f = new JiuyiMineDeliveryReceiptFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 处理NavigationBar显示隐藏
     */
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange){
        Fragment mCurrFragment = mFragmentPagerAdapter.getCurrItem();
        if(mCurrFragment instanceof JiuyiFragmentBase)
            ((JiuyiFragmentBase)mCurrFragment).dealNavigationBarVisiableChange(nCurrBodyHeight, nDeltaChange);
    }
    public class RiskGroupFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments = null;

        public RiskGroupFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
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
