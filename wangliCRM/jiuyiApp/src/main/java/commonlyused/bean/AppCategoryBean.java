package commonlyused.bean;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:AppCategoryBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/9/12 13:38
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/9/12 1.00 初始版本
 * ****************************************************************
 */
public class AppCategoryBean {

    /**
     * category : 办公类应用
     * items : [{"createdBy":"15858562128","createdDate":"2018-09-12","lastModifiedBy":"15858562128","lastModifiedDate":"2018-09-12","id":81491621,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":"外贸欠款统计","iosIconUrl":"http://crm-cdn-open.huafeng.com/default_ios.png1536728828345","androidIconUrl":"http://crm-cdn-open.huafeng.com/default_android.png1536728838261","url":"http://dev-crm-h5.huafeng.com/foreignDeptSenior","flag":null,"enable":true,"firstCategory":false,"parent":null,"category":"办公类应用","prefixUrl":null,"positionList":null},{"createdBy":"15858562128","createdDate":"2018-09-12","lastModifiedBy":"15858562128","lastModifiedDate":"2018-09-12","id":81491627,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":"客户欠款分布","iosIconUrl":"http://crm-cdn-open.huafeng.com/default_ios.png1536730230599","androidIconUrl":"http://crm-cdn-open.huafeng.com/default_android.png1536730241908","url":"http://dev-crm-h5.huafeng.com/custDeptAccount","flag":null,"enable":true,"firstCategory":false,"parent":null,"category":"办公类应用","prefixUrl":null,"positionList":null},{"createdBy":"15858562128","createdDate":"2018-09-12","lastModifiedBy":"15858562128","lastModifiedDate":"2018-09-12","id":81491617,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":"外贸发货统计","iosIconUrl":"http://crm-cdn-open.huafeng.com/default_ios.png1536728610073","androidIconUrl":"http://crm-cdn-open.huafeng.com/default_android.png1536728622144","url":"http://dev-crm-h5.huafeng.com/foreignSapInvoiceSummary","flag":null,"enable":true,"firstCategory":false,"parent":null,"category":"办公类应用","prefixUrl":null,"positionList":null}]
     */

    private String category;
    private List<ItemsBean> items;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * createdBy : 15858562128
         * createdDate : 2018-09-12
         * lastModifiedBy : 15858562128
         * lastModifiedDate : 2018-09-12
         * id : 81491621
         * deleted : false
         * sort : 10
         * fromClientType : null
         * optionGroup : []
         * name : 外贸欠款统计
         * iosIconUrl : http://crm-cdn-open.huafeng.com/default_ios.png1536728828345
         * androidIconUrl : http://crm-cdn-open.huafeng.com/default_android.png1536728838261
         * url : http://dev-crm-h5.huafeng.com/foreignDeptSenior
         * flag : null
         * enable : true
         * firstCategory : false
         * parent : null
         * category : 办公类应用
         * prefixUrl : null
         * positionList : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private Object fromClientType;
        private String name;
        private String iosIconUrl;
        private String androidIconUrl;
        private String url;
        private Object flag;
        private boolean enable;
        private boolean firstCategory;
        private Object parent;
        private String category;
        private Object prefixUrl;
        private Object positionList;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIosIconUrl() {
            return iosIconUrl;
        }

        public void setIosIconUrl(String iosIconUrl) {
            this.iosIconUrl = iosIconUrl;
        }

        public String getAndroidIconUrl() {
            return androidIconUrl;
        }

        public void setAndroidIconUrl(String androidIconUrl) {
            this.androidIconUrl = androidIconUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Object getFlag() {
            return flag;
        }

        public void setFlag(Object flag) {
            this.flag = flag;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public boolean isFirstCategory() {
            return firstCategory;
        }

        public void setFirstCategory(boolean firstCategory) {
            this.firstCategory = firstCategory;
        }

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
            this.parent = parent;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Object getPrefixUrl() {
            return prefixUrl;
        }

        public void setPrefixUrl(Object prefixUrl) {
            this.prefixUrl = prefixUrl;
        }

        public Object getPositionList() {
            return positionList;
        }

        public void setPositionList(Object positionList) {
            this.positionList = positionList;
        }

        public List<?> getOptionGroup() {
            return optionGroup;
        }

        public void setOptionGroup(List<?> optionGroup) {
            this.optionGroup = optionGroup;
        }
    }
}
