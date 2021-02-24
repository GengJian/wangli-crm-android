package dynamic.entity;

import java.util.List;

import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称:DySampleBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/17 17:18
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/17 1.00 初始版本
 * ****************************************************************
 */
public class DySampleBean {



    private int number;
    private boolean last;
    private int numberOfElements;
    private int size;
    private int totalPages;
    private boolean first;
    private int totalElements;
    private List<SortBean> sort;
    private List<ContentBean> content;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
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
         * nullHandling : NATIVE
         * ignoreCase : false
         * property : id
         * ascending : false
         * descending : true
         * direction : DESC
         */

        private String nullHandling;
        private boolean ignoreCase;
        private String property;
        private boolean ascending;
        private boolean descending;
        private String direction;

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
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

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }

    public static class ContentBean {


        private String sendTimesKey;
        private OperatorBean operator;
        private String applicateDate;
        private String appReason;
        private Object provideDate;
        private String producerValue;
        private Object experimentalReport;
        private long id;
        private String reliabilityRemark;
        private Object massProductionResultAttachments;
        private boolean massProduction;
        private String addresseePhone;
        private String expectProvideDate;
        private double massProductionCount;
        private String sampleNum;
        private int sort;
        private String massProductionResultRemark;
        private String salesAreaKey;
        private Object massProductionDate;
        private String producerKey;
        private Object massProductionResultKey;
        private String sampleTypeValue;
        private String status;
        private double monthFreeNum;
        private Object remark;
        private String title;
        private double totalCount;
        private Object attachmentList;
        private Object businessChance;
        private String requestId;
        private MemberBeanID member;
        private String approvalStatusDesp;
        private Object productOperator;
        private String appRemark;
        private Object reservedSample;
        private boolean free;
        private String nodeIdentifier;
        private Object historySampleResult;
        private Object historySampleFailedReason;
        private String address;
        private String salesAreaValue;
        private String lastModifiedDate;
        private String lastModifiedBy;
        private Object logisticsPreparation;
        private String statusDesp;
        private String createdDate;
        private double reservedSampleCount;
        private String createdBy;
        private String sendTimesValue;
        private Object massProductionResultValue;
        private String recipient;
        private Object inspectionReport;
        private String sampleTypeKey;
        private String contractNum;
        private List<SampleApplicationsBean> sampleApplications;
        private List<ReliabilityTestsBean> reliabilityTests;
        private List<SampleItemsBean> sampleItems;

        public String getSendTimesKey() {
            return sendTimesKey;
        }

        public void setSendTimesKey(String sendTimesKey) {
            this.sendTimesKey = sendTimesKey;
        }

        public OperatorBean getOperator() {
            return operator;
        }

        public void setOperator(OperatorBean operator) {
            this.operator = operator;
        }

        public String getApplicateDate() {
            return applicateDate;
        }

        public void setApplicateDate(String applicateDate) {
            this.applicateDate = applicateDate;
        }

        public String getAppReason() {
            return appReason;
        }

        public void setAppReason(String appReason) {
            this.appReason = appReason;
        }

        public Object getProvideDate() {
            return provideDate;
        }

        public void setProvideDate(Object provideDate) {
            this.provideDate = provideDate;
        }

        public String getProducerValue() {
            return producerValue;
        }

        public void setProducerValue(String producerValue) {
            this.producerValue = producerValue;
        }

        public Object getExperimentalReport() {
            return experimentalReport;
        }

        public void setExperimentalReport(Object experimentalReport) {
            this.experimentalReport = experimentalReport;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getReliabilityRemark() {
            return reliabilityRemark;
        }

        public void setReliabilityRemark(String reliabilityRemark) {
            this.reliabilityRemark = reliabilityRemark;
        }

        public Object getMassProductionResultAttachments() {
            return massProductionResultAttachments;
        }

        public void setMassProductionResultAttachments(Object massProductionResultAttachments) {
            this.massProductionResultAttachments = massProductionResultAttachments;
        }

        public boolean isMassProduction() {
            return massProduction;
        }

        public void setMassProduction(boolean massProduction) {
            this.massProduction = massProduction;
        }

        public String getAddresseePhone() {
            return addresseePhone;
        }

        public void setAddresseePhone(String addresseePhone) {
            this.addresseePhone = addresseePhone;
        }

        public String getExpectProvideDate() {
            return expectProvideDate;
        }

        public void setExpectProvideDate(String expectProvideDate) {
            this.expectProvideDate = expectProvideDate;
        }

        public double getMassProductionCount() {
            return massProductionCount;
        }

        public void setMassProductionCount(double massProductionCount) {
            this.massProductionCount = massProductionCount;
        }

        public String getSampleNum() {
            return sampleNum;
        }

        public void setSampleNum(String sampleNum) {
            this.sampleNum = sampleNum;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getMassProductionResultRemark() {
            return massProductionResultRemark;
        }

        public void setMassProductionResultRemark(String massProductionResultRemark) {
            this.massProductionResultRemark = massProductionResultRemark;
        }

        public String getSalesAreaKey() {
            return salesAreaKey;
        }

        public void setSalesAreaKey(String salesAreaKey) {
            this.salesAreaKey = salesAreaKey;
        }

        public Object getMassProductionDate() {
            return massProductionDate;
        }

        public void setMassProductionDate(Object massProductionDate) {
            this.massProductionDate = massProductionDate;
        }

        public String getProducerKey() {
            return producerKey;
        }

        public void setProducerKey(String producerKey) {
            this.producerKey = producerKey;
        }

        public Object getMassProductionResultKey() {
            return massProductionResultKey;
        }

        public void setMassProductionResultKey(Object massProductionResultKey) {
            this.massProductionResultKey = massProductionResultKey;
        }

        public String getSampleTypeValue() {
            return sampleTypeValue;
        }

        public void setSampleTypeValue(String sampleTypeValue) {
            this.sampleTypeValue = sampleTypeValue;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getMonthFreeNum() {
            return monthFreeNum;
        }

        public void setMonthFreeNum(double monthFreeNum) {
            this.monthFreeNum = monthFreeNum;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(double totalCount) {
            this.totalCount = totalCount;
        }

        public Object getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(Object attachmentList) {
            this.attachmentList = attachmentList;
        }

        public Object getBusinessChance() {
            return businessChance;
        }

        public void setBusinessChance(Object businessChance) {
            this.businessChance = businessChance;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getApprovalStatusDesp() {
            return approvalStatusDesp;
        }

        public void setApprovalStatusDesp(String approvalStatusDesp) {
            this.approvalStatusDesp = approvalStatusDesp;
        }

        public Object getProductOperator() {
            return productOperator;
        }

        public void setProductOperator(Object productOperator) {
            this.productOperator = productOperator;
        }

        public String getAppRemark() {
            return appRemark;
        }

        public void setAppRemark(String appRemark) {
            this.appRemark = appRemark;
        }

        public Object getReservedSample() {
            return reservedSample;
        }

        public void setReservedSample(Object reservedSample) {
            this.reservedSample = reservedSample;
        }

        public boolean isFree() {
            return free;
        }

        public void setFree(boolean free) {
            this.free = free;
        }

        public String getNodeIdentifier() {
            return nodeIdentifier;
        }

        public void setNodeIdentifier(String nodeIdentifier) {
            this.nodeIdentifier = nodeIdentifier;
        }

        public Object getHistorySampleResult() {
            return historySampleResult;
        }

        public void setHistorySampleResult(Object historySampleResult) {
            this.historySampleResult = historySampleResult;
        }

        public Object getHistorySampleFailedReason() {
            return historySampleFailedReason;
        }

        public void setHistorySampleFailedReason(Object historySampleFailedReason) {
            this.historySampleFailedReason = historySampleFailedReason;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSalesAreaValue() {
            return salesAreaValue;
        }

        public void setSalesAreaValue(String salesAreaValue) {
            this.salesAreaValue = salesAreaValue;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public Object getLogisticsPreparation() {
            return logisticsPreparation;
        }

        public void setLogisticsPreparation(Object logisticsPreparation) {
            this.logisticsPreparation = logisticsPreparation;
        }

        public String getStatusDesp() {
            return statusDesp;
        }

        public void setStatusDesp(String statusDesp) {
            this.statusDesp = statusDesp;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public double getReservedSampleCount() {
            return reservedSampleCount;
        }

        public void setReservedSampleCount(double reservedSampleCount) {
            this.reservedSampleCount = reservedSampleCount;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getSendTimesValue() {
            return sendTimesValue;
        }

        public void setSendTimesValue(String sendTimesValue) {
            this.sendTimesValue = sendTimesValue;
        }

        public Object getMassProductionResultValue() {
            return massProductionResultValue;
        }

        public void setMassProductionResultValue(Object massProductionResultValue) {
            this.massProductionResultValue = massProductionResultValue;
        }

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        public Object getInspectionReport() {
            return inspectionReport;
        }

        public void setInspectionReport(Object inspectionReport) {
            this.inspectionReport = inspectionReport;
        }

        public String getSampleTypeKey() {
            return sampleTypeKey;
        }

        public void setSampleTypeKey(String sampleTypeKey) {
            this.sampleTypeKey = sampleTypeKey;
        }

        public String getContractNum() {
            return contractNum;
        }

        public void setContractNum(String contractNum) {
            this.contractNum = contractNum;
        }

        public List<SampleApplicationsBean> getSampleApplications() {
            return sampleApplications;
        }

        public void setSampleApplications(List<SampleApplicationsBean> sampleApplications) {
            this.sampleApplications = sampleApplications;
        }

        public List<ReliabilityTestsBean> getReliabilityTests() {
            return reliabilityTests;
        }

        public void setReliabilityTests(List<ReliabilityTestsBean> reliabilityTests) {
            this.reliabilityTests = reliabilityTests;
        }

        public List<SampleItemsBean> getSampleItems() {
            return sampleItems;
        }

        public void setSampleItems(List<SampleItemsBean> sampleItems) {
            this.sampleItems = sampleItems;
        }

        public static class OperatorBean {
            /**
             * departmentName : null
             * lastModifiedDate : 2019-01-21 16:03:44
             * lastModifiedBy : 何达能
             * sort : 10
             * title : 副总经理(a)
             * createdDate : 2018-11-29 21:15:42
             * createdBy : 13901565517
             * name : 何达能
             * id : 38
             * department : {"desp":"","createdDate":"2018-12-26 20:36:56","lastModifiedDate":"2019-01-17 19:09:22","createdBy":"15167156690","lastModifiedBy":"费婷","name":"总经办","oaDepartmentId":"71","id":45,"sort":100}
             * oaId : 714
             * username : 1004605
             * oaCode : 1004605
             */

            private Object departmentName;
            private String lastModifiedDate;
            private String lastModifiedBy;
            private int sort;
            private String title;
            private String createdDate;
            private String createdBy;
            private String name;
            private long id;
            private DepartmentBean department;
            private String oaId;
            private String username;
            private String oaCode;

            public Object getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(Object departmentName) {
                this.departmentName = departmentName;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public DepartmentBean getDepartment() {
                return department;
            }

            public void setDepartment(DepartmentBean department) {
                this.department = department;
            }

            public String getOaId() {
                return oaId;
            }

            public void setOaId(String oaId) {
                this.oaId = oaId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getOaCode() {
                return oaCode;
            }

            public void setOaCode(String oaCode) {
                this.oaCode = oaCode;
            }

            public static class DepartmentBean {
                /**
                 * desp :
                 * createdDate : 2018-12-26 20:36:56
                 * lastModifiedDate : 2019-01-17 19:09:22
                 * createdBy : 15167156690
                 * lastModifiedBy : 费婷
                 * name : 总经办
                 * oaDepartmentId : 71
                 * id : 45
                 * sort : 100
                 */

                private String desp;
                private String createdDate;
                private String lastModifiedDate;
                private String createdBy;
                private String lastModifiedBy;
                private String name;
                private String oaDepartmentId;
                private long id;
                private int sort;

                public String getDesp() {
                    return desp;
                }

                public void setDesp(String desp) {
                    this.desp = desp;
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

                public String getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(String createdBy) {
                    this.createdBy = createdBy;
                }

                public String getLastModifiedBy() {
                    return lastModifiedBy;
                }

                public void setLastModifiedBy(String lastModifiedBy) {
                    this.lastModifiedBy = lastModifiedBy;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOaDepartmentId() {
                    return oaDepartmentId;
                }

                public void setOaDepartmentId(String oaDepartmentId) {
                    this.oaDepartmentId = oaDepartmentId;
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
            }
        }


        public static class SampleApplicationsBean {
            /**
             * createdDate : 2019-01-21 15:35:09
             * lastModifiedDate : 2019-01-21 15:35:09
             * createdBy : 何达能
             * applicationValue : 试产
             * lastModifiedBy : 何达能
             * applicationKey : trial_produce
             * id : 88
             * sort : 10
             */

            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String applicationValue;
            private String lastModifiedBy;
            private String applicationKey;
            private long id;
            private int sort;

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

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public String getApplicationValue() {
                return applicationValue;
            }

            public void setApplicationValue(String applicationValue) {
                this.applicationValue = applicationValue;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public String getApplicationKey() {
                return applicationKey;
            }

            public void setApplicationKey(String applicationKey) {
                this.applicationKey = applicationKey;
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
        }

        public static class ReliabilityTestsBean {
            /**
             * createdDate : 2019-01-21 15:35:10
             * lastModifiedDate : 2019-01-21 15:35:10
             * createdBy : 何达能
             * reliabilityValue : 反向电流过载
             * lastModifiedBy : 何达能
             * id : 87
             * sort : 10
             * reliabilityKey : reverse_current_overload
             */

            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String reliabilityValue;
            private String lastModifiedBy;
            private long id;
            private int sort;
            private String reliabilityKey;

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

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public String getReliabilityValue() {
                return reliabilityValue;
            }

            public void setReliabilityValue(String reliabilityValue) {
                this.reliabilityValue = reliabilityValue;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
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

            public String getReliabilityKey() {
                return reliabilityKey;
            }

            public void setReliabilityKey(String reliabilityKey) {
                this.reliabilityKey = reliabilityKey;
            }
        }

        public static class SampleItemsBean {
            /**
             * effectiveness : 80
             * amount : 10.0
             * efficiencyConvert : {"createdDate":"2019-01-09 15:21:29","lastModifiedDate":"2019-01-09 15:21:29","createdBy":"17721180295","lastModifiedBy":"17721180295","id":2,"sort":10,"wpc":"4.91"}
             * lastModifiedDate : 2019-01-21 15:35:09
             * lastModifiedBy : 何达能
             * remark : 测试
             * sort : 10
             * sample : {"massProductionResultAttachments":null,"createdDate":"2019-01-21 15:35:07","lastModifiedDate":"2019-01-21 15:35:12","createdBy":"何达能","attachmentList":null,"businessChance":null,"lastModifiedBy":"何达能","member":{"statusValue":"潜在","crmNumber":"A20190113007","srOperatorName":"未分配","office":null,"creditLevelValue":"AAAA","sapNumber":null,"arOperatorName":"潘梦洋","frOperator":{"departmentName":null,"lastModifiedDate":"2019-01-21 14:24:38","lastModifiedBy":"劳建勳","sort":10,"title":"销售总监(z)","createdDate":"2018-11-29 21:15:42","createdBy":"15167156690","name":"劳建勳","id":21,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 16:04:32","createdBy":"13901565517","lastModifiedBy":"徐超","name":"海外销售部（Z）","oaDepartmentId":"412","id":76,"sort":200},"oaId":"1104","username":"1013447","oaCode":"1013447"},"memberLevelValue":"潜在战略","employeeSizeValue":null,"simpleSpell":"xsj","registeredCapital":45123,"statusKey":"potential","srOperator":null,"id":2834,"companyTypeValue":"有限责任公司分公司","orgName":"求带飞","arOperator":{"departmentName":null,"lastModifiedDate":"2019-01-21 14:27:51","lastModifiedBy":"潘梦洋","sort":10,"title":"市场专员(z)","createdDate":"2018-11-29 21:15:42","createdBy":"15167156690","name":"潘梦洋","id":1,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200},"oaId":"2381","username":"1022654","oaCode":"1022654"},"lastModifiedDate":"2019-01-19 21:54:21","avatarUrl":"97b9e00b31a94cae95eb80ea2d516b5e","lastModifiedBy":"劳建勳","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"新世纪","customerDemand":"萨德阿斯顿发","createdDate":"2019-01-13 18:20:33","createdBy":"徐超","dealer":null,"provinceName":null,"cooperationTypeValue":"供应商","riskLevelValue":null,"frOperatorName":"劳建勳"},"id":85,"sort":10,"operator":{"departmentName":null,"lastModifiedDate":"2019-01-21 16:03:44","lastModifiedBy":"何达能","sort":10,"title":"副总经理(a)","createdDate":"2018-11-29 21:15:42","createdBy":"13901565517","name":"何达能","id":38,"department":{"desp":"","createdDate":"2018-12-26 20:36:56","lastModifiedDate":"2019-01-17 19:09:22","createdBy":"15167156690","lastModifiedBy":"费婷","name":"总经办","oaDepartmentId":"71","id":45,"sort":100},"oaId":"714","username":"1004605","oaCode":"1004605"}}
             * productName : 单晶PBPERC双面5主栅直栅110细栅4分段150细栅半片C5
             * unitKey : PC
             * createdDate : 2019-01-21 15:35:09
             * createdBy : 何达能
             * materialNum : 60000043
             * id : 52
             * unitValue : PC
             */

            private String effectiveness;
            private double amount;
            private EfficiencyConvertBean efficiencyConvert;
            private String lastModifiedDate;
            private String lastModifiedBy;
            private String remark;
            private int sort;
            private SampleBean sample;
            private String productName;
            private String unitKey;
            private String createdDate;
            private String createdBy;
            private String materialNum;
            private long id;
            private String unitValue;

            public String getEffectiveness() {
                return effectiveness;
            }

            public void setEffectiveness(String effectiveness) {
                this.effectiveness = effectiveness;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public EfficiencyConvertBean getEfficiencyConvert() {
                return efficiencyConvert;
            }

            public void setEfficiencyConvert(EfficiencyConvertBean efficiencyConvert) {
                this.efficiencyConvert = efficiencyConvert;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public SampleBean getSample() {
                return sample;
            }

            public void setSample(SampleBean sample) {
                this.sample = sample;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getUnitKey() {
                return unitKey;
            }

            public void setUnitKey(String unitKey) {
                this.unitKey = unitKey;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public String getMaterialNum() {
                return materialNum;
            }

            public void setMaterialNum(String materialNum) {
                this.materialNum = materialNum;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getUnitValue() {
                return unitValue;
            }

            public void setUnitValue(String unitValue) {
                this.unitValue = unitValue;
            }

            public static class EfficiencyConvertBean {
                /**
                 * createdDate : 2019-01-09 15:21:29
                 * lastModifiedDate : 2019-01-09 15:21:29
                 * createdBy : 17721180295
                 * lastModifiedBy : 17721180295
                 * id : 2
                 * sort : 10
                 * wpc : 4.91
                 */

                private String createdDate;
                private String lastModifiedDate;
                private String createdBy;
                private String lastModifiedBy;
                private long id;
                private int sort;
                private String wpc;

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

                public String getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(String createdBy) {
                    this.createdBy = createdBy;
                }

                public String getLastModifiedBy() {
                    return lastModifiedBy;
                }

                public void setLastModifiedBy(String lastModifiedBy) {
                    this.lastModifiedBy = lastModifiedBy;
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

                public String getWpc() {
                    return wpc;
                }

                public void setWpc(String wpc) {
                    this.wpc = wpc;
                }
            }

            public static class SampleBean {
                private Object massProductionResultAttachments;
                private String createdDate;
                private String lastModifiedDate;
                private String createdBy;
                private Object attachmentList;
                private Object businessChance;
                private String lastModifiedBy;
                private MemberBeanX member;
                private long id;
                private int sort;
                private OperatorBeanX operator;

                public Object getMassProductionResultAttachments() {
                    return massProductionResultAttachments;
                }

                public void setMassProductionResultAttachments(Object massProductionResultAttachments) {
                    this.massProductionResultAttachments = massProductionResultAttachments;
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

                public String getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(String createdBy) {
                    this.createdBy = createdBy;
                }

                public Object getAttachmentList() {
                    return attachmentList;
                }

                public void setAttachmentList(Object attachmentList) {
                    this.attachmentList = attachmentList;
                }

                public Object getBusinessChance() {
                    return businessChance;
                }

                public void setBusinessChance(Object businessChance) {
                    this.businessChance = businessChance;
                }

                public String getLastModifiedBy() {
                    return lastModifiedBy;
                }

                public void setLastModifiedBy(String lastModifiedBy) {
                    this.lastModifiedBy = lastModifiedBy;
                }

                public MemberBeanX getMember() {
                    return member;
                }

                public void setMember(MemberBeanX member) {
                    this.member = member;
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

                public OperatorBeanX getOperator() {
                    return operator;
                }

                public void setOperator(OperatorBeanX operator) {
                    this.operator = operator;
                }

                public static class MemberBeanX {
                    /**
                     * statusValue : 潜在
                     * crmNumber : A20190113007
                     * srOperatorName : 未分配
                     * office : null
                     * creditLevelValue : AAAA
                     * sapNumber : null
                     * arOperatorName : 潘梦洋
                     * frOperator : {"departmentName":null,"lastModifiedDate":"2019-01-21 14:24:38","lastModifiedBy":"劳建勳","sort":10,"title":"销售总监(z)","createdDate":"2018-11-29 21:15:42","createdBy":"15167156690","name":"劳建勳","id":21,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 16:04:32","createdBy":"13901565517","lastModifiedBy":"徐超","name":"海外销售部（Z）","oaDepartmentId":"412","id":76,"sort":200},"oaId":"1104","username":"1013447","oaCode":"1013447"}
                     * memberLevelValue : 潜在战略
                     * employeeSizeValue : null
                     * simpleSpell : xsj
                     * registeredCapital : 45123.0
                     * statusKey : potential
                     * srOperator : null
                     * id : 2834
                     * companyTypeValue : 有限责任公司分公司
                     * orgName : 求带飞
                     * arOperator : {"departmentName":null,"lastModifiedDate":"2019-01-21 14:27:51","lastModifiedBy":"潘梦洋","sort":10,"title":"市场专员(z)","createdDate":"2018-11-29 21:15:42","createdBy":"15167156690","name":"潘梦洋","id":1,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200},"oaId":"2381","username":"1022654","oaCode":"1022654"}
                     * lastModifiedDate : 2019-01-19 21:54:21
                     * avatarUrl : 97b9e00b31a94cae95eb80ea2d516b5e
                     * lastModifiedBy : 劳建勳
                     * riskLevelKey : null
                     * creditModifyDate : null
                     * sort : 10
                     * abbreviation : 新世纪
                     * customerDemand : 萨德阿斯顿发
                     * createdDate : 2019-01-13 18:20:33
                     * createdBy : 徐超
                     * dealer : null
                     * provinceName : null
                     * cooperationTypeValue : 供应商
                     * riskLevelValue : null
                     * frOperatorName : 劳建勳
                     */

                    private String statusValue;
                    private String crmNumber;
                    private String srOperatorName;
                    private Object office;
                    private String creditLevelValue;
                    private Object sapNumber;
                    private String arOperatorName;
                    private FrOperatorBeanX frOperator;
                    private String memberLevelValue;
                    private Object employeeSizeValue;
                    private String simpleSpell;
                    private double registeredCapital;
                    private String statusKey;
                    private Object srOperator;
                    private long id;
                    private String companyTypeValue;
                    private String orgName;
                    private ArOperatorBeanX arOperator;
                    private String lastModifiedDate;
                    private String avatarUrl;
                    private String lastModifiedBy;
                    private Object riskLevelKey;
                    private Object creditModifyDate;
                    private int sort;
                    private String abbreviation;
                    private String customerDemand;
                    private String createdDate;
                    private String createdBy;
                    private Object dealer;
                    private Object provinceName;
                    private String cooperationTypeValue;
                    private Object riskLevelValue;
                    private String frOperatorName;

                    public String getStatusValue() {
                        return statusValue;
                    }

                    public void setStatusValue(String statusValue) {
                        this.statusValue = statusValue;
                    }

                    public String getCrmNumber() {
                        return crmNumber;
                    }

                    public void setCrmNumber(String crmNumber) {
                        this.crmNumber = crmNumber;
                    }

                    public String getSrOperatorName() {
                        return srOperatorName;
                    }

                    public void setSrOperatorName(String srOperatorName) {
                        this.srOperatorName = srOperatorName;
                    }

                    public Object getOffice() {
                        return office;
                    }

                    public void setOffice(Object office) {
                        this.office = office;
                    }

                    public String getCreditLevelValue() {
                        return creditLevelValue;
                    }

                    public void setCreditLevelValue(String creditLevelValue) {
                        this.creditLevelValue = creditLevelValue;
                    }

                    public Object getSapNumber() {
                        return sapNumber;
                    }

                    public void setSapNumber(Object sapNumber) {
                        this.sapNumber = sapNumber;
                    }

                    public String getArOperatorName() {
                        return arOperatorName;
                    }

                    public void setArOperatorName(String arOperatorName) {
                        this.arOperatorName = arOperatorName;
                    }

                    public FrOperatorBeanX getFrOperator() {
                        return frOperator;
                    }

                    public void setFrOperator(FrOperatorBeanX frOperator) {
                        this.frOperator = frOperator;
                    }

                    public String getMemberLevelValue() {
                        return memberLevelValue;
                    }

                    public void setMemberLevelValue(String memberLevelValue) {
                        this.memberLevelValue = memberLevelValue;
                    }

                    public Object getEmployeeSizeValue() {
                        return employeeSizeValue;
                    }

                    public void setEmployeeSizeValue(Object employeeSizeValue) {
                        this.employeeSizeValue = employeeSizeValue;
                    }

                    public String getSimpleSpell() {
                        return simpleSpell;
                    }

                    public void setSimpleSpell(String simpleSpell) {
                        this.simpleSpell = simpleSpell;
                    }

                    public double getRegisteredCapital() {
                        return registeredCapital;
                    }

                    public void setRegisteredCapital(double registeredCapital) {
                        this.registeredCapital = registeredCapital;
                    }

                    public String getStatusKey() {
                        return statusKey;
                    }

                    public void setStatusKey(String statusKey) {
                        this.statusKey = statusKey;
                    }

                    public Object getSrOperator() {
                        return srOperator;
                    }

                    public void setSrOperator(Object srOperator) {
                        this.srOperator = srOperator;
                    }

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public String getCompanyTypeValue() {
                        return companyTypeValue;
                    }

                    public void setCompanyTypeValue(String companyTypeValue) {
                        this.companyTypeValue = companyTypeValue;
                    }

                    public String getOrgName() {
                        return orgName;
                    }

                    public void setOrgName(String orgName) {
                        this.orgName = orgName;
                    }

                    public ArOperatorBeanX getArOperator() {
                        return arOperator;
                    }

                    public void setArOperator(ArOperatorBeanX arOperator) {
                        this.arOperator = arOperator;
                    }

                    public String getLastModifiedDate() {
                        return lastModifiedDate;
                    }

                    public void setLastModifiedDate(String lastModifiedDate) {
                        this.lastModifiedDate = lastModifiedDate;
                    }

                    public String getAvatarUrl() {
                        return avatarUrl;
                    }

                    public void setAvatarUrl(String avatarUrl) {
                        this.avatarUrl = avatarUrl;
                    }

                    public String getLastModifiedBy() {
                        return lastModifiedBy;
                    }

                    public void setLastModifiedBy(String lastModifiedBy) {
                        this.lastModifiedBy = lastModifiedBy;
                    }

                    public Object getRiskLevelKey() {
                        return riskLevelKey;
                    }

                    public void setRiskLevelKey(Object riskLevelKey) {
                        this.riskLevelKey = riskLevelKey;
                    }

                    public Object getCreditModifyDate() {
                        return creditModifyDate;
                    }

                    public void setCreditModifyDate(Object creditModifyDate) {
                        this.creditModifyDate = creditModifyDate;
                    }

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getAbbreviation() {
                        return abbreviation;
                    }

                    public void setAbbreviation(String abbreviation) {
                        this.abbreviation = abbreviation;
                    }

                    public String getCustomerDemand() {
                        return customerDemand;
                    }

                    public void setCustomerDemand(String customerDemand) {
                        this.customerDemand = customerDemand;
                    }

                    public String getCreatedDate() {
                        return createdDate;
                    }

                    public void setCreatedDate(String createdDate) {
                        this.createdDate = createdDate;
                    }

                    public String getCreatedBy() {
                        return createdBy;
                    }

                    public void setCreatedBy(String createdBy) {
                        this.createdBy = createdBy;
                    }

                    public Object getDealer() {
                        return dealer;
                    }

                    public void setDealer(Object dealer) {
                        this.dealer = dealer;
                    }

                    public Object getProvinceName() {
                        return provinceName;
                    }

                    public void setProvinceName(Object provinceName) {
                        this.provinceName = provinceName;
                    }

                    public String getCooperationTypeValue() {
                        return cooperationTypeValue;
                    }

                    public void setCooperationTypeValue(String cooperationTypeValue) {
                        this.cooperationTypeValue = cooperationTypeValue;
                    }

                    public Object getRiskLevelValue() {
                        return riskLevelValue;
                    }

                    public void setRiskLevelValue(Object riskLevelValue) {
                        this.riskLevelValue = riskLevelValue;
                    }

                    public String getFrOperatorName() {
                        return frOperatorName;
                    }

                    public void setFrOperatorName(String frOperatorName) {
                        this.frOperatorName = frOperatorName;
                    }

                    public static class FrOperatorBeanX {
                        /**
                         * departmentName : null
                         * lastModifiedDate : 2019-01-21 14:24:38
                         * lastModifiedBy : 劳建勳
                         * sort : 10
                         * title : 销售总监(z)
                         * createdDate : 2018-11-29 21:15:42
                         * createdBy : 15167156690
                         * name : 劳建勳
                         * id : 21
                         * department : {"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 16:04:32","createdBy":"13901565517","lastModifiedBy":"徐超","name":"海外销售部（Z）","oaDepartmentId":"412","id":76,"sort":200}
                         * oaId : 1104
                         * username : 1013447
                         * oaCode : 1013447
                         */

                        private Object departmentName;
                        private String lastModifiedDate;
                        private String lastModifiedBy;
                        private int sort;
                        private String title;
                        private String createdDate;
                        private String createdBy;
                        private String name;
                        private long id;
                        private DepartmentBeanXXX department;
                        private String oaId;
                        private String username;
                        private String oaCode;

                        public Object getDepartmentName() {
                            return departmentName;
                        }

                        public void setDepartmentName(Object departmentName) {
                            this.departmentName = departmentName;
                        }

                        public String getLastModifiedDate() {
                            return lastModifiedDate;
                        }

                        public void setLastModifiedDate(String lastModifiedDate) {
                            this.lastModifiedDate = lastModifiedDate;
                        }

                        public String getLastModifiedBy() {
                            return lastModifiedBy;
                        }

                        public void setLastModifiedBy(String lastModifiedBy) {
                            this.lastModifiedBy = lastModifiedBy;
                        }

                        public int getSort() {
                            return sort;
                        }

                        public void setSort(int sort) {
                            this.sort = sort;
                        }

                        public String getTitle() {
                            return title;
                        }

                        public void setTitle(String title) {
                            this.title = title;
                        }

                        public String getCreatedDate() {
                            return createdDate;
                        }

                        public void setCreatedDate(String createdDate) {
                            this.createdDate = createdDate;
                        }

                        public String getCreatedBy() {
                            return createdBy;
                        }

                        public void setCreatedBy(String createdBy) {
                            this.createdBy = createdBy;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public long getId() {
                            return id;
                        }

                        public void setId(long id) {
                            this.id = id;
                        }

                        public DepartmentBeanXXX getDepartment() {
                            return department;
                        }

                        public void setDepartment(DepartmentBeanXXX department) {
                            this.department = department;
                        }

                        public String getOaId() {
                            return oaId;
                        }

                        public void setOaId(String oaId) {
                            this.oaId = oaId;
                        }

                        public String getUsername() {
                            return username;
                        }

                        public void setUsername(String username) {
                            this.username = username;
                        }

                        public String getOaCode() {
                            return oaCode;
                        }

                        public void setOaCode(String oaCode) {
                            this.oaCode = oaCode;
                        }

                        public static class DepartmentBeanXXX {
                            /**
                             * desp : null
                             * createdDate : 2018-12-12 00:00:00
                             * lastModifiedDate : 2019-01-10 16:04:32
                             * createdBy : 13901565517
                             * lastModifiedBy : 徐超
                             * name : 海外销售部（Z）
                             * oaDepartmentId : 412
                             * id : 76
                             * sort : 200
                             */

                            private Object desp;
                            private String createdDate;
                            private String lastModifiedDate;
                            private String createdBy;
                            private String lastModifiedBy;
                            private String name;
                            private String oaDepartmentId;
                            private long id;
                            private int sort;

                            public Object getDesp() {
                                return desp;
                            }

                            public void setDesp(Object desp) {
                                this.desp = desp;
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

                            public String getCreatedBy() {
                                return createdBy;
                            }

                            public void setCreatedBy(String createdBy) {
                                this.createdBy = createdBy;
                            }

                            public String getLastModifiedBy() {
                                return lastModifiedBy;
                            }

                            public void setLastModifiedBy(String lastModifiedBy) {
                                this.lastModifiedBy = lastModifiedBy;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getOaDepartmentId() {
                                return oaDepartmentId;
                            }

                            public void setOaDepartmentId(String oaDepartmentId) {
                                this.oaDepartmentId = oaDepartmentId;
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
                        }
                    }

                    public static class ArOperatorBeanX {
                        /**
                         * departmentName : null
                         * lastModifiedDate : 2019-01-21 14:27:51
                         * lastModifiedBy : 潘梦洋
                         * sort : 10
                         * title : 市场专员(z)
                         * createdDate : 2018-11-29 21:15:42
                         * createdBy : 15167156690
                         * name : 潘梦洋
                         * id : 1
                         * department : {"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200}
                         * oaId : 2381
                         * username : 1022654
                         * oaCode : 1022654
                         */

                        private Object departmentName;
                        private String lastModifiedDate;
                        private String lastModifiedBy;
                        private int sort;
                        private String title;
                        private String createdDate;
                        private String createdBy;
                        private String name;
                        private long id;
                        private DepartmentBeanXXXX department;
                        private String oaId;
                        private String username;
                        private String oaCode;

                        public Object getDepartmentName() {
                            return departmentName;
                        }

                        public void setDepartmentName(Object departmentName) {
                            this.departmentName = departmentName;
                        }

                        public String getLastModifiedDate() {
                            return lastModifiedDate;
                        }

                        public void setLastModifiedDate(String lastModifiedDate) {
                            this.lastModifiedDate = lastModifiedDate;
                        }

                        public String getLastModifiedBy() {
                            return lastModifiedBy;
                        }

                        public void setLastModifiedBy(String lastModifiedBy) {
                            this.lastModifiedBy = lastModifiedBy;
                        }

                        public int getSort() {
                            return sort;
                        }

                        public void setSort(int sort) {
                            this.sort = sort;
                        }

                        public String getTitle() {
                            return title;
                        }

                        public void setTitle(String title) {
                            this.title = title;
                        }

                        public String getCreatedDate() {
                            return createdDate;
                        }

                        public void setCreatedDate(String createdDate) {
                            this.createdDate = createdDate;
                        }

                        public String getCreatedBy() {
                            return createdBy;
                        }

                        public void setCreatedBy(String createdBy) {
                            this.createdBy = createdBy;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public long getId() {
                            return id;
                        }

                        public void setId(long id) {
                            this.id = id;
                        }

                        public DepartmentBeanXXXX getDepartment() {
                            return department;
                        }

                        public void setDepartment(DepartmentBeanXXXX department) {
                            this.department = department;
                        }

                        public String getOaId() {
                            return oaId;
                        }

                        public void setOaId(String oaId) {
                            this.oaId = oaId;
                        }

                        public String getUsername() {
                            return username;
                        }

                        public void setUsername(String username) {
                            this.username = username;
                        }

                        public String getOaCode() {
                            return oaCode;
                        }

                        public void setOaCode(String oaCode) {
                            this.oaCode = oaCode;
                        }

                        public static class DepartmentBeanXXXX {
                            /**
                             * desp : null
                             * createdDate : 2018-12-12 00:00:00
                             * lastModifiedDate : 2019-01-13 11:17:11
                             * createdBy : 13901565517
                             * lastModifiedBy : 徐超
                             * name : 市场部（Z）
                             * oaDepartmentId : 415
                             * id : 78
                             * sort : 200
                             */

                            private Object desp;
                            private String createdDate;
                            private String lastModifiedDate;
                            private String createdBy;
                            private String lastModifiedBy;
                            private String name;
                            private String oaDepartmentId;
                            private long id;
                            private int sort;

                            public Object getDesp() {
                                return desp;
                            }

                            public void setDesp(Object desp) {
                                this.desp = desp;
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

                            public String getCreatedBy() {
                                return createdBy;
                            }

                            public void setCreatedBy(String createdBy) {
                                this.createdBy = createdBy;
                            }

                            public String getLastModifiedBy() {
                                return lastModifiedBy;
                            }

                            public void setLastModifiedBy(String lastModifiedBy) {
                                this.lastModifiedBy = lastModifiedBy;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getOaDepartmentId() {
                                return oaDepartmentId;
                            }

                            public void setOaDepartmentId(String oaDepartmentId) {
                                this.oaDepartmentId = oaDepartmentId;
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
                        }
                    }
                }

                public static class OperatorBeanX {
                    /**
                     * departmentName : null
                     * lastModifiedDate : 2019-01-21 16:03:44
                     * lastModifiedBy : 何达能
                     * sort : 10
                     * title : 副总经理(a)
                     * createdDate : 2018-11-29 21:15:42
                     * createdBy : 13901565517
                     * name : 何达能
                     * id : 38
                     * department : {"desp":"","createdDate":"2018-12-26 20:36:56","lastModifiedDate":"2019-01-17 19:09:22","createdBy":"15167156690","lastModifiedBy":"费婷","name":"总经办","oaDepartmentId":"71","id":45,"sort":100}
                     * oaId : 714
                     * username : 1004605
                     * oaCode : 1004605
                     */

                    private Object departmentName;
                    private String lastModifiedDate;
                    private String lastModifiedBy;
                    private int sort;
                    private String title;
                    private String createdDate;
                    private String createdBy;
                    private String name;
                    private long id;
                    private DepartmentBeanXXXXX department;
                    private String oaId;
                    private String username;
                    private String oaCode;

                    public Object getDepartmentName() {
                        return departmentName;
                    }

                    public void setDepartmentName(Object departmentName) {
                        this.departmentName = departmentName;
                    }

                    public String getLastModifiedDate() {
                        return lastModifiedDate;
                    }

                    public void setLastModifiedDate(String lastModifiedDate) {
                        this.lastModifiedDate = lastModifiedDate;
                    }

                    public String getLastModifiedBy() {
                        return lastModifiedBy;
                    }

                    public void setLastModifiedBy(String lastModifiedBy) {
                        this.lastModifiedBy = lastModifiedBy;
                    }

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getCreatedDate() {
                        return createdDate;
                    }

                    public void setCreatedDate(String createdDate) {
                        this.createdDate = createdDate;
                    }

                    public String getCreatedBy() {
                        return createdBy;
                    }

                    public void setCreatedBy(String createdBy) {
                        this.createdBy = createdBy;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public long getId() {
                        return id;
                    }

                    public void setId(long id) {
                        this.id = id;
                    }

                    public DepartmentBeanXXXXX getDepartment() {
                        return department;
                    }

                    public void setDepartment(DepartmentBeanXXXXX department) {
                        this.department = department;
                    }

                    public String getOaId() {
                        return oaId;
                    }

                    public void setOaId(String oaId) {
                        this.oaId = oaId;
                    }

                    public String getUsername() {
                        return username;
                    }

                    public void setUsername(String username) {
                        this.username = username;
                    }

                    public String getOaCode() {
                        return oaCode;
                    }

                    public void setOaCode(String oaCode) {
                        this.oaCode = oaCode;
                    }

                    public static class DepartmentBeanXXXXX {
                        /**
                         * desp :
                         * createdDate : 2018-12-26 20:36:56
                         * lastModifiedDate : 2019-01-17 19:09:22
                         * createdBy : 15167156690
                         * lastModifiedBy : 费婷
                         * name : 总经办
                         * oaDepartmentId : 71
                         * id : 45
                         * sort : 100
                         */

                        private String desp;
                        private String createdDate;
                        private String lastModifiedDate;
                        private String createdBy;
                        private String lastModifiedBy;
                        private String name;
                        private String oaDepartmentId;
                        private long id;
                        private int sort;

                        public String getDesp() {
                            return desp;
                        }

                        public void setDesp(String desp) {
                            this.desp = desp;
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

                        public String getCreatedBy() {
                            return createdBy;
                        }

                        public void setCreatedBy(String createdBy) {
                            this.createdBy = createdBy;
                        }

                        public String getLastModifiedBy() {
                            return lastModifiedBy;
                        }

                        public void setLastModifiedBy(String lastModifiedBy) {
                            this.lastModifiedBy = lastModifiedBy;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getOaDepartmentId() {
                            return oaDepartmentId;
                        }

                        public void setOaDepartmentId(String oaDepartmentId) {
                            this.oaDepartmentId = oaDepartmentId;
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
                    }
                }
            }
        }
    }
}
