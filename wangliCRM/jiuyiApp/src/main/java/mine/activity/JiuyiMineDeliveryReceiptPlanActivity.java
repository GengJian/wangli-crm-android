package mine.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;

import mine.fragment.JiuyiMineDeliveryReceiptFragment;
/**
 * ****************************************************************
 * 文件名称 : JiuyiMineDeliveryReceiptPlanActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的计划完成情况
 *****************************************************************
 */
public class JiuyiMineDeliveryReceiptPlanActivity extends JiuyiActivityBase {
    private JiuyiMineDeliveryReceiptFragment mContentFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_minedeliveryreceiptplan_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        //获取对象
        FragmentManager fm = getSupportFragmentManager();
        mContentFragment = (JiuyiMineDeliveryReceiptFragment) fm.findFragmentById(Res.getViewID(this, "jiuyi_deliveryreceipt_fragment"));
        if(mContentFragment == null ) {
            mContentFragment = new JiuyiMineDeliveryReceiptFragment();
            fm.beginTransaction().add(Res.getViewID(this, "jiuyi_deliveryreceipt_fragment"), mContentFragment).commit();
        }
        //初始化Fragment是在布局里
        mContentFragment.setCallBack(mCallActivityCallBack);

        setTitle();
        setContentView(mBodyLayout);
    }
    public void setTitle(){
        mTitle="我的计划完成情况";
        super.setTitle(mTitle);
    }
    /**
     * 处理NavigationBar显示隐藏
     */
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange){
        mContentFragment.dealNavigationBarVisiableChange(nCurrBodyHeight, nDeltaChange);
    }
}
