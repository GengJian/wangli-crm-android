package commonlyused.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.control.widget.JiuyiBigTextGroup;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.nanchen.compresshelper.CompressHelper;
import com.recyclerview.swipe.Closeable;
import com.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.recyclerview.swipe.SwipeMenu;
import com.recyclerview.swipe.SwipeMenuCreator;
import com.recyclerview.swipe.SwipeMenuItem;
import com.recyclerview.swipe.SwipeMenuRecyclerView;
import com.wanglicrm.android.R;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import commonlyused.adapter.NewSignIntentMutiAdapter;
import commonlyused.adapter.NewVisitIntentMutiAdapter;
import commonlyused.bean.ChannelDevelopBean;
import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.PlanTargetBean;
import commonlyused.bean.PlanTargetProvinceBrandBean;
import commonlyused.bean.ProvinceAndBrand;
import commonlyused.bean.SumActualShipAndDayBean;
import commonlyused.bean.SumActualShipmentBean;
import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.entity.AttachmentBean;
import customer.entity.Media;
import customer.listener.OnItemClickListener;
import customer.listener.PickerConfig;
import customer.view.JiuyiAttachment;
import customer.view.jiuyiRecycleViewDivider;

import static customer.adapter.NewSpecialProductAdapter.VIEW_TYPE_MENU_DELETE;

/**
 * ****************************************************************
 * 文件名称 : JiuyiRetailChannelNewActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 零售渠道部计划提报和总结录入界面
 *****************************************************************
 */
public class JiuyiChannelDevelopNewActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private String operatorType,sTitle,s_returnvalue;
    private ImageView   iv_back;
    private LinearLayout ll_content;
    private JiuyiItemGroup jigSalesman,jig_province;
    private JiuyiItemGroup jigSalesTarget,jig_cumulativeShipments;
    private JiuyiItemGroup jigProjectedShipment;
    private JiuyiItemGroup jigActualShipment;
    private JiuyiItemGroup jigOmpletionRate;
    private JiuyiItemGroup jigMarkete;
    private JiuyiItemGroup jigExpectVisit;
    private JiuyiItemGroup jigActualVisit;
    private JiuyiItemGroup jigDate,jigWorkPlanDate;
    private JiuyiBigTextGroup jigRemark;
    private SwipeMenuRecyclerView rvDetaillist,rv_signdetaillist;

    private JiuyiAttachment ahAttach;
    private NewVisitIntentMutiAdapter menuAdapter;
    private NewSignIntentMutiAdapter signIntentMutiAdapter;
    private RelativeLayout rvNew,rv_signnew;
    private String operType="";
    private static final int VISIT_CODE = 100;
    private ChannelDevelopBean.VisitIntentionsBean visitIntentionsBean;
    private List<ChannelDevelopBean.VisitIntentionsBean> visitIntentionsBeans;

    private ChannelDevelopBean.SignIntentionsBean signIntentionsBean;
    private List<ChannelDevelopBean.SignIntentionsBean> signIntentionsBeans;
    private int currentPosition=-1,currentSignPosition=-1;
    private ChannelDevelopBean.ContentBean createBean;
    private long billID=0;
    protected static final int TAKE_PICTURE = 1000;
    protected static final int SELECT_PICTURE = 1500;
    private  File[] files;
    private List<AttachmentBean> attachmentsBeanslist,attachmentsEditBeanslist;
    private String filePath;
    private Boolean needUpload=false;
    private String backEndFlag="channel-development";
    //申明对象
    CityPickerView mCityPickerView=new CityPickerView();
    private String msprovince="";
    private String mscity="";
    private String msdistrict="";
    private Map<String, Object> inputParamMap= new HashMap<String, Object>();
    private List<PlanTargetBean> planTargetBeanList;



    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newchanneldevelop_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        operatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }
        if(Func.IsStringEmpty(operatorType)){
            operatorType="";
        }
        if(operatorType.equals(ViewOperatorType.ADD)){
            createBean=new ChannelDevelopBean.ContentBean();
        }
        initViews();
        if(operatorType.equals(ViewOperatorType.ADD)){
            NormalOperatorBean.OperatorBeanID operatorBeanID=new NormalOperatorBean.OperatorBeanID();
            operatorBeanID.setId(Rc.id);
            createBean.setOperator(operatorBeanID);
            inputParamMap.put("id",Rc.id);
            inputParamMap.put("oaCode","");
            jig_province.setText("");
            jig_cumulativeShipments.setText("");
            jigOmpletionRate.setText("0.00");
            getPlanTarget(inputParamMap);
        }
        if(operatorType.equals(ViewOperatorType.ADD)){
            getInfo();
        }
        if(operatorType.equals(ViewOperatorType.EDIT)||operatorType.equals(ViewOperatorType.VIEW)||operatorType.equals(ViewOperatorType.SPECIAL)){//
            billID=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
            if(billID>0){
                getDetailInfo(billID);
            }
        }
        mCityPickerView.init(this);
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setConfirmTextColorStr("#0089d1");
        mCityPickerView.setConfig(cityConfig);
        setTitle();
        iv_back = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            if(operatorType.equals(ViewOperatorType.VIEW)){
                mtvcomplete.setVisibility(View.INVISIBLE);
                rvNew.setVisibility(View.GONE);
                rv_signnew.setVisibility(View.GONE);
                disableSubControls(ll_content);
            }
            mtvcomplete.setText("提交");
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Rc.mBackfresh=true;
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
                    showDialog();
                    if(operatorType.equals(ViewOperatorType.ADD)){
                        createBean();
                    }else if(operatorType.equals(ViewOperatorType.EDIT)||operatorType.equals(ViewOperatorType.SPECIAL)){
                        updateInfo();
                    }
                }
            });

        }


    }
    public void initViews(){
        ll_content= (LinearLayout) mBodyLayout.findViewById(R.id.ll_content);
        jigSalesman = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_salesman);
        jigSalesman.setText(Rc.name);
        jigSalesman.setItemOnClickListener(null);
//        jigSalesman.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
//            @Override
//            public void onItemClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
//                Bundle mBundle = new Bundle();
//                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
//                intent.putExtras(mBundle);
//                startActivityForResult(intent, 200);
//
//            }
//        });
        jig_province = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_province);
        jig_province.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                if(Func.IsStringEmpty(jigSalesman.getText())){
                    Toast.makeText(JiuyiChannelDevelopNewActivity.this, "请先选择业务员", Toast.LENGTH_SHORT).show();
                    return;
                }
              getPlanTargetNew( inputParamMap );

            }
        });
        jigSalesTarget = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_salesTarget);
        jig_cumulativeShipments = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_cumulativeShipments);
        jigProjectedShipment = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_projectedShipment);
        jigProjectedShipment.contentEdt.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
        jigActualShipment = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_actualShipment);
        jigActualShipment.contentEdt.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
//        jigActualShipment.contentEdt.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(!Func.IsStringEmpty(s.toString())){
//                    if(!Func.IsStringEmpty(jigSalesTarget.getText().toString()) && !Func.IsStringEmpty(jig_cumulativeShipments.getText().toString())){
//                        double total;
//                        total=Double.parseDouble(jigSalesTarget.getText().toString())+Double.parseDouble(jig_cumulativeShipments.getText().toString());
//                        if(total>0){
//                            double num= Double.parseDouble(Double.parseDouble(s.toString())/total+"");
//                            DecimalFormat df = new DecimalFormat("0.00");
//                            String sfor = df.format(num*100);
//                            jigOmpletionRate.setText(sfor);
//                        }
//
//                    }
//
//                }else{
//                    jigOmpletionRate.setText("0.00");
//                }
//            }
//        });

        jigOmpletionRate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_ompletionRate);
        jigMarkete = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_markete);
        jigMarkete.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                hideKeyboard();
                mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        StringBuilder sb = new StringBuilder();
                        if (province != null) {
                            msprovince=province.getName();
                            sb.append(province.getName() /*+ " " + province.getId()*/ );
                            createBean.setProvinceName(msprovince);
                            createBean.setProvinceNumber(province.getId());
                        }

                        if (city != null) {
                            mscity=city.getName();
                            sb.append(city.getName()/* + " " + city.getId()*/ );
                            createBean.setCityName(mscity);
                            createBean.setCityNumber(city.getId());
                        }

                        if (district != null) {
                            msdistrict=district.getName();
                            sb.append(district.getName() /*+ " " + district.getId()*/ );
                            createBean.setAreaName(msdistrict);
                            createBean.setAreaNumber(district.getId());
                        }else{
                            msdistrict="";
                        }

                        jigMarkete.setText("" + sb.toString());

                    }

                    @Override
                    public void onCancel() {
                    }
                });
                mCityPickerView.showCityPicker();
            }
        });
        jigExpectVisit = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_expectVisit);
        jigActualVisit = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_actualVisit);
        ahAttach = (JiuyiAttachment) mBodyLayout.findViewById(R.id.ah_attach);
        jigDate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_date);
        jigWorkPlanDate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_workplandate);
        jigRemark = (JiuyiBigTextGroup) mBodyLayout.findViewById(R.id.jig_remark);


        jigWorkPlanDate.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
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
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        String sDate=new SimpleDateFormat("yyyy-MM-dd").format(date);
                        jigWorkPlanDate.setText(sDate);
                        createBean.setWorkPlanDate(sDate);
                    }
                });
                dialog.show();
            }
        });


        if(operatorType.equals(ViewOperatorType.ADD)){
            if(!Func.IsStringEmpty(Rc.serviceDate)){
                jigDate.setText(Rc.serviceDate);
            }else{
                jigDate.setText(JiuyiDateUtil.getNowTime3());
            }
//            jigWorkPlanDate.setText(JiuyiDateUtil.getBeforeTime(1));
            createBean.setDate(jigDate.getText().trim());
//            createBean.setWorkPlanDate(jigWorkPlanDate.getText().trim());
        }else{
            jigWorkPlanDate.setItemOnClickListener(null);
        }
        jigDate.setItemOnClickListener(null);

        rvDetaillist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_detaillist);
        rvDetaillist.setLayoutManager(new LinearLayoutManager(JiuyiChannelDevelopNewActivity.this));
        rvDetaillist.setHasFixedSize(true);
        rvDetaillist.setItemAnimator(new DefaultItemAnimator());
        rvDetaillist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiChannelDevelopNewActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rvDetaillist.setSwipeMenuCreator(swipeMenuCreator);
        rvDetaillist.setSwipeMenuItemClickListener(menuItemClickListener);
        rvNew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_new);
        rvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Func.IsStringEmpty(jigSalesman.getText())){
                    startDialog(DialogID.DialogDoNothing, "", "请选择业务员！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), JiuyiNewVisitIntentDtailMutiActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                startActivityForResult(intent, VISIT_CODE);
            }
        });
        rv_signdetaillist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_signdetaillist);
        rv_signdetaillist.setLayoutManager(new LinearLayoutManager(JiuyiChannelDevelopNewActivity.this));
        rv_signdetaillist.setHasFixedSize(true);
        rv_signdetaillist.setItemAnimator(new DefaultItemAnimator());
        rv_signdetaillist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiChannelDevelopNewActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rv_signdetaillist.setSwipeMenuCreator(swipeMenuCreator);
        rv_signdetaillist.setSwipeMenuItemClickListener(menuItemClickListener2);
        rv_signnew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_signnew);
        rv_signnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiNewSignIntentionDtailMutiActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                startActivityForResult(intent, 300);
            }
        });

        ahAttach = (JiuyiAttachment) mBodyLayout.findViewById(R.id.ah_attach);
        ahAttach.setAdapter();

        visitIntentionsBeans =new ArrayList<>();
        menuAdapter = new NewVisitIntentMutiAdapter(visitIntentionsBeans);
        menuAdapter.setOnItemClickListener(onItemClickListener);
        menuAdapter.setViewType("VIEW");
        if(operatorType.equals(ViewOperatorType.VIEW)){
            rvDetaillist.setSwipeMenuCreator(null);
        }
        rvDetaillist.setAdapter(menuAdapter);

        signIntentionsBeans =new ArrayList<>();
        signIntentMutiAdapter = new NewSignIntentMutiAdapter(signIntentionsBeans);
        signIntentMutiAdapter.setOnItemClickListener(onItemClickListener2);
        signIntentMutiAdapter.setViewType("VIEW");
        if(operatorType.equals(ViewOperatorType.VIEW)){
            rv_signdetaillist.setSwipeMenuCreator(null);
        }
        rv_signdetaillist.setAdapter(signIntentMutiAdapter);
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && this.getCurrentFocus() != null) {
            if (this.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }

    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else{
            mTitle = "新建渠道开发部计划";
        }
        setTitle(mTitle);
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
            if (viewType == VIEW_TYPE_MENU_DELETE) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(JiuyiChannelDevelopNewActivity.this)
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
                if(menuAdapter!=null){
                    ChannelDevelopBean.VisitIntentionsBean contentBean=menuAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        menuAdapter.mViewTypeBeanList.remove(adapterPosition);
                        menuAdapter.notifyDataSetChanged();
                        setShipmentData();
                    }
                }
            }
        }
    };
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(menuAdapter!=null){
                ChannelDevelopBean.VisitIntentionsBean contentBean=menuAdapter.mViewTypeBeanList.get(position);
                if(contentBean!=null){
                    currentPosition=position;
                    if(operatorType.equals(ViewOperatorType.VIEW)){
                        operType=ViewOperatorType.VIEW;
                    }else{
                        operType=ViewOperatorType.EDIT;
                    }
                    Intent intent = new Intent(getApplicationContext(), JiuyiNewVisitIntentDtailMutiActivity.class);
                    intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, operType);
                    intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                    intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN, contentBean);
                    startActivityForResult(intent, 101);
                }
            }
        }
    };


    private OnItemClickListener onItemClickListener2 = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(signIntentMutiAdapter!=null){
                ChannelDevelopBean.SignIntentionsBean contentBean=signIntentMutiAdapter.mViewTypeBeanList.get(position);
                if(contentBean!=null){
                    currentSignPosition=position;
                    if(operatorType.equals(ViewOperatorType.VIEW)){
                        operType=ViewOperatorType.VIEW;
                    }else{
                        operType=ViewOperatorType.EDIT;
                    }
                    Intent intent = new Intent(getApplicationContext(), JiuyiNewSignIntentionDtailMutiActivity.class);
                    intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, operType);
                    intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                    intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN, contentBean);
                    startActivityForResult(intent, 301);
                }
            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener2 = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if(signIntentMutiAdapter!=null){
                    ChannelDevelopBean.SignIntentionsBean contentBean=signIntentMutiAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        signIntentMutiAdapter.mViewTypeBeanList.remove(adapterPosition);
                        signIntentMutiAdapter.notifyDataSetChanged();
                        int count=0;
                        for(int i=0;i<signIntentMutiAdapter.mViewTypeBeanList.size();i++){
                            if(signIntentMutiAdapter.mViewTypeBeanList.get(i).isSign()){
                                count++;
                            }
                        }
                        jigExpectVisit.setText(signIntentionsBeans.size()+"");
                        jigActualVisit.setText(count+"");
                    }
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((data == null || data.getExtras() == null)&& requestCode!=TAKE_PICTURE) {
            return;
        }
        Bundle bundle;
        switch (requestCode) {
            case 100:
                bundle = data.getExtras();
                visitIntentionsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (visitIntentionsBean != null) {
                    if (visitIntentionsBeans == null) {
                        visitIntentionsBeans = new ArrayList<>();
                    }
                    visitIntentionsBeans.add(visitIntentionsBean);
                    if (menuAdapter == null) {
                        menuAdapter = new NewVisitIntentMutiAdapter(visitIntentionsBeans);
                        menuAdapter.setOnItemClickListener(onItemClickListener);
                        rvDetaillist.setAdapter(menuAdapter);
                    } else {
                        menuAdapter.setmViewTypeBeanList(visitIntentionsBeans);
                        menuAdapter.notifyDataSetChanged();
                    }
                    setShipmentData();
                }
                break;
            case 101:
                bundle = data.getExtras();
                visitIntentionsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (visitIntentionsBean != null) {
                    if (visitIntentionsBeans != null && visitIntentionsBeans.size() > 0 && currentPosition >= 0) {
                        visitIntentionsBeans.set(currentPosition, visitIntentionsBean);
                        menuAdapter.setmViewTypeBeanList(visitIntentionsBeans);
                        menuAdapter.notifyDataSetChanged();
                        setShipmentData();
                    }

                }
                break;
            case 200:
                bundle = data.getExtras();
                s_returnvalue=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long valueId=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(valueId>0 && s_returnvalue!=null ){
                    jigSalesman.setText(s_returnvalue);
                    NormalOperatorBean.OperatorBeanID operatorBeanID=new NormalOperatorBean.OperatorBeanID();
                    operatorBeanID.setId(valueId);
                    createBean.setOperator(operatorBeanID);
                    inputParamMap.put("id",valueId);
                    inputParamMap.put("oaCode","");
                    jig_province.setText("");
                    jig_cumulativeShipments.setText("");
                    jigOmpletionRate.setText("0.00");
                    getPlanTarget(inputParamMap);
                }
                break;
            case 300:
                bundle = data.getExtras();
                signIntentionsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (signIntentionsBean != null) {
                    if (signIntentionsBeans == null) {
                        signIntentionsBeans = new ArrayList<>();
                    }
                    signIntentionsBeans.add(signIntentionsBean);
                    if (signIntentMutiAdapter == null) {
                        signIntentMutiAdapter = new NewSignIntentMutiAdapter(signIntentionsBeans);
                        signIntentMutiAdapter.setOnItemClickListener(onItemClickListener2);
                        rv_signdetaillist.setAdapter(signIntentMutiAdapter);
                    } else {
                        signIntentMutiAdapter.setmViewTypeBeanList(signIntentionsBeans);
                        signIntentMutiAdapter.notifyDataSetChanged();
                    }
                    int count=0;
                    for(int i = 0; i< signIntentionsBeans.size(); i++){
                        if(signIntentionsBeans.get(i).isSign()){
                            count++;
                        }
                    }
                    jigExpectVisit.setText(signIntentionsBeans.size()+"");
                    jigActualVisit.setText(count+"");
                }
                break;
            case 301:
                bundle = data.getExtras();
                signIntentionsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (signIntentionsBean != null) {
                    if (signIntentionsBeans != null && signIntentionsBeans.size() > 0 && currentSignPosition >= 0) {
                        signIntentionsBeans.set(currentSignPosition, signIntentionsBean);
                        signIntentMutiAdapter.setmViewTypeBeanList(signIntentionsBeans);
                        signIntentMutiAdapter.notifyDataSetChanged();
                        int count=0;
                        for(int i = 0; i< signIntentionsBeans.size(); i++){
                            if(signIntentionsBeans.get(i).isSign()){
                                count++;
                            }
                        }
                        jigExpectVisit.setText(signIntentionsBeans.size()+"");
                        jigActualVisit.setText(count+"");
                    }

                }
                break;
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK) { //
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  // 检测sd是否可用
                        Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    if(!Func.IsStringEmpty(Rc.picVideoUrl)){
                        filePath=Rc.picVideoUrl;
                        Rc.picVideoUrl="";
                    }

                    //优化压缩图片
                    Uri uri = null;
                    File file1 = new File(filePath);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                    Media media=new Media();
                    media.setExtension(".jpg");
                    media.setMediaType(0);
                    media.setPath(newFile.getPath());
                    ahAttach.adapter.getMviewBeanList().add(media);
                    ahAttach.refreshAttach();
                }
                break;
            case SELECT_PICTURE:
                ArrayList<Media> selects = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                ahAttach.setMediaArrayList(selects);
                ahAttach.adapter.setMviewBeanList(selects);
                ahAttach.refreshAttach();
                break;

            default:
                break;

        }
    }

    public boolean check(){
        if(Func.IsStringEmpty(jig_province.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择省份！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jigSalesTarget.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "当月开发目标不能为空！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jigWorkPlanDate.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择工作计划日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jig_cumulativeShipments.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "当月累计拜访量不能为空！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(menuAdapter!=null && menuAdapter.mViewTypeBeanList.size()<3){
            startDialog(DialogID.DialogDoNothing, "", "拜访意向客户不能少于3家！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
//        if(Func.IsStringEmpty(jigProjectedShipment.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "当日拜访意向客户不能为空！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
//        if(Func.IsStringEmpty(jigActualShipment.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请输入当日实际发货量！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
//        if(Func.IsStringEmpty(jigContent.getText().toString().trim())){
//            startDialog(DialogID.DialogDoNothing, "", "请输入内容！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
        return true;
    }
    public void updateInfo(){
        if(createBean==null){
            createBean =new ChannelDevelopBean.ContentBean();
            createBean.setId(billID);
        }
        setBean();
        JiuyiHttp.PUT(backEndFlag+"/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(createBean))
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
                });
    }
    private void getInfo() {
        showLoadingDialog();
        JiuyiHttp.POST(backEndFlag+"/getInfo")
                .tag("request_get_"+backEndFlag)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ChannelDevelopBean.ContentBean>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(ChannelDevelopBean.ContentBean contentBean) {
                        if(contentBean!=null && contentBean.getId()!=null){
                            createBean=contentBean;
                            createBean.setId( null );
                            if(contentBean.getOperator()!=null && contentBean.getOperator().getName()!=null ){
                                jigSalesman.setText(contentBean.getOperator().getName());
                                inputParamMap.put("id",contentBean.getOperator().getId());
                                inputParamMap.put("oaCode","");
                                getPlanTarget(inputParamMap);
                            }
                            jigSalesTarget.setText(contentBean.getDevelopmentProject()+"");
                            if(contentBean.getProvince()!=null){
                                jig_province.setText(contentBean.getProvince());
                            }
                            jig_cumulativeShipments.setText(contentBean.getAccumulateVisit()+"");
                            jigProjectedShipment.setText(contentBean.getVisitIntentional()+"");
                            jigActualShipment.setText(contentBean.getFinishVisit()+"");
                            jigOmpletionRate.setText(contentBean.getOmpletionRate()+"");
                            String region="";
                            if(contentBean.getProvinceName()!=null){
                                region+=contentBean.getProvinceName();
                            }
                            if(contentBean.getCityName()!=null){
                                region+=contentBean.getCityName();
                            }
                            if(contentBean.getAreaName()!=null){
                                region+=contentBean.getAreaName();
                            }
                            jigMarkete.setText(region);
                            jigExpectVisit.setText(contentBean.getSignIntention()+"");
                            jigActualVisit.setText(contentBean.getActualSign()+"");
                            if(!Func.IsStringEmpty(Rc.serviceDate)){
                                jigDate.setText(Rc.serviceDate);
                            }else{
                                jigDate.setText(JiuyiDateUtil.getNowTime3());
                            }
//                            if(!Func.IsStringEmpty(contentBean.getWorkPlanDate())){
//                                jigWorkPlanDate.setText(contentBean.getWorkPlanDate());
//                            }
                            if(!Func.IsStringEmpty(contentBean.getRemark())){
                                jigRemark.setText(contentBean.getRemark());
                            }
                            if(contentBean.getVisitIntentions()!=null && contentBean.getVisitIntentions().size()>0){
                                for(int i=0;i<contentBean.getVisitIntentions().size();i++){
                                    contentBean.getVisitIntentions().get( i ).setId( null );
                                }
                                visitIntentionsBeans =contentBean.getVisitIntentions();
                                menuAdapter.setmViewTypeBeanList(visitIntentionsBeans);
                                menuAdapter.notifyDataSetChanged();
                            }
                            if(contentBean.getSignIntentions()!=null && contentBean.getSignIntentions().size()>0){
                                for(int i=0;i<contentBean.getSignIntentions().size();i++){
                                    contentBean.getSignIntentions().get( i ).setId( null );
                                }
                                signIntentionsBeans =contentBean.getSignIntentions();

                                signIntentMutiAdapter.setmViewTypeBeanList(signIntentionsBeans);
                                signIntentMutiAdapter.notifyDataSetChanged();
                            }


                        }
                        if(progressLoadingDialog!=null){
                            progressLoadingDialog.dismiss();
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

    private void getDetailInfo(long id) {
        showLoadingDialog();
        JiuyiHttp.GET(backEndFlag+"/detail/"+id)
                .tag("request_get_"+backEndFlag)
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<ChannelDevelopBean.ContentBean>() {
                    @Override
                    public void onSuccess(ChannelDevelopBean.ContentBean contentBean) {
                        if(contentBean!=null){
                            createBean=contentBean;
                            if(contentBean.getOperator()!=null && contentBean.getOperator().getName()!=null ){
                                jigSalesman.setText(contentBean.getOperator().getName());
                                inputParamMap.put("id",contentBean.getOperator().getId());
                                inputParamMap.put("oaCode","");
                                getPlanTarget(inputParamMap);
                            }
                            jigSalesTarget.setText(contentBean.getDevelopmentProject()+"");
                            if(contentBean.getProvince()!=null){
                                jig_province.setText(contentBean.getProvince());
                            }
                            jig_cumulativeShipments.setText(contentBean.getAccumulateVisit()+"");
                            jigProjectedShipment.setText(contentBean.getVisitIntentional()+"");
                            jigActualShipment.setText(contentBean.getFinishVisit()+"");
                            jigOmpletionRate.setText(contentBean.getOmpletionRate()+"");
                            String region="";
                            if(contentBean.getProvinceName()!=null){
                                region+=contentBean.getProvinceName();
                            }
                            if(contentBean.getCityName()!=null){
                                region+=contentBean.getCityName();
                            }
                            if(contentBean.getAreaName()!=null){
                                region+=contentBean.getAreaName();
                            }
                            jigMarkete.setText(region);
                            jigExpectVisit.setText(contentBean.getSignIntention()+"");
                            jigActualVisit.setText(contentBean.getActualSign()+"");
                            if(!Func.IsStringEmpty(contentBean.getDate())){
                                jigDate.setText(contentBean.getDate());
                            }
                            if(!Func.IsStringEmpty(contentBean.getWorkPlanDate())){
                                jigWorkPlanDate.setText(contentBean.getWorkPlanDate());
                            }
                            if(!Func.IsStringEmpty(contentBean.getRemark())){
                                jigRemark.setText(contentBean.getRemark());
                            }
                            if(contentBean.getVisitIntentions()!=null && contentBean.getVisitIntentions().size()>0){
                                visitIntentionsBeans =contentBean.getVisitIntentions();
                                menuAdapter.setmViewTypeBeanList(visitIntentionsBeans);
                                menuAdapter.notifyDataSetChanged();
                            }
                            if(contentBean.getSignIntentions()!=null && contentBean.getSignIntentions().size()>0){
                                signIntentionsBeans =contentBean.getSignIntentions();
                                signIntentMutiAdapter.setmViewTypeBeanList(signIntentionsBeans);
                                signIntentMutiAdapter.notifyDataSetChanged();
                            }
                            if(progressLoadingDialog!=null){
                                progressLoadingDialog.dismiss();
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

    public void setBean(){
        if(!Func.IsStringEmpty(jigSalesTarget.getText().toString().trim())){
            createBean.setDevelopmentProject(Func.convertToLong(jigSalesTarget.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jig_province.getText().toString().trim())){
            createBean.setProvince(jig_province.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jig_cumulativeShipments.getText().toString().trim())){
            createBean.setAccumulateVisit(Func.convertToLong(jig_cumulativeShipments.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigProjectedShipment.getText().toString().trim())){
            createBean.setVisitIntentional(Func.convertToLong(jigProjectedShipment.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigActualShipment.getText().toString().trim())){
            createBean.setFinishVisit(Func.convertToLong(jigActualShipment.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigOmpletionRate.getText().toString().trim())){
            createBean.setOmpletionRate(Double.parseDouble(jigOmpletionRate.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigExpectVisit.getText().toString().trim())){
            createBean.setSignIntention(Func.convertToLong(jigExpectVisit.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigActualVisit.getText().toString().trim())){
            createBean.setActualSign(Func.convertToLong(jigActualVisit.getText().toString().trim()));
        }

        if(!Func.IsStringEmpty(jigDate.getText().toString().trim())){
            createBean.setDate(jigDate.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigWorkPlanDate.getText().toString().trim())){
            createBean.setWorkPlanDate(jigWorkPlanDate.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigRemark.getText().toString().trim())){
            createBean.setRemark(jigRemark.getText().toString().trim());
        }
        if(visitIntentionsBeans !=null && visitIntentionsBeans.size()>0){
            createBean.setVisitIntentions(visitIntentionsBeans);
        }else {
            createBean.setVisitIntentions(null);
        }
        if(signIntentionsBeans !=null && signIntentionsBeans.size()>0){
            createBean.setSignIntentions(signIntentionsBeans);
        }else{
            createBean.setSignIntentions(null);
        }

    }


    public void createBean(){
        setBean();
        submit();
    }
    public void submit(){
        JiuyiHttp.POST(backEndFlag+"/create")
                .setJson(GsonUtil.gson().toJson(createBean))
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

    public void setShipmentData(){
        if(menuAdapter!=null && menuAdapter.mViewTypeBeanList.size()>0){
            int count=0;
            for(int i=0;i<menuAdapter.mViewTypeBeanList.size();i++){
                if(menuAdapter.mViewTypeBeanList.get(i).isVisit()){
                    count++;
                }
            }
            jigProjectedShipment.setText(visitIntentionsBeans.size()+"");
            jigActualShipment.setText(count+"");
            if(!Func.IsStringEmpty(jigSalesTarget.getText()) && !Func.IsStringEmpty(jig_cumulativeShipments.getText())  && Double.parseDouble(jigSalesTarget.getText())>0 ){
                double num= (Double.parseDouble(count+"")+Double.parseDouble(jig_cumulativeShipments.getText()))/Double.parseDouble(jigSalesTarget.getText());
                DecimalFormat df = new DecimalFormat("0.00");
                String sfor = df.format(num*100);
                jigOmpletionRate.setText(sfor);
            }
        }else{
            jigProjectedShipment.setText("0");
            jigActualShipment.setText("0");
            if(!Func.IsStringEmpty(jigSalesTarget.getText()) && !Func.IsStringEmpty(jig_cumulativeShipments.getText())  && Double.parseDouble(jigSalesTarget.getText())>0 ){
                double num= (Double.parseDouble(jig_cumulativeShipments.getText()))/Double.parseDouble(jigSalesTarget.getText());
                DecimalFormat df = new DecimalFormat("0.00");
                String sfor = df.format(num*100);
                jigOmpletionRate.setText(sfor);
            }
        }

    }
    public void getSumActualShipment(ProvinceAndBrand provinceAndBrand){
        JiuyiHttp.POST(backEndFlag+"/sum-accumulateVisit/")
                .setJson(GsonUtil.gson().toJson(provinceAndBrand))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<SumActualShipmentBean>() {
                    @Override
                    public void onSuccess(SumActualShipmentBean result ) {
                        if(result!=null){
                            jig_cumulativeShipments.setText(result.getSumAccumulateVisit()+"");
                            setShipmentData();
//                            jigSalesTarget.setText(result.getWorkPlan()+"");
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    public void getPlanTarget(Map<String, Object> inputParamMap){
        JiuyiHttp.POST("work-plan-target/findByOperator/2")
                .setJson(GsonUtil.gson().toJson(inputParamMap))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<List<PlanTargetBean>>() {
                    @Override
                    public void onSuccess(List<PlanTargetBean> result ) {
                        if(result!=null){
                            planTargetBeanList=result;
//                            jigSalesTarget.setText(result.getWorkPlan());
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    public void getPlanTargetNew(Map<String, Object> inputParamMap){
        showLoadingDialog();
        JiuyiHttp.POST("work-plan-target/findByOperator/2")
                .setJson(GsonUtil.gson().toJson(inputParamMap))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<List<PlanTargetBean>>() {
                    @Override
                    public void onSuccess(List<PlanTargetBean> result ) {
                        if(result!=null){
                            planTargetBeanList=result;
                            AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiChannelDevelopNewActivity.this);
                            buidler.setTitle("省份");
                            final String[] data;
                            if(progressLoadingDialog!=null){
                                progressLoadingDialog.dismiss();
                            }
                            if(planTargetBeanList!=null &&planTargetBeanList.size()>0){
                                data=new String[planTargetBeanList.size()];
                                final boolean b[];
                                b=new boolean[planTargetBeanList.size()];
                                for(int i=0;i<planTargetBeanList.size();i++){
                                    data[i]=planTargetBeanList.get(i).getProvince();
                                    b[i]=false;
                                }
                                buidler.setMultiChoiceItems(data, b, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        //    将客户是否被勾选的记录保存到集合中
                                        b[which] = isChecked;  //保存客户选择的属性是否被勾选
                                    }
                                });
                                //设置一个确定和取消按钮
                                buidler.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    //保存数据
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String item="";
                                        double amt=0;
                                        Set<PlanTargetProvinceBrandBean.BrandBean> brandSet=new HashSet<>();
                                        Set<customer.entity.ProvinceBean.ContentBean> provinceSet=new HashSet<>();
                                        //取出被勾选中的内容
                                        ArrayList selectList=new ArrayList<>();
                                        for(int i=0;i<planTargetBeanList.size();i++){
                                            if(b[i]){
                                                //如果被勾线则保存数据
                                                if("".equals(item )){
                                                    item+=data[i];

                                                }else{
                                                    item+=","+data[i];
                                                }
                                                amt+=planTargetBeanList.get(i).getSalesTarget();
                                                selectList.add(planTargetBeanList.get(i));
                                                PlanTargetProvinceBrandBean.BrandBean brand=new PlanTargetProvinceBrandBean.BrandBean();
//                                                if(planTargetBeanList.get(i).getBrand()!=null){
//                                                    brand.setBrandName( planTargetBeanList.get(i).getBrand().getBrandName() );
//                                                    brandSet.add( brand );
//                                                }
                                                customer.entity.ProvinceBean.ContentBean provinceBean=new customer.entity.ProvinceBean.ContentBean();
                                                provinceBean.setProvinceName(planTargetBeanList.get(i).getProvince()  );
                                                provinceSet.add( provinceBean );
                                            }
                                        }
                                        jig_province.setText(item);
                                        jigSalesTarget.setText(amt+"");
                                        ProvinceAndBrand provinceAndBrand=new ProvinceAndBrand();
                                        provinceAndBrand.setBrandSet( brandSet );
                                        provinceAndBrand.setProvinceSet( provinceSet );
                                        getSumActualShipment(provinceAndBrand);
                                        //对话框消失
                                        dialog.dismiss();
                                    }
                                });
                                buidler.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                buidler.show();
                            }else{
                                Toast.makeText(JiuyiChannelDevelopNewActivity.this, "无匹配结果", Toast.LENGTH_SHORT).show();
                                return;
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
