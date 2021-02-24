package customer.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.magicindicator.MagicIndicator;
import com.control.widget.magicindicator.ViewPagerHelper;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiCustomerVisitViewPager;

import java.util.ArrayList;
import java.util.List;

import customer.view.ScaleCircleNavigator;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerCostPandectFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 费用分析
 *****************************************************************
 */
public class JiuyiCustomerCostPandectFragment extends JiuyiFragmentBase {

    private long Customerid=-1;
    private String Customername="";
    private String mRisktype="";
    protected TabLayout mTablayout;//标题栏tablayout
    protected jiuyiCustomerVisitViewPager pViewPageBar;//下方viewpager
    private GroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表
    float[] ydata = new float[7];

    private List<Fragment> frags;
    private jiuyiCustomerVisitViewPager pager;
    private MagicIndicator indicator;
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
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customercostpandectfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        Customerid = mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername = mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        mRisktype = mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE);
        mTablayout = (TabLayout) mRootView.findViewById(R.id.jiuyi_risk_tablayout);
        pViewPageBar = (jiuyiCustomerVisitViewPager) mRootView.findViewById(R.id.jiuyi_customer_riskviewpager);
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));

        app_bar=(AppBarLayout)mRootView.findViewById(Res.getViewID(getContext(), "app_bar"));
        nsc_content=(NestedScrollView)mRootView.findViewById(Res.getViewID(getContext(), "nsc_content"));
        onIinitViewPage();
        initData();
        initPager();
        initIndicator();
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicator.onPageScrollStateChanged(state);
            }
        });
        ViewPagerHelper.bind(indicator, pager);

        tv_no_authoritytext.setVisibility(View.VISIBLE);
//        iv_no_authority.setVisibility(View.VISIBLE);
        app_bar.setVisibility(View.GONE);
        nsc_content.setVisibility(View.GONE);

    }

    private void initData() {
        frags=new ArrayList<>();
        frags.add(JiuyiCostTypeFragment.newInstance(R.layout.jiuyi_customerfragment_costtype));
        frags.add(JiuyiIronTriFragment.newInstance(R.layout.jiuyi_customerfragment_irontri));


    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerCostPandectFragment newInstance(int nPageType) {
        JiuyiCustomerCostPandectFragment f = new JiuyiCustomerCostPandectFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerCostPandectFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerCostPandectFragment f = new JiuyiCustomerCostPandectFragment();
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

    /**
     * 初始化viewpager，更新数据源
     */
    protected void onIinitViewPage() {
        //创建子fragment
        ArrayList<Fragment> fragments = new ArrayList<>();//子fragment
        //读取配置信息
        String sValue="";
        if(mPageType==Pub.Customer_Cost){
            sValue="jiuyicustomercoststatustabbar";
        }
        String strData = Res.getString(null, sValue);
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
                case Pub.Customer_CostAll:
                case Pub.Customer_CostOaApping:
                case Pub.Customer_CostOaApped:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCostListFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiCostListFragment.newInstance(nPageType, null);
                    }
                    break;

            }
            if (itemFragment != null) {
                itemFragment.setCallBack(mCallBack);
                if (!itemFragment.isAdded() ) {
                    fragments.add(itemFragment);
                }

            }
        }

        //设置数据源
        mFragmentPagerAdapter = new GroupFragmentPagerAdapter(getChildFragmentManager(), fragments);
        pViewPageBar.setAdapter(mFragmentPagerAdapter);
        //绑定 ViewPager & Tablayout
        mTablayout.setupWithViewPager(pViewPageBar);
        //设置自定义视图
        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            if(tab!=null && mFragmentPagerAdapter!=null &&(mPageType!=Pub.Customer_CostType && mPageType!=Pub.Customer_IronTriCost)){
                tab.setCustomView(mFragmentPagerAdapter.getTabView(i));
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag(i);
                    tabView.setOnClickListener(mTabOnClickListener);
                }

            }

        }


        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==0){
                    setUnSelectTab(1);
                    setUnSelectTab(2);
                }else if(tab.getPosition()==1){
                    setUnSelectTab(0);
                    setUnSelectTab(2);
                }else if(tab.getPosition()==2){
                    setUnSelectTab(0);
                    setUnSelectTab(1);
                }
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
                    txt_title2.setTextColor(Res.getColor(null, "jiuyi_blue"));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


//        //根据action设置当前选中的item
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
        setSelectTab(0);
    }

    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    public void setUnSelectTab(int index){
        TabLayout.Tab tab0 = mTablayout.getTabAt(index);
        if (tab0!=null && tab0.getCustomView() != null) {
            View tabView = (View) tab0.getCustomView().getParent();
            TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
            txt_title2.setTextColor(Res.getColor(null, "jiuyi_info_color"));
//            ImageView img2 = (ImageView) tabView.findViewById(R.id.img_title);
//            img2.setImageResource(R.drawable.client_down_n);
//            if(index==1){
//                img2.setVisibility(View.GONE);
//            }
        }

    }
    public void setSelectTab(int index){
        TabLayout.Tab tab = mTablayout.getTabAt(index);
        if (tab!=null && tab.getCustomView() != null) {
            View tabView = (View) tab.getCustomView().getParent();
            TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
            txt_title2.setTextColor(Res.getColor(null, "jiuyi_blue"));
        }
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

        public View getTabView(int position){
            View view = LayoutInflater.from(mCallBack.getActivity()).inflate(R.layout.customerrisktabitem, null);
            TextView tv= (TextView) view.findViewById(R.id.txt_title);
            if(tv!=null){
                tv.setText(mTitleList.get(position));
                tv.setTextColor(Res.getColor(null, "jiuyi_info_color"));
            }

            ImageView img = (ImageView) view.findViewById(R.id.img_title);
            if(img!=null){
                img.setVisibility(View.GONE);
            }

            return view;
        }
    }

    private void initPager() {
        pager=(jiuyiCustomerVisitViewPager) mRootView.findViewById(R.id.vp_top);
        FragmentAdapter adapter=new FragmentAdapter(getChildFragmentManager(), frags);
        pager.setAdapter(adapter);
    }
    private void initIndicator() {
        indicator=(MagicIndicator) mRootView.findViewById(R.id.bottom_indicator);
        ScaleCircleNavigator navigator=new ScaleCircleNavigator(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity());
        navigator.setCircleCount(pager.getAdapter().getCount());
        navigator.setNormalCircleColor(Color.DKGRAY);
        navigator.setSelectedCircleColor(R.color.jiuyi_cost_color);
        navigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                pager.setCurrentItem(index);
            }
        });
        indicator.setNavigator(navigator);
    }

    public class FragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> datas;

        public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            datas=list;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }
    }


}
