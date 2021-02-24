package dynamic.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import customer.entity.AttachmentBean;
import customer.entity.DeptNewBean;

/**
 * ****************************************************************
 * 文件名称:DynamicBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/6 17:53
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/6 1.00 初始版本
 * ****************************************************************
 */
public class DynamicBean {

    /**
     * content : [{"createdBy":"1022768","createdDate":"2019-01-06","lastModifiedBy":"1022768","lastModifiedDate":"2019-01-06","id":10,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"bigCategory":null,"childCategoryId":null,"childPrimaryKey":null,"member":null,"orgName":null,"abbreviation":null,"title":null,"content":null,"fkType":"WORKING_CIRCLE","fkTypeValue":"工作圈","iconDictName":null,"iconDictNameUrl":null,"attachmentList":null,"vdieoAttachmentList":null,"iconUrl":null,"createdDateStr":null,"deleteAble":false,"url":null,"operater":null,"operatorName":"蒋鹏祥","departmentName":null,"departmentSet":null}]
     * totalElements : 1
     * last : true
     * totalPages : 1
     * number : 0
     * size : 5
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * numberOfElements : 1
     * first : true
     */

    private int totalElements;
    private boolean last;
    private int totalPages;
    private int number;
    private int size;
    private int numberOfElements;
    private boolean first;
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

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
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

    public static class ContentBean implements Parcelable {

        private String createdBy;

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            createdBy = in.readString();
            createdDate = in.readString();
            lastModifiedBy = in.readString();
            lastModifiedDate = in.readString();

            id = in.readLong();
            fromClientType = in.readString();
            title = in.readString();
            content = in.readString();
            fkType = in.readString();
            fkTypeValue = in.readString();
            operatorName = in.readString();
            operatorNameSrFrAr = in.readString();
            iconUrl = in.readString();
            iconDictNameUrl = in.readString();
            favorited = in.readLong();
            liked = in.readLong();
            viewed = in.readLong();
            likedCount = in.readInt();
            viewedCount = in.readInt();
            attachmentList = in.createTypedArrayList(AttachmentBean.CREATOR);
            vdieoAttachmentList = in.createTypedArrayList(AttachmentBean.CREATOR);
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel in) {
                return new ContentBean(in);
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

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFkType() {
            return fkType;
        }

        public void setFkType(String fkType) {
            this.fkType = fkType;
        }

        public String getFkTypeValue() {
            return fkTypeValue;
        }

        public void setFkTypeValue(String fkTypeValue) {
            this.fkTypeValue = fkTypeValue;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private String fromClientType;
        private String title;
        private String content;
        private String fkType;
        private String fkTypeValue;
        private String operatorName;

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        private String avatarUrl;

        public String getOperatorNameSrFrAr() {
            return operatorNameSrFrAr;
        }

        public void setOperatorNameSrFrAr(String operatorNameSrFrAr) {
            this.operatorNameSrFrAr = operatorNameSrFrAr;
        }

        private String operatorNameSrFrAr;

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getIconDictNameUrl() {
            return iconDictNameUrl;
        }

        public void setIconDictNameUrl(String iconDictNameUrl) {
            this.iconDictNameUrl = iconDictNameUrl;
        }

        private String iconUrl;
        private String iconDictNameUrl;

        public long getFavorited() {
            return favorited;
        }

        public void setFavorited(long favorited) {
            this.favorited = favorited;
        }

        public long getLiked() {
            return liked;
        }

        public void setLiked(long liked) {
            this.liked = liked;
        }

        public long getViewed() {
            return viewed;
        }

        public void setViewed(long viewed) {
            this.viewed = viewed;
        }

        public int getLikedCount() {
            return likedCount;
        }

        public void setLikedCount(int likedCount) {
            this.likedCount = likedCount;
        }

        public int getViewedCount() {
            return viewedCount;
        }

        public void setViewedCount(int viewedCount) {
            this.viewedCount = viewedCount;
        }

        private long favorited=-1;
        private long liked=-1;
        private long viewed=-1;
        private int likedCount=0;
        private int viewedCount=0;

        public List<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }

        public List<AttachmentBean> getVdieoAttachmentList() {
            return vdieoAttachmentList;
        }

        public void setVdieoAttachmentList(List<AttachmentBean> vdieoAttachmentList) {
            this.vdieoAttachmentList = vdieoAttachmentList;
        }

        private List<AttachmentBean> attachmentList;
        private List<AttachmentBean> vdieoAttachmentList;

        public List<DeptNewBean.ContentBean> getDepartmentSet() {
            return departmentSet;
        }

        public void setDepartmentSet(List<DeptNewBean.ContentBean> departmentSet) {
            this.departmentSet = departmentSet;
        }

        private List<DeptNewBean.ContentBean> departmentSet;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(createdBy);
            dest.writeString(createdDate);
            dest.writeString(lastModifiedBy);
            dest.writeString(lastModifiedDate);
            dest.writeLong(id);
            dest.writeString(fromClientType);
            dest.writeString(title);
            dest.writeString(content);
            dest.writeString(fkType);
            dest.writeString(fkTypeValue);
            dest.writeString(operatorName);
            dest.writeString(operatorNameSrFrAr);
            dest.writeString(iconUrl);
            dest.writeString(iconDictNameUrl);
            dest.writeLong(favorited);
            dest.writeLong(liked);
            dest.writeLong(viewed);
            dest.writeInt(likedCount);
            dest.writeInt(viewedCount);
            dest.writeTypedList(attachmentList);
            dest.writeTypedList(vdieoAttachmentList);
        }
    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : id
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : true
         * descending : false
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
