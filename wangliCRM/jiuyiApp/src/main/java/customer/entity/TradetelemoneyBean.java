package customer.entity;

import java.util.List;

public class TradetelemoneyBean {

    /**
     * content : [{"createdBy":"1777","createdDate":"2018-05-18","lastModifiedBy":null,"lastModifiedDate":null,"id":2,"deleted":null,"sort":null,"fromClientType":null,"number":"666","companyCode":"3","companyName":"麦当劳","receiptDate":"2018-05-22T00:00:00.000+0800","department":null,"memberSapNumber":"7788","memberName":"大冬瓜","operatorSapNumber":"2222","operatorName":"蚂蚁","receiptAmount":1700,"receiptStatus":"ALREADY_ARRIVED","receiptType":"WIRE_TRANSFER","remark":"洋葱多点","member":null,"paidDate":null,"claimedDate":null,"arrivedDate":null,"bookedDate":"2018-05-30T00:00:00.000+0800"}]
     * last : true
     * totalElements : 1
     * totalPages : 1
     * number : 0
     * size : 5
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * first : true
     * numberOfElements : 1
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
         * createdBy : 1777
         * createdDate : 2018-05-18
         * lastModifiedBy : null
         * lastModifiedDate : null
         * id : 2
         * deleted : null
         * sort : null
         * fromClientType : null
         * number : 666
         * companyCode : 3
         * companyName : 麦当劳
         * receiptDate : 2018-05-22T00:00:00.000+0800
         * department : null
         * memberSapNumber : 7788
         * memberName : 大冬瓜
         * operatorSapNumber : 2222
         * operatorName : 蚂蚁
         * receiptAmount : 1700
         * receiptStatus : ALREADY_ARRIVED
         * receiptType : WIRE_TRANSFER
         * remark : 洋葱多点
         * member : null
         * paidDate : null
         * claimedDate : null
         * arrivedDate : null
         * bookedDate : 2018-05-30T00:00:00.000+0800
         */

        private String createdBy;
        private String createdDate;
        private Object lastModifiedBy;
        private Object lastModifiedDate;
        private long id;
        private Object deleted;
        private Object sort;
        private Object fromClientType;
        private String number;
        private String companyCode;
        private String companyName;
        private String receiptDate;
        private Object department;
        private String memberSapNumber;
        private String memberName;
        private String operatorSapNumber;
        private String operatorName;
        private double receiptAmount;
        private String receiptStatus;
        private String receiptType;
        private String remark;
        private Object member;
        private Object paidDate;
        private Object claimedDate;
        private Object arrivedDate;
        private String bookedDate;

        public String getZtext() {
            return ztext;
        }

        public void setZtext(String ztext) {
            this.ztext = ztext;
        }

        private String ztext;

        public String getZtype() {
            return ztype;
        }

        public void setZtype(String ztype) {
            this.ztype = ztype;
        }

        public String getGttyp() {
            return gttyp;
        }

        public void setGttyp(String gttyp) {
            this.gttyp = gttyp;
        }

        private String ztype;
        private String gttyp;

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

        public Object getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(Object lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public Object getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(Object lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Object getDeleted() {
            return deleted;
        }

        public void setDeleted(Object deleted) {
            this.deleted = deleted;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public Object getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(Object fromClientType) {
            this.fromClientType = fromClientType;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getReceiptDate() {
            return receiptDate;
        }

        public void setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
        }

        public Object getDepartment() {
            return department;
        }

        public void setDepartment(Object department) {
            this.department = department;
        }

        public String getMemberSapNumber() {
            return memberSapNumber;
        }

        public void setMemberSapNumber(String memberSapNumber) {
            this.memberSapNumber = memberSapNumber;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getOperatorSapNumber() {
            return operatorSapNumber;
        }

        public void setOperatorSapNumber(String operatorSapNumber) {
            this.operatorSapNumber = operatorSapNumber;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public double getReceiptAmount() {
            return receiptAmount;
        }

        public void setReceiptAmount(double receiptAmount) {
            this.receiptAmount = receiptAmount;
        }

        public String getReceiptStatus() {
            return receiptStatus;
        }

        public void setReceiptStatus(String receiptStatus) {
            this.receiptStatus = receiptStatus;
        }

        public String getReceiptType() {
            return receiptType;
        }

        public void setReceiptType(String receiptType) {
            this.receiptType = receiptType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public Object getPaidDate() {
            return paidDate;
        }

        public void setPaidDate(Object paidDate) {
            this.paidDate = paidDate;
        }

        public Object getClaimedDate() {
            return claimedDate;
        }

        public void setClaimedDate(Object claimedDate) {
            this.claimedDate = claimedDate;
        }

        public Object getArrivedDate() {
            return arrivedDate;
        }

        public void setArrivedDate(Object arrivedDate) {
            this.arrivedDate = arrivedDate;
        }

        public String getBookedDate() {
            return bookedDate;
        }

        public void setBookedDate(String bookedDate) {
            this.bookedDate = bookedDate;
        }
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
