package customer.entity;

import java.util.List;

public class TradelogisticsBean {




    private boolean last;
    private int totalPages;
    private int totalElements;
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
        private Object deleted;
        private Object sort;
        private Object fromClientType;
        private String number;
        private Object member;
        private String status;
        private String phoneFax;
        private String transportType;
        private String shippingPort;
        private String targetPort;
        private String country;
        private String shippingAgent;
        private String packages;
        private int freight;
        private String containerType;
        private String invoiceDate;
        private String bookingDate;
        private String loadingCabinetDate;
        private Object customsDate;
        private Object shipmentDate;
        private Object receiveLadingBillDate;
        private Object receivePaymentDate;
        private Object deliveryNoteDate;
        private Object pickUpDate;
        private String memberName;
        private String address;
        private String changeDate;
        private List<SalesContractsBean> salesContracts;
        private List<ForeignInvoiceDetailsBean> foreignInvoiceDetails;

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

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPhoneFax() {
            return phoneFax;
        }

        public void setPhoneFax(String phoneFax) {
            this.phoneFax = phoneFax;
        }

        public String getTransportType() {
            return transportType;
        }

        public void setTransportType(String transportType) {
            this.transportType = transportType;
        }

        public String getShippingPort() {
            return shippingPort;
        }

        public void setShippingPort(String shippingPort) {
            this.shippingPort = shippingPort;
        }

        public String getTargetPort() {
            return targetPort;
        }

        public void setTargetPort(String targetPort) {
            this.targetPort = targetPort;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getShippingAgent() {
            return shippingAgent;
        }

        public void setShippingAgent(String shippingAgent) {
            this.shippingAgent = shippingAgent;
        }

        public String getPackages() {
            return packages;
        }

        public void setPackages(String packages) {
            this.packages = packages;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public String getContainerType() {
            return containerType;
        }

        public void setContainerType(String containerType) {
            this.containerType = containerType;
        }

        public String getInvoiceDate() {
            return invoiceDate;
        }

        public void setInvoiceDate(String invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public String getBookingDate() {
            return bookingDate;
        }

        public void setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
        }

        public String getLoadingCabinetDate() {
            return loadingCabinetDate;
        }

        public void setLoadingCabinetDate(String loadingCabinetDate) {
            this.loadingCabinetDate = loadingCabinetDate;
        }

        public Object getCustomsDate() {
            return customsDate;
        }

        public void setCustomsDate(Object customsDate) {
            this.customsDate = customsDate;
        }

        public Object getShipmentDate() {
            return shipmentDate;
        }

        public void setShipmentDate(Object shipmentDate) {
            this.shipmentDate = shipmentDate;
        }

        public Object getReceiveLadingBillDate() {
            return receiveLadingBillDate;
        }

        public void setReceiveLadingBillDate(Object receiveLadingBillDate) {
            this.receiveLadingBillDate = receiveLadingBillDate;
        }

        public Object getReceivePaymentDate() {
            return receivePaymentDate;
        }

        public void setReceivePaymentDate(Object receivePaymentDate) {
            this.receivePaymentDate = receivePaymentDate;
        }

        public Object getDeliveryNoteDate() {
            return deliveryNoteDate;
        }

        public void setDeliveryNoteDate(Object deliveryNoteDate) {
            this.deliveryNoteDate = deliveryNoteDate;
        }

        public Object getPickUpDate() {
            return pickUpDate;
        }

        public void setPickUpDate(Object pickUpDate) {
            this.pickUpDate = pickUpDate;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getChangeDate() {
            return changeDate;
        }

        public void setChangeDate(String changeDate) {
            this.changeDate = changeDate;
        }

        public List<SalesContractsBean> getSalesContracts() {
            return salesContracts;
        }

        public void setSalesContracts(List<SalesContractsBean> salesContracts) {
            this.salesContracts = salesContracts;
        }

        public List<ForeignInvoiceDetailsBean> getForeignInvoiceDetails() {
            return foreignInvoiceDetails;
        }

        public void setForeignInvoiceDetails(List<ForeignInvoiceDetailsBean> foreignInvoiceDetails) {
            this.foreignInvoiceDetails = foreignInvoiceDetails;
        }

        public static class SalesContractsBean {
            /**
             * createdBy : 13064793669
             * createdDate : 2018-06-14
             * lastModifiedBy : 13064793669
             * lastModifiedDate : 2018-06-14
             * id : 6
             * deleted : false
             * sort : 0
             * fromClientType : null
             * member : null
             * type : {"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":679,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_type","desp":"销售合同类型","key":"saleconstract_pre_domestic","value":"事前内销合同","modifiable":null,"builtIn":false,"remark":"事前内销合同"}
             * status : {"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":682,"deleted":false,"sort":10,"fromClientType":null,"name":"salescontract_status","desp":"销售合同状态","key":"contract_draft","value":"草稿","modifiable":null,"builtIn":false,"remark":"草稿"}
             * operator : {"createdBy":"admin","createdDate":"2017-12-25","lastModifiedBy":"13064793669","lastModifiedDate":"2018-06-19","id":34,"deleted":false,"sort":30,"fromClientType":null,"username":"00000001","activated":false,"name":"刘伟","telOne":"13064793669","telTwo":null,"telThree":null,"address":null,"email":null,"sex":"FEMALE","birthday":"1994-06-02","superiorOperator":{"createdBy":"admin","createdDate":"2017-12-25","lastModifiedBy":"15167156690","lastModifiedDate":"2018-06-18","id":33,"deleted":false,"sort":65,"fromClientType":null,"username":"00000004","activated":true,"name":"吴楚昊","telOne":"15167156690","telTwo":null,"telThree":null,"address":null,"email":null,"sex":"MALE","birthday":"1996-04-15","superiorOperator":null,"department":{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"15167156690","lastModifiedDate":"2018-06-15","id":8,"deleted":false,"sort":70,"fromClientType":null,"parent":null,"name":"诸暨办事处","desp":"重庆、成都等","path":null,"totalCount":10,"subDepartmentCount":2,"sapVkbur":"1116","salesOffice":{"createdBy":"15058305380","createdDate":"2018-06-12","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-12","id":7,"deleted":false,"sort":10,"fromClientType":null,"vkbur":"1116","bezei":"诸暨办事处","departments":null},"marketTreands":null,"officeOrderApprovalConfigs":null},"position":{"createdBy":"15058305380","createdDate":"2018-05-23","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23","id":4,"deleted":false,"sort":10,"fromClientType":null,"name":"MARKETING_MINISTER","desp":"营销部长"},"avatarUrl":"http://img.jiuyisoft.com/icons8-boy-48.png","lastLoginDate":"2018-06-18 12:33:48","previousLoginDate":"2018-06-15T06:43:31Z","timIdentifier":"sysadmin","sapCode":null,"sapCname":null,"roles":[{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ADMINISTRATOR","desp":"系统管理员","builtIn":true,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":2,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OPERATOR_MANAGE","desp":"操作员管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":18,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_1tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":3,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MEMBER_MANAGE","desp":"客户管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":16,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGER","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":5,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_DEMAND_PLAN_MANAGE","desp":"要货计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":14,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SAP_DICT_MANAGER","desp":"SAP字典管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":13,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_CRM_DICT_MANAGER","desp":"系统字典管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":19,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":4,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ORDER_MANAGE","desp":"订单管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":7,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OFFICE_MANAGER","desp":"办事处主任","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":12,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ARTICLE_MANAGER","desp":"文章管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-13","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13","id":20,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tgR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":17,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":6,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SALE_MAN","desp":"业务员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":15,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MATERIAL_MANAGER","desp":"物料管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"18405863019","createdDate":"2018-05-23","lastModifiedBy":"18405863019","lastModifiedDate":"2018-05-23","id":24,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_GATHERING_PLAN_MANAGE","desp":"收款计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_HIGH_MANAGER","desp":"高管","builtIn":false,"operators":null,"checked":false},{"createdBy":"15167156690","createdDate":"2018-05-11","lastModifiedBy":"15167156690","lastModifiedDate":"2018-05-11","id":23,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MARKETING_ANALYSIS","desp":"营销分析员","builtIn":false,"operators":null,"checked":false}],"members":[],"dunningFailures":null,"shortWaybills":null,"officeOrderApprovalConfigs":null},"department":{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"15167156690","lastModifiedDate":"2018-06-14","id":9,"deleted":false,"sort":80,"fromClientType":null,"parent":null,"name":"海宁办事处","desp":null,"path":null,"totalCount":5,"subDepartmentCount":1,"sapVkbur":"1117","salesOffice":{"createdBy":"15058305380","createdDate":"2018-06-12","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-12","id":8,"deleted":false,"sort":10,"fromClientType":null,"vkbur":"1117","bezei":"海宁办事处","departments":null},"marketTreands":null,"officeOrderApprovalConfigs":null},"position":{"createdBy":"15058305380","createdDate":"2018-05-23","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23","id":2,"deleted":false,"sort":10,"fromClientType":null,"name":"MARKETING_DEPARTMENT_MINISTER","desp":"市场部部长"},"avatarUrl":"http://img.jiuyisoft.com/icons8-girl-48.png","lastLoginDate":"2018-06-19 10:24:46","previousLoginDate":"2018-06-19T02:08:28Z","timIdentifier":"huafon_crm","sapCode":null,"sapCname":null,"roles":[{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":2,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OPERATOR_MANAGE","desp":"操作员管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":3,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MEMBER_MANAGE","desp":"客户管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":16,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGER","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":5,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_DEMAND_PLAN_MANAGE","desp":"要货计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":14,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SAP_DICT_MANAGER","desp":"SAP字典管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":19,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":7,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OFFICE_MANAGER","desp":"办事处主任","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":12,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ARTICLE_MANAGER","desp":"文章管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-13","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13","id":20,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tgR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":17,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":6,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SALE_MAN","desp":"业务员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":15,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MATERIAL_MANAGER","desp":"物料管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"18405863019","createdDate":"2018-05-23","lastModifiedBy":"18405863019","lastModifiedDate":"2018-05-23","id":24,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_GATHERING_PLAN_MANAGE","desp":"收款计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_HIGH_MANAGER","desp":"高管","builtIn":false,"operators":null,"checked":false},{"createdBy":"15167156690","createdDate":"2018-05-11","lastModifiedBy":"15167156690","lastModifiedDate":"2018-05-11","id":23,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MARKETING_ANALYSIS","desp":"营销分析员","builtIn":false,"operators":null,"checked":false}],"members":[],"dunningFailures":null,"shortWaybills":null,"officeOrderApprovalConfigs":null}
             * number : HFHT20180614006
             * infoDate : 2018-06-14
             * startDate : null
             * endDate : null
             * postBeginDate : null
             * postEndDate : null
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
            private Object fromClientType;
            private Object member;
            private TypeBean type;
            private StatusBean status;
            private OperatorBean operator;
            private String number;
            private String infoDate;
            private Object startDate;
            private Object endDate;
            private Object postBeginDate;
            private Object postEndDate;
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
            private Object internationalTradeClause;
            private Object foreignInvoiceTrackings;
            private Object currency;
            private Object payWay;
            private Object tradeWay;
            private Object attachments;
            private Object salesContractMaterials;
            private Object salesContractInvoices;

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

            public Object getMember() {
                return member;
            }

            public void setMember(Object member) {
                this.member = member;
            }

            public TypeBean getType() {
                return type;
            }

            public void setType(TypeBean type) {
                this.type = type;
            }

            public StatusBean getStatus() {
                return status;
            }

            public void setStatus(StatusBean status) {
                this.status = status;
            }

            public OperatorBean getOperator() {
                return operator;
            }

            public void setOperator(OperatorBean operator) {
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

            public Object getPostBeginDate() {
                return postBeginDate;
            }

            public void setPostBeginDate(Object postBeginDate) {
                this.postBeginDate = postBeginDate;
            }

            public Object getPostEndDate() {
                return postEndDate;
            }

            public void setPostEndDate(Object postEndDate) {
                this.postEndDate = postEndDate;
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

            public Object getAttachments() {
                return attachments;
            }

            public void setAttachments(Object attachments) {
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

            public static class TypeBean {
                /**
                 * createdBy : 18405863019
                 * createdDate : 2018-06-14
                 * lastModifiedBy : 18405863019
                 * lastModifiedDate : 2018-06-14
                 * id : 679
                 * deleted : false
                 * sort : 10
                 * fromClientType : null
                 * name : salescontract_type
                 * desp : 销售合同类型
                 * key : saleconstract_pre_domestic
                 * value : 事前内销合同
                 * modifiable : null
                 * builtIn : false
                 * remark : 事前内销合同
                 */

                private String createdBy;
                private String createdDate;
                private String lastModifiedBy;
                private String lastModifiedDate;
                private long id;
                private boolean deleted;
                private int sort;
                private Object fromClientType;
                private String name;
                private String desp;
                private String key;
                private String value;
                private Object modifiable;
                private boolean builtIn;
                private String remark;

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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDesp() {
                    return desp;
                }

                public void setDesp(String desp) {
                    this.desp = desp;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public Object getModifiable() {
                    return modifiable;
                }

                public void setModifiable(Object modifiable) {
                    this.modifiable = modifiable;
                }

                public boolean isBuiltIn() {
                    return builtIn;
                }

                public void setBuiltIn(boolean builtIn) {
                    this.builtIn = builtIn;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }
            }

            public static class StatusBean {
                /**
                 * createdBy : 18405863019
                 * createdDate : 2018-06-14
                 * lastModifiedBy : 18405863019
                 * lastModifiedDate : 2018-06-14
                 * id : 682
                 * deleted : false
                 * sort : 10
                 * fromClientType : null
                 * name : salescontract_status
                 * desp : 销售合同状态
                 * key : contract_draft
                 * value : 草稿
                 * modifiable : null
                 * builtIn : false
                 * remark : 草稿
                 */

                private String createdBy;
                private String createdDate;
                private String lastModifiedBy;
                private String lastModifiedDate;
                private long id;
                private boolean deleted;
                private int sort;
                private Object fromClientType;
                private String name;
                private String desp;
                private String key;
                private String value;
                private Object modifiable;
                private boolean builtIn;
                private String remark;

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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDesp() {
                    return desp;
                }

                public void setDesp(String desp) {
                    this.desp = desp;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public Object getModifiable() {
                    return modifiable;
                }

                public void setModifiable(Object modifiable) {
                    this.modifiable = modifiable;
                }

                public boolean isBuiltIn() {
                    return builtIn;
                }

                public void setBuiltIn(boolean builtIn) {
                    this.builtIn = builtIn;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }
            }

            public static class OperatorBean {
                /**
                 * createdBy : admin
                 * createdDate : 2017-12-25
                 * lastModifiedBy : 13064793669
                 * lastModifiedDate : 2018-06-19
                 * id : 34
                 * deleted : false
                 * sort : 30
                 * fromClientType : null
                 * username : 00000001
                 * activated : false
                 * name : 刘伟
                 * telOne : 13064793669
                 * telTwo : null
                 * telThree : null
                 * address : null
                 * email : null
                 * sex : FEMALE
                 * birthday : 1994-06-02
                 * superiorOperator : {"createdBy":"admin","createdDate":"2017-12-25","lastModifiedBy":"15167156690","lastModifiedDate":"2018-06-18","id":33,"deleted":false,"sort":65,"fromClientType":null,"username":"00000004","activated":true,"name":"吴楚昊","telOne":"15167156690","telTwo":null,"telThree":null,"address":null,"email":null,"sex":"MALE","birthday":"1996-04-15","superiorOperator":null,"department":{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"15167156690","lastModifiedDate":"2018-06-15","id":8,"deleted":false,"sort":70,"fromClientType":null,"parent":null,"name":"诸暨办事处","desp":"重庆、成都等","path":null,"totalCount":10,"subDepartmentCount":2,"sapVkbur":"1116","salesOffice":{"createdBy":"15058305380","createdDate":"2018-06-12","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-12","id":7,"deleted":false,"sort":10,"fromClientType":null,"vkbur":"1116","bezei":"诸暨办事处","departments":null},"marketTreands":null,"officeOrderApprovalConfigs":null},"position":{"createdBy":"15058305380","createdDate":"2018-05-23","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23","id":4,"deleted":false,"sort":10,"fromClientType":null,"name":"MARKETING_MINISTER","desp":"营销部长"},"avatarUrl":"http://img.jiuyisoft.com/icons8-boy-48.png","lastLoginDate":"2018-06-18 12:33:48","previousLoginDate":"2018-06-15T06:43:31Z","timIdentifier":"sysadmin","sapCode":null,"sapCname":null,"roles":[{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ADMINISTRATOR","desp":"系统管理员","builtIn":true,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":2,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OPERATOR_MANAGE","desp":"操作员管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":18,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_1tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":3,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MEMBER_MANAGE","desp":"客户管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":16,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGER","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":5,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_DEMAND_PLAN_MANAGE","desp":"要货计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":14,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SAP_DICT_MANAGER","desp":"SAP字典管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":13,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_CRM_DICT_MANAGER","desp":"系统字典管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":19,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":4,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ORDER_MANAGE","desp":"订单管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":7,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OFFICE_MANAGER","desp":"办事处主任","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":12,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ARTICLE_MANAGER","desp":"文章管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-13","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13","id":20,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tgR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":17,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":6,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SALE_MAN","desp":"业务员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":15,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MATERIAL_MANAGER","desp":"物料管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"18405863019","createdDate":"2018-05-23","lastModifiedBy":"18405863019","lastModifiedDate":"2018-05-23","id":24,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_GATHERING_PLAN_MANAGE","desp":"收款计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_HIGH_MANAGER","desp":"高管","builtIn":false,"operators":null,"checked":false},{"createdBy":"15167156690","createdDate":"2018-05-11","lastModifiedBy":"15167156690","lastModifiedDate":"2018-05-11","id":23,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MARKETING_ANALYSIS","desp":"营销分析员","builtIn":false,"operators":null,"checked":false}],"members":[],"dunningFailures":null,"shortWaybills":null,"officeOrderApprovalConfigs":null}
                 * department : {"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"15167156690","lastModifiedDate":"2018-06-14","id":9,"deleted":false,"sort":80,"fromClientType":null,"parent":null,"name":"海宁办事处","desp":null,"path":null,"totalCount":5,"subDepartmentCount":1,"sapVkbur":"1117","salesOffice":{"createdBy":"15058305380","createdDate":"2018-06-12","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-12","id":8,"deleted":false,"sort":10,"fromClientType":null,"vkbur":"1117","bezei":"海宁办事处","departments":null},"marketTreands":null,"officeOrderApprovalConfigs":null}
                 * position : {"createdBy":"15058305380","createdDate":"2018-05-23","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23","id":2,"deleted":false,"sort":10,"fromClientType":null,"name":"MARKETING_DEPARTMENT_MINISTER","desp":"市场部部长"}
                 * avatarUrl : http://img.jiuyisoft.com/icons8-girl-48.png
                 * lastLoginDate : 2018-06-19 10:24:46
                 * previousLoginDate : 2018-06-19T02:08:28Z
                 * timIdentifier : huafon_crm
                 * sapCode : null
                 * sapCname : null
                 * roles : [{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":2,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OPERATOR_MANAGE","desp":"操作员管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":3,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MEMBER_MANAGE","desp":"客户管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":16,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGER","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":5,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_DEMAND_PLAN_MANAGE","desp":"要货计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":14,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SAP_DICT_MANAGER","desp":"SAP字典管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":19,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":7,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OFFICE_MANAGER","desp":"办事处主任","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":12,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ARTICLE_MANAGER","desp":"文章管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-13","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13","id":20,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tgR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":17,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":6,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SALE_MAN","desp":"业务员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":15,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MATERIAL_MANAGER","desp":"物料管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"18405863019","createdDate":"2018-05-23","lastModifiedBy":"18405863019","lastModifiedDate":"2018-05-23","id":24,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_GATHERING_PLAN_MANAGE","desp":"收款计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_HIGH_MANAGER","desp":"高管","builtIn":false,"operators":null,"checked":false},{"createdBy":"15167156690","createdDate":"2018-05-11","lastModifiedBy":"15167156690","lastModifiedDate":"2018-05-11","id":23,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MARKETING_ANALYSIS","desp":"营销分析员","builtIn":false,"operators":null,"checked":false}]
                 * members : []
                 * dunningFailures : null
                 * shortWaybills : null
                 * officeOrderApprovalConfigs : null
                 */

                private String createdBy;
                private String createdDate;
                private String lastModifiedBy;
                private String lastModifiedDate;
                private long id;
                private boolean deleted;
                private int sort;
                private Object fromClientType;
                private String username;
                private boolean activated;
                private String name;
                private String telOne;
                private Object telTwo;
                private Object telThree;
                private Object address;
                private Object email;
                private String sex;
                private String birthday;
                private SuperiorOperatorBean superiorOperator;
                private DepartmentBeanX department;
                private PositionBeanX position;
                private String avatarUrl;
                private String lastLoginDate;
                private String previousLoginDate;
                private String timIdentifier;
                private Object sapCode;
                private Object sapCname;
                private Object dunningFailures;
                private Object shortWaybills;
                private Object officeOrderApprovalConfigs;
                private List<RolesBeanX> roles;
                private List<?> members;

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

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public boolean isActivated() {
                    return activated;
                }

                public void setActivated(boolean activated) {
                    this.activated = activated;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTelOne() {
                    return telOne;
                }

                public void setTelOne(String telOne) {
                    this.telOne = telOne;
                }

                public Object getTelTwo() {
                    return telTwo;
                }

                public void setTelTwo(Object telTwo) {
                    this.telTwo = telTwo;
                }

                public Object getTelThree() {
                    return telThree;
                }

                public void setTelThree(Object telThree) {
                    this.telThree = telThree;
                }

                public Object getAddress() {
                    return address;
                }

                public void setAddress(Object address) {
                    this.address = address;
                }

                public Object getEmail() {
                    return email;
                }

                public void setEmail(Object email) {
                    this.email = email;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getBirthday() {
                    return birthday;
                }

                public void setBirthday(String birthday) {
                    this.birthday = birthday;
                }

                public SuperiorOperatorBean getSuperiorOperator() {
                    return superiorOperator;
                }

                public void setSuperiorOperator(SuperiorOperatorBean superiorOperator) {
                    this.superiorOperator = superiorOperator;
                }

                public DepartmentBeanX getDepartment() {
                    return department;
                }

                public void setDepartment(DepartmentBeanX department) {
                    this.department = department;
                }

                public PositionBeanX getPosition() {
                    return position;
                }

                public void setPosition(PositionBeanX position) {
                    this.position = position;
                }

                public String getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(String avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }

                public String getLastLoginDate() {
                    return lastLoginDate;
                }

                public void setLastLoginDate(String lastLoginDate) {
                    this.lastLoginDate = lastLoginDate;
                }

                public String getPreviousLoginDate() {
                    return previousLoginDate;
                }

                public void setPreviousLoginDate(String previousLoginDate) {
                    this.previousLoginDate = previousLoginDate;
                }

                public String getTimIdentifier() {
                    return timIdentifier;
                }

                public void setTimIdentifier(String timIdentifier) {
                    this.timIdentifier = timIdentifier;
                }

                public Object getSapCode() {
                    return sapCode;
                }

                public void setSapCode(Object sapCode) {
                    this.sapCode = sapCode;
                }

                public Object getSapCname() {
                    return sapCname;
                }

                public void setSapCname(Object sapCname) {
                    this.sapCname = sapCname;
                }

                public Object getDunningFailures() {
                    return dunningFailures;
                }

                public void setDunningFailures(Object dunningFailures) {
                    this.dunningFailures = dunningFailures;
                }

                public Object getShortWaybills() {
                    return shortWaybills;
                }

                public void setShortWaybills(Object shortWaybills) {
                    this.shortWaybills = shortWaybills;
                }

                public Object getOfficeOrderApprovalConfigs() {
                    return officeOrderApprovalConfigs;
                }

                public void setOfficeOrderApprovalConfigs(Object officeOrderApprovalConfigs) {
                    this.officeOrderApprovalConfigs = officeOrderApprovalConfigs;
                }

                public List<RolesBeanX> getRoles() {
                    return roles;
                }

                public void setRoles(List<RolesBeanX> roles) {
                    this.roles = roles;
                }

                public List<?> getMembers() {
                    return members;
                }

                public void setMembers(List<?> members) {
                    this.members = members;
                }

                public static class SuperiorOperatorBean {
                    /**
                     * createdBy : admin
                     * createdDate : 2017-12-25
                     * lastModifiedBy : 15167156690
                     * lastModifiedDate : 2018-06-18
                     * id : 33
                     * deleted : false
                     * sort : 65
                     * fromClientType : null
                     * username : 00000004
                     * activated : true
                     * name : 吴楚昊
                     * telOne : 15167156690
                     * telTwo : null
                     * telThree : null
                     * address : null
                     * email : null
                     * sex : MALE
                     * birthday : 1996-04-15
                     * superiorOperator : null
                     * department : {"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"15167156690","lastModifiedDate":"2018-06-15","id":8,"deleted":false,"sort":70,"fromClientType":null,"parent":null,"name":"诸暨办事处","desp":"重庆、成都等","path":null,"totalCount":10,"subDepartmentCount":2,"sapVkbur":"1116","salesOffice":{"createdBy":"15058305380","createdDate":"2018-06-12","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-12","id":7,"deleted":false,"sort":10,"fromClientType":null,"vkbur":"1116","bezei":"诸暨办事处","departments":null},"marketTreands":null,"officeOrderApprovalConfigs":null}
                     * position : {"createdBy":"15058305380","createdDate":"2018-05-23","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23","id":4,"deleted":false,"sort":10,"fromClientType":null,"name":"MARKETING_MINISTER","desp":"营销部长"}
                     * avatarUrl : http://img.jiuyisoft.com/icons8-boy-48.png
                     * lastLoginDate : 2018-06-18 12:33:48
                     * previousLoginDate : 2018-06-15T06:43:31Z
                     * timIdentifier : sysadmin
                     * sapCode : null
                     * sapCname : null
                     * roles : [{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ADMINISTRATOR","desp":"系统管理员","builtIn":true,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":2,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OPERATOR_MANAGE","desp":"操作员管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":18,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_1tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":3,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MEMBER_MANAGE","desp":"客户管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":16,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGER","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":5,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_DEMAND_PLAN_MANAGE","desp":"要货计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":14,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SAP_DICT_MANAGER","desp":"SAP字典管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":13,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_CRM_DICT_MANAGER","desp":"系统字典管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":19,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":4,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ORDER_MANAGE","desp":"订单管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":7,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_OFFICE_MANAGER","desp":"办事处主任","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":12,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_ARTICLE_MANAGER","desp":"文章管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-13","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13","id":20,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_F1tgR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-12","lastModifiedBy":"admin","lastModifiedDate":"2018-04-12","id":17,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_TASK_MANAGE\u2014_tR","desp":"任务管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":6,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_SALE_MAN","desp":"业务员","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-06","id":15,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MATERIAL_MANAGER","desp":"物料管理员","builtIn":false,"operators":null,"checked":false},{"createdBy":"18405863019","createdDate":"2018-05-23","lastModifiedBy":"18405863019","lastModifiedDate":"2018-05-23","id":24,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_GATHERING_PLAN_MANAGE","desp":"收款计划管理","builtIn":false,"operators":null,"checked":false},{"createdBy":"admin","createdDate":"2018-04-05","lastModifiedBy":"admin","lastModifiedDate":"2018-04-05","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_HIGH_MANAGER","desp":"高管","builtIn":false,"operators":null,"checked":false},{"createdBy":"15167156690","createdDate":"2018-05-11","lastModifiedBy":"15167156690","lastModifiedDate":"2018-05-11","id":23,"deleted":false,"sort":10,"fromClientType":null,"name":"ROLE_MARKETING_ANALYSIS","desp":"营销分析员","builtIn":false,"operators":null,"checked":false}]
                     * members : []
                     * dunningFailures : null
                     * shortWaybills : null
                     * officeOrderApprovalConfigs : null
                     */

                    private String createdBy;
                    private String createdDate;
                    private String lastModifiedBy;
                    private String lastModifiedDate;
                    private long id;
                    private boolean deleted;
                    private int sort;
                    private Object fromClientType;
                    private String username;
                    private boolean activated;
                    private String name;
                    private String telOne;
                    private Object telTwo;
                    private Object telThree;
                    private Object address;
                    private Object email;
                    private String sex;
                    private String birthday;
                    private Object superiorOperator;
                    private DepartmentBean department;
                    private PositionBean position;
                    private String avatarUrl;
                    private String lastLoginDate;
                    private String previousLoginDate;
                    private String timIdentifier;
                    private Object sapCode;
                    private Object sapCname;
                    private Object dunningFailures;
                    private Object shortWaybills;
                    private Object officeOrderApprovalConfigs;
                    private List<RolesBean> roles;
                    private List<?> members;

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

                    public String getUsername() {
                        return username;
                    }

                    public void setUsername(String username) {
                        this.username = username;
                    }

                    public boolean isActivated() {
                        return activated;
                    }

                    public void setActivated(boolean activated) {
                        this.activated = activated;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getTelOne() {
                        return telOne;
                    }

                    public void setTelOne(String telOne) {
                        this.telOne = telOne;
                    }

                    public Object getTelTwo() {
                        return telTwo;
                    }

                    public void setTelTwo(Object telTwo) {
                        this.telTwo = telTwo;
                    }

                    public Object getTelThree() {
                        return telThree;
                    }

                    public void setTelThree(Object telThree) {
                        this.telThree = telThree;
                    }

                    public Object getAddress() {
                        return address;
                    }

                    public void setAddress(Object address) {
                        this.address = address;
                    }

                    public Object getEmail() {
                        return email;
                    }

                    public void setEmail(Object email) {
                        this.email = email;
                    }

                    public String getSex() {
                        return sex;
                    }

                    public void setSex(String sex) {
                        this.sex = sex;
                    }

                    public String getBirthday() {
                        return birthday;
                    }

                    public void setBirthday(String birthday) {
                        this.birthday = birthday;
                    }

                    public Object getSuperiorOperator() {
                        return superiorOperator;
                    }

                    public void setSuperiorOperator(Object superiorOperator) {
                        this.superiorOperator = superiorOperator;
                    }

                    public DepartmentBean getDepartment() {
                        return department;
                    }

                    public void setDepartment(DepartmentBean department) {
                        this.department = department;
                    }

                    public PositionBean getPosition() {
                        return position;
                    }

                    public void setPosition(PositionBean position) {
                        this.position = position;
                    }

                    public String getAvatarUrl() {
                        return avatarUrl;
                    }

                    public void setAvatarUrl(String avatarUrl) {
                        this.avatarUrl = avatarUrl;
                    }

                    public String getLastLoginDate() {
                        return lastLoginDate;
                    }

                    public void setLastLoginDate(String lastLoginDate) {
                        this.lastLoginDate = lastLoginDate;
                    }

                    public String getPreviousLoginDate() {
                        return previousLoginDate;
                    }

                    public void setPreviousLoginDate(String previousLoginDate) {
                        this.previousLoginDate = previousLoginDate;
                    }

                    public String getTimIdentifier() {
                        return timIdentifier;
                    }

                    public void setTimIdentifier(String timIdentifier) {
                        this.timIdentifier = timIdentifier;
                    }

                    public Object getSapCode() {
                        return sapCode;
                    }

                    public void setSapCode(Object sapCode) {
                        this.sapCode = sapCode;
                    }

                    public Object getSapCname() {
                        return sapCname;
                    }

                    public void setSapCname(Object sapCname) {
                        this.sapCname = sapCname;
                    }

                    public Object getDunningFailures() {
                        return dunningFailures;
                    }

                    public void setDunningFailures(Object dunningFailures) {
                        this.dunningFailures = dunningFailures;
                    }

                    public Object getShortWaybills() {
                        return shortWaybills;
                    }

                    public void setShortWaybills(Object shortWaybills) {
                        this.shortWaybills = shortWaybills;
                    }

                    public Object getOfficeOrderApprovalConfigs() {
                        return officeOrderApprovalConfigs;
                    }

                    public void setOfficeOrderApprovalConfigs(Object officeOrderApprovalConfigs) {
                        this.officeOrderApprovalConfigs = officeOrderApprovalConfigs;
                    }

                    public List<RolesBean> getRoles() {
                        return roles;
                    }

                    public void setRoles(List<RolesBean> roles) {
                        this.roles = roles;
                    }

                    public List<?> getMembers() {
                        return members;
                    }

                    public void setMembers(List<?> members) {
                        this.members = members;
                    }

                    public static class DepartmentBean {
                        /**
                         * createdBy : admin
                         * createdDate : 2018-04-06
                         * lastModifiedBy : 15167156690
                         * lastModifiedDate : 2018-06-15
                         * id : 8
                         * deleted : false
                         * sort : 70
                         * fromClientType : null
                         * parent : null
                         * name : 诸暨办事处
                         * desp : 重庆、成都等
                         * path : null
                         * totalCount : 10
                         * subDepartmentCount : 2
                         * sapVkbur : 1116
                         * salesOffice : {"createdBy":"15058305380","createdDate":"2018-06-12","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-12","id":7,"deleted":false,"sort":10,"fromClientType":null,"vkbur":"1116","bezei":"诸暨办事处","departments":null}
                         * marketTreands : null
                         * officeOrderApprovalConfigs : null
                         */

                        private String createdBy;
                        private String createdDate;
                        private String lastModifiedBy;
                        private String lastModifiedDate;
                        private long id;
                        private boolean deleted;
                        private int sort;
                        private Object fromClientType;
                        private Object parent;
                        private String name;
                        private String desp;
                        private Object path;
                        private int totalCount;
                        private int subDepartmentCount;
                        private String sapVkbur;
                        private SalesOfficeBean salesOffice;
                        private Object marketTreands;
                        private Object officeOrderApprovalConfigs;

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

                        public Object getParent() {
                            return parent;
                        }

                        public void setParent(Object parent) {
                            this.parent = parent;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getDesp() {
                            return desp;
                        }

                        public void setDesp(String desp) {
                            this.desp = desp;
                        }

                        public Object getPath() {
                            return path;
                        }

                        public void setPath(Object path) {
                            this.path = path;
                        }

                        public int getTotalCount() {
                            return totalCount;
                        }

                        public void setTotalCount(int totalCount) {
                            this.totalCount = totalCount;
                        }

                        public int getSubDepartmentCount() {
                            return subDepartmentCount;
                        }

                        public void setSubDepartmentCount(int subDepartmentCount) {
                            this.subDepartmentCount = subDepartmentCount;
                        }

                        public String getSapVkbur() {
                            return sapVkbur;
                        }

                        public void setSapVkbur(String sapVkbur) {
                            this.sapVkbur = sapVkbur;
                        }

                        public SalesOfficeBean getSalesOffice() {
                            return salesOffice;
                        }

                        public void setSalesOffice(SalesOfficeBean salesOffice) {
                            this.salesOffice = salesOffice;
                        }

                        public Object getMarketTreands() {
                            return marketTreands;
                        }

                        public void setMarketTreands(Object marketTreands) {
                            this.marketTreands = marketTreands;
                        }

                        public Object getOfficeOrderApprovalConfigs() {
                            return officeOrderApprovalConfigs;
                        }

                        public void setOfficeOrderApprovalConfigs(Object officeOrderApprovalConfigs) {
                            this.officeOrderApprovalConfigs = officeOrderApprovalConfigs;
                        }

                        public static class SalesOfficeBean {
                            /**
                             * createdBy : 15058305380
                             * createdDate : 2018-06-12
                             * lastModifiedBy : 15058305380
                             * lastModifiedDate : 2018-06-12
                             * id : 7
                             * deleted : false
                             * sort : 10
                             * fromClientType : null
                             * vkbur : 1116
                             * bezei : 诸暨办事处
                             * departments : null
                             */

                            private String createdBy;
                            private String createdDate;
                            private String lastModifiedBy;
                            private String lastModifiedDate;
                            private long id;
                            private boolean deleted;
                            private int sort;
                            private Object fromClientType;
                            private String vkbur;
                            private String bezei;
                            private Object departments;

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

                            public String getVkbur() {
                                return vkbur;
                            }

                            public void setVkbur(String vkbur) {
                                this.vkbur = vkbur;
                            }

                            public String getBezei() {
                                return bezei;
                            }

                            public void setBezei(String bezei) {
                                this.bezei = bezei;
                            }

                            public Object getDepartments() {
                                return departments;
                            }

                            public void setDepartments(Object departments) {
                                this.departments = departments;
                            }
                        }
                    }

                    public static class PositionBean {
                        /**
                         * createdBy : 15058305380
                         * createdDate : 2018-05-23
                         * lastModifiedBy : 15058305380
                         * lastModifiedDate : 2018-05-23
                         * id : 4
                         * deleted : false
                         * sort : 10
                         * fromClientType : null
                         * name : MARKETING_MINISTER
                         * desp : 营销部长
                         */

                        private String createdBy;
                        private String createdDate;
                        private String lastModifiedBy;
                        private String lastModifiedDate;
                        private long id;
                        private boolean deleted;
                        private int sort;
                        private Object fromClientType;
                        private String name;
                        private String desp;

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

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getDesp() {
                            return desp;
                        }

                        public void setDesp(String desp) {
                            this.desp = desp;
                        }
                    }

                    public static class RolesBean {
                        /**
                         * createdBy : admin
                         * createdDate : 2018-04-05
                         * lastModifiedBy : admin
                         * lastModifiedDate : 2018-04-12
                         * id : 1
                         * deleted : false
                         * sort : 10
                         * fromClientType : null
                         * name : ROLE_ADMINISTRATOR
                         * desp : 系统管理员
                         * builtIn : true
                         * operators : null
                         * checked : false
                         */

                        private String createdBy;
                        private String createdDate;
                        private String lastModifiedBy;
                        private String lastModifiedDate;
                        private long id;
                        private boolean deleted;
                        private int sort;
                        private Object fromClientType;
                        private String name;
                        private String desp;
                        private boolean builtIn;
                        private Object operators;
                        private boolean checked;

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

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getDesp() {
                            return desp;
                        }

                        public void setDesp(String desp) {
                            this.desp = desp;
                        }

                        public boolean isBuiltIn() {
                            return builtIn;
                        }

                        public void setBuiltIn(boolean builtIn) {
                            this.builtIn = builtIn;
                        }

                        public Object getOperators() {
                            return operators;
                        }

                        public void setOperators(Object operators) {
                            this.operators = operators;
                        }

                        public boolean isChecked() {
                            return checked;
                        }

                        public void setChecked(boolean checked) {
                            this.checked = checked;
                        }
                    }
                }

                public static class DepartmentBeanX {
                    /**
                     * createdBy : admin
                     * createdDate : 2018-04-06
                     * lastModifiedBy : 15167156690
                     * lastModifiedDate : 2018-06-14
                     * id : 9
                     * deleted : false
                     * sort : 80
                     * fromClientType : null
                     * parent : null
                     * name : 海宁办事处
                     * desp : null
                     * path : null
                     * totalCount : 5
                     * subDepartmentCount : 1
                     * sapVkbur : 1117
                     * salesOffice : {"createdBy":"15058305380","createdDate":"2018-06-12","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-12","id":8,"deleted":false,"sort":10,"fromClientType":null,"vkbur":"1117","bezei":"海宁办事处","departments":null}
                     * marketTreands : null
                     * officeOrderApprovalConfigs : null
                     */

                    private String createdBy;
                    private String createdDate;
                    private String lastModifiedBy;
                    private String lastModifiedDate;
                    private long id;
                    private boolean deleted;
                    private int sort;
                    private Object fromClientType;
                    private Object parent;
                    private String name;
                    private Object desp;
                    private Object path;
                    private int totalCount;
                    private int subDepartmentCount;
                    private String sapVkbur;
                    private SalesOfficeBeanX salesOffice;
                    private Object marketTreands;
                    private Object officeOrderApprovalConfigs;

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

                    public Object getParent() {
                        return parent;
                    }

                    public void setParent(Object parent) {
                        this.parent = parent;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Object getDesp() {
                        return desp;
                    }

                    public void setDesp(Object desp) {
                        this.desp = desp;
                    }

                    public Object getPath() {
                        return path;
                    }

                    public void setPath(Object path) {
                        this.path = path;
                    }

                    public int getTotalCount() {
                        return totalCount;
                    }

                    public void setTotalCount(int totalCount) {
                        this.totalCount = totalCount;
                    }

                    public int getSubDepartmentCount() {
                        return subDepartmentCount;
                    }

                    public void setSubDepartmentCount(int subDepartmentCount) {
                        this.subDepartmentCount = subDepartmentCount;
                    }

                    public String getSapVkbur() {
                        return sapVkbur;
                    }

                    public void setSapVkbur(String sapVkbur) {
                        this.sapVkbur = sapVkbur;
                    }

                    public SalesOfficeBeanX getSalesOffice() {
                        return salesOffice;
                    }

                    public void setSalesOffice(SalesOfficeBeanX salesOffice) {
                        this.salesOffice = salesOffice;
                    }

                    public Object getMarketTreands() {
                        return marketTreands;
                    }

                    public void setMarketTreands(Object marketTreands) {
                        this.marketTreands = marketTreands;
                    }

                    public Object getOfficeOrderApprovalConfigs() {
                        return officeOrderApprovalConfigs;
                    }

                    public void setOfficeOrderApprovalConfigs(Object officeOrderApprovalConfigs) {
                        this.officeOrderApprovalConfigs = officeOrderApprovalConfigs;
                    }

                    public static class SalesOfficeBeanX {
                        /**
                         * createdBy : 15058305380
                         * createdDate : 2018-06-12
                         * lastModifiedBy : 15058305380
                         * lastModifiedDate : 2018-06-12
                         * id : 8
                         * deleted : false
                         * sort : 10
                         * fromClientType : null
                         * vkbur : 1117
                         * bezei : 海宁办事处
                         * departments : null
                         */

                        private String createdBy;
                        private String createdDate;
                        private String lastModifiedBy;
                        private String lastModifiedDate;
                        private long id;
                        private boolean deleted;
                        private int sort;
                        private Object fromClientType;
                        private String vkbur;
                        private String bezei;
                        private Object departments;

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

                        public String getVkbur() {
                            return vkbur;
                        }

                        public void setVkbur(String vkbur) {
                            this.vkbur = vkbur;
                        }

                        public String getBezei() {
                            return bezei;
                        }

                        public void setBezei(String bezei) {
                            this.bezei = bezei;
                        }

                        public Object getDepartments() {
                            return departments;
                        }

                        public void setDepartments(Object departments) {
                            this.departments = departments;
                        }
                    }
                }

                public static class PositionBeanX {
                    /**
                     * createdBy : 15058305380
                     * createdDate : 2018-05-23
                     * lastModifiedBy : 15058305380
                     * lastModifiedDate : 2018-05-23
                     * id : 2
                     * deleted : false
                     * sort : 10
                     * fromClientType : null
                     * name : MARKETING_DEPARTMENT_MINISTER
                     * desp : 市场部部长
                     */

                    private String createdBy;
                    private String createdDate;
                    private String lastModifiedBy;
                    private String lastModifiedDate;
                    private long id;
                    private boolean deleted;
                    private int sort;
                    private Object fromClientType;
                    private String name;
                    private String desp;

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

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getDesp() {
                        return desp;
                    }

                    public void setDesp(String desp) {
                        this.desp = desp;
                    }
                }

                public static class RolesBeanX {
                    /**
                     * createdBy : admin
                     * createdDate : 2018-04-05
                     * lastModifiedBy : admin
                     * lastModifiedDate : 2018-04-05
                     * id : 2
                     * deleted : false
                     * sort : 10
                     * fromClientType : null
                     * name : ROLE_OPERATOR_MANAGE
                     * desp : 操作员管理
                     * builtIn : false
                     * operators : null
                     * checked : false
                     */

                    private String createdBy;
                    private String createdDate;
                    private String lastModifiedBy;
                    private String lastModifiedDate;
                    private long id;
                    private boolean deleted;
                    private int sort;
                    private Object fromClientType;
                    private String name;
                    private String desp;
                    private boolean builtIn;
                    private Object operators;
                    private boolean checked;

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

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getDesp() {
                        return desp;
                    }

                    public void setDesp(String desp) {
                        this.desp = desp;
                    }

                    public boolean isBuiltIn() {
                        return builtIn;
                    }

                    public void setBuiltIn(boolean builtIn) {
                        this.builtIn = builtIn;
                    }

                    public Object getOperators() {
                        return operators;
                    }

                    public void setOperators(Object operators) {
                        this.operators = operators;
                    }

                    public boolean isChecked() {
                        return checked;
                    }

                    public void setChecked(boolean checked) {
                        this.checked = checked;
                    }
                }
            }
        }

        public static class ForeignInvoiceDetailsBean {
            /**
             * createdBy : 18405863019
             * createdDate : 2018-05-14
             * lastModifiedBy : null
             * lastModifiedDate : null
             * id : 1
             * deleted : null
             * sort : null
             * fromClientType : null
             * sapInvoice : {"createdBy":"11","createdDate":"2018-06-09","lastModifiedBy":null,"lastModifiedDate":null,"id":1,"deleted":null,"sort":null,"fromClientType":null,"orderNumber":"4500015246","lineNumber":null,"quantity":null,"amount":null,"deliveryDate":null,"denier":"37","zzszs":800,"zksks":3,"number":"A0908","invoiceNumber":"9180000000","invoiceLineNumber":"000010","materialNumber":null,"materialBatchNumber":"020","materialSpec":"1130","orderSapNumber":null,"orderLineNumber":"10","shippedQuantity":100,"status":"DELIVERY","licensePlateNumber":null,"driverName":null,"driverPhone":null,"memberName":"大松树","member":null,"posnr":null,"werks":null,"lgort":null,"vrkme":null,"bstkd":null,"posex":null,"kunnr":null,"wadatist":null,"wbstk":null,"ernam":null,"aedat":null,"maktx":null,"volum":0,"memo":null,"lfart":null,"foreignInvoiceDetails":null}
             * shipmentQuantity : 120
             * weightBoxType : 小箱
             * volumeBoxType : 222
             * netWeight : 444
             * grossWeight : 13300
             * volume : 111
             */

            private String createdBy;
            private String createdDate;
            private Object lastModifiedBy;
            private Object lastModifiedDate;
            private long id;
            private Object deleted;
            private Object sort;
            private Object fromClientType;
            private SapInvoiceBean sapInvoice;
            private int shipmentQuantity;
            private String weightBoxType;
            private String volumeBoxType;
            private int netWeight;
            private int grossWeight;
            private int volume;

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

            public SapInvoiceBean getSapInvoice() {
                return sapInvoice;
            }

            public void setSapInvoice(SapInvoiceBean sapInvoice) {
                this.sapInvoice = sapInvoice;
            }

            public int getShipmentQuantity() {
                return shipmentQuantity;
            }

            public void setShipmentQuantity(int shipmentQuantity) {
                this.shipmentQuantity = shipmentQuantity;
            }

            public String getWeightBoxType() {
                return weightBoxType;
            }

            public void setWeightBoxType(String weightBoxType) {
                this.weightBoxType = weightBoxType;
            }

            public String getVolumeBoxType() {
                return volumeBoxType;
            }

            public void setVolumeBoxType(String volumeBoxType) {
                this.volumeBoxType = volumeBoxType;
            }

            public int getNetWeight() {
                return netWeight;
            }

            public void setNetWeight(int netWeight) {
                this.netWeight = netWeight;
            }

            public int getGrossWeight() {
                return grossWeight;
            }

            public void setGrossWeight(int grossWeight) {
                this.grossWeight = grossWeight;
            }

            public int getVolume() {
                return volume;
            }

            public void setVolume(int volume) {
                this.volume = volume;
            }

            public static class SapInvoiceBean {
                /**
                 * createdBy : 11
                 * createdDate : 2018-06-09
                 * lastModifiedBy : null
                 * lastModifiedDate : null
                 * id : 1
                 * deleted : null
                 * sort : null
                 * fromClientType : null
                 * orderNumber : 4500015246
                 * lineNumber : null
                 * quantity : null
                 * amount : null
                 * deliveryDate : null
                 * denier : 37
                 * zzszs : 800
                 * zksks : 3
                 * number : A0908
                 * invoiceNumber : 9180000000
                 * invoiceLineNumber : 000010
                 * materialNumber : null
                 * materialBatchNumber : 020
                 * materialSpec : 1130
                 * orderSapNumber : null
                 * orderLineNumber : 10
                 * shippedQuantity : 100
                 * status : DELIVERY
                 * licensePlateNumber : null
                 * driverName : null
                 * driverPhone : null
                 * memberName : 大松树
                 * member : null
                 * posnr : null
                 * werks : null
                 * lgort : null
                 * vrkme : null
                 * bstkd : null
                 * posex : null
                 * kunnr : null
                 * wadatist : null
                 * wbstk : null
                 * ernam : null
                 * aedat : null
                 * maktx : null
                 * volum : 0
                 * memo : null
                 * lfart : null
                 * foreignInvoiceDetails : null
                 */

                private String createdBy;
                private String createdDate;
                private Object lastModifiedBy;
                private Object lastModifiedDate;
                private long id;
                private Object deleted;
                private Object sort;
                private Object fromClientType;
                private String orderNumber;
                private Object lineNumber;
                private Object quantity;
                private Object amount;
                private Object deliveryDate;
                private String denier;
                private int zzszs;
                private int zksks;
                private String number;
                private String invoiceNumber;
                private String invoiceLineNumber;
                private Object materialNumber;
                private String materialBatchNumber;
                private String materialSpec;
                private Object orderSapNumber;
                private String orderLineNumber;
                private double shippedQuantity;
                private String status;
                private Object licensePlateNumber;
                private Object driverName;
                private Object driverPhone;
                private String memberName;
                private Object member;
                private Object posnr;
                private Object werks;
                private Object lgort;
                private Object vrkme;
                private Object bstkd;
                private Object posex;
                private Object kunnr;
                private Object wadatist;
                private Object wbstk;
                private Object ernam;
                private Object aedat;
                private Object maktx;
                private int volum;
                private Object memo;
                private Object lfart;
                private Object foreignInvoiceDetails;

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

                public String getOrderNumber() {
                    return orderNumber;
                }

                public void setOrderNumber(String orderNumber) {
                    this.orderNumber = orderNumber;
                }

                public Object getLineNumber() {
                    return lineNumber;
                }

                public void setLineNumber(Object lineNumber) {
                    this.lineNumber = lineNumber;
                }

                public Object getQuantity() {
                    return quantity;
                }

                public void setQuantity(Object quantity) {
                    this.quantity = quantity;
                }

                public Object getAmount() {
                    return amount;
                }

                public void setAmount(Object amount) {
                    this.amount = amount;
                }

                public Object getDeliveryDate() {
                    return deliveryDate;
                }

                public void setDeliveryDate(Object deliveryDate) {
                    this.deliveryDate = deliveryDate;
                }

                public String getDenier() {
                    return denier;
                }

                public void setDenier(String denier) {
                    this.denier = denier;
                }

                public int getZzszs() {
                    return zzszs;
                }

                public void setZzszs(int zzszs) {
                    this.zzszs = zzszs;
                }

                public int getZksks() {
                    return zksks;
                }

                public void setZksks(int zksks) {
                    this.zksks = zksks;
                }

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }

                public String getInvoiceNumber() {
                    return invoiceNumber;
                }

                public void setInvoiceNumber(String invoiceNumber) {
                    this.invoiceNumber = invoiceNumber;
                }

                public String getInvoiceLineNumber() {
                    return invoiceLineNumber;
                }

                public void setInvoiceLineNumber(String invoiceLineNumber) {
                    this.invoiceLineNumber = invoiceLineNumber;
                }

                public Object getMaterialNumber() {
                    return materialNumber;
                }

                public void setMaterialNumber(Object materialNumber) {
                    this.materialNumber = materialNumber;
                }

                public String getMaterialBatchNumber() {
                    return materialBatchNumber;
                }

                public void setMaterialBatchNumber(String materialBatchNumber) {
                    this.materialBatchNumber = materialBatchNumber;
                }

                public String getMaterialSpec() {
                    return materialSpec;
                }

                public void setMaterialSpec(String materialSpec) {
                    this.materialSpec = materialSpec;
                }

                public Object getOrderSapNumber() {
                    return orderSapNumber;
                }

                public void setOrderSapNumber(Object orderSapNumber) {
                    this.orderSapNumber = orderSapNumber;
                }

                public String getOrderLineNumber() {
                    return orderLineNumber;
                }

                public void setOrderLineNumber(String orderLineNumber) {
                    this.orderLineNumber = orderLineNumber;
                }

                public double getShippedQuantity() {
                    return shippedQuantity;
                }

                public void setShippedQuantity(double shippedQuantity) {
                    this.shippedQuantity = shippedQuantity;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
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

                public String getMemberName() {
                    return memberName;
                }

                public void setMemberName(String memberName) {
                    this.memberName = memberName;
                }

                public Object getMember() {
                    return member;
                }

                public void setMember(Object member) {
                    this.member = member;
                }

                public Object getPosnr() {
                    return posnr;
                }

                public void setPosnr(Object posnr) {
                    this.posnr = posnr;
                }

                public Object getWerks() {
                    return werks;
                }

                public void setWerks(Object werks) {
                    this.werks = werks;
                }

                public Object getLgort() {
                    return lgort;
                }

                public void setLgort(Object lgort) {
                    this.lgort = lgort;
                }

                public Object getVrkme() {
                    return vrkme;
                }

                public void setVrkme(Object vrkme) {
                    this.vrkme = vrkme;
                }

                public Object getBstkd() {
                    return bstkd;
                }

                public void setBstkd(Object bstkd) {
                    this.bstkd = bstkd;
                }

                public Object getPosex() {
                    return posex;
                }

                public void setPosex(Object posex) {
                    this.posex = posex;
                }

                public Object getKunnr() {
                    return kunnr;
                }

                public void setKunnr(Object kunnr) {
                    this.kunnr = kunnr;
                }

                public Object getWadatist() {
                    return wadatist;
                }

                public void setWadatist(Object wadatist) {
                    this.wadatist = wadatist;
                }

                public Object getWbstk() {
                    return wbstk;
                }

                public void setWbstk(Object wbstk) {
                    this.wbstk = wbstk;
                }

                public Object getErnam() {
                    return ernam;
                }

                public void setErnam(Object ernam) {
                    this.ernam = ernam;
                }

                public Object getAedat() {
                    return aedat;
                }

                public void setAedat(Object aedat) {
                    this.aedat = aedat;
                }

                public Object getMaktx() {
                    return maktx;
                }

                public void setMaktx(Object maktx) {
                    this.maktx = maktx;
                }

                public int getVolum() {
                    return volum;
                }

                public void setVolum(int volum) {
                    this.volum = volum;
                }

                public Object getMemo() {
                    return memo;
                }

                public void setMemo(Object memo) {
                    this.memo = memo;
                }

                public Object getLfart() {
                    return lfart;
                }

                public void setLfart(Object lfart) {
                    this.lfart = lfart;
                }

                public Object getForeignInvoiceDetails() {
                    return foreignInvoiceDetails;
                }

                public void setForeignInvoiceDetails(Object foreignInvoiceDetails) {
                    this.foreignInvoiceDetails = foreignInvoiceDetails;
                }
            }
        }
    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : id
         * ignoreCase : false
         * nullHandling : NATIVE
         * descending : false
         * ascending : true
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
