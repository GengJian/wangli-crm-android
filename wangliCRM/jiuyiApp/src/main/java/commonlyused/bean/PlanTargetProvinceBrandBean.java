package commonlyused.bean;

import java.util.List;

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
public class PlanTargetProvinceBrandBean {


    /**
     * province : 全国
     * salesTarget : 65.0
     * brand : {"createdBy":"guanliyuan","createdDate":"2019-04-09 14:07:42","lastModifiedBy":"guanliyuan","lastModifiedDate":"2019-04-09 14:07:47","id":3,"deleted":null,"sort":0,"fromClientType":null,"optionGroup":[],"searchContent":null,"brandName":"华爵","brandDesc":"华爵"}
     */

    private String province;
    private double salesTarget;
    private BrandBean brand;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

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

    public BrandBean getBrand() {
        return brand;
    }

    public void setBrand(BrandBean brand) {
        this.brand = brand;
    }

    public static class BrandBean {
        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandDesc() {
            return brandDesc;
        }

        public void setBrandDesc(String brandDesc) {
            this.brandDesc = brandDesc;
        }

        private String brandName;
        private String brandDesc;



    }
}
