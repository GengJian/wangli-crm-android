package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CustomerStoreInfoBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/3/21 15:11
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/3/21 1.00 初始版本
 * ****************************************************************
 */
public class CustomerPlanBean {
    private int number;
    private boolean last;
    private int numberOfElements;
    private int size;
    private int totalPages;
    private boolean first;
    private int totalElements;
    private List<SortBean> sort;
    private List<ContentBean> content;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
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

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
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
         * nullHandling : NATIVE
         * ignoreCase : false
         * property : id
         * ascending : false
         * descending : true
         * direction : DESC
         */

        private String nullHandling;
        private boolean ignoreCase;
        private String property;
        private boolean ascending;
        private boolean descending;
        private String direction;

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
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

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }

    public static class ContentBean {

        private double november;

        public double getNovember() {
            return november;
        }

        public void setNovember(double november) {
            this.november = november;
        }

        public double getOctoberToNovember() {
            return octoberToNovember;
        }

        public void setOctoberToNovember(double octoberToNovember) {
            this.octoberToNovember = octoberToNovember;
        }

        public double getApril() {
            return april;
        }

        public void setApril(double april) {
            this.april = april;
        }

        public double getTotalForTheWholeYear() {
            return totalForTheWholeYear;
        }

        public void setTotalForTheWholeYear(double totalForTheWholeYear) {
            this.totalForTheWholeYear = totalForTheWholeYear;
        }

        public double getJulyToSeptember() {
            return julyToSeptember;
        }

        public void setJulyToSeptember(double julyToSeptember) {
            this.julyToSeptember = julyToSeptember;
        }

        public double getSeptember() {
            return september;
        }

        public void setSeptember(double september) {
            this.september = september;
        }

        public ProvinceBean getProvince() {
            return province;
        }

        public void setProvince(ProvinceBean province) {
            this.province = province;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public double getDecember() {
            return december;
        }

        public void setDecember(double december) {
            this.december = december;
        }

        public long getDealerType() {
            return dealerType;
        }

        public void setDealerType(long dealerType) {
            this.dealerType = dealerType;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public BrandBean getBrand() {
            return brand;
        }

        public void setBrand(BrandBean brand) {
            this.brand = brand;
        }

        public String getGetDealerPlanName() {
            return getDealerPlanName;
        }

        public void setGetDealerPlanName(String getDealerPlanName) {
            this.getDealerPlanName = getDealerPlanName;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public double getMay() {
            return may;
        }

        public void setMay(double may) {
            this.may = may;
        }

        public double getAugust() {
            return august;
        }

        public void setAugust(double august) {
            this.august = august;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public double getJanuaryToJune() {
            return januaryToJune;
        }

        public void setJanuaryToJune(double januaryToJune) {
            this.januaryToJune = januaryToJune;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public double getFebruary() {
            return february;
        }

        public void setFebruary(double february) {
            this.february = february;
        }

        public double getJuly() {
            return july;
        }

        public void setJuly(double july) {
            this.july = july;
        }

        public String getDealerPlanCode() {
            return dealerPlanCode;
        }

        public void setDealerPlanCode(String dealerPlanCode) {
            this.dealerPlanCode = dealerPlanCode;
        }

        public double getMarch() {
            return march;
        }

        public void setMarch(double march) {
            this.march = march;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public double getJune() {
            return june;
        }

        public void setJune(double june) {
            this.june = june;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public double getJanuary() {
            return january;
        }

        public void setJanuary(double january) {
            this.january = january;
        }

        public double getOctober() {
            return october;
        }

        public void setOctober(double october) {
            this.october = october;
        }

        private double octoberToNovember;
        private double april;
        private double totalForTheWholeYear;
        private double julyToSeptember;
        private double september;
        private ProvinceBean province;
        private MemberBeanID member;
        private double december;
        private long dealerType;
        private long id;
        private BrandBean brand;
        private String getDealerPlanName;
        private String brandName;
        private double may;
        private double august;
        private String lastModifiedDate;
        private double januaryToJune;
        private String lastModifiedBy;
        private double february;
        private double july;
        private String dealerPlanCode;
        private double march;
        private String createdDate;
        private double june;
        private String createdBy;
        private double january;
        private double october;


        public static class ProvinceBean {
            /**
             * provinceName : 湖南省
             * id : 18
             * provinceId : 430000
             */

            private String provinceName;
            private long id;
            private String provinceId;

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }
        }


        public static class BrandBean {
            /**
             * brandName : 华爵
             * createdDate : 2019-04-09 14:07:42
             * brandDesc : 华爵
             * lastModifiedDate : 2019-04-09 14:07:47
             * createdBy : 管理员
             * lastModifiedBy : 管理员
             * id : 3
             * sort : 0
             */

            private String brandName;
            private String createdDate;
            private String brandDesc;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private long id;
            private int sort;

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getBrandDesc() {
                return brandDesc;
            }

            public void setBrandDesc(String brandDesc) {
                this.brandDesc = brandDesc;
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
        }
    }
}
