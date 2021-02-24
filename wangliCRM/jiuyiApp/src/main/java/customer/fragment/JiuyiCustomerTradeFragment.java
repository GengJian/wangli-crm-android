package customer.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.wanglicrm.android.R;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.jiuyiCustomerViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.adapter.CustomerSelMenuAdapter;
import customer.entity.MemberAuthorityBean;
import customer.view.SFPopupWindow;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerTradeFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户360交易跟踪
 *****************************************************************
 */
public class JiuyiCustomerTradeFragment extends JiuyiFragmentBase implements View.OnClickListener {
    protected TabLayout mTablayout;//标题栏tablayout
    protected jiuyiCustomerViewPager pViewPageBar;//下方viewpager
    private RiskGroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表
    private SFPopupWindow popMenu;
    private CustomerSelMenuAdapter menuAdapter1;
    private ListView popListView;
    private List<Map<String, Object>>  menuData1;
    private int[] imageids = { R.drawable.client_selected };
    private long Customerid=-1;
    private String Customername="",mRisktype=""/*,mRisktypeName=""*/;
    Bundle mBundleRisk ;
    List<DictBean> dictBeansList;
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
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        mRisktype=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE);
        mBundleRisk = new Bundle();
        mBundleRisk.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
        mBundleRisk.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, Customername);
        mBundleRisk.putString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE, mRisktype);
        mTablayout = (TabLayout) mRootView.findViewById(Res.getViewID(getCallBack().getActivity(), "jiuyi_risk_tablayout"));
        pViewPageBar = (jiuyiCustomerViewPager) mRootView.findViewById(Res.getViewID(getCallBack().getActivity(), "jiuyi_customer_riskviewpager"));
//        pViewPageBar.setAllowScroll(false);
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        view_1=(View)mRootView.findViewById(Res.getViewID(getContext(), "view_1"));
//        if(!MemberAuthorityBean.getIns().transactionTracking){
//            tv_no_authoritytext.setVisibility(View.VISIBLE);
//            iv_no_authority.setVisibility(View.VISIBLE);
//            mTablayout.setVisibility(View.GONE);
//            pViewPageBar.setVisibility(View.GONE);
//            view_1.setVisibility(View.GONE);
//        }else {
//            initMenuData();
//            initPopMenu();
//            //绑定数据
//            onIinitViewPage();
//        }
        initMenuData();
        initPopMenu();
        //绑定数据
        onIinitViewPage();
    }

    private void initMenuData() {
        String[] menuStr1;
        Map<String, Object>  map1,map2;
        menuData1 =  new ArrayList<Map<String, Object>>();
        menuStr1=new String[2];
        map1 = new HashMap<String, Object>();
        map1.put("name", "按时间轴显示");
        map1.put("select", imageids[0]);
        menuData1.add(map1);
        map2 = new HashMap<String, Object>();
        map2.put("name", "按表格显示");
        map2.put("select", imageids[0]);
        menuData1.add(map2);
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
//                mTvcharsort.setTextColor(Color.parseColor("#5a5959"));
            }
        });
        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });



        menuAdapter1=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData1);

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                //改变选中状态
                menuAdapter1.setCurrentItem(pos);
                //通知ListView改变状态
                menuAdapter1.notifyDataSetChanged();
                popMenu.dismiss();
                if(mTablayout!=null){
                    TabLayout.Tab tab = mTablayout.getTabAt(0);
                    if (tab.getCustomView() != null) {
                        View tabView = (View) tab.getCustomView().getParent();
                        ImageView img = (ImageView) tabView.findViewById(R.id.img_title);
                        if(img!=null){
                            img.setImageResource(R.drawable.client_down_n);
                        }
                        TextView txt_title= (TextView) tabView.findViewById(R.id.txt_title);
                        txt_title.setText(menuData1.get(pos).get("name").toString());

                    }
                }
                if(menuData1.get(pos).get("name").toString().equals("按时间轴显示")){
                    ((JiuyiCustomerTradeTrackFragment) mFragmentPagerAdapter.fragments.get(0)).showContent("1");
                }else if(menuData1.get(pos).get("name").toString().equals("按表格显示")){
                    ((JiuyiCustomerTradeTrackFragment) mFragmentPagerAdapter.fragments.get(0)).showContent("2");
                }
            }
        });
    }
    /**
     * 初始化viewpager，更新数据源
     */
    protected void onIinitViewPage() {
        //创建子fragment
        ArrayList<Fragment> fragments = new ArrayList<>();//子fragment
        //读取配置信息
        String strData = Res.getString(null, "jiuyicustomertradetabbar");
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
                case Pub.Customer_tradetrack:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerTradeTrackFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleRisk.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradetrack);
                        itemFragment = JiuyiCustomerTradeTrackFragment.newInstance(nPageType, mBundleRisk);
                    }
/*                    String urladd=Res.getString(null, "jiuyitransactionTracking");
                    String url="";
                    if(!Func.IsStringEmpty(urladd)){
                        url= Constant.BaseH5Url+urladd+"?memberId="+Customerid+"&token="+ Rc.id_tokenparam;
                    }
                    itemFragment = JiuyiWebViewFragment.newInstance(Pub.HttpServer, url);*/
                    break;
                case Pub.Customer_tradepandect:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiCustomerTradePandectFragment.newInstance(nPageType, mBundle);
                    }else {
                        mBundleRisk.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_tradepandect);
                        itemFragment = JiuyiCustomerTradePandectFragment.newInstance(nPageType, mBundleRisk);
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
        //设置自定义视图
        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(mFragmentPagerAdapter.getTabView(i));
            if (tab.getCustomView() != null) {
                View tabView = (View) tab.getCustomView().getParent();
                tabView.setTag(i);
                tabView.setOnClickListener(mTabOnClickListener);
            }
        }
        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==0){
                    TabLayout.Tab tab1 = mTablayout.getTabAt(1);
                    if (tab1.getCustomView() != null) {
                        View tabView = (View) tab1.getCustomView().getParent();
                        TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
                        txt_title2.setTextColor(Res.getColor(null, "jiuyi_info_color"));
                    }
                }else if(tab.getPosition()==1){
                    TabLayout.Tab tab0 = mTablayout.getTabAt(0);
                    if (tab0.getCustomView() != null) {
                        View tabView = (View) tab0.getCustomView().getParent();
                        TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
                        txt_title2.setTextColor(Res.getColor(null, "jiuyi_info_color"));
                    }
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
        TabLayout.Tab tab = mTablayout.getTabAt(0);
        if (tab.getCustomView() != null) {
            View tabView = (View) tab.getCustomView().getParent();
            TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
            txt_title2.setTextColor(Res.getColor(null, "jiuyi_blue"));
        }
    }

   private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           int pos = (int) view.getTag();
           if (pos==0 &&mTablayout.getTabAt(pos).isSelected()==true){
               TextView txt_title= (TextView) view.findViewById(R.id.txt_title);
               txt_title.setTextColor(Res.getColor(null, "jiuyi_blue"));
               popListView.setAdapter(menuAdapter1);
               popMenu.showAsDropDown(mTablayout, 0, 2);
               ImageView img = (ImageView) view.findViewById(R.id.img_title);
               if(img!=null){
                   img.setImageResource(R.drawable.drop_down_s);
               }
               TabLayout.Tab tab = mTablayout.getTabAt(1);
//                tab.setCustomView(mFragmentPagerAdapter.getTabView(i));
               if (tab.getCustomView() != null) {
                   View tabView = (View) tab.getCustomView().getParent();
                   TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
                   txt_title2.setTextColor(Res.getColor(null, "jiuyi_info_color"));
               }

           } else {
               TabLayout.Tab tab = mTablayout.getTabAt(pos);
               if (tab != null) {
                   tab.select();
               }
               TextView txt_title= (TextView) view.findViewById(R.id.txt_title);
               txt_title.setTextColor(Res.getColor(null, "jiuyi_blue"));
               if(pos==0){
                   TabLayout.Tab tab1 = mTablayout.getTabAt(1);
                   if (tab1.getCustomView() != null) {
                       View tabView = (View) tab1.getCustomView().getParent();
                       TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
                       txt_title2.setTextColor(Res.getColor(null, "jiuyi_info_color"));
                   }
               }else if(pos==1){
                   TabLayout.Tab tab0 = mTablayout.getTabAt(0);
                   if (tab0.getCustomView() != null) {
                       View tabView = (View) tab0.getCustomView().getParent();
                       TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
                       txt_title2.setTextColor(Res.getColor(null, "jiuyi_info_color"));
                   }
               }
           }
       }
   };



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
    public static JiuyiCustomerTradeFragment newInstance(int nPageType) {
        JiuyiCustomerTradeFragment f = new JiuyiCustomerTradeFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerTradeFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerTradeFragment f = new JiuyiCustomerTradeFragment();
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

    @Override
    public void onClick(View v) {

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

        public View getTabView(int position){
            View view = LayoutInflater.from(mCallBack.getActivity()).inflate(R.layout.customerrisktabitem, null);
            TextView tv= (TextView) view.findViewById(R.id.txt_title);
            if(tv!=null){
                tv.setText(mTitleList.get(position));
            }
            ImageView img = (ImageView) view.findViewById(R.id.img_title);
            if(img!=null){
                if(position!=0){
                    img.setVisibility(View.GONE);
                }
            }

            return view;
        }

    }
}
