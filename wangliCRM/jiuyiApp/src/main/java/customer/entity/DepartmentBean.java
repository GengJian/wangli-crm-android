package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:DepartmentBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/16 16:32
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/16 1.00 初始版本
 * ****************************************************************
 */
public class DepartmentBean {

    /**
     * content : [{"createdBy":"15167156690","createdDate":"2018-12-26 18:36:44","lastModifiedBy":"1004613","lastModifiedDate":"2019-01-13 11:17:47","id":31,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"parent":null,"name":"广东爱旭科技股份有限公司","desp":"广东爱旭科技股份有限公司","oaDepartmentId":null,"approval":null,"path":null,"totalCount":5,"subDepartmentCount":1,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},{"createdBy":"15167156690","createdDate":"2018-12-26 18:56:01","lastModifiedBy":"15167156690","lastModifiedDate":"2018-12-26 19:47:24","id":32,"deleted":false,"sort":20,"fromClientType":null,"optionGroup":[],"parent":null,"name":"天津爱旭太阳能科技有限公司","desp":"","oaDepartmentId":null,"approval":null,"path":null,"totalCount":0,"subDepartmentCount":0,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},{"createdBy":"15167156690","createdDate":"2018-12-26 18:56:39","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-15 20:24:47","id":33,"deleted":false,"sort":30,"fromClientType":null,"optionGroup":[],"parent":null,"name":"爱旭太阳能","desp":"","oaDepartmentId":null,"approval":null,"path":null,"totalCount":39,"subDepartmentCount":12,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},{"createdBy":"15167156690","createdDate":"2018-12-26 20:36:56","lastModifiedBy":"1004613","lastModifiedDate":"2019-01-13 11:17:47","id":45,"deleted":false,"sort":100,"fromClientType":null,"optionGroup":[],"parent":{"createdBy":"15167156690","createdDate":"2018-12-26 18:36:44","lastModifiedBy":"1004613","lastModifiedDate":"2019-01-13 11:17:47","id":31,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"parent":null,"name":"广东爱旭科技股份有限公司","desp":"广东爱旭科技股份有限公司","oaDepartmentId":null,"approval":null,"path":null,"totalCount":5,"subDepartmentCount":1,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},"name":"总经办","desp":"","oaDepartmentId":"71","approval":null,"path":"-31-","totalCount":5,"subDepartmentCount":0,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},{"createdBy":"13901565517","createdDate":"2018-12-12 00:00:00","lastModifiedBy":"13901565517","lastModifiedDate":"2018-12-27 16:54:48","id":55,"deleted":null,"sort":200,"fromClientType":null,"optionGroup":[],"parent":{"createdBy":"15167156690","createdDate":"2018-12-26 18:56:39","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-15 20:24:47","id":33,"deleted":false,"sort":30,"fromClientType":null,"optionGroup":[],"parent":null,"name":"爱旭太阳能","desp":"","oaDepartmentId":null,"approval":null,"path":null,"totalCount":39,"subDepartmentCount":12,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},"name":"资金管理部(Z)","desp":null,"oaDepartmentId":"249","approval":null,"path":"-33-","totalCount":0,"subDepartmentCount":0,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},{"createdBy":"13901565517","createdDate":"2018-12-12 00:00:00","lastModifiedBy":"1004613","lastModifiedDate":"2019-01-13 11:16:08","id":56,"deleted":null,"sort":200,"fromClientType":null,"optionGroup":[],"parent":{"createdBy":"15167156690","createdDate":"2018-12-26 18:56:39","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-15 20:24:47","id":33,"deleted":false,"sort":30,"fromClientType":null,"optionGroup":[],"parent":null,"name":"爱旭太阳能","desp":"","oaDepartmentId":null,"approval":null,"path":null,"totalCount":39,"subDepartmentCount":12,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},"name":"总经办(Z)","desp":null,"oaDepartmentId":"250","approval":null,"path":"-33-","totalCount":7,"subDepartmentCount":4,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},{"createdBy":"13901565517","createdDate":"2018-12-12 00:00:00","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-12 15:07:22","id":57,"deleted":null,"sort":200,"fromClientType":null,"optionGroup":[],"parent":{"createdBy":"15167156690","createdDate":"2018-12-26 18:56:39","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-15 20:24:47","id":33,"deleted":false,"sort":30,"fromClientType":null,"optionGroup":[],"parent":null,"name":"爱旭太阳能","desp":"","oaDepartmentId":null,"approval":null,"path":null,"totalCount":39,"subDepartmentCount":12,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},"name":"财经管理部(Z)","desp":null,"oaDepartmentId":"254","approval":null,"path":"-33-","totalCount":2,"subDepartmentCount":2,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},{"createdBy":"13901565517","createdDate":"2018-12-12 00:00:00","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-15 20:24:47","id":58,"deleted":null,"sort":200,"fromClientType":null,"optionGroup":[],"parent":{"createdBy":"15167156690","createdDate":"2018-12-26 18:56:39","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-15 20:24:47","id":33,"deleted":false,"sort":30,"fromClientType":null,"optionGroup":[],"parent":null,"name":"爱旭太阳能","desp":"","oaDepartmentId":null,"approval":null,"path":null,"totalCount":39,"subDepartmentCount":12,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},"name":"流程与IT部(Z)","desp":null,"oaDepartmentId":"255","approval":null,"path":"-33-","totalCount":4,"subDepartmentCount":2,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},{"createdBy":"13901565517","createdDate":"2018-12-12 00:00:00","lastModifiedBy":"1004613","lastModifiedDate":"2019-01-13 11:17:11","id":59,"deleted":null,"sort":200,"fromClientType":null,"optionGroup":[],"parent":{"createdBy":"15167156690","createdDate":"2018-12-26 18:56:39","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-15 20:24:47","id":33,"deleted":false,"sort":30,"fromClientType":null,"optionGroup":[],"parent":null,"name":"爱旭太阳能","desp":"","oaDepartmentId":null,"approval":null,"path":null,"totalCount":39,"subDepartmentCount":12,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},"name":"销售部(Z)","desp":null,"oaDepartmentId":"260","approval":null,"path":"-33-","totalCount":13,"subDepartmentCount":5,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},{"createdBy":"13901565517","createdDate":"2018-12-12 00:00:00","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-12 18:31:10","id":60,"deleted":null,"sort":200,"fromClientType":null,"optionGroup":[],"parent":{"createdBy":"15167156690","createdDate":"2018-12-26 18:56:39","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-15 20:24:47","id":33,"deleted":false,"sort":30,"fromClientType":null,"optionGroup":[],"parent":null,"name":"爱旭太阳能","desp":"","oaDepartmentId":null,"approval":null,"path":null,"totalCount":39,"subDepartmentCount":12,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false},"name":"研发部(Z)","desp":null,"oaDepartmentId":"265","approval":null,"path":"-33-","totalCount":2,"subDepartmentCount":2,"sapVkbur":null,"salesOffice":null,"officeOrderApprovalConfigs":null,"checked":false}]
     * last : false
     * totalPages : 8
     * totalElements : 80
     * number : 0
     * size : 10
     * sort : [{"direction":"ASC","property":"sort","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * numberOfElements : 10
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

    public static class ContentBean {
        /**
         * createdBy : 15167156690
         * createdDate : 2018-12-26 18:36:44
         * lastModifiedBy : 1004613
         * lastModifiedDate : 2019-01-13 11:17:47
         * id : 31
         * deleted : false
         * sort : 10
         * fromClientType : null
         * optionGroup : []
         * parent : null
         * name : 广东爱旭科技股份有限公司
         * desp : 广东爱旭科技股份有限公司
         * oaDepartmentId : null
         * approval : null
         * path : null
         * totalCount : 5
         * subDepartmentCount : 1
         * sapVkbur : null
         * salesOffice : null
         * officeOrderApprovalConfigs : null
         * checked : false
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private Object fromClientType;
        private Object parent;
        private String name;
        private String desp;
        private Object oaDepartmentId;
        private Object approval;
        private Object path;
        private int totalCount;
        private int subDepartmentCount;
        private Object sapVkbur;
        private Object salesOffice;
        private Object officeOrderApprovalConfigs;
        private boolean checked;
        private List<?> optionGroup;

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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(Object fromClientType) {
            this.fromClientType = fromClientType;
        }

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
            this.parent = parent;
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

        public Object getOaDepartmentId() {
            return oaDepartmentId;
        }

        public void setOaDepartmentId(Object oaDepartmentId) {
            this.oaDepartmentId = oaDepartmentId;
        }

        public Object getApproval() {
            return approval;
        }

        public void setApproval(Object approval) {
            this.approval = approval;
        }

        public Object getPath() {
            return path;
        }

        public void setPath(Object path) {
            this.path = path;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getSubDepartmentCount() {
            return subDepartmentCount;
        }

        public void setSubDepartmentCount(int subDepartmentCount) {
            this.subDepartmentCount = subDepartmentCount;
        }

        public Object getSapVkbur() {
            return sapVkbur;
        }

        public void setSapVkbur(Object sapVkbur) {
            this.sapVkbur = sapVkbur;
        }

        public Object getSalesOffice() {
            return salesOffice;
        }

        public void setSalesOffice(Object salesOffice) {
            this.salesOffice = salesOffice;
        }

        public Object getOfficeOrderApprovalConfigs() {
            return officeOrderApprovalConfigs;
        }

        public void setOfficeOrderApprovalConfigs(Object officeOrderApprovalConfigs) {
            this.officeOrderApprovalConfigs = officeOrderApprovalConfigs;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public List<?> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<?> optionGroup) {
            this.optionGroup = optionGroup;
        }
    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : sort
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
    public static class DepartmentBeanID implements Parcelable{
        public DepartmentBeanID() {
        }

        public DepartmentBeanID(Parcel in) {
            id = in.readLong();
            name = in.readString();

        }

        public static final Creator<DepartmentBeanID> CREATOR = new Creator<DepartmentBeanID>() {
            @Override
            public DepartmentBeanID createFromParcel(Parcel in) {
                return new DepartmentBeanID(in);
            }

            @Override
            public DepartmentBeanID[] newArray(int size) {
                return new DepartmentBeanID[size];
            }
        };

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private long id;
        private String name;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(name);
        }
    }

}
