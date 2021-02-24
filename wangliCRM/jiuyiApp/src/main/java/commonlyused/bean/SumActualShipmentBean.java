package commonlyused.bean;

/**
 * ****************************************************************
 * 文件名称:SumActualShipmentBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/4/17 18:06
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/4/17 1.00 初始版本
 * ****************************************************************
 */
public class SumActualShipmentBean {
    public double getSumAccumulateVisit() {
        return sumAccumulateVisit;
    }

    public void setSumAccumulateVisit(double sumAccumulateVisit) {
        this.sumAccumulateVisit = sumAccumulateVisit;
    }

    private double sumAccumulateVisit;
    public double getSumActualReceivedPayments() {
        return sumActualReceivedPayments;
    }

    public void setSumActualReceivedPayments(double sumActualReceivedPayments) {
        this.sumActualReceivedPayments = sumActualReceivedPayments;
    }

    public double sumActualReceivedPayments;
    public double getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(double workPlan) {
        this.workPlan = workPlan;
    }

    private double workPlan;

    public double getSumActualShipment() {
        return sumActualShipment;
    }

    public void setSumActualShipment(double sumActualShipment) {
        this.sumActualShipment = sumActualShipment;
    }

    /**
     * sumActualShipment : 102
     */

    private double sumActualShipment;

    public double getTodayDeliveryTotal() {
        return todayDeliveryTotal;
    }

    public void setTodayDeliveryTotal(double todayDeliveryTotal) {
        this.todayDeliveryTotal = todayDeliveryTotal;
    }

    private double todayDeliveryTotal;
}
