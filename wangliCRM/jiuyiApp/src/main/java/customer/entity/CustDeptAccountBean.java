package customer.entity;

import java.math.BigDecimal;

/**
 * ****************************************************************
 * 文件名称:CustDeptAccountBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/9/19 17:23
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/9/19 1.00 初始版本
 * ****************************************************************
 */
public class CustDeptAccountBean {


    public BigDecimal getOwedTotalAmount() {
        return owedTotalAmount;
    }

    public void setOwedTotalAmount(BigDecimal owedTotalAmount) {
        this.owedTotalAmount = owedTotalAmount;
    }

    private BigDecimal owedTotalAmount;//欠款总额
    private BigDecimal zeroToAccount; //0-帐期之间的

    public CustDeptAccountBean() {
    }

    public BigDecimal getZeroToAccount() {

        return zeroToAccount;
    }

    public void setZeroToAccount(BigDecimal zeroToAccount) {
        this.zeroToAccount = zeroToAccount;
    }

    public BigDecimal getAccountToNinety() {
        return accountToNinety;
    }

    public void setAccountToNinety(BigDecimal accountToNinety) {
        this.accountToNinety = accountToNinety;
    }

    public BigDecimal getMoreThanNinety() {
        return moreThanNinety;
    }

    public void setMoreThanNinety(BigDecimal moreThanNinety) {
        this.moreThanNinety = moreThanNinety;
    }

    private BigDecimal accountToNinety; //帐期-90天的金额
    private BigDecimal moreThanNinety; //90天以上

    private BigDecimal receivedAmount;

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimal getPlanTotalAmount() {
        return planTotalAmount;
    }

    public void setPlanTotalAmount(BigDecimal planTotalAmount) {
        this.planTotalAmount = planTotalAmount;
    }

    private BigDecimal planTotalAmount;

}
