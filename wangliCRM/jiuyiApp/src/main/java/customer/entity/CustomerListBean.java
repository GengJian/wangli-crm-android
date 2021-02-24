package customer.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CustomerListBean {

    private String cnumber;// 客户编号
    private String password;// 密码
    private String orgName;// 公司组织名称
    private String transactionType;// 交易类型（直接从SAP同步)
    private String salesman;// 操作员
    private String salesmandept;// 操作员所属部门
    private String riskLevel;// 风险等级
    private Integer accountPeriod;// 账期
    private BigDecimal credit;// 额度
    private Date lastTransactionDays;// 上次交易日期
    private String field;// 领域
    private String application;// 用途
    private String avatarUrl;// 头像地址
    private String sapNumber;// SAP客户编码
    private String abbreviation;// 简称
    private CustomerStatus status;// 客户状态
    private String simpleSpell;// 简拼
    private String level;// 客户等级
    private String saleOrgNumber;// 销售组织代号
    private String saleOrgName;// 销售组织描述
    private String distributionChannelName;// 分销渠道描述
    private String distributionChannelNumber;// 分销渠道代号
    private BigDecimal registeredCapital;// 注册资金
    private String taxNumber;// 开户税号
    private String accountBank;// 开户行
    private String registeredAddress;// 注册地址
    private String legalName;// 法人姓名
    private String businessScope;// 经营范围
    private String provinceName;// 省份名称
    private String provinceNumber;// 省份代号
    private String cityName;// 城市名称
    private String cityNumber;// 城市代号
    private String companyAddress;// 办公地址
    private String companyPhone;// 公司电话
    private Date registrationDate;// 注册日期
    private String creditCode;// 信用代码
    private String businessStatus;// 经营状态
    private String companyType;// 公司类型
    private Date operatingPeriodFrom;// 经营期限开始日期
    private Date operatingPeriodTo;// 经营期限结束日期
    private String website;// 公司网址
    private String productGroupNumber;// 产品组代号
    private String productGroupName;// 产品组名称
    private String saleRegionNumber;// 销售范围代号
    private String saleRegionName;// 销售范围名称
    private String paymentTermNumber;// 特殊条款编号
    private String paymentTermName;// 特殊条款名称
    private BigDecimal creditRiskTotalAmount;// 信用额度
    private BigDecimal accountsReceivable;// 账户回款
    private int viewType;//页面滑动打开类型

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getSalesmandept() {
        return salesmandept;
    }

    public void setSalesmandept(String salesmandept) {
        this.salesmandept = salesmandept;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(Integer accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Date getLastTransactionDays() {
        return lastTransactionDays;
    }

    public void setLastTransactionDays(Date lastTransactionDays) {
        this.lastTransactionDays = lastTransactionDays;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSapNumber() {
        return sapNumber;
    }

    public void setSapNumber(String sapNumber) {
        this.sapNumber = sapNumber;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public String getSimpleSpell() {
        return simpleSpell;
    }

    public void setSimpleSpell(String simpleSpell) {
        this.simpleSpell = simpleSpell;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSaleOrgNumber() {
        return saleOrgNumber;
    }

    public void setSaleOrgNumber(String saleOrgNumber) {
        this.saleOrgNumber = saleOrgNumber;
    }

    public String getSaleOrgName() {
        return saleOrgName;
    }

    public void setSaleOrgName(String saleOrgName) {
        this.saleOrgName = saleOrgName;
    }

    public String getDistributionChannelName() {
        return distributionChannelName;
    }

    public void setDistributionChannelName(String distributionChannelName) {
        this.distributionChannelName = distributionChannelName;
    }

    public String getDistributionChannelNumber() {
        return distributionChannelNumber;
    }

    public void setDistributionChannelNumber(String distributionChannelNumber) {
        this.distributionChannelNumber = distributionChannelNumber;
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceNumber() {
        return provinceNumber;
    }

    public void setProvinceNumber(String provinceNumber) {
        this.provinceNumber = provinceNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(String cityNumber) {
        this.cityNumber = cityNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public Date getOperatingPeriodFrom() {
        return operatingPeriodFrom;
    }

    public void setOperatingPeriodFrom(Date operatingPeriodFrom) {
        this.operatingPeriodFrom = operatingPeriodFrom;
    }

    public Date getOperatingPeriodTo() {
        return operatingPeriodTo;
    }

    public void setOperatingPeriodTo(Date operatingPeriodTo) {
        this.operatingPeriodTo = operatingPeriodTo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProductGroupNumber() {
        return productGroupNumber;
    }

    public void setProductGroupNumber(String productGroupNumber) {
        this.productGroupNumber = productGroupNumber;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public String getSaleRegionNumber() {
        return saleRegionNumber;
    }

    public void setSaleRegionNumber(String saleRegionNumber) {
        this.saleRegionNumber = saleRegionNumber;
    }

    public String getSaleRegionName() {
        return saleRegionName;
    }

    public void setSaleRegionName(String saleRegionName) {
        this.saleRegionName = saleRegionName;
    }

    public String getPaymentTermNumber() {
        return paymentTermNumber;
    }

    public void setPaymentTermNumber(String paymentTermNumber) {
        this.paymentTermNumber = paymentTermNumber;
    }

    public String getPaymentTermName() {
        return paymentTermName;
    }

    public void setPaymentTermName(String paymentTermName) {
        this.paymentTermName = paymentTermName;
    }

    public BigDecimal getCreditRiskTotalAmount() {
        return creditRiskTotalAmount;
    }

    public void setCreditRiskTotalAmount(BigDecimal creditRiskTotalAmount) {
        this.creditRiskTotalAmount = creditRiskTotalAmount;
    }

    public BigDecimal getAccountsReceivable() {
        return accountsReceivable;
    }

    public void setAccountsReceivable(BigDecimal accountsReceivable) {
        this.accountsReceivable = accountsReceivable;
    }

    public BigDecimal getSpecialDebt() {
        return specialDebt;
    }

    public void setSpecialDebt(BigDecimal specialDebt) {
        this.specialDebt = specialDebt;
    }

    public Integer getCreditModifyDate() {
        return creditModifyDate;
    }

    public void setCreditModifyDate(Integer creditModifyDate) {
        this.creditModifyDate = creditModifyDate;
    }

    public BigDecimal getOwedTotalAmount() {
        return owedTotalAmount;
    }

    public void setOwedTotalAmount(BigDecimal owedTotalAmount) {
        this.owedTotalAmount = owedTotalAmount;
    }

    public BigDecimal getDueTotalAmount() {
        return dueTotalAmount;
    }

    public void setDueTotalAmount(BigDecimal dueTotalAmount) {
        this.dueTotalAmount = dueTotalAmount;
    }

    public Integer getMaxDueDays() {
        return maxDueDays;
    }

    public void setMaxDueDays(Integer maxDueDays) {
        this.maxDueDays = maxDueDays;
    }

    private BigDecimal specialDebt;// 特殊账单额度
    private Integer creditModifyDate;// 账期天数
    private BigDecimal owedTotalAmount;// 欠款总额
    private BigDecimal dueTotalAmount;// 到期总额
    private Integer maxDueDays;// 最长逾期天数

}
