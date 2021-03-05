package commonlyused.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.jiuyi.model.DictResultBean;

import java.util.List;

import customer.entity.AttachmentBean;
import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称:MarketNengChengBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/5/8 18:31
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/5/8 1.00 初始版本
 * ****************************************************************
 */
public class MarketNengChengBean {


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
         * property : date
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
        public String getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(String handleStatus) {
            this.handleStatus = handleStatus;
        }

        private String handleStatus;
        private long actualSign;
        private String date;
        public String getWorkPlanDate() {
            return workPlanDate;
        }

        public void setWorkPlanDate(String workPlanDate) {
            this.workPlanDate = workPlanDate;
        }

        private String workPlanDate;
        private String afterSaleUnprocessed;
        private String cityNumber;
        private String activity;
        private double cumulativeShipments;
        private double actualShipment;
        private String remark;
        private String remarkCompletion;//其他事项完成情况
        private double completionRate;
        private double ompletionRate;
        private NormalOperatorBean.OperatorBeanID operator;
        private String cityName;
        private String areaName;
        private String provinceNumber;
        private boolean finish;
        private Long id;
        private long visitIntentional;
        private String lastModifiedDate;
        private long expectVisit;
        private String lastModifiedBy;
        private double developmentProject;
        private String areaNumber;
        private double salesTarget;
        private long finishVisit;
        private String followUpReportingProject;
        private long signIntention;
        private long actualVisit;
        private String createdDate;
        private String createdBy;
        private long accumulateVisit;
        private String followUpUnfulfilledProject;
        private String provinceName;
        private double projectedShipment;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getDevelopmentProvince() {
            return developmentProvince;
        }

        public void setDevelopmentProvince(String developmentProvince) {
            this.developmentProvince = developmentProvince;
        }

        private String province;
        private String developmentProvince;

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

        public String getRemarkCompletion() {
            return remarkCompletion;
        }

        public void setRemarkCompletion(String remarkCompletion) {
            this.remarkCompletion = remarkCompletion;
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

        public String getProvinceNumber() {
            return provinceNumber;
        }

        public void setProvinceNumber(String provinceNumber) {
            this.provinceNumber = provinceNumber;
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

        public long getVisitIntentional() {
            return visitIntentional;
        }

        public void setVisitIntentional(long visitIntentional) {
            this.visitIntentional = visitIntentional;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public long getExpectVisit() {
            return expectVisit;
        }

        public void setExpectVisit(long expectVisit) {
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

        public long getSignIntention() {
            return signIntention;
        }

        public void setSignIntention(long signIntention) {
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

        public double getProjectedShipment() {
            return projectedShipment;
        }

        public void setProjectedShipment(double projectedShipment) {
            this.projectedShipment = projectedShipment;
        }




        public List<MarketHuaJueBean.HjUnfulfilledProjectsBean> getNcUnfulfilledProjects() {
            return ncUnfulfilledProjects;
        }

        public void setNcUnfulfilledProjects(List<MarketHuaJueBean.HjUnfulfilledProjectsBean> ncUnfulfilledProjects) {
            this.ncUnfulfilledProjects = ncUnfulfilledProjects;
        }

        public List<MarketHuaJueBean.HjReportingProjectsBean> getNcReportingProjects() {
            return ncReportingProjects;
        }

        public void setNcReportingProjects(List<MarketHuaJueBean.HjReportingProjectsBean> ncReportingProjects) {
            this.ncReportingProjects = ncReportingProjects;
        }

        public List<MarketHuaJueBean.HuaJueItemsBean> getNengChengItems() {
            return nengChengItems;
        }

        public void setNengChengItems(List<MarketHuaJueBean.HuaJueItemsBean> nengChengItems) {
            this.nengChengItems = nengChengItems;
        }

        private List<MarketHuaJueBean.HjUnfulfilledProjectsBean> ncUnfulfilledProjects;

        public List<ChannelDevelopBean.VisitIntentionsBean> getNcVisitIntentions() {
            return ncVisitIntentions;
        }

        public void setNcVisitIntentions(List<ChannelDevelopBean.VisitIntentionsBean> ncVisitIntentions) {
            this.ncVisitIntentions = ncVisitIntentions;
        }

        public List<ChannelDevelopBean.SignIntentionsBean> getNcSignIntentions() {
            return ncSignIntentions;
        }

        public void setNcSignIntentions(List<ChannelDevelopBean.SignIntentionsBean> ncSignIntentions) {
            this.ncSignIntentions = ncSignIntentions;
        }

        private List<ChannelDevelopBean.VisitIntentionsBean> ncVisitIntentions;
        private List<ChannelDevelopBean.SignIntentionsBean> ncSignIntentions;
        private List<MarketHuaJueBean.HjReportingProjectsBean> ncReportingProjects;
        private List<MarketHuaJueBean.HuaJueItemsBean> nengChengItems;
//        private List<ChannelDevelopBean.VisitIntentionsBean> visitIntentions;
//
//        public List<ChannelDevelopBean.VisitIntentionsBean> getVisitIntentions() {
//            return visitIntentions;
//        }
//
//        public void setVisitIntentions(List<ChannelDevelopBean.VisitIntentionsBean> visitIntentions) {
//            this.visitIntentions = visitIntentions;
//        }
//
//        public List<ChannelDevelopBean.SignIntentionsBean> getSignIntentions() {
//            return signIntentions;
//        }
//
//        public void setSignIntentions(List<ChannelDevelopBean.SignIntentionsBean> signIntentions) {
//            this.signIntentions = signIntentions;
//        }
//
//        private List<ChannelDevelopBean.SignIntentionsBean> signIntentions;


        public static class NcUnfulfilledProjectsBean {

            private boolean effective;
            private String estimatedOrderTime;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String reasonsNoPerformance;
            private long id;
            private String engineering;

            public boolean isEffective() {
                return effective;
            }

            public void setEffective(boolean effective) {
                this.effective = effective;
            }

            public String getEstimatedOrderTime() {
                return estimatedOrderTime;
            }

            public void setEstimatedOrderTime(String estimatedOrderTime) {
                this.estimatedOrderTime = estimatedOrderTime;
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

            public String getReasonsNoPerformance() {
                return reasonsNoPerformance;
            }

            public void setReasonsNoPerformance(String reasonsNoPerformance) {
                this.reasonsNoPerformance = reasonsNoPerformance;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }


            public String getEngineering() {
                return engineering;
            }

            public void setEngineering(String engineering) {
                this.engineering = engineering;
            }
        }
    }
        public static class NcVisitIntentionsBean implements Parcelable {
            /**
             * createdDate : 2019-05-08 18:36:56
             * managementBrand : b
             * lastModifiedDate : 2019-05-08 18:36:56
             * createdBy : 管理员
             * annualSalesVolume : 1
             * lastModifiedBy : 管理员
             * member : a
             * id : 69
             * sort : 10
             * visit : true
             * cooperationIntention : c
             */

            private String createdDate;
            private String managementBrand;
            private String lastModifiedDate;
            private String createdBy;
            private double annualSalesVolume;
            private String lastModifiedBy;
            private String member;
            private long id;
            private boolean visit;
            private String cooperationIntention;

            protected NcVisitIntentionsBean(Parcel in) {
                createdDate = in.readString();
                managementBrand = in.readString();
                lastModifiedDate = in.readString();
                createdBy = in.readString();
                annualSalesVolume = in.readDouble();
                lastModifiedBy = in.readString();
                member = in.readString();
                id = in.readLong();
                visit = in.readByte() != 0;
                cooperationIntention = in.readString();
            }

            public static final Creator<NcVisitIntentionsBean> CREATOR = new Creator<NcVisitIntentionsBean>() {
                @Override
                public NcVisitIntentionsBean createFromParcel(Parcel in) {
                    return new NcVisitIntentionsBean(in);
                }

                @Override
                public NcVisitIntentionsBean[] newArray(int size) {
                    return new NcVisitIntentionsBean[size];
                }
            };

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getManagementBrand() {
                return managementBrand;
            }

            public void setManagementBrand(String managementBrand) {
                this.managementBrand = managementBrand;
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

            public double getAnnualSalesVolume() {
                return annualSalesVolume;
            }

            public void setAnnualSalesVolume(int annualSalesVolume) {
                this.annualSalesVolume = annualSalesVolume;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public String getMember() {
                return member;
            }

            public void setMember(String member) {
                this.member = member;
            }

            public double getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }


            public boolean isVisit() {
                return visit;
            }

            public void setVisit(boolean visit) {
                this.visit = visit;
            }

            public String getCooperationIntention() {
                return cooperationIntention;
            }

            public void setCooperationIntention(String cooperationIntention) {
                this.cooperationIntention = cooperationIntention;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(createdDate);
                dest.writeString(managementBrand);
                dest.writeString(lastModifiedDate);
                dest.writeString(createdBy);
                dest.writeDouble(annualSalesVolume);
                dest.writeString(lastModifiedBy);
                dest.writeString(member);
                dest.writeLong(id);
                dest.writeByte((byte) (visit ? 1 : 0));
                dest.writeString(cooperationIntention);
            }
        }

        public static class NcSignIntentionsBean implements Parcelable{
            /**
             * createdDate : 2019-05-08 18:36:56
             * lastModifiedDate : 2019-05-08 18:36:56
             * createdBy : 管理员
             * lastModifiedBy : 管理员
             * member : d
             * sign : true
             * id : 20
             * sort : 10
             */

            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String member;
            private boolean sign;
            private long id;

            protected NcSignIntentionsBean(Parcel in) {
                createdDate = in.readString();
                lastModifiedDate = in.readString();
                createdBy = in.readString();
                lastModifiedBy = in.readString();
                member = in.readString();
                sign = in.readByte() != 0;
                id = in.readLong();
            }

            public static final Creator<NcSignIntentionsBean> CREATOR = new Creator<NcSignIntentionsBean>() {
                @Override
                public NcSignIntentionsBean createFromParcel(Parcel in) {
                    return new NcSignIntentionsBean(in);
                }

                @Override
                public NcSignIntentionsBean[] newArray(int size) {
                    return new NcSignIntentionsBean[size];
                }
            };

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

            public String getMember() {
                return member;
            }

            public void setMember(String member) {
                this.member = member;
            }

            public boolean isSign() {
                return sign;
            }

            public void setSign(boolean sign) {
                this.sign = sign;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(createdDate);
                dest.writeString(lastModifiedDate);
                dest.writeString(createdBy);
                dest.writeString(lastModifiedBy);
                dest.writeString(member);
                dest.writeByte((byte) (sign ? 1 : 0));
                dest.writeLong(id);
            }
        }

        public static class NcReportingProjectsBean implements Parcelable {
            /**
             * followUp : true
             * createdDate : 2019-05-08 18:36:56
             * lastModifiedDate : 2019-05-08 18:36:56
             * createdBy : 管理员
             * followUpResults : a
             * lastModifiedBy : 管理员
             * id : 33
             * sort : 10
             * engineering : a
             */

            private boolean followUp;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String followUpResults;
            private String lastModifiedBy;
            private long id;
            private String engineering;

            protected NcReportingProjectsBean(Parcel in) {
                followUp = in.readByte() != 0;
                createdDate = in.readString();
                lastModifiedDate = in.readString();
                createdBy = in.readString();
                followUpResults = in.readString();
                lastModifiedBy = in.readString();
                id = in.readLong();
                engineering = in.readString();
            }

            public static final Creator<NcReportingProjectsBean> CREATOR = new Creator<NcReportingProjectsBean>() {
                @Override
                public NcReportingProjectsBean createFromParcel(Parcel in) {
                    return new NcReportingProjectsBean(in);
                }

                @Override
                public NcReportingProjectsBean[] newArray(int size) {
                    return new NcReportingProjectsBean[size];
                }
            };

            public boolean isFollowUp() {
                return followUp;
            }

            public void setFollowUp(boolean followUp) {
                this.followUp = followUp;
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

            public String getFollowUpResults() {
                return followUpResults;
            }

            public void setFollowUpResults(String followUpResults) {
                this.followUpResults = followUpResults;
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



            public String getEngineering() {
                return engineering;
            }

            public void setEngineering(String engineering) {
                this.engineering = engineering;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeByte((byte) (followUp ? 1 : 0));
                dest.writeString(createdDate);
                dest.writeString(lastModifiedDate);
                dest.writeString(createdBy);
                dest.writeString(followUpResults);
                dest.writeString(lastModifiedBy);
                dest.writeLong(id);
                dest.writeString(engineering);
            }
        }

        public static class NengChengItemsBean implements Parcelable   {
            /**
             * memberAttribute : {"createdDate":"2019-05-07 15:57:35","lastModifiedDate":"2019-05-07 15:57:35","createdBy":"管理员","lastModifiedBy":"管理员","id":84,"sort":2,"value":"零售","key":"retail"}
             * lastModifiedDate : 2019-05-08 18:36:56
             * engineerProcessingMatters :
             * lastModifiedBy : 管理员
             * sort : 10
             * policyProcessAdvocacy : null
             * content : null
             * createdDate : 2019-05-08 18:36:56
             * createdBy : 管理员
             * monopoly : null
             * member : {"storeNumber":123,"cityNumber":"","bail":null,"operator":{"departmentName":null,"lastModifiedDate":"2019-05-08 17:56:02","avatarUrl":"b9740afb30e4437f880b5393934e81e9","lastModifiedBy":"管理员","sort":10,"title":"管理员","createdDate":"1999-01-10 14:08:40","province":[],"createdBy":"SYSADMIN","name":"管理员","id":215,"department":null,"brand":[],"oaId":"-1","oaCode":"guanliyuan","username":"guanliyuan"},"legalName":"王五","accountBank":"","registeredAddress":"","areaName":"","companyPhone":"","statusKey":"potential","provinceNumber":"","id":295,"brand":"","orgName":"测试2","creditLevelKey":"A","avatarUrl":null,"areaNumber":"","taxNumber":"","marketBailQuentity":"","businessScope":null,"sort":10,"afterSalesNumber":"123","superiorChain":null,"firstDistributor":null,"provinceName":"","cooperationTypeValue":"二级分销商","targetQuantity":null,"assessmentAgreement":null,"crmNumber":"WL20190417003","statusValue":"潜在","growthRate":null,"creditLevelValue":"A","sapNumber":null,"operatorName":"管理员","cooperationTypeKey":"two_level_distributor","exclusiveShopNumber":123,"memberLevelValue":"普通","firstDistributorName":null,"cityName":"","registeredCapital":123,"simpleSpell":"cs2","natureManagement":null,"createdOperatorName":"管理员","lastModifiedDate":"2019-04-17 15:45:42","lastModifiedBy":"管理员","abbreviation":null,"marketBail":null,"createdDate":"2019-04-17 15:45:42","createdBy":"管理员","companyAddress":"","faxNumber":"123","memberLevelKey":"ordinary","annualTargetCompletionRate":null}
             * comparativeSales : null
             * id : 55
             * visit : true
             * complete : null
             * train : null
             */

            private DictResultBean.ContentBean memberAttribute;
            private String lastModifiedDate;
            private String engineerProcessingMatters;
            private String lastModifiedBy;
            private boolean policyProcessAdvocacy;
            private String content;
            private String createdDate;
            private String createdBy;
            private boolean monopoly;

            protected NengChengItemsBean(Parcel in) {
                memberAttribute = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
                lastModifiedDate = in.readString();
                engineerProcessingMatters = in.readString();
                lastModifiedBy = in.readString();
                policyProcessAdvocacy = in.readByte() != 0;
                content = in.readString();
                createdDate = in.readString();
                createdBy = in.readString();
                monopoly = in.readByte() != 0;
                attachments = in.createTypedArrayList(AttachmentBean.CREATOR);
                attachmentsTwo = in.createTypedArrayList(AttachmentBean.CREATOR);
                member = in.readParcelable(MemberBeanID.class.getClassLoader());
                comparativeSales = in.readByte() != 0;
                id = in.readLong();
                visit = in.readByte() != 0;
                complete = in.readByte() != 0;
                train = in.readByte() != 0;
            }

            public static final Creator<NengChengItemsBean> CREATOR = new Creator<NengChengItemsBean>() {
                @Override
                public NengChengItemsBean createFromParcel(Parcel in) {
                    return new NengChengItemsBean(in);
                }

                @Override
                public NengChengItemsBean[] newArray(int size) {
                    return new NengChengItemsBean[size];
                }
            };

            public List<AttachmentBean> getAttachments() {
                return attachments;
            }

            public void setAttachments(List<AttachmentBean> attachments) {
                this.attachments = attachments;
            }

            private List<AttachmentBean> attachments;

            public List<AttachmentBean> getAttachmentsTwo() {
                return attachmentsTwo;
            }

            public void setAttachmentsTwo(List<AttachmentBean> attachmentsTwo) {
                this.attachmentsTwo = attachmentsTwo;
            }

            private List<AttachmentBean> attachmentsTwo;



            public DictResultBean.ContentBean getMemberAttribute() {
                return memberAttribute;
            }

            public void setMemberAttribute(DictResultBean.ContentBean memberAttribute) {
                this.memberAttribute = memberAttribute;
            }

            public String getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public String getEngineerProcessingMatters() {
                return engineerProcessingMatters;
            }

            public void setEngineerProcessingMatters(String engineerProcessingMatters) {
                this.engineerProcessingMatters = engineerProcessingMatters;
            }

            public String getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
            }

            public boolean isPolicyProcessAdvocacy() {
                return policyProcessAdvocacy;
            }

            public void setPolicyProcessAdvocacy(boolean policyProcessAdvocacy) {
                this.policyProcessAdvocacy = policyProcessAdvocacy;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public boolean isMonopoly() {
                return monopoly;
            }

            public void setMonopoly(boolean monopoly) {
                this.monopoly = monopoly;
            }

            public MemberBeanID getMember() {
                return member;
            }

            public void setMember(MemberBeanID member) {
                this.member = member;
            }

            public boolean isComparativeSales() {
                return comparativeSales;
            }

            public void setComparativeSales(boolean comparativeSales) {
                this.comparativeSales = comparativeSales;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public boolean isVisit() {
                return visit;
            }

            public void setVisit(boolean visit) {
                this.visit = visit;
            }

            public boolean isComplete() {
                return complete;
            }

            public void setComplete(boolean complete) {
                this.complete = complete;
            }

            public boolean isTrain() {
                return train;
            }

            public void setTrain(boolean train) {
                this.train = train;
            }

            private MemberBeanID member;
            private boolean comparativeSales;
            private long id;
            private boolean visit;
            private boolean complete;
            private boolean train;


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(memberAttribute, flags);
                dest.writeString(lastModifiedDate);
                dest.writeString(engineerProcessingMatters);
                dest.writeString(lastModifiedBy);
                dest.writeByte((byte) (policyProcessAdvocacy ? 1 : 0));
                dest.writeString(content);
                dest.writeString(createdDate);
                dest.writeString(createdBy);
                dest.writeByte((byte) (monopoly ? 1 : 0));
                dest.writeTypedList(attachments);
                dest.writeTypedList(attachmentsTwo);
                dest.writeParcelable(member, flags);
                dest.writeByte((byte) (comparativeSales ? 1 : 0));
                dest.writeLong(id);
                dest.writeByte((byte) (visit ? 1 : 0));
                dest.writeByte((byte) (complete ? 1 : 0));
                dest.writeByte((byte) (train ? 1 : 0));
            }
        }

}
