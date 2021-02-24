package commonlyused.bean;

import java.util.List;

import customer.entity.AttachmentBean;

/**
 * ****************************************************************
 * 文件名称:MarketWorkPlanBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/8/20 11:30
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/8/20 1.00 初始版本
 * ****************************************************************
 */
public class MarketWorkPlanBean {

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


        private long actualSign;
        private String date;
        private String afterSaleUnprocessed;
        private String cityNumber;
        private String activity;
        private double cumulativeShipments;
        private double actualShipment;
        private String remark;
        private double completionRate;
        private double ompletionRate;
        private NormalOperatorBean.OperatorBeanID operator;
        private String province;
        private String cityName;
        private String areaName;
        private String workPlanDate;
        private String provinceNumber;
        private String handleStatus;
        private boolean finish;
        private Long id;
        private double visitIntentional;
        private String lastModifiedDate;
        private double expectVisit;
        private String lastModifiedBy;
        private double developmentProject;
        private String areaNumber;
        private double salesTarget;
        private long finishVisit;
        private String followUpReportingProject;
        private double signIntention;
        private long actualVisit;
        private String createdDate;
        private String workPlanType;
        private String createdBy;
        private long accumulateVisit;
        private String followUpUnfulfilledProject;
        private String provinceName;
        private String developmentProvince;
        private double projectedShipment;

        public List<AttachmentBean> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<AttachmentBean> attachments) {
            this.attachments = attachments;
        }

        private List<AttachmentBean> attachments;

        public long getActualSign() {
            return actualSign;
        }

        public void setActualSign(long actualSign) {
            this.actualSign = actualSign;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAfterSaleUnprocessed() {
            return afterSaleUnprocessed;
        }

        public void setAfterSaleUnprocessed(String afterSaleUnprocessed) {
            this.afterSaleUnprocessed = afterSaleUnprocessed;
        }

        public String getCityNumber() {
            return cityNumber;
        }

        public void setCityNumber(String cityNumber) {
            this.cityNumber = cityNumber;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

        public double getCumulativeShipments() {
            return cumulativeShipments;
        }

        public void setCumulativeShipments(double cumulativeShipments) {
            this.cumulativeShipments = cumulativeShipments;
        }

        public double getActualShipment() {
            return actualShipment;
        }

        public void setActualShipment(double actualShipment) {
            this.actualShipment = actualShipment;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public double getCompletionRate() {
            return completionRate;
        }

        public void setCompletionRate(double completionRate) {
            this.completionRate = completionRate;
        }

        public double getOmpletionRate() {
            return ompletionRate;
        }

        public void setOmpletionRate(double ompletionRate) {
            this.ompletionRate = ompletionRate;
        }

        public NormalOperatorBean.OperatorBeanID getOperator() {
            return operator;
        }

        public void setOperator(NormalOperatorBean.OperatorBeanID operator) {
            this.operator = operator;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getWorkPlanDate() {
            return workPlanDate;
        }

        public void setWorkPlanDate(String workPlanDate) {
            this.workPlanDate = workPlanDate;
        }

        public String getProvinceNumber() {
            return provinceNumber;
        }

        public void setProvinceNumber(String provinceNumber) {
            this.provinceNumber = provinceNumber;
        }

        public String getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(String handleStatus) {
            this.handleStatus = handleStatus;
        }

        public boolean isFinish() {
            return finish;
        }

        public void setFinish(boolean finish) {
            this.finish = finish;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public double getVisitIntentional() {
            return visitIntentional;
        }

        public void setVisitIntentional(double visitIntentional) {
            this.visitIntentional = visitIntentional;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public double getExpectVisit() {
            return expectVisit;
        }

        public void setExpectVisit(double expectVisit) {
            this.expectVisit = expectVisit;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public double getDevelopmentProject() {
            return developmentProject;
        }

        public void setDevelopmentProject(double developmentProject) {
            this.developmentProject = developmentProject;
        }

        public String getAreaNumber() {
            return areaNumber;
        }

        public void setAreaNumber(String areaNumber) {
            this.areaNumber = areaNumber;
        }

        public double getSalesTarget() {
            return salesTarget;
        }

        public void setSalesTarget(double salesTarget) {
            this.salesTarget = salesTarget;
        }

        public long getFinishVisit() {
            return finishVisit;
        }

        public void setFinishVisit(long finishVisit) {
            this.finishVisit = finishVisit;
        }

        public String getFollowUpReportingProject() {
            return followUpReportingProject;
        }

        public void setFollowUpReportingProject(String followUpReportingProject) {
            this.followUpReportingProject = followUpReportingProject;
        }

        public double getSignIntention() {
            return signIntention;
        }

        public void setSignIntention(double signIntention) {
            this.signIntention = signIntention;
        }

        public long getActualVisit() {
            return actualVisit;
        }

        public void setActualVisit(long actualVisit) {
            this.actualVisit = actualVisit;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getWorkPlanType() {
            return workPlanType;
        }

        public void setWorkPlanType(String workPlanType) {
            this.workPlanType = workPlanType;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public long getAccumulateVisit() {
            return accumulateVisit;
        }

        public void setAccumulateVisit(long accumulateVisit) {
            this.accumulateVisit = accumulateVisit;
        }

        public String getFollowUpUnfulfilledProject() {
            return followUpUnfulfilledProject;
        }

        public void setFollowUpUnfulfilledProject(String followUpUnfulfilledProject) {
            this.followUpUnfulfilledProject = followUpUnfulfilledProject;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getDevelopmentProvince() {
            return developmentProvince;
        }

        public void setDevelopmentProvince(String developmentProvince) {
            this.developmentProvince = developmentProvince;
        }

        public double getProjectedShipment() {
            return projectedShipment;
        }

        public void setProjectedShipment(double projectedShipment) {
            this.projectedShipment = projectedShipment;
        }

        public List<MarketHuaJueBean.HuaJueItemsBean> getWorkPlanItems() {
            return workPlanItems;
        }

        public void setWorkPlanItems(List<MarketHuaJueBean.HuaJueItemsBean> workPlanItems) {
            this.workPlanItems = workPlanItems;
        }

        public List<MarketHuaJueBean.HjReportingProjectsBean> getWorkPlanReportingProjects() {
            return workPlanReportingProjects;
        }

        public void setWorkPlanReportingProjects(List<MarketHuaJueBean.HjReportingProjectsBean> workPlanReportingProjects) {
            this.workPlanReportingProjects = workPlanReportingProjects;
        }

        public List<ChannelDevelopBean.VisitIntentionsBean> getWorkPlanVisitIntentions() {
            return workPlanVisitIntentions;
        }

        public void setWorkPlanVisitIntentions(List<ChannelDevelopBean.VisitIntentionsBean> workPlanVisitIntentions) {
            this.workPlanVisitIntentions = workPlanVisitIntentions;
        }

        public List<ChannelDevelopBean.SignIntentionsBean> getWorkPlanSignIntentions() {
            return workPlanSignIntentions;
        }

        public void setWorkPlanSignIntentions(List<ChannelDevelopBean.SignIntentionsBean> workPlanSignIntentions) {
            this.workPlanSignIntentions = workPlanSignIntentions;
        }

        public List<MarketHuaJueBean.HjUnfulfilledProjectsBean> getWorkPlanUnfulfilledProjects() {
            return workPlanUnfulfilledProjects;
        }

        public void setWorkPlanUnfulfilledProjects(List<MarketHuaJueBean.HjUnfulfilledProjectsBean> workPlanUnfulfilledProjects) {
            this.workPlanUnfulfilledProjects = workPlanUnfulfilledProjects;
        }

        private List<MarketHuaJueBean.HuaJueItemsBean> workPlanItems;
        private List<MarketHuaJueBean.HjReportingProjectsBean> workPlanReportingProjects;
        private List<ChannelDevelopBean.VisitIntentionsBean> workPlanVisitIntentions;
        private List<ChannelDevelopBean.SignIntentionsBean> workPlanSignIntentions;
        private List<MarketHuaJueBean.HjUnfulfilledProjectsBean> workPlanUnfulfilledProjects;






    }
}
