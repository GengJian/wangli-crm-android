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
public class CustomerStoreInfoBean {

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


        private String lastUpdateByName;
        private String numberOfGuides;
        private CityBean city;
        private boolean whetherToMonopolize;
        private String intoTheStore;
        private String afterSalesCall;
        private ProvinceBean province;
        private String legalPerson;


        public String getLegalName() {
            return legalName;
        }

        public void setLegalName(String legalName) {
            this.legalName = legalName;
        }

        private String legalName;
        private String contactNumber;
        private String storeName;
        private long id;
        private String sampleDoorStyle;
        private List<BrandBean> brand;

        public List<BrandBean> getBrand() {
            return brand;
        }

        public void setBrand(List<BrandBean> brand) {
            this.brand = brand;
        }

        private String storeArea;
        private AreaBean area;
        private String brandName;
        private String shipment;
        private String createByName;
        private String lastModifiedDate;
        private String lastModifiedBy;
        private String retailVolume;
        private String customerName;
        private String communityActivityThisMonth;
        private String createdDate;
        private String compareSalesItems;
        private String forPublicHouseholds;
        private String createdBy;
        private String storeAddress;
        private String turnoverRate;

        public Boolean getDeleted() {
            return deleted;
        }

        public void setDeleted(Boolean deleted) {
            this.deleted = deleted;
        }

        private Boolean deleted = false;

        public String getLastUpdateByName() {
            return lastUpdateByName;
        }

        public void setLastUpdateByName(String lastUpdateByName) {
            this.lastUpdateByName = lastUpdateByName;
        }

        public String getNumberOfGuides() {
            return numberOfGuides;
        }

        public void setNumberOfGuides(String numberOfGuides) {
            this.numberOfGuides = numberOfGuides;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public boolean isWhetherToMonopolize() {
            return whetherToMonopolize;
        }

        public void setWhetherToMonopolize(boolean whetherToMonopolize) {
            this.whetherToMonopolize = whetherToMonopolize;
        }

        public String getIntoTheStore() {
            return intoTheStore;
        }

        public void setIntoTheStore(String intoTheStore) {
            this.intoTheStore = intoTheStore;
        }

        public String getAfterSalesCall() {
            return afterSalesCall;
        }

        public void setAfterSalesCall(String afterSalesCall) {
            this.afterSalesCall = afterSalesCall;
        }

        public ProvinceBean getProvince() {
            return province;
        }

        public void setProvince(ProvinceBean province) {
            this.province = province;
        }

        public String getLegalPerson() {
            return legalPerson;
        }

        public void setLegalPerson(String legalPerson) {
            this.legalPerson = legalPerson;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getSampleDoorStyle() {
            return sampleDoorStyle;
        }

        public void setSampleDoorStyle(String sampleDoorStyle) {
            this.sampleDoorStyle = sampleDoorStyle;
        }



        public String getStoreArea() {
            return storeArea;
        }

        public void setStoreArea(String storeArea) {
            this.storeArea = storeArea;
        }

        public AreaBean getArea() {
            return area;
        }

        public void setArea(AreaBean area) {
            this.area = area;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getShipment() {
            return shipment;
        }

        public void setShipment(String shipment) {
            this.shipment = shipment;
        }

        public String getCreateByName() {
            return createByName;
        }

        public void setCreateByName(String createByName) {
            this.createByName = createByName;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getRetailVolume() {
            return retailVolume;
        }

        public void setRetailVolume(String retailVolume) {
            this.retailVolume = retailVolume;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCommunityActivityThisMonth() {
            return communityActivityThisMonth;
        }

        public void setCommunityActivityThisMonth(String communityActivityThisMonth) {
            this.communityActivityThisMonth = communityActivityThisMonth;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getCompareSalesItems() {
            return compareSalesItems;
        }

        public void setCompareSalesItems(String compareSalesItems) {
            this.compareSalesItems = compareSalesItems;
        }

        public String getForPublicHouseholds() {
            return forPublicHouseholds;
        }

        public void setForPublicHouseholds(String forPublicHouseholds) {
            this.forPublicHouseholds = forPublicHouseholds;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getTurnoverRate() {
            return turnoverRate;
        }

        public void setTurnoverRate(String turnoverRate) {
            this.turnoverRate = turnoverRate;
        }

        public String getFaxNumber() {
            return faxNumber;
        }

        public void setFaxNumber(String faxNumber) {
            this.faxNumber = faxNumber;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getNumberOfSampleDoors() {
            return numberOfSampleDoors;
        }

        public void setNumberOfSampleDoors(String numberOfSampleDoors) {
            this.numberOfSampleDoors = numberOfSampleDoors;
        }

        public String getQualitySituation() {
            return qualitySituation;
        }

        public void setQualitySituation(String qualitySituation) {
            this.qualitySituation = qualitySituation;
        }

        public MemberBeanID getCustomer() {
            return customer;
        }

        public void setCustomer(MemberBeanID customer) {
            this.customer = customer;
        }

        private String faxNumber;
        private String provinceName;
        private String numberOfSampleDoors;
        private String qualitySituation;
        private MemberBeanID customer;



        public static class CityBean {
            /**
             * cityName : 和田地区
             * cityId : 653200
             * id : 340
             * provinceId : 650000
             */

            private String cityName;
            private String cityId;
            private long id;
            private String provinceId;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
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
             * brandName : 能诚
             * createdDate : 2019-04-09 14:08:12
             * brandDesc : 能诚
             * lastModifiedDate : 2019-04-09 14:08:17
             * createdBy : 管理员
             * lastModifiedBy : 管理员
             * id : 4
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

        public static class AreaBean {
            /**
             * areaId : 653224
             * areaName : 洛浦县
             * cityId : 653200
             * id : 3113
             */

            private String areaId;
            private String areaName;
            private String cityId;
            private long id;

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }
        }
    }
}
