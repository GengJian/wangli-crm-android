package dynamic.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiBigTextGroup;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.wanglicrm.android.R;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.nanchen.compresshelper.CompressHelper;
import com.recyclerview.swipe.Closeable;
import com.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.recyclerview.swipe.SwipeMenu;
import com.recyclerview.swipe.SwipeMenuCreator;
import com.recyclerview.swipe.SwipeMenuItem;
import com.recyclerview.swipe.SwipeMenuRecyclerView;
import com.tencent.qcloud.sdk.Constant;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import customer.Utils.FileUtils;
import customer.Utils.ViewOperatorType;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.activity.JiuyiNewBusinessDetailMutiActivity;
import customer.adapter.NewComplaintMutiAdapter;
import customer.entity.AttachmentBean;
import customer.entity.BatchTransBean;
import customer.entity.MaterialTypeBean;
import customer.entity.Media;
import customer.entity.MemberBeanID;
import customer.entity.MemberUpdateBean;
import customer.listener.OnItemClickListener;
import customer.listener.PickerConfig;
import customer.view.JiuyiAttachment;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.adapter.NewCompeteMutiAdapter;
import dynamic.entity.DyActivityBean;
import dynamic.entity.DyBusinessBean;
import dynamic.entity.DyClueBean;
import dynamic.entity.DyInteligenceBean;

import static customer.Utils.ViewOperatorType.EDIT;
import static customer.adapter.NewSpecialProductAdapter.VIEW_TYPE_MENU_DELETE;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewComplaintActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 录入界面
 *****************************************************************
 */
public class JiuyiCustomerNewBusinessActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private String operatorType,sTitle,s_returnvalue;
    private  long customerid=-1;
    private ImageView   iv_back;
    private JiuyiItemGroup jigTitle;
    private JiuyiItemGroup jigSource,jig_type;
    private JiuyiItemGroup jigOldclient;
    private JiuyiItemGroup jigActivity;
    private JiuyiItemGroup jigClue;
    private JiuyiItemGroup jigPerson;
    private JiuyiItemGroup jigCommitdate;
    private JiuyiItemGroup jigClient;
    private JiuyiItemGroup jigLinkman;
    private JiuyiItemGroup jig_duty,jig_import;
    private EditText jig_linkmantel,jigAmt;
    private JiuyiItemGroup jigDealdate,jig_bigtype;
    private JiuyiBigTextGroup jigContent;
    private JiuyiAttachment ahAttach;

    private SwipeMenuRecyclerView rvComplaintlist;
    private NewCompeteMutiAdapter menuAdapter;
    private RelativeLayout rvNew;
    private String operType="";
    private static final int COMPLAINT_CODE = 100;
    private DyBusinessBean.ContentBean.CompetitorBehavior competitorBean;
    private List<DyBusinessBean.ContentBean.CompetitorBehavior> competitorBehaviorList;
    private int currentPosition=-1;
    private DyBusinessBean.ContentBean createBean;
    private List<MaterialTypeBean.ContentBean> mProductTypeSelectList;
    private long billID=0;
    protected static final int TAKE_PICTURE = 1000;
    protected static final int SELECT_PICTURE = 1500;
    private  File[] files;
    private List<AttachmentBean> attachmentsBeanslist,attachmentsEditBeanslist;
    private String filePath;
    private Boolean needUpload=false;



    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newbusiness_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        operatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }
        if(Func.IsStringEmpty(operatorType)){
            operatorType="";
        }
        if(operatorType.equals(ViewOperatorType.ADD)){
            createBean=new DyBusinessBean.ContentBean();
        }
        initViews();
        if(operatorType.equals(ViewOperatorType.EDIT)){//从动态跳转
            billID=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
            if(billID>0){
                getDetailInfo(billID);
            }
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
                    Rc.mBackfresh=true;
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
                    showDialog();
                    if(ahAttach.getMediaArrayList().size()>0) {
                        if(operatorType.equals(ViewOperatorType.EDIT)){
                            attachmentsEditBeanslist=new ArrayList<>();
                        }

                        files = new File[ahAttach.getMediaArrayList().size()];
                        for (int i = 0; i < ahAttach.getMediaArrayList().size(); i++) {
                            Media media=ahAttach.getMediaArrayList().get(i);
                            if (media.getPath() != null ) {
                                File file1 = new File(media.getPath());
                                files[i] = file1;
                                needUpload=true;
                            }else{
                                if(media.getUrl()!=null && attachmentsBeanslist!=null && attachmentsBeanslist.size()>0  ){
                                    for(int j=0;j<attachmentsBeanslist.size();j++){
                                        AttachmentBean attachmentBean=attachmentsBeanslist.get(j);
                                        if(attachmentBean.getQiniuKey()!=null   ){
                                            int pos=media.getUrl().lastIndexOf("/");
                                            if(pos>0){
                                                String qiuniukey=media.getUrl().substring(pos+1,media.getUrl().length());
                                                if(qiuniukey.equals(attachmentBean.getQiniuKey())){
                                                    attachmentsEditBeanslist.add(attachmentBean);
                                                }
                                            }
                                        }
//                                        if(attachmentBean.getQiniuKey()!=null){
//                                            String qiuniukey=media.getQiniuKey();
//                                            if( qiuniukey!=null && qiuniukey.equals(attachmentBean.getQiniuKey())){
//                                                attachmentsEditBeanslist.add(attachmentBean);
//                                            }
//                                        }
                                    }

                                }


                            }
                        }
                        if(needUpload){
                            upload();
                        }else{
                            if(attachmentsEditBeanslist.size()>0){
                                createBean.setAttachmentList(attachmentsEditBeanslist);
                                updateInfo();
                            }
                        }

                    }else{
                        if(operatorType.equals(ViewOperatorType.ADD)){
                            createBean();
                        }else if(operatorType.equals(ViewOperatorType.EDIT)){
                            updateInfo();
                        }

                    }



                }
            });

        }


    }
    public void initViews(){
        jigClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        jigTitle = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_title);
        jigSource = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_source);
        jigSource.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewBusinessActivity.this);
                buidler.setTitle("商机来源");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("business_chance_resource");
                if(dictBeansList!=null &&dictBeansList.size()>0) {
                    data = new String[dictBeansList.size()];
                    for (int i = 0; i < dictBeansList.size(); i++) {
                        data[i] = dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            jigSource.setText(data[which].trim());
                            createBean.setResourceKey(dictBeansList.get(which).getKey());
                            createBean.setResourceValue(dictBeansList.get(which).getValue());
                            if(dictBeansList.get(which).getKey().equals("customer_introduce")){
                                jigOldclient.setVisibility(View.VISIBLE);
                            }else {
                                 jigOldclient.setVisibility(View.GONE);
                            }
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        jig_type = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_type);
        jig_type.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewBusinessActivity.this);
                buidler.setTitle("商机类型");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("business_chance_type");
                if(dictBeansList!=null &&dictBeansList.size()>0) {
                    data = new String[dictBeansList.size()];
                    for (int i = 0; i < dictBeansList.size(); i++) {
                        data[i] = dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            jig_type.setText(data[which].trim());
                            createBean.setTypeKey(dictBeansList.get(which).getKey());
                            createBean.setTypeValue(dictBeansList.get(which).getValue());
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        jigOldclient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_oldclient);
        jigOldclient.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_SPECIALCONDITION,"Y");
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 200);

            }
        });

        jigActivity = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_activity);
        jigActivity.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"ACTIVITY");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 204);

            }
        });
        jigClue = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_clue);
        jigClue.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CLUE");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 205);

            }
        });
        jigClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        jigClient.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_SPECIALCONDITION,"Y");
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 201);

            }
        });
        jigLinkman = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_linkman);
        jig_duty = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_duty);
        jigDealdate = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_dealdate);
        jigDealdate.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                DatePickDialog dialog = new DatePickDialog(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity());
                //设置上下年分限制
                dialog.setYearLimt(60);
                //设置标题
                dialog.setTitle("选择时间");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat("yyyy-MM-dd");
                //设置选择回调
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        jigDealdate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                        createBean.setTransactionDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
                    }
                });
                dialog.show();
            }
        });
        jig_linkmantel = (EditText) mBodyLayout.findViewById(R.id.jig_linkmantel);
        jig_linkmantel.setInputType(InputType.TYPE_CLASS_PHONE);
        jigAmt = (EditText) mBodyLayout.findViewById(R.id.jig_amt);
        jigContent = (JiuyiBigTextGroup) mBodyLayout.findViewById(R.id.jig_content);
        jig_import = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_import);
        if(operatorType.equals(ViewOperatorType.ADD)){
            jig_import.setText("一般");
            createBean.setImportantKey("general");
            createBean.setImportantValue("一般");
            jig_type.setText("新商机");
            createBean.setTypeKey("business_chance_type");
            createBean.setTypeValue("新商机");
        }
        jig_import.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerNewBusinessActivity.this);
                buidler.setTitle("重要程度");
                final String[] data;
                final List<DictBean> dictBeansList= DictUtils.getDictList("importance");
                if(dictBeansList!=null &&dictBeansList.size()>0) {
                    data = new String[dictBeansList.size()];
                    for (int i = 0; i < dictBeansList.size(); i++) {
                        data[i] = dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            jig_import.setText(data[which].trim());
                            createBean.setImportantKey(dictBeansList.get(which).getKey());
                            createBean.setImportantValue(dictBeansList.get(which).getValue());

                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });
        jig_bigtype = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_bigtype);
        jig_bigtype.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                if(mProductTypeSelectList==null){
                    mProductTypeSelectList=new ArrayList<>();
                }
                mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mProductTypeSelectList);
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"PRODUCTBIGTYPE");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 206);

            }
        });
        ahAttach = (JiuyiAttachment) mBodyLayout.findViewById(R.id.ah_attach);
        ahAttach.setAdapter();
        rvComplaintlist = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.rv_complaintlist);
        rvComplaintlist.setLayoutManager(new LinearLayoutManager(JiuyiCustomerNewBusinessActivity.this));
        rvComplaintlist.setHasFixedSize(true);
        rvComplaintlist.setItemAnimator(new DefaultItemAnimator());
        rvComplaintlist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiCustomerNewBusinessActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        rvComplaintlist.setSwipeMenuCreator(swipeMenuCreator);
        rvComplaintlist.setSwipeMenuItemClickListener(menuItemClickListener);
        competitorBehaviorList=new ArrayList<>();
        menuAdapter = new NewCompeteMutiAdapter(competitorBehaviorList);
        menuAdapter.setOnItemClickListener(onItemClickListener);
        rvComplaintlist.setAdapter(menuAdapter);
        rvNew = (RelativeLayout) mBodyLayout.findViewById(R.id.rv_new);
        rvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiNewBusinessDetailMutiActivity.class);
                intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.ADD);
                startActivityForResult(intent, 100);

            }
        });
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Rc.mBackfresh=true;
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
                    showDialog();
                    if(ahAttach.getMediaArrayList().size()>0) {
                        files = new File[ahAttach.getMediaArrayList().size()];
                        for (int i = 0; i < ahAttach.getMediaArrayList().size(); i++) {
                            if (ahAttach.getMediaArrayList().get(i).getPath() != null) {
                                File file1 = new File(ahAttach.getMediaArrayList().get(i).getPath());
                                files[i] = file1;
                            }

                        }
                        upload();
                    }else{
                        if(operatorType.equals(ViewOperatorType.ADD)){
                            createBean();
                        }else if(operatorType.equals(ViewOperatorType.EDIT)){
                            updateInfo();
                        }

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
            mTitle = "新建商机";
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
                SwipeMenuItem wechatItem = new SwipeMenuItem(JiuyiCustomerNewBusinessActivity.this)
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
                    DyBusinessBean.ContentBean.CompetitorBehavior contentBean=menuAdapter.mViewTypeBeanList.get(adapterPosition);
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
                DyBusinessBean.ContentBean.CompetitorBehavior contentBean=menuAdapter.mViewTypeBeanList.get(position);
                if(contentBean!=null){
                    currentPosition=position;
                    operType="MODIFY";
                    Intent intent = new Intent(getApplicationContext(), JiuyiNewBusinessDetailMutiActivity.class);
                    intent.putExtra(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                    intent.putExtra(JiuyiBundleKey.PARAM_COMMONBEAN, contentBean);
                    startActivityForResult(intent, 101);
                }
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((data == null || data.getExtras() == null)&& requestCode!=TAKE_PICTURE) {
            return;
        }
        Bundle bundle;
        switch (requestCode) {
            case 100:
                bundle = data.getExtras();
                competitorBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
//                competitorBean.setViewType(VIEW_TYPE_MENU_DELETE);

                if (competitorBean != null) {
                    if (competitorBehaviorList == null) {
                        competitorBehaviorList = new ArrayList<>();
                    }

                    competitorBehaviorList.add(competitorBean);
                    if (menuAdapter == null) {
                        menuAdapter = new NewCompeteMutiAdapter(competitorBehaviorList);
                        menuAdapter.setOnItemClickListener(onItemClickListener);
                        rvComplaintlist.setAdapter(menuAdapter);
                    } else {
                        menuAdapter.setmViewTypeBeanList(competitorBehaviorList);
                        menuAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case 101:
                bundle = data.getExtras();
                competitorBean = bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
                if (competitorBean != null) {
                    if (competitorBehaviorList != null && competitorBehaviorList.size() > 0 && currentPosition >= 0) {
                        competitorBehaviorList.set(currentPosition, competitorBean);
                        menuAdapter.setmViewTypeBeanList(competitorBehaviorList);
                        menuAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK) { //
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  // 检测sd是否可用
                        Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    if(!Func.IsStringEmpty(Rc.picVideoUrl)){
                        filePath=Rc.picVideoUrl;
                        Rc.picVideoUrl="";
                    }

                    //优化压缩图片
                    Uri uri = null;
                    File file1 = new File(filePath);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                    Media media=new Media();
                    media.setExtension(".jpg");
                    media.setMediaType(0);
                    media.setPath(newFile.getPath());
                    ahAttach.adapter.getMviewBeanList().add(media);
                    ahAttach.refreshAttach();
                }
                break;
            case SELECT_PICTURE:
                ArrayList<Media> selects = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                ahAttach.setMediaArrayList(selects);
                ahAttach.adapter.setMviewBeanList(selects);
                ahAttach.refreshAttach();
                break;
            case 200:
                bundle = data.getExtras();
                String customerName=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long customerID=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(customerID>0 && customerName!=null ){
                    jigOldclient.setText(customerName);
                    MemberBeanID memberBeanID=new MemberBeanID();
                    memberBeanID.setId(customerID);
                    memberBeanID.setOrgName(customerName);
                    createBean.setRegularCustomer(memberBeanID);
                }
                break;
            case 201:
                bundle = data.getExtras();
                String customerName2=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long customerID2=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(customerID2>0 && customerName2!=null ){
                    jigClient.setText(customerName2);
                    MemberBeanID memberBeanID=new MemberBeanID();
                    memberBeanID.setId(customerID2);
                    memberBeanID.setOrgName(customerName2);
                    createBean.setMember(memberBeanID);
                }
                break;
            case 204:
                bundle = data.getExtras();
                String customerName3=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long activityId=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(activityId>0 && customerName3!=null ){
                    jigActivity.setText(customerName3);
                    DyActivityBean.DyActivityBeanID activityBeanID=new DyActivityBean.DyActivityBeanID();
                    activityBeanID.setId(activityId);
                    createBean.setActivity(activityBeanID);
                }
                break;
            case 205:
                bundle = data.getExtras();
                String clueName=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long clueId=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(clueId>0 && clueName!=null ){
                    jigClue.setText(clueName);
                    DyClueBean.DyClueBeanID clueBeanID=new DyClueBean.DyClueBeanID();
                    clueBeanID.setId(clueId);
                    createBean.setClue(clueBeanID);
                }
                break;
            case 206:
                bundle = data.getExtras();
                mProductTypeSelectList=bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
                List<DyClueBean.ProductBigCategory> bigCategoryList=new ArrayList<>();
                String name="";
                if(mProductTypeSelectList!=null && mProductTypeSelectList.size()>0){
                    for(int i=0;i<mProductTypeSelectList.size();i++){
                        name+=mProductTypeSelectList.get(i).getName()+",";
                        DyClueBean.ProductBigCategory productBigCategory=new DyClueBean.ProductBigCategory();
                        productBigCategory.setId(mProductTypeSelectList.get(i).getId());
                        bigCategoryList.add(productBigCategory);
                    }
                }
                createBean.setMaterialTypes(bigCategoryList);
                jig_bigtype.setText(name.substring(0,name.length()-1));
                break;
            default:
                break;

        }
    }

    public boolean check(){
        if(Func.IsStringEmpty(jigTitle.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入标题！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jig_bigtype.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择产品大类！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jigClient.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(Func.IsStringEmpty(jigContent.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入内容！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(competitorBehaviorList==null ||competitorBehaviorList.size()==0){
            startDialog(DialogID.DialogDoNothing, "", "请输入友商内容！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;
    }
    public void updateInfo(){
        if(createBean==null){
            createBean =new DyBusinessBean.ContentBean();
            createBean.setId(billID);
        }
        createBean.setTitle(jigTitle.getText());
        createBean.setCustomerContact(jigLinkman.getText());
        createBean.setCustomerTel(jig_linkmantel.getText().toString());
        createBean.setContent(jigContent.getText());
        createBean.setCustomerJob(jig_duty.getText());
        createBean.setAmount(Double.parseDouble(jigAmt.getText()+""));
//         Map<String,Object> mapResult=null;
//        String field[]=new String[1];
//        field[0]="viewType";
//
//        mapResult= FileUtils.jsonObject2(createBean,field);
        JiuyiHttp.PUT("business-chance/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(com.common.GsonUtil.gson().toJson(createBean))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        if(data!=null){
                            startDialog(DialogID.DialogTrue, "", "更新成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }
                    }
                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    private void getDetailInfo(long id) {
        JiuyiHttp.GET("business-chance/detail/"+id)
                .tag("request_get_market-clue")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DyBusinessBean.ContentBean>() {
                    @Override
                    public void onSuccess(DyBusinessBean.ContentBean contentBean) {
                        if(contentBean!=null){
                            createBean=contentBean;
                            if(contentBean.getTitle()!=null){
                                jigTitle.setText(contentBean.getTitle());
                            }
                            if(contentBean.getResourceValue()!=null){
                                jigSource.setText(contentBean.getResourceValue());
                            }
                            if(contentBean.getResourceKey()!=null){
                                if(contentBean.getResourceKey().equals("customer_introduce")){
                                    jigOldclient.setVisibility(View.VISIBLE);
                                    if(contentBean.getRegularCustomer()!=null && contentBean.getRegularCustomer().getOrgName()!=null){
                                        jigOldclient.setText(contentBean.getRegularCustomer().getOrgName());
                                    }

                                }else{
                                    jigOldclient.setVisibility(View.GONE);
                                }
                            }
                            if(contentBean.getTypeValue()!=null){
                                jig_type.setText(contentBean.getTypeValue());
                            }

                            if(contentBean.getActivity()!=null && contentBean.getActivity().getTitle()!=null ){
                                jigActivity.setText(contentBean.getActivity().getTitle());
                            }
                            if(contentBean.getClue()!=null && contentBean.getClue().getTitle()!=null ){
                                jigClue.setText(contentBean.getClue().getTitle());
                            }


                            if(contentBean.getMember()!=null && contentBean.getMember().getOrgName()!=null){
                                jigClient.setText(contentBean.getMember().getOrgName());
                            }
                            if(contentBean.getCustomerContact()!=null){
                                jigLinkman.setText(contentBean.getCustomerContact());
                            }
                            if(contentBean.getCustomerJob()!=null){
                                jig_duty.setText(contentBean.getCustomerJob());
                            }
                            if(contentBean.getCustomerTel()!=null){
                                jig_linkmantel.setText(contentBean.getCustomerTel());
                            }
                            jigAmt.setText(contentBean.getAmount()+"");

                            if(contentBean.getTransactionDate()!=null){
                                jigDealdate.setText(contentBean.getTransactionDate());
                            }
                            if(contentBean.getImportantValue()!=null){
                                jig_import.setText(contentBean.getImportantValue());
                            }
                            if(contentBean.getMaterialTypes()!=null && contentBean.getMaterialTypes().size()>0){
                                String name="";
                                for(int i=0;i<contentBean.getMaterialTypes().size();i++){
                                    name+=contentBean.getMaterialTypes().get(i).getName()+",";
                                }
                                jig_bigtype.setText(name.substring(0,name.length()-1));
                            }
                            if(contentBean.getContent()!=null){
                                jigContent.setText(contentBean.getContent());
                            }
                            if(contentBean.getCompetitorBehaviorset()!=null){
                                menuAdapter.setmViewTypeBeanList(contentBean.getCompetitorBehaviorset());
                                menuAdapter.notifyDataSetChanged();
                            }
                            if(contentBean.getAttachmentList()!=null && contentBean.getAttachmentList().size()>0){
                                attachmentsBeanslist=contentBean.getAttachmentList();
                                ArrayList mediaArrayList=new ArrayList<>();
                                for(int i=0;i<contentBean.getAttachmentList().size();i++){

                                    AttachmentBean attachmentBean = contentBean.getAttachmentList().get(i);//把JSON字符串转为对象
                                    Media media=new Media();
                                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                                    media.setExtension(".jpg");
                                    media.setMediaType(0);
                                    mediaArrayList.add(media);
                                }
                                ahAttach.setMediaArrayList(mediaArrayList);
                                ahAttach.adapter.setMviewBeanList(mediaArrayList);
                                ahAttach.adapter.notifyDataSetChanged();
                                ahAttach.refreshAttach();
                            }


                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressLoadingDialog!=null){
                            progressLoadingDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }
    public void createBean(){

        if(!Func.IsStringEmpty(jigTitle.getText().toString().trim())){
            createBean.setTitle(jigTitle.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigContent.getText().toString().trim())){
            createBean.setContent(jigContent.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigLinkman.getText().toString().trim())){
            createBean.setCustomerContact(jigLinkman.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jig_duty.getText().toString().trim())){
            createBean.setCustomerJob(jig_duty.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jig_linkmantel.getText().toString().trim())){
            createBean.setCustomerTel(jig_linkmantel.getText().toString().trim());
        }
        if(!Func.IsStringEmpty(jigAmt.getText().toString().trim())){
            createBean.setAmount(Double.parseDouble(jigAmt.getText().toString().trim()));
        }
        if(competitorBehaviorList!=null && competitorBehaviorList.size()>0){
            createBean.setCompetitorBehaviorset(competitorBehaviorList);
        }

        submit();
    }
    public void submit(){
        String field[]=new String[2];
        field[0]="id";
        field[1]="serialVersionUID";
        Map<String,Object> mapResult=FileUtils.jsonObject2(createBean,field);
        JiuyiHttp.POST("business-chance/create")
                .setJson(GsonUtil.gson().toJson(mapResult))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }
    private void upload()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.UPLOAD("file/upload-multi")
                        .tag("upload-multi")
                        .addFiles2("file",files)
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<ArrayList<AttachmentBean>>() {
                            @Override
                            public void onSuccess(ArrayList<AttachmentBean> data) {
                                if(data!=null && data.size()>0){
                                    attachmentsBeanslist=data;
                                    if(operatorType.equals(ViewOperatorType.ADD)){
                                        createBean.setAttachmentList(attachmentsBeanslist);
                                        createBean();
                                    }else if(operatorType.equals(ViewOperatorType.EDIT)){
                                        for(int i=0;i<attachmentsBeanslist.size();i++){
                                            attachmentsEditBeanslist.add(attachmentsBeanslist.get(i));
                                        }
                                        createBean.setAttachmentList(attachmentsEditBeanslist);
                                        updateInfo();
                                    }

                                }else{
                                    if(progressDialog!=null){
                                        progressDialog.dismiss();
                                    }
                                }

                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                if(progressDialog!=null){
                                    progressDialog.dismiss();
                                }
                                startDialog(DialogID.DialogTrue, "", "附件上传失败！", JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });
            }
        };

        thread.start();
    }



}
