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
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.control.widget.NoScrollGridView;
import com.wanglicrm.android.R;




import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import customer.adapter.PictureAdapter;


/**
 * ****************************************************************
 * 文件名称:JiuyiCustomerInformation.java
 * 作    者:Created by zhengss
 * 创建时间:2018/11/27 11:50
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/11/27 1.00 初始版本
 * ****************************************************************
 */

public class JiuyiCustomerInformation extends FrameLayout implements View.OnClickListener {
    private LinearLayout itemGroupLayout; //组合控件的布局
//    private TextView tvCount; //附件数量
    protected NoScrollGridView noScrollGridView;
    DrawableTextView tvAt,tvVoice,tvPicture,tvVideo;

    private ItemOnClickListener itemOnClickListener; //Item的点击事件

    /*图片适配器*/
    protected PictureAdapter adapter;
    protected SelectPicPopupWindow menuWindow;
    protected String filePath;
    /*公共静态Bitmap*/
    public static Bitmap bimap;

    protected static final int TAKE_PICTURE = 1000;


    public JiuyiCustomerInformation(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public JiuyiCustomerInformation(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public JiuyiCustomerInformation(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }


    //初始化View
    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.jiuyi_customerinfomationitem_layout, null);
        itemGroupLayout = (LinearLayout) view.findViewById(R.id.item_group_layout);
//        NiceSpinner niceSpinner1 = (NiceSpinner) view.findViewById(R.id.type1);
//        List<String> dataset1 = new LinkedList<>(Arrays.asList("10","11","12","13","14","15","16","17","18","19"));
//        niceSpinner1.attachDataSource(dataset1);
//        NiceSpinner niceSpinner2 = (NiceSpinner) view.findViewById(R.id.type2);
//        List<String> dataset2 = new LinkedList<>(Arrays.asList("10","11","12","13","14","15","16","17","18","19"));
//        niceSpinner2.attachDataSource(dataset2);


        addView(view); //把自定义的这个组合控件的布局加入到当前FramLayout

        itemGroupLayout.setOnClickListener(this);
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
    public void refreshAttach(){
        adapter.notifyDataSetChanged();

    }

}
