package customer.entity;

import java.util.List;

public class MemberSelectBean {

    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 122
         * orgName : 中宁纺织
         * cnumber : null
         * sapNumber : null
         * field : null
         * transactionType : null
         * salesmanId : null
         * salesmanName : null
         * riskLevel : null
         * officeId : null
         * officeName : null
         * headUrl : null
         * lastTransactionDayCount : null
         * release : false
         * transfer : false
         * claim : false
         */

        private long id;
        private String orgName;
        private Object cnumber;
        private Object sapNumber;
        private Object field;
        private Object transactionType;
        private Object salesmanId;
        private Object salesmanName;
        private Object riskLevel;
        private Object officeId;
        private Object officeName;
        private Object headUrl;
        private Object lastTransactionDayCount;
        private boolean release;
        private boolean transfer;
        private boolean claim;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public Object getCnumber() {
            return cnumber;
        }

        public void setCnumber(Object cnumber) {
            this.cnumber = cnumber;
        }

        public Object getSapNumber() {
            return sapNumber;
        }

        public void setSapNumber(Object sapNumber) {
            this.sapNumber = sapNumber;
        }

        public Object getField() {
            return field;
        }

        public void setField(Object field) {
            this.field = field;
        }

        public Object getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(Object transactionType) {
            this.transactionType = transactionType;
        }

        public Object getSalesmanId() {
            return salesmanId;
        }

        public void setSalesmanId(Object salesmanId) {
            this.salesmanId = salesmanId;
        }

        public Object getSalesmanName() {
            return salesmanName;
        }

        public void setSalesmanName(Object salesmanName) {
            this.salesmanName = salesmanName;
        }

        public Object getRiskLevel() {
            return riskLevel;
        }

        public void setRiskLevel(Object riskLevel) {
            this.riskLevel = riskLevel;
        }

        public Object getOfficeId() {
            return officeId;
        }

        public void setOfficeId(Object officeId) {
            this.officeId = officeId;
        }

        public Object getOfficeName() {
            return officeName;
        }

        public void setOfficeName(Object officeName) {
            this.officeName = officeName;
        }

        public Object getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(Object headUrl) {
            this.headUrl = headUrl;
        }

        public Object getLastTransactionDayCount() {
            return lastTransactionDayCount;
        }

        public void setLastTransactionDayCount(Object lastTransactionDayCount) {
            this.lastTransactionDayCount = lastTransactionDayCount;
        }

        public boolean isRelease() {
            return release;
        }

        public void setRelease(boolean release) {
            this.release = release;
        }

        public boolean isTransfer() {
            return transfer;
        }

        public void setTransfer(boolean transfer) {
            this.transfer = transfer;
        }

        public boolean isClaim() {
            return claim;
        }

        public void setClaim(boolean claim) {
            this.claim = claim;
        }
    }
}
