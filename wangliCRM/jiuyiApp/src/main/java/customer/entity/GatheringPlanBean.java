package customer.entity;

import java.math.BigDecimal;
import java.util.List;

public class GatheringPlanBean {

    /**
     * content : [{"createdBy":"15058305380","createdDate":"2018-05-23 16:18:16","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-28 14:47:46","id":5,"deleted":false,"sort":10,"fromClientType":null,"member":null,"year":2018,"month":5,"office":null,"operator":null,"status":"APPROVALED","quantity":20,"adjustedQuantity":5.33,"actualShip":"35.1%","issuedQuantity":3.3,"undoneReasonType":null,"undoneRemark":null}]
     * last : true
     * totalElements : 1
     * totalPages : 1
     * number : 0
     * size : 2
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","descending":false,"ascending":true}]
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
        /**
         * createdBy : 15058305380
         * createdDate : 2018-05-23 16:18:16
         * lastModifiedBy : 15058305380
         * lastModifiedDate : 2018-05-28 14:47:46
         * id : 5
         * deleted : false
         * sort : 10
         * fromClientType : null
         * member : null
         * year : 2018
         * month : 5
         * office : null
         * operator : null
         * status : APPROVALED
         * quantity : 20
         * adjustedQuantity : 5.33
         * actualShip : 35.1%
         * issuedQuantity : 3.3
         * undoneReasonType : null
         * undoneRemark : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private MemberCenterBean member;
        private int year;
        private int month;
        private Object office;
        private Object operator;
        private String status;
        private double quantity;
        private double adjustedQuantity;
        private String actualShip;
        private double issuedQuantity;
        private Object undoneReasonType;
        private Object undoneRemark;

        public BigDecimal getZeroToAccount() {
            return zeroToAccount;
        }

        public void setZeroToAccount(BigDecimal zeroToAccount) {
            this.zeroToAccount = zeroToAccount;
        }

        public BigDecimal getAccountToNinety() {
            return accountToNinety;
        }

        public void setAccountToNinety(BigDecimal accountToNinety) {
            this.accountToNinety = accountToNinety;
        }

        public BigDecimal getMoreThanNinety() {
            return moreThanNinety;
        }

        public void setMoreThanNinety(BigDecimal moreThanNinety) {
            this.moreThanNinety = moreThanNinety;
        }

        public BigDecimal getReceivedAmount() {
            return receivedAmount;
        }

        public void setReceivedAmount(BigDecimal receivedAmount) {
            this.receivedAmount = receivedAmount;
        }

        public BigDecimal getPlanTotalAmount() {
            return planTotalAmount;
        }

        public void setPlanTotalAmount(BigDecimal planTotalAmount) {
            this.planTotalAmount = planTotalAmount;
        }

        private BigDecimal zeroToAccount; //0-帐期之间的
        private BigDecimal accountToNinety; //帐期-90天的金额
        private BigDecimal moreThanNinety; //90天以上
        private BigDecimal receivedAmount;
        private BigDecimal planTotalAmount;


        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        private String remark;

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

        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
        }

//        public MemberCenterBean.MemberBean getMember() {
//            return member;
//        }
//
//        public void setMember(MemberCenterBean.MemberBean member) {
//            this.member = member;
//        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public Object getOffice() {
            return office;
        }

        public void setOffice(Object office) {
            this.office = office;
        }

        public Object getOperator() {
            return operator;
        }

        public void setOperator(Object operator) {
            this.operator = operator;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public double getAdjustedQuantity() {
            return adjustedQuantity;
        }

        public void setAdjustedQuantity(double adjustedQuantity) {
            this.adjustedQuantity = adjustedQuantity;
        }

        public String getActualShip() {
            return actualShip;
        }

        public void setActualShip(String actualShip) {
            this.actualShip = actualShip;
        }

        public double getIssuedQuantity() {
            return issuedQuantity;
        }

        public void setIssuedQuantity(double issuedQuantity) {
            this.issuedQuantity = issuedQuantity;
        }

        public Object getUndoneReasonType() {
            return undoneReasonType;
        }

        public void setUndoneReasonType(Object undoneReasonType) {
            this.undoneReasonType = undoneReasonType;
        }

        public Object getUndoneRemark() {
            return undoneRemark;
        }

        public void setUndoneRemark(Object undoneRemark) {
            this.undoneRemark = undoneRemark;
        }
    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : id
         * ignoreCase : false
         * nullHandling : NATIVE
         * descending : false
         * ascending : true
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean descending;
        private boolean ascending;

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

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }
    }

    /*public static class MemberBean {
        *//**
         * id : 20
         *//*

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }*/
}
