package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ReceiveAddressParcelBean implements Parcelable {
    private String createdBy;
    private String createdDate;
    private String lastModifiedBy;
    private String lastModifiedDate;
    private long id;
    private boolean deleted;
    private int sort;
    private String abbreviation;

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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getPhoneOne() {
        return phoneOne;
    }

    public void setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
    }

    public String getProvinceNumber() {
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

    public String getCityNumber() {
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public boolean isDefaults() {
        return defaults;
    }

    public void setDefaults(boolean defaults) {
        this.defaults = defaults;
    }

    private String phoneOne;
    private String provinceNumber;
    private String provinceName;
    private String cityNumber;
    private String cityName;
    private String areaNumber;
    private String areaName;
    private String address;
    private String receiver;
    private boolean defaults;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createdBy);
        dest.writeString(this.createdDate);
        dest.writeString(this.lastModifiedBy);
        dest.writeString(this.lastModifiedDate);
        dest.writeLong(this.id);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeInt(this.sort);
        dest.writeString(this.abbreviation);
        dest.writeString(this.phoneOne);
        dest.writeString(this.provinceNumber);
        dest.writeString(this.provinceName);
        dest.writeString(this.cityNumber);
        dest.writeString(this.cityName);
        dest.writeString(this.areaNumber);
        dest.writeString(this.areaName);
        dest.writeString(this.address);
        dest.writeString(this.receiver);
        dest.writeByte(this.defaults ? (byte) 1 : (byte) 0);
    }

    public ReceiveAddressParcelBean() {
    }

    protected ReceiveAddressParcelBean(Parcel in) {
        this.createdBy = in.readString();
        this.createdDate = in.readString();
        this.lastModifiedBy = in.readString();
        this.lastModifiedDate = in.readString();
        this.id = in.readLong();
        this.deleted = in.readByte() != 0;
        this.sort = in.readInt();
        this.abbreviation = in.readString();
        this.phoneOne = in.readString();
        this.provinceNumber = in.readString();
        this.provinceName = in.readString();
        this.cityNumber = in.readString();
        this.cityName = in.readString();
        this.areaNumber = in.readString();
        this.areaName = in.readString();
        this.address = in.readString();
        this.receiver = in.readString();
        this.defaults = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ReceiveAddressParcelBean> CREATOR = new Parcelable.Creator<ReceiveAddressParcelBean>() {
        @Override
        public ReceiveAddressParcelBean createFromParcel(Parcel source) {
            return new ReceiveAddressParcelBean(source);
        }

        @Override
        public ReceiveAddressParcelBean[] newArray(int size) {
            return new ReceiveAddressParcelBean[size];
        }
    };
}
