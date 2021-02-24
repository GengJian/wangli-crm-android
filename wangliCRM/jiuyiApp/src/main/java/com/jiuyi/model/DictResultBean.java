package com.jiuyi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DictResultBean {

    /**
     * content : [{"createdBy":"15058305380","createdDate":"2018-05-28 17:29:39","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-28 17:29:39","id":74,"deleted":false,"sort":10,"fromClientType":null,"name":"gathering_plan_undone_reason","desp":"收款计划未完成原因","key":"other_problem","value":"其它问题","modifiable":null,"builtIn":false,"remark":"其它问题","orders":null},{"createdBy":"15058305380","createdDate":"2018-05-28 17:29:25","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-28 17:29:25","id":73,"deleted":false,"sort":10,"fromClientType":null,"name":"gathering_plan_undone_reason","desp":"收款计划未完成原因","key":"satisfied_problem","value":"满意度问题","modifiable":null,"builtIn":false,"remark":"满意度问题","orders":null},{"createdBy":"15058305380","createdDate":"2018-05-28 17:28:58","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-28 17:28:58","id":72,"deleted":false,"sort":10,"fromClientType":null,"name":"gathering_plan_undone_reason","desp":"收款计划未完成原因","key":"money_problem","value":"资金问题","modifiable":null,"builtIn":false,"remark":"资金问题","orders":null},{"createdBy":"15058305380","createdDate":"2018-05-28 17:26:46","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-28 17:26:46","id":71,"deleted":false,"sort":10,"fromClientType":null,"name":"gathering_plan_undone_reason","desp":"收款计划未完成原因","key":"quality_problem","value":"质量问题","modifiable":null,"builtIn":false,"remark":"质量问题","orders":null},{"createdBy":"15058305380","createdDate":"2018-05-23 14:20:03","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23 14:20:03","id":70,"deleted":false,"sort":10,"fromClientType":null,"name":"demand_plan_undone_reason","desp":"要货计划未完成原因","key":"rival_promotion","value":"对手促销","modifiable":null,"builtIn":false,"remark":"对手促销","orders":null},{"createdBy":"15058305380","createdDate":"2018-05-23 14:18:43","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23 14:18:43","id":69,"deleted":false,"sort":10,"fromClientType":null,"name":"demand_plan_undone_reason","desp":"要货计划未完成原因","key":"price_problem","value":"价格问题转批","modifiable":null,"builtIn":false,"remark":"价格问题转批","orders":null},{"createdBy":"15058305380","createdDate":"2018-05-23 14:18:17","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23 14:18:17","id":68,"deleted":false,"sort":10,"fromClientType":null,"name":"demand_plan_undone_reason","desp":"要货计划未完成原因","key":"quality_problem","value":"质量问题转批","modifiable":null,"builtIn":false,"remark":"质量问题转批","orders":null},{"createdBy":"15058305380","createdDate":"2018-05-23 14:16:52","lastModifiedBy":"15058305380","lastModifiedDate":"2018-05-23 14:16:52","id":67,"deleted":false,"sort":10,"fromClientType":null,"name":"demand_plan_undone_reason","desp":"要货计划未完成原因","key":"goods_short","value":"货源不足转批","modifiable":null,"builtIn":false,"remark":"货源不足转批","orders":null},{"createdBy":"13064793669","createdDate":"2018-05-18 13:25:03","lastModifiedBy":"13064793669","lastModifiedDate":"2018-05-18 13:25:03","id":16,"deleted":false,"sort":10,"fromClientType":null,"name":"risk_type","desp":"风险预警的类型","key":"all","value":"所有","modifiable":null,"builtIn":false,"remark":"所有","orders":null},{"createdBy":"13064793669","createdDate":"2018-05-18 13:25:03","lastModifiedBy":"13064793669","lastModifiedDate":"2018-05-18 13:25:03","id":31,"deleted":false,"sort":10,"fromClientType":null,"name":"risk_type","desp":"风险预警的类型","key":"asset_transfer","value":"资产转移","modifiable":null,"builtIn":false,"remark":"资产转移","orders":null}]
     * totalElements : 74
     * last : false
     * totalPages : 8
     * number : 0
     * size : 10
     * sort : [{"direction":"DESC","property":"lastModifiedDate","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}]
     * numberOfElements : 10
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
        public ContentBean() {
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

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public double getSort() {
            return sort;
        }

        public void setSort(double sort) {
            this.sort = sort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesp() {
            return desp;
        }

        public void setDesp(String desp) {
            this.desp = desp;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isBuiltIn() {
            return builtIn;
        }

        public void setBuiltIn(boolean builtIn) {
            this.builtIn = builtIn;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public boolean isCached() {
            return cached;
        }

        public void setCached(boolean cached) {
            this.cached = cached;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public List<?> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<?> optionGroup) {
            this.optionGroup = optionGroup;
        }

        public static Creator<ContentBean> getCREATOR() {
            return CREATOR;
        }

        /**
         * createdBy : 15058305380
         * createdDate : 2018-05-28 17:29:39
         * lastModifiedBy : 15058305380
         * lastModifiedDate : 2018-05-28 17:29:39
         * id : 74
         * deleted : false
         * sort : 10
         * fromClientType : null
         * name : gathering_plan_undone_reason
         * desp : 收款计划未完成原因
         * key : other_problem
         * value : 其它问题
         * modifiable : null
         * builtIn : false
         * remark : 其它问题
         * orders : null
         */


        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private double sort;
        private String name;
        private String desp;
        private String key;
        private String value;
        private boolean builtIn;
        private String remark;
        private boolean cached;
        private boolean enable;
        private List<?> optionGroup;


        protected ContentBean(Parcel in) {
            createdBy = in.readString();
            createdDate = in.readString();
            lastModifiedBy = in.readString();
            lastModifiedDate = in.readString();
            id = in.readLong();
            deleted = in.readByte() != 0;
            sort = in.readDouble();
            name = in.readString();
            desp = in.readString();
            key = in.readString();
            value = in.readString();
            builtIn = in.readByte() != 0;
            remark = in.readString();
            cached = in.readByte() != 0;
            enable = in.readByte() != 0;
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
            dest.writeByte((byte) (deleted ? 1 : 0));
            dest.writeDouble(sort);
            dest.writeString(name);
            dest.writeString(desp);
            dest.writeString(key);
            dest.writeString(value);
            dest.writeByte((byte) (builtIn ? 1 : 0));
            dest.writeString(remark);
            dest.writeByte((byte) (cached ? 1 : 0));
            dest.writeByte((byte) (enable ? 1 : 0));
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : lastModifiedDate
         * ignoreCase : false
         * nullHandling : NATIVE
         * descending : true
         * ascending : false
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean descending;
        private boolean ascending;

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

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }
    }
}
