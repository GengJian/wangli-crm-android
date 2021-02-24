package commonlyused.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;

import commonlyused.fragment.JiuyiTaskSearchFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiTaskSearchActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用任务搜索界面
 *****************************************************************
 */
public class JiuyiTaskSearchActivity extends JiuyiActivityBase {
    private JiuyiTaskSearchFragment mContentFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_searchtask_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少

        FragmentManager fm = getSupportFragmentManager();
        mContentFragment = (JiuyiTaskSearchFragment) fm.findFragmentById(Res.getViewID(this, "jiuyi_searchcustomer_fragment"));
        if(mContentFragment == null ) {
            mContentFragment = new JiuyiTaskSearchFragment();
            fm.beginTransaction().add(Res.getViewID(this, "jiuyi_searchcustomer_fragment"), mContentFragment).commit();
        }
        //初始化Fragment是在布局里，所以要设置tztICallActivityCallBack
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
