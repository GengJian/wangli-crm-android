package messages.timchat.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDialog;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import java.util.ArrayList;
import java.util.List;

import commonlyused.adapter.AppCategoryAdapter;
import commonlyused.adapter.AppCategoryDetailAdapter;
import commonlyused.bean.AppCategoryBean;
import commonlyused.bean.AppTotalBean;
import commonlyused.bean.QueryConditionBean;
import customer.Utils.ViewOperatorType;
import customer.entity.MemberSelectBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiPopStartActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 :弹出新增界面
 *****************************************************************
 */
public class JiuyiPopStartActivity extends JiuyiActivityBase {
    private TextView mtvclose ;
//    private TextView tv_add_customer,tv_add_delivery_plan,tv_add_collection_plan,tv_add_risk_warning;
private TextView tvAddCustomer;
    private TextView tvAddVisit;
    private TextView tvAddIntelligencenew;
    private TextView tvAddActivity;
    private TextView tvAddClue;
    private TextView tvAddBusiness;
    private TextView tvAddOffer;
    private TextView tvAddSampleApplication;
    private TextView tvAddContract;
    private TextView tvAddConsultation;
    private TextView tvAddComplaint;
    private TextView tvAddDynamic;
    private TextView tv_add_plan,tv_add_trip;
    private List<MemberSelectBean.ContentBean> memberBeanIDList=new ArrayList<>();
    private LoadingDialog dialog1;
    private int customerId=0;
    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_popstart_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        tvAddCustomer = (TextView) mBodyLayout.findViewById(R.id.tv_add_customer);
        tvAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(mBundle, Pub.Customer_new,false);
            }
        });
        tv_add_trip = (TextView) mBodyLayout.findViewById(R.id.tv_add_trip);
        tv_add_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"travel-business");
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建差旅");

                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newDynamicForm,false);
//                changePage(mBundle, Pub.Customer_NewVisit,false);
            }
        });
        tv_add_plan = (TextView) mBodyLayout.findViewById(R.id.tv_add_plan);
        tv_add_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog();
                getApplicationItemList();
            }
        });
        tvAddIntelligencenew = (TextView) mBodyLayout.findViewById(R.id.tv_add_intelligencenew);
        tvAddIntelligencenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新情报");
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.SINGLEADD);
                changePage(mBundle, Pub.Customer_InformationNew, false);
            }
        });
        tvAddActivity = (TextView) mBodyLayout.findViewById(R.id.tv_add_activity);
        tvAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"storme-manage-entity");
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建门店");
                changePage(mBundle, Pub.Customer_newDynamicForm,false);
            }
        });
        tvAddClue = (TextView) mBodyLayout.findViewById(R.id.tv_add_clue);
        tvAddClue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.ADD);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"新线索");
                changePage(mBundle, Pub.Dy_ClueNew,false);
            }
        });
        tvAddBusiness = (TextView) mBodyLayout.findViewById(R.id.tv_add_business);
        tvAddBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.ADD);
                changePage(mBundle, Pub.Dy_BusinessNew,false);
            }
        });
        tvAddOffer = (TextView) mBodyLayout.findViewById(R.id.tv_add_offer);
        tvAddOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.ADD);
                changePage(mBundle, Pub.Dy_OfferNew,false);
            }
        });
        tvAddSampleApplication = (TextView) mBodyLayout.findViewById(R.id.tv_add_sample_application);
        tvAddSampleApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.ADD);
//                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"新样品");
//                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"product-standard");
//                changePage(mBundle, Pub.Dy_SampleNew,false);
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"新联系人");
               changePage(mBundle, Pub.Customer_newlinkman,true);
            }
        });
        tvAddContract = (TextView) mBodyLayout.findViewById(R.id.tv_add_contract);
        tvAddContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.ADD);
//                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"新合同");
//                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,"product-standard");
//                changePage(mBundle, Pub.Dy_SampleNew,false);
                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.ADD);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"新任务");
                changePage(mBundle, Pub.Normal_NewTask,false);
            }
        });
//        tvAddConsultation = (TextView) mBodyLayout.findViewById(R.id.tv_add_consultation);
//        tvAddConsultation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
//                mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新需求");
//                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_newlinkmanneed,false);
//            }
//        });
//        tvAddComplaint = (TextView) mBodyLayout.findViewById(R.id.tv_add_complaint);
//        tvAddComplaint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(JiuyiPopStartActivity.this, "该功能将于2019.4.1上线！", Toast.LENGTH_SHORT).show();
////                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,ViewOperatorType.ADD);
////                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"新建客投");
////                changePage(mBundle, Pub.Customer_NewComplaint,false);
//            }
//        });
        tvAddDynamic = (TextView) mBodyLayout.findViewById(R.id.tv_add_dynamic);
        tvAddDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(null, Pub.Mine_Sign,false);
            }
        });



        mtvclose=(TextView) mBodyLayout.findViewById(Res.getViewID(null,"tv_add_close"));
        mtvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });

    }
    public  void  getApplicationItemList(){
        final QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("application-item/find-page-category")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<List<AppCategoryBean>>() {
                            @Override
                            public void onSuccess(List<AppCategoryBean> data) {
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                                boolean bFind=false;
                                if(data!=null && data.size()>0){
                                    for(int i=0;i<data.size();i++){
                                        for(int j=0;j<data.get(i).getItems().size();j++){
                                            int action=0;
                                            AppCategoryBean.ItemsBean itemsBean=data.get(i).getItems().get(j);
                                            if( itemsBean!=null && itemsBean.getUrl()!=null && itemsBean.getUrl().startsWith("action:71")){
                                                if(itemsBean.getName()!=null){
                                                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建"+itemsBean.getName());
                                                }
                                                if(itemsBean.getUrl().indexOf(":")>0){
                                                    action=Integer.parseInt(itemsBean.getUrl().substring(itemsBean.getUrl().indexOf(":")+1,itemsBean.getUrl().length()).trim())+1;

                                                    if(action==Pub.Normal_JinMumenSalesNew){
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建金木门工作计划");
                                                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "GOLDWOOD");
                                                    }else if(action==Pub.Normal_MumenSalesNew){
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建木门工作计划");
                                                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "TIMBER");
                                                    }else if(action==Pub.Normal_LvMumenSalesNew){
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建铝木门工作计划");
                                                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "ALD");
                                                    }else if(action==Pub.Normal_CopperSalesNew){
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建铜艺销售工作计划");
                                                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "CERART");
                                                    }else if(action==Pub.Normal_IntelligentLockNew){
                                                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新增智能锁销售工作计划");
                                                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "HYZNG");
                                                    }
                                                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                                                    changePage(mBundle, action, false);
                                                }
                                                bFind=true;
                                                break;

                                            }
                                        }
                                    }
                                }
                                if(!bFind){
                                    startDialog(DialogID.DialogDoNothing, "", "没有新建相关计划的权限", JiuyiDialogBase.Dialog_Type_Yes, null);
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
//                                startDialog(DialogID.DialogDoNothing, "", "没有新建相关计划权限", JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });


            }
        };
        thread.start();

    }





}
