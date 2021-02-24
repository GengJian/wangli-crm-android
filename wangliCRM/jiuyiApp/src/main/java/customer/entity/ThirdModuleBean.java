package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:ThirdModuleBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/6 11:26
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/6 1.00 初始版本
 * ****************************************************************
 */
public class ThirdModuleBean {

    /**
     * fundsId : -1
     * title : 暂无记录
     * beanList : [{"field":"资产总额","fieldTitle":"负债率：0.00%","fieldValue":0,"unit":"万","iconUrl":""},{"field":"销售收入","fieldTitle":"增长率：0.00%","fieldValue":0,"unit":"万","iconUrl":""},{"field":"毛利润","fieldTitle":"利润率：0.00%","fieldValue":0,"unit":"万","iconUrl":""},{"field":"净利润","fieldTitle":"利润率0.00%","fieldValue":0,"unit":"万","iconUrl":""},{"field":"现金流","fieldTitle":"比率：0.00%","fieldValue":0,"unit":"万","iconUrl":""},{"field":"应收","fieldTitle":"周转天数：0天","fieldValue":0,"unit":"万","iconUrl":""},{"field":"应付","fieldTitle":"周转天数0天","fieldValue":0,"unit":"万","iconUrl":""},{"field":"三年盈亏","fieldTitle":"","fieldValue":0,"unit":"万","iconUrl":""},{"field":"平均变现周期","fieldTitle":"","fieldValue":0,"unit":"天","iconUrl":""},{"field":"应收逾期天数","fieldTitle":"","fieldValue":0,"unit":"天","iconUrl":""},{"field":"注册资金","fieldTitle":"","fieldValue":0,"unit":"万","iconUrl":""},{"field":"信用额度","fieldTitle":"帐期：0天","fieldValue":0,"unit":"万","iconUrl":""},{"field":"客户余额","fieldTitle":"","fieldValue":0,"unit":"万","iconUrl":""},{"field":"已发货未开票","fieldTitle":"","fieldValue":0,"unit":"万","iconUrl":""}]
     */

    private int fundsId;
    private String title;
    private List<BeanListBean> beanList;

    public int getFundsId() {
        return fundsId;
    }

    public void setFundsId(int fundsId) {
        this.fundsId = fundsId;
    }

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
         * field : 资产总额
         * fieldTitle : 负债率：0.00%
         * fieldValue : 0
         * unit : 万
         * iconUrl :
         */

        private String field;
        private String fieldTitle;
        private Object fieldValue;
        private String unit;
        private String iconUrl;

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

        public Object getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(Object fieldValue) {
            this.fieldValue = fieldValue;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }
    }
}
