package dynamic.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:DyContractBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/12 21:36
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/12 1.00 初始版本
 * ****************************************************************
 */
public class DyContractBean {

    /**
     * content : [{"fromClientType":null,"fkId":114,"memberName":"江西晶科","createDate":"2019-01-09T20:17:58.454+0800","crmNumber":"AKSM2019005","sapNumber":"40003691","unit":"W","materialDesp":"单晶PBPERC5主栅直栅106细栅4分段单面C","actualQuantity":1200,"quantity":16,"rate":"7500.00%"}]
     * last : true
     * totalElements : 1
     * totalPages : 1
     * number : 0
     * size : 10
     * sort : [{"direction":"DESC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * numberOfElements : 1
     * first : true
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int number;
    private int size;
    private int numberOfElements;
    private boolean first;
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

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
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
        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
        }

        public long getFkId() {
            return fkId;
        }

        public void setFkId(long fkId) {
            this.fkId = fkId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCrmNumber() {
            return crmNumber;
        }

        public void setCrmNumber(String crmNumber) {
            this.crmNumber = crmNumber;
        }

        public String getSapNumber() {
            return sapNumber;
        }

        public void setSapNumber(String sapNumber) {
            this.sapNumber = sapNumber;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getMaterialDesp() {
            return materialDesp;
        }

        public void setMaterialDesp(String materialDesp) {
            this.materialDesp = materialDesp;
        }

        public double getActualQuantity() {
            return actualQuantity;
        }

        public void setActualQuantity(double actualQuantity) {
            this.actualQuantity = actualQuantity;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        /**
         * fromClientType : null
         * fkId : 114
         * memberName : 江西晶科
         * createDate : 2019-01-09T20:17:58.454+0800
         * crmNumber : AKSM2019005
         * sapNumber : 40003691
         * unit : W
         * materialDesp : 单晶PBPERC5主栅直栅106细栅4分段单面C
         * actualQuantity : 1200
         * quantity : 16
         * rate : 7500.00%
         */

        private String fromClientType;
        private long fkId;
        private String memberName;
        private String createDate;
        private String crmNumber;
        private String sapNumber;
        private String unit;
        private String materialDesp;
        private double actualQuantity;
        private double quantity;
        private String rate;


    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : id
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : false
         * descending : true
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
