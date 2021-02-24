package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CustomerBusinessReceiptBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/3/11 14:44
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/3/11 1.00 初始版本
 * ****************************************************************
 */
public class CustomerBusinessReceiptBean {


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
         * property : lastModifiedDate
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


        private String receptionStatusKey;
        private String meetingStandard;
        private boolean needPlanTicket;
        private String endDate;
        private String receptionStatusValue;
        private String mealStandard;
        private String hotelLevelKey;
        private String planTicketFinalPlan;
        private String contactTel;
        private String receptionLevelDesp;
        private String meetingFinalPlan;
        private String timeSlotCar;
        private Object giftNum;
        private String planTicketStandard;
        private long id;
        private String mealFinalPlan;
        private String carOtherAppeal;
        private String receptionCountryTypeDesp;
        private String visitingReason;
        private String meetingOtherAppeal;
        private String predetermineMeetingRoom;
        private String contactName;
        private String mealOtherAppeal;
        private String giftStandard;
        private String giftOtherAppeal;
        private Object approvalNodeDesp;
        private String applyRemark;
        private String mealArea;
        private String workShopOtherAppeal;
        private String receptionLevel;
        private String visitingObjects;
        private String hotelOtherAppeal;
        private String hotelLevelValue;
        private String hotelStandard;
        private String receptionCompanyDesp;
        private String title;
        private boolean meetingNameplate;
        private Object meetingData;
        private String hotelFinalPlan;
        private MemberBeanID member;
        private String approvalStatusDesp;
        private boolean needCar;
        private String receptionCountryType;
        private boolean needHotel;
        private String workShopStandard;
        private boolean needMeetingRoom;
        private String explainMeeting;
        private String approvalStatus;
        private String meetingResource;
        private String carStandard;
        private String lastModifiedDate;
        private String lastModifiedBy;
        private String carFinalPlan;
        private String planTicketOtherAppeal;
        private Object approvalNodeIdentifier;
        private String receptionObjects;
        private boolean needMeal;

        public String getReceptionStatusKey() {
            return receptionStatusKey;
        }

        public void setReceptionStatusKey(String receptionStatusKey) {
            this.receptionStatusKey = receptionStatusKey;
        }

        public String getMeetingStandard() {
            return meetingStandard;
        }

        public void setMeetingStandard(String meetingStandard) {
            this.meetingStandard = meetingStandard;
        }

        public boolean isNeedPlanTicket() {
            return needPlanTicket;
        }

        public void setNeedPlanTicket(boolean needPlanTicket) {
            this.needPlanTicket = needPlanTicket;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getReceptionStatusValue() {
            return receptionStatusValue;
        }

        public void setReceptionStatusValue(String receptionStatusValue) {
            this.receptionStatusValue = receptionStatusValue;
        }

        public String getMealStandard() {
            return mealStandard;
        }

        public void setMealStandard(String mealStandard) {
            this.mealStandard = mealStandard;
        }

        public String getHotelLevelKey() {
            return hotelLevelKey;
        }

        public void setHotelLevelKey(String hotelLevelKey) {
            this.hotelLevelKey = hotelLevelKey;
        }

        public String getPlanTicketFinalPlan() {
            return planTicketFinalPlan;
        }

        public void setPlanTicketFinalPlan(String planTicketFinalPlan) {
            this.planTicketFinalPlan = planTicketFinalPlan;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getReceptionLevelDesp() {
            return receptionLevelDesp;
        }

        public void setReceptionLevelDesp(String receptionLevelDesp) {
            this.receptionLevelDesp = receptionLevelDesp;
        }

        public String getMeetingFinalPlan() {
            return meetingFinalPlan;
        }

        public void setMeetingFinalPlan(String meetingFinalPlan) {
            this.meetingFinalPlan = meetingFinalPlan;
        }

        public String getTimeSlotCar() {
            return timeSlotCar;
        }

        public void setTimeSlotCar(String timeSlotCar) {
            this.timeSlotCar = timeSlotCar;
        }

        public Object getGiftNum() {
            return giftNum;
        }

        public void setGiftNum(Object giftNum) {
            this.giftNum = giftNum;
        }

        public String getPlanTicketStandard() {
            return planTicketStandard;
        }

        public void setPlanTicketStandard(String planTicketStandard) {
            this.planTicketStandard = planTicketStandard;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getMealFinalPlan() {
            return mealFinalPlan;
        }

        public void setMealFinalPlan(String mealFinalPlan) {
            this.mealFinalPlan = mealFinalPlan;
        }

        public String getCarOtherAppeal() {
            return carOtherAppeal;
        }

        public void setCarOtherAppeal(String carOtherAppeal) {
            this.carOtherAppeal = carOtherAppeal;
        }

        public String getReceptionCountryTypeDesp() {
            return receptionCountryTypeDesp;
        }

        public void setReceptionCountryTypeDesp(String receptionCountryTypeDesp) {
            this.receptionCountryTypeDesp = receptionCountryTypeDesp;
        }

        public String getVisitingReason() {
            return visitingReason;
        }

        public void setVisitingReason(String visitingReason) {
            this.visitingReason = visitingReason;
        }

        public String getMeetingOtherAppeal() {
            return meetingOtherAppeal;
        }

        public void setMeetingOtherAppeal(String meetingOtherAppeal) {
            this.meetingOtherAppeal = meetingOtherAppeal;
        }

        public String getPredetermineMeetingRoom() {
            return predetermineMeetingRoom;
        }

        public void setPredetermineMeetingRoom(String predetermineMeetingRoom) {
            this.predetermineMeetingRoom = predetermineMeetingRoom;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getMealOtherAppeal() {
            return mealOtherAppeal;
        }

        public void setMealOtherAppeal(String mealOtherAppeal) {
            this.mealOtherAppeal = mealOtherAppeal;
        }

        public String getGiftStandard() {
            return giftStandard;
        }

        public void setGiftStandard(String giftStandard) {
            this.giftStandard = giftStandard;
        }

        public String getGiftOtherAppeal() {
            return giftOtherAppeal;
        }

        public void setGiftOtherAppeal(String giftOtherAppeal) {
            this.giftOtherAppeal = giftOtherAppeal;
        }

        public Object getApprovalNodeDesp() {
            return approvalNodeDesp;
        }

        public void setApprovalNodeDesp(Object approvalNodeDesp) {
            this.approvalNodeDesp = approvalNodeDesp;
        }

        public String getApplyRemark() {
            return applyRemark;
        }

        public void setApplyRemark(String applyRemark) {
            this.applyRemark = applyRemark;
        }

        public String getMealArea() {
            return mealArea;
        }

        public void setMealArea(String mealArea) {
            this.mealArea = mealArea;
        }

        public String getWorkShopOtherAppeal() {
            return workShopOtherAppeal;
        }

        public void setWorkShopOtherAppeal(String workShopOtherAppeal) {
            this.workShopOtherAppeal = workShopOtherAppeal;
        }

        public String getReceptionLevel() {
            return receptionLevel;
        }

        public void setReceptionLevel(String receptionLevel) {
            this.receptionLevel = receptionLevel;
        }

        public String getVisitingObjects() {
            return visitingObjects;
        }

        public void setVisitingObjects(String visitingObjects) {
            this.visitingObjects = visitingObjects;
        }

        public String getHotelOtherAppeal() {
            return hotelOtherAppeal;
        }

        public void setHotelOtherAppeal(String hotelOtherAppeal) {
            this.hotelOtherAppeal = hotelOtherAppeal;
        }

        public String getHotelLevelValue() {
            return hotelLevelValue;
        }

        public void setHotelLevelValue(String hotelLevelValue) {
            this.hotelLevelValue = hotelLevelValue;
        }

        public String getHotelStandard() {
            return hotelStandard;
        }

        public void setHotelStandard(String hotelStandard) {
            this.hotelStandard = hotelStandard;
        }

        public String getReceptionCompanyDesp() {
            return receptionCompanyDesp;
        }

        public void setReceptionCompanyDesp(String receptionCompanyDesp) {
            this.receptionCompanyDesp = receptionCompanyDesp;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMeetingNameplate() {
            return meetingNameplate;
        }

        public void setMeetingNameplate(boolean meetingNameplate) {
            this.meetingNameplate = meetingNameplate;
        }

        public Object getMeetingData() {
            return meetingData;
        }

        public void setMeetingData(Object meetingData) {
            this.meetingData = meetingData;
        }

        public String getHotelFinalPlan() {
            return hotelFinalPlan;
        }

        public void setHotelFinalPlan(String hotelFinalPlan) {
            this.hotelFinalPlan = hotelFinalPlan;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getApprovalStatusDesp() {
            return approvalStatusDesp;
        }

        public void setApprovalStatusDesp(String approvalStatusDesp) {
            this.approvalStatusDesp = approvalStatusDesp;
        }

        public boolean isNeedCar() {
            return needCar;
        }

        public void setNeedCar(boolean needCar) {
            this.needCar = needCar;
        }

        public String getReceptionCountryType() {
            return receptionCountryType;
        }

        public void setReceptionCountryType(String receptionCountryType) {
            this.receptionCountryType = receptionCountryType;
        }

        public boolean isNeedHotel() {
            return needHotel;
        }

        public void setNeedHotel(boolean needHotel) {
            this.needHotel = needHotel;
        }

        public String getWorkShopStandard() {
            return workShopStandard;
        }

        public void setWorkShopStandard(String workShopStandard) {
            this.workShopStandard = workShopStandard;
        }

        public boolean isNeedMeetingRoom() {
            return needMeetingRoom;
        }

        public void setNeedMeetingRoom(boolean needMeetingRoom) {
            this.needMeetingRoom = needMeetingRoom;
        }

        public String getExplainMeeting() {
            return explainMeeting;
        }

        public void setExplainMeeting(String explainMeeting) {
            this.explainMeeting = explainMeeting;
        }

        public String getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(String approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public String getMeetingResource() {
            return meetingResource;
        }

        public void setMeetingResource(String meetingResource) {
            this.meetingResource = meetingResource;
        }

        public String getCarStandard() {
            return carStandard;
        }

        public void setCarStandard(String carStandard) {
            this.carStandard = carStandard;
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

        public String getCarFinalPlan() {
            return carFinalPlan;
        }

        public void setCarFinalPlan(String carFinalPlan) {
            this.carFinalPlan = carFinalPlan;
        }

        public String getPlanTicketOtherAppeal() {
            return planTicketOtherAppeal;
        }

        public void setPlanTicketOtherAppeal(String planTicketOtherAppeal) {
            this.planTicketOtherAppeal = planTicketOtherAppeal;
        }

        public Object getApprovalNodeIdentifier() {
            return approvalNodeIdentifier;
        }

        public void setApprovalNodeIdentifier(Object approvalNodeIdentifier) {
            this.approvalNodeIdentifier = approvalNodeIdentifier;
        }

        public String getReceptionObjects() {
            return receptionObjects;
        }

        public void setReceptionObjects(String receptionObjects) {
            this.receptionObjects = receptionObjects;
        }

        public boolean isNeedMeal() {
            return needMeal;
        }

        public void setNeedMeal(boolean needMeal) {
            this.needMeal = needMeal;
        }

        public String getReceptionCompany() {
            return receptionCompany;
        }

        public void setReceptionCompany(String receptionCompany) {
            this.receptionCompany = receptionCompany;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getWorkShopFinalPlan() {
            return workShopFinalPlan;
        }

        public void setWorkShopFinalPlan(String workShopFinalPlan) {
            this.workShopFinalPlan = workShopFinalPlan;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public boolean isNeedGift() {
            return needGift;
        }

        public void setNeedGift(boolean needGift) {
            this.needGift = needGift;
        }

        public boolean isNeedVisitWorkShop() {
            return needVisitWorkShop;
        }

        public void setNeedVisitWorkShop(boolean needVisitWorkShop) {
            this.needVisitWorkShop = needVisitWorkShop;
        }

        public String getGiftFinalPlan() {
            return giftFinalPlan;
        }

        public void setGiftFinalPlan(String giftFinalPlan) {
            this.giftFinalPlan = giftFinalPlan;
        }

        private String receptionCompany;
        private String beginDate;
        private String createdDate;
        private String workShopFinalPlan;
        private String createdBy;
        private boolean needGift;
        private boolean needVisitWorkShop;
        private String giftFinalPlan;



    }
}
