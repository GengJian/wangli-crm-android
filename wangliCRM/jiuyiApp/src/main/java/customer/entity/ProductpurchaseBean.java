package customer.entity;

import com.jiuyi.model.DictResultBean;

import java.util.List;

public class ProductpurchaseBean {


    /**
     * content : [{"createdBy":"admin","createdDate":"2018-04-26 13:42:57","lastModifiedBy":"admin","lastModifiedDate":"2018-04-26 13:42:57","id":4,"deleted":false,"sort":10,"fromClientType":null,"member":null,"type":{"createdBy":"admin","createdDate":"2018-04-13 09:48:48","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:48:48","id":7,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"PLAN","value":"计划小助手","modifiable":null,"builtIn":true,"remark":"计划小助手"},"typeValue":null,"title":"招标4","publishDate":"2018-04-26T08:00:00.000+0800","content":"祖传手艺","attachments":null},{"createdBy":"admin","createdDate":"2018-04-26 13:49:21","lastModifiedBy":"admin","lastModifiedDate":"2018-04-26 13:49:21","id":5,"deleted":false,"sort":10,"fromClientType":null,"member":null,"type":{"createdBy":"admin","createdDate":"2018-04-09 18:34:16","lastModifiedBy":"admin","lastModifiedDate":"2018-04-09 18:49:14","id":4,"deleted":false,"sort":10,"fromClientType":null,"name":"name_1","desp":null,"key":"key_4","value":"value_fa1","modifiable":null,"builtIn":null,"remark":null},"typeValue":null,"title":"祖传招标","publishDate":"2018-04-26T08:00:00.000+0800","content":"一分钱一分货","attachments":null}]
     * last : true
     * totalElements : 4
     * totalPages : 2
     * number : 1
     * size : 2
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * first : false
     * numberOfElements : 2
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
        /**
         * createdBy : admin
         * createdDate : 2018-04-26 13:42:57
         * lastModifiedBy : admin
         * lastModifiedDate : 2018-04-26 13:42:57
         * id : 4
         * deleted : false
         * sort : 10
         * fromClientType : null
         * member : null
         * type : {"createdBy":"admin","createdDate":"2018-04-13 09:48:48","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:48:48","id":7,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"PLAN","value":"计划小助手","modifiable":null,"builtIn":true,"remark":"计划小助手"}
         * typeValue : null
         * title : 招标4
         * publishDate : 2018-04-26T08:00:00.000+0800
         * content : 祖传手艺
         * attachments : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;

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

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
        }

        public DictResultBean.ContentBean getType() {
            return type;
        }

        public void setType(DictResultBean.ContentBean type) {
            this.type = type;
        }

        public Object getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(Object typeValue) {
            this.typeValue = typeValue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public List<AttachmentBean> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<AttachmentBean> attachments) {
            this.attachments = attachments;
        }

        private String fromClientType;
        private DictResultBean.ContentBean type;
        private Object typeValue;
        private String title;

        public String getInfoDate() {
            return infoDate;
        }

        public void setInfoDate(String infoDate) {
            this.infoDate = infoDate;
        }

        private String infoDate;
        private String content;
        private MemberBeanID member;
        private List<AttachmentBean> attachments;


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
