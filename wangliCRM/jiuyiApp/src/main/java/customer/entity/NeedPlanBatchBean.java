package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:NeedPlanBatchBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/9/12 15:45
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/9/12 1.00 初始版本
 * ****************************************************************
 */
public class NeedPlanBatchBean {

    /**
     * memberId : 38632
     * date : 2018-10
     * demandPlanBatchBeans : [{"batchId":3405202,"quantity":2000},{"batchId":3405201,"quantity":3000}]
     */

    private long memberId;
    private String date;
    private List<DemandPlanBatchBeansBean> demandPlanBatchBeans;

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

    public List<DemandPlanBatchBeansBean> getDemandPlanBatchBeans() {
        return demandPlanBatchBeans;
    }

    public void setDemandPlanBatchBeans(List<DemandPlanBatchBeansBean> demandPlanBatchBeans) {
        this.demandPlanBatchBeans = demandPlanBatchBeans;
    }

    public static class DemandPlanBatchBeansBean {
        /**
         * batchId : 3405202
         * quantity : 2000
         */

        private long batchId;
        private double quantity;

        public long getBatchId() {
            return batchId;
        }

        public void setBatchId(long batchId) {
            this.batchId = batchId;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }
    }
}
