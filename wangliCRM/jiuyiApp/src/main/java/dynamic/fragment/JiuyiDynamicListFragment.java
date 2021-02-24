package dynamic.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.FavoriteType;
import customer.entity.FavoriteBean;
import customer.entity.FavoriteReturnBean;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.Utils.DynamicConstant;
import dynamic.adapter.DynamicListAdapter;
import dynamic.entity.DynamicBean;
import dynamic.entity.OrderBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiBusinessTripListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 订单列表界面
 *****************************************************************
 */
public class JiuyiDynamicListFragment extends JiuyiFragmentBase  {


    QueryConditionBean queryConditionBean;
    List<QueryConditionBean.RulesBean> rulesBeanList;
    private DynamicListAdapter adapter;
    private List<DynamicBean.ContentBean> mDynamicBeanList;
    private  int pageIndex=1,pagesize=20,totalPage=0;
    RefreshLayout refreshLayout;
    RecyclerView swipeMenuRecyclerView;
    private String orderdate="";
    List<String> menuStr1;
    private String spec="",grade="";
    ImageButton mibadd;
    private  FavoriteBean favoriteBean,likedBean;
    private int currentPostion=-1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_dynamiclistfragment_layout"), null);
            onInit();
        } else {
            checkRootViewParent();
        }
        return mRootView;
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiDynamicListFragment newInstance(int nPageType) {
        JiuyiDynamicListFragment f = new JiuyiDynamicListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiDynamicListFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiDynamicListFragment f = new JiuyiDynamicListFragment();
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
        mDynamicBeanList = new ArrayList<>();
        swipeMenuRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_orderlist);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity(), 1, false));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getDynamicList(0);
                refreshlayout.finishRefresh(2000);
                refreshlayout.setLoadmoreFinished(false);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(pageIndex>=totalPage){
                    refreshlayout.finishLoadmore(500);
                    refreshlayout.setLoadmoreFinished(true);
                }else{
                    getDynamicList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;
            }
        });
        getDynamicList(0);
        ImageButton mibadd;
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null, Pub.MSG_PopStart,true);
                }
            });
        }
        showProcessBar(0);
    }



    public  void getDynamicList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=builderCondition(page);
        JiuyiHttp.POST("feed-flow/page-trend")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<DynamicBean>() {
                    @Override
                    public void onSuccess(DynamicBean data) {
                        if(data!=null){
                            totalPage=data.getTotalPages();
                            mDynamicBeanList =data.getContent();
                            if(page==0){
                                if(adapter==null){
                                    adapter = new DynamicListAdapter(R.layout.jiuyi_dynamiclist_item, mDynamicBeanList);
                                    swipeMenuRecyclerView.setAdapter(adapter);
                                    adapter.setOnCLickItemListener(new DynamicListAdapter.onCLickItemListener() {
                                        @Override
                                        public void click(int position, String colname, View view) {
                                            DynamicBean.ContentBean contentBean=adapter.getItem(position);
                                            currentPostion=position;
                                            if(colname.equals("tv_collection")){
                                                if(contentBean.getFavorited()<=0){
                                                    favoriteBean=new FavoriteBean();
                                                    favoriteBean.setFkId(contentBean.getId());
                                                    favoriteBean.setFkType("FEED_FLOW");
                                                    favoriteBean.setFromClientType("android");
                                                    addFavorite(favoriteBean);
                                                }else if(contentBean.getFavorited()>0){
                                                    deleteFavorite(contentBean.getFavorited());
                                                }


                                            }
                                            if(colname.equals("tv_good")){
                                                if(contentBean.getLiked()<=0){
                                                    favoriteBean=new FavoriteBean();
                                                    favoriteBean.setFkId(contentBean.getId());
                                                    favoriteBean.setFkType("FEED_FLOW");
                                                    favoriteBean.setFromClientType("android");
                                                    addLike(favoriteBean);
                                                }else if(contentBean.getLiked()>0){
                                                    deleteLike(contentBean.getLiked());
                                                }

                                            }
//                                            if(colname.equals("tv_comment")){
////                                                Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "收藏", Toast.LENGTH_SHORT).show();
//
//                                            }
                                        }
                                    });

                                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            DynamicBean.ContentBean contentBean= (DynamicBean.ContentBean) adapter.getData().get(position);
                                            if(contentBean!=null){
                                                if(!contentBean.getFkType().equals("INTELLIGENCE_ITEM")){
                                                    mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                    mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,contentBean);
                                                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"动态详情");
                                                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Dynamic_detail,true);
                                                }

                                            }
                                        }
                                    });

                                }else{
                                    adapter.refresh(mDynamicBeanList);
                                }
                                if(mDynamicBeanList.size()==0|| mDynamicBeanList ==null){
                                    adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }
                                showProcessBar(100);
                            }else{
                                adapter.add(mDynamicBeanList);
                                showProcessBar(100);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }
    public  QueryConditionBean  builderCondition(int page){
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;


        if(getPageType()!=Pub.Dynamic_all){
            String status="";
            switch (mPageType) {
                case Pub.Dynamic_intelligence:
                    status = "INTELLIGENCE_ITEM";
                    break;
                case Pub.Dynamic_activity:
                    status = "MARKEND_ACTIVITY";
                    break;
                case Pub.Dynamic_clue:
                    status = "CLUE";
                    break;
                case Pub.Dynamic_business:
                    status = "BUSINESS_CHANCE";
                    break;
                case Pub.Dynamic_sample:
                    status = "SAMPLE";
                    break;
                case Pub.Dynamic_offer:
                    status = "QUOTED_PRICE";
                    break;
                case Pub.Dynamic_visit:
                    status = "VISIT_ACTIVITY";
                    break;
                case Pub.Dynamic_receipt:
                    status = "VISIT_ACTIVITY";
                    break;
                case Pub.Dynamic_order:
                    status = "ORDER";
                    break;
                case Pub.Dynamic_delivery:
                    status = "INVOICE";
                    break;
                case Pub.Dynamic_invoice:
                    status = "SALES_BILLING";
                    break;
                case Pub.Dynamic_receivables:
                    status = "RECEIPT_TRACKING";
                    break;
                case Pub.Dynamic_requery:
                    status = "INTELLIGENCE_ITEM";
                    break;
                case Pub.Dynamic_complaint:
                    status = "INTELLIGENCE_ITEM";
                    break;
                case Pub.Dynamic_notice:
                    status = "ARTICLE";
                    break;
                case Pub.Dynamic_customer:
                    status = "INTELLIGENCE_ITEM";
                    break;
                case Pub.Dynamic_workring:
                    status = "WORKING_CIRCLE";
                    break;
                case Pub.Dynamic_contract:
                    status = "SALES_CONTRACT";
                    break;
            }
            if(!Func.IsStringEmpty(status)){
                List<String> value2 = new ArrayList<String>();
                QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
                rulesBean2.setField("fkType");
                rulesBean2.setOption("EQ");
                value2.add(status);
                rulesBean2.setValues(value2);
                rulesBeanList.add(rulesBean2);
            }
        }
        queryConditionBean.setRules(rulesBeanList);
        return queryConditionBean;
    }

    public void addFavorite( FavoriteBean favoriteBean){
        JiuyiHttp.POST("favorite/create")
                .setJson(GsonUtil.gson().toJson(favoriteBean))
                .addHeader("Authorization",Rc.id_tokenparam)

                .request(new ACallback<FavoriteReturnBean>() {
                    @Override
                    public void onSuccess(FavoriteReturnBean result ) {
                        Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "收藏成功!", Toast.LENGTH_SHORT).show();
                        if(result!=null){
                            if(currentPostion>=0 && adapter!=null && adapter.getData()!=null){
                                adapter.getData().get(currentPostion).setFavorited(result.getId());
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    public void deleteFavorite(long id){
        JiuyiHttp.DELETE("favorite/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(currentPostion>=0 && adapter!=null && adapter.getData()!=null){
                                adapter.getData().get(currentPostion).setFavorited(-8);
                                adapter.notifyDataSetChanged();
                            }
                            Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "取消收藏成功!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }


    public void addLike( FavoriteBean favoriteBean){
        JiuyiHttp.POST("like-record/add")
                .setJson(GsonUtil.gson().toJson(favoriteBean))
                .addHeader("Authorization",Rc.id_tokenparam)

                .request(new ACallback<FavoriteReturnBean>() {
                    @Override
                    public void onSuccess(FavoriteReturnBean result ) {
                        Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "点赞成功!", Toast.LENGTH_SHORT).show();
                        if(result!=null){
                            if(currentPostion>=0 && adapter!=null && adapter.getData()!=null){
                                adapter.getData().get(currentPostion).setLiked(result.getId());
                                adapter.getData().get(currentPostion).setLikedCount(adapter.getData().get(currentPostion).getLikedCount()+1);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    public void deleteLike(long id){
        JiuyiHttp.DELETE("like-record/remove/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(currentPostion>=0 && adapter!=null && adapter.getData()!=null){
                                adapter.getData().get(currentPostion).setLiked(-8);
                                adapter.getData().get(currentPostion).setLikedCount(adapter.getData().get(currentPostion).getLikedCount()-1);
                                adapter.notifyDataSetChanged();
                            }
                            Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "取消点赞!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

}
