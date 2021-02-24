package customer.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.common.GsonUtil;
import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.tools.NormalPinyinComparator;
import com.jiuyi.tools.PinyinUtils;
import com.jiuyi.tools.SideBar;
import com.recyclerview.swipe.Closeable;
import com.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.recyclerview.swipe.SwipeMenu;
import com.recyclerview.swipe.SwipeMenuCreator;
import com.recyclerview.swipe.SwipeMenuItem;
import com.recyclerview.swipe.SwipeMenuRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import commonlyused.adapter.NormalDeptLinkmanSortAdapter;
import commonlyused.bean.ContactBean;
import commonlyused.bean.LinkmanBean;
import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.CustomerAssistantAdapter;
import customer.entity.CustomerAssistantBean;
import customer.entity.UpdateAssistantBean;
import customer.entity.VisibleTypeBean;
import customer.entity.VisitIntelligenceBean;
import customer.listener.OnItemClickListener;
import customer.view.ListViewDecoration;

import static customer.adapter.NewSpecialProductAdapter.VIEW_TYPE_MENU_DELETE;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNormalCustomerContactActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 :客户协助人
 *****************************************************************
 */
public class JiuyiCustomerAssistantActivity extends JiuyiActivityBase {
    private SwipeMenuRecyclerView mRecyclerView;
    private CustomerAssistantAdapter adapter;
    LinearLayoutManager manager;
    private List<CustomerAssistantBean> SourceDateList;
    private TextView tv_emptytext;
    private ImageView iv_empty;
    private long customerId=-1;
    RefreshLayout refreshLayout;
    private RelativeLayout rv_bottom;
    private ArrayList<UpdateAssistantBean> assistantSelectList;
    private ImageView   iv_back;

    @Override
    public void onInit() {
        customerId=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_customerassistantlist_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        setContentView(mBodyLayout);
        setTitle();
        iv_back = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });
        rv_bottom=(RelativeLayout) mBodyLayout.findViewById(R.id.rv_bottom);
        rv_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,customerId);
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.ADD);
                changePage(mBundle,Pub.Customer_NewAssistant,true);

            }
        });
        tv_emptytext=(TextView)mBodyLayout.findViewById(R.id.tv_emptytext);
        iv_empty=(ImageView)mBodyLayout.findViewById(R.id.iv_empty);

        RefreshLayout refreshLayout = (RefreshLayout)mBodyLayout.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getContactList(customerId);
                refreshlayout.finishRefresh(2000);
                refreshlayout.setLoadmoreFinished(false);
            }
        });

        mRecyclerView = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(JiuyiCustomerAssistantActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new ListViewDecoration());
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        showProcessBar(0);
        getContactList(customerId);

    }
    @Override
    public void backPage() {
        Rc.mBackfresh=true;
        EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
        super.backPage();
    }

    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (viewType == VIEW_TYPE_MENU_DELETE) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(JiuyiCustomerAssistantActivity.this)
                        .setBackgroundDrawable(R.drawable.tzt_red)
                        .setText("删除")
                        .setTextColor(Res.getColor(null, "jiuyi_white_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);

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
                if (adapter != null) {
                    CustomerAssistantBean contentBean = adapter.mViewTypeBeanList.get(adapterPosition);
                    if (contentBean != null) {
                        adapter.mViewTypeBeanList.remove(adapterPosition);
                        deleteAssistant(contentBean.getId());
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        }
    };
    public void deleteAssistant(long id){
        JiuyiHttp.DELETE("member-assist/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

    public void setTitle(){
        mTitle = "客户协助人";
        setTitle(mTitle);
    }
    public  void  getContactList(long id){
        JiuyiHttp.GET("member-assist/findOperator/"+id)
                .tag("request_get_")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<CustomerAssistantBean>>() {
                    @Override
                    public void onSuccess(List<CustomerAssistantBean> returndata) {
                        if(returndata!=null){
                            adapter=new CustomerAssistantAdapter(returndata);
                            adapter.setOnItemClickListener(onItemClickListener);
                            mRecyclerView.setAdapter(adapter);
                        }
                        showProcessBar(100);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserEvent(JiuyiEventBusEvent event) {
        if (event == null) {
            return;
        }

        switch (event.getEventType()){
            default:
                super.onUserEvent(event);
                break;
        }
    }
    @Override
    public void createReq(final boolean IsBg) {
        getContactList(customerId);
    }
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(adapter!=null && adapter.mViewTypeBeanList.size()>0){
                CustomerAssistantBean contentBean=adapter.mViewTypeBeanList.get(position);
                mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN, contentBean);
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.EDIT);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"查看协助人");
               changePage(mBundle, Pub.Customer_NewAssistant,true);
            }

        }
    };
}
