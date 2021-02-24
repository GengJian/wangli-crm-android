package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class LinkmanSortModel implements Parcelable {


    private String letters;//显示拼音的首字母
    private String createdBy;
    private String createdDate;
    private String lastModifiedBy;
    private String lastModifiedDate;
    private long id;
    private boolean deleted;
    private int sort;
    private String type;
    private String name;
    private String title;
    private Object member;
    private String phoneOne;
    private String phoneTwo;
    private String phoneThree;
    private String phoneFour;
    private SexEnum sex;
    private String birthday;
    private String email;
    private String address;
    private String provinceNumber;
    private String provinceName;
    private String cityNumber;
    private String cityName;
    private String office;
    private String duty;
    private String headAddress;
    private Object actualControllers;
//    private boolean import=false;

    public LinkmanSortModel(){

    }



    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getMember() {
        return member;
    }

    public void setMember(Object member) {
        this.member = member;
    }

    public String getPhoneOne() {
        return phoneOne;
    }

    public void setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
    }

    public String getPhoneTwo() {
        return phoneTwo;
    }

    public void setPhoneTwo(String phoneTwo) {
        this.phoneTwo = phoneTwo;
    }

    public Object getPhoneThree() {
        return phoneThree;
    }

    public void setPhoneThree(String phoneThree) {
        this.phoneThree = phoneThree;
    }

    public Object getPhoneFour() {
        return phoneFour;
    }

    public void setPhoneFour(String phoneFour) {
        this.phoneFour = phoneFour;
    }

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

    public Object getProvinceNumber() {
        return provinceNumber;
    }

    public void setProvinceNumber(String provinceNumber) {
        this.provinceNumber = provinceNumber;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Object getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(String cityNumber) {
        this.cityNumber = cityNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Object getHeadAddress() {
        return headAddress;
    }

    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress;
    }

    public Object getActualControllers() {
        return actualControllers;
    }

    public void setActualControllers(Object actualControllers) {
        this.actualControllers = actualControllers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.letters);
        dest.writeString(this.createdBy);
        dest.writeString(this.createdDate);
        dest.writeString(this.lastModifiedBy);
        dest.writeString(this.lastModifiedDate);
        dest.writeLong(this.id);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeInt(this.sort);
        dest.writeString(this.type);
        dest.writeString(this.name);
        dest.writeString(this.title);

        dest.writeString(this.phoneOne);
        dest.writeString(this.phoneTwo);
        dest.writeString(this.phoneThree);
        dest.writeString(this.phoneFour);
        dest.writeInt(this.sex == null ? -1 : this.sex.ordinal());
        dest.writeString(this.birthday);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeString(this.provinceNumber);
        dest.writeString(this.provinceName);
        dest.writeString(this.cityNumber);
        dest.writeString(this.cityName);
        dest.writeString(this.office);
        dest.writeString(this.duty);
        dest.writeString(this.headAddress);

    }

    protected LinkmanSortModel(Parcel in) {
        this.letters = in.readString();
        this.createdBy = in.readString();
        this.createdDate = in.readString();
        this.lastModifiedBy = in.readString();
        this.lastModifiedDate = in.readString();
        this.id = in.readLong();
        this.deleted = in.readByte() != 0;
        this.sort = in.readInt();
        this.type = in.readString();
        this.name = in.readString();
        this.title = in.readString();
        this.member = in.readParcelable(Object.class.getClassLoader());
        this.phoneOne = in.readString();
        this.phoneTwo = in.readString();
        this.phoneThree = in.readString();
        this.phoneFour = in.readString();
        int tmpSex = in.readInt();
        this.sex = tmpSex == -1 ? null : SexEnum.values()[tmpSex];
        this.birthday = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.provinceNumber = in.readString();
        this.provinceName = in.readString();
        this.cityNumber = in.readString();
        this.cityName = in.readString();
        this.office = in.readString();
        this.duty = in.readString();
        this.headAddress = in.readString();
        this.actualControllers = in.readParcelable(Object.class.getClassLoader());
    }

    public static final Creator<LinkmanSortModel> CREATOR = new Creator<LinkmanSortModel>() {
        @Override
        public LinkmanSortModel createFromParcel(Parcel source) {
            return new LinkmanSortModel(source);
        }

        @Override
        public LinkmanSortModel[] newArray(int size) {
            return new LinkmanSortModel[size];
        }
    };
}
