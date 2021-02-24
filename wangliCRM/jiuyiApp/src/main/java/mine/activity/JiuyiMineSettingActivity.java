package mine.activity;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.control.utils.CacheManager;
import com.control.utils.DialogID;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineSettingActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的设置
 *****************************************************************
 */

public class JiuyiMineSettingActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,tv_aboutus,tv_lockpassword,tv_clearcache,tv_changepwd;
    private JiuyiButton mbtRelogin;



    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_minesetting_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);

        setTitle();
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        tv_lockpassword = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_lockpassword"));
        if (tv_lockpassword != null) {
            tv_lockpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null,Pub.JY_MENU_PasswordLock,true);
                }
            });
        }
        tv_changepwd = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_changepwd"));
        tv_changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(null,Pub.PasswordChange,true);
            }
        });
        tv_clearcache = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_clearcache"));
        try {
            if(!CacheManager.getTotalCacheSize(Rc.getApplication()).equals("OK")){
                tv_clearcache.setText(CacheManager.getTotalCacheSize(Rc.getApplication()));
            }else{
                tv_clearcache.setText("0B");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tv_clearcache != null) {
            tv_clearcache.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tv_clearcache.getText().toString().equals("0B")){
                        Toast.makeText(JiuyiMineSettingActivity.this, "无缓存可清除！", Toast.LENGTH_SHORT).show();
                    }else{
                        startDialog(DialogID.DialogTrue, "", "确定清除缓存吗？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                    }
                }
            });
        }

        tv_aboutus = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_aboutus"));
        if (tv_aboutus != null) {
            tv_aboutus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null,Pub.Mine_Aboutus,true);
                }
            });
        }
        mbtRelogin= (JiuyiButton) mBodyLayout.findViewById(Res.getViewID(null, "btn_relogin"));
        if(mbtRelogin!=null){
            mbtRelogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Rc.ResetLogin(JiuyiMineSettingActivity.this);
                    changePage(null, Pub.ResetLogin,false);
                }
            });
        }



    }


    public void setTitle(){
        mTitle = "设置";
        setTitle(mTitle);
    }

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            if(nKeyCode==66){
                CacheManager.cleanAppWebCacheFiles(Rc.getApplication());
                CacheManager.cleanInternalCache(Rc.getApplication());
                tv_clearcache.setText("0B");
                Toast.makeText(JiuyiMineSettingActivity.this, "清除成功！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
