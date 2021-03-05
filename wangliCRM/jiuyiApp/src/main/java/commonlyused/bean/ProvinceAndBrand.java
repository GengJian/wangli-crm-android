package commonlyused.bean;


import customer.entity.BrandBean;
import customer.entity.ProvinceBean;

import java.util.Set;

/**
 * @author: zhengss
 * @Date: 2020/9/10
 * @description: 省份 品牌
 */
public class ProvinceAndBrand {

    private Set<PlanTargetProvinceBrandBean.BrandBean> brandSet;

    private Set<ProvinceBean.ContentBean> provinceSet;

    public Set<PlanTargetProvinceBrandBean.BrandBean> getBrandSet() {
        return brandSet;
    }

    public void setBrandSet(Set<PlanTargetProvinceBrandBean.BrandBean> brandSet) {
        this.brandSet = brandSet;
    }

    public Set<ProvinceBean.ContentBean> getProvinceSet() {
        return provinceSet;
    }

    public void setProvinceSet(Set<ProvinceBean.ContentBean> provinceSet) {
        this.provinceSet = provinceSet;
    }
}
