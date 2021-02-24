package mine.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Pub;
import com.jiuyi.tools.CustomPopWindow;
import com.wanglicrm.android.R;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiDateUtil;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiMainApplication;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.qcloud.sdk.Constant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commonlyused.adapter.TaskListAdapter;
import commonlyused.bean.QueryConditionBean;
import commonlyused.bean.ServerDate;
import commonlyused.bean.TaskBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.ReceiveAddressAdapter;
import customer.view.jiuyiRecycleViewDivider;
import mine.adapter.SignListAdapter;
import mine.bean.MineSignListBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerVisitListFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 签到记录
 *****************************************************************
 */
public class JiuyiMineSignListFragment extends JiuyiFragmentBase
         {
    private LinearLayout ll_begin,ll_end;
    private TextView tv_endtime,tv_begintime,tv_query;
    RefreshLayout refreshLayout;
    private  int pageIndex=1,pagesize=20,totalPage=0;
    RecyclerView rvInfolist;
    private SignListAdapter adapter;
    private String taskdate ="";
    QueryConditionBean queryConditionBean;
    private List<MineSignListBean.ContentBean> mBeanList;
    ImageButton mibadd;
    private CustomPopWindow mCustomPopWindow;
    private int curpostion=-1;
    private MineSignListBean.ContentBean contentBean;
    private String sServerDatetime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_minesignlistfragment_layout"), null);
//        setOnRefreshListener();
        onInit();
        return mRootView;
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineSignListFragment newInstance(int nPageType) {
        JiuyiMineSignListFragment f = new JiuyiMineSignListFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineSignListFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineSignListFragment f = new JiuyiMineSignListFragment();
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
        getServerDate();
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null, Pub.Mine_Sign,true);
                }
            });
        }
        ll_begin= (LinearLayout) mRootView.findViewById(R.id.ll_begin);
        ll_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        tv_begintime.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                    }
                });
                dialog.show();
            }
        });
        ll_end= (LinearLayout) mRootView.findViewById(R.id.ll_end);
        ll_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        tv_endtime.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                    }
                });
                dialog.show();
            }
        });
        tv_begintime= (TextView) mRootView.findViewById(R.id.tv_begintime);
        tv_begintime.setText(JiuyiDateUtil.getBeforeTime(-7));
        tv_endtime= (TextView) mRootView.findViewById(R.id.tv_endtime);
        tv_endtime.setText(JiuyiDateUtil.getNowTime3());
        tv_query= (TextView) mRootView.findViewById(R.id.tv_query);
        tv_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(JiuyiDateUtil.isDateOneBigger(tv_begintime.getText().toString(),tv_endtime.getText().toString())){
                    Toast.makeText(JiuyiMainApplication.getIns(), "开始日期不能大于结束日期", Toast.LENGTH_SHORT).show();
                    return;
                }
                pageIndex=1;
                getInfoList(0);
            }
        });
        rvInfolist = (RecyclerView) mRootView.findViewById(R.id.rv_infolist);
        rvInfolist.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns(), 1, false));
        rvInfolist.setHasFixedSize(true);
        rvInfolist.setItemAnimator(new DefaultItemAnimator());
        rvInfolist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));
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
        getInfoList(0);

    }




     public  void getInfoList(final int page){
         getServerDate();
         showProcessBar(0);
         queryConditionBean=builderCondition(page);
         JiuyiHttp.POST("sign-in/page")
                 .setJson(GsonUtil.gson().toJson(queryConditionBean))
                 .addHeader("Authorization", Rc.getIns().id_tokenparam)
                 .request(new ACallback<MineSignListBean>() {
                     @Override
                     public void onSuccess(MineSignListBean data) {
                         if(data!=null){
                             totalPage=data.getTotalPages();
                             mBeanList =data.getContent();
                             if(page==0){
                                 if(adapter==null){
                                     adapter = new SignListAdapter(R.layout.jiuyi_sign_item, mBeanList);
                                     rvInfolist.setAdapter(adapter);
                                     adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                                         @Override
                                         public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                                             curpostion=position;
                                             contentBean=(MineSignListBean.ContentBean)adapter.getData().get(position);
                                             if(contentBean!=null && (contentBean.getAddress()==null||Func.IsStringEmpty(contentBean.getAddress()))
                                                      ){
                                                 if( JiuyiDateUtil.getTimeExpend(contentBean.getSignInDate(),sServerDatetime)<=30){
                                                     showPopMenu(view);
                                                 }else{
                                                     Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "已超出补签时间，不能重签!", Toast.LENGTH_SHORT).show();
                                                 }

                                             }
                                             return false;
                                         }
                                     });

                                 }else{
                                     adapter.refresh(mBeanList);
                                 }
                                 if(mBeanList.size()==0|| mBeanList ==null){
                                     adapter.setEmptyView(R.layout.layout_empty_view, (ViewGroup) rvInfolist.getParent());
                                 }

                             }else{
                                 adapter.add(mBeanList);
                             }
                             showProcessBar(100);
                         }
                     }

                     @Override
                     public void onFail(int errCode, String errMsg) {
                         showProcessBar(100);
                         startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                     }
                 });


     }
     public QueryConditionBean builderCondition(int page){
         queryConditionBean=new QueryConditionBean();
         queryConditionBean.setNumber(page);
         queryConditionBean.setSize(pagesize);
         queryConditionBean.setDirection("DESC");
         queryConditionBean.setProperty("createdDate");
         queryConditionBean.setFromClientType("android");
         List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
         if(!Func.IsStringEmpty(tv_begintime.getText().toString()) && !Func.IsStringEmpty(tv_endtime.getText().toString())){
             List<String> value = new ArrayList<String>();
             QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
             rulesBean.setField("signInDate");
             rulesBean.setOption("BTD");
             value.add(tv_begintime.getText().toString().trim()+" 00:00:00");
             value.add(tv_endtime.getText().toString().trim()+" 23:59:59");
             rulesBean.setValues(value);
             rulesBeanList.add(rulesBean);
         }

         if(rulesBeanList.size()>0){
             queryConditionBean.setRules(rulesBeanList);
         }
         return queryConditionBean;
     }

     @Override
     public void createReq(boolean IsBg) {
         getInfoList(0);
     }
     @Override
     public void createBackReq(boolean IsBg) {
         super.createBackReq(IsBg);
         if(Rc.mBackfresh){
             Rc.mBackfresh=false;
             getInfoList(0);
         }

     }
     @Override
     public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
         if(nAction== DialogID.DialogDoNothing)
         {
             if(nKeyCode == KeyEvent.KEYCODE_BACK ){
                 return;
             }
         }


     }

         private void showPopMenu(View v){
             View contentView = LayoutInflater.from(mCallBack.getActivity()).inflate(R.layout.jiuyi_pop_sign_menu,null);
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
                     mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN, contentBean);
                     mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "修改打卡地址");
                     mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                     changePage(mBundle, Pub.Mine_Sign,true);

                 }
             };
             contentView.findViewById(R.id.menu1).setOnClickListener(listener);
         }

         private void getServerDate() {
             JiuyiHttp.GET("date/today")
                     .tag("request_get_")
                     .addHeader("Authorization", Rc.id_tokenparam)
                     .request(new ACallback<ServerDate>() {
                         @Override
                         public void onSuccess(ServerDate data) {
                             if(data!=null){
                                 sServerDatetime=data.getTodayNow();

                             }
                         }

                         @Override
                         public void onFail(int errCode, String errMsg) {

                         }
                     });

         }


     }


