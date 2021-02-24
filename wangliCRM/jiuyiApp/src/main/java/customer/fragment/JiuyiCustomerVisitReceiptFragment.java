package customer.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiCustomerViewPager;

import java.util.ArrayList;
import java.util.List;

import mine.fragment.JiuyiMineScheduleListFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerVisitReceiptFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户360交易跟踪
 *****************************************************************
 */
public class JiuyiCustomerVisitReceiptFragment extends JiuyiFragmentBase implements View.OnClickListener {
    protected TabLayout mTablayout;//标题栏tablayout
    protected jiuyiCustomerViewPager pViewPageBar;//下方viewpager
    private GroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表

    private long Customerid=-1;
    private String Customername="",mRisktype=""/*,mRisktypeName=""*/;
    Bundle mBundleRisk ;
    private TextView tv_no_authoritytext;
    private ImageView iv_no_authority;
    private View view_1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerriskfragment_layout"), null);
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
        mBundleRisk = new Bundle();
        mBundleRisk.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
        mBundleRisk.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, Customername);
        mBundleRisk.putString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE, mRisktype);
        mTablayout = (TabLayout) mRootView.findViewById(R.id.jiuyi_risk_tablayout);
        pViewPageBar = (jiuyiCustomerViewPager) mRootView.findViewById(R.id.jiuyi_customer_riskviewpager);
//        pViewPageBar.setAllowScroll(false);
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        view_1=(View)mRootView.findViewById(Res.getViewID(getContext(), "view_1"));
        //绑定数据
        onIinitViewPage();
       /* if(!MemberAuthorityBean.getIns().transactionTracking){
            tv_no_authoritytext.setVisibility(View.VISIBLE);
            iv_no_authority.setVisibility(View.VISIBLE);
            mTablayout.setVisibility(View.GONE);
            pViewPageBar.setVisibility(View.GONE);
            view_1.setVisibility(View.GONE);
        }else {
            initMenuData();
            initPopMenu();
            //绑定数据
            onIinitViewPage();
        }*/
    }



    /**
     * 初始化viewpager，更新数据源
     */
    protected void onIinitViewPage() {
        //创建子fragment
        ArrayList<Fragment> fragments = new ArrayList<>();//子fragment
        //读取配置信息
        String strData="";
        if(mPageType==Pub.Mine_Date){
            strData = Res.getString(null, "jiuyiminevisitreceipttabbar");
        }else{
            strData = Res.getString(null, "jiuyicustomervisitreceipttabbar");
        }

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
                case Pub.Customer_VisitInfo:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerVisitListFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleRisk.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_VisitInfo);
                        itemFragment = JiuyiCustomerVisitListFragment.newInstance(nPageType, mBundleRisk);
                    }
                    break;
                case Pub.Customer_ReceiptInfo:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerVisitListFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleRisk.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_ReceiptInfo);
                        itemFragment = JiuyiCustomerVisitListFragment.newInstance(nPageType, mBundleRisk);
                    }
                    break;
                case Pub.Mine_Date_Visit:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiMineScheduleListFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleRisk.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Mine_Date_Visit);
                        itemFragment = JiuyiMineScheduleListFragment.newInstance(nPageType, mBundleRisk);
                    }
                    break;
                case Pub.Mine_Date_Receipt:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiMineScheduleListFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleRisk.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Mine_Date_Receipt);
                        itemFragment = JiuyiMineScheduleListFragment.newInstance(nPageType, mBundleRisk);
                    }
                    break;

            }
            if (itemFragment != null) {
                itemFragment.setCallBack(mCallBack);
                fragments.add(itemFragment);
            }
        }

        //设置数据源
        mFragmentPagerAdapter = new GroupFragmentPagerAdapter(getChildFragmentManager(), fragments);
        pViewPageBar.setAdapter(mFragmentPagerAdapter);
        //绑定 ViewPager & Tablayout
        mTablayout.setupWithViewPager(pViewPageBar);

     //根据action设置当前选中的item
        setCurrentItemByAction(mPageType);
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
        TabLayout.Tab tab = mTablayout.getTabAt(0);
        if (tab.getCustomView() != null) {
            View tabView = (View) tab.getCustomView().getParent();
            TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
            txt_title2.setTextColor(Res.getColor(null, "jiuyi_blue"));
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
    public static JiuyiCustomerVisitReceiptFragment newInstance(int nPageType) {
        JiuyiCustomerVisitReceiptFragment f = new JiuyiCustomerVisitReceiptFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerVisitReceiptFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerVisitReceiptFragment f = new JiuyiCustomerVisitReceiptFragment();
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
    public void onClick(View v) {

    }

    public class GroupFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments = null;

        public GroupFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
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
