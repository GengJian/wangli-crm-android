package customer.entity;

/**
 * ****************************************************************
 * 文件名称:RadarBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/11/2 15:47
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/11/2 1.00 初始版本
 * ****************************************************************
 */
public class RadarBean {

    /**
     * field : 信息完整度
     * fieldTitle : null
     * fieldValue : 24
     */

    private String field;
    private Object fieldTitle;
    private Double fieldValue;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getFieldTitle() {
        return fieldTitle;
    }

    public void setFieldTitle(Object fieldTitle) {
        this.fieldTitle = fieldTitle;
    }

    public Double getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Double fieldValue) {
        this.fieldValue = fieldValue;
    }
}
