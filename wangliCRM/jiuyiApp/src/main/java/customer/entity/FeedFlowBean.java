package customer.entity;

import java.util.List;

public class FeedFlowBean {

    /**
     * content : [{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":15,"deleted":false,"sort":10,"fromClientType":null,"bigCategory":"feed_risk_warning","childCategoryId":18,"childPrimaryKey":34,"memberId":10,"title":"裁决文书-feed","content":"裁决文书content-feed","fkType":"RISK_WARNING","iconDictNamePrefix":"risk_icon_two_","attachmentList":[{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":377,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":34,"qiniuKey":"qiniuKey","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey?e=1529035611&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:bGaVRW8tSE6c_-Y4kC3tIIL7WeY="},{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":378,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":34,"qiniuKey":"qiniuKey","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey?e=1529035611&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:bGaVRW8tSE6c_-Y4kC3tIIL7WeY="}],"iconUrl":"http://img.jiuyisoft.com/enforce_person_ios_gz.png","createdDateStr":"19:32"},{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":16,"deleted":false,"sort":10,"fromClientType":null,"bigCategory":"feed_risk_warning","childCategoryId":18,"childPrimaryKey":35,"memberId":10,"title":"裁决文书-feed","content":"裁决文书content-feed","fkType":"RISK_WARNING","iconDictNamePrefix":"risk_icon_two_","attachmentList":[{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":381,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":35,"qiniuKey":"qiniuKey1-feed-改改","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey1-feed-改改?e=1529035611&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:2aMquX7cAd7-ed0rO_IWlQ6NcCs="},{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":382,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":35,"qiniuKey":"qiniuKey2-改改","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey2-改改?e=1529035611&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:hVxj6lZ-X_Cw34aSwkZYVL7bF0A="}],"iconUrl":"http://img.jiuyisoft.com/enforce_person_ios_gz.png","createdDateStr":"19:32"},{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":17,"deleted":false,"sort":10,"fromClientType":null,"bigCategory":"feed_risk_warning","childCategoryId":18,"childPrimaryKey":35,"memberId":10,"title":"裁决文书-feed-改","content":"裁决文书content-改-feed","fkType":"RISK_WARNING","iconDictNamePrefix":"risk_icon_two_","attachmentList":[{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":381,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":35,"qiniuKey":"qiniuKey1-feed-改改","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey1-feed-改改?e=1529035611&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:2aMquX7cAd7-ed0rO_IWlQ6NcCs="},{"createdBy":"18405863019","createdDate":"2018-06-14","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-14","id":382,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":35,"qiniuKey":"qiniuKey2-改改","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey2-改改?e=1529035611&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:hVxj6lZ-X_Cw34aSwkZYVL7bF0A="}],"iconUrl":"http://img.jiuyisoft.com/enforce_person_ios_gz.png","createdDateStr":"19:32"}]
     * totalElements : 3
     * last : true
     * totalPages : 1
     * number : 0
     * size : 5
     * sort : [{"direction":"ASC","property":"createdDate","ignoreCase":false,"nullHandling":"NATIVE","descending":false,"ascending":true}]
     * first : true
     * numberOfElements : 3
     */

    private int totalElements;
    private boolean last;
    private int totalPages;
    private int number;
    private int size;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;
    private List<SortBean> sort;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
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

        public String getBigCategory() {
            return bigCategory;
        }

        public void setBigCategory(String bigCategory) {
            this.bigCategory = bigCategory;
        }

        public long getChildCategoryId() {
            return childCategoryId;
        }

        public void setChildCategoryId(long childCategoryId) {
            this.childCategoryId = childCategoryId;
        }

        public long getChildPrimaryKey() {
            return childPrimaryKey;
        }

        public void setChildPrimaryKey(long childPrimaryKey) {
            this.childPrimaryKey = childPrimaryKey;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
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

        public String getFkType() {
            return fkType;
        }

        public void setFkType(String fkType) {
            this.fkType = fkType;
        }

        public String getFkTypeValue() {
            return fkTypeValue;
        }

        public void setFkTypeValue(String fkTypeValue) {
            this.fkTypeValue = fkTypeValue;
        }

        public String getIconDictName() {
            return iconDictName;
        }

        public void setIconDictName(String iconDictName) {
            this.iconDictName = iconDictName;
        }

        public String getIconDictNameUrl() {
            return iconDictNameUrl;
        }

        public void setIconDictNameUrl(String iconDictNameUrl) {
            this.iconDictNameUrl = iconDictNameUrl;
        }

        public List<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }

        public List<AttachmentBean> getVdieoAttachmentList() {
            return vdieoAttachmentList;
        }

        public void setVdieoAttachmentList(List<AttachmentBean> vdieoAttachmentList) {
            this.vdieoAttachmentList = vdieoAttachmentList;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getCreatedDateStr() {
            return createdDateStr;
        }

        public void setCreatedDateStr(String createdDateStr) {
            this.createdDateStr = createdDateStr;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private String fromClientType;
        private String bigCategory;
        private long childCategoryId;
        private long childPrimaryKey;
        private MemberBeanID member;
        private String orgName;
        private String abbreviation;
        private String title;
        private String content;
        private String fkType;
        private String fkTypeValue;
        private String iconDictName;
        private String iconDictNameUrl;
        private List<AttachmentBean> attachmentList;
        private List<AttachmentBean> vdieoAttachmentList;
        private String iconUrl;
        private String createdDateStr;
        private String url;

        public boolean isDeleteAble() {
            return deleteAble;
        }

        public void setDeleteAble(boolean deleteAble) {
            this.deleteAble = deleteAble;
        }

        private boolean deleteAble;

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        private String operatorName;
        private String departmentName;



    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : createdDate
         * ignoreCase : false
         * nullHandling : NATIVE
         * descending : false
         * ascending : true
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean descending;
        private boolean ascending;

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

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }
    }
}
