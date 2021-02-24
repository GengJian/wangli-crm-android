package customer.entity;

import java.util.List;

public class TradeinvoiceBean {

    /**
     * content : [{"createdBy":"18124124","createdDate":"2018-05-16","lastModifiedBy":"18405863019","lastModifiedDate":"2018-05-16","id":2,"deleted":null,"sort":null,"fromClientType":null,"number":"ys20180516","goldenTaxNumber":"asda2","billDate":null,"amount":300,"status":"READY","customerSinger":"沈谋人","signDate":"2018-05-16","expressCompanyName":"顺丰","expressNumber":"889988","member":null,"salesBillingItems":null,"salesBillingTracking":null},{"createdBy":"18405863019","createdDate":"2018-05-16","lastModifiedBy":"18405863019","lastModifiedDate":"2018-05-16","id":1,"deleted":null,"sort":null,"fromClientType":null,"number":"ys20180516","goldenTaxNumber":"zzzzzz","billDate":null,"amount":null,"status":"HANDLING","customerSinger":null,"signDate":null,"expressCompanyName":"顺丰","expressNumber":"889988","member":null,"salesBillingItems":null,"salesBillingTracking":null}]
     * last : true
     * totalElements : 2
     * totalPages : 1
     * number : 0
     * size : 10
     * sort : [{"direction":"DESC","property":"lastModifiedDate","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * first : true
     * numberOfElements : 2
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
         * createdBy : 18124124
         * createdDate : 2018-05-16
         * lastModifiedBy : 18405863019
         * lastModifiedDate : 2018-05-16
         * id : 2
         * deleted : null
         * sort : null
         * fromClientType : null
         * number : ys20180516
         * goldenTaxNumber : asda2
         * billDate : null
         * amount : 300
         * status : READY
         * customerSinger : 沈谋人
         * signDate : 2018-05-16
         * expressCompanyName : 顺丰
         * expressNumber : 889988
         * member : null
         * salesBillingItems : null
         * salesBillingTracking : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private Object deleted;
        private Object sort;
        private Object fromClientType;
        private String number;
        private String goldenTaxNumber;
        private Object billDate;
        private double amount;
        private String status;
        private String customerSinger;
        private String signDate;
        private String expressCompanyName;
        private String expressNumber;
        private Object member;
        private Object salesBillingItems;
        private Object salesBillingTracking;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        private double price;

        public String getStatusDesp() {
            return statusDesp;
        }

        public void setStatusDesp(String statusDesp) {
            this.statusDesp = statusDesp;
        }

        private String statusDesp;

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

        public String getGoldenTaxNumber() {
            return goldenTaxNumber;
        }

        public void setGoldenTaxNumber(String goldenTaxNumber) {
            this.goldenTaxNumber = goldenTaxNumber;
        }

        public Object getBillDate() {
            return billDate;
        }

        public void setBillDate(Object billDate) {
            this.billDate = billDate;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCustomerSinger() {
            return customerSinger;
        }

        public void setCustomerSinger(String customerSinger) {
            this.customerSinger = customerSinger;
        }

        public String getSignDate() {
            return signDate;
        }

        public void setSignDate(String signDate) {
            this.signDate = signDate;
        }

        public String getExpressCompanyName() {
            return expressCompanyName;
        }

        public void setExpressCompanyName(String expressCompanyName) {
            this.expressCompanyName = expressCompanyName;
        }

        public String getExpressNumber() {
            return expressNumber;
        }

        public void setExpressNumber(String expressNumber) {
            this.expressNumber = expressNumber;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public Object getSalesBillingItems() {
            return salesBillingItems;
        }

        public void setSalesBillingItems(Object salesBillingItems) {
            this.salesBillingItems = salesBillingItems;
        }

        public Object getSalesBillingTracking() {
            return salesBillingTracking;
        }

        public void setSalesBillingTracking(Object salesBillingTracking) {
            this.salesBillingTracking = salesBillingTracking;
        }
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
