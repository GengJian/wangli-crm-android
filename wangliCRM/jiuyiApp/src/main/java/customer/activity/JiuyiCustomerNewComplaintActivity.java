package customer.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiBigTextGroup;
import com.control.widget.JiuyiEditTextField;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.NoScrollGridView;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.activity.common.activity.JiuyiCaptureActivity;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.tools.ImageUtil;
import com.recyclerview.swipe.Closeable;
import com.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.recyclerview.swipe.SwipeMenu;
import com.recyclerview.swipe.SwipeMenuCreator;
import com.recyclerview.swipe.SwipeMenuItem;
import com.recyclerview.swipe.SwipeMenuRecyclerView;
import com.tencent.qcloud.sdk.Constant;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;

import customer.Utils.ViewOperatorType;
import customer.adapter.NewComplaintMutiAdapter;
import customer.adapter.PictureAdapter;
import customer.entity.BatchTransBean;
import customer.entity.ImageBrowseBean;
import customer.entity.MemberUpdateBean;
import customer.listener.ItemClickListener;
import customer.listener.OnItemClickListener;
import customer.view.Bimp;
import customer.view.jiuyiRecycleViewDivider;

import static customer.Utils.ViewOperatorType.EDIT;
import static customer.adapter.NewSpecialProductAdapter.VIEW_TYPE_MENU_DELETE;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewComplaintActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 客户投诉录入界面
 *****************************************************************
 */
public class JiuyiCustomerNewComplaintActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,tv_label;
    private String operatorType,sTitle,s_returnvalue;
    private  long customerid=-1;
    private ImageView   iv_back;
    private JiuyiItemGroup jigClient;
    private JiuyiItemGroup jigApp;
    private JiuyiItemGroup jigCsman;
    private JiuyiItemGroup jigComplaintdate;
    private JiuyiItemGroup jigAppdate;
    private JiuyiBigTextGroup jigRemark;
    private LinearLayout itemGroupLayout;
    private JiuyiEditTextField jetfBatch;
    private ImageView ivScan;
    private JiuyiItemGroup jigComplaintproduct;
    private JiuyiItemGroup jigDeliverydate;
    private JiuyiItemGroup jigDeliveryno;
    private JiuyiItemGroup jigDeliverycount;
    private JiuyiItemGroup jigOrderno;
    private JiuyiItemGroup jigContractno;
    private JiuyiItemGroup jigFactory;
    private JiuyiItemGroup jigHappendate;
    private JiuyiItemGroup jigHappenlink;
    private ImageView ivNew;
    private TextView tvNew;
    private JiuyiItemGroup jigNeedreport;
    private JiuyiItemGroup jigEnddate;
    private JiuyiBigTextGroup jigClientdemand;

    private SwipeMenuRecyclerView rvComplaintlist;
    private NewComplaintMutiAdapter menuAdapter;
    private RelativeLayout rvNew;
    private String operType="";
    private static final int COMPLAINT_CODE = 100;
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newcomplaint_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        operatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
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

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Rc.mBackfresh=true;
                    if(operatorType.equals(ViewOperatorType.EDIT)){
                        UpdateMemberInfo();
                    }else if(operatorType.equals(ViewOperatorType.ADD)){

                        setBackActivityBundle();
                        backPage();
                    }


                }
            });

        }
        initViews();

    }
    @Override
    public void initViews(){
        super.initViews();
//        tv_label = (TextView) mBodyLayout.findViewById(R.id.tv_label);
//        tv_label.setVisibility(View.VISIBLE);
        jigClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        jigApp = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_app);
        jigCsman = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_csman);
        jigComplaintdate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_complaintdate);
        jigAppdate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_appdate);
        jigRemark = (JiuyiBigTextGroup) mBodyLayout.findViewById(R.id.jig_remark);
        itemGroupLayout = (LinearLayout) mBodyLayout.findViewById(R.id.item_group_layout);
//        jetfBatch = (JiuyiEditTextField) mBodyLayout.findViewById(R.id.jetf_batch);
        ivScan = (ImageView) mBodyLayout.findViewById(R.id.iv_scan);
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
                Intent intent = new Intent(getApplication(), JiuyiCaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        jigComplaintproduct = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_complaintproduct);
        jigDeliverydate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_deliverydate);
        jigDeliveryno = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_deliveryno);
        jigDeliverycount = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_deliverycount);
        jigOrderno = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_orderno);
        jigContractno = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_contractno);
        jigFactory = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_factory);
        jigHappendate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_happendate);
        jigHappenlink = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_happenlink);
        rvComplaintlist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_complaintlist);
        rvNew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_new);
        ivNew = (ImageView) mBodyLayout.findViewById(R.id.iv_new);
        tvNew = (TextView) mBodyLayout.findViewById(R.id.tv_new);
        jigNeedreport = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_needreport);
        jigEnddate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_enddate);
        jigClientdemand = (JiuyiBigTextGroup) mBodyLayout.findViewById(R.id.jig_clientdemand);
        rvComplaintlist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_complaintlist);
        rvComplaintlist.setLayoutManager(new LinearLayoutManager(JiuyiCustomerNewComplaintActivity.this));
        rvComplaintlist.setHasFixedSize(true);
        rvComplaintlist.setItemAnimator(new DefaultItemAnimator());
        rvComplaintlist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiCustomerNewComplaintActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rvComplaintlist.setSwipeMenuCreator(swipeMenuCreator);
        rvComplaintlist.setSwipeMenuItemClickListener(menuItemClickListener);
        rvNew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_new);
        rvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiNewComplaintDetailMutiActivity.class);
//                intent.putExtra(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
                intent.putExtra(JiuyiBundleKey.PARAM_BILLTYPE, ViewOperatorType.ADD);
                startActivityForResult(intent, COMPLAINT_CODE);

            }
        });
    }
    @Override
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE,s_returnvalue);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiCustomerNewComplaintActivity.this.setResult(1, intent);
    }
    public void UpdateMemberInfo(){
        MemberUpdateBean updateBean=new MemberUpdateBean();

    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }

    @Override
    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else{
            mTitle = "新建客户投诉";
        }
        setTitle(mTitle);
    }
    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            if (viewType == VIEW_TYPE_MENU_DELETE) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(JiuyiCustomerNewComplaintActivity.this)
                        .setBackgroundDrawable(R.drawable.tzt_red)
                        .setText("删除")
                        .setTextColor(Res.getColor(null, "jiuyi_white_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);

            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if(menuAdapter!=null){
                    BatchTransBean contentBean=menuAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        menuAdapter.mViewTypeBeanList.remove(adapterPosition);
                        menuAdapter.notifyDataSetChanged();
                    }
//                    if(batchTransBeanList!=null && batchTransBeanList.size()>0){
//                        for(int i=0;i<batchTransBeanList.size();i++){
//                            BatchTransBean batchTransBean=batchTransBeanList.get(i);
//                            if(batchTransBean.getId()==contentBean.getId()){
//                                batchTransBeanList.remove(i);
//                            }
//                        }
//
//
//                    }
                }
            }
        }
    };

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(menuAdapter!=null){
                BatchTransBean contentBean=menuAdapter.mViewTypeBeanList.get(position);
                if(contentBean!=null){
                    operType="MODIFY";
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, EDIT);
                    mBundle.putParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN, contentBean);
                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.Customer_newneedplanmultiProduct);
                    changePage(mBundle, Pub.Customer_newneedplanmultiProduct,true);
                }
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    if(!Func.IsStringEmpty(result)){
                        String urladd=Res.getString(null, "jiuyibatchnum");
                        String url="";
                        if(!Func.IsStringEmpty(urladd)){
                            url= Constant.BaseH5Url+urladd+"?code="+result+"&token="+ Rc.id_tokenparam;
                        }
                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"批次查询");
                        mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                    }

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(JiuyiCustomerNewComplaintActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(JiuyiCustomerNewComplaintActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(JiuyiCustomerNewComplaintActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
