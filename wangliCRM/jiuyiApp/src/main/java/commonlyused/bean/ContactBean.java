package commonlyused.bean;

import java.util.List;

public class ContactBean {



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

        private String birthday;
        private OfficeBean office;
        private String phoneTwo;
        private String phoneFour;
        private String areaName;
        private String simpleSpell;
        private MemberBeanX member;
        private long id;
        private String email;
        private boolean incumbency;
        private String address;
        private String lastModifiedDate;
        private String lastModifiedBy;
        private String sex;
        private String areaNumber;
        private int sort;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public OfficeBean getOffice() {
            return office;
        }

        public void setOffice(OfficeBean office) {
            this.office = office;
        }

        public String getPhoneTwo() {
            return phoneTwo;
        }

        public void setPhoneTwo(String phoneTwo) {
            this.phoneTwo = phoneTwo;
        }

        public String getPhoneFour() {
            return phoneFour;
        }

        public void setPhoneFour(String phoneFour) {
            this.phoneFour = phoneFour;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getSimpleSpell() {
            return simpleSpell;
        }

        public void setSimpleSpell(String simpleSpell) {
            this.simpleSpell = simpleSpell;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isIncumbency() {
            return incumbency;
        }

        public void setIncumbency(boolean incumbency) {
            this.incumbency = incumbency;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAreaNumber() {
            return areaNumber;
        }

        public void setAreaNumber(String areaNumber) {
            this.areaNumber = areaNumber;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getOfficeAddress() {
            return officeAddress;
        }

        public void setOfficeAddress(String officeAddress) {
            this.officeAddress = officeAddress;
        }

        public String getAvatralUrl() {
            return avatralUrl;
        }

        public void setAvatralUrl(String avatralUrl) {
            this.avatralUrl = avatralUrl;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoneThree() {
            return phoneThree;
        }

        public void setPhoneThree(String phoneThree) {
            this.phoneThree = phoneThree;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDuty() {
            return duty;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }

        private String officeAddress;
        private String avatralUrl;
        private String createdDate;
        private String createdBy;
        private String phone;
        private String phoneThree;
        private String name;
        private String duty;
        private String favorite;


        public static class OfficeBean {

            private Object parentOffice;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private MemberBean member;
            private String name;
            private long id;
            private int sort;

            public Object getParentOffice() {
                return parentOffice;
            }

            public void setParentOffice(Object parentOffice) {
                this.parentOffice = parentOffice;
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

            public MemberBean getMember() {
                return member;
            }

            public void setMember(MemberBean member) {
                this.member = member;
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public static class MemberBean {

                private String crmNumber;
                private String statusValue;
                private String srOperatorName;
                private Object office;
                private String creditLevelValue;
                private String sapNumber;
                private String arOperatorName;
                private FrOperatorBean frOperator;
                private String memberLevelValue;
                private String employeeSizeValue;
                private double registeredCapital;
                private String simpleSpell;
                private String statusKey;
                private SrOperatorBean srOperator;
                private long id;
                private String companyTypeValue;
                private String orgName;
                private ArOperatorBean arOperator;
                private String lastModifiedDate;
                private Object avatarUrl;
                private String lastModifiedBy;
                private Object riskLevelKey;
                private int creditModifyDate;
                private String businessScope;
                private int sort;
                private String abbreviation;
                private String customerDemand;
                private String createdDate;
                private String createdBy;
                private Object dealer;
                private String cooperationTypeValue;
                private String provinceName;
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

                public FrOperatorBean getFrOperator() {
                    return frOperator;
                }

                public void setFrOperator(FrOperatorBean frOperator) {
                    this.frOperator = frOperator;
                }

                public String getMemberLevelValue() {
                    return memberLevelValue;
                }

                public void setMemberLevelValue(String memberLevelValue) {
                    this.memberLevelValue = memberLevelValue;
                }

                public String getEmployeeSizeValue() {
                    return employeeSizeValue;
                }

                public void setEmployeeSizeValue(String employeeSizeValue) {
                    this.employeeSizeValue = employeeSizeValue;
                }

                public double getRegisteredCapital() {
                    return registeredCapital;
                }

                public void setRegisteredCapital(double registeredCapital) {
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

                public SrOperatorBean getSrOperator() {
                    return srOperator;
                }

                public void setSrOperator(SrOperatorBean srOperator) {
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

                public ArOperatorBean getArOperator() {
                    return arOperator;
                }

                public void setArOperator(ArOperatorBean arOperator) {
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

                public int getCreditModifyDate() {
                    return creditModifyDate;
                }

                public void setCreditModifyDate(int creditModifyDate) {
                    this.creditModifyDate = creditModifyDate;
                }

                public String getBusinessScope() {
                    return businessScope;
                }

                public void setBusinessScope(String businessScope) {
                    this.businessScope = businessScope;
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

                public String getCooperationTypeValue() {
                    return cooperationTypeValue;
                }

                public void setCooperationTypeValue(String cooperationTypeValue) {
                    this.cooperationTypeValue = cooperationTypeValue;
                }

                public String getProvinceName() {
                    return provinceName;
                }

                public void setProvinceName(String provinceName) {
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

                public static class FrOperatorBean {
                    /**
                     * departmentName : null
                     * lastModifiedDate : 2019-01-27 19:28:06
                     * lastModifiedBy : 胡志豪
                     * sort : 10
                     * title : 资深工程师<z>
                     * createdDate : 2019-01-26 22:05:35
                     * createdBy : 潘梦洋
                     * name : 刘博
                     * id : 412
                     * department : {"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-26 22:05:43","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"客服部（Z）","oaDepartmentId":"440","id":92,"sort":200}
                     * oaId : 683
                     * oaCode : 1011138
                     * username : 1011138
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
                         * desp : null
                         * createdDate : 2018-12-12 00:00:00
                         * lastModifiedDate : 2019-01-26 22:05:43
                         * createdBy : 13901565517
                         * lastModifiedBy : 潘梦洋
                         * name : 客服部（Z）
                         * oaDepartmentId : 440
                         * id : 92
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

                public static class SrOperatorBean {
                    /**
                     * departmentName : null
                     * lastModifiedDate : 2019-01-26 15:20:20
                     * lastModifiedBy : 胡志豪
                     * sort : 10
                     * title : 商务经理（Z）
                     * createdDate : 2019-01-10 13:51:10
                     * createdBy : 徐超
                     * name : 刘少岗
                     * id : 141
                     * department : {"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200}
                     * oaId : 1844
                     * oaCode : 1018213
                     * username : 1018213
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
                    private DepartmentBeanX department;
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

                    public DepartmentBeanX getDepartment() {
                        return department;
                    }

                    public void setDepartment(DepartmentBeanX department) {
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

                    public static class DepartmentBeanX {
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

                public static class ArOperatorBean {

                    private Object departmentName;
                    private String lastModifiedDate;
                    private String lastModifiedBy;
                    private int sort;
                    private String title;
                    private String createdDate;
                    private String createdBy;
                    private String name;
                    private long id;
                    private DepartmentBeanXX department;
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

                    public DepartmentBeanXX getDepartment() {
                        return department;
                    }

                    public void setDepartment(DepartmentBeanXX department) {
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

                    public static class DepartmentBeanXX {
                        /**
                         * desp : null
                         * createdDate : 2018-12-12 00:00:00
                         * lastModifiedDate : 2019-01-26 22:05:42
                         * createdBy : 13901565517
                         * lastModifiedBy : 潘梦洋
                         * name : 国内销售部（Z）
                         * oaDepartmentId : 411
                         * id : 75
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
        }

        public static class MemberBeanX {

            private String crmNumber;
            private String statusValue;
            private String srOperatorName;
            private Object office;
            private String creditLevelValue;
            private String sapNumber;
            private String arOperatorName;
            private FrOperatorBeanX frOperator;
            private String memberLevelValue;
            private String employeeSizeValue;
            private double registeredCapital;
            private String simpleSpell;
            private String statusKey;
            private SrOperatorBeanX srOperator;
            private long id;
            private String companyTypeValue;
            private String orgName;
            private ArOperatorBeanX arOperator;
            private String lastModifiedDate;
            private Object avatarUrl;
            private String lastModifiedBy;
            private Object riskLevelKey;
            private int creditModifyDate;
            private String businessScope;
            private int sort;
            private String abbreviation;
            private String customerDemand;
            private String createdDate;
            private String createdBy;
            private Object dealer;
            private String cooperationTypeValue;
            private String provinceName;
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

            public String getEmployeeSizeValue() {
                return employeeSizeValue;
            }

            public void setEmployeeSizeValue(String employeeSizeValue) {
                this.employeeSizeValue = employeeSizeValue;
            }

            public double getRegisteredCapital() {
                return registeredCapital;
            }

            public void setRegisteredCapital(double registeredCapital) {
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

            public SrOperatorBeanX getSrOperator() {
                return srOperator;
            }

            public void setSrOperator(SrOperatorBeanX srOperator) {
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

            public int getCreditModifyDate() {
                return creditModifyDate;
            }

            public void setCreditModifyDate(int creditModifyDate) {
                this.creditModifyDate = creditModifyDate;
            }

            public String getBusinessScope() {
                return businessScope;
            }

            public void setBusinessScope(String businessScope) {
                this.businessScope = businessScope;
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

            public String getCooperationTypeValue() {
                return cooperationTypeValue;
            }

            public void setCooperationTypeValue(String cooperationTypeValue) {
                this.cooperationTypeValue = cooperationTypeValue;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
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

            public static class FrOperatorBeanX {
                /**
                 * departmentName : null
                 * lastModifiedDate : 2019-01-27 19:28:06
                 * lastModifiedBy : 胡志豪
                 * sort : 10
                 * title : 资深工程师<z>
                 * createdDate : 2019-01-26 22:05:35
                 * createdBy : 潘梦洋
                 * name : 刘博
                 * id : 412
                 * department : {"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-26 22:05:43","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"客服部（Z）","oaDepartmentId":"440","id":92,"sort":200}
                 * oaId : 683
                 * oaCode : 1011138
                 * username : 1011138
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

                public static class DepartmentBeanXXX {
                    /**
                     * desp : null
                     * createdDate : 2018-12-12 00:00:00
                     * lastModifiedDate : 2019-01-26 22:05:43
                     * createdBy : 13901565517
                     * lastModifiedBy : 潘梦洋
                     * name : 客服部（Z）
                     * oaDepartmentId : 440
                     * id : 92
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

            public static class SrOperatorBeanX {
                /**
                 * departmentName : null
                 * lastModifiedDate : 2019-01-26 15:20:20
                 * lastModifiedBy : 胡志豪
                 * sort : 10
                 * title : 商务经理（Z）
                 * createdDate : 2019-01-10 13:51:10
                 * createdBy : 徐超
                 * name : 刘少岗
                 * id : 141
                 * department : {"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200}
                 * oaId : 1844
                 * oaCode : 1018213
                 * username : 1018213
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

            public static class ArOperatorBeanX {
                /**
                 * departmentName : null
                 * lastModifiedDate : 2019-01-27 19:41:03
                 * lastModifiedBy : 徐超
                 * sort : 10
                 * title : 销售经理（Z）
                 * createdDate : 2019-01-10 12:05:26
                 * createdBy : 费婷
                 * name : 徐超
                 * id : 139
                 * department : {"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-26 22:05:42","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"国内销售部（Z）","oaDepartmentId":"411","id":75,"sort":200}
                 * oaId : 651
                 * oaCode : 1004613
                 * username : 1004613
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

                public static class DepartmentBeanXXXXX {
                    /**
                     * desp : null
                     * createdDate : 2018-12-12 00:00:00
                     * lastModifiedDate : 2019-01-26 22:05:42
                     * createdBy : 13901565517
                     * lastModifiedBy : 潘梦洋
                     * name : 国内销售部（Z）
                     * oaDepartmentId : 411
                     * id : 75
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
    }
}
