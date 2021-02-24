package mine.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.CustomerQueryConditionBean;
import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiFragmentBase;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.recyclerview.swipe.Closeable;
import com.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.recyclerview.swipe.SwipeMenu;
import com.recyclerview.swipe.SwipeMenuCreator;
import com.recyclerview.swipe.SwipeMenuItem;
import com.recyclerview.swipe.SwipeMenuRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import customer.adapter.CustomerListAdapter;
import customer.entity.MemberListBean;
import customer.listener.OnItemClickListener;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineCollectionCustomerFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的客户收藏
 *****************************************************************
 */
public class JiuyiMineCollectionCustomerFragment extends JiuyiFragmentBase {
    private List<MemberListBean.ContentBean> mCustomerListBeanList;
    SwipeMenuRecyclerView swipeMenuRecyclerView;
    CustomerListAdapter menuAdapter;
    RefreshLayout refreshLayout;
    private long[] customerids=null;
    int pageIndex = 1;
    int pageSize = 20;
    private int totalPage=0;
    private TextView tv_emptytext;
    private ImageView iv_empty;

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
    public static JiuyiMineCollectionCustomerFragment newInstance(int nPageType) {
        JiuyiMineCollectionCustomerFragment f = new JiuyiMineCollectionCustomerFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineCollectionCustomerFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineCollectionCustomerFragment f = new JiuyiMineCollectionCustomerFragment();
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
        tv_emptytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_emptytext"));
        iv_empty=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_empty"));
        swipeMenuRecyclerView = (SwipeMenuRecyclerView) mRootView.findViewById(R.id.recycler_view);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

        swipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        swipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        getCustomerList(0);

        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                getCustomerList(0);
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
                    getCustomerList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;
            }
        });
        showProcessBar(0);
    }
    public  void  getCustomerList(final int page){
        if(page==0){
            pageIndex=1;
        }
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        if(Rc.getIns().queryConditionBean!=null ){
            queryConditionBean=Rc.getIns().queryConditionBean;
        }else{
            queryConditionBean.setNumber(page);
            queryConditionBean.setSize(20);
            queryConditionBean.setDirection("DESC");
            queryConditionBean.setProperty("id");
        }
        final CustomerQueryConditionBean finalQueryConditionBean = queryConditionBean;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("favorite/MEMBER/page")
                        .setJson(GsonUtil.gson().toJson(finalQueryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<MemberListBean>() {
                            @Override
                            public void onSuccess(MemberListBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<MemberListBean.ContentBean> ContentBeanlist =data.getContent();
                                    mCustomerListBeanList =data.getContent();
                                    if(page==0){
                                        if(menuAdapter==null){
                                            menuAdapter = new CustomerListAdapter(mCustomerListBeanList);
                                            menuAdapter.setOnItemClickListener(onItemClickListener);
                                            swipeMenuRecyclerView.setAdapter(menuAdapter);
                                        }else{
                                            menuAdapter.refresh(mCustomerListBeanList);
                                        }

                                        showProcessBar(100);
                                    }else{
                                        menuAdapter.add(ContentBeanlist);
                                        showProcessBar(100);
                                    }
                                    if((ContentBeanlist==null|| ContentBeanlist.size()==0) && page==0){
                                        tv_emptytext.setVisibility(View.VISIBLE);
                                        iv_empty.setVisibility(View.VISIBLE);
                                        swipeMenuRecyclerView.setVisibility(View.GONE);

                                    }else{
                                        swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                                        tv_emptytext.setVisibility(View.GONE);
                                        iv_empty.setVisibility(View.GONE);
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
    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_MULTI) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("转移")
                        .setTextColor(Res.getColor(null, "jiuyi_info2_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);
                SwipeMenuItem viewLine = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("|")
                        .setTextColor(Res.getColor(null, "color_line"))
                        .setWidth(Res.Dip2Pix(2))
                        .setHeight(height);
                swipeRightMenu.addMenuItem(viewLine);

                SwipeMenuItem addItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("释放")
                        .setTextColor(Res.getColor(null, "jiuyi_red_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem);
            }else if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_NODIRECT) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("认领")
                        .setTextColor(Res.getColor(null, "jiuyi_blue"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);
            }else if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_TRANSFER) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("转移")
                        .setTextColor(Res.getColor(null, "jiuyi_info2_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);
            }else if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_RELEASE) { // 是需要添加多个菜单的Item。
                SwipeMenuItem addItem = new SwipeMenuItem(mCallBack.getActivity())
                        .setBackgroundDrawable(R.drawable.tzt_line_background)
                        .setText("释放")
                        .setTextColor(Res.getColor(null, "jiuyi_red_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem);
            }
        }
    };


    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if(menuAdapter!=null){
                    MemberListBean.ContentBean  contentBean=menuAdapter.mViewTypeBeanList.get(adapterPosition);
                }

            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            finish();
        }
        return true;
    }
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(menuAdapter.mViewTypeBeanList!=null && menuAdapter.mViewTypeBeanList.size()>0){
                customerids=new long[menuAdapter.mViewTypeBeanList.size()];
                for(int i=0;i<menuAdapter.mViewTypeBeanList.size();i++){
                    MemberListBean.ContentBean contentBean=menuAdapter.mViewTypeBeanList.get(i);
                    if(contentBean!=null){
                        customerids[i]=contentBean.getId();
                    }
                }
                mBundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerids);
                mBundle.putInt(JiuyiBundleKey.PARAM_CUSTOMERPOSITION, position);
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, menuAdapter.mViewTypeBeanList.get(position).getId());
            }
            changePage(mBundle, Pub.Customer_main,true);
        }
    };

    @Override
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh){
            Rc.mBackfresh=false;
            getCustomerList(0);
        }

    }

}
