package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class LinkmanNewBean implements Parcelable {
    public LinkmanNewBean() {
    }

    private String createdBy;

    protected LinkmanNewBean(Parcel in) {
        createdBy = in.readString();
        createdDate = in.readString();
        lastModifiedBy = in.readString();
        lastModifiedDate = in.readString();
        id = in.readLong();
        fromClientType = in.readString();
        name = in.readString();
        birthday = in.readString();
        phone = in.readString();
        phoneTwo = in.readString();
        phoneThree = in.readString();
        phoneFour = in.readString();
        email = in.readString();
        member = in.readParcelable(MemberBeanID.class.getClassLoader());
        duty = in.readString();
        favorite = in.readString();
        avatralUrl = in.readString();
        simpleSpell = in.readString();
        sex = in.readString();
        officeAddress = in.readString();
        areaNumber = in.readString();
        areaName = in.readString();
        address = in.readString();
        office = in.readParcelable(DepartmentBean.DepartmentBeanID.class.getClassLoader());
        incumbency = in.readByte() != 0;
    }

    public static final Creator<LinkmanNewBean> CREATOR = new Creator<LinkmanNewBean>() {
        @Override
        public LinkmanNewBean createFromParcel(Parcel in) {
            return new LinkmanNewBean(in);
        }

        @Override
        public LinkmanNewBean[] newArray(int size) {
            return new LinkmanNewBean[size];
        }
    };

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

    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }

    public void setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    public String getPhoneThree() {
        return phoneThree;
    }

    public void setPhoneThree(String phoneThree) {
        this.phoneThree = phoneThree;
    }

    public String getPhoneFour() {
        return phoneFour;
    }

    public void setPhoneFour(String phoneFour) {
        this.phoneFour = phoneFour;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberBeanID getMember() {
        return member;
    }

    public void setMember(MemberBeanID member) {
        this.member = member;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getAvatralUrl() {
        return avatralUrl;
    }

    public void setAvatralUrl(String avatralUrl) {
        this.avatralUrl = avatralUrl;
    }

    public String getSimpleSpell() {
        return simpleSpell;
    }

    public void setSimpleSpell(String simpleSpell) {
        this.simpleSpell = simpleSpell;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DepartmentBean.DepartmentBeanID getOffice() {
        return office;
    }

    public void setOffice(DepartmentBean.DepartmentBeanID office) {
        this.office = office;
    }

    public boolean isIncumbency() {
        return incumbency;
    }

    public void setIncumbency(boolean incumbency) {
        this.incumbency = incumbency;
    }

    private String createdDate;
    private String lastModifiedBy;
    private String lastModifiedDate;
    private long id;
    private String fromClientType;
    private String name;
    private String birthday;
    private String phone;
    private String phoneTwo;
    private String phoneThree;
    private String phoneFour;
    private String email;
    private MemberBeanID member;
    private String duty;
    private String favorite;
    private String avatralUrl;
    private String simpleSpell;
    private String sex;
    private String officeAddress;
    private String areaNumber;
    private String areaName;
    private String address;
    private DepartmentBean.DepartmentBeanID office;
    private boolean incumbency=true;

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
        dest.writeString(fromClientType);
        dest.writeString(name);
        dest.writeString(birthday);
        dest.writeString(phone);
        dest.writeString(phoneTwo);
        dest.writeString(phoneThree);
        dest.writeString(phoneFour);
        dest.writeString(email);
        dest.writeParcelable(member, flags);
        dest.writeString(duty);
        dest.writeString(favorite);
        dest.writeString(avatralUrl);
        dest.writeString(simpleSpell);
        dest.writeString(sex);
        dest.writeString(officeAddress);
        dest.writeString(areaNumber);
        dest.writeString(areaName);
        dest.writeString(address);
        dest.writeParcelable(office, flags);
        dest.writeByte((byte) (incumbency ? 1 : 0));
    }
}
