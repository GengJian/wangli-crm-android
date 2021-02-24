package customer.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.utils.CustomerQueryConditionBean;
import com.control.utils.DialogID;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.control.widget.viewpager.JiuyiNotSmoothViewPager;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import java.util.ArrayList;
import java.util.List;

import customer.entity.MemberCenterBean;
import customer.entity.MemberListBean;
import customer.fragment.JiuyiCustomerMainFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerMainActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户360详情界面
 *****************************************************************
 */
public class JiuyiCustomerMainActivity extends JiuyiActivityBase {
    /** 标题栏 */
    private RelativeLayout mTitleBar;

    /** 标题栏 右侧图片*/
    protected ImageView m_vTitleRightImageView;

    private Context mContext;
    public JiuyiNotSmoothViewPager mViewPager;

    private DetailPagerAdapter mAdapter;
    public List<Long> mList;
    private int TOTAL_SIZE = 40;
    private final int PAGE_SIZE = 10;
    private int mCount = 0;
    private long[] customerids=null;
    public  int postion=-1;
    private  int pageIndex=0;


    /**
     * 初始化UI
     */
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customermain_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        mTitleBar = (RelativeLayout)mBodyLayout.findViewById(Res.getViewID(this, "jiuyi_titlebar_layout"));
        m_vTitleRightImageView = (ImageView) mBodyLayout.findViewById(Res.getViewID(this, "jiuyi_titlebar_more"));
        if(m_vTitleRightImageView!=null){
            m_vTitleRightImageView.setVisibility(View.INVISIBLE);
        }
        customerids=mBundle.getLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS);
        postion=mBundle.getInt(JiuyiBundleKey.PARAM_CUSTOMERPOSITION);
        pageIndex=mBundle.getInt(JiuyiBundleKey.PARAM_CUSTOMERPAGEINDEX);
        initData();
        initView();
    }

    private void initData() {
        mContext = this;
        mList = new ArrayList<>();
        if(customerids!=null&&customerids.length>0 ){
            TOTAL_SIZE=customerids.length;
            for (int i = 0; i < customerids.length; i++) {
                mCount++;
                mList.add(customerids[i]);
            }
        }

    }

    private void initView() {
        mViewPager = (JiuyiNotSmoothViewPager) findViewById(R.id.vp_detail);
        mAdapter = new DetailPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new OnFragmentPageChangeListener());
        if(postion!=-1){
            mViewPager.setCurrentItem(postion);
        }
    }


    private class DetailPagerAdapter extends FragmentStatePagerAdapter {

        public DetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return JiuyiCustomerMainFragment.newInstance(mList.get(position));
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            JiuyiCustomerMainFragment fragment = (JiuyiCustomerMainFragment) super.instantiateItem(container, position);
            fragment.setCallBack(mCallActivityCallBack);
//            mFragments[position] = fragment;
            return fragment;
        }
    }

    private class OnFragmentPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float offsetPerc,
                                   int offsetPixel) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageSelected(int position) {
            int size = mList.size();
            if (position == size - 1 && pageIndex>0/* && TOTAL_SIZE > size*/) {
                //实际项目中是网络请求下一页的列表数据
                getMoreDetailList();
            }
        }
    }

    private void getMoreDetailList() {
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        if(Rc.getIns().queryConditionBean!=null ){
            queryConditionBean=Rc.getIns().queryConditionBean;
        }
        pageIndex=pageIndex+1;
        queryConditionBean.setNumber(pageIndex);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("member/find-page")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<MemberListBean>() {
                            @Override
                            public void onSuccess(MemberListBean data) {
                                if(data!=null){
                                    List<MemberListBean.ContentBean> ContentBeanlist =data.getContent();
                                    if(ContentBeanlist!=null &&ContentBeanlist.size()>0){
                                        for(int i=0;i< ContentBeanlist.size();i++){
                                            mCount++;
                                            mList.add(ContentBeanlist.get(i).getId());
                                        }
                                        mAdapter.notifyDataSetChanged();
                                    }
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });
            }
        };
        thread.start();
    }
}
