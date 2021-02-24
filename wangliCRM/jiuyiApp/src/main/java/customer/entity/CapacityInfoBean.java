package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CapacityInfoBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/4 19:07
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/4 1.00 初始版本
 * ****************************************************************
 */
public class CapacityInfoBean {

    /**
     * content : [{"createdBy":"1022654","createdDate":"2019-01-04","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-04","id":4,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂","member":null,"productType":"a","factoryName":"b","workshop":"c","theoryYield":1,"actualYield":1,"infoDate":"2019-01-04","remark":"222","attachmentList":null,"operator":null}]
     * last : true
     * totalElements : 1
     * totalPages : 1
     * number : 0
     * size : 5
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * numberOfElements : 1
     * first : true
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int number;
    private int size;
    private int numberOfElements;
    private boolean first;
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

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
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
        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
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

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getFactoryName() {
            return factoryName;
        }

        public void setFactoryName(String factoryName) {
            this.factoryName = factoryName;
        }

        public String getWorkshop() {
            return workshop;
        }

        public void setWorkshop(String workshop) {
            this.workshop = workshop;
        }

        public double getTheoryYield() {
            return theoryYield;
        }

        public void setTheoryYield(double theoryYield) {
            this.theoryYield = theoryYield;
        }

        public double getActualYield() {
            return actualYield;
        }

        public void setActualYield(double actualYield) {
            this.actualYield = actualYield;
        }

        public String getInfoDate() {
            return infoDate;
        }

        public void setInfoDate(String infoDate) {
            this.infoDate = infoDate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }

        /**
         * createdBy : 1022654
         * createdDate : 2019-01-04
         * lastModifiedBy : 1022654
         * lastModifiedDate : 2019-01-04
         * id : 4
         * deleted : false
         * sort : 10
         * fromClientType : null
         * optionGroup : []
         * businessTypeKey : silicon_factory
         * businessTypeValue : 硅料厂
         * member : null
         * productType : a
         * factoryName : b
         * workshop : c
         * theoryYield : 1
         * actualYield : 1
         * infoDate : 2019-01-04
         * remark : 222
         * attachmentList : null
         * operator : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;

        private String fromClientType;
        private String businessTypeKey;
        private String businessTypeValue;
        private MemberBeanID member;
        private String productType;
        private String factoryName;
        private String workshop;
        private double theoryYield;
        private double actualYield;
        private String infoDate;
        private String remark;
        private List<AttachmentBean> attachmentList;



    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : id
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
