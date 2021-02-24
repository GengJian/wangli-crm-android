package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CustomerBillBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/9 17:41
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/9 1.00 初始版本
 * ****************************************************************
 */
public class CustomerBillBean {

    private boolean last;
    private int totalPages;
    private int totalElements;
    private int number;
    private int size;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;
    private List<SortBean> sort;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public static class ContentBean {


        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private Object fromClientType;
        private String memberNumber;
        private Object member;
        private String memberName;
        private String number;
        private String lineNumber;
        private String fkdat;
        private String vgbel;
        private String vgpos;
        private String aubel;
        private String aupos;
        private String matnr;
        private String charg;
        private String matkl;
        private String arktx;
        private String pstyv;
        private String posar;
        private double fkimg;
        private String vrkme;
        private String kzwi5;
        private String netwr;
        private String mwsbp;
        private String erdat;
        private String erzet;
        private Object erdatv;
        private boolean firstNotified;
        private Object logisticsNumber;
        private Object expressDate;
        private Object expressKey;
        private Object expressValue;
        private Object expressNumber;
        private Object addressee;
        private Object address;
        private Object goldenTaxNumber;
        private Object billingLogistics;
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

        public String getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(String memberNumber) {
            this.memberNumber = memberNumber;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(String lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String getFkdat() {
            return fkdat;
        }

        public void setFkdat(String fkdat) {
            this.fkdat = fkdat;
        }

        public String getVgbel() {
            return vgbel;
        }

        public void setVgbel(String vgbel) {
            this.vgbel = vgbel;
        }

        public String getVgpos() {
            return vgpos;
        }

        public void setVgpos(String vgpos) {
            this.vgpos = vgpos;
        }

        public String getAubel() {
            return aubel;
        }

        public void setAubel(String aubel) {
            this.aubel = aubel;
        }

        public String getAupos() {
            return aupos;
        }

        public void setAupos(String aupos) {
            this.aupos = aupos;
        }

        public String getMatnr() {
            return matnr;
        }

        public void setMatnr(String matnr) {
            this.matnr = matnr;
        }

        public String getCharg() {
            return charg;
        }

        public void setCharg(String charg) {
            this.charg = charg;
        }

        public String getMatkl() {
            return matkl;
        }

        public void setMatkl(String matkl) {
            this.matkl = matkl;
        }

        public String getArktx() {
            return arktx;
        }

        public void setArktx(String arktx) {
            this.arktx = arktx;
        }

        public String getPstyv() {
            return pstyv;
        }

        public void setPstyv(String pstyv) {
            this.pstyv = pstyv;
        }

        public String getPosar() {
            return posar;
        }

        public void setPosar(String posar) {
            this.posar = posar;
        }

        public double getFkimg() {
            return fkimg;
        }

        public void setFkimg(double fkimg) {
            this.fkimg = fkimg;
        }

        public String getVrkme() {
            return vrkme;
        }

        public void setVrkme(String vrkme) {
            this.vrkme = vrkme;
        }

        public String getKzwi5() {
            return kzwi5;
        }

        public void setKzwi5(String kzwi5) {
            this.kzwi5 = kzwi5;
        }

        public String getNetwr() {
            return netwr;
        }

        public void setNetwr(String netwr) {
            this.netwr = netwr;
        }

        public String getMwsbp() {
            return mwsbp;
        }

        public void setMwsbp(String mwsbp) {
            this.mwsbp = mwsbp;
        }

        public String getErdat() {
            return erdat;
        }

        public void setErdat(String erdat) {
            this.erdat = erdat;
        }

        public String getErzet() {
            return erzet;
        }

        public void setErzet(String erzet) {
            this.erzet = erzet;
        }

        public Object getErdatv() {
            return erdatv;
        }

        public void setErdatv(Object erdatv) {
            this.erdatv = erdatv;
        }

        public boolean isFirstNotified() {
            return firstNotified;
        }

        public void setFirstNotified(boolean firstNotified) {
            this.firstNotified = firstNotified;
        }

        public Object getLogisticsNumber() {
            return logisticsNumber;
        }

        public void setLogisticsNumber(Object logisticsNumber) {
            this.logisticsNumber = logisticsNumber;
        }

        public Object getExpressDate() {
            return expressDate;
        }

        public void setExpressDate(Object expressDate) {
            this.expressDate = expressDate;
        }

        public Object getExpressKey() {
            return expressKey;
        }

        public void setExpressKey(Object expressKey) {
            this.expressKey = expressKey;
        }

        public Object getExpressValue() {
            return expressValue;
        }

        public void setExpressValue(Object expressValue) {
            this.expressValue = expressValue;
        }

        public Object getExpressNumber() {
            return expressNumber;
        }

        public void setExpressNumber(Object expressNumber) {
            this.expressNumber = expressNumber;
        }

        public Object getAddressee() {
            return addressee;
        }

        public void setAddressee(Object addressee) {
            this.addressee = addressee;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getGoldenTaxNumber() {
            return goldenTaxNumber;
        }

        public void setGoldenTaxNumber(Object goldenTaxNumber) {
            this.goldenTaxNumber = goldenTaxNumber;
        }

        public Object getBillingLogistics() {
            return billingLogistics;
        }

        public void setBillingLogistics(Object billingLogistics) {
            this.billingLogistics = billingLogistics;
        }

        public List<?> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<?> optionGroup) {
            this.optionGroup = optionGroup;
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : lastModifiedDate
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : false
         * descending : true
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean ascending;
        private boolean descending;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
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
    }
}
