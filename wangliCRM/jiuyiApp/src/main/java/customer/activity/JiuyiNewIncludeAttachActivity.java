package customer.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.TextView;

import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Func;
import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.nanchen.compresshelper.CompressHelper;
import com.qiniu.android.storage.UploadManager;

import java.io.File;
import java.util.ArrayList;

import customer.Utils.BitmapUtils;
import customer.Utils.FileUtils;
import customer.adapter.PictureAdapter;
import customer.entity.ImageBean;
import customer.entity.ImageBrowseBean;
import customer.listener.ItemClickListener;
import customer.view.Bimp;
import com.control.widget.NoScrollGridView;
import customer.view.SelectPicPopupWindow;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNewIncludeAttachActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 新增附件页面
 *****************************************************************
 */
public class JiuyiNewIncludeAttachActivity extends JiuyiActivityBase {
    protected TextView mtvcount;
    /*不滚动的GridView*/
    protected NoScrollGridView noScrollGridView;
    /*图片适配器*/
    protected PictureAdapter adapter;
    protected SelectPicPopupWindow menuWindow;
    protected String filePath;
    /*公共静态Bitmap*/
    public static Bitmap bimap;

    protected static final int TAKE_PICTURE = 1000;
    protected UploadManager uploadManager;





    @Override
    public void onInit() {
        Bimp.tempSelectBitmap.clear();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewmarketdynamic_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        uploadManager = new UploadManager();
    }

    protected void initViews() {
        mtvcount=(TextView)findViewById(R.id.tv_count);
        noScrollGridView = (NoScrollGridView) findViewById(R.id.noScrollgridview);
        noScrollGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        // 开始上传
//        btnUpload = (Button) findViewById(R.id.btn_upload);
//        btnUpload.setOnClickListener(itemsOnClick);
        adapter = new PictureAdapter(this, new ItemClickListener() {
            @Override
            public void itemClickListener(View v, int position) {
                Bimp.tempSelectBitmap.remove(position);
                adapter.notifyDataSetChanged();
                if(mtvcount!=null){
                    mtvcount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
                }
            }
        });

        noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == Bimp.getTempSelectBitmap().size()) {
                    selectImgs();
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
                        Intent intent = new Intent(JiuyiNewIncludeAttachActivity.this,ImageBrowseActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("param_flag_enum",ImageBrowseActivity.FLAG_ENUM[4]);
                        bundle.putInt("param_position",i);
                        bundle.putParcelableArrayList("image_url_group",imageList);
//                        bundle.putStringArrayList("image_url_group",imageList);
                        intent.putExtras(bundle);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        JiuyiNewIncludeAttachActivity.this.startActivity(intent);
                    }
                }


            }
        });
        noScrollGridView.setAdapter(adapter);
    }
    public void setTitle(){
        setTitle(mTitle);
    }
    @Override
    public void onResume() {
        super.onResume();
        // 刷新数据
        adapter.notifyDataSetChanged();
        if(mtvcount!=null){
            mtvcount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Bimp.tempSelectBitmap.clear();
        if(mtvcount!=null){
            mtvcount.setText(0+ "/" + 9 );
        }
    }

    protected void selectImgs() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(JiuyiNewIncludeAttachActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuWindow = new SelectPicPopupWindow(JiuyiNewIncludeAttachActivity.this, itemsOnClick);
        //设置弹窗位置
        menuWindow.showAtLocation(JiuyiNewIncludeAttachActivity.this.findViewById(R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    protected View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
            }
            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
            switch (v.getId()) {
                case R.id.item_popupwindows_camera:        //点击拍照按钮
                    String[] list = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                    //检测权限
                    new JiuyiHiPermissionUtil(JiuyiNewIncludeAttachActivity.this).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            goCamera();
                        }
                    });

                    break;
                case R.id.item_popupwindows_Photo:       //点击从相册中选择按钮

                    String[] list2 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil(JiuyiNewIncludeAttachActivity.this).checkPermission(list2, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Intent intent = new Intent(JiuyiNewIncludeAttachActivity.this,
                                    AlbumActivity.class);
                            startActivity(intent);
                        }
                    });


                    break;
                default:
                    break;
            }
        }

    };

    protected void goCamera() {
        filePath = FileUtils.SDPATH + String.valueOf(System.currentTimeMillis())+"photo.jpg";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            File vDirPath = file1.getParentFile();
            vDirPath.mkdirs();
        }
        Uri uri = null;

        if (Build.VERSION.SDK_INT < 24) {
            // 从文件中创建uri
            uri = Uri.fromFile(file1);
        } else {
            //兼容android7.0 使用共享文件的形式
           /* ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, file1.getAbsolutePath());
            uri = JiuyiNewIncludeAttachActivity.this.getApplication().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);*/
            uri = FileProvider.getUriForFile(JiuyiNewIncludeAttachActivity.this, "com.wanglicrm.android.fileProvider", file1 );
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
//                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = BitmapUtils.getCompressedBitmap(JiuyiNewIncludeAttachActivity.this, filePath);
//                    FileUtils.saveBitmap(bm, fileName);

                    ImageBean takePhoto = new ImageBean();
                    takePhoto.setBitmap(bm);

                    //优化压缩图片
                    Uri uri = null;
                    File file1 = new File(filePath);
//                    if (Build.VERSION.SDK_INT < 24) {
//                        // 从文件中创建uri
//                        uri = Uri.fromFile(file1);
//                    } else {
//                        //兼容android7.0 使用共享文件的形式
//                        uri = FileProvider.getUriForFile(JiuyiNewIncludeAttachActivity.this, "com.wanglicrm.android.fileProvider", file1 );
//                    }
//                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(uri);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                    takePhoto.setPath(newFile.getPath());
                    Bimp.tempSelectBitmap.add(takePhoto);
                    if(mtvcount!=null){
                        mtvcount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
                    }
                }
                break;
        }
    }
}
