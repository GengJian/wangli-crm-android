package customer.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;

import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;

import customer.fragment.JiuyiCustomerProductinfoFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerProductSearchActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 产品搜索页面
 *****************************************************************
 */
public class JiuyiCustomerProductStatusActivity extends JiuyiActivityBase {
    private JiuyiCustomerProductinfoFragment mContentFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customerproductstatus_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        //获取tztSearchStockFragment对象
        FragmentManager fm = getSupportFragmentManager();
        mContentFragment = (JiuyiCustomerProductinfoFragment) fm.findFragmentById(Res.getViewID(this, "jiuyi_searchcustomer_fragment"));
        if(mContentFragment == null ) {
            mContentFragment = new JiuyiCustomerProductinfoFragment();
            fm.beginTransaction().add(Res.getViewID(this, "jiuyi_searchcustomer_fragment"), mContentFragment).commit();
        }
        //初始化Fragment是在布局里，所以要设置tztICallActivityCallBack
        mContentFragment.setCallBack(mCallActivityCallBack);

        setTitle();
        setContentView(mBodyLayout);
    }
    public void setTitle(){
        mTitle="生产状况";
        super.setTitle(mTitle);
    }
    /**
     * 处理NavigationBar显示隐藏
     */
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange){
        mContentFragment.dealNavigationBarVisiableChange(nCurrBodyHeight, nDeltaChange);
    }
}
