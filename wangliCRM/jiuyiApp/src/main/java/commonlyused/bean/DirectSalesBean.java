package commonlyused.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:DirectSalesBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/4/19 11:39
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/4/19 1.00 初始版本
 * ****************************************************************
 */
public class DirectSalesBean {

    /**
     * number : 0
     * last : true
     * numberOfElements : 5
     * size : 10
     * totalPages : 1
     * sort : [{"nullHandling":"NATIVE","ignoreCase":false,"property":"id","ascending":false,"descending":true,"direction":"DESC"}]
     * first : true
     * content : [{"date":"2019-04-12","cityNumber":null,"lastModifiedDate":"2019-04-19 11:26:23","expectVisit":1,"lastModifiedBy":"管理员","expectReceivedPayments":50,"directSalesEngineeringItems":[{"createdDate":"2019-04-19 11:26:23","lastModifiedDate":"2019-04-19 11:26:23","createdBy":"管理员","lastModifiedBy":"管理员","achieveResults":"aaa","project":"a","id":10,"sort":10,"visit":true}],"areaNumber":null,"remark":"","sort":10,"actualReceivedPayments":500,"ompletionRate":12625,"operator":{"departmentName":null,"lastModifiedDate":"2019-04-18 13:33:28","avatarUrl":null,"lastModifiedBy":"刘锋","sort":10,"title":"主管","createdDate":"2019-04-08 16:28:50","province":[],"createdBy":"管理员","name":"刘锋","id":478,"department":{"desp":null,"createdDate":"2019-04-04 15:01:26","lastModifiedDate":"2019-04-04 15:01:26","createdBy":"管理员","lastModifiedBy":"管理员","name":"西区营销服务部","oaDepartmentId":null,"id":127,"sort":10},"brand":[],"oaId":null,"oaCode":"WLM11040108","username":"WLM11040108"},"monthReceivedPayments":400,"actualVisit":1,"accumulatePayments":50000,"createdDate":"2019-04-19 11:26:23","cityName":null,"createdBy":"管理员","areaName":null,"provinceNumber":null,"id":10,"provinceName":null},{"date":"2019-04-12","cityNumber":null,"lastModifiedDate":"2019-04-12 14:45:10","expectVisit":0,"lastModifiedBy":"管理员","expectReceivedPayments":10000,"directSalesEngineeringItems":[],"areaNumber":null,"remark":"","sort":10,"actualReceivedPayments":10000,"ompletionRate":12500,"operator":{"departmentName":null,"lastModifiedDate":"2019-04-18 13:33:28","avatarUrl":null,"lastModifiedBy":"刘锋","sort":10,"title":"主管","createdDate":"2019-04-08 16:28:50","province":[],"createdBy":"管理员","name":"刘锋","id":478,"department":{"desp":null,"createdDate":"2019-04-04 15:01:26","lastModifiedDate":"2019-04-04 15:01:26","createdBy":"管理员","lastModifiedBy":"管理员","name":"西区营销服务部","oaDepartmentId":null,"id":127,"sort":10},"brand":[],"oaId":null,"oaCode":"WLM11040108","username":"WLM11040108"},"monthReceivedPayments":400,"actualVisit":0,"accumulatePayments":40000,"createdDate":"2019-04-12 14:45:10","cityName":null,"createdBy":"管理员","areaName":null,"provinceNumber":null,"id":9,"provinceName":null},{"date":"2019-04-12","cityNumber":null,"lastModifiedDate":"2019-04-12 14:40:04","expectVisit":1,"lastModifiedBy":"管理员","expectReceivedPayments":40000,"directSalesEngineeringItems":[{"createdDate":"2019-04-12 14:40:04","lastModifiedDate":"2019-04-12 14:40:04","createdBy":"管理员","lastModifiedBy":"管理员","achieveResults":"","project":"项目","id":9,"sort":10,"visit":true}],"areaNumber":null,"remark":"","sort":10,"actualReceivedPayments":40000,"ompletionRate":10000,"operator":{"departmentName":null,"lastModifiedDate":"2019-04-18 13:33:28","avatarUrl":null,"lastModifiedBy":"刘锋","sort":10,"title":"主管","createdDate":"2019-04-08 16:28:50","province":[],"createdBy":"管理员","name":"刘锋","id":478,"department":{"desp":null,"createdDate":"2019-04-04 15:01:26","lastModifiedDate":"2019-04-04 15:01:26","createdBy":"管理员","lastModifiedBy":"管理员","name":"西区营销服务部","oaDepartmentId":null,"id":127,"sort":10},"brand":[],"oaId":null,"oaCode":"WLM11040108","username":"WLM11040108"},"monthReceivedPayments":400,"actualVisit":1,"accumulatePayments":0,"createdDate":"2019-04-12 14:40:04","cityName":null,"createdBy":"管理员","areaName":null,"provinceNumber":null,"id":8,"provinceName":null},{"date":"2019-04-09","cityNumber":null,"lastModifiedDate":"2019-04-10 17:58:04","expectVisit":0,"lastModifiedBy":"管理员","expectReceivedPayments":9.9999999E7,"directSalesEngineeringItems":[],"areaNumber":null,"remark":"","sort":10,"actualReceivedPayments":8.8888888888E10,"ompletionRate":88888.89,"operator":{"departmentName":null,"lastModifiedDate":"2019-04-08 16:31:30","avatarUrl":null,"lastModifiedBy":"管理员","sort":10,"title":"员工","createdDate":"2019-04-08 16:28:50","province":[],"createdBy":"管理员","name":"王元港","id":480,"department":{"desp":null,"createdDate":"2019-04-04 15:01:26","lastModifiedDate":"2019-04-04 15:01:26","createdBy":"管理员","lastModifiedBy":"管理员","name":"西区营销服务部","oaDepartmentId":null,"id":127,"sort":10},"brand":[],"oaId":null,"oaCode":"A201458","username":"A201458"},"monthReceivedPayments":1000000,"actualVisit":0,"accumulatePayments":null,"createdDate":"2019-04-10 17:58:04","cityName":null,"createdBy":"管理员","areaName":null,"provinceNumber":null,"id":7,"provinceName":null},{"date":"2019-03-28","cityNumber":null,"lastModifiedDate":"2019-04-09 11:28:06","expectVisit":123,"lastModifiedBy":"管理员","expectReceivedPayments":45,"directSalesEngineeringItems":[{"createdDate":"2019-04-09 11:28:05","lastModifiedDate":"2019-04-09 11:28:06","createdBy":"管理员","lastModifiedBy":"管理员","achieveResults":"321","project":"123","id":2,"sort":10,"visit":true}],"areaNumber":null,"remark":"123","sort":10,"actualReceivedPayments":1231,"ompletionRate":452,"operator":null,"monthReceivedPayments":5.0E10,"actualVisit":213,"accumulatePayments":null,"createdDate":"2019-04-09 11:28:05","cityName":null,"createdBy":"管理员","areaName":null,"provinceNumber":null,"id":1,"provinceName":null}]
     * totalElements : 5
     */

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
        private double expectReceivedPayments;
        private String areaNumber;
        private String remark;
        private double actualReceivedPayments;
        private double ompletionRate;
        private NormalOperatorBean.OperatorBeanID operator;
        private double monthReceivedPayments;
        private long actualVisit;
        private double accumulatePayments;
        private String createdDate;
        private String cityName;
        private String createdBy;
        private String areaName;

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

        public double getExpectReceivedPayments() {
            return expectReceivedPayments;
        }

        public void setExpectReceivedPayments(double expectReceivedPayments) {
            this.expectReceivedPayments = expectReceivedPayments;
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

        public double getActualReceivedPayments() {
            return actualReceivedPayments;
        }

        public void setActualReceivedPayments(double actualReceivedPayments) {
            this.actualReceivedPayments = actualReceivedPayments;
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

        public double getMonthReceivedPayments() {
            return monthReceivedPayments;
        }

        public void setMonthReceivedPayments(double monthReceivedPayments) {
            this.monthReceivedPayments = monthReceivedPayments;
        }

        public long getActualVisit() {
            return actualVisit;
        }

        public void setActualVisit(long actualVisit) {
            this.actualVisit = actualVisit;
        }

        public double getAccumulatePayments() {
            return accumulatePayments;
        }

        public void setAccumulatePayments(double accumulatePayments) {
            this.accumulatePayments = accumulatePayments;
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

        public List<DirectSalesEngineeringItemsBean> getDirectSalesEngineeringItems() {
            return directSalesEngineeringItems;
        }

        public void setDirectSalesEngineeringItems(List<DirectSalesEngineeringItemsBean> directSalesEngineeringItems) {
            this.directSalesEngineeringItems = directSalesEngineeringItems;
        }

        private String provinceNumber;
        private Long id;
        private String provinceName;
        private List<DirectSalesEngineeringItemsBean> directSalesEngineeringItems;


        }

        public static class DirectSalesEngineeringItemsBean implements Parcelable {
            public DirectSalesEngineeringItemsBean() {
            }

            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String achieveResults;
            private String project;
            private Long id;
            private boolean visit;

            protected DirectSalesEngineeringItemsBean(Parcel in) {
                createdDate = in.readString();
                lastModifiedDate = in.readString();
                createdBy = in.readString();
                lastModifiedBy = in.readString();
                achieveResults = in.readString();
                project = in.readString();
                id = in.readLong();
                visit = in.readByte() != 0;
            }

            public static final Creator<DirectSalesEngineeringItemsBean> CREATOR = new Creator<DirectSalesEngineeringItemsBean>() {
                @Override
                public DirectSalesEngineeringItemsBean createFromParcel(Parcel in) {
                    return new DirectSalesEngineeringItemsBean(in);
                }

                @Override
                public DirectSalesEngineeringItemsBean[] newArray(int size) {
                    return new DirectSalesEngineeringItemsBean[size];
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

            public String getAchieveResults() {
                return achieveResults;
            }

            public void setAchieveResults(String achieveResults) {
                this.achieveResults = achieveResults;
            }

            public String getProject() {
                return project;
            }

            public void setProject(String project) {
                this.project = project;
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
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(createdDate);
                dest.writeString(lastModifiedDate);
                dest.writeString(createdBy);
                dest.writeString(lastModifiedBy);
                dest.writeString(achieveResults);
                dest.writeString(project);
                if (id == null) {
                    dest.writeByte( (byte) 0 );
                } else {
                    dest.writeByte( (byte) 1 );
                    dest.writeLong( id );
                }
                dest.writeByte((byte) (visit ? 1 : 0));
            }
        }

}
