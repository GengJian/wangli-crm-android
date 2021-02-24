package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.bean.NormalOperatorBean;
import customer.Utils.ViewOperatorType;
import customer.adapter.AcountinfoAdapter;
import customer.entity.BrandBean;
import customer.entity.CommonBeanID;
import customer.entity.CustomerCreateBean;
import customer.entity.CustomerPlanBean;
import customer.entity.MaterialTypeBean;
import customer.entity.MemberBean;
import customer.entity.MemberBeanID;
import customer.entity.MemberReadBean;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.entity.DyClueBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNewCustomerActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 新增客户页面
 *****************************************************************
 */
public class JiuyiNewCustomerActivity extends JiuyiActivityBase {
    TextView tv_accountinfo,tv_businessinfo,mtvcomplete;
    RecyclerView rv_account,rv_businessinfo;
    private List<MemberReadBean.ContentBean.DataBean> beanList,businessinfoList;
    private AcountinfoAdapter acountinfoAdapter,businessinfoAdapter;
    private long Customerid=-1;
    private String sReturnvalue;
    private int accountPostion=-1,bussinessPosition=-1;
    private MemberBean.ContentBean memberBean;
    private List<CustomerCreateBean> customerCreateBeanList=new ArrayList<>();
//    private Map <String, String> memberMap= new HashMap <String, String>();
    private Map <String, Object> memberMap= new HashMap <String, Object>();
    //申明对象
    CityPickerView mCityPickerView=new CityPickerView();
    private String msprovince="";
    private String mscity="";
    private String msdistrict="";
    private List<BrandBean.ContentBean> mProductTypeSelectList;

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernew_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        mCityPickerView.init(this);
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setConfirmTextColorStr("#0089d1");
        mCityPickerView.setConfig(cityConfig);
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        beanList=new ArrayList<>();
        businessinfoList=new ArrayList<>();
        tv_accountinfo=(TextView)mBodyLayout.findViewById(Res.getViewID(this, "tv_accountinfo"));
        rv_account=(RecyclerView)mBodyLayout.findViewById(Res.getViewID(this, "rv_account"));
        rv_account.setLayoutManager(new LinearLayoutManager(JiuyiNewCustomerActivity.this));
        rv_account.setNestedScrollingEnabled(false);
        rv_account.setHasFixedSize(true);
        rv_account.setItemAnimator(new DefaultItemAnimator());
        rv_account.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiNewCustomerActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rv_businessinfo=(RecyclerView)mBodyLayout.findViewById(Res.getViewID(this, "rv_businessinfo"));
        rv_businessinfo.setLayoutManager(new LinearLayoutManager(JiuyiNewCustomerActivity.this));
        rv_businessinfo.setNestedScrollingEnabled(false);
        rv_businessinfo.setHasFixedSize(true);
        rv_businessinfo.setItemAnimator(new DefaultItemAnimator());
        rv_businessinfo.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiNewCustomerActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(this, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    submit();
                }
            });
        }
        showProcessBar(0);
        getBaseInfoList();
    }

    public void setTitle(){
        mTitle = "新建客户";
        setTitle(mTitle);
    }
    private void getBaseInfoList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("member/find-read-bean/-1")
                        .tag("request_get_member")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<MemberReadBean>() {
                            @Override
                            public void onSuccess(MemberReadBean data) {
                                JiuyiLog.e("getsig","request onSuccess!");
                                List<MemberReadBean.ContentBean>  contentBeanList=data.getContent();
                                if(contentBeanList!=null &&contentBeanList.size()>0){
                                    for(MemberReadBean.ContentBean contentBean :contentBeanList){
                                        if(contentBean.getTitle().equals("账户信息")){
                                            List<MemberReadBean.ContentBean.DataBean> dataBeanList=contentBean.getData();
                                            if(dataBeanList!=null && dataBeanList.size()>0){
                                                for(MemberReadBean.ContentBean.DataBean dataBean :dataBeanList){
                                                    if(dataBean.isChange()){
                                                        beanList.add(dataBean) ;
                                                    }
                                                }
                                            }
                                            acountinfoAdapter = new AcountinfoAdapter(R.layout.customer_baseinfo_item, beanList,"add");
                                            acountinfoAdapter.setOnCLickItemListener(new AcountinfoAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(final int position, String colname, View view) {
                                                    if(colname.equals("rightContent")){
                                                        if(beanList!=null && beanList.size()>0 && beanList.get(position).isChange()){
                                                            if(beanList.get(position).getField().equals("cooperationTypeValue")){
                                                                return;
                                                            }
                                                            if(beanList.get(position).getField().equals("firstDistributor")){
                                                                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                                                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                                                                mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                                                                accountPostion=position;
                                                                bussinessPosition=-1;
                                                                changePage(mBundle,Pub.CustomerSelect,true);
                                                            }else  if(beanList.get(position).getField().equals("region")){
                                                                mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
                                                                    @Override
                                                                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                                                                        StringBuilder sb = new StringBuilder();
                                                                        if (province != null) {
                                                                            msprovince=province.getName();
                                                                            sb.append(province.getName() /*+ " " + province.getId()*/ );
                                                                            memberMap.put("provinceName",msprovince);
                                                                            memberMap.put("provinceNumber",province.getId());
                                                                        }

                                                                        if (city != null) {
                                                                            mscity=city.getName();
                                                                            sb.append(city.getName()/* + " " + city.getId()*/ );
                                                                            memberMap.put("cityName",mscity);
                                                                            memberMap.put("cityNumber",city.getId());
                                                                        }

                                                                        if (district != null) {
                                                                            msdistrict=district.getName();
                                                                            sb.append(district.getName() /*+ " " + district.getId()*/ );
                                                                            memberMap.put("areaName",msdistrict);
                                                                            memberMap.put("areaNumber",district.getId());
                                                                        }else{
                                                                            msdistrict="";
                                                                        }
                                                                        acountinfoAdapter.getData().get(position).setRightContent(sb.toString());
                                                                        acountinfoAdapter.notifyDataSetChanged();
                                                                    }

                                                                    @Override
                                                                    public void onCancel() {
                                                                    }
                                                                });
                                                                mCityPickerView.showCityPicker();
                                                            }else{
                                                                if(beanList.get(position).getDictField()!=null){
                                                                    if(beanList.get(position).getField().equals("field")||beanList.get(position).getField().equals("businessScope")){
                                                                        AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiNewCustomerActivity.this);
                                                                        buidler.setTitle(beanList.get(position).getLeftContent()/*+"选择"*/);

                                                                        final boolean b[];
                                                                        final String[] data;
                                                                        final List<DictBean> dictBeansList= DictUtils.getDictList(beanList.get(position).getDictField());
                                                                        if (dictBeansList!=null && dictBeansList.size()>0){
                                                                            data=new String[dictBeansList.size()];
                                                                            b=new boolean[dictBeansList.size()];
                                                                            for(int i=0;i<dictBeansList.size();i++){
                                                                                data[i]=dictBeansList.get(i).getValue();
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
                                                                                    //取出被勾选中的内容
                                                                                    ArrayList selectList=new ArrayList<>();
                                                                                    for(int i=0;i<dictBeansList.size();i++){
                                                                                        if(b[i]){             //如果被勾线则保存数据
                                                                                            item+=data[i]+",";
                                                                                            selectList.add(dictBeansList.get(i));
                                                                                        }
                                                                                    }
                                                                                    if(!Func.IsStringEmpty(item)){
                                                                                        String sValue=item.substring(0,item.length()-1);
                                                                                        acountinfoAdapter.getData().get(position).setRightContent(sValue);
                                                                                        beanList.get(position).setRightContent(sValue);


                                                                                        if(memberMap!=null && memberMap.containsKey(acountinfoAdapter.getData().get(position).getField())){
                                                                                            memberMap.remove(acountinfoAdapter.getData().get(position).getField());
                                                                                        }
                                                                                        memberMap.put(acountinfoAdapter.getData().get(position).getField(),sValue);
                                                                                        acountinfoAdapter.notifyDataSetChanged();
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
                                                                        }

                                                                    }else {
                                                                        AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiNewCustomerActivity.this);
                                                                        buidler.setTitle(beanList.get(position).getLeftContent()/*+"选择"*/);
                                                                        final String[] data;
                                                                        final List<DictBean> dictBeansList= DictUtils.getDictList(beanList.get(position).getDictField());
                                                                        if(dictBeansList!=null &&dictBeansList.size()>0){
                                                                            data=new String[dictBeansList.size()];
                                                                            for(int i=0;i<dictBeansList.size();i++){
                                                                                data[i]=dictBeansList.get(i).getValue();
                                                                            }
                                                                            buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                                                                                @Override
                                                                                public void onClick(DialogInterface dialog, int which) {
                                                                                    String sValue=dictBeansList.get(which).getValue();
                                                                                    acountinfoAdapter.getData().get(position).setRightContent(sValue);
                                                                                    beanList.get(position).setRightContent(sValue);
                                                                                    String key="",value="";
                                                                                    value=acountinfoAdapter.getData().get(position).getField();

                                                                                    if(value.endsWith("Value")){
                                                                                        key=value.substring(0,value.length()-5)+"Key";
                                                                                    }

//                                                                                    if(memberMap!=null && memberMap.containsKey(acountinfoAdapter.getData().get(position).getField())){
//                                                                                        memberMap.remove(acountinfoAdapter.getData().get(position).getField());
//                                                                                        memberMap.remove(key);
//                                                                                    }
                                                                                    if(acountinfoAdapter.getData().get(position).getField().equals("listing")){
                                                                                        if(dictBeansList.get(which).getKey().equals("true")){
                                                                                            memberMap.put("listing",true);
                                                                                        }else if(dictBeansList.get(which).getKey().equals("false")){
                                                                                            memberMap.put("listing",false);
                                                                                        }
                                                                                    }else{
                                                                                        memberMap.put(acountinfoAdapter.getData().get(position).getField(),sValue);
                                                                                        if(!Func.IsStringEmpty(key)){
                                                                                            memberMap.put(key,dictBeansList.get(which).getKey());
                                                                                        }

                                                                                    }
                                                                                    acountinfoAdapter.notifyDataSetChanged();
                                                                                    acountinfoAdapter.setPos(position);

                                                                                    dialog.dismiss();
                                                                                }
                                                                            });
                                                                            buidler.show();
                                                                        }
                                                                    }


                                                                }else {
                                                                    mBundle.putParcelable(JiuyiBundleKey.PARAM_CUSTOMERCOLBEAN, beanList.get(position));
                                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                                                                    mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerupdatecolInfo);
                                                                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "2");
                                                                    accountPostion=position;
                                                                    bussinessPosition=-1;
                                                                    changePage(mBundle, Pub.CustomerupdatecolInfo,true);
                                                                }
                                                            }

                                                        }

                                                    }
                                                }
                                            });
                                            rv_account.setAdapter(acountinfoAdapter);
                                        }else if(contentBean.getTitle().equals("业务信息")){
//                                            businessinfoList=contentBean.getData();
                                            List<MemberReadBean.ContentBean.DataBean> dataBeanList=contentBean.getData();
                                            if(dataBeanList!=null && dataBeanList.size()>0){
                                                for(MemberReadBean.ContentBean.DataBean dataBean :dataBeanList){
                                                    if(dataBean.isChange()){
                                                        businessinfoList.add(dataBean) ;
                                                    }
                                                }
                                            }
                                            businessinfoAdapter = new AcountinfoAdapter(R.layout.customer_baseinfo_item, businessinfoList);
                                            rv_businessinfo.setAdapter(businessinfoAdapter);
                                            businessinfoAdapter.setOnCLickItemListener(new AcountinfoAdapter.onCLickItemListener() {
                                                @Override
                                                public void click(final int position, String colname, View view) {
                                                    if(businessinfoList!=null && businessinfoList.size()>0 && businessinfoList.get(position).isChange()){

                                                        if(businessinfoList.get(position).getDictField()!=null){
                                                            AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiNewCustomerActivity.this);
                                                            if(businessinfoList.get(position).getDictField().toLowerCase().equals("country_city")){
                                                                buidler.setTitle("国家、地区、城市");
                                                            }else{
                                                                buidler.setTitle(businessinfoList.get(position).getLeftContent()/*+"选择"*/);
                                                            }

                                                            final String[] data;
                                                            final List<DictBean> dictBeansList= DictUtils.getDictList(businessinfoList.get(position).getDictField());
                                                            if(dictBeansList!=null &&dictBeansList.size()>0){
                                                                data=new String[dictBeansList.size()];
                                                                for(int i=0;i<dictBeansList.size();i++){
                                                                    data[i]=dictBeansList.get(i).getValue();
                                                                }
                                                                buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        String sValue=dictBeansList.get(which).getValue();
                                                                        businessinfoAdapter.getData().get(position).setRightContent(sValue);
                                                                        businessinfoList.get(position).setRightContent(sValue);
                                                                        String key="",value="";
                                                                        value=businessinfoAdapter.getData().get(position).getField();

                                                                        if(value.endsWith("Value")){
                                                                            key=value.substring(0,value.length()-5)+"Key";
                                                                        }


//                                                                        if(memberMap!=null && memberMap.containsKey(businessinfoAdapter.getData().get(position).getField())){
//                                                                            memberMap.remove(businessinfoAdapter.getData().get(position).getField());
//                                                                            memberMap.remove(key);
//                                                                        }
                                                                        memberMap.put(businessinfoAdapter.getData().get(position).getField(),sValue);
                                                                        if(!Func.IsStringEmpty(key)){
                                                                            memberMap.put(key,dictBeansList.get(which).getKey());
                                                                        }

                                                                        businessinfoAdapter.notifyDataSetChanged();

                                                                        dialog.dismiss();
                                                                    }
                                                                });
                                                                buidler.show();
                                                            }
                                                        }else if(businessinfoList.get(position).getField().toLowerCase().endsWith("date")){
                                                            DatePickDialog dialog = new DatePickDialog(JiuyiNewCustomerActivity.this);
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
                                                                    String sValue=new SimpleDateFormat("yyyy-MM-dd").format(date);
                                                                    businessinfoAdapter.getData().get(position).setRightContent(sValue);
                                                                    businessinfoList.get(position).setRightContent(sValue);

//                                                                    if(memberMap!=null && memberMap.containsKey(businessinfoAdapter.getData().get(position).getField())){
//                                                                        memberMap.remove(businessinfoAdapter.getData().get(position).getField());
//                                                                    }
                                                                    memberMap.put(businessinfoAdapter.getData().get(position).getField(),sValue);
                                                                    businessinfoAdapter.notifyDataSetChanged();
//                                                                    tv_type.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                                                                }
                                                            });
                                                            dialog.show();
                                                        }else if(businessinfoList.get(position).getField().equals("operatingPeriodFrom")||businessinfoList.get(position).getField().equals("operatingPeriodTo")){
                                                            DatePickDialog dialog = new DatePickDialog(JiuyiNewCustomerActivity.this);
                                                            //设置上下年分限制
                                                            dialog.setYearLimt(60);
                                                            //设置标题
                                                            dialog.setTitle("选择时间");
                                                            //设置类型
                                                            dialog.setType(DateType.TYPE_ALL);
                                                            //设置消息体的显示格式，日期格式
                                                            dialog.setMessageFormat("yyyy-MM-dd");
                                                            //设置选择回调
                                                            dialog.setOnChangeLisener(null);
                                                            //设置点击确定按钮回调
                                                            dialog.setOnSureLisener(new OnSureLisener() {
                                                                @Override
                                                                public void onSure(Date date) {
                                                                    String sValue=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
                                                                    businessinfoAdapter.getData().get(position).setRightContent(sValue);
                                                                    businessinfoList.get(position).setRightContent(sValue);

//                                                                    if(memberMap!=null && memberMap.containsKey(businessinfoAdapter.getData().get(position).getField())){
//                                                                        memberMap.remove(businessinfoAdapter.getData().get(position).getField());
//                                                                    }
                                                                    memberMap.put(businessinfoAdapter.getData().get(position).getField(),sValue);
                                                                    businessinfoAdapter.notifyDataSetChanged();
                                                                }
                                                            });
                                                            dialog.show();
                                                        } else if(businessinfoList.get(position).getField().equals("operatorName")){
                                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "200");
                                                            bussinessPosition=position;
                                                            accountPostion=-1;
                                                            changePage(mBundle, Pub.CustomerSelect,true);
                                                        }else if(businessinfoList.get(position).getField().equals("brandNames")){
                                                            Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                                                            Bundle mBundle = new Bundle();
                                                            if(mProductTypeSelectList==null){
                                                                mProductTypeSelectList=new ArrayList<>();
                                                            }
                                                            mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mProductTypeSelectList);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"BRAND");
                                                            intent.putExtras(mBundle);
                                                            startActivityForResult(intent, 206);
                                                            bussinessPosition=position;
                                                            accountPostion=-1;
                                                        }else {
                                                            mBundle.putParcelable(JiuyiBundleKey.PARAM_CUSTOMERCOLBEAN, businessinfoList.get(position));
                                                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                                                            mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.CustomerupdatecolInfo);
                                                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "2");
                                                            bussinessPosition=position;
                                                            accountPostion=-1;
                                                            changePage(mBundle, Pub.CustomerupdatecolInfo,true);
                                                        }

                                                    }
                                                }
                                            });

                                        }
                                    }
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
        };
        thread.start();
    }
    public void submit(){
        boolean mbcheck=false;
        mbcheck=check();
        if(!mbcheck){
            return;
        }
        if(Func.IsStringEmpty(memberMap.toString())){
            return;
        }
        JiuyiHttp.POST("member/create")
                .setJson(GsonUtil.gson().toJson(memberMap))
                .addHeader("Authorization", Rc.id_tokenparam)

                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result) {
                        startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        Rc.mUpdate=false;
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }
    public boolean check(){
        if( acountinfoAdapter!=null && acountinfoAdapter.getData().size()>0){
            for(int i=0;i<acountinfoAdapter.getData().size();i++){
                String sValue="";
                if(acountinfoAdapter.getData().get(i).getField().equals("memberLevelValue")){
                    if(Func.IsStringEmpty(acountinfoAdapter.getData().get(i).getRightContent())){
                        sValue="普通";
                        acountinfoAdapter.getData().get(i).setRightContent(sValue);
                        beanList.get(i).setRightContent(sValue);
                        if(memberMap!=null && memberMap.containsKey(acountinfoAdapter.getData().get(i).getField())){
                            memberMap.remove(acountinfoAdapter.getData().get(i).getField());
                        }
                        memberMap.put("memberLevelKey","ordinary");
                        memberMap.put(acountinfoAdapter.getData().get(i).getField(),sValue);
                        acountinfoAdapter.notifyDataSetChanged();
                    }
                }
                if(acountinfoAdapter.getData().get(i).getField().equals("creditLevelValue")){
                    if(Func.IsStringEmpty(acountinfoAdapter.getData().get(i).getRightContent())){
                        sValue="A";
                        acountinfoAdapter.getData().get(i).setRightContent(sValue);
                        beanList.get(i).setRightContent(sValue);
                        if(memberMap!=null && memberMap.containsKey(acountinfoAdapter.getData().get(i).getField())){
                            memberMap.remove(acountinfoAdapter.getData().get(i).getField());
                        }
                        memberMap.put("creditLevelKey","A");
                        memberMap.put(acountinfoAdapter.getData().get(i).getField(),sValue);
                        acountinfoAdapter.notifyDataSetChanged();
                    }
                }
                if(acountinfoAdapter.getData().get(i).getField().equals("cooperationTypeValue")){
                    memberMap.put("cooperationTypeKey","two_level_distributor");
                    memberMap.put(acountinfoAdapter.getData().get(i).getField(),"二级分销商");
//                    if(Func.IsStringEmpty(acountinfoAdapter.getData().get(i).getRightContent())){
//                        sValue="二级分销商";
//                        acountinfoAdapter.getData().get(i).setRightContent(sValue);
//                        beanList.get(i).setRightContent(sValue);
//                        if(memberMap!=null && memberMap.containsKey(acountinfoAdapter.getData().get(i).getField())){
//                            memberMap.remove(acountinfoAdapter.getData().get(i).getField());
//                        }
//                        memberMap.put("cooperationTypeKey","two_level_distributor");
//                        memberMap.put(acountinfoAdapter.getData().get(i).getField(),sValue);
//                        acountinfoAdapter.notifyDataSetChanged();
//                    }
                }
                sValue=acountinfoAdapter.getData().get(i).getRightContent();
                if(Func.IsStringEmpty(sValue)){
                    startDialog(DialogID.DialogDoNothing, "", "请完善账号信息！", JiuyiDialogBase.Dialog_Type_Yes, null);
                     return false;
                }
            }
        }
        return true;
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction== DialogID.DialogTrue)
        {
            backPage();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent intent){
        if(intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle bundle=null;
        switch (requestCode) {
            case 1:
                bundle = intent.getExtras();
                Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                String CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                if(Customerid>0 && !Func.IsStringEmpty(CustomerName) ){
                    MemberBeanID memberBeanID=new MemberBeanID();
                    memberBeanID.setId(Customerid);
                    Rc.mUpdate = true;
                    acountinfoAdapter.getData().get(accountPostion).setRightContent(CustomerName);
                    beanList.get(accountPostion).setRightContent(CustomerName);


                    if (memberMap != null && memberMap.containsKey(acountinfoAdapter.getData().get(accountPostion).getField())) {
                        memberMap.remove(acountinfoAdapter.getData().get(accountPostion).getField());
                    }
                    if (memberMap != null && memberMap.containsKey("firstDistributor")) {
                        memberMap.remove("firstDistributor");
                        memberMap.remove("firstDistributor");
                    }
                    memberMap.put("firstDistributor", memberBeanID);
                    acountinfoAdapter.notifyDataSetChanged();
                    accountPostion = -1;
                }
                break;
            case 2:
                bundle = intent.getExtras();
                sReturnvalue = bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE);
                if (!Func.IsStringEmpty(sReturnvalue)) {
                    if (accountPostion >= 0 && acountinfoAdapter != null && acountinfoAdapter.getData().size() > 0) {
                        Rc.mUpdate = true;
                        acountinfoAdapter.getData().get(accountPostion).setRightContent(sReturnvalue);
                        beanList.get(accountPostion).setRightContent(sReturnvalue);


//                        if (memberMap != null && memberMap.containsKey(acountinfoAdapter.getData().get(accountPostion).getField())) {
//                            memberMap.remove(acountinfoAdapter.getData().get(accountPostion).getField());
//                        }
                        memberMap.put(acountinfoAdapter.getData().get(accountPostion).getField(), sReturnvalue);

                        acountinfoAdapter.notifyDataSetChanged();
                        accountPostion = -1;

                    } else if (bussinessPosition >= 0 && businessinfoAdapter != null && businessinfoAdapter.getData().size() > 0) {
                        businessinfoAdapter.getData().get(bussinessPosition).setRightContent(sReturnvalue);
                        businessinfoList.get(bussinessPosition).setRightContent(sReturnvalue);
//                        if (memberMap != null && memberMap.containsKey(businessinfoAdapter.getData().get(bussinessPosition).getField())) {
//                            memberMap.remove(businessinfoAdapter.getData().get(bussinessPosition).getField());
//                        }
                        memberMap.put(businessinfoAdapter.getData().get(bussinessPosition).getField(), sReturnvalue);
                        businessinfoAdapter.notifyDataSetChanged();
                        bussinessPosition = -1;
                    }
                }
                break;
            case 200:
                bundle = intent.getExtras();
                String name2=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long valueId=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(!Func.IsStringEmpty(name2) && valueId>0 && bussinessPosition>=0){
                    businessinfoAdapter.getData().get(bussinessPosition).setRightContent(name2);
                    businessinfoList.get(bussinessPosition).setRightContent(name2);
                    NormalOperatorBean.OperatorBeanID operatorBeanID=new NormalOperatorBean.OperatorBeanID();
                    operatorBeanID.setName(name2);
                    operatorBeanID.setId(valueId);
                    memberMap.put("operator", operatorBeanID);
                    businessinfoAdapter.notifyDataSetChanged();
                    bussinessPosition = -1;
                }else{
                    businessinfoAdapter.getData().get(bussinessPosition).setRightContent("");
                    businessinfoList.get(bussinessPosition).setRightContent("");
                    memberMap.put("operator", null);
                    businessinfoAdapter.notifyDataSetChanged();
                    bussinessPosition = -1;
                }

                break;
            case 206:
                bundle = intent.getExtras();
                mProductTypeSelectList=bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
                List<customer.entity.CustomerPlanBean.ContentBean.BrandBean> bigCategoryList=new ArrayList<>();
                String name="";
                if(mProductTypeSelectList!=null && mProductTypeSelectList.size()>0){
                    for(int i=0;i<mProductTypeSelectList.size();i++){
                        name+=mProductTypeSelectList.get(i).getBrandName()+",";
                        customer.entity.CustomerPlanBean.ContentBean.BrandBean    commonBeanID=new customer.entity.CustomerPlanBean.ContentBean.BrandBean();
                        commonBeanID.setId(mProductTypeSelectList.get(i).getId());
                        commonBeanID.setBrandName(mProductTypeSelectList.get(i).getBrandName());
                        bigCategoryList.add(commonBeanID);
                    }
                    memberMap.put("brands",bigCategoryList);
                    businessinfoAdapter.getData().get(bussinessPosition).setRightContent(name.substring(0,name.length()-1));
                    businessinfoList.get(bussinessPosition).setRightContent(name.substring(0,name.length()-1));
                    businessinfoAdapter.notifyDataSetChanged();
                    bussinessPosition = -1;
                }

                break;

            default:
              break;

        }
    }
}
