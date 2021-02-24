package customer.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.widget.NoScrollGridView;
import com.dalong.recordlib.RecordVideoActivity;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;

import java.io.File;
import java.util.ArrayList;

import customer.Utils.FileUtils;
import customer.activity.AlbumActivity;
import customer.activity.ImageBrowseActivity;
import customer.activity.JiuyiCustomerNewMarketDynamicActivity;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.activity.PickerActivity;
import customer.activity.PreviewActivity;
import customer.adapter.InfomationInvoiceAdapter;
import customer.adapter.InfomationPictureAdapter;
import customer.adapter.InfomationVideoAdapter;
import customer.entity.ImageBrowseBean;
import customer.entity.Media;
import customer.listener.ItemClickListener;
import customer.listener.PickerConfig;
import messages.timchat.ui.ImagePreviewActivity;

import static com.vise.utils.handler.HandlerUtil.runOnUiThread;


/**
 * ****************************************************************
 * 文件名称:JiuyiAttachment.java
 * 作    者:Created by zhengss
 * 创建时间:2018/11/27 11:50
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/11/27 1.00 初始版本
 * ****************************************************************
 */

public class JiuyiCommunicationBar extends FrameLayout implements View.OnClickListener {
    private LinearLayout itemGroupLayout; //组合控件的布局
    protected NoScrollGridView noScrollGridView,nsgVideo,nsgVoice2;
    DrawableTextView tvAt,tvVoice,tvPicture,tvVideo;
    private ItemOnClickListener itemOnClickListener; //Item的点击事件

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    private int postion=-1;

    /*图片适配器*/
    public InfomationInvoiceAdapter infomationInvoiceAdapter;
    public InfomationPictureAdapter adapter;
    public InfomationVideoAdapter infomationVideoAdapter;
    protected SelectPicPopupWindow menuWindow;
    protected SelectVideoPopupWindow menuVideoWindow;
    protected SelectVoicePopupWindow menuVoiceWindow;
    protected String filePath;
    /*公共静态Bitmap*/
    public static Bitmap bimap;

    protected static final int TAKE_PICTURE = 1000;
    public static final  int TAKE_DATA=2000;

    private TextView file_path_text;
    private static final int CAMERA_RQ = 8099;
    String videoPath="";



    public JiuyiCommunicationBar(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public JiuyiCommunicationBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public JiuyiCommunicationBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }


    //初始化View
    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.jiuyi_communicationbar_layout, null);
        itemGroupLayout = (LinearLayout) view.findViewById(R.id.item_group_layout);
        tvAt = (DrawableTextView) view.findViewById(R.id.tv_at);
        tvAt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Person");
                intent.putExtras(mBundle);
                Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 1);
            }
        });
        tvVoice = (DrawableTextView) view.findViewById(R.id.tv_voice);
        tvVoice.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infomationInvoiceAdapter!=null && infomationInvoiceAdapter.getCount()>=9){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectVoice();
            }
        });
        tvPicture = (DrawableTextView) view.findViewById(R.id.tv_picture);
        tvPicture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter!=null && adapter.getCount()>=9){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectImgs();
            }
        });
        tvVideo = (DrawableTextView) view.findViewById(R.id.tv_video);
        tvVideo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infomationVideoAdapter!=null && infomationVideoAdapter.getCount()>=PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT){
                    Toast.makeText(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                    return;
                }
                selectVideos();
            }
        });
        noScrollGridView = (NoScrollGridView) view.findViewById(R.id.noScrollgridview);
        noScrollGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new InfomationPictureAdapter(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), new ItemClickListener() {
            @Override
            public void itemClickListener(View v, int position) {
                Bimp.tempSelectBitmap.remove(position);
                refreshPicAttach();
            }
        });
        noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
                    Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(),ImageBrowseActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("param_flag_enum",ImageBrowseActivity.FLAG_ENUM[4]);
                    bundle.putInt("param_position",i);
                    bundle.putParcelableArrayList("image_url_group",imageList);
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivity(intent);
                }


            }
        });
        noScrollGridView.setAdapter(adapter);
        nsgVideo = (NoScrollGridView) view.findViewById(R.id.nsg_video);
        nsgVideo.setSelector(new ColorDrawable(Color.TRANSPARENT));
        infomationVideoAdapter = new InfomationVideoAdapter(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), new ItemClickListener() {
            @Override
            public void itemClickListener(View v, int position) {
                Bimp.tempVideoSelectBitmap.remove(position);
                refreshVideaAttach();
            }
        });
        nsgVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(Bimp.getTempVideoSelectBitmap()!=null && Bimp.getTempVideoSelectBitmap().size()>0){
                    Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                    intent.putExtra(PickerConfig.PRE_RAW_LIST, Bimp.getTempVideoSelectBitmap());
                    Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 200);
                }
            }
        });
        nsgVideo.setAdapter(infomationVideoAdapter);

        nsgVoice2 = (NoScrollGridView) view.findViewById(R.id.nsg_voice2);
        nsgVoice2.setSelector(new ColorDrawable(Color.TRANSPARENT));
        infomationInvoiceAdapter = new InfomationInvoiceAdapter(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), new ItemClickListener() {
            @Override
            public void itemClickListener(View v, int position) {
                Bimp.tempSelectBitmap.remove(position);
                refreshVoiceAttach();
            }
        });
        nsgVoice2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
                    Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(),ImageBrowseActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("param_flag_enum",ImageBrowseActivity.FLAG_ENUM[4]);
                    bundle.putInt("param_position",i);
                    bundle.putParcelableArrayList("image_url_group",imageList);
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivity(intent);
                }


            }
        });
        nsgVoice2.setAdapter(infomationInvoiceAdapter);


        addView(view); //把自定义的这个组合控件的布局加入到当前FramLayout

        itemGroupLayout.setOnClickListener(this);
    }
    protected void selectImgs() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuWindow = new SelectPicPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected void selectVideos() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuVideoWindow = new SelectVideoPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuVideoWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected void selectVoice() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuVoiceWindow = new SelectVoicePopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuVoiceWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected OnClickListener itemsOnClick = new OnClickListener() {
        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
            }
            if (menuVideoWindow != null) {
                menuVideoWindow.dismiss();
            }
            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
            switch (v.getId()) {
                case com.wanglicrm.android.R.id.item_popupwindows_camera:        //点击拍照按钮
                    String[] list = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            goCamera();
                        }
                    });

                    break;
                case com.wanglicrm.android.R.id.item_popupwindows_Photo:       //点击从相册中选择按钮

                    String[] list2 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list2, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Intent intent = new Intent( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(),
                                    AlbumActivity.class);
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivity(intent);
                        }
                    });


                    break;
                case R.id.ll_sure:       //点击从相册中选择按钮
                    if (menuVoiceWindow != null) {
                        menuVoiceWindow.dismiss();
                    }
                    Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.Customer_RecordNew,true);
                    break;
                case R.id.item_popupwindows_video:        //点击录视频按钮
                    String[] list3 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list3, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            startRecordVideo();
                        }
                    });

                    break;
                case R.id.item_popupwindows_videocenter:       //点击从相册中选择按钮

                    String[] list4 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list4, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Intent intent =new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PickerActivity.class);
                            intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_VIDEO);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
                            long maxSize=188743680L;//long long long long类型
                            intent.putExtra(PickerConfig.MAX_SELECT_SIZE,PickerConfig.DEFAULT_SELECTED_MAX_SIZE); //最大选择大小，默认180M（非必填参数）
                            intent.putExtra(PickerConfig.MAX_SELECT_COUNT,PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT);  //最大选择数量，默认40（非必填参数）
//                            ArrayList<Media> defaultSelect = select;//可以设置默认选中的照片，比如把select刚刚选择的list设置成默认的。
//                            intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,defaultSelect); //可以设置默认选中的照片(非必填参数)
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,200);
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
        Rc.getIns().picVideoUrl=filePath;

        if (Build.VERSION.SDK_INT < 24) {
            // 从文件中创建uri
            uri = Uri.fromFile(file1);
        } else {
            //兼容android7.0 使用共享文件的形式
            uri = FileProvider.getUriForFile(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "com.wanglicrm.android.fileProvider", file1 );
        }

        // 启动Camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, TAKE_PICTURE);
    }

    public void startRecordVideo() {
        File path=new File(Environment.getExternalStorageDirectory(),
                "jiuyivideo");
        if (!path.exists()) {
            path.mkdirs();
        }
        videoPath=path.getAbsolutePath()+File.separator+System.currentTimeMillis()+".mp4";
        Rc.getIns().picVideoUrl=videoPath;
        Intent intent=new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), RecordVideoActivity.class);
        intent.putExtra(RecordVideoActivity.RECORD_VIDEO_PATH,videoPath);
        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,TAKE_DATA);
    }


    /**
     * 初始化相关属性，引入相关属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        //通过obtainStyledAttributes方法获取到一个TypedArray对象，然后通过TypedArray对象就可以获取到相对应定义的属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemGroup);
        float paddingLeft = typedArray.getDimension(R.styleable.ItemGroup_paddingLeft, 15);
        float paddingRight = typedArray.getDimension(R.styleable.ItemGroup_paddingRight, 15);
        float paddingTop = typedArray.getDimension(R.styleable.ItemGroup_paddingTop, 5);
        float paddingBottom = typedArray.getDimension(R.styleable.ItemGroup_paddingTop, 5);
        typedArray.recycle();

        //设置数据
        //设置item的内边距
        itemGroupLayout.setPadding((int) paddingLeft, (int) paddingTop, (int) paddingRight, (int) paddingBottom);
    }

    //Item点击事件监听
    public interface ItemOnClickListener {
        void onItemClick(View v);
    }

    /**
     * 供外部调用的方法，设置Item的点击事件
     *
     * @param itemOnClickListener
     */
    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }



    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.item_group_layout) {//点击了Item的布局
            if (itemOnClickListener != null) {
                itemOnClickListener.onItemClick(this);
            }

        }
    }
    public void refreshPicAttach(){
        if(adapter!=null){
            adapter.notifyDataSetChanged();
            if(adapter.getCount()==0){
                noScrollGridView.setVisibility(GONE);
            }
        }



    }
    public void refreshVoiceAttach(){
        if(infomationInvoiceAdapter!=null){
            infomationInvoiceAdapter.notifyDataSetChanged();
            if(infomationInvoiceAdapter.getCount()==0){
                nsgVoice2.setVisibility(GONE);
            }
        }



    }
    public void refreshVideaAttach(){
        if(infomationVideoAdapter!=null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    infomationVideoAdapter.notifyDataSetChanged();
                    if(infomationVideoAdapter.getCount()==0){
                        nsgVideo.setVisibility(GONE);
                    }
                }
            });

        }
    }

}
