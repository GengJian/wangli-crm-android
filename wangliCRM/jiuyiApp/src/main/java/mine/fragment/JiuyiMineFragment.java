package mine.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.facebook.drawee.view.SimpleDraweeView;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.tencent.qcloud.sdk.Constant;

import java.util.ArrayList;
import java.util.List;

import mine.bean.MineBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的界面
 *****************************************************************
 */
public class JiuyiMineFragment extends JiuyiFragmentBase {
    private TextView tv_collection,tv_feedback,tv_Setting;
    private LinearLayout llDate,llTask;
    private Button mbtnCheck;
    private TextView tvName,tvdept;
    private boolean havePermission = false;
    ImageButton mibadd;
    private SimpleDraweeView ivAvatar;
    private String msAvatar="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_minefragment_layout"), null);
            setOnRefreshListener();
            onInit();
        }else{
            checkRootViewParent();
        }

        return mRootView;
    }
    @Override
    public void onInit() {
        havePermission = checkRecordPermission();
        super.onInit();
        ivAvatar=(SimpleDraweeView)mRootView.findViewById(Res.getViewID(null, "ivAvatar"));
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERICONURL,msAvatar);
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customerpicture);
                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,Rc.id);
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"operator");
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.Customerpicture,true);

            }
        });
        llDate=(LinearLayout)mRootView.findViewById(Res.getViewID(null,"ll_date"));
        llDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(mBundle, Pub.Mine_Date,true);
            }
        });
        llTask=(LinearLayout)mRootView.findViewById(Res.getViewID(null,"ll_task"));
        llTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(mBundle, Pub.Normal_TaskList,true);
            }
        });
        tv_collection=(TextView)mRootView.findViewById(Res.getViewID(null,"tv_collection"));
        if(tv_collection!=null){
            tv_collection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(mBundle, Pub.Mine_Collection,true);
                }
            });
        }
        tv_feedback=(TextView)mRootView.findViewById(Res.getViewID(null,"tv_feedback"));
        if(tv_feedback!=null){
            tv_feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(mBundle, Pub.Mine_NewFeedback,true);
                }
            });
        }
        tv_Setting=(TextView)mRootView.findViewById(Res.getViewID(null,"tv_Setting"));
        if(tv_Setting!=null){
            tv_Setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(mBundle, Pub.Mine_Setting,true);
                }
            });
        }
        tvName=(TextView)mRootView.findViewById(Res.getViewID(null,"tvName"));
        tvdept=(TextView)mRootView.findViewById(Res.getViewID(null,"tvdept"));
//        tvValue=(TextView)mRootView.findViewById(Res.getViewID(null,"tvValue"));
//        tvRank=(TextView)mRootView.findViewById(Res.getViewID(null,"tvRank"));
//        tv_deliveryplan=(TextView)mRootView.findViewById(Res.getViewID(null,"tv_deliveryplan"));
//        tv_receiptplan=(TextView)mRootView.findViewById(Res.getViewID(null,"tv_receiptplan"));
        mbtnCheck=(Button) mRootView.findViewById(Res.getViewID(null,"btnCheck"));
        if(mbtnCheck!=null){
            mbtnCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(mBundle, Pub.Mine_Sign,true);
                }
            });
        }
        mibadd=(ImageButton) mRootView.findViewById(R.id.ib_add);
        if(mibadd!=null){
            mibadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null, Pub.MSG_PopStart,true);
                }
            });
        }
        getMineInfo();
    }
    /**
     * 检查权限
     *
     * @return
     */
    private boolean checkRecordPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getCallBack().getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(getCallBack().getActivity(), permissions.toArray(new String[0]), 0);
                return false;
            }
        }
        return true;
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineFragment newInstance(int nPageType) {
        JiuyiMineFragment f = new JiuyiMineFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }

    private void getMineInfo() {
        showProcessBar(0);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("operator/find_index")
                        .tag("request_find_index")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<MineBean>() {
                            @Override
                            public void onSuccess(MineBean data) {
                                if(data!=null){
                                    if(data.getOperator()!=null){
                                        tvName.setText(data.getOperator());
                                    }
                                    if(data.getOffice()!=null){
                                        tvdept.setText(data.getOffice());
                                    }

//                                    if(data.getReward()>=0){
//                                        tvValue.setText("￥"+data.getReward());
//                                    }
//
//                                    if(data.getLevel()>0){
//                                        tvRank.setText("第"+data.getLevel()+"名");
//                                    }else{
//                                        tvRank.setText("无排名");
//                                    }
//
//                                    if(data.getDemandPlanDesp()!=null){
//                                        tv_deliveryplan.setText(data.getDemandPlanDesp());
//                                    }
//                                    if(data.getGatherPlnaDesp()!=null){
//                                        tv_receiptplan.setText(data.getGatherPlnaDesp());
//                                    }
                                    if(data.getUrl()!=null){
                                        msAvatar=data.getUrl();
                                        if(!Func.IsStringEmpty(msAvatar)){
                                            LoaderManager.getLoader().loadNet(ivAvatar, Constant.BaseUrl+"file/"+msAvatar, new ILoader.Options(R.drawable.m_avatar_def, R.drawable.m_avatar_def));
                                        }
                                    }else{
                                        ivAvatar.setImageResource(R.drawable.m_avatar_def);
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

    @Override
    public void createReq(boolean IsBg) {
        getMineInfo();
    }
}
