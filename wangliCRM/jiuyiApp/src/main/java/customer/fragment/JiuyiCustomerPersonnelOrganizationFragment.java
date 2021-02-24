package customer.fragment;

import android.content.Intent;
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
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.model.DictBean;
import com.jiuyi.model.DictResultBean;
import com.jiuyi.tools.DictUtils;
import com.jiuyi.tools.jiuyiCustomerViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import commonlyused.bean.ContactBean;
import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiOrgDeptSelectActivity;
import customer.adapter.CustomerSelMenuAdapter;
import customer.adapter.MultiLevelPagerAdapter;
import customer.adapter.MultiLevelSelectAdapter;
import customer.view.MultiSelectViewPager;
import customer.view.SFPopupWindow;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerPersonnelOrganizationFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户联系人信息
 *****************************************************************
 */
public class JiuyiCustomerPersonnelOrganizationFragment extends JiuyiFragmentBase {
    protected TabLayout mTablayout;//标题栏tablayout
    protected jiuyiCustomerViewPager pViewPageBar;//下方viewpager
    private GroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表
    private int menuIndex = 0;
    private SFPopupWindow popMenu;
    private CustomerSelMenuAdapter menuAdapter2;
    private ListView popListView;
    private List<Map<String, Object>>  menuData1,menuData2;
    List<DictBean> dictBeansList;
    private int[] imageids = { R.drawable.client_selected };
    private long Customerid=-1;
    public long deptID=-1;
    private String customerName="",deptName="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerlinkmanfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        customerName=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        initViews();
        initPopMenu();
        //绑定数据
        onIinitViewPage();
       /* if(!MemberAuthorityBean.getIns().contact){
            tv_no_authoritytext.setVisibility(View.VISIBLE);
            iv_no_authority.setVisibility(View.VISIBLE);
        }else {
            showProcessBar(0);
            getContactInfoList();
        }*/
        showProcessBar(0);
        getContactInfoList();

    }
    private void initViews() {
        mTablayout = (TabLayout) mRootView.findViewById(Res.getViewID(getCallBack().getActivity(), "jiuyi_tablayout"));
        pViewPageBar = (jiuyiCustomerViewPager) mRootView.findViewById(Res.getViewID(getCallBack().getActivity(), "jiuyi_customer_viewpager"));
    }
    private void initMenuData() {
        String[] menuStr2;
        Map<String, Object>  map2;
        menuData2 =  new ArrayList<Map<String, Object>>();
        map2 = new HashMap<String, Object>();
        map2.put("name","所有");
        map2.put("select", imageids[0]);
        menuData2.add(map2);
        dictBeansList= DictUtils.getDictList("intelligence_type","personnel_type");
        if(dictBeansList!=null && dictBeansList.size()>0){
            menuStr2=new String[dictBeansList.size()+1];
            for(int i=0;i<dictBeansList.size();i++){
                menuStr2[i]=dictBeansList.get(i).getValue();
                map2 = new HashMap<String, Object>();
                map2.put("name", menuStr2[i]);
                map2.put("select", imageids[0]);
                menuData2.add(map2);
            }
        }

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
            }
        });
        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });

        menuAdapter2=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData2);

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {

                //改变选中状态
                menuAdapter2.setCurrentItem(pos);
                //通知ListView改变状态
                menuAdapter2.notifyDataSetChanged();
                popMenu.dismiss();
                if (menuIndex == 2){
                    if(mTablayout!=null){
                        TabLayout.Tab tab = mTablayout.getTabAt(1);
                        if (tab.getCustomView() != null) {
                            View tabView = (View) tab.getCustomView().getParent();
                            ImageView img = (ImageView) tabView.findViewById(R.id.img_title);
                            if(img!=null){
                                img.setImageResource(R.drawable.client_down_n);
                            }
                            TextView txt_title= (TextView) tabView.findViewById(R.id.txt_title);
                            txt_title.setText(menuData2.get(pos).get("name").toString());
                        }
                    }
                    if(pos>0){
                        ((JiuyiCustomerMarketTrackFragment) mFragmentPagerAdapter.fragments.get(1)).setmSmalltype(dictBeansList.get(pos-1).getOriginalid()+"");
                    }else{
                        ((JiuyiCustomerMarketTrackFragment) mFragmentPagerAdapter.fragments.get(1)).setmSmalltype("all");
                    }
                    ((JiuyiCustomerMarketTrackFragment) mFragmentPagerAdapter.fragments.get(1)).showDialog();
                    ((JiuyiCustomerMarketTrackFragment) mFragmentPagerAdapter.fragments.get(1)).getRiskWarnList(0);
                }
                popMenu.dismiss();

            }
        });
    }
    private void getContactInfoList() {
        long depId=((JiuyiCustomerLinkmanOrgFragment) mFragmentPagerAdapter.fragments.get(0)).getDeptID();
        if(depId>0){
            ((JiuyiCustomerLinkmanOrgFragment) mFragmentPagerAdapter.fragments.get(0)).setDeptID(depId);
            ((JiuyiCustomerLinkmanOrgFragment) mFragmentPagerAdapter.fragments.get(0)).getContractByDeptIdList(0);
        }else {
            ((JiuyiCustomerLinkmanOrgFragment) mFragmentPagerAdapter.fragments.get(0)).setDeptID(depId);
            ((JiuyiCustomerLinkmanOrgFragment) mFragmentPagerAdapter.fragments.get(0)).getContractByMemberIdList(0);
        }

    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerPersonnelOrganizationFragment newInstance(int nPageType) {
        JiuyiCustomerPersonnelOrganizationFragment f = new JiuyiCustomerPersonnelOrganizationFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerPersonnelOrganizationFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerPersonnelOrganizationFragment f = new JiuyiCustomerPersonnelOrganizationFragment();
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
            getContactInfoList();
        }

    }

    @Override
    public void createReq(boolean IsBg) {
        getContactInfoList();
    }

    /**
     * 初始化viewpager，更新数据源
     */
    protected void onIinitViewPage() {
        //创建子fragment
        ArrayList<Fragment> fragments = new ArrayList<>();//子fragment
        //读取配置信息
        String strData = Res.getString(null, "jiuyicustomerlinkmantabbar");
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
                case Pub.Customer_linkmanOrg:
                    itemFragment = JiuyiCustomerLinkmanOrgFragment.newInstance(nPageType, mBundle);
                    break;
                case Pub.Customer_linkmandynamic:
                    mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERBIGTYPE,"personnel_type");
                    itemFragment = JiuyiCustomerMarketTrackFragment.newInstance(nPageType, mBundle);
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
                    setUnSelectTab(1);
                }else if(tab.getPosition()==1){
                    setUnSelectTab(0);
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

    public void setUnSelectTab(int index){
        TabLayout.Tab tab0 = mTablayout.getTabAt(index);
        View tabView = (View) tab0.getCustomView().getParent();
        TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
        txt_title2.setTextColor(Res.getColor(null, "jiuyi_info_color"));
        ImageView img2 = (ImageView) tabView.findViewById(R.id.img_title);
        img2.setImageResource(R.drawable.client_down_n);
    }
    public void setSelectTab(int index){
        TabLayout.Tab tab = mTablayout.getTabAt(index);
        if (tab!=null && tab.getCustomView() != null) {
            View tabView = (View) tab.getCustomView().getParent();
            TextView txt_title2= (TextView) tabView.findViewById(R.id.txt_title);
            txt_title2.setTextColor(Res.getColor(null, "jiuyi_blue"));
        }
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
            int pos = (int) view.getTag();
            if (mTablayout.getTabAt(pos).isSelected()==true){
                TextView txt_title= (TextView) view.findViewById(R.id.txt_title);
                txt_title.setTextColor(Res.getColor(null, "jiuyi_blue"));
                if(pos==0){
                    Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiOrgDeptSelectActivity.class);
                    intent.putExtra(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                    startActivityForResult(intent, 200);
//                    setUnSelectTab(1);
                }else if(pos==1){
                    menuIndex =2;
                    popListView.setAdapter(menuAdapter2);
                    popMenu.showAsDropDown(mTablayout, 0, 2);
                    ImageView img = (ImageView) view.findViewById(R.id.img_title);
                    if(img!=null){
                        img.setImageResource(R.drawable.drop_down_s);
                    }
                    setUnSelectTab(0);
                }
            } else {
                TabLayout.Tab tab = mTablayout.getTabAt(pos);
                if (tab != null) {
                    tab.select();
                }
                TextView txt_title= (TextView) view.findViewById(R.id.txt_title);
                txt_title.setTextColor(Res.getColor(null, "jiuyi_blue"));
//                if(pos==0){
//                    setUnSelectTab(1);
//                }else if(pos==1){
//                    setUnSelectTab(0);
//                }
            }
        }
    };



    /**
     * 获取当前Fragment的界面号
     *
     * @return
     */
    @Override
    public int getPageType() {
        try {
            return ((JiuyiFragmentBase)mFragmentPagerAdapter.getCurrItem()).getPageType();
        } catch (Exception e) {
            return super.getPageType();
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
//            if(!Func.IsStringEmpty(mRisktypeName) && position==0){
//                tv.setText(mRisktypeName);
//            }else{
//                tv.setText(mTitleList.get(position));
//            }

            if(!Func.IsStringEmpty(customerName)){
                tv.setText(customerName);
            }else {
                tv.setText(mTitleList.get(position));
            }

            ImageView img = (ImageView) view.findViewById(R.id.img_title);
//            if(img!=null){
//                if(position!=0){
//                    img.setVisibility(View.GONE);
//                }
//            }

            return view;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 200:
                if(intent!=null){
                    Bundle bundle = intent.getExtras();
                    deptID= bundle.getLong(JiuyiBundleKey.PARAM_COMMONID);
                    deptName= bundle.getString(JiuyiBundleKey.PARAM_COMMONNAME);
//                    mTablayout.getTabAt(0).setText(deptName);
                    if(mTablayout!=null){
                        TabLayout.Tab tab = mTablayout.getTabAt(0);
                        if (tab.getCustomView() != null) {
                            View tabView = (View) tab.getCustomView().getParent();
                            ImageView img = (ImageView) tabView.findViewById(R.id.img_title);
                            if(img!=null){
                                img.setImageResource(R.drawable.client_down_n);
                            }
                            TextView txt_title= (TextView) tabView.findViewById(R.id.txt_title);
                            txt_title.setText(deptName);
                        }
                    }
                    if(deptID>0){
                        ((JiuyiCustomerLinkmanOrgFragment) mFragmentPagerAdapter.fragments.get(0)).setDeptID(deptID);
                        ((JiuyiCustomerLinkmanOrgFragment) mFragmentPagerAdapter.fragments.get(0)).getContractByDeptIdList(0);
                    }else {
                        ((JiuyiCustomerLinkmanOrgFragment) mFragmentPagerAdapter.fragments.get(0)).setDeptID(deptID);
                        ((JiuyiCustomerLinkmanOrgFragment) mFragmentPagerAdapter.fragments.get(0)).getContractByMemberIdList(0);
                    }

                }
                break;

            default:
                break;
        }
    }



}
