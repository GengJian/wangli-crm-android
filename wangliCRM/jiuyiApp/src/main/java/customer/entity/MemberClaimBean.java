package customer.entity;

public class MemberClaimBean {
    public MemberBeanID getMember() {
        return member;
    }

    public void setMember(MemberBeanID member) {
        this.member = member;
    }

    public SalemanBean getTargetSaleman() {
        return targetSaleman;
    }

    public void setTargetSaleman(SalemanBean targetSaleman) {
        this.targetSaleman = targetSaleman;
    }

    public SalemanBean getOriginalSaleman() {
        return originalSaleman;
    }

    public void setOriginalSaleman(SalemanBean originalSaleman) {
        this.originalSaleman = originalSaleman;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    MemberBeanID member;
    SalemanBean targetSaleman;
    SalemanBean originalSaleman;
    private String remark;
    private String infoDate;

    public static  class SalemanBean {
        /**
         * id : 20
         */

        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

}
