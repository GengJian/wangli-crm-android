
package com.control.utils;

/**
 * ****************************************************************
 * 文件名称:JiuyiBundleKey.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/9 15:01
 * 文件描述:页面间传参处理逻辑
 * 注意事项:
 * 1.传递对象的方法
 * 实现实例化接口 class jiuyiStruct implements Serializable
 * mBundle.putParcelable(JiuyiBundleKey.PARAM_, st);//传入对象,对象类必须实现Parcelable或者序列化
 * 2.传递对象的二维数组 mBundle.putSerializable(JiuyiBundleKey.PARAM_DARRAY, new jiuyiSerializableBean());
 * ****************************************************************
 */
public class JiuyiBundleKey {

    public static final String PARAM_MENU = "PARAM_MENU"; //执行startNextActivityForResult
	public static final String PARAM_TITLE = "PARAM_TITLE"; // 获得标题
    public static final String PARAM_TITLETYPE = "PARAM_TITLETYPE"; // 标题栏
    public static final String PARAM_HIDECLIENTTITLE = "PARAM_HIDECLIENTTITLE"; // H5是否隐藏标题栏
    public static final String PARAM_SKINTYPE = "PARAM_SKINTYPE";//默认的黑白皮肤
    public static final String PARAM_BILLTYPE = "PARAM_BILLTYPE";//单据类型
    public static final String PARAM_BILLID = "PARAM_BILLID";//单据类型
    public static final String PARAM_FEILD= "PARAM_FEILD"; // 字段
    public static final String PARAM_BACKPAGETYPE = "PARAM_BACKPAGETYPE"; // 后台接口标识

    public static final String PARAM_PAGETYPE = "PARAM_PAGETYPE"; // 要跳转的功能号
    public static final String PARAM_PRODUCTPAGETYPE = "PARAM_PRODUCTPAGETYPE"; // 要跳转的功能号
    public static final String PARAM_TRADEPAGETYPE = "PARAM_TRADEPAGETYPE"; // 要跳转的功能号
    public static final String PARAM_PRODUCTTYPE = "PARAM_PRODUCTTYPE"; // 生产状况flagment类型
    public static final String PARAM_SPECIALCONDITION= "PARAM_SPECIALCONDITION";//

    public static final String PARAM_OPERATORTYPE = "PARAM_OPERATORTYPE"; // 操作类型View查看，add新增，edit编辑
    public static final String PARAM_BILLOPERATORTYPE = "PARAM_BILLOPERATORTYPE"; // 操作类型View查看，add新增，edit编辑
    public static final String PARAM_ADDRESSBEAN = "PARAM_ADDRESSBEAN";//收货人地址对象
    public static final String PARAM_UPDATECONTROL = "PARAM_UPDATECONTROL";//实控人

    public static final String PARAM_TRADEACTION = "PARAM_TRADEACTION";// 是不是tradeaction功能
    public static final String PARAM_IDENTIFY = "PARAM_IDENTIFY";// 消息identify

    public static final String PARAM_LINKMANBEAN = "PARAM_LINKMANBEAN";//联系人对象
    public static final String PARAM_LINKMANBEANID = "PARAM_LINKMANBEANID";//联系人对象ID

    public static final String PARAM_PRODUCTINFOBEANID = "PARAM_PRODUCTINFOBEANID";//产品信息

    public static final String PARAM_NEEDPLANID = "PARAM_NEEDPLANID";//要货计划

    public static final String PARAM_INFOBEANID = "PARAM_INFOBEANID";//信息ID
    public static final String PARAM_INFOBEANTYPEID = "PARAM_INFOBEANTYPEID";//信息ID


    public static final String PARAM_CUSTOMERID = "PARAM_CUSTOMERID";//客户ID
    public static final String PARAM_CUSTOMERIDS = "PARAM_CUSTOMERIDS";//客户ID数组
    public static final String PARAM_CUSTOMERNAME = "PARAM_CUSTOMERNAME";//客户名称
    public static final String PARAM_INCHARGENAME = "PARAM_INCHARGENAME";//负责人名称
    public static final String PARAM_INCHARGEID = "PARAM_INCHARGEID";//负责人名称
    public static final String PARAM_OWETOTAL = "PARAM_OWETOTAL";//总欠款
    public static final String PARAM_DUETOTAL= "PARAM_DUETOTAL";//到期欠款
    public static final String PARAM_SAPNUMBER = "PARAM_SAPNUMBER";//SAPNUMBER

    public static final String PARAM_CUSTOMERLIMIT = "PARAM_CUSTOMERLIMIT";//额度
    public static final String PARAM_CUSTOMERPAYMENTDAYS = "PARAM_CUSTOMERPAYMENTDAYS";//账期
    public static final String PARAM_CUSTOMERPOSITION = "PARAM_CUSTOMERPOSITION";//客户ID
    public static final String PARAM_CUSTOMERPAGEINDEX = "PARAM_CUSTOMERPAGEINDEX";//客户pageindex
    public static final String PARAM_CUSTOMERRISKID = "PARAM_CUSTOMERRISKID";//客户风险ID
    public static final String PARAM_CUSTOMERCOLBEAN = "PARAM_CUSTOMERCOLBEAN";//客户列名称bean

    public static final String PARAM_CUSTOMERBIGTYPE = "PARAM_CUSTOMERBIGTYPE"; // 大类类型
    public static final String PARAM_CUSTOMERSMALLTYPE = "PARAM_CUSTOMERSMALLTYPE"; // 小类类型
    public static final String PARAM_CUSTOMERRISKTYPENAME = "PARAM_CUSTOMERRISKTYPENAME"; // 预警类型
    public static final String PARAM_CUSTOMERCOLVALUE = "PARAM_CUSTOMERCOLVALUE";//客户列值

    public static final String PARAM_CUSTOMERICONURL = "PARAM_CUSTOMERICONURL";//客户头像url
    public static final String PARAM_BATCHNUM = "PARAM_BATCHNUM";//批号
    public static final String PARAM_COMMONID = "PARAM_COMMONID";//通用id
    public static final String PARAM_COMMONKEY = "PARAM_COMMONKEY";//通用id
    public static final String PARAM_COMMONNAME = "PARAM_COMMONNAME";//通用id
    public static final String PARAM_PRODUCTPLACE = "PARAM_PRODUCTPLACE";//产地
    public static final String PARAM_FACTORYNAME = "PARAM_FACTORYNAME";//工厂
    public static final String PARAM_COMMONCODE = "PARAM_COMMONCODE";//工厂
    public static final String PARAM_WEIGHT = "PARAM_WEIGHT";//件重
    public static final String PARAM_PRESCRIPTION = "PARAM_PRESCRIPTION";//配方
    public static final String PARAM_LEVELNAME = "PARAM_LEVELNAME";//等级
    public static final String PARAM_LEVELCODE = "PARAM_LEVELCODE";//等级
    public static final String PARAM_SPEC = "PARAM_SPEC";//规格
    public static final String PARAM_DARRAY = "PARAM_DARRAY";//传数组
    public static final String PARAM_COMMONBEAN = "PARAM_COMMONBEAN";//对象
    public static final String PARAM_COMMONBEAN2 = "PARAM_COMMONBEAN2";//对象
    public static final String PARAM_PROVINCE = "PARAM_PROVINCE";//通用id
    public static final String PARAM_PROVINCESELECT = "PARAM_PROVINCESELECT";//通用id


    //选择日期相关的参数
	public static final String PARAM_DATE_BEGIN = "PARAM_DATE_BEGIN";// 开始日期
	public static final String PARAM_DATE_END = "PARAM_DATE_END";// 结束日期
	public static final String PARAM_DATE = "PARAM_DATE";// 日期
	public static final String PARAM_DATE_TYPR = "PARAM_DATE_TYPR";// 日期类型，0-没有日期选择， 1-通用日期选择方式（带周月）
	public static final String PARAM_DATE_TYPR_INCLUDETODAY = "PARAM_DATE_TYPR_INCLUDETODAY";// 日期是否包含今天

    //网页和URL相关的参数
	public static final String PARAM_HTTPServer = "PARAM_HTTPServer";// HTTPSERVER的参数部分ActionPageType()
    public static final String PARAM_AJAXGOBACKONLOAD_PARAM = "PARAM_AJAXGOBACKONLOAD_PARAM";//多个界面之间返回时,要把网页返回到上一页面时执行的GoBackOnLoad()方法名传回,默认执行"GoBackOnLoad();"方法
    //密码解锁相关的参数
    public static final String PARAM_UNLOCKPASSWORDNEXTPAGETYPE = "PARAM_UNLOCKPASSWORDNEXTPAGETYPE";//密码解锁下一个页面功能号
    public static final String PARAM_UNLOCKPASSWORDNEXTPAGEURL = "PARAM_UNLOCKPASSWORDNEXTPAGEURL";//密码解锁下一个页面url

	public static final String PARAM_PUSH_OPENAPP = "PARAM_PUSH_OPENAPP";//点击推送图标进入app


    public static final String PARAM_NEXTPAGERTYPE = "PARAM_NEXTPAGERTYPE"; //登录手机号跳转的功能号
    public static final String PARAM_PLANNEXTPAGERTYPE = "PARAM_PLANNEXTPAGERTYPE"; //登录手机号跳转的功能号
    public static final String PARAM_PLANEAN = "PARAM_PLANBEAN";//发货计划

    public static final String PARAM_DOSTARTNEXTACTIVITYFORRESULT = "PARAM_DOSTARTNEXTACTIVITYFORRESULT"; //执行startNextActivityForResult
    

    public static final String PARAM_URI= "PARAM_URI";//下载的PDF的URI地址 注意：不是url
    public static final String PARAM_HTTPPDF = "PARAM_HTTPPDF";
    public static final String PARAM_FILETYPE = "PARAM_FILETYPE";
}
