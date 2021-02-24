package customer.entity;

import android.graphics.Bitmap;



import java.io.IOException;

import customer.view.Bimp;

/**
 * Created by wcz on 2016/10/14.
 */
public class ImageBean {
    public String id;

    public String getQiniuKey() {
        return qiniuKey;
    }

    public void setQiniuKey(String qiniuKey) {
        this.qiniuKey = qiniuKey;
    }

    private String qiniuKey="";

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String path;
    private String imgUrl;
    private Bitmap bitmap;

    public Boolean getVideo() {
        return isVideo;
    }

    public void setVideo(Boolean video) {
        isVideo = video;
    }

    private Boolean isVideo;
    // 上传进度
    private int progress;
    // 是否显示删除图标与progressbar(true:不显示删除图标，显示progressbar)
    private boolean isShowIcon;

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String thumbnailPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getBitmap() {
        if(bitmap == null){
            try {
                bitmap = Bimp.revitionImageSize(path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isShowIcon() {
        return isShowIcon;
    }

    public void setShowIcon(boolean showIcon) {
        isShowIcon = showIcon;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ImageBean) {
            ImageBean imageBean = (ImageBean) obj;
            return (id.equals(imageBean.id));
        }
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        ImageBean imageBean = (ImageBean) this;
        return id.hashCode();

    }
}
