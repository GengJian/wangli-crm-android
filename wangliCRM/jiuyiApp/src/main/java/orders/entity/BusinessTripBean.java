package orders.entity;

import java.util.List;

import commonlyused.bean.NormalOperatorBean;

/**
 * ****************************************************************
 * 文件名称:BusinessTripBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/4/3 16:09
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/4/3 1.00 初始版本
 * ****************************************************************
 */
public class BusinessTripBean {
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


        private String noRealNameRemark;
        private String kcTransport;
        private String stayReimbursementStandard;
        private String hgfTransport;
        private String cityTrafficInvoiceAmount;
        private String endDate;
        private String mealBusinessTrip;
        private boolean commit;
        private String cityTrafficReimbursementAmount;
        private String remark;
        private String travelStatusDesp;
        private String title;
        private NormalOperatorBean.OperatorBeanID cohabitOperator;
        private NormalOperatorBean.OperatorBeanID operator;
        private String cityTrafficReimbursementStandard;
        private Long requestId;
        private String hotel;
        private Long id;
        private String plannedBusinessTrip;
        private String stayInvoiceAmount;
        private String otherExpenses;
        private String lastModifiedDate;
        private String stayReimbursementAmount;
        private String lastModifiedBy;
        private String placeDeparture;
        private String createdDate;
        private String travelStatus;
        private String noRealNameTransport;
        private String createdBy;

        public String getNoRealNameRemark() {
            return noRealNameRemark;
        }

        public void setNoRealNameRemark(String noRealNameRemark) {
            this.noRealNameRemark = noRealNameRemark;
        }

        public String getKcTransport() {
            return kcTransport;
        }

        public void setKcTransport(String kcTransport) {
            this.kcTransport = kcTransport;
        }

        public String getStayReimbursementStandard() {
            return stayReimbursementStandard;
        }

        public void setStayReimbursementStandard(String stayReimbursementStandard) {
            this.stayReimbursementStandard = stayReimbursementStandard;
        }

        public String getHgfTransport() {
            return hgfTransport;
        }

        public void setHgfTransport(String hgfTransport) {
            this.hgfTransport = hgfTransport;
        }

        public String getCityTrafficInvoiceAmount() {
            return cityTrafficInvoiceAmount;
        }

        public void setCityTrafficInvoiceAmount(String cityTrafficInvoiceAmount) {
            this.cityTrafficInvoiceAmount = cityTrafficInvoiceAmount;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getMealBusinessTrip() {
            return mealBusinessTrip;
        }

        public void setMealBusinessTrip(String mealBusinessTrip) {
            this.mealBusinessTrip = mealBusinessTrip;
        }

        public boolean isCommit() {
            return commit;
        }

        public void setCommit(boolean commit) {
            this.commit = commit;
        }

        public String getCityTrafficReimbursementAmount() {
            return cityTrafficReimbursementAmount;
        }

        public void setCityTrafficReimbursementAmount(String cityTrafficReimbursementAmount) {
            this.cityTrafficReimbursementAmount = cityTrafficReimbursementAmount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTravelStatusDesp() {
            return travelStatusDesp;
        }

        public void setTravelStatusDesp(String travelStatusDesp) {
            this.travelStatusDesp = travelStatusDesp;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public NormalOperatorBean.OperatorBeanID getCohabitOperator() {
            return cohabitOperator;
        }

        public void setCohabitOperator(NormalOperatorBean.OperatorBeanID cohabitOperator) {
            this.cohabitOperator = cohabitOperator;
        }

        public NormalOperatorBean.OperatorBeanID getOperator() {
            return operator;
        }

        public void setOperator(NormalOperatorBean.OperatorBeanID operator) {
            this.operator = operator;
        }

        public String getCityTrafficReimbursementStandard() {
            return cityTrafficReimbursementStandard;
        }

        public void setCityTrafficReimbursementStandard(String cityTrafficReimbursementStandard) {
            this.cityTrafficReimbursementStandard = cityTrafficReimbursementStandard;
        }

        public Long getRequestId() {
            return requestId;
        }

        public void setRequestId(Long requestId) {
            this.requestId = requestId;
        }

        public String getHotel() {
            return hotel;
        }

        public void setHotel(String hotel) {
            this.hotel = hotel;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPlannedBusinessTrip() {
            return plannedBusinessTrip;
        }

        public void setPlannedBusinessTrip(String plannedBusinessTrip) {
            this.plannedBusinessTrip = plannedBusinessTrip;
        }

        public String getStayInvoiceAmount() {
            return stayInvoiceAmount;
        }

        public void setStayInvoiceAmount(String stayInvoiceAmount) {
            this.stayInvoiceAmount = stayInvoiceAmount;
        }

        public String getOtherExpenses() {
            return otherExpenses;
        }

        public void setOtherExpenses(String otherExpenses) {
            this.otherExpenses = otherExpenses;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getStayReimbursementAmount() {
            return stayReimbursementAmount;
        }

        public void setStayReimbursementAmount(String stayReimbursementAmount) {
            this.stayReimbursementAmount = stayReimbursementAmount;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getPlaceDeparture() {
            return placeDeparture;
        }

        public void setPlaceDeparture(String placeDeparture) {
            this.placeDeparture = placeDeparture;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getTravelStatus() {
            return travelStatus;
        }

        public void setTravelStatus(String travelStatus) {
            this.travelStatus = travelStatus;
        }

        public String getNoRealNameTransport() {
            return noRealNameTransport;
        }

        public void setNoRealNameTransport(String noRealNameTransport) {
            this.noRealNameTransport = noRealNameTransport;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getTravelDate() {
            return travelDate;
        }

        public void setTravelDate(String travelDate) {
            this.travelDate = travelDate;
        }

        public String getPlaceArrival() {
            return placeArrival;
        }

        public void setPlaceArrival(String placeArrival) {
            this.placeArrival = placeArrival;
        }

        public String getReimbursementCity() {
            return reimbursementCity;
        }

        public void setReimbursementCity(String reimbursementCity) {
            this.reimbursementCity = reimbursementCity;
        }

        public String getMealAllowance() {
            return mealAllowance;
        }

        public void setMealAllowance(String mealAllowance) {
            this.mealAllowance = mealAllowance;
        }

        private String travelDate;
        private String placeArrival;
        private String reimbursementCity;
        private String mealAllowance;


    }
}
