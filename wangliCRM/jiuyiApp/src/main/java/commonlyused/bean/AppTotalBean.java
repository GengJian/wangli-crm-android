package commonlyused.bean;

import java.util.List;

public class AppTotalBean {

    /**
     * content : [{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":23,"deleted":false,"sort":18,"fromClientType":null,"name":"应收款预警","iosIconUrl":"http://img.jiuyisoft.com/应收款预警.png","androidIconUrl":"http://img.jiuyisoft.com/应收款预警.png","url":"http://www.baidu.com","flag":"receivables_early_warning"},{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":22,"deleted":false,"sort":17,"fromClientType":null,"name":"业务员统计","iosIconUrl":"http://img.jiuyisoft.com/业务员统计.png","androidIconUrl":"http://img.jiuyisoft.com/业务员统计.png","url":"http://www.baidu.com","flag":"salesman_statistics"},{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":21,"deleted":false,"sort":16,"fromClientType":null,"name":"销售台账","iosIconUrl":"http://img.jiuyisoft.com/销售台账.png","androidIconUrl":"http://img.jiuyisoft.com/销售台账.png","url":"http://www.baidu.com","flag":"sales_ledger"},{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":20,"deleted":false,"sort":15,"fromClientType":null,"name":"索赔报表","iosIconUrl":"http://img.jiuyisoft.com/索赔报表.png","androidIconUrl":"http://img.jiuyisoft.com/索赔报表.png","url":"http://www.baidu.com","flag":"claims_report"},{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":19,"deleted":false,"sort":14,"fromClientType":null,"name":"市占率报表","iosIconUrl":"http://img.jiuyisoft.com/市占率报表.png","androidIconUrl":"http://img.jiuyisoft.com/市占率报表.png","url":"http://www.baidu.com","flag":"market_share_report"},{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":18,"deleted":false,"sort":13,"fromClientType":null,"name":"任务协作","iosIconUrl":"http://img.jiuyisoft.com/任务协作.png","androidIconUrl":"http://img.jiuyisoft.com/任务协作.png","url":"http://www.baidu.com","flag":"task_collaboration"},{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":17,"deleted":false,"sort":12,"fromClientType":null,"name":"满意度报表","iosIconUrl":"http://img.jiuyisoft.com/满意度报表.png","androidIconUrl":"http://img.jiuyisoft.com/满意度报表.png","url":"http://www.baidu.com","flag":"satisfaction_report"},{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":16,"deleted":false,"sort":11,"fromClientType":null,"name":"客户欠款明细","iosIconUrl":"http://img.jiuyisoft.com/客户欠款明细.png","androidIconUrl":"http://img.jiuyisoft.com/客户欠款明细.png","url":"http://www.baidu.com","flag":"details_customer_debt"},{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":15,"deleted":false,"sort":10,"fromClientType":null,"name":"开机率统计","iosIconUrl":"http://img.jiuyisoft.com/开机率统计.png","androidIconUrl":"http://img.jiuyisoft.com/开机率统计.png","url":"http://www.baidu.com","flag":"operating_rate_statistics"},{"createdBy":"15355495537","createdDate":"2018-06-20","lastModifiedBy":"15355495537","lastModifiedDate":"2018-06-20","id":14,"deleted":false,"sort":9,"fromClientType":null,"name":"价格指导书","iosIconUrl":"http://img.jiuyisoft.com/价格指导书.png","androidIconUrl":"http://img.jiuyisoft.com/价格指导书.png","url":"http://www.baidu.com","flag":"price_guide_book"}]
     * totalElements : 18
     * last : false
     * totalPages : 2
     * number : 0
     * size : 10
     * sort : [{"direction":"DESC","property":"lastModifiedDate","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}]
     * numberOfElements : 10
     * first : true
     */

    private int totalElements;
    private boolean last;
    private int totalPages;
    private int number;
    private int size;
    private int numberOfElements;
    private boolean first;
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
        /**
         * createdBy : 15355495537
         * createdDate : 2018-06-20
         * lastModifiedBy : 15355495537
         * lastModifiedDate : 2018-06-20
         * id : 23
         * deleted : false
         * sort : 18
         * fromClientType : null
         * name : 应收款预警
         * iosIconUrl : http://img.jiuyisoft.com/应收款预警.png
         * androidIconUrl : http://img.jiuyisoft.com/应收款预警.png
         * url : http://www.baidu.com
         * flag : receivables_early_warning
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
        private String iosIconUrl;
        private String androidIconUrl;
        private String url;
        private String flag;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIosIconUrl() {
            return iosIconUrl;
        }

        public void setIosIconUrl(String iosIconUrl) {
            this.iosIconUrl = iosIconUrl;
        }

        public String getAndroidIconUrl() {
            return androidIconUrl;
        }

        public void setAndroidIconUrl(String androidIconUrl) {
            this.androidIconUrl = androidIconUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : lastModifiedDate
         * ignoreCase : false
         * nullHandling : NATIVE
         * descending : true
         * ascending : false
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
