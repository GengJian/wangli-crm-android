package mine.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.common.GsonUtil;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiHardNo;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.activity.common.activity.JiuyiHeadPageActivity;
import com.jiuyi.model.DictBean;
import com.jiuyi.model.DictResultBean;
import com.jiuyi.tools.DictUtils;
import com.tencent.qcloud.sdk.Constant;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiMainApplication;
import com.nanchen.compresshelper.CompressHelper;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import customer.Utils.BitmapUtils;
import customer.Utils.FileUtils;
import customer.Utils.ViewOperatorType;
import customer.activity.ImageBrowseActivity;
import customer.entity.AttachmentBean;
import customer.entity.ImageBean;
import customer.entity.ImageBrowseBean;
import customer.entity.UploadTokenBean;
import customer.entity.VisitActivityListBean;
import customer.listener.ItemClickListener;
import customer.listener.PickerConfig;
import customer.view.Bimp;
import com.control.widget.NoScrollGridView;
import mine.adapter.SignPictureAdapter;
import mine.bean.MineSignListBean;
import mine.bean.SignBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineSignActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的签到
 *****************************************************************
 */
public class JiuyiMineSignActivity extends JiuyiActivityBase implements AMap.OnCameraChangeListener, AMap.OnMapLoadedListener, AMapLocationListener, PoiSearch.OnPoiSearchListener, View.OnClickListener,GeocodeSearch.OnGeocodeSearchListener{
    private TextView mtvcomplete;
    private AMap aMap;
    private MapView mapView;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private Marker locationMarker, checkinMarker;
    private LatLonPoint searchLatlonPoint;
    private List<PoiItem> resultData;
    private WifiManager mWifiManager;
    private PoiSearch poisearch;
    private Circle mcircle;
    private LatLng checkinpoint,mlocation;
    private TextView tvLocation;
    private JiuyiItemGroup etRemark,tv_signtype;
    private TextView tvSubmit;
    private boolean isItemClickAction, isLocationAction;
    /*公共静态Bitmap*/
    public static Bitmap bimap;
    protected UploadManager uploadManager;
    private List<AttachmentBean> attachmentsBeanslist;
    GeocodeSearch geocoderSearch;
    private boolean havePermission = false;

    /*不滚动的GridView*/
    private NoScrollGridView noScrollGridView;
    private String filePath,sTitle;
    /*图片适配器*/
    private SignPictureAdapter adapter;
    private static final int TAKE_PICTURE = 1000;
    private MineSignListBean.ContentBean signBean;
    private  File[] files;
    private VisitActivityListBean visitActivityListBean;
    String formatAddress="",sProvince="";
//    RegeocodeResult regeocodeResult;
    private String operatorType;
    private MineSignListBean.ContentBean contentBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bimp.tempSelectBitmap.clear();
        super.onCreate(savedInstanceState);
        operatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        if(operatorType==null){
            operatorType="";
        }
        contentBean=mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_minesign_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        setContentView(mBodyLayout);

        mapView = (MapView) mBodyLayout.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
         aMap = mapView.getMap();//初始化地图控制器对象
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setOnCameraChangeListener(this);
        aMap.setOnMapLoadedListener(this);
        mWifiManager = (WifiManager) JiuyiMainApplication.getIns().getApplicationContext().getSystemService(JiuyiMineSignActivity.this.WIFI_SERVICE);
        resultData = new ArrayList<>();
        onInit();
        if(operatorType.equals( ViewOperatorType.EDIT ) && contentBean!=null){
            setData(contentBean);
//            getDetailInfo(contentBean.getId());
        }
        //初始化定位
        initLocation();
        //开始定位
        startLocation();

    }
    @Override
    public void onInit() {
        havePermission = checkRecordPermission();
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }

        setTitle();
        uploadManager = new UploadManager();
        signBean=new MineSignListBean.ContentBean();
        if(mBodyLayout==null){
            mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_minesign_layout"), null);
            mBodyLayout.findTitleToolBars(this, this);//必不可少
            setContentView(mBodyLayout);
        }
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_send"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
                }
            });

        }
        tv_signtype = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.tv_signtype);
        tv_signtype.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiMineSignActivity.this);
                buidler.setTitle("打卡类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("sign_record_type");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tv_signtype.setText(data[which].trim());
//                            sEquipmenttype =dictBeansList.get(which).getOriginalid();
                            DictResultBean.ContentBean contentBean =new DictResultBean.ContentBean();
                            contentBean.setId(dictBeansList.get(which).getOriginalid());
                            contentBean.setKey(dictBeansList.get(which).getKey());
                            contentBean.setValue(dictBeansList.get(which).getValue());
                            signBean.setSignType(contentBean);
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }else{
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "没有可选打卡类型!", Toast.LENGTH_SHORT).show();
                }
            }

        });
/*
        tv_signtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiMineSignActivity.this);
                buidler.setTitle("打卡类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("sign_record_type");
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tv_signtype.setText(data[which].trim());
//                            sEquipmenttype =dictBeansList.get(which).getOriginalid();
                            DictResultBean.ContentBean contentBean =new DictResultBean.ContentBean();
                            contentBean.setId(dictBeansList.get(which).getOriginalid());
                            contentBean.setKey(dictBeansList.get(which).getKey());
                            contentBean.setValue(dictBeansList.get(which).getValue());
                            signBean.setSignType(contentBean);
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }else{
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "没有可选打卡类型!", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

        tvLocation = (TextView) mBodyLayout.findViewById(R.id.tv_location);
        etRemark = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.et_remark);
        tvSubmit = (TextView) mBodyLayout.findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean mbcheck=false;
                mbcheck=check();
                if(!mbcheck){
                    return;
                }
                if(operatorType.equals( ViewOperatorType.EDIT )){
                    if(Func.IsStringEmpty( tvLocation.getText().toString().trim() )){
                        Toast.makeText(JiuyiMineSignActivity.this, "地理位置获取不到,请退出重试!", Toast.LENGTH_SHORT).show();
                        return ;
                    }else{
                        if(!Func.IsStringEmpty( formatAddress )){
                            signBean.setAddress(formatAddress);
                        }
                        if(!Func.IsStringEmpty(sProvince)){
                            signBean.setProvinceName(sProvince);
                        }


                    }
                    updateAddressInfo();
                }else {
                    showDialog();
                    if(Bimp.tempSelectBitmap.size()>0){
                        files=new File[Bimp.tempSelectBitmap.size()];
                        for(int i=0;i<Bimp.tempSelectBitmap.size();i++){
                            if(Bimp.tempSelectBitmap.get(i).getPath()!=null){
                                File file1 = new File(Bimp.tempSelectBitmap.get(i).getPath());
                                files[i]=file1;
                            }
                        }
                        upload();
                    }else{
                        createSign();
                    }
                }

            }
        });
        noScrollGridView = (NoScrollGridView) findViewById(R.id.noScrollgridview);
        noScrollGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        adapter = new SignPictureAdapter(this, new ItemClickListener() {
            @Override
            public void itemClickListener(View v, int position) {
                Bimp.tempSelectBitmap.remove(position);
                adapter.notifyDataSetChanged();

            }
        });

        noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == Bimp.getTempSelectBitmap().size()) {
                    String[] list = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                    //检测权限
                    new JiuyiHiPermissionUtil(JiuyiMineSignActivity.this).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
                            goCamera();
                        }
                    });
                } else {
                    ArrayList<ImageBrowseBean> imageList = new ArrayList<>();
                    if(Bimp.getTempSelectBitmap()!=null && Bimp.getTempSelectBitmap().size()>0){
                        for(int j=0;j<Bimp.getTempSelectBitmap().size();j++){
                            if(!Func.IsStringEmpty(Bimp.getTempSelectBitmap().get(j).getImgUrl())){
                                ImageBrowseBean imageBrowseBean =new ImageBrowseBean();
                                imageBrowseBean.setType("Net");
                                imageBrowseBean.setUrl(Bimp.getTempSelectBitmap().get(j).getImgUrl());
                                imageList.add(imageBrowseBean);
                            }else if(Bimp.getTempSelectBitmap().get(j).getPath()!=null){
                                ImageBrowseBean imageBrowseBean =new ImageBrowseBean();
                                imageBrowseBean.setType("Local");
                                imageBrowseBean.setFilePath(Bimp.getTempSelectBitmap().get(j).getPath());
                                imageList.add(imageBrowseBean);
                            }

                        }
                        Intent intent = new Intent(JiuyiMineSignActivity.this,ImageBrowseActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("param_flag_enum",ImageBrowseActivity.FLAG_ENUM[4]);
                        bundle.putInt("param_position",i);
                        bundle.putParcelableArrayList("image_url_group",imageList);
                        intent.putExtras(bundle);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        JiuyiMineSignActivity.this.startActivity(intent);
                    }
                }
            }
        });
        noScrollGridView.setAdapter(adapter);


    }
    public boolean check(){
//        if(Func.IsStringEmpty(tvLocation.getText().toString().trim())){
//            Toast.makeText(JiuyiMineSignActivity.this, "地理位置获取不到，请半个小时内补签地址!", Toast.LENGTH_SHORT).show();
//        }

        if(Func.IsStringEmpty(tv_signtype.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择打卡类型！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Bimp.tempSelectBitmap.size()<=0){
            startDialog(DialogID.DialogDoNothing, "", "未上传照片！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }

        return true;
    }
    private void createSign(){
        if(!Func.IsStringEmpty(etRemark.getText().toString().trim())){
            signBean.setRemark(etRemark.getText().toString().trim());
        }
        signBean.setHardwareInfo(JiuyiHardNo.getIns().getDefautHardNo(Rc.mobilecode)+"v"+Rc.mSysVersion);
        signBean.setFromClientType("android");
        JiuyiHttp.POST("sign-in/create")
                .setJson(GsonUtil.gson().toJson(signBean))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        if(visitActivityListBean!=null){
                            updateInfo();
                        }
                        Rc.mBackfresh=true;
                        String sMessage="";
                        if(Func.IsStringEmpty(tvLocation.getText().toString().trim())){
                            sMessage="获取不到打卡位置，请在半个小时内补签地址!";
                        }else{
                            sMessage="提交成功！";
                        }


                        startDialog(DialogID.DialogTrue, "", sMessage, JiuyiDialogBase.Dialog_Type_Yes, null);
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
    public void setData(MineSignListBean.ContentBean signBeanDetail){
        signBean=signBeanDetail;
        if(signBeanDetail.getSignType()!=null &&signBeanDetail.getSignType().getValue()!=null ){
            tv_signtype.setText(signBeanDetail.getSignType().getValue());
            tv_signtype.setItemOnClickListener(null);
        }
        if(signBeanDetail.getRemark()!=null){
            etRemark.setText(signBeanDetail.getRemark()  );
            etRemark.contentEdt.setEnabled( false );
        }
        if(signBeanDetail.getAttachments()!=null && signBeanDetail.getAttachments().size()>0) {
            Bimp.tempSelectBitmap.clear();
            for (int i = 0; i < signBeanDetail.getAttachments().size(); i++) {
                AttachmentBean attachmentBean = signBeanDetail.getAttachments().get( i );
                ImageBean returnPhoto = new ImageBean();
                if (!Func.IsStringEmpty( attachmentBean.getQiniuKey() )) {
                    returnPhoto.setQiniuKey( attachmentBean.getQiniuKey() );
                    returnPhoto.setImgUrl( Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                    returnPhoto.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    returnPhoto.setShowIcon( true );
                }
                Bimp.tempSelectBitmap.add( returnPhoto );
            }
            adapter.notifyDataSetChanged();
        }
        noScrollGridView.setEnabled( false );

    }

/*
    private void getDetailInfo(long id) {
        JiuyiHttp.GET("sign-in/detail/"+id)
                .tag("request_get_")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<SignBean>() {
                    @Override
                    public void onSuccess(SignBean signBeanDetail) {
                        if(signBeanDetail!=null){
                            signBean=signBeanDetail;
                            if(signBeanDetail.getSignType()!=null &&signBeanDetail.getSignType().getValue()!=null ){
                                tv_signtype.setText(signBeanDetail.getSignType().getValue());
                                tv_signtype.setOnClickListener( null );
                            }
                            if(signBeanDetail.getRemark()!=null){
                                etRemark.setText(signBeanDetail.getRemark()  );
                                etRemark.setEnabled( false );
                            }
                            if(signBeanDetail.getAttachments()!=null && signBeanDetail.getAttachments().size()>0) {
                                Bimp.tempSelectBitmap.clear();
                                for (int i = 0; i < signBeanDetail.getAttachments().size(); i++) {
                                    AttachmentBean attachmentBean = signBeanDetail.getAttachments().get( i );
                                    ImageBean returnPhoto = new ImageBean();
                                    if (!Func.IsStringEmpty( attachmentBean.getThumbnail() )) {
                                        returnPhoto.setThumbnailPath( attachmentBean.getThumbnail() );
                                    }
                                    if (!Func.IsStringEmpty( attachmentBean.getQiniuKey() )) {
                                        returnPhoto.setQiniuKey( attachmentBean.getQiniuKey() );
                                    }
                                    if (!Func.IsStringEmpty( attachmentBean.getUrl() )) {
                                        returnPhoto.setImgUrl( attachmentBean.getUrl() );
                                    }
                                    Bimp.tempSelectBitmap.add( returnPhoto );
                                }
                                adapter.notifyDataSetChanged();
                            }
                            noScrollGridView.setEnabled( false );

                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        tv_signtype.setOnClickListener( null );
                        etRemark.setEnabled( false );
                        noScrollGridView.setEnabled( false );
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }
*/

    public void updateAddressInfo(){
        if(signBean==null){
            return ;
        }
        JiuyiHttp.PUT("sign-in/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(signBean))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            Rc.mBackfresh=true;
                            startDialog(DialogID.DialogTrue, "", "更新成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }
                    }
                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }


    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }
    private void goCamera() {
        filePath = FileUtils.SDPATH + String.valueOf(System.currentTimeMillis())+"photo.jpg";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            File vDirPath = file1.getParentFile();
            vDirPath.mkdirs();
        }
        Uri uri = null;


        /*
        * 1.现象
            在项目中调用相机拍照和录像的时候，android4.x,Android5.x,Android6.x均没有问题,在Android7.x下面直接闪退
           2.原因分析
            Android升级到7.0后对权限又做了一个更新即不允许出现以file://的形式调用隐式APP，需要用共享文件的形式：content:// URI
           3.解决方案
            下面是打开系统相机的方法，做了android各个版本的兼容:
        * */

        if (Build.VERSION.SDK_INT < 24) {
            // 从文件中创建uri
            uri = Uri.fromFile(file1);
        } else {
            //兼容android7.0 使用共享文件的形式
//            ContentValues contentValues = new ContentValues(1);
//            contentValues.put(MediaStore.Images.Media.DATA, file1.getAbsolutePath());
//            uri = JiuyiMineSignActivity.this.getApplication().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);


            uri = FileProvider.getUriForFile(JiuyiMineSignActivity.this, "com.wanglicrm.android.fileProvider", file1 );
        }

        // 启动Camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) { //
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  // 检测sd是否可用
                        Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    //优化压缩图片
                    Bitmap bm = BitmapUtils.getCompressedBitmap(JiuyiMineSignActivity.this, filePath);
                    ImageBean takePhoto = new ImageBean();
                    takePhoto.setBitmap(bm);
                    Uri uri = null;
                    File file1 = new File(filePath);
//                    if (Build.VERSION.SDK_INT < 24) {
//                        // 从文件中创建uri
//                        uri = Uri.fromFile(file1);
//                    } else {
//                        //兼容android7.0 使用共享文件的形式
//                        uri = FileProvider.getUriForFile(JiuyiMineSignActivity.this, "com.wanglicrm.android.fileProvider", file1 );
//                    }
//                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(uri);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                    takePhoto.setPath(newFile.getPath());
                    Bimp.tempSelectBitmap.add(takePhoto);

                }
                break;
        }
    }


    /**
     * 初始化定位，设置回调监听
     */
    private void initLocation() {
        //初始化client
        mlocationClient = new AMapLocationClient(this.getApplicationContext());
        // 设置定位监听
        mlocationClient.setLocationListener(JiuyiMineSignActivity.this);
    }
    /**
     * 开始定位
     */
    private void startLocation(){
        checkWifiSetting();
        //设置定位参数
        mlocationClient.setLocationOption(getOption());
        // 启动定位
        mlocationClient.startLocation();
    }
    /**
     * 检查wifi，并提示用户开启wifi
     */
    private void checkWifiSetting() {
        if (mWifiManager.isWifiEnabled()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("开启WIFI模块会提升定位准确性"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("去开启", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent); // 打开系统设置界面
            }
        });
        builder.setNegativeButton("不了", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }
    /**
     * 设置定位参数
     * @return 定位参数类
     */
    private AMapLocationClientOption getOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setLocationCacheEnable(false);//设置是否返回缓存中位置，默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        return mOption;
    }
    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else {
            mTitle = "考勤打卡";
        }

        setTitle(mTitle);
    }


    /**
     * 销毁定位
     *
     */
    private void destroyLocation(){
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
            mlocationClient = null;}
    }
    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        // 刷新数据
        adapter.notifyDataSetChanged();
    }
    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        destroyLocation();
    }


    @Override
    public void onClick(View v) {

    }

    /**
     * 返回定位结果的回调
     * @param aMapLocation 定位结果
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null
                && aMapLocation.getErrorCode() == 0) {
            signBean.setLatitude(aMapLocation.getLatitude()+"");
            signBean.setLongitude(aMapLocation.getLongitude()+"");
            mlocation = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            searchLatlonPoint = new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            checkinpoint = mlocation;
            isLocationAction = true;
//            searchResultAdapter.setSelectedPosition(0);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mlocation, 16f));
            if (mcircle != null) {
                mcircle.setCenter(mlocation);
            } else {
                mcircle = aMap.addCircle(new CircleOptions().center(mlocation).radius(500).strokeWidth(5));
            }
            if (searchLatlonPoint != null) {
                resultData.clear();
                resultData.add(new PoiItem("ID", searchLatlonPoint,"我的位置", searchLatlonPoint.toString()));
//                doSearchQuery(searchLatlonPoint);
                setCurrentLocationDetails(); // 设置当前位置具体描述
//                searchResultAdapter.notifyDataSetChanged();
            }
        } else {
            String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
            Log.e("AmapErr",errText);
        }
    }
    /**
     * 搜索周边poi
     * @param centerpoint
     */
    private void doSearchQuery(LatLonPoint centerpoint) {
        PoiSearch.Query query = new PoiSearch.Query("","","");
        query.setPageSize(20);
        query.setPageNum(0);
        poisearch = new PoiSearch(this,query);
        poisearch.setOnPoiSearchListener(this);
        poisearch.setBound(new PoiSearch.SearchBound(centerpoint, 500, true));
        poisearch.searchPOIAsyn();
    }
    /**
     * 搜索Poi回调
     * @param poiResult 搜索结果
     * @param resultCode 错误码
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int resultCode) {

        if (resultCode == AMapException.CODE_AMAP_SUCCESS){
            if (poiResult != null && poiResult.getPois().size() > 0){
                List<PoiItem> poiItems = poiResult.getPois();
                resultData.addAll(poiItems);

                final String[] data=new String[resultData.size() - 1];
                if(resultData!=null &&resultData.size()>1) {
//                    data = new String[resultData.size() - 1];
                    for (int i = 0; i < resultData.size() - 1; i++) {
                        String address = "";
                        if (resultData.get(i + 1).getProvinceName() != null) {
                            address += resultData.get(i + 1).getProvinceName();
                        }
                        if (resultData.get(i + 1).getCityName() != null) {
                            address += resultData.get(i + 1).getCityName();
                        }
                        if (resultData.get(i + 1).getAdName() != null) {
                            address += resultData.get(i + 1).getAdName();
                        }
                        if (resultData.get(i + 1).getSnippet() != null) {
                            address += resultData.get(i + 1).getSnippet();
                        }
                        data[i] = address;
                    }
                    tvLocation.setText(data[0]);
                    signBean.setAddress(data[0]);
                }


                tvLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiMineSignActivity.this);
                        buidler.setTitle("我的位置");
//                        final String[] data;
                        buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvLocation.setText(data[which].trim());
                                signBean.setAddress(tvLocation.getText().toString().trim());
                                dialog.dismiss();
                            }
                        });
                        buidler.show();
                    }
                });
            } else {
                Toast.makeText(JiuyiMineSignActivity.this, "无搜索结果", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(JiuyiMineSignActivity.this, "搜索失败，错误 "+resultCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    /**
     * 地图移动结束回调
     * 在这里判断移动距离有无超过500米
     * @param cameraPosition
     */
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        if (!isItemClickAction && !isLocationAction){
        }
        if (isItemClickAction)
            isItemClickAction = false;
        if (isLocationAction)
            isLocationAction = false;

        if (mcircle != null) {
            if (mcircle.contains(cameraPosition.target)){
                checkinpoint = cameraPosition.target;
            } else{
                Toast.makeText(JiuyiMineSignActivity.this, "微调距离不可超过500米", Toast.LENGTH_SHORT).show();
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mlocation ,16f ));
            }
        }else {
            startLocation();
            Toast.makeText(JiuyiMineSignActivity.this, "重新定位中。。。", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 地图加载完成回调
     */
    @Override
    public void onMapLoaded() {
        addMarkerInScreenCenter();
    }
    /**
     * 添加选点marker
     */
    private void addMarkerInScreenCenter() {
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        locationMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f,0.5f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.purple_pin)));
        //设置Marker在屏幕上,不跟随地图移动
        locationMarker.setPositionByPixels(screenPosition.x,screenPosition.y);
    }



    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

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
                                    signBean.setAttachments(attachmentsBeanslist);
                                    createSign();

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


    public void updateInfo(){
        HashMap beanMap= new HashMap<String, Object>();
        if(visitActivityListBean.getMember()!=null){
            beanMap.put("id",visitActivityListBean.getId());
            beanMap.put("signInInfo",tvLocation.getText().toString());
        }

        JiuyiHttp.PUT("visit-activity/update-mobile")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(beanMap))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        setBackActivityBundle();
                        backPage();
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        backPage();
//                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    @Override
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(JiuyiBundleKey.PARAM_COMMONNAME,tvLocation.getText().toString());

        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiMineSignActivity.this.setResult(100, intent);
    }
    private void setCurrentLocationDetails(){
// 地址逆解析
        geocoderSearch = new GeocodeSearch(getApplicationContext());
        geocoderSearch.setOnGeocodeSearchListener(JiuyiMineSignActivity.this);
        // 第一个参数表示一个Latlng(经纬度)，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(searchLatlonPoint, 25, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
    }


    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
         formatAddress = regeocodeResult.getRegeocodeAddress().getFormatAddress();
        tvLocation.setText(formatAddress);
        signBean.setAddress(formatAddress);
        if(!Func.IsStringEmpty(regeocodeResult.getRegeocodeAddress().getProvince())){
            sProvince=regeocodeResult.getRegeocodeAddress().getProvince();
            signBean.setProvinceName(regeocodeResult.getRegeocodeAddress().getProvince());
        }

        Log.e("formatAddress", "formatAddress:"+formatAddress);
        Log.e("formatAddress", "rCode:"+i);
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
    private boolean checkRecordPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(JiuyiMineSignActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(JiuyiMineSignActivity.this, permissions.toArray(new String[0]), 0);
                return false;
            }
        }
        return true;
    }
}
