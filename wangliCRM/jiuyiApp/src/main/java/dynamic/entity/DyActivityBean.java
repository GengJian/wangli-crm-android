package dynamic.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import customer.entity.AttachmentBean;

/**
 * ****************************************************************
 * 文件名称:DyActivityBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/12 20:04
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/12 1.00 初始版本
 * ****************************************************************
 */
public class DyActivityBean {


    private boolean first;
    private boolean last;
    private int number;
    private int numberOfElements;
    private int size;
    private int totalElements;
    private int totalPages;
    private List<SortBean> sort;
    private List<ContentBean> content;

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
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
         * ascending : false
         * descending : true
         * direction : DESC
         * ignoreCase : false
         * nullHandling : NATIVE
         * property : id
         */

        private boolean ascending;
        private boolean descending;
        private String direction;
        private boolean ignoreCase;
        private String nullHandling;
        private String property;

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

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }

    public static class ContentBean {
        /**
         * id : 42
         * sort : 10
         * createdDate : 2019-01-11 17:54:35
         * lastModifiedDate : 2019-01-11 17:54:35
         * createdBy : 刘少岗
         * lastModifiedBy : 刘少岗
         * optionGroup : [{"name":"approve","content":"通过"},{"name":"reject","content":"退回"}]
         * content : 持续时间较长
         * activityTypeKey : media_event
         * actualPeopleItemSet : []
         * approveStatusValue : 审批中
         * status : draft
         * importantValue : 一般
         * title : 常规展示活动
         * endDate : 2019-02-03
         * beginDate : 2019-01-13
         * invitePeopleItemSet : []
         * activityTypeValue : 媒体活动
         * planPeopleCount : 0
         * actualPeopleCount : 0
         * statusValue : 草稿
         * createOperator : {"id":141,"sort":10,"createdDate":"2019-01-10 13:51:10","lastModifiedDate":"2019-01-12 13:57:40","createdBy":"徐超","lastModifiedBy":"潘梦洋","name":"刘少岗","username":"1018213","title":"商务经理（Z）","oaCode":"1018213","departmentName":"","department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-12 18:14:09","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"市场部（Z）","oaDepartmentId":"415","desp":""},"oaId":"1844"}
         * publishWaySet : [{"id":130,"sort":10,"createdDate":"2019-01-11 17:54:35","lastModifiedDate":"2019-01-11 17:54:35","createdBy":"刘少岗","lastModifiedBy":"刘少岗","publishWayValue":"新媒体","publishWayKey":"new_media"},{"id":129,"sort":10,"createdDate":"2019-01-11 17:54:35","lastModifiedDate":"2019-01-11 17:54:35","createdBy":"刘少岗","lastModifiedBy":"刘少岗","publishWayValue":"电视TV","publishWayKey":"tv"}]
         * approveStatus : approvalInCrm
         * attachmentList : []
         * operator : {"id":1,"sort":10,"createdDate":"2018-11-29 21:15:42","lastModifiedDate":"2019-01-12 20:01:10","createdBy":"15167156690","lastModifiedBy":"潘梦洋","name":"潘梦洋","username":"1022654","title":"市场专员(z)","oaCode":"1022654","departmentName":"","department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-12 18:14:09","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"市场部（Z）","oaDepartmentId":"415","desp":""},"oaId":"2381"}
         * department : {"id":33,"sort":30,"createdDate":"2018-12-26 18:56:39","lastModifiedDate":"2019-01-12 19:34:31","createdBy":"15167156690","lastModifiedBy":"潘梦洋","name":"爱旭太阳能","oaDepartmentId":"","desp":""}
         * importantKey : general
         * budgetAmount : 8900000.0
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String content;
        private String activityTypeKey;
        private String approveStatusValue;
        private String status;
        private String importantValue;
        private String title;
        private String endDate;
        private String beginDate;
        private String activityTypeValue;
        private int planPeopleCount;
        private int actualPeopleCount;
        private String statusValue;
        private CreateOperatorBean createOperator;
        private String approveStatus;
        private OperatorBean operator;
        private DepartmentBeanXX department;
        private String importantKey;
        private double budgetAmount;
        private List<OptionGroupBean> optionGroup;
        private List<?> actualPeopleItemSet;
        private List<?> invitePeopleItemSet;
        private List<PublishWaySetBean> publishWaySet;
        private List<AttachmentBean> attachmentList;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getActivityTypeKey() {
            return activityTypeKey;
        }

        public void setActivityTypeKey(String activityTypeKey) {
            this.activityTypeKey = activityTypeKey;
        }

        public String getApproveStatusValue() {
            return approveStatusValue;
        }

        public void setApproveStatusValue(String approveStatusValue) {
            this.approveStatusValue = approveStatusValue;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImportantValue() {
            return importantValue;
        }

        public void setImportantValue(String importantValue) {
            this.importantValue = importantValue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getActivityTypeValue() {
            return activityTypeValue;
        }

        public void setActivityTypeValue(String activityTypeValue) {
            this.activityTypeValue = activityTypeValue;
        }

        public int getPlanPeopleCount() {
            return planPeopleCount;
        }

        public void setPlanPeopleCount(int planPeopleCount) {
            this.planPeopleCount = planPeopleCount;
        }

        public int getActualPeopleCount() {
            return actualPeopleCount;
        }

        public void setActualPeopleCount(int actualPeopleCount) {
            this.actualPeopleCount = actualPeopleCount;
        }

        public String getStatusValue() {
            return statusValue;
        }

        public void setStatusValue(String statusValue) {
            this.statusValue = statusValue;
        }

        public CreateOperatorBean getCreateOperator() {
            return createOperator;
        }

        public void setCreateOperator(CreateOperatorBean createOperator) {
            this.createOperator = createOperator;
        }

        public String getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(String approveStatus) {
            this.approveStatus = approveStatus;
        }

        public OperatorBean getOperator() {
            return operator;
        }

        public void setOperator(OperatorBean operator) {
            this.operator = operator;
        }

        public DepartmentBeanXX getDepartment() {
            return department;
        }

        public void setDepartment(DepartmentBeanXX department) {
            this.department = department;
        }

        public String getImportantKey() {
            return importantKey;
        }

        public void setImportantKey(String importantKey) {
            this.importantKey = importantKey;
        }

        public double getBudgetAmount() {
            return budgetAmount;
        }

        public void setBudgetAmount(double budgetAmount) {
            this.budgetAmount = budgetAmount;
        }

        public List<OptionGroupBean> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<OptionGroupBean> optionGroup) {
            this.optionGroup = optionGroup;
        }

        public List<?> getActualPeopleItemSet() {
            return actualPeopleItemSet;
        }

        public void setActualPeopleItemSet(List<?> actualPeopleItemSet) {
            this.actualPeopleItemSet = actualPeopleItemSet;
        }

        public List<?> getInvitePeopleItemSet() {
            return invitePeopleItemSet;
        }

        public void setInvitePeopleItemSet(List<?> invitePeopleItemSet) {
            this.invitePeopleItemSet = invitePeopleItemSet;
        }

        public List<PublishWaySetBean> getPublishWaySet() {
            return publishWaySet;
        }

        public void setPublishWaySet(List<PublishWaySetBean> publishWaySet) {
            this.publishWaySet = publishWaySet;
        }

        public List<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }

        public static class CreateOperatorBean {
            /**
             * id : 141
             * sort : 10
             * createdDate : 2019-01-10 13:51:10
             * lastModifiedDate : 2019-01-12 13:57:40
             * createdBy : 徐超
             * lastModifiedBy : 潘梦洋
             * name : 刘少岗
             * username : 1018213
             * title : 商务经理（Z）
             * oaCode : 1018213
             * departmentName :
             * department : {"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-12 18:14:09","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"市场部（Z）","oaDepartmentId":"415","desp":""}
             * oaId : 1844
             */

            private long id;
            private int sort;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String name;
            private String username;
            private String title;
            private String oaCode;
            private String departmentName;
            private DepartmentBean department;
            private String oaId;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getOaCode() {
                return oaCode;
            }

            public void setOaCode(String oaCode) {
                this.oaCode = oaCode;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public DepartmentBean getDepartment() {
                return department;
            }

            public void setDepartment(DepartmentBean department) {
                this.department = department;
            }

            public String getOaId() {
                return oaId;
            }

            public void setOaId(String oaId) {
                this.oaId = oaId;
            }

            public static class DepartmentBean {
                /**
                 * id : 78
                 * sort : 200
                 * createdDate : 2018-12-12 00:00:00
                 * lastModifiedDate : 2019-01-12 18:14:09
                 * createdBy : 13901565517
                 * lastModifiedBy : 潘梦洋
                 * name : 市场部（Z）
                 * oaDepartmentId : 415
                 * desp :
                 */

                private long id;
                private int sort;
                private String createdDate;
                private String lastModifiedDate;
                private String createdBy;
                private String lastModifiedBy;
                private String name;
                private String oaDepartmentId;
                private String desp;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOaDepartmentId() {
                    return oaDepartmentId;
                }

                public void setOaDepartmentId(String oaDepartmentId) {
                    this.oaDepartmentId = oaDepartmentId;
                }

                public String getDesp() {
                    return desp;
                }

                public void setDesp(String desp) {
                    this.desp = desp;
                }
            }
        }

        public static class OperatorBean {
            /**
             * id : 1
             * sort : 10
             * createdDate : 2018-11-29 21:15:42
             * lastModifiedDate : 2019-01-12 20:01:10
             * createdBy : 15167156690
             * lastModifiedBy : 潘梦洋
             * name : 潘梦洋
             * username : 1022654
             * title : 市场专员(z)
             * oaCode : 1022654
             * departmentName :
             * department : {"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-12 18:14:09","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"市场部（Z）","oaDepartmentId":"415","desp":""}
             * oaId : 2381
             */

            private long id;
            private int sort;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String name;
            private String username;
            private String title;
            private String oaCode;
            private String departmentName;
            private DepartmentBeanX department;
            private String oaId;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getOaCode() {
                return oaCode;
            }

            public void setOaCode(String oaCode) {
                this.oaCode = oaCode;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public DepartmentBeanX getDepartment() {
                return department;
            }

            public void setDepartment(DepartmentBeanX department) {
                this.department = department;
            }

            public String getOaId() {
                return oaId;
            }

            public void setOaId(String oaId) {
                this.oaId = oaId;
            }

            public static class DepartmentBeanX {
                /**
                 * id : 78
                 * sort : 200
                 * createdDate : 2018-12-12 00:00:00
                 * lastModifiedDate : 2019-01-12 18:14:09
                 * createdBy : 13901565517
                 * lastModifiedBy : 潘梦洋
                 * name : 市场部（Z）
                 * oaDepartmentId : 415
                 * desp :
                 */

                private long id;
                private int sort;
                private String createdDate;
                private String lastModifiedDate;
                private String createdBy;
                private String lastModifiedBy;
                private String name;
                private String oaDepartmentId;
                private String desp;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOaDepartmentId() {
                    return oaDepartmentId;
                }

                public void setOaDepartmentId(String oaDepartmentId) {
                    this.oaDepartmentId = oaDepartmentId;
                }

                public String getDesp() {
                    return desp;
                }

                public void setDesp(String desp) {
                    this.desp = desp;
                }
            }
        }

        public static class DepartmentBeanXX {
            /**
             * id : 33
             * sort : 30
             * createdDate : 2018-12-26 18:56:39
             * lastModifiedDate : 2019-01-12 19:34:31
             * createdBy : 15167156690
             * lastModifiedBy : 潘梦洋
             * name : 爱旭太阳能
             * oaDepartmentId :
             * desp :
             */

            private long id;
            private int sort;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String name;
            private String oaDepartmentId;
            private String desp;

            public long getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOaDepartmentId() {
                return oaDepartmentId;
            }

            public void setOaDepartmentId(String oaDepartmentId) {
                this.oaDepartmentId = oaDepartmentId;
            }

            public String getDesp() {
                return desp;
            }

            public void setDesp(String desp) {
                this.desp = desp;
            }
        }

        public static class OptionGroupBean {
            /**
             * name : approve
             * content : 通过
             */

            private String name;
            private String content;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class PublishWaySetBean {
            /**
             * id : 130
             * sort : 10
             * createdDate : 2019-01-11 17:54:35
             * lastModifiedDate : 2019-01-11 17:54:35
             * createdBy : 刘少岗
             * lastModifiedBy : 刘少岗
             * publishWayValue : 新媒体
             * publishWayKey : new_media
             */

            private long id;
            private int sort;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String publishWayValue;
            private String publishWayKey;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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

            public String getPublishWayValue() {
                return publishWayValue;
            }

            public void setPublishWayValue(String publishWayValue) {
                this.publishWayValue = publishWayValue;
            }

            public String getPublishWayKey() {
                return publishWayKey;
            }

            public void setPublishWayKey(String publishWayKey) {
                this.publishWayKey = publishWayKey;
            }
        }
    }

    public static class DyActivityBeanID implements Parcelable{
        private long id;

        public DyActivityBeanID() {
        }

        protected DyActivityBeanID(Parcel in) {
            id = in.readLong();
            title = in.readString();
        }

        public static final Creator<DyActivityBeanID> CREATOR = new Creator<DyActivityBeanID>() {
            @Override
            public DyActivityBeanID createFromParcel(Parcel in) {
                return new DyActivityBeanID(in);
            }

            @Override
            public DyActivityBeanID[] newArray(int size) {
                return new DyActivityBeanID[size];
            }
        };

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private String title="";

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(title);
        }
    }

}
