package commonlyused.bean;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import com.jiuyi.model.DictResultBean;

import java.util.List;
import java.util.Objects;

import customer.entity.AttachmentBean;
import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称:MarketHuaJueBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/5/8 19:19
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/5/8 1.00 初始版本
 * ****************************************************************
 */
public class MarketHuaJueBean {

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
        public List<AttachmentBean> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<AttachmentBean> attachments) {
            this.attachments = attachments;
        }

        private List<AttachmentBean> attachments;
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
        public String getWorkPlanDate() {
            return workPlanDate;
        }

        public void setWorkPlanDate(String workPlanDate) {
            this.workPlanDate = workPlanDate;
        }

        private String workPlanDate;

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

        public List<HjReportingProjectsBean> getHjReportingProjects() {
            return hjReportingProjects;
        }

        public void setHjReportingProjects(List<HjReportingProjectsBean> hjReportingProjects) {
            this.hjReportingProjects = hjReportingProjects;
        }

        public List<HuaJueItemsBean> getHuaJueItems() {
            return huaJueItems;
        }

        public void setHuaJueItems(List<HuaJueItemsBean> huaJueItems) {
            this.huaJueItems = huaJueItems;
        }

        public List<HjUnfulfilledProjectsBean> getHjUnfulfilledProjects() {
            return hjUnfulfilledProjects;
        }

        public void setHjUnfulfilledProjects(List<HjUnfulfilledProjectsBean> hjUnfulfilledProjects) {
            this.hjUnfulfilledProjects = hjUnfulfilledProjects;
        }

        private String afterSaleUnprocessed;
        private String cityNumber;
        private String activity;
        private double cumulativeShipments;
        private double actualShipment;
        private String remark;
        private String remarkCompletion;//其他事项完成情况

        public String getRemarkCompletion() {
            return remarkCompletion;
        }

        public void setRemarkCompletion(String remarkCompletion) {
            this.remarkCompletion = remarkCompletion;
        }

        private double ompletionRate;
        private NormalOperatorBean.OperatorBeanID operator;
        private String cityName;
        private String areaName;
        private String provinceNumber;
        private boolean finish;
        private Long id;
        private String lastModifiedDate;
        private long expectVisit;
        private String lastModifiedBy;
        private String areaNumber;
        private double salesTarget;
        private String followUpReportingProject;
        private long actualVisit;
        private String createdDate;
        private String createdBy;
        private String followUpUnfulfilledProject;
        private String provinceName;
        private double projectedShipment;
        private List<HjReportingProjectsBean> hjReportingProjects;
        private List<HuaJueItemsBean> huaJueItems;
        private List<HjUnfulfilledProjectsBean> hjUnfulfilledProjects;


        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        private String province;//省份
    }

        public static class HjReportingProjectsBean implements Parcelable {
            /**
             * followUp : false
             * createdDate : 2019-05-08 19:23:16
             * lastModifiedDate : 2019-05-08 19:23:16
             * createdBy : 管理员
             * followUpResults : 111
             * lastModifiedBy : 管理员
             * id : 11
             * sort : 10
             * engineering : 132
             */

            private boolean followUp;
            private String createdDate;
            private String lastModifiedDate;

            protected HjReportingProjectsBean(Parcel in) {
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
                sort = in.readInt();
                engineering = in.readString();
            }

            public static final Creator<HjReportingProjectsBean> CREATOR = new Creator<HjReportingProjectsBean>() {
                @Override
                public HjReportingProjectsBean createFromParcel(Parcel in) {
                    return new HjReportingProjectsBean( in );
                }

                @Override
                public HjReportingProjectsBean[] newArray(int size) {
                    return new HjReportingProjectsBean[size];
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

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getEngineering() {
                return engineering;
            }

            public void setEngineering(String engineering) {
                this.engineering = engineering;
            }

            private String createdBy;
            private String followUpResults;
            private String lastModifiedBy;
            private Long id;
            private int sort;
            private String engineering;

            public HjReportingProjectsBean() {
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
                dest.writeInt( sort );
                dest.writeString( engineering );
            }
        }

        public static class HuaJueItemsBean implements Parcelable{
            public HuaJueItemsBean() {
            }

            /**
             * memberAttribute : {"createdDate":"2019-05-07 15:56:48","lastModifiedDate":"2019-05-07 15:56:48","createdBy":"管理员","lastModifiedBy":"管理员","id":83,"sort":1,"value":"工程","key":"project"}
             * lastModifiedDate : 2019-05-08 19:23:16
             * engineerProcessingMatters :
             * lastModifiedBy : 管理员
             * sort : 10

             * policyProcessAdvocacy : null
             * content : null
             * createdDate : 2019-05-08 19:23:16
             * createdBy : 管理员
             * monopoly : null
             * member : {"storeNumber":123,"cityNumber":"","bail":null,"operator":{"departmentName":null,"lastModifiedDate":"2019-05-08 17:56:02","avatarUrl":"b9740afb30e4437f880b5393934e81e9","lastModifiedBy":"管理员","sort":10,"title":"管理员","createdDate":"1999-01-10 14:08:40","province":[],"createdBy":"SYSADMIN","name":"管理员","id":215,"department":null,"brand":[],"oaId":"-1","oaCode":"guanliyuan","username":"guanliyuan"},"legalName":"王五","accountBank":"","registeredAddress":"","areaName":"","companyPhone":"","statusKey":"potential","provinceNumber":"","id":295,"brand":"","orgName":"测试2","creditLevelKey":"A","avatarUrl":null,"areaNumber":"","taxNumber":"","marketBailQuentity":"","businessScope":null,"sort":10,"afterSalesNumber":"123","superiorChain":null,"firstDistributor":null,"provinceName":"","cooperationTypeValue":"二级分销商","targetQuantity":null,"assessmentAgreement":null,"crmNumber":"WL20190417003","statusValue":"潜在","growthRate":null,"creditLevelValue":"A","sapNumber":null,"operatorName":"管理员","cooperationTypeKey":"two_level_distributor","exclusiveShopNumber":123,"memberLevelValue":"普通","firstDistributorName":null,"cityName":"","registeredCapital":123,"simpleSpell":"cs2","natureManagement":null,"createdOperatorName":"管理员","lastModifiedDate":"2019-04-17 15:45:42","lastModifiedBy":"管理员","abbreviation":null,"marketBail":null,"createdDate":"2019-04-17 15:45:42","createdBy":"管理员","companyAddress":"","faxNumber":"123","memberLevelKey":"ordinary","annualTargetCompletionRate":null}
             * comparativeSales : null
             * id : 18
             * visit : null
             * complete : null
             * train : null
             */

            private DictResultBean.ContentBean memberAttribute;
            private String lastModifiedDate;


            protected HuaJueItemsBean(Parcel in) {
                memberAttribute = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
                lastModifiedDate = in.readString();
                engineerProcessingMatters = in.readString();
                lastModifiedBy = in.readString();
                policyProcessAdvocacy = in.readByte() != 0;
                content = in.readString();
                createdDate = in.readString();
                createdBy = in.readString();
                monopoly = in.readByte() != 0;
                member = in.readParcelable(MemberBeanID.class.getClassLoader());
                comparativeSales = in.readByte() != 0;
                if (in.readByte() == 0) {
                    id = null;
                } else {
                    id = in.readLong();
                }
                visit = in.readByte() != 0;
                complete = in.readByte() != 0;
                train = in.readByte() != 0;
                attachments = in.createTypedArrayList(AttachmentBean.CREATOR);
                attachmentsTwo = in.createTypedArrayList(AttachmentBean.CREATOR);
            }

            public static final Creator<HuaJueItemsBean> CREATOR = new Creator<HuaJueItemsBean>() {
                @Override
                public HuaJueItemsBean createFromParcel(Parcel in) {
                    return new HuaJueItemsBean(in);
                }

                @Override
                public HuaJueItemsBean[] newArray(int size) {
                    return new HuaJueItemsBean[size];
                }
            };

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

            public List<AttachmentBean> getAttachments() {
                return attachments;
            }

            public void setAttachments(List<AttachmentBean> attachments) {
                this.attachments = attachments;
            }

            private String engineerProcessingMatters;
            private String lastModifiedBy;
            private boolean policyProcessAdvocacy;
            private String content;
            private String createdDate;
            private String createdBy;
            private boolean monopoly;
            private MemberBeanID member;
            private boolean comparativeSales;
            private Long id;
            private boolean visit;
            private boolean complete;
            private boolean train;
            private List<AttachmentBean> attachments;

            public List<AttachmentBean> getAttachmentsTwo() {
                return attachmentsTwo;
            }

            public void setAttachmentsTwo(List<AttachmentBean> attachmentsTwo) {
                this.attachmentsTwo = attachmentsTwo;
            }

            private List<AttachmentBean> attachmentsTwo;





            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (!(o instanceof HuaJueItemsBean)) {
                    return false;
                }
                if (getClass() != o.getClass()) {
                    return false;
                }
                HuaJueItemsBean retailChannelBean = (HuaJueItemsBean) o;
                if (retailChannelBean == null ) {
                    return false;
                }


                return Objects.equals(member.getId(), retailChannelBean.getMember().getId());
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public int hashCode() {

                return Objects.hash(member.getId());
            }

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
                dest.writeParcelable(member, flags);
                dest.writeByte((byte) (comparativeSales ? 1 : 0));
                if (id == null) {
                    dest.writeByte((byte) 0);
                } else {
                    dest.writeByte((byte) 1);
                    dest.writeLong(id);
                }
                dest.writeByte((byte) (visit ? 1 : 0));
                dest.writeByte((byte) (complete ? 1 : 0));
                dest.writeByte((byte) (train ? 1 : 0));
                dest.writeTypedList(attachments);
                dest.writeTypedList(attachmentsTwo);
            }
        }

        public static class HjUnfulfilledProjectsBean implements Parcelable  {
            public HjUnfulfilledProjectsBean() {
            }

            /**
             * effective : true
             * estimatedOrderTime : 2019-05-08

             * createdDate : 2019-05-08 19:23:16
             * lastModifiedDate : 2019-05-08 19:23:16
             * createdBy : 管理员
             * lastModifiedBy : 管理员
             * reasonsNoPerformance : 未履行原因
             * id : 13
             * sort : 10
             * engineering : 工程
             */

            private boolean effective;

            protected HjUnfulfilledProjectsBean(Parcel in) {
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

            public static final Creator<HjUnfulfilledProjectsBean> CREATOR = new Creator<HjUnfulfilledProjectsBean>() {
                @Override
                public HjUnfulfilledProjectsBean createFromParcel(Parcel in) {
                    return new HjUnfulfilledProjectsBean( in );
                }

                @Override
                public HjUnfulfilledProjectsBean[] newArray(int size) {
                    return new HjUnfulfilledProjectsBean[size];
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

            private String estimatedOrderTime;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String reasonsNoPerformance;
            private Long id;
            private String engineering;


            @Override
            public int describeContents() {
                return 0;
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

}
