package customer.entity;

import java.util.List;

import commonlyused.bean.NormalOperatorBean;

public class InformationBean {


    /**
     * sapNumber : 200193
     * createOperator : 系统创建
     * createDate : 2018-12-18
     * lastModify : null
     * assistNumber : 2
     * memberAssistSet : [{"createdBy":"17721180295","createdDate":"2018-12-20 17:49:28","lastModifiedBy":"17721180295","lastModifiedDate":"2018-12-20 17:49:28","id":79,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"member":null,"operator":null,"assistRole":"FR","assistReason":"人事部"},{"createdBy":"17721180295","createdDate":"2018-12-20 19:03:08","lastModifiedBy":"17721180295","lastModifiedDate":"2018-12-20 19:03:08","id":84,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"member":null,"operator":null,"assistRole":"AR","assistReason":"SSSSSSSSSSSSS"}]
     * modifyDate : 2018-12-18
     */

    private String sapNumber;
    private String createOperator;
    private String createDate;
    private Object lastModify;
    private int assistNumber;
    private String modifyDate;
    private List<MemberAssistSetBean> memberAssistSet;

    public String getSapNumber() {
        return sapNumber;
    }

    public void setSapNumber(String sapNumber) {
        this.sapNumber = sapNumber;
    }

    public String getCreateOperator() {
        return createOperator;
    }

    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getLastModify() {
        return lastModify;
    }

    public void setLastModify(Object lastModify) {
        this.lastModify = lastModify;
    }

    public int getAssistNumber() {
        return assistNumber;
    }

    public void setAssistNumber(int assistNumber) {
        this.assistNumber = assistNumber;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<MemberAssistSetBean> getMemberAssistSet() {
        return memberAssistSet;
    }

    public void setMemberAssistSet(List<MemberAssistSetBean> memberAssistSet) {
        this.memberAssistSet = memberAssistSet;
    }

    public static class MemberAssistSetBean {
        /**
         * createdBy : 17721180295
         * createdDate : 2018-12-20 17:49:28
         * lastModifiedBy : 17721180295
         * lastModifiedDate : 2018-12-20 17:49:28
         * id : 79
         * deleted : false
         * sort : 1
         * fromClientType : null
         * optionGroup : []
         * member : null
         * operator : null
         * assistRole : FR
         * assistReason : 人事部
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private int id;
        private boolean deleted;
        private int sort;
        private Object fromClientType;
        private Object member;
        private NormalOperatorBean.OperatorBeanID operator;
        private String assistRole;
        private String assistReason;
        private List<?> optionGroup;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(Object fromClientType) {
            this.fromClientType = fromClientType;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public NormalOperatorBean.OperatorBeanID getOperator() {
            return operator;
        }

        public void setOperator(NormalOperatorBean.OperatorBeanID operator) {
            this.operator = operator;
        }

        public String getAssistRole() {
            return assistRole;
        }

        public void setAssistRole(String assistRole) {
            this.assistRole = assistRole;
        }

        public String getAssistReason() {
            return assistReason;
        }

        public void setAssistReason(String assistReason) {
            this.assistReason = assistReason;
        }

        public List<?> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<?> optionGroup) {
            this.optionGroup = optionGroup;
        }
    }
}
