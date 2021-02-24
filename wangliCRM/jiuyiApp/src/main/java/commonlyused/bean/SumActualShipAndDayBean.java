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
public class SumActualShipAndDayBean {

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getCumulativeSalesForTheMonth() {
        return cumulativeSalesForTheMonth;
    }

    public void setCumulativeSalesForTheMonth(double cumulativeSalesForTheMonth) {
        this.cumulativeSalesForTheMonth = cumulativeSalesForTheMonth;
    }

    public double getActualSales() {
        return actualSales;
    }

    public void setActualSales(double actualSales) {
        this.actualSales = actualSales;
    }

    private String brandName;
    private double cumulativeSalesForTheMonth;
    private double actualSales;



}
