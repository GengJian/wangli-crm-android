package customer.entity;

import java.util.List;

import commonlyused.bean.NormalOperatorBean;

/**
 * ****************************************************************
 * 文件名称:CustomerAssistantCreatBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/3/13 17:00
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/3/13 1.00 初始版本
 * ****************************************************************
 */
public class CustomerAssistantCreatBean {

    public MemberBeanID getMember() {
        return member;
    }

    public void setMember(MemberBeanID member) {
        this.member = member;
    }

    public String getAssistRole() {
        return assistRole;
    }

    public void setAssistRole(String assistRole) {
        this.assistRole = assistRole;
    }

    public String getAssistReason() {
        return assistReason;
    }

    public void setAssistReason(String assistReason) {
        this.assistReason = assistReason;
    }

    public List<UpdateAssistantBean> getOperators() {
        return operators;
    }

    public void setOperators(List<UpdateAssistantBean> operators) {
        this.operators = operators;
    }

    /**
     * member : {"id":11442}
     * assistRole : AR
     * assistReason : AR协助
     * operators : [{"id":50},{"id":51}]
     */

    private MemberBeanID member;
    private String assistRole;
    private String assistReason;
    private List<UpdateAssistantBean> operators;


}
