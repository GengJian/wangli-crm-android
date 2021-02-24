package customer.activity;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.common.GsonUtil;
import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
//import com.hengyi.wheelpicker.listener.OnCityWheelComfirmListener;
//import com.hengyi.wheelpicker.ppw.CityWheelPickerPopupWindow;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import customer.Utils.ViewOperatorType;
import customer.entity.ReceiveAddressBean;
import customer.entity.ReceiveAddressParcelBean;
import customer.entity.UpdateAddressBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewReceiptAddressActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 收货地址新增页面
 *****************************************************************
 */

public class JiuyiCustomerNewReceiptAddressActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,tv_region;
    private JiuyiButton mbtnsave;
    private ToggleButton tb;
    private JiuyiEditText et_address_personname,et_tel,et_moreaddress;
    //申明对象
    CityPickerView mCityPickerView=new CityPickerView();
    private String msprovince="";
    private String mscity="";
    private String msdistrict="";
    private Boolean bdefautl=false;
    private ReceiveAddressBean.ContentBean  contentBean;
    ReceiveAddressParcelBean addressbean;
    private long Customerid=-1;
    private String ViewType="";

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewreceiptaddress_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);


        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        ViewType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        addressbean = mBundle.getParcelable(JiuyiBundleKey.PARAM_ADDRESSBEAN);
        if(ViewType.equals(ViewOperatorType.ADD)){
            addressbean=null;
        }
        setTitle();
        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        mCityPickerView.init(this);
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setConfirmTextColorStr("#0089d1");
        mCityPickerView.setConfig(cityConfig);

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        et_address_personname= (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_address_personname"));
        et_tel= (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_tel"));
        et_moreaddress= (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_moreaddress"));
        tv_region = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_region"));
        tb = (ToggleButton) mBodyLayout.findViewById(Res.getViewID(null, "toggleButton"));
        if(tb!=null){
            tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        bdefautl=true;
                    }else{
                        bdefautl=false;
                    }
                }
            });
        }

        if(addressbean!=null){
            et_address_personname.setText(addressbean.getReceiver());
            et_tel.setText(addressbean.getPhoneOne());
            et_moreaddress.setText(addressbean.getAddress());
            String sRegion="";
            sRegion=addressbean.getProvinceName()+addressbean.getCityName();
            if(addressbean.getAreaName()!=null){
                sRegion+= addressbean.getAreaName();
            }
            tv_region.setText(sRegion);
            msprovince=addressbean.getProvinceName();
            mscity=addressbean.getCityName();
            msdistrict=addressbean.getAreaName();
            CityConfig cityConfig2 = new CityConfig.Builder().build();
            cityConfig2.setDefaultProvinceName(addressbean.getProvinceName());
            cityConfig2.setDefaultCityName(addressbean.getCityName());
            cityConfig2.setDefaultDistrict(addressbean.getAreaName());
            mCityPickerView.setConfig(cityConfig2);
            if(addressbean.isDefaults()){
                tb.setChecked(true);
            }
        }

        if (tv_region != null) {
            tv_region.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideKeyboard();
                    mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
                        @Override
                        public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                            StringBuilder sb = new StringBuilder();
//                            sb.append("选择的结果：\n");
                            if (province != null) {
                                msprovince=province.getName();
                                sb.append(province.getName() /*+ " " + province.getId()*/ );
                            }

                            if (city != null) {
                                mscity=city.getName();
                                sb.append(city.getName()/* + " " + city.getId()*/ );
                            }

                            if (district != null) {
                                msdistrict=district.getName();
                                sb.append(district.getName() /*+ " " + district.getId()*/ );
                            }else{
                                msdistrict="";
                            }

                            tv_region.setText("" + sb.toString());

                        }

                        @Override
                        public void onCancel() {
                        }
                    });
                    mCityPickerView.showCityPicker();
                }
            });
        }
        mbtnsave= (JiuyiButton) mBodyLayout.findViewById(Res.getViewID(null, "btn_save"));
        if(mbtnsave!=null){
            mbtnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if(ViewType.equals(ViewOperatorType.EDIT)){
                        updataAddress();
                    }else {
                        submit();
                    }
                }
            });
        }



    }

private void hideKeyboard() {
    InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
    if (imm.isActive() && this.getCurrentFocus() != null) {
        if (this.getCurrentFocus().getWindowToken() != null) {
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}

    public void updataAddress(){
        UpdateAddressBean updateAddressBean =new UpdateAddressBean();
        updateAddressBean.setReceiver(et_address_personname.getText().toString().trim());
        updateAddressBean.setPhoneOne(et_tel.getText().toString().trim());
        if(!Func.IsStringEmpty(msprovince)){
            updateAddressBean.setProvinceName(msprovince);
        }
       if(!Func.IsStringEmpty(mscity)){
           updateAddressBean.setCityName(mscity);
       }
        updateAddressBean.setAreaName(msdistrict);
        updateAddressBean.setAddress(et_moreaddress.getText().toString().trim());
        updateAddressBean.setDefaults(bdefautl);
        updateAddressBean.setId(addressbean.getId()+"");
        UpdateAddressBean.MemberBean memberBean=new UpdateAddressBean.MemberBean();
        memberBean.setId(Customerid);
        updateAddressBean.setMember(memberBean);
        Map<String, String> headMap= new HashMap<String, String>();
        headMap.put("content-type","application/json;charset=UTF-8");
        headMap.put("Authorization",Rc.getIns().id_tokenparam);
        JiuyiHttp.PUT("address/update")
                .addHeaders(headMap)
                .setJson(com.common.GsonUtil.gson().toJson(updateAddressBean))
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

    public void submit(){
        boolean mbcheck=false;
        mbcheck=check();
        if(!mbcheck){
            return;
        }
        Map<String, String> addressMap= new HashMap<String, String>();
        addressMap.put("receiver",et_address_personname.getText().toString().trim());
        addressMap.put("phoneOne",et_tel.getText().toString().trim());
        addressMap.put("provinceName",msprovince);
        addressMap.put("cityName",mscity);
        addressMap.put("areaName",msdistrict);
        addressMap.put("address",et_moreaddress.getText().toString().trim());
        String sDefault="false";
        if(bdefautl){
             sDefault="true";
        }else{
            sDefault="false";
        }
        addressMap.put("defaults",sDefault);
        String memberStr="";
        String addressJson="";
        if(Customerid>0){
            memberStr="{\"id\":\""+Customerid+"\"}";
            addressMap.put("member",memberStr);
            addressMap.put("fromClientType","android");
            addressJson=GsonUtil.gson().toJson(addressMap).replace("\\","");
            addressJson=addressJson.replace("\"{\"","{\"");
            addressJson=addressJson.replace("\"}\"","\"}");
        }else{
          return;
        }
        JiuyiHttp.POST("address/create")
                .setJson(addressJson)
                .addHeader("Authorization", Rc.id_tokenparam)

                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result) {
                        startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }
    public boolean check(){
        if(TextUtils.isEmpty(tv_region.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择地区！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_tel.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入联系电话！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(et_address_personname.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入收货人姓名！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;
    }

    public void setTitle(){
        if(ViewType.equals(ViewOperatorType.EDIT)){
            mTitle = "编辑收货地址信息";
        }else {
            mTitle = "新建收货地址";
        }

        setTitle(mTitle);
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction== DialogID.DialogTrue)
        {
            Rc.mBackfresh=true;
            EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
            backPage();
        }
    }
}
