package messages.timchat.utils;

public class TimHelperType {
    public static final String ORDER = "ORDER"; // 订单小助手
    public static final String RECEIVE = "RECEIVE"; // 订单小助手
    public static final String PLAN = "PLAN"; // 订单小助手
    public static final String MARKET = "MARKET"; // 订单小助手
    public static final String PERFORMANCE = "PERFORMANCE"; // 订单小助手
    public static final String AFTER_SALE = "AFTER_SALE"; // 订单小助手
    public static final String NOTICE = "NOTICE"; // 订单小助手
    public static final String TASK = "TASK"; // 订单小助手
    public static final String ORGANIZATION = "ORGANIZATION"; // 组织小助手
    public static final String INTELLIGENCE = "INTELLIGENCE"; // 情报小助手
    public static final String CLUE = "CLUE"; // 线索小助手
    public static final String BUSINESS_CHANCE = "BUSINESS_CHANCE"; // 商机小助手
    public static final String COMMERCIAL = "COMMERCIAL"; // 商务小助手
    public static final String CONTRACT = "CONTRACT"; // 合同小助手
    public static final String DYNAMIC = "DYNAMIC"; // 动态小助手
    public static final String MARKET_ACTIVITY = "MARKET_ACTIVITY"; // 市场小助手
    public static final String RECEIPT_ACTIVITY = "RECEIPT_ACTIVITY"; // 收款小助手


    public static final String ORDER_NOTIFY_CN = "订单小助手"; // 订单小助手
    public static final String RECEIVE_NOTIFY_CN = "催款小助手"; // 订单小助手
    public static final String PLAN_NOTIFY_CN = "计划小助手"; // 订单小助手
    public static final String MARKET_NOTIFY_CN = "营销小助手"; // 订单小助手
    public static final String PERFORMANCE_NOTIFY_CN = "绩效小助手"; // 订单小助手
    public static final String AFTER_SALE_NOTIFY_CN = "售后小助手"; // 订单小助手
    public static final String NOTICE_NOTIFY_CN = "公告小助手"; // 订单小助手
    public static final String TASK_NOTIFY_CN = "任务小助手"; // 订单小助手
    public static final String ORGANIZATION_NOTIFY_CN  = "组织小助手"; // 组织小助手
    public static final String INTELLIGENCE_NOTIFY_CN  = "情报小助手"; // 情报小助手
    public static final String CLUE_NOTIFY_CN  = "线索小助手"; // 线索小助手
    public static final String BUSINESS_CHANCE_NOTIFY_CN  = "商机小助手"; // 商机小助手
    public static final String COMMERCIAL_NOTIFY_CN  = "商务小助手"; // 商务小助手
    public static final String CONTRACT_NOTIFY_CN  = "合同小助手"; // 合同小助手
    public static final String DYNAMIC_NOTIFY_CN  = "动态小助手"; // 动态小助手
    public static final String MARKET_ACTIVITY_NOTIFY_CN  = "市场小助手"; // 市场小助手
    public static final String RECEIPT_ACTIVITY_NOTIFY_CN  = "收款小助手"; // 收款小助手




    public static final String ORDER_NOTIFY = "order_helper"; // 订单小助手
    public static final String RECEIVE_NOTIFY = "recieve_helper"; // 订单小助手
    public static final String PLAN_NOTIFY = "plan_helper"; // 订单小助手
    public static final String MARKET_NOTIFY = "market_helper"; // 订单小助手
    public static final String PERFORMANCE_NOTIFY = "performance_helper"; // 订单小助手
    public static final String AFTER_SALE_NOTIFY = "after_sale_helper"; // 订单小助手
    public static final String NOTICE_NOTIFY = "notice_helper"; // 订单小助手
    public static final String TASK_NOTIFY = "task_helper"; // 订单小助手
    public static final String ORGANIZATION_NOTIFY = "organization_helper"; // 组织小助手
    public static final String INTELLIGENCE_NOTIFY = "intelligence_helper"; // 情报小助手
    public static final String CLUE_NOTIFY = "clue_helper"; // 线索小助手
    public static final String BUSINESS_CHANCE_NOTIFY = "business_chance_helper"; // 商机小助手
    public static final String COMMERCIAL_NOTIFY = "commercial_helper"; // 商务小助手
    public static final String CONTRACT_NOTIFY = "contract_helper"; // 合同小助手
    public static final String DYNAMIC_NOTIFY = "dynamic_helper"; // 动态小助手
    public static final String MARKET_ACTIVITY_NOTIFY = "market_activity_helper"; // 市场小助手
    public static final String RECEIPT_ACTIVITY_NOTIFY = "receipt_helper"; // 收款小助手

    public static final String SAMPLE_NOTIFY = "sample_helper"; // 送样小助手
    public static final String QUOTED_PRICE_NOTIFY = "quoted_price_helper"; // 报价小助手



    public static final String TYPE_CUSTOM_LIST = "TYPE_CUSTOM_LIST"; // 订单小助手
    public static final String TYPE_CUSTOM_TEXT= "TYPE_CUSTOM_TEXT"; // 订单小助手
    public static final String TYPE_CUSTOM_CONTENTONLY = "TYPE_CUSTOM_CONTENTONLY"; // 订单小助手

    public static String NoticeToHelp(String notice){
        if(notice.equals(ORDER_NOTIFY)){
            return ORDER;
        }else if(notice.equals(RECEIVE_NOTIFY)){
            return RECEIVE;
        }else if(notice.equals(PLAN_NOTIFY)){
            return PLAN;
        }else if(notice.equals(MARKET_NOTIFY)){
            return MARKET;
        }else if(notice.equals(PERFORMANCE_NOTIFY)){
            return PERFORMANCE;
        }else if(notice.equals(AFTER_SALE_NOTIFY)){
            return AFTER_SALE;
        }else if(notice.equals(NOTICE_NOTIFY)){
            return NOTICE;
        }else if(notice.equals(TASK_NOTIFY)){
            return TASK;
        }else{
            return "";
        }

    }

    public static Boolean isHelp(String identify){
        if( identify.equals(TimHelperType.ORDER_NOTIFY) ||identify.equals(TimHelperType.RECEIVE_NOTIFY)||identify.equals(TimHelperType.PLAN_NOTIFY)||
                identify.equals(TimHelperType.AFTER_SALE_NOTIFY) ||identify.equals(TimHelperType.MARKET_NOTIFY)||identify.equals(TimHelperType.PERFORMANCE_NOTIFY)||
                identify.equals(TimHelperType.NOTICE_NOTIFY) ||identify.equals(TimHelperType.TASK_NOTIFY)||
                identify.equals(TimHelperType.ORGANIZATION_NOTIFY) ||identify.equals(TimHelperType.INTELLIGENCE_NOTIFY)||
                identify.equals(TimHelperType.CLUE_NOTIFY) ||identify.equals(TimHelperType.BUSINESS_CHANCE_NOTIFY)||
                identify.equals(TimHelperType.COMMERCIAL_NOTIFY) ||identify.equals(TimHelperType.CONTRACT_NOTIFY)||
                identify.equals(TimHelperType.DYNAMIC_NOTIFY) ||identify.equals(TimHelperType.MARKET_ACTIVITY_NOTIFY)||
                identify.equals(TimHelperType.SAMPLE_NOTIFY) ||identify.equals(TimHelperType.QUOTED_PRICE_NOTIFY)||
                identify.equals(TimHelperType.RECEIPT_ACTIVITY_NOTIFY) ){
            return true;
        }else {
            return false;
        }
    }


}
