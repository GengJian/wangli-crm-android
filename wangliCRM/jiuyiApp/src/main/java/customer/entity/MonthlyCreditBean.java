package customer.entity;

import java.util.List;

public class MonthlyCreditBean {


    /**
     * content : {"secondItem":{"title":"2017年6月至2018年5月欠款走势图","list":[{"field":"6","fieldValue":"52"},{"field":"7","fieldValue":"64"},{"field":"8","fieldValue":"187"},{"field":"9","fieldValue":"149"},{"field":"10","fieldValue":"54"},{"field":"11","fieldValue":"32"},{"field":"12","fieldValue":"4"},{"field":"1","fieldValue":"42"},{"field":"2","fieldValue":"170"},{"field":"3","fieldValue":"199"},{"field":"4","fieldValue":"7"},{"field":"5","fieldValue":"120"}]},"firstItem":[{"field":"注册资金","fieldValue":"￥184.45","unit":"万"},{"field":"信用额度","fieldValue":"￥0.12","unit":"万/30天"},{"field":"欠款总额","fieldValue":"￥0.00","unit":"万"},{"field":"到期总额","fieldValue":"￥0.00","unit":"万"}],"forthItem":{"title":{"field":"截止到今日(2018-06-07)","fieldValue":""},"list":[{"field":"截止今日到期金额：","fieldValue":"￥0.00"},{"field":"截止本月底到期金额：","fieldValue":"￥0.00"},{"field":"截止下月底到期金额：","fieldValue":"￥0.00"},{"field":"截止今日未开票金额：","fieldValue":"￥0.00"},{"field":"上个月未开票金额：","fieldValue":"￥0.00"}]},"thirdItem":{"title":{"field":"该客户最长预期天数为：","fieldValue":"180天"},"list":[{"field":"本月订单(万)","fieldValue":"￥0.00"},{"field":"本月发货(万)","fieldValue":"￥0.00"},{"field":"本月开票(万)","fieldValue":"￥0.00"},{"field":"本月回款(万)","fieldValue":"￥0.00"},{"field":"上月订单(万)","fieldValue":"￥0.00"},{"field":"上月发货(万)","fieldValue":"￥0.00"},{"field":"上月开票(万)","fieldValue":"￥0.00"},{"field":"上月回款(万)","fieldValue":"￥0.00"}]}}
     */

    private ContentBean content;

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * secondItem : {"title":"2017年6月至2018年5月欠款走势图","list":[{"field":"6","fieldValue":"52"},{"field":"7","fieldValue":"64"},{"field":"8","fieldValue":"187"},{"field":"9","fieldValue":"149"},{"field":"10","fieldValue":"54"},{"field":"11","fieldValue":"32"},{"field":"12","fieldValue":"4"},{"field":"1","fieldValue":"42"},{"field":"2","fieldValue":"170"},{"field":"3","fieldValue":"199"},{"field":"4","fieldValue":"7"},{"field":"5","fieldValue":"120"}]}
         * firstItem : [{"field":"注册资金","fieldValue":"￥184.45","unit":"万"},{"field":"信用额度","fieldValue":"￥0.12","unit":"万/30天"},{"field":"欠款总额","fieldValue":"￥0.00","unit":"万"},{"field":"到期总额","fieldValue":"￥0.00","unit":"万"}]
         * forthItem : {"title":{"field":"截止到今日(2018-06-07)","fieldValue":""},"list":[{"field":"截止今日到期金额：","fieldValue":"￥0.00"},{"field":"截止本月底到期金额：","fieldValue":"￥0.00"},{"field":"截止下月底到期金额：","fieldValue":"￥0.00"},{"field":"截止今日未开票金额：","fieldValue":"￥0.00"},{"field":"上个月未开票金额：","fieldValue":"￥0.00"}]}
         * thirdItem : {"title":{"field":"该客户最长预期天数为：","fieldValue":"180天"},"list":[{"field":"本月订单(万)","fieldValue":"￥0.00"},{"field":"本月发货(万)","fieldValue":"￥0.00"},{"field":"本月开票(万)","fieldValue":"￥0.00"},{"field":"本月回款(万)","fieldValue":"￥0.00"},{"field":"上月订单(万)","fieldValue":"￥0.00"},{"field":"上月发货(万)","fieldValue":"￥0.00"},{"field":"上月开票(万)","fieldValue":"￥0.00"},{"field":"上月回款(万)","fieldValue":"￥0.00"}]}
         */

        private SecondItemBean secondItem;
        private ForthItemBean forthItem;
        private ThirdItemBean thirdItem;
        private List<FirstItemBean> firstItem;

        public SecondItemBean getSecondItem() {
            return secondItem;
        }

        public void setSecondItem(SecondItemBean secondItem) {
            this.secondItem = secondItem;
        }

        public ForthItemBean getForthItem() {
            return forthItem;
        }

        public void setForthItem(ForthItemBean forthItem) {
            this.forthItem = forthItem;
        }

        public ThirdItemBean getThirdItem() {
            return thirdItem;
        }

        public void setThirdItem(ThirdItemBean thirdItem) {
            this.thirdItem = thirdItem;
        }

        public List<FirstItemBean> getFirstItem() {
            return firstItem;
        }

        public void setFirstItem(List<FirstItemBean> firstItem) {
            this.firstItem = firstItem;
        }

        public static class SecondItemBean {
            /**
             * title : 2017年6月至2018年5月欠款走势图
             * list : [{"field":"6","fieldValue":"52"},{"field":"7","fieldValue":"64"},{"field":"8","fieldValue":"187"},{"field":"9","fieldValue":"149"},{"field":"10","fieldValue":"54"},{"field":"11","fieldValue":"32"},{"field":"12","fieldValue":"4"},{"field":"1","fieldValue":"42"},{"field":"2","fieldValue":"170"},{"field":"3","fieldValue":"199"},{"field":"4","fieldValue":"7"},{"field":"5","fieldValue":"120"}]
             */

            private String title;
            private List<ListBean> list;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * field : 6
                 * fieldValue : 52
                 */

                private String field;
                private String fieldValue;

                public String getField() {
                    return field;
                }

                public void setField(String field) {
                    this.field = field;
                }

                public String getFieldValue() {
                    return fieldValue;
                }

                public void setFieldValue(String fieldValue) {
                    this.fieldValue = fieldValue;
                }
            }
        }

        public static class ForthItemBean {
            /**
             * title : {"field":"截止到今日(2018-06-07)","fieldValue":""}
             * list : [{"field":"截止今日到期金额：","fieldValue":"￥0.00"},{"field":"截止本月底到期金额：","fieldValue":"￥0.00"},{"field":"截止下月底到期金额：","fieldValue":"￥0.00"},{"field":"截止今日未开票金额：","fieldValue":"￥0.00"},{"field":"上个月未开票金额：","fieldValue":"￥0.00"}]
             */

            private TitleBean title;
            private List<ListBeanX> list;

            public TitleBean getTitle() {
                return title;
            }

            public void setTitle(TitleBean title) {
                this.title = title;
            }

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            public static class TitleBean {
                /**
                 * field : 截止到今日(2018-06-07)
                 * fieldValue :
                 */

                private String field;
                private String fieldValue;

                public String getField() {
                    return field;
                }

                public void setField(String field) {
                    this.field = field;
                }

                public String getFieldValue() {
                    return fieldValue;
                }

                public void setFieldValue(String fieldValue) {
                    this.fieldValue = fieldValue;
                }
            }

            public static class ListBeanX {
                /**
                 * field : 截止今日到期金额：
                 * fieldValue : ￥0.00
                 */

                private String field;
                private String fieldValue;

                public String getField() {
                    return field;
                }

                public void setField(String field) {
                    this.field = field;
                }

                public String getFieldValue() {
                    return fieldValue;
                }

                public void setFieldValue(String fieldValue) {
                    this.fieldValue = fieldValue;
                }
            }
        }

        public static class ThirdItemBean {
            /**
             * title : {"field":"该客户最长预期天数为：","fieldValue":"180天"}
             * list : [{"field":"本月订单(万)","fieldValue":"￥0.00"},{"field":"本月发货(万)","fieldValue":"￥0.00"},{"field":"本月开票(万)","fieldValue":"￥0.00"},{"field":"本月回款(万)","fieldValue":"￥0.00"},{"field":"上月订单(万)","fieldValue":"￥0.00"},{"field":"上月发货(万)","fieldValue":"￥0.00"},{"field":"上月开票(万)","fieldValue":"￥0.00"},{"field":"上月回款(万)","fieldValue":"￥0.00"}]
             */

            private TitleBeanX title;
            private List<ListBeanXX> list;

            public TitleBeanX getTitle() {
                return title;
            }

            public void setTitle(TitleBeanX title) {
                this.title = title;
            }

            public List<ListBeanXX> getList() {
                return list;
            }

            public void setList(List<ListBeanXX> list) {
                this.list = list;
            }

            public static class TitleBeanX {
                /**
                 * field : 该客户最长预期天数为：
                 * fieldValue : 180天
                 */

                private String field;
                private String fieldValue;

                public String getField() {
                    return field;
                }

                public void setField(String field) {
                    this.field = field;
                }

                public String getFieldValue() {
                    return fieldValue;
                }

                public void setFieldValue(String fieldValue) {
                    this.fieldValue = fieldValue;
                }
            }

            public static class ListBeanXX {
                /**
                 * field : 本月订单(万)
                 * fieldValue : ￥0.00
                 */

                private String field;
                private String fieldValue;

                public String getField() {
                    return field;
                }

                public void setField(String field) {
                    this.field = field;
                }

                public String getFieldValue() {
                    return fieldValue;
                }

                public void setFieldValue(String fieldValue) {
                    this.fieldValue = fieldValue;
                }
            }
        }

        public static class FirstItemBean {
            /**
             * field : 注册资金
             * fieldValue : ￥184.45
             * unit : 万
             */

            private String field;
            private String fieldValue;
            private String unit;

            public String getField() {
                return field;
            }

            public void setField(String field) {
                this.field = field;
            }

            public String getFieldValue() {
                return fieldValue;
            }

            public void setFieldValue(String fieldValue) {
                this.fieldValue = fieldValue;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }
    }
}
