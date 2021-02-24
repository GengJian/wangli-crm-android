package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CtmReportBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/4 19:44
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/4 1.00 初始版本
 * ****************************************************************
 */
public class CtmReportBean {


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
        private String remark;

        private String sapinvoiceNumber;
        private String batteryEfficient;
        private String memberFactory;

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSapinvoiceNumber() {
            return sapinvoiceNumber;
        }

        public void setSapinvoiceNumber(String sapinvoiceNumber) {
            this.sapinvoiceNumber = sapinvoiceNumber;
        }

        public String getBatteryEfficient() {
            return batteryEfficient;
        }

        public void setBatteryEfficient(String batteryEfficient) {
            this.batteryEfficient = batteryEfficient;
        }

        public String getMemberFactory() {
            return memberFactory;
        }

        public void setMemberFactory(String memberFactory) {
            this.memberFactory = memberFactory;
        }

        public String getArrivalBatch() {
            return arrivalBatch;
        }

        public void setArrivalBatch(String arrivalBatch) {
            this.arrivalBatch = arrivalBatch;
        }

        public String getBom() {
            return bom;
        }

        public void setBom(String bom) {
            this.bom = bom;
        }

        public String getStandardBoard() {
            return standardBoard;
        }

        public void setStandardBoard(String standardBoard) {
            this.standardBoard = standardBoard;
        }

        public String getProcessType() {
            return processType;
        }

        public void setProcessType(String processType) {
            this.processType = processType;
        }

        public String getFinishedCode() {
            return finishedCode;
        }

        public void setFinishedCode(String finishedCode) {
            this.finishedCode = finishedCode;
        }

        public int getPutCount() {
            return putCount;
        }

        public void setPutCount(int putCount) {
            this.putCount = putCount;
        }

        public String getBoardtype() {
            return boardtype;
        }

        public void setBoardtype(String boardtype) {
            this.boardtype = boardtype;
        }

        public double getTheoryPower() {
            return theoryPower;
        }

        public void setTheoryPower(double theoryPower) {
            this.theoryPower = theoryPower;
        }

        public double getActualPower() {
            return actualPower;
        }

        public void setActualPower(double actualPower) {
            this.actualPower = actualPower;
        }

        public String getCtm() {
            return ctm;
        }

        public void setCtm(String ctm) {
            this.ctm = ctm;
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

        private String arrivalBatch;
        private String bom;
        private String standardBoard;
        private String processType;
        private String finishedCode;
        private int putCount;
        private String boardtype;
        private double theoryPower;
        private double actualPower;
        private String ctm;
        private String putDate;
        private String productType;


    }
}
