package customer.activity;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.tools.CustomPopWindow;
import com.jiuyi.tools.jiuyiCustomerVisitViewPager;

import java.util.ArrayList;
import java.util.List;

import customer.entity.ProductcheckBean;
import customer.fragment.JiuyiCustomerVisitRemarkFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerVisitDetailActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 拜访详情
 *****************************************************************
 */

public class JiuyiCustomerReceiptDetailActivity extends JiuyiActivityBase {

    private long beanid=-1;
    private JiuyiItemGroup nameIG,dateIG;
    private ImageView  ivNavbarbackbg,ivMore;
    private CustomPopWindow mCustomPopWindow;
    protected TabLayout mTablayout;//标题栏tablayout
    protected jiuyiCustomerVisitViewPager pViewPageBar;//下方viewpager
    private GroupFragmentPagerAdapter mFragmentPagerAdapter;//viewpager适配器
    protected ArrayList<Integer> mPageTypeList = new ArrayList<>();//标题栏功能号列表
    protected ArrayList<String> mTitleList = new ArrayList<>();//标题栏名称列表

    @Override
    public void onInit() {
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_PRODUCTINFOBEANID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customerreceiptdetail_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        //绑定数据
        onIinitViewPage();

        if(beanid>0 ){
            getProductinfoList(beanid);
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
                        Toast.makeText(JiuyiCustomerReceiptDetailActivity.this, "111", Toast.LENGTH_LONG).show();
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
        String strData = Res.getString(null, "jiuyicustomerreceipttabbar");
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

                case Pub.Customer_ReceiptHotel:
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"接待酒店");
                    itemFragment = JiuyiCustomerVisitRemarkFragment.newInstance(nPageType, mBundle);
                    break;
                case Pub.Customer_ReceiptDining:
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"接待用餐");
                    itemFragment = JiuyiCustomerVisitRemarkFragment.newInstance(nPageType, mBundle);
                    break;
                case Pub.Customer_ReceiptCar:
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"接待用车");
                    itemFragment = JiuyiCustomerVisitRemarkFragment.newInstance(nPageType, mBundle);
                    break;
                case Pub.Customer_ReceiptContent:
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"接待");
                    itemFragment = JiuyiCustomerVisitRemarkFragment.newInstance(nPageType, mBundle);
                    break;
                case Pub.Customer_ReceiptMeetting:
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"接待会议");
                    itemFragment = JiuyiCustomerVisitRemarkFragment.newInstance(nPageType, mBundle);
                    break;
                case Pub.Customer_ReceiptGift:
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"接待礼物");
                    itemFragment = JiuyiCustomerVisitRemarkFragment.newInstance(nPageType, mBundle);
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
        mTitle = "接待活动";
        setTitle(mTitle);
    }
    public void submit(){
        JiuyiHttp.POST("spot_check/create")
//                .setJson(GsonUtil.gson().toJson(productinfoBean))
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

   private void getProductinfoList(long id) {
    JiuyiHttp.GET("spot_check/detail/"+id)
            .tag("request_get_spot_check")
            .addHeader("Authorization", Rc.id_tokenparam)
            .request(new ACallback<ProductcheckBean.ContentBean>() {
                @Override
                public void onSuccess(ProductcheckBean.ContentBean contentBean) {

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

    public void createProduct(){
        submit();
    }

    public void updateProductInfo(){


     /*   JiuyiHttp.PUT("spot_check/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
//                .setJson(GsonUtil.gson().toJson(updateBean))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        if(data!=null){
                            startDialog(DialogID.DialogTrue, "", "更新成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });*/
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
