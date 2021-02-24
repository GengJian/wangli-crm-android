package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:PutIntoProductBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/4 19:41
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/4 1.00 初始版本
 * ****************************************************************
 */
public class PutIntoProductBean {


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
        private MemberBeanID member;
        private String businessTypeKey;
        private String businessTypeValue;
        private String supervisionInfo;
        private String sapInoviceNumber;
        private int putBatteryCount;
        private int totalDamageCount;
        private double totalDamageRatio;
        private String finishedCode;
        private String memberOrder;
        private String bomInfo;
        private String putFactory;
        private String putBatch;
        private int outputCount;
        private int ydamageCount;
        private double ydamageRatio;

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

        public String getSupervisionInfo() {
            return supervisionInfo;
        }

        public void setSupervisionInfo(String supervisionInfo) {
            this.supervisionInfo = supervisionInfo;
        }

        public String getSapInoviceNumber() {
            return sapInoviceNumber;
        }

        public void setSapInoviceNumber(String sapInoviceNumber) {
            this.sapInoviceNumber = sapInoviceNumber;
        }

        public int getPutBatteryCount() {
            return putBatteryCount;
        }

        public void setPutBatteryCount(int putBatteryCount) {
            this.putBatteryCount = putBatteryCount;
        }

        public int getTotalDamageCount() {
            return totalDamageCount;
        }

        public void setTotalDamageCount(int totalDamageCount) {
            this.totalDamageCount = totalDamageCount;
        }

        public double getTotalDamageRatio() {
            return totalDamageRatio;
        }

        public void setTotalDamageRatio(double totalDamageRatio) {
            this.totalDamageRatio = totalDamageRatio;
        }

        public String getFinishedCode() {
            return finishedCode;
        }

        public void setFinishedCode(String finishedCode) {
            this.finishedCode = finishedCode;
        }

        public String getMemberOrder() {
            return memberOrder;
        }

        public void setMemberOrder(String memberOrder) {
            this.memberOrder = memberOrder;
        }

        public String getBomInfo() {
            return bomInfo;
        }

        public void setBomInfo(String bomInfo) {
            this.bomInfo = bomInfo;
        }

        public String getPutFactory() {
            return putFactory;
        }

        public void setPutFactory(String putFactory) {
            this.putFactory = putFactory;
        }

        public String getPutBatch() {
            return putBatch;
        }

        public void setPutBatch(String putBatch) {
            this.putBatch = putBatch;
        }

        public int getOutputCount() {
            return outputCount;
        }

        public void setOutputCount(int outputCount) {
            this.outputCount = outputCount;
        }

        public int getYdamageCount() {
            return ydamageCount;
        }

        public void setYdamageCount(int ydamageCount) {
            this.ydamageCount = ydamageCount;
        }

        public double getYdamageRatio() {
            return ydamageRatio;
        }

        public void setYdamageRatio(double ydamageRatio) {
            this.ydamageRatio = ydamageRatio;
        }

        public int getGdamageCount() {
            return gdamageCount;
        }

        public void setGdamageCount(int gdamageCount) {
            this.gdamageCount = gdamageCount;
        }

        public double getGdamageRatio() {
            return gdamageRatio;
        }

        public void setGdamageRatio(double gdamageRatio) {
            this.gdamageRatio = gdamageRatio;
        }

        public int getReworkCount() {
            return reworkCount;
        }

        public void setReworkCount(int reworkCount) {
            this.reworkCount = reworkCount;
        }

        public double getReworkRatio() {
            return reworkRatio;
        }

        public void setReworkRatio(double reworkRatio) {
            this.reworkRatio = reworkRatio;
        }

        public String getPutDate() {
            return putDate;
        }

        public void setPutDate(String putDate) {
            this.putDate = putDate;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        private int gdamageCount;
        private double gdamageRatio;
        private int reworkCount;
        private double reworkRatio;
        private String putDate;
        private String productType;


    }
}
