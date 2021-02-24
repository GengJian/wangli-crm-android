package com.control.utils;


/**
 * @author zhengss 放一些共用的函数和变量
 */
public final class Pub {
	public static final String SPLIT_CHAR_VLINE = "|";
	public static final String SPLIT_CHAR_COMMA = ",";
    public static final String SPLIT_CHAR_EQUAL = "=";
    //全局颜色
    public static int fontColor = 0x333333;   // 非选中字体颜色白色
    public static int hintcolor = 0xcccccc;   // 输入框的hine颜色
    public static int BgColor = 0xFFFFFF;     // 背景颜色，是灰色背景上面的白颜色


	// 10的几次方
	public static final long g_lMulti[] = { 1L, 10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L, 10000000000L, 100000000000L,
			1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L };
	/*
	 * 各个界面的ID
	 */
	public static int m_nStartHomePage;// 默认的首页
	public static final int MSG_Start = 1000;
	public static final int IntroductPage = MSG_Start + 10;//更新程序后显示引导页1010
	public static final int Doback = MSG_Start + 105; // 返回 1105

	public static final int MSG_PopStart = MSG_Start + 115; // 弹出开始
	public static final int MSG_detail = MSG_Start + 200; // 消息详情
	public static final int MSG_chat = MSG_Start + 201; // 聊天界面


	public static final int QS_Start = MSG_Start + 500;// 1500
	public static final int ResetLogin = QS_Start + 15;//  1515
	public static final int ModifyWebTextSizePopWnd = QS_Start + 22;//针对webview的字体大小修改弹出 1522
	public static final int HttpServer = QS_Start + 108; // HttpServer 1608
	public static final int ShareSDKPage = QS_Start + 209; //1709 分享界面 界面号在sharesdk的module中

	//客户信息功能号
	public static final int Customer_Start = 2000 ;//2000
	public static final int Customer_baseinfo = Customer_Start + 1 ;//2001基本信息
	public static final int Customer_PersonnelOrganization = Customer_Start + 2 ;//人事组织,2002
	public static final int Customer_FinancialRisk = Customer_Start + 3 ;//财务风险,2003
	public static final int Customer_Purchase = Customer_Start + 4 ;//采购状况,2004
	public static final int Customer_ProductionStatus = Customer_Start + 5 ;//生产动态,2005
	public static final int Customer_Sales = Customer_Start + 6 ;//销售状况,2006
	public static final int Customer_Research = Customer_Start + 7 ;//研发状况,2007
	public static final int Customer_Visit = Customer_Start + 8 ;//商务拜访,2008
	public static final int Customer_Business = Customer_Start + 9 ;//商机跟进,2009
	public static final int Customer_Contract = Customer_Start + 10 ;//合同跟踪,2010
	public static final int Customer_Service = Customer_Start + 11 ;//服务投诉,2011
	public static final int Customer_Cost = Customer_Start + 12 ;//费用分析,2012
	public static final int Customer_main = Customer_Start + 13 ;//客户主界面,2013
	public static final int Customer_detail = Customer_Start + 14 ;//客户详情界面,2014
	public static final int Customer_manualsort = Customer_Start + 15 ;//客户查询界面
	public static final int Customer_newlinkman = Customer_Start + 16 ;//新增联系人
	public static final int Customer_newreceiptaddress = Customer_Start + 17 ;//新增地址
	public static final int Customer_risktrack= Customer_Start + 18 ;//风险跟踪
	public static final int Customer_riskpandect= Customer_Start + 19 ;//风险总览
	public static final int Customer_newrisk= Customer_Start + 20 ;//新增风险预警
	public static final int Customer_newneedplan= Customer_Start + 21 ;//新增要货计划
	public static final int Customer_newReceiptplan= Customer_Start + 22 ;//新增收款计划
	public static final int Customer_productinfo= Customer_Start + 23 ;//产品信息
	public static final int Customer_productequipment= Customer_Start + 24 ;//工厂设备
	public static final int Customer_productdynamic= Customer_Start + 25;//生产动态
	public static final int Customer_productmaterial= Customer_Start + 26;//原材料信息
	public static final int Customer_productcompetition= Customer_Start + 27 ;//竞争信息
	public static final int Customer_productrecruitmentts= Customer_Start + 28 ;//工厂招工
	public static final int Customer_productlicense= Customer_Start + 29 ;//生产许可
	public static final int Customer_productpurchase= Customer_Start + 30 ;//采购招标
	public static final int Customer_productimport= Customer_Start + 31 ;//进出口
	public static final int Customer_productrating= Customer_Start + 32 ;//税务评级
	public static final int Customer_productcheck= Customer_Start + 33 ;//抽查检查
	public static final int Customer_markettrack= Customer_Start + 34 ;//市场动态跟踪
	public static final int Customer_marketpandect= Customer_Start + 35 ;//信息总览
	public static final int Customer_tradetrack= Customer_Start + 36 ;//交易跟踪
	public static final int Customer_tradepandect= Customer_Start + 37 ;//信息总览
	public static final int Customer_newproductinfo= Customer_Start + 38 ;//新增产品信息
	public static final int Customer_transactioncontract= Customer_Start + 39 ;//商务合同
	public static final int Customer_newproductequipment= Customer_Start + 40 ;//新增工厂设备
	public static final int Customer_newproductdynamic= Customer_Start + 41 ;//新增生产动态
	public static final int Customer_newproductmaterial= Customer_Start + 42 ;//新增原料信息
	public static final int Customer_newcompeteinfo= Customer_Start + 43 ;//新增竞品信息
	public static final int Customer_newrecruitment= Customer_Start + 44 ;//新增工厂招工
	public static final int Customer_newproductlicense= Customer_Start + 45 ;//新增生产许可
	public static final int Customer_newproductpurchase= Customer_Start + 46 ;//新增采购招标
	public static final int Customer_newproductimport= Customer_Start + 47 ;//新增进出口
	public static final int Customer_newproducttax= Customer_Start + 48 ;//新增税务评级
	public static final int Customer_newproductcheck= Customer_Start + 49 ;//新增检查抽查信息
	public static final int Customer_newmarketdynamic= Customer_Start + 50 ;//新建市场动态
	public static final int Customer_newmarketcomplain= Customer_Start + 51 ;//新建客户投诉
	public static final int Customer_newtradecontact= Customer_Start + 52 ;//新建合同

	public static final int Customer_tradecontact= Customer_Start + 53 ;//商务合同
	public static final int Customer_tradeorder= Customer_Start + 54 ;//销售订单
	public static final int Customer_tradedelivery= Customer_Start + 55 ;//发货
	public static final int Customer_tradeinvoice= Customer_Start + 56 ;//发票
	public static final int Customer_tradetelemoney= Customer_Start + 57 ;//电汇/承兑
	public static final int Customer_tradelogistics= Customer_Start + 58 ;//外贸物流
	public static final int Customer_tradebankstatement= Customer_Start + 59 ;//对账单

	public static final int Customer_transfer= Customer_Start + 60 ;//客户转移
	public static final int Customer_release= Customer_Start + 61 ;//客户释放
	public static final int Customer_claim= Customer_Start + 62 ;//客户认领

	public static final int Customer_search= Customer_Start + 100 ;//客户搜索
	public static final int Customerproduct_search= Customer_Start + 101 ;//客户产品搜索
	public static final int Customerpicture= Customer_Start + 102 ;//客户t头像
	public static final int CustomerupdatecolInfo= Customer_Start + 103 ;//更新客户信息

	public static final int Customer_new= Customer_Start + 104 ;//新增客户
	public static final int Customer_tradeDetail= Customer_Start + 105 ;//新增客户

	public static final int CustomerSelect= Customer_Start + 106 ;//客户选择界面
	public static final int CustomerChangeCredit= Customer_Start + 107 ;//修改信用额度
	public static final int Customer_newspecialprice= Customer_Start + 108 ;//新建特价申请
	public static final int Customer_newspecialpriceproduct= Customer_Start + 109 ;//新建特价申请
	public static final int Customer_newplanorder= Customer_Start + 110 ;//计划外订单
	public static final int Customer_newplanorderproduct= Customer_Start + 111 ;//计划外订单产品选择
	public static final int Customer_newLabel= Customer_Start + 112 ;//标签
	public static final int Customer_newneedplanmulti= Customer_Start + 113 ;//新增要货计划多个产品
	public static final int Customer_newneedplanmultiProduct= Customer_Start + 114 ;//新增要货计划选择多个产品
	public static final int Customer_productbond= Customer_Start + 115 ;//债券信息
	public static final int Customer_productland= Customer_Start + 116 ;//购地信息
	public static final int Customer_newproductbond= Customer_Start + 117 ;//新增债券信息
	public static final int Customer_newproductland= Customer_Start + 118 ;//新增购地信息

    //新增
	public static final int Customer_SystemInfo = Customer_Start + 601 ;//系统信息,2601

	public static final int Customer_ContributeDim = Customer_Start + 610 ;//贡献
	public static final int Customer_demandanalyseDim = Customer_Start + 611 ;//需求分析
	public static final int Customer_strategyDim = Customer_Start + 612 ;//战略
	public static final int Customer_purchaseDim = Customer_Start + 613 ;//售后服务维度
	public static final int Customer_quanlityDim = Customer_Start + 614 ;//售后服务维度
	public static final int Customer_keydemandDim = Customer_Start + 615 ;//关键需求分析
	public static final int Customer_keyindexDim = Customer_Start + 616 ;//关键指标
	public static final int Customer_linkmanOrg= Customer_Start + 620 ;//人事组织
	public static final int Customer_linkmandynamic= Customer_Start + 621 ;//人事动态
	public static final int Customer_newlinkmanneed= Customer_Start + 625 ;//新建痛点
	public static final int Customer_FinancialStatus= Customer_Start + 627 ;//财务状况
	public static final int Customer_FinancialInformation= Customer_Start + 628 ;//财务情报
	public static final int Customer_PurchaseStatus= Customer_Start + 629 ;//采购状况
	public static final int Customer_PurchaseInformation= Customer_Start + 630 ;//采购情报
	public static final int Customer_newDynamicForm = Customer_Start + 631 ;//新建动态表单
	public static final int Customer_newpurchase= Customer_Start + 632 ;//新建采购目录
	public static final int Customer_newstandard= Customer_Start + 633 ;//新建准入技检标准
	public static final int Customer_newevaluation= Customer_Start + 634 ;//新建评价考核体系
	public static final int Customer_ProductStatus= Customer_Start + 635 ;//生产状况
	public static final int Customer_ProductInformation= Customer_Start + 636 ;//生产情报
	public static final int Customer_NewProductionStandard= Customer_Start + 637 ;//新建标准
	public static final int Customer_NewPoductInformation= Customer_Start + 638 ;//新建产品信息
	public static final int Customer_NewPoductEquipment= Customer_Start + 639 ;//新建工厂设备
	public static final int Customer_NewPoductCapacity= Customer_Start + 640 ;//新建产能信息
	public static final int Customer_NewPoductQualityStandard= Customer_Start + 641 ;//新建品质标准
	public static final int Customer_NewIqc= Customer_Start + 642 ;//新建IQC来料
	public static final int Customer_NewPoductDynamic= Customer_Start + 643 ;//新建投产状况
	public static final int Customer_NewCtm= Customer_Start + 644 ;//新建CTM报告
	public static final int Customer_SalesStatus = Customer_Start + 647 ;//销售状况
	public static final int Customer_SalesInformation = Customer_Start + 648 ;//销售情报
	public static final int Customer_NewSaleSystem= Customer_Start + 649 ;//新建销售体系文件
	public static final int Customer_NewCustomerDirectory= Customer_Start + 650 ;//新建客户名录
	public static final int Customer_NewSalesQuotation= Customer_Start + 651 ;//新建销售报价
	public static final int Customer_NewImportsAndExports= Customer_Start + 652 ;//新建进出口产品
	public static final int Customer_ResearchStatus = Customer_Start + 655 ;//研发状况
	public static final int Customer_ResearchInformation = Customer_Start + 656 ;//研发情报
	public static final int Customer_NewResearchStandards= Customer_Start + 657 ;//新建研发标准
	public static final int Customer_NewPatentAuthentication= Customer_Start + 658 ;//新建专利认证
	public static final int Customer_NewTechnologyRoadmaps= Customer_Start + 659 ;//新建技术路标
	public static final int Customer_NewLaboratory= Customer_Start + 660 ;//新建实验室
	public static final int Customer_NewVisit= Customer_Start + 661 ;//新建拜访
	public static final int Customer_VisitDetail= Customer_Start + 662 ;//拜访详情
	public static final int Customer_VisitProcess= Customer_Start + 663 ;//拜访过程
	public static final int Customer_VisitRemark= Customer_Start + 664 ;//拜访备注呢
	public static final int Customer_VisitAttach= Customer_Start + 665 ;//拜访附件
	public static final int Customer_VisitInfo= Customer_Start + 666 ;//拜访客户
	public static final int Customer_ReceiptInfo= Customer_Start + 667 ;//接待客户
	public static final int Customer_CommonInput= Customer_Start + 668 ;//录入界面
	public static final int Customer_NewReceipt= Customer_Start + 669 ;//新建接待
	public static final int Customer_ReceiptDetail= Customer_Start + 670 ;//接待详情
	public static final int Customer_ReceiptHotel= Customer_Start + 671 ;//接待酒店
	public static final int Customer_ReceiptDining= Customer_Start + 672 ;//接待用餐
	public static final int Customer_ReceiptCar= Customer_Start + 673 ;//接待用车
	public static final int Customer_ReceiptGift= Customer_Start + 674 ;//接待礼品
	public static final int Customer_ReceiptContent= Customer_Start + 675 ;//接待
	public static final int Customer_ReceiptMeetting= Customer_Start + 676 ;//接待会议
	public static final int Customer_CommuniactionRecordNew= Customer_Start + 677 ;//新建沟通记录
	public static final int Customer_InformationNew= Customer_Start + 678 ;//新建情报

	public static final int Customer_RecordNew= Customer_Start + 679 ;//新建录音
	public static final int Customer_RecordPlay= Customer_Start + 680 ;//录音播放
	public static final int Customer_BusinessPandect = Customer_Start + 686 ;//商机总览
	public static final int Customer_BusinessTrack = Customer_Start + 687 ;//商机feed流
	public static final int Customer_ContractPandect = Customer_Start + 688 ;//合同总览
	public static final int Customer_ContractTrack = Customer_Start + 689 ;//合同跟踪

	public static final int Customer_NewConsultation= Customer_Start + 690 ;//新建咨询
	public static final int Customer_NewComplaint= Customer_Start + 691 ;//新建投诉
	public static final int Customer_NewTranferGoods= Customer_Start + 692 ;//新建退换货
	public static final int Customer_NewProblem= Customer_Start + 693 ;//新建问题
	public static final int Customer_NewReply= Customer_Start + 694 ;//新建问回复

	public static final int Customer_CostType= Customer_Start + 700 ;//费用类型分析
	public static final int Customer_IronTriCost= Customer_Start + 701 ;//铁三角费用分析
	public static final int Customer_CostAll= Customer_Start + 702 ;//所有
	public static final int Customer_CostOaApping= Customer_Start + 703 ;//OA审批中
	public static final int Customer_CostOaApped= Customer_Start + 704 ;//OA已审批

	public static final int Customer_AssistantList = Customer_Start + 710 ;//协助人
	public static final int Customer_NewAssistant= Customer_Start + 711 ;//新增协助人



	//订单信息功能号
	public static final int Dynamic_Start = 3000 ;//3000
	public static final int Orders_all = Dynamic_Start + 1 ;//3001所有订单信息
	public static final int Orders_app = Dynamic_Start + 2 ;//3001审批中订单信息
	public static final int Orders_delivery = Dynamic_Start + 3 ;//3001发货中订单信息
	public static final int Orders_completed = Dynamic_Start + 4 ;//3001完成订单信息
	public static final int Orders_search= Dynamic_Start + 5 ;//订单搜索

	public static final int Dynamic_all= Dynamic_Start + 10 ;//所有
	public static final int Dynamic_intelligence= Dynamic_Start + 11 ;//情报
	public static final int Dynamic_activity= Dynamic_Start + 12 ;//活动
	public static final int Dynamic_clue= Dynamic_Start + 13 ;//线索
	public static final int Dynamic_business= Dynamic_Start + 14 ;//商机
	public static final int Dynamic_sample= Dynamic_Start + 15 ;//样品
	public static final int Dynamic_offer= Dynamic_Start + 16 ;//报价
	public static final int Dynamic_visit= Dynamic_Start + 17 ;//拜访
	public static final int Dynamic_receipt= Dynamic_Start + 18 ;//接待
	public static final int Dynamic_order= Dynamic_Start + 19 ;//订单
	public static final int Dynamic_delivery= Dynamic_Start + 20 ;//发货
	public static final int Dynamic_invoice= Dynamic_Start + 21 ;//开票
	public static final int Dynamic_receivables= Dynamic_Start + 22 ;//收款
	public static final int Dynamic_requery= Dynamic_Start + 23 ;//咨询
	public static final int Dynamic_complaint= Dynamic_Start + 24 ;//客诉
	public static final int Dynamic_notice= Dynamic_Start + 25 ;//公告
	public static final int Dynamic_customer= Dynamic_Start + 26 ;//客户
	public static final int Dynamic_workring= Dynamic_Start + 27 ;//工作圈
	public static final int Dynamic_contract= Dynamic_Start + 28 ;//合同
	public static final int Dynamic_detail= Dynamic_Start + 29 ;//动态详情

	public static final int Dy_IntelligenceWhole= Dynamic_Start + 30 ;//情报大全
	public static final int Dy_IntelligenceAll= Dynamic_Start + 31 ;//情报大全
	public static final int Dy_IntelligenceDirectCustomer= Dynamic_Start + 32 ;//情报直销客户
	public static final int Dy_IntelligenceCompete= Dynamic_Start + 33 ;//情报竞争对手
	public static final int Dy_IntelligenceProvide= Dynamic_Start + 34 ;//情报供应商
	public static final int Dy_IntelligenceTerminal= Dynamic_Start + 35 ;//情报终端客户
	public static final int Dy_IntelligencePolicy= Dynamic_Start + 36 ;//情报政策
	public static final int Dy_IntelligenceTechnology= Dynamic_Start + 37 ;//情报技术
	public static final int Dy_IntelligenceNew= Dynamic_Start + 38 ;//新情报

	public static final int Dy_ActivityWhole= Dynamic_Start + 40 ;//市场活动大全
	public static final int Dy_ActivityAll= Dynamic_Start + 41 ;//市场活动所有
	public static final int Dy_ActivityApping= Dynamic_Start + 42 ;//市场活动审批中
	public static final int Dy_Activitying= Dynamic_Start + 43 ;//市场活动进行中
	public static final int Dy_ActivityEnd= Dynamic_Start + 44 ;//市场活动已结束
	public static final int Dy_ActivityCancel= Dynamic_Start + 45 ;//市场活动取消
	public static final int Dy_ActivityNew= Dynamic_Start + 46 ;//市场活动新建

	public static final int Dy_ClueWhole= Dynamic_Start + 50 ;//线索大全
	public static final int Dy_ClueALL= Dynamic_Start + 51 ;//线索所有
	public static final int Dy_ClueCheck= Dynamic_Start + 52 ;//线索验证中
	public static final int Dy_ClueBreed= Dynamic_Start + 53 ;//线索培育中
	public static final int Dy_ClueTransBusiness= Dynamic_Start + 54 ;//线索已转商机
	public static final int Dy_ClueClose= Dynamic_Start + 55 ;//线索关闭
	public static final int Dy_ClueNew= Dynamic_Start + 56 ;//线索新建

	public static final int Dy_BusinessWhole= Dynamic_Start + 60 ;//商机大全
	public static final int Dy_BusinessAll= Dynamic_Start + 61 ;//商机大全
	public static final int Dy_BusinessUnderReview = Dynamic_Start + 62 ;//商机谈判
	public static final int Dy_BusinessInvalid = Dynamic_Start + 63 ;//商机样品
	public static final int Dy_BusinessBreediing = Dynamic_Start + 64 ;//商机合同
	public static final int Dy_BusinessTransferContract = Dynamic_Start + 65 ;//商机签约合同
	public static final int Dy_BusinessClose= Dynamic_Start + 66 ;//商机关闭
	public static final int Dy_BusinessNew= Dynamic_Start + 67 ;//商机新建
	public static final int Dy_BusinessAssign = Dynamic_Start + 68 ;//商机指派

	public static final int Dy_OfferWhole= Dynamic_Start + 70 ;//报价大全
	public static final int Dy_OfferAll= Dynamic_Start + 71 ;//报价大全
	public static final int Dy_OfferApp= Dynamic_Start + 72 ;//报价审批中
	public static final int Dy_OfferCheck= Dynamic_Start + 73 ;//报价验证
	public static final int Dy_OfferAccept= Dynamic_Start + 74 ;//报价客户接受
	public static final int Dy_OfferReject= Dynamic_Start + 75 ;//报价客户拒绝
	public static final int Dy_OfferCancel= Dynamic_Start + 76 ;//报价取消
	public static final int Dy_OfferNew= Dynamic_Start + 78 ;//报价新建

	public static final int Dy_SampleWhole= Dynamic_Start + 80 ;//样品
	public static final int Dy_SampleAll= Dynamic_Start + 81 ;//样品所有
	public static final int Dy_SampleApping= Dynamic_Start + 82 ;//样品审批中
	public static final int Dy_SampleApped= Dynamic_Start + 83 ;//样品已审批
	public static final int Dy_SampleBack= Dynamic_Start + 84 ;//样品退回
	public static final int Dy_SampleNew= Dynamic_Start + 85 ;//样品新建
	public static final int Dy_SampleDraft= Dynamic_Start + 87 ;//草稿
	public static final int Dy_SampleCANCEL= Dynamic_Start + 88 ;//取消

	public static final int Dy_ContractWhole= Dynamic_Start + 90 ;//合同大全
	public static final int Dy_ContractAll= Dynamic_Start + 91 ;//合同所有
	public static final int Dy_ContractApping= Dynamic_Start + 92 ;//合同审批中
	public static final int Dy_ContractDelivery= Dynamic_Start + 93 ;//合同发货
	public static final int Dy_ContractComplete= Dynamic_Start + 94 ;//合同完成
	public static final int Dy_ContractCancel= Dynamic_Start + 95 ;//合同取消
	public static final int Dy_ContractSave= Dynamic_Start + 96 ;//草稿
	public static final int Dy_ContractCommit= Dynamic_Start + 97 ;//待提交
	public static final int Dy_ContractUNDelivery= Dynamic_Start + 98 ;//待发货
	public static final int Dy_ContractStop= Dynamic_Start + 99 ;//终止

	public static final int Dy_OrderWhole= Dynamic_Start + 100 ;//订单
	public static final int Dy_OrderAll= Dynamic_Start + 101 ;//订单所有
	public static final int Dy_OrderFreeze= Dynamic_Start + 102 ;//订单冻结
	public static final int Dy_OrderDelivery= Dynamic_Start + 103 ;//订单发货
	public static final int Dy_OrderComplete= Dynamic_Start + 104 ;//订单完成
	public static final int Dy_OrderCancel= Dynamic_Start + 105 ;//订单取消

	public static final int Dy_deliveryWhole= Dynamic_Start + 110 ;//发货
	public static final int Dy_deliveryAll= Dynamic_Start + 111 ;//发货所有
	public static final int Dy_deliveryUnPost= Dynamic_Start + 112 ;//发货未过账
	public static final int Dy_deliveryPosted= Dynamic_Start + 113 ;//发货已过账
	public static final int Dy_deliveryPartInvoice= Dynamic_Start + 114 ;//发货不分开票
	public static final int Dy_deliveryInvoiced= Dynamic_Start + 115 ;//发货已开票

	public static final int Dy_invoiceWhole= Dynamic_Start + 120 ;//开票大全
	public static final int Dy_invoiceAll= Dynamic_Start + 121 ;//开票所有
	public static final int Dy_invoiceUnPost= Dynamic_Start + 122 ;//开票未过账
	public static final int Dy_invoicePost= Dynamic_Start + 123 ;//开票已过账
	public static final int Dy_invoiceClear= Dynamic_Start + 124 ;//开票一清账
	public static final int Dy_invoiceWriteOff= Dynamic_Start + 125 ;//开票已冲销
	public static final int Dy_invoiceError= Dynamic_Start + 126;//开票出错
	public static final int Dy_invoiceCancel= Dynamic_Start + 127 ;//开票已冲销

	public static final int Dy_receivablesWhole= Dynamic_Start + 130 ;//收款大全
	public static final int Dy_receivablesAll= Dynamic_Start + 131 ;//收款所有
	public static final int Dy_receivablesGuangdong= Dynamic_Start + 132 ;//收款广东
	public static final int Dy_receivablesZhejiang= Dynamic_Start + 133 ;//收款浙江
	public static final int Dy_receivablesTianjin= Dynamic_Start + 134 ;//收款天津

	public static final int Dy_complaintWhole= Dynamic_Start + 140 ;//客诉大全
	public static final int Dy_complaintAll= Dynamic_Start + 141 ;//客诉所有
	public static final int Dy_complaintRegister= Dynamic_Start + 142 ;//客诉立案
	public static final int Dy_complaintDeal= Dynamic_Start + 143 ;//客诉处理中
	public static final int Dy_complaintClosing= Dynamic_Start + 144 ;//客诉结案中
	public static final int Dy_complaintClosed= Dynamic_Start + 145 ;//客诉已结案
	public static final int Dy_complaintUnRegister= Dynamic_Start + 146 ;//客诉已结案
	public static final int Dy_complaintNew= Dynamic_Start + 147 ;//客诉新建
	public static final int Dy_aixu1000askWhole = Dynamic_Start + 150 ;//爱旭1000问
	public static final int Dy_aixu1000askAll= Dynamic_Start + 151 ;//爱旭1000问所有
	public static final int Dy_aixu1000askRecommend= Dynamic_Start + 152 ;//爱旭1000问推荐
	public static final int Dy_aixu1000askHot= Dynamic_Start + 153 ;//爱旭1000问热门
	public static final int Dy_aixu1000askWaitreply= Dynamic_Start + 154 ;//爱旭1000问等待回答
	//常用功能号
	public static final int Normal_Start = 7000 ;//6000
	public static final int Normal_OrgAddresslist = Normal_Start + 1 ;//机构通讯录
	public static final int Normal_DeptAddresslist = Normal_Start + 2 ;//部门通讯录
	public static final int Normal_CompanyAddresslist = Normal_Start + 3 ;//公司通讯录
	public static final int Normal_subordinateContacts = Normal_Start +4 ;//下属联系人
	public static final int Normal_CustomerContacts = Normal_Start + 5 ;//客户联系人
	public static final int Normal_ImportantContacts = Normal_Start + 6 ;//重要联系人
	public static final int Normal_ContactsInfo = Normal_Start + 7 ;//联系人详情

	public static final int Normal_TaskList = Normal_Start + 8 ;//任务协作列表
	public static final int Normal_TaskAll = Normal_Start + 9 ;//任务协作列表
	public static final int Normal_TaskUnreceipt = Normal_Start + 10 ;//任务协作列表
	public static final int Normal_TaskUndo = Normal_Start + 11 ;//任务协作列表
	public static final int Normal_Taskcompleted = Normal_Start + 12 ;//任务协作列表
	public static final int Normal_NewTask = Normal_Start + 13 ;//新增任务
	public static final int Normal_NewFeedback = Normal_Start + 14 ;//新反馈
	public static final int Normal_ContactSearch = Normal_Start + 15 ;//搜索
	public static final int Normal_TaskSearch= Normal_Start + 16 ;//任务搜索
	public static final int Normal_TaskcompletedConfirm = Normal_Start + 17 ;//任务协作完成确认


	public static final int Normal_RetailChannel= Normal_Start + 110 ;//零售渠道部工作计划
	public static final int Normal_RetailChannelNew= Normal_Start + 111 ;//零售渠道部部工作计划新增
	public static final int Normal_ChannelDevelopemnt= Normal_Start + 120 ;//渠道开发部工作计划
	public static final int Normal_ChannelDevelopemntNew= Normal_Start + 121 ;//渠道开发部工作计划新增
	public static final int Normal_MarketEngineering= Normal_Start + 130 ;//市场工程部计划工作计划
	public static final int Normal_MarketEngineeringNew= Normal_Start + 131 ;//市场工程部计划工作计划新增
	public static final int Normal_StrategicEngineering= Normal_Start + 140 ;//战略工程部工作计划
	public static final int Normal_StrategicEngineeringNew= Normal_Start + 141 ;//战略工程部部工作计划新增
	public static final int Normal_DirectSalesEngineering= Normal_Start + 150 ;//直营工程部计划
	public static final int Normal_DirectSalesEngineeringNew= Normal_Start + 151 ;//直营工程部计划新增

	public static final int Normal_NengChengSales= Normal_Start + 160 ;//能诚
	public static final int Normal_NengChengSalesNew= Normal_Start + 161 ;//能诚新增
	public static final int Normal_HuaJueSales= Normal_Start + 170 ;//华爵
	public static final int Normal_HuaJueSalesNew= Normal_Start + 171 ;//华爵新增

	public static final int Normal_JinMumenSales= Normal_Start + 180 ;//金木门
	public static final int Normal_JinMumenSalesNew = Normal_Start + 181 ;//金木门新增

	public static final int Normal_MumenSales= Normal_Start + 190 ;//木门
	public static final int Normal_MumenSalesNew = Normal_Start + 191 ;//木门新增

	public static final int Normal_LvMumenSales= Normal_Start + 200 ;//铝木门
	public static final int Normal_LvMumenSalesNew = Normal_Start + 201 ;//铝木门新增

	public static final int Normal_CopperSales= Normal_Start + 210 ;//铜艺销售人员
	public static final int Normal_CopperSalesNew = Normal_Start + 211 ;//铜艺销售人员新增

	public static final int Normal_IntelligentLock= Normal_Start + 220 ;//智能锁
	public static final int Normal_IntelligentLockNew = Normal_Start + 221 ;//智能锁

	//我的功能号
	public static final int Mine_Start = 6000 ;//6000
	public static final int Mine_deliveryReceiptPlan = Mine_Start + 1 ;//我的发货收款计划
	public static final int Mine_deliveryPlan = Mine_Start + 2 ;//我的发货计划
	public static final int Mine_ReceiptPlan = Mine_Start + 3 ;//我的收款计划
	public static final int Mine_deliveryPlanDetail = Mine_Start + 4 ;//我的发货计划明细
	public static final int Mine_ReceiptPlanDetail = Mine_Start + 5 ;//我的收款计划明细
	public static final int Mine_Collection = Mine_Start + 6 ;//我的收藏
	public static final int Mine_CollectionAll = Mine_Start + 7 ;//我的所有收藏
	public static final int Mine_CollectionProduct = Mine_Start + 8 ;//我的产品收藏
	public static final int Mine_CollectionCustomer = Mine_Start + 9 ;//我的客户收藏
	public static final int Mine_CollectionOrder = Mine_Start + 10 ;//我的订单收藏
	public static final int Mine_CollectionNotice= Mine_Start + 11 ;//我的公告收藏
	public static final int Mine_CollectionTask = Mine_Start + 12 ;//我的任务收藏

	public static final int Mine_NewFeedback = Mine_Start + 13 ;//新建意见反馈
	public static final int Mine_Setting = Mine_Start + 14 ;//设置
	public static final int Mine_Aboutus = Mine_Start + 15 ;//关于我们
	public static final int Mine_Sign = Mine_Start + 16 ;//签到
	public static final int Mine_deliveryPlanTotal = Mine_Start + 17 ;//我的发货计划汇总

	public static final int Mine_Date = Mine_Start + 18 ;//我的日程
	public static final int Mine_Date_Visit = Mine_Start + 19 ;//我的日程拜访
	public static final int Mine_Date_Receipt = Mine_Start + 20 ;//我的日程接待

	/**
	 * 密码锁
	 */
	public static final int PasswordLock_Start = 4900 ;//4900
	public static final int PasswordLock_Open = PasswordLock_Start + 1 ;//4901 开启密码锁
	public static final int PasswordLock_Close = PasswordLock_Start + 2 ;//4902 关闭密码锁
	public static final int PasswordLock_ResetPassword = PasswordLock_Start + 3 ;//4903 密码锁更改密码
	public static final int PasswordLock_SetLockTime = PasswordLock_Start + 4 ;//4904 设置锁定时间
	public static final int PasswordLock_Lock = PasswordLock_Start + 5 ;//4905 锁屏界面
	public static final int PasswordLock_OpenFingerLock = PasswordLock_Start + 6 ;//4906开启指纹解锁
	public static final int PasswordLock_CloseFingerLock = PasswordLock_Start + 7 ;//4906关闭指纹解锁



	public static final int PasswordChange = PasswordLock_Start + 20 ;//修改密码
	/**
	 * 底部菜单栏功能号
	 */
	public static final int MENU_Message = 5001;//消息
	public static final int MENU_Customer = 5002;//客户
	public static final int MENU_Trip = 5003; // 差旅
	public static final int MENU_Normal = 5004;//常用
	public static final int MENU_Mine = 5005;//我的
	public static final int MENU_QS_NJSC_StartOpen = 57800;
	//屏幕截图
	public static final int MENU_QS_CutScreen = 59111;//截屏幕的某个区域保存为图片操作

	public static final int MENU_PDF = 59000;//打开pdf

	//常用功能号
	public static final int JY_COMMON_Start = 8000 ;//8000
	public static final int JY_COMMON_NEWACTIVITY = JY_COMMON_Start+1 ;//8001


	/**
	 * 新功能号区间
	 */
	//操作功能 10000~10200**********************/
	public static final int JY_MENU_BEGIN = 10000;
	public static final int JY_MENU_Return = 10002;//返回，关闭当前的WebView
	public static final int JY_MENU_ResetToolBarImage = 1901;//修改底部tabbar图片
	public static final int JY_MENU_ToHotToolBarImage = 1902;//修改底部tabbar Badge
	public static final int JY_MENU_CloseAndReturn = 3413;//返回，关闭当前的WebView的组
	public static final int JY_MENU_SendMsg = JY_MENU_BEGIN + 20;// 10020 调用客户端发送短信

	public static final int JY_MENU_StartOpen = JY_MENU_BEGIN + 48;// 10048

	public static final int JY_MENU_OpenReqFile = JY_MENU_BEGIN + 54;// 10054 下载文件并打开文件
	public static final int JY_MENU_Share = JY_MENU_BEGIN + 55;// 10055 分享
	public static final int JY_MENU_OpenPicture = JY_MENU_BEGIN + 56;// 10056 打开图片
	public static final int JY_MENU_OpenPictureGroup = JY_MENU_BEGIN + 57;// 10056 打开图片组
	public static final int JY_MENU_OpenVideo = JY_MENU_BEGIN + 58;// 10057 打开视频
	public static final int JY_MENU_OpenVoice = JY_MENU_BEGIN + 59;// 10059打开语音
	public static final int JY_MENU_UIActivity = JY_MENU_BEGIN + 49;// 10049 关闭进度条
	public static final int JY_MENU_OpenWebInfoContent = JY_MENU_BEGIN + 61; //10061 打开指定URL地址，并可定制右上角按钮
	public static final int JY_MENU_GetGPSLocation = JY_MENU_BEGIN + 62; //10062
	public static final int JY_MENU_ClearPhoneState = JY_MENU_BEGIN + 71; //10071清除手机通知栏的消息
	public static final int JY_MENU_OpenOtherSoft = JY_MENU_BEGIN + 73; //10073打开指定url的第三方应用
	public static final int JY_MENU_ClearWebPageCache = JY_MENU_BEGIN + 74; //10074清除本地缓存页面文件

	public static final int JY_MENU_StartQRCode = JY_MENU_BEGIN + 78; //10078调用二维码扫描，并将扫描结果通过js返回给页面

	public static final int JY_MENU_WebOpenLocaleyboard = JY_MENU_BEGIN + 81;//10081网页调用系统键盘

	public static final int JY_MENU_Speech = JY_MENU_BEGIN + 85;//10085语音播放
	public static final int JY_MENU_PasswordLock = JY_MENU_BEGIN + 86;//10086密码锁
	public static final int JY_MENU_WebLogin = JY_MENU_BEGIN + 90;//10090 网页发起登录，登录成功后跳转到指定url地址


	//预留功能
	//***************系统功能 10300~10500**********************/
	public static final int MENU_SYS_BEGIN = (JY_MENU_BEGIN + 300);//10300
	public static final int MENU_SYS_UserLogout             = (JY_MENU_BEGIN + 302);//10302登出

	public static final int MENU_SYS_UpdataVersion          = (MENU_SYS_BEGIN + 30); //10330 升级版本
	public static final int MENU_SYS_SetStartPage           = (MENU_SYS_BEGIN + 32); //10332 启动后默认显示的界面
	public static final int MENU_SYS_KeepScreenOn                 = (MENU_SYS_BEGIN + 66); //10366 屏幕常亮


	public static final int Customer_test = 600001 ;//新增地址
}
