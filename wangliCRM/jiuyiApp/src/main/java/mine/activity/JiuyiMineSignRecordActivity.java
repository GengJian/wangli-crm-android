package mine.activity;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.control.shared.JiuyiUpdateInfoShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiDateUtil;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiButton;
import com.control.widget.dialog.JiuyiAppAdviceUpdateDialog;
import com.control.widget.dialog.JiuyiAppUpdataDialog;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.CheckUpdateBean;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.jiuyi.tools.jiuyiOperateApp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.fragment.JiuyiCustomerSearchFragment;
import customer.fragment.JiuyiCustomerVisitListFragment;
import customer.fragment.JiuyiCustomerVisitReceiptFragment;
import mine.fragment.JiuyiMineScheduleListFragment;
import mine.fragment.JiuyiMineSignListFragment;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineSignRecordActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的签到记录
 *****************************************************************
 */

public class JiuyiMineSignRecordActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiMineSignListFragment mContentFragment;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_mineschedulenew_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        FragmentManager fm = getSupportFragmentManager();
        mContentFragment = (JiuyiMineSignListFragment) fm.findFragmentById(Res.getViewID(this, "jiuyi_visit_fragment"));
        if(mContentFragment == null ) {
            mContentFragment =  JiuyiMineSignListFragment.newInstance(mPageType, mBundle);
            fm.beginTransaction().add(Res.getViewID(this, "jiuyi_visit_fragment"), mContentFragment).commit();
        }
        //初始化Fragment是在布局里，所以要设置tztICallActivityCallBack
        mContentFragment.setCallBack(mCallActivityCallBack);
        setContentView(mBodyLayout);
        setTitle();

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }

    }


    public void setTitle(){
        mTitle = "我的打卡记录";
        setTitle(mTitle);
    }

}
