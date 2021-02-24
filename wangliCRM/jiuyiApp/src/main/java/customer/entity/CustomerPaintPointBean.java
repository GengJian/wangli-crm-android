package customer.entity;

import commonlyused.bean.NormalOperatorBean;

/**
 * ****************************************************************
 * 文件名称:CustomerPaintPointBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/20 19:12
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/20 1.00 初始版本
 * ****************************************************************
 */
public class CustomerPaintPointBean {

    /**
     * id :
     * desp : 新需求
     * operator : {"id":207}
     * needFeedBack : true
     * closingDate : 2019-01-29T06:41:17.245Z
     * reply :
     * member : {"id":2545}
     * linkMan : {"id":78}
     */

    private long id;
    private String desp;
    private NormalOperatorBean.OperatorBeanID operator;
    private boolean needFeedBack;
    private String closingDate;
    private String reply;
    private MemberBeanID member;
    private LinkManBean linkMan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public NormalOperatorBean.OperatorBeanID getOperator() {
        return operator;
    }

    public void setOperator(NormalOperatorBean.OperatorBeanID operator) {
        this.operator = operator;
    }

    public boolean isNeedFeedBack() {
        return needFeedBack;
    }

    public void setNeedFeedBack(boolean needFeedBack) {
        this.needFeedBack = needFeedBack;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public MemberBeanID getMember() {
        return member;
    }

    public void setMember(MemberBeanID member) {
        this.member = member;
    }

    public LinkManBean getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(LinkManBean linkMan) {
        this.linkMan = linkMan;
    }

    public static class OperatorBean {
        /**
         * id : 207
         */

        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }



    public static class LinkManBean {
        /**
         * id : 78
         */

        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
}
