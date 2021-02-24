package commonlyused.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:ChannelDevelopBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/4/18 17:42
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/4/18 1.00 初始版本
 * ****************************************************************
 */
public class ChannelDevelopBean {


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
        private String lastModifiedDate;
        private String lastModifiedBy;
        private long developmentProject;
        private String areaNumber;
        private String remark;
        private long finishVisit;
        private double ompletionRate;
        private NormalOperatorBean.OperatorBeanID operator;
        private long signIntention;
        private String createdDate;
        private String cityName;
        private String createdBy;
        private String areaName;
        private long accumulateVisit;
        private String provinceNumber;
        private Long id;
        private String provinceName;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        private String province;
        private long visitIntentional;

        public long getActualSign() {
            return actualSign;
        }

        public void setActualSign(long actualSign) {
            this.actualSign = actualSign;
        }

        private long actualSign;
        private List<VisitIntentionsBean> visitIntentions;

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

        public long getDevelopmentProject() {
            return developmentProject;
        }

        public void setDevelopmentProject(long developmentProject) {
            this.developmentProject = developmentProject;
        }

        public String getAreaNumber() {
            return areaNumber;
        }

        public void setAreaNumber(String areaNumber) {
            this.areaNumber = areaNumber;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getFinishVisit() {
            return finishVisit;
        }

        public void setFinishVisit(long finishVisit) {
            this.finishVisit = finishVisit;
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

        public long getSignIntention() {
            return signIntention;
        }

        public void setSignIntention(long signIntention) {
            this.signIntention = signIntention;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public long getAccumulateVisit() {
            return accumulateVisit;
        }

        public void setAccumulateVisit(long accumulateVisit) {
            this.accumulateVisit = accumulateVisit;
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

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public long getVisitIntentional() {
            return visitIntentional;
        }

        public void setVisitIntentional(long visitIntentional) {
            this.visitIntentional = visitIntentional;
        }

        public List<VisitIntentionsBean> getVisitIntentions() {
            return visitIntentions;
        }

        public void setVisitIntentions(List<VisitIntentionsBean> visitIntentions) {
            this.visitIntentions = visitIntentions;
        }

        public List<SignIntentionsBean> getSignIntentions() {
            return signIntentions;
        }

        public void setSignIntentions(List<SignIntentionsBean> signIntentions) {
            this.signIntentions = signIntentions;
        }

        private List<SignIntentionsBean> signIntentions;




    }

        public static class VisitIntentionsBean implements Parcelable {
            public VisitIntentionsBean() {
            }

            private String createdDate;
            private String managementBrand;

            protected VisitIntentionsBean(Parcel in) {
                createdDate = in.readString();
                managementBrand = in.readString();
                lastModifiedDate = in.readString();
                createdBy = in.readString();
                annualSalesVolume = in.readDouble();
                lastModifiedBy = in.readString();
                member = in.readString();
                if (in.readByte() == 0) {
                    id = null;
                } else {
                    id = in.readLong();
                }
                visit = in.readByte() != 0;
                cooperationIntention = in.readString();
            }

            public static final Creator<VisitIntentionsBean> CREATOR = new Creator<VisitIntentionsBean>() {
                @Override
                public VisitIntentionsBean createFromParcel(Parcel in) {
                    return new VisitIntentionsBean(in);
                }

                @Override
                public VisitIntentionsBean[] newArray(int size) {
                    return new VisitIntentionsBean[size];
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

            public void setAnnualSalesVolume(double annualSalesVolume) {
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

            public String getCooperationIntention() {
                return cooperationIntention;
            }

            public void setCooperationIntention(String cooperationIntention) {
                this.cooperationIntention = cooperationIntention;
            }

            private String lastModifiedDate;
            private String createdBy;
            private double annualSalesVolume;
            private String lastModifiedBy;
            private String member;
            private Long id;
            private boolean visit;
            private String cooperationIntention;


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
                if (id == null) {
                    dest.writeByte((byte) 0);
                } else {
                    dest.writeByte((byte) 1);
                    dest.writeLong(id);
                }
                dest.writeByte((byte) (visit ? 1 : 0));
                dest.writeString(cooperationIntention);
            }
        }
        public static class SignIntentionsBean implements Parcelable {
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String member;
            private boolean sign;
            private Long id;

            public SignIntentionsBean() {
            }

            protected SignIntentionsBean(Parcel in) {
                createdDate = in.readString();
                lastModifiedDate = in.readString();
                createdBy = in.readString();
                lastModifiedBy = in.readString();
                member = in.readString();
                sign = in.readByte() != 0;
                if (in.readByte() == 0) {
                    id = null;
                } else {
                    id = in.readLong();
                }
            }

            public static final Creator<SignIntentionsBean> CREATOR = new Creator<SignIntentionsBean>() {
                @Override
                public SignIntentionsBean createFromParcel(Parcel in) {
                    return new SignIntentionsBean(in);
                }

                @Override
                public SignIntentionsBean[] newArray(int size) {
                    return new SignIntentionsBean[size];
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

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
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
                if (id == null) {
                    dest.writeByte((byte) 0);
                } else {
                    dest.writeByte((byte) 1);
                    dest.writeLong(id);
                }
            }
        }


}
