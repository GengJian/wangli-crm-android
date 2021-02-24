package customer.entity;

import com.jiuyi.model.DictResultBean;

import java.util.List;

public class TradebankstatementBean {


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
        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private String accountNumber;
        private DictResultBean.ContentBean status;
        private Object sendDate;
        private Object receiveDate;
        private MemberBeanID member;
        private Object operator;
        private String yearMonth;
        private double currentMonthOrderQuantity;
        private double currentMonthOrderAmount;
        private double lastMonthRemainAmount;
        private double currentMonthPayAmount;
        private double currentMonthOweAmount;
        private Object company;
        private Object balanceAccountItemSet;
        private List<?> optionGroup;

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

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public DictResultBean.ContentBean getStatus() {
            return status;
        }

        public void setStatus(DictResultBean.ContentBean status) {
            this.status = status;
        }

        public Object getSendDate() {
            return sendDate;
        }

        public void setSendDate(Object sendDate) {
            this.sendDate = sendDate;
        }

        public Object getReceiveDate() {
            return receiveDate;
        }

        public void setReceiveDate(Object receiveDate) {
            this.receiveDate = receiveDate;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public Object getOperator() {
            return operator;
        }

        public void setOperator(Object operator) {
            this.operator = operator;
        }

        public String getYearMonth() {
            return yearMonth;
        }

        public void setYearMonth(String yearMonth) {
            this.yearMonth = yearMonth;
        }

        public Double getCurrentMonthOrderQuantity() {
            return currentMonthOrderQuantity;
        }

        public void setCurrentMonthOrderQuantity(Double currentMonthOrderQuantity) {
            this.currentMonthOrderQuantity = currentMonthOrderQuantity;
        }

        public Double getCurrentMonthOrderAmount() {
            return currentMonthOrderAmount;
        }

        public void setCurrentMonthOrderAmount(Double currentMonthOrderAmount) {
            this.currentMonthOrderAmount = currentMonthOrderAmount;
        }

        public double getLastMonthRemainAmount() {
            return lastMonthRemainAmount;
        }

        public void setLastMonthRemainAmount(double lastMonthRemainAmount) {
            this.lastMonthRemainAmount = lastMonthRemainAmount;
        }

        public Double getCurrentMonthPayAmount() {
            return currentMonthPayAmount;
        }

        public void setCurrentMonthPayAmount(Double currentMonthPayAmount) {
            this.currentMonthPayAmount = currentMonthPayAmount;
        }

        public double getCurrentMonthOweAmount() {
            return currentMonthOweAmount;
        }

        public void setCurrentMonthOweAmount(double currentMonthOweAmount) {
            this.currentMonthOweAmount = currentMonthOweAmount;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public Object getBalanceAccountItemSet() {
            return balanceAccountItemSet;
        }

        public void setBalanceAccountItemSet(Object balanceAccountItemSet) {
            this.balanceAccountItemSet = balanceAccountItemSet;
        }

        public List<?> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<?> optionGroup) {
            this.optionGroup = optionGroup;
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : createdDate
         * ignoreCase : false
         * nullHandling : NATIVE
         * descending : true
         * ascending : false
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
}
