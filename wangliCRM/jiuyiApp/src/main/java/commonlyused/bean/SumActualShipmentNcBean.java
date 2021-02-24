package commonlyused.bean;

/**
 * ****************************************************************
 * 文件名称:SumActualShipmentNcBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/5/10 17:24
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/5/10 1.00 初始版本
 * ****************************************************************
 */
public class SumActualShipmentNcBean {

    public double getSumAccumulateVisit() {
        return sumAccumulateVisit;
    }

    public void setSumAccumulateVisit(double sumAccumulateVisit) {
        this.sumAccumulateVisit = sumAccumulateVisit;
    }

    public double getWorkPlan0() {
        return workPlan0;
    }

    public void setWorkPlan0(double workPlan0) {
        this.workPlan0 = workPlan0;
    }

    public double getSumActualShipment() {
        return sumActualShipment;
    }

    public void setSumActualShipment(double sumActualShipment) {
        this.sumActualShipment = sumActualShipment;
    }

    public double getWorkPlan1() {
        return workPlan1;
    }

    public void setWorkPlan1(double workPlan1) {
        this.workPlan1 = workPlan1;
    }

    /**
     * sumAccumulateVisit : 7
     * workPlan0 : 123
     * sumActualShipment : 690
     * workPlan1 : 123.66
     */

    private double sumAccumulateVisit;
    private double workPlan0;
    private double sumActualShipment;
    private double workPlan1;


}
