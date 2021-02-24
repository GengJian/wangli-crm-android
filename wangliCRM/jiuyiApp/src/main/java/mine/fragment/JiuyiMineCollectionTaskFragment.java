package mine.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFragmentBase;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.recyclerview.swipe.SwipeMenuRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;

import java.util.List;

import commonlyused.adapter.TaskListAdapter;
import commonlyused.bean.QueryConditionBean;
import commonlyused.bean.TaskBean;
import customer.adapter.ReceiveAddressAdapter;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineCollectionTaskFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的任务收藏
 *****************************************************************
 */
public class JiuyiMineCollectionTaskFragment extends JiuyiFragmentBase {
    SwipeMenuRecyclerView swipeMenuRecyclerView;
    private TaskListAdapter adapter;
    QueryConditionBean queryConditionBean;
    private List<TaskBean.ContentBean> mTaskBeanList;
    RefreshLayout refreshLayout;
    private  int pageIndex=0,pagesize=20,totalPage=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_collectionallfragment_layout"), null);
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
    public static JiuyiMineCollectionTaskFragment newInstance(int nPageType) {
        JiuyiMineCollectionTaskFragment f = new JiuyiMineCollectionTaskFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineCollectionTaskFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineCollectionTaskFragment f = new JiuyiMineCollectionTaskFragment();
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
        swipeMenuRecyclerView = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.recycler_view);
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity(), 1, false));
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                getTaskList(0);
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
                    getTaskList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;
            }
        });
        getTaskList(0);
        showProcessBar(0);

    }
    public  void getTaskList(final int page){
        if(page==0){
            pageIndex=1;
        }
        queryConditionBean=builderCondition(page);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("favorite/COLLABORATIVE_TASK/page")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.getIns().id_tokenparam)
                        .request(new ACallback<TaskBean>() {
                            @Override
                            public void onSuccess(TaskBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    mTaskBeanList=data.getContent();
                                    if(page==0){
                                        if(adapter==null){
                                            adapter = new TaskListAdapter(R.layout.jiuyi_task_item, mTaskBeanList);
                                            swipeMenuRecyclerView.setAdapter(adapter);
                                            adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
                                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    TaskBean.ContentBean contentBean= (TaskBean.ContentBean) adapter.getData().get(position);
                                                    if(contentBean!=null){
                                                        String urladd=Res.getString(null, "jiuyitaskdetail");
                                                        String url="";
                                                        if(!Func.IsStringEmpty(urladd)){
                                                            url= Constant.BaseH5Url+urladd+"?loginid="+Rc.id+"&id="+contentBean.getId()+"&token="+ Rc.id_tokenparam;
                                                        }
                                                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"任务详情");
                                                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                                                    }


                                                }
                                            });
                                        }else{
                                            adapter.refresh(mTaskBeanList);
                                        }
                                        if(mTaskBeanList.size()==0||mTaskBeanList==null){
                                            adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                        }

                                        showProcessBar(100);
                                    }else{
                                        adapter.add(mTaskBeanList);
//                                        swipeMenuRecyclerView.setAdapter(adapter);
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
        };
        thread.start();

    }
    public QueryConditionBean builderCondition(int page){
        queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(pagesize);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("id");
        queryConditionBean.setFromClientType("android");
        return queryConditionBean;
    }
}
