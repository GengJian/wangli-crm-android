package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:LabelBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/9/7 16:07
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/9/7 1.00 初始版本
 * ****************************************************************
 */
public class LabelBean {

    /**
     * content : [{"createdBy":"15858562128","createdDate":"2018-09-07","lastModifiedBy":"15858562128","lastModifiedDate":"2018-09-07","id":110437561,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":null,"desp":"美滋滋","hotNumber":2},{"createdBy":"15858562128","createdDate":"2018-09-06","lastModifiedBy":"15858562128","lastModifiedDate":"2018-09-06","id":110433979,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":null,"desp":"美滋滋","hotNumber":1},{"createdBy":"15858562128","createdDate":"2018-09-06","lastModifiedBy":"15858562128","lastModifiedDate":"2018-09-06","id":110433977,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":null,"desp":"美滋滋","hotNumber":1},{"createdBy":"15858562128","createdDate":"2018-09-06","lastModifiedBy":"15858562128","lastModifiedDate":"2018-09-06","id":110433975,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":null,"desp":"美滋滋","hotNumber":1},{"createdBy":"15858562128","createdDate":"2018-09-06","lastModifiedBy":"15858562128","lastModifiedDate":"2018-09-06","id":110433969,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":null,"desp":"美滋滋","hotNumber":1},{"createdBy":"13606552772","createdDate":"2018-09-07","lastModifiedBy":"13606552772","lastModifiedDate":"2018-09-07","id":110610133,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":"15","desp":"服饰","hotNumber":0},{"createdBy":"13957543371","createdDate":"2018-09-07","lastModifiedBy":"13957543371","lastModifiedDate":"2018-09-07","id":110570881,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":null,"desp":"稳","hotNumber":0},{"createdBy":"15858562128","createdDate":"2018-09-06","lastModifiedBy":"15858562128","lastModifiedDate":"2018-09-06","id":110433941,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":null,"desp":"老铁问的一批","hotNumber":0},{"createdBy":"13957543371","createdDate":"2018-08-25","lastModifiedBy":"13957543371","lastModifiedDate":"2018-08-25","id":48561590,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":null,"desp":"老板很好","hotNumber":null},{"createdBy":"15858562128","createdDate":"2018-08-24","lastModifiedBy":"15858562128","lastModifiedDate":"2018-08-24","id":19429409,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":null,"desp":"纳税大户","hotNumber":null}]
     * last : false
     * totalElements : 89
     * totalPages : 9
     * first : true
     * numberOfElements : 10
     * sort : [{"direction":"DESC","property":"hotNumber","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * size : 10
     * number : 0
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private boolean first;
    private int numberOfElements;
    private int size;
    private int number;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
         * createdBy : 15858562128
         * createdDate : 2018-09-07
         * lastModifiedBy : 15858562128
         * lastModifiedDate : 2018-09-07
         * id : 110437561
         * deleted : false
         * sort : 10
         * fromClientType : null
         * optionGroup : []
         * name : null
         * desp : 美滋滋
         * hotNumber : 2
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private String name;
        private String desp;
        private int hotNumber;
        private List<?> optionGroup;

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

        public void setFromClientType(String fromClientType) {
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

        public int getHotNumber() {
            return hotNumber;
        }

        public void setHotNumber(int hotNumber) {
            this.hotNumber = hotNumber;
        }

        public List<?> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<?> optionGroup) {
            this.optionGroup = optionGroup;
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : hotNumber
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
