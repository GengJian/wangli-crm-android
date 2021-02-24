package customer.entity;

/**
 * ****************************************************************
 * 文件名称:MemberAuthorityBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/8/18 11:23
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/8/18 1.00 初始版本
 * ****************************************************************
 */
public class MemberAuthorityBean {
    public static MemberAuthorityBean authorityBean;
    public static Boolean base=true; //基本资料
    public static Boolean linkMan=true;; //人事组织
    public static Boolean financialRisk=true;; //财务风险
    public static Boolean procurementStatus=true;; //采购状况
    public static Boolean productionStatus=true;; //生产及品质
    public static Boolean salesStatus=true;; //销售状况
    public static Boolean developmentStatus=true;; //研发状况
    public static Boolean businessVisit=true;; //商务拜访
    public static Boolean businessFollow=true;; //商务跟进
    public static Boolean contractTracking=true;; //合同跟踪
    public static Boolean serviceComplaint=true;; //服务投诉
    public static Boolean costAnalysis=true;; //费用分析
    public static Boolean system=true; //系统
    public static Boolean topFlag=true; //系统

    public MemberAuthorityBean(){

    }
    public static MemberAuthorityBean getIns(){
        if(authorityBean == null){
            authorityBean = new MemberAuthorityBean();
        }
        return authorityBean;
    }
}
