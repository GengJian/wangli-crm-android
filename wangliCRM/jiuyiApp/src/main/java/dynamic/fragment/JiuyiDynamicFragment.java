package dynamic.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.magicindicator.MagicIndicator;
import com.control.widget.magicindicator.ViewPagerHelper;
import com.control.widget.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.control.widget.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.control.widget.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.control.widget.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.control.widget.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.control.widget.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.jiuyi.tools.jiuyiCustomerViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.adapter.CustomerSelMenuAdapter;
import customer.adapter.DynamicPopwindowAdapter;
import customer.view.ListViewDecoration;
import customer.view.SFPopupWindow;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiDynamicFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 动态界面
 *****************************************************************
 */
public class JiuyiDynamicFragment extends JiuyiFragmentBase {
    MagicIndicator magicIndicator;
    CommonNavigator commonNavigator;
    protected jiuyiCustomerViewPager pViewPageBar;//下方viewpager
    private GroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表
    private LinearLayout llDynamicIntelligence;
    private LinearLayout llDynamicActivity;
    private LinearLayout llDynamicClue;
    private LinearLayout llDynamicBusiness;
    private LinearLayout llHintcontent;
    private LinearLayout llDynamicSample;
    private LinearLayout llDynamicOffer;
    private LinearLayout llDynamicContract;
    private LinearLayout llDynamicOrder;
    private LinearLayout llDynamicDelivery;
    private LinearLayout llDynamicInvoice;
    private LinearLayout llDynamicReceivables;
    private LinearLayout llDynamicComplaint;
    private LinearLayout llHintshow;
    private LinearLayout llMore;
    private ImageView ivHintshow;
    private boolean bShow=false;

    private DynamicPopwindowAdapter menuAdapter1;
    private RecyclerView popListView;
    private SFPopupWindow popMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_dynamicfragment_layout"), null);
        onInit();
        return mRootView;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        llDynamicIntelligence = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_intelligence);
        llDynamicIntelligence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"情报大全");
                changePage(mBundle,Pub.Dy_IntelligenceWhole,true);
            }
        });
        llDynamicActivity = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_activity);
        llDynamicActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"活动大全");
                changePage(mBundle,Pub.Dy_ActivityWhole,true);
            }
        });
        llDynamicClue = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_clue);
        llDynamicClue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"线索大全");
                changePage(mBundle,Pub.Dy_ClueWhole,true);
            }
        });
        llDynamicBusiness = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_business);
        llDynamicBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"商机大全");
                changePage(mBundle,Pub.Dy_BusinessWhole,true);
            }
        });
        llHintcontent = (LinearLayout) mRootView.findViewById(R.id.ll_hintcontent);
        llDynamicSample = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_sample);
        llDynamicSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"样品申请");
                changePage(mBundle,Pub.Dy_SampleWhole,true);
            }
        });
        llDynamicOffer = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_offer);
        llDynamicOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"报价大全");
                changePage(mBundle,Pub.Dy_OfferWhole,true);
            }
        });
        llDynamicContract = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_contract);
        llDynamicContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"合同清单");
                changePage(mBundle,Pub.Dy_ContractWhole,true);
            }
        });
        llDynamicOrder = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_order);
        llDynamicOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"订单清单");
                changePage(mBundle,Pub.Dy_OrderWhole,true);
            }
        });
        llDynamicDelivery = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_delivery);
        llDynamicDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"发货清单");
                changePage(mBundle,Pub.Dy_deliveryWhole,true);
            }
        });
        llDynamicInvoice = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_invoice);
        llDynamicInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"发票清单");
                changePage(mBundle,Pub.Dy_invoiceWhole,true);
            }
        });
        llDynamicReceivables = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_receivables);
        llDynamicReceivables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"收款清单");
                changePage(mBundle,Pub.Dy_receivablesWhole,true);
            }
        });
        llDynamicComplaint = (LinearLayout) mRootView.findViewById(R.id.ll_dynamic_complaint);
        llDynamicComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "该功能将于2019.4.1上线！", Toast.LENGTH_SHORT).show();
//                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"客诉申请");
//                changePage(mBundle,Pub.Dy_complaintWhole,true);
            }
        });
        llMore = (LinearLayout) mRootView.findViewById(R.id.ll_more);
        llMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuAdapter1=new DynamicPopwindowAdapter(JiuyiMainApplication.getIns(), mTitleList,getCurrentItemPosition());
                menuAdapter1.setOnCLickItemListener(new DynamicPopwindowAdapter.onCLickItemListener() {
                @Override
                public void click(int position, String colname, View view) {
                    if(menuAdapter1.mviewBeanList!=null && menuAdapter1.mviewBeanList.size()>0) {
                        if(mPageTypeList!=null && mPageTypeList.size()>0 ){
                            popMenu.dismiss();
                            setCurrentItemByAction(mPageTypeList.get(position));
                        }

                    }

                }
        });
                popListView.setAdapter(menuAdapter1);
                popMenu.showAsDropDown(llMore, 0, 2);
            }
        });
        llHintshow = (LinearLayout) mRootView.findViewById(R.id.ll_hintshow);
        llHintshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bShow){
                    llHintcontent.setVisibility(View.GONE);
                    ivHintshow.setImageResource(R.drawable.drop_down);
                    bShow=false;
                }else {
                    llHintcontent.setVisibility(View.VISIBLE);
                    ivHintshow.setImageResource(R.drawable.pack_up);
                    bShow=true;
                }
            }
        });
        ivHintshow = (ImageView) mRootView.findViewById(R.id.iv_hintshow);
        initPopMenu();
        pViewPageBar = (jiuyiCustomerViewPager) mRootView.findViewById(Res.getViewID(getCallBack().getActivity(), "jiuyi_customer_riskviewpager"));
        //绑定数据
        onIinitViewPage();
        initMagicIndicator1();


    }

    private void initPopMenu() {
        View contentView = View.inflate(JiuyiMainApplication.getIns(), R.layout.popwin_dynamic_list,
                null);
        popMenu = new SFPopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

        popListView = (RecyclerView) contentView.findViewById(R.id.rv_list);
        GridLayoutManager mgr = new GridLayoutManager(mCallBack.getActivity(), 6);
        popListView.setLayoutManager(mgr);
        popListView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, Rc.getApplication().getResources().getColor(R.color.background)));
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                popMenu.dismiss();
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
        String strData = Res.getString(null, "jiuyidynamictabbar");
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
                case Pub.Dynamic_all:
                case Pub.Dynamic_intelligence:
                case Pub.Dynamic_activity:
                case Pub.Dynamic_clue:
                case Pub.Dynamic_business:
                case Pub.Dynamic_sample:
                case Pub.Dynamic_offer:
                case Pub.Dynamic_visit:
                case Pub.Dynamic_receipt:
                case Pub.Dynamic_order:
                case Pub.Dynamic_delivery:
                case Pub.Dynamic_invoice:
                case Pub.Dynamic_receivables:
                case Pub.Dynamic_requery:
                case Pub.Dynamic_complaint:
                case Pub.Dynamic_notice:
                case Pub.Dynamic_customer:
                case Pub.Dynamic_workring:
                case Pub.Dynamic_contract:
                    if(nPageType == mPageType){
                        itemFragment = JiuyiDynamicListFragment.newInstance(nPageType, mBundle);
                    }else {
                        itemFragment = JiuyiDynamicListFragment.newInstance(nPageType, null);
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
        //根据action设置当前选中的item
        setCurrentItemByAction(mPageType);
    }
    private void initMagicIndicator1() {
        magicIndicator=(MagicIndicator)mRootView.findViewById(Res.getViewID(null, "magic_indicator"));
        commonNavigator = new CommonNavigator(JiuyiMainApplication.getIns());
        commonNavigator.setAdjustMode(false);
        commonNavigator.setSmoothScroll(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleList == null ? 0 : mTitleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(JiuyiMainApplication.getIns());
                commonPagerTitleView.setContentView(R.layout.custom_dim_indicator_item);
                // 初始化
                final TextView titleText = (TextView) commonPagerTitleView.findViewById(R.id.title_text);
                titleText.setText(mTitleList.get(index));

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.setTextColor(Res.getColor(null, "jiuyi_blue"));
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setTextColor(Res.getColor(null, "jiuyi_title_color"));
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                    }
                });
                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pViewPageBar.setCurrentItem(index);
                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(JiuyiMainApplication.getIns());
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                //设置indicator的宽度
                indicator.setLineWidth(Res.Dip2Pix(30));
                indicator.setColors(Res.getColor(null, "jiuyi_blue"));
                return indicator;

            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, pViewPageBar);
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
    public static JiuyiDynamicFragment newInstance(int nPageType) {
        JiuyiDynamicFragment f = new JiuyiDynamicFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiDynamicFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiDynamicFragment f = new JiuyiDynamicFragment();
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
    public int getCurrentItemPosition(){
        int currentPostion=-1;
        if (pViewPageBar != null ){
            currentPostion=pViewPageBar.getCurrentItem();
        }
        return currentPostion;
    }

}
