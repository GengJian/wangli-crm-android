package customer.entity;

import java.util.List;

public class ProductdynamicBean {

    /**
     * content : [{"createdBy":"admin","createdDate":"2018-04-24 19:25:11","lastModifiedBy":"admin","lastModifiedDate":"2018-04-24 19:25:11","id":4,"deleted":false,"sort":10,"fromClientType":null,"customerId":null,"factory":{"createdBy":"admin","createdDate":"2018-04-23 17:02:49","lastModifiedBy":"admin","lastModifiedDate":"2018-04-23 17:02:49","id":1,"deleted":false,"sort":10,"fromClientType":null,"member":null,"name":"工厂一","defaults":false,"address":"西湖区文三路"},"equipment":{"createdBy":"15058305380","createdDate":"2018-06-06 13:55:19","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-06 13:55:19","id":15,"deleted":false,"sort":10,"fromClientType":null,"member":null,"factory":{"createdBy":"15058305380","createdDate":"2018-06-06 10:35:18","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-06 10:35:18","id":8,"deleted":false,"sort":10,"fromClientType":null,"member":null,"name":"工厂测试","defaults":false,"address":"西湖区教工路"},"field":{"createdBy":"admin","createdDate":"2018-04-13 09:49:11","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:11","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"MARKET","value":"营销小助手","modifiable":null,"builtIn":true,"remark":"营销小助手"},"fieldValue":null,"type":{"createdBy":"admin","createdDate":"2018-04-13 09:49:27","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:27","id":9,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"PERFORMANCE","value":"绩效小助手","modifiable":null,"builtIn":true,"remark":"绩效小助手"},"typeValue":null,"name":"设备三号","quantity":10,"remark":null,"brands":"温州制造","size":"15英寸","purchaseYears":5,"attachments":null},"year":2017,"month":12,"bootedQuantity":3,"bootedRatio":0.5,"bootRatioEstimate":0.2,"member":null,"attachments":null},{"createdBy":"18405863019","createdDate":"2018-06-04 18:02:03","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-04 18:02:03","id":5,"deleted":false,"sort":10,"fromClientType":null,"customerId":null,"factory":{"createdBy":"admin","createdDate":"2018-04-23 17:02:49","lastModifiedBy":"admin","lastModifiedDate":"2018-04-23 17:02:49","id":1,"deleted":false,"sort":10,"fromClientType":null,"member":null,"name":"工厂一","defaults":false,"address":"西湖区文三路"},"equipment":{"createdBy":"admin","createdDate":"2018-04-24 19:02:39","lastModifiedBy":"admin","lastModifiedDate":"2018-04-24 19:02:39","id":2,"deleted":false,"sort":10,"fromClientType":null,"member":null,"factory":{"createdBy":"15058305380","createdDate":"2018-06-06 10:35:15","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-06 10:35:15","id":7,"deleted":false,"sort":10,"fromClientType":null,"member":null,"name":"工厂测试","defaults":false,"address":"西湖区教工路"},"field":{"createdBy":"admin","createdDate":"2018-04-13 09:49:11","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:11","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"MARKET","value":"营销小助手","modifiable":null,"builtIn":true,"remark":"营销小助手"},"fieldValue":null,"type":{"createdBy":"admin","createdDate":"2018-04-13 09:49:27","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:27","id":9,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"PERFORMANCE","value":"绩效小助手","modifiable":null,"builtIn":true,"remark":"绩效小助手"},"typeValue":null,"name":"设备二号","quantity":10,"remark":null,"brands":"温州制造","size":"15英寸","purchaseYears":5,"attachments":null},"year":2018,"month":3,"bootedQuantity":1,"bootedRatio":0.2,"bootRatioEstimate":0.3,"member":null,"attachments":null}]
     * totalElements : 41
     * last : false
     * totalPages : 21
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
         * createdDate : 2018-04-24 19:25:11
         * lastModifiedBy : admin
         * lastModifiedDate : 2018-04-24 19:25:11
         * id : 4
         * deleted : false
         * sort : 10
         * fromClientType : null
         * customerId : null
         * factory : {"createdBy":"admin","createdDate":"2018-04-23 17:02:49","lastModifiedBy":"admin","lastModifiedDate":"2018-04-23 17:02:49","id":1,"deleted":false,"sort":10,"fromClientType":null,"member":null,"name":"工厂一","defaults":false,"address":"西湖区文三路"}
         * equipment : {"createdBy":"15058305380","createdDate":"2018-06-06 13:55:19","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-06 13:55:19","id":15,"deleted":false,"sort":10,"fromClientType":null,"member":null,"factory":{"createdBy":"15058305380","createdDate":"2018-06-06 10:35:18","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-06 10:35:18","id":8,"deleted":false,"sort":10,"fromClientType":null,"member":null,"name":"工厂测试","defaults":false,"address":"西湖区教工路"},"field":{"createdBy":"admin","createdDate":"2018-04-13 09:49:11","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:11","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"MARKET","value":"营销小助手","modifiable":null,"builtIn":true,"remark":"营销小助手"},"fieldValue":null,"type":{"createdBy":"admin","createdDate":"2018-04-13 09:49:27","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:27","id":9,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"PERFORMANCE","value":"绩效小助手","modifiable":null,"builtIn":true,"remark":"绩效小助手"},"typeValue":null,"name":"设备三号","quantity":10,"remark":null,"brands":"温州制造","size":"15英寸","purchaseYears":5,"attachments":null}
         * year : 2017
         * month : 12
         * bootedQuantity : 3
         * bootedRatio : 0.5
         * bootRatioEstimate : 0.2
         * member : null
         * attachments : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private long customerId;
        private FactoryBean factory;
        private String spandexNumber;//氨纶批号
        private String spandexBackoffSpeed;//氨纶退绕速度
        private String spandexBackoffTension;//氨纶退绕张力
        private String drawRation;//牵伸比
        private String productUsingCondition;//产品使用情况
        private String airPressure;//气压
        private String recordDate;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        private String size;

        public String getRecordDate() {
            return recordDate;
        }

        public void setRecordDate(String recordDate) {
            this.recordDate = recordDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProductionDate() {
            return productionDate;
        }

        public void setProductionDate(String productionDate) {
            this.productionDate = productionDate;
        }

        public String getUsedQuantityExpected() {
            return usedQuantityExpected;
        }

        public void setUsedQuantityExpected(String usedQuantityExpected) {
            this.usedQuantityExpected = usedQuantityExpected;
        }

        public String getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(String stockQuantity) {
            this.stockQuantity = stockQuantity;
        }

        public String getStockDays() {
            return stockDays;
        }

        public void setStockDays(String stockDays) {
            this.stockDays = stockDays;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        private String name;
        private String productionDate;
        private String usedQuantityExpected;
        private String stockQuantity;
        private String stockDays;
        private String remark;

        public String getSpandexNumber() {
            return spandexNumber;
        }

        public void setSpandexNumber(String spandexNumber) {
            this.spandexNumber = spandexNumber;
        }

        public String getSpandexBackoffSpeed() {
            return spandexBackoffSpeed;
        }

        public void setSpandexBackoffSpeed(String spandexBackoffSpeed) {
            this.spandexBackoffSpeed = spandexBackoffSpeed;
        }

        public String getSpandexBackoffTension() {
            return spandexBackoffTension;
        }

        public void setSpandexBackoffTension(String spandexBackoffTension) {
            this.spandexBackoffTension = spandexBackoffTension;
        }

        public String getDrawRation() {
            return drawRation;
        }

        public void setDrawRation(String drawRation) {
            this.drawRation = drawRation;
        }

        public String getProductUsingCondition() {
            return productUsingCondition;
        }

        public void setProductUsingCondition(String productUsingCondition) {
            this.productUsingCondition = productUsingCondition;
        }

        public String getAirPressure() {
            return airPressure;
        }

        public void setAirPressure(String airPressure) {
            this.airPressure = airPressure;
        }



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

        public long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(long customerId) {
            this.customerId = customerId;
        }

        public FactoryBean getFactory() {
            return factory;
        }

        public void setFactory(FactoryBean factory) {
            this.factory = factory;
        }

        public EquipmentBean.ContentBean getEquipment() {
            return equipment;
        }

        public void setEquipment(EquipmentBean.ContentBean equipment) {
            this.equipment = equipment;
        }

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

        public int getBootedQuantity() {
            return bootedQuantity;
        }

        public void setBootedQuantity(int bootedQuantity) {
            this.bootedQuantity = bootedQuantity;
        }

        public double getBootedRatio() {
            return bootedRatio;
        }

        public void setBootedRatio(double bootedRatio) {
            this.bootedRatio = bootedRatio;
        }

        public double getBootRatioEstimate() {
            return bootRatioEstimate;
        }

        public void setBootRatioEstimate(double bootRatioEstimate) {
            this.bootRatioEstimate = bootRatioEstimate;
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

        private EquipmentBean.ContentBean equipment;
        private int year;
        private int month;
        private int bootedQuantity;
        private double bootedRatio;
        private double bootRatioEstimate;
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
