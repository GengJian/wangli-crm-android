package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.List;

public class MemberCenterBean {
    private long memberId;
    private String orgName;
    private String crmNumber;
    private String statusValue;
    private String creditLevelValue;
    private int linkManCount;
    private int warningCount;
    private int procurementStatusCount;
    private int productStatusCount;
    private int salesStatusCount;
    private int researchStatusCount;
    private int businessVisitCount;
    private int businessChanceCount;
    private int contractCount;
    private int serviceComplaintCount;
    private int costAnalysisCount;

    public int getStormeManageCount() {
        return stormeManageCount;
    }

    public void setStormeManageCount(int stormeManageCount) {
        this.stormeManageCount = stormeManageCount;
    }

    public int getDealerPlanCount() {
        return dealerPlanCount;
    }

    public void setDealerPlanCount(int dealerPlanCount) {
        this.dealerPlanCount = dealerPlanCount;
    }

    private int stormeManageCount;//门店数量
    private int dealerPlanCount;//经销商计划数量

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    private String avatarUrl;

    public BigDecimal getContractSumMonth() {
        return contractSumMonth;
    }

    public void setContractSumMonth(BigDecimal contractSumMonth) {
        this.contractSumMonth = contractSumMonth;
    }

    public BigDecimal getOrderSumMounth() {
        return orderSumMounth;
    }

    public void setOrderSumMounth(BigDecimal orderSumMounth) {
        this.orderSumMounth = orderSumMounth;
    }

    public BigDecimal getBillingSumMonth() {
        return billingSumMonth;
    }

    public void setBillingSumMonth(BigDecimal billingSumMonth) {
        this.billingSumMonth = billingSumMonth;
    }

    public BigDecimal getReceivedSumMonth() {
        return receivedSumMonth;
    }

    public void setReceivedSumMonth(BigDecimal receivedSumMonth) {
        this.receivedSumMonth = receivedSumMonth;
    }

    private BigDecimal contractSumMonth;
    private BigDecimal orderSumMounth;
    private BigDecimal billingSumMonth;
    private BigDecimal receivedSumMonth;
    private float contractShip;
    private float orderShip;
    private float billingShip;
    private float receivedShip;
    private String title;
    private long favorite;

    public void setAuthorityBean(AuthorityBean authorityBean) {
        this.authorityBean = authorityBean;
    }

    public AuthorityBean getAuthorityBean() {
        return authorityBean;
    }

    private AuthorityBean authorityBean;
    private List<LabelsBean> labels;
    private List<IntelligenceListBean> intelligenceItemBeans;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCrmNumber() {
        return crmNumber;
    }

    public void setCrmNumber(String crmNumber) {
        this.crmNumber = crmNumber;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getCreditLevelValue() {
        return creditLevelValue;
    }

    public void setCreditLevelValue(String creditLevelValue) {
        this.creditLevelValue = creditLevelValue;
    }

    public int getLinkManCount() {
        return linkManCount;
    }

    public void setLinkManCount(int linkManCount) {
        this.linkManCount = linkManCount;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public int getProcurementStatusCount() {
        return procurementStatusCount;
    }

    public void setProcurementStatusCount(int procurementStatusCount) {
        this.procurementStatusCount = procurementStatusCount;
    }

    public int getProductStatusCount() {
        return productStatusCount;
    }

    public void setProductStatusCount(int productStatusCount) {
        this.productStatusCount = productStatusCount;
    }

    public int getSalesStatusCount() {
        return salesStatusCount;
    }

    public void setSalesStatusCount(int salesStatusCount) {
        this.salesStatusCount = salesStatusCount;
    }

    public int getResearchStatusCount() {
        return researchStatusCount;
    }

    public void setResearchStatusCount(int researchStatusCount) {
        this.researchStatusCount = researchStatusCount;
    }

    public int getBusinessVisitCount() {
        return businessVisitCount;
    }

    public void setBusinessVisitCount(int businessVisitCount) {
        this.businessVisitCount = businessVisitCount;
    }

    public int getBusinessChanceCount() {
        return businessChanceCount;
    }

    public void setBusinessChanceCount(int businessChanceCount) {
        this.businessChanceCount = businessChanceCount;
    }

    public int getContractCount() {
        return contractCount;
    }

    public void setContractCount(int contractCount) {
        this.contractCount = contractCount;
    }

    public int getServiceComplaintCount() {
        return serviceComplaintCount;
    }

    public void setServiceComplaintCount(int serviceComplaintCount) {
        this.serviceComplaintCount = serviceComplaintCount;
    }

    public int getCostAnalysisCount() {
        return costAnalysisCount;
    }

    public void setCostAnalysisCount(int costAnalysisCount) {
        this.costAnalysisCount = costAnalysisCount;
    }


    public float getContractShip() {
        return contractShip;
    }

    public void setContractShip(float contractShip) {
        this.contractShip = contractShip;
    }

    public float getOrderShip() {
        return orderShip;
    }

    public void setOrderShip(float orderShip) {
        this.orderShip = orderShip;
    }

    public float getBillingShip() {
        return billingShip;
    }

    public void setBillingShip(float billingShip) {
        this.billingShip = billingShip;
    }

    public float getReceivedShip() {
        return receivedShip;
    }

    public void setReceivedShip(float receivedShip) {
        this.receivedShip = receivedShip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getFavorite() {
        return favorite;
    }

    public void setFavorite(long favorite) {
        this.favorite = favorite;
    }


    public List<LabelsBean> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelsBean> labels) {
        this.labels = labels;
    }

    public List<IntelligenceListBean> getIntelligenceList() {
        return intelligenceItemBeans;
    }

    public void setIntelligenceList(List<IntelligenceListBean> intelligenceList) {
        this.intelligenceItemBeans = intelligenceList;
    }
    public static class AuthorityBean {
        private Boolean base; //基本资料
        private Boolean linkMan; //人事组织
        private Boolean financialRisk; //财务风险
        private Boolean procurementStatus; //采购状况
        private Boolean productionStatus; //生产及品质
        private Boolean salesStatus; //销售状况
        private Boolean developmentStatus; //研发状况

        public Boolean getBase() {
            return base;
        }

        public void setBase(Boolean base) {
            this.base = base;
        }

        public Boolean getLinkMan() {
            return linkMan;
        }

        public void setLinkMan(Boolean linkMan) {
            this.linkMan = linkMan;
        }

        public Boolean getFinancialRisk() {
            return financialRisk;
        }

        public void setFinancialRisk(Boolean financialRisk) {
            this.financialRisk = financialRisk;
        }

        public Boolean getProcurementStatus() {
            return procurementStatus;
        }

        public void setProcurementStatus(Boolean procurementStatus) {
            this.procurementStatus = procurementStatus;
        }

        public Boolean getProductionStatus() {
            return productionStatus;
        }

        public void setProductionStatus(Boolean productionStatus) {
            this.productionStatus = productionStatus;
        }

        public Boolean getSalesStatus() {
            return salesStatus;
        }

        public void setSalesStatus(Boolean salesStatus) {
            this.salesStatus = salesStatus;
        }

        public Boolean getDevelopmentStatus() {
            return developmentStatus;
        }

        public void setDevelopmentStatus(Boolean developmentStatus) {
            this.developmentStatus = developmentStatus;
        }

        public Boolean getBusinessVisit() {
            return businessVisit;
        }

        public void setBusinessVisit(Boolean businessVisit) {
            this.businessVisit = businessVisit;
        }

        public Boolean getBusinessFollow() {
            return businessFollow;
        }

        public void setBusinessFollow(Boolean businessFollow) {
            this.businessFollow = businessFollow;
        }

        public Boolean getContractTracking() {
            return contractTracking;
        }

        public void setContractTracking(Boolean contractTracking) {
            this.contractTracking = contractTracking;
        }

        public Boolean getServiceComplaint() {
            return serviceComplaint;
        }

        public void setServiceComplaint(Boolean serviceComplaint) {
            this.serviceComplaint = serviceComplaint;
        }

        public Boolean getCostAnalysis() {
            return costAnalysis;
        }

        public void setCostAnalysis(Boolean costAnalysis) {
            this.costAnalysis = costAnalysis;
        }

        public Boolean getSystem() {
            return system;
        }

        public void setSystem(Boolean system) {
            this.system = system;
        }

        private Boolean businessVisit; //商务拜访
        private Boolean businessFollow; //商务跟进
        private Boolean contractTracking; //合同跟踪
        private Boolean serviceComplaint; //服务投诉
        private Boolean costAnalysis; //费用分析
        private Boolean system; //系统信息

        public Boolean getTopFlag() {
            return topFlag;
        }

        public void setTopFlag(Boolean topFlag) {
            this.topFlag = topFlag;
        }

        private Boolean topFlag; //总权限

    }
    public static class LabelsBean implements Parcelable {
        public LabelsBean() {
        }

        /**
         * createdBy : 17721180295
         * createdDate : 2018-12-17

         * lastModifiedBy : 17721180295
         * lastModifiedDate : 2018-12-28
         * id : 4
         * deleted : false
         * sort : 1
         * fromClientType : null
         * optionGroup : []
         * name : null
         * desp : 货到付款
         * hotNumber : 2
         * type : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private String name;
        private String desp;
        private int hotNumber;
        private Object type;
        private List<?> optionGroup;

        protected LabelsBean(Parcel in) {
            createdBy = in.readString();
            createdDate = in.readString();
            lastModifiedBy = in.readString();
            lastModifiedDate = in.readString();
            id = in.readLong();
            deleted = in.readByte() != 0;
            sort = in.readInt();
            desp = in.readString();
            hotNumber = in.readInt();
        }

        public static final Creator<LabelsBean> CREATOR = new Creator<LabelsBean>() {
            @Override
            public LabelsBean createFromParcel(Parcel in) {
                return new LabelsBean(in);
            }

            @Override
            public LabelsBean[] newArray(int size) {
                return new LabelsBean[size];
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

        public long getId() {
            return id;
        }

        public void setId(long id) {
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

        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesp() {
            return desp;
        }

        public void setDesp(String desp) {
            this.desp = desp;
        }

        public int getHotNumber() {
            return hotNumber;
        }

        public void setHotNumber(int hotNumber) {
            this.hotNumber = hotNumber;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public List<?> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<?> optionGroup) {
            this.optionGroup = optionGroup;
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
            dest.writeByte((byte) (deleted ? 1 : 0));
            dest.writeInt(sort);
            dest.writeString(desp);
            dest.writeInt(hotNumber);
        }
    }

    public static class IntelligenceListBean {
        private long intelligenceId;

        public long getIntelligenceId() {
            return intelligenceId;
        }

        public void setIntelligenceId(long intelligenceId) {
            this.intelligenceId = intelligenceId;
        }

        public long getIntelligenceItemId() {
            return intelligenceItemId;
        }

        public void setIntelligenceItemId(long intelligenceItemId) {
            this.intelligenceItemId = intelligenceItemId;
        }

        public String getIntelligenceType() {
            return intelligenceType;
        }

        public void setIntelligenceType(String intelligenceType) {
            this.intelligenceType = intelligenceType;
        }

        public String getIntelligenceInfoValue() {
            return intelligenceInfoValue;
        }

        public void setIntelligenceInfoValue(String intelligenceInfoValue) {
            this.intelligenceInfoValue = intelligenceInfoValue;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private long intelligenceItemId;
        private String intelligenceType;
        private String intelligenceInfoValue;
        private String content;
    }
}
