package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:IqcMaterialBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/4 19:33
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/4 1.00 初始版本
 * ****************************************************************
 */
public class IqcMaterialBean {

    /**
     * number : 0
     * last : true
     * numberOfElements : 1
     * size : 5
     * totalPages : 1
     * sort : [{"nullHandling":"NATIVE","ignoreCase":false,"property":"lastModifiedDate","ascending":false,"descending":true,"direction":"DESC"}]
     * first : true
     * content : [{"checkQuanity":1,"businessTypeValue":"硅料厂","lastModifiedDate":"2019-01-26 19:30:53","lastModifiedBy":"陈刚(a)","remark":"备注","sort":10,"checkDate":"2019-01-26","operator":{"departmentName":null,"lastModifiedDate":"2019-01-26 19:23:01","lastModifiedBy":"陈刚(a)","sort":10,"title":"总经理(a)","createdDate":"2019-01-10 14:08:40","createdBy":"徐超","name":"陈刚(a)","id":143,"department":{"desp":"","createdDate":"2018-12-26 20:36:56","lastModifiedDate":"2019-01-23 04:17:45","createdBy":"15167156690","lastModifiedBy":"韩卫涛","name":"总经办","oaDepartmentId":"71","id":45,"sort":100},"oaId":"534","oaCode":"1009999","username":"1009999"},"businessTypeKey":"silicon_factory","createdDate":"2019-01-26 19:30:53","createdBy":"陈刚(a)","memberFactory":"工厂","member":{"crmNumber":"200139","statusValue":"正式","srOperatorName":"刘少岗","office":null,"creditLevelValue":"A","sapNumber":"200139","arOperatorName":"徐超","frOperator":{"departmentName":null,"lastModifiedDate":"2019-01-26 15:20:20","lastModifiedBy":"胡志豪","sort":10,"title":"品质客服高级工程师","createdDate":"2019-01-10 13:46:11","createdBy":"徐超","name":"秦光明","id":140,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 13:46:13","createdBy":"13901565517","lastModifiedBy":"徐超","name":"客服部（Z）","oaDepartmentId":"440","id":92,"sort":200},"oaId":"1708","oaCode":"1017179","username":"1017179"},"memberLevelValue":"普通","employeeSizeValue":null,"simpleSpell":"zjjk","registeredCapital":null,"statusKey":"formal","srOperator":{"departmentName":null,"lastModifiedDate":"2019-01-26 15:20:20","lastModifiedBy":"胡志豪","sort":10,"title":"商务经理（Z）","createdDate":"2019-01-10 13:51:10","createdBy":"徐超","name":"刘少岗","id":141,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200},"oaId":"1844","oaCode":"1018213","username":"1018213"},"id":5758,"companyTypeValue":null,"orgName":"浙江晶科能源有限公司","arOperator":{"departmentName":null,"lastModifiedDate":"2019-01-26 17:29:37","lastModifiedBy":"徐超","sort":10,"title":"销售经理（Z）","createdDate":"2019-01-10 12:05:26","createdBy":"费婷","name":"徐超","id":139,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 12:05:27","createdBy":"13901565517","lastModifiedBy":"费婷","name":"国内销售部（Z）","oaDepartmentId":"411","id":75,"sort":200},"oaId":"651","oaCode":"1004613","username":"1004613"},"lastModifiedDate":"2019-01-26 16:23:58","avatarUrl":null,"lastModifiedBy":"徐超","riskLevelKey":null,"creditModifyDate":null,"businessScope":"组件","sort":10,"abbreviation":"浙江晶科","customerDemand":null,"createdDate":"2019-01-24 20:06:03","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"cooperationTypeValue":"直销","provinceName":null,"frOperatorName":"秦光明"},"id":14,"iqcMaterialItemSet":[]}]
     * totalElements : 1
     */

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
         * property : lastModifiedDate
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
        /**
         * checkQuanity : 1.0
         * businessTypeValue : 硅料厂
         * lastModifiedDate : 2019-01-26 19:30:53
         * lastModifiedBy : 陈刚(a)
         * remark : 备注
         * sort : 10
         * checkDate : 2019-01-26
         * operator : {"departmentName":null,"lastModifiedDate":"2019-01-26 19:23:01","lastModifiedBy":"陈刚(a)","sort":10,"title":"总经理(a)","createdDate":"2019-01-10 14:08:40","createdBy":"徐超","name":"陈刚(a)","id":143,"department":{"desp":"","createdDate":"2018-12-26 20:36:56","lastModifiedDate":"2019-01-23 04:17:45","createdBy":"15167156690","lastModifiedBy":"韩卫涛","name":"总经办","oaDepartmentId":"71","id":45,"sort":100},"oaId":"534","oaCode":"1009999","username":"1009999"}
         * businessTypeKey : silicon_factory
         * createdDate : 2019-01-26 19:30:53
         * createdBy : 陈刚(a)
         * memberFactory : 工厂
         * member : {"crmNumber":"200139","statusValue":"正式","srOperatorName":"刘少岗","office":null,"creditLevelValue":"A","sapNumber":"200139","arOperatorName":"徐超","frOperator":{"departmentName":null,"lastModifiedDate":"2019-01-26 15:20:20","lastModifiedBy":"胡志豪","sort":10,"title":"品质客服高级工程师","createdDate":"2019-01-10 13:46:11","createdBy":"徐超","name":"秦光明","id":140,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 13:46:13","createdBy":"13901565517","lastModifiedBy":"徐超","name":"客服部（Z）","oaDepartmentId":"440","id":92,"sort":200},"oaId":"1708","oaCode":"1017179","username":"1017179"},"memberLevelValue":"普通","employeeSizeValue":null,"simpleSpell":"zjjk","registeredCapital":null,"statusKey":"formal","srOperator":{"departmentName":null,"lastModifiedDate":"2019-01-26 15:20:20","lastModifiedBy":"胡志豪","sort":10,"title":"商务经理（Z）","createdDate":"2019-01-10 13:51:10","createdBy":"徐超","name":"刘少岗","id":141,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200},"oaId":"1844","oaCode":"1018213","username":"1018213"},"id":5758,"companyTypeValue":null,"orgName":"浙江晶科能源有限公司","arOperator":{"departmentName":null,"lastModifiedDate":"2019-01-26 17:29:37","lastModifiedBy":"徐超","sort":10,"title":"销售经理（Z）","createdDate":"2019-01-10 12:05:26","createdBy":"费婷","name":"徐超","id":139,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 12:05:27","createdBy":"13901565517","lastModifiedBy":"费婷","name":"国内销售部（Z）","oaDepartmentId":"411","id":75,"sort":200},"oaId":"651","oaCode":"1004613","username":"1004613"},"lastModifiedDate":"2019-01-26 16:23:58","avatarUrl":null,"lastModifiedBy":"徐超","riskLevelKey":null,"creditModifyDate":null,"businessScope":"组件","sort":10,"abbreviation":"浙江晶科","customerDemand":null,"createdDate":"2019-01-24 20:06:03","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"cooperationTypeValue":"直销","provinceName":null,"frOperatorName":"秦光明"}
         * id : 14
         * iqcMaterialItemSet : []
         */

        private double checkQuanity;
        private String businessTypeValue;
        private String lastModifiedDate;
        private String lastModifiedBy;
        private String remark;
        private String checkDate;
        private OperatorBean operator;
        private String businessTypeKey;
        private String createdDate;
        private String createdBy;
        private String memberFactory;
        private MemberBeanID member;
        private long id;
        private List<IqcMaterialItemSetBean> iqcMaterialItemSet;

        public double getCheckQuanity() {
            return checkQuanity;
        }

        public void setCheckQuanity(double checkQuanity) {
            this.checkQuanity = checkQuanity;
        }

        public String getBusinessTypeValue() {
            return businessTypeValue;
        }

        public void setBusinessTypeValue(String businessTypeValue) {
            this.businessTypeValue = businessTypeValue;
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



        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public OperatorBean getOperator() {
            return operator;
        }

        public void setOperator(OperatorBean operator) {
            this.operator = operator;
        }

        public String getBusinessTypeKey() {
            return businessTypeKey;
        }

        public void setBusinessTypeKey(String businessTypeKey) {
            this.businessTypeKey = businessTypeKey;
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

        public String getMemberFactory() {
            return memberFactory;
        }

        public void setMemberFactory(String memberFactory) {
            this.memberFactory = memberFactory;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public List<IqcMaterialItemSetBean> getIqcMaterialItemSet() {
            return iqcMaterialItemSet;
        }

        public void setIqcMaterialItemSet(List<IqcMaterialItemSetBean> iqcMaterialItemSet) {
            this.iqcMaterialItemSet = iqcMaterialItemSet;
        }

        public static class OperatorBean {
            /**
             * departmentName : null
             * lastModifiedDate : 2019-01-26 19:23:01
             * lastModifiedBy : 陈刚(a)
             * sort : 10
             * title : 总经理(a)
             * createdDate : 2019-01-10 14:08:40
             * createdBy : 徐超
             * name : 陈刚(a)
             * id : 143
             * department : {"desp":"","createdDate":"2018-12-26 20:36:56","lastModifiedDate":"2019-01-23 04:17:45","createdBy":"15167156690","lastModifiedBy":"韩卫涛","name":"总经办","oaDepartmentId":"71","id":45,"sort":100}
             * oaId : 534
             * oaCode : 1009999
             * username : 1009999
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
            private String oaCode;
            private String username;

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

            public String getOaCode() {
                return oaCode;
            }

            public void setOaCode(String oaCode) {
                this.oaCode = oaCode;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public static class DepartmentBean {
                /**
                 * desp :
                 * createdDate : 2018-12-26 20:36:56
                 * lastModifiedDate : 2019-01-23 04:17:45
                 * createdBy : 15167156690
                 * lastModifiedBy : 韩卫涛
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

    public static class IqcMaterialItemSetBean {
        /**
         * effic : 21.5-21.6%
         * actualEfficient : a
         * lastModifiedDate : 2019-01-26 19:30:53
         * lid : null
         * lastModifiedBy : 陈刚(a)
         * boiledTest : null
         * zwpc : 5.25 WPC
         * sort : 10
         * pressure : null
         * erdat : 2019-01-25
         * arrivalDate : null
         * result : null
         * pull : null
         * elword : null
         * createdDate : 2019-01-26 19:30:53
         * sapinvoiceNumber : 80035743
         * createdBy : 陈刚(a)
         * invoiceQuanity : 630.0
         * id : 11
         * iqcMaterial : {"checkQuanity":1,"businessTypeValue":"硅料厂","lastModifiedDate":"2019-01-26 19:30:53","lastModifiedBy":"陈刚(a)","remark":"备注","sort":10,"checkDate":"2019-01-26","operator":{"departmentName":null,"lastModifiedDate":"2019-01-26 19:23:01","lastModifiedBy":"陈刚(a)","sort":10,"title":"总经理(a)","createdDate":"2019-01-10 14:08:40","createdBy":"徐超","name":"陈刚(a)","id":143,"department":{"desp":"","createdDate":"2018-12-26 20:36:56","lastModifiedDate":"2019-01-23 04:17:45","createdBy":"15167156690","lastModifiedBy":"韩卫涛","name":"总经办","oaDepartmentId":"71","id":45,"sort":100},"oaId":"534","oaCode":"1009999","username":"1009999"},"businessTypeKey":"silicon_factory","createdDate":"2019-01-26 19:30:53","createdBy":"陈刚(a)","memberFactory":"工厂","member":{"crmNumber":"200139","statusValue":"正式","srOperatorName":"刘少岗","office":null,"creditLevelValue":"A","sapNumber":"200139","arOperatorName":"徐超","frOperator":{"departmentName":null,"lastModifiedDate":"2019-01-26 15:20:20","lastModifiedBy":"胡志豪","sort":10,"title":"品质客服高级工程师","createdDate":"2019-01-10 13:46:11","createdBy":"徐超","name":"秦光明","id":140,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 13:46:13","createdBy":"13901565517","lastModifiedBy":"徐超","name":"客服部（Z）","oaDepartmentId":"440","id":92,"sort":200},"oaId":"1708","oaCode":"1017179","username":"1017179"},"memberLevelValue":"普通","employeeSizeValue":null,"simpleSpell":"zjjk","registeredCapital":null,"statusKey":"formal","srOperator":{"departmentName":null,"lastModifiedDate":"2019-01-26 15:20:20","lastModifiedBy":"胡志豪","sort":10,"title":"商务经理（Z）","createdDate":"2019-01-10 13:51:10","createdBy":"徐超","name":"刘少岗","id":141,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200},"oaId":"1844","oaCode":"1018213","username":"1018213"},"id":5758,"companyTypeValue":null,"orgName":"浙江晶科能源有限公司","arOperator":{"departmentName":null,"lastModifiedDate":"2019-01-26 17:29:37","lastModifiedBy":"徐超","sort":10,"title":"销售经理（Z）","createdDate":"2019-01-10 12:05:26","createdBy":"费婷","name":"徐超","id":139,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 12:05:27","createdBy":"13901565517","lastModifiedBy":"费婷","name":"国内销售部（Z）","oaDepartmentId":"411","id":75,"sort":200},"oaId":"651","oaCode":"1004613","username":"1004613"},"lastModifiedDate":"2019-01-26 16:23:58","avatarUrl":null,"lastModifiedBy":"徐超","riskLevelKey":null,"creditModifyDate":null,"businessScope":"组件","sort":10,"abbreviation":"浙江晶科","customerDemand":null,"createdDate":"2019-01-24 20:06:03","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"cooperationTypeValue":"直销","provinceName":null,"frOperatorName":"秦光明"},"id":14}
         * outward : null
         * batteryType : a
         * iqcMaterialNumber : 60000700
         */

        private String effic;
        private String actualEfficient;
        private String lastModifiedDate;
        private Object lid;
        private String lastModifiedBy;
        private Object boiledTest;
        private String zwpc;
        private int sort;
        private Object pressure;
        private String erdat;
        private Object arrivalDate;
        private Object result;
        private Object pull;
        private Object elword;
        private String createdDate;
        private String sapinvoiceNumber;
        private String createdBy;
        private double invoiceQuanity;
        private long id;
        private IqcMaterialBean iqcMaterial;
        private Object outward;
        private String batteryType;
        private String iqcMaterialNumber;

        public String getEffic() {
            return effic;
        }

        public void setEffic(String effic) {
            this.effic = effic;
        }

        public String getActualEfficient() {
            return actualEfficient;
        }

        public void setActualEfficient(String actualEfficient) {
            this.actualEfficient = actualEfficient;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public Object getLid() {
            return lid;
        }

        public void setLid(Object lid) {
            this.lid = lid;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public Object getBoiledTest() {
            return boiledTest;
        }

        public void setBoiledTest(Object boiledTest) {
            this.boiledTest = boiledTest;
        }

        public String getZwpc() {
            return zwpc;
        }

        public void setZwpc(String zwpc) {
            this.zwpc = zwpc;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getPressure() {
            return pressure;
        }

        public void setPressure(Object pressure) {
            this.pressure = pressure;
        }

        public String getErdat() {
            return erdat;
        }

        public void setErdat(String erdat) {
            this.erdat = erdat;
        }

        public Object getArrivalDate() {
            return arrivalDate;
        }

        public void setArrivalDate(Object arrivalDate) {
            this.arrivalDate = arrivalDate;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public Object getPull() {
            return pull;
        }

        public void setPull(Object pull) {
            this.pull = pull;
        }

        public Object getElword() {
            return elword;
        }

        public void setElword(Object elword) {
            this.elword = elword;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getSapinvoiceNumber() {
            return sapinvoiceNumber;
        }

        public void setSapinvoiceNumber(String sapinvoiceNumber) {
            this.sapinvoiceNumber = sapinvoiceNumber;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public double getInvoiceQuanity() {
            return invoiceQuanity;
        }

        public void setInvoiceQuanity(double invoiceQuanity) {
            this.invoiceQuanity = invoiceQuanity;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public IqcMaterialBean getIqcMaterial() {
            return iqcMaterial;
        }

        public void setIqcMaterial(IqcMaterialBean iqcMaterial) {
            this.iqcMaterial = iqcMaterial;
        }

        public Object getOutward() {
            return outward;
        }

        public void setOutward(Object outward) {
            this.outward = outward;
        }

        public String getBatteryType() {
            return batteryType;
        }

        public void setBatteryType(String batteryType) {
            this.batteryType = batteryType;
        }

        public String getIqcMaterialNumber() {
            return iqcMaterialNumber;
        }

        public void setIqcMaterialNumber(String iqcMaterialNumber) {
            this.iqcMaterialNumber = iqcMaterialNumber;
        }

    }
}
