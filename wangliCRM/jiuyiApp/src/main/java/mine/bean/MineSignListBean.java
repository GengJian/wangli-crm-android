package mine.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.jiuyi.model.DictResultBean;

import java.util.List;

import customer.entity.AttachmentBean;

/**
 * ****************************************************************
 * 文件名称:MineSignListBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/3/20 15:31
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/3/20 1.00 初始版本
 * ****************************************************************
 */
public class MineSignListBean {



    private int totalElements;
    private boolean last;
    private int totalPages;
    private int size;
    private int number;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;
    private List<SortBean> sort;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public static class ContentBean implements Parcelable  {
        private String createdBy;

        public ContentBean() {
        }


        protected ContentBean(Parcel in) {
            createdBy = in.readString();
            createdDate = in.readString();
            lastModifiedBy = in.readString();
            lastModifiedDate = in.readString();
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readLong();
            }
            fromClientType = in.readString();
            searchContent = in.readString();
            signInDate = in.readString();
            longitude = in.readString();
            latitude = in.readString();
            address = in.readString();
            remark = in.readString();
            hardwareInfo = in.readString();
            provinceName = in.readString();
            signType = in.readParcelable( DictResultBean.ContentBean.class.getClassLoader() );
            attachments = in.createTypedArrayList( AttachmentBean.CREATOR );
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel in) {
                return new ContentBean( in );
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
        }

        public String getSearchContent() {
            return searchContent;
        }

        public void setSearchContent(String searchContent) {
            this.searchContent = searchContent;
        }

        public String getSignInDate() {
            return signInDate;
        }

        public void setSignInDate(String signInDate) {
            this.signInDate = signInDate;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getHardwareInfo() {
            return hardwareInfo;
        }

        public void setHardwareInfo(String hardwareInfo) {
            this.hardwareInfo = hardwareInfo;
        }

        public List<AttachmentBean> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<AttachmentBean> attachments) {
            this.attachments = attachments;
        }

        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private Long id=null;
        private String fromClientType;
        private String searchContent;
        private String signInDate;
        private String longitude;
        private String latitude;
        private String address;
        private String remark;
        private String hardwareInfo;

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        private String provinceName;

        public DictResultBean.ContentBean getSignType() {
            return signType;
        }

        public void setSignType(DictResultBean.ContentBean signType) {
            this.signType = signType;
        }

        private DictResultBean.ContentBean signType;
        private List<AttachmentBean> attachments;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest , int flags) {
            dest.writeString( createdBy );
            dest.writeString( createdDate );
            dest.writeString( lastModifiedBy );
            dest.writeString( lastModifiedDate );
            if (id == null) {
                dest.writeByte( (byte) 0 );
            } else {
                dest.writeByte( (byte) 1 );
                dest.writeLong( id );
            }
            dest.writeString( fromClientType );
            dest.writeString( searchContent );
            dest.writeString( signInDate );
            dest.writeString( longitude );
            dest.writeString( latitude );
            dest.writeString( address );
            dest.writeString( remark );
            dest.writeString( hardwareInfo );
            dest.writeString( provinceName );
            dest.writeParcelable( signType , flags );
            dest.writeTypedList( attachments );
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : createdDate
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : false
         * descending : true
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean ascending;
        private boolean descending;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
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
    }
}
