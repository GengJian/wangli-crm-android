package customer.entity;

import java.util.List;

public class DemandPlanBean
{

    /**
     * content : [{"createdBy":"18405863019","createdDate":"2018-02-22 10:02:48","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23 14:32:32","id":8,"deleted":false,"sort":10,"fromClientType":null,"number":null,"member":null,"material":{"createdBy":"18405863019","createdDate":"2018-04-27 17:05:05","lastModifiedBy":"18405863019","lastModifiedDate":"2018-04-27 17:05:05","id":13,"deleted":false,"sort":10,"fromClientType":null,"number":"1","desp":"测试物料","batchNumber":"D1355","batchNumberTwo":null,"spec":"40D","application":null,"field":null,"unit":null,"denier":null,"productPlace":null,"packingType":null,"holesRotatingSpeed":null,"status":"USEABLE","minPrice":null,"maxPrice":null,"cartItems":null,"orderItems":null},"year":2018,"month":6,"office":null,"salesMan":null,"status":"APPROVALED","quantity":20,"adjustedQuantity":100,"unit":null,"weight":40,"actualShip":"13.2%","issuedQuantity":13.2,"undoneReasonType":{"createdBy":"15058305380","createdDate":"2018-05-23 14:18:17","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23 14:18:17","id":68,"deleted":false,"sort":10,"fromClientType":null,"name":"demand_plan_undone_reason","desp":"要货计划未完成原因","key":"quality_problem","value":"质量问题转批","modifiable":null,"builtIn":false,"remark":"质量问题转批","orders":null},"undoneRemark":null,"showQuantity":null}]
     * totalElements : 1
     * last : true
     * totalPages : 1
     * size : 2
     * number : 0
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * first : true
     * numberOfElements : 1
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
        public ContentBean(int id, double quantity) {
            this.id = id;
            this.quantity = quantity;
        }

        public ContentBean() {
        }

        /**
         * createdBy : 18405863019
         * createdDate : 2018-02-22 10:02:48
         * lastModifiedBy : 15058305380
         * lastModifiedDate : 2018-05-23 14:32:32
         * id : 8
         * deleted : false
         * sort : 10
         * fromClientType : null
         * number : null
         * member : null
         * material : {"createdBy":"18405863019","createdDate":"2018-04-27 17:05:05","lastModifiedBy":"18405863019","lastModifiedDate":"2018-04-27 17:05:05","id":13,"deleted":false,"sort":10,"fromClientType":null,"number":"1","desp":"测试物料","batchNumber":"D1355","batchNumberTwo":null,"spec":"40D","application":null,"field":null,"unit":null,"denier":null,"productPlace":null,"packingType":null,"holesRotatingSpeed":null,"status":"USEABLE","minPrice":null,"maxPrice":null,"cartItems":null,"orderItems":null}
         * year : 2018
         * month : 6
         * office : null
         * salesMan : null
         * status : APPROVALED
         * quantity : 20
         * adjustedQuantity : 100
         * unit : null
         * weight : 40
         * actualShip : 13.2%
         * issuedQuantity : 13.2
         * undoneReasonType : {"createdBy":"15058305380","createdDate":"2018-05-23 14:18:17","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23 14:18:17","id":68,"deleted":false,"sort":10,"fromClientType":null,"name":"demand_plan_undone_reason","desp":"要货计划未完成原因","key":"quality_problem","value":"质量问题转批","modifiable":null,"builtIn":false,"remark":"质量问题转批","orders":null}
         * undoneRemark : null
         * showQuantity : null
         */


        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private int number;
        private MemberBeanID member;
        private MaterialBean material;
        private int year;
        private int month;
        private Object office;
        private Object salesMan;
        private String status;
        private double quantity;
        private double adjustedQuantity;
        private Object unit;
        private double weight;
        private String actualShip;
        private double issuedQuantity;
        private UndoneReasonTypeBean undoneReasonType;
        private String undoneRemark;
        private String showQuantity;
        private String productLevel;

        public String getPrescription() {
            return prescription;
        }

        public void setPrescription(String prescription) {
            this.prescription = prescription;
        }

        private String prescription;

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        private String spec;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getFactoryNumber() {
            return factoryNumber;
        }

        public void setFactoryNumber(String factoryNumber) {
            this.factoryNumber = factoryNumber;
        }

        private String factory;
        private String factoryNumber;

        public String getProductLevelName() {
            return productLevelName;
        }

        public void setProductLevelName(String productLevelName) {
            this.productLevelName = productLevelName;
        }

        public String getBatchNumber() {
            return batchNumber;
        }

        public void setBatchNumber(String batchNumber) {
            this.batchNumber = batchNumber;
        }

        private String productLevelName;
        private String batchNumber;

        public String getProductLevel() {
            return productLevel;
        }

        public void setProductLevel(String productLevel) {
            this.productLevel = productLevel;
        }

        public String getProductPlace() {
            return productPlace;
        }

        public void setProductPlace(String productPlace) {
            this.productPlace = productPlace;
        }

        public double getGuidePrice() {
            return guidePrice;
        }

        public void setGuidePrice(double guidePrice) {
            this.guidePrice = guidePrice;
        }

        private String productPlace;
        private double guidePrice;

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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public MaterialBean getMaterial() {
            return material;
        }

        public void setMaterial(MaterialBean material) {
            this.material = material;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public Object getOffice() {
            return office;
        }

        public void setOffice(Object office) {
            this.office = office;
        }

        public Object getSalesMan() {
            return salesMan;
        }

        public void setSalesMan(Object salesMan) {
            this.salesMan = salesMan;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }

        public double getAdjustedQuantity() {
            return adjustedQuantity;
        }

        public void setAdjustedQuantity(double adjustedQuantity) {
            this.adjustedQuantity = adjustedQuantity;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getActualShip() {
            return actualShip;
        }

        public void setActualShip(String actualShip) {
            this.actualShip = actualShip;
        }

        public double getIssuedQuantity() {
            return issuedQuantity;
        }

        public void setIssuedQuantity(double issuedQuantity) {
            this.issuedQuantity = issuedQuantity;
        }

        public UndoneReasonTypeBean getUndoneReasonType() {
            return undoneReasonType;
        }

        public void setUndoneReasonType(UndoneReasonTypeBean undoneReasonType) {
            this.undoneReasonType = undoneReasonType;
        }

        public String getUndoneRemark() {
            return undoneRemark;
        }

        public void setUndoneRemark(String undoneRemark) {
            this.undoneRemark = undoneRemark;
        }

        public String getShowQuantity() {
            return showQuantity;
        }

        public void setShowQuantity(String showQuantity) {
            this.showQuantity = showQuantity;
        }

        public static class MaterialBean {
            /**
             * createdBy : 18405863019
             * createdDate : 2018-04-27 17:05:05
             * lastModifiedBy : 18405863019
             * lastModifiedDate : 2018-04-27 17:05:05
             * id : 13
             * deleted : false
             * sort : 10
             * fromClientType : null
             * number : 1
             * desp : 测试物料
             * batchNumber : D1355
             * batchNumberTwo : null
             * spec : 40D
             * application : null
             * field : null
             * unit : null
             * denier : null
             * productPlace : null
             * packingType : null
             * holesRotatingSpeed : null
             * status : USEABLE
             * minPrice : null
             * maxPrice : null
             * cartItems : null
             * orderItems : null
             */

            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private Object fromClientType;
            private String number;
            private String desp;
            private String batchNumber;
            private Object batchNumberTwo;
            private String spec;
            private Object application;
            private Object field;
            private Object unit;
            private Object denier;
            private Object productPlace;

            public String getProductPlaceTwo() {
                return productPlaceTwo;
            }

            public void setProductPlaceTwo(String productPlaceTwo) {
                this.productPlaceTwo = productPlaceTwo;
            }

            private String productPlaceTwo;
            private Object packingType;
            private Object holesRotatingSpeed;
            private String status;
            private Object minPrice;
            private Object maxPrice;
            private Object cartItems;
            private Object orderItems;

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

            public void setFromClientType(Object fromClientType) {
                this.fromClientType = fromClientType;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getDesp() {
                return desp;
            }

            public void setDesp(String desp) {
                this.desp = desp;
            }

            public String getBatchNumber() {
                return batchNumber;
            }

            public void setBatchNumber(String batchNumber) {
                this.batchNumber = batchNumber;
            }

            public Object getBatchNumberTwo() {
                return batchNumberTwo;
            }

            public void setBatchNumberTwo(Object batchNumberTwo) {
                this.batchNumberTwo = batchNumberTwo;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public Object getApplication() {
                return application;
            }

            public void setApplication(Object application) {
                this.application = application;
            }

            public Object getField() {
                return field;
            }

            public void setField(Object field) {
                this.field = field;
            }

            public Object getUnit() {
                return unit;
            }

            public void setUnit(Object unit) {
                this.unit = unit;
            }

            public Object getDenier() {
                return denier;
            }

            public void setDenier(Object denier) {
                this.denier = denier;
            }

            public Object getProductPlace() {
                return productPlace;
            }

            public void setProductPlace(Object productPlace) {
                this.productPlace = productPlace;
            }

            public Object getPackingType() {
                return packingType;
            }

            public void setPackingType(Object packingType) {
                this.packingType = packingType;
            }

            public Object getHolesRotatingSpeed() {
                return holesRotatingSpeed;
            }

            public void setHolesRotatingSpeed(Object holesRotatingSpeed) {
                this.holesRotatingSpeed = holesRotatingSpeed;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getMinPrice() {
                return minPrice;
            }

            public void setMinPrice(Object minPrice) {
                this.minPrice = minPrice;
            }

            public Object getMaxPrice() {
                return maxPrice;
            }

            public void setMaxPrice(Object maxPrice) {
                this.maxPrice = maxPrice;
            }

            public Object getCartItems() {
                return cartItems;
            }

            public void setCartItems(Object cartItems) {
                this.cartItems = cartItems;
            }

            public Object getOrderItems() {
                return orderItems;
            }

            public void setOrderItems(Object orderItems) {
                this.orderItems = orderItems;
            }
        }

        public static class UndoneReasonTypeBean {
            /**
             * createdBy : 15058305380
             * createdDate : 2018-05-23 14:18:17
             * lastModifiedBy : 15058305380
             * lastModifiedDate : 2018-05-23 14:18:17
             * id : 68
             * deleted : false
             * sort : 10
             * fromClientType : null
             * name : demand_plan_undone_reason
             * desp : 要货计划未完成原因
             * key : quality_problem
             * value : 质量问题转批
             * modifiable : null
             * builtIn : false
             * remark : 质量问题转批
             * orders : null
             */

            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private Object fromClientType;
            private String name;
            private String desp;
            private String key;
            private String value;
            private Object modifiable;
            private boolean builtIn;
            private String remark;
            private Object orders;

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

            public void setFromClientType(Object fromClientType) {
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

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public Object getModifiable() {
                return modifiable;
            }

            public void setModifiable(Object modifiable) {
                this.modifiable = modifiable;
            }

            public boolean isBuiltIn() {
                return builtIn;
            }

            public void setBuiltIn(boolean builtIn) {
                this.builtIn = builtIn;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public Object getOrders() {
                return orders;
            }

            public void setOrders(Object orders) {
                this.orders = orders;
            }
        }
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
    /*public static class MemberBean {
        *//**
         * id : 20
         *//*

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }*/
}
