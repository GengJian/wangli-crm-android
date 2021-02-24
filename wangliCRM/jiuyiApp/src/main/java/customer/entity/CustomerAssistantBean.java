package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ****************************************************************
 * 文件名称:CustomerAssistantBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/3/13 13:36
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/3/13 1.00 初始版本
 * ****************************************************************
 */
public class CustomerAssistantBean implements Parcelable{


    private String assistRole;

    public CustomerAssistantBean() {
    }

    protected CustomerAssistantBean(Parcel in) {
        assistRole = in.readString();
        assistReason = in.readString();
        createdDate = in.readString();
        lastModifiedDate = in.readString();
        createdBy = in.readString();
        lastModifiedBy = in.readString();
        member = in.readParcelable(MemberBeanID.class.getClassLoader());
        id = in.readLong();
        operator = in.readParcelable(ClientListBean.ContentBean.OperatorBean.class.getClassLoader());
    }

    public static final Creator<CustomerAssistantBean> CREATOR = new Creator<CustomerAssistantBean>() {
        @Override
        public CustomerAssistantBean createFromParcel(Parcel in) {
            return new CustomerAssistantBean(in);
        }

        @Override
        public CustomerAssistantBean[] newArray(int size) {
            return new CustomerAssistantBean[size];
        }
    };

    public String getAssistRole() {
        return assistRole;
    }

    public void setAssistRole(String assistRole) {
        this.assistRole = assistRole;
    }

    public String getAssistReason() {
        return assistReason;
    }

    public void setAssistReason(String assistReason) {
        this.assistReason = assistReason;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClientListBean.ContentBean.OperatorBean getOperator() {
        return operator;
    }

    public void setOperator(ClientListBean.ContentBean.OperatorBean operator) {
        this.operator = operator;
    }

    private String assistReason;
    private String createdDate;
    private String lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
    private MemberBeanID member;
    private long id;
    private ClientListBean.ContentBean.OperatorBean operator;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(assistRole);
        dest.writeString(assistReason);
        dest.writeString(createdDate);
        dest.writeString(lastModifiedDate);
        dest.writeString(createdBy);
        dest.writeString(lastModifiedBy);
        dest.writeParcelable(member, flags);
        dest.writeLong(id);
        dest.writeParcelable(operator, flags);
    }
}
