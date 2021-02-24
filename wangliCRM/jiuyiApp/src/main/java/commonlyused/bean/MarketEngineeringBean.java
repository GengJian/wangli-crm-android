package commonlyused.bean;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.Objects;

import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称:MarketEngineeringBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/4/19 10:52
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/4/19 1.00 初始版本
 * ****************************************************************
 */
public class MarketEngineeringBean {


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
        public String getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(String handleStatus) {
            this.handleStatus = handleStatus;
        }

        private String handleStatus;

        private String date;
        public String getWorkPlanDate() {
            return workPlanDate;
        }

        public void setWorkPlanDate(String workPlanDate) {
            this.workPlanDate = workPlanDate;
        }

        private String workPlanDate;
        private String cityNumber;
        private double cumulativeShipments;
        private double actualShipment;
        private String remark;
        private double ompletionRate;
        private NormalOperatorBean.OperatorBeanID operator;
        private String cityName;
        private String areaName;
        private String provinceNumber;
        private Long id;
        private String lastModifiedDate;
        private double expectVisit;
        private String lastModifiedBy;
        private String areaNumber;
        private double salesTarget;
        private String followUpReportingProject;
        private double actualVisit;
        private String createdDate;
        private String createdBy;
        private String followUpUnfulfilledProject;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        private String province;//省份

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCityNumber() {
            return cityNumber;
        }

        public void setCityNumber(String cityNumber) {
            this.cityNumber = cityNumber;
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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public String getFollowUpReportingProject() {
            return followUpReportingProject;
        }

        public void setFollowUpReportingProject(String followUpReportingProject) {
            this.followUpReportingProject = followUpReportingProject;
        }

        public double getActualVisit() {
            return actualVisit;
        }

        public void setActualVisit(double actualVisit) {
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

        public List<UnfulfilledProjectsBean> getUnfulfilledProjects() {
            return unfulfilledProjects;
        }

        public void setUnfulfilledProjects(List<UnfulfilledProjectsBean> unfulfilledProjects) {
            this.unfulfilledProjects = unfulfilledProjects;
        }

        public List<MarketEngineeringItemsBean> getMarketEngineeringItems() {
            return marketEngineeringItems;
        }

        public void setMarketEngineeringItems(List<MarketEngineeringItemsBean> marketEngineeringItems) {
            this.marketEngineeringItems = marketEngineeringItems;
        }

        public List<ReportingProjectsBean> getReportingProjects() {
            return reportingProjects;
        }

        public void setReportingProjects(List<ReportingProjectsBean> reportingProjects) {
            this.reportingProjects = reportingProjects;
        }

        private String provinceName;
        private double projectedShipment;
        private List<UnfulfilledProjectsBean> unfulfilledProjects;
        private List<MarketEngineeringItemsBean> marketEngineeringItems;
        private List<ReportingProjectsBean> reportingProjects;





    }
    public static class MarketEngineeringItemsBean implements  Parcelable{
        public MarketEngineeringItemsBean() {
        }

        private String createdDate;

        protected MarketEngineeringItemsBean(Parcel in) {
            createdDate = in.readString();
            lastModifiedDate = in.readString();
            createdBy = in.readString();
            handlingMatters = in.readString();
            lastModifiedBy = in.readString();
            member = in.readParcelable(MemberBeanID.class.getClassLoader());
            id = in.readLong();
            visit = in.readByte() != 0;
        }

        public static final Creator<MarketEngineeringItemsBean> CREATOR = new Creator<MarketEngineeringItemsBean>() {
            @Override
            public MarketEngineeringItemsBean createFromParcel(Parcel in) {
                return new MarketEngineeringItemsBean(in);
            }

            @Override
            public MarketEngineeringItemsBean[] newArray(int size) {
                return new MarketEngineeringItemsBean[size];
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

        public String getHandlingMatters() {
            return handlingMatters;
        }

        public void setHandlingMatters(String handlingMatters) {
            this.handlingMatters = handlingMatters;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public boolean isVisit() {
            return visit;
        }

        public void setVisit(boolean visit) {
            this.visit = visit;
        }

        private String lastModifiedDate;
        private String createdBy;
        private String handlingMatters;
        private String lastModifiedBy;
        private MemberBeanID member;
        private Long id;
        private boolean visit;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(createdDate);
            dest.writeString(lastModifiedDate);
            dest.writeString(createdBy);
            dest.writeString(handlingMatters);
            dest.writeString(lastModifiedBy);
            dest.writeParcelable(member, flags);
            if (id == null) {
                dest.writeByte( (byte) 0 );
            } else {
                dest.writeByte( (byte) 1 );
                dest.writeLong( id );
            }
            dest.writeByte((byte) (visit ? 1 : 0));
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean equals(Object arg0) {
            // TODO Auto-generated method stub
            MarketEngineeringItemsBean p = (MarketEngineeringItemsBean) arg0;
//            return (member.getId()==p.member.getId());
            return Objects.equals(member.getId(), p.getMember().getId());
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public int hashCode() {
            return Objects.hash(member.getId());
        }

    }

    public static class UnfulfilledProjectsBean implements Parcelable  {
        protected UnfulfilledProjectsBean(Parcel in) {
            effective = in.readByte() != 0;
            estimatedOrderTime = in.readString();
            createdDate = in.readString();
            lastModifiedDate = in.readString();
            createdBy = in.readString();
            lastModifiedBy = in.readString();
            reasonsNoPerformance = in.readString();
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readLong();
            }
            engineering = in.readString();
        }

        public static final Creator<UnfulfilledProjectsBean> CREATOR = new Creator<UnfulfilledProjectsBean>() {
            @Override
            public UnfulfilledProjectsBean createFromParcel(Parcel in) {
                return new UnfulfilledProjectsBean( in );
            }

            @Override
            public UnfulfilledProjectsBean[] newArray(int size) {
                return new UnfulfilledProjectsBean[size];
            }
        };

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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEngineering() {
            return engineering;
        }

        public void setEngineering(String engineering) {
            this.engineering = engineering;
        }

        private boolean effective;
        private String estimatedOrderTime;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String reasonsNoPerformance;
        private Long id;
        private String engineering;
        public UnfulfilledProjectsBean() {
        }


        @Override
        public int describeContents() {
            return 0;
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean equals(Object arg0) {
            // TODO Auto-generated method stub
            UnfulfilledProjectsBean p = (UnfulfilledProjectsBean) arg0;
//            return (member.getId()==p.member.getId());
            return Objects.equals(engineering, p.getEngineering());
        }

        @Override
        public void writeToParcel(Parcel dest , int flags) {
            dest.writeByte( (byte) (effective ? 1 : 0) );
            dest.writeString( estimatedOrderTime );
            dest.writeString( createdDate );
            dest.writeString( lastModifiedDate );
            dest.writeString( createdBy );
            dest.writeString( lastModifiedBy );
            dest.writeString( reasonsNoPerformance );
            if (id == null) {
                dest.writeByte( (byte) 0 );
            } else {
                dest.writeByte( (byte) 1 );
                dest.writeLong( id );
            }
            dest.writeString( engineering );
        }
    }


    public static class ReportingProjectsBean implements Parcelable  {
        private boolean followUp;

        protected ReportingProjectsBean(Parcel in) {
            followUp = in.readByte() != 0;
            createdDate = in.readString();
            lastModifiedDate = in.readString();
            createdBy = in.readString();
            followUpResults = in.readString();
            lastModifiedBy = in.readString();
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readLong();
            }
            engineering = in.readString();
        }

        public static final Creator<ReportingProjectsBean> CREATOR = new Creator<ReportingProjectsBean>() {
            @Override
            public ReportingProjectsBean createFromParcel(Parcel in) {
                return new ReportingProjectsBean( in );
            }

            @Override
            public ReportingProjectsBean[] newArray(int size) {
                return new ReportingProjectsBean[size];
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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEngineering() {
            return engineering;
        }

        public void setEngineering(String engineering) {
            this.engineering = engineering;
        }

        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String followUpResults;
        private String lastModifiedBy;
        private Long id;
        private String engineering;

        public ReportingProjectsBean() {
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest , int flags) {
            dest.writeByte( (byte) (followUp ? 1 : 0) );
            dest.writeString( createdDate );
            dest.writeString( lastModifiedDate );
            dest.writeString( createdBy );
            dest.writeString( followUpResults );
            dest.writeString( lastModifiedBy );
            if (id == null) {
                dest.writeByte( (byte) 0 );
            } else {
                dest.writeByte( (byte) 1 );
                dest.writeLong( id );
            }
            dest.writeString( engineering );
        }
    }
}
