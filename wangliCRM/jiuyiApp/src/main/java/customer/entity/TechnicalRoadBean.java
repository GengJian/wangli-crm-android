package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:TechnicalRoadBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/8 14:14
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/8 1.00 初始版本
 * ****************************************************************
 */
public class TechnicalRoadBean {

    /**
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 1
     * size : 5
     * totalElements : 1
     * totalPages : 1
     * sort : [{"ascending":true,"descending":false,"direction":"ASC","ignoreCase":false,"nullHandling":"NATIVE","property":"id"}]
     * content : [{"id":6,"sort":10,"createdDate":1546928001776,"lastModifiedDate":1546928001776,"createdBy":"1022654","lastModifiedBy":"1022654","content":"aaa","technicalMeansSet":[{"id":17,"sort":10,"createdDate":1546928001801,"lastModifiedDate":1546928001801,"createdBy":"1022654","lastModifiedBy":"1022654","meansKey":"tile","meansValue":"叠瓦"},{"id":18,"sort":10,"createdDate":1546928001808,"lastModifiedDate":1546928001808,"createdBy":"1022654","lastModifiedBy":"1022654","meansKey":"half_piece","meansValue":"半片"}],"typeValue":"效率规划","typeKey":"efficiency_planning","productType":"a","statusValue":"未达成","statusKey":"unreach","member":{"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546079225376,"createdBy":"system","lastModifiedBy":"system","cooperationTypeValue":"供应商","simpleSpell":"dgsgr","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","sapNumber":"200567","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配"},"endDate":"2019-01-24","businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂","planDate":"2019-01-04"}]
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
         * id : 6
         * sort : 10
         * createdDate : 1546928001776
         * lastModifiedDate : 1546928001776
         * createdBy : 1022654
         * lastModifiedBy : 1022654
         * content : aaa
         * technicalMeansSet : [{"id":17,"sort":10,"createdDate":1546928001801,"lastModifiedDate":1546928001801,"createdBy":"1022654","lastModifiedBy":"1022654","meansKey":"tile","meansValue":"叠瓦"},{"id":18,"sort":10,"createdDate":1546928001808,"lastModifiedDate":1546928001808,"createdBy":"1022654","lastModifiedBy":"1022654","meansKey":"half_piece","meansValue":"半片"}]
         * typeValue : 效率规划
         * typeKey : efficiency_planning
         * productType : a
         * statusValue : 未达成
         * statusKey : unreach
         * member : {"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546079225376,"createdBy":"system","lastModifiedBy":"system","cooperationTypeValue":"供应商","simpleSpell":"dgsgr","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","sapNumber":"200567","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配"}
         * endDate : 2019-01-24
         * businessTypeKey : silicon_factory
         * businessTypeValue : 硅料厂
         * planDate : 2019-01-04
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String content;
        private String typeValue;
        private String typeKey;
        private String productType;
        private String statusValue;
        private String statusKey;
        private MemberBeanID member;
        private String endDate;
        private String businessTypeKey;
        private String businessTypeValue;
        private String planDate;
        private List<TechnicalMeansSetBean> technicalMeansSet;

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

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }

        public String getTypeKey() {
            return typeKey;
        }

        public void setTypeKey(String typeKey) {
            this.typeKey = typeKey;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getStatusValue() {
            return statusValue;
        }

        public void setStatusValue(String statusValue) {
            this.statusValue = statusValue;
        }

        public String getStatusKey() {
            return statusKey;
        }

        public void setStatusKey(String statusKey) {
            this.statusKey = statusKey;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
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

        public String getPlanDate() {
            return planDate;
        }

        public void setPlanDate(String planDate) {
            this.planDate = planDate;
        }

        public List<TechnicalMeansSetBean> getTechnicalMeansSet() {
            return technicalMeansSet;
        }

        public void setTechnicalMeansSet(List<TechnicalMeansSetBean> technicalMeansSet) {
            this.technicalMeansSet = technicalMeansSet;
        }


        public static class TechnicalMeansSetBean {
            /**
             * id : 17
             * sort : 10
             * createdDate : 1546928001801
             * lastModifiedDate : 1546928001801
             * createdBy : 1022654
             * lastModifiedBy : 1022654
             * meansKey : tile
             * meansValue : 叠瓦
             */

            private long id;
            private int sort;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String meansKey;
            private String meansValue;

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

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public String getMeansKey() {
                return meansKey;
            }

            public void setMeansKey(String meansKey) {
                this.meansKey = meansKey;
            }

            public String getMeansValue() {
                return meansValue;
            }

            public void setMeansValue(String meansValue) {
                this.meansValue = meansValue;
            }
        }
    }
}
