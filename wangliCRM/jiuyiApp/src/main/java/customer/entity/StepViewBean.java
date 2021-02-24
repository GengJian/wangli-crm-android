package customer.entity;

import java.util.List;

/**
 * 风险跟踪时间轴实体类
 */

public class StepViewBean {
    public StepViewBean(String iconUrl, String acceptTime, String acceptStation, String acceptStationBelow, String title, String imageUrl) {
        this.iconUrl = iconUrl;
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
        this.acceptStationBelow = acceptStationBelow;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public List<AttachmentBean> getAttachurl() {
        return attachurl;
    }

    public void setAttachurl(List<AttachmentBean> attachurl) {
        this.attachurl = attachurl;
    }

    private List<AttachmentBean> attachurl;
    public String getIconUrl() {
        return iconUrl;

    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    private String iconUrl;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;
    /** 时间 */
    private String acceptTime;
    /** 描述 */
    private String acceptStation;
    /** 描述下方*/
    private String acceptStationBelow;


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    /** 创建人 */
    private String creator;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    /** 缩略图地址*/
    private String thumbnail;

    public boolean isDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(boolean deleteAble) {
        this.deleteAble = deleteAble;
    }

    private boolean deleteAble;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public StepViewBean(String acceptTime, String acceptStation, String acceptStationBelow, String imageUrl) {
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
        this.acceptStationBelow = acceptStationBelow;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //图片地址
    private String imageUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public String getAcceptStationBelow() {
        return acceptStationBelow;
    }

    public void setAcceptStationBelow(String acceptStationBelow) {
        this.acceptStationBelow = acceptStationBelow;
    }



    public StepViewBean() {
    }

    public StepViewBean(String acceptTime, String acceptStation) {
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
    }

    public StepViewBean(String acceptTime, String acceptStation, String acceptStationBelow) {
        this.acceptTime = acceptTime;
        this.acceptStation = acceptStation;
        this.acceptStationBelow = acceptStationBelow;

    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getAcceptStation() {
        return acceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        this.acceptStation = acceptStation;
    }
}
