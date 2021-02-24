package customer.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.CustomerQueryConditionBean;
import com.control.utils.Pub;
import com.jiuyi.app.JiuyiMainApplication;
import com.tencent.qcloud.sdk.Constant;
import com.wanglicrm.android.R;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnCancelLisener;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiEditText;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.tools.CustomPopWindow;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.KeyBoardUtils;
import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.adapter.CustomerSelMenuAdapter;
import customer.adapter.CustomerStoreAdapter;
import customer.entity.BrandBean;
import customer.entity.CustomerStoreInfoBean;
import customer.view.SFPopupWindow;
import customer.view.jiuyiRecycleViewDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerStoreFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户门店
 *****************************************************************
 */
public class JiuyiCustomerStoreFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private LinearLayout ll_date, ll_province,ll_brand;
    private TextView tv_date, tv_province,tv_brand;
    private JiuyiEditText etSearch;
    private int[] imageids = { R.drawable.client_selected };
    private CustomerStoreAdapter adapter;
    private long Customerid=-1;
    private String Customername="";
    private String sDate="";
    RecyclerView swipeMenuRecyclerView;
    private  int mpage=0;
    private String msPlace="",provineID="";
    RefreshLayout refreshLayout;
    int pageIndex = 1;
    int pageSize = 20;
    private int totalPage=0;
    private LinearLayout ll_content;
    private TextView tv_no_authoritytext;
    private ImageView iv_no_authority;
    private ImageView iv_add;
    private CustomPopWindow mCustomPopWindow;
    private int curpostion=-1;

    List<String> menuStr1;
    private List<Map<String, Object>> menuData1;
    private CustomerSelMenuAdapter menuAdapter1;
    private ListView popListView;
    private SFPopupWindow popMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerstorefragment_layout"), null);
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
    public static JiuyiCustomerStoreFragment newInstance(int nPageType) {
        JiuyiCustomerStoreFragment f = new JiuyiCustomerStoreFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerStoreFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerStoreFragment f = new JiuyiCustomerStoreFragment();
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
        Customername=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        ll_content=(LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_content"));
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        initPopMenu();
        findView();
        getInfoList(0);
//        if(!MemberAuthorityBean.getIns().demandPlan){
//            tv_no_authoritytext.setVisibility(View.VISIBLE);
//            iv_no_authority.setVisibility(View.VISIBLE);
//            ll_content.setVisibility(View.GONE);
//        }else {
//            getInfoList(mpage);
//            getDenierList();
//            findView();
//            initMenuData();
//            initPopMenu();
//            mViewTypeBeanList = new ArrayList<>();
//        }


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_date:
                DatePickDialog dialog = new DatePickDialog(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity());
                //设置上下年分限制
                dialog.setYearLimt(60);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                dialog.setOnChangeLisener(null);
                dialog.setOnCancelListener(new OnCancelLisener() {
                    @Override
                    public void onCancel(String s) {
                        tv_date.setText("创建日期");
                        sDate="";
                        getInfoList(0);
                    }
                });
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        tv_date.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        sDate=tv_date.getText().toString();
                        getInfoList(0);
                    }
                });
                dialog.show();
                break;
            case R.id.ll_province:
                Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"PROVINCE");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 200);
                break;
            case R.id.ll_brand:
                getBrand();
                break;


        }
    }
    protected void findView() {

        ll_date = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_date"));
        ll_province = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_province"));
        ll_brand = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_brand"));
        ll_brand.setOnClickListener(this);
        tv_brand = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_brand"));
        tv_date = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_date"));
        tv_province = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_province"));
        ll_date.setOnClickListener(this);
        ll_province.setOnClickListener(this);
        iv_add = (ImageView) mRootView.findViewById(Res.getViewID(getContext(), "iv_add"));
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Customerid);
                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"storme-manage-entity");
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建门店");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newDynamicForm,true);
            }
        });
//        iv_add.setVisibility(View.GONE);
        etSearch= (JiuyiEditText) mRootView.findViewById(R.id.tv_search);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyBoardUtils.hideSoftInput(etSearch);
                    getInfoList(0);
                    return true;
                }
                return false;
            }
        });
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                getInfoList(0);
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
                    getInfoList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;

            }
        });

        swipeMenuRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_needplan);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity(), 1, false));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));


    }

    public  void getInfoList(final int page){
        if(page==0){
            pageIndex=1;
        }

        final QueryConditionBean   queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("customer.id");
        rulesBean.setOption("EQ");
        value.add(Customerid+"");
        rulesBean.setValues(value);
        rulesBeanList.add(rulesBean);
        if(!Func.IsStringEmpty(sDate) ){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("createdDate");
            rulesBean2.setOption("BTD");
            value2.add(sDate+" 00:00:00");
            value2.add(sDate+" 23:59:59");
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(provineID)){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("province.provinceId");
            rulesBean2.setOption("EQ");
            value2.add(provineID);
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        if(!Func.IsStringEmpty(etSearch.getText().toString().trim())){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("searchContent");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(etSearch.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }
        if(!Func.IsStringEmpty(tv_brand.getText().toString().trim()) && !tv_brand.getText().toString().trim().equals("不限")&&
        !tv_brand.getText().toString().trim().equals("品牌")){
            List<String> value2 = new ArrayList<String>();
            QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
            rulesBean2.setField("brandName");
            rulesBean2.setOption("LIKE_ANYWHERE");
            value2.add(tv_brand.getText().toString().trim());
            rulesBean2.setValues(value2);
            rulesBeanList.add(rulesBean2);
        }

        queryConditionBean.setRules(rulesBeanList);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("storme-manage-entity/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<CustomerStoreInfoBean>() {
                            @Override
                            public void onSuccess(CustomerStoreInfoBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<CustomerStoreInfoBean.ContentBean> contentBeanList=data.getContent();
                                    if(page==0){
                                        if(adapter==null){
                                            adapter = new CustomerStoreAdapter(R.layout.customer_item_store, contentBeanList);
                                            swipeMenuRecyclerView.setAdapter(adapter);
                                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                                    CustomerStoreInfoBean.ContentBean contentBean =(CustomerStoreInfoBean.ContentBean)adapter.getData().get(position);
                                                    if(contentBean!=null){
//                                                        if(contentBean.getCreatedBy().equals(Rc.id+"")){
//                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
//                                                        }else {
//                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
//                                                        }
                                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                                        mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                                        mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"storme-manage-entity");
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "门店详情");
                                                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newDynamicForm,true);
                                                    }
                                                }
                                            });
                                            adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                                                @Override
                                                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                                                    curpostion=position;
                                                    showPopMenu(view);
                                                    return false;
                                                }
                                            });
                                        }else{
                                            adapter.refresh(contentBeanList);
                                        }
                                        if(contentBeanList.size()==0||contentBeanList==null){
                                            adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                        }
                                    }else{
                                        adapter.add(contentBeanList);
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
    public void createBackReq(boolean IsBg) {
        super.createBackReq(IsBg);
        if(Rc.mBackfresh ){
            Rc.mBackfresh=false;
            getInfoList(0);
        }

    }
    @Override
    public void createReq(boolean IsBg) {
        getInfoList(0);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent){
        if(intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle bundle=null;
        switch (requestCode) {
            case 200:
                bundle = intent.getExtras();
                String customerName = bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                String code = bundle.getString(JiuyiBundleKey.PARAM_COMMONCODE);
                provineID=code;
                if (code != null && customerName != null) {
                    tv_province.setText(customerName);
                }else{
                    tv_province.setText("省份");
                }
                getInfoList(0);
                break;

            default:
                break;

        }
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction== DialogID.DialogDoNothing)
        {
            if(nKeyCode == KeyEvent.KEYCODE_BACK ){
                return;
            }
        }else if(nAction== DialogID.DialogDeleteTrue){
            if(nKeyCode == KeyEvent.KEYCODE_ENTER){
                if(adapter!=null && curpostion!=-1){
                    deleteRecord(adapter.getData().get(curpostion));
                }
            }
        }
    }

   /* public void deleteRecord(long id){
        JiuyiHttp.DELETE("storme-manage-entity/delete/"+id)
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            if(adapter!=null && curpostion!=-1){
                                adapter.remove(curpostion);
                                adapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(adapter.getData()==null||adapter.getData().size()==0){
                                    adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }*/

    public void deleteRecord(CustomerStoreInfoBean.ContentBean contentBean){
        contentBean.setDeleted( true );
        JiuyiHttp.PUT("storme-manage-entity/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(contentBean))
                .request(new ACallback<CustomerStoreInfoBean.ContentBean>() {
                    @Override
                    public void onSuccess(CustomerStoreInfoBean.ContentBean data) {
                        if(data!=null){
                            getInfoList(0);
                           /* if(adapter!=null && curpostion!=-1){
                                adapter.remove(curpostion);
                                adapter.notifyDataSetChanged();
                                curpostion=-1;
                                if(adapter.getData()==null||adapter.getData().size()==0){
                                    adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) swipeMenuRecyclerView.getParent());
                                }
                            }*/
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

    private void showPopMenu(View v){
        View contentView = LayoutInflater.from(mCallBack.getActivity()).inflate(R.layout.jiuyi_pop_delete_menu,null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(mCallBack.getActivity())
                .setView(contentView)
                .create()
                .showAsDropDown(v,0,0);
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
                JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
                switch (v.getId()){
                    case R.id.menu1:
                        startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条记录？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                        break;
                }

            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
    }

    private void getBrand() {
        CustomerQueryConditionBean queryConditionBean=new CustomerQueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        ArrayList rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("brand/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization",Rc.getIns().id_tokenparam)
                        .request(new ACallback<BrandBean>() {
                            @Override
                            public void onSuccess(BrandBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    List<BrandBean.ContentBean> contentBeanList=data.getContent();
                                    menuData1 =  new ArrayList<Map<String, Object>>();
                                    Map<String, Object>  map1;
                                    map1 = new HashMap<String, Object>();
                                    map1.put("name", "不限");
                                    map1.put("select", imageids[0]);
                                    menuData1.add(map1);
                                    for (int i = 1, len =contentBeanList.size(); i <=len; ++i) {
                                        map1 = new HashMap<String, Object>();
                                        map1.put("name", contentBeanList.get(i-1).getBrandName());
                                        map1.put("select", imageids[0]);
                                        menuData1.add(map1);
                                    }
                                    menuAdapter1=new CustomerSelMenuAdapter(JiuyiMainApplication.getIns(), menuData1);
//                                    tv_brand.setTextColor(Res.getColor(null, "jiuyi_blue"));
                                    popListView.setAdapter(menuAdapter1);
                                    popMenu.showAsDropDown(tv_brand, 0, 2);
                                }
                                popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                                            long arg3) {
                                        //改变选中状态
                                        menuAdapter1.setCurrentItem(pos);
                                        //通知ListView改变状态
                                        menuAdapter1.notifyDataSetChanged();
                                        popMenu.dismiss();
                                        tv_brand.setText(menuData1.get(pos).get("name").toString());
                                        getInfoList(0);
                                    }
                                });
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

    private void initPopMenu() {
        View contentView = View.inflate( JiuyiMainApplication.getIns(), R.layout.popwin_supplier_list,
                null);
        popMenu = new SFPopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
//                tv_brand.setTextColor( Color.parseColor("#5a5959"));
            }
        });

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });
    }

}
