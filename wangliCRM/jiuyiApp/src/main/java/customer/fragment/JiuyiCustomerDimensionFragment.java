package customer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import customer.adapter.DimensionInfoAdapter;
import customer.entity.WorthBean;
import customer.view.SpaceItemDecoration;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineCollectionProductFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的产品收藏
 *****************************************************************
 */
public class JiuyiCustomerDimensionFragment extends JiuyiFragmentBase {
    RefreshLayout refreshLayout;
    RecyclerView rvContent;
    private List<WorthBean.DataBean> commonLabelValueBeanList;
    DimensionInfoAdapter dimensionInfoAdapter;
    private long Customerid=-1;
    private String type="";
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerdimensionfragment_layout"), null);
        onInit();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }
    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            getWorthBean();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;

        }
    }
    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerDimensionFragment newInstance(int nPageType) {
        JiuyiCustomerDimensionFragment f = new JiuyiCustomerDimensionFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerDimensionFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerDimensionFragment f = new JiuyiCustomerDimensionFragment();
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
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        rvContent = (RecyclerView) mRootView.findViewById(R.id.rv_content);
        rvContent.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns(), 1, false));
        rvContent.setHasFixedSize(true);
        rvContent.setItemAnimator(new DefaultItemAnimator());

        rvContent.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0,Res.getColor(JiuyiMainApplication.getIns(), "divider_dark")));
        rvContent.addItemDecoration(new SpaceItemDecoration(-3));
        rvContent.setNestedScrollingEnabled(false);
        rvContent.setHasFixedSize(true);
    //解决数据加载完成后, 没有停留在顶部的问题
        rvContent.setFocusable(false);
//        getWorthBean();

    }
    public void getWorthBean() {
        switch (mPageType) {
            case Pub.Customer_ContributeDim:
                type="VALUECONTRIBUTION";
                break;
            case Pub.Customer_demandanalyseDim:
                type="DEMANDANALYSIS";
                break;
            case Pub.Customer_strategyDim:
                type="STRATEGICANALYSIS";
                break;
            case Pub.Customer_purchaseDim:
                type="COMPETITIONANALYSIS";
                break;
            case Pub.Customer_quanlityDim:
                type="QUALITYCOST";
                break;
            case Pub.Customer_keydemandDim:
                type="KEYDEMAND";
                break;
            case Pub.Customer_keyindexDim:
                type="KEYINDICATORS";
                break;


        }

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("member-center/getWorthBean/"+Customerid+"/"+type)
                        .tag("request_get_center")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<WorthBean>() {
                            @Override
                            public void onSuccess(WorthBean data) {
                                if(data!=null){
                                    if(data.getData()!=null && data.getData().size()>0 ){
                                        dimensionInfoAdapter=new DimensionInfoAdapter(R.layout.customer_dimensioninfo_item, data.getData());
                                        rvContent.setAdapter(dimensionInfoAdapter);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;
    }
}
