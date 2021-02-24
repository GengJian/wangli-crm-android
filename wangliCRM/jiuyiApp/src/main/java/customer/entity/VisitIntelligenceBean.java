package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * 文件名称:VisitIntelligenceBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/11 16:01
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/11 1.00 初始版本
 * ****************************************************************
 */
public class VisitIntelligenceBean implements Parcelable {

    public VisitIntelligenceBean() {
    }

    protected VisitIntelligenceBean(Parcel in) {
        id = in.readLong();
        content = in.readString();
        intelligence = in.readParcelable(IntelligenceBean.class.getClassLoader());
        itemStatusKey = in.readString();
        member = in.readParcelable(MemberBeanID.class.getClassLoader());
        intelligenceTypeKey = in.readString();
        intelligenceInfoKey = in.readString();
        intelligenceInfoValue = in.readString();
        intelligenceTypeValue = in.readString();
        itemStatusValue = in.readString();
        attachmentList = in.createTypedArrayList(AttachmentBean.CREATOR);
        viewType = in.readInt();
    }

    public static final Creator<VisitIntelligenceBean> CREATOR = new Creator<VisitIntelligenceBean>() {
        @Override
        public VisitIntelligenceBean createFromParcel(Parcel in) {
            return new VisitIntelligenceBean(in);
        }

        @Override
        public VisitIntelligenceBean[] newArray(int size) {
            return new VisitIntelligenceBean[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public MemberBeanID getMember() {
        return member;
    }

    public void setMember(MemberBeanID member) {
        this.member = member;
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

    private long id;
    private String content;
    private IntelligenceBean intelligence;
    private String itemStatusKey;
    private MemberBeanID member;
    private String intelligenceTypeKey;
    private String intelligenceInfoKey;
    private String intelligenceInfoValue;
    private String intelligenceTypeValue;
    private String itemStatusValue;
    private ArrayList<AttachmentBean> attachmentList;
    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(content);
        dest.writeParcelable(intelligence, flags);
        dest.writeString(itemStatusKey);
        dest.writeParcelable(member, flags);
        dest.writeString(intelligenceTypeKey);
        dest.writeString(intelligenceInfoKey);
        dest.writeString(intelligenceInfoValue);
        dest.writeString(intelligenceTypeValue);
        dest.writeString(itemStatusValue);
        dest.writeTypedList(attachmentList);
        dest.writeInt(viewType);
    }


    public static class IntelligenceBean implements Parcelable {
        protected IntelligenceBean(Parcel in) {
            id = in.readLong();
            statusKey = in.readString();
            statusValue = in.readString();
            collectDate = in.readString();
            member = in.readParcelable(MemberBeanID.class.getClassLoader());
            bigCategoryKey = in.readString();
            businessTypeKey = in.readString();
            bigCategoryValue = in.readString();
            businessTypeValue = in.readString();
        }

        public static final Creator<IntelligenceBean> CREATOR = new Creator<IntelligenceBean>() {
            @Override
            public IntelligenceBean createFromParcel(Parcel in) {
                return new IntelligenceBean(in);
            }

            @Override
            public IntelligenceBean[] newArray(int size) {
                return new IntelligenceBean[size];
            }
        };

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        public String getCollectDate() {
            return collectDate;
        }

        public void setCollectDate(String collectDate) {
            this.collectDate = collectDate;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
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

        /**
         * id : 61
         * sort : 10
         * createdDate : 2019-01-10 14:13:28
         * lastModifiedDate : 2019-01-10 14:13:28
         * createdBy : 潘梦洋
         * lastModifiedBy : 潘梦洋
         * statusKey :
         * statusValue :
         * collectDate : 2019-01-10
         * member : {"id":2313,"sort":10,"createdDate":"2019-01-09 22:07:13","lastModifiedDate":"2019-01-10 18:05:01","createdBy":"系统自动创建","lastModifiedBy":"系统自动创建","statusKey":"formal","abbreviation":"广州西陇化工","orgName":"广州市西陇化工有限公司","cooperationTypeValue":"供应商","memberLevelValue":"普通","employeeSizeValue":"","companyTypeValue":"","customerDemand":"","creditModifyDate":0,"office":{},"dealer":{},"simpleSpell":"gzxlhg","avatarUrl":"","statusValue":"正式","crmNumber":"200522","riskLevelKey":"","registeredCapital":0,"provinceName":"","frOperatorName":"未分配","srOperatorName":"未分配","creditLevelValue":"","riskLevelValue":"","sapNumber":"200522","arOperatorName":"未分配"}
         * bigCategoryKey : customer
         * businessTypeKey : wafer_factory
         * bigCategoryValue : 客户
         * businessTypeValue : 硅片厂
         * operator : {"id":1,"sort":10,"createdDate":"2018-11-29 21:15:42","lastModifiedDate":"2019-01-11 15:48:49","createdBy":"15167156690","lastModifiedBy":"潘梦洋","name":"潘梦洋","username":"1022654","title":"市场专员(z)","departmentName":"","oaCode":"1022654","oaId":"2381","department":{"id":78,"sort":200,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 13:53:54","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","desp":"","oaDepartmentId":"415"}}
         */

        private long id;
        private String statusKey;
        private String statusValue;
        private String collectDate;
        private MemberBeanID member;
        private String bigCategoryKey;
        private String businessTypeKey;
        private String bigCategoryValue;
        private String businessTypeValue;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(statusKey);
            dest.writeString(statusValue);
            dest.writeString(collectDate);
            dest.writeParcelable(member, flags);
            dest.writeString(bigCategoryKey);
            dest.writeString(businessTypeKey);
            dest.writeString(bigCategoryValue);
            dest.writeString(businessTypeValue);
        }
    }


}
