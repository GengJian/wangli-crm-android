package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ****************************************************************
 * 文件名称:NewSpecialProductBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/8/11 9:09
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/8/11 1.00 初始版本
 * ****************************************************************
 */
public class NewSpecialProductBean implements Parcelable   {
    private String batchNum;

    public NewSpecialProductBean() {
    }

    public NewSpecialProductBean(String batchNum, String levelName, String levelCode, Double specialPrice, int viewType, Double count) {
        this.batchNum = batchNum;
        this.levelName = levelName;
        this.levelCode = levelCode;
        this.specialPrice = specialPrice;
        this.viewType = viewType;
        this.count = count;
    }

    protected NewSpecialProductBean(Parcel in) {
        batchNum = in.readString();
        levelName = in.readString();
        levelCode = in.readString();
        if (in.readByte() == 0) {
            specialPrice = null;
        } else {
            specialPrice = in.readDouble();
        }
        viewType = in.readInt();
        if (in.readByte() == 0) {
            count = null;
        } else {
            count = in.readDouble();
        }
    }

    public static final Creator<NewSpecialProductBean> CREATOR = new Creator<NewSpecialProductBean>() {
        @Override
        public NewSpecialProductBean createFromParcel(Parcel in) {
            return new NewSpecialProductBean(in);
        }

        @Override
        public NewSpecialProductBean[] newArray(int size) {
            return new NewSpecialProductBean[size];
        }
    };

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public Double getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    private String levelName;
    private String levelCode;
    private Double specialPrice;
    private int viewType;
    private Double count;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(batchNum);
        dest.writeString(levelName);
        dest.writeString(levelCode);
        if (specialPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(specialPrice);
        }
        dest.writeInt(viewType);
        if (count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(count);
        }
    }
}
