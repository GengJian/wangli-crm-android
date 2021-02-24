package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:VisitActivityBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/10 11:48
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/10 1.00 初始版本
 * ****************************************************************
 */
public class VisitActivityBean {

    public List<Bean> getNext() {
        return next;
    }

    public void setNext(List<Bean> next) {
        this.next = next;
    }

    public List<Bean> getCurrent() {
        return current;
    }

    public void setCurrent(List<Bean> current) {
        this.current = current;
    }

    public List<Bean> getPre() {
        return pre;
    }

    public void setPre(List<Bean> pre) {
        this.pre = pre;
    }

    private List<Bean> next;
    private List<Bean> current;
    private List<Bean> pre;



    public static class Bean {
        /**
         * field : 1
         * fieldTitle : null
         * fieldValue : 2019-01-01
         */

        private int field;
        private String fieldTitle;
        private String fieldValue;

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }

        public String getFieldTitle() {
            return fieldTitle;
        }

        public void setFieldTitle(String fieldTitle) {
            this.fieldTitle = fieldTitle;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }
    }
}
