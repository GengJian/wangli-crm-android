package commonlyused.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import customer.entity.SexEnum;

public class LinkmanBean implements Parcelable {
    private String name;
    private SexEnum sex;
    private String birthday;
    private String email;
    private String address;
    private String timIdentifier;
    private String letters;//显示拼音的首字母
    private String telOne; //电话1
    private String org;
    private String dept;
    private String duty;
    private String telTwo; //电话2
    private String telThree; //电话3
    private String office;

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    private boolean important;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static Creator<LinkmanBean> getCREATOR() {
        return CREATOR;
    }

    private long id;

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimIdentifier() {
        return timIdentifier;
    }

    public void setTimIdentifier(String timIdentifier) {
        this.timIdentifier = timIdentifier;
    }


    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }



    public String getTelOne() {
        return telOne;
    }

    public void setTelOne(String telOne) {
        this.telOne = telOne;
    }

    public String getTelTwo() {
        return telTwo;
    }

    public void setTelTwo(String telTwo) {
        this.telTwo = telTwo;
    }

    public String getTelThree() {
        return telThree;
    }

    public void setTelThree(String telThree) {
        this.telThree = telThree;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public LinkmanBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.sex == null ? -1 : this.sex.ordinal());
        dest.writeString(this.birthday);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeString(this.timIdentifier);
        dest.writeString(this.letters);
        dest.writeString(this.telOne);
        dest.writeString(this.org);
        dest.writeString(this.dept);
        dest.writeString(this.duty);
        dest.writeString(this.telTwo);
        dest.writeString(this.telThree);
        dest.writeByte(this.important ? (byte) 1 : (byte) 0);
        dest.writeLong(this.id);
    }

    protected LinkmanBean(Parcel in) {
        this.name = in.readString();
        int tmpSex = in.readInt();
        this.sex = tmpSex == -1 ? null : SexEnum.values()[tmpSex];
        this.birthday = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.timIdentifier = in.readString();
        this.letters = in.readString();
        this.telOne = in.readString();
        this.org = in.readString();
        this.dept = in.readString();
        this.duty = in.readString();
        this.telTwo = in.readString();
        this.telThree = in.readString();
        this.important = in.readByte() != 0;
        this.id = in.readLong();
    }

    public static final Creator<LinkmanBean> CREATOR = new Creator<LinkmanBean>() {
        @Override
        public LinkmanBean createFromParcel(Parcel source) {
            return new LinkmanBean(source);
        }

        @Override
        public LinkmanBean[] newArray(int size) {
            return new LinkmanBean[size];
        }
    };
}
