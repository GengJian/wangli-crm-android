package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CountryBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/20 20:16
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/20 1.00 初始版本
 * ****************************************************************
 */
public class CountryBean {

    /**
     * content : [{"id":1,"land1":"AD","landx":"安道尔共和国","landx50":"安道尔共和国"},{"id":2,"land1":"AE","landx":"阿联酋","landx50":"阿拉伯联合酋长国"},{"id":3,"land1":"AF","landx":"阿富汗","landx50":"阿富汗"},{"id":4,"land1":"AG","landx":"安提瓜和巴布达","landx50":"安提瓜和巴布达"},{"id":5,"land1":"AI","landx":"安圭拉岛","landx50":"安圭拉岛"},{"id":6,"land1":"AL","landx":"阿尔巴尼亚","landx50":"阿尔巴尼亚"},{"id":7,"land1":"AM","landx":"亚美尼亚","landx50":"亚美尼亚"},{"id":8,"land1":"AN","landx":"荷属安的列斯岛","landx50":"荷属安的列斯岛"},{"id":9,"land1":"AO","landx":"安哥拉","landx50":"安哥拉"},{"id":10,"land1":"AQ","landx":"南极洲","landx50":"南极洲"}]
     * last : false
     * totalElements : 245
     * totalPages : 25
     * number : 0
     * size : 10
     * sort : [{"direction":"ASC","property":"land1","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * first : true
     * numberOfElements : 10
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int number;
    private int size;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;
    private List<SortBean> sort;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public static class ContentBean {
        /**
         * id : 1
         * land1 : AD
         * landx : 安道尔共和国
         * landx50 : 安道尔共和国
         */

        private long id;
        private String land1;
        private String landx;
        private String landx50;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getLand1() {
            return land1;
        }

        public void setLand1(String land1) {
            this.land1 = land1;
        }

        public String getLandx() {
            return landx;
        }

        public void setLandx(String landx) {
            this.landx = landx;
        }

        public String getLandx50() {
            return landx50;
        }

        public void setLandx50(String landx50) {
            this.landx50 = landx50;
        }
    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : land1
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : true
         * descending : false
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean ascending;
        private boolean descending;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
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
    }
}
