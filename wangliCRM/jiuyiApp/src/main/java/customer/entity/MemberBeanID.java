package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MemberBeanID implements Parcelable {
    public MemberBeanID() {
    }

    /**
     * id : 20

     */

    private long id;

    protected MemberBeanID(Parcel in) {
        id = in.readLong();
        orgName = in.readString();
        abbreviation = in.readString();
    }

    public static final Creator<MemberBeanID> CREATOR = new Creator<MemberBeanID>() {
        @Override
        public MemberBeanID createFromParcel(Parcel in) {
            return new MemberBeanID(in);
        }

        @Override
        public MemberBeanID[] newArray(int size) {
            return new MemberBeanID[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    private String orgName;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    private String abbreviation;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(orgName);
        dest.writeString(abbreviation);
    }
}
