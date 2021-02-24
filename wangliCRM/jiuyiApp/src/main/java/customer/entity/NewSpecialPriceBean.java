package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:NewSpecialPriceBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/8/10 18:17
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/8/10 1.00 初始版本
 * ****************************************************************
 */
public class NewSpecialPriceBean {

    /**
     * memberId : 46930
     * beginDate : 2018-08-09
     * endDate : 2018-10-10
     * lowReason : 老客户
     * specialRecordItems : [{"batchNumber":"C4290S","grade":"01","price":"1"}]
     */

    private long memberId;
    private String beginDate;
    private String endDate;
    private String lowReason;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String remark;
    private List<SpecialRecordItemsBean> specialRecordItems;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLowReason() {
        return lowReason;
    }

    public void setLowReason(String lowReason) {
        this.lowReason = lowReason;
    }

    public List<SpecialRecordItemsBean> getSpecialRecordItems() {
        return specialRecordItems;
    }

    public void setSpecialRecordItems(List<SpecialRecordItemsBean> specialRecordItems) {
        this.specialRecordItems = specialRecordItems;
    }

    public static class SpecialRecordItemsBean {
        /**
         * batchNumber : C4290S
         * grade : 01
         * price : 1
         */

        private String batchNumber;
        private String grade;
        private String price;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
