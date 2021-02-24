package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:MaterialTypeBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/16 13:51
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/16 1.00 初始版本
 * ****************************************************************
 */
public class MaterialTypeBean {

    /**
     * content : [{"createdBy":"18021647031","createdDate":"2018-12-19 21:05:22","lastModifiedBy":"18021647031","lastModifiedDate":"2018-12-19 21:05:22","id":8,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"叠瓦单晶","remark":""},{"createdBy":"18021647031","createdDate":"2018-12-19 21:05:15","lastModifiedBy":"18021647031","lastModifiedDate":"2018-12-19 21:05:15","id":7,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"MBB单晶","remark":""},{"createdBy":"18021647031","createdDate":"2018-12-19 21:05:07","lastModifiedBy":"18021647031","lastModifiedDate":"2018-12-19 21:05:07","id":6,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"金刚线多晶","remark":""},{"createdBy":"18021647031","createdDate":"2018-12-19 21:04:54","lastModifiedBy":"18021647031","lastModifiedDate":"2018-12-19 21:04:54","id":5,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"双面SE PERC单晶","remark":""},{"createdBy":"18021647031","createdDate":"2018-12-19 21:04:43","lastModifiedBy":"18021647031","lastModifiedDate":"2018-12-19 21:04:43","id":4,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"单面SE PERC单晶","remark":""},{"createdBy":"18021647031","createdDate":"2018-12-19 20:46:07","lastModifiedBy":"18021647031","lastModifiedDate":"2018-12-19 20:53:37","id":2,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"双面PERC单晶","remark":"说明一下"},{"createdBy":"18021647031","createdDate":"2018-12-19 20:43:23","lastModifiedBy":"18021647031","lastModifiedDate":"2018-12-19 20:43:23","id":1,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"单面PERC单晶","remark":""}]
     * last : true
     * totalPages : 1
     * totalElements : 7
     * number : 0
     * size : 10
     * sort : [{"direction":"DESC","property":"lastModifiedDate","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * numberOfElements : 7
     * first : true
     */

    private boolean last;
    private int totalPages;
    private int totalElements;
    private int number;
    private int size;
    private int numberOfElements;
    private boolean first;
    private List<ContentBean> content;
    private List<SortBean> sort;

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
        /**
         * createdBy : 18021647031
         * createdDate : 2018-12-19 21:05:22
         * lastModifiedBy : 18021647031
         * lastModifiedDate : 2018-12-19 21:05:22
         * id : 8
         * deleted : false
         * sort : 1
         * fromClientType : null
         * optionGroup : []
         * name : 叠瓦单晶
         * remark :
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private Object fromClientType;
        private String name;
        private String remark;
        private boolean isCheck=false;

        protected ContentBean(Parcel in) {
            createdBy = in.readString();
            createdDate = in.readString();
            lastModifiedBy = in.readString();
            lastModifiedDate = in.readString();
            id = in.readLong();
            name = in.readString();
            remark = in.readString();
            isCheck = in.readByte() != 0;
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

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

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



        public Object getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(Object fromClientType) {
            this.fromClientType = fromClientType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }


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
            dest.writeString(name);
            dest.writeString(remark);
            dest.writeByte((byte) (isCheck ? 1 : 0));
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : lastModifiedDate
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
