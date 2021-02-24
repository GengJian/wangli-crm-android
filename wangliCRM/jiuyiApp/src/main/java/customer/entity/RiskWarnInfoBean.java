package customer.entity;

import com.jiuyi.model.DictResultBean;

import java.util.List;

public class RiskWarnInfoBean {


    /**
     * content : [{"createdBy":"18405863019","createdDate":"2018-06-04 19:39:04","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-04 19:39:04","id":46,"deleted":false,"sort":10,"fromClientType":null,"type":"ruling_clerical","typeValue":"裁决文书","title":"裁决文书","content":"裁决文书content","riskManageComments":null,"riskManageHandleDate":null,"member":null,"operator":null,"iconUrl":"","attachmentList":[{"createdBy":"18405863019","createdDate":"2018-06-04 19:39:04","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-04 19:39:04","id":90,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":46,"qiniuKey":"qiniuKey1","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey1?e=1528116059&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:NRl8Pq4WbNfbvODFCoPpqBQvYv4="},{"createdBy":"18405863019","createdDate":"2018-06-04 19:39:04","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-04 19:39:04","id":91,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":46,"qiniuKey":"qiniuKey2","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey2?e=1528116059&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:D9yM_o5yIyuBARuMOXL5z5M3jR0="}]},{"createdBy":"18405863019","createdDate":"2018-06-04 19:39:41","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-04 19:39:41","id":48,"deleted":false,"sort":10,"fromClientType":null,"type":"ruling_clerical","typeValue":"裁决文书","title":"裁决文书","content":"裁决文书content","riskManageComments":null,"riskManageHandleDate":null,"member":null,"operator":null,"iconUrl":"","attachmentList":[{"createdBy":"18405863019","createdDate":"2018-06-04 19:39:41","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-04 19:39:41","id":94,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":48,"qiniuKey":"qiniuKey1","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey1?e=1528116059&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:NRl8Pq4WbNfbvODFCoPpqBQvYv4="},{"createdBy":"18405863019","createdDate":"2018-06-04 19:39:41","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-04 19:39:41","id":95,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":48,"qiniuKey":"qiniuKey2","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey2?e=1528116059&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:D9yM_o5yIyuBARuMOXL5z5M3jR0="}]}]
     * last : true
     * totalElements : 2
     * totalPages : 1
     * number : 0
     * size : 5
     * sort : [{"direction":"ASC","property":"createdDate","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * first : true
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
         * createdBy : 18405863019
         * createdDate : 2018-06-04 19:39:04
         * lastModifiedBy : 18405863019
         * lastModifiedDate : 2018-06-04 19:39:04
         * id : 46
         * deleted : false
         * sort : 10
         * fromClientType : null
         * type : ruling_clerical
         * typeValue : 裁决文书
         * title : 裁决文书
         * content : 裁决文书content
         * riskManageComments : null
         * riskManageHandleDate : null
         * member : null
         * operator : null
         * iconUrl :
         * attachmentList : [{"createdBy":"18405863019","createdDate":"2018-06-04 19:39:04","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-04 19:39:04","id":90,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":46,"qiniuKey":"qiniuKey1","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey1?e=1528116059&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:NRl8Pq4WbNfbvODFCoPpqBQvYv4="},{"createdBy":"18405863019","createdDate":"2018-06-04 19:39:04","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-04 19:39:04","id":91,"deleted":false,"sort":10,"fromClientType":null,"fkType":"RISK_WARNING","fkId":46,"qiniuKey":"qiniuKey2","fileName":null,"fileType":null,"fileSize":null,"url":"http://p6wldlrh8.bkt.clouddn.com/qiniuKey2?e=1528116059&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:D9yM_o5yIyuBARuMOXL5z5M3jR0="}]
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;

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

        public Object getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(Object fromClientType) {
            this.fromClientType = fromClientType;
        }

        public DictResultBean.ContentBean getType() {
            return type;
        }

        public void setType(DictResultBean.ContentBean type) {
            this.type = type;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
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
        public Object getRiskManageComments() {
            return riskManageComments;
        }

        public void setRiskManageComments(Object riskManageComments) {
            this.riskManageComments = riskManageComments;
        }

        public Object getRiskManageHandleDate() {
            return riskManageHandleDate;
        }

        public void setRiskManageHandleDate(Object riskManageHandleDate) {
            this.riskManageHandleDate = riskManageHandleDate;
        }

        public Object getCreatedDateStr() {
            return createdDateStr;
        }

        public void setCreatedDateStr(Object createdDateStr) {
            this.createdDateStr = createdDateStr;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public Object getOperator() {
            return operator;
        }

        public void setOperator(Object operator) {
            this.operator = operator;
        }

        public Object getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(Object iconUrl) {
            this.iconUrl = iconUrl;
        }

        private int sort;
        private Object fromClientType;
        private DictResultBean.ContentBean type;
        private String typeValue;
        private String title;
        private String content;
        private Object riskManageComments;
        private Object riskManageHandleDate;
        private Object createdDateStr;
        private Object member;
        private Object operator;
        private Object iconUrl;

        private List<AttachmentListBean> attachmentList;

        public List<AttachmentListBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<AttachmentListBean> attachmentList) {
            this.attachmentList = attachmentList;
        }

        public static class AttachmentListBean {
            /**
             * createdBy : 18405863019
             * createdDate : 2018-06-04 19:39:04
             * lastModifiedBy : 18405863019
             * lastModifiedDate : 2018-06-04 19:39:04
             * id : 90
             * deleted : false
             * sort : 10
             * fromClientType : null
             * fkType : RISK_WARNING
             * fkId : 46
             * qiniuKey : qiniuKey1
             * fileName : null
             * fileType : null
             * fileSize : null
             * url : http://p6wldlrh8.bkt.clouddn.com/qiniuKey1?e=1528116059&token=ZSVlIT279gC4irwHchoOJR8U56Q7y3_h9Qgt-UsW:NRl8Pq4WbNfbvODFCoPpqBQvYv4=
             */

            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private String fromClientType;
            private String fkType;
            private int fkId;
            private String qiniuKey;
            private Object fileName;
            private Object fileType;
            private Object fileSize;
            private String url;

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

            public String getFkType() {
                return fkType;
            }

            public void setFkType(String fkType) {
                this.fkType = fkType;
            }

            public int getFkId() {
                return fkId;
            }

            public void setFkId(int fkId) {
                this.fkId = fkId;
            }

            public String getQiniuKey() {
                return qiniuKey;
            }

            public void setQiniuKey(String qiniuKey) {
                this.qiniuKey = qiniuKey;
            }

            public Object getFileName() {
                return fileName;
            }

            public void setFileName(Object fileName) {
                this.fileName = fileName;
            }

            public Object getFileType() {
                return fileType;
            }

            public void setFileType(Object fileType) {
                this.fileType = fileType;
            }

            public Object getFileSize() {
                return fileSize;
            }

            public void setFileSize(Object fileSize) {
                this.fileSize = fileSize;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : createdDate
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
    public static class TypeBean {
        /**
         * createdBy : 13064793669
         * createdDate : 2018-05-18 11:41:09
         * lastModifiedBy : 13064793669
         * lastModifiedDate : 2018-05-18 11:41:09
         * id : 17
         * deleted : false
         * sort : 10
         * fromClientType : null
         * name : risk_type
         * desp : 风险预警的类型
         * key : ruling_clerical
         * value : 裁决文书
         * modifiable : null
         * builtIn : false
         * remark : 裁决文书
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private Object fromClientType;
        private String name;
        private String desp;
        private String key;
        private String value;
        private Object modifiable;
        private boolean builtIn;
        private String remark;

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

        public Object getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(Object fromClientType) {
            this.fromClientType = fromClientType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesp() {
            return desp;
        }

        public void setDesp(String desp) {
            this.desp = desp;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Object getModifiable() {
            return modifiable;
        }

        public void setModifiable(Object modifiable) {
            this.modifiable = modifiable;
        }

        public boolean isBuiltIn() {
            return builtIn;
        }

        public void setBuiltIn(boolean builtIn) {
            this.builtIn = builtIn;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
