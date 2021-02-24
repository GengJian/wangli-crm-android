package customer.entity;

import java.util.List;

public class AddAttachmentBean {

    /**
     * id : 1
     * attachmentList : [{"qiniuKey":"qiniuKey"},{"qiniuKey":"qiniuKey"}]
     */

    private long id;
    private List<AttachmentBean> attachmentList;

    public List<AttachmentBean> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentBean> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
