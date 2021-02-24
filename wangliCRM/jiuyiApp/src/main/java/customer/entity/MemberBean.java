package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import commonlyused.bean.NormalOperatorBean;

public class MemberBean {


    private int number;
    private boolean last;
    private int numberOfElements;
    private int size;
    private int totalPages;
    private boolean first;
    private int totalElements;
    private List<SortBean> sort;
    private List<ContentBean> content;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class SortBean {
        /**
         * nullHandling : NATIVE
         * ignoreCase : false
         * property : id
         * ascending : false
         * descending : true
         * direction : DESC
         */

        private String nullHandling;
        private boolean ignoreCase;
        private String property;
        private boolean ascending;
        private boolean descending;
        private String direction;

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }

    public static class ContentBean implements Parcelable{

        public ContentBean() {
        }

        private Object vtext1;
        private Object orto1;
        private int storeNumber;
        private String cityNumber;
        private Object zterm;
        private String bail;
        private NormalOperatorBean.OperatorBeanID operator;
        private Object erdat;
        private Object aufsd;
        private String legalName;
        private String accountBank;
        private String registeredAddress;
        private String areaName;
        private String companyPhone;
        private String statusKey;
        private String provinceNumber;
        private long id;
        private String orgName;
        private String creditLevelKey;
        private String avatarUrl;
        private String areaNumber;
        private String stras;
        private String taxNumber;
        private String marketBailQuentity;
        private String businessScope;
        private String faksd;
        private String afterSalesNumber;
        private Object superiorChain;
        private MemberBeanID firstDistributor;
        private Object land1;
        private Object lifsd;
        private Object kkber;
        private String provinceName;
        private String cooperationTypeValue;
        private Object ztag1;
        private Object region;
        private Object vkorg;
        private Object ztag3;
        private Object ztag2;
        private Object targetQuantity;
        private String assessmentAgreement;
        private Object cassd;
        private String crmNumber;
        private String statusValue;
        private Object growthRate;
        private String creditLevelValue;
        private Object sapNumber;
        private Object vtextf;
        private String operatorName;
        private Object regio;
        private String cooperationTypeKey;
        private int exclusiveShopNumber;
        private String memberLevelValue;
        private String firstDistributorName;
        private String cityName;
        private double registeredCapital;
        private String simpleSpell;
        private Object natureManagement;
        private Object vtextx;
        private String createdOperatorName;
        private String faxAreaCode;
        private Object sperr;
        private String lastModifiedDate;
        private String lastModifiedBy;
        private String abbreviation;
        private String faxPhoneNumber;
        private Object vtweg;
        private Object spart;
        private Object bezei;
        private String marketBail;
        private String brandNames;
        private Object landx;
        private Object knkli;
        private String createdDate;
        private String createdBy;
        private String companyAddress;
        private String faxNumber;
        private String memberLevelKey;

        protected ContentBean(Parcel in) {
            storeNumber = in.readInt();
            cityNumber = in.readString();
            bail = in.readString();
            operator = in.readParcelable(NormalOperatorBean.OperatorBeanID.class.getClassLoader());
            legalName = in.readString();
            accountBank = in.readString();
            registeredAddress = in.readString();
            areaName = in.readString();
            companyPhone = in.readString();
            statusKey = in.readString();
            provinceNumber = in.readString();
            id = in.readLong();
            orgName = in.readString();
            creditLevelKey = in.readString();
            avatarUrl = in.readString();
            areaNumber = in.readString();
            stras = in.readString();
            taxNumber = in.readString();
            marketBailQuentity = in.readString();
            businessScope = in.readString();
            faksd = in.readString();
            afterSalesNumber = in.readString();
            firstDistributor = in.readParcelable(MemberBeanID.class.getClassLoader());
            provinceName = in.readString();
            cooperationTypeValue = in.readString();
            assessmentAgreement = in.readString();
            crmNumber = in.readString();
            statusValue = in.readString();
            creditLevelValue = in.readString();
            operatorName = in.readString();
            cooperationTypeKey = in.readString();
            exclusiveShopNumber = in.readInt();
            memberLevelValue = in.readString();
            firstDistributorName = in.readString();
            cityName = in.readString();
            registeredCapital = in.readDouble();
            simpleSpell = in.readString();
            createdOperatorName = in.readString();
            faxAreaCode = in.readString();
            lastModifiedDate = in.readString();
            lastModifiedBy = in.readString();
            abbreviation = in.readString();
            faxPhoneNumber = in.readString();
            marketBail = in.readString();
            brandNames = in.readString();
            createdDate = in.readString();
            createdBy = in.readString();
            companyAddress = in.readString();
            faxNumber = in.readString();
            memberLevelKey = in.readString();
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel in) {
                return new ContentBean(in);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };

        public Object getVtext1() {
            return vtext1;
        }

        public void setVtext1(Object vtext1) {
            this.vtext1 = vtext1;
        }

        public Object getOrto1() {
            return orto1;
        }

        public void setOrto1(Object orto1) {
            this.orto1 = orto1;
        }

        public int getStoreNumber() {
            return storeNumber;
        }

        public void setStoreNumber(int storeNumber) {
            this.storeNumber = storeNumber;
        }

        public String getCityNumber() {
            return cityNumber;
        }

        public void setCityNumber(String cityNumber) {
            this.cityNumber = cityNumber;
        }

        public Object getZterm() {
            return zterm;
        }

        public void setZterm(Object zterm) {
            this.zterm = zterm;
        }

        public String getBail() {
            return bail;
        }

        public void setBail(String bail) {
            this.bail = bail;
        }

        public NormalOperatorBean.OperatorBeanID getOperator() {
            return operator;
        }

        public void setOperator(NormalOperatorBean.OperatorBeanID operator) {
            this.operator = operator;
        }

        public Object getErdat() {
            return erdat;
        }

        public void setErdat(Object erdat) {
            this.erdat = erdat;
        }

        public Object getAufsd() {
            return aufsd;
        }

        public void setAufsd(Object aufsd) {
            this.aufsd = aufsd;
        }

        public String getLegalName() {
            return legalName;
        }

        public void setLegalName(String legalName) {
            this.legalName = legalName;
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

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public String getStatusKey() {
            return statusKey;
        }

        public void setStatusKey(String statusKey) {
            this.statusKey = statusKey;
        }

        public String getProvinceNumber() {
            return provinceNumber;
        }

        public void setProvinceNumber(String provinceNumber) {
            this.provinceNumber = provinceNumber;
        }

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

        public String getCreditLevelKey() {
            return creditLevelKey;
        }

        public void setCreditLevelKey(String creditLevelKey) {
            this.creditLevelKey = creditLevelKey;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getAreaNumber() {
            return areaNumber;
        }

        public void setAreaNumber(String areaNumber) {
            this.areaNumber = areaNumber;
        }

        public String getStras() {
            return stras;
        }

        public void setStras(String stras) {
            this.stras = stras;
        }

        public String getTaxNumber() {
            return taxNumber;
        }

        public void setTaxNumber(String taxNumber) {
            this.taxNumber = taxNumber;
        }

        public String getMarketBailQuentity() {
            return marketBailQuentity;
        }

        public void setMarketBailQuentity(String marketBailQuentity) {
            this.marketBailQuentity = marketBailQuentity;
        }

        public String getBusinessScope() {
            return businessScope;
        }

        public void setBusinessScope(String businessScope) {
            this.businessScope = businessScope;
        }

        public String getFaksd() {
            return faksd;
        }

        public void setFaksd(String faksd) {
            this.faksd = faksd;
        }

        public String getAfterSalesNumber() {
            return afterSalesNumber;
        }

        public void setAfterSalesNumber(String afterSalesNumber) {
            this.afterSalesNumber = afterSalesNumber;
        }

        public Object getSuperiorChain() {
            return superiorChain;
        }

        public void setSuperiorChain(Object superiorChain) {
            this.superiorChain = superiorChain;
        }

        public MemberBeanID getFirstDistributor() {
            return firstDistributor;
        }

        public void setFirstDistributor(MemberBeanID firstDistributor) {
            this.firstDistributor = firstDistributor;
        }

        public Object getLand1() {
            return land1;
        }

        public void setLand1(Object land1) {
            this.land1 = land1;
        }

        public Object getLifsd() {
            return lifsd;
        }

        public void setLifsd(Object lifsd) {
            this.lifsd = lifsd;
        }

        public Object getKkber() {
            return kkber;
        }

        public void setKkber(Object kkber) {
            this.kkber = kkber;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCooperationTypeValue() {
            return cooperationTypeValue;
        }

        public void setCooperationTypeValue(String cooperationTypeValue) {
            this.cooperationTypeValue = cooperationTypeValue;
        }

        public Object getZtag1() {
            return ztag1;
        }

        public void setZtag1(Object ztag1) {
            this.ztag1 = ztag1;
        }

        public Object getRegion() {
            return region;
        }

        public void setRegion(Object region) {
            this.region = region;
        }

        public Object getVkorg() {
            return vkorg;
        }

        public void setVkorg(Object vkorg) {
            this.vkorg = vkorg;
        }

        public Object getZtag3() {
            return ztag3;
        }

        public void setZtag3(Object ztag3) {
            this.ztag3 = ztag3;
        }

        public Object getZtag2() {
            return ztag2;
        }

        public void setZtag2(Object ztag2) {
            this.ztag2 = ztag2;
        }

        public Object getTargetQuantity() {
            return targetQuantity;
        }

        public void setTargetQuantity(Object targetQuantity) {
            this.targetQuantity = targetQuantity;
        }

        public String getAssessmentAgreement() {
            return assessmentAgreement;
        }

        public void setAssessmentAgreement(String assessmentAgreement) {
            this.assessmentAgreement = assessmentAgreement;
        }

        public Object getCassd() {
            return cassd;
        }

        public void setCassd(Object cassd) {
            this.cassd = cassd;
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

        public Object getGrowthRate() {
            return growthRate;
        }

        public void setGrowthRate(Object growthRate) {
            this.growthRate = growthRate;
        }

        public String getCreditLevelValue() {
            return creditLevelValue;
        }

        public void setCreditLevelValue(String creditLevelValue) {
            this.creditLevelValue = creditLevelValue;
        }

        public Object getSapNumber() {
            return sapNumber;
        }

        public void setSapNumber(Object sapNumber) {
            this.sapNumber = sapNumber;
        }

        public Object getVtextf() {
            return vtextf;
        }

        public void setVtextf(Object vtextf) {
            this.vtextf = vtextf;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public Object getRegio() {
            return regio;
        }

        public void setRegio(Object regio) {
            this.regio = regio;
        }

        public String getCooperationTypeKey() {
            return cooperationTypeKey;
        }

        public void setCooperationTypeKey(String cooperationTypeKey) {
            this.cooperationTypeKey = cooperationTypeKey;
        }

        public int getExclusiveShopNumber() {
            return exclusiveShopNumber;
        }

        public void setExclusiveShopNumber(int exclusiveShopNumber) {
            this.exclusiveShopNumber = exclusiveShopNumber;
        }

        public String getMemberLevelValue() {
            return memberLevelValue;
        }

        public void setMemberLevelValue(String memberLevelValue) {
            this.memberLevelValue = memberLevelValue;
        }

        public String getFirstDistributorName() {
            return firstDistributorName;
        }

        public void setFirstDistributorName(String firstDistributorName) {
            this.firstDistributorName = firstDistributorName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public double getRegisteredCapital() {
            return registeredCapital;
        }

        public void setRegisteredCapital(double registeredCapital) {
            this.registeredCapital = registeredCapital;
        }

        public String getSimpleSpell() {
            return simpleSpell;
        }

        public void setSimpleSpell(String simpleSpell) {
            this.simpleSpell = simpleSpell;
        }

        public Object getNatureManagement() {
            return natureManagement;
        }

        public void setNatureManagement(Object natureManagement) {
            this.natureManagement = natureManagement;
        }

        public Object getVtextx() {
            return vtextx;
        }

        public void setVtextx(Object vtextx) {
            this.vtextx = vtextx;
        }

        public String getCreatedOperatorName() {
            return createdOperatorName;
        }

        public void setCreatedOperatorName(String createdOperatorName) {
            this.createdOperatorName = createdOperatorName;
        }

        public String getFaxAreaCode() {
            return faxAreaCode;
        }

        public void setFaxAreaCode(String faxAreaCode) {
            this.faxAreaCode = faxAreaCode;
        }

        public Object getSperr() {
            return sperr;
        }

        public void setSperr(Object sperr) {
            this.sperr = sperr;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getFaxPhoneNumber() {
            return faxPhoneNumber;
        }

        public void setFaxPhoneNumber(String faxPhoneNumber) {
            this.faxPhoneNumber = faxPhoneNumber;
        }

        public Object getVtweg() {
            return vtweg;
        }

        public void setVtweg(Object vtweg) {
            this.vtweg = vtweg;
        }

        public Object getSpart() {
            return spart;
        }

        public void setSpart(Object spart) {
            this.spart = spart;
        }

        public Object getBezei() {
            return bezei;
        }

        public void setBezei(Object bezei) {
            this.bezei = bezei;
        }

        public String getMarketBail() {
            return marketBail;
        }

        public void setMarketBail(String marketBail) {
            this.marketBail = marketBail;
        }

        public String getBrandNames() {
            return brandNames;
        }

        public void setBrandNames(String brandNames) {
            this.brandNames = brandNames;
        }

        public Object getLandx() {
            return landx;
        }

        public void setLandx(Object landx) {
            this.landx = landx;
        }

        public Object getKnkli() {
            return knkli;
        }

        public void setKnkli(Object knkli) {
            this.knkli = knkli;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getFaxNumber() {
            return faxNumber;
        }

        public void setFaxNumber(String faxNumber) {
            this.faxNumber = faxNumber;
        }

        public String getMemberLevelKey() {
            return memberLevelKey;
        }

        public void setMemberLevelKey(String memberLevelKey) {
            this.memberLevelKey = memberLevelKey;
        }

        public Object getAnnualTargetCompletionRate() {
            return annualTargetCompletionRate;
        }

        public void setAnnualTargetCompletionRate(Object annualTargetCompletionRate) {
            this.annualTargetCompletionRate = annualTargetCompletionRate;
        }

        public Object getPstlz() {
            return pstlz;
        }

        public void setPstlz(Object pstlz) {
            this.pstlz = pstlz;
        }

        private Object annualTargetCompletionRate;
        private Object pstlz;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(storeNumber);
            dest.writeString(cityNumber);
            dest.writeString(bail);
            dest.writeParcelable(operator, flags);
            dest.writeString(legalName);
            dest.writeString(accountBank);
            dest.writeString(registeredAddress);
            dest.writeString(areaName);
            dest.writeString(companyPhone);
            dest.writeString(statusKey);
            dest.writeString(provinceNumber);
            dest.writeLong(id);
            dest.writeString(orgName);
            dest.writeString(creditLevelKey);
            dest.writeString(avatarUrl);
            dest.writeString(areaNumber);
            dest.writeString(stras);
            dest.writeString(taxNumber);
            dest.writeString(marketBailQuentity);
            dest.writeString(businessScope);
            dest.writeString(faksd);
            dest.writeString(afterSalesNumber);
            dest.writeParcelable(firstDistributor, flags);
            dest.writeString(provinceName);
            dest.writeString(cooperationTypeValue);
            dest.writeString(assessmentAgreement);
            dest.writeString(crmNumber);
            dest.writeString(statusValue);
            dest.writeString(creditLevelValue);
            dest.writeString(operatorName);
            dest.writeString(cooperationTypeKey);
            dest.writeInt(exclusiveShopNumber);
            dest.writeString(memberLevelValue);
            dest.writeString(firstDistributorName);
            dest.writeString(cityName);
            dest.writeDouble(registeredCapital);
            dest.writeString(simpleSpell);
            dest.writeString(createdOperatorName);
            dest.writeString(faxAreaCode);
            dest.writeString(lastModifiedDate);
            dest.writeString(lastModifiedBy);
            dest.writeString(abbreviation);
            dest.writeString(faxPhoneNumber);
            dest.writeString(marketBail);
            dest.writeString(brandNames);
            dest.writeString(createdDate);
            dest.writeString(createdBy);
            dest.writeString(companyAddress);
            dest.writeString(faxNumber);
            dest.writeString(memberLevelKey);
        }
    }
}
