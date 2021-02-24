package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commonlyused.bean.JiuyiRegionBean;
import commonlyused.bean.NormalOperatorBean;
import customer.Utils.ViewOperatorType;
import customer.api.CustomerService;
import customer.entity.BrandBean;
import customer.entity.CommonBeanID;
import customer.entity.MemberBeanID;
import customer.entity.MemberReadBean;
import customer.entity.MemberUpdateBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerUpdateColInfoActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 客户信息修改页面
 *****************************************************************
 */
public class JiuyiCustomerUpdateColInfoActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,mtvcancel,mtvdate,tv_type;
    private String startDate;
    MemberReadBean.ContentBean.DataBean contentBean;
    private String operatorType;
    private String col="",typeDictName;
    private JiuyiEditText et_col;
    private  long customerid=-1;
    private String s_returnvalue;
    private String msControlFlag="";
    MemberUpdateBean updateBean;
    //申明对象
    CityPickerView mCityPickerView=new CityPickerView();
    private String msprovince="";
    private String mscity="";
    private String msdistrict="";
    private JiuyiRegionBean jiuyiRegionBean;
    private String sRegion="";
    MemberBeanID memberBeanID;
    private List<BrandBean.ContentBean> mProductTypeSelectList;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customerupdatecolinfo_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        operatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        contentBean=mBundle.getParcelable(JiuyiBundleKey.PARAM_CUSTOMERCOLBEAN);
        msControlFlag=mBundle.getString(JiuyiBundleKey.PARAM_UPDATECONTROL);
        setTitle();
        mCityPickerView.init(this);
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setConfirmTextColorStr("#0089d1");
        mCityPickerView.setConfig(cityConfig);
        tv_type= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_type"));
        et_col = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_col"));
         updateBean=new MemberUpdateBean();
        if(contentBean!=null){
            if(contentBean.getDictField()!=null||contentBean.getField().toLowerCase().endsWith("date")||contentBean.getField().toLowerCase().equals("birthday")
                    ||contentBean.getField().equals("brandNames")||contentBean.getField().equals("operatorName")
                    ||contentBean.getField().equals("region")
                    ||contentBean.getField().equals("firstDistributor")){
                tv_type.setVisibility(View.VISIBLE);
                if(contentBean.getRightContent()!=null){
                    tv_type.setText(contentBean.getRightContent());
                }
                et_col.setVisibility(View.GONE);
                if(contentBean.getField().toLowerCase().endsWith("date")||contentBean.getField().toLowerCase().equals("birthday")){
                    tv_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatePickDialog dialog = new DatePickDialog(JiuyiCustomerUpdateColInfoActivity.this);
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
                                    tv_type.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                                }
                            });
                            dialog.show();
                        }
                    });
                }else if(contentBean.getField().equals("operatorName")){
                    tv_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "205");
                            changePage(mBundle, Pub.CustomerSelect,true);
                        }
                    });
                }else if(contentBean.getField().equals("brandNames")){
                    tv_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                            Bundle mBundle = new Bundle();
//                            mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mProductTypeSelectList);
                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"BRAND");
                            intent.putExtras(mBundle);
                            startActivityForResult(intent, 206);
                        }
                    });
                }else if(contentBean.getField().equals("firstDistributor")){
                    tv_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                            mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                            changePage(mBundle,Pub.CustomerSelect,true);
                        }
                    });
                }else if(contentBean.getField().equals("region")){
                    tv_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
                                @Override
                                public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                                    StringBuilder sb = new StringBuilder();
                                    sRegion="";
//                                    jiuyiRegionBean=new JiuyiRegionBean();
                                    if (province != null) {
                                        msprovince=province.getName();
                                        sb.append(province.getName() /*+ " " + province.getId()*/ );
                                        sRegion+=province.getId()+"-"+msprovince+"-";
                                    }else{
                                        sRegion+=" -";
                                    }

                                    if (city != null) {
                                        mscity=city.getName();
                                        sb.append(city.getName()/* + " " + city.getId()*/ );
                                        sRegion+=city.getId()+"-"+mscity+"-";
                                    }else{
                                        sRegion+=" -";
                                    }

                                    if (district != null) {
                                        msdistrict=district.getName();
                                        sb.append(district.getName() /*+ " " + district.getId()*/ );
                                        sRegion+=district.getId()+"-"+msdistrict;
                                    }else{
                                        msdistrict="";
                                        sRegion+=" ";
                                    }
                                    tv_type.setText(msprovince+"-"+mscity+"-"+msdistrict);
                                }

                                @Override
                                public void onCancel() {
                                }
                            });
                            mCityPickerView.showCityPicker();
                        }
                    });
                }else if(contentBean.getField().equals("field")||contentBean.getField().equals("businessScope")){
                    tv_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerUpdateColInfoActivity.this);
                            buidler.setTitle(contentBean.getLeftContent()/*+"选择"*/);

                            final boolean b[];
                            final String[] data;
                            final List<DictBean> dictBeansList= DictUtils.getDictList(contentBean.getDictField());
                            if (dictBeansList!=null && dictBeansList.size()>0) {
                                data = new String[dictBeansList.size()];
                                b = new boolean[dictBeansList.size()];
                                for (int i = 0; i < dictBeansList.size(); i++) {
                                    data[i] = dictBeansList.get(i).getValue();
                                    b[i] = false;
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
                                        String item = "";
                                        //取出被勾选中的内容
                                        ArrayList selectList = new ArrayList<>();
                                        for (int i = 0; i < dictBeansList.size(); i++) {
                                            if (b[i]) {             //如果被勾线则保存数据
                                                item += data[i] + ",";
                                                selectList.add(dictBeansList.get(i));
                                            }
                                        }
                                        if (!Func.IsStringEmpty(item)) {
                                            String sValue = item.substring(0, item.length() - 1);
                                            tv_type.setText(sValue);
                                            typeDictName =sValue;
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
                        }
                    });
                }else {
                    tv_type.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerUpdateColInfoActivity.this);
                            buidler.setTitle(contentBean.getLeftContent()/*+"选择"*/);
                            final String[] data;
                            final List<DictBean> dictBeansList= DictUtils.getDictList(contentBean.getDictField());
                            if(dictBeansList!=null &&dictBeansList.size()>0){
                                data=new String[dictBeansList.size()];
                                for(int i=0;i<dictBeansList.size();i++){
                                    data[i]=dictBeansList.get(i).getValue();
                                }
                                buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        tv_type.setText(data[which].trim());
                                        typeDictName =dictBeansList.get(which).getValue();
                                        dialog.dismiss();
                                    }
                                });
                                buidler.show();
                            }

                        }
                    });
                }

            }else{
                if(contentBean.getField().equals("registeredCapital")||contentBean.getField().equals("storeNumber")||contentBean.getField().equals("exclusiveShopNumber")){
                    et_col.setInputType(InputType.TYPE_CLASS_NUMBER);
                }else if(contentBean.getField().equals("afterSalesNumber")||contentBean.getField().equals("faxNumber")||contentBean.getField().equals("companyPhone")){
                    et_col.setInputType(InputType.TYPE_CLASS_PHONE);
                }
                et_col.setVisibility(View.VISIBLE);
                tv_type.setVisibility(View.GONE);
                if(!Func.IsStringEmpty(contentBean.getRightContent())){
                    et_col.setText(contentBean.getRightContent());
                }else{
                    et_col.setHint("请填写"+contentBean.getLeftContent()+"信息");
                }
            }
        }
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Rc.mBackfresh=true;
                    if(operatorType.equals(ViewOperatorType.EDIT)){
                        UpdateMemberInfo();
                    }else if(operatorType.equals(ViewOperatorType.ADD)){
                        if(contentBean.getDictField()!=null){
                            s_returnvalue=typeDictName;
                        }else if(contentBean.getField().endsWith("Date")||contentBean.getField().equals("brandNames")
                                ||contentBean.getField().equals("operatorName")){
                            s_returnvalue=tv_type.getText().toString().trim();
                        }else{
                            s_returnvalue=et_col.getText().toString().trim();
                        }
                        setBackActivityBundle();
                        backPage();
                    }


                }
            });

        }
        mtvcancel = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_cancel"));
        if (mtvcancel != null) {
            mtvcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
                }
            });

        }
        mtvdate = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_date"));
        if (mtvdate != null) {
            mtvdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiCustomerUpdateColInfoActivity.this);
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
                            mtvdate.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        }
                    });
                    dialog.show();
                }
            });

        }
    }

    @Override
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE,s_returnvalue);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiCustomerUpdateColInfoActivity.this.setResult(1, intent);
    }
    public void UpdateMemberInfo(){
        if(contentBean!=null){
            updateBean.setField(contentBean.getField());
            updateBean.setId(customerid);
            if(contentBean.getDictField()!=null){
                updateBean.setValue(typeDictName);
            }else if(contentBean.getField().toLowerCase().endsWith("date")||contentBean.getField().equals("brandNames")||contentBean.getField().toLowerCase().equals("birthday")

                    ){
                updateBean.setValue(tv_type.getText().toString().trim());
            }else if(contentBean.getField().toLowerCase().equals("region")){
                updateBean.setValue(sRegion);
            }else if(contentBean.getField().equals("firstDistributor")||contentBean.getField().equals("operatorName")){
                if(memberBeanID!=null){
                    updateBean.setValue(memberBeanID.getId()+"");
                }
            }else{
                updateBean.setValue(et_col.getText().toString().trim());
            }

        }
        if(!Func.IsStringEmpty(msControlFlag) && msControlFlag.equals("1")){
            JiuyiHttp.RETROFIT().addHeader("Authorization", Rc.id_tokenparam)
                    .create(CustomerService.class)
                    .updateActual(updateBean)
                    .enqueue(new Callback<MemberUpdateBean>() {
                        @Override
                        public void onResponse(Call<MemberUpdateBean> call, Response<MemberUpdateBean> response) {
                            startDialog(DialogID.DialogTrue, "", "更新成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }

                        @Override
                        public void onFailure(Call<MemberUpdateBean> call, Throwable t) {
                            startDialog(DialogID.DialogDoNothing, "", "更新失败！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }
                    });
        }else{
            JiuyiHttp.RETROFIT().addHeader("Authorization", Rc.id_tokenparam)
                    .create(CustomerService.class)
                    .updateMember(updateBean)
                    .enqueue(new Callback<MemberUpdateBean>() {
                        @Override
                        public void onResponse(Call<MemberUpdateBean> call, Response<MemberUpdateBean> response) {
                            startDialog(DialogID.DialogTrue, "", "更新成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }

                        @Override
                        public void onFailure(Call<MemberUpdateBean> call, Throwable t) {
                            startDialog(DialogID.DialogDoNothing, "", "更新失败！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }
                    });
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
        if(operatorType!=null){
            if(operatorType.equals(ViewOperatorType.EDIT) && contentBean!=null){
                mTitle = "修改"+contentBean.getLeftContent()+"信息";
            }else {
                mTitle = "新增"+contentBean.getLeftContent()+"信息";
            }
        }else {
            mTitle = "新增客户信息";
        }
        setTitle(mTitle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle;
        if(data == null || data.getExtras() == null) {
            return;
        }
        switch (requestCode) {
            case 1:
                bundle = data.getExtras();
                long Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                String CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                if(Customerid>0 && !Func.IsStringEmpty(CustomerName) ){
                    if(Customerid==customerid){
                        startDialog(DialogID.DialogDoNothing, "", "一级经销商不能选自己！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        return;
                    }
                    tv_type.setText(CustomerName);
                    memberBeanID=new MemberBeanID();
                    memberBeanID.setId(Customerid);
//                    updateBean.setValue(memberBeanID);
                }
                break;
            case 200:
                bundle = data.getExtras();
                s_returnvalue=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long valueId=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(valueId>0 && s_returnvalue!=null ){
                    tv_type.setText(s_returnvalue);
                    NormalOperatorBean.OperatorBeanID operatorBeanID=new NormalOperatorBean.OperatorBeanID();
                    operatorBeanID.setId(valueId);
                    updateBean.setValue(operatorBeanID);
                }
                break;
            case 201:
                bundle = data.getExtras();
                s_returnvalue=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                String code = bundle.getString(JiuyiBundleKey.PARAM_COMMONCODE);
                if(code!=null && s_returnvalue!=null ){
                    tv_type.setText(s_returnvalue);
                    updateBean.setValue(s_returnvalue);
                }
                break;
            case 205:
                bundle = data.getExtras();
                s_returnvalue=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long id=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(id>0 && s_returnvalue!=null ){
                    tv_type.setText(s_returnvalue);
                    memberBeanID=new MemberBeanID();
                    memberBeanID.setId(id);
                    updateBean.setValue(id+"");
                }else{
                    tv_type.setText("");
                }
                break;
            case 206:
                bundle = data.getExtras();
                mProductTypeSelectList=bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
                List<CommonBeanID> bigCategoryList=new ArrayList<>();
                String name="";
                if(mProductTypeSelectList!=null && mProductTypeSelectList.size()>0){
                    for(int i=0;i<mProductTypeSelectList.size();i++){
                        name+=mProductTypeSelectList.get(i).getBrandName()+",";
//                        CommonBeanID commonBeanID=new CommonBeanID();
//                        commonBeanID.setId(mProductTypeSelectList.get(i).getId());
//                        bigCategoryList.add(commonBeanID);
                    }
                    tv_type.setText(name.substring(0,name.length()-1));
                    updateBean.setValue(name.substring(0,name.length()-1));
                }

                break;



        }
    }
}
