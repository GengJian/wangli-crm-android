package dynamic.entity;

import java.util.List;

public class OrderBean {




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
         * createdBy : 15058305380
         * createdDate : 2018-06-13
         * lastModifiedBy : 15058305380
         * lastModifiedDate : 2018-06-13
         * id : 27
         * deleted : false
         * sort : 10
         * fromClientType : null
         * type : ZOR1
         * member : null
         * operator : null
         * orderMember : null
         * crmNumber : ZOR120180613001
         * sapNumber : null
         * office : null
         * orderRoleType : DEPARTMENT
         * orderOperator : null
         * status : CRM_COMMIT
         * finalAmount : 60
         * lowPriceReasonType : null
         * lowReason : null
         * requestDeliveryDate : 2018-04-04T00:00:00.000+0800
         * internationalTradeTerm : null
         * currency : null
         * reason : null
         * shippingAddress : null
         * statusDate : 2018-06-13T20:56:45.000+0800
         * orderDate : null
         * salemanName : null
         * avatarUrl : http://img.jiuyisoft.com/cava4.jpg
         * orgName : 王大林
         * memberName : 噶人都要
         * memberPhone : 123
         * address : 东部软件园
         * owedTotalAmount : 3
         * dueTotalAmount : null
         * creditRiskTotalAmount : 3
         * remark : 10
         * adjusted : true
         * orderItem : [{"createdBy":"15058305380","createdDate":"2018-06-13","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-13","id":15,"deleted":false,"sort":10,"fromClientType":null,"material":null,"quantity":100,"batchNumber":"H650/9558","spec":"40D/H650/9558-01标准大箱","grade":null,"guidePrice":30,"actualPrice":30,"amount":60,"application":null,"orderItemStatus":null,"pictureUrl":"http://img.jiuyisoft.com/4506943871_1906051777.310x310.jpg","unit":"KG","denier":"0.00","zzszs":null,"zksks":0,"gradeName":"一等品","applicationName":"原机","member":null,"lineNumber":null,"invoiceNumber":null,"month":6,"year":2018,"low":false,"affirm":false,"orderItemFixStatus":null,"priceVos":null,"checkAdjust":null,"adjust":false}]
         * orderLogs : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private Object fromClientType;
        private String type;
        private Object member;
        private Object operator;
        private Object orderMember;
        private String crmNumber;
        private Object sapNumber;
        private Object office;
        private String orderRoleType;
        private Object orderOperator;
        private String status;
        private double finalAmount;

        public String getWearsSign() {
            return wearsSign;
        }

        public void setWearsSign(String wearsSign) {
            this.wearsSign = wearsSign;
        }

        private String wearsSign;
        private Object lowPriceReasonType;
        private Object lowReason;
        private String requestDeliveryDate;
        private Object internationalTradeTerm;
        private Object currency;
        private Object reason;
        private Object shippingAddress;
        private String statusDate;
        private Object orderDate;
        private String salemanName;
        private String avatarUrl;
        private String orgName;
        private String memberName;
        private String memberPhone;
        private String address;
        private double owedTotalAmount;
        private Object dueTotalAmount;
        private double creditRiskTotalAmount;
        private String remark;
        private boolean adjusted;
        private Object orderLogs;

        public AuthBean getAuthBean() {
            return authBean;
        }

        public void setAuthBean(AuthBean authBean) {
            this.authBean = authBean;
        }

        private AuthBean authBean;

        public String getStatusDesp() {
            return statusDesp;
        }

        public void setStatusDesp(String statusDesp) {
            this.statusDesp = statusDesp;
        }

        private String statusDesp;

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        private String abbreviation;
        private List<OrderItemBean> orderItem;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public Object getOperator() {
            return operator;
        }

        public void setOperator(Object operator) {
            this.operator = operator;
        }

        public Object getOrderMember() {
            return orderMember;
        }

        public void setOrderMember(Object orderMember) {
            this.orderMember = orderMember;
        }

        public String getCrmNumber() {
            return crmNumber;
        }

        public void setCrmNumber(String crmNumber) {
            this.crmNumber = crmNumber;
        }

        public Object getSapNumber() {
            return sapNumber;
        }

        public void setSapNumber(Object sapNumber) {
            this.sapNumber = sapNumber;
        }

        public Object getOffice() {
            return office;
        }

        public void setOffice(Object office) {
            this.office = office;
        }

        public String getOrderRoleType() {
            return orderRoleType;
        }

        public void setOrderRoleType(String orderRoleType) {
            this.orderRoleType = orderRoleType;
        }

        public Object getOrderOperator() {
            return orderOperator;
        }

        public void setOrderOperator(Object orderOperator) {
            this.orderOperator = orderOperator;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(double finalAmount) {
            this.finalAmount = finalAmount;
        }

        public Object getLowPriceReasonType() {
            return lowPriceReasonType;
        }

        public void setLowPriceReasonType(Object lowPriceReasonType) {
            this.lowPriceReasonType = lowPriceReasonType;
        }

        public Object getLowReason() {
            return lowReason;
        }

        public void setLowReason(Object lowReason) {
            this.lowReason = lowReason;
        }

        public String getRequestDeliveryDate() {
            return requestDeliveryDate;
        }

        public void setRequestDeliveryDate(String requestDeliveryDate) {
            this.requestDeliveryDate = requestDeliveryDate;
        }

        public Object getInternationalTradeTerm() {
            return internationalTradeTerm;
        }

        public void setInternationalTradeTerm(Object internationalTradeTerm) {
            this.internationalTradeTerm = internationalTradeTerm;
        }

        public Object getCurrency() {
            return currency;
        }

        public void setCurrency(Object currency) {
            this.currency = currency;
        }

        public Object getReason() {
            return reason;
        }

        public void setReason(Object reason) {
            this.reason = reason;
        }

        public Object getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(Object shippingAddress) {
            this.shippingAddress = shippingAddress;
        }

        public String getStatusDate() {
            return statusDate;
        }

        public void setStatusDate(String statusDate) {
            this.statusDate = statusDate;
        }

        public Object getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(Object orderDate) {
            this.orderDate = orderDate;
        }

        public String getSalemanName() {
            return salemanName;
        }

        public void setSalemanName(String salemanName) {
            this.salemanName = salemanName;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getMemberPhone() {
            return memberPhone;
        }

        public void setMemberPhone(String memberPhone) {
            this.memberPhone = memberPhone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getOwedTotalAmount() {
            return owedTotalAmount;
        }

        public void setOwedTotalAmount(double owedTotalAmount) {
            this.owedTotalAmount = owedTotalAmount;
        }

        public Object getDueTotalAmount() {
            return dueTotalAmount;
        }

        public void setDueTotalAmount(Object dueTotalAmount) {
            this.dueTotalAmount = dueTotalAmount;
        }

        public double getCreditRiskTotalAmount() {
            return creditRiskTotalAmount;
        }

        public void setCreditRiskTotalAmount(double creditRiskTotalAmount) {
            this.creditRiskTotalAmount = creditRiskTotalAmount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public boolean isAdjusted() {
            return adjusted;
        }

        public void setAdjusted(boolean adjusted) {
            this.adjusted = adjusted;
        }

        public Object getOrderLogs() {
            return orderLogs;
        }

        public void setOrderLogs(Object orderLogs) {
            this.orderLogs = orderLogs;
        }

        public List<OrderItemBean> getOrderItem() {
            return orderItem;
        }

        public void setOrderItem(List<OrderItemBean> orderItem) {
            this.orderItem = orderItem;
        }
        public static class AuthBean{
            public boolean isModifiable() {
                return modifiable;
            }

            public void setModifiable(boolean modifiable) {
                this.modifiable = modifiable;
            }

            public boolean isDeletable() {
                return deletable;
            }

            public void setDeletable(boolean deletable) {
                this.deletable = deletable;
            }

            public boolean isCredit() {
                return credit;
            }

            public void setCredit(boolean credit) {
                this.credit = credit;
            }

            public boolean isPrice() {
                return price;
            }

            public void setPrice(boolean price) {
                this.price = price;
            }

            public boolean isHistory() {
                return history;
            }

            public void setHistory(boolean history) {
                this.history = history;
            }

            private boolean modifiable;
            private boolean deletable;
            private boolean credit;
            private boolean price;
            private boolean history;

        }

        public static class OrderItemBean {
            /**
             * createdBy : 15058305380
             * createdDate : 2018-06-13
             * lastModifiedBy : 15058305380
             * lastModifiedDate : 2018-06-13
             * id : 15
             * deleted : false
             * sort : 10
             * fromClientType : null
             * material : null
             * quantity : 100
             * batchNumber : H650/9558
             * spec : 40D/H650/9558-01标准大箱
             * grade : null
             * guidePrice : 30
             * actualPrice : 30
             * amount : 60
             * application : null
             * orderItemStatus : null
             * pictureUrl : http://img.jiuyisoft.com/4506943871_1906051777.310x310.jpg
             * unit : KG
             * denier : 0.00
             * zzszs : null
             * zksks : 0
             * gradeName : 一等品
             * applicationName : 原机
             * member : null
             * lineNumber : null
             * invoiceNumber : null
             * month : 6
             * year : 2018
             * low : false
             * affirm : false
             * orderItemFixStatus : null
             * priceVos : null
             * checkAdjust : null
             * adjust : false
             */

            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private Object fromClientType;
            private Object material;
            private double quantity;
            private String batchNumber;
            private String spec;
            private Object grade;
            private double guidePrice;
            private double actualPrice;
            private double amount;
            private Object application;
            private Object orderItemStatus;
            private String pictureUrl;
            private String unit;
            private String denier;
            private Object zzszs;
            private double zksks;
            private String gradeName;
            private String applicationName;
            private Object member;
            private Object lineNumber;
            private Object invoiceNumber;
            private int month;
            private int year;
            private boolean low;
            private boolean affirm;
            private Object orderItemFixStatus;
            private Object priceVos;
            private Object checkAdjust;
            private boolean adjust;

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

            public Object getMaterial() {
                return material;
            }

            public void setMaterial(Object material) {
                this.material = material;
            }

            public double getQuantity() {
                return quantity;
            }

            public void setQuantity(double quantity) {
                this.quantity = quantity;
            }

            public String getBatchNumber() {
                return batchNumber;
            }

            public void setBatchNumber(String batchNumber) {
                this.batchNumber = batchNumber;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public Object getGrade() {
                return grade;
            }

            public void setGrade(Object grade) {
                this.grade = grade;
            }

            public double getGuidePrice() {
                return guidePrice;
            }

            public void setGuidePrice(double guidePrice) {
                this.guidePrice = guidePrice;
            }

            public double getActualPrice() {
                return actualPrice;
            }

            public void setActualPrice(int actualPrice) {
                this.actualPrice = actualPrice;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public Object getApplication() {
                return application;
            }

            public void setApplication(Object application) {
                this.application = application;
            }

            public Object getOrderItemStatus() {
                return orderItemStatus;
            }

            public void setOrderItemStatus(Object orderItemStatus) {
                this.orderItemStatus = orderItemStatus;
            }

            public String getPictureUrl() {
                return pictureUrl;
            }

            public void setPictureUrl(String pictureUrl) {
                this.pictureUrl = pictureUrl;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getDenier() {
                return denier;
            }

            public void setDenier(String denier) {
                this.denier = denier;
            }

            public Object getZzszs() {
                return zzszs;
            }

            public void setZzszs(Object zzszs) {
                this.zzszs = zzszs;
            }

            public double getZksks() {
                return zksks;
            }

            public void setZksks(double zksks) {
                this.zksks = zksks;
            }

            public String getGradeName() {
                return gradeName;
            }

            public void setGradeName(String gradeName) {
                this.gradeName = gradeName;
            }

            public String getApplicationName() {
                return applicationName;
            }

            public void setApplicationName(String applicationName) {
                this.applicationName = applicationName;
            }

            public Object getMember() {
                return member;
            }

            public void setMember(Object member) {
                this.member = member;
            }

            public Object getLineNumber() {
                return lineNumber;
            }

            public void setLineNumber(Object lineNumber) {
                this.lineNumber = lineNumber;
            }

            public Object getInvoiceNumber() {
                return invoiceNumber;
            }

            public void setInvoiceNumber(Object invoiceNumber) {
                this.invoiceNumber = invoiceNumber;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public boolean isLow() {
                return low;
            }

            public void setLow(boolean low) {
                this.low = low;
            }

            public boolean isAffirm() {
                return affirm;
            }

            public void setAffirm(boolean affirm) {
                this.affirm = affirm;
            }

            public Object getOrderItemFixStatus() {
                return orderItemFixStatus;
            }

            public void setOrderItemFixStatus(Object orderItemFixStatus) {
                this.orderItemFixStatus = orderItemFixStatus;
            }

            public Object getPriceVos() {
                return priceVos;
            }

            public void setPriceVos(Object priceVos) {
                this.priceVos = priceVos;
            }

            public Object getCheckAdjust() {
                return checkAdjust;
            }

            public void setCheckAdjust(Object checkAdjust) {
                this.checkAdjust = checkAdjust;
            }

            public boolean isAdjust() {
                return adjust;
            }

            public void setAdjust(boolean adjust) {
                this.adjust = adjust;
            }
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : id
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
