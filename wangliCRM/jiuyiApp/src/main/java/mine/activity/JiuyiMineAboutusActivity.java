package mine.activity;

import android.app.Dialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.control.shared.JiuyiUpdateInfoShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiDateUtil;
import com.control.widget.dialog.JiuyiAppAdviceUpdateDialog;
import com.control.widget.dialog.JiuyiAppUpdataDialog;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.CheckUpdateBean;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.jiuyi.tools.jiuyiOperateApp;
import com.tencent.qcloud.sdk.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineAboutusActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的关于信息
 *****************************************************************
 */

public class JiuyiMineAboutusActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,tv_introduce,tv_manager,tv_question,tv_license,tv_version,tv_version2,tv_update;
    private JiuyiButton mbtRelogin;
    private String msIntroduct,msManager,msQuestion,msLicense;
    /** 升级的URL*/
    private String mUpdateUrl;//升级的url



    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_mineaboutus_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);

        setTitle();
         List<DictBean> dictBeansList= DictUtils.getDictList("about_us");
        if(dictBeansList!=null &&dictBeansList.size()>0){
            for(int i=0;i<dictBeansList.size();i++){
                DictBean dictBean=dictBeansList.get(i);
                if(dictBean!=null){
                    if(dictBean.getKey().equals("app_introduce")){
                        msIntroduct=dictBean.getValue();
                    }else if(dictBean.getKey().equals("call_admin")){
                        msManager=dictBean.getValue();
                    }else if(dictBean.getKey().equals("common_problem")){
                        msQuestion=dictBean.getValue();
                    }else if(dictBean.getKey().equals("copyright_info")){
                        msLicense=dictBean.getValue();
                    }
                }
            }

        }
        tv_introduce = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_introduce"));
        tv_introduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="";
                if(!Func.IsStringEmpty(msIntroduct)){
                    msIntroduct+="&token="+Rc.id_tokenparam;
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, msIntroduct);
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"功能介绍");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                }

            }
        });
        tv_manager = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_manager"));
        tv_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Func.IsStringEmpty(msManager)){
                    msManager+="&token="+Rc.id_tokenparam;
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, msManager);
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"联系管理员");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                }

            }
        });
        tv_question = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_question"));
        tv_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Func.IsStringEmpty(msQuestion)){
                    msQuestion+="&token="+Rc.id_tokenparam;
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, msQuestion);
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"常见问题");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                }

            }
        });
        tv_license = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_license"));
        tv_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Func.IsStringEmpty(msLicense)){
                    msLicense+="&token="+Rc.id_tokenparam;
                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, msLicense);
                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"版本信息");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                }

            }
        });
        tv_update = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_update"));
        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpdateInfo();
            }
        });
//        if((!Func.IsStringEmpty(JiuyiUpdateInfoShared.getIns().mNoAlertTime)|| JiuyiDateUtil.TimeCompare(JiuyiDateUtil.getNowTime2(), JiuyiUpdateInfoShared.getIns().mNoAlertTime))
//                || JiuyiUpdateInfoShared.getIns().mCancelUpdate.equals("Y")){
//            tv_update.setVisibility(View.VISIBLE);
//        }
        String sVersion="王力CRM V"+Rc.getIns().mSysVersion;
//        if(!Rc.getIns().isPackageWithProguard()){
//            sVersion+= "_"+Constant.ServerHJ;
//        }
        tv_version = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_version"));
        tv_version.setText(sVersion);
        tv_version2 = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_version2"));
        tv_version2.setText(sVersion);
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        mbtRelogin= (JiuyiButton) mBodyLayout.findViewById(Res.getViewID(null, "btn_relogin"));
        if(mbtRelogin!=null){
            mbtRelogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Rc.ResetLogin(JiuyiMineAboutusActivity.this);
                    changePage(null, Pub.ResetLogin,false);
                }
            });
        }
    }


    public void setTitle(){
        mTitle = "关于我们";
        setTitle(mTitle);
    }

    public  void  getUpdateInfo(){
         Map<String, String> queryMap= new HashMap<String, String>();
        queryMap.put("version",Rc.getIns().mUpVersion );
        queryMap.put("fromClientType","android" );
        JiuyiHttp.POST("android_version_check/check")
                .setJson(com.common.GsonUtil.gson().toJson(queryMap))
                .request(new ACallback<CheckUpdateBean>() {
                    @Override
                    public void onSuccess(CheckUpdateBean data) {
                        if(data!=null){
                            try {
                                final String sUpdateSign=data.getRemark();
                                // 升级标志UpdateSign 1建议升级，2强制升级
                                mUpdateUrl=data.getAddress();
                                if(sUpdateSign==null||sUpdateSign.equals("0")){
                                    Toast.makeText(JiuyiMineAboutusActivity.this, "当前版本已是最新版本!", Toast.LENGTH_SHORT).show();

                                }else if (sUpdateSign.equals("1")||sUpdateSign.equals("2")) {
                                    final String sUpdateUrl=data.getAddress();
//                                    final String sUpdateMsg=data.getMessage();
                                    String sDesp="";
                                    sDesp=data.getDesp();
                                    if(sDesp==null){
                                        sDesp="检查到新版本，是否升级？";
                                    }
                                    final String sUpdateMsg=sDesp;
                                    if (sUpdateSign.equals("2")) {// 强制升级
                                        final JiuyiAppUpdataDialog dialog = new JiuyiAppUpdataDialog();
                                        final JiuyiDialogBase.DialogStruct struct = new JiuyiDialogBase.DialogStruct(sUpdateUrl, false, "升级", "取消");
                                        JiuyiMineAboutusActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                dialog.startDialog(JiuyiMineAboutusActivity.this, JiuyiMineAboutusActivity.this, DialogID.DialogForceUpdate, "新版本升级", sUpdateMsg, JiuyiDialogBase.Dialog_Type_YesNo, struct);
                                                dialog.show();
                                            }
                                        });
                                    }else if(sUpdateSign.equals("1")){// 建议升级
                                        final JiuyiAppAdviceUpdateDialog dialog = new JiuyiAppAdviceUpdateDialog();
                                        JiuyiMineAboutusActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                dialog.startDialog(JiuyiMineAboutusActivity.this, JiuyiMineAboutusActivity.this, DialogID.DialogAdviceUpdate, "新版本升级", sUpdateMsg, JiuyiDialogBase.Dialog_Type_YesNo, null);
                                                dialog.show();

                                            }
                                        });
                                    }

                                }

                            } catch (Exception e) {
                                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
                            }

                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                    }
                });

    }

    /**
     * 处理弹出Dialog的事件
     * @param nAction   与startDialog时的nAction相同，唯一标示是哪个Dialog
     * @param nKeyCode  确定(Pub.SOFT1)和返回(Pub.SOFT2)按钮
     * @param url       需要处理的Url
     * @param pDialog  pDialog弹框，用于后续处理关闭弹框
     */
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog){
        switch(nAction){
            case DialogID.DialogAdviceUpdate:
            case DialogID.DialogForceUpdate:
            {
                if (nKeyCode == KeyEvent.KEYCODE_BACK) {
                    switch (nAction) {
                        case DialogID.DialogForceUpdate:
                            new jiuyiOperateApp().doExit();
                            JiuyiMineAboutusActivity.this.finish();
                            break;
                        case DialogID.DialogAdviceUpdate:
                            JiuyiUpdateInfoShared.getIns().mCancelUpdate="Y";
                            JiuyiUpdateInfoShared.getIns().onSaveData(JiuyiMineAboutusActivity.this);
                        default:
                            mUpdateUrl= "";
                            gotoUpVersion();
                            break;
                    }
                } else if (nKeyCode == KeyEvent.KEYCODE_HOME) {
                    if(nAction == DialogID.DialogAdviceUpdate){
                        JiuyiUpdateInfoShared.getIns().mNoAlertTime= JiuyiDateUtil.getNowTime2();
                        JiuyiUpdateInfoShared.getIns().onSaveData(JiuyiMineAboutusActivity.this);
                        mUpdateUrl= "";
                        gotoUpVersion();
                    }
                }else if (nKeyCode == KeyEvent.KEYCODE_ENTER) {
                    // 只有点击确定才需要赋值url
                    //建议升级要跳转
                    if(nAction == DialogID.DialogAdviceUpdate){
                        JiuyiUpdateInfoShared.getIns().clearData();
                        gotoUpVersion();
                    }else if(nAction == DialogID.DialogForceUpdate){//强制升级要关闭软件
                        JiuyiUpdateInfoShared.getIns().clearData();
                        Rc.getIns().getBaseCallTopCallBack().gotoUpVersion(JiuyiMineAboutusActivity.this, mUpdateUrl, -1);
                        new jiuyiOperateApp().doExit();
                        JiuyiMineAboutusActivity.this.finish();
                    }
                }
                break;
            }
        }

    }
    public void gotoUpVersion(){
        Rc.getIns().getBaseCallTopCallBack().gotoUpVersion(JiuyiMineAboutusActivity.this, mUpdateUrl, -1);
    }
}
