package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:MarketIntelligenceItemBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/16 13:36
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/16 1.00 初始版本
 * ****************************************************************
 */
public class MarketIntelligenceItemBean {


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
         * ascending : false
         * descending : true
         * direction : DESC
         * ignoreCase : false
         * nullHandling : NATIVE
         * property : createdDate
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
         * id : 156
         * sort : 10
         * createdDate : 2019-01-14 15:33:55
         * lastModifiedDate : 2019-01-14 15:33:55
         * createdBy : 潘梦洋
         * lastModifiedBy : 潘梦洋
         * content : 666
         * attachmentList : []
         * intelligenceTypeKey : purchase_supplier_chanage
         * intelligenceInfoKey : purchare_type
         * intelligenceInfoValue : 采购类
         * intelligenceTypeValue : 供应商名录变动
         * itemStatusValue : 待审核
         * operator : {"id":1,"sort":10,"createdDate":"2018-11-29 21:15:42","lastModifiedDate":"2019-01-15 21:05:34","createdBy":"15167156690","lastModifiedBy":"潘梦洋","name":"潘梦洋","username":"1022654","title":"市场专员(z)","departmentName":"","department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","desp":""},"oaCode":"1022654","oaId":"2381"}
         * member : {"id":2835,"sort":10,"createdDate":"2019-01-14 10:34:09","lastModifiedDate":"2019-01-14 10:34:09","createdBy":"何达能","lastModifiedBy":"何达能","orgName":"杭州东部软件园","srOperator":{},"frOperator":{},"arOperator":{},"statusKey":"potential","abbreviation":"db","statusValue":"潜在","crmNumber":"A20190114001","sapNumber":"","provinceName":"浙江省","cooperationTypeValue":"直销","memberLevelValue":"战略","employeeSizeValue":"100GW以下","companyTypeValue":"有限责任公司","customerDemand":"的观点","creditModifyDate":0,"arOperatorName":"未分配","registeredCapital":143434,"frOperatorName":"未分配","srOperatorName":"未分配","creditLevelValue":"AA","riskLevelValue":"","avatarUrl":"","office":{},"riskLevelKey":"","dealer":{},"simpleSpell":"db"}
         * intelligence : {"id":86,"sort":10,"createdDate":"2019-01-14 15:33:55","lastModifiedDate":"2019-01-14 15:33:55","createdBy":"潘梦洋","lastModifiedBy":"潘梦洋","statusKey":"","statusValue":"","bigCategoryKey":"customer","businessTypeKey":"wafer_factory","bigCategoryValue":"客户","businessTypeValue":"硅片厂","operator":{"id":1,"sort":10,"createdDate":"2018-11-29 21:15:42","lastModifiedDate":"2019-01-15 21:05:34","createdBy":"15167156690","lastModifiedBy":"潘梦洋","name":"潘梦洋","username":"1022654","title":"市场专员(z)","departmentName":"","department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","desp":""},"oaCode":"1022654","oaId":"2381"},"member":{"id":2835,"sort":10,"createdDate":"2019-01-14 10:34:09","lastModifiedDate":"2019-01-14 10:34:09","createdBy":"何达能","lastModifiedBy":"何达能","orgName":"杭州东部软件园","srOperator":{},"frOperator":{},"arOperator":{},"statusKey":"potential","abbreviation":"db","statusValue":"潜在","crmNumber":"A20190114001","sapNumber":"","provinceName":"浙江省","cooperationTypeValue":"直销","memberLevelValue":"战略","employeeSizeValue":"100GW以下","companyTypeValue":"有限责任公司","customerDemand":"的观点","creditModifyDate":0,"arOperatorName":"未分配","registeredCapital":143434,"frOperatorName":"未分配","srOperatorName":"未分配","creditLevelValue":"AA","riskLevelValue":"","avatarUrl":"","office":{},"riskLevelKey":"","dealer":{},"simpleSpell":"db"},"collectDate":""}
         * itemStatusKey : draft
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String content;
        private String intelligenceTypeKey;
        private String intelligenceInfoKey;
        private String intelligenceInfoValue;
        private String intelligenceTypeValue;
        private String itemStatusValue;
        private OperatorBean operator;
        private MemberBeanID member;
        private IntelligenceBean intelligence;
        private String itemStatusKey;
        private List<?> attachmentList;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIntelligenceTypeKey() {
            return intelligenceTypeKey;
        }

        public void setIntelligenceTypeKey(String intelligenceTypeKey) {
            this.intelligenceTypeKey = intelligenceTypeKey;
        }

        public String getIntelligenceInfoKey() {
            return intelligenceInfoKey;
        }

        public void setIntelligenceInfoKey(String intelligenceInfoKey) {
            this.intelligenceInfoKey = intelligenceInfoKey;
        }

        public String getIntelligenceInfoValue() {
            return intelligenceInfoValue;
        }

        public void setIntelligenceInfoValue(String intelligenceInfoValue) {
            this.intelligenceInfoValue = intelligenceInfoValue;
        }

        public String getIntelligenceTypeValue() {
            return intelligenceTypeValue;
        }

        public void setIntelligenceTypeValue(String intelligenceTypeValue) {
            this.intelligenceTypeValue = intelligenceTypeValue;
        }

        public String getItemStatusValue() {
            return itemStatusValue;
        }

        public void setItemStatusValue(String itemStatusValue) {
            this.itemStatusValue = itemStatusValue;
        }

        public OperatorBean getOperator() {
            return operator;
        }

        public void setOperator(OperatorBean operator) {
            this.operator = operator;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public IntelligenceBean getIntelligence() {
            return intelligence;
        }

        public void setIntelligence(IntelligenceBean intelligence) {
            this.intelligence = intelligence;
        }

        public String getItemStatusKey() {
            return itemStatusKey;
        }

        public void setItemStatusKey(String itemStatusKey) {
            this.itemStatusKey = itemStatusKey;
        }

        public List<?> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<?> attachmentList) {
            this.attachmentList = attachmentList;
        }

        public static class OperatorBean {
            /**
             * id : 1
             * sort : 10
             * createdDate : 2018-11-29 21:15:42
             * lastModifiedDate : 2019-01-15 21:05:34
             * createdBy : 15167156690
             * lastModifiedBy : 潘梦洋
             * name : 潘梦洋
             * username : 1022654
             * title : 市场专员(z)
             * departmentName :
             * department : {"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","desp":""}
             * oaCode : 1022654
             * oaId : 2381
             */

            private long id;
            private int sort;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String name;
            private String username;
            private String title;
            private String departmentName;
            private DepartmentBean department;
            private String oaCode;
            private String oaId;

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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public DepartmentBean getDepartment() {
                return department;
            }

            public void setDepartment(DepartmentBean department) {
                this.department = department;
            }

            public String getOaCode() {
                return oaCode;
            }

            public void setOaCode(String oaCode) {
                this.oaCode = oaCode;
            }

            public String getOaId() {
                return oaId;
            }

            public void setOaId(String oaId) {
                this.oaId = oaId;
            }

            public static class DepartmentBean {
                /**
                 * id : 78
                 * sort : 200
                 * createdDate : 2018-12-12 00:00:00
                 * lastModifiedDate : 2019-01-13 11:17:11
                 * createdBy : 13901565517
                 * lastModifiedBy : 徐超
                 * name : 市场部（Z）
                 * oaDepartmentId : 415
                 * desp :
                 */

                private long id;
                private int sort;
                private String createdDate;
                private String lastModifiedDate;
                private String createdBy;
                private String lastModifiedBy;
                private String name;
                private String oaDepartmentId;
                private String desp;

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

                public String getDesp() {
                    return desp;
                }

                public void setDesp(String desp) {
                    this.desp = desp;
                }
            }
        }


        public static class IntelligenceBean {
            /**
             * id : 86
             * sort : 10
             * createdDate : 2019-01-14 15:33:55
             * lastModifiedDate : 2019-01-14 15:33:55
             * createdBy : 潘梦洋
             * lastModifiedBy : 潘梦洋
             * statusKey :
             * statusValue :
             * bigCategoryKey : customer
             * businessTypeKey : wafer_factory
             * bigCategoryValue : 客户
             * businessTypeValue : 硅片厂
             * operator : {"id":1,"sort":10,"createdDate":"2018-11-29 21:15:42","lastModifiedDate":"2019-01-15 21:05:34","createdBy":"15167156690","lastModifiedBy":"潘梦洋","name":"潘梦洋","username":"1022654","title":"市场专员(z)","departmentName":"","department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","desp":""},"oaCode":"1022654","oaId":"2381"}
             * member : {"id":2835,"sort":10,"createdDate":"2019-01-14 10:34:09","lastModifiedDate":"2019-01-14 10:34:09","createdBy":"何达能","lastModifiedBy":"何达能","orgName":"杭州东部软件园","srOperator":{},"frOperator":{},"arOperator":{},"statusKey":"potential","abbreviation":"db","statusValue":"潜在","crmNumber":"A20190114001","sapNumber":"","provinceName":"浙江省","cooperationTypeValue":"直销","memberLevelValue":"战略","employeeSizeValue":"100GW以下","companyTypeValue":"有限责任公司","customerDemand":"的观点","creditModifyDate":0,"arOperatorName":"未分配","registeredCapital":143434,"frOperatorName":"未分配","srOperatorName":"未分配","creditLevelValue":"AA","riskLevelValue":"","avatarUrl":"","office":{},"riskLevelKey":"","dealer":{},"simpleSpell":"db"}
             * collectDate :
             */

            private long id;
            private int sort;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String statusKey;
            private String statusValue;
            private String bigCategoryKey;
            private String businessTypeKey;
            private String bigCategoryValue;
            private String businessTypeValue;
            private OperatorBeanX operator;
            private MemberBeanID member;
            private String collectDate;

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

            public String getStatusKey() {
                return statusKey;
            }

            public void setStatusKey(String statusKey) {
                this.statusKey = statusKey;
            }

            public String getStatusValue() {
                return statusValue;
            }

            public void setStatusValue(String statusValue) {
                this.statusValue = statusValue;
            }

            public String getBigCategoryKey() {
                return bigCategoryKey;
            }

            public void setBigCategoryKey(String bigCategoryKey) {
                this.bigCategoryKey = bigCategoryKey;
            }

            public String getBusinessTypeKey() {
                return businessTypeKey;
            }

            public void setBusinessTypeKey(String businessTypeKey) {
                this.businessTypeKey = businessTypeKey;
            }

            public String getBigCategoryValue() {
                return bigCategoryValue;
            }

            public void setBigCategoryValue(String bigCategoryValue) {
                this.bigCategoryValue = bigCategoryValue;
            }

            public String getBusinessTypeValue() {
                return businessTypeValue;
            }

            public void setBusinessTypeValue(String businessTypeValue) {
                this.businessTypeValue = businessTypeValue;
            }

            public OperatorBeanX getOperator() {
                return operator;
            }

            public void setOperator(OperatorBeanX operator) {
                this.operator = operator;
            }

            public MemberBeanID getMember() {
                return member;
            }

            public void setMember(MemberBeanID member) {
                this.member = member;
            }

            public String getCollectDate() {
                return collectDate;
            }

            public void setCollectDate(String collectDate) {
                this.collectDate = collectDate;
            }

            public static class OperatorBeanX {
                /**
                 * id : 1
                 * sort : 10
                 * createdDate : 2018-11-29 21:15:42
                 * lastModifiedDate : 2019-01-15 21:05:34
                 * createdBy : 15167156690
                 * lastModifiedBy : 潘梦洋
                 * name : 潘梦洋
                 * username : 1022654
                 * title : 市场专员(z)
                 * departmentName :
                 * department : {"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","desp":""}
                 * oaCode : 1022654
                 * oaId : 2381
                 */

                private long id;
                private int sort;
                private String createdDate;
                private String lastModifiedDate;
                private String createdBy;
                private String lastModifiedBy;
                private String name;
                private String username;
                private String title;
                private String departmentName;
                private DepartmentBeanX department;
                private String oaCode;
                private String oaId;

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

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDepartmentName() {
                    return departmentName;
                }

                public void setDepartmentName(String departmentName) {
                    this.departmentName = departmentName;
                }

                public DepartmentBeanX getDepartment() {
                    return department;
                }

                public void setDepartment(DepartmentBeanX department) {
                    this.department = department;
                }

                public String getOaCode() {
                    return oaCode;
                }

                public void setOaCode(String oaCode) {
                    this.oaCode = oaCode;
                }

                public String getOaId() {
                    return oaId;
                }

                public void setOaId(String oaId) {
                    this.oaId = oaId;
                }

                public static class DepartmentBeanX {
                    /**
                     * id : 78
                     * sort : 200
                     * createdDate : 2018-12-12 00:00:00
                     * lastModifiedDate : 2019-01-13 11:17:11
                     * createdBy : 13901565517
                     * lastModifiedBy : 徐超
                     * name : 市场部（Z）
                     * oaDepartmentId : 415
                     * desp :
                     */

                    private long id;
                    private int sort;
                    private String createdDate;
                    private String lastModifiedDate;
                    private String createdBy;
                    private String lastModifiedBy;
                    private String name;
                    private String oaDepartmentId;
                    private String desp;

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

                    public String getDesp() {
                        return desp;
                    }

                    public void setDesp(String desp) {
                        this.desp = desp;
                    }
                }
            }

        }
    }
}
