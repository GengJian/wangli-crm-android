package commonlyused.bean;

import java.util.List;

public class NormalDeptBean {

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

    public static class ContentBean {
        /**
         * createdBy : admin
         * createdDate : 2018-04-06T10:12:04Z
         * lastModifiedBy : admin
         * lastModifiedDate : 2018-04-06T10:12:04Z
         * id : 2
         * deleted : false
         * sort : 10
         * parent : null
         * name : 总裁办
         * desp : null
         * path : null
         * totalCount : 0
         * marketTreands : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private Long id;
        private boolean deleted;
        private int sort;
        private Object parent;
        private String name;
        private Object desp;
        private Object path;
        private int totalCount;
        private Object marketTreands;

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

        public Object getDesp() {
            return desp;
        }

        public void setDesp(Object desp) {
            this.desp = desp;
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

        public Object getMarketTreands() {
            return marketTreands;
        }

        public void setMarketTreands(Object marketTreands) {
            this.marketTreands = marketTreands;
        }
    }
    public static class ParentBean {
        /**
         * createdBy : guanliyuan
         * createdDate : 2019-01-25 20:15:57
         * lastModifiedBy : guanliyuan
         * lastModifiedDate : 2019-01-26 19:23:24
         * id : 163
         * deleted : false
         * sort : 0
         * fromClientType : null
         * optionGroup : []
         * parent : null
         * name : 工艺部（a)
         * desp :
         * oaDepartmentId : 205
         * approval : null
         * path : -31-
         * totalCount : 40
         * subDepartmentCount : 4
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
        private ParentBean parent;
        private String name;
        private String desp;
        private String oaDepartmentId;
        private Object approval;
        private String path;
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

        public ParentBean getParent() {
            return parent;
        }

        public void setParent(ParentBean parent) {
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

        public String getOaDepartmentId() {
            return oaDepartmentId;
        }

        public void setOaDepartmentId(String oaDepartmentId) {
            this.oaDepartmentId = oaDepartmentId;
        }

        public Object getApproval() {
            return approval;
        }

        public void setApproval(Object approval) {
            this.approval = approval;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
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
