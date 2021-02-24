package commonlyused.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;

import commonlyused.fragment.JiuyiContactSearchFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiContactSearchActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用联系人搜索界面
 *****************************************************************
 */
public class JiuyiContactSearchActivity extends JiuyiActivityBase {
    private JiuyiContactSearchFragment mContentFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_searchcontact_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少

        FragmentManager fm = getSupportFragmentManager();
        mContentFragment = (JiuyiContactSearchFragment) fm.findFragmentById(Res.getViewID(this, "jiuyi_searchcustomer_fragment"));
        if(mContentFragment == null ) {
            mContentFragment = new JiuyiContactSearchFragment();
            fm.beginTransaction().add(Res.getViewID(this, "jiuyi_searchcustomer_fragment"), mContentFragment).commit();
        }
        //初始化Fragment是在布局里，所以要设置ICallActivityCallBack
        mContentFragment.setCallBack(mCallActivityCallBack);

        setTitle();
        setContentView(mBodyLayout);
    }
    public void setTitle(){
        super.setTitle(mTitle);
    }
    /**
     * 处理NavigationBar显示隐藏
     */
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange){
        mContentFragment.dealNavigationBarVisiableChange(nCurrBodyHeight, nDeltaChange);
    }
}
