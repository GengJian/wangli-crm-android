package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:AccessStandardBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/9 21:43
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/9 1.00 初始版本
 * ****************************************************************
 */
public class AccessStandardBean {

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
         * property : lastModifiedDate
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
         * id : 7
         * sort : 10
         * createdDate : 2019-01-09 21:41:41
         * lastModifiedDate : 2019-01-09 21:41:41
         * createdBy : 刘启明
         * lastModifiedBy : 刘启明
         * name : a
         * content : d
         * attachmentList : []
         * member : {"id":983,"sort":10,"createdDate":"2019-01-08 17:40:18","lastModifiedDate":"2019-01-08 22:23:51","createdBy":"系统自动创建","lastModifiedBy":"刘启明","office":{},"dealer":{"id":896,"sort":1,"createdDate":"2018-12-29 18:27:05","lastModifiedDate":"2019-01-08 14:28:50","createdBy":"系统自动创建","lastModifiedBy":"蒋鹏祥","office":{},"dealer":{},"simpleSpell":"dgsgr","riskLevelKey":"","registeredCapital":0,"arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配","creditLevelValue":"","riskLevelValue":"","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","avatarUrl":"","statusValue":"潜在","crmNumber":"200567","cooperationTypeValue":"供应商","memberLevelValue":"","employeeSizeValue":"","companyTypeValue":"","customerDemand":"","creditModifyDate":0,"sapNumber":"200567","provinceName":"","statusKey":"potential"},"simpleSpell":"gdax","riskLevelKey":"","registeredCapital":156512,"arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配","creditLevelValue":"AAA","riskLevelValue":"","abbreviation":"广东爱旭","orgName":"广东爱旭科技股份有限公司","avatarUrl":"","statusValue":"","crmNumber":"1001","cooperationTypeValue":"直销","memberLevelValue":"重要","employeeSizeValue":"5000GW以上","companyTypeValue":"股份有限公司","customerDemand":"546","creditModifyDate":0,"sapNumber":"1001","provinceName":"山西省","statusKey":""}
         * infoDate : 2019-01-10
         * summary : b
         * businessTypeKey : silicon_factory
         * businessTypeValue : 硅料厂
         * operator : {"id":1,"sort":10,"createdDate":"2018-11-29 21:15:42","lastModifiedDate":"2019-01-09 21:28:19","createdBy":"15167156690","lastModifiedBy":"刘启明","name":"刘启明","username":"","oaId":"","oaCode":"1022654","departmentName":"","department":{"id":1,"sort":100,"createdDate":"2018-11-29 21:14:56","lastModifiedDate":"2018-12-26 21:46:18","createdBy":"15167156690","lastModifiedBy":"15167156690","name":"营销部-禁用","oaDepartmentId":"","desp":""},"title":"营销分析员"}
         * typeKey : product
         * typeValue : 生产
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String name;
        private String content;
        private MemberBeanID member;
        private String infoDate;
        private String summary;
        private String businessTypeKey;
        private String businessTypeValue;
        private String typeKey;
        private String typeValue;
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

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getInfoDate() {
            return infoDate;
        }

        public void setInfoDate(String infoDate) {
            this.infoDate = infoDate;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getBusinessTypeKey() {
            return businessTypeKey;
        }

        public void setBusinessTypeKey(String businessTypeKey) {
            this.businessTypeKey = businessTypeKey;
        }

        public String getBusinessTypeValue() {
            return businessTypeValue;
        }

        public void setBusinessTypeValue(String businessTypeValue) {
            this.businessTypeValue = businessTypeValue;
        }



        public String getTypeKey() {
            return typeKey;
        }

        public void setTypeKey(String typeKey) {
            this.typeKey = typeKey;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }

        public List<?> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<?> attachmentList) {
            this.attachmentList = attachmentList;
        }


    }
}
