package customer.entity;

import java.util.List;

public class TradePandectBean {

    /**
     * average : [{"field":"40D/H650/9556-01标准大箱","fieldValue":[{"field":"7月","fieldValue":0},{"field":"8月","fieldValue":0},{"field":"9月","fieldValue":0},{"field":"10月","fieldValue":0},{"field":"11月","fieldValue":0},{"field":"12月","fieldValue":0},{"field":"1月","fieldValue":0},{"field":"2月","fieldValue":0},{"field":"3月","fieldValue":0},{"field":"4月","fieldValue":0.2},{"field":"5月","fieldValue":20},{"field":"6月","fieldValue":111}]},{"field":"40D/H650/9558-01标准大箱","fieldValue":[{"field":"7月","fieldValue":0},{"field":"8月","fieldValue":0},{"field":"9月","fieldValue":0},{"field":"10月","fieldValue":0},{"field":"11月","fieldValue":0},{"field":"12月","fieldValue":0},{"field":"1月","fieldValue":0},{"field":"2月","fieldValue":0},{"field":"3月","fieldValue":0},{"field":"4月","fieldValue":0},{"field":"5月","fieldValue":0.3},{"field":"6月","fieldValue":0.891089}]}]
     * content : {"content":[{"field":700,"fieldValue":"所有","count":0,"iconUrl":""},{"field":701,"fieldValue":"销售订单","count":1,"iconUrl":""},{"field":702,"fieldValue":"商务合同","count":1,"iconUrl":""},{"field":703,"fieldValue":"发货","count":1,"iconUrl":""},{"field":704,"fieldValue":"发票","count":1,"iconUrl":""},{"field":705,"fieldValue":"电汇/承兑","count":1,"iconUrl":""},{"field":706,"fieldValue":"外贸物流","count":0,"iconUrl":""}]}
     */

    private ContentBeanX content;
    private List<AverageBean> average;

    public ContentBeanX getContent() {
        return content;
    }

    public void setContent(ContentBeanX content) {
        this.content = content;
    }

    public List<AverageBean> getAverage() {
        return average;
    }

    public void setAverage(List<AverageBean> average) {
        this.average = average;
    }

    public static class ContentBeanX {
        private List<ContentBean> content;

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * field : 700
             * fieldValue : 所有
             * count : 0
             * iconUrl :
             */

            private int field;
            private String fieldValue;
            private int count;
            private String iconUrl;

            public int getField() {
                return field;
            }

            public void setField(int field) {
                this.field = field;
            }

            public String getFieldValue() {
                return fieldValue;
            }

            public void setFieldValue(String fieldValue) {
                this.fieldValue = fieldValue;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }
        }
    }

    public static class AverageBean {
        /**
         * field : 40D/H650/9556-01标准大箱
         * fieldValue : [{"field":"7月","fieldValue":0},{"field":"8月","fieldValue":0},{"field":"9月","fieldValue":0},{"field":"10月","fieldValue":0},{"field":"11月","fieldValue":0},{"field":"12月","fieldValue":0},{"field":"1月","fieldValue":0},{"field":"2月","fieldValue":0},{"field":"3月","fieldValue":0},{"field":"4月","fieldValue":0.2},{"field":"5月","fieldValue":20},{"field":"6月","fieldValue":111}]
         */

        private String field;

        public String getFieldTitle() {
            return fieldTitle;
        }

        public void setFieldTitle(String fieldTitle) {
            this.fieldTitle = fieldTitle;
        }

        private String fieldTitle;
        private List<FieldValueBean> fieldValue;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public List<FieldValueBean> getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(List<FieldValueBean> fieldValue) {
            this.fieldValue = fieldValue;
        }

        public static class FieldValueBean {
            /**
             * field : 7月
             * fieldValue : 0
             */

            private String field;
            private double fieldValue;

            public String getField() {
                return field;
            }

            public void setField(String field) {
                this.field = field;
            }

            public double getFieldValue() {
                return fieldValue;
            }

            public void setFieldValue(double fieldValue) {
                this.fieldValue = fieldValue;
            }
        }
    }
}
