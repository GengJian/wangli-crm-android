package commonlyused.activity;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;
import customer.Utils.ViewOperatorType;
import commonlyused.fragment.JiuyiPlanListFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiPlanListActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 工作计划列表
 *****************************************************************
 */
public class JiuyiPlanListActivity extends JiuyiActivityBase {
    private String sTitle;
    private ImageView   iv_back,iv_add;
    private  JiuyiPlanListFragment mContentFragment;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_planlist_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        FragmentManager fm = getSupportFragmentManager();
        mContentFragment = (JiuyiPlanListFragment) fm.findFragmentById(Res.getViewID(this, "jiuyi_plan_fragment"));
        if(mContentFragment == null ) {
            mContentFragment = JiuyiPlanListFragment.newInstance(mPageType);
            fm.beginTransaction().add(Res.getViewID(this, "jiuyi_plan_fragment"), mContentFragment).commit();
        }
        mContentFragment.setCallBack(mCallActivityCallBack);
        setTitle();
        setContentView(mBodyLayout);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }
        setTitle();
        iv_back = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });

        iv_add = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_add"));
        if (iv_add != null) {
            iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                    if(mPageType==Pub.Normal_RetailChannel){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建零售渠道部工作计划");
                        changePage(mBundle, Pub.Normal_RetailChannelNew, true);
                    }else if(mPageType==Pub.Normal_ChannelDevelopemnt){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建渠道开发部工作计划");
                        changePage(mBundle, Pub.Normal_ChannelDevelopemntNew, true);
                    }else if(mPageType==Pub.Normal_MarketEngineering){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建市场工程部工作计划");
                        changePage(mBundle, Pub.Normal_MarketEngineeringNew, true);
                    }else if(mPageType==Pub.Normal_StrategicEngineering){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建战略工程部工作计划");
                        changePage(mBundle, Pub.Normal_StrategicEngineeringNew, true);
                    }else if(mPageType==Pub.Normal_DirectSalesEngineering){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建直营工程部工作计划");
                        changePage(mBundle, Pub.Normal_DirectSalesEngineeringNew, true);
                    }else if(mPageType==Pub.Normal_NengChengSales){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建能诚市场部工作计划");
                        changePage(mBundle, Pub.Normal_NengChengSalesNew, true);
                    }else if(mPageType==Pub.Normal_HuaJueSales){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建华爵市场部工作计划");
                        changePage(mBundle, Pub.Normal_HuaJueSalesNew, true);
                    }else if(mPageType==Pub.Normal_JinMumenSales){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建金木门工作计划");
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "GOLDWOOD");
                        changePage(mBundle, Pub.Normal_JinMumenSalesNew, true);
                    }else if(mPageType==Pub.Normal_MumenSales){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建木门工作计划");
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "TIMBER");
                        changePage(mBundle, Pub.Normal_MumenSalesNew, true);
                    }else if(mPageType==Pub.Normal_LvMumenSales){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建铝木门工作计划");
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "ALD");
                        changePage(mBundle, Pub.Normal_LvMumenSalesNew, true);
                    }else if(mPageType==Pub.Normal_CopperSales){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新建铜艺销售工作计划");
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "CERART");
                        changePage(mBundle, Pub.Normal_CopperSalesNew, true);
                    }else if(mPageType==Pub.Normal_IntelligentLock){
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE, "新增智能锁销售工作计划");
                        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, "HYZNG");
                        changePage(mBundle, Pub.Normal_IntelligentLockNew, true);
                    }
                }
            });

        }

    }


    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }

    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else{
            mTitle = "计划列表";
        }
        setTitle(mTitle);
    }

}
