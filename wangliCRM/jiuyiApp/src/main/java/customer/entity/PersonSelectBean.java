package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ****************************************************************
 * 文件名称:PersonSelectBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/8/26 11:12
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/8/26 1.00 初始版本
 * ****************************************************************
 */
public class PersonSelectBean implements Parcelable  {
    private String name;
    private String deptName;
    private String title="";


    protected PersonSelectBean(Parcel in) {
        name = in.readString();
        deptName = in.readString();
        title = in.readString();
        id = in.readLong();
        isCheck = in.readByte() != 0;
    }

    public static final Creator<PersonSelectBean> CREATOR = new Creator<PersonSelectBean>() {
        @Override
        public PersonSelectBean createFromParcel(Parcel in) {
            return new PersonSelectBean(in);
        }

        @Override
        public PersonSelectBean[] newArray(int size) {
            return new PersonSelectBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PersonSelectBean() {

    }

    private long id;
    private boolean isCheck=false;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(deptName);
        dest.writeString(title);
        dest.writeLong(id);
        dest.writeByte((byte) (isCheck ? 1 : 0));
    }
}
