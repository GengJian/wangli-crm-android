package commonlyused.bean;

import java.util.List;

import customer.entity.AttachmentBean;
import customer.entity.UpdateAssistantBean;

public class TaskBean {


    private boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private Object sort;
    private int number;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
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

    public static class ContentBean {


        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private String typeKey;
        private String typeValue;
        private FounderBean founder;
        private String taskNumber;
        private String title;
        private ReceiverBean receiver;
        private List<UpdateAssistantBean> receiverSet;
        private String collaboratorSetStr;
        private String statusKey;
        private String statusValue;

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

        public String getTypeKey() {
            return typeKey;
        }

        public void setTypeKey(String typeKey) {
            this.typeKey = typeKey;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }

        public FounderBean getFounder() {
            return founder;
        }

        public void setFounder(FounderBean founder) {
            this.founder = founder;
        }

        public String getTaskNumber() {
            return taskNumber;
        }

        public void setTaskNumber(String taskNumber) {
            this.taskNumber = taskNumber;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ReceiverBean getReceiver() {
            return receiver;
        }

        public void setReceiver(ReceiverBean receiver) {
            this.receiver = receiver;
        }

        public List<UpdateAssistantBean>  getReceiverSet() {
            return receiverSet;
        }

        public void setReceiverSet(List<UpdateAssistantBean>  receiverSet) {
            this.receiverSet = receiverSet;
        }

        public String getCollaboratorSetStr() {
            return collaboratorSetStr;
        }

        public void setCollaboratorSetStr(String collaboratorSetStr) {
            this.collaboratorSetStr = collaboratorSetStr;
        }

        public String getStatusKey() {
            return statusKey;
        }

        public void setStatusKey(String statusKey) {
            this.statusKey = statusKey;
        }

        public String getStatusValue() {
            return statusValue;
        }

        public void setStatusValue(String statusValue) {
            this.statusValue = statusValue;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public boolean isUpdateAble() {
            return updateAble;
        }

        public void setUpdateAble(boolean updateAble) {
            this.updateAble = updateAble;
        }

        public boolean isFavoriteAble() {
            return favoriteAble;
        }

        public void setFavoriteAble(boolean favoriteAble) {
            this.favoriteAble = favoriteAble;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(Object finishTime) {
            this.finishTime = finishTime;
        }

        public String getRemindTimeKey() {
            return remindTimeKey;
        }

        public void setRemindTimeKey(String remindTimeKey) {
            this.remindTimeKey = remindTimeKey;
        }

        public String getRemindTimeValue() {
            return remindTimeValue;
        }

        public void setRemindTimeValue(String remindTimeValue) {
            this.remindTimeValue = remindTimeValue;
        }

        public long getFavoriteId() {
            return favoriteId;
        }

        public void setFavoriteId(long favoriteId) {
            this.favoriteId = favoriteId;
        }

        public List<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public List<UpdateAssistantBean> getCollaboratorSet() {
            return collaboratorSet;
        }

        public void setCollaboratorSet(List<UpdateAssistantBean> collaboratorSet) {
            this.collaboratorSet = collaboratorSet;
        }

        private String startTime;
        private boolean updateAble;
        private boolean favoriteAble;
        private String endTime;
        private String remark;
        private Object finishTime;
        private String remindTimeKey;
        private String remindTimeValue;
        private long favoriteId;
        private List<AttachmentBean> attachmentList;
        private String comments;
        private List<UpdateAssistantBean> collaboratorSet;


        public static class FounderBean {


            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private Object fromClientType;
            private String oaCode;
            private String oaId;
            private String username;
            private boolean activated;
            private String name;
            private String nameFullPinyin;
            private String nameShortPinyin;
            private String telOne;
            private Object telTwo;
            private Object telThree;
            private Object address;
            private Object email;
            private String sex;
            private Object birthday;
            private Object superiorOperator;
            private Object department;
            private Object departmentName;
            private Object position;
            private String avatarUrl;
            private String title;
            private String lastLoginDate;
            private String previousLoginDate;
            private String timIdentifier;
            private Object sapCode;
            private Object sapCname;
            private Object roles;
            private Object officeOrderApprovalConfigs;
            private Object signInRecords;
            private Object assistDepartmentSet;
            private Object taskSet;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public boolean isActivated() {
                return activated;
            }

            public void setActivated(boolean activated) {
                this.activated = activated;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNameFullPinyin() {
                return nameFullPinyin;
            }

            public void setNameFullPinyin(String nameFullPinyin) {
                this.nameFullPinyin = nameFullPinyin;
            }

            public String getNameShortPinyin() {
                return nameShortPinyin;
            }

            public void setNameShortPinyin(String nameShortPinyin) {
                this.nameShortPinyin = nameShortPinyin;
            }

            public String getTelOne() {
                return telOne;
            }

            public void setTelOne(String telOne) {
                this.telOne = telOne;
            }

            public Object getTelTwo() {
                return telTwo;
            }

            public void setTelTwo(Object telTwo) {
                this.telTwo = telTwo;
            }

            public Object getTelThree() {
                return telThree;
            }

            public void setTelThree(Object telThree) {
                this.telThree = telThree;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public Object getSuperiorOperator() {
                return superiorOperator;
            }

            public void setSuperiorOperator(Object superiorOperator) {
                this.superiorOperator = superiorOperator;
            }

            public Object getDepartment() {
                return department;
            }

            public void setDepartment(Object department) {
                this.department = department;
            }

            public Object getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(Object departmentName) {
                this.departmentName = departmentName;
            }

            public Object getPosition() {
                return position;
            }

            public void setPosition(Object position) {
                this.position = position;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLastLoginDate() {
                return lastLoginDate;
            }

            public void setLastLoginDate(String lastLoginDate) {
                this.lastLoginDate = lastLoginDate;
            }

            public String getPreviousLoginDate() {
                return previousLoginDate;
            }

            public void setPreviousLoginDate(String previousLoginDate) {
                this.previousLoginDate = previousLoginDate;
            }

            public String getTimIdentifier() {
                return timIdentifier;
            }

            public void setTimIdentifier(String timIdentifier) {
                this.timIdentifier = timIdentifier;
            }

            public Object getSapCode() {
                return sapCode;
            }

            public void setSapCode(Object sapCode) {
                this.sapCode = sapCode;
            }

            public Object getSapCname() {
                return sapCname;
            }

            public void setSapCname(Object sapCname) {
                this.sapCname = sapCname;
            }

            public Object getRoles() {
                return roles;
            }

            public void setRoles(Object roles) {
                this.roles = roles;
            }

            public Object getOfficeOrderApprovalConfigs() {
                return officeOrderApprovalConfigs;
            }

            public void setOfficeOrderApprovalConfigs(Object officeOrderApprovalConfigs) {
                this.officeOrderApprovalConfigs = officeOrderApprovalConfigs;
            }

            public Object getSignInRecords() {
                return signInRecords;
            }

            public void setSignInRecords(Object signInRecords) {
                this.signInRecords = signInRecords;
            }

            public Object getAssistDepartmentSet() {
                return assistDepartmentSet;
            }

            public void setAssistDepartmentSet(Object assistDepartmentSet) {
                this.assistDepartmentSet = assistDepartmentSet;
            }

            public Object getTaskSet() {
                return taskSet;
            }

            public void setTaskSet(Object taskSet) {
                this.taskSet = taskSet;
            }

            public List<?> getOptionGroup() {
                return optionGroup;
            }

            public void setOptionGroup(List<?> optionGroup) {
                this.optionGroup = optionGroup;
            }
        }

        public static class ReceiverBean {

            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private Object fromClientType;
            private String oaCode;
            private String oaId;
            private String username;
            private boolean activated;
            private String name;
            private String nameFullPinyin;
            private String nameShortPinyin;
            private String telOne;
            private Object telTwo;
            private Object telThree;
            private Object address;
            private Object email;
            private String sex;
            private Object birthday;
            private Object superiorOperator;
            private Object department;
            private Object departmentName;
            private Object position;
            private String avatarUrl;
            private String title;
            private String lastLoginDate;
            private String previousLoginDate;
            private String timIdentifier;
            private Object sapCode;
            private Object sapCname;
            private Object roles;
            private Object officeOrderApprovalConfigs;
            private Object signInRecords;
            private Object assistDepartmentSet;
            private Object taskSet;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public boolean isActivated() {
                return activated;
            }

            public void setActivated(boolean activated) {
                this.activated = activated;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNameFullPinyin() {
                return nameFullPinyin;
            }

            public void setNameFullPinyin(String nameFullPinyin) {
                this.nameFullPinyin = nameFullPinyin;
            }

            public String getNameShortPinyin() {
                return nameShortPinyin;
            }

            public void setNameShortPinyin(String nameShortPinyin) {
                this.nameShortPinyin = nameShortPinyin;
            }

            public String getTelOne() {
                return telOne;
            }

            public void setTelOne(String telOne) {
                this.telOne = telOne;
            }

            public Object getTelTwo() {
                return telTwo;
            }

            public void setTelTwo(Object telTwo) {
                this.telTwo = telTwo;
            }

            public Object getTelThree() {
                return telThree;
            }

            public void setTelThree(Object telThree) {
                this.telThree = telThree;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public Object getSuperiorOperator() {
                return superiorOperator;
            }

            public void setSuperiorOperator(Object superiorOperator) {
                this.superiorOperator = superiorOperator;
            }

            public Object getDepartment() {
                return department;
            }

            public void setDepartment(Object department) {
                this.department = department;
            }

            public Object getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(Object departmentName) {
                this.departmentName = departmentName;
            }

            public Object getPosition() {
                return position;
            }

            public void setPosition(Object position) {
                this.position = position;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLastLoginDate() {
                return lastLoginDate;
            }

            public void setLastLoginDate(String lastLoginDate) {
                this.lastLoginDate = lastLoginDate;
            }

            public String getPreviousLoginDate() {
                return previousLoginDate;
            }

            public void setPreviousLoginDate(String previousLoginDate) {
                this.previousLoginDate = previousLoginDate;
            }

            public String getTimIdentifier() {
                return timIdentifier;
            }

            public void setTimIdentifier(String timIdentifier) {
                this.timIdentifier = timIdentifier;
            }

            public Object getSapCode() {
                return sapCode;
            }

            public void setSapCode(Object sapCode) {
                this.sapCode = sapCode;
            }

            public Object getSapCname() {
                return sapCname;
            }

            public void setSapCname(Object sapCname) {
                this.sapCname = sapCname;
            }

            public Object getRoles() {
                return roles;
            }

            public void setRoles(Object roles) {
                this.roles = roles;
            }

            public Object getOfficeOrderApprovalConfigs() {
                return officeOrderApprovalConfigs;
            }

            public void setOfficeOrderApprovalConfigs(Object officeOrderApprovalConfigs) {
                this.officeOrderApprovalConfigs = officeOrderApprovalConfigs;
            }

            public Object getSignInRecords() {
                return signInRecords;
            }

            public void setSignInRecords(Object signInRecords) {
                this.signInRecords = signInRecords;
            }

            public Object getAssistDepartmentSet() {
                return assistDepartmentSet;
            }

            public void setAssistDepartmentSet(Object assistDepartmentSet) {
                this.assistDepartmentSet = assistDepartmentSet;
            }

            public Object getTaskSet() {
                return taskSet;
            }

            public void setTaskSet(Object taskSet) {
                this.taskSet = taskSet;
            }

            public List<?> getOptionGroup() {
                return optionGroup;
            }

            public void setOptionGroup(List<?> optionGroup) {
                this.optionGroup = optionGroup;
            }
        }

        public static class CollaboratorSetBean {

            private String createdBy;
            private String createdDate;
            private String lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private Object fromClientType;
            private String oaCode;
            private String oaId;
            private String username;
            private boolean activated;
            private String name;
            private String nameFullPinyin;
            private String nameShortPinyin;
            private String telOne;
            private Object telTwo;
            private Object telThree;
            private Object address;
            private Object email;
            private String sex;
            private Object birthday;
            private Object superiorOperator;
            private Object department;
            private Object departmentName;
            private Object position;
            private String avatarUrl;
            private String title;
            private Object lastLoginDate;
            private Object previousLoginDate;
            private String timIdentifier;
            private Object sapCode;
            private Object sapCname;
            private Object roles;
            private Object officeOrderApprovalConfigs;
            private Object signInRecords;
            private Object assistDepartmentSet;
            private Object taskSet;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public boolean isActivated() {
                return activated;
            }

            public void setActivated(boolean activated) {
                this.activated = activated;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNameFullPinyin() {
                return nameFullPinyin;
            }

            public void setNameFullPinyin(String nameFullPinyin) {
                this.nameFullPinyin = nameFullPinyin;
            }

            public String getNameShortPinyin() {
                return nameShortPinyin;
            }

            public void setNameShortPinyin(String nameShortPinyin) {
                this.nameShortPinyin = nameShortPinyin;
            }

            public String getTelOne() {
                return telOne;
            }

            public void setTelOne(String telOne) {
                this.telOne = telOne;
            }

            public Object getTelTwo() {
                return telTwo;
            }

            public void setTelTwo(Object telTwo) {
                this.telTwo = telTwo;
            }

            public Object getTelThree() {
                return telThree;
            }

            public void setTelThree(Object telThree) {
                this.telThree = telThree;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public Object getSuperiorOperator() {
                return superiorOperator;
            }

            public void setSuperiorOperator(Object superiorOperator) {
                this.superiorOperator = superiorOperator;
            }

            public Object getDepartment() {
                return department;
            }

            public void setDepartment(Object department) {
                this.department = department;
            }

            public Object getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(Object departmentName) {
                this.departmentName = departmentName;
            }

            public Object getPosition() {
                return position;
            }

            public void setPosition(Object position) {
                this.position = position;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getLastLoginDate() {
                return lastLoginDate;
            }

            public void setLastLoginDate(Object lastLoginDate) {
                this.lastLoginDate = lastLoginDate;
            }

            public Object getPreviousLoginDate() {
                return previousLoginDate;
            }

            public void setPreviousLoginDate(Object previousLoginDate) {
                this.previousLoginDate = previousLoginDate;
            }

            public String getTimIdentifier() {
                return timIdentifier;
            }

            public void setTimIdentifier(String timIdentifier) {
                this.timIdentifier = timIdentifier;
            }

            public Object getSapCode() {
                return sapCode;
            }

            public void setSapCode(Object sapCode) {
                this.sapCode = sapCode;
            }

            public Object getSapCname() {
                return sapCname;
            }

            public void setSapCname(Object sapCname) {
                this.sapCname = sapCname;
            }

            public Object getRoles() {
                return roles;
            }

            public void setRoles(Object roles) {
                this.roles = roles;
            }

            public Object getOfficeOrderApprovalConfigs() {
                return officeOrderApprovalConfigs;
            }

            public void setOfficeOrderApprovalConfigs(Object officeOrderApprovalConfigs) {
                this.officeOrderApprovalConfigs = officeOrderApprovalConfigs;
            }

            public Object getSignInRecords() {
                return signInRecords;
            }

            public void setSignInRecords(Object signInRecords) {
                this.signInRecords = signInRecords;
            }

            public Object getAssistDepartmentSet() {
                return assistDepartmentSet;
            }

            public void setAssistDepartmentSet(Object assistDepartmentSet) {
                this.assistDepartmentSet = assistDepartmentSet;
            }

            public Object getTaskSet() {
                return taskSet;
            }

            public void setTaskSet(Object taskSet) {
                this.taskSet = taskSet;
            }

            public List<?> getOptionGroup() {
                return optionGroup;
            }

            public void setOptionGroup(List<?> optionGroup) {
                this.optionGroup = optionGroup;
            }
        }
    }
}
