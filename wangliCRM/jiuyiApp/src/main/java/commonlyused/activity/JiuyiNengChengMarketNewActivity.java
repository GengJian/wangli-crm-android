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
import android.widget.CompoundButton;
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
import com.tencent.qcloud.sdk.Constant;
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

import commonlyused.adapter.NewMarketHuaJueMutiAdapter;
import commonlyused.adapter.NewProjectHuaJueMutiAdapter;
import commonlyused.adapter.NewSignIntentMutiAdapter;
import commonlyused.adapter.NewUnfulProjectHuJueMutiAdapter;
import commonlyused.adapter.NewVisitIntentMutiAdapter;
import commonlyused.bean.ChannelDevelopBean;
import commonlyused.bean.MarketHuaJueBean;
import commonlyused.bean.MarketNengChengBean;
import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.PlanTargetBean;
import commonlyused.bean.PlanTargetProvinceBrandBean;
import commonlyused.bean.ProvinceAndBrand;
import commonlyused.bean.ProvinceProjectBean;
import commonlyused.bean.SumActualShipAndDayBean;
import commonlyused.bean.SumActualShipmentBean;
import commonlyused.bean.SumActualShipmentNcBean;
import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.entity.AttachmentBean;
import customer.entity.Media;
import customer.entity.MemberBeanID;
import customer.listener.OnItemClickListener;
import customer.listener.PickerConfig;
import customer.view.JiuyiAttachment;
import customer.view.JiuyiIntelligenceBar;
import customer.view.JiuyiToggleButtonGroup;
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
public class JiuyiNengChengMarketNewActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private String operatorType,sTitle,s_returnvalue;
    private ImageView   iv_back;
    private LinearLayout ll_content;
    private JiuyiItemGroup jigSalesman,jig_province,jig_developprovince;
    private JiuyiItemGroup jigSalesTarget,jig_cumulativeShipments,jig_followUpReportingProject,jig_followUpUnfulfilledProject;
    private JiuyiItemGroup jig_salesTarget2,jig_projectedIntent,jig_actualIntent,jig_ompletionRate2;
    private JiuyiItemGroup jigProjectedShipment;
    private JiuyiItemGroup jigActualShipment;
    private JiuyiItemGroup jigOmpletionRate;
    private JiuyiItemGroup jigMarkete;
    private JiuyiItemGroup jigExpectVisit;
    private JiuyiItemGroup jigActualVisit;
    private JiuyiItemGroup jigDate,jigWorkPlanDate;
    private JiuyiBigTextGroup jigRemark;
    private SwipeMenuRecyclerView rvDetaillist, rv_unfuldetaillist,rv_clientdetaillist;
    private SwipeMenuRecyclerView rv_intentdetaillist,rv_signintentdetaillist;
    private JiuyiBigTextGroup jigUnhandle;
    private JiuyiItemGroup jigActivity;
    private JiuyiToggleButtonGroup jigIscomplete;

    private JiuyiAttachment ahAttach;
    private NewProjectHuaJueMutiAdapter menuAdapter;
    private NewUnfulProjectHuJueMutiAdapter unfulMutiAdapter;
    private NewMarketHuaJueMutiAdapter marketClientMutiAdapter;

    private NewVisitIntentMutiAdapter newVisitIntentMutiAdapter;
    private NewSignIntentMutiAdapter signIntentMutiAdapter;
    private RelativeLayout rvNew,rv_signnew,rv_clientnew;
    private RelativeLayout rv_intentnew,rv_signintentnew;
    private String operType="";
    private static final int VISIT_CODE = 100;
    private MarketHuaJueBean.HjReportingProjectsBean reportingProjectsBean;
    private List<MarketHuaJueBean.HjReportingProjectsBean> reportingProjectsBeans;

    private MarketHuaJueBean.HjUnfulfilledProjectsBean unfulfilledProjectsBean;
    private List<MarketHuaJueBean.HjUnfulfilledProjectsBean> unfulfilledProjectsBeans;

    private MarketHuaJueBean.HuaJueItemsBean huaJueItemsBean;
    private List<MarketHuaJueBean.HuaJueItemsBean> huaJueItemsBeans;

    private ChannelDevelopBean.VisitIntentionsBean visitIntentionsBean;
    private List<ChannelDevelopBean.VisitIntentionsBean> visitIntentionsBeans;

    private ChannelDevelopBean.SignIntentionsBean signIntentionsBean;
    private List<ChannelDevelopBean.SignIntentionsBean> signIntentionsBeans;

    private int currentPosition=-1, currentUnfulfillPosition =-1,currentclientPosition =-1,currentintentPosition,currentSignPosition;
    private MarketNengChengBean.ContentBean createBean;
    private long billID=0;
    protected static final int TAKE_PICTURE = 1000;
    protected static final int SELECT_PICTURE = 1500;
    private  File[] files;
    private List<AttachmentBean> attachmentsBeanslist,attachmentsEditBeanslist;
    private String filePath;
    private Boolean needUpload=false;
    private String backEndFlag="neng-cheng";
    //申明对象
    CityPickerView mCityPickerView=new CityPickerView();
    private String msprovince="",msprovinceId="";
    private String mscity="";
    private String msdistrict="";
    private Map<String, Object> inputParamMap= new HashMap<String, Object>();
    private double sumAccumulateVisit=0;
    private List<PlanTargetProvinceBrandBean> planTargetBeanList,planDevelopTargetBeanList;




    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newmarketnengcheng_layout"), null);
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
            createBean=new MarketNengChengBean.ContentBean();
        }
        initViews();
        if(operatorType.equals(ViewOperatorType.ADD)){
            getInfo();
        }
        if(operatorType.equals(ViewOperatorType.EDIT)||operatorType.equals(ViewOperatorType.VIEW)||operatorType.equals(ViewOperatorType.SPECIAL)){//
            billID=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
            if(billID>0){
                getDetailInfo(billID);
            }
        }
        if(operatorType.equals(ViewOperatorType.ADD)){
            NormalOperatorBean.OperatorBeanID operatorBeanID=new NormalOperatorBean.OperatorBeanID();
            operatorBeanID.setId(Rc.id);
            createBean.setOperator(operatorBeanID);
            inputParamMap.put("id",Rc.id);
            inputParamMap.put("oaCode","");
            jig_province.setText("");
            jig_cumulativeShipments.setText("");
            jigOmpletionRate.setText("0.00");

            jig_developprovince.setText("");
            jig_actualIntent.setText("");
            jig_ompletionRate2.setText("0.00");
            getPlanTarget(inputParamMap);
            getPlanDevelopTarget(inputParamMap);

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
                    if(ahAttach.getMediaArrayList().size()>0) {
                        if(operatorType.equals(ViewOperatorType.EDIT)||operatorType.equals(ViewOperatorType.SPECIAL)){
                            attachmentsEditBeanslist=new ArrayList<>();
                        }

                        files = new File[ahAttach.getMediaArrayList().size()];
                        for (int i = 0; i < ahAttach.getMediaArrayList().size(); i++) {
                            Media media=ahAttach.getMediaArrayList().get(i);
                            if (media.getPath() != null ) {
                                File file1 = new File(media.getPath());
                                files[i] = file1;
                                needUpload=true;
                            }else{
                                if(media.getUrl()!=null && attachmentsBeanslist!=null && attachmentsBeanslist.size()>0  ){
                                    for(int j=0;j<attachmentsBeanslist.size();j++){
                                        AttachmentBean attachmentBean=attachmentsBeanslist.get(j);
                                        if(attachmentBean.getQiniuKey()!=null   ){
                                            int pos=media.getUrl().lastIndexOf("/");
                                            if(pos>0){
                                                String qiuniukey=media.getUrl().substring(pos+1,media.getUrl().length());
                                                if(qiuniukey.equals(attachmentBean.getQiniuKey())){
                                                    attachmentsEditBeanslist.add(attachmentBean);
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                        if(needUpload){
                            upload();
                        }else{
                            if(attachmentsEditBeanslist.size()>0){
                                createBean.setAttachments(attachmentsEditBeanslist);
                                updateInfo();
                            }
                        }

                    }else{
                        if(operatorType.equals(ViewOperatorType.ADD)){
                            createBean();
                        }else if(operatorType.equals(ViewOperatorType.EDIT)||operatorType.equals(ViewOperatorType.SPECIAL)){
                            createBean.setAttachments(null);
                            updateInfo();
                        }

                    }
                }
            });
        }
        if(operatorType.equals(ViewOperatorType.VIEW)){
            mtvcomplete.setVisibility(View.INVISIBLE);
            rvNew.setVisibility(View.GONE);
            rv_signnew.setVisibility(View.GONE);
            rv_clientnew.setVisibility(View.GONE);
            rv_intentnew.setVisibility(View.GONE);
            rv_signintentnew.setVisibility(View.GONE);
            disableSubControls(ll_content);
        }else if(operatorType.equals(ViewOperatorType.ADD)||operatorType.equals(ViewOperatorType.EDIT)){
            jigUnhandle.contentEdt.setClickable(false);
            jigUnhandle.contentEdt.setEnabled(false);
            jigIscomplete.tb_type.setEnabled(false);
        }else if(operatorType.equals(ViewOperatorType.SPECIAL)){
            mtvcomplete.setVisibility(View.VISIBLE);
//            rvNew.setVisibility(View.GONE);
//            rv_signnew.setVisibility(View.GONE);
//            rv_clientnew.setVisibility(View.GONE);
//            rv_intentnew.setVisibility(View.GONE);
//            rv_signintentnew.setVisibility(View.GONE);
            disableSubControls(ll_content);
            jigUnhandle.setEnabled(true);
            jigUnhandle.setClickable(true);
            jigUnhandle.contentEdt.setClickable(true);
            jigUnhandle.contentEdt.setEnabled(true);
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
                    Toast.makeText(JiuyiNengChengMarketNewActivity.this, "请先选择业务员", Toast.LENGTH_SHORT).show();
                    return;
                }
              getPlanTargetNew( inputParamMap );

            }
        });

        jig_developprovince = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_developprovince);
        jig_developprovince.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                if(Func.IsStringEmpty(jigSalesman.getText())){
                    Toast.makeText(JiuyiNengChengMarketNewActivity.this, "请先选择业务员", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiNengChengMarketNewActivity.this);
                buidler.setTitle("省份");
                final String[] data;

                if(planDevelopTargetBeanList!=null &&planDevelopTargetBeanList.size()>0){
                    data=new String[planDevelopTargetBeanList.size()];
                    final boolean b[];
                    b=new boolean[planDevelopTargetBeanList.size()];
                    for(int i=0;i<planDevelopTargetBeanList.size();i++){
//                        data[i]=planDevelopTargetBeanList.get(i).getProvince();
                        PlanTargetProvinceBrandBean planTargetProvinceBrandBean=planDevelopTargetBeanList.get(i);
                        if(planTargetProvinceBrandBean.getProvince()!=null && planTargetProvinceBrandBean.getBrand()!=null){
                            data[i]=planDevelopTargetBeanList.get(i).getProvince()+"-"+planDevelopTargetBeanList.get(i).getBrand().getBrandName();
                        }
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
                            for(int i=0;i<planDevelopTargetBeanList.size();i++){
                                if(b[i]){
                                    //如果被勾线则保存数据
                                    if("".equals(item )){
                                        item+=data[i];

                                    }else{
                                        item+=","+data[i];
                                    }
                                    amt+=planDevelopTargetBeanList.get(i).getSalesTarget();
                                    selectList.add(planDevelopTargetBeanList.get(i));
                                    PlanTargetProvinceBrandBean.BrandBean brand=new PlanTargetProvinceBrandBean.BrandBean();
                                    if(planDevelopTargetBeanList.get(i).getBrand()!=null){
                                        brand.setBrandName( planDevelopTargetBeanList.get(i).getBrand().getBrandName() );
                                        brandSet.add( brand );
                                    }
                                    customer.entity.ProvinceBean.ContentBean provinceBean=new customer.entity.ProvinceBean.ContentBean();
                                    provinceBean.setProvinceName(planDevelopTargetBeanList.get(i).getProvince()  );
                                    provinceSet.add( provinceBean );
                                }
                            }
                            jig_developprovince.setText(item);
                            jig_salesTarget2.setText(amt+"");
                            if(operatorType.equals(ViewOperatorType.EDIT) && createBean!=null
                                    && createBean.getDevelopmentProvince()!=null && createBean.getDevelopmentProvince().equals(item)){
                                sumAccumulateVisit=createBean.getAccumulateVisit();
                                setShipmentData2();
                            }else{
                                ProvinceAndBrand provinceAndBrand=new ProvinceAndBrand();
                                provinceAndBrand.setBrandSet( brandSet );
                                provinceAndBrand.setProvinceSet( provinceSet );
                                getSumDevelopActualShipment(provinceAndBrand);
                            }
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
                    Toast.makeText(JiuyiNengChengMarketNewActivity.this, "无匹配结果", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        jigIscomplete = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_iscomplete);
        jigIscomplete.tb_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    jigIscomplete.bdefautl=true;
                    ahAttach.setVisibility(View.VISIBLE);
                }else{
                    jigIscomplete.bdefautl=false;
                    ahAttach.setVisibility(View.GONE);
                }
            }
        });
        jigSalesTarget = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_salesTarget);
        jig_cumulativeShipments = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_cumulativeShipments);
        jigUnhandle = (JiuyiBigTextGroup) mBodyLayout.findViewById(R.id.jig_unhandle);
        jigActivity = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_activity);
        jig_followUpReportingProject = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_followUpReportingProject);
        jig_followUpUnfulfilledProject = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_followUpUnfulfilledProject);
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
//                        total=Double.parseDouble(s.toString())+Double.parseDouble(jig_cumulativeShipments.getText().toString());
//                        if(total>0 && Double.parseDouble(jigSalesTarget.getText().toString())>0){
//                            double num= total/Double.parseDouble(jigSalesTarget.getText().toString());
//                            DecimalFormat df = new DecimalFormat("0.00");
//                            String sfor = df.format(num*100);
//                            jigOmpletionRate.setText(sfor);
//                        }
//                    }
//
//                }else{
//                    jigOmpletionRate.setText("0.00");
//                }
//            }
//        });

        jigOmpletionRate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_ompletionRate);

        jig_salesTarget2 = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_salesTarget2);
        jig_projectedIntent = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_projectedIntent);
        jig_actualIntent = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_actualIntent);
        jig_ompletionRate2 = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_ompletionRate2);

        jigMarkete = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_markete);
        jigMarkete.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                hideKeyboard();
                mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        Map<String, Object> inputParamMap2= new HashMap<String, Object>();
                        Map<String, Object> inputParamMap3= new HashMap<String, Object>();
                        StringBuilder sb = new StringBuilder();
                        if (province != null) {
                            msprovince=province.getName();
                            sb.append(province.getName() /*+ " " + province.getId()*/ );
                            createBean.setProvinceName(msprovince);
                            createBean.setProvinceNumber(province.getId());
                            msprovinceId=province.getId();
                            inputParamMap2.put("provinceNumber",province.getId());
                            if(!Func.IsStringEmpty( msprovince ) && msprovince.length()>1){
                                inputParamMap3.put("provinceName",msprovince.substring(0,2));
                                getProjectByProvince(inputParamMap3);
                            }
                        }

                        if (city != null) {
                            mscity=city.getName();
                            sb.append(city.getName()/* + " " + city.getId()*/ );
                            createBean.setCityName(mscity);
                            createBean.setCityNumber(city.getId());
                            inputParamMap2.put("cityNumber",city.getId());
                        }

                        if (district != null) {
                            msdistrict=district.getName();
                            sb.append(district.getName() /*+ " " + district.getId()*/ );
                            createBean.setAreaName(msdistrict);
                            createBean.setAreaNumber(district.getId());
                            inputParamMap2.put("areaNumber",district.getId());
                        }else{
                            msdistrict="";
                        }

                        jigMarkete.setText("" + sb.toString());
                        if(inputParamMap2.size()>0 /*&& operatorType.equals(ViewOperatorType.ADD)*/){
                            getCustomerByRegion(inputParamMap2);
                        }

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


        rv_clientdetaillist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_clientdetaillist);
        rv_clientdetaillist.setLayoutManager(new LinearLayoutManager(JiuyiNengChengMarketNewActivity.this));
        rv_clientdetaillist.setHasFixedSize(true);
        rv_clientdetaillist.setItemAnimator(new DefaultItemAnimator());
        rv_clientdetaillist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiNengChengMarketNewActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rv_clientdetaillist.setSwipeMenuCreator(swipeMenuCreator);
        rv_clientdetaillist.setSwipeMenuItemClickListener(menuItemClickListener3);
        rv_clientnew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_clientnew);
        rv_clientnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Func.IsStringEmpty(jigSalesman.getText())){
                    startDialog(DialogID.DialogDoNothing, "", "请选择业务员！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                if(Func.IsStringEmpty(msprovinceId)){
                    startDialog(DialogID.DialogDoNothing, "", "请先选择走访市场！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return ;
                }
                Intent intent = new Intent(getApplicationContext(), JiuyiNewMarketHuaJueDtailMutiActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                intent.putExtra(JiuyiBundleKey.PARAM_PROVINCE, msprovinceId);
                startActivityForResult(intent, 400);
            }
        });


        rvDetaillist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_detaillist);
        rvDetaillist.setLayoutManager(new LinearLayoutManager(JiuyiNengChengMarketNewActivity.this));
        rvDetaillist.setHasFixedSize(true);
        rvDetaillist.setItemAnimator(new DefaultItemAnimator());
        rvDetaillist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiNengChengMarketNewActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

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
                Intent intent = new Intent(getApplicationContext(), JiuyiNewProjectHuaJueDtailMutiActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                startActivityForResult(intent, VISIT_CODE);
            }
        });
        rv_unfuldetaillist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_signdetaillist);
        rv_unfuldetaillist.setLayoutManager(new LinearLayoutManager(JiuyiNengChengMarketNewActivity.this));
        rv_unfuldetaillist.setHasFixedSize(true);
        rv_unfuldetaillist.setNestedScrollingEnabled(false);
        rv_unfuldetaillist.setItemAnimator(new DefaultItemAnimator());
        rv_unfuldetaillist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiNengChengMarketNewActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rv_unfuldetaillist.setSwipeMenuCreator(swipeMenuCreator);
        rv_unfuldetaillist.setSwipeMenuItemClickListener(menuItemClickListener2);
        rv_signnew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_signnew);
        rv_signnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Func.IsStringEmpty(msprovinceId)){
                    startDialog(DialogID.DialogDoNothing, "", "请先选择走访市场！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return ;
                }
                Intent intent = new Intent(getApplicationContext(), JiuyiNewUnfullProjectHuaJueDtailMutiActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                startActivityForResult(intent, 300);
            }
        });

        ahAttach = (JiuyiAttachment) mBodyLayout.findViewById(R.id.ah_attach);
        ahAttach.setAdapter();

        reportingProjectsBeans =new ArrayList<>();

        menuAdapter = new NewProjectHuaJueMutiAdapter(reportingProjectsBeans);
        menuAdapter.setOnItemClickListener(onItemClickListener);
        menuAdapter.setViewType("VIEW");
        if(operatorType.equals(ViewOperatorType.VIEW)){
            rvDetaillist.setSwipeMenuCreator(null);
        }
        rvDetaillist.setAdapter(menuAdapter);

        unfulfilledProjectsBeans =new ArrayList<>();
        unfulMutiAdapter = new NewUnfulProjectHuJueMutiAdapter(unfulfilledProjectsBeans);
        unfulMutiAdapter.setOnItemClickListener(onItemClickListener2);
        unfulMutiAdapter.setViewType("VIEW");
        if(operatorType.equals(ViewOperatorType.VIEW)){
            rv_unfuldetaillist.setSwipeMenuCreator(null);
        }
        rv_unfuldetaillist.setAdapter(unfulMutiAdapter);

        huaJueItemsBeans =new ArrayList<>();
        marketClientMutiAdapter = new NewMarketHuaJueMutiAdapter(huaJueItemsBeans);
        marketClientMutiAdapter.setOnItemClickListener(onItemClickListener3);
        marketClientMutiAdapter.setViewType("VIEW");
        if(operatorType.equals(ViewOperatorType.VIEW)){
            rv_clientdetaillist.setSwipeMenuCreator(null);
        }
        rv_clientdetaillist.setAdapter(marketClientMutiAdapter);

        rv_intentdetaillist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_intentdetaillist);
        rv_intentdetaillist.setLayoutManager(new LinearLayoutManager(JiuyiNengChengMarketNewActivity.this));
        rv_intentdetaillist.setHasFixedSize(true);
        rv_intentdetaillist.setItemAnimator(new DefaultItemAnimator());
        rv_intentdetaillist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiNengChengMarketNewActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rv_intentdetaillist.setSwipeMenuCreator(swipeMenuCreator);
        rv_intentdetaillist.setSwipeMenuItemClickListener(menuItemClickListener5);
        rv_intentnew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_intentnew);
        rv_intentnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiNewVisitIntentDtailMutiActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                startActivityForResult(intent, 500);
            }
        });

        rv_signintentdetaillist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_signintentdetaillist);
        rv_signintentdetaillist.setLayoutManager(new LinearLayoutManager(JiuyiNengChengMarketNewActivity.this));
        rv_signintentdetaillist.setHasFixedSize(true);
        rv_signintentdetaillist.setItemAnimator(new DefaultItemAnimator());
        rv_signintentdetaillist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiNengChengMarketNewActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rv_signintentdetaillist.setSwipeMenuCreator(swipeMenuCreator);
        rv_signintentdetaillist.setSwipeMenuItemClickListener(menuItemClickListener6);
        rv_signintentnew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_signintentnew);
        rv_signintentnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Func.IsStringEmpty(jigSalesman.getText())){
                    startDialog(DialogID.DialogDoNothing, "", "请选择业务员！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), JiuyiNewSignIntentionDtailMutiActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                startActivityForResult(intent, 600);
            }
        });

        visitIntentionsBeans =new ArrayList<>();
        newVisitIntentMutiAdapter = new NewVisitIntentMutiAdapter(visitIntentionsBeans);
        newVisitIntentMutiAdapter.setOnItemClickListener(onItemClickListener5);
        newVisitIntentMutiAdapter.setViewType("VIEW");
        if(operatorType.equals(ViewOperatorType.VIEW)){
            rv_intentdetaillist.setSwipeMenuCreator(null);
        }
        rv_intentdetaillist.setAdapter(newVisitIntentMutiAdapter);

        signIntentionsBeans =new ArrayList<>();
        signIntentMutiAdapter = new NewSignIntentMutiAdapter(signIntentionsBeans);
        signIntentMutiAdapter.setOnItemClickListener(onItemClickListener6);
        signIntentMutiAdapter.setViewType("VIEW");
        if(operatorType.equals(ViewOperatorType.VIEW)){
            rv_signintentdetaillist.setSwipeMenuCreator(null);
        }
        rv_signintentdetaillist.setAdapter(signIntentMutiAdapter);

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
            mTitle = "新建能诚开发计划";
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
                SwipeMenuItem wechatItem = new SwipeMenuItem(JiuyiNengChengMarketNewActivity.this)
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
                    MarketHuaJueBean.HjReportingProjectsBean contentBean=menuAdapter.mViewTypeBeanList.get(adapterPosition);
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
                MarketHuaJueBean.HjReportingProjectsBean contentBean=menuAdapter.mViewTypeBeanList.get(position);
                if(contentBean!=null){
                    currentPosition=position;
                    if(operatorType.equals(ViewOperatorType.VIEW)){
                        operType=ViewOperatorType.VIEW;
                    }else{
                        operType=ViewOperatorType.EDIT;
                    }
                    Intent intent = new Intent(getApplicationContext(), JiuyiNewProjectHuaJueDtailMutiActivity.class);
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
            if(unfulMutiAdapter !=null){
                MarketHuaJueBean.HjUnfulfilledProjectsBean contentBean= unfulMutiAdapter.mViewTypeBeanList.get(position);
                if(contentBean!=null){
                    currentUnfulfillPosition =position;
                    if(operatorType.equals(ViewOperatorType.VIEW)){
                        operType=ViewOperatorType.VIEW;
                    }else{
                        operType=ViewOperatorType.EDIT;
                    }
                    Intent intent = new Intent(getApplicationContext(), JiuyiNewUnfullProjectHuaJueDtailMutiActivity.class);
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
                if(unfulMutiAdapter !=null){
                    MarketHuaJueBean.HjUnfulfilledProjectsBean contentBean= unfulMutiAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        unfulMutiAdapter.mViewTypeBeanList.remove(adapterPosition);
                        unfulMutiAdapter.notifyDataSetChanged();
//                        int count=0;
//                        for(int i = 0; i< unfulMutiAdapter.mViewTypeBeanList.size(); i++){
//                            if(unfulMutiAdapter.mViewTypeBeanList.get(i).isEffective()){
//                                count++;
//                            }
//                        }
//                        jigExpectVisit.setText(unfulfilledProjectsBeans.size()+"");
//                        jigActualVisit.setText(count+"");
                    }
                }
            }
        }
    };

    private OnItemClickListener onItemClickListener3 = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(marketClientMutiAdapter !=null){
                MarketHuaJueBean.HuaJueItemsBean contentBean= marketClientMutiAdapter.mViewTypeBeanList.get(position);
                if(contentBean!=null){
                    currentclientPosition =position;
                    if(operatorType.equals(ViewOperatorType.VIEW)){
                        operType=ViewOperatorType.VIEW;
                    }else{
                        operType=ViewOperatorType.EDIT;
                    }
                    Intent intent = new Intent(getApplicationContext(), JiuyiNewMarketHuaJueDtailMutiActivity.class);
                    intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, operType);
                    intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                    intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN, contentBean);
                    intent.putExtra(JiuyiBundleKey.PARAM_PROVINCE, msprovinceId);
                    startActivityForResult(intent, 401);
                }
            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener3 = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if(marketClientMutiAdapter !=null){
                    MarketHuaJueBean.HuaJueItemsBean contentBean= marketClientMutiAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        marketClientMutiAdapter.mViewTypeBeanList.remove(adapterPosition);
                        marketClientMutiAdapter.notifyDataSetChanged();
                        int count=0;
                        for(int i = 0; i< marketClientMutiAdapter.mViewTypeBeanList.size(); i++){
                            if(marketClientMutiAdapter.mViewTypeBeanList.get(i).isVisit()){
                                count++;
                            }
                        }
                        jigExpectVisit.setText(huaJueItemsBeans.size()+"");
                        jigActualVisit.setText(count+"");
                    }
                }
            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener5 = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if(newVisitIntentMutiAdapter!=null){
                    ChannelDevelopBean.VisitIntentionsBean contentBean=newVisitIntentMutiAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        newVisitIntentMutiAdapter.mViewTypeBeanList.remove(adapterPosition);
                        newVisitIntentMutiAdapter.notifyDataSetChanged();
                        setShipmentData2();
                    }
                }
            }
        }
    };
    private OnItemClickListener onItemClickListener5 = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(newVisitIntentMutiAdapter!=null){
                ChannelDevelopBean.VisitIntentionsBean contentBean=newVisitIntentMutiAdapter.mViewTypeBeanList.get(position);
                if(contentBean!=null){
                    currentintentPosition=position;
                    if(operatorType.equals(ViewOperatorType.VIEW)){
                        operType=ViewOperatorType.VIEW;
                    }else{
                        operType=ViewOperatorType.EDIT;
                    }
                    Intent intent = new Intent(getApplicationContext(), JiuyiNewVisitIntentDtailMutiActivity.class);
                    intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, operType);
                    intent.putExtra(JiuyiBundleKey.PARAM_BILLOPERATORTYPE, operatorType);
                    intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN, contentBean);
                    startActivityForResult(intent, 501);
                }
            }
        }
    };


    private OnItemClickListener onItemClickListener6 = new OnItemClickListener() {
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
                    startActivityForResult(intent, 601);
                }
            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener6 = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if(signIntentMutiAdapter!=null){
                    ChannelDevelopBean.SignIntentionsBean contentBean=signIntentMutiAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        signIntentMutiAdapter.mViewTypeBeanList.remove(adapterPosition);
                        signIntentMutiAdapter.notifyDataSetChanged();
//                        int count=0;
//                        for(int i=0;i<signIntentMutiAdapter.mViewTypeBeanList.size();i++){
//                            if(signIntentMutiAdapter.mViewTypeBeanList.get(i).isSign()){
//                                count++;
//                            }
//                        }
//                        jigExpectVisit.setText(signIntentionsBeans.size()+"");
//                        jigActualVisit.setText(count+"");
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
                reportingProjectsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (reportingProjectsBean != null) {
                    if (reportingProjectsBeans == null) {
                        reportingProjectsBeans = new ArrayList<>();
                    }
                    reportingProjectsBeans.add(reportingProjectsBean);
                    if (menuAdapter == null) {
                        menuAdapter = new NewProjectHuaJueMutiAdapter(reportingProjectsBeans);
                        menuAdapter.setOnItemClickListener(onItemClickListener);
                        rvDetaillist.setAdapter(menuAdapter);
                    } else {
                        menuAdapter.setmViewTypeBeanList(reportingProjectsBeans);
                        menuAdapter.notifyDataSetChanged();
                    }
//                    setShipmentData();
                }
                break;
            case 101:
                bundle = data.getExtras();
                reportingProjectsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (reportingProjectsBean != null) {
                    if (reportingProjectsBeans != null && reportingProjectsBeans.size() > 0 && currentPosition >= 0) {
                        reportingProjectsBeans.set(currentPosition, reportingProjectsBean);
                        menuAdapter.setmViewTypeBeanList(reportingProjectsBeans);
                        menuAdapter.notifyDataSetChanged();
//                        setShipmentData();
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
//                    jigSalesTarget.setText("");
//                    jigActualShipment.setText("");
                    jig_province.setText("");
                    jig_cumulativeShipments.setText("");
                    jigOmpletionRate.setText("0.00");

                    jig_developprovince.setText("");
                    jig_actualIntent.setText("");
                    jig_ompletionRate2.setText("0.00");
                    getPlanTarget(inputParamMap);
                    getPlanDevelopTarget(inputParamMap);
//                    getSumActualShipment(inputParamMap);
                }
                break;
            case 300:
                bundle = data.getExtras();
                unfulfilledProjectsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (unfulfilledProjectsBean != null) {
                    if (unfulfilledProjectsBeans == null) {
                        unfulfilledProjectsBeans = new ArrayList<>();
                    }
                    unfulfilledProjectsBeans.add(unfulfilledProjectsBean);
                    if (unfulMutiAdapter == null) {
                        unfulMutiAdapter = new NewUnfulProjectHuJueMutiAdapter(unfulfilledProjectsBeans);
                        unfulMutiAdapter.setOnItemClickListener(onItemClickListener2);
                        rv_unfuldetaillist.setAdapter(unfulMutiAdapter);
                    } else {
                        unfulMutiAdapter.setmViewTypeBeanList(unfulfilledProjectsBeans);
                        unfulMutiAdapter.notifyDataSetChanged();
                    }
//                    int count=0;
//                    for(int i = 0; i< unfulfilledProjectsBeans.size(); i++){
//                        if(unfulfilledProjectsBeans.get(i).isEffective()){
//                            count++;
//                        }
//                    }
//                    jigExpectVisit.setText(unfulfilledProjectsBeans.size()+"");
//                    jigActualVisit.setText(count+"");
                }
                break;
            case 301:
                bundle = data.getExtras();
                unfulfilledProjectsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (unfulfilledProjectsBean != null) {
                    if (unfulfilledProjectsBeans != null && unfulfilledProjectsBeans.size() > 0 && currentUnfulfillPosition >= 0) {
                        unfulfilledProjectsBeans.set(currentUnfulfillPosition, unfulfilledProjectsBean);
                        unfulMutiAdapter.setmViewTypeBeanList(unfulfilledProjectsBeans);
                        unfulMutiAdapter.notifyDataSetChanged();
//                        int count=0;
//                        for(int i = 0; i< unfulfilledProjectsBeans.size(); i++){
//                            if(unfulfilledProjectsBeans.get(i).isEffective()){
//                                count++;
//                            }
//                        }
//                        jigExpectVisit.setText(unfulfilledProjectsBeans.size()+"");
//                        jigActualVisit.setText(count+"");
                    }

                }
                break;
            case 400:
                bundle = data.getExtras();
                huaJueItemsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (huaJueItemsBean != null) {
                    if (huaJueItemsBeans == null) {
                        huaJueItemsBeans = new ArrayList<>();
                    }
                    huaJueItemsBeans.add(huaJueItemsBean);
                    if (marketClientMutiAdapter == null) {
                        marketClientMutiAdapter = new NewMarketHuaJueMutiAdapter(huaJueItemsBeans);
                        marketClientMutiAdapter.setOnItemClickListener(onItemClickListener3);
                        rv_clientdetaillist.setAdapter(marketClientMutiAdapter);
                    } else {
                        marketClientMutiAdapter.setmViewTypeBeanList(huaJueItemsBeans);
                        marketClientMutiAdapter.notifyDataSetChanged();
                    }
                    int count=0;
                    for(int i=0;i<marketClientMutiAdapter.mViewTypeBeanList.size();i++){
                        if(marketClientMutiAdapter.mViewTypeBeanList.get(i).isVisit()){
                            count++;
                        }
                    }
                    jigExpectVisit.setText(huaJueItemsBeans.size()+"");
                    jigActualVisit.setText(count+"");
                }
                break;
            case 401:
                bundle = data.getExtras();
                huaJueItemsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (huaJueItemsBean != null) {
                    if (huaJueItemsBeans != null && huaJueItemsBeans.size() > 0 && currentclientPosition >= 0) {
                        huaJueItemsBeans.set(currentclientPosition, huaJueItemsBean);
                        marketClientMutiAdapter.setmViewTypeBeanList(huaJueItemsBeans);
                        marketClientMutiAdapter.notifyDataSetChanged();
                        int count=0;
                        for(int i=0;i<marketClientMutiAdapter.mViewTypeBeanList.size();i++){
                            if(marketClientMutiAdapter.mViewTypeBeanList.get(i).isVisit()){
                                count++;
                            }
                        }
                        jigExpectVisit.setText(huaJueItemsBeans.size()+"");
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
            case 500:
                bundle = data.getExtras();
                visitIntentionsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (visitIntentionsBean != null) {
                    if (visitIntentionsBeans == null) {
                        visitIntentionsBeans = new ArrayList<>();
                    }
                    visitIntentionsBeans.add(visitIntentionsBean);
                    if (newVisitIntentMutiAdapter == null) {
                        newVisitIntentMutiAdapter = new NewVisitIntentMutiAdapter(visitIntentionsBeans);
                        newVisitIntentMutiAdapter.setOnItemClickListener(onItemClickListener5);
                        rv_intentdetaillist.setAdapter(newVisitIntentMutiAdapter);
                    } else {
                        newVisitIntentMutiAdapter.setmViewTypeBeanList(visitIntentionsBeans);
                        newVisitIntentMutiAdapter.notifyDataSetChanged();
                    }
                    setShipmentData2();
                }
                break;
            case 501:
                bundle = data.getExtras();
                visitIntentionsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (visitIntentionsBean != null) {
                    if (visitIntentionsBeans != null && visitIntentionsBeans.size() > 0 && currentintentPosition >= 0) {
                        visitIntentionsBeans.set(currentintentPosition, visitIntentionsBean);
                        newVisitIntentMutiAdapter.setmViewTypeBeanList(visitIntentionsBeans);
                        newVisitIntentMutiAdapter.notifyDataSetChanged();
                        setShipmentData2();
                    }

                }
                break;

            case 600:
                bundle = data.getExtras();
                signIntentionsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (signIntentionsBean != null) {
                    if (signIntentionsBeans == null) {
                        signIntentionsBeans = new ArrayList<>();
                    }
                    signIntentionsBeans.add(signIntentionsBean);
                    if (signIntentMutiAdapter == null) {
                        signIntentMutiAdapter = new NewSignIntentMutiAdapter(signIntentionsBeans);
                        signIntentMutiAdapter.setOnItemClickListener(onItemClickListener6);
                        rv_signintentdetaillist.setAdapter(signIntentMutiAdapter);
                    } else {
                        signIntentMutiAdapter.setmViewTypeBeanList(signIntentionsBeans);
                        signIntentMutiAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case 601:
                bundle = data.getExtras();
                signIntentionsBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (signIntentionsBean != null) {
                    if (signIntentionsBeans != null && signIntentionsBeans.size() > 0 && currentSignPosition >= 0) {
                        signIntentionsBeans.set(currentSignPosition, signIntentionsBean);
                        signIntentMutiAdapter.setmViewTypeBeanList(signIntentionsBeans);
                        signIntentMutiAdapter.notifyDataSetChanged();
//                        int count=0;
//                        for(int i = 0; i< signIntentionsBeans.size(); i++){
//                            if(signIntentionsBeans.get(i).isSign()){
//                                count++;
//                            }
//                        }
//                        jigExpectVisit.setText(signIntentionsBeans.size()+"");
//                        jigActualVisit.setText(count+"");
                    }

                }
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
            startDialog(DialogID.DialogDoNothing, "", "当月销售目标不能为空！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jig_cumulativeShipments.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "当月累计发货量不能为空！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jig_developprovince.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择省份！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jigMarkete.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择走访市场！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jigWorkPlanDate.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择工作计划日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(marketClientMutiAdapter!=null){
            if(marketClientMutiAdapter.mViewTypeBeanList.size()<1){
                startDialog(DialogID.DialogDoNothing, "", "客户不能空！", JiuyiDialogBase.Dialog_Type_Yes, null);
                return false;
            }else {
                for(int i=0;i<marketClientMutiAdapter.mViewTypeBeanList.size();i++){
                    if(marketClientMutiAdapter.mViewTypeBeanList.get( i ).getMemberAttribute()==null ){
                        startDialog(DialogID.DialogDoNothing, "", "客户属性不能空！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        return false;
                    }
                }
            }
        }

//        if(menuAdapter!=null && menuAdapter.mViewTypeBeanList.size()<3){
//            startDialog(DialogID.DialogDoNothing, "", "拜访意向客户不能少于3家！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
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
            createBean =new MarketNengChengBean.ContentBean();
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
                .request(new ACallback<MarketNengChengBean.ContentBean>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(MarketNengChengBean.ContentBean contentBean) {
                        if(contentBean!=null && contentBean.getId()!=null){
                            createBean=contentBean;
                            createBean.setId( null );
                            if(contentBean.getOperator()!=null && contentBean.getOperator().getName()!=null ){
                                jigSalesman.setText(contentBean.getOperator().getName());
                                inputParamMap.put("id",contentBean.getOperator().getId());
                                inputParamMap.put("oaCode","");
                                getPlanTarget(inputParamMap);
                                getPlanDevelopTarget(inputParamMap);
                            }
                            jigSalesTarget.setText(contentBean.getSalesTarget()+"");
                            if(contentBean.getProvince()!=null){
                                jig_province.setText(contentBean.getProvince());
                            }
                            if(contentBean.getDevelopmentProvince()!=null){
                                jig_developprovince.setText(contentBean.getDevelopmentProvince());
                            }
                            jig_cumulativeShipments.setText(contentBean.getCumulativeShipments()+"");
                            jigProjectedShipment.setText(contentBean.getProjectedShipment()+"");
                            jigActualShipment.setText(contentBean.getActualShipment()+"");
                            jigOmpletionRate.setText(contentBean.getOmpletionRate()+"");
                            jig_ompletionRate2.setText(contentBean.getCompletionRate()+"");
                            jig_salesTarget2.setText(contentBean.getDevelopmentProject()+"");
                            String region="";
                            if(contentBean.getProvinceName()!=null){
                                region+=contentBean.getProvinceName();
                                msprovinceId=contentBean.getProvinceNumber();
                            }
                            if(contentBean.getCityName()!=null){
                                region+=contentBean.getCityName();
                            }
                            if(contentBean.getAreaName()!=null){
                                region+=contentBean.getAreaName();
                            }
                            jigMarkete.setText(region);
                            jigExpectVisit.setText(contentBean.getExpectVisit()+"");
                            jigActualVisit.setText(contentBean.getActualVisit()+"");
                            if(contentBean.getAfterSaleUnprocessed()!=null){
                                jigUnhandle.setText(contentBean.getAfterSaleUnprocessed());
                            }
                            if(contentBean.getActivity()!=null){
                                jigActivity.setText(contentBean.getActivity());
                            }
                            jigIscomplete.tb_type.setChecked(contentBean.isFinish());
                            jigIscomplete.bdefautl=contentBean.isFinish();
                            if(contentBean.isFinish()){
                                ahAttach.setVisibility(View.VISIBLE);
                            }else {
                                ahAttach.setVisibility(View.GONE);
                            }
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
                            jig_projectedIntent.setText(contentBean.getVisitIntentional()+"");
                            jig_actualIntent.setText(contentBean.getFinishVisit()+"");

                            if(!Func.IsStringEmpty(contentBean.getFollowUpReportingProject())){
                                jig_followUpReportingProject.setText(contentBean.getFollowUpReportingProject());
                            }
                            if(!Func.IsStringEmpty(contentBean.getFollowUpUnfulfilledProject())){
                                jig_followUpUnfulfilledProject.setText(contentBean.getFollowUpUnfulfilledProject());
                            }
                            if(contentBean.getNcReportingProjects()!=null && contentBean.getNcReportingProjects().size()>0){
                                for(int i=0;i<contentBean.getNcReportingProjects().size();i++){
                                    contentBean.getNcReportingProjects().get( i ).setId( null );
                                }
                                reportingProjectsBeans =contentBean.getNcReportingProjects();
                                menuAdapter.setmViewTypeBeanList(reportingProjectsBeans);
                                menuAdapter.notifyDataSetChanged();
                            }
                            if(contentBean.getNcUnfulfilledProjects()!=null && contentBean.getNcUnfulfilledProjects().size()>0){
                                for(int i=0;i<contentBean.getNcUnfulfilledProjects().size();i++){
                                    contentBean.getNcUnfulfilledProjects().get( i ).setId( null );
                                }
                                unfulfilledProjectsBeans =contentBean.getNcUnfulfilledProjects();
                                unfulMutiAdapter.setmViewTypeBeanList(unfulfilledProjectsBeans);
                                unfulMutiAdapter.notifyDataSetChanged();
                            }
                            if(contentBean.getNcVisitIntentions()!=null && contentBean.getNcVisitIntentions().size()>0){
                                for(int i=0;i<contentBean.getNcVisitIntentions().size();i++){
                                    contentBean.getNcVisitIntentions().get( i ).setId( null );
                                }
                                visitIntentionsBeans =contentBean.getNcVisitIntentions();
                                newVisitIntentMutiAdapter.setmViewTypeBeanList(visitIntentionsBeans);
                                newVisitIntentMutiAdapter.notifyDataSetChanged();
                            }
                            if(contentBean.getNcSignIntentions()!=null && contentBean.getNcSignIntentions().size()>0){
                                for(int i=0;i<contentBean.getNcSignIntentions().size();i++){
                                    contentBean.getNcSignIntentions().get( i ).setId( null );
                                }
                                signIntentionsBeans =contentBean.getNcSignIntentions();
                                signIntentMutiAdapter.setmViewTypeBeanList(signIntentionsBeans);
                                signIntentMutiAdapter.notifyDataSetChanged();
                            }

                            if(contentBean.getNengChengItems()!=null && contentBean.getNengChengItems().size()>0){
                                for(int i=0;i<contentBean.getNengChengItems().size();i++){
                                    contentBean.getNengChengItems().get( i ).setId( null );
                                }
                                huaJueItemsBeans =contentBean.getNengChengItems();
                                marketClientMutiAdapter.setmViewTypeBeanList(huaJueItemsBeans);
                                marketClientMutiAdapter.notifyDataSetChanged();
//                                for(int i=0;i<contentBean.getNengChengItems().size();i++){
//                                    getDetailAttachment("NENG_CHENG_ITEM",contentBean.getNengChengItems().get(i).getId(),i);
//                                }
                            }else{
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                            }
                            if(contentBean.getAttachments()!=null && contentBean.getAttachments().size()>0){
                                attachmentsBeanslist=contentBean.getAttachments();
                                ArrayList mediaArrayList=new ArrayList<>();
                                for(int i = 0; i<contentBean.getAttachments().size(); i++){

                                    AttachmentBean attachmentBean = contentBean.getAttachments().get(i);//把JSON字符串转为对象
                                    Media media=new Media();
                                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                                    media.setExtension(".jpg");
                                    media.setMediaType(0);
                                    if(operatorType.equals(ViewOperatorType.VIEW)){
                                        media.setShowIcon(false);
                                    }
                                    mediaArrayList.add(media);
                                }
                                ahAttach.setMediaArrayList(mediaArrayList);
                                ahAttach.adapter.setMviewBeanList(mediaArrayList);
                                ahAttach.adapter.notifyDataSetChanged();
                                ahAttach.refreshAttach();
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
                .request(new ACallback<MarketNengChengBean.ContentBean>() {
                    @Override
                    public void onSuccess(MarketNengChengBean.ContentBean contentBean) {
                        if(contentBean!=null){
                            createBean=contentBean;
                            if(contentBean.getOperator()!=null && contentBean.getOperator().getName()!=null ){
                                jigSalesman.setText(contentBean.getOperator().getName());
                                inputParamMap.put("id",contentBean.getOperator().getId());
                                inputParamMap.put("oaCode","");
                                getPlanTarget(inputParamMap);
                                getPlanDevelopTarget(inputParamMap);
                            }
                            jigSalesTarget.setText(contentBean.getSalesTarget()+"");
                            if(contentBean.getProvince()!=null){
                                jig_province.setText(contentBean.getProvince());
                            }
                            if(contentBean.getDevelopmentProvince()!=null){
                                jig_developprovince.setText(contentBean.getDevelopmentProvince());
                            }
                            jig_cumulativeShipments.setText(contentBean.getCumulativeShipments()+"");
                            jigProjectedShipment.setText(contentBean.getProjectedShipment()+"");
                            jigActualShipment.setText(contentBean.getActualShipment()+"");
                            jigOmpletionRate.setText(contentBean.getOmpletionRate()+"");
                            jig_ompletionRate2.setText(contentBean.getCompletionRate()+"");
                            jig_salesTarget2.setText(contentBean.getDevelopmentProject()+"");
                            String region="";
                            if(contentBean.getProvinceName()!=null){
                                region+=contentBean.getProvinceName();
                                msprovinceId=contentBean.getProvinceNumber();
                            }
                            if(contentBean.getCityName()!=null){
                                region+=contentBean.getCityName();
                            }
                            if(contentBean.getAreaName()!=null){
                                region+=contentBean.getAreaName();
                            }
                            jigMarkete.setText(region);
                            jigExpectVisit.setText(contentBean.getExpectVisit()+"");
                            jigActualVisit.setText(contentBean.getActualVisit()+"");
                            if(contentBean.getAfterSaleUnprocessed()!=null){
                                jigUnhandle.setText(contentBean.getAfterSaleUnprocessed());
                            }
                            if(contentBean.getActivity()!=null){
                                jigActivity.setText(contentBean.getActivity());
                            }
                            jigIscomplete.tb_type.setChecked(contentBean.isFinish());
                            jigIscomplete.bdefautl=contentBean.isFinish();
                            if(contentBean.isFinish()){
                                ahAttach.setVisibility(View.VISIBLE);
                            }else {
                                ahAttach.setVisibility(View.GONE);
                            }
                            if(!Func.IsStringEmpty(contentBean.getDate())){
                                jigDate.setText(contentBean.getDate());
                            }
                            if(!Func.IsStringEmpty(contentBean.getWorkPlanDate())){
                                jigWorkPlanDate.setText(contentBean.getWorkPlanDate());
                            }
                            if(!Func.IsStringEmpty(contentBean.getRemark())){
                                jigRemark.setText(contentBean.getRemark());
                            }
                            jig_projectedIntent.setText(contentBean.getVisitIntentional()+"");
                            jig_actualIntent.setText(contentBean.getFinishVisit()+"");

                            if(!Func.IsStringEmpty(contentBean.getFollowUpReportingProject())){
                                jig_followUpReportingProject.setText(contentBean.getFollowUpReportingProject());
                            }
                            if(!Func.IsStringEmpty(contentBean.getFollowUpUnfulfilledProject())){
                                jig_followUpUnfulfilledProject.setText(contentBean.getFollowUpUnfulfilledProject());
                            }
                            if(contentBean.getNcReportingProjects()!=null && contentBean.getNcReportingProjects().size()>0){
                                reportingProjectsBeans =contentBean.getNcReportingProjects();
                                menuAdapter.setmViewTypeBeanList(reportingProjectsBeans);
                                menuAdapter.notifyDataSetChanged();
                            }
                            if(contentBean.getNcUnfulfilledProjects()!=null && contentBean.getNcUnfulfilledProjects().size()>0){
                                unfulfilledProjectsBeans =contentBean.getNcUnfulfilledProjects();
                                unfulMutiAdapter.setmViewTypeBeanList(unfulfilledProjectsBeans);
                                unfulMutiAdapter.notifyDataSetChanged();
                            }
                            if(contentBean.getNcVisitIntentions()!=null && contentBean.getNcVisitIntentions().size()>0){
                                visitIntentionsBeans =contentBean.getNcVisitIntentions();
                                newVisitIntentMutiAdapter.setmViewTypeBeanList(visitIntentionsBeans);
                                newVisitIntentMutiAdapter.notifyDataSetChanged();
                            }
                            if(contentBean.getNcSignIntentions()!=null && contentBean.getNcSignIntentions().size()>0){
                                signIntentionsBeans =contentBean.getNcSignIntentions();
                                signIntentMutiAdapter.setmViewTypeBeanList(signIntentionsBeans);
                                signIntentMutiAdapter.notifyDataSetChanged();
                            }

                            if(contentBean.getNengChengItems()!=null && contentBean.getNengChengItems().size()>0){
                                huaJueItemsBeans =contentBean.getNengChengItems();
                                marketClientMutiAdapter.setmViewTypeBeanList(huaJueItemsBeans);
                                marketClientMutiAdapter.notifyDataSetChanged();
                                for(int i=0;i<contentBean.getNengChengItems().size();i++){
                                    getDetailAttachment("NENG_CHENG_ITEM",contentBean.getNengChengItems().get(i).getId(),i);
                                }
                            }else{
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                            }
                            if(contentBean.getAttachments()!=null && contentBean.getAttachments().size()>0){
                                attachmentsBeanslist=contentBean.getAttachments();
                                ArrayList mediaArrayList=new ArrayList<>();
                                for(int i = 0; i<contentBean.getAttachments().size(); i++){

                                    AttachmentBean attachmentBean = contentBean.getAttachments().get(i);//把JSON字符串转为对象
                                    Media media=new Media();
                                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                                    media.setExtension(".jpg");
                                    media.setMediaType(0);
                                    if(operatorType.equals(ViewOperatorType.VIEW)){
                                        media.setShowIcon(false);
                                    }
                                    mediaArrayList.add(media);
                                }
                                ahAttach.setMediaArrayList(mediaArrayList);
                                ahAttach.adapter.setMviewBeanList(mediaArrayList);
                                ahAttach.adapter.notifyDataSetChanged();
                                ahAttach.refreshAttach();
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
            createBean.setSalesTarget(Func.convertToLong(jigSalesTarget.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jig_province.getText().toString().trim())){
            createBean.setProvince(jig_province.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jig_developprovince.getText().toString().trim())){
            createBean.setDevelopmentProvince(jig_developprovince.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jig_salesTarget2.getText().toString().trim())){
            createBean.setDevelopmentProject(Double.parseDouble(jig_salesTarget2.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jig_cumulativeShipments.getText().toString().trim())){
            createBean.setCumulativeShipments(Double.parseDouble(jig_cumulativeShipments.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigProjectedShipment.getText().toString().trim())){
            createBean.setProjectedShipment(Double.parseDouble(jigProjectedShipment.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigActualShipment.getText().toString().trim())){
            createBean.setActualShipment(Double.parseDouble(jigActualShipment.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigOmpletionRate.getText().toString().trim())){
            createBean.setOmpletionRate(Double.parseDouble(jigOmpletionRate.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jig_ompletionRate2.getText().toString().trim())){
            createBean.setCompletionRate(Double.parseDouble(jig_ompletionRate2.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigExpectVisit.getText().toString().trim())){
            createBean.setExpectVisit(Func.convertToLong(jigExpectVisit.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigActualVisit.getText().toString().trim())){
            createBean.setActualVisit(Func.convertToLong(jigActualVisit.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jigUnhandle.getText().toString().trim())){
            createBean.setAfterSaleUnprocessed(jigUnhandle.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigActivity.getText().toString().trim())){
            createBean.setActivity(jigActivity.getText().toString().trim());
        }
        createBean.setFinish(jigIscomplete.bdefautl);
        if(!Func.IsStringEmpty(jigDate.getText().toString().trim())){
            createBean.setDate(jigDate.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigWorkPlanDate.getText().toString().trim())){
            createBean.setWorkPlanDate(jigWorkPlanDate.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigRemark.getText().toString().trim())){
            createBean.setRemark(jigRemark.getText().toString().trim());
        }

        if(!Func.IsStringEmpty(jig_projectedIntent.getText().toString().trim())){
            createBean.setVisitIntentional(Func.convertToLong(jig_projectedIntent.getText().toString().trim()));
        }
        if(!Func.IsStringEmpty(jig_actualIntent.getText().toString().trim())){
            createBean.setFinishVisit(Func.convertToLong( jig_actualIntent.getText().toString().trim()));
        }
//        if(!Func.IsStringEmpty(jigActualVisit.getText().toString().trim())){
//            createBean.setFinishVisit(Func.convertToLong(jigActualVisit.getText().toString().trim()));
//        }


        if(!Func.IsStringEmpty(jig_followUpReportingProject.getText().toString().trim())){
            createBean.setFollowUpReportingProject(jig_followUpReportingProject.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jig_followUpUnfulfilledProject.getText().toString().trim())){
            createBean.setFollowUpUnfulfilledProject(jig_followUpUnfulfilledProject.getText().toString().trim());
        }
        if(unfulfilledProjectsBeans !=null && unfulfilledProjectsBeans.size()>0){
            createBean.setNcUnfulfilledProjects(unfulfilledProjectsBeans);
        }else{
            createBean.setNcUnfulfilledProjects(null);
        }
        if(reportingProjectsBeans !=null && reportingProjectsBeans.size()>0){
            createBean.setNcReportingProjects(reportingProjectsBeans);
        }else{
            createBean.setNcReportingProjects(null);
        }
        if(huaJueItemsBeans !=null && huaJueItemsBeans.size()>0){
            createBean.setNengChengItems(huaJueItemsBeans);
        }else{
            createBean.setNengChengItems(null);
        }
        if(visitIntentionsBeans !=null && visitIntentionsBeans.size()>0){
            createBean.setNcVisitIntentions(visitIntentionsBeans);
        }else{
            createBean.setNcVisitIntentions(null);
        }
        if(signIntentionsBeans !=null && signIntentionsBeans.size()>0){
            createBean.setNcSignIntentions(signIntentionsBeans);
        }else{
            createBean.setNcSignIntentions(null);
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

    public void getSumActualShipment(Map<String, Object> inputParamMap){
        JiuyiHttp.POST(backEndFlag+"/sum-actualShipment/"+jig_province.getText())
                .setJson(GsonUtil.gson().toJson(inputParamMap))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<SumActualShipmentBean>() {
                    @Override
                    public void onSuccess(SumActualShipmentBean result ) {
                        if(result!=null){
                            jig_cumulativeShipments.setText(result.getSumActualShipment()+"");
                            getdailyShipment();
//                            computeRate();
//                            jigSalesTarget.setText(result.getWorkPlan0()+"");
//                            jig_salesTarget2.setText(result.getWorkPlan1()+"");
//                            sumAccumulateVisit=result.getSumAccumulateVisit();
//                            setShipmentData2();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    public void getSumDevelopActualShipment(ProvinceAndBrand provinceAndBrand){
        JiuyiHttp.POST(backEndFlag+"/sum-accumulateVisit")
                .setJson(GsonUtil.gson().toJson(provinceAndBrand))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<SumActualShipmentBean>() {
                    @Override
                    public void onSuccess(SumActualShipmentBean result ) {
                        if(result!=null){
                            sumAccumulateVisit=result.getSumActualShipment();
                            setShipmentData2();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    public void setShipmentData2(){
        if(newVisitIntentMutiAdapter!=null && newVisitIntentMutiAdapter.mViewTypeBeanList.size()>0){
            int count=0;
            for(int i=0;i<newVisitIntentMutiAdapter.mViewTypeBeanList.size();i++){
                if(newVisitIntentMutiAdapter.mViewTypeBeanList.get(i).isVisit()){
                    count++;
                }
            }
            jig_projectedIntent.setText(visitIntentionsBeans.size()+"");
            jig_actualIntent.setText(count+"");
            if(!Func.IsStringEmpty(jig_salesTarget2.getText())   && Double.parseDouble(jig_salesTarget2.getText())>0 ){
                double num= (Double.parseDouble(count+"") +sumAccumulateVisit)/Double.parseDouble(jig_salesTarget2.getText());
                DecimalFormat df = new DecimalFormat("0.00");
                String sfor = df.format(num*100);
                jig_ompletionRate2.setText(sfor);
            }
        }else{
            jig_projectedIntent.setText("0");
            jig_actualIntent.setText("0");
            if(!Func.IsStringEmpty(jig_salesTarget2.getText())   && Double.parseDouble(jig_salesTarget2.getText())>0 ){
                double num= (sumAccumulateVisit)/Double.parseDouble(jig_salesTarget2.getText());
                DecimalFormat df = new DecimalFormat("0.00");
                String sfor = df.format(num*100);
                jig_ompletionRate2.setText(sfor);
            }
        }

    }
    public void setShipmentData(){
        if(menuAdapter!=null && menuAdapter.mViewTypeBeanList.size()>0){
            int count=0;
            for(int i=0;i<menuAdapter.mViewTypeBeanList.size();i++){
                if(menuAdapter.mViewTypeBeanList.get(i).isFollowUp()){
                    count++;
                }
            }
            jigProjectedShipment.setText(huaJueItemsBeans.size()+"");
            jigActualShipment.setText(count+"");
        }else{
            jigProjectedShipment.setText("0");
            jigActualShipment.setText("0");
        }

    }
    private void upload()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.UPLOAD("file/upload-multi")
                        .tag("upload-multi")
                        .addFiles2("file",files)
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<ArrayList<AttachmentBean>>() {
                            @Override
                            public void onSuccess(ArrayList<AttachmentBean> data) {
                                if(data!=null && data.size()>0){
                                    attachmentsBeanslist=data;
                                    if(operatorType.equals(ViewOperatorType.ADD)){
                                        createBean.setAttachments(attachmentsBeanslist);
                                        createBean();
                                    }else if(operatorType.equals(ViewOperatorType.EDIT)||operatorType.equals(ViewOperatorType.SPECIAL)){
                                        for(int i=0;i<attachmentsBeanslist.size();i++){
                                            attachmentsEditBeanslist.add(attachmentsBeanslist.get(i));
                                        }
                                        createBean.setAttachments(attachmentsEditBeanslist);
                                        updateInfo();
                                    }
                                }else{
                                    if(progressLoadingDialog!=null){
                                        progressLoadingDialog.dismiss();
                                    }
                                }

                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                if(progressDialog!=null){
                                    progressDialog.dismiss();
                                }
                                startDialog(DialogID.DialogTrue, "", "附件上传失败！", JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });
            }
        };

        thread.start();
    }

    private void getDetailAttachment(String fktype,Long id,int i) {
        JiuyiHttp.GET("attachment/list/"+fktype+"/"+id)
                .tag("request_get_")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<AttachmentBean>>() {
                    @Override
                    public void onSuccess(List<AttachmentBean> data) {
                        if(data!=null){
                            createBean.getNengChengItems().get(i).setAttachments(data);
                            if(i==createBean.getNengChengItems().size()-1){
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                            }
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressLoadingDialog!=null){
                            progressLoadingDialog.dismiss();
                        }
                    }
                });

    }
public void getPlanTarget(Map<String, Object> inputParamMap){
    JiuyiHttp.POST("work-plan-target-area/findByBrandAndProvince/0")
            .setJson(GsonUtil.gson().toJson(inputParamMap))
            .addHeader("Authorization",Rc.id_tokenparam)
            .request(new ACallback<List<PlanTargetProvinceBrandBean>>() {
                @Override
                public void onSuccess(List<PlanTargetProvinceBrandBean> result ) {
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
        JiuyiHttp.POST("work-plan-target-area/findByBrandAndProvince/0")
                .setJson(GsonUtil.gson().toJson(inputParamMap))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<List<PlanTargetProvinceBrandBean>>() {
                    @Override
                    public void onSuccess(List<PlanTargetProvinceBrandBean> result ) {
                        if(result!=null){
                            planTargetBeanList=result;
                            AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiNengChengMarketNewActivity.this);
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
                                    PlanTargetProvinceBrandBean planTargetProvinceBrandBean=planTargetBeanList.get(i);
                                    if(planTargetProvinceBrandBean.getProvince()!=null && planTargetProvinceBrandBean.getBrand()!=null){
                                        data[i]=planTargetBeanList.get(i).getProvince()+"-"+planTargetBeanList.get(i).getBrand().getBrandName();
                                    }
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
                                                if(planTargetBeanList.get(i).getBrand()!=null){
                                                    brand.setBrandName( planTargetBeanList.get(i).getBrand().getBrandName() );
                                                    brandSet.add( brand );
                                                }
                                                customer.entity.ProvinceBean.ContentBean provinceBean=new customer.entity.ProvinceBean.ContentBean();
                                                provinceBean.setProvinceName(planTargetBeanList.get(i).getProvince()  );
                                                provinceSet.add( provinceBean );
                                            }
                                        }
                                        jig_province.setText(item);
                                        jigSalesTarget.setText(amt+"");
                                        if(operatorType.equals(ViewOperatorType.EDIT) && createBean!=null
                                                && createBean.getProvince()!=null && createBean.getProvince().equals(item)){
                                            jig_cumulativeShipments.setText(createBean.getCumulativeShipments()+"");
                                        }else{
                                            ProvinceAndBrand provinceAndBrand=new ProvinceAndBrand();
                                            provinceAndBrand.setBrandSet( brandSet );
                                            provinceAndBrand.setProvinceSet( provinceSet );
                                            getSapSalesByBrandAdnYear(provinceAndBrand);
//                                            getSumActualShipment(inputParamMap);
                                        }
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
                                Toast.makeText(JiuyiNengChengMarketNewActivity.this, "无匹配结果", Toast.LENGTH_SHORT).show();
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

   /* public void getPlanDevelopTarget(Map<String, Object> inputParamMap){
        JiuyiHttp.POST("work-plan-target/findByOperator/1")
                .setJson(GsonUtil.gson().toJson(inputParamMap))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<List<PlanTargetBean>>() {
                    @Override
                    public void onSuccess(List<PlanTargetBean> result ) {
                        if(result!=null){
                            planDevelopTargetBeanList=result;
//                            jigSalesTarget.setText(result.getWorkPlan());
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }*/

    public void getPlanDevelopTarget(Map<String, Object> inputParamMap){
        JiuyiHttp.POST("work-plan-target-area/findByBrandAndProvince/2")
                .setJson(GsonUtil.gson().toJson(inputParamMap))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<List<PlanTargetProvinceBrandBean>>() {
                    @Override
                    public void onSuccess(List<PlanTargetProvinceBrandBean> result ) {
                        if(result!=null){
                            planDevelopTargetBeanList=result;
//                            jigSalesTarget.setText(result.getWorkPlan());
                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    public void getdailyShipment( ){
        JiuyiHttp.GET("retail-channel/daily-develivery/"+jig_province.getText())
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<SumActualShipmentBean>() {
                    @Override
                    public void onSuccess(SumActualShipmentBean result ) {
                        if(result!=null){
                            jigActualShipment.setText(result.getTodayDeliveryTotal()+"");
                            computeRate();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    public void computeRate(){
        if(!Func.IsStringEmpty(jigSalesTarget.getText().toString()) && !Func.IsStringEmpty(jig_cumulativeShipments.getText().toString())
                && !Func.IsStringEmpty(jigActualShipment.getText().toString())){
            double total;
            total=Double.parseDouble(jigActualShipment.getText().toString())+Double.parseDouble(jig_cumulativeShipments.getText().toString());
            if(Double.parseDouble(jigSalesTarget.getText().toString())>0){
                double num= total/Double.parseDouble(jigSalesTarget.getText().toString());
                DecimalFormat df = new DecimalFormat("0.00");
                String sfor = df.format(num*100);
                jigOmpletionRate.setText(sfor);
            }

        }
    }
    public void getCustomerByRegion(Map<String, Object> inputParamMap){
        JiuyiHttp.POST("member/find-careful-area")
                .setJson(GsonUtil.gson().toJson(inputParamMap))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<List<MemberBeanID>>() {
                    @Override
                    public void onSuccess(List<MemberBeanID> result ) {
                        huaJueItemsBeans = new ArrayList<>();
                        if(result!=null  && result.size()>0){
                            for(int i=0;i<result.size();i++){
                                MarketHuaJueBean.HuaJueItemsBean huaJueItemsBean =new MarketHuaJueBean.HuaJueItemsBean();
                                huaJueItemsBean.setMember(result.get(i));
                                if(!huaJueItemsBeans.contains( huaJueItemsBean )){
                                    huaJueItemsBeans.add(huaJueItemsBean);
                                }

                            }

                        }
                        if (marketClientMutiAdapter == null) {
                            marketClientMutiAdapter = new NewMarketHuaJueMutiAdapter(huaJueItemsBeans);
                            marketClientMutiAdapter.setOnItemClickListener(onItemClickListener3);
                            rv_clientdetaillist.setAdapter(marketClientMutiAdapter);
                        } else {
                            marketClientMutiAdapter.setmViewTypeBeanList(huaJueItemsBeans);
                            marketClientMutiAdapter.notifyDataSetChanged();
                        }
                        int count=0;
                        for(int i=0;i<marketClientMutiAdapter.mViewTypeBeanList.size();i++){
                            if(marketClientMutiAdapter.mViewTypeBeanList.get(i).isVisit()){
                                count++;
                            }
                        }
                        jigExpectVisit.setText(huaJueItemsBeans.size()+"");
                        jigActualVisit.setText(count+"");
                    }


                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    public void getProjectByProvince(Map<String, Object> inputParamMap){
        JiuyiHttp.POST("market-engineering/require-engineer")
                .setJson(GsonUtil.gson().toJson(inputParamMap))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<List<ProvinceProjectBean>>() {
                    @Override
                    public void onSuccess(List<ProvinceProjectBean> result ) {
                        unfulfilledProjectsBeans =new ArrayList<>();
                        if(result!=null  && result.size()>0){
                            for(int i=0;i<result.size();i++){
                                MarketHuaJueBean.HjUnfulfilledProjectsBean contentBean=new MarketHuaJueBean.HjUnfulfilledProjectsBean();
                                contentBean.setEngineering( result.get( i ).getProjectName() );
                                if(!unfulfilledProjectsBeans.contains( contentBean )){
                                    unfulfilledProjectsBeans.add(contentBean);
                                }
                            }
                        }

                        unfulMutiAdapter = new NewUnfulProjectHuJueMutiAdapter(unfulfilledProjectsBeans);
                        unfulMutiAdapter.setOnItemClickListener(onItemClickListener2);
                        rv_unfuldetaillist.setAdapter(unfulMutiAdapter);
                    }


                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    public void getSapSalesByBrandAdnYear(ProvinceAndBrand provinceAndBrand){
        JiuyiHttp.POST("retail-channel/getSapSalesByBrandAdnYear")
                .setJson(GsonUtil.gson().toJson(provinceAndBrand))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<List<SumActualShipAndDayBean>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(List<SumActualShipAndDayBean> result ) {
                        if(result!=null && result.size()>0){
                            double actualShipTotal = 0,dayTotal = 0;
                            for(int i=0;i<result.size();i++){
                                actualShipTotal+=result.get( i ).getCumulativeSalesForTheMonth();
                                dayTotal+=result.get( i ).getActualSales();
                            }
                            jig_cumulativeShipments.setText(actualShipTotal+"");
                            jigActualShipment.setText(dayTotal+"");
                        }else{
                            jig_cumulativeShipments.setText(0+"");
                            jigActualShipment.setText(0+"");
                        }
                        computeRate();
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

}
