package dynamic.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import customer.entity.AttachmentBean;
import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称:DyBusinessBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/12 20:40
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/12 1.00 初始版本
 * ****************************************************************
 */
public class DyBusinessBean {


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


        private long id;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String content;
        private PrincipalBean principal;
        private String transactionDate;
        private String importantValue;
        private String approvalStatusValue;
        private MemberBeanID regularCustomer;
        private String typeKey;
        private String title;
        private String statusKey;
        private String abbreviation;
        private ContractBean contract;
        private String operatorName;
        private String statusValue;
        private String principalName;
        private String feedback;
        private String submitDate;
        private double amount;
        private String businessNumber;
        private String approvalStatusKey;
        private String customerContact;
        private OperatorBean operator;
        private String typeValue;
        private String customerTel;
        private String customerJob;
        private DyClueBean.DyClueBeanID clue;
        private DyActivityBean.DyActivityBeanID activity;
        private String resourceValue;
        private String importantKey;
        private String resourceKey;
        private String closeDesp;
        private List<DyClueBean.ProductBigCategory> materialTypes;
        private List<CompetitorBehavior> competitorBehaviorset;
        private List<?> friendDealerSet;
        private List<?> businessJournalSet;
        private List<AttachmentBean> attachmentList;
        private List<?> quotedPrice;
        private List<OptionGroupBean> optionGroup;

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        private MemberBeanID member;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        public PrincipalBean getPrincipal() {
            return principal;
        }

        public void setPrincipal(PrincipalBean principal) {
            this.principal = principal;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }

        public String getImportantValue() {
            return importantValue;
        }

        public void setImportantValue(String importantValue) {
            this.importantValue = importantValue;
        }

        public String getApprovalStatusValue() {
            return approvalStatusValue;
        }

        public void setApprovalStatusValue(String approvalStatusValue) {
            this.approvalStatusValue = approvalStatusValue;
        }

        public MemberBeanID getRegularCustomer() {
            return regularCustomer;
        }

        public void setRegularCustomer(MemberBeanID regularCustomer) {
            this.regularCustomer = regularCustomer;
        }


        public String getTypeKey() {
            return typeKey;
        }

        public void setTypeKey(String typeKey) {
            this.typeKey = typeKey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatusKey() {
            return statusKey;
        }

        public void setStatusKey(String statusKey) {
            this.statusKey = statusKey;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public ContractBean getContract() {
            return contract;
        }

        public void setContract(ContractBean contract) {
            this.contract = contract;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getStatusValue() {
            return statusValue;
        }

        public void setStatusValue(String statusValue) {
            this.statusValue = statusValue;
        }

        public String getPrincipalName() {
            return principalName;
        }

        public void setPrincipalName(String principalName) {
            this.principalName = principalName;
        }

        public String getFeedback() {
            return feedback;
        }

        public void setFeedback(String feedback) {
            this.feedback = feedback;
        }

        public String getSubmitDate() {
            return submitDate;
        }

        public void setSubmitDate(String submitDate) {
            this.submitDate = submitDate;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getBusinessNumber() {
            return businessNumber;
        }

        public void setBusinessNumber(String businessNumber) {
            this.businessNumber = businessNumber;
        }

        public String getApprovalStatusKey() {
            return approvalStatusKey;
        }

        public void setApprovalStatusKey(String approvalStatusKey) {
            this.approvalStatusKey = approvalStatusKey;
        }

        public String getCustomerContact() {
            return customerContact;
        }

        public void setCustomerContact(String customerContact) {
            this.customerContact = customerContact;
        }

        public OperatorBean getOperator() {
            return operator;
        }

        public void setOperator(OperatorBean operator) {
            this.operator = operator;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }

        public String getCustomerTel() {
            return customerTel;
        }

        public void setCustomerTel(String customerTel) {
            this.customerTel = customerTel;
        }

        public String getCustomerJob() {
            return customerJob;
        }

        public void setCustomerJob(String customerJob) {
            this.customerJob = customerJob;
        }

        public DyClueBean.DyClueBeanID getClue() {
            return clue;
        }

        public void setClue(DyClueBean.DyClueBeanID clue) {
            this.clue = clue;
        }


        public DyActivityBean.DyActivityBeanID getActivity() {
            return activity;
        }

        public void setActivity(DyActivityBean.DyActivityBeanID activity) {
            this.activity = activity;
        }

        public String getResourceValue() {
            return resourceValue;
        }

        public void setResourceValue(String resourceValue) {
            this.resourceValue = resourceValue;
        }

        public String getImportantKey() {
            return importantKey;
        }

        public void setImportantKey(String importantKey) {
            this.importantKey = importantKey;
        }

        public String getResourceKey() {
            return resourceKey;
        }

        public void setResourceKey(String resourceKey) {
            this.resourceKey = resourceKey;
        }

        public String getCloseDesp() {
            return closeDesp;
        }

        public void setCloseDesp(String closeDesp) {
            this.closeDesp = closeDesp;
        }

        public List<DyClueBean.ProductBigCategory> getMaterialTypes() {
            return materialTypes;
        }

        public void setMaterialTypes(List<DyClueBean.ProductBigCategory> materialTypes) {
            this.materialTypes = materialTypes;
        }

        public List<CompetitorBehavior> getCompetitorBehaviorset() {
            return competitorBehaviorset;
        }

        public void setCompetitorBehaviorset(List<CompetitorBehavior> competitorBehaviorset) {
            this.competitorBehaviorset = competitorBehaviorset;
        }

        public List<?> getFriendDealerSet() {
            return friendDealerSet;
        }

        public void setFriendDealerSet(List<?> friendDealerSet) {
            this.friendDealerSet = friendDealerSet;
        }

        public List<?> getBusinessJournalSet() {
            return businessJournalSet;
        }

        public void setBusinessJournalSet(List<?> businessJournalSet) {
            this.businessJournalSet = businessJournalSet;
        }

        public List<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }

        public List<?> getQuotedPrice() {
            return quotedPrice;
        }

        public void setQuotedPrice(List<?> quotedPrice) {
            this.quotedPrice = quotedPrice;
        }

        public List<OptionGroupBean> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<OptionGroupBean> optionGroup) {
            this.optionGroup = optionGroup;
        }

        public static class PrincipalBean {
            public static class DepartmentBean {
            }
        }


        public static class ContractBean {
        }

        public static class OperatorBean {
            /**
             * id : 1
             * sort : 10
             * createdDate : 2018-11-29 21:15:42
             * lastModifiedDate : 2019-01-12 15:53:11
             * createdBy : 15167156690
             * lastModifiedBy : 潘梦洋
             * name : 潘梦洋
             * username : 1022654
             * departmentName :
             * title : 市场专员(z)
             * department : {"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-12 18:14:09","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"市场部（Z）","oaDepartmentId":"415","desp":""}
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
            private String departmentName;
            private String title;
            private DyInteligenceBean.ContentBean.OperatorBean.DepartmentBean department;
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

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public DyInteligenceBean.ContentBean.OperatorBean.DepartmentBean getDepartment() {
                return department;
            }

            public void setDepartment(DyInteligenceBean.ContentBean.OperatorBean.DepartmentBean department) {
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
                 * lastModifiedDate : 2019-01-12 18:14:09
                 * createdBy : 13901565517
                 * lastModifiedBy : 潘梦洋
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


        public static class ClueBean {
        }




        public static class OptionGroupBean {
            /**
             * name : assignPrincipal
             * content : 指派商机
             */

            private String name;
            private String content;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class CompetitorBehavior implements Parcelable  {
            protected CompetitorBehavior(Parcel in) {
                if (in.readByte() == 0) {
                    id = null;
                } else {
                    id = in.readLong();
                }
                businessChance = in.readParcelable(DyBusinessBeanID.class.getClassLoader());
                member = in.readParcelable(MemberBeanID.class.getClassLoader());
                abbreviation = in.readString();
                content = in.readString();
                principalName = in.readString();
                principalTel = in.readString();
                principalJob = in.readString();
                principalDepartment = in.readString();
            }

            public static final Creator<CompetitorBehavior> CREATOR = new Creator<CompetitorBehavior>() {
                @Override
                public CompetitorBehavior createFromParcel(Parcel in) {
                    return new CompetitorBehavior(in);
                }

                @Override
                public CompetitorBehavior[] newArray(int size) {
                    return new CompetitorBehavior[size];
                }
            };

            public void setId(Long id) {
                this.id = id;
            }

            private Long id=null;






            /**
             * 商机
             */
            private DyBusinessBeanID businessChance;
            /**
             * 友商
             */
            private MemberBeanID member;

            /**
             * 客户简称
             */
            private String abbreviation;

            /**
             * 备注
             */
            private String content;

            /**
             * 负责人姓名
             */
            private String principalName;

            /**
             * 负责人电话
             */
            private String principalTel;

//            public int getViewType() {
//                return viewType;
//            }
//
//            public void setViewType(int viewType) {
//                this.viewType = viewType;
//            }
//
//            private int viewType;

            public CompetitorBehavior() {
            }

            public DyBusinessBeanID getBusinessChance() {
                return businessChance;
            }

            public void setBusinessChance(DyBusinessBeanID businessChance) {
                this.businessChance = businessChance;
            }

            public MemberBeanID getMember() {
                return member;
            }

            public void setMember(MemberBeanID member) {
                this.member = member;
            }

            public String getAbbreviation() {
                return abbreviation;
            }

            public void setAbbreviation(String abbreviation) {
                this.abbreviation = abbreviation;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPrincipalName() {
                return principalName;
            }

            public void setPrincipalName(String principalName) {
                this.principalName = principalName;
            }

            public String getPrincipalTel() {
                return principalTel;
            }

            public void setPrincipalTel(String principalTel) {
                this.principalTel = principalTel;
            }

            public String getPrincipalJob() {
                return principalJob;
            }

            public void setPrincipalJob(String principalJob) {
                this.principalJob = principalJob;
            }

            public String getPrincipalDepartment() {
                return principalDepartment;
            }

            public void setPrincipalDepartment(String principalDepartment) {
                this.principalDepartment = principalDepartment;
            }

            /**
             * 职位
             */
            private String principalJob;

            /**
             * 部门
             */
            private String principalDepartment;


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                if (id == null) {
                    dest.writeByte((byte) 0);
                } else {
                    dest.writeByte((byte) 1);
                    dest.writeLong(id);
                }
                dest.writeParcelable(businessChance, flags);
                dest.writeParcelable(member, flags);
                dest.writeString(abbreviation);
                dest.writeString(content);
                dest.writeString(principalName);
                dest.writeString(principalTel);
                dest.writeString(principalJob);
                dest.writeString(principalDepartment);
            }
        }

        public static class DyBusinessBeanID implements Parcelable{
            private long id;

            public DyBusinessBeanID() {
            }

            protected DyBusinessBeanID(Parcel in) {
                id = in.readLong();
                content = in.readString();
                title = in.readString();
            }

            public static final Creator<DyBusinessBeanID> CREATOR = new Creator<DyBusinessBeanID>() {
                @Override
                public DyBusinessBeanID createFromParcel(Parcel in) {
                    return new DyBusinessBeanID(in);
                }

                @Override
                public DyBusinessBeanID[] newArray(int size) {
                    return new DyBusinessBeanID[size];
                }
            };

            public long getId() {

                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            private String content;
            private String title;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeLong(id);
                dest.writeString(content);
                dest.writeString(title);
            }
        }


    }
}
