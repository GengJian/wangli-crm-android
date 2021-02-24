package customer.activity;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.tools.CustomPopWindow;
import com.jiuyi.tools.jiuyiCustomerVisitViewPager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import customer.entity.ProductcheckBean;
import customer.entity.VisitActivityListBean;
import customer.fragment.JiuyiCustomerVisitAttachmentFragment;
import customer.fragment.JiuyiCustomerVisitRemarkFragment;
import customer.fragment.JiuyiCustomerVistProcessFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerVisitDetailActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 拜访详情
 *****************************************************************
 */

public class JiuyiCustomerVisitDetailActivity extends JiuyiActivityBase {

    private long beanid=-1;
    private JiuyiItemGroup nameIG,dateIG;
    private ImageView  ivNavbarbackbg,ivMore;
    private CustomPopWindow mCustomPopWindow;
    protected TabLayout mTablayout;//标题栏tablayout
    protected jiuyiCustomerVisitViewPager pViewPageBar;//下方viewpager
    private GroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表
    private VisitActivityListBean visitActivityListBean;
    private TextView tvtitle;
    private TextView tvBegintime;
    private TextView tvEndtime;
    private TextView tvStatus;
    private TextView tvTarget;
    private TextView tvLocation;
    private CardView cvVisitinfo;
    private TabLayout jiuyiRiskTablayout;
    private jiuyiCustomerVisitViewPager jiuyiCustomerRiskviewpager;
    private RelativeLayout rvBottom;
    private View view_bottom;
    private TextView tvCompletevisit;
    RefreshLayout refreshLayout;


    @Override
    public void onInit() {
//        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        visitActivityListBean=mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customervisitdetail_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        if(visitActivityListBean!=null && visitActivityListBean.getId()>0){
            getDetailinfoList(visitActivityListBean.getId());
        }else if (beanid>0){
            getDetailinfoList(beanid);
        }else {
            //绑定数据
            onIinitViewPage();
            initData();
        }


    }
    private void upload()
    {

    }
    public void initViews(){
        ivNavbarbackbg=(ImageView) mBodyLayout.findViewById(R.id.jiuyi_titlebar_navbarbackbg);
        ivNavbarbackbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });
        ivMore=(ImageView) mBodyLayout.findViewById(R.id.jiuyi_titlebar_more);
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMenu();
            }
        });
        mTablayout = (TabLayout) mBodyLayout.findViewById(R.id.jiuyi_risk_tablayout);
        pViewPageBar = (jiuyiCustomerVisitViewPager) mBodyLayout.findViewById(R.id.jiuyi_customer_riskviewpager);

        tvtitle = (TextView) mBodyLayout.findViewById(R.id.tvtitle);
        tvBegintime = (TextView) mBodyLayout.findViewById(R.id.tv_begintime);
        tvEndtime = (TextView) mBodyLayout.findViewById(R.id.tv_endtime);
        tvStatus = (TextView) mBodyLayout.findViewById(R.id.tv_status);
        tvTarget = (TextView) mBodyLayout.findViewById(R.id.tv_target);
        tvLocation = (TextView) mBodyLayout.findViewById(R.id.tv_location);
        cvVisitinfo = (CardView) mBodyLayout.findViewById(R.id.cv_visitinfo);
        jiuyiRiskTablayout = (TabLayout) mBodyLayout.findViewById(R.id.jiuyi_risk_tablayout);
        jiuyiCustomerRiskviewpager = (jiuyiCustomerVisitViewPager) mBodyLayout.findViewById(R.id.jiuyi_customer_riskviewpager);
        view_bottom = (View) mBodyLayout.findViewById(R.id.view_bottom);
        rvBottom = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_bottom);
        rvBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visitActivityListBean.getStatus()!=null && visitActivityListBean.getStatus().equals("end") ){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "拜访活动已完成，无需重复点击!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(visitActivityListBean.getVisitor()!=null && visitActivityListBean.getVisitor().getId()!=Rc.id){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "只能修改自己创建的拜访记录！", Toast.LENGTH_SHORT).show();
                    return;
                }
                updateStatusInfo();
            }
        });
        if(visitActivityListBean!=null && visitActivityListBean.getStatus().equals("end")){
            rvBottom.setVisibility(View.GONE);
            view_bottom.setVisibility(View.GONE);
        }
        tvCompletevisit = (TextView) mBodyLayout.findViewById(R.id.tv_completevisit);
        RefreshLayout refreshLayout = (RefreshLayout)mBodyLayout.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (visitActivityListBean != null) {
                    getDetailinfoList(visitActivityListBean.getId());
                }

                refreshlayout.finishRefresh(2000);
            }
        });

    }
    public void initData(){
        if(visitActivityListBean!=null){
            if(visitActivityListBean.getPurpose()!=null){
                tvTarget.setText(visitActivityListBean.getPurpose());
            }
            String date="";
            if(visitActivityListBean.getBeginDate()!=null){
                date+=visitActivityListBean.getBeginDate();
            }
            if(visitActivityListBean.getEndDate()!=null){
                date+="--"+visitActivityListBean.getEndDate();
            }
            tvBegintime.setText(date);
            if(visitActivityListBean.getStatusValue()!=null){
                tvStatus.setText(visitActivityListBean.getStatusValue());
            }
            if(visitActivityListBean.getAddress()!=null){
                tvLocation.setText(visitActivityListBean.getAddress());
            }

        }

    }
    private void showPopMenu(){
        View contentView = LayoutInflater.from(this).inflate(R.layout.jiuyi_pop_menu,null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .create()
                .showAsDropDown(ivMore,20,20);
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

                switch (v.getId()){
                    case R.id.menu1:
//                        Toast.makeText(JiuyiCustomerVisitDetailActivity.this, "111", Toast.LENGTH_LONG).show();
                        break;


                }

            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
    }
    /**
     * 初始化viewpager，更新数据源
     */
    protected void onIinitViewPage() {
        //创建子fragment
        ArrayList<Fragment> fragments = new ArrayList<>();//子fragment
        //读取配置信息
        String strData = Res.getString(null, "jiuyicustomervisittabbar");
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
            mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,visitActivityListBean);
            switch (nPageType) {
                case Pub.Customer_VisitProcess:
                    itemFragment = JiuyiCustomerVistProcessFragment.newInstance(nPageType, mBundle);
                    break;
                case Pub.Customer_VisitRemark:
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"拜访备注");
                    itemFragment = JiuyiCustomerVisitRemarkFragment.newInstance(nPageType, mBundle);
                    break;
                case Pub.Customer_VisitAttach:
                    itemFragment = JiuyiCustomerVisitAttachmentFragment.newInstance(nPageType, mBundle);
                    break;



            }
            if (itemFragment != null) {
                itemFragment.setCallBack(mCallActivityCallBack);
                fragments.add(itemFragment);
            }
        }
        //设置数据源
        mFragmentPagerAdapter = new GroupFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pViewPageBar.setAdapter(mFragmentPagerAdapter);
        //绑定 ViewPager & Tablayout
        mTablayout.setupWithViewPager(pViewPageBar);

    }
    public void setTitle(){
        mTitle = "拜访活动";
        setTitle(mTitle);
    }
    @Override
    public void createReq(boolean IsBg) {
        if (visitActivityListBean != null) {
            getDetailinfoList(visitActivityListBean.getId());
        }

    }
    public void submit(){
        JiuyiHttp.POST("spot_check/create")
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    public boolean check(){

//        if(TextUtils.isEmpty(tvDate.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请选择日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }

        return true;
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            Rc.mBackfresh=true;
            backPage();
        }
    }



    public void createProduct(){
        submit();
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

    public void updateStatusInfo(){
        HashMap beanMap= new HashMap<String, Object>();
        beanMap.put("id",visitActivityListBean.getId());

        JiuyiHttp.PUT("visit-activity/update-mobile/status")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(beanMap))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        Toast.makeText(JiuyiCustomerVisitDetailActivity.this, "完成拜访", Toast.LENGTH_SHORT).show();
                        backPage();
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    private void getDetailinfoList(long id) {
        JiuyiHttp.GET("visit-activity/detail/"+id)
                .tag("request_get_visit-activity")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<VisitActivityListBean>() {
                    @Override
                    public void onSuccess(VisitActivityListBean contentBean) {
                        if(contentBean!=null){
                            visitActivityListBean=contentBean;
                            onIinitViewPage();
                            initData();
                            if(visitActivityListBean!=null && visitActivityListBean.getStatus().equals("end")){
                                rvBottom.setVisibility(View.GONE);
                                view_bottom.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressLoadingDialog!=null){
                            progressLoadingDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }


}
