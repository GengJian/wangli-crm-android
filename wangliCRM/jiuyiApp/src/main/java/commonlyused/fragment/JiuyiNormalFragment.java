package commonlyused.fragment;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.JiuyiSwipeRefreshLayout;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiMainApplication;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.wanglicrm.android.R;

import java.util.ArrayList;
import java.util.List;

import commonlyused.adapter.AppCategoryAdapter;
import commonlyused.adapter.AppCategoryDetailAdapter;
import commonlyused.bean.AppCategoryBean;
import commonlyused.bean.AppItemBean;
import commonlyused.bean.AppTotalBean;
import commonlyused.bean.QueryConditionBean;
import commonlyused.db.AppItemBeanDao;
import commonlyused.db.DbHelper;
import customer.view.ListViewDecoration;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNormalFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用界面
 *****************************************************************
 */
public class JiuyiNormalFragment extends JiuyiFragmentBase {
    RecyclerView mRvrecentuse,mRvmenutotal;
    List<AppTotalBean.ContentBean> contentUseBeanList;
    List<AppCategoryBean.ItemsBean>  contentUseBeanList2;
    List<AppCategoryBean> appCategoryBeanList;
    RefreshLayout refreshLayout;
    ImageButton mibadd;
    private AppCategoryDetailAdapter appCategoryDetailAdapter;
    private AppCategoryAdapter appCategoryAdapter;
    private NestedScrollView nsv_content;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_normalfragment_layout"), null);
            setOnRefreshListener();
            onInit();
        }else{
            checkRootViewParent();
        }

        return mRootView;
    }
    @Override
    public void onInit() {
        super.onInit();
        initview();
        initdata();


    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiNormalFragment newInstance(int nPageType) {
        JiuyiNormalFragment f = new JiuyiNormalFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    private void initdata() {
        List<AppItemBean> appItemBeanList = DbHelper.getInstance().appItemBeanLongDBManager().queryBuilder().orderDesc(AppItemBeanDao.Properties.Id).build().list();
        contentUseBeanList=new ArrayList<>();
        if(appItemBeanList!=null && appItemBeanList.size()>0){
            for(int i=0;i<appItemBeanList.size();i++){
                AppTotalBean.ContentBean contentBean=new AppTotalBean.ContentBean();
                AppItemBean appItemBean=appItemBeanList.get(i);
                if(appItemBean.getAndroidIconUrl()!=null){
                    contentBean.setAndroidIconUrl(appItemBean.getAndroidIconUrl());
                }
                if(appItemBean.getName()!=null){
                    contentBean.setName(appItemBean.getName());
                }
                if(appItemBean.getUrl()!=null){
                    contentBean.setUrl(appItemBean.getUrl());
                }
                contentUseBeanList.add(contentBean);
                if(i>=7){
                    break;
                }
            }
        }else {
            appCategoryDetailAdapter = new AppCategoryDetailAdapter(mCallBack.getActivity(), contentUseBeanList2);
            mRvrecentuse.setAdapter(appCategoryDetailAdapter);
            onRecyclerItemClickListener();
        }
        getApplicationItemList();
//        getAppCategoryList();
    }
    private void initview() {
        nsv_content = (NestedScrollView) mRootView.findViewById(R.id.nsv_content);
        if (nsv_content != null) {
            nsv_content.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (mRootView != null) {
                        ((JiuyiSwipeRefreshLayout)mRootView).setEnabled(nsv_content.getScrollY() == 0);
                    }
                }
            });
        }

        mRvrecentuse = (RecyclerView) mRootView.findViewById(R.id.rv_recentuse);

        GridLayoutManager mgr = new GridLayoutManager(mCallBack.getActivity(), 4);
        mRvrecentuse.setLayoutManager(mgr);
        mRvrecentuse.addItemDecoration(new ListViewDecoration());

        mRvmenutotal = (RecyclerView) mRootView.findViewById(R.id.rv_menutotal);
        LinearLayoutManager mgr2 =  new LinearLayoutManager(JiuyiMainApplication.getIns(), 1, false);
        mRvmenutotal.setItemAnimator(new DefaultItemAnimator());
        mRvmenutotal.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, Rc.getApplication().getResources().getColor(R.color.background)));
        mRvmenutotal.setLayoutManager(mgr2);


        //设置分隔线
        mRvmenutotal.addItemDecoration(new ListViewDecoration());
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null, Pub.MSG_PopStart,true);
                }
            });
        }
    }

    private void onRecyclerItemClickListener() {
        appCategoryDetailAdapter.setOnRecyclerViewItemListener(new AppCategoryDetailAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {

            }

            @Override
            public void onItemLongClickListener(View view, int position) {
            }
        });
    }

    public  void  getApplicationItemList(){
        final QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("application-item/find-page-category")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<List<AppCategoryBean>>() {
                            @Override
                            public void onSuccess(List<AppCategoryBean> data) {
                                if(data!=null){

                                    appCategoryBeanList=data;
                                    contentUseBeanList2=new ArrayList<>();
                                    appCategoryAdapter = new AppCategoryAdapter(R.layout.jiuyi_appcategory_item, appCategoryBeanList);
                                    mRvmenutotal.setAdapter(appCategoryAdapter);
                                    if(appCategoryBeanList!=null && appCategoryBeanList.size()>0){
                                        if(contentUseBeanList!=null && contentUseBeanList.size()>0){
                                            for(int i=0;i<contentUseBeanList.size();i++){
                                                AppTotalBean.ContentBean contentBean=contentUseBeanList.get(i);
                                                if(contentBean!=null){
                                                    for(int j=0;j<appCategoryBeanList.size();j++){
                                                        if(appCategoryBeanList.get(j)!=null &&appCategoryBeanList.get(j).getItems()!=null&& appCategoryBeanList.get(j).getItems().size()>0){
                                                            for(int k=0;k<appCategoryBeanList.get(j).getItems().size();k++){
                                                                if(contentBean.getName().equals(appCategoryBeanList.get(j).getItems().get(k).getName())){
                                                                    contentUseBeanList2.add(appCategoryBeanList.get(j).getItems().get(k));
                                                                }
                                                            }
                                                        }

                                                    }
                                                }
                                                if(i>=7){
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    appCategoryDetailAdapter= new AppCategoryDetailAdapter(mCallBack.getActivity(), contentUseBeanList2);
                                    mRvrecentuse.setAdapter(appCategoryDetailAdapter);
                                    onRecyclerItemClickListener();
                                }
                                JiuyiLog.e("httplogin","request onSuccess!" + data);
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



    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh){
            Rc.mBackfresh=false;
            initdata();
        }

    }
    @Override
    public void createReq(boolean IsBg) {
        getApplicationItemList();

    }

}
