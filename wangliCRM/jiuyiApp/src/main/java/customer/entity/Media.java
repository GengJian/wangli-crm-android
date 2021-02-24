package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 *
 * @author dmcBig
 * @date 2017/7/4
 */

public class Media  implements  Parcelable {
    public String path;

    public Media() {
    }


    protected Media(Parcel in) {
        path = in.readString();
        name = in.readString();
        extension = in.readString();
        time = in.readLong();
        mediaType = in.readInt();
        size = in.readLong();
        id = in.readInt();
        parentDir = in.readString();
        thumbnailPath = in.readString();
        qiniuKey = in.readString();
        isShowIcon = in.readByte() != 0;
        fileTime = in.readString();
        url = in.readString();
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String name;
    public String extension;
    public long time;
    public int mediaType;
    public long size;
    public int id;
    public String parentDir;
    private String thumbnailPath;
    private String qiniuKey="";
    private boolean isShowIcon=true;

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    private String fileTime="";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getQiniuKey() {
        return qiniuKey;
    }

    public void setQiniuKey(String qiniuKey) {
        this.qiniuKey = qiniuKey;
    }


    public boolean isShowIcon() {
        return isShowIcon;
    }

    public void setShowIcon(boolean showIcon) {
        isShowIcon = showIcon;
    }



    public Media(String path, String name, long time, int mediaType, long size, int id, String parentDir) {
        this.path = path;
        this.name = name;
        if (!TextUtils.isEmpty(name) && name.indexOf(".") != -1) {
            this.extension = name.substring(name.lastIndexOf("."), name.length());
        } else {
            this.extension = "null";
        }
        this.time = time;
        this.mediaType = mediaType;
        this.size = size;
        this.id = id;
        this.parentDir = parentDir;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(name);
        dest.writeString(extension);
        dest.writeLong(time);
        dest.writeInt(mediaType);
        dest.writeLong(size);
        dest.writeInt(id);
        dest.writeString(parentDir);
        dest.writeString(thumbnailPath);
        dest.writeString(qiniuKey);
        dest.writeByte((byte) (isShowIcon ? 1 : 0));
        dest.writeString(fileTime);
        dest.writeString(url);
    }
}
