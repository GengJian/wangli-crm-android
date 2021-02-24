package customer.entity;

/**
 * ****************************************************************
 * 文件名称:BukrsBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/27 16:28
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/27 1.00 初始版本
 * ****************************************************************
 */
public class BukrsBean {

    /**
     * field : 1000
     * fieldTitle : CNY
     * fieldValue : -7403965.17
     * unit :
     * iconUrl : null
     */

    private String field;
    private String fieldTitle;
    private double fieldValue;
    private String unit;
    private Object iconUrl;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldTitle() {
        return fieldTitle;
    }

    public void setFieldTitle(String fieldTitle) {
        this.fieldTitle = fieldTitle;
    }

    public double getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(double fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Object getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(Object iconUrl) {
        this.iconUrl = iconUrl;
    }
}
