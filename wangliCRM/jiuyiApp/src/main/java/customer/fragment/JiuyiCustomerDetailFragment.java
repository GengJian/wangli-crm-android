package customer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.viewpager.JiuyiNotSmoothViewPager;

import java.util.ArrayList;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerDetailFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户信息
 *****************************************************************
 */
public class JiuyiCustomerDetailFragment extends JiuyiFragmentBase {
    private JiuyiNotSmoothViewPager mChildViewPager;//viewpager内容
    private CustomerFragmentPagerAdapter mCustomerFragmentPagerAdapter;//viewpager适配器
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_fragment_infohome_layout"), null);

            setOnRefreshListener();
            onInit();
        } else {
            checkRootViewParent();
        }
        return mRootView;
    }
    /**
     * 初始化
     */
    public void onInit() {

        mChildViewPager = (JiuyiNotSmoothViewPager) mRootView.findViewById(Res.getViewID(getContext(), "tzt_infohome_childviewpager"));
        //绑定数据
        onIinitViewPage();
    }
    /**
     * 绑定数据源
     */
    protected void onIinitViewPage() {


        //清空旧的view和data
        mChildViewPager.removeAllViewsInLayout();
        if(mCustomerFragmentPagerAdapter != null)
            mCustomerFragmentPagerAdapter.clearFragments();
        //生成新的fragments
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();//子fragment

        //设置数据源
        mCustomerFragmentPagerAdapter = new CustomerFragmentPagerAdapter(getChildFragmentManager(), fragments);
        mChildViewPager.setAdapter(mCustomerFragmentPagerAdapter);


    }
    /**
     * 获取当前Fragment的界面号
     * @return
     */
    public int getPageType(){
        try {
            return ((JiuyiFragmentBase)mCustomerFragmentPagerAdapter.getCurrItem()).getPageType();
        }catch (Exception e){
            return super.getPageType();
        }
    }
    /**
     * 获取当前Fragment的界面字符串
     * @return
     */
    public String getSimpleName(){
        try {
            return mCustomerFragmentPagerAdapter.getCurrItem().getClass().getSimpleName();
        }catch (Exception e){
            return super.getSimpleName();
        }
    }
    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerDetailFragment newInstance(int nPageType) {
        JiuyiCustomerDetailFragment f = new JiuyiCustomerDetailFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * viewpager
     *  动态的非固定的fragment使用 FragmentStatePagerAdapter
     */
    public class CustomerFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Fragment> fragments = null;

        public CustomerFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
            super(fragmentManager);
            this.fragments = fragments;
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        public Fragment getCurrItem() {
//            return fragments.get(mTablayout.getSelectedTabPosition());
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void clearFragments(){
            fragments.clear();
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return m_pButtonList.get(position).getTabBar_Title();
            return null;
        }
    }
}
