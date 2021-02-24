package customer.entity;

/**
 * ****************************************************************
 * 文件名称:AbstractSummaryBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/13 11:23
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/13 1.00 初始版本
 * ****************************************************************
 */
public class AbstractSummaryBean {

    /**
     * field : SupplierDirectory
     * fieldTitle : false
     * fieldValue : 供应商名录
     * count : 0
     * iconUrl : http://img.jiuyisoft.com/supplier_directory_android.png
     * businessId : null
     */

    private String field;
    private boolean fieldTitle;
    private String fieldValue;
    private int count;
    private String iconUrl;
    private String businessId;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isFieldTitle() {
        return fieldTitle;
    }

    public void setFieldTitle(boolean fieldTitle) {
        this.fieldTitle = fieldTitle;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}
