package mine.bean;

import java.util.List;

import customer.entity.AttachmentBean;

public class FeedbackBean {

    public String getFeedTypeKey() {
        return feedTypeKey;
    }

    public void setFeedTypeKey(String feedTypeKey) {
        this.feedTypeKey = feedTypeKey;
    }

    public String getFeedTypeValue() {
        return feedTypeValue;
    }

    public void setFeedTypeValue(String feedTypeValue) {
        this.feedTypeValue = feedTypeValue;
    }

    /**
     * content : 内容
     * title : 标题
     * infoDate : 2018-06-01
     * deviceInfo : 设别信息
     * attachmentList : [{"qiniuKey":"qiniuKey1"},{"qiniuKey":"qiniuKey2"}]
     */
    private String feedTypeKey;
    private String feedTypeValue;
    private String content;
    private String title;
    private String infoDate;
    private String deviceInfo;
    private List<AttachmentBean> attachmentList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public List<AttachmentBean> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentBean> attachmentList) {
        this.attachmentList = attachmentList;
    }


}
