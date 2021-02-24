package customer.entity;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import com.control.utils.Func;

import java.util.Objects;

public class AttachmentBean implements Parcelable  {
    private String createdBy;
    private String createdDate;
    private String lastModifiedBy;

    public AttachmentBean() {
    }


    protected AttachmentBean(Parcel in) {
        createdBy = in.readString();
        createdDate = in.readString();
        lastModifiedBy = in.readString();
        lastModifiedDate = in.readString();
        id = in.readLong();
        fromClientType = in.readString();
        fkType = in.readString();
        fkId = in.readLong();
        qiniuKey = in.readString();
        fileName = in.readString();
        fileType = in.readString();
        fileSize = in.readString();
        sizeOfByte = in.readLong();
        url = in.readString();
        yyyymm = in.readString();
        extData = in.readString();
        thumbnail = in.readString();
        key = in.readString();
    }

    public static final Creator<AttachmentBean> CREATOR = new Creator<AttachmentBean>() {
        @Override
        public AttachmentBean createFromParcel(Parcel in) {
            return new AttachmentBean(in);
        }

        @Override
        public AttachmentBean[] newArray(int size) {
            return new AttachmentBean[size];
        }
    };

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




    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    public String getFkType() {
        return fkType;
    }

    public void setFkType(String fkType) {
        this.fkType = fkType;
    }

    public Long getFkId() {
        return fkId;
    }

    public void setFkId(Long fkId) {
        this.fkId = fkId;
    }

    public String getQiniuKey() {
        return qiniuKey;
    }

    public void setQiniuKey(String qiniuKey) {
        this.qiniuKey = qiniuKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String lastModifiedDate;
    private Long id=null;
    private String fromClientType;
    private String fkType;
    private Long fkId=null;
    private String qiniuKey;
    private String fileName;
    private String fileType;
    private String fileSize;
    private long sizeOfByte;
    private String url;

    public String getYyyymm() {
        return yyyymm;
    }

    public void setYyyymm(String yyyymm) {
        this.yyyymm = yyyymm;
    }

    private String yyyymm;

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData;
    }

    private String extData ="";

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /** 缩略图地址*/
    private String thumbnail;
    private String key;

    public long getSizeOfByte() {
        return sizeOfByte;
    }

    public void setSizeOfByte(long sizeOfByte) {
        this.sizeOfByte = sizeOfByte;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttachmentBean)) return false;
        if (getClass() != o.getClass())
            return false;
        AttachmentBean attachmentBean = (AttachmentBean) o;
        if (url == null && attachmentBean.getUrl() != null) {
            return false;
        }
        if (!Func.IsStringEmpty(qiniuKey) ) {
            if (!Func.IsStringEmpty(attachmentBean.getQiniuKey()) &&attachmentBean.getQiniuKey().equals(qiniuKey) )
                return true;
        }
        if (!Func.IsStringEmpty(fileName) ) {
            if (!Func.IsStringEmpty(attachmentBean.getFileName()) &&attachmentBean.getFileName().equals(fileName) )
                return true;
        }
        if (url == null && attachmentBean.getUrl() == null) {
            return false;
        }

        return Objects.equals(getUrl(), attachmentBean.getUrl());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {

        return Objects.hash(getUrl());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdBy);
        dest.writeString(createdDate);
        dest.writeString(lastModifiedBy);
        dest.writeString(lastModifiedDate);
        dest.writeLong(id);
        dest.writeString(fromClientType);
        dest.writeString(fkType);
        dest.writeLong(fkId);
        dest.writeString(qiniuKey);
        dest.writeString(fileName);
        dest.writeString(fileType);
        dest.writeString(fileSize);
        dest.writeLong(sizeOfByte);
        dest.writeString(url);
        dest.writeString(yyyymm);
        dest.writeString(extData);
        dest.writeString(thumbnail);
        dest.writeString(key);
    }
}
