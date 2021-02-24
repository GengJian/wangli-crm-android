package customer.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CustomerDebtBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/8/21 11:15
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/8/21 1.00 初始版本
 * ****************************************************************
 */
public class CustomerDebtBean {

    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * companyCode : {"fromClientType":null,"companyCode":"1100","amount":-3722780}
         * companyList : [{"createdBy":null,"createdDate":"2018-08-21","lastModifiedBy":null,"lastModifiedDate":"2018-08-21","id":46930,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"cnumber":"104054","password":null,"orgName":"桐昆集团浙江恒盛化纤有限公司","transactionType":null,"office":null,"salesman":null,"riskLevel":null,"riskRate":null,"superiorDealer":null,"accountPeriod":null,"credit":null,"lastTransactionDays":null,"field":null,"application":null,"avatarUrl":null,"sapNumber":"104054","abbreviation":"恒盛","status":null,"statusValue":null,"simpleSpell":null,"level":null,"levelCode":null,"saleOrgNumber":null,"saleOrgName":null,"distributionChannelName":null,"distributionChannelNumber":null,"registeredCapital":null,"taxNumber":null,"accountBank":null,"registeredAddress":null,"legalName":null,"businessScope":null,"provinceName":null,"provinceNumber":null,"cityName":null,"cityNumber":null,"companyAddress":null,"companyPhone":null,"registrationDate":null,"creditCode":null,"businessStatus":null,"companyType":null,"operatingPeriodFrom":null,"operatingPeriodTo":null,"shareholderInformation":null,"mainMember":null,"branchesOrg":null,"website":null,"productGroupNumber":null,"productGroupName":null,"saleRegionNumber":null,"saleRegionName":null,"paymentTermNumber":null,"paymentTermName":null,"creditRiskTotalAmount":null,"accountsReceivable":null,"specialDebt":null,"creditModifyDate":null,"owedTotalAmount":-3722780,"dueTotalAmount":null,"maxDueDays":null,"lastTransactionDayCount":null,"sign":false,"completeness":null,"completenessNumber":null,"availableCredit":0,"businessDate":"2018-08-21 10:15:08","badDebt":false,"vkbur":null,"land1":null,"landx":null,"salesManCode":null,"salesManName":null,"vkgrp":null,"bezei2":null,"waers":null,"ltext":null,"zterm":null,"ztermDesp":null,"companyCode":"1100","kkber":null,"klimk":null,"skfor":null,"ssobl":null,"oblig":null,"cashc":null,"knkli":"104054","ctlpc":null,"uedat":null,"oeikw":null,"amountUntilMonth":null,"actualShip":null,"deliveringTop":null,"currentMonthUnbilledAmount":null,"machinesNumberRange":null,"openMachinesRateRange":null,"cartItems":null,"labels":null,"operators":null,"transactionTrackings":null,"equipments":null,"inExPortInfos":null,"productionLicenses":null,"productionPerformances":null,"purchaseTenders":null,"recruitments":null,"rowMaterials":null,"spotChecks":null,"taxRatings":null}]
         */

        private CompanyCodeBean companyCode;
        private List<CompanyListBean> companyList;

        public CompanyCodeBean getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(CompanyCodeBean companyCode) {
            this.companyCode = companyCode;
        }

        public List<CompanyListBean> getCompanyList() {
            return companyList;
        }

        public void setCompanyList(List<CompanyListBean> companyList) {
            this.companyList = companyList;
        }

        public static class CompanyCodeBean {
            /**
             * fromClientType : null
             * companyCode : 1100
             * amount : -3722780
             */

            private String fromClientType;
            private String companyCode;
            private BigDecimal amount;

            public String getFromClientType() {
                return fromClientType;
            }

            public void setFromClientType(String fromClientType) {
                this.fromClientType = fromClientType;
            }

            public String getCompanyCode() {
                return companyCode;
            }

            public void setCompanyCode(String companyCode) {
                this.companyCode = companyCode;
            }

            public BigDecimal getAmount() {
                return amount;
            }

            public void setAmount(BigDecimal amount) {
                this.amount = amount;
            }
        }

        public static class CompanyListBean {
            /**
             * createdBy : null
             * createdDate : 2018-08-21
             * lastModifiedBy : null
             * lastModifiedDate : 2018-08-21
             * id : 46930
             * deleted : false
             * sort : 10
             * fromClientType : null
             * optionGroup : []
             * cnumber : 104054
             * password : null
             * orgName : 桐昆集团浙江恒盛化纤有限公司
             * transactionType : null
             * office : null
             * salesman : null
             * riskLevel : null
             * riskRate : null
             * superiorDealer : null
             * accountPeriod : null
             * credit : null
             * lastTransactionDays : null
             * field : null
             * application : null
             * avatarUrl : null
             * sapNumber : 104054
             * abbreviation : 恒盛
             * status : null
             * statusValue : null
             * simpleSpell : null
             * level : null
             * levelCode : null
             * saleOrgNumber : null
             * saleOrgName : null
             * distributionChannelName : null
             * distributionChannelNumber : null
             * registeredCapital : null
             * taxNumber : null
             * accountBank : null
             * registeredAddress : null
             * legalName : null
             * businessScope : null
             * provinceName : null
             * provinceNumber : null
             * cityName : null
             * cityNumber : null
             * companyAddress : null
             * companyPhone : null
             * registrationDate : null
             * creditCode : null
             * businessStatus : null
             * companyType : null
             * operatingPeriodFrom : null
             * operatingPeriodTo : null
             * shareholderInformation : null
             * mainMember : null
             * branchesOrg : null
             * website : null
             * productGroupNumber : null
             * productGroupName : null
             * saleRegionNumber : null
             * saleRegionName : null
             * paymentTermNumber : null
             * paymentTermName : null
             * creditRiskTotalAmount : null
             * accountsReceivable : null
             * specialDebt : null
             * creditModifyDate : null
             * owedTotalAmount : -3722780
             * dueTotalAmount : null
             * maxDueDays : null
             * lastTransactionDayCount : null
             * sign : false
             * completeness : null
             * completenessNumber : null
             * availableCredit : 0
             * businessDate : 2018-08-21 10:15:08
             * badDebt : false
             * vkbur : null
             * land1 : null
             * landx : null
             * salesManCode : null
             * salesManName : null
             * vkgrp : null
             * bezei2 : null
             * waers : null
             * ltext : null
             * zterm : null
             * ztermDesp : null
             * companyCode : 1100
             * kkber : null
             * klimk : null
             * skfor : null
             * ssobl : null
             * oblig : null
             * cashc : null
             * knkli : 104054
             * ctlpc : null
             * uedat : null
             * oeikw : null
             * amountUntilMonth : null
             * actualShip : null
             * deliveringTop : null
             * currentMonthUnbilledAmount : null
             * machinesNumberRange : null
             * openMachinesRateRange : null
             * cartItems : null
             * labels : null
             * operators : null
             * transactionTrackings : null
             * equipments : null
             * inExPortInfos : null
             * productionLicenses : null
             * productionPerformances : null
             * purchaseTenders : null
             * recruitments : null
             * rowMaterials : null
             * spotChecks : null
             * taxRatings : null
             */

            private Object createdBy;
            private String createdDate;
            private Object lastModifiedBy;
            private String lastModifiedDate;
            private long id;
            private boolean deleted;
            private int sort;
            private Object fromClientType;
            private String cnumber;
            private Object password;
            private String orgName;
            private Object transactionType;
            private Object office;
            private Object salesman;
            private Object riskLevel;
            private Object riskRate;
            private Object superiorDealer;
            private Object accountPeriod;
            private Object credit;
            private Object lastTransactionDays;
            private Object field;
            private Object application;
            private Object avatarUrl;
            private String sapNumber;
            private String abbreviation;
            private Object status;
            private Object statusValue;
            private Object simpleSpell;
            private Object level;
            private Object levelCode;
            private Object saleOrgNumber;
            private Object saleOrgName;
            private Object distributionChannelName;
            private Object distributionChannelNumber;
            private Object registeredCapital;
            private Object taxNumber;
            private Object accountBank;
            private Object registeredAddress;
            private Object legalName;
            private Object businessScope;
            private Object provinceName;
            private Object provinceNumber;
            private Object cityName;
            private Object cityNumber;
            private Object companyAddress;
            private Object companyPhone;
            private Object registrationDate;
            private Object creditCode;
            private Object businessStatus;
            private Object companyType;
            private Object operatingPeriodFrom;
            private Object operatingPeriodTo;
            private Object shareholderInformation;
            private Object mainMember;
            private Object branchesOrg;
            private Object website;
            private Object productGroupNumber;
            private Object productGroupName;
            private Object saleRegionNumber;
            private Object saleRegionName;
            private Object paymentTermNumber;
            private Object paymentTermName;
            private Object creditRiskTotalAmount;
            private Object accountsReceivable;
            private Object specialDebt;
            private Object creditModifyDate;
            private BigDecimal owedTotalAmount;
            private Object dueTotalAmount;
            private Object maxDueDays;
            private Object lastTransactionDayCount;
            private boolean sign;
            private Object completeness;
            private Object completenessNumber;
            private BigDecimal availableCredit;
            private String businessDate;
            private boolean badDebt;
            private Object vkbur;
            private Object land1;
            private Object landx;
            private Object salesManCode;
            private Object salesManName;
            private Object vkgrp;
            private Object bezei2;
            private Object waers;
            private Object ltext;
            private Object zterm;
            private Object ztermDesp;
            private String companyCode;
            private Object kkber;
            private Object klimk;
            private Object skfor;
            private Object ssobl;
            private Object oblig;
            private Object cashc;
            private String knkli;
            private Object ctlpc;
            private Object uedat;
            private Object oeikw;
            private Object amountUntilMonth;
            private Object actualShip;
            private Object deliveringTop;
            private Object currentMonthUnbilledAmount;
            private Object machinesNumberRange;
            private Object openMachinesRateRange;
            private Object cartItems;
            private Object labels;
            private Object operators;
            private Object transactionTrackings;
            private Object equipments;
            private Object inExPortInfos;
            private Object productionLicenses;
            private Object productionPerformances;
            private Object purchaseTenders;
            private Object recruitments;
            private Object rowMaterials;
            private Object spotChecks;
            private Object taxRatings;
            private List<?> optionGroup;

            public Object getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(Object createdBy) {
                this.createdBy = createdBy;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public Object getLastModifiedBy() {
                return lastModifiedBy;
            }

            public void setLastModifiedBy(Object lastModifiedBy) {
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

            public Object getFromClientType() {
                return fromClientType;
            }

            public void setFromClientType(Object fromClientType) {
                this.fromClientType = fromClientType;
            }

            public String getCnumber() {
                return cnumber;
            }

            public void setCnumber(String cnumber) {
                this.cnumber = cnumber;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public String getOrgName() {
                return orgName;
            }

            public void setOrgName(String orgName) {
                this.orgName = orgName;
            }

            public Object getTransactionType() {
                return transactionType;
            }

            public void setTransactionType(Object transactionType) {
                this.transactionType = transactionType;
            }

            public Object getOffice() {
                return office;
            }

            public void setOffice(Object office) {
                this.office = office;
            }

            public Object getSalesman() {
                return salesman;
            }

            public void setSalesman(Object salesman) {
                this.salesman = salesman;
            }

            public Object getRiskLevel() {
                return riskLevel;
            }

            public void setRiskLevel(Object riskLevel) {
                this.riskLevel = riskLevel;
            }

            public Object getRiskRate() {
                return riskRate;
            }

            public void setRiskRate(Object riskRate) {
                this.riskRate = riskRate;
            }

            public Object getSuperiorDealer() {
                return superiorDealer;
            }

            public void setSuperiorDealer(Object superiorDealer) {
                this.superiorDealer = superiorDealer;
            }

            public Object getAccountPeriod() {
                return accountPeriod;
            }

            public void setAccountPeriod(Object accountPeriod) {
                this.accountPeriod = accountPeriod;
            }

            public Object getCredit() {
                return credit;
            }

            public void setCredit(Object credit) {
                this.credit = credit;
            }

            public Object getLastTransactionDays() {
                return lastTransactionDays;
            }

            public void setLastTransactionDays(Object lastTransactionDays) {
                this.lastTransactionDays = lastTransactionDays;
            }

            public Object getField() {
                return field;
            }

            public void setField(Object field) {
                this.field = field;
            }

            public Object getApplication() {
                return application;
            }

            public void setApplication(Object application) {
                this.application = application;
            }

            public Object getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(Object avatarUrl) {
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

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getStatusValue() {
                return statusValue;
            }

            public void setStatusValue(Object statusValue) {
                this.statusValue = statusValue;
            }

            public Object getSimpleSpell() {
                return simpleSpell;
            }

            public void setSimpleSpell(Object simpleSpell) {
                this.simpleSpell = simpleSpell;
            }

            public Object getLevel() {
                return level;
            }

            public void setLevel(Object level) {
                this.level = level;
            }

            public Object getLevelCode() {
                return levelCode;
            }

            public void setLevelCode(Object levelCode) {
                this.levelCode = levelCode;
            }

            public Object getSaleOrgNumber() {
                return saleOrgNumber;
            }

            public void setSaleOrgNumber(Object saleOrgNumber) {
                this.saleOrgNumber = saleOrgNumber;
            }

            public Object getSaleOrgName() {
                return saleOrgName;
            }

            public void setSaleOrgName(Object saleOrgName) {
                this.saleOrgName = saleOrgName;
            }

            public Object getDistributionChannelName() {
                return distributionChannelName;
            }

            public void setDistributionChannelName(Object distributionChannelName) {
                this.distributionChannelName = distributionChannelName;
            }

            public Object getDistributionChannelNumber() {
                return distributionChannelNumber;
            }

            public void setDistributionChannelNumber(Object distributionChannelNumber) {
                this.distributionChannelNumber = distributionChannelNumber;
            }

            public Object getRegisteredCapital() {
                return registeredCapital;
            }

            public void setRegisteredCapital(Object registeredCapital) {
                this.registeredCapital = registeredCapital;
            }

            public Object getTaxNumber() {
                return taxNumber;
            }

            public void setTaxNumber(Object taxNumber) {
                this.taxNumber = taxNumber;
            }

            public Object getAccountBank() {
                return accountBank;
            }

            public void setAccountBank(Object accountBank) {
                this.accountBank = accountBank;
            }

            public Object getRegisteredAddress() {
                return registeredAddress;
            }

            public void setRegisteredAddress(Object registeredAddress) {
                this.registeredAddress = registeredAddress;
            }

            public Object getLegalName() {
                return legalName;
            }

            public void setLegalName(Object legalName) {
                this.legalName = legalName;
            }

            public Object getBusinessScope() {
                return businessScope;
            }

            public void setBusinessScope(Object businessScope) {
                this.businessScope = businessScope;
            }

            public Object getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(Object provinceName) {
                this.provinceName = provinceName;
            }

            public Object getProvinceNumber() {
                return provinceNumber;
            }

            public void setProvinceNumber(Object provinceNumber) {
                this.provinceNumber = provinceNumber;
            }

            public Object getCityName() {
                return cityName;
            }

            public void setCityName(Object cityName) {
                this.cityName = cityName;
            }

            public Object getCityNumber() {
                return cityNumber;
            }

            public void setCityNumber(Object cityNumber) {
                this.cityNumber = cityNumber;
            }

            public Object getCompanyAddress() {
                return companyAddress;
            }

            public void setCompanyAddress(Object companyAddress) {
                this.companyAddress = companyAddress;
            }

            public Object getCompanyPhone() {
                return companyPhone;
            }

            public void setCompanyPhone(Object companyPhone) {
                this.companyPhone = companyPhone;
            }

            public Object getRegistrationDate() {
                return registrationDate;
            }

            public void setRegistrationDate(Object registrationDate) {
                this.registrationDate = registrationDate;
            }

            public Object getCreditCode() {
                return creditCode;
            }

            public void setCreditCode(Object creditCode) {
                this.creditCode = creditCode;
            }

            public Object getBusinessStatus() {
                return businessStatus;
            }

            public void setBusinessStatus(Object businessStatus) {
                this.businessStatus = businessStatus;
            }

            public Object getCompanyType() {
                return companyType;
            }

            public void setCompanyType(Object companyType) {
                this.companyType = companyType;
            }

            public Object getOperatingPeriodFrom() {
                return operatingPeriodFrom;
            }

            public void setOperatingPeriodFrom(Object operatingPeriodFrom) {
                this.operatingPeriodFrom = operatingPeriodFrom;
            }

            public Object getOperatingPeriodTo() {
                return operatingPeriodTo;
            }

            public void setOperatingPeriodTo(Object operatingPeriodTo) {
                this.operatingPeriodTo = operatingPeriodTo;
            }

            public Object getShareholderInformation() {
                return shareholderInformation;
            }

            public void setShareholderInformation(Object shareholderInformation) {
                this.shareholderInformation = shareholderInformation;
            }

            public Object getMainMember() {
                return mainMember;
            }

            public void setMainMember(Object mainMember) {
                this.mainMember = mainMember;
            }

            public Object getBranchesOrg() {
                return branchesOrg;
            }

            public void setBranchesOrg(Object branchesOrg) {
                this.branchesOrg = branchesOrg;
            }

            public Object getWebsite() {
                return website;
            }

            public void setWebsite(Object website) {
                this.website = website;
            }

            public Object getProductGroupNumber() {
                return productGroupNumber;
            }

            public void setProductGroupNumber(Object productGroupNumber) {
                this.productGroupNumber = productGroupNumber;
            }

            public Object getProductGroupName() {
                return productGroupName;
            }

            public void setProductGroupName(Object productGroupName) {
                this.productGroupName = productGroupName;
            }

            public Object getSaleRegionNumber() {
                return saleRegionNumber;
            }

            public void setSaleRegionNumber(Object saleRegionNumber) {
                this.saleRegionNumber = saleRegionNumber;
            }

            public Object getSaleRegionName() {
                return saleRegionName;
            }

            public void setSaleRegionName(Object saleRegionName) {
                this.saleRegionName = saleRegionName;
            }

            public Object getPaymentTermNumber() {
                return paymentTermNumber;
            }

            public void setPaymentTermNumber(Object paymentTermNumber) {
                this.paymentTermNumber = paymentTermNumber;
            }

            public Object getPaymentTermName() {
                return paymentTermName;
            }

            public void setPaymentTermName(Object paymentTermName) {
                this.paymentTermName = paymentTermName;
            }

            public Object getCreditRiskTotalAmount() {
                return creditRiskTotalAmount;
            }

            public void setCreditRiskTotalAmount(Object creditRiskTotalAmount) {
                this.creditRiskTotalAmount = creditRiskTotalAmount;
            }

            public Object getAccountsReceivable() {
                return accountsReceivable;
            }

            public void setAccountsReceivable(Object accountsReceivable) {
                this.accountsReceivable = accountsReceivable;
            }

            public Object getSpecialDebt() {
                return specialDebt;
            }

            public void setSpecialDebt(Object specialDebt) {
                this.specialDebt = specialDebt;
            }

            public Object getCreditModifyDate() {
                return creditModifyDate;
            }

            public void setCreditModifyDate(Object creditModifyDate) {
                this.creditModifyDate = creditModifyDate;
            }

            public BigDecimal getOwedTotalAmount() {
                return owedTotalAmount;
            }

            public void setOwedTotalAmount(BigDecimal owedTotalAmount) {
                this.owedTotalAmount = owedTotalAmount;
            }

            public Object getDueTotalAmount() {
                return dueTotalAmount;
            }

            public void setDueTotalAmount(Object dueTotalAmount) {
                this.dueTotalAmount = dueTotalAmount;
            }

            public Object getMaxDueDays() {
                return maxDueDays;
            }

            public void setMaxDueDays(Object maxDueDays) {
                this.maxDueDays = maxDueDays;
            }

            public Object getLastTransactionDayCount() {
                return lastTransactionDayCount;
            }

            public void setLastTransactionDayCount(Object lastTransactionDayCount) {
                this.lastTransactionDayCount = lastTransactionDayCount;
            }

            public boolean isSign() {
                return sign;
            }

            public void setSign(boolean sign) {
                this.sign = sign;
            }

            public Object getCompleteness() {
                return completeness;
            }

            public void setCompleteness(Object completeness) {
                this.completeness = completeness;
            }

            public Object getCompletenessNumber() {
                return completenessNumber;
            }

            public void setCompletenessNumber(Object completenessNumber) {
                this.completenessNumber = completenessNumber;
            }

            public BigDecimal getAvailableCredit() {
                return availableCredit;
            }

            public void setAvailableCredit(BigDecimal availableCredit) {
                this.availableCredit = availableCredit;
            }

            public String getBusinessDate() {
                return businessDate;
            }

            public void setBusinessDate(String businessDate) {
                this.businessDate = businessDate;
            }

            public boolean isBadDebt() {
                return badDebt;
            }

            public void setBadDebt(boolean badDebt) {
                this.badDebt = badDebt;
            }

            public Object getVkbur() {
                return vkbur;
            }

            public void setVkbur(Object vkbur) {
                this.vkbur = vkbur;
            }

            public Object getLand1() {
                return land1;
            }

            public void setLand1(Object land1) {
                this.land1 = land1;
            }

            public Object getLandx() {
                return landx;
            }

            public void setLandx(Object landx) {
                this.landx = landx;
            }

            public Object getSalesManCode() {
                return salesManCode;
            }

            public void setSalesManCode(Object salesManCode) {
                this.salesManCode = salesManCode;
            }

            public Object getSalesManName() {
                return salesManName;
            }

            public void setSalesManName(Object salesManName) {
                this.salesManName = salesManName;
            }

            public Object getVkgrp() {
                return vkgrp;
            }

            public void setVkgrp(Object vkgrp) {
                this.vkgrp = vkgrp;
            }

            public Object getBezei2() {
                return bezei2;
            }

            public void setBezei2(Object bezei2) {
                this.bezei2 = bezei2;
            }

            public Object getWaers() {
                return waers;
            }

            public void setWaers(Object waers) {
                this.waers = waers;
            }

            public Object getLtext() {
                return ltext;
            }

            public void setLtext(Object ltext) {
                this.ltext = ltext;
            }

            public Object getZterm() {
                return zterm;
            }

            public void setZterm(Object zterm) {
                this.zterm = zterm;
            }

            public Object getZtermDesp() {
                return ztermDesp;
            }

            public void setZtermDesp(Object ztermDesp) {
                this.ztermDesp = ztermDesp;
            }

            public String getCompanyCode() {
                return companyCode;
            }

            public void setCompanyCode(String companyCode) {
                this.companyCode = companyCode;
            }

            public Object getKkber() {
                return kkber;
            }

            public void setKkber(Object kkber) {
                this.kkber = kkber;
            }

            public Object getKlimk() {
                return klimk;
            }

            public void setKlimk(Object klimk) {
                this.klimk = klimk;
            }

            public Object getSkfor() {
                return skfor;
            }

            public void setSkfor(Object skfor) {
                this.skfor = skfor;
            }

            public Object getSsobl() {
                return ssobl;
            }

            public void setSsobl(Object ssobl) {
                this.ssobl = ssobl;
            }

            public Object getOblig() {
                return oblig;
            }

            public void setOblig(Object oblig) {
                this.oblig = oblig;
            }

            public Object getCashc() {
                return cashc;
            }

            public void setCashc(Object cashc) {
                this.cashc = cashc;
            }

            public String getKnkli() {
                return knkli;
            }

            public void setKnkli(String knkli) {
                this.knkli = knkli;
            }

            public Object getCtlpc() {
                return ctlpc;
            }

            public void setCtlpc(Object ctlpc) {
                this.ctlpc = ctlpc;
            }

            public Object getUedat() {
                return uedat;
            }

            public void setUedat(Object uedat) {
                this.uedat = uedat;
            }

            public Object getOeikw() {
                return oeikw;
            }

            public void setOeikw(Object oeikw) {
                this.oeikw = oeikw;
            }

            public Object getAmountUntilMonth() {
                return amountUntilMonth;
            }

            public void setAmountUntilMonth(Object amountUntilMonth) {
                this.amountUntilMonth = amountUntilMonth;
            }

            public Object getActualShip() {
                return actualShip;
            }

            public void setActualShip(Object actualShip) {
                this.actualShip = actualShip;
            }

            public Object getDeliveringTop() {
                return deliveringTop;
            }

            public void setDeliveringTop(Object deliveringTop) {
                this.deliveringTop = deliveringTop;
            }

            public Object getCurrentMonthUnbilledAmount() {
                return currentMonthUnbilledAmount;
            }

            public void setCurrentMonthUnbilledAmount(Object currentMonthUnbilledAmount) {
                this.currentMonthUnbilledAmount = currentMonthUnbilledAmount;
            }

            public Object getMachinesNumberRange() {
                return machinesNumberRange;
            }

            public void setMachinesNumberRange(Object machinesNumberRange) {
                this.machinesNumberRange = machinesNumberRange;
            }

            public Object getOpenMachinesRateRange() {
                return openMachinesRateRange;
            }

            public void setOpenMachinesRateRange(Object openMachinesRateRange) {
                this.openMachinesRateRange = openMachinesRateRange;
            }

            public Object getCartItems() {
                return cartItems;
            }

            public void setCartItems(Object cartItems) {
                this.cartItems = cartItems;
            }

            public Object getLabels() {
                return labels;
            }

            public void setLabels(Object labels) {
                this.labels = labels;
            }

            public Object getOperators() {
                return operators;
            }

            public void setOperators(Object operators) {
                this.operators = operators;
            }

            public Object getTransactionTrackings() {
                return transactionTrackings;
            }

            public void setTransactionTrackings(Object transactionTrackings) {
                this.transactionTrackings = transactionTrackings;
            }

            public Object getEquipments() {
                return equipments;
            }

            public void setEquipments(Object equipments) {
                this.equipments = equipments;
            }

            public Object getInExPortInfos() {
                return inExPortInfos;
            }

            public void setInExPortInfos(Object inExPortInfos) {
                this.inExPortInfos = inExPortInfos;
            }

            public Object getProductionLicenses() {
                return productionLicenses;
            }

            public void setProductionLicenses(Object productionLicenses) {
                this.productionLicenses = productionLicenses;
            }

            public Object getProductionPerformances() {
                return productionPerformances;
            }

            public void setProductionPerformances(Object productionPerformances) {
                this.productionPerformances = productionPerformances;
            }

            public Object getPurchaseTenders() {
                return purchaseTenders;
            }

            public void setPurchaseTenders(Object purchaseTenders) {
                this.purchaseTenders = purchaseTenders;
            }

            public Object getRecruitments() {
                return recruitments;
            }

            public void setRecruitments(Object recruitments) {
                this.recruitments = recruitments;
            }

            public Object getRowMaterials() {
                return rowMaterials;
            }

            public void setRowMaterials(Object rowMaterials) {
                this.rowMaterials = rowMaterials;
            }

            public Object getSpotChecks() {
                return spotChecks;
            }

            public void setSpotChecks(Object spotChecks) {
                this.spotChecks = spotChecks;
            }

            public Object getTaxRatings() {
                return taxRatings;
            }

            public void setTaxRatings(Object taxRatings) {
                this.taxRatings = taxRatings;
            }

            public List<?> getOptionGroup() {
                return optionGroup;
            }

            public void setOptionGroup(List<?> optionGroup) {
                this.optionGroup = optionGroup;
            }
        }
    }
}
