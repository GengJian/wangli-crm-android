package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:MonthCreditHeadBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/9/13 20:30
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/9/13 1.00 初始版本
 * ****************************************************************
 */
public class MonthCreditHeadBean {

    private List<FirstItemBean> firstItem;

    public List<FirstItemBean> getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(List<FirstItemBean> firstItem) {
        this.firstItem = firstItem;
    }

    public static class FirstItemBean {
        /**
         * field : 注册资金
         * fieldTitle : null
         * fieldValue : 0
         * unit : 万
         */

        private String field;
        private String fieldTitle;
        private String fieldValue;
        private String unit;

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

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
