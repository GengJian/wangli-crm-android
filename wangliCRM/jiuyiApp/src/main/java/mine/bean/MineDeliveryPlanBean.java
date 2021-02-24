package mine.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称:MineDeliveryPlanBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/7/25 10:47
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/7/25 1.00 初始版本
 * ****************************************************************
 */
public class MineDeliveryPlanBean {

    /**
     * type : BATCHNUMBER
     * content : [{"fromClientType":null,"type":"BATCHNUMBER","member":null,"memberId":null,"name":null,"batchNumber":"T359H","year":2018,"month":8,"totalPlanQuantity":11,"actualQuantity":0,"actualShipNumber":0,"actualShipString":"0.00%","searchValue":null},{"fromClientType":null,"type":"BATCHNUMBER","member":null,"memberId":null,"name":null,"batchNumber":"C4196","year":2018,"month":8,"totalPlanQuantity":21470,"actualQuantity":0,"actualShipNumber":0,"actualShipString":"0.00%","searchValue":null},{"fromClientType":null,"type":"BATCHNUMBER","member":null,"memberId":null,"name":null,"batchNumber":"HF200/S9809","year":2018,"month":8,"totalPlanQuantity":200,"actualQuantity":0,"actualShipNumber":0,"actualShipString":"0.00%","searchValue":null}]
     */

    private boolean last;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    private int totalPages;
    private int totalElements;
    private int number;
    private int size;
    private boolean first;
    private Object sort;
    private int numberOfElements;
    private String type;
    private List<ContentBean> content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements Parcelable {
        /**
         * fromClientType : null
         * type : BATCHNUMBER
         * member : null
         * memberId : null
         * name : null
         * batchNumber : T359H
         * year : 2018
         * month : 8
         * totalPlanQuantity : 11
         * actualQuantity : 0
         * actualShipNumber : 0
         * actualShipString : 0.00%
         * searchValue : null
         */

        private String fromClientType;

        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBatchNumber() {
            return batchNumber;
        }

        public void setBatchNumber(String batchNumber) {
            this.batchNumber = batchNumber;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public double getTotalPlanQuantity() {
            return totalPlanQuantity;
        }

        public void setTotalPlanQuantity(double totalPlanQuantity) {
            this.totalPlanQuantity = totalPlanQuantity;
        }

        public double getActualQuantity() {
            return actualQuantity;
        }

        public void setActualQuantity(double actualQuantity) {
            this.actualQuantity = actualQuantity;
        }

        public double getActualShipNumber() {
            return actualShipNumber;
        }

        public void setActualShipNumber(double actualShipNumber) {
            this.actualShipNumber = actualShipNumber;
        }

        public String getActualShipString() {
            return actualShipString;
        }

        public void setActualShipString(String actualShipString) {
            this.actualShipString = actualShipString;
        }

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        private String type;
        private MemberBeanID member;
        private String memberId;
        private String name;
        private String batchNumber;
        private int year;
        private int month;
        private double totalPlanQuantity;
        private double actualQuantity;
        private double actualShipNumber;
        private String actualShipString;
        private String searchValue;
        private String factory;
        private String factoryNumber;

        public String getFactory() {
            return factory;
        }

        public void setFactory(String factory) {
            this.factory = factory;
        }

        public String getFactoryNumber() {
            return factoryNumber;
        }

        public void setFactoryNumber(String factoryNumber) {
            this.factoryNumber = factoryNumber;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.fromClientType);
            dest.writeString(this.type);
            dest.writeString(this.memberId);
            dest.writeString(this.name);
            dest.writeString(this.batchNumber);
            dest.writeInt(this.year);
            dest.writeInt(this.month);
            dest.writeDouble(this.totalPlanQuantity);
            dest.writeDouble(this.actualQuantity);
            dest.writeDouble(this.actualShipNumber);
            dest.writeString(this.actualShipString);
            dest.writeString(this.searchValue);
        }

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.fromClientType = in.readString();
            this.type = in.readString();
            this.memberId = in.readString();
            this.name = in.readString();
            this.batchNumber = in.readString();
            this.year = in.readInt();
            this.month = in.readInt();
            this.totalPlanQuantity = in.readDouble();
            this.actualQuantity = in.readDouble();
            this.actualShipNumber = in.readDouble();
            this.actualShipString = in.readString();
            this.searchValue = in.readString();
        }

        public static final Parcelable.Creator<ContentBean> CREATOR = new Parcelable.Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel source) {
                return new ContentBean(source);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };
    }
}
