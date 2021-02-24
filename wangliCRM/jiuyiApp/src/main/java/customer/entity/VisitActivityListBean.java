package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:VisitActivityListBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/10 13:21
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/10 1.00 初始版本
 * ****************************************************************
 */
public class VisitActivityListBean implements  Parcelable  {


    protected VisitActivityListBean(Parcel in) {
        id = in.readLong();
        sort = in.readInt();
        createdDate = in.readString();
        lastModifiedDate = in.readString();
        createdBy = in.readString();
        lastModifiedBy = in.readString();
        address = in.readString();
        status = in.readString();
        endDate = in.readString();
        beginDate = in.readString();
        remark = in.readString();
        communicateRecord = in.readString();
        member = in.readParcelable(MemberBeanID.class.getClassLoader());
        visitNumber = in.readString();
        purpose = in.readString();
        signInRecord = in.readString();
        signInInfo = in.readString();
        visitReport = in.readString();
        statusValue = in.readString();
        attachmentList = in.createTypedArrayList(AttachmentBean.CREATOR);
        intelligenceItemList = in.createTypedArrayList(AttachmentBean.CREATOR);
    }

    public static final Creator<VisitActivityListBean> CREATOR = new Creator<VisitActivityListBean>() {
        @Override
        public VisitActivityListBean createFromParcel(Parcel in) {
            return new VisitActivityListBean(in);
        }

        @Override
        public VisitActivityListBean[] newArray(int size) {
            return new VisitActivityListBean[size];
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCommunicateRecord() {
        return communicateRecord;
    }

    public void setCommunicateRecord(String communicateRecord) {
        this.communicateRecord = communicateRecord;
    }

    public MemberBeanID getMember() {
        return member;
    }

    public void setMember(MemberBeanID member) {
        this.member = member;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSignInRecord() {
        return signInRecord;
    }

    public void setSignInRecord(String signInRecord) {
        this.signInRecord = signInRecord;
    }

    public String getSignInInfo() {
        return signInInfo;
    }

    public void setSignInInfo(String signInInfo) {
        this.signInInfo = signInInfo;
    }

    public String getVisitReport() {
        return visitReport;
    }

    public void setVisitReport(String visitReport) {
        this.visitReport = visitReport;
    }

    public VisitorBean getVisitor() {
        return visitor;
    }

    public void setVisitor(VisitorBean visitor) {
        this.visitor = visitor;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public List<AttachmentBean> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentBean> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<AttachmentBean> getIntelligenceItemList() {
        return intelligenceItemList;
    }

    public void setIntelligenceItemList(List<AttachmentBean> intelligenceItemList) {
        this.intelligenceItemList = intelligenceItemList;
    }

    /**
     * id : 14
     * sort : 10
     * createdDate : 2019-01-08 16:07:49
     * lastModifiedDate : 2019-01-08 16:07:49
     * createdBy : 刘启明
     * lastModifiedBy : 刘启明
     * address :
     * status : draft
     * endDate : 2019-01-09 16:07:00
     * beginDate : 2019-01-01 16:07:00
     * attachmentList : []
     * remark : 333
     * communicateRecord : 22
     * member : {"id":896,"sort":1,"createdDate":"2018-12-29 18:27:05","lastModifiedDate":"2019-01-09 22:04:05","createdBy":"系统自动创建","lastModifiedBy":"系统自动创建","statusKey":"formal","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","avatarUrl":"","sapNumber":"200567","provinceName":"","riskLevelKey":"","dealer":{},"simpleSpell":"dgsgr","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配","creditLevelValue":"","riskLevelValue":"","statusValue":"正式","crmNumber":"200567","office":{},"cooperationTypeValue":"供应商","memberLevelValue":"","employeeSizeValue":"","companyTypeValue":"","customerDemand":"","creditModifyDate":0,"registeredCapital":0}
     * visitNumber : AXBF20190108001
     * purpose : 拜访1
     * signInRecord :
     * signInInfo : 1111
     * visitReport : 33
     * visitor : {"id":1,"sort":10,"createdDate":"2018-11-29 21:15:42","lastModifiedDate":"2019-01-10 11:06:30","createdBy":"15167156690","lastModifiedBy":"刘启明","name":"刘启明","oaId":"","username":"","title":"营销分析员","oaCode":"1022654","department":{"id":1,"sort":100,"createdDate":"2018-11-29 21:14:56","lastModifiedDate":"2018-12-26 21:46:18","createdBy":"15167156690","lastModifiedBy":"15167156690","name":"营销部-禁用","desp":"","oaDepartmentId":""},"departmentName":""}
     * statusValue : 草稿
     * intelligenceItemList : []
     */

    private long id;
    private int sort;
    private String createdDate;
    private String lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
    private String address;
    private String status;
    private String endDate;
    private String beginDate;
    private String remark;
    private String communicateRecord;
    private MemberBeanID member;
    private String visitNumber;
    private String purpose;
    private String signInRecord;
    private String signInInfo;
    private String visitReport;
    private VisitorBean visitor;
    private String statusValue;
    private List<AttachmentBean> attachmentList;
    private List<AttachmentBean> intelligenceItemList;

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
        dest.writeString(address);
        dest.writeString(status);
        dest.writeString(endDate);
        dest.writeString(beginDate);
        dest.writeString(remark);
        dest.writeString(communicateRecord);
        dest.writeParcelable(member, flags);
        dest.writeString(visitNumber);
        dest.writeString(purpose);
        dest.writeString(signInRecord);
        dest.writeString(signInInfo);
        dest.writeString(visitReport);
        dest.writeString(statusValue);
        dest.writeTypedList(attachmentList);
        dest.writeTypedList(intelligenceItemList);
    }


    public static class VisitorBean {
        /**
         * id : 1
         * sort : 10
         * createdDate : 2018-11-29 21:15:42
         * lastModifiedDate : 2019-01-10 11:06:30
         * createdBy : 15167156690
         * lastModifiedBy : 刘启明
         * name : 刘启明
         * oaId :
         * username :
         * title : 营销分析员
         * oaCode : 1022654
         * department : {"id":1,"sort":100,"createdDate":"2018-11-29 21:14:56","lastModifiedDate":"2018-12-26 21:46:18","createdBy":"15167156690","lastModifiedBy":"15167156690","name":"营销部-禁用","desp":"","oaDepartmentId":""}
         * departmentName :
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String name;
        private String oaId;
        private String username;
        private String title;
        private String oaCode;
        private DepartmentBean department;
        private String departmentName;

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

        public DepartmentBean getDepartment() {
            return department;
        }

        public void setDepartment(DepartmentBean department) {
            this.department = department;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public static class DepartmentBean {
            /**
             * id : 1
             * sort : 100
             * createdDate : 2018-11-29 21:14:56
             * lastModifiedDate : 2018-12-26 21:46:18
             * createdBy : 15167156690
             * lastModifiedBy : 15167156690
             * name : 营销部-禁用
             * desp :
             * oaDepartmentId :
             */

            private long id;
            private int sort;
            private String createdDate;
            private String lastModifiedDate;
            private String createdBy;
            private String lastModifiedBy;
            private String name;
            private String desp;
            private String oaDepartmentId;

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
        }
    }
}
