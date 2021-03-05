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
import android.provider.MediaStore;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.R;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.widget.NoScrollGridView;
import com.jiuyi.app.JiuyiMainApplication;

import java.io.File;
import java.util.ArrayList;

import customer.Utils.FileUtils;
import customer.activity.AlbumActivity;
import customer.activity.ImageBrowseActivity;
import customer.activity.JiuyiNewIncludeAttachActivity;
import customer.activity.PickerActivity;
import customer.activity.PreviewActivity;
import customer.adapter.CommonPictureAdapter;
import customer.adapter.IntelligenceDetailPictureAdapter;
import customer.adapter.PictureAdapter;
import customer.entity.ImageBrowseBean;
import customer.entity.Media;
import customer.listener.ItemClickListener;
import customer.listener.PickerConfig;


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

public class JiuyiAttachment extends FrameLayout implements View.OnClickListener {
    private LinearLayout itemGroupLayout; //组合控件的布局
    private TextView tvCount; //附件数量
    protected RecyclerView noScrollGridView;


    private ItemOnClickListener itemOnClickListener; //Item的点击事件

    public CommonPictureAdapter adapter;
    protected SelectPicPopupWindow menuWindow;
    protected String filePath;
    protected static final int SELECT_PICTURE = 1500;
    protected static final int TAKE_PICTURE = 1000;
    private  int SELECT_PICTURE_NUM=1500;
    private  int TAKE_PICTURE_NUM=1000;

    public ArrayList<Media> getMediaArrayList() {
        return mediaArrayList;
    }

    public void setMediaArrayList(ArrayList<Media> mediaArrayList) {
        this.mediaArrayList = mediaArrayList;
    }

    private ArrayList<Media> mediaArrayList=new ArrayList<>();


    public JiuyiAttachment(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public JiuyiAttachment(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public JiuyiAttachment(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }


    //初始化View
    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.jiuyi_attachment_layout, null);
        itemGroupLayout = (LinearLayout) view.findViewById(R.id.item_group_layout);
        tvCount = (TextView) view.findViewById(R.id.tv_count);
        noScrollGridView = (RecyclerView) view.findViewById(R.id.noScrollgridview);
        GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(),5);
        noScrollGridView.setLayoutManager(mgr);
        noScrollGridView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(com.wanglicrm.android.R.color.background)));
        addView(view); //把自定义的这个组合控件的布局加入到当前FramLayout

        itemGroupLayout.setOnClickListener(this);
    }
    public void setAdapter() {
        adapter = new CommonPictureAdapter(JiuyiMainApplication.getIns(), mediaArrayList);
        noScrollGridView.setAdapter(adapter);
        adapter.setOnRecyclerViewItemListener(new CommonPictureAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if (position == mediaArrayList.size()) {
                    selectImgs();
                } else {
                    Intent intent = new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PreviewActivity.class);
                    intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
                    intent.putExtra(PickerConfig.PRE_RAW_LIST, mediaArrayList);
                    intent.putExtra(PickerConfig.MAX_SELECT_COUNT,9);  //最大选择数量，默认40（非必填参数）
                    intent.putExtra(PickerConfig.CURRENT_POSITION,position);  //最大选择数量，默认40（非必填参数）
                    Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 1500);
                }

            }
        });
        adapter.setOnCLickItemListener(new CommonPictureAdapter.onCLickItemListener() {
            @Override
            public void click(int position, String colname, View view) {
                if (colname.equals("img_delete")) {
                    mediaArrayList.remove(position);
                    refreshAttach();
                }

            }
        });
    }
    protected void selectImgs() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuWindow = new SelectPicPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
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
                            Intent intent =new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PickerActivity.class);
                            intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
                            intent.putExtra(PickerConfig.MAX_SELECT_COUNT,9);  //最大选择数量，默认40（非必填参数）
                            if(mediaArrayList!=null && mediaArrayList.size()>0 ){
                                intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,mediaArrayList); //可以设置默认选中的照片(非必填参数)
                            }
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,SELECT_PICTURE_NUM);
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
        Rc.picVideoUrl=filePath;
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
            uri = FileProvider.getUriForFile(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "com.wanglicrm.android.fileProvider", file1 );
        }

        // 启动Camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, TAKE_PICTURE_NUM);
    }


    /**
     * 初始化相关属性，引入相关属性
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        //标题默认的字体大小，15sp
        float defaultTitleSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, context.getResources().getDisplayMetrics());
        //标题的默认字体颜色
        int defaultTitleColor = context.getResources().getColor(R.color.item_group_title);
        //输入框默认的字体大小，13sp
        float defaultEdtSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, context.getResources().getDisplayMetrics());
        //输入框的默认字体颜色
        int defaultEdtColor = context.getResources().getColor(R.color.item_group_edt);
        //输入框的默认的提示内容的字体颜色
        int defaultHintColor = context.getResources().getColor(R.color.item_group_edt);

        //通过obtainStyledAttributes方法获取到一个TypedArray对象，然后通过TypedArray对象就可以获取到相对应定义的属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemGroup);
        String title = typedArray.getString(R.styleable.ItemGroup_title);
        float paddingLeft = typedArray.getDimension(R.styleable.ItemGroup_paddingLeft, 15);
        float paddingRight = typedArray.getDimension(R.styleable.ItemGroup_paddingRight, 15);
        float paddingTop = typedArray.getDimension(R.styleable.ItemGroup_paddingTop, 5);
        float paddingBottom = typedArray.getDimension(R.styleable.ItemGroup_paddingTop, 5);
        float titleSize = typedArray.getDimension(R.styleable.ItemGroup_title_size, 15);
        int titleColor = typedArray.getColor(R.styleable.ItemGroup_title_color, defaultTitleColor);
        String content = typedArray.getString(R.styleable.ItemGroup_edt_content);
        float contentSize = typedArray.getDimension(R.styleable.ItemGroup_edt_text_size, 13);
        int contentColor = typedArray.getColor(R.styleable.ItemGroup_edt_text_color, defaultEdtColor);
        String hintContent = typedArray.getString(R.styleable.ItemGroup_edt_hint_content);
        int hintColor = typedArray.getColor(R.styleable.ItemGroup_edt_hint_text_color, defaultHintColor);
        //默认输入框可以编辑
        boolean isEditable = typedArray.getBoolean(R.styleable.ItemGroup_isEditable, true);
        //向右的箭头图标是否可见，默认可见
        boolean showJtIcon = typedArray.getBoolean(R.styleable.ItemGroup_jt_visible, true);
        SELECT_PICTURE_NUM = typedArray.getColor(R.styleable.ItemGroup_select_picture_num, 1500);
        TAKE_PICTURE_NUM = typedArray.getColor(R.styleable.ItemGroup_take_picture_num, 1000);
        typedArray.recycle();

        //设置数据
        //设置item的内边距
        itemGroupLayout.setPadding((int) paddingLeft, (int) paddingTop, (int) paddingRight, (int) paddingBottom);
        tvCount.setText(title);
        tvCount.setTextSize(titleSize);
        tvCount.setTextColor(titleColor);
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
    public void refreshAttach(){
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
        if(tvCount!=null){
            tvCount.setText(mediaArrayList.size()+ "/" + 9 );
        }
    }

}
