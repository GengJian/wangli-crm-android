package customer.entity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Parcel;
import android.support.annotation.Nullable;

@SuppressLint("ParcelCreator")
public class ImageViewBean implements IThumbViewInfo {
    public void setUrl(String url) {
        this.url = url;
    }

    public Rect getmBounds() {
        return mBounds;
    }

    public void setmBounds(Rect mBounds) {
        this.mBounds = mBounds;
    }

    private String url;  //图片地址
    private Rect mBounds; // 记录坐标
    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public Rect getBounds() {
        return null;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.mBounds, flags);
    }

    public ImageViewBean() {
    }

    protected ImageViewBean(Parcel in) {
        this.url = in.readString();
        this.mBounds = in.readParcelable(Rect.class.getClassLoader());
    }

    public static final Creator<ImageViewBean> CREATOR = new Creator<ImageViewBean>() {
        @Override
        public ImageViewBean createFromParcel(Parcel source) {
            return new ImageViewBean(source);
        }

        @Override
        public ImageViewBean[] newArray(int size) {
            return new ImageViewBean[size];
        }
    };
}
