package customer.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bobomee.android.mentions.edit.MentionEditText;
import com.common.GsonUtil;
import com.control.permission.JiuyiHiPermissionUtil;
import com.control.shared.JiuyiPasswordLockShared;
import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.dalong.recordlib.RecordVideoActivity;
import com.wanglicrm.android.R;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.model.DictBean;
import com.jiuyi.tools.DictUtils;
import com.nanchen.compresshelper.CompressHelper;
import com.tencent.qcloud.sdk.Constant;


import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.OperatorNodeBean;
import commonlyused.db.DbHelper;
import commonlyused.db.OperatorNodeBeanDao;
import commonlyused.treelist.Node;
import customer.Utils.BitmapUtils;
import customer.Utils.CommonUtils;
import customer.Utils.FileUtils;
import customer.Utils.ViewOperatorType;
import customer.adapter.CustomerIntelligenceAdapter;
import customer.adapter.OrgDeptSimpleTreeAdapter;
import customer.entity.AttachmentBean;
import customer.entity.ImageBean;
import customer.entity.IntelligenceDetailCreateBean;
import customer.entity.MarketIntelligenceBean;
import customer.entity.Media;
import customer.entity.MemberBeanID;
import customer.entity.MemberOperatorBean;
import customer.entity.MultiInputFileBean;
import customer.entity.PersonSelectBean;
import customer.entity.UpdateAssistantBean;
import customer.entity.User;
import customer.entity.VisibilityScropeBean;
import customer.entity.VisibleTypeBean;
import customer.entity.VisiblityScropeEditBean;
import customer.entity.VisitActivityListBean;
import customer.entity.VisitIntelligenceBean;
import customer.listener.PickerConfig;
import customer.view.JiuyiIntelligenceBar;
import customer.view.SelectPicPopupWindow;
import customer.view.SelectVideoPopupWindow;
import customer.view.SelectVoicePopupWindow;
import dynamic.entity.DyInteligenceBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerInfomationActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 情报录入界面
 *****************************************************************
 */
public class JiuyiCustomerInfomationActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private String operatorType,sTitle,s_returnvalue;
    private  long customerid=-1;
    private ImageView   iv_back;
    private TextView tvNew;
    private RecyclerView rvInfoDetailList;
    private List<MarketIntelligenceBean.IntelligenceItemSetBean> newBeanList;
    protected String filePath;

    private List<MarketIntelligenceBean.IntelligenceItemSetBean> list = new ArrayList<MarketIntelligenceBean.IntelligenceItemSetBean>();
    private CheckBox cbSelectAll;
    private ImageView ivDelete;
    private boolean isSelectAll = false;
    private int index = 0;
    protected SelectVideoPopupWindow menuVideoWindow;
    protected SelectPicPopupWindow menuWindow;
    protected SelectVoicePopupWindow menuVoiceWindow;
    private int currentPos=-1;

    private CustomerIntelligenceAdapter customerIntelligenceAdapter;
    String videoPath="",videoName="";
    private static final int PERSON_CODE = 800;
    protected static final int TAKE_PICTURE = 1000;
    protected static final int SELECT_PICTURE = 1500;
    protected static final int TAKE_Video = 2000;
    protected static final int SELECT_Video = 2500;
    protected static final int TAKE_Voice = 3000;
    protected static final int TAKE_Colleague = 4000;

    private VisitIntelligenceBean visitIntelligenceBean;
    private JiuyiItemGroup ig_infomationtype,ig_linkcustomer,ig_businesstype,ig_visilbity,ig_person;
    private long billID;
    private IntelligenceDetailCreateBean intelligenceDetailCreateBean;
    private VisitActivityListBean visitActivityListBean;
    private  List<DictBean> dictInfoBeansList,dictBussinessBeansList,dictIntelligenceInfoBeanList,dictIntelligenceTypeBeanList;
    private int pos=-1;
    private ArrayList<IntelligenceDetailCreateBean.IntelligenceItemSetBean> intelligenceDetailItemBeans;
    private RelativeLayout rv_addtitle;
    private Map<String,Object> mapResult=null;
    private  DyInteligenceBean.ContentBean dyInteligenceBean;
    private ArrayList<AttachmentBean> attachmentsBeanslist,attachmentsEditBeanslist,attachmentsEditOldBeanslist;
    private  File[] files;
    private String extData="";
    private List<File> fileList;
    private int currentAttachmentPos=-1;
    private List<MultiInputFileBean> inputFileList;
    private List<String> extDataList;
    private Boolean needUpload=false;
    private String CustomerName;
    private  ArrayList<AttachmentBean> tempAttachmentlist;
    LinearLayoutManager linearLayoutManager;
    private List<VisibilityScropeBean> visibilityScropeBeanList=new ArrayList<>();
    private String visibleType="";
    private ListView lv_companyaddresslist;
    private List<Node> mDatas = new ArrayList<>();
    private OrgDeptSimpleTreeAdapter mAdapter;
    private List<PersonSelectBean> mPersonBeanSelectList;
    private ArrayList<UpdateAssistantBean> inchargeSelectList;




    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customerinfomation_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        customerid = mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        CustomerName = mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);

        operatorType=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        sTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }
        if(Func.IsStringEmpty(operatorType)){
            operatorType="";
        }
        initView();
        if(operatorType.equals(ViewOperatorType.ADD)){
            intelligenceDetailCreateBean=new IntelligenceDetailCreateBean();
            visitActivityListBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2);
            if(visitActivityListBean!=null){
                billID=visitActivityListBean.getId();
            }
        }else if(operatorType.equals(ViewOperatorType.EDIT)){//从沟通记录跳转过来
            visitIntelligenceBean=mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        }else if(operatorType.equals(ViewOperatorType.SINGLEEDIT)){//从动态跳转
            billID=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
            currentPos=0;
            if(billID>0){
                getDetailInfo(billID);
                getVisibleRange(billID);
            }
        }else if(operatorType.equals(ViewOperatorType.SINGLEADD)){//直接新增
            intelligenceDetailCreateBean=new IntelligenceDetailCreateBean();
        }
        setTitle();

        if(operatorType.equals(ViewOperatorType.SINGLEADD)){//直接新增
            dictInfoBeansList= DictUtils.getDictList("intelligence_big_category");
            if(dictInfoBeansList!=null && dictInfoBeansList.size()>0){
                ig_infomationtype.setText(dictInfoBeansList.get(0).getValue());
                intelligenceDetailCreateBean.setBigCategoryKey(dictInfoBeansList.get(0).getKey());
                intelligenceDetailCreateBean.setBigCategoryValue(dictInfoBeansList.get(0).getValue());
                dictBussinessBeansList= DictUtils.getDictLikeList("intelligence_business_type","%"+dictInfoBeansList.get(0).getKey()+"%");
                if(dictBussinessBeansList!=null && dictBussinessBeansList.size()>0){
                    ig_businesstype.setText(dictBussinessBeansList.get(0).getValue());
                    intelligenceDetailCreateBean.setBusinessTypeKey(dictBussinessBeansList.get(0).getKey());
                    intelligenceDetailCreateBean.setBusinessTypeValue(dictBussinessBeansList.get(0).getValue());
                }
            }
        }

        if(operatorType.equals(ViewOperatorType.ADD) && visitActivityListBean!=null){
            ig_linkcustomer.setText(visitActivityListBean.getMember().getOrgName());
            intelligenceDetailCreateBean.setMember(visitActivityListBean.getMember());
        }else if(operatorType.equals(ViewOperatorType.EDIT) && visitIntelligenceBean!=null){
            ig_linkcustomer.setText(visitIntelligenceBean.getMember().getOrgName());
        }else if(operatorType.equals(ViewOperatorType.SINGLEEDIT) && dyInteligenceBean!=null){
            if(dyInteligenceBean.getMember()!=null){
                ig_linkcustomer.setText(dyInteligenceBean.getMember().getOrgName());
            }

        }
        if(operatorType.equals(ViewOperatorType.SINGLEADD) && customerid>0 && CustomerName!=null){
            ig_linkcustomer.setText(CustomerName);
            MemberBeanID memberBeanID=new MemberBeanID();
            memberBeanID.setOrgName(CustomerName);
            memberBeanID.setId(customerid);
            intelligenceDetailCreateBean.setMember(memberBeanID);
        }

        if(operatorType.equals(ViewOperatorType.EDIT) && visitIntelligenceBean!=null){
            if(visitIntelligenceBean.getIntelligence().getBigCategoryValue()!=null){
                ig_infomationtype.setText(visitIntelligenceBean.getIntelligence().getBigCategoryValue());
                ig_infomationtype.setItemOnClickListener(null);
            }
            if(visitIntelligenceBean.getMember()!=null){
                ig_linkcustomer.setText(visitIntelligenceBean.getMember().getOrgName());
                ig_linkcustomer.setItemOnClickListener(null);
            }
            if(visitIntelligenceBean.getIntelligence().getBusinessTypeValue()!=null){
                ig_businesstype.setText(visitIntelligenceBean.getIntelligence().getBusinessTypeValue());
                ig_businesstype.setItemOnClickListener(null);
            }
            rv_addtitle.setVisibility(View.GONE);
            newBeanList=new ArrayList<>();
            MarketIntelligenceBean.IntelligenceItemSetBean contentBean=new MarketIntelligenceBean.IntelligenceItemSetBean();
            if(visitIntelligenceBean.getContent()!=null){
                contentBean.setContent(visitIntelligenceBean.getContent());
            }
            if(visitIntelligenceBean.getIntelligenceInfoKey()!=null){
                contentBean.setIntelligenceInfoKey(visitIntelligenceBean.getIntelligenceInfoKey());
            }
            if(visitIntelligenceBean.getIntelligenceInfoValue()!=null){
                contentBean.setIntelligenceInfoValue(visitIntelligenceBean.getIntelligenceInfoValue());
            }
            if(visitIntelligenceBean.getIntelligenceTypeKey()!=null){
                contentBean.setIntelligenceTypeKey(visitIntelligenceBean.getIntelligenceTypeKey());
            }
            if(visitIntelligenceBean.getIntelligenceTypeValue()!=null){
                contentBean.setIntelligenceTypeValue(visitIntelligenceBean.getIntelligenceTypeValue());
            }
            if(visitIntelligenceBean.getAttachmentList()!=null){
                contentBean.setAttachmentList(visitIntelligenceBean.getAttachmentList());
            }

            newBeanList.add(contentBean);
            customerIntelligenceAdapter.add(newBeanList);
            customerIntelligenceAdapter.notifyDataSetChanged();
        }else if(operatorType.equals(ViewOperatorType.ADD)||operatorType.equals(ViewOperatorType.SINGLEADD)){
            tvNew.performClick();
        }


    }
    public void initView(){
        iv_back = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });

        ig_infomationtype = (JiuyiItemGroup) mBodyLayout.findViewById(Res.getViewID(null, "ig_infomationtype"));
        ig_infomationtype.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerInfomationActivity.this);
                buidler.setTitle("情报大类");
                final String[] data;
                dictInfoBeansList= DictUtils.getDictList("intelligence_big_category");
                if(dictInfoBeansList!=null &&dictInfoBeansList.size()>0) {
                    data = new String[dictInfoBeansList.size()];
                    for (int i = 0; i < dictInfoBeansList.size(); i++) {
                        data[i] = dictInfoBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pos=which;
                            if(dictInfoBeansList.get(which).getKey().equals("industry_policy")||dictInfoBeansList.get(which).getKey().equals("technology_trend")){
                                ig_linkcustomer.setVisibility(View.GONE);
                                ig_linkcustomer.setText("");
                                intelligenceDetailCreateBean.setMember(null);
                            }else {
                                ig_linkcustomer.setVisibility(View.VISIBLE);
                            }
                            ig_infomationtype.setText(data[which].trim());
                            intelligenceDetailCreateBean.setBigCategoryKey(dictInfoBeansList.get(which).getKey());
                            intelligenceDetailCreateBean.setBigCategoryValue(dictInfoBeansList.get(which).getValue());

                            dictBussinessBeansList= DictUtils.getDictLikeList("intelligence_business_type","%"+dictInfoBeansList.get(which).getKey()+"%");
                            if(dictBussinessBeansList!=null && dictBussinessBeansList.size()>0){
                                ig_businesstype.setText(dictBussinessBeansList.get(0).getValue());
                                intelligenceDetailCreateBean.setBusinessTypeKey(dictBussinessBeansList.get(0).getKey());
                                intelligenceDetailCreateBean.setBusinessTypeValue(dictBussinessBeansList.get(0).getValue());

                                if(customerIntelligenceAdapter !=null && customerIntelligenceAdapter.jiuyiIntelligenceBar!=null ){
                                    customerIntelligenceAdapter.jiuyiIntelligenceBar.setBusinessType(dictBussinessBeansList.get(0).getKey());
                                }
                                dictIntelligenceInfoBeanList = DictUtils.getDictLikeList("intelligence_info_type","%"+intelligenceDetailCreateBean.getBusinessTypeKey()+"%");
                                for(int i = 0; i< customerIntelligenceAdapter.getData().size(); i++){
                                    if(dictIntelligenceInfoBeanList!=null && dictIntelligenceInfoBeanList.size()>0){
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceInfoKey(dictIntelligenceInfoBeanList.get(0).getKey());
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceInfoValue(dictIntelligenceInfoBeanList.get(0).getValue());
                                        customerIntelligenceAdapter.getData().get(i).setDictBeansList(dictIntelligenceInfoBeanList);
                                        dictIntelligenceTypeBeanList = DictUtils.getDictLikeList("intelligence_type","%"+dictIntelligenceInfoBeanList.get(0).getKey()+"%");

                                        if(dictIntelligenceTypeBeanList!=null && dictIntelligenceTypeBeanList.size()>0){
                                            customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeKey(dictIntelligenceTypeBeanList.get(0).getKey());
                                            customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeValue(dictIntelligenceTypeBeanList.get(0).getValue());
                                            customerIntelligenceAdapter.getData().get(i).setDictBeansList2(dictIntelligenceTypeBeanList);
                                        }else{
                                            customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeKey(null);
                                            customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeValue(null);
                                            customerIntelligenceAdapter.getData().get(i).setDictBeansList2(null);
                                        }
                                    }else {
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceInfoKey(null);
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceInfoValue(null);
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeKey(null);
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeValue(null);
                                        customerIntelligenceAdapter.getData().get(i).setDictBeansList(null);
                                        customerIntelligenceAdapter.getData().get(i).setDictBeansList2(null);
                                    }
                                }


                                customerIntelligenceAdapter.notifyDataSetChanged();
                            }

                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }
            }
        });



        ig_businesstype = (JiuyiItemGroup) mBodyLayout.findViewById(Res.getViewID(null, "ig_businesstype"));
        ig_businesstype.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerInfomationActivity.this);
                buidler.setTitle("业务类型");
                final String[] data;
                dictBussinessBeansList= DictUtils.getDictLikeList("intelligence_business_type","%"+intelligenceDetailCreateBean.getBigCategoryKey()+"%");
                if(dictBussinessBeansList!=null &&dictBussinessBeansList.size()>0) {
                    data = new String[dictBussinessBeansList.size()];
                    for (int i = 0; i < dictBussinessBeansList.size(); i++) {
                        data[i] = dictBussinessBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ig_businesstype.setText(data[which].trim());
                            intelligenceDetailCreateBean.setBusinessTypeKey(dictBussinessBeansList.get(which).getKey());
                            intelligenceDetailCreateBean.setBusinessTypeValue(dictBussinessBeansList.get(which).getValue());
                            if(customerIntelligenceAdapter !=null && customerIntelligenceAdapter.jiuyiIntelligenceBar!=null ){
                                customerIntelligenceAdapter.jiuyiIntelligenceBar.setBusinessType(dictBussinessBeansList.get(which).getKey());
                            }
                            dictIntelligenceInfoBeanList = DictUtils.getDictLikeList("intelligence_info_type","%"+intelligenceDetailCreateBean.getBusinessTypeKey()+"%");
                            for(int i = 0; i< customerIntelligenceAdapter.getData().size(); i++){
                                if(dictIntelligenceInfoBeanList!=null && dictIntelligenceInfoBeanList.size()>0){
                                    customerIntelligenceAdapter.getData().get(i).setIntelligenceInfoKey(dictIntelligenceInfoBeanList.get(0).getKey());
                                    customerIntelligenceAdapter.getData().get(i).setIntelligenceInfoValue(dictIntelligenceInfoBeanList.get(0).getValue());
                                    customerIntelligenceAdapter.getData().get(i).setDictBeansList(dictIntelligenceInfoBeanList);
                                    dictIntelligenceTypeBeanList = DictUtils.getDictLikeList("intelligence_type","%"+dictIntelligenceInfoBeanList.get(0).getKey()+"%");
                                    if(dictIntelligenceTypeBeanList!=null && dictIntelligenceTypeBeanList.size()>0){
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeKey(dictIntelligenceTypeBeanList.get(0).getKey());
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeValue(dictIntelligenceTypeBeanList.get(0).getValue());
                                        customerIntelligenceAdapter.getData().get(i).setDictBeansList2(dictIntelligenceTypeBeanList);
                                    }else{
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeKey(null);
                                        customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeValue(null);
                                        customerIntelligenceAdapter.getData().get(i).setDictBeansList2(null);
                                    }
                                }else {
                                    customerIntelligenceAdapter.getData().get(i).setIntelligenceInfoKey(null);
                                    customerIntelligenceAdapter.getData().get(i).setIntelligenceInfoValue(null);
                                    customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeKey(null);
                                    customerIntelligenceAdapter.getData().get(i).setIntelligenceTypeValue(null);
                                    customerIntelligenceAdapter.getData().get(i).setDictBeansList(null);
                                    customerIntelligenceAdapter.getData().get(i).setDictBeansList2(null);
                                }
                            }
                            customerIntelligenceAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }

            }
        });
        ig_visilbity = (JiuyiItemGroup) mBodyLayout.findViewById(Res.getViewID(null, "ig_visilbity"));
        ig_visilbity.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                if(ig_linkcustomer.getVisibility()==View.VISIBLE && Func.IsStringEmpty(ig_linkcustomer.getText())){
                    startDialog(DialogID.DialogDoNothing, "", "请先选关联对象！", JiuyiDialogBase.Dialog_Type_Yes, null);
                    return;
                }
                showLoadingDialog();
                getVisibleTypeList();
            }
        });

        ig_person = (JiuyiItemGroup) mBodyLayout.findViewById(Res.getViewID(null, "ig_person"));
        ig_person.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                if(visibleType.equals("FRSRAR")){
                    if(intelligenceDetailCreateBean!=null && intelligenceDetailCreateBean.getMember()!=null){
                        getMemberCenterInfo(intelligenceDetailCreateBean.getMember().getId());
                    }
                    if(dyInteligenceBean!=null && dyInteligenceBean.getMember()!=null && operatorType.equals(ViewOperatorType.SINGLEEDIT)){
                        getMemberCenterInfo(dyInteligenceBean.getMember().getId());
                    }

                }else if(visibleType.equals("SOMEONE")){
                    Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                    Bundle mBundle = new Bundle();
                    if(mPersonBeanSelectList!=null && mPersonBeanSelectList.size()>0){
                        mBundle.putParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY, (ArrayList<? extends Parcelable>) mPersonBeanSelectList);
                    }
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Person");
                    intent.putExtras(mBundle);
                    startActivityForResult(intent, PERSON_CODE);
                }
            }
        });
        lv_companyaddresslist= (ListView) mBodyLayout.findViewById(R.id.lv_companyaddresslist);
        ig_linkcustomer = (JiuyiItemGroup) mBodyLayout.findViewById(Res.getViewID(null, "ig_linkcustomer"));
        ig_linkcustomer.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_SPECIALCONDITION,"001");
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 200);

            }
        });

        rv_addtitle = (RelativeLayout) mBodyLayout.findViewById(Res.getViewID(null, "rv_addtitle"));

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(customerIntelligenceAdapter.getData()==null || customerIntelligenceAdapter.getData().size()<=0 ){
                        Toast.makeText(JiuyiCustomerInfomationActivity.this, "请填写情报明细！", Toast.LENGTH_SHORT).show();
                        return;
                    }
//                    if(Func.IsStringEmpty(ig_visilbity.getText())){
//                        Toast.makeText(JiuyiCustomerInfomationActivity.this, "请选择可见范围！", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                    if(visibilityScropeBeanList==null){
                        visibilityScropeBeanList=new ArrayList<>();
                    }
                    if(visibleType.equals("PUBLIC")){
                        VisibilityScropeBean visibilityScropeBean=new VisibilityScropeBean();
                        visibilityScropeBean.setVisibleType(visibleType);
                        visibilityScropeBeanList.add(visibilityScropeBean);
                    }else if(visibleType.equals("DEPARTMENT")){
                        if(mAdapter.getSelectIDs()!=null && mAdapter.getSelectIDs().size()>0){
                            for(int i=0;i<mAdapter.getSelectIDs().size();i++){
                                VisibilityScropeBean visibilityScropeBean=new VisibilityScropeBean();
                                visibilityScropeBean.setVisibleType(visibleType);
                                visibilityScropeBean.setDepartmentId(mAdapter.getSelectIDs().get(i));
                                visibilityScropeBeanList.add(visibilityScropeBean);
                            }
                        }else{
                            Toast.makeText(JiuyiCustomerInfomationActivity.this, "请选择部门！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }else if(visibleType.equals("FRSRAR")){
                        if(visibilityScropeBeanList==null || visibilityScropeBeanList.size()==0){
                            VisibilityScropeBean visibilityScropeBean=new VisibilityScropeBean();
                            visibilityScropeBean.setVisibleType(visibleType);
                            visibilityScropeBeanList.add(visibilityScropeBean);
                        }

                    }else if(visibleType.equals("SOMEONE")){
                        if(inchargeSelectList!=null && inchargeSelectList.size()>0){
                            for(int i=0;i<inchargeSelectList.size();i++){
                                VisibilityScropeBean visibilityScropeBean=new VisibilityScropeBean();
                                visibilityScropeBean.setVisibleType(visibleType);
                                visibilityScropeBean.setOperatorId(inchargeSelectList.get(i).getId());
                                visibilityScropeBeanList.add(visibilityScropeBean);
                            }
                        }else{
                            Toast.makeText(JiuyiCustomerInfomationActivity.this, "请选择可见人员！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }




                    showDialog();
                    extData="";
                    if(operatorType.equals(ViewOperatorType.SINGLEEDIT)){
                        //只能修改一个情报详情，新增可以录入多个情报明细
                        if(customerIntelligenceAdapter.getData()!=null && customerIntelligenceAdapter.getData().size()>0){
                            MarketIntelligenceBean.IntelligenceItemSetBean intelligenceItemSetBean = customerIntelligenceAdapter.getData().get(0);
                            dyInteligenceBean.setIntelligenceInfoKey(intelligenceItemSetBean.getIntelligenceInfoKey());
                            dyInteligenceBean.setIntelligenceInfoValue(intelligenceItemSetBean.getIntelligenceInfoValue());
                            dyInteligenceBean.setIntelligenceTypeKey(intelligenceItemSetBean.getIntelligenceTypeKey());
                            dyInteligenceBean.setIntelligenceTypeValue(intelligenceItemSetBean.getIntelligenceTypeValue());
                            View childAt = linearLayoutManager.findViewByPosition((0));
                            if (childAt!=null){
                                MentionEditText edtContent=childAt.findViewById(R.id.content_edt);
                                dyInteligenceBean.setContent(edtContent.getFormatCharSequence().toString());
                            }

                            dyInteligenceBean.setVisibleRangeList(visibilityScropeBeanList);
                            attachmentsEditBeanslist=new ArrayList<>();
                            fileList=new ArrayList<>();
                            if(customerIntelligenceAdapter.getData().get(0).getAttachmentList()!=null && customerIntelligenceAdapter.getData().get(0).getAttachmentList().size()>0){
                                for(int i = 0; i< customerIntelligenceAdapter.getData().get(0).getAttachmentList().size(); i++){
                                    AttachmentBean media= customerIntelligenceAdapter.getData().get(0).getAttachmentList().get(i);
                                    if (Func.IsStringEmpty(media.getQiniuKey())) {
                                        File file1 = new File(media.getUrl());
                                        fileList.add(file1);
                                        if(media.getFileType().toLowerCase().equals("mp3")){
                                            extData+=media.getExtData()+",";
                                        }else{
                                            extData+=",";
                                        }
                                        needUpload=true;
                                    }else{
                                        if(!Func.IsStringEmpty(media.getQiniuKey()) && attachmentsEditOldBeanslist!=null && attachmentsEditOldBeanslist.size()>0 ){
                                            for(int j=0;j<attachmentsEditOldBeanslist.size();j++){
                                                if(!Func.IsStringEmpty(attachmentsEditOldBeanslist.get(j).getQiniuKey()) && media.getQiniuKey().equals(attachmentsEditOldBeanslist.get(j).getQiniuKey())){
                                                    attachmentsEditBeanslist.add(attachmentsEditOldBeanslist.get(j));
                                                }
                                            }
                                        }
                                    }
                                }
                                if(needUpload){
                                    if(fileList!=null && fileList.size()>0){
                                        files=new File[fileList.size()];
                                        for(int i=0;i<fileList.size();i++){
                                            files[i]=fileList.get(i);
                                        }
                                    }
                                    upload();
                                }else{
                                    dyInteligenceBean.setAttachmentList(attachmentsEditBeanslist);
                                    UpdateBeanFromDy();
                                }


                            }else{
                                UpdateBeanFromDy();
                            }
                        }

                    }else if(operatorType.equals(ViewOperatorType.EDIT)){
                        if(customerIntelligenceAdapter.getData()!=null && customerIntelligenceAdapter.getData().size()>0){
                            for(int i = 0; i< customerIntelligenceAdapter.getData().size(); i++){
                                MarketIntelligenceBean.IntelligenceItemSetBean intelligenceItemSetBean = customerIntelligenceAdapter.getData().get(i);
                                visitIntelligenceBean.setIntelligenceInfoKey(intelligenceItemSetBean.getIntelligenceInfoKey());
                                visitIntelligenceBean.setIntelligenceInfoValue(intelligenceItemSetBean.getIntelligenceInfoValue());
                                visitIntelligenceBean.setIntelligenceTypeKey(intelligenceItemSetBean.getIntelligenceTypeKey());
                                visitIntelligenceBean.setIntelligenceTypeValue(intelligenceItemSetBean.getIntelligenceTypeValue());
                                visitIntelligenceBean.setAttachmentList(intelligenceItemSetBean.getAttachmentList());
                                visitIntelligenceBean.setContent(intelligenceItemSetBean.getContent());
                            }

                        }
                        String field[]=new String[2];
                        field[0]="viewType";
                        field[1]="serialVersionUID";

                        mapResult=FileUtils.jsonObject2(visitIntelligenceBean,field);
                        UpdateBean();
                    }else if(operatorType.equals(ViewOperatorType.ADD)|| operatorType.equals(ViewOperatorType.SINGLEADD)){
                        intelligenceDetailCreateBean.setVisibleRangeList(visibilityScropeBeanList);
                        intelligenceDetailItemBeans=new ArrayList<>();
                        Boolean needUpload=false;
                        if(customerIntelligenceAdapter.getData()!=null && customerIntelligenceAdapter.getData().size()>0){
                            inputFileList=new ArrayList<>();
                            extDataList=new ArrayList<>();
                            for(int i = 0; i< customerIntelligenceAdapter.getData().size(); i++){
                                MarketIntelligenceBean.IntelligenceItemSetBean intelligenceItemSetBean = customerIntelligenceAdapter.getData().get(i);
                                if(intelligenceItemSetBean.getAttachmentList()!=null && intelligenceItemSetBean.getAttachmentList().size()>0){
                                    needUpload=true;
                                    files = new File[intelligenceItemSetBean.getAttachmentList().size()];
                                    extData="";
                                    for (int j = 0; j < intelligenceItemSetBean.getAttachmentList().size(); j++) {
                                        if (intelligenceItemSetBean.getAttachmentList().get(j).getUrl()!= null) {
                                            File file1 = new File(intelligenceItemSetBean.getAttachmentList().get(j).getUrl());
                                            files[j] = file1;
                                        }
                                        if(intelligenceItemSetBean.getAttachmentList().get(j).getFileType()!=null
                                                && intelligenceItemSetBean.getAttachmentList().get(j).getFileType().toString().toLowerCase().equals("mp3")){
                                            extData+=intelligenceItemSetBean.getAttachmentList().get(j).getExtData()+",";
                                        }else{
                                            extData+=",";
                                        }
                                    }
                                    MultiInputFileBean bean=new MultiInputFileBean();
                                    bean.setPos(i);
                                    bean.setFiles(files);
                                    inputFileList.add(bean);
                                    extDataList.add(extData);
                                }

                                IntelligenceDetailCreateBean.IntelligenceItemSetBean intelligenceDetailItemBean=new  IntelligenceDetailCreateBean.IntelligenceItemSetBean();
                                intelligenceDetailItemBean.setIntelligenceInfoKey(intelligenceItemSetBean.getIntelligenceInfoKey());
                                intelligenceDetailItemBean.setIntelligenceInfoValue(intelligenceItemSetBean.getIntelligenceInfoValue());
                                intelligenceDetailItemBean.setIntelligenceTypeKey(intelligenceItemSetBean.getIntelligenceTypeKey());
                                intelligenceDetailItemBean.setIntelligenceTypeValue(intelligenceItemSetBean.getIntelligenceTypeValue());
                                intelligenceDetailItemBean.setContent(intelligenceItemSetBean.getOrginalContent());
                                intelligenceDetailItemBeans.add(intelligenceDetailItemBean);

                            }
                            intelligenceDetailCreateBean.setIntelligenceItemSet(intelligenceDetailItemBeans);
                            if(!needUpload){
                                if(operatorType.equals(ViewOperatorType.ADD)){
                                    submit();
                                }else if(operatorType.equals(ViewOperatorType.SINGLEADD)){
                                    singleSubmit();
                                }
                            }else{
                                if(inputFileList!=null && inputFileList.size()>0){
                                    currentAttachmentPos=0;
                                    upload(inputFileList.get(currentAttachmentPos));
                                }
                            }
                        }

                    }
                }
            });

        }
        cbSelectAll=(CheckBox) mBodyLayout.findViewById(R.id.cb_selectall);
        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    for (int i = 0, j = customerIntelligenceAdapter.getItemCount(); i < j; i++) {
                        customerIntelligenceAdapter.getData().get(i).setSelect(true);
                    }
                    customerIntelligenceAdapter.notifyDataSetChanged();
                }else{
                    for (int i = 0, j = customerIntelligenceAdapter.getItemCount(); i < j; i++) {
                        customerIntelligenceAdapter.getData().get(i).setSelect(false);
                    }
                    customerIntelligenceAdapter.notifyDataSetChanged();
                }
            }
        });
        ivDelete=(ImageView) mBodyLayout.findViewById(R.id.iv_delete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canDelete=false;
                for (int i = customerIntelligenceAdapter.getData().size(), j = 0; i > j; i--) {
                    MarketIntelligenceBean.IntelligenceItemSetBean myLive = customerIntelligenceAdapter.getData().get(i-1);
                    if (myLive.isSelect()) {
                        canDelete=true;
                    }
                }
                if(canDelete){
                    startDialog(DialogID.DialogDeleteTrue, "", "确认删除？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                }else{
                    startDialog(DialogID.DialogDoNothing, "", "请先选中要删的记录？", JiuyiDialogBase.Dialog_Type_No);
                }

            }
        });
        rvInfoDetailList=(RecyclerView)mBodyLayout.findViewById(R.id.rv_infodetaillist);
        initRecycle();
        tvNew = (TextView) mBodyLayout.findViewById(R.id.tv_new);
        tvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newBeanList=new ArrayList<>();
                MarketIntelligenceBean.IntelligenceItemSetBean contentBean=new MarketIntelligenceBean.IntelligenceItemSetBean();
                if(intelligenceDetailCreateBean!=null && intelligenceDetailCreateBean.getBusinessTypeKey()!=null){
                    dictIntelligenceInfoBeanList = DictUtils.getDictLikeList("intelligence_info_type","%"+intelligenceDetailCreateBean.getBusinessTypeKey()+"%");
                    if(dictIntelligenceInfoBeanList!=null && dictIntelligenceInfoBeanList.size()>0){
                        contentBean.setIntelligenceInfoKey(dictIntelligenceInfoBeanList.get(0).getKey());
                        contentBean.setIntelligenceInfoValue(dictIntelligenceInfoBeanList.get(0).getValue());
                        contentBean.setDictBeansList(dictIntelligenceInfoBeanList);
                        dictIntelligenceTypeBeanList = DictUtils.getDictLikeList("intelligence_type","%"+dictIntelligenceInfoBeanList.get(0).getKey()+"%");
                        if(dictIntelligenceTypeBeanList!=null && dictIntelligenceTypeBeanList.size()>0){
                            contentBean.setIntelligenceTypeKey(dictIntelligenceTypeBeanList.get(0).getKey());
                            contentBean.setIntelligenceTypeValue(dictIntelligenceTypeBeanList.get(0).getValue());
                            contentBean.setDictBeansList2(dictIntelligenceTypeBeanList);
                        }else{
                            contentBean.setIntelligenceTypeKey(null);
                            contentBean.setIntelligenceTypeValue(null);
                            contentBean.setDictBeansList2(null);

                        }
                    }else {
                        contentBean.setIntelligenceInfoKey(null);
                        contentBean.setIntelligenceInfoValue(null);
                        contentBean.setIntelligenceTypeKey(null);
                        contentBean.setIntelligenceTypeValue(null);
                        contentBean.setDictBeansList(null);
                        contentBean.setDictBeansList2(null);
                    }

                }else {
                    contentBean.setIntelligenceInfoKey(null);
                    contentBean.setIntelligenceInfoValue(null);
                    contentBean.setIntelligenceTypeKey(null);
                    contentBean.setIntelligenceTypeValue(null);
                    contentBean.setDictBeansList(null);
                    contentBean.setDictBeansList2(null);
                }


                newBeanList.add(contentBean);
                customerIntelligenceAdapter.add(newBeanList);
                customerIntelligenceAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecycle() {
        //  纵向滑动
         linearLayoutManager = new LinearLayoutManager(this);
        rvInfoDetailList.setLayoutManager(linearLayoutManager);
//      获取数据，向适配器传数据，绑定适配器
//        list = initData();
        customerIntelligenceAdapter = new CustomerIntelligenceAdapter(R.layout.jiuyi_customerinfomationitem_layout, list);
        rvInfoDetailList.setAdapter(customerIntelligenceAdapter);
        customerIntelligenceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                currentPos=position;
                View childAt = linearLayoutManager.findViewByPosition((currentPos));
                if (childAt!=null){
                    JiuyiIntelligenceBar jiuyiIntelligenceBar=childAt.findViewById(R.id.cob_bar);
                    if(jiuyiIntelligenceBar!=null){

                        if(view.getId()==R.id.tv_picture){
                            if(jiuyiIntelligenceBar.getPicMediaList()!=null && jiuyiIntelligenceBar.getPicMediaList().size()>=9){
                                Toast.makeText(JiuyiCustomerInfomationActivity.this, "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            selectImgs();
                        }else if (view.getId()==R.id.tv_video){
                            if(jiuyiIntelligenceBar.getVideoMediaList()!=null && jiuyiIntelligenceBar.getVideoMediaList().size()>=PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT){
                                Toast.makeText(JiuyiCustomerInfomationActivity.this, "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            selectVideos();
                        }else if (view.getId()==R.id.tv_voice){
                            if(jiuyiIntelligenceBar.getVoiceMediaList()!=null && jiuyiIntelligenceBar.getVoiceMediaList().size()>=9){
                                Toast.makeText(JiuyiCustomerInfomationActivity.this, "已达到选择数量上限", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            selectVoice();
                        }else if (view.getId()==R.id.tv_at){
                            Intent intent = new Intent(JiuyiMainApplication.getIns(), JiuyiCustomerSelectActivity.class);
                            Bundle mBundle = new Bundle();
                            mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"SINGLEPERSON");
                            intent.putExtras(mBundle);
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, 300);
                        }
                    }

                }


            }
        });
//      添加动画
        rvInfoDetailList.setItemAnimator(new DefaultItemAnimator());
    }
    protected void selectVideos() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuVideoWindow = new SelectVideoPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuVideoWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected void selectImgs() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuWindow = new SelectPicPopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(com.wanglicrm.android.R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    protected void selectVoice() {
        ((InputMethodManager) Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        menuVoiceWindow = new SelectVoicePopupWindow(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), itemsOnClick);
        //设置弹窗位置
        menuVoiceWindow.showAtLocation(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().findViewById(R.id.jiuyi_relative_layout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE,"1");
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiCustomerInfomationActivity.this.setResult(1, intent);
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }else if(nAction== DialogID.DialogDeleteTrue) {
            if (nKeyCode == KeyEvent.KEYCODE_ENTER) {
                for (int i = customerIntelligenceAdapter.getData().size(), j = 0; i > j; i--) {
                    MarketIntelligenceBean.IntelligenceItemSetBean contentBean = customerIntelligenceAdapter.getData().get(i-1);
                    if (contentBean.isSelect()) {
                        customerIntelligenceAdapter.getData().remove(contentBean);
                    }
                }
                customerIntelligenceAdapter.notifyDataSetChanged();

            }
        }
    }

    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else{
            mTitle = "新情报";
        }
        setTitle(mTitle);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle;
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK) { //
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  // 检测sd是否可用
                        Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    if(!Func.IsStringEmpty(Rc.getIns().picVideoUrl)){
                        filePath=Rc.getIns().picVideoUrl;
                        Rc.getIns().picVideoUrl="";
                    }
                    Bitmap bm = BitmapUtils.getCompressedBitmap(JiuyiCustomerInfomationActivity.this, filePath);

                    ImageBean takePhoto = new ImageBean();
                    takePhoto.setBitmap(bm);

                    //优化压缩图片
                    Uri uri = null;
                    File file1 = new File(filePath);
                    File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                    takePhoto.setPath(newFile.getPath());
                    if(currentPos>=0){
                        ArrayList<AttachmentBean> picList= customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList();
                        if(picList==null){
                            picList=new ArrayList<>();
                        }
                        AttachmentBean attachmentBean=new AttachmentBean();
                        attachmentBean.setFileType("jpg");
                        attachmentBean.setUrl(filePath);
                        picList.add(attachmentBean);
                        customerIntelligenceAdapter.getData().get(currentPos).setAttachmentList(picList);
                        customerIntelligenceAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case SELECT_PICTURE:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                ArrayList<Media> selects = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                if(tempAttachmentlist==null){
                    tempAttachmentlist=new ArrayList<>();
                }
                if(currentPos>=0 && selects!=null && selects.size()>0 ){
                    for(int i=0;i<selects.size();i++){
                        AttachmentBean attachmentBean=new AttachmentBean();
                        attachmentBean.setFileType("jpg");
                        if(!Func.IsStringEmpty(selects.get(i).getPath())){
                            attachmentBean.setUrl(selects.get(i).getPath());
                        }
                        if(!Func.IsStringEmpty(selects.get(i).getName())){
                            attachmentBean.setFileName(selects.get(i).getName());
                        }
                        String url=selects.get(i).getUrl();
                        if(url!=null && url.lastIndexOf("/")>0 ){
                            attachmentBean.setQiniuKey(url.substring(url.lastIndexOf("/")+1,url.length()));
                        }
                        if(!Func.IsStringEmpty(selects.get(i).getQiniuKey())){
                            attachmentBean.setQiniuKey(selects.get(i).getQiniuKey());
                        }
                        if(!Func.IsStringEmpty(selects.get(i).getUrl())){
                            attachmentBean.setUrl(selects.get(i).getUrl());
                        }
                        if(!tempAttachmentlist.contains(attachmentBean)){
                            tempAttachmentlist.add(attachmentBean);
                        }
                    }
                }
                customerIntelligenceAdapter.getData().get(currentPos).setAttachmentList(tempAttachmentlist);
                customerIntelligenceAdapter.notifyDataSetChanged();

                break;
            case TAKE_Video:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                if(!Func.IsStringEmpty(videoPath)){
                    if(currentPos>=0){

                        ArrayList<AttachmentBean> picList= customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList();
                        if(picList==null){
                            picList=new ArrayList<>();
                        }
                        AttachmentBean attachmentBean=new AttachmentBean();
                        attachmentBean.setFileType("mp4");
                        attachmentBean.setFileName(videoName);
                        attachmentBean.setUrl(videoPath);
                        picList.add(attachmentBean);
                        customerIntelligenceAdapter.getData().get(currentPos).setAttachmentList(picList);
                        customerIntelligenceAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case SELECT_Video:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                ArrayList<Media> selects2 = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                if(tempAttachmentlist==null){
                    tempAttachmentlist=new ArrayList<>();
                }
                if(currentPos>=0 && selects2!=null && selects2.size()>0 ){
                    for(int i=0;i<selects2.size();i++){
                        AttachmentBean attachmentBean=new AttachmentBean();
                        attachmentBean.setFileType("mp4");
                        if(!Func.IsStringEmpty(selects2.get(i).getPath())){
                            attachmentBean.setUrl(selects2.get(i).getPath());
                        }
                        if(!Func.IsStringEmpty(selects2.get(i).getName())){
                            attachmentBean.setFileName(selects2.get(i).getName());
                        }
                        if(!Func.IsStringEmpty(selects2.get(i).getQiniuKey())){
                            attachmentBean.setQiniuKey(selects2.get(i).getQiniuKey());
                        }
                        if(!Func.IsStringEmpty(selects2.get(i).getUrl())){
                            attachmentBean.setUrl(selects2.get(i).getUrl());
                        }


                        if(!tempAttachmentlist.contains(attachmentBean)){
                            tempAttachmentlist.add(attachmentBean);
                        }
                    }
                }
                customerIntelligenceAdapter.getData().get(currentPos).setAttachmentList(tempAttachmentlist);
                customerIntelligenceAdapter.notifyDataSetChanged();

                break;
            case 150:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                ArrayList<Media> selectsVoice = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                if(currentPos>=0 && selectsVoice!=null && selectsVoice.size()>0 ){
                    for(int i=0;i<selectsVoice.size();i++){
                        ArrayList<AttachmentBean> picList= customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList();
                        if(picList==null){
                            picList=new ArrayList<>();
                        }
                        AttachmentBean attachmentBean=new AttachmentBean();
                        attachmentBean.setFileType("mp3");
                        attachmentBean.setFileName(selectsVoice.get(i).getName());
                        attachmentBean.setExtData(selectsVoice.get(i).getFileTime());
                        attachmentBean.setUrl(selectsVoice.get(i).getPath());
                        picList.add(attachmentBean);
                        customerIntelligenceAdapter.getData().get(currentPos).setAttachmentList(picList);
                        customerIntelligenceAdapter.notifyDataSetChanged();
                    }


                }

                break;
            case 300:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
               long  Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                CustomerName = bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                if (Customerid > 0 && CustomerName != null) {
                    User user = new User(Customerid+"",CustomerName);
                    View childAt = linearLayoutManager.findViewByPosition((currentPos));
                    if (childAt!=null){
                        MentionEditText edtContent=childAt.findViewById(R.id.content_edt);
                        customerIntelligenceAdapter.jiuyiIntelligenceBar.setRefreshData(false);
                        edtContent.insert(user);
                        customerIntelligenceAdapter.getData().get(currentPos).setOrginalContent(edtContent.getFormatCharSequence().toString());
                    }
                }
                break;
            case 200:
                if(data == null || data.getExtras() == null) {
                    return;
                }
                 bundle = data.getExtras();
                 String customerName=bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                long customerID=bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                if(customerID>0 && customerName!=null ){
                    ig_linkcustomer.setText(customerName);
                    MemberBeanID memberBeanID=new MemberBeanID();
                    memberBeanID.setId(customerID);
                    memberBeanID.setOrgName(customerName);
                    intelligenceDetailCreateBean.setMember(memberBeanID);
                    visibilityScropeBeanList=new ArrayList<>();
                    visibleType="";
                    ig_visilbity.setText("");
                    ig_person.setText("");
                    ig_person.setVisibility(View.GONE);
                }
                break;
            case PERSON_CODE:
                if (data == null || data.getExtras() == null) {
                    return;
                }
                bundle = data.getExtras();
                mPersonBeanSelectList = new ArrayList<>();
                mPersonBeanSelectList = bundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
                if (mPersonBeanSelectList != null && mPersonBeanSelectList.size() > 0) {
                    String selectNames = "";
                    inchargeSelectList = new ArrayList<>();
                    for (int i = 0; i < mPersonBeanSelectList.size(); i++) {
                        selectNames += mPersonBeanSelectList.get(i).getName() + "，";
                        UpdateAssistantBean updateAssistantBean = new UpdateAssistantBean();
                        updateAssistantBean.setId(mPersonBeanSelectList.get(i).getId());
                        inchargeSelectList.add(updateAssistantBean);
                    }
                    if (!Func.IsStringEmpty(selectNames)) {
                        ig_person.setText(selectNames.substring(0, selectNames.length() - 1));
                    }
                } else {
                    inchargeSelectList = new ArrayList<>();
                    ig_person.setText("");
                }
                break;

            default:
                break;
        }
    }
    protected View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (menuWindow != null) {
                menuWindow.dismiss();
            }
            if (menuVideoWindow != null) {
                menuVideoWindow.dismiss();
            }
            if (menuVoiceWindow != null) {
                menuVoiceWindow.dismiss();
            }
            JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
            switch (v.getId()) {
                case com.wanglicrm.android.R.id.item_popupwindows_camera:        //点击拍照按钮
                    String[] list = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            goCamera();
                        }
                    });

                    break;
                case com.wanglicrm.android.R.id.item_popupwindows_Photo:       //点击从相册中选择按钮
                    String[] list4 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list4, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Intent intent =new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PickerActivity.class);
                            intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
                            intent.putExtra(PickerConfig.MAX_SELECT_COUNT,9);  //最大选择数量，默认40（非必填参数）
                            if(customerIntelligenceAdapter !=null && customerIntelligenceAdapter.getData()!=null && currentPos>=0 && customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList()!=null
                            && customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList().size()>0){
                                tempAttachmentlist= customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList();
                                ArrayList<AttachmentBean> attachmentList=new ArrayList<>();
                                ArrayList<Media> defaultSelect=new ArrayList<>();

                                for(int i = 0; i< customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList().size(); i++){
                                    AttachmentBean attachmentBean= customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList().get(i);
                                    if(attachmentBean.getFileType().toLowerCase().equals("jpg")){
                                        attachmentList.add(attachmentBean);
                                        Media media=new Media();
                                        if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                            media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                                            media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                                        }else if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                                            media.setPath(attachmentBean.getUrl());
                                        }
                                        if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                            media.setQiniuKey(attachmentBean.getQiniuKey());
                                        }
                                        media.setExtension(".jpg");
                                        media.setMediaType(0);
                                        defaultSelect.add(media);
                                    }
                                }
                                if(attachmentList!=null && attachmentList.size()>0){
                                    for(int j=0;j<attachmentList.size();j++){
                                        tempAttachmentlist.remove(attachmentList.get(j));
                                    }
                                }
                                intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,defaultSelect); //可以设置默认选中的照片(非必填参数)

                            }else{
                                tempAttachmentlist=null;
                            }
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,SELECT_PICTURE);
                        }
                    });


                    break;
                case R.id.ll_sure:       //点击从相册中选择按钮
                    Intent intent =new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), JiuyiRecordActivity.class);
                    Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,150);
                    break;
                case R.id.item_popupwindows_video:        //点击录视频按钮
                    String[] list3 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list3, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            startRecordVideo();
                        }
                    });

                    break;
                case R.id.item_popupwindows_videocenter:       //点击从相册中选择按钮

                    String[] list5 = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //检测权限
                    new JiuyiHiPermissionUtil( Rc.getIns().getBaseCallTopCallBack().getCurrentActivity()).checkPermission(list5, new JiuyiHiPermissionUtil.onGuaranteeCallBack() {
                        @Override
                        public void onGuarantee(String permission, int position) {
                            Intent intent =new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), PickerActivity.class);
                            intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_VIDEO);//设置选择类型，默认是图片和视频可一起选择(非必填参数)
                            long maxSize=188743680L;//long long long long类型
                            intent.putExtra(PickerConfig.MAX_SELECT_SIZE,PickerConfig.DEFAULT_SELECTED_MAX_SIZE); //最大选择大小，默认180M（非必填参数）
                            intent.putExtra(PickerConfig.MAX_SELECT_COUNT,PickerConfig.DEFAULT_VIDEO_SELECTED_MAX_COUNT);  //最大选择数量，默认40（非必填参数）
                            if(customerIntelligenceAdapter !=null && customerIntelligenceAdapter.getData()!=null && currentPos>=0 && customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList()!=null
                                    && customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList().size()>0){
                                tempAttachmentlist= customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList();
                                ArrayList<Media> defaultSelect=new ArrayList<>();
                                ArrayList<AttachmentBean> attachmentList=new ArrayList<>();
                                for(int i = 0; i< customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList().size(); i++){
                                    AttachmentBean attachmentBean= customerIntelligenceAdapter.getData().get(currentPos).getAttachmentList().get(i);
                                    if(attachmentBean.getFileType().toLowerCase().equals("mp4")){
                                        attachmentList.add(attachmentBean);
                                        Media media=new Media();
                                        if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                            media.setUrl(CommonUtils.getAttachUrl(attachmentBean));
                                            media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                                        }else if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                                            media.setPath(attachmentBean.getUrl());
                                        }
                                        if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                            media.setQiniuKey(attachmentBean.getQiniuKey());
                                        }
                                        media.setExtension(".mp4");
                                        media.setName(attachmentBean.getFileName());
                                        media.setMediaType(3);
                                        defaultSelect.add(media);
                                    }
                                }
                                if(attachmentList!=null && attachmentList.size()>0){
                                    for(int j=0;j<attachmentList.size();j++){
                                        tempAttachmentlist.remove(attachmentList.get(j));
                                    }
                                }
                                intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,defaultSelect); //可以设置默认选中的照片(非必填参数)

                            }else{
                                tempAttachmentlist=null;
                            }
                            Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,SELECT_Video);
                        }
                    });


                    break;
                default:
                    break;
            }
        }

    };
    protected void goCamera() {
        filePath = FileUtils.SDPATH + String.valueOf(System.currentTimeMillis())+"photo.jpg";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            File vDirPath = file1.getParentFile();
            vDirPath.mkdirs();
        }
        Uri uri = null;
        Rc.getIns().picVideoUrl=filePath;

        if (Build.VERSION.SDK_INT < 24) {
            // 从文件中创建uri
            uri = Uri.fromFile(file1);
        } else {
            //兼容android7.0 使用共享文件的形式
            uri = FileProvider.getUriForFile(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), "com.wanglicrm.android.fileProvider", file1 );
        }

        // 启动Camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent, TAKE_PICTURE);
    }

    public void startRecordVideo() {
        File path=new File(Environment.getExternalStorageDirectory(),
                "jiuyivideo");
        if (!path.exists()) {
            path.mkdirs();
        }
        videoPath=path.getAbsolutePath()+File.separator+System.currentTimeMillis()+".mp4";
        videoName=System.currentTimeMillis()+".mp4";
        Rc.getIns().picVideoUrl=videoPath;
        Intent intent=new Intent(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), RecordVideoActivity.class);
        intent.putExtra(RecordVideoActivity.RECORD_VIDEO_PATH,videoPath);
        Rc.getIns().getBaseCallTopCallBack().getCurrentActivity().startActivityForResult(intent,TAKE_Video);
    }

    public void singleSubmit(){
        JiuyiHttp.POST("market-intelligence/create-link-activity/")
                .setJson(GsonUtil.gson().toJson(intelligenceDetailCreateBean))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        Rc.mBackfresh=true;
                        EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
                        Toast.makeText(JiuyiCustomerInfomationActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        setBackActivityBundle();
                        backPage();
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

    public void submit(){
        JiuyiHttp.POST("market-intelligence/create-link-activity/"+billID)
                .setJson(GsonUtil.gson().toJson(intelligenceDetailCreateBean))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object result ) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        Rc.mBackfresh=true;
                        EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
                        Toast.makeText(JiuyiCustomerInfomationActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        setBackActivityBundle();
                        backPage();
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


    public void UpdateBean(){
        JiuyiHttp.PUT("market-intelligence-item/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(mapResult))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        if(data!=null){
                            Rc.mBackfresh=true;
                            EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
                            Toast.makeText(JiuyiCustomerInfomationActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                            setBackActivityBundle();
                            backPage();
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
        JiuyiHttp.GET("market-intelligence-item/detail/"+id)
                .tag("request_get_market-trend")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<DyInteligenceBean.ContentBean>() {
                    @Override
                    public void onSuccess(DyInteligenceBean.ContentBean contentBean) {
                        if(contentBean!=null){
                            dyInteligenceBean=contentBean;
                            attachmentsEditOldBeanslist=new ArrayList<>();
                            if(dyInteligenceBean.getAttachmentList()!=null && dyInteligenceBean.getAttachmentList().size()>0){
                                for(int i=0;i<dyInteligenceBean.getAttachmentList().size();i++){
                                    attachmentsEditOldBeanslist.add(dyInteligenceBean.getAttachmentList().get(i));
                                }
                            }
//                            attachmentsEditOldBeanslist=dyInteligenceBean.getAttachments();
                            if(contentBean.getMember()!=null){
                                ig_linkcustomer.setText(contentBean.getMember().getOrgName());
                                ig_linkcustomer.setItemOnClickListener(null);
                            }

                            if(contentBean.getIntelligence()!=null){
                                ig_infomationtype.setText(contentBean.getIntelligence().getBigCategoryValue());
                                ig_infomationtype.setItemOnClickListener(null);
                                ig_businesstype.setText(contentBean.getIntelligence().getBusinessTypeValue());
                                ig_businesstype.setItemOnClickListener(null);

                            }

                            rv_addtitle.setVisibility(View.GONE);
                            newBeanList=new ArrayList<>();
                            MarketIntelligenceBean.IntelligenceItemSetBean intelligenceItemSetBean=new MarketIntelligenceBean.IntelligenceItemSetBean();
                            if(contentBean.getContent()!=null){
                                intelligenceItemSetBean.setOrginalContent(contentBean.getContent());
                            }
                            if(contentBean.getIntelligenceInfoKey()!=null){
                                intelligenceItemSetBean.setIntelligenceInfoKey(contentBean.getIntelligenceInfoKey());
                            }
                            if(contentBean.getIntelligenceInfoValue()!=null){
                                intelligenceItemSetBean.setIntelligenceInfoValue(contentBean.getIntelligenceInfoValue());
                            }
                            if(contentBean.getIntelligenceTypeKey()!=null){
                                intelligenceItemSetBean.setIntelligenceTypeKey(contentBean.getIntelligenceTypeKey());
                            }
                            if(contentBean.getIntelligenceTypeValue()!=null){
                                intelligenceItemSetBean.setIntelligenceTypeValue(contentBean.getIntelligenceTypeValue());
                            }
                            if(contentBean.getAttachmentList()!=null){
                                intelligenceItemSetBean.setAttachmentList(contentBean.getAttachmentList());
                            }

                            newBeanList.add(intelligenceItemSetBean);
                            customerIntelligenceAdapter.add(newBeanList);
                            customerIntelligenceAdapter.notifyDataSetChanged();
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

    public void UpdateBeanFromDy(){
        String field[]=new String[2];
        field[0]="viewedCount";
        field[1]="serialVersionUID";

        mapResult=FileUtils.jsonObject2(dyInteligenceBean,field);
        JiuyiHttp.PUT("market-intelligence-item/update")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .setJson(GsonUtil.gson().toJson(mapResult))
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        if(data!=null){
                            Rc.mBackfresh=true;
                            EventBus.getDefault().post(new JiuyiEventBusEvent(JiuyiEventBusEvent.EventType.EventType_Refresh, "", ""));
                            Toast.makeText(JiuyiCustomerInfomationActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                            setBackActivityBundle();
                            backPage();
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
    private void upload(MultiInputFileBean inputFileBean)
    {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.UPLOAD("file/upload-multi")
                        .tag("upload-multi")
                        .addFiles2("file",inputFileBean.getFiles())
                        .addParam("extData",extDataList.get(currentAttachmentPos))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<ArrayList<AttachmentBean>>() {
                            @Override
                            public void onSuccess(ArrayList<AttachmentBean> data) {
                                if(data!=null && data.size()>0){
                                    if(intelligenceDetailCreateBean!=null){
                                        intelligenceDetailCreateBean.getIntelligenceItemSet().get(inputFileBean.getPos()).setAttachmentList(data);
                                    }
                                    if(inputFileList!=null && inputFileList.size()>0){
                                        currentAttachmentPos++;
                                        if(currentAttachmentPos<inputFileList.size()){
                                            upload(inputFileList.get(currentAttachmentPos));
                                        }else{
                                            if(operatorType.equals(ViewOperatorType.SINGLEADD)){
                                                singleSubmit();
                                            }else if(operatorType.equals(ViewOperatorType.ADD)){
                                                submit();
                                            }
                                        }
                                    }
                                }else{
                                    if(progressLoadingDialog!=null){
                                        progressLoadingDialog.dismiss();
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

    private void upload()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                if(!Func.IsStringEmpty(extData) && extData.length()>0){
                    extData=extData.substring(0,extData.length()-1);
                }
                JiuyiHttp.UPLOAD("file/upload-multi")
                        .tag("upload-multi")
                        .addFiles2("file",files)
                        .addParam("extData",extData)
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<ArrayList<AttachmentBean>>() {
                            @Override
                            public void onSuccess(ArrayList<AttachmentBean> data) {
                                if(data!=null && data.size()>0){
                                    attachmentsBeanslist=data;
                                 if(operatorType.equals(ViewOperatorType.SINGLEEDIT)){
                                        for(int i=0;i<attachmentsBeanslist.size();i++){
                                            attachmentsEditBeanslist.add(attachmentsBeanslist.get(i));
                                        }
                                     dyInteligenceBean.setAttachmentList(attachmentsEditBeanslist);
                                     UpdateBeanFromDy();
                                    }
                                }else{
                                    if(progressLoadingDialog!=null){
                                        progressLoadingDialog.dismiss();
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

    private void getVisibleTypeList() {

        JiuyiHttp.GET("visible-range/visible-type-list")
                .tag("request_get_")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<VisibleTypeBean>>() {
                    @Override
                    public void onSuccess(List<VisibleTypeBean> returndata) {
                        if(progressLoadingDialog!=null){
                            progressLoadingDialog.dismiss();
                        }
                        if(returndata!=null && returndata.size()>0){
                            AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerInfomationActivity.this);
                            buidler.setTitle("可见范围");
                            final String[] data;
                            data=new String[returndata.size()];
                            for(int i=0;i<returndata.size();i++){
                                data[i]=returndata.get(i).getValue();
                            }
                            buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    visibilityScropeBeanList=new ArrayList<>();
                                    ig_visilbity.setText(data[which].trim());
                                    visibleType=returndata.get(which).getKey();
                                    if(visibleType.equals("DEPARTMENT")){
                                        getDeptmentInfo();

                                    }else {
                                        lv_companyaddresslist.setVisibility(View.GONE);
                                    }
                                    if(visibleType.equals("FRSRAR")||visibleType.equals("SOMEONE")){
                                        ig_person.setVisibility(View.VISIBLE);
                                        ig_person.setText("");
                                    }else {
                                        ig_person.setVisibility(View.GONE);
                                        ig_person.setText("");
                                    }
                                    dialog.dismiss();
                                }
                            });
                            buidler.show();
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
    public void getDeptmentInfo(){
        lv_companyaddresslist.setVisibility(View.VISIBLE);
        mDatas=new ArrayList<>();
        List<OperatorNodeBean> userList = DbHelper.getInstance().operatorNode().queryBuilder()
                .where(OperatorNodeBeanDao.Properties.IsPeople.notEq(1)).build().list();
        if(userList!=null && userList.size()>0){
            for(int i=0;i<userList.size();i++){
                OperatorNodeBean officeBean=userList.get(i);
                mDatas.add(new Node(officeBean.getNodeid(), officeBean.getNodepid(), officeBean.getName().toString()));
            }
        }
        //给 ListView 设置 Adapter
        mAdapter = new OrgDeptSimpleTreeAdapter(lv_companyaddresslist, JiuyiCustomerInfomationActivity.this, mDatas, 1, R.drawable.cn_address_book_less, R.drawable.cn_address_book_plus);
        mAdapter.setType("NOSHOW");
        mAdapter.setMulti(true);
        lv_companyaddresslist.setAdapter(mAdapter);
        setListViewHeightBasedOnChildren(lv_companyaddresslist);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除

        listView.setLayoutParams(params);
    }

    public void getMemberCenterInfo(long Customerid) {
        JiuyiHttp.GET("member/find/"+Customerid+"?fromClientType=android")
                .tag("request_get_center")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<MemberOperatorBean>() {
                    @Override
                    public void onSuccess(MemberOperatorBean memberOperatorBean) {
                        if(memberOperatorBean!=null){
                            AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiCustomerInfomationActivity.this);
                            buidler.setTitle("客户干系人");
                            final boolean b[];
                            final String[] data;
                            ArrayList<MemberOperatorBean.ArOperatorBean> operatorBeanArrayList=new ArrayList<>();
                            if(memberOperatorBean.getArOperator()!=null){
                                memberOperatorBean.getArOperator().setRole("AR");
                                memberOperatorBean.getArOperator().setShowInfo("AR-"+memberOperatorBean.getArOperator().getId()+"-"+memberOperatorBean.getArOperator().getName());
                                operatorBeanArrayList.add(memberOperatorBean.getArOperator());
                            }
                            if(memberOperatorBean.getSrOperator()!=null){
                                memberOperatorBean.getSrOperator().setRole("SR");
                                memberOperatorBean.getSrOperator().setShowInfo("SR-"+memberOperatorBean.getSrOperator().getId()+"-"+memberOperatorBean.getSrOperator().getName());
                                operatorBeanArrayList.add(memberOperatorBean.getSrOperator());
                            }
                            if(memberOperatorBean.getFrOperator()!=null){
                                memberOperatorBean.getFrOperator().setRole("FR");
                                memberOperatorBean.getFrOperator().setShowInfo("FR-"+memberOperatorBean.getFrOperator().getId()+"-"+memberOperatorBean.getFrOperator().getName());
                                operatorBeanArrayList.add(memberOperatorBean.getFrOperator());
                            }

                            if (operatorBeanArrayList!=null && operatorBeanArrayList.size()>0) {
                                data = new String[operatorBeanArrayList.size()];
                                b = new boolean[operatorBeanArrayList.size()];
                                for (int i = 0; i < operatorBeanArrayList.size(); i++) {
                                    data[i] = operatorBeanArrayList.get(i).getShowInfo();
                                    b[i] = false;
                                }
                                buidler.setMultiChoiceItems(data, b, new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        //    将客户是否被勾选的记录保存到集合中
                                        b[which] = isChecked;  //保存客户选择的属性是否被勾选
                                    }
                                });
                                //设置一个确定和取消按钮
                                buidler.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    //保存数据
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String item = "";
                                        //取出被勾选中的内容
                                        ArrayList selectList = new ArrayList<>();
                                        visibilityScropeBeanList=new ArrayList<>();
                                        for (int i = 0; i < operatorBeanArrayList.size(); i++) {
                                            if (b[i]) {             //如果被勾线则保存数据
                                                item += data[i] + " ,";
                                                VisibilityScropeBean visibilityScropeBean=new VisibilityScropeBean();
                                                visibilityScropeBean.setVisibleType(visibleType);
                                                visibilityScropeBean.setOperatorId(operatorBeanArrayList.get(i).getId());
                                                visibilityScropeBean.setFrSrAr(operatorBeanArrayList.get(i).getRole());
                                                visibilityScropeBeanList.add(visibilityScropeBean);
                                                selectList.add(operatorBeanArrayList.get(i).getShowInfo());
                                            }
                                        }
                                        if (!Func.IsStringEmpty(item)) {
                                            String sValue = item.substring(0, item.length() - 1);
                                            ig_person.setText(sValue);
                                        }
                                        //对话框消失
                                        dialog.dismiss();
                                    }
                                });
                                buidler.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                buidler.show();
                            }else{
                                Toast.makeText(JiuyiCustomerInfomationActivity.this, "该关联对象没有可选干系人！", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }

    public void getVisibleRange(long id) {
        JiuyiHttp.GET("visible-range/list-by-fktype-fkid/INTELLIGENCE_ITEM/"+id+"?fromClientType=android")
                .tag("request_get_center")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<VisiblityScropeEditBean>() {
                    @Override
                    public void onSuccess(VisiblityScropeEditBean data) {
                        if(data!=null && !Func.IsStringEmpty(data.getVisibleType()) ){
                            ig_visilbity.setText(data.getVisibleTypeValue());
                            visibleType=data.getVisibleType();
                            if(data.getVisibleType().equals("PUBLIC")){
                                ig_person.setVisibility(View.GONE);
                            }else if(data.getVisibleType().equals("DEPARTMENT")){
                                getDeptmentInfo();
                                if(data.getDepartmentIdList()!=null && data.getDepartmentIdList().size()>0 && mAdapter!=null){
                                    if(mAdapter.getAllNodes()!=null && mAdapter.getAllNodes().size()>0){
                                        List<Long> selectIDs=new ArrayList<>();

                                        for(int i=0;i<mAdapter.getAllNodes().size();i++){
                                            if(mAdapter.getAllNodes().get(i)!=null && !mAdapter.getAllNodes().get(i).getId().toString().equals("dall")){
                                                long id=Long.parseLong(mAdapter.getAllNodes().get(i).getId().toString().replace("d",""));
                                                if(data.getDepartmentIdList().contains(id)){
                                                    mAdapter.getAllNodes().get(i).setChecked(true);
                                                    if (mAdapter.getAllNodes().get(i).getParent() != null){
                                                        mAdapter.setNodeParentChecked(mAdapter.getAllNodes().get(i).getParent(), true);
                                                    }
                                                    selectIDs.add(id);
                                                }
                                            }
                                            if(mAdapter.getAllNodes().get(i).getId().toString().equals("dall")){
                                                mAdapter.getAllNodes().get(i).setChecked(true);
                                            }
                                        }
                                        mAdapter.setSelectIDs(selectIDs);
                                    }
                                    mAdapter.notifyDataSetChanged();

                                }
                            }else if(data.getVisibleType().equals("FRSRAR")){
                                if(data.getFrSrArBeanList()!=null && data.getFrSrArBeanList().size()>0){
                                    String sPerson="";
                                    for(int i=0;i<data.getFrSrArBeanList().size();i++){
                                        VisiblityScropeEditBean.FrSrArBeanListBean bean=data.getFrSrArBeanList().get(i);
                                        if(bean!=null && bean.getFrSrAr()!=null && bean.getOperator()!=null){
                                            sPerson+= bean.getFrSrAr()+"-"+bean.getOperator().getId()+"-"+bean.getOperator().getName()+",";
                                        }
                                    }
                                    if(!Func.IsStringEmpty(sPerson) && sPerson.length()>0){
                                        ig_person.setText(sPerson.substring(0,sPerson.length()-1));
                                    }
                                    ig_person.setVisibility(View.VISIBLE);

                                }
                            }else if(data.getVisibleType().equals("SOMEONE")){
                                String sPerson="";
                                mPersonBeanSelectList = new ArrayList<>();
                                inchargeSelectList=new ArrayList<>();
                                if(data.getSomeoneList()!=null && data.getSomeoneList().size()>0){
                                    for(int i=0;i<data.getSomeoneList().size();i++){
                                        NormalOperatorBean.OperatorBeanID operatorBeanID=data.getSomeoneList().get(i);
                                        if(operatorBeanID!=null && !Func.IsStringEmpty(operatorBeanID.getName())){
                                            sPerson+= operatorBeanID.getName()+",";
                                            PersonSelectBean personSelectBean=new PersonSelectBean();
                                            personSelectBean.setId(operatorBeanID.getId());
                                            personSelectBean.setName(operatorBeanID.getName());
                                            mPersonBeanSelectList.add(personSelectBean);
                                            UpdateAssistantBean updateAssistantBean=new UpdateAssistantBean();
                                            updateAssistantBean.setId(operatorBeanID.getId());
                                            updateAssistantBean.setName(operatorBeanID.getName());
                                            inchargeSelectList.add(updateAssistantBean);
                                        }

                                    }
                                    if(!Func.IsStringEmpty(sPerson) && sPerson.length()>0){
                                        ig_person.setText(sPerson.substring(0,sPerson.length()-1));
                                    }
                                    ig_person.setVisibility(View.VISIBLE);
                                }
                            }

                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });


    }



}
