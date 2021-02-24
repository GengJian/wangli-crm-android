package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:LaboratoryBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/8 14:31
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/8 1.00 初始版本
 * ****************************************************************
 */
public class LaboratoryBean {

    /**
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 1
     * size : 5
     * totalElements : 1
     * totalPages : 1
     * sort : [{"ascending":true,"descending":false,"direction":"ASC","ignoreCase":false,"nullHandling":"NATIVE","property":"id"}]
     * content : [{"id":8,"sort":10,"createdDate":1546929048013,"lastModifiedDate":1546929048013,"createdBy":"1022654","lastModifiedBy":"1022654","name":"a","typeValue":"校企共建","typeKey":"school_enterprise","member":{"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546928930188,"createdBy":"system","lastModifiedBy":"1022768","cooperationTypeValue":"供应商","simpleSpell":"dgsgr","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","sapNumber":"200567","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配"},"businessTypeKey":"silicon_factory","businessTypeValue":"硅料厂","electronMicroscope":"j","mechanicalMeter":"e","environmentTestBox":"f","ultravioletBox":"l","solarIrradianceMeter":"o","solarSimulator":"h","area":"b","researchCount":2,"labBoss":"c","outputResult":"i","yearPutCost":7,"lidTestBox":"k","hotSpotBox":"g","otherInfo":"pa"}]
     */

    private boolean first;
    private boolean last;
    private int number;
    private int numberOfElements;
    private int size;
    private int totalElements;
    private int totalPages;
    private List<SortBean> sort;
    private List<ContentBean> content;

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
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
         * ascending : true
         * descending : false
         * direction : ASC
         * ignoreCase : false
         * nullHandling : NATIVE
         * property : id
         */

        private boolean ascending;
        private boolean descending;
        private String direction;
        private boolean ignoreCase;
        private String nullHandling;
        private String property;

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

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }

    public static class ContentBean {
        /**
         * id : 8
         * sort : 10
         * createdDate : 1546929048013
         * lastModifiedDate : 1546929048013
         * createdBy : 1022654
         * lastModifiedBy : 1022654
         * name : a
         * typeValue : 校企共建
         * typeKey : school_enterprise
         * member : {"id":896,"sort":1,"createdDate":1546079225376,"lastModifiedDate":1546928930188,"createdBy":"system","lastModifiedBy":"1022768","cooperationTypeValue":"供应商","simpleSpell":"dgsgr","statusValue":"潜在","statusKey":"potential","crmNumber":"200567","abbreviation":"东莞市冠润","orgName":"东莞市冠润印刷机械设备科技有限公司","sapNumber":"200567","arOperatorName":"未分配","frOperatorName":"未分配","srOperatorName":"未分配"}
         * businessTypeKey : silicon_factory
         * businessTypeValue : 硅料厂
         * electronMicroscope : j
         * mechanicalMeter : e
         * environmentTestBox : f
         * ultravioletBox : l
         * solarIrradianceMeter : o
         * solarSimulator : h
         * area : b
         * researchCount : 2
         * labBoss : c
         * outputResult : i
         * yearPutCost : 7
         * lidTestBox : k
         * hotSpotBox : g
         * otherInfo : pa
         */

        private long id;
        private int sort;
        private String createdDate;
        private String lastModifiedDate;
        private String createdBy;
        private String lastModifiedBy;
        private String name;
        private String typeValue;
        private String typeKey;
        private MemberBeanID member;
        private String businessTypeKey;
        private String businessTypeValue;
        private String electronMicroscope;
        private String mechanicalMeter;
        private String environmentTestBox;
        private String ultravioletBox;
        private String solarIrradianceMeter;
        private String solarSimulator;
        private String area;
        private int researchCount;
        private String labBoss;
        private String outputResult;
        private double yearPutCost;
        private String lidTestBox;
        private String hotSpotBox;
        private String otherInfo;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypeValue() {
            return typeValue;
        }

        public void setTypeValue(String typeValue) {
            this.typeValue = typeValue;
        }

        public String getTypeKey() {
            return typeKey;
        }

        public void setTypeKey(String typeKey) {
            this.typeKey = typeKey;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getBusinessTypeKey() {
            return businessTypeKey;
        }

        public void setBusinessTypeKey(String businessTypeKey) {
            this.businessTypeKey = businessTypeKey;
        }

        public String getBusinessTypeValue() {
            return businessTypeValue;
        }

        public void setBusinessTypeValue(String businessTypeValue) {
            this.businessTypeValue = businessTypeValue;
        }

        public String getElectronMicroscope() {
            return electronMicroscope;
        }

        public void setElectronMicroscope(String electronMicroscope) {
            this.electronMicroscope = electronMicroscope;
        }

        public String getMechanicalMeter() {
            return mechanicalMeter;
        }

        public void setMechanicalMeter(String mechanicalMeter) {
            this.mechanicalMeter = mechanicalMeter;
        }

        public String getEnvironmentTestBox() {
            return environmentTestBox;
        }

        public void setEnvironmentTestBox(String environmentTestBox) {
            this.environmentTestBox = environmentTestBox;
        }

        public String getUltravioletBox() {
            return ultravioletBox;
        }

        public void setUltravioletBox(String ultravioletBox) {
            this.ultravioletBox = ultravioletBox;
        }

        public String getSolarIrradianceMeter() {
            return solarIrradianceMeter;
        }

        public void setSolarIrradianceMeter(String solarIrradianceMeter) {
            this.solarIrradianceMeter = solarIrradianceMeter;
        }

        public String getSolarSimulator() {
            return solarSimulator;
        }

        public void setSolarSimulator(String solarSimulator) {
            this.solarSimulator = solarSimulator;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getResearchCount() {
            return researchCount;
        }

        public void setResearchCount(int researchCount) {
            this.researchCount = researchCount;
        }

        public String getLabBoss() {
            return labBoss;
        }

        public void setLabBoss(String labBoss) {
            this.labBoss = labBoss;
        }

        public String getOutputResult() {
            return outputResult;
        }

        public void setOutputResult(String outputResult) {
            this.outputResult = outputResult;
        }

        public double getYearPutCost() {
            return yearPutCost;
        }

        public void setYearPutCost(double yearPutCost) {
            this.yearPutCost = yearPutCost;
        }

        public String getLidTestBox() {
            return lidTestBox;
        }

        public void setLidTestBox(String lidTestBox) {
            this.lidTestBox = lidTestBox;
        }

        public String getHotSpotBox() {
            return hotSpotBox;
        }

        public void setHotSpotBox(String hotSpotBox) {
            this.hotSpotBox = hotSpotBox;
        }

        public String getOtherInfo() {
            return otherInfo;
        }

        public void setOtherInfo(String otherInfo) {
            this.otherInfo = otherInfo;
        }

    }
}
