package commonlyused.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import customer.entity.SexEnum;

public class NormalOperatorBean {



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
         * property : createdDate
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
         * id : 194
         * sort : 10
         * createdDate : 2019-01-15 20:24:46
         * lastModifiedDate : 2019-01-15 20:24:53
         * createdBy : 潘梦洋
         * lastModifiedBy : 潘梦洋
         * address :
         * name : ITgongong
         * position : {}
         * username : ITgongong
         * departmentName :
         * superiorOperator : {}
         * previousLoginDate :
         * oaId : 2415
         * birthday : 2019-01-25
         * sapCode :
         * sapCname :
         * department : {"id":58,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-15 20:24:47","createdBy":"13901565517","lastModifiedBy":"潘梦洋","name":"流程与IT部(Z)","oaDepartmentId":"255","desp":""}
         * activated : true
         * timIdentifier : dev_im_194
         * title : IT服务工程师(Z)
         * oaCode : ITgongong
         * telOne : 15155326969
         * email :
         * sex : MALE
         * lastLoginDate :
         * avatarUrl :
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String address;
        private String name;
        private PositionBean position;
        private String username;
        private String departmentName;
        private SuperiorOperatorBean superiorOperator;
        private String previousLoginDate;
        private String oaId;
        private String birthday;
        private String sapCode;
        private String sapCname;
        private DepartmentBean department;
        private boolean activated;
        private String timIdentifier;
        private String title;
        private String oaCode;
        private String telOne;
        private String email;
        private SexEnum sex;
        private String lastLoginDate;
        private String avatarUrl;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PositionBean getPosition() {
            return position;
        }

        public void setPosition(PositionBean position) {
            this.position = position;
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

        public SuperiorOperatorBean getSuperiorOperator() {
            return superiorOperator;
        }

        public void setSuperiorOperator(SuperiorOperatorBean superiorOperator) {
            this.superiorOperator = superiorOperator;
        }

        public String getPreviousLoginDate() {
            return previousLoginDate;
        }

        public void setPreviousLoginDate(String previousLoginDate) {
            this.previousLoginDate = previousLoginDate;
        }

        public String getOaId() {
            return oaId;
        }

        public void setOaId(String oaId) {
            this.oaId = oaId;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSapCode() {
            return sapCode;
        }

        public void setSapCode(String sapCode) {
            this.sapCode = sapCode;
        }

        public String getSapCname() {
            return sapCname;
        }

        public void setSapCname(String sapCname) {
            this.sapCname = sapCname;
        }

        public DepartmentBean getDepartment() {
            return department;
        }

        public void setDepartment(DepartmentBean department) {
            this.department = department;
        }

        public boolean isActivated() {
            return activated;
        }

        public void setActivated(boolean activated) {
            this.activated = activated;
        }

        public String getTimIdentifier() {
            return timIdentifier;
        }

        public void setTimIdentifier(String timIdentifier) {
            this.timIdentifier = timIdentifier;
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

        public String getTelOne() {
            return telOne;
        }

        public void setTelOne(String telOne) {
            this.telOne = telOne;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public SexEnum getSex() {
            return sex;
        }

        public void setSex(SexEnum sex) {
            this.sex = sex;
        }

        public String getLastLoginDate() {
            return lastLoginDate;
        }

        public void setLastLoginDate(String lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public static class PositionBean {
        }

        public static class SuperiorOperatorBean {
        }

        public static class DepartmentBean {
            /**
             * id : 58
             * sort : 200
             * createdDate : 2018-12-12 00:00:00
             * lastModifiedDate : 2019-01-15 20:24:47
             * createdBy : 13901565517
             * lastModifiedBy : 潘梦洋
             * name : 流程与IT部(Z)
             * oaDepartmentId : 255
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
    public static class OperatorBeanID implements Parcelable{
        public OperatorBeanID() {
        }

        protected OperatorBeanID(Parcel in) {
            id = in.readLong();

            name = in.readString();
        }

        public static final Creator<OperatorBeanID> CREATOR = new Creator<OperatorBeanID>() {
            @Override
            public OperatorBeanID createFromParcel(Parcel in) {
                return new OperatorBeanID(in);
            }

            @Override
            public OperatorBeanID[] newArray(int size) {
                return new OperatorBeanID[size];
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
