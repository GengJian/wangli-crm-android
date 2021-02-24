package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:BusinessChanceBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/8 17:00
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/8 1.00 初始版本
 * ****************************************************************
 */
public class BusinessChanceBean {


    /**
     * content : [{"id":214,"content":"测试2019年1月17日12:48:51","customerContact":"张三","operatorName":"范金纯","submitDate":"2019-01-17T12:53:34.149+0800"},{"id":213,"content":"测试2019年1月17日12:48:51","customerContact":"张三","operatorName":"何达能","submitDate":"2019-01-17T12:49:01.234+0800"},{"id":211,"content":"测2019年1月16日16:59:07","customerContact":"张三","operatorName":"何达能","submitDate":"2019-01-16T16:59:10.476+0800"},{"id":206,"content":"测试2019年1月16日16:42:37","customerContact":"张三","operatorName":"何达能","submitDate":"2019-01-16T16:42:42.197+0800"},{"id":175,"content":"测试2019年1月15日19:10:19","customerContact":"张三","operatorName":"何达能","submitDate":"2019-01-15T19:11:46.868+0800"},{"id":174,"content":"测试2019年1月15日19:08:13","customerContact":"张三","operatorName":"何达能","submitDate":"2019-01-15T19:08:20.164+0800"},{"id":160,"content":"测试2019年1月12日22:26:03","customerContact":"张三","operatorName":"潘梦洋","submitDate":"2019-01-13T14:13:16.051+0800"},{"id":158,"content":"测试2019年1月12日22:26:03","customerContact":"张三","operatorName":"滕细权","submitDate":"2019-01-12T22:30:42.459+0800"},{"id":157,"content":"测试2019年1月12日22:26:03","customerContact":"张三","operatorName":"何达能","submitDate":"2019-01-12T22:26:59.206+0800"},{"id":151,"content":"晶科五月硅片订单商机","customerContact":"张三","operatorName":"刘少岗","submitDate":"2019-01-11T19:29:26.661+0800"}]
     * totalElements : 10
     * last : true
     * totalPages : 1
     * size : 10
     * number : 0
     * first : true
     * sort : [{"direction":"DESC","property":"createdDate","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * numberOfElements : 10
     */

    private int totalElements;
    private boolean last;
    private int totalPages;
    private int size;
    private int number;
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
         * id : 214
         * content : 测试2019年1月17日12:48:51
         * customerContact : 张三
         * operatorName : 范金纯
         * submitDate : 2019-01-17T12:53:34.149+0800
         */

        private long id;
        private String content;
        private String customerContact;
        private String operatorName;
        private String submitDate;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCustomerContact() {
            return customerContact;
        }

        public void setCustomerContact(String customerContact) {
            this.customerContact = customerContact;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getSubmitDate() {
            return submitDate;
        }

        public void setSubmitDate(String submitDate) {
            this.submitDate = submitDate;
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : createdDate
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
