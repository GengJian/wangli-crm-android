package customer.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.GlideApp;
import com.jiuyi.app.JiuyiActivityBase;
import com.qiniu.android.storage.UploadManager;
import com.tencent.qcloud.sdk.Constant;


import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TImage;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.model.TakePhotoOptions;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import customer.entity.AttachmentBean;
import customer.view.SelectPicPopupWindow;
import mine.bean.UpdateAvatarBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerPictureActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 图片预览页面
 *****************************************************************
 */
public class JiuyiCustomerPictureActivity extends JiuyiActivityBase implements TakePhoto.TakeResultListener, InvokeListener {
    private ImageView mRightView;
    private SelectPicPopupWindow menuWindow;
    private TakePhoto takePhoto;
    private static final String TAG = JiuyiCustomerPictureActivity.class.getName();
    private ImageView item_image;
    private InvokeParam invokeParam;
    private long billID=-1;
    private String iconUrl="";
    private String billType="";
    protected UploadManager uploadManager;
    private  Uri uri;
    private  File file;


    @Override
    public void onInit() {
        billID=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
        iconUrl=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERICONURL);
        billType=mBundle.getString(JiuyiBundleKey.PARAM_BILLTYPE);
        uploadManager = new UploadManager();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customerpicture_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        mRightView = (ImageView)mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_more"));
        if(mRightView!=null){
            mRightView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectImgs();
                }
            });
        }
        item_image=(ImageView) mBodyLayout.findViewById(Res.getViewID(null, "item_image"));
        GlideApp.with(JiuyiCustomerPictureActivity.this).load(Constant.BaseUrl+"file/"+iconUrl).placeholder(R.drawable.ic_default_image).into(item_image);
        setTitle();
    }


    private void selectImgs() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(JiuyiCustomerPictureActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuWindow = new SelectPicPopupWindow(JiuyiCustomerPictureActivity.this, itemsOnClick);
        //设置弹窗位置
        menuWindow.showAtLocation(JiuyiCustomerPictureActivity.this.findViewById(R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
            }
            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            configCompress(takePhoto);
            configTakePhotoOption(takePhoto);
//            final Uri imageUri = null;
//            if (Build.VERSION.SDK_INT < 24) {
//                // 从文件中创建uri
//                imageUri = Uri.fromFile(file);
//            } else {
//                //兼容android7.0 使用共享文件的形式
//                imageUri = FileProvider.getUriForFile(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "com.wanglicrm.android.fileProvider", file );
//            }
            switch (v.getId()) {
                case R.id.item_popupwindows_camera:        //点击拍照按钮
                    String[] list = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                    //检测权限
                    new JiuyiHiPermissionUtil(JiuyiCustomerPictureActivity.this).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                             Uri imageUri = null;
                            imageUri = Uri.fromFile(file);
//                            if (Build.VERSION.SDK_INT < 24) {
//                                // 从文件中创建uri
//                                imageUri = Uri.fromFile(file);
//                            } else {
//                                //兼容android7.0 使用共享文件的形式
//                                imageUri = FileProvider.getUriForFile(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "com.wanglicrm.android.fileProvider", file );
//                            }
                            takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                        }
                    });

                    break;
                case R.id.item_popupwindows_Photo:       //点击从相册中选择按钮

                    String[] list2 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil(JiuyiCustomerPictureActivity.this).checkPermission(list2, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Uri imageUri = null;
                            imageUri = Uri.fromFile(file);
                            takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                        }
                    });
                    break;

                default:
                    break;
            }
        }

    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setTitle(){
        mTitle="更换头像";
        super.setTitle(mTitle);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {
        showImg(result.getImages());
        Log.i(TAG, "takeSuccess：" + result.getImage().getCompressPath());
    }
    private void showImg(ArrayList<TImage> images) {
        uri=Uri.fromFile(new File(images.get(0).getCompressPath()));
        item_image.setImageURI(uri);
        file = new File(images.get(0).getCompressPath());
        upload();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Log.i(TAG, "takeFail:" + msg);
    }

    @Override
    public void takeCancel() {
        Log.i(TAG, getResources().getString(org.devio.takephoto.R.string.msg_operation_canceled));
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());

    }

    private void configCompress(TakePhoto takePhoto) {
        int maxSize = 10240;
        int width = 800;
        int height = 800;
        boolean showProgressBar = false;
        boolean enableRawFile = true;
        CompressConfig config;
        config = new CompressConfig.Builder().setMaxSize(maxSize)
                .setMaxPixel(width >= height ? width : height)
                .enableReserveRaw(enableRawFile)
                .create();
        takePhoto.onEnableCompress(config, showProgressBar);
    }

    private CropOptions getCropOptions() {
        int height = 800;
        int width = 800;
        boolean withWonCrop =false;
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(width).setOutputY(height);
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }


    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
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
                        .addFile("file",file)
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<List<AttachmentBean>>() {
                            @Override
                            public void onSuccess(List<AttachmentBean> data) {
                                if(data!=null && data.size()>0 ){
                                    if(billType.equals("operator")){
                                        updataPicture(data.get(0).getQiniuKey());
                                    }else if(billType.equals("customer")){
                                        updataCustomerPicture(data.get(0).getQiniuKey());
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


    public void updataPicture(String key){
        UpdateAvatarBean updateAvatarBean =new UpdateAvatarBean();
        updateAvatarBean.setId(Rc.id);
        updateAvatarBean.setKey(key);
        JiuyiHttp.PUT("operator/update-avatar-url")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(updateAvatarBean))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        if(data!=null){
                            Rc.mBackfresh=true;
                            Toast.makeText(JiuyiCustomerPictureActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                            setBackActivityBundle();
                            backPage();
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





    public void updataCustomerPicture(String key){
        UpdateAvatarBean updateAvatarBean =new UpdateAvatarBean();
        updateAvatarBean.setId(billID);
        updateAvatarBean.setKey(key);
        JiuyiHttp.PUT("member/update-avatar-url")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(updateAvatarBean))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        if(data!=null){
                            Rc.mBackfresh=true;
                            Toast.makeText(JiuyiCustomerPictureActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                            setBackActivityBundle();
                            backPage();
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

/*
    public void updataCustomerPicture(String key){
        UpdateAvatarBean updateAvatarBean =new UpdateAvatarBean();
        updateAvatarBean.setId(billID);
        updateAvatarBean.setKey(key);
        JiuyiHttp.RETROFIT().addHeader("Authorization", Rc.id_tokenparam)
                .create(CustomerService.class)
                .updateCustomerPicture(updateAvatarBean)
                .enqueue(new Callback<UpdateAvatarBean>() {
                    @Override
                    public void onResponse(Call<UpdateAvatarBean> call, Response<UpdateAvatarBean> response) {
                        Log.d("success", "response:" );
                        startDialog(DialogID.DialogTrue, "", "更新成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    }

                    @Override
                    public void onFailure(Call<UpdateAvatarBean> call, Throwable t) {
                        startDialog(DialogID.DialogDoNothing, "", "更新失败！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        Log.d("success", "response:" );
                    }
                });
    }
*/


}
