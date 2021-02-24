package dynamic.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import customer.entity.AttachmentBean;
import customer.entity.MemberBeanID;
import customer.entity.VisibilityScropeBean;

/**
 * ****************************************************************
 * 文件名称:DyInteligenceBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/12 18:45
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/12 1.00 初始版本
 * ****************************************************************
 */
public class DyInteligenceBean {


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
         * property : lastModifiedDate
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

    public static class ContentBean implements Parcelable {
        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String content;
        private MemberBeanID member;
        private OperatorBean operator;
        private IntelligenceBean intelligence;
        private String itemStatusKey;
        private String intelligenceTypeKey;
        private String intelligenceInfoKey;
        private String intelligenceInfoValue;
        private String intelligenceTypeValue;
        private String itemStatusValue;

        public List<VisibilityScropeBean> getVisibleRangeList() {
            return visibleRangeList;
        }

        public void setVisibleRangeList(List<VisibilityScropeBean> visibleRangeList) {
            this.visibleRangeList = visibleRangeList;
        }

        private List<VisibilityScropeBean> visibleRangeList;

        public int getViewedCount() {
            return viewedCount;
        }

        public void setViewedCount(int viewedCount) {
            this.viewedCount = viewedCount;
        }

        private int viewedCount =0;
        private ArrayList<AttachmentBean> attachmentList;

        protected ContentBean(Parcel in) {
            id = in.readLong();
            sort = in.readInt();
            createdDate = in.readString();
            lastModifiedDate = in.readString();
            createdBy = in.readString();
            lastModifiedBy = in.readString();
            content = in.readString();
            member = in.readParcelable(MemberBeanID.class.getClassLoader());
            itemStatusKey = in.readString();
            intelligenceTypeKey = in.readString();
            intelligenceInfoKey = in.readString();
            intelligenceInfoValue = in.readString();
            intelligenceTypeValue = in.readString();
            itemStatusValue = in.readString();
            attachmentList = in.createTypedArrayList(AttachmentBean.CREATOR);
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

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public OperatorBean getOperator() {
            return operator;
        }

        public void setOperator(OperatorBean operator) {
            this.operator = operator;
        }

        public IntelligenceBean getIntelligence() {
            return intelligence;
        }

        public void setIntelligence(IntelligenceBean intelligence) {
            this.intelligence = intelligence;
        }

        public String getItemStatusKey() {
            return itemStatusKey;
        }

        public void setItemStatusKey(String itemStatusKey) {
            this.itemStatusKey = itemStatusKey;
        }

        public String getIntelligenceTypeKey() {
            return intelligenceTypeKey;
        }

        public void setIntelligenceTypeKey(String intelligenceTypeKey) {
            this.intelligenceTypeKey = intelligenceTypeKey;
        }

        public String getIntelligenceInfoKey() {
            return intelligenceInfoKey;
        }

        public void setIntelligenceInfoKey(String intelligenceInfoKey) {
            this.intelligenceInfoKey = intelligenceInfoKey;
        }

        public String getIntelligenceInfoValue() {
            return intelligenceInfoValue;
        }

        public void setIntelligenceInfoValue(String intelligenceInfoValue) {
            this.intelligenceInfoValue = intelligenceInfoValue;
        }

        public String getIntelligenceTypeValue() {
            return intelligenceTypeValue;
        }

        public void setIntelligenceTypeValue(String intelligenceTypeValue) {
            this.intelligenceTypeValue = intelligenceTypeValue;
        }

        public String getItemStatusValue() {
            return itemStatusValue;
        }

        public void setItemStatusValue(String itemStatusValue) {
            this.itemStatusValue = itemStatusValue;
        }

        public ArrayList<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(ArrayList<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeInt(sort);
            dest.writeString(createdDate);
            dest.writeString(lastModifiedDate);
            dest.writeString(createdBy);
            dest.writeString(lastModifiedBy);
            dest.writeString(content);
            dest.writeParcelable(member, flags);
            dest.writeString(itemStatusKey);
            dest.writeString(intelligenceTypeKey);
            dest.writeString(intelligenceInfoKey);
            dest.writeString(intelligenceInfoValue);
            dest.writeString(intelligenceTypeValue);
            dest.writeString(itemStatusValue);
            dest.writeTypedList(attachmentList);
        }


        public static class OperatorBean {
            /**
             * id : 1
             * sort : 10
             * createdDate : 2018-11-29 21:15:42
             * lastModifiedDate : 2019-01-12 15:53:11
             * createdBy : 15167156690
             * lastModifiedBy : 潘梦洋
             * name : 潘梦洋
             * username : 1022654
             * departmentName :
             * title : 市场专员(z)
             * department : {"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-12 18:14:09","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"市场部（Z）","oaDepartmentId":"415","desp":""}
             * oaCode : 1022654
             * oaId : 2381
             */

            private long id;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String name;
            private String username;
            private String departmentName;
            private String title;
            private DepartmentBean department;
            private String oaCode;
            private String oaId;

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            private String avatarUrl;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
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

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public DepartmentBean getDepartment() {
                return department;
            }

            public void setDepartment(DepartmentBean department) {
                this.department = department;
            }

            public String getOaCode() {
                return oaCode;
            }

            public void setOaCode(String oaCode) {
                this.oaCode = oaCode;
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

        public static class IntelligenceBean {

            private long id;
            private int sort;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private MemberBeanID member;
            private String statusKey;
            private OperatorBeanX operator;
            private String statusValue;
            private String collectDate;
            private String bigCategoryKey;
            private String businessTypeKey;
            private String bigCategoryValue;
            private String businessTypeValue;

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

            public MemberBeanID getMember() {
                return member;
            }

            public void setMember(MemberBeanID member) {
                this.member = member;
            }

            public String getStatusKey() {
                return statusKey;
            }

            public void setStatusKey(String statusKey) {
                this.statusKey = statusKey;
            }

            public OperatorBeanX getOperator() {
                return operator;
            }

            public void setOperator(OperatorBeanX operator) {
                this.operator = operator;
            }

            public String getStatusValue() {
                return statusValue;
            }

            public void setStatusValue(String statusValue) {
                this.statusValue = statusValue;
            }

            public String getCollectDate() {
                return collectDate;
            }

            public void setCollectDate(String collectDate) {
                this.collectDate = collectDate;
            }

            public String getBigCategoryKey() {
                return bigCategoryKey;
            }

            public void setBigCategoryKey(String bigCategoryKey) {
                this.bigCategoryKey = bigCategoryKey;
            }

            public String getBusinessTypeKey() {
                return businessTypeKey;
            }

            public void setBusinessTypeKey(String businessTypeKey) {
                this.businessTypeKey = businessTypeKey;
            }

            public String getBigCategoryValue() {
                return bigCategoryValue;
            }

            public void setBigCategoryValue(String bigCategoryValue) {
                this.bigCategoryValue = bigCategoryValue;
            }

            public String getBusinessTypeValue() {
                return businessTypeValue;
            }

            public void setBusinessTypeValue(String businessTypeValue) {
                this.businessTypeValue = businessTypeValue;
            }


            public static class OperatorBeanX {
                /**
                 * id : 1
                 * sort : 10
                 * createdDate : 2018-11-29 21:15:42
                 * lastModifiedDate : 2019-01-12 15:53:11
                 * createdBy : 15167156690
                 * lastModifiedBy : 潘梦洋
                 * name : 潘梦洋
                 * username : 1022654
                 * departmentName :
                 * title : 市场专员(z)
                 * department : {"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-12 18:14:09","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"市场部（Z）","oaDepartmentId":"415","desp":""}
                 * oaCode : 1022654
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
                private String departmentName;
                private String title;
                private DepartmentBeanX department;
                private String oaCode;
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

                public String getDepartmentName() {
                    return departmentName;
                }

                public void setDepartmentName(String departmentName) {
                    this.departmentName = departmentName;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public DepartmentBeanX getDepartment() {
                    return department;
                }

                public void setDepartment(DepartmentBeanX department) {
                    this.department = department;
                }

                public String getOaCode() {
                    return oaCode;
                }

                public void setOaCode(String oaCode) {
                    this.oaCode = oaCode;
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
        }
    }

    public static class IntelligenceItemBeanID implements  Parcelable {
        private long id;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private String content;

        public IntelligenceItemBeanID() {
        }

        public long getId() {

            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        protected IntelligenceItemBeanID(Parcel in) {
            id = in.readLong();
        }

        public static final Creator<IntelligenceItemBeanID> CREATOR = new Creator<IntelligenceItemBeanID>() {
            @Override
            public IntelligenceItemBeanID createFromParcel(Parcel in) {
                return new IntelligenceItemBeanID(in);
            }

            @Override
            public IntelligenceItemBeanID[] newArray(int size) {
                return new IntelligenceItemBeanID[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
        }
    }
}
