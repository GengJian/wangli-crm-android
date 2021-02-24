package dynamic.entity;

import java.util.List;

import customer.entity.MemberBeanID;

/**
 * ****************************************************************
 * 文件名称:DyReceiptBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/21 11:26
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/21 1.00 初始版本
 * ****************************************************************
 */
public class DyReceiptBean {

    /**
     * number : 0
     * last : false
     * numberOfElements : 10
     * size : 10
     * totalPages : 2
     * sort : [{"nullHandling":"NATIVE","ignoreCase":false,"property":"id","ascending":false,"descending":true,"direction":"DESC"}]
     * first : true
     * content : [{"companyValue":"广东爱旭","optionGroup":[{"name":"account","content":"过账"},{"name":"cancel","content":"取消"}],"memberName":"江西晶科","remark":"21","type":"CASH_DEPOSIT","typeDesp":"保证金","number":"AXSK20190116007","member":{"crmNumber":"200011","statusValue":null,"srOperatorName":"秦光明","creditLevelValue":"AAA","office":null,"sapNumber":"200011","arOperatorName":"徐超","frOperator":{"departmentName":null,"lastModifiedDate":"2019-01-21 09:30:29","lastModifiedBy":"潘梦洋","sort":10,"title":"市场专员(z)","createdDate":"2018-11-29 21:15:42","createdBy":"15167156690","name":"潘梦洋","id":1,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200},"oaId":"2381","username":"1022654","oaCode":"1022654"},"memberLevelValue":"普通","employeeSizeValue":"","registeredCapital":2300,"simpleSpell":"jxjk","statusKey":null,"srOperator":{"departmentName":null,"lastModifiedDate":"2019-01-20 15:54:35","lastModifiedBy":"秦光明","sort":10,"title":"品质客服高级工程师","createdDate":"2019-01-10 13:46:11","createdBy":"徐超","name":"秦光明","id":140,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 13:46:13","createdBy":"13901565517","lastModifiedBy":"徐超","name":"客服部（Z）","oaDepartmentId":"440","id":92,"sort":200},"oaId":"1708","username":"1017179","oaCode":"1017179"},"id":2326,"companyTypeValue":"有限责任公司","orgName":"晶科能源有限公司","arOperator":{"departmentName":null,"lastModifiedDate":"2019-01-20 20:39:36","lastModifiedBy":"徐超","sort":10,"title":"销售经理（Z）","createdDate":"2019-01-10 12:05:26","createdBy":"费婷","name":"徐超","id":139,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 12:05:27","createdBy":"13901565517","lastModifiedBy":"费婷","name":"国内销售部（Z）","oaDepartmentId":"411","id":75,"sort":200},"oaId":"651","username":"1004613","oaCode":"1004613"},"lastModifiedDate":"2019-01-20 19:50:48","avatarUrl":null,"lastModifiedBy":"徐超","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"江西晶科","customerDemand":"组件和硅片","createdDate":"2019-01-10 17:03:04","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":"江西省","cooperationTypeValue":"直销","frOperatorName":"潘梦洋"},"id":19,"companyKey":"gd_aikosolar","meId":2326,"amount":21,"lastModifiedDate":"2019-01-16 09:51:27","lastModifiedBy":"曹康","receiptDate":"2019-01-17 00:00:00","sort":10,"statusDesp":"未过账","receiptTypeValue":"电汇","createdDate":"2019-01-16 09:51:27","memberNumber":"200011","createdBy":"曹康","currencyValue":"中国人民币 元","receiptTypeKey":"wire_transfer","currencyKey":"RMB","status":"NOT_ACCOUNT"},{"companyValue":"浙江爱旭","optionGroup":[{"name":"cancel","content":"取消"}],"memberName":"江西晶科","remark":"21","type":"CASH_DEPOSIT","typeDesp":"保证金","number":"AXSK20190116006","member":{"crmNumber":"200230","statusValue":"正式","srOperatorName":"未分配","creditLevelValue":null,"office":null,"sapNumber":"200230","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"sx","statusKey":"formal","srOperator":null,"id":2571,"companyTypeValue":null,"orgName":"山东硕响新能源有限责任公司","arOperator":null,"lastModifiedDate":"2019-01-13 14:16:40","avatarUrl":null,"lastModifiedBy":"系统自动创建","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"硕响","customerDemand":null,"createdDate":"2019-01-13 14:15:43","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":null,"cooperationTypeValue":"直销","frOperatorName":"未分配"},"id":18,"companyKey":"zj_aikosolar","meId":2571,"amount":23,"lastModifiedDate":"2019-01-16 09:31:58","lastModifiedBy":"曹康","receiptDate":"2019-01-17 00:00:00","sort":10,"statusDesp":"已过账","receiptTypeValue":"电汇","createdDate":"2019-01-16 09:31:58","memberNumber":"200230","createdBy":"曹康","currencyValue":"阿富汗语","receiptTypeKey":"wire_transfer","currencyKey":"AFA","status":"ACCOUNT"},{"companyValue":"浙江爱旭","optionGroup":[{"name":"cancel","content":"取消"}],"memberName":"浙江晶科","remark":"21","type":"CASH_DEPOSIT","typeDesp":"保证金","number":"AXSK20190116005","member":{"crmNumber":"200139","statusValue":"正式","srOperatorName":"未分配","creditLevelValue":null,"office":null,"sapNumber":"200139","arOperatorName":"徐超","frOperator":{"departmentName":null,"lastModifiedDate":"2019-01-20 16:31:22","lastModifiedBy":"范金纯","sort":10,"title":"审计专员(s)","createdDate":"2018-11-29 21:15:42","createdBy":"15167156698","name":"范金纯","id":7,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:16:08","createdBy":"13901565517","lastModifiedBy":"徐超","name":"审计部(Z)","oaDepartmentId":"253","id":69,"sort":200},"oaId":"2231","username":"1022768","oaCode":"1022768"},"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"zjjk","statusKey":"formal","srOperator":null,"id":2415,"companyTypeValue":null,"orgName":"浙江晶科能源有限公司","arOperator":{"departmentName":null,"lastModifiedDate":"2019-01-20 20:39:36","lastModifiedBy":"徐超","sort":10,"title":"销售经理（Z）","createdDate":"2019-01-10 12:05:26","createdBy":"费婷","name":"徐超","id":139,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 12:05:27","createdBy":"13901565517","lastModifiedBy":"费婷","name":"国内销售部（Z）","oaDepartmentId":"411","id":75,"sort":200},"oaId":"651","username":"1004613","oaCode":"1004613"},"lastModifiedDate":"2019-01-13 14:15:56","avatarUrl":null,"lastModifiedBy":"系统自动创建","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"浙江晶科","customerDemand":null,"createdDate":"2019-01-10 17:03:06","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":null,"cooperationTypeValue":"直销","frOperatorName":"范金纯"},"id":17,"companyKey":"zj_aikosolar","meId":2415,"amount":21,"lastModifiedDate":"2019-01-16 09:30:20","lastModifiedBy":"曹康","receiptDate":"2019-01-17 00:00:00","sort":10,"statusDesp":"已过账","receiptTypeValue":"电汇","createdDate":"2019-01-16 09:30:20","memberNumber":"200139","createdBy":"曹康","currencyValue":"阿富汗语","receiptTypeKey":"wire_transfer","currencyKey":"AFA","status":"ACCOUNT"},{"companyValue":"广东爱旭","optionGroup":[{"name":"account","content":"过账"},{"name":"cancel","content":"取消"}],"memberName":"比亚迪","remark":"12","type":"CASH_DEPOSIT","typeDesp":"保证金","number":"AXSK20190116004","member":{"crmNumber":"200433","statusValue":"潜在","srOperatorName":"未分配","creditLevelValue":null,"office":null,"sapNumber":"200433","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"byd","statusKey":"potential","srOperator":null,"id":2832,"companyTypeValue":null,"orgName":"深圳市比亚迪供应链管理有限公司","arOperator":null,"lastModifiedDate":"2019-01-17 17:55:50","avatarUrl":"d85b3f8daadf4632be678977cfad6038","lastModifiedBy":"潘梦洋","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"比亚迪","customerDemand":null,"createdDate":"2019-01-13 14:15:49","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":null,"cooperationTypeValue":"直销","frOperatorName":"未分配"},"id":16,"companyKey":"gd_aikosolar","meId":2832,"amount":23,"lastModifiedDate":"2019-01-16 09:29:15","lastModifiedBy":"曹康","receiptDate":"2019-01-17 00:00:00","sort":10,"statusDesp":"未过账","receiptTypeValue":"电汇","createdDate":"2019-01-16 09:29:15","memberNumber":"200433","createdBy":"曹康","currencyValue":"阿拉伯联合酋长国 迪拉姆","receiptTypeKey":"wire_transfer","currencyKey":"AED","status":"NOT_ACCOUNT"},{"companyValue":"浙江爱旭","optionGroup":[{"name":"account","content":"过账"},{"name":"cancel","content":"取消"}],"memberName":"宝利特","remark":"32","type":"CASH_DEPOSIT","typeDesp":"保证金","number":"AXSK20190116003","member":{"crmNumber":"200430","statusValue":"正式","srOperatorName":"未分配","creditLevelValue":null,"office":null,"sapNumber":"200430","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"blt","statusKey":"formal","srOperator":null,"id":2827,"companyTypeValue":null,"orgName":"浙江宝利特新能源股份有限公司","arOperator":null,"lastModifiedDate":"2019-01-13 14:16:44","avatarUrl":null,"lastModifiedBy":"系统自动创建","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"宝利特","customerDemand":null,"createdDate":"2019-01-13 14:15:49","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":null,"cooperationTypeValue":"直销","frOperatorName":"未分配"},"id":15,"companyKey":"zj_aikosolar","meId":2827,"amount":32,"lastModifiedDate":"2019-01-16 09:24:41","lastModifiedBy":"曹康","receiptDate":"2019-01-17 00:00:00","sort":10,"statusDesp":"未过账","receiptTypeValue":"电汇","createdDate":"2019-01-16 09:24:41","memberNumber":"200430","createdBy":"曹康","currencyValue":"土耳其里拉（旧）","receiptTypeKey":"wire_transfer","currencyKey":"TRL","status":"NOT_ACCOUNT"},{"companyValue":"广东爱旭","optionGroup":[{"name":"account","content":"过账"},{"name":"cancel","content":"取消"}],"memberName":"比亚迪","remark":"32","type":"CASH_DEPOSIT","typeDesp":"保证金","number":"AXSK20190116002","member":{"crmNumber":"200433","statusValue":"潜在","srOperatorName":"未分配","creditLevelValue":null,"office":null,"sapNumber":"200433","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"byd","statusKey":"potential","srOperator":null,"id":2832,"companyTypeValue":null,"orgName":"深圳市比亚迪供应链管理有限公司","arOperator":null,"lastModifiedDate":"2019-01-17 17:55:50","avatarUrl":"d85b3f8daadf4632be678977cfad6038","lastModifiedBy":"潘梦洋","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"比亚迪","customerDemand":null,"createdDate":"2019-01-13 14:15:49","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":null,"cooperationTypeValue":"直销","frOperatorName":"未分配"},"id":14,"companyKey":"gd_aikosolar","meId":2832,"amount":23,"lastModifiedDate":"2019-01-16 09:23:42","lastModifiedBy":"曹康","receiptDate":"2019-01-17 00:00:00","sort":10,"statusDesp":"未过账","receiptTypeValue":"电汇","createdDate":"2019-01-16 09:23:42","memberNumber":"200433","createdBy":"曹康","currencyValue":"特立尼达和多巴哥 元","receiptTypeKey":"wire_transfer","currencyKey":"TTD","status":"NOT_ACCOUNT"},{"companyValue":"浙江爱旭","optionGroup":[{"name":"account","content":"过账"},{"name":"cancel","content":"取消"}],"memberName":"比亚迪","remark":"30","type":"CASH_DEPOSIT","typeDesp":"保证金","number":"AXSK20190116001","member":{"crmNumber":"200433","statusValue":"潜在","srOperatorName":"未分配","creditLevelValue":null,"office":null,"sapNumber":"200433","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"byd","statusKey":"potential","srOperator":null,"id":2832,"companyTypeValue":null,"orgName":"深圳市比亚迪供应链管理有限公司","arOperator":null,"lastModifiedDate":"2019-01-17 17:55:50","avatarUrl":"d85b3f8daadf4632be678977cfad6038","lastModifiedBy":"潘梦洋","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"比亚迪","customerDemand":null,"createdDate":"2019-01-13 14:15:49","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":null,"cooperationTypeValue":"直销","frOperatorName":"未分配"},"id":13,"companyKey":"zj_aikosolar","meId":2832,"amount":10000,"lastModifiedDate":"2019-01-16 09:22:18","lastModifiedBy":"曹康","receiptDate":"2019-01-17 00:00:00","sort":10,"statusDesp":"未过账","receiptTypeValue":"电汇","createdDate":"2019-01-16 09:22:18","memberNumber":"200433","createdBy":"曹康","currencyValue":"福克兰 镑","receiptTypeKey":"wire_transfer","currencyKey":"FKP","status":"NOT_ACCOUNT"},{"companyValue":"浙江爱旭","optionGroup":[{"name":"cancel","content":"取消"}],"memberName":"三水弘泰","remark":"11","type":"LOANS","typeDesp":"贷款","number":"AXSK20190115001","member":{"crmNumber":"200011","statusValue":null,"srOperatorName":"秦光明","creditLevelValue":"AAA","office":null,"sapNumber":"200011","arOperatorName":"徐超","frOperator":{"departmentName":null,"lastModifiedDate":"2019-01-21 09:30:29","lastModifiedBy":"潘梦洋","sort":10,"title":"市场专员(z)","createdDate":"2018-11-29 21:15:42","createdBy":"15167156690","name":"潘梦洋","id":1,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200},"oaId":"2381","username":"1022654","oaCode":"1022654"},"memberLevelValue":"普通","employeeSizeValue":"","registeredCapital":2300,"simpleSpell":"jxjk","statusKey":null,"srOperator":{"departmentName":null,"lastModifiedDate":"2019-01-20 15:54:35","lastModifiedBy":"秦光明","sort":10,"title":"品质客服高级工程师","createdDate":"2019-01-10 13:46:11","createdBy":"徐超","name":"秦光明","id":140,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 13:46:13","createdBy":"13901565517","lastModifiedBy":"徐超","name":"客服部（Z）","oaDepartmentId":"440","id":92,"sort":200},"oaId":"1708","username":"1017179","oaCode":"1017179"},"id":2326,"companyTypeValue":"有限责任公司","orgName":"晶科能源有限公司","arOperator":{"departmentName":null,"lastModifiedDate":"2019-01-20 20:39:36","lastModifiedBy":"徐超","sort":10,"title":"销售经理（Z）","createdDate":"2019-01-10 12:05:26","createdBy":"费婷","name":"徐超","id":139,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 12:05:27","createdBy":"13901565517","lastModifiedBy":"费婷","name":"国内销售部（Z）","oaDepartmentId":"411","id":75,"sort":200},"oaId":"651","username":"1004613","oaCode":"1004613"},"lastModifiedDate":"2019-01-20 19:50:48","avatarUrl":null,"lastModifiedBy":"徐超","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"江西晶科","customerDemand":"组件和硅片","createdDate":"2019-01-10 17:03:04","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":"江西省","cooperationTypeValue":"直销","frOperatorName":"潘梦洋"},"id":12,"companyKey":"zj_aikosolar","meId":2326,"amount":22,"lastModifiedDate":"2019-01-15 23:35:35","lastModifiedBy":"曹康","receiptDate":"2019-01-16 00:00:00","sort":10,"statusDesp":"已过账","receiptTypeValue":"电汇","createdDate":"2019-01-15 23:35:35","memberNumber":"200011","createdBy":"曹康","currencyValue":"西印第安盾","receiptTypeKey":"wire_transfer","currencyKey":"ANG","status":"ACCOUNT"},{"companyValue":"浙江爱旭","optionGroup":[{"name":"cancel","content":"取消"}],"memberName":"移动端测试","remark":"00","type":"CASH_DEPOSIT","typeDesp":"保证金","number":"AXSK20190112001","member":{"crmNumber":"A20190112002","statusValue":"潜在","srOperatorName":"未分配","creditLevelValue":"AAA","office":null,"sapNumber":null,"arOperatorName":"未分配","frOperator":null,"memberLevelValue":"战略","employeeSizeValue":null,"registeredCapital":1.2E7,"simpleSpell":"yddcs","statusKey":"potential","srOperator":null,"id":2538,"companyTypeValue":"有限责任公司","orgName":"新建客户消息测试1","arOperator":null,"lastModifiedDate":"2019-01-12 16:43:49","avatarUrl":null,"lastModifiedBy":"潘梦洋","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"移动端测试","customerDemand":"浙江华云","createdDate":"2019-01-12 16:43:49","createdBy":"潘梦洋","dealer":null,"riskLevelValue":null,"provinceName":"浙江","cooperationTypeValue":"直销","frOperatorName":"未分配"},"id":11,"companyKey":"zj_aikosolar","meId":2538,"amount":98,"lastModifiedDate":"2019-01-12 19:38:39","lastModifiedBy":"何达能","receiptDate":"2019-01-15 00:00:00","sort":10,"statusDesp":"已过账","receiptTypeValue":"电汇","createdDate":"2019-01-12 19:38:39","memberNumber":"A20190112002","createdBy":"何达能","currencyValue":"阿拉伯联合酋长国 迪拉姆","receiptTypeKey":"wire_transfer","currencyKey":"AED","status":"ACCOUNT"},{"meId":979,"companyValue":"浙江爱旭","amount":11000,"lastModifiedDate":"2019-01-09 16:12:50","lastModifiedBy":"潘梦洋","receiptDate":"2019-01-09 00:00:00","memberName":"GREEN WING","remark":"","sort":10,"type":"LOANS","statusDesp":"已取消","typeDesp":"贷款","number":"AXSK20190109002","receiptTypeValue":"电汇","createdDate":"2019-01-09 16:12:50","memberNumber":"100111","createdBy":"潘梦洋","currencyValue":"阿富汗语","receiptTypeKey":"wire_transfer","member":{"crmNumber":"100111","statusValue":"正式","srOperatorName":"未分配","creditLevelValue":null,"office":null,"sapNumber":"100111","arOperatorName":"未分配","frOperator":null,"memberLevelValue":"普通","employeeSizeValue":null,"registeredCapital":null,"simpleSpell":"GREEN WING","statusKey":"formal","srOperator":null,"id":979,"companyTypeValue":null,"orgName":"GREEN WING SOLAR TECHNOLOGY","arOperator":null,"lastModifiedDate":"2019-01-13 14:16:31","avatarUrl":null,"lastModifiedBy":"系统自动创建","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"GREEN WING","customerDemand":null,"createdDate":"2019-01-08 17:36:45","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":null,"cooperationTypeValue":"直销","frOperatorName":"未分配"},"currencyKey":"AFN","id":10,"companyKey":"zj_aikosolar","status":"CANCEL"}]
     * totalElements : 18
     */

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

    public static class ContentBean {
        /**
         * companyValue : 广东爱旭
         * optionGroup : [{"name":"account","content":"过账"},{"name":"cancel","content":"取消"}]
         * memberName : 江西晶科
         * remark : 21
         * type : CASH_DEPOSIT
         * typeDesp : 保证金
         * number : AXSK20190116007
         * member : {"crmNumber":"200011","statusValue":null,"srOperatorName":"秦光明","creditLevelValue":"AAA","office":null,"sapNumber":"200011","arOperatorName":"徐超","frOperator":{"departmentName":null,"lastModifiedDate":"2019-01-21 09:30:29","lastModifiedBy":"潘梦洋","sort":10,"title":"市场专员(z)","createdDate":"2018-11-29 21:15:42","createdBy":"15167156690","name":"潘梦洋","id":1,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-13 11:17:11","createdBy":"13901565517","lastModifiedBy":"徐超","name":"市场部（Z）","oaDepartmentId":"415","id":78,"sort":200},"oaId":"2381","username":"1022654","oaCode":"1022654"},"memberLevelValue":"普通","employeeSizeValue":"","registeredCapital":2300,"simpleSpell":"jxjk","statusKey":null,"srOperator":{"departmentName":null,"lastModifiedDate":"2019-01-20 15:54:35","lastModifiedBy":"秦光明","sort":10,"title":"品质客服高级工程师","createdDate":"2019-01-10 13:46:11","createdBy":"徐超","name":"秦光明","id":140,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 13:46:13","createdBy":"13901565517","lastModifiedBy":"徐超","name":"客服部（Z）","oaDepartmentId":"440","id":92,"sort":200},"oaId":"1708","username":"1017179","oaCode":"1017179"},"id":2326,"companyTypeValue":"有限责任公司","orgName":"晶科能源有限公司","arOperator":{"departmentName":null,"lastModifiedDate":"2019-01-20 20:39:36","lastModifiedBy":"徐超","sort":10,"title":"销售经理（Z）","createdDate":"2019-01-10 12:05:26","createdBy":"费婷","name":"徐超","id":139,"department":{"desp":null,"createdDate":"2018-12-12 00:00:00","lastModifiedDate":"2019-01-10 12:05:27","createdBy":"13901565517","lastModifiedBy":"费婷","name":"国内销售部（Z）","oaDepartmentId":"411","id":75,"sort":200},"oaId":"651","username":"1004613","oaCode":"1004613"},"lastModifiedDate":"2019-01-20 19:50:48","avatarUrl":null,"lastModifiedBy":"徐超","riskLevelKey":null,"creditModifyDate":null,"sort":10,"abbreviation":"江西晶科","customerDemand":"组件和硅片","createdDate":"2019-01-10 17:03:04","createdBy":"系统自动创建","dealer":null,"riskLevelValue":null,"provinceName":"江西省","cooperationTypeValue":"直销","frOperatorName":"潘梦洋"}
         * id : 19
         * companyKey : gd_aikosolar
         * meId : 2326
         * amount : 21.0
         * lastModifiedDate : 2019-01-16 09:51:27
         * lastModifiedBy : 曹康
         * receiptDate : 2019-01-17 00:00:00
         * sort : 10
         * statusDesp : 未过账
         * receiptTypeValue : 电汇
         * createdDate : 2019-01-16 09:51:27
         * memberNumber : 200011
         * createdBy : 曹康
         * currencyValue : 中国人民币 元
         * receiptTypeKey : wire_transfer
         * currencyKey : RMB
         * status : NOT_ACCOUNT
         */

        private String companyValue;
        private String memberName;
        private String remark;
        private String type;
        private String typeDesp;
        private String number;
        private MemberBeanID member;
        private long id;
        private String companyKey;
        private long meId;
        private double amount;
        private String lastModifiedDate;
        private String lastModifiedBy;
        private String receiptDate;
        private String statusDesp;
        private String receiptTypeValue;

        public String getCompanyValue() {
            return companyValue;
        }

        public void setCompanyValue(String companyValue) {
            this.companyValue = companyValue;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
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

        public String getTypeDesp() {
            return typeDesp;
        }

        public void setTypeDesp(String typeDesp) {
            this.typeDesp = typeDesp;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getCompanyKey() {
            return companyKey;
        }

        public void setCompanyKey(String companyKey) {
            this.companyKey = companyKey;
        }

        public long getMeId() {
            return meId;
        }

        public void setMeId(long meId) {
            this.meId = meId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
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

        public String getReceiptDate() {
            return receiptDate;
        }

        public void setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
        }

        public String getStatusDesp() {
            return statusDesp;
        }

        public void setStatusDesp(String statusDesp) {
            this.statusDesp = statusDesp;
        }

        public String getReceiptTypeValue() {
            return receiptTypeValue;
        }

        public void setReceiptTypeValue(String receiptTypeValue) {
            this.receiptTypeValue = receiptTypeValue;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(String memberNumber) {
            this.memberNumber = memberNumber;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCurrencyValue() {
            return currencyValue;
        }

        public void setCurrencyValue(String currencyValue) {
            this.currencyValue = currencyValue;
        }

        public String getReceiptTypeKey() {
            return receiptTypeKey;
        }

        public void setReceiptTypeKey(String receiptTypeKey) {
            this.receiptTypeKey = receiptTypeKey;
        }

        public String getCurrencyKey() {
            return currencyKey;
        }

        public void setCurrencyKey(String currencyKey) {
            this.currencyKey = currencyKey;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        private String createdDate;
        private String memberNumber;
        private String createdBy;
        private String currencyValue;
        private String receiptTypeKey;
        private String currencyKey;
        private String status;






    }
}
