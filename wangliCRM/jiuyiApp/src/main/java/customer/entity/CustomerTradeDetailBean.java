package customer.entity;

import java.util.List;

public class CustomerTradeDetailBean {

    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * title : 客户名称:大冬瓜
         * data : [{"leftContent":"回款编号","rightContent":"666","field":"number","change":true,"url":null,"dictField":null},{"leftContent":"金额","rightContent":"1700.00","field":"receiptAmount","change":true,"url":null,"dictField":null},{"leftContent":"日期","rightContent":"2018-05-22 00:00:00.0","field":"receiptDate","change":true,"url":null,"dictField":null},{"leftContent":"类型","rightContent":"电汇","field":"receiptType.desp","change":true,"url":null,"dictField":null},{"leftContent":"业务员","rightContent":"蚂蚁","field":"operatorName","change":true,"url":null,"dictField":null},{"leftContent":"入账日期","rightContent":"2018-05-30 00:00:00.0","field":"bookedDate","change":true,"url":null,"dictField":null},{"leftContent":"状态","rightContent":"已到账","field":"receiptStatus.desp","change":true,"url":null,"dictField":null}]
         */

        private String title;
        private List<DataBean> data;

        public List<AttachmentBean> getAttachmentList() {
            return attachments;
        }

        public void setAttachmentList(List<AttachmentBean> attachmentList) {
            this.attachments = attachmentList;
        }

        private List<AttachmentBean> attachments;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * leftContent : 回款编号
             * rightContent : 666
             * field : number
             * change : true
             * url : null
             * dictField : null
             */

            private String leftContent;
            private String rightContent;
            private String field;
            private boolean change;
            private String url;
            private Object dictField;

            public String getLeftContent() {
                return leftContent;
            }

            public void setLeftContent(String leftContent) {
                this.leftContent = leftContent;
            }

            public String getRightContent() {
                return rightContent;
            }

            public void setRightContent(String rightContent) {
                this.rightContent = rightContent;
            }

            public String getField() {
                return field;
            }

            public void setField(String field) {
                this.field = field;
            }

            public boolean isChange() {
                return change;
            }

            public void setChange(boolean change) {
                this.change = change;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getDictField() {
                return dictField;
            }

            public void setDictField(Object dictField) {
                this.dictField = dictField;
            }
        }
    }
}
