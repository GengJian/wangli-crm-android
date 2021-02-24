package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CreditBalanceAmountChartBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/7 16:29
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/7 1.00 初始版本
 * ****************************************************************
 */
public class CreditBalanceAmountChartBean {

    /**
     * title : 2019-01前12个月收款趋势图
     * beanList : [{"field":"2018-02","fieldTitle":null,"fieldValue":1000},{"field":"2018-03","fieldTitle":null,"fieldValue":1000},{"field":"2018-04","fieldTitle":null,"fieldValue":1000},{"field":"2018-05","fieldTitle":null,"fieldValue":1000},{"field":"2018-06","fieldTitle":null,"fieldValue":1000},{"field":"2018-07","fieldTitle":null,"fieldValue":1000},{"field":"2018-08","fieldTitle":null,"fieldValue":1000},{"field":"2018-09","fieldTitle":null,"fieldValue":1000},{"field":"2018-10","fieldTitle":null,"fieldValue":1000},{"field":"2018-11","fieldTitle":null,"fieldValue":1000},{"field":"2018-12","fieldTitle":null,"fieldValue":1000},{"field":"2019-01","fieldTitle":null,"fieldValue":1000}]
     */

    private String title;
    private List<BeanListBean> beanList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BeanListBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<BeanListBean> beanList) {
        this.beanList = beanList;
    }

    public static class BeanListBean {
        /**
         * field : 2018-02
         * fieldTitle : null
         * fieldValue : 1000
         */

        private String field;
        private Object fieldTitle;
        private double fieldValue;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        private String unit;

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

        public double getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(double fieldValue) {
            this.fieldValue = fieldValue;
        }
    }
}
