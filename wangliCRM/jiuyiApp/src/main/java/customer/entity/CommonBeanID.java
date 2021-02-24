package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ****************************************************************
 * 文件名称:CommonBeanID.java
 * 作    者:Created by zhengss
 * 创建时间:2019/5/29 15:41
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/5/29 1.00 初始版本
 * ****************************************************************
 */
public class CommonBeanID implements Parcelable{
    protected CommonBeanID(Parcel in) {
        id = in.readLong();
    }

    public CommonBeanID() {
    }

    public static final Creator<CommonBeanID> CREATOR = new Creator<CommonBeanID>() {
        @Override
        public CommonBeanID createFromParcel(Parcel in) {
            return new CommonBeanID(in);
        }

        @Override
        public CommonBeanID[] newArray(int size) {
            return new CommonBeanID[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
    }
}
