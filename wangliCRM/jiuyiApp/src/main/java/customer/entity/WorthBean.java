package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:WorthBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/5 19:38
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/5 1.00 初始版本
 * ****************************************************************
 */
public class WorthBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : true
         * isText : false
         * leftContent : 毛利率(月)
         * rightContent : 30.00
         */

        private boolean type;
        private boolean isText;
        private String leftContent;
        private String rightContent;

        public boolean isType() {
            return type;
        }

        public void setType(boolean type) {
            this.type = type;
        }

        public boolean isIsText() {
            return isText;
        }

        public void setIsText(boolean isText) {
            this.isText = isText;
        }

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
    }
}
