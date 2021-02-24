package customer.entity;

import com.jiuyi.model.DictResultBean;

import java.util.List;

public class ProductmaterialBean {


    /**
     * content : [{"createdBy":"admin","createdDate":"2018-04-25 14:01:23","lastModifiedBy":"admin","lastModifiedDate":"2018-04-25 14:01:23","id":4,"deleted":false,"sort":10,"fromClientType":null,"type":{"createdBy":"admin","createdDate":"2018-04-13 09:48:08","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:48:08","id":6,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"RECEIVE","value":"催款小助手","modifiable":null,"builtIn":true,"remark":"催款小助手"},"typeValue":null,"name":"原料四","annualPurchaseVolume":109.55,"monthlyUsage":8.03,"purchaseVolumeNextMonth":3,"member":null,"attachments":null},{"createdBy":"admin","createdDate":"2018-04-25 14:05:17","lastModifiedBy":"admin","lastModifiedDate":"2018-04-25 14:05:17","id":5,"deleted":false,"sort":10,"fromClientType":null,"type":{"createdBy":"admin","createdDate":"2018-04-13 09:49:11","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:11","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"MARKET","value":"营销小助手","modifiable":null,"builtIn":true,"remark":"营销小助手"},"typeValue":null,"name":"修改原料五","annualPurchaseVolume":200,"monthlyUsage":15,"purchaseVolumeNextMonth":30,"member":null,"attachments":null}]
     * totalElements : 5
     * last : false
     * totalPages : 3
     * number : 1
     * size : 2
     * first : false
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * numberOfElements : 2
     */

    private int totalElements;
    private boolean last;
    private int totalPages;
    private int number;
    private int size;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;
    private List<SortBean> sort;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
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
         * createdBy : admin
         * createdDate : 2018-04-25 14:01:23
         * lastModifiedBy : admin
         * lastModifiedDate : 2018-04-25 14:01:23
         * id : 4
         * deleted : false
         * sort : 10
         * fromClientType : null
         * type : {"createdBy":"admin","createdDate":"2018-04-13 09:48:08","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:48:08","id":6,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"RECEIVE","value":"催款小助手","modifiable":null,"builtIn":true,"remark":"催款小助手"}
         * typeValue : null
         * name : 原料四
         * annualPurchaseVolume : 109.55
         * monthlyUsage : 8.03
         * purchaseVolumeNextMonth : 3
         * member : null
         * attachments : null
         */

        private String createdBy;
        private String createdDate;

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

        public DictResultBean.ContentBean getType() {
            return type;
        }

        public void setType(DictResultBean.ContentBean type) {
            this.type = type;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getAnnualPurchaseVolume() {
            return annualPurchaseVolume;
        }

        public void setAnnualPurchaseVolume(double annualPurchaseVolume) {
            this.annualPurchaseVolume = annualPurchaseVolume;
        }

        public double getMonthlyUsage() {
            return monthlyUsage;
        }

        public void setMonthlyUsage(double monthlyUsage) {
            this.monthlyUsage = monthlyUsage;
        }

        public double getPurchaseVolumeNextMonth() {
            return purchaseVolumeNextMonth;
        }

        public void setPurchaseVolumeNextMonth(double purchaseVolumeNextMonth) {
            this.purchaseVolumeNextMonth = purchaseVolumeNextMonth;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public List<AttachmentBean> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<AttachmentBean> attachments) {
            this.attachments = attachments;
        }

        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private String typeValue;
        private DictResultBean.ContentBean type;
        private String name;
        private double annualPurchaseVolume;
        private double monthlyUsage;
        private double purchaseVolumeNextMonth;
        private MemberBeanID member;
        private List<AttachmentBean> attachments;

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
