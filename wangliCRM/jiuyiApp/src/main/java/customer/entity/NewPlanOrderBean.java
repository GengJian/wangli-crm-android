package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:NewPlanOrderBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/8/10 18:17
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/8/10 1.00 初始版本
 * ****************************************************************
 */
public class NewPlanOrderBean {

    /**
     * memberId : 46930
     * date : 2018-08-01
     * remark : okok
     * demandRecordItems : [{"batchNumber":"C4398","grade":"01","quantity":"100"}]
     */

    private long memberId;
    private String date;
    private String remark;
    private List<DemandRecordItemsBean> demandRecordItems;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DemandRecordItemsBean> getDemandRecordItems() {
        return demandRecordItems;
    }

    public void setDemandRecordItems(List<DemandRecordItemsBean> demandRecordItems) {
        this.demandRecordItems = demandRecordItems;
    }

    public static class DemandRecordItemsBean {
        /**
         * batchNumber : C4398
         * grade : 01
         * quantity : 100
         */

        private String batchNumber;
        private String grade;
        private String quantity;

        public String getBatchNumber() {
            return batchNumber;
        }

        public void setBatchNumber(String batchNumber) {
            this.batchNumber = batchNumber;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }
}
