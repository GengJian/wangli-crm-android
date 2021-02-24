package commonlyused.bean;

/**
 * ****************************************************************
 * 文件名称:PlanTargetBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/4/17 18:10
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/4/17 1.00 初始版本
 * ****************************************************************
 */
public class PlanTargetBean {


    /**
     * province : 江西
     * salesTarget : 800.0
     */

    private String province;
    private double salesTarget;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getSalesTarget() {
        return salesTarget;
    }

    public void setSalesTarget(double salesTarget) {
        this.salesTarget = salesTarget;
    }
}
