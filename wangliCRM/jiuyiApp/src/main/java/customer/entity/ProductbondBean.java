package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:ProductbondBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/11/14 18:03
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/11/14 1.00 初始版本
 * ****************************************************************
 */
public class ProductbondBean {

    private boolean last;
    private int totalElements;
    private int totalPages;
    private boolean first;
    private int numberOfElements;
    private int size;
    private int number;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private Object fromClientType;
        private Object member;
        private String bondName;
        private String bondNum;
        private long bondStopTime;
        private String bondStopTimeStr;
        private String bondTimeLimit;
        private Object bondTradeTime;
        private String bondType;
        private String calInterestType;
        private long createTime;
        private String creditRatingGov;
        private String debtRating;
        private String escrowAgent;
        private Object exeRightTime;
        private String exeRightTimeStr;
        private String exeRightType;
        private double faceInterestRate;
        private double faceValue;
        private String flowRange;
        private Object tycId;
        private double interestDiff;
        private double issuedPrice;
        private String payInterestHZ;
        private double planIssuedQuantity;
        private long publishExpireTime;
        private String publishExpireTimeStr;
        private long publishTime;
        private String publishTimeStr;
        private String publisherName;
        private double realIssuedQuantity;
        private double refInterestRate;
        private String remark;
        private long startCalInterestTime;
        private String startCalInterestTimeStr;
        private String tip;
        private long updateTime;
        private String updateTimeStr;
        private String tycInterfaceName;

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

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(Object fromClientType) {
            this.fromClientType = fromClientType;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public String getBondName() {
            return bondName;
        }

        public void setBondName(String bondName) {
            this.bondName = bondName;
        }

        public String getBondNum() {
            return bondNum;
        }

        public void setBondNum(String bondNum) {
            this.bondNum = bondNum;
        }

        public long getBondStopTime() {
            return bondStopTime;
        }

        public void setBondStopTime(long bondStopTime) {
            this.bondStopTime = bondStopTime;
        }

        public String getBondStopTimeStr() {
            return bondStopTimeStr;
        }

        public void setBondStopTimeStr(String bondStopTimeStr) {
            this.bondStopTimeStr = bondStopTimeStr;
        }

        public String getBondTimeLimit() {
            return bondTimeLimit;
        }

        public void setBondTimeLimit(String bondTimeLimit) {
            this.bondTimeLimit = bondTimeLimit;
        }

        public Object getBondTradeTime() {
            return bondTradeTime;
        }

        public void setBondTradeTime(Object bondTradeTime) {
            this.bondTradeTime = bondTradeTime;
        }

        public String getBondType() {
            return bondType;
        }

        public void setBondType(String bondType) {
            this.bondType = bondType;
        }

        public String getCalInterestType() {
            return calInterestType;
        }

        public void setCalInterestType(String calInterestType) {
            this.calInterestType = calInterestType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreditRatingGov() {
            return creditRatingGov;
        }

        public void setCreditRatingGov(String creditRatingGov) {
            this.creditRatingGov = creditRatingGov;
        }

        public String getDebtRating() {
            return debtRating;
        }

        public void setDebtRating(String debtRating) {
            this.debtRating = debtRating;
        }

        public String getEscrowAgent() {
            return escrowAgent;
        }

        public void setEscrowAgent(String escrowAgent) {
            this.escrowAgent = escrowAgent;
        }

        public Object getExeRightTime() {
            return exeRightTime;
        }

        public void setExeRightTime(Object exeRightTime) {
            this.exeRightTime = exeRightTime;
        }

        public String getExeRightTimeStr() {
            return exeRightTimeStr;
        }

        public void setExeRightTimeStr(String exeRightTimeStr) {
            this.exeRightTimeStr = exeRightTimeStr;
        }

        public String getExeRightType() {
            return exeRightType;
        }

        public void setExeRightType(String exeRightType) {
            this.exeRightType = exeRightType;
        }

        public double getFaceInterestRate() {
            return faceInterestRate;
        }

        public void setFaceInterestRate(double faceInterestRate) {
            this.faceInterestRate = faceInterestRate;
        }

        public double getFaceValue() {
            return faceValue;
        }

        public void setFaceValue(double faceValue) {
            this.faceValue = faceValue;
        }

        public String getFlowRange() {
            return flowRange;
        }

        public void setFlowRange(String flowRange) {
            this.flowRange = flowRange;
        }

        public Object getTycId() {
            return tycId;
        }

        public void setTycId(Object tycId) {
            this.tycId = tycId;
        }

        public double getInterestDiff() {
            return interestDiff;
        }

        public void setInterestDiff(double interestDiff) {
            this.interestDiff = interestDiff;
        }

        public double getIssuedPrice() {
            return issuedPrice;
        }

        public void setIssuedPrice(double issuedPrice) {
            this.issuedPrice = issuedPrice;
        }

        public String getPayInterestHZ() {
            return payInterestHZ;
        }

        public void setPayInterestHZ(String payInterestHZ) {
            this.payInterestHZ = payInterestHZ;
        }

        public double getPlanIssuedQuantity() {
            return planIssuedQuantity;
        }

        public void setPlanIssuedQuantity(double planIssuedQuantity) {
            this.planIssuedQuantity = planIssuedQuantity;
        }

        public long getPublishExpireTime() {
            return publishExpireTime;
        }

        public void setPublishExpireTime(long publishExpireTime) {
            this.publishExpireTime = publishExpireTime;
        }

        public String getPublishExpireTimeStr() {
            return publishExpireTimeStr;
        }

        public void setPublishExpireTimeStr(String publishExpireTimeStr) {
            this.publishExpireTimeStr = publishExpireTimeStr;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getPublishTimeStr() {
            return publishTimeStr;
        }

        public void setPublishTimeStr(String publishTimeStr) {
            this.publishTimeStr = publishTimeStr;
        }

        public String getPublisherName() {
            return publisherName;
        }

        public void setPublisherName(String publisherName) {
            this.publisherName = publisherName;
        }

        public double getRealIssuedQuantity() {
            return realIssuedQuantity;
        }

        public void setRealIssuedQuantity(double realIssuedQuantity) {
            this.realIssuedQuantity = realIssuedQuantity;
        }

        public double getRefInterestRate() {
            return refInterestRate;
        }

        public void setRefInterestRate(double refInterestRate) {
            this.refInterestRate = refInterestRate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getStartCalInterestTime() {
            return startCalInterestTime;
        }

        public void setStartCalInterestTime(long startCalInterestTime) {
            this.startCalInterestTime = startCalInterestTime;
        }

        public String getStartCalInterestTimeStr() {
            return startCalInterestTimeStr;
        }

        public void setStartCalInterestTimeStr(String startCalInterestTimeStr) {
            this.startCalInterestTimeStr = startCalInterestTimeStr;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTimeStr() {
            return updateTimeStr;
        }

        public void setUpdateTimeStr(String updateTimeStr) {
            this.updateTimeStr = updateTimeStr;
        }

        public String getTycInterfaceName() {
            return tycInterfaceName;
        }

        public void setTycInterfaceName(String tycInterfaceName) {
            this.tycInterfaceName = tycInterfaceName;
        }

        public String getBondTradeTimeStr() {
            return bondTradeTimeStr;
        }

        public void setBondTradeTimeStr(String bondTradeTimeStr) {
            this.bondTradeTimeStr = bondTradeTimeStr;
        }

        public Object getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(Object attachmentList) {
            this.attachmentList = attachmentList;
        }

        private String bondTradeTimeStr;
        private Object attachmentList;



    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : lastModifiedDate
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : false
         * descending : true
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
