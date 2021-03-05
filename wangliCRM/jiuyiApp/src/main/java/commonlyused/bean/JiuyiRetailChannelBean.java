package commonlyused.bean;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import com.control.utils.Func;

import java.util.List;
import java.util.Objects;

import customer.entity.AttachmentBean;
import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称:JiuyiRetailChannelBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/17 18:01
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/17 1.00 初始版本
 * ****************************************************************
 */
public class JiuyiRetailChannelBean {




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
        private String date;
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

        public List<RetailChannelItemsBean> getRetailChannelItems() {
            return retailChannelItems;
        }

        public void setRetailChannelItems(List<RetailChannelItemsBean> retailChannelItems) {
            this.retailChannelItems = retailChannelItems;
        }

        private String afterSaleUnprocessed;
        private String cityNumber;
        private String activity;
        private double cumulativeShipments;
        private double actualShipment;
        private String remark;

        public String getRemarkCompletion() {
            return remarkCompletion;
        }

        public void setRemarkCompletion(String remarkCompletion) {
            this.remarkCompletion = remarkCompletion;
        }

        private String remarkCompletion;
        private double ompletionRate;
        private NormalOperatorBean.OperatorBeanID operator;
        private String cityName;
        private String areaName;
        private String provinceNumber;
        private boolean finish;
        private Long id;
        private long expectVisit;
        private String lastModifiedBy;
        private String areaNumber;
        private double salesTarget;
        private long actualVisit;
        private String createdDate;
        private String createdBy;
        private String provinceName;
        private double projectedShipment;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        private String province;//省份
        private List<RetailChannelItemsBean> retailChannelItems;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

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




    }
    public static class RetailChannelItemsBean implements Parcelable {
        public RetailChannelItemsBean() {
        }

        private boolean policyProcessAdvocacy;
        private String content;


        protected RetailChannelItemsBean(Parcel in) {
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

        public static final Creator<RetailChannelItemsBean> CREATOR = new Creator<RetailChannelItemsBean>() {
            @Override
            public RetailChannelItemsBean createFromParcel(Parcel in) {
                return new RetailChannelItemsBean(in);
            }

            @Override
            public RetailChannelItemsBean[] newArray(int size) {
                return new RetailChannelItemsBean[size];
            }
        };

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

        private String createdDate;
        private String createdBy;
        private boolean monopoly=false;
        private MemberBeanID member;
        private boolean comparativeSales=false;
        private Long id;
        private boolean visit=false;
        private boolean complete=false;
        private boolean train=false;
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
            if (!(o instanceof RetailChannelItemsBean)) {
                return false;
            }
            if (getClass() != o.getClass()) {
                return false;
            }
            RetailChannelItemsBean retailChannelBean = (RetailChannelItemsBean) o;
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

}

