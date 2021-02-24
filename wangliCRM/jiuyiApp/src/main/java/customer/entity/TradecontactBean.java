package customer.entity;

import com.jiuyi.model.DictResultBean;

import java.util.List;

public class TradecontactBean {

    /**
     * content : [{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":1,"deleted":false,"sort":10,"fromClientType":null,"member":null,"type":{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":679,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_type","desp":"销售合同类型","key":"saleconstract_pre_domestic","value":"事前内销合同","modifiable":null,"builtIn":false,"remark":"事前内销合同"},"status":{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":682,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_status","desp":"销售合同状态","key":"contract_draft","value":"草稿","modifiable":null,"builtIn":false,"remark":"草稿"},"operator":null,"number":"HFHT20180614001","infoDate":null,"startDate":null,"endDate":null,"overdueNotifyDays":null,"amount":null,"demandDeliveryDate":null,"shipAddressId":null,"shipContact":null,"shipPhone":null,"shipProvince":null,"shipCity":null,"shipAddr":null,"invoicingTitle":null,"invoicingTaxNumber":null,"ticketCollectorName":null,"ticketCollectorPhone":null,"specialClause":null,"remark":null,"paymementCondition":null,"shipType":null,"internationalTradeClause":null,"foreignInvoiceTrackings":null,"currency":null,"payWay":null,"tradeWay":null,"attachments":null,"salesContractMaterials":null,"salesContractInvoices":null},{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":2,"deleted":false,"sort":10,"fromClientType":null,"member":null,"type":null,"status":{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":682,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_status","desp":"销售合同状态","key":"contract_draft","value":"草稿","modifiable":null,"builtIn":false,"remark":"草稿"},"operator":null,"number":"HFHT20180614002","infoDate":null,"startDate":null,"endDate":null,"overdueNotifyDays":null,"amount":null,"demandDeliveryDate":null,"shipAddressId":null,"shipContact":null,"shipPhone":null,"shipProvince":null,"shipCity":null,"shipAddr":null,"invoicingTitle":null,"invoicingTaxNumber":null,"ticketCollectorName":null,"ticketCollectorPhone":null,"specialClause":null,"remark":null,"paymementCondition":null,"shipType":null,"internationalTradeClause":null,"foreignInvoiceTrackings":null,"currency":null,"payWay":null,"tradeWay":null,"attachments":null,"salesContractMaterials":null,"salesContractInvoices":null},{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":3,"deleted":false,"sort":10,"fromClientType":null,"member":null,"type":null,"status":{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":682,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_status","desp":"销售合同状态","key":"contract_draft","value":"草稿","modifiable":null,"builtIn":false,"remark":"草稿"},"operator":null,"number":"HFHT20180614003","infoDate":null,"startDate":null,"endDate":null,"overdueNotifyDays":null,"amount":null,"demandDeliveryDate":null,"shipAddressId":null,"shipContact":null,"shipPhone":null,"shipProvince":null,"shipCity":null,"shipAddr":null,"invoicingTitle":null,"invoicingTaxNumber":null,"ticketCollectorName":null,"ticketCollectorPhone":null,"specialClause":null,"remark":null,"paymementCondition":null,"shipType":null,"internationalTradeClause":null,"foreignInvoiceTrackings":null,"currency":null,"payWay":null,"tradeWay":null,"attachments":null,"salesContractMaterials":null,"salesContractInvoices":null},{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":4,"deleted":false,"sort":10,"fromClientType":null,"member":null,"type":{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":680,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_type","desp":"销售合同类型","key":"saleconstract_post_domestic","value":"事后内销合同","modifiable":null,"builtIn":false,"remark":"事后内销合同"},"status":{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":682,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_status","desp":"销售合同状态","key":"contract_draft","value":"草稿","modifiable":null,"builtIn":false,"remark":"草稿"},"operator":null,"number":"HFHT20180614004","infoDate":"2018-06-16","startDate":null,"endDate":null,"overdueNotifyDays":null,"amount":null,"demandDeliveryDate":null,"shipAddressId":null,"shipContact":null,"shipPhone":null,"shipProvince":null,"shipCity":null,"shipAddr":null,"invoicingTitle":null,"invoicingTaxNumber":null,"ticketCollectorName":null,"ticketCollectorPhone":null,"specialClause":null,"remark":null,"paymementCondition":null,"shipType":null,"internationalTradeClause":null,"foreignInvoiceTrackings":null,"currency":null,"payWay":null,"tradeWay":null,"attachments":null,"salesContractMaterials":null,"salesContractInvoices":null}]
     * totalPages : 1
     * last : true
     * totalElements : 4
     * number : 0
     * size : 5
     * sort : [{"direction":"ASC","property":"createdDate","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * first : true
     * numberOfElements : 4
     */

    private int totalPages;
    private boolean last;
    private int totalElements;
    private int number;
    private int size;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;
    private List<SortBean> sort;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

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
         * createdBy : 18405863019
         * createdDate : 2018-06-14
         * lastModifiedBy : 18405863019
         * lastModifiedDate : 2018-06-14
         * id : 1
         * deleted : false
         * sort : 10
         * fromClientType : null
         * member : null
         * type : {"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":679,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_type","desp":"销售合同类型","key":"saleconstract_pre_domestic","value":"事前内销合同","modifiable":null,"builtIn":false,"remark":"事前内销合同"}
         * status : {"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":682,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_status","desp":"销售合同状态","key":"contract_draft","value":"草稿","modifiable":null,"builtIn":false,"remark":"草稿"}
         * operator : null
         * number : HFHT20180614001
         * infoDate : null
         * startDate : null
         * endDate : null
         * overdueNotifyDays : null
         * amount : null
         * demandDeliveryDate : null
         * shipAddressId : null
         * shipContact : null
         * shipPhone : null
         * shipProvince : null
         * shipCity : null
         * shipAddr : null
         * invoicingTitle : null
         * invoicingTaxNumber : null
         * ticketCollectorName : null
         * ticketCollectorPhone : null
         * specialClause : null
         * remark : null
         * paymementCondition : null
         * shipType : null
         * internationalTradeClause : null
         * foreignInvoiceTrackings : null
         * currency : null
         * payWay : null
         * tradeWay : null
         * attachments : null
         * salesContractMaterials : null
         * salesContractInvoices : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private MemberBeanID member;
        private DictResultBean.ContentBean type;
        private DictResultBean.ContentBean status;
        private Object operator;
        private String number;
        private String infoDate;
        private Object startDate;
        private Object endDate;
        private Object overdueNotifyDays;
        private Object amount;
        private Object demandDeliveryDate;
        private Object shipAddressId;
        private Object shipContact;
        private Object shipPhone;
        private Object shipProvince;
        private Object shipCity;
        private Object shipAddr;
        private Object invoicingTitle;
        private Object invoicingTaxNumber;
        private Object ticketCollectorName;
        private Object ticketCollectorPhone;
        private Object specialClause;
        private Object remark;
        private Object paymementCondition;
        private Object shipType;

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

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public DictResultBean.ContentBean getType() {
            return type;
        }

        public void setType(DictResultBean.ContentBean type) {
            this.type = type;
        }

        public DictResultBean.ContentBean getStatus() {
            return status;
        }

        public void setStatus(DictResultBean.ContentBean status) {
            this.status = status;
        }

        public Object getOperator() {
            return operator;
        }

        public void setOperator(Object operator) {
            this.operator = operator;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getInfoDate() {
            return infoDate;
        }

        public void setInfoDate(String infoDate) {
            this.infoDate = infoDate;
        }

        public Object getStartDate() {
            return startDate;
        }

        public void setStartDate(Object startDate) {
            this.startDate = startDate;
        }

        public Object getEndDate() {
            return endDate;
        }

        public void setEndDate(Object endDate) {
            this.endDate = endDate;
        }

        public Object getOverdueNotifyDays() {
            return overdueNotifyDays;
        }

        public void setOverdueNotifyDays(Object overdueNotifyDays) {
            this.overdueNotifyDays = overdueNotifyDays;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public Object getDemandDeliveryDate() {
            return demandDeliveryDate;
        }

        public void setDemandDeliveryDate(Object demandDeliveryDate) {
            this.demandDeliveryDate = demandDeliveryDate;
        }

        public Object getShipAddressId() {
            return shipAddressId;
        }

        public void setShipAddressId(Object shipAddressId) {
            this.shipAddressId = shipAddressId;
        }

        public Object getShipContact() {
            return shipContact;
        }

        public void setShipContact(Object shipContact) {
            this.shipContact = shipContact;
        }

        public Object getShipPhone() {
            return shipPhone;
        }

        public void setShipPhone(Object shipPhone) {
            this.shipPhone = shipPhone;
        }

        public Object getShipProvince() {
            return shipProvince;
        }

        public void setShipProvince(Object shipProvince) {
            this.shipProvince = shipProvince;
        }

        public Object getShipCity() {
            return shipCity;
        }

        public void setShipCity(Object shipCity) {
            this.shipCity = shipCity;
        }

        public Object getShipAddr() {
            return shipAddr;
        }

        public void setShipAddr(Object shipAddr) {
            this.shipAddr = shipAddr;
        }

        public Object getInvoicingTitle() {
            return invoicingTitle;
        }

        public void setInvoicingTitle(Object invoicingTitle) {
            this.invoicingTitle = invoicingTitle;
        }

        public Object getInvoicingTaxNumber() {
            return invoicingTaxNumber;
        }

        public void setInvoicingTaxNumber(Object invoicingTaxNumber) {
            this.invoicingTaxNumber = invoicingTaxNumber;
        }

        public Object getTicketCollectorName() {
            return ticketCollectorName;
        }

        public void setTicketCollectorName(Object ticketCollectorName) {
            this.ticketCollectorName = ticketCollectorName;
        }

        public Object getTicketCollectorPhone() {
            return ticketCollectorPhone;
        }

        public void setTicketCollectorPhone(Object ticketCollectorPhone) {
            this.ticketCollectorPhone = ticketCollectorPhone;
        }

        public Object getSpecialClause() {
            return specialClause;
        }

        public void setSpecialClause(Object specialClause) {
            this.specialClause = specialClause;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getPaymementCondition() {
            return paymementCondition;
        }

        public void setPaymementCondition(Object paymementCondition) {
            this.paymementCondition = paymementCondition;
        }

        public Object getShipType() {
            return shipType;
        }

        public void setShipType(Object shipType) {
            this.shipType = shipType;
        }

        public Object getInternationalTradeClause() {
            return internationalTradeClause;
        }

        public void setInternationalTradeClause(Object internationalTradeClause) {
            this.internationalTradeClause = internationalTradeClause;
        }

        public Object getForeignInvoiceTrackings() {
            return foreignInvoiceTrackings;
        }

        public void setForeignInvoiceTrackings(Object foreignInvoiceTrackings) {
            this.foreignInvoiceTrackings = foreignInvoiceTrackings;
        }

        public Object getCurrency() {
            return currency;
        }

        public void setCurrency(Object currency) {
            this.currency = currency;
        }

        public Object getPayWay() {
            return payWay;
        }

        public void setPayWay(Object payWay) {
            this.payWay = payWay;
        }

        public Object getTradeWay() {
            return tradeWay;
        }

        public void setTradeWay(Object tradeWay) {
            this.tradeWay = tradeWay;
        }

        public List<AttachmentBean> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<AttachmentBean> attachments) {
            this.attachments = attachments;
        }

        public Object getSalesContractMaterials() {
            return salesContractMaterials;
        }

        public void setSalesContractMaterials(Object salesContractMaterials) {
            this.salesContractMaterials = salesContractMaterials;
        }

        public Object getSalesContractInvoices() {
            return salesContractInvoices;
        }

        public void setSalesContractInvoices(Object salesContractInvoices) {
            this.salesContractInvoices = salesContractInvoices;
        }

        private Object internationalTradeClause;
        private Object foreignInvoiceTrackings;
        private Object currency;
        private Object payWay;
        private Object tradeWay;
        private List<AttachmentBean> attachments;
        private Object salesContractMaterials;
        private Object salesContractInvoices;


    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : createdDate
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
