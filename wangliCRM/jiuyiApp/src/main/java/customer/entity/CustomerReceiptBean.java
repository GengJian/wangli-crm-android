package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CustomerReceiptBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/9 17:59
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/9 1.00 初始版本
 * ****************************************************************
 */
public class CustomerReceiptBean {

    /**
     * content : [{"createdBy":"1022654","createdDate":"2019-01-09 16:12:50","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-09 16:12:50","id":10,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"number":"AXSK20190109002","member":null,"memberName":"GREEN WING","memberNumber":"100111","amount":11000,"receiptTypeKey":"wire_transfer","receiptTypeValue":"电汇","receiptDate":"2019-01-09","companyKey":"zj_aikosolar","companyValue":"浙江爱旭","currencyKey":"AFN","currencyValue":"阿富汗语","status":"CANCEL","statusDesp":"已取消","remark":"","type":"LOANS","meId":979,"typeDesp":"贷款","receiptContractAssign":null},{"createdBy":"1004605","createdDate":"2019-01-09 15:50:11","lastModifiedBy":"1004605","lastModifiedDate":"2019-01-09 15:50:11","id":9,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[{"name":"account","content":"过账"},{"name":"cancel","content":"取消"}],"number":"AXSK20190109001","member":null,"memberName":"江西晶科","memberNumber":"200011","amount":222,"receiptTypeKey":"cash","receiptTypeValue":"现金","receiptDate":"2019-01-08","companyKey":"zj_aikosolar","companyValue":"浙江爱旭","currencyKey":"ADP","currencyValue":"安道尔 比塞塔","status":"NOT_ACCOUNT","statusDesp":"未过账","remark":"222","type":"LOANS","meId":67,"typeDesp":"贷款","receiptContractAssign":null},{"createdBy":"1004605","createdDate":"2019-01-05 20:35:14","lastModifiedBy":"1004605","lastModifiedDate":"2019-01-05 20:35:14","id":8,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[{"name":"cancel","content":"取消"}],"number":"AXSK20190105001","member":null,"memberName":"环境","memberNumber":"300001","amount":332,"receiptTypeKey":"electronic_acceptance","receiptTypeValue":"电子承兑","receiptDate":"2019-01-16","companyKey":"gd_aikosolar","companyValue":"广东爱旭","currencyKey":"AOA","currencyValue":"Angolanische Kwanza","status":"ACCOUNT","statusDesp":"已过账","remark":"不错","type":"LOANS","meId":184,"typeDesp":"贷款","receiptContractAssign":null},{"createdBy":"15167156690","createdDate":"2018-12-25 20:23:19","lastModifiedBy":"15167156690","lastModifiedDate":"2018-12-25 20:23:19","id":7,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"number":"AXSK20181225002","member":null,"memberName":"新日光能源","memberNumber":"100018","amount":7000,"receiptTypeKey":"bank_draft","receiptTypeValue":"银行汇票","receiptDate":"2018-12-25","companyKey":"zj_aikosolar","companyValue":"浙江爱旭","currencyKey":"JPY","currencyValue":"日元","status":"CANCEL","statusDesp":"已取消","remark":"","type":"LOANS","meId":231,"typeDesp":"贷款","receiptContractAssign":null},{"createdBy":"13959896162","createdDate":"2018-12-25 18:19:19","lastModifiedBy":"13959896162","lastModifiedDate":"2018-12-25 18:19:19","id":6,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[{"name":"cancel","content":"取消"}],"number":"AXSK20181225001","member":null,"memberName":"昆山威日光伏有限公司","memberNumber":"200160","amount":2223333,"receiptTypeKey":"letters_credit","receiptTypeValue":"信用证","receiptDate":"2018-12-19","companyKey":"gd_aikosolar","companyValue":"广东爱旭","currencyKey":"ANG","currencyValue":"西印第安盾","status":"ACCOUNT","statusDesp":"已过账","remark":"加油啊","type":"LOANS","meId":236,"typeDesp":"贷款","receiptContractAssign":null},{"createdBy":"13959896162","createdDate":"2018-12-24 11:03:48","lastModifiedBy":"13959896162","lastModifiedDate":"2018-12-24 11:03:48","id":5,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"number":"AXSK20181224002","member":null,"memberName":"常州中弘","memberNumber":"200056","amount":34,"receiptTypeKey":"electronic_acceptance","receiptTypeValue":"电子承兑","receiptDate":"2018-12-25","companyKey":"zj_aikosolar","companyValue":"浙江爱旭","currencyKey":"BBD","currencyValue":"巴巴多斯 元","status":"CANCEL","statusDesp":"已取消","remark":"33","type":"LOANS","meId":29,"typeDesp":"贷款","receiptContractAssign":null},{"createdBy":"13959896162","createdDate":"2018-12-22 18:16:04","lastModifiedBy":"13959896162","lastModifiedDate":"2018-12-22 18:16:04","id":3,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"number":"AXSK20181222001","member":null,"memberName":"深圳荣基佳业","memberNumber":"200170","amount":21122,"receiptTypeKey":"electronic_acceptance","receiptTypeValue":"电子承兑","receiptDate":"2018-12-21","companyKey":"zj_aikosolar","companyValue":"浙江爱旭","currencyKey":"AMD","currencyValue":"亚美尼亚 打兰","status":"CANCEL","statusDesp":"已取消","remark":"212","type":"LOANS","meId":null,"typeDesp":"贷款","receiptContractAssign":null},{"createdBy":"13959896162","createdDate":"2018-12-21 19:19:57","lastModifiedBy":"13959896162","lastModifiedDate":"2018-12-21 19:19:57","id":2,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"number":"axsk20181221005","member":null,"memberName":"东莞南玻","memberNumber":"200130","amount":122,"receiptTypeKey":"letters_credit","receiptTypeValue":"信用证","receiptDate":"2018-12-19","companyKey":"zj_aikosolar","companyValue":"浙江爱旭","currencyKey":"AOA","currencyValue":"Angolanische Kwanza","status":null,"statusDesp":"","remark":"112","type":null,"meId":null,"typeDesp":"","receiptContractAssign":null},{"createdBy":"13959896162","createdDate":"2018-12-21 19:19:55","lastModifiedBy":"13959896162","lastModifiedDate":"2018-12-21 19:19:55","id":1,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"number":"axsk20181221004","member":null,"memberName":"江阴九龄","memberNumber":"500001","amount":333,"receiptTypeKey":"electronic_acceptance","receiptTypeValue":"电子承兑","receiptDate":"2018-12-27","companyKey":"gd_aikosolar","companyValue":"广东爱旭","currencyKey":"ANG","currencyValue":"西印第安盾","status":null,"statusDesp":"","remark":"22","type":null,"meId":null,"typeDesp":"","receiptContractAssign":null}]
     * last : true
     * totalElements : 9
     * totalPages : 1
     * number : 0
     * size : 10
     * sort : [{"direction":"DESC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * first : true
     * numberOfElements : 9
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
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
        /**
         * createdBy : 1022654
         * createdDate : 2019-01-09 16:12:50
         * lastModifiedBy : 1022654
         * lastModifiedDate : 2019-01-09 16:12:50
         * id : 10
         * deleted : false
         * sort : 10
         * fromClientType : null
         * optionGroup : []
         * number : AXSK20190109002
         * member : null
         * memberName : GREEN WING
         * memberNumber : 100111
         * amount : 11000.0
         * receiptTypeKey : wire_transfer
         * receiptTypeValue : 电汇
         * receiptDate : 2019-01-09
         * companyKey : zj_aikosolar
         * companyValue : 浙江爱旭
         * currencyKey : AFN
         * currencyValue : 阿富汗语
         * status : CANCEL
         * statusDesp : 已取消
         * remark :
         * type : LOANS
         * meId : 979
         * typeDesp : 贷款
         * receiptContractAssign : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private Object fromClientType;
        private String number;
        private Object member;
        private String memberName;
        private String memberNumber;
        private double amount;
        private String receiptTypeKey;
        private String receiptTypeValue;
        private String receiptDate;
        private String companyKey;
        private String companyValue;
        private String currencyKey;
        private String currencyValue;
        private String status;
        private String statusDesp;
        private String remark;
        private String type;
        private int meId;
        private String typeDesp;
        private Object receiptContractAssign;
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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

        public String getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(String memberNumber) {
            this.memberNumber = memberNumber;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getReceiptTypeKey() {
            return receiptTypeKey;
        }

        public void setReceiptTypeKey(String receiptTypeKey) {
            this.receiptTypeKey = receiptTypeKey;
        }

        public String getReceiptTypeValue() {
            return receiptTypeValue;
        }

        public void setReceiptTypeValue(String receiptTypeValue) {
            this.receiptTypeValue = receiptTypeValue;
        }

        public String getReceiptDate() {
            return receiptDate;
        }

        public void setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
        }

        public String getCompanyKey() {
            return companyKey;
        }

        public void setCompanyKey(String companyKey) {
            this.companyKey = companyKey;
        }

        public String getCompanyValue() {
            return companyValue;
        }

        public void setCompanyValue(String companyValue) {
            this.companyValue = companyValue;
        }

        public String getCurrencyKey() {
            return currencyKey;
        }

        public void setCurrencyKey(String currencyKey) {
            this.currencyKey = currencyKey;
        }

        public String getCurrencyValue() {
            return currencyValue;
        }

        public void setCurrencyValue(String currencyValue) {
            this.currencyValue = currencyValue;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusDesp() {
            return statusDesp;
        }

        public void setStatusDesp(String statusDesp) {
            this.statusDesp = statusDesp;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getMeId() {
            return meId;
        }

        public void setMeId(int meId) {
            this.meId = meId;
        }

        public String getTypeDesp() {
            return typeDesp;
        }

        public void setTypeDesp(String typeDesp) {
            this.typeDesp = typeDesp;
        }

        public Object getReceiptContractAssign() {
            return receiptContractAssign;
        }

        public void setReceiptContractAssign(Object receiptContractAssign) {
            this.receiptContractAssign = receiptContractAssign;
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
         * property : id
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
