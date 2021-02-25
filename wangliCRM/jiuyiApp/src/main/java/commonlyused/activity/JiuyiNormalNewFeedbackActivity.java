package commonlyused.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Res;
import com.control.widget.JiuyiButton;
import com.control.widget.NoScrollGridView;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;
import com.nanchen.compresshelper.CompressHelper;
import com.wanglicrm.android.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import customer.Utils.BitmapUtils;
import customer.Utils.FileUtils;
import customer.activity.AlbumActivity;
import customer.activity.GalleryActivity;
import customer.adapter.PictureAdapter;
import customer.entity.ImageBean;
import customer.listener.ItemClickListener;
import customer.view.Bimp;
import customer.view.SelectPicPopupWindow;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNormalNewFeedbackActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用新反馈新增界面
 *****************************************************************
 */
public class JiuyiNormalNewFeedbackActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,mtvcancel,mtvdate,mtvcount,tv_producttype;
    private String startDate;
    private JiuyiButton mbtnSave;

    /*不滚动的GridView*/
    private NoScrollGridView noScrollGridView;
    /*图片适配器*/
    private PictureAdapter adapter;
    private SelectPicPopupWindow menuWindow;
    private String filePath;
    private Button btnUpload;
    /*公共静态Bitmap*/
    public static Bitmap bimap;

    private static final int TAKE_PICTURE = 1000;



    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_normalnewfeedback_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);

        setTitle();
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.GONE);
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
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
        mbtnSave= (JiuyiButton) mBodyLayout.findViewById(Res.getViewID(null, "btn_save"));
        if(mbtnSave!=null){
            mbtnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
                }
            });
        }
        mtvcount= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_count"));
        tv_producttype= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_type"));
        tv_producttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    设置一个单项选择下拉框
                /**
                 * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                 * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
                 * 第三个参数给每一个单选项绑定一个监听器
                 */
              /*  AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiNormalNewFeedbackActivity.this);
                buidler.setTitle("产品类型");
                final String[] data = getResources().getStringArray(R.array.producttype);
                buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Way = data[which];
                        tv_producttype.setText(data[which].trim());
                        dialog.dismiss();
                    }
                });
                buidler.show();*/
            }
        });
        mtvdate = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_date"));
        if (mtvdate != null) {
            mtvdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiNormalNewFeedbackActivity.this);
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
        initViews();


    }

    private void initViews() {
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
                    Intent intent = new Intent(JiuyiNormalNewFeedbackActivity.this,
                            GalleryActivity.class);
                    intent.putExtra("ID", i);
                    startActivity(intent);
                }
            }
        });
        noScrollGridView.setAdapter(adapter);
    }
    public void setTitle(){
        mTitle = "新建任务反馈";
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

    private void selectImgs() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(JiuyiNormalNewFeedbackActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuWindow = new SelectPicPopupWindow(JiuyiNormalNewFeedbackActivity.this, itemsOnClick);
        //设置弹窗位置
        menuWindow.showAtLocation(JiuyiNormalNewFeedbackActivity.this.findViewById(R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
            }
            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
            switch (v.getId()) {
                case R.id.item_popupwindows_camera:        //点击拍照按钮
                    String[] list = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                    //检测权限
                    new JiuyiHiPermissionUtil(JiuyiNormalNewFeedbackActivity.this).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            goCamera();
                        }
                    });

                    break;
                case R.id.item_popupwindows_Photo:       //点击从相册中选择按钮

                    String[] list2 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil(JiuyiNormalNewFeedbackActivity.this).checkPermission(list2, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Intent intent = new Intent(JiuyiNormalNewFeedbackActivity.this,
                                    AlbumActivity.class);
                            startActivity(intent);
                        }
                    });


                    break;
//                case R.id.btn_upload:         // 开始上传
//                {
//                    if (Bimp.tempSelectBitmap.size() == 0) {
//                        Toast.makeText(JiuyiCustomerNewProductInfoActivity.this, "没有可上传的图片", Toast.LENGTH_SHORT).show();
//                    } else {
////                        UploadTask uTask = new UploadTask();
////                        uTask.execute(100);
//                    }
//                }
//                break;
                default:
                    break;
            }
        }

    };

    private void goCamera() {
        filePath = FileUtils.SDPATH + String.valueOf(System.currentTimeMillis())+"photo.jpg";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            File vDirPath = file1.getParentFile();
            vDirPath.mkdirs();
        }
//        Uri uri = Uri.fromFile(file1);
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
          /*  ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, file1.getAbsolutePath());
            uri = JiuyiNormalNewFeedbackActivity.this.getApplication().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);*/

            uri = FileProvider.getUriForFile(JiuyiNormalNewFeedbackActivity.this, "com.wanglicrm.android.fileProvider", file1 );
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
                    Bitmap bm = BitmapUtils.getCompressedBitmap(JiuyiNormalNewFeedbackActivity.this, filePath);
                    ImageBean takePhoto = new ImageBean();
                    takePhoto.setBitmap(bm);
                    Uri uri = null;
                    File file1 = new File(filePath);
//                    if (Build.VERSION.SDK_INT < 24) {
//                        // 从文件中创建uri
//                        uri = Uri.fromFile(file1);
//                    } else {
//                        //兼容android7.0 使用共享文件的形式
//                        uri = FileProvider.getUriForFile(JiuyiNormalNewFeedbackActivity.this, "com.wanglicrm.android.fileProvider", file1 );
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
