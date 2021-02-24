package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CustomerProductInfoBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/4 17:56
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/4 1.00 初始版本
 * ****************************************************************
 */
public class CustomerProductInfoBean {



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
        /**
         * id : 9
         * sort : 1
         * createdDate : 1546429079874
         * lastModifiedDate : 1546429797331
         * member : {"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546079225376,"cooperationTypeValue":"供应商","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","sapNumber":"200567","simpleSpell":"dgsgr"}
         * businessTypeKey : silicon_factory
         * businessTypeValue : 硅料厂
         * process : 大家很好的发挥
         * productPatent : 地方领导
         * capacityInfo : 反对浪费的
         * equipment : 地方领导了
         * stockInfo : 额 food
         * productDefect : 地方领导
         * reworkInfo : 地方老地方
         * complaintInfo : 得到了疯了
         * productPrice : 10
         * orderInfo : 多力量大
         * inOutInfo : 带来的
         * returnInfo : 的佛罗里达
         * revenue : 10
         * cost : 10
         * technicalDynamics : 非打开快递
         * productRoadSign : 的落落大方
         * technicalDefect : 发来的
         * demandStandard : 的落落大方
         * poorProductInfo : 地方领导
         * singleGrossProfit : 10
         * grossProfit : 10
         * supplier : 多力量大
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private MemberBeanID member;
        private String businessTypeKey;
        private String businessTypeValue;
        private String process;
        private String productPatent;
        private String capacityInfo;
        private String equipment;
        private String stockInfo;
        private String productDefect;
        private String reworkInfo;
        private String complaintInfo;
        private double productPrice;
        private String orderInfo;
        private String inOutInfo;
        private String returnInfo;
        private double revenue;
        private double cost;
        private String technicalDynamics;
        private String productRoadSign;
        private String technicalDefect;
        private String demandStandard;
        private String poorProductInfo;
        private double singleGrossProfit;
        private double grossProfit;
        private String supplier;

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

        public String getProcess() {
            return process;
        }

        public void setProcess(String process) {
            this.process = process;
        }

        public String getProductPatent() {
            return productPatent;
        }

        public void setProductPatent(String productPatent) {
            this.productPatent = productPatent;
        }

        public String getCapacityInfo() {
            return capacityInfo;
        }

        public void setCapacityInfo(String capacityInfo) {
            this.capacityInfo = capacityInfo;
        }

        public String getEquipment() {
            return equipment;
        }

        public void setEquipment(String equipment) {
            this.equipment = equipment;
        }

        public String getStockInfo() {
            return stockInfo;
        }

        public void setStockInfo(String stockInfo) {
            this.stockInfo = stockInfo;
        }

        public String getProductDefect() {
            return productDefect;
        }

        public void setProductDefect(String productDefect) {
            this.productDefect = productDefect;
        }

        public String getReworkInfo() {
            return reworkInfo;
        }

        public void setReworkInfo(String reworkInfo) {
            this.reworkInfo = reworkInfo;
        }

        public String getComplaintInfo() {
            return complaintInfo;
        }

        public void setComplaintInfo(String complaintInfo) {
            this.complaintInfo = complaintInfo;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public String getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(String orderInfo) {
            this.orderInfo = orderInfo;
        }

        public String getInOutInfo() {
            return inOutInfo;
        }

        public void setInOutInfo(String inOutInfo) {
            this.inOutInfo = inOutInfo;
        }

        public String getReturnInfo() {
            return returnInfo;
        }

        public void setReturnInfo(String returnInfo) {
            this.returnInfo = returnInfo;
        }

        public double getRevenue() {
            return revenue;
        }

        public void setRevenue(double revenue) {
            this.revenue = revenue;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public String getTechnicalDynamics() {
            return technicalDynamics;
        }

        public void setTechnicalDynamics(String technicalDynamics) {
            this.technicalDynamics = technicalDynamics;
        }

        public String getProductRoadSign() {
            return productRoadSign;
        }

        public void setProductRoadSign(String productRoadSign) {
            this.productRoadSign = productRoadSign;
        }

        public String getTechnicalDefect() {
            return technicalDefect;
        }

        public void setTechnicalDefect(String technicalDefect) {
            this.technicalDefect = technicalDefect;
        }

        public String getDemandStandard() {
            return demandStandard;
        }

        public void setDemandStandard(String demandStandard) {
            this.demandStandard = demandStandard;
        }

        public String getPoorProductInfo() {
            return poorProductInfo;
        }

        public void setPoorProductInfo(String poorProductInfo) {
            this.poorProductInfo = poorProductInfo;
        }

        public double getSingleGrossProfit() {
            return singleGrossProfit;
        }

        public void setSingleGrossProfit(double singleGrossProfit) {
            this.singleGrossProfit = singleGrossProfit;
        }

        public double getGrossProfit() {
            return grossProfit;
        }

        public void setGrossProfit(double grossProfit) {
            this.grossProfit = grossProfit;
        }

        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

    }
}
