package customer.entity;

/**
 * ****************************************************************
 * 文件名称:UpdateCreditBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/7/26 19:56
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/7/26 1.00 初始版本
 * ****************************************************************
 */
public class UpdateCreditBean {

    /**
     * member : {"id":1}
     * oldAccount : 10
     * newAccount : 20
     * oldAmount : 200000
     * newAmount : 400000
     * remark : 申请说明
     */

    private MemberBeanID member;
    private int oldAccount;
    private int newAccount;
    private double oldAmount;
    private double newAmount;
    private String remark;

    public MemberBeanID getMember() {
        return member;
    }

    public void setMember(MemberBeanID member) {
        this.member = member;
    }

    public int getOldAccount() {
        return oldAccount;
    }

    public void setOldAccount(int oldAccount) {
        this.oldAccount = oldAccount;
    }

    public int getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(int newAccount) {
        this.newAccount = newAccount;
    }

    public double getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(double oldAmount) {
        this.oldAmount = oldAmount;
    }

    public double getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(double newAmount) {
        this.newAmount = newAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static class MemberBean {
        /**
         * id : 1
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
