package customer.entity;

import com.jiuyi.model.DictResultBean;

import java.util.List;

public class EquipmentBean {

    /**
     * content : [{"createdBy":"admin","createdDate":"2018-04-24 19:02:39","lastModifiedBy":"admin","lastModifiedDate":"2018-04-24 19:02:39","id":2,"deleted":false,"sort":10,"fromClientType":null,"member":null,"factory":{"createdBy":"15058305380","createdDate":"2018-06-06 10:35:15","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-06 10:35:15","id":7,"deleted":false,"sort":10,"fromClientType":null,"member":null,"name":"工厂测试","defaults":false,"address":"西湖区教工路"},"field":{"createdBy":"admin","createdDate":"2018-04-13 09:49:11","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:11","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"MARKET","value":"营销小助手","modifiable":null,"builtIn":true,"remark":"营销小助手"},"fieldValue":null,"type":{"createdBy":"admin","createdDate":"2018-04-13 09:49:27","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:27","id":9,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"PERFORMANCE","value":"绩效小助手","modifiable":null,"builtIn":true,"remark":"绩效小助手"},"typeValue":null,"name":"设备二号","quantity":10,"remark":null,"brands":"温州制造","size":"15英寸","purchaseYears":5,"attachments":null}]
     * last : false
     * totalPages : 19
     * totalElements : 19
     * number : 1
     * size : 1
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * first : false
     * numberOfElements : 1
     */

    private boolean last;
    private int totalPages;
    private int totalElements;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
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
         * createdDate : 2018-04-24 19:02:39
         * lastModifiedBy : admin
         * lastModifiedDate : 2018-04-24 19:02:39
         * id : 2
         * deleted : false
         * sort : 10
         * fromClientType : null
         * member : null
         * factory : {"createdBy":"15058305380","createdDate":"2018-06-06 10:35:15","lastModifiedBy":"15058305380","lastModifiedDate":"2018-06-06 10:35:15","id":7,"deleted":false,"sort":10,"fromClientType":null,"member":null,"name":"工厂测试","defaults":false,"address":"西湖区教工路"}
         * field : {"createdBy":"admin","createdDate":"2018-04-13 09:49:11","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:11","id":8,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"MARKET","value":"营销小助手","modifiable":null,"builtIn":true,"remark":"营销小助手"}
         * fieldValue : null
         * type : {"createdBy":"admin","createdDate":"2018-04-13 09:49:27","lastModifiedBy":"admin","lastModifiedDate":"2018-04-13 09:49:27","id":9,"deleted":false,"sort":10,"fromClientType":null,"name":"tim_helper_type","desp":"消息小助手类型","key":"PERFORMANCE","value":"绩效小助手","modifiable":null,"builtIn":true,"remark":"绩效小助手"}
         * typeValue : null
         * name : 设备二号
         * quantity : 10
         * remark : null
         * brands : 温州制造
         * size : 15英寸
         * purchaseYears : 5
         * attachments : null
         */

        private String createdBy;
        private String createdDate;

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

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public FactoryBean getFactory() {
            return factory;
        }

        public void setFactory(FactoryBean factory) {
            this.factory = factory;
        }

        public DictResultBean.ContentBean getField() {
            return field;
        }

        public void setField(DictResultBean.ContentBean field) {
            this.field = field;
        }

        public Object getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(Object fieldValue) {
            this.fieldValue = fieldValue;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getBrands() {
            return brands;
        }

        public void setBrands(String brands) {
            this.brands = brands;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public int getPurchaseYears() {
            return purchaseYears;
        }

        public void setPurchaseYears(int purchaseYears) {
            this.purchaseYears = purchaseYears;
        }

        public List<AttachmentBean> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<AttachmentBean> attachments) {
            this.attachments = attachments;
        }

        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private MemberBeanID member;
        private FactoryBean factory;
        private DictResultBean.ContentBean field;
        private Object fieldValue;
        private DictResultBean.ContentBean type;
        private Object typeValue;
        private String name;
        private int quantity;
        private String remark;
        private String brands;
        private String size;
        private int purchaseYears;
        private List<AttachmentBean> attachments;

        public String getRecordDate() {
            return recordDate;
        }

        public void setRecordDate(String recordDate) {
            this.recordDate = recordDate;
        }

        public String getGuideMethod() {
            return guideMethod;
        }

        public void setGuideMethod(String guideMethod) {
            this.guideMethod = guideMethod;
        }

        private String recordDate;
        private String guideMethod;

        public String getCompositionContent() {
            return compositionContent;
        }

        public void setCompositionContent(String compositionContent) {
            this.compositionContent = compositionContent;
        }

        public String getGauze() {
            return gauze;
        }

        public void setGauze(String gauze) {
            this.gauze = gauze;
        }

        public String getGramWeight() {
            return gramWeight;
        }

        public void setGramWeight(String gramWeight) {
            this.gramWeight = gramWeight;
        }

        public String getDyeing() {
            return dyeing;
        }

        public void setDyeing(String dyeing) {
            this.dyeing = dyeing;
        }

        public DictResultBean.ContentBean getProductType() {
            return productType;
        }

        public void setProductType(DictResultBean.ContentBean productType) {
            this.productType = productType;
        }

        private String compositionContent; //成分含量
        private String gauze; //纱支
        private String gramWeight; // 克重
        private String dyeing; //染整工艺
        private DictResultBean.ContentBean productType;//产品类型





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
