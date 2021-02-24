package com.jiuyi.model;

import com.control.widget.entity.MultiItemEntity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CommonFormBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/12/5 10:01
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/12/5 1.00 初始版本
 * ****************************************************************
 */
public class CommonFormBean implements MultiItemEntity {
    /**
     * 布局类型ItemType
     * IT_INPUT_TEXT普通输入框和下拉选择
     * IT_BIG_INPUT大文本
     * IT_FILE_INPUT 附件
     */
    public static final int IT_INPUT_TEXT = 1;
    public static final int IT_BIG_INPUT = 2;
    public static final int IT_FILE_INPUT = 3;
    public static final int IT_TITLE_INPUT = 4;
    public static final int IT_TOGGLEBUTTON_INPUT = 5;

    /**
     * 行类型
     * INPUT_TEXT,//输入框
     * INPUT_SELECT,//下拉选择框
     * DATE_SELECT,// 日期选择框;
     * FILE_INPUT;//附件输入框
     */
    public static final String INPUT_TEXT = "INPUT_TEXT";
    public static final String INPUT_SELECT = "INPUT_SELECT";
    public static final String FILE_INPUT = "FILE_INPUT";
    public static final String DATE_SELECT = "DATE_SELECT";
    public static final String INPUT_DEPARTMENT = "INPUT_DEPARTMENT";
    public static final String INPUT_MEMBER = "INPUT_MEMBER";
    public static final String INPUT_BRAND_SELECT = "INPUT_BRAND_SELECT";
    public static final String INPUT_OPERATOR = "INPUT_OPERATOR";
    public static final String INPUT_TITLE = "INPUT_TITLE";
    public static final String INPUT_TOGGLEBUTTON = "INPUT_TOGGLEBUTTON";

    /**
     * 输入框类型
     * SHORT_TEXT //短文本
     * LONG_TEXT //长文本
     * NUMBWE_PRICE_TEXT //价格框
     * PHONE_TEXT //手机号码
     * EMAIL_TEXT//邮件
     */
    public static final String SHORT_TEXT = "SHORT_TEXT";
    public static final String LONG_TEXT = "LONG_TEXT";
    public static final String NUMBWE_PRICE_TEXT = "NUMBWE_PRICE_TEXT";
    public static final String PHONE_TEXT = "PHONE_TEXT";
    public static final String EMAIL_TEXT = "EMAIL_TEXT";
    /**
     * 键盘类型
     * KBINTEGER //数字键盘
     * KBDOUBLE  //带小数
     * KBDEFAULT //默认键盘
     */
    public static final String KBINTEGER = "KBINTEGER";
    public static final String KBDOUBLE = "KBDOUBLE";
    public static final String KBDEFAULT = "KBDEFAULT";







    /**
     * 输入框类型:短文本输入框编辑
     */
    public final static String INPUT_TYPE_TEXT_SHORT = "SHORT_TEXT";

    /**
     * 是否为空
     */
    private boolean nullAble;

    /**
     * 是否可编辑
     */
    private boolean editAble;
    /**
     * 输入框类型
     */
    private String rowType;
    /**
     * 行索引,从0开始
     */
    private int index;
    /**
     * 输入框左边描述
     */
    private String leftContent;
    /**
     * 输入框右边描述
     */
    private String rightContent;
    /**
     * 字段名称
     */
    private String key;
    /**
     * 字段值
     */
    private Object value;
    /**
     * 输入框类型：默认端文本输入框
     */
    private String inputType;
    /**
     * 键盘类型：主要用于数字，是否为小数
     */
    private String keyBoardType;
    /**
     * 日期格式
     */
    private String pattern;
    /**
     * 下拉框类型：true:多选，false:单选
     */
    private boolean mutiAble;
    /**
     * 字典
     */
    private String dictName;
    /**
     * 单选的值
     */
    private Object singleValue;
    /**
     * 多选的值
     */
    private List<Object> multipleValue;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    private int itemType=1;

    public boolean isNullAble() {
        return nullAble;
    }

    public void setNullAble(boolean nullAble) {
        this.nullAble = nullAble;
    }

    public boolean isEditAble() {
        return editAble;
    }

    public void setEditAble(boolean editAble) {
        this.editAble = editAble;
    }

    public String getRowType() {
        return rowType;
    }

    public void setRowType(String rowType) {
        this.rowType = rowType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLeftContent() {
        return leftContent;
    }

    public void setLeftContent(String leftContent) {
        this.leftContent = leftContent;
    }

    public String getRightContent() {
        return rightContent;
    }

    public void setRightContent(String rightContent) {
        this.rightContent = rightContent;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getKeyBoardType() {
        return keyBoardType;
    }

    public void setKeyBoardType(String keyBoardType) {
        this.keyBoardType = keyBoardType;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isMutiAble() {
        return mutiAble;
    }

    public void setMutiAble(boolean mutiAble) {
        this.mutiAble = mutiAble;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Object getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(Object singleValue) {
        this.singleValue = singleValue;
    }

    public List<Object> getMultipleValue() {
        return multipleValue;
    }

    public void setMultipleValue(List<Object> multipleValue) {
        this.multipleValue = multipleValue;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
