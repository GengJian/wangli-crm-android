package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:ProductStandardBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/3 19:33
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/3 1.00 初始版本
 * ****************************************************************
 */
public class ProductStandardBean {

    /**
     * content : [{"createdBy":"18888996063","createdDate":"2019-01-02","lastModifiedBy":"18888996063","lastModifiedDate":"2019-01-02","id":15,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂","member":null,"summary":"测试动态表单","content":"非打开快递放","infoDate":"2019-01-02","attachmentList":null},{"createdBy":"18888996057","createdDate":"2019-01-02","lastModifiedBy":"18888996057","lastModifiedDate":"2019-01-02","id":16,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂","member":null,"summary":"yup","content":"July","infoDate":"2019-01-02","attachmentList":null},{"createdBy":"18888996057","createdDate":"2019-01-03","lastModifiedBy":"18888996057","lastModifiedDate":"2019-01-03","id":17,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂","member":null,"summary":"t","content":"t","infoDate":"2019-01-03","attachmentList":null},{"createdBy":"1022654","createdDate":"2019-01-03","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-03","id":19,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂","member":null,"summary":"yyy","content":"tyyy","infoDate":"2019-01-03","attachmentList":null}]
     * last : true
     * totalElements : 4
     * totalPages : 1
     * number : 0
     * size : 5
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * first : true
     * numberOfElements : 4
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int number;
    private int size;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;
    private List<SortBean> sort;

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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
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

        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
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

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getInfoDate() {
            return infoDate;
        }

        public void setInfoDate(String infoDate) {
            this.infoDate = infoDate;
        }

        public List<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }

        /**
         * createdBy : 18888996063
         * createdDate : 2019-01-02
         * lastModifiedBy : 18888996063
         * lastModifiedDate : 2019-01-02
         * id : 15
         * deleted : false
         * sort : 10
         * fromClientType : null
         * optionGroup : []
         * businessTypeKey : silicon_factory
         * businessTypeValue : 硅料厂
         * member : null
         * summary : 测试动态表单
         * content : 非打开快递放
         * infoDate : 2019-01-02
         * attachmentList : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private String fromClientType;
        private String businessTypeKey;
        private String businessTypeValue;
        private MemberBeanID member;
        private String summary;
        private String content;
        private String infoDate;
        private List<AttachmentBean> attachmentList;


    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : id
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
