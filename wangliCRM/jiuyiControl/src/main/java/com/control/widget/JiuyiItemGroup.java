package com.control.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.R;


/**
 * ****************************************************************
 * 文件名称:JiuyiItemGroup.java
 * 作    者:Created by zhengss
 * 创建时间:2018/11/27 11:50
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/11/27 1.00 初始版本
 * ****************************************************************
 */

public class JiuyiItemGroup extends FrameLayout implements View.OnClickListener {

    private LinearLayout itemGroupLayout; //组合控件的布局
    private TextView titleTv,tv_value; //标题
    public JiuyiEditTextField contentEdt; //输入框
    public ImageView clearIv; //清楚输入内容
    private ImageView jtRightIv; //向右的箭头
    private ItemOnClickListener itemOnClickListener; //Item的点击事件
    private  boolean isEditable=true;


    public JiuyiItemGroup(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public JiuyiItemGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttrs(context, attrs);
    }

    public JiuyiItemGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttrs(context, attrs);
    }


    //初始化View
    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.jiuyi_item_group_layout, null);
        itemGroupLayout = (LinearLayout) view.findViewById(R.id.item_group_layout);
        titleTv = (TextView) view.findViewById(R.id.tv_title);
        tv_value = (TextView) view.findViewById(R.id.tv_value);
        contentEdt = (JiuyiEditTextField) view.findViewById(R.id.content_edt);
        clearIv = (ImageView) view.findViewById(R.id.clear_iv);
        jtRightIv = (ImageView) view.findViewById(R.id.jt_right_iv);
        addView(view); //把自定义的这个组合控件的布局加入到当前FramLayout

        itemGroupLayout.setOnClickListener(this);
        clearIv.setOnClickListener(this);
        contentEdt.setSingleLine(false);
        contentEdt.setHorizontallyScrolling(false);
        contentEdt.addTextChangedListener(new JiuyiTextChangeWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                //输入框输入内容改变
                String content = contentEdt.getText().toString().trim();
               /* if (!TextUtils.isEmpty(content)) {
                    //输入内容不为空的时候，清除输入的Icon可见
                    clearIv.setVisibility(VISIBLE);
                } else {
                    clearIv.setVisibility(GONE);
                }*/
            }
        });
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
        float paddingLeft = typedArray.getDimension(R.styleable.ItemGroup_paddingLeft, 10);
        float paddingRight = typedArray.getDimension(R.styleable.ItemGroup_paddingRight, 10);
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
        isEditable = typedArray.getBoolean(R.styleable.ItemGroup_isEditable, true);
        //向右的箭头图标是否可见，默认可见
        boolean showJtIcon = typedArray.getBoolean(R.styleable.ItemGroup_jt_visible, true);
        typedArray.recycle();

        //设置数据
        //设置item的内边距
//        itemGroupLayout.setPadding((int) paddingLeft, (int) paddingTop, (int) paddingRight, (int) paddingBottom);
        titleTv.setText(title);
        titleTv.setTextSize(titleSize);
        titleTv.setTextColor(titleColor);

        contentEdt.setText(content);
        contentEdt.setTextSize(contentSize);
        contentEdt.setTextColor(contentColor);
        contentEdt.setHint(hintContent);
        contentEdt.setHintTextColor(hintColor);
        contentEdt.setFocusableInTouchMode(isEditable); //设置输入框是否可以编辑
        contentEdt.setLongClickable(false); //输入框不允许长按
        contentEdt.setVisibility(isEditable ? View.VISIBLE : View.GONE);  //设置向右的箭头图标是否可见

        tv_value.setText(content);
        tv_value.setTextSize(contentSize);
        tv_value.setTextColor(contentColor);
        tv_value.setHint(hintContent);
        tv_value.setHintTextColor(hintColor);
        tv_value.setVisibility(isEditable ? View.GONE:View.VISIBLE);
        jtRightIv.setVisibility(showJtIcon ? View.VISIBLE : View.GONE);  //设置向右的箭头图标是否可见
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

    //获取输入的内容
    public String getText() {
        if(isEditable){
            return contentEdt.getText().toString().trim();
        }else{
            return tv_value.getText().toString().trim();
        }

    }

    //设置输入框的内容
    public void setText(String text) {
        contentEdt.setText(text);
        tv_value.setText(text);
    }
    

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.item_group_layout) {//点击了Item的布局
            if (itemOnClickListener != null) {
                itemOnClickListener.onItemClick(this);
            }

        } /*else if (i == R.id.clear_iv) {//清除输入内容
            contentEdt.setText("");
            clearIv.setVisibility(GONE);

        }*/
    }
}
