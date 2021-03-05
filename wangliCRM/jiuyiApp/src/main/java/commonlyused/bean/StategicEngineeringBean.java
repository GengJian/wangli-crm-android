package commonlyused.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.List;

/**
 * ****************************************************************
 * 文件名称:StategicEngineeringBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/4/19 11:19
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/4/19 1.00 初始版本
 * ****************************************************************
 */
public class StategicEngineeringBean {

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
        private long expectVisit;
        private String lastModifiedBy;
        private String areaNumber;
        private double salesTarget;
        private double cumulativeShipments;
        private double actualShipment;
        private String remark;

        public String getRemarkCompletion() {
            return remarkCompletion;
        }

        public void setRemarkCompletion(String remarkCompletion) {
            this.remarkCompletion = remarkCompletion;
        }

        private String remarkCompletion;//其他事项完成情况
        private double ompletionRate;
        private NormalOperatorBean.OperatorBeanID operator;
        private long actualVisit;
        private String createdDate;
        private String cityName;
        private String createdBy;
        private String areaName;
        private String provinceNumber;
        private Long id;
        private String provinceName;
        private double projectedShipment;

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

        public double getProjectedShipment() {
            return projectedShipment;
        }

        public void setProjectedShipment(double projectedShipment) {
            this.projectedShipment = projectedShipment;
        }

        public List<StrategicEngineeringItemsBean> getStrategicEngineeringItems() {
            return strategicEngineeringItems;
        }

        public void setStrategicEngineeringItems(List<StrategicEngineeringItemsBean> strategicEngineeringItems) {
            this.strategicEngineeringItems = strategicEngineeringItems;
        }

        private List<StrategicEngineeringItemsBean> strategicEngineeringItems;


        }

        public static class StrategicEngineeringItemsBean implements Parcelable {
            public StrategicEngineeringItemsBean() {
            }

            private String constructionSite;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String achieveResults;
            private Long id;
            private boolean visit;

            protected StrategicEngineeringItemsBean(Parcel in) {
                constructionSite = in.readString();
                createdDate = in.readString();
                lastModifiedDate = in.readString();
                createdBy = in.readString();
                lastModifiedBy = in.readString();
                achieveResults = in.readString();
                id = in.readLong();
                visit = in.readByte() != 0;
            }


            public static final Creator<StrategicEngineeringItemsBean> CREATOR = new Creator<StrategicEngineeringItemsBean>() {
                @Override
                public StrategicEngineeringItemsBean createFromParcel(Parcel in) {
                    return new StrategicEngineeringItemsBean( in );
                }

                @Override
                public StrategicEngineeringItemsBean[] newArray(int size) {
                    return new StrategicEngineeringItemsBean[size];
                }
            };

            public String getConstructionSite() {
                return constructionSite;
            }

            public void setConstructionSite(String constructionSite) {
                this.constructionSite = constructionSite;
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

            public String getAchieveResults() {
                return achieveResults;
            }

            public void setAchieveResults(String achieveResults) {
                this.achieveResults = achieveResults;
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


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest , int flags) {
                dest.writeString( constructionSite );
                dest.writeString( createdDate );
                dest.writeString( lastModifiedDate );
                dest.writeString( createdBy );
                dest.writeString( lastModifiedBy );
                dest.writeString( achieveResults );
                if (id == null) {
                    dest.writeByte( (byte) 0 );
                } else {
                    dest.writeByte( (byte) 1 );
                    dest.writeLong( id );
                }
                dest.writeByte( (byte) (visit ? 1 : 0) );
            }
        }

}
