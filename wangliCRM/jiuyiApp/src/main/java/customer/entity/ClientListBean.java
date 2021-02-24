package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:ClientListBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/8 11:40
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/8 1.00 初始版本
 * ****************************************************************
 */
public class ClientListBean {

    /**
     * number : 0
     * last : true
     * numberOfElements : 1
     * size : 5
     * totalPages : 1
     * sort : [{"nullHandling":"NATIVE","ignoreCase":false,"property":"id","ascending":true,"descending":false,"direction":"ASC"}]
     * first : true
     * content : [{"businessTypeValue":"硅料厂","salesCount":0,"lastModifiedDate":"2019-01-24 20:10:52","purchaseCount":0,"lastModifiedBy":"陈刚(a)","memberOfMember":{"crmNumber":"201930","statusValue":"正式","srOperatorName":"未分配","office":null,"creditLevelValue":"A","sapNumber":"201930","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"tjjg","statusKey":"formal","srOperator":null,"id":8373,"companyTypeValue":null,"orgName":"天津建工工程管理有限公司","arOperator":null,"lastModifiedDate":"2019-01-24 20:06:52","avatarUrl":null,"lastModifiedBy":"系统自动创建","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"天津建工","customerDemand":null,"createdDate":"2019-01-24 20:06:52","createdBy":"系统自动创建","dealer":null,"cooperationTypeValue":"供应商","provinceName":null,"riskLevelValue":null,"frOperatorName":"未分配"},"sort":10,"productCount":0,"operator":{"departmentName":null,"lastModifiedDate":"2019-01-24 19:05:02","lastModifiedBy":"陈刚(a)","sort":10,"title":"总经理(a)","createdDate":"2019-01-10 14:08:40","createdBy":"徐超","name":"陈刚(a)","id":143,"department":{"desp":"","createdDate":"2018-12-26 20:36:56","lastModifiedDate":"2019-01-23 04:17:45","createdBy":"15167156690","lastModifiedBy":"韩卫涛","name":"总经办","oaDepartmentId":"71","id":45,"sort":100},"oaId":"534","oaCode":"1009999","username":"1009999"},"businessTypeKey":"silicon_factory","createdDate":"2019-01-24 20:10:52","createdBy":"陈刚(a)","member":{"crmNumber":"200431","statusValue":"正式","srOperatorName":"未分配","office":null,"creditLevelValue":"A","sapNumber":"200431","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"an","statusKey":"formal","srOperator":null,"id":6172,"companyTypeValue":null,"orgName":"中环艾能（北京）科技有限公司","arOperator":null,"lastModifiedDate":"2019-01-24 20:06:07","avatarUrl":null,"lastModifiedBy":"系统自动创建","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"艾能","customerDemand":null,"createdDate":"2019-01-24 20:06:03","createdBy":"系统自动创建","dealer":null,"cooperationTypeValue":"直销","provinceName":null,"riskLevelValue":null,"frOperatorName":"未分配"},"id":19}]
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
         * property : id
         * ascending : true
         * descending : false
         * direction : ASC
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
         * businessTypeValue : 硅料厂
         * salesCount : 0
         * lastModifiedDate : 2019-01-24 20:10:52
         * purchaseCount : 0
         * lastModifiedBy : 陈刚(a)
         * memberOfMember : {"crmNumber":"201930","statusValue":"正式","srOperatorName":"未分配","office":null,"creditLevelValue":"A","sapNumber":"201930","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"tjjg","statusKey":"formal","srOperator":null,"id":8373,"companyTypeValue":null,"orgName":"天津建工工程管理有限公司","arOperator":null,"lastModifiedDate":"2019-01-24 20:06:52","avatarUrl":null,"lastModifiedBy":"系统自动创建","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"天津建工","customerDemand":null,"createdDate":"2019-01-24 20:06:52","createdBy":"系统自动创建","dealer":null,"cooperationTypeValue":"供应商","provinceName":null,"riskLevelValue":null,"frOperatorName":"未分配"}
         * sort : 10
         * productCount : 0
         * operator : {"departmentName":null,"lastModifiedDate":"2019-01-24 19:05:02","lastModifiedBy":"陈刚(a)","sort":10,"title":"总经理(a)","createdDate":"2019-01-10 14:08:40","createdBy":"徐超","name":"陈刚(a)","id":143,"department":{"desp":"","createdDate":"2018-12-26 20:36:56","lastModifiedDate":"2019-01-23 04:17:45","createdBy":"15167156690","lastModifiedBy":"韩卫涛","name":"总经办","oaDepartmentId":"71","id":45,"sort":100},"oaId":"534","oaCode":"1009999","username":"1009999"}
         * businessTypeKey : silicon_factory
         * createdDate : 2019-01-24 20:10:52
         * createdBy : 陈刚(a)
         * member : {"crmNumber":"200431","statusValue":"正式","srOperatorName":"未分配","office":null,"creditLevelValue":"A","sapNumber":"200431","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"an","statusKey":"formal","srOperator":null,"id":6172,"companyTypeValue":null,"orgName":"中环艾能（北京）科技有限公司","arOperator":null,"lastModifiedDate":"2019-01-24 20:06:07","avatarUrl":null,"lastModifiedBy":"系统自动创建","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"艾能","customerDemand":null,"createdDate":"2019-01-24 20:06:03","createdBy":"系统自动创建","dealer":null,"cooperationTypeValue":"直销","provinceName":null,"riskLevelValue":null,"frOperatorName":"未分配"}
         * id : 19
         */

        private String businessTypeValue;
        private int salesCount;
        private String lastModifiedDate;
        private int purchaseCount;
        private String lastModifiedBy;
        private MemberOfMemberBean memberOfMember;
        private int sort;
        private int productCount;
        private OperatorBean operator;
        private String businessTypeKey;
        private String createdDate;
        private String createdBy;
        private MemberBean member;


        private long id;

        public String getBusinessTypeValue() {
            return businessTypeValue;
        }

        public void setBusinessTypeValue(String businessTypeValue) {
            this.businessTypeValue = businessTypeValue;
        }

        public int getSalesCount() {
            return salesCount;
        }

        public void setSalesCount(int salesCount) {
            this.salesCount = salesCount;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public int getPurchaseCount() {
            return purchaseCount;
        }

        public void setPurchaseCount(int purchaseCount) {
            this.purchaseCount = purchaseCount;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public MemberOfMemberBean getMemberOfMember() {
            return memberOfMember;
        }

        public void setMemberOfMember(MemberOfMemberBean memberOfMember) {
            this.memberOfMember = memberOfMember;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getProductCount() {
            return productCount;
        }

        public void setProductCount(int productCount) {
            this.productCount = productCount;
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

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public static class MemberOfMemberBean {
            /**
             * crmNumber : 201930
             * statusValue : 正式
             * srOperatorName : 未分配
             * office : null
             * creditLevelValue : A
             * sapNumber : 201930
             * arOperatorName : 未分配
             * frOperator : null
             * memberLevelValue : 普通
             * employeeSizeValue : null
             * registeredCapital : null
             * simpleSpell : tjjg
             * statusKey : formal
             * srOperator : null
             * id : 8373
             * companyTypeValue : null
             * orgName : 天津建工工程管理有限公司
             * arOperator : null
             * lastModifiedDate : 2019-01-24 20:06:52
             * avatarUrl : null
             * lastModifiedBy : 系统自动创建
             * riskLevelKey : null
             * creditModifyDate : null
             * sort : 10
             * abbreviation : 天津建工
             * customerDemand : null
             * createdDate : 2019-01-24 20:06:52
             * createdBy : 系统自动创建
             * dealer : null
             * cooperationTypeValue : 供应商
             * provinceName : null
             * riskLevelValue : null
             * frOperatorName : 未分配
             */

            private String crmNumber;
            private String statusValue;
            private String srOperatorName;
            private Object office;
            private String creditLevelValue;
            private String sapNumber;
            private String arOperatorName;
            private Object frOperator;
            private String memberLevelValue;
            private Object employeeSizeValue;
            private Object registeredCapital;
            private String simpleSpell;
            private String statusKey;
            private Object srOperator;
            private long id;
            private Object companyTypeValue;
            private String orgName;
            private Object arOperator;
            private String lastModifiedDate;
            private Object avatarUrl;
            private String lastModifiedBy;
            private Object riskLevelKey;
            private Object creditModifyDate;
            private int sort;
            private String abbreviation;
            private Object customerDemand;
            private String createdDate;
            private String createdBy;
            private Object dealer;
            private String cooperationTypeValue;
            private Object provinceName;
            private Object riskLevelValue;
            private String frOperatorName;

            public String getBusinessScope() {
                return businessScope;
            }

            public void setBusinessScope(String businessScope) {
                this.businessScope = businessScope;
            }

            private String businessScope;

            public String getCrmNumber() {
                return crmNumber;
            }

            public void setCrmNumber(String crmNumber) {
                this.crmNumber = crmNumber;
            }

            public String getStatusValue() {
                return statusValue;
            }

            public void setStatusValue(String statusValue) {
                this.statusValue = statusValue;
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

            public String getSapNumber() {
                return sapNumber;
            }

            public void setSapNumber(String sapNumber) {
                this.sapNumber = sapNumber;
            }

            public String getArOperatorName() {
                return arOperatorName;
            }

            public void setArOperatorName(String arOperatorName) {
                this.arOperatorName = arOperatorName;
            }

            public Object getFrOperator() {
                return frOperator;
            }

            public void setFrOperator(Object frOperator) {
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

            public Object getRegisteredCapital() {
                return registeredCapital;
            }

            public void setRegisteredCapital(Object registeredCapital) {
                this.registeredCapital = registeredCapital;
            }

            public String getSimpleSpell() {
                return simpleSpell;
            }

            public void setSimpleSpell(String simpleSpell) {
                this.simpleSpell = simpleSpell;
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

            public Object getCompanyTypeValue() {
                return companyTypeValue;
            }

            public void setCompanyTypeValue(Object companyTypeValue) {
                this.companyTypeValue = companyTypeValue;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public Object getArOperator() {
                return arOperator;
            }

            public void setArOperator(Object arOperator) {
                this.arOperator = arOperator;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public Object getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(Object avatarUrl) {
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

            public Object getCustomerDemand() {
                return customerDemand;
            }

            public void setCustomerDemand(Object customerDemand) {
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

            public String getCooperationTypeValue() {
                return cooperationTypeValue;
            }

            public void setCooperationTypeValue(String cooperationTypeValue) {
                this.cooperationTypeValue = cooperationTypeValue;
            }

            public Object getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(Object provinceName) {
                this.provinceName = provinceName;
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
        }

        public static class OperatorBean implements Parcelable {
            /**
             * departmentName : null
             * lastModifiedDate : 2019-01-24 19:05:02
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

            protected OperatorBean(Parcel in) {
                lastModifiedDate = in.readString();
                lastModifiedBy = in.readString();
                sort = in.readInt();
                title = in.readString();
                createdDate = in.readString();
                createdBy = in.readString();
                name = in.readString();
                id = in.readLong();
                oaId = in.readString();
                oaCode = in.readString();
                username = in.readString();
            }


            public static final Creator<OperatorBean> CREATOR = new Creator<OperatorBean>() {
                @Override
                public OperatorBean createFromParcel(Parcel in) {
                    return new OperatorBean(in);
                }

                @Override
                public OperatorBean[] newArray(int size) {
                    return new OperatorBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(lastModifiedDate);
                dest.writeString(lastModifiedBy);
                dest.writeInt(sort);
                dest.writeString(title);
                dest.writeString(createdDate);
                dest.writeString(createdBy);
                dest.writeString(name);
                dest.writeLong(id);
                dest.writeParcelable(department, flags);
                dest.writeString(oaId);
                dest.writeString(oaCode);
                dest.writeString(username);
            }


            public static class DepartmentBean implements Parcelable{
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

                protected DepartmentBean(Parcel in) {
                    desp = in.readString();
                    createdDate = in.readString();
                    lastModifiedDate = in.readString();
                    createdBy = in.readString();
                    lastModifiedBy = in.readString();
                    name = in.readString();
                    oaDepartmentId = in.readString();
                    id = in.readLong();
                    sort = in.readInt();
                }

                public static final Creator<DepartmentBean> CREATOR = new Creator<DepartmentBean>() {
                    @Override
                    public DepartmentBean createFromParcel(Parcel in) {
                        return new DepartmentBean(in);
                    }

                    @Override
                    public DepartmentBean[] newArray(int size) {
                        return new DepartmentBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(desp);
                    dest.writeString(createdDate);
                    dest.writeString(lastModifiedDate);
                    dest.writeString(createdBy);
                    dest.writeString(lastModifiedBy);
                    dest.writeString(name);
                    dest.writeString(oaDepartmentId);
                    dest.writeLong(id);
                    dest.writeInt(sort);
                }
            }
        }

        public static class MemberBean {
            /**
             * crmNumber : 200431
             * statusValue : 正式
             * srOperatorName : 未分配
             * office : null
             * creditLevelValue : A
             * sapNumber : 200431
             * arOperatorName : 未分配
             * frOperator : null
             * memberLevelValue : 普通
             * employeeSizeValue : null
             * registeredCapital : null
             * simpleSpell : an
             * statusKey : formal
             * srOperator : null
             * id : 6172
             * companyTypeValue : null
             * orgName : 中环艾能（北京）科技有限公司
             * arOperator : null
             * lastModifiedDate : 2019-01-24 20:06:07
             * avatarUrl : null
             * lastModifiedBy : 系统自动创建
             * riskLevelKey : null
             * creditModifyDate : null
             * sort : 10
             * abbreviation : 艾能
             * customerDemand : null
             * createdDate : 2019-01-24 20:06:03
             * createdBy : 系统自动创建
             * dealer : null
             * cooperationTypeValue : 直销
             * provinceName : null
             * riskLevelValue : null
             * frOperatorName : 未分配
             */

            private String crmNumber;
            private String statusValue;
            private String srOperatorName;
            private Object office;
            private String creditLevelValue;
            private String sapNumber;
            private String arOperatorName;
            private Object frOperator;
            private String memberLevelValue;
            private Object employeeSizeValue;
            private Object registeredCapital;
            private String simpleSpell;
            private String statusKey;
            private Object srOperator;
            private long id;
            private Object companyTypeValue;
            private String orgName;
            private Object arOperator;
            private String lastModifiedDate;
            private Object avatarUrl;
            private String lastModifiedBy;
            private Object riskLevelKey;
            private Object creditModifyDate;
            private int sort;
            private String abbreviation;
            private Object customerDemand;
            private String createdDate;
            private String createdBy;
            private Object dealer;
            private String cooperationTypeValue;
            private Object provinceName;
            private Object riskLevelValue;
            private String frOperatorName;

            public String getCrmNumber() {
                return crmNumber;
            }

            public void setCrmNumber(String crmNumber) {
                this.crmNumber = crmNumber;
            }

            public String getStatusValue() {
                return statusValue;
            }

            public void setStatusValue(String statusValue) {
                this.statusValue = statusValue;
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

            public String getSapNumber() {
                return sapNumber;
            }

            public void setSapNumber(String sapNumber) {
                this.sapNumber = sapNumber;
            }

            public String getArOperatorName() {
                return arOperatorName;
            }

            public void setArOperatorName(String arOperatorName) {
                this.arOperatorName = arOperatorName;
            }

            public Object getFrOperator() {
                return frOperator;
            }

            public void setFrOperator(Object frOperator) {
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

            public Object getRegisteredCapital() {
                return registeredCapital;
            }

            public void setRegisteredCapital(Object registeredCapital) {
                this.registeredCapital = registeredCapital;
            }

            public String getSimpleSpell() {
                return simpleSpell;
            }

            public void setSimpleSpell(String simpleSpell) {
                this.simpleSpell = simpleSpell;
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

            public Object getCompanyTypeValue() {
                return companyTypeValue;
            }

            public void setCompanyTypeValue(Object companyTypeValue) {
                this.companyTypeValue = companyTypeValue;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public Object getArOperator() {
                return arOperator;
            }

            public void setArOperator(Object arOperator) {
                this.arOperator = arOperator;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public Object getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(Object avatarUrl) {
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

            public Object getCustomerDemand() {
                return customerDemand;
            }

            public void setCustomerDemand(Object customerDemand) {
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

            public String getCooperationTypeValue() {
                return cooperationTypeValue;
            }

            public void setCooperationTypeValue(String cooperationTypeValue) {
                this.cooperationTypeValue = cooperationTypeValue;
            }

            public Object getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(Object provinceName) {
                this.provinceName = provinceName;
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
        }
    }
}
