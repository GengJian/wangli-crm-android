package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:FactoryEquipmentBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/4 18:17
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/4 1.00 初始版本
 * ****************************************************************
 */
public class FactoryEquipmentBean {

    /**
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 1
     * size : 5
     * totalElements : 1
     * totalPages : 1
     * sort : [{"ascending":true,"descending":false,"direction":"ASC","ignoreCase":false,"nullHandling":"NATIVE","property":"id"}]
     * content : [{"id":5,"sort":10,"createdDate":1546596949605,"lastModifiedDate":1546596949605,"parameters":"aaaa","count":0,"member":{"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546079225376,"cooperationTypeValue":"供应商","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","sapNumber":"200567","simpleSpell":"dgsgr"},"operator":{"id":1,"sort":10,"createdDate":1543497342573,"lastModifiedDate":1546595222083,"name":"刘启明","department":{"id":1,"sort":100,"createdDate":1543497296440,"lastModifiedDate":1545831978687,"name":"营销部-禁用"},"title":"营销分析员","oaCode":"1022654"},"businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂","factoryName":"aa","workshop":"aa","vendor":"aa","equipmentTypeKey":"welding_machine","equipmentTypeValue":"焊接机","equipmentModel":"aa"}]
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
         * id : 5
         * sort : 10
         * createdDate : 1546596949605
         * lastModifiedDate : 1546596949605
         * parameters : aaaa
         * count : 0
         * member : {"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546079225376,"cooperationTypeValue":"供应商","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","sapNumber":"200567","simpleSpell":"dgsgr"}
         * operator : {"id":1,"sort":10,"createdDate":1543497342573,"lastModifiedDate":1546595222083,"name":"刘启明","department":{"id":1,"sort":100,"createdDate":1543497296440,"lastModifiedDate":1545831978687,"name":"营销部-禁用"},"title":"营销分析员","oaCode":"1022654"}
         * businessTypeKey : silicon_factory
         * businessTypeValue : 硅料厂
         * factoryName : aa
         * workshop : aa
         * vendor : aa
         * equipmentTypeKey : welding_machine
         * equipmentTypeValue : 焊接机
         * equipmentModel : aa
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String parameters;
        private int count;
        private MemberBeanID member;
        private String businessTypeKey;
        private String businessTypeValue;
        private String factoryName;
        private String workshop;
        private String vendor;
        private String equipmentTypeKey;
        private String equipmentTypeValue;
        private String equipmentModel;

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

        public String getParameters() {
            return parameters;
        }

        public void setParameters(String parameters) {
            this.parameters = parameters;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public String getFactoryName() {
            return factoryName;
        }

        public void setFactoryName(String factoryName) {
            this.factoryName = factoryName;
        }

        public String getWorkshop() {
            return workshop;
        }

        public void setWorkshop(String workshop) {
            this.workshop = workshop;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getEquipmentTypeKey() {
            return equipmentTypeKey;
        }

        public void setEquipmentTypeKey(String equipmentTypeKey) {
            this.equipmentTypeKey = equipmentTypeKey;
        }

        public String getEquipmentTypeValue() {
            return equipmentTypeValue;
        }

        public void setEquipmentTypeValue(String equipmentTypeValue) {
            this.equipmentTypeValue = equipmentTypeValue;
        }

        public String getEquipmentModel() {
            return equipmentModel;
        }

        public void setEquipmentModel(String equipmentModel) {
            this.equipmentModel = equipmentModel;
        }
    }
}
