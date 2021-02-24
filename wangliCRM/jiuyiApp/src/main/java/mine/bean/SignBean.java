package mine.bean;

import com.jiuyi.model.DictResultBean;

import java.util.List;

import customer.entity.AttachmentBean;

public class SignBean {

    /**
     * createdBy : 18405863019
     * createdDate : 2018-06-24
     * lastModifiedBy : 18405863019
     * lastModifiedDate : 2018-06-24
     * id : 5
     * deleted : false
     * sort : 10
     * fromClientType : null
     * signInDate : 2018-08-25
     * longitude : 121054446
     * latitude : 544321749
     * address : 杭州市西湖区文三西路五联西苑
     * remark : 今天到杭州慰问
     * attachments : [{"createdBy":"18405863019","createdDate":"2018-06-24","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-24","id":545,"deleted":false,"sort":10,"fromClientType":null,"fkType":"SIGN_IN_RECORD","fkId":5,"qiniuKey":"key_1","fileName":null,"fileType":null,"fileSize":null,"url":null,"key":null},{"createdBy":"18405863019","createdDate":"2018-06-24","lastModifiedBy":"18405863019","lastModifiedDate":"2018-06-24","id":546,"deleted":false,"sort":10,"fromClientType":null,"fkType":"SIGN_IN_RECORD","fkId":5,"qiniuKey":"key_2","fileName":null,"fileType":null,"fileSize":null,"url":null,"key":null}]
     * operatorVo : null
     */


    private String fromClientType;

    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHardwareInfo() {
        return hardwareInfo;
    }

    public void setHardwareInfo(String hardwareInfo) {
        this.hardwareInfo = hardwareInfo;
    }

    public List<AttachmentBean> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentBean> attachments) {
        this.attachments = attachments;
    }

    private String longitude;
    private String latitude;
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    private String provinceName;
    private String remark;
    private String hardwareInfo;

    public DictResultBean.ContentBean getSignType() {
        return signType;
    }

    public void setSignType(DictResultBean.ContentBean signType) {
        this.signType = signType;
    }

    private DictResultBean.ContentBean signType;
    private List<AttachmentBean> attachments;


}
