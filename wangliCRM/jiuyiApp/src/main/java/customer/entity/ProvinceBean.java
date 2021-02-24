package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:ProvinceBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/4/3 11:48
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/4/3 1.00 初始版本
 * ****************************************************************
 */
public class ProvinceBean {

    /**
     * number : 0
     * last : false
     * numberOfElements : 10
     * size : 10
     * totalPages : 4
     * sort : [{"nullHandling":"NATIVE","ignoreCase":false,"property":"id","ascending":true,"descending":false,"direction":"ASC"}]
     * first : true
     * content : [{"provinceName":"北京市","id":1,"provinceId":"110000"},{"provinceName":"天津市","id":2,"provinceId":"120000"},{"provinceName":"河北省","id":3,"provinceId":"130000"},{"provinceName":"山西省","id":4,"provinceId":"140000"},{"provinceName":"内蒙古自治区","id":5,"provinceId":"150000"},{"provinceName":"辽宁省","id":6,"provinceId":"210000"},{"provinceName":"吉林省","id":7,"provinceId":"220000"},{"provinceName":"黑龙江省","id":8,"provinceId":"230000"},{"provinceName":"上海市","id":9,"provinceId":"310000"},{"provinceName":"江苏省","id":10,"provinceId":"320000"}]
     * totalElements : 34
     */

    private int number;
    private boolean last;
    private int numberOfElements;
    private int size;
    private int totalPages;
    private boolean first;
    private int totalElements;
    private List<SortBean> sort;
    private List<ContentBean> content;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class SortBean {
        /**
         * nullHandling : NATIVE
         * ignoreCase : false
         * property : id
         * ascending : true
         * descending : false
         * direction : ASC
         */

        private String nullHandling;
        private boolean ignoreCase;
        private String property;
        private boolean ascending;
        private boolean descending;
        private String direction;

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }

    public static class ContentBean {
        /**
         * provinceName : 北京市
         * id : 1
         * provinceId : 110000
         */

        private String provinceName;
        private long id;
        private String provinceId;

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }
    }


    public static class CityBean {


        private String cityName;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        private String cityId;
        private long id;
        private String provinceId;


    }
    public static class AreaBean {


        private String areaName;

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        private String areaId;
        private long id;
        private String cityId;


    }

}
