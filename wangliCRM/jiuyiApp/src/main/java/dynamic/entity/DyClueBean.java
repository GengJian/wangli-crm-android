package dynamic.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import customer.entity.AttachmentBean;
import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称:DyClueBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/12 20:44
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/12 1.00 初始版本
 * ****************************************************************
 */
public class DyClueBean {

    /**
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 1
     * size : 10
     * totalElements : 1
     * totalPages : 1
     * sort : [{"ascending":false,"descending":true,"direction":"DESC","ignoreCase":false,"nullHandling":"NATIVE","property":"id"}]
     * content : [{"id":132,"sort":10,"createdDate":"2019-01-14 19:59:12","lastModifiedDate":"2019-01-14 19:59:12","createdBy":"潘梦洋","lastModifiedBy":"潘梦洋","optionGroup":[{"name":"assignPrincipal","content":"指派负责人"},{"name":"disable","content":"无效"}],"clueNumber":"AXXS20190114009","memberContactor":"陈芪华","memberContactorPhone":"11111111111","marketActivityTitle":"爱旭之夜","intelligenceContent":"","coupleBack":"","oldMember":{},"oldMemberAbbreviation":{},"importantValue":"一般","resourceValue":"市场活动","importantKey":"general","resourceKey":"market_activity","closeDesp":"","member":{"id":2831,"sort":10,"createdDate":"2019-01-13 14:15:49","lastModifiedDate":"2019-01-14 04:55:43","createdBy":"系统自动创建","lastModifiedBy":"周红","sapNumber":"200435","cooperationTypeValue":"直销","memberLevelValue":"普通","provinceName":"","employeeSizeValue":"","companyTypeValue":"","customerDemand":"","creditModifyDate":0,"statusKey":"formal","crmNumber":"200435","abbreviation":"阳光能源(香港)有限","statusValue":"正式","orgName":"阳光能源(香港)有限公司","avatarUrl":"","arOperatorName":"未分配","registeredCapital":0,"office":{},"riskLevelKey":"","frOperatorName":"未分配","srOperatorName":"未分配","creditLevelValue":"A","dealer":{},"riskLevelValue":"","simpleSpell":"ygny(xg)yx"},"title":"测试待办","statusKey":"in_verification","abbreviation":"","statusValue":"验证中","attachments":[],"principalName":"","statusDesp":"销售管理部经理审批","approvalNodeIdentifier":"SALES_MANAGER","submitDate":"2019-01-15","materialTypes":[{"id":1,"sort":10,"createdDate":"2019-01-14 19:59:12","lastModifiedDate":"2019-01-14 19:59:12","createdBy":"潘梦洋","lastModifiedBy":"潘梦洋","productBigCategoryKey":"double_perc","productBigCategoryValue":"双面PERC单晶"}],"intelligenceItem":{},"submitter":{"id":1,"sort":10,"createdDate":"2018-11-29 21:15:42","lastModifiedDate":"2019-01-15 01:17:27","createdBy":"15167156690","lastModifiedBy":"潘梦洋","department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","desp":"","oaDepartmentId":"415","name":"市场部（Z）"},"title":"市场专员(z)","departmentName":"","oaCode":"1022654","oaId":"2381","username":"1022654","name":"潘梦洋"},"submitterName":"潘梦洋","approvalStatus":"approvalInCrm","approvalStatusDesp":"审批中","marketActivity":{"id":50,"sort":10,"createdDate":"2019-01-13 22:12:58","lastModifiedDate":"2019-01-13 22:14:42","createdBy":"马禄萍","lastModifiedBy":"马禄萍","operator":{"id":141,"sort":10,"createdDate":"2019-01-10 13:51:10","lastModifiedDate":"2019-01-14 06:42:09","createdBy":"徐超","lastModifiedBy":"刘少岗","department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","desp":"","oaDepartmentId":"415","name":"市场部（Z）"},"title":"商务经理（Z）","departmentName":"","oaCode":"1018213","oaId":"1844","username":"1018213","name":"刘少岗"},"department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","desp":"","oaDepartmentId":"415","name":"市场部（Z）"},"attachments":[],"createOperator":{"id":5,"sort":10,"createdDate":"2018-11-29 21:15:42","lastModifiedDate":"2019-01-13 11:15:08","createdBy":"15167156698","lastModifiedBy":"徐超","department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","desp":"","oaDepartmentId":"415","name":"市场部（Z）"},"title":"市场专员(z)","departmentName":"","oaCode":"1023443","oaId":"2330","username":"1023443","name":"马禄萍"}},"principal":{},"content":"测试待帮"}]
     */

    private boolean first;
    private boolean last;
    private int number;

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

    private int numberOfElements;
    private int size;
    private int totalElements;
    private int totalPages;
    private List<SortBean> sort;
    private List<ContentBean> content;

    public static class ContentBean {


        private long id;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String content;
        private String title;
        private String clueNumber;
        private String resourceValue;
        private String resourceKey;
        private DyActivityBean.DyActivityBeanID marketActivity;
        private DyInteligenceBean.IntelligenceItemBeanID intelligenceItem;
        private MemberBeanID oldMember;
        private MemberBeanID member;
        private String memberContactor;
        private String memberContactorPhone;
        private String statusValue;
        private String statusKey;

        public DyActivityBean.ContentBean.OperatorBean getOperator() {
            return operator;
        }

        public void setOperator(DyActivityBean.ContentBean.OperatorBean operator) {
            this.operator = operator;
        }

        private DyActivityBean.ContentBean.OperatorBean operator;

        public DyInteligenceBean.ContentBean.OperatorBean getSubmitter() {
            return submitter;
        }

        public void setSubmitter(DyInteligenceBean.ContentBean.OperatorBean submitter) {
            this.submitter = submitter;
        }

        public String getSubmitterName() {
            return submitterName;
        }

        public void setSubmitterName(String submitterName) {
            this.submitterName = submitterName;
        }

        public DyInteligenceBean.ContentBean.OperatorBean getPrincipal() {
            return principal;
        }

        public void setPrincipal(DyInteligenceBean.ContentBean.OperatorBean principal) {
            this.principal = principal;
        }

        public String getPrincipalName() {
            return principalName;
        }

        public void setPrincipalName(String principalName) {
            this.principalName = principalName;
        }

        /**
         * 提交人
         */
        private DyInteligenceBean.ContentBean.OperatorBean submitter;
        /**
         * 提交人姓名
         */
        private String submitterName;
        /**
         * 负责人
         */
        private DyInteligenceBean.ContentBean.OperatorBean principal;
        /**
         * 负责人姓名
         */
        private String principalName;

        public String getApprovalStatusDesp() {
            return ApprovalStatusDesp;
        }

        public void setApprovalStatusDesp(String approvalStatusDesp) {
            ApprovalStatusDesp = approvalStatusDesp;
        }

        public String getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(String approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public String getStatusDesp() {
            return statusDesp;
        }

        public void setStatusDesp(String statusDesp) {
            this.statusDesp = statusDesp;
        }

        public String getApprovalNodeIdentifier() {
            return approvalNodeIdentifier;
        }

        public void setApprovalNodeIdentifier(String approvalNodeIdentifier) {
            this.approvalNodeIdentifier = approvalNodeIdentifier;
        }

        /**
         * 审批状态
         */
        private String ApprovalStatusDesp;
        /**
         * 审批状态
         */
        private String approvalStatus;
        /**
         * 审批状态描述
         */
        private String statusDesp;
        /**
         * 审批节点标识
         */
        private String approvalNodeIdentifier;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getClueNumber() {
            return clueNumber;
        }

        public void setClueNumber(String clueNumber) {
            this.clueNumber = clueNumber;
        }

        public String getResourceValue() {
            return resourceValue;
        }

        public void setResourceValue(String resourceValue) {
            this.resourceValue = resourceValue;
        }

        public String getResourceKey() {
            return resourceKey;
        }

        public void setResourceKey(String resourceKey) {
            this.resourceKey = resourceKey;
        }

        public DyActivityBean.DyActivityBeanID getMarketActivity() {
            return marketActivity;
        }

        public void setMarketActivity(DyActivityBean.DyActivityBeanID marketActivity) {
            this.marketActivity = marketActivity;
        }

        public DyInteligenceBean.IntelligenceItemBeanID getIntelligenceItem() {
            return intelligenceItem;
        }

        public void setIntelligenceItem(DyInteligenceBean.IntelligenceItemBeanID intelligenceItem) {
            this.intelligenceItem = intelligenceItem;
        }

        public MemberBeanID getOldMember() {
            return oldMember;
        }

        public void setOldMember(MemberBeanID oldMember) {
            this.oldMember = oldMember;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getMemberContactor() {
            return memberContactor;
        }

        public void setMemberContactor(String memberContactor) {
            this.memberContactor = memberContactor;
        }

        public String getMemberContactorPhone() {
            return memberContactorPhone;
        }

        public void setMemberContactorPhone(String memberContactorPhone) {
            this.memberContactorPhone = memberContactorPhone;
        }

        public String getStatusValue() {
            return statusValue;
        }

        public void setStatusValue(String statusValue) {
            this.statusValue = statusValue;
        }

        public String getStatusKey() {
            return statusKey;
        }

        public void setStatusKey(String statusKey) {
            this.statusKey = statusKey;
        }

        public String getSubmitDate() {
            return submitDate;
        }

        public void setSubmitDate(String submitDate) {
            this.submitDate = submitDate;
        }

        public String getImportantValue() {
            return importantValue;
        }

        public void setImportantValue(String importantValue) {
            this.importantValue = importantValue;
        }

        public String getImportantKey() {
            return importantKey;
        }

        public void setImportantKey(String importantKey) {
            this.importantKey = importantKey;
        }

        public List<ProductBigCategory> getMaterialTypes() {
            return materialTypes;
        }

        public void setMaterialTypes(List<ProductBigCategory> materialTypes) {
            this.materialTypes = materialTypes;
        }

        public List<AttachmentBean> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<AttachmentBean> attachments) {
            this.attachments = attachments;
        }

        private String submitDate;
        private String importantValue;
        private String importantKey;
        private List<ProductBigCategory> materialTypes;
        private List<AttachmentBean> attachments;




    }
    public static class ProductBigCategory{
        public ProductBigCategory() {
        }

        public long getId() {

            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ProductBigCategory(long id, String name) {
            this.id = id;
            this.name = name;

        }

        private long id;
        private String name;

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
    public static class DyClueBeanID implements Parcelable{
        public DyClueBeanID() {
        }

        protected DyClueBeanID(Parcel in) {
            id = in.readLong();
            title = in.readString();
        }

        public static final Creator<DyClueBeanID> CREATOR = new Creator<DyClueBeanID>() {
            @Override
            public DyClueBeanID createFromParcel(Parcel in) {
                return new DyClueBeanID(in);
            }

            @Override
            public DyClueBeanID[] newArray(int size) {
                return new DyClueBeanID[size];
            }
        };

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private long id;
        private String title="";

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(title);
        }
    }
}
