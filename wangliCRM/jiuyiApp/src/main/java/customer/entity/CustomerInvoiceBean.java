package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CustomerInvoiceBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/9 16:20
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/9 1.00 初始版本
 * ****************************************************************
 */
public class CustomerInvoiceBean {

    /**
     * content : [{"createdBy":"system","createdDate":"2019-01-08 17:24:18","lastModifiedBy":"system","lastModifiedDate":"2019-01-08 17:24:18","id":1970,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"memberNumber":"200011","memberName":"晶科能源有限公司","number":"80002961","deliveryNumber":"200011","lineNumber":"10","pstyv":"ZTAN","erdat":"2018-03-07","erzet":"10:37:47","bldat":"2018-03-07T00:00:00.000+0800","lfart":"ZLF","bwart":"601","orderNumber":"30002988","orderLineNumber":"10","materialNumber":"60000007","materialDesp":"成品单晶低氧180PERC4主栅> C 16.80-17.00 4.11 WPC","quantity":100,"meins":"PC","unit":"PC","amount":"0.00","charg":"BU10000214","werks":"1000","lgort":"3001","saleGroupKey":"201","saleGroupValue":null,"distributionChannelKey":"10","distributionChannelValue":"直销","spart":"00","erzetv":"00:00:00","meinsQuantity":100,"firstNotified":true,"logisticsNumber":null,"licensePlateNumber":null,"driverName":null,"driverPhone":null,"expressKey":null,"expressValue":null,"expressNumber":null,"member":null,"invoiceLogistics":null,"material":null,"order":null,"contractNumber":"40001167","contractLineNumber":"10"}]
     * last : true
     * totalPages : 1
     * totalElements : 1
     * number : 0
     * size : 5
     * sort : [{"direction":"DESC","property":"lastModifiedDate","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * first : true
     * numberOfElements : 1
     */

    private boolean last;
    private int totalPages;
    private int totalElements;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
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


        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private int sort;
        private String memberNumber;
        private String memberName;
        private String number;
        private String deliveryNumber;
        private String lineNumber;
        private String pstyv;
        private String erdat;
        private String erzet;
        private String bldat;
        private String lfart;
        private String bwart;
        private String orderNumber;
        private String orderLineNumber;
        private String materialNumber;
        private String materialDesp;
        private double quantity;
        private String meins;
        private String unit;
        private String amount;
        private String charg;
        private String werks;
        private String lgort;
        private String saleGroupKey;
        private Object saleGroupValue;
        private String distributionChannelKey;
        private String distributionChannelValue;
        private String spart;
        private String erzetv;
        private double meinsQuantity;
        private boolean firstNotified;
        private Object logisticsNumber;
        private Object licensePlateNumber;
        private Object driverName;
        private Object driverPhone;
        private Object expressKey;
        private Object expressValue;
        private Object expressNumber;
        private Object member;
        private Object invoiceLogistics;
        private Object material;
        private Object order;
        private String contractNumber;
        private String contractLineNumber;
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


        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }


        public String getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(String memberNumber) {
            this.memberNumber = memberNumber;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getDeliveryNumber() {
            return deliveryNumber;
        }

        public void setDeliveryNumber(String deliveryNumber) {
            this.deliveryNumber = deliveryNumber;
        }

        public String getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(String lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String getPstyv() {
            return pstyv;
        }

        public void setPstyv(String pstyv) {
            this.pstyv = pstyv;
        }

        public String getErdat() {
            return erdat;
        }

        public void setErdat(String erdat) {
            this.erdat = erdat;
        }

        public String getErzet() {
            return erzet;
        }

        public void setErzet(String erzet) {
            this.erzet = erzet;
        }

        public String getBldat() {
            return bldat;
        }

        public void setBldat(String bldat) {
            this.bldat = bldat;
        }

        public String getLfart() {
            return lfart;
        }

        public void setLfart(String lfart) {
            this.lfart = lfart;
        }

        public String getBwart() {
            return bwart;
        }

        public void setBwart(String bwart) {
            this.bwart = bwart;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getOrderLineNumber() {
            return orderLineNumber;
        }

        public void setOrderLineNumber(String orderLineNumber) {
            this.orderLineNumber = orderLineNumber;
        }

        public String getMaterialNumber() {
            return materialNumber;
        }

        public void setMaterialNumber(String materialNumber) {
            this.materialNumber = materialNumber;
        }

        public String getMaterialDesp() {
            return materialDesp;
        }

        public void setMaterialDesp(String materialDesp) {
            this.materialDesp = materialDesp;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getMeins() {
            return meins;
        }

        public void setMeins(String meins) {
            this.meins = meins;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCharg() {
            return charg;
        }

        public void setCharg(String charg) {
            this.charg = charg;
        }

        public String getWerks() {
            return werks;
        }

        public void setWerks(String werks) {
            this.werks = werks;
        }

        public String getLgort() {
            return lgort;
        }

        public void setLgort(String lgort) {
            this.lgort = lgort;
        }

        public String getSaleGroupKey() {
            return saleGroupKey;
        }

        public void setSaleGroupKey(String saleGroupKey) {
            this.saleGroupKey = saleGroupKey;
        }

        public Object getSaleGroupValue() {
            return saleGroupValue;
        }

        public void setSaleGroupValue(Object saleGroupValue) {
            this.saleGroupValue = saleGroupValue;
        }

        public String getDistributionChannelKey() {
            return distributionChannelKey;
        }

        public void setDistributionChannelKey(String distributionChannelKey) {
            this.distributionChannelKey = distributionChannelKey;
        }

        public String getDistributionChannelValue() {
            return distributionChannelValue;
        }

        public void setDistributionChannelValue(String distributionChannelValue) {
            this.distributionChannelValue = distributionChannelValue;
        }

        public String getSpart() {
            return spart;
        }

        public void setSpart(String spart) {
            this.spart = spart;
        }

        public String getErzetv() {
            return erzetv;
        }

        public void setErzetv(String erzetv) {
            this.erzetv = erzetv;
        }

        public double getMeinsQuantity() {
            return meinsQuantity;
        }

        public void setMeinsQuantity(double meinsQuantity) {
            this.meinsQuantity = meinsQuantity;
        }

        public boolean isFirstNotified() {
            return firstNotified;
        }

        public void setFirstNotified(boolean firstNotified) {
            this.firstNotified = firstNotified;
        }

        public Object getLogisticsNumber() {
            return logisticsNumber;
        }

        public void setLogisticsNumber(Object logisticsNumber) {
            this.logisticsNumber = logisticsNumber;
        }

        public Object getLicensePlateNumber() {
            return licensePlateNumber;
        }

        public void setLicensePlateNumber(Object licensePlateNumber) {
            this.licensePlateNumber = licensePlateNumber;
        }

        public Object getDriverName() {
            return driverName;
        }

        public void setDriverName(Object driverName) {
            this.driverName = driverName;
        }

        public Object getDriverPhone() {
            return driverPhone;
        }

        public void setDriverPhone(Object driverPhone) {
            this.driverPhone = driverPhone;
        }

        public Object getExpressKey() {
            return expressKey;
        }

        public void setExpressKey(Object expressKey) {
            this.expressKey = expressKey;
        }

        public Object getExpressValue() {
            return expressValue;
        }

        public void setExpressValue(Object expressValue) {
            this.expressValue = expressValue;
        }

        public Object getExpressNumber() {
            return expressNumber;
        }

        public void setExpressNumber(Object expressNumber) {
            this.expressNumber = expressNumber;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public Object getInvoiceLogistics() {
            return invoiceLogistics;
        }

        public void setInvoiceLogistics(Object invoiceLogistics) {
            this.invoiceLogistics = invoiceLogistics;
        }

        public Object getMaterial() {
            return material;
        }

        public void setMaterial(Object material) {
            this.material = material;
        }

        public Object getOrder() {
            return order;
        }

        public void setOrder(Object order) {
            this.order = order;
        }

        public String getContractNumber() {
            return contractNumber;
        }

        public void setContractNumber(String contractNumber) {
            this.contractNumber = contractNumber;
        }

        public String getContractLineNumber() {
            return contractLineNumber;
        }

        public void setContractLineNumber(String contractLineNumber) {
            this.contractLineNumber = contractLineNumber;
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
