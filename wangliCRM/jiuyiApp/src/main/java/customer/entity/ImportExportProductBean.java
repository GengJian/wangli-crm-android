package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:ImportExportProductBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/8 11:59
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/8 1.00 初始版本
 * ****************************************************************
 */
public class ImportExportProductBean {


    /**
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 1
     * size : 5
     * totalElements : 1
     * totalPages : 1
     * sort : [{"ascending":true,"descending":false,"direction":"ASC","ignoreCase":false,"nullHandling":"NATIVE","property":"id"}]
     * content : [{"id":7,"sort":10,"createdDate":1546925585628,"lastModifiedDate":1546925585628,"createdBy":"1022654","lastModifiedBy":"1022654","country":"D","productName":"1","dataResource":"C","lastYearQ1S":1,"lastYearQ1D":1,"lastYearQ2S":1,"lastYearQ2D":1,"lastYearQ3S":1,"lastYearQ3D":1,"lastYearQ4S":1,"lastYearQ4D":1,"thisYearQ1S":1,"thisYearQ1D":1,"thisYearQ2S":1,"thisYearQ2D":2,"thisYearQ3S":1,"thisYearQ3D":0,"thisYearQ4S":1,"thisYearQ4D":1,"nextYearQ1S":1,"nextYearQ1D":0,"nextYearQ2S":1,"nextYearQ2D":1,"nextYearQ3S":1,"nextYearQ3D":1,"nextYearQ4S":1,"nextYearQ4D":1,"productType":"B","member":{"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546079225376,"createdBy":"system","lastModifiedBy":"system","cooperationTypeValue":"供应商","simpleSpell":"dgsgr","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","sapNumber":"200567","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配"},"operator":{"id":1,"sort":10,"createdDate":1543497342573,"lastModifiedDate":1546917438095,"createdBy":"15167156690","lastModifiedBy":"1022654","name":"刘启明","department":{"id":1,"sort":100,"createdDate":1543497296440,"lastModifiedDate":1545831978687,"createdBy":"15167156690","lastModifiedBy":"15167156690","name":"营销部-禁用"},"title":"营销分析员","oaCode":"1022654"},"businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂"}]
     */

    private boolean first;
    private boolean last;
    private int number;
    private int numberOfElements;
    private int size;
    private int totalElements;
    private int totalPages;
    private List<SortBean> sort;
    private List<ContentBean> content;

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
         * ascending : true
         * descending : false
         * direction : ASC
         * ignoreCase : false
         * nullHandling : NATIVE
         * property : id
         */

        private boolean ascending;
        private boolean descending;
        private String direction;
        private boolean ignoreCase;
        private String nullHandling;
        private String property;

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

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }

    public static class ContentBean {
        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String country;
        private String productName;
        private String dataResource;
        private double lastYearQ1S;
        private double lastYearQ1D;
        private double lastYearQ2S;
        private double lastYearQ2D;
        private double lastYearQ3S;
        private double lastYearQ3D;
        private double lastYearQ4S;
        private double lastYearQ4D;
        private double thisYearQ1S;
        private double thisYearQ1D;
        private double thisYearQ2S;
        private double thisYearQ2D;
        private double thisYearQ3S;
        private double thisYearQ3D;
        private double thisYearQ4S;
        private double thisYearQ4D;
        private double nextYearQ1S;
        private double nextYearQ1D;
        private double nextYearQ2S;
        private double nextYearQ2D;
        private double nextYearQ3S;
        private double nextYearQ3D;
        private double nextYearQ4S;
        private double nextYearQ4D;
        private String productType;
        private MemberBeanID member;
        private String businessTypeKey;
        private String businessTypeValue;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getDataResource() {
            return dataResource;
        }

        public void setDataResource(String dataResource) {
            this.dataResource = dataResource;
        }

        public double getLastYearQ1S() {
            return lastYearQ1S;
        }

        public void setLastYearQ1S(double lastYearQ1S) {
            this.lastYearQ1S = lastYearQ1S;
        }

        public double getLastYearQ1D() {
            return lastYearQ1D;
        }

        public void setLastYearQ1D(double lastYearQ1D) {
            this.lastYearQ1D = lastYearQ1D;
        }

        public double getLastYearQ2S() {
            return lastYearQ2S;
        }

        public void setLastYearQ2S(double lastYearQ2S) {
            this.lastYearQ2S = lastYearQ2S;
        }

        public double getLastYearQ2D() {
            return lastYearQ2D;
        }

        public void setLastYearQ2D(double lastYearQ2D) {
            this.lastYearQ2D = lastYearQ2D;
        }

        public double getLastYearQ3S() {
            return lastYearQ3S;
        }

        public void setLastYearQ3S(double lastYearQ3S) {
            this.lastYearQ3S = lastYearQ3S;
        }

        public double getLastYearQ3D() {
            return lastYearQ3D;
        }

        public void setLastYearQ3D(double lastYearQ3D) {
            this.lastYearQ3D = lastYearQ3D;
        }

        public double getLastYearQ4S() {
            return lastYearQ4S;
        }

        public void setLastYearQ4S(double lastYearQ4S) {
            this.lastYearQ4S = lastYearQ4S;
        }

        public double getLastYearQ4D() {
            return lastYearQ4D;
        }

        public void setLastYearQ4D(double lastYearQ4D) {
            this.lastYearQ4D = lastYearQ4D;
        }

        public double getThisYearQ1S() {
            return thisYearQ1S;
        }

        public void setThisYearQ1S(double thisYearQ1S) {
            this.thisYearQ1S = thisYearQ1S;
        }

        public double getThisYearQ1D() {
            return thisYearQ1D;
        }

        public void setThisYearQ1D(double thisYearQ1D) {
            this.thisYearQ1D = thisYearQ1D;
        }

        public double getThisYearQ2S() {
            return thisYearQ2S;
        }

        public void setThisYearQ2S(double thisYearQ2S) {
            this.thisYearQ2S = thisYearQ2S;
        }

        public double getThisYearQ2D() {
            return thisYearQ2D;
        }

        public void setThisYearQ2D(double thisYearQ2D) {
            this.thisYearQ2D = thisYearQ2D;
        }

        public double getThisYearQ3S() {
            return thisYearQ3S;
        }

        public void setThisYearQ3S(double thisYearQ3S) {
            this.thisYearQ3S = thisYearQ3S;
        }

        public double getThisYearQ3D() {
            return thisYearQ3D;
        }

        public void setThisYearQ3D(double thisYearQ3D) {
            this.thisYearQ3D = thisYearQ3D;
        }

        public double getThisYearQ4S() {
            return thisYearQ4S;
        }

        public void setThisYearQ4S(double thisYearQ4S) {
            this.thisYearQ4S = thisYearQ4S;
        }

        public double getThisYearQ4D() {
            return thisYearQ4D;
        }

        public void setThisYearQ4D(double thisYearQ4D) {
            this.thisYearQ4D = thisYearQ4D;
        }

        public double getNextYearQ1S() {
            return nextYearQ1S;
        }

        public void setNextYearQ1S(double nextYearQ1S) {
            this.nextYearQ1S = nextYearQ1S;
        }

        public double getNextYearQ1D() {
            return nextYearQ1D;
        }

        public void setNextYearQ1D(double nextYearQ1D) {
            this.nextYearQ1D = nextYearQ1D;
        }

        public double getNextYearQ2S() {
            return nextYearQ2S;
        }

        public void setNextYearQ2S(double nextYearQ2S) {
            this.nextYearQ2S = nextYearQ2S;
        }

        public double getNextYearQ2D() {
            return nextYearQ2D;
        }

        public void setNextYearQ2D(double nextYearQ2D) {
            this.nextYearQ2D = nextYearQ2D;
        }

        public double getNextYearQ3S() {
            return nextYearQ3S;
        }

        public void setNextYearQ3S(double nextYearQ3S) {
            this.nextYearQ3S = nextYearQ3S;
        }

        public double getNextYearQ3D() {
            return nextYearQ3D;
        }

        public void setNextYearQ3D(double nextYearQ3D) {
            this.nextYearQ3D = nextYearQ3D;
        }

        public double getNextYearQ4S() {
            return nextYearQ4S;
        }

        public void setNextYearQ4S(double nextYearQ4S) {
            this.nextYearQ4S = nextYearQ4S;
        }

        public double getNextYearQ4D() {
            return nextYearQ4D;
        }

        public void setNextYearQ4D(double nextYearQ4D) {
            this.nextYearQ4D = nextYearQ4D;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }


        public String getBusinessTypeKey() {
            return businessTypeKey;
        }

        public void setBusinessTypeKey(String businessTypeKey) {
            this.businessTypeKey = businessTypeKey;
        }

        public String getBusinessTypeValue() {
            return businessTypeValue;
        }

        public void setBusinessTypeValue(String businessTypeValue) {
            this.businessTypeValue = businessTypeValue;
        }


    }
}
