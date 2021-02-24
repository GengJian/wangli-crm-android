package customer.entity;

import com.jiuyi.model.DictResultBean;

import java.util.List;

public class CompeteInfoBean {


    /**
     * content : [{"createdBy":"admin","createdDate":"2018-04-25 14:28:15","lastModifiedBy":"admin","lastModifiedDate":"2018-04-25 14:28:15","id":4,"deleted":false,"sort":10,"fromClientType":null,"member":null,"companyType":{"createdBy":"admin","createdDate":"2018-04-13 09:48:08","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:48:08","id":6,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"RECEIVE","value":"催款小助手","modifiable":null,"builtIn":true,"remark":"催款小助手"},"companyTypeValue":null,"industryRank":null,"industryRankValue":null,"companyName":"华峰","brand":null,"brandValue":null,"brandPosition":{"createdBy":"admin","createdDate":"2018-04-13 09:49:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:56","id":10,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"AFTER_SALE","value":"售后小助手","modifiable":null,"builtIn":true,"remark":"售后小助手"},"brandPositionValue":null,"salesVolume":50,"features":"稳准狠","marketStrategy":"敌退我进","serviceMode":"线下服务","background":"国家支持","attachments":null},{"createdBy":"admin","createdDate":"2018-04-25 14:32:19","lastModifiedBy":"admin","lastModifiedDate":"2018-04-25 14:32:19","id":5,"deleted":false,"sort":10,"fromClientType":null,"member":null,"companyType":{"createdBy":"admin","createdDate":"2018-04-13 09:49:11","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:11","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"MARKET","value":"营销小助手","modifiable":null,"builtIn":true,"remark":"营销小助手"},"companyTypeValue":null,"industryRank":null,"industryRankValue":null,"companyName":"中国移动","brand":null,"brandValue":null,"brandPosition":{"createdBy":"admin","createdDate":"2018-04-13 09:48:48","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:48:48","id":7,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"PLAN","value":"计划小助手","modifiable":null,"builtIn":true,"remark":"计划小助手"},"brandPositionValue":null,"salesVolume":30,"features":"666","marketStrategy":"垄断","serviceMode":"线下服务","background":"国家支持","attachments":null}]
     * totalElements : 4
     * last : true
     * totalPages : 2
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
         * createdDate : 2018-04-25 14:28:15
         * lastModifiedBy : admin
         * lastModifiedDate : 2018-04-25 14:28:15
         * id : 4
         * deleted : false
         * sort : 10
         * fromClientType : null
         * member : null
         * companyType : {"createdBy":"admin","createdDate":"2018-04-13 09:48:08","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:48:08","id":6,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"RECEIVE","value":"催款小助手","modifiable":null,"builtIn":true,"remark":"催款小助手"}
         * companyTypeValue : null
         * industryRank : null
         * industryRankValue : null
         * companyName : 华峰
         * brand : null
         * brandValue : null
         * brandPosition : {"createdBy":"admin","createdDate":"2018-04-13 09:49:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:56","id":10,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"AFTER_SALE","value":"售后小助手","modifiable":null,"builtIn":true,"remark":"售后小助手"}
         * brandPositionValue : null
         * salesVolume : 50
         * features : 稳准狠
         * marketStrategy : 敌退我进
         * serviceMode : 线下服务
         * background : 国家支持
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
        private DictResultBean.ContentBean companyType;
        private DictResultBean.ContentBean brandPosition;
        private DictResultBean.ContentBean brand;
        private DictResultBean.ContentBean industryRank;
        private Object companyTypeValue;

        private Object industryRankValue;
        private String companyName;
        private String spandexNumber;//氨纶批号


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

        public double getPriceVolume() {
            return priceVolume;
        }

        public void setPriceVolume(double priceVolume) {
            this.priceVolume = priceVolume;
        }

        public String getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(String payMethod) {
            this.payMethod = payMethod;
        }

        public String getAccountPeriod() {
            return accountPeriod;
        }

        public void setAccountPeriod(String accountPeriod) {
            this.accountPeriod = accountPeriod;
        }

        public String getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(String stockQuantity) {
            this.stockQuantity = stockQuantity;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public DictResultBean.ContentBean getDistributionChannel() {
            return distributionChannel;
        }

        public void setDistributionChannel(DictResultBean.ContentBean distributionChannel) {
            this.distributionChannel = distributionChannel;
        }

        private String recordDate;
        private String name;
        private double priceVolume;
        private String payMethod;
        private String accountPeriod;
        private String stockQuantity;
        private String remark;
        private DictResultBean.ContentBean distributionChannel;

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

        private String spandexBackoffSpeed;//氨纶退绕速度
        private String spandexBackoffTension;//氨纶退绕张力
        private String drawRation;//牵伸比
        private String productUsingCondition;//产品使用情况
        private String airPressure;//气压

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

        public DictResultBean.ContentBean getCompanyType() {
            return companyType;
        }

        public void setCompanyType(DictResultBean.ContentBean companyType) {
            this.companyType = companyType;
        }

        public DictResultBean.ContentBean getBrandPosition() {
            return brandPosition;
        }

        public void setBrandPosition(DictResultBean.ContentBean brandPosition) {
            this.brandPosition = brandPosition;
        }

        public DictResultBean.ContentBean getBrand() {
            return brand;
        }

        public void setBrand(DictResultBean.ContentBean brand) {
            this.brand = brand;
        }

        public DictResultBean.ContentBean getIndustryRank() {
            return industryRank;
        }

        public void setIndustryRank(DictResultBean.ContentBean industryRank) {
            this.industryRank = industryRank;
        }

        public Object getCompanyTypeValue() {
            return companyTypeValue;
        }

        public void setCompanyTypeValue(Object companyTypeValue) {
            this.companyTypeValue = companyTypeValue;
        }

        public Object getIndustryRankValue() {
            return industryRankValue;
        }

        public void setIndustryRankValue(Object industryRankValue) {
            this.industryRankValue = industryRankValue;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public Object getBrandValue() {
            return brandValue;
        }

        public void setBrandValue(Object brandValue) {
            this.brandValue = brandValue;
        }

        public Object getBrandPositionValue() {
            return brandPositionValue;
        }

        public void setBrandPositionValue(Object brandPositionValue) {
            this.brandPositionValue = brandPositionValue;
        }

        public Double getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(Double salesVolume) {
            this.salesVolume = salesVolume;
        }

        public String getFeatures() {
            return features;
        }

        public void setFeatures(String features) {
            this.features = features;
        }

        public String getMarketStrategy() {
            return marketStrategy;
        }

        public void setMarketStrategy(String marketStrategy) {
            this.marketStrategy = marketStrategy;
        }

        public String getServiceMode() {
            return serviceMode;
        }

        public void setServiceMode(String serviceMode) {
            this.serviceMode = serviceMode;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
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

        private Object brandValue;

        private Object brandPositionValue;
        private Double salesVolume;
        private String features;
        private String marketStrategy;
        private String serviceMode;
        private String background;
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
