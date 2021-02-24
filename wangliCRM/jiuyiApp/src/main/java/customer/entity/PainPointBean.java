package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:PainPointBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/8 16:57
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/8 1.00 初始版本
 * ****************************************************************
 */
public class PainPointBean {


    /**
     * content : [{"id":24,"createdDate":"2019-01-10T14:36:16.894+0800","createOperator":"潘梦洋","linkManName":"赵","desp":"新需求要重视","grade":"","needFeedBack":true,"closingDate":"2019-01-30T00:00:00.000+0800","reply":"我们会尽快处理的"},{"id":21,"createdDate":"2018-12-21T14:56:25.525+0800","createOperator":"潘梦洋","linkManName":"赵","desp":"发货太慢","grade":"","needFeedBack":true,"closingDate":"2018-12-22T00:00:00.000+0800","reply":"我们会增加样品"}]
     * totalElements : 2
     * last : true
     * totalPages : 1
     * size : 10
     * number : 0
     * first : true
     * sort : [{"direction":"DESC","property":"createdDate","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * numberOfElements : 2
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
         * id : 24
         * createdDate : 2019-01-10T14:36:16.894+0800
         * createOperator : 潘梦洋
         * linkManName : 赵
         * desp : 新需求要重视
         * grade :
         * needFeedBack : true
         * closingDate : 2019-01-30T00:00:00.000+0800
         * reply : 我们会尽快处理的
         */

        private long id;
        private String createdDate;
        private String createOperator;
        private String linkManName;
        private String desp;
        private String grade;
        private boolean needFeedBack;
        private String closingDate;
        private String reply;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreateOperator() {
            return createOperator;
        }

        public void setCreateOperator(String createOperator) {
            this.createOperator = createOperator;
        }

        public String getLinkManName() {
            return linkManName;
        }

        public void setLinkManName(String linkManName) {
            this.linkManName = linkManName;
        }

        public String getDesp() {
            return desp;
        }

        public void setDesp(String desp) {
            this.desp = desp;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public boolean isNeedFeedBack() {
            return needFeedBack;
        }

        public void setNeedFeedBack(boolean needFeedBack) {
            this.needFeedBack = needFeedBack;
        }

        public String getClosingDate() {
            return closingDate;
        }

        public void setClosingDate(String closingDate) {
            this.closingDate = closingDate;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
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
