package customer.entity;

import android.graphics.Rect;
import android.os.Parcel;
import android.support.annotation.Nullable;

import com.previewlibrary.enitity.IThumbViewInfo;

/**
 *
 * @author yangc
 * @date 2017/4/26
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 图片预览实体类
 **/
public class UserViewInfo implements IThumbViewInfo {

    private String url;  //图片地址
    private Rect mBounds; // 记录坐标
    private String user = "用户字段";
     private String videoUrl;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /** 缩略图地址*/
    private String thumbnail;

    public long getTraceId() {
        return traceId;
    }


    public void setTraceId(long traceId) {
        this.traceId = traceId;
    }

    private long traceId;

    public UserViewInfo( String url) {
        this.url = url;
    }
    public UserViewInfo(int traceId, String url) {
        this.url = url;
        this.traceId = traceId;
    }
    public UserViewInfo(long traceId, String url,String thumbnail) {
        this.url = url;
        this.traceId = traceId;
        this.thumbnail = thumbnail;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getUrl() {//将你的图片地址字段返回
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Rect getBounds() {//将你的图片显示坐标字段返回
        return mBounds;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setBounds(Rect bounds) {
        mBounds = bounds;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.mBounds, flags);
        dest.writeString(this.user);
        dest.writeString(this.videoUrl);
    }

    protected UserViewInfo(Parcel in) {
        this.url = in.readString();
        this.mBounds = in.readParcelable(Rect.class.getClassLoader());
        this.user = in.readString();
        this.videoUrl = in.readString();
    }

    public static final Creator<UserViewInfo> CREATOR = new Creator<UserViewInfo>() {
        @Override
        public UserViewInfo createFromParcel(Parcel source) {
            return new UserViewInfo(source);
        }

        @Override
        public UserViewInfo[] newArray(int size) {
            return new UserViewInfo[size];
        }
    };
}
