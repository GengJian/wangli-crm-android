package customer.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.jar.Pack200;

/**
 * ****************************************************************
 * 文件名称:ImageBrowseBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/10/16 9:49
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/10/16 1.00 初始版本
 * ****************************************************************
 */
public class ImageBrowseBean implements Parcelable {
    private  String type="";
    public ImageBrowseBean() {
    }

    protected ImageBrowseBean(Parcel in) {
        type = in.readString();
        url = in.readString();
        filePath = in.readString();
    }


    public static final Creator<ImageBrowseBean> CREATOR = new Creator<ImageBrowseBean>() {
        @Override
        public ImageBrowseBean createFromParcel(Parcel in) {
            return new ImageBrowseBean(in);
        }

        @Override
        public ImageBrowseBean[] newArray(int size) {
            return new ImageBrowseBean[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private String url="";
    private String filePath="";


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(url);
        dest.writeString(filePath);
    }
}
