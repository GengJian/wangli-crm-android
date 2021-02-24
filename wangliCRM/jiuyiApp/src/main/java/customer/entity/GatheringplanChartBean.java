package customer.entity;

import java.util.List;

public class GatheringplanChartBean {

    /**
     * list : [{"field":"1月","fieldValue":"0"},{"field":"2月","fieldValue":"0"},{"field":"3月","fieldValue":"0"},{"field":"4月","fieldValue":"0"},{"field":"5月","fieldValue":"5.40"},{"field":"6月","fieldValue":"0"},{"field":"7月","fieldValue":"0"},{"field":"8月","fieldValue":"0"},{"field":"9月","fieldValue":"0"},{"field":"10月","fieldValue":"0"},{"field":"11月","fieldValue":"0"},{"field":"12月","fieldValue":"0"}]
     * content : 2018年收款实际收款金额折线图
     */

    private String content;
    private List<ListBean> list;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * field : 1月
         * fieldValue : 0
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
