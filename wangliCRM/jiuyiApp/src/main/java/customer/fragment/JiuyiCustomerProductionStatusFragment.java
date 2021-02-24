package customer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import customer.Utils.ProductBillType;
import customer.entity.MemberAuthorityBean;
import customer.entity.RiskWarnBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerProductionStatusFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户生产状况信息
 *****************************************************************
 */
public class JiuyiCustomerProductionStatusFragment extends JiuyiFragmentBase {
    LinearLayout ll_poduct_information,ll_poduct_equipment,ll_poduct_dynamic,ll_poduct_raw_material,ll_poduct_competition_products,ll_poduct_recruitmentts,ll_poduct_license,ll_poduct_purchase,ll_poduct_import,
            ll_poduct_tax_rating,ll_poduct_check,ll_poduct_bond,ll_poduct_land;
    private TextView tvPoductInformation;
    private TextView tvPoductEquipment;
    private TextView tvPoductDynamic;
    private TextView tvPoductRawMaterial;
    private TextView tvPoductCompetitionProducts;
    private TextView tvPoductRecruitmentts;
    private TextView tvPoductLicense;
    private TextView tvPoductPurchase;
    private TextView tvPoductImport;
    private TextView tvPoductTaxRating;
    private TextView tvPoductCheck,tvPoductBond,tvPoductLand;
    private long Customerid=-1;
    RefreshLayout refreshLayout;
    private LinearLayout ll_content;
    private TextView tv_no_authoritytext;
    private ImageView iv_no_authority;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerproductionstatusfragment_layout"), null);
            onInit();
        } else {
            checkRootViewParent();
        }
        return mRootView;
    }
    /**
     * 初始化
     */
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        ll_content=(LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_content"));
        tvPoductInformation = (TextView) mRootView.findViewById(R.id.tv_poduct_information);
        tvPoductEquipment = (TextView) mRootView.findViewById(R.id.tv_poduct_equipment);
        tvPoductDynamic = (TextView) mRootView.findViewById(R.id.tv_poduct_dynamic);
        tvPoductRawMaterial = (TextView) mRootView.findViewById(R.id.tv_poduct_raw_material);
        tvPoductCompetitionProducts = (TextView) mRootView.findViewById(R.id.tv_poduct_competition_products);
        tvPoductRecruitmentts = (TextView) mRootView.findViewById(R.id.tv_poduct_recruitmentts);
        tvPoductLicense = (TextView) mRootView.findViewById(R.id.tv_poduct_license);
        tvPoductPurchase = (TextView) mRootView.findViewById(R.id.tv_poduct_purchase);
        tvPoductImport = (TextView) mRootView.findViewById(R.id.tv_poduct_import);
        tvPoductTaxRating = (TextView) mRootView.findViewById(R.id.tv_poduct_tax_rating);
        tvPoductCheck = (TextView) mRootView.findViewById(R.id.tv_poduct_check);
        tvPoductBond = (TextView) mRootView.findViewById(R.id.tv_poduct_bond);
        tvPoductLand = (TextView) mRootView.findViewById(R.id.tv_poduct_land);
        ll_poduct_information=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_information);
        if(ll_poduct_information!=null){
            ll_poduct_information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBundle==null){
                         mBundle = new Bundle();
                    }
                    mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productinfo);
                    changePage(mBundle, Pub.Customer_productinfo,true);
                }
            });
        }

        ll_poduct_equipment=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_equipment);
        if(ll_poduct_equipment!=null){
            ll_poduct_equipment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mBundle==null){
                        mBundle = new Bundle();
                    }
                    mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productequipment);
                    changePage(mBundle, Pub.Customer_productequipment,true);
                }
            });
        }

        ll_poduct_dynamic=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_dynamic);
        ll_poduct_dynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productdynamic);
                changePage(mBundle, Pub.Customer_productdynamic,true);

            }
        });
        ll_poduct_raw_material=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_raw_material);
        ll_poduct_raw_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productmaterial);
                changePage(mBundle, Pub.Customer_productmaterial,true);
            }
        });
        ll_poduct_competition_products=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_competition_products);
        ll_poduct_competition_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productcompetition);
                changePage(mBundle, Pub.Customer_productcompetition,true);
            }
        });
        ll_poduct_recruitmentts=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_recruitmentts);
        ll_poduct_recruitmentts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productrecruitmentts);
                changePage(mBundle, Pub.Customer_productrecruitmentts,true);
            }
        });
        ll_poduct_license=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_license);
        ll_poduct_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productlicense);
                changePage(mBundle, Pub.Customer_productlicense,true);
            }
        });
        ll_poduct_purchase=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_purchase);
        ll_poduct_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productpurchase);
                changePage(mBundle, Pub.Customer_productpurchase,true);
            }
        });
        ll_poduct_import=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_import);
        ll_poduct_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productimport);
                changePage(mBundle, Pub.Customer_productimport,true);
            }
        });
        ll_poduct_tax_rating=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_tax_rating);
        ll_poduct_tax_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productrating);
                changePage(mBundle, Pub.Customer_productrating,true);
            }
        });
        ll_poduct_check=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_check);
        ll_poduct_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productcheck);
                changePage(mBundle, Pub.Customer_productcheck,true);
            }
        });
        ll_poduct_bond=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_bond);
        ll_poduct_bond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productbond);
                changePage(mBundle, Pub.Customer_productbond,true);
            }
        });
        ll_poduct_land=(LinearLayout) mRootView.findViewById(R.id.ll_poduct_land);
        ll_poduct_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBundle==null){
                    mBundle = new Bundle();
                }
                mBundle.putInt(JiuyiBundleKey.PARAM_PRODUCTPAGETYPE,Pub.Customer_productland);
                changePage(mBundle, Pub.Customer_productland,true);
            }
        });
//        if(!MemberAuthorityBean.getIns().productPerformance){
//            tv_no_authoritytext.setVisibility(View.VISIBLE);
//            iv_no_authority.setVisibility(View.VISIBLE);
//            ll_content.setVisibility(View.GONE);
//        }else {
//            getInfoList();
//            RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
//            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//                @Override
//                public void onRefresh(RefreshLayout refreshlayout) {
//                    getInfoList();
//                    refreshlayout.finishRefresh(2000);
//                }
//            });
//        }
        getInfoList();
        RefreshLayout refreshLayout = (RefreshLayout)mRootView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getInfoList();
                refreshlayout.finishRefresh(2000);
            }
        });


    }


    private void getInfoList() {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("product/situation/"+Customerid+"?fromClientType=android")
                        .tag("request_get_situation")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<RiskWarnBean>() {
                            @Override
                            public void onSuccess(final RiskWarnBean data) {
                                if(data.getContent()!=null &&data.getContent().size()>0){
                                    final List<RiskWarnBean.ContentBean> contentBeansList=data.getContent();
                                   for(RiskWarnBean.ContentBean contentBean :contentBeansList){
                                       if(contentBean.getFieldValue().equals("产品信息")){
                                           tvPoductInformation.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("工厂设备")){
                                           tvPoductEquipment.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("生产动态")){
                                           tvPoductDynamic.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("原料信息")){
                                           tvPoductRawMaterial.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("竞品信息")){
                                           tvPoductCompetitionProducts.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("工厂招工")){
                                           tvPoductRecruitmentts.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("生产许可")){
                                           tvPoductLicense.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("采购招标")){
                                           tvPoductPurchase.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("进出口信息")){
                                           tvPoductImport.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("税务评级")){
                                           tvPoductTaxRating.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals("抽查检查")){
                                           tvPoductCheck.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals(ProductBillType.PRODUCTBOND)){
                                           tvPoductBond.setText(contentBean.getCount()+"");
                                       }else if(contentBean.getFieldValue().equals(ProductBillType.PRODUCTLAND)){
                                           tvPoductLand.setText(contentBean.getCount()+"");
                                       }
                                   }
                                }
                                showProcessBar(100);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                showProcessBar(100);
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });

            }
        };
        thread.start();

    }




    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerProductionStatusFragment newInstance(int nPageType) {
        JiuyiCustomerProductionStatusFragment f = new JiuyiCustomerProductionStatusFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerProductionStatusFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerProductionStatusFragment f = new JiuyiCustomerProductionStatusFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
}
