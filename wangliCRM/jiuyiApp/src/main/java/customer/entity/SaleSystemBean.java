package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:SaleSystemBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/8 11:17
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/8 1.00 初始版本
 * ****************************************************************
 */
public class SaleSystemBean {

    /**
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 1
     * size : 5
     * totalElements : 1
     * totalPages : 1
     * sort : [{"ascending":true,"descending":false,"direction":"ASC","ignoreCase":false,"nullHandling":"NATIVE","property":"id"}]
     * content : [{"id":6,"sort":10,"createdDate":1546912610683,"lastModifiedDate":1546912610683,"createdBy":"1013821","lastModifiedBy":"1013821","content":"地方看看的份","fileName":"饭都好好的放","infoDate":"2019-01-08","fileTypeValue":"生产","fileTypeKey":"product","member":{"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546079225376,"createdBy":"system","lastModifiedBy":"system","cooperationTypeValue":"供应商","simpleSpell":"dgsgr","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","sapNumber":"200567","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配"},"operator":{"id":114,"sort":1,"createdDate":1545893236513,"lastModifiedDate":1546516423942,"createdBy":"15167156690","lastModifiedBy":"1013821","name":"王富善","username":"8888","department":{"id":45,"sort":100,"createdDate":1545827816572,"lastModifiedDate":1545893239297,"createdBy":"15167156690","lastModifiedBy":"15167156690","name":"总经办","desp":""},"title":"总经理","oaCode":"1013821"},"businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂","summary":"谁都快递费"}]
     */

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
         * ascending : true
         * descending : false
         * direction : ASC
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
        /**
         * id : 6
         * sort : 10
         * createdDate : 1546912610683
         * lastModifiedDate : 1546912610683
         * createdBy : 1013821
         * lastModifiedBy : 1013821
         * content : 地方看看的份
         * fileName : 饭都好好的放
         * infoDate : 2019-01-08
         * fileTypeValue : 生产
         * fileTypeKey : product
         * member : {"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546079225376,"createdBy":"system","lastModifiedBy":"system","cooperationTypeValue":"供应商","simpleSpell":"dgsgr","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","sapNumber":"200567","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配"}
         * operator : {"id":114,"sort":1,"createdDate":1545893236513,"lastModifiedDate":1546516423942,"createdBy":"15167156690","lastModifiedBy":"1013821","name":"王富善","username":"8888","department":{"id":45,"sort":100,"createdDate":1545827816572,"lastModifiedDate":1545893239297,"createdBy":"15167156690","lastModifiedBy":"15167156690","name":"总经办","desp":""},"title":"总经理","oaCode":"1013821"}
         * businessTypeKey : silicon_factory
         * businessTypeValue : 硅料厂
         * summary : 谁都快递费
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String content;
        private String fileName;
        private String infoDate;
        private String fileTypeValue;
        private String fileTypeKey;
        private MemberBeanID member;
        private String businessTypeKey;
        private String businessTypeValue;
        private String summary;

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

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getInfoDate() {
            return infoDate;
        }

        public void setInfoDate(String infoDate) {
            this.infoDate = infoDate;
        }

        public String getFileTypeValue() {
            return fileTypeValue;
        }

        public void setFileTypeValue(String fileTypeValue) {
            this.fileTypeValue = fileTypeValue;
        }

        public String getFileTypeKey() {
            return fileTypeKey;
        }

        public void setFileTypeKey(String fileTypeKey) {
            this.fileTypeKey = fileTypeKey;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
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

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}