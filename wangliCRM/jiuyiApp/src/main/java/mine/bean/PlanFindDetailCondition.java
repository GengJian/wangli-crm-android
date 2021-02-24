package mine.bean;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:PlanFindDetailCondition.java
 * 作    者:Created by zhengss
 * 创建时间:2018/7/25 15:35
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/7/25 1.00 初始版本
 * ****************************************************************
 */
public class PlanFindDetailCondition {

    /**
     * operatorCollectBean : {"fromClientType":null,"type":"MEMBER","member":null,"memberId":47748,"name":"杰士","batchNumber":null,"year":2018,"month":8,"totalPlanQuantity":12600,"actualQuantity":0,"actualShipNumber":0,"actualShipString":"0.00%","searchValue":null}
     * pager : {"number":0,"size":10,"direction":"ASC","property":"id","specialConditions":[""],"rules":[]}
     */

    private MineDeliveryPlanBean.ContentBean operatorCollectBean;
    private PagerBean pager;

    public MineDeliveryPlanBean.ContentBean getOperatorCollectBean() {
        return operatorCollectBean;
    }

    public void setOperatorCollectBean(MineDeliveryPlanBean.ContentBean operatorCollectBean) {
        this.operatorCollectBean = operatorCollectBean;
    }

    public PagerBean getPager() {
        return pager;
    }

    public void setPager(PagerBean pager) {
        this.pager = pager;
    }


    public static class PagerBean {
        /**
         * number : 0
         * size : 10
         * direction : ASC
         * property : id
         * specialConditions : [""]
         * rules : []
         */

        private int number;
        private int size;
        private String direction;
        private String property;
        private List<String> specialConditions;
        private List<?> rules;

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

        public List<String> getSpecialConditions() {
            return specialConditions;
        }

        public void setSpecialConditions(List<String> specialConditions) {
            this.specialConditions = specialConditions;
        }

        public List<?> getRules() {
            return rules;
        }

        public void setRules(List<?> rules) {
            this.rules = rules;
        }
    }
}
