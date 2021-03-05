package commonlyused.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiBigTextGroup;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiItemGroup;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.nanchen.compresshelper.CompressHelper;
import com.tencent.qcloud.sdk.Constant;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import customer.Utils.ViewOperatorType;
import commonlyused.bean.JiuyiRetailChannelBean;
import customer.activity.JiuyiCustomerSelectActivity;
import customer.entity.AttachmentBean;
import customer.entity.Media;
import customer.entity.MemberBean;
import customer.entity.MemberBeanID;
import customer.listener.PickerConfig;
import customer.view.JiuyiAttachment;
import customer.view.JiuyiToggleButtonGroup;
import freemarker.template.utility.CollectionUtils;

import static commonlyused.activity.JiuyiRetailChannelNewActivity.SELECT_PICTURE;
import static commonlyused.activity.JiuyiRetailChannelNewActivity.TAKE_PICTURE;
import static customer.Utils.ViewOperatorType.EDIT;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNewRetailChannelDtailMutiActivity
 * 作       者 : zhengss
 * 创建时间:2018/8/9 14:43
 * 文件描述 : 客户明细
 *****************************************************************
 */
public class JiuyiNewRetailChannelDtailMutiActivity extends JiuyiActivityBase {
    private TextView mtvcomplete;
    private JiuyiButton mbtnsave;
    private String billType="";
    private long Customerid=-1;
    private String bill_type="";
    private JiuyiItemGroup jigClient;
    private JiuyiToggleButtonGroup jigIsvisit;
    private JiuyiToggleButtonGroup jigIssell;
    private JiuyiToggleButtonGroup jigIsstyle;
    private JiuyiToggleButtonGroup jigIscompare;
    private JiuyiBigTextGroup jigContent;
    private JiuyiAttachment ahAttach;
    private JiuyiAttachment ahAttachTwo;
    private JiuyiToggleButtonGroup jigIspolitics;
    private JiuyiToggleButtonGroup jigIstrain;
    private JiuyiButton btnSave;
    private String operType="",billOperType="";
    private JiuyiRetailChannelBean.RetailChannelItemsBean retailChannelItemsBean;
    private String filePath;
    private  File[] files;
    private List<AttachmentBean> attachmentsBeanslist,attachmentsEditBeanslist;
    private  File[] filesTwo;
    private List<AttachmentBean> attachmentsTwoBeanslist,attachmentsTwoEditBeanslist;
    private Boolean needUpload=false;
    private LinearLayout ll_content;
    private String msProvince="";




    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newretailchannelmulti_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
         setContentView(mBodyLayout);
        if(mBundle!=null){
            operType= mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
            billOperType= mBundle.getString(JiuyiBundleKey.PARAM_BILLOPERATORTYPE);
            msProvince= mBundle.getString(JiuyiBundleKey.PARAM_PROVINCE);
        }
        setTitle();
        if(operType==null){
            operType="";
        }
        if(billOperType==null){
            billOperType="";
        }
        if(msProvince==null){
            msProvince="";
        }
        if(operType.equals(ViewOperatorType.ADD)){
            retailChannelItemsBean =new JiuyiRetailChannelBean.RetailChannelItemsBean();
        }else{
            retailChannelItemsBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        }



        jigClient = (JiuyiItemGroup) mBodyLayout.findViewById(R.id.jig_client);
        jigClient.setItemOnClickListener(new JiuyiItemGroup.ItemOnClickListener() {
            @Override
            public void onItemClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_SPECIALCONDITION,"Y");
                mBundle.putString(JiuyiBundleKey.PARAM_PROVINCE,msProvince);
                mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CUSTOMER");
                intent.putExtras(mBundle);
                startActivityForResult(intent, 201);

            }
        });
        jigIsvisit = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_isvisit);
        jigIssell = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_issell);
        jigIsstyle = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_isstyle);
        jigIscompare = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_iscompare);
        jigIscompare.tb_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    jigIscompare.bdefautl=true;
                    ahAttach.setVisibility(View.VISIBLE);
                    jigContent.setVisibility(View.GONE);
                }else{
                    jigIscompare.bdefautl=false;
                    ahAttach.setVisibility(View.GONE);
                    jigContent.setVisibility(View.VISIBLE);
                }
            }
        });
        ll_content= (LinearLayout) mBodyLayout.findViewById(R.id.ll_content);
        jigContent = (JiuyiBigTextGroup) mBodyLayout.findViewById(R.id.jig_content);
        ahAttach = (JiuyiAttachment) mBodyLayout.findViewById(R.id.ah_attach);
        ahAttach.setAdapter();
        ahAttachTwo = (JiuyiAttachment) mBodyLayout.findViewById(R.id.ah_attach_two);
        ahAttachTwo.setAdapter();
        jigIspolitics = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_ispolitics);
        jigIstrain = (JiuyiToggleButtonGroup) mBodyLayout.findViewById(R.id.jig_istrain);
        btnSave = (JiuyiButton) mBodyLayout.findViewById(R.id.btn_save);
        if(retailChannelItemsBean !=null) {
            if (retailChannelItemsBean.getMember() != null && retailChannelItemsBean.getMember().getOrgName()!= null) {
                jigClient.setText(retailChannelItemsBean.getMember().getOrgName());
            }
            jigIsvisit.tb_type.setChecked(retailChannelItemsBean.isVisit());
            jigIssell.tb_type.setChecked(retailChannelItemsBean.isMonopoly());
            jigIsstyle.tb_type.setChecked(retailChannelItemsBean.isComplete());
            jigIscompare.tb_type.setChecked(retailChannelItemsBean.isComparativeSales());
            if(retailChannelItemsBean.isComparativeSales()){
                jigContent.setVisibility(View.GONE);
                ahAttach.setVisibility(View.VISIBLE);
                if(retailChannelItemsBean.getAttachments()!=null && retailChannelItemsBean.getAttachments().size()>0){
                    attachmentsBeanslist=retailChannelItemsBean.getAttachments();
                    ArrayList mediaArrayList=new ArrayList<>();
                    for(int i=0;i<retailChannelItemsBean.getAttachments().size();i++){

                        AttachmentBean attachmentBean = retailChannelItemsBean.getAttachments().get(i);//把JSON字符串转为对象
                        Media media=new Media();
                        media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                        media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                        media.setExtension(".jpg");
                        media.setMediaType(0);
                        if(operType.equals(ViewOperatorType.VIEW)){
                            media.setShowIcon(false);
                        }
                        mediaArrayList.add(media);
                    }
                    ahAttach.setMediaArrayList(mediaArrayList);
                    ahAttach.adapter.setMviewBeanList(mediaArrayList);
                    ahAttach.adapter.notifyDataSetChanged();
                    ahAttach.refreshAttach();
                }

            }else{
                jigContent.setVisibility(View.VISIBLE);
                ahAttach.setVisibility(View.GONE);
                if(!Func.IsStringEmpty(retailChannelItemsBean.getContent())){
                    jigContent.setText(retailChannelItemsBean.getContent());
                }
            }
            if(retailChannelItemsBean.getAttachmentsTwo()!=null && retailChannelItemsBean.getAttachmentsTwo().size()>0){
                attachmentsTwoBeanslist=retailChannelItemsBean.getAttachmentsTwo();
                ArrayList mediaArrayList=new ArrayList<>();
                for(int i=0;i<retailChannelItemsBean.getAttachmentsTwo().size();i++){

                    AttachmentBean attachmentBean = retailChannelItemsBean.getAttachmentsTwo().get(i);//把JSON字符串转为对象
                    Media media=new Media();
                    media.setUrl(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey());
                    media.setThumbnailPath(Constant.BaseUrl+"file/"+attachmentBean.getQiniuKey()+"/_thumbnail");
                    media.setExtension(".jpg");
                    media.setMediaType(0);
                    if(operType.equals(ViewOperatorType.VIEW)){
                        media.setShowIcon(false);
                    }
                    mediaArrayList.add(media);
                }
                ahAttachTwo.setMediaArrayList(mediaArrayList);
                ahAttachTwo.adapter.setMviewBeanList(mediaArrayList);
                ahAttachTwo.adapter.notifyDataSetChanged();
                ahAttachTwo.refreshAttach();
            }
            jigIspolitics.tb_type.setChecked(retailChannelItemsBean.isPolicyProcessAdvocacy());
            jigIstrain.tb_type.setChecked(retailChannelItemsBean.isTrain());



            retailChannelItemsBean.setComplete(jigIsstyle.bdefautl);
            retailChannelItemsBean.setComparativeSales(jigIscompare.bdefautl);
            retailChannelItemsBean.setContent(jigContent.getText());
            retailChannelItemsBean.setPolicyProcessAdvocacy(jigIspolitics.bdefautl);
            retailChannelItemsBean.setTrain(jigIstrain.bdefautl);
        }



        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        mbtnsave= (JiuyiButton) mBodyLayout.findViewById(Res.getViewID(null, "btn_save"));
        if(mbtnsave!=null){
            mbtnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    boolean mbcheck=false;
                    mbcheck=check();
                    if(!mbcheck){
                        return;
                    }
                    retailChannelItemsBean.setVisit(jigIsvisit.bdefautl);
                    retailChannelItemsBean.setMonopoly(jigIssell.bdefautl);
                    retailChannelItemsBean.setComplete(jigIsstyle.bdefautl);
                    retailChannelItemsBean.setComparativeSales(jigIscompare.bdefautl);
                    retailChannelItemsBean.setContent(jigContent.getText());
                    retailChannelItemsBean.setPolicyProcessAdvocacy(jigIspolitics.bdefautl);
                    retailChannelItemsBean.setTrain(jigIstrain.bdefautl);
                    if(ahAttach.getMediaArrayList().size()>0 || ahAttachTwo.getMediaArrayList().size()>0) {
                        if(operType.equals(ViewOperatorType.EDIT)){
                            attachmentsEditBeanslist=new ArrayList<>();
                            attachmentsTwoEditBeanslist=new ArrayList<>();
                        }

                        if(ahAttach.getMediaArrayList().size()>0 ){
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
                                        }

                                    }


                                }
                            }
                        }
                        if(ahAttachTwo.getMediaArrayList().size()>0 ){
                            filesTwo = new File[ahAttachTwo.getMediaArrayList().size()];
                            for (int i = 0; i < ahAttachTwo.getMediaArrayList().size(); i++) {
                                Media media=ahAttachTwo.getMediaArrayList().get(i);
                                if (media.getPath() != null ) {
                                    File file1 = new File(media.getPath());
                                    filesTwo[i] = file1;
                                    needUpload=true;
                                }else{
                                    if(media.getUrl()!=null && attachmentsTwoBeanslist!=null && attachmentsTwoBeanslist.size()>0  ){
                                        for(int j=0;j<attachmentsTwoBeanslist.size();j++){
                                            AttachmentBean attachmentBean=attachmentsTwoBeanslist.get(j);
                                            if(attachmentBean.getQiniuKey()!=null   ){
                                                int pos=media.getUrl().lastIndexOf("/");
                                                if(pos>0){
                                                    String qiuniukey=media.getUrl().substring(pos+1,media.getUrl().length());
                                                    if(qiuniukey.equals(attachmentBean.getQiniuKey())){
                                                        attachmentsTwoEditBeanslist.add(attachmentBean);
                                                    }
                                                }
                                            }
                                        }

                                    }


                                }
                            }
                        }


                        if(needUpload){
                            upload();
                        }else{
                            if(attachmentsEditBeanslist.size()>0){
                                retailChannelItemsBean.setAttachments(attachmentsEditBeanslist);
                            }
                            if(attachmentsTwoEditBeanslist.size()>0){
                                retailChannelItemsBean.setAttachmentsTwo(attachmentsTwoEditBeanslist);
                            }
                            setBackActivityBundle();
                            backPage();
                        }

                    }else{
                        if(ahAttach.getMediaArrayList()==null ||ahAttach.getMediaArrayList().size()<1){
                            retailChannelItemsBean.setAttachments(null);
                        }
                        if(ahAttachTwo.getMediaArrayList()==null ||ahAttachTwo.getMediaArrayList().size()<1){
                            retailChannelItemsBean.setAttachmentsTwo(null);
                        }

                        setBackActivityBundle();
                        backPage();
                    }
                }
            });
        }

        if(operType.equals(ViewOperatorType.VIEW)){
            disableSubControls(ll_content);
        }
        if(billOperType.equals(ViewOperatorType.SPECIAL)){
            jigIsvisit.tb_type.setEnabled(true);
            jigIssell.tb_type.setEnabled(true);
            jigIsstyle.tb_type.setEnabled(true);
            jigIscompare.tb_type.setEnabled(true);
            jigContent.contentEdt.setEnabled(true);
            jigIspolitics.tb_type.setEnabled(true);
            jigIstrain.tb_type.setEnabled(true);
        }else {
            jigIsvisit.tb_type.setEnabled(false);
            jigIssell.tb_type.setEnabled(false);
            jigIsstyle.tb_type.setEnabled(false);
            jigIscompare.tb_type.setEnabled(false);
            jigContent.contentEdt.setEnabled(false);
            jigIspolitics.tb_type.setEnabled(false);
            jigIstrain.tb_type.setEnabled(false);
        }

    }


    public void setTitle(){
        if(operType!=null ){
            if(operType.equals(EDIT)){
                mTitle = "修改客户明细";
            }else if(operType.equals(ViewOperatorType.VIEW)){
                mTitle = "查看客户明细";
            }else if(operType.equals(ViewOperatorType.ADD)){
                mTitle = "添加客户明细";
            }
        }else{
            mTitle = "添加客户明细";
        }

        setTitle(mTitle);
    }

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            backPage();
        }
    }

    @Override
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN2, retailChannelItemsBean);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiNewRetailChannelDtailMutiActivity.this.setResult(1, intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((data == null || data.getExtras() == null) && (requestCode!=TAKE_PICTURE  &&  requestCode!=1200)) {
            return;
        }
        Bundle bundle;
        switch (requestCode) {
            case 201:
                if(retailChannelItemsBean ==null){
                    retailChannelItemsBean =new JiuyiRetailChannelBean.RetailChannelItemsBean();
                }
                bundle = data.getExtras();
                MemberBean.ContentBean contentBean=bundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
                if(contentBean!=null && contentBean.getOrgName()!=null ){
                    String customerName3=contentBean.getOrgName();
                    long activityId=contentBean.getId();

                    if(activityId>0 && customerName3!=null ){
                        jigClient.setText(customerName3);
                        MemberBeanID memberBeanID=new MemberBeanID();
                        memberBeanID.setId(activityId);
                        memberBeanID.setOrgName(customerName3);
                        retailChannelItemsBean.setMember(memberBeanID);
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
            case 1100:
                ArrayList<Media> selectsTwo = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                ahAttachTwo.setMediaArrayList(selectsTwo);
                ahAttachTwo.adapter.setMviewBeanList(selectsTwo);
                ahAttachTwo.refreshAttach();
                break;
            case 1200:
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
                    ahAttachTwo.adapter.getMviewBeanList().add(media);
                    ahAttachTwo.refreshAttach();
                }
                break;



            default:
                break;

        }
    }

    public boolean check(){
        if(TextUtils.isEmpty(jigClient.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(jigIsvisit.bdefautl && (ahAttachTwo.getMediaArrayList()==null||ahAttachTwo.getMediaArrayList().size()==0)){
            startDialog(DialogID.DialogDoNothing, "", "请选择走访附件！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
//        if(!jigIscompare.bdefautl && Func.IsStringEmpty(jigContent.getText())){
//            startDialog(DialogID.DialogDoNothing, "", "请输入内容！", JiuyiDialogBase.Dialog_Type_Yes, null);
//            return false;
//        }
        return true;

    }
    private void upload()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                if(files!=null && files.length>0){
                    JiuyiHttp.UPLOAD("file/upload-multi")
                            .tag("upload-multi")
                            .addFiles2("file",files)
                            .addHeader("Authorization", Rc.id_tokenparam)
                            .request(new ACallback<ArrayList<AttachmentBean>>() {
                                @Override
                                public void onSuccess(ArrayList<AttachmentBean> data) {
                                    if(data!=null && data.size()>0){
                                        attachmentsBeanslist=data;
                                        if(operType.equals(ViewOperatorType.ADD)){
                                            retailChannelItemsBean.setAttachments(attachmentsBeanslist);
                                        }else if(operType.equals(ViewOperatorType.EDIT)){
                                            for(int i=0;i<attachmentsBeanslist.size();i++){
                                                attachmentsEditBeanslist.add(attachmentsBeanslist.get(i));
                                            }
                                            retailChannelItemsBean.setAttachments(attachmentsEditBeanslist);
                                        }
                                    }
                                    if(progressLoadingDialog!=null){
                                        progressLoadingDialog.dismiss();
                                    }
                                    setBackActivityBundle();
                                    backPage();

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
                if(filesTwo!=null && filesTwo.length>0){
                    JiuyiHttp.UPLOAD("file/upload-multi")
                            .tag("upload-multi")
                            .addFiles2("file",filesTwo)
                            .addHeader("Authorization", Rc.id_tokenparam)
                            .request(new ACallback<ArrayList<AttachmentBean>>() {
                                @Override
                                public void onSuccess(ArrayList<AttachmentBean> data) {
                                    if(data!=null && data.size()>0){
                                        attachmentsTwoBeanslist=data;
                                        if(operType.equals(ViewOperatorType.ADD)){
                                            retailChannelItemsBean.setAttachmentsTwo(attachmentsTwoBeanslist);
                                        }else if(operType.equals(ViewOperatorType.EDIT)){
                                            for(int i=0;i<attachmentsTwoBeanslist.size();i++){
                                                attachmentsTwoEditBeanslist.add(attachmentsTwoBeanslist.get(i));
                                            }
                                            retailChannelItemsBean.setAttachmentsTwo(attachmentsTwoEditBeanslist);
                                        }
                                    }
                                    if(progressLoadingDialog!=null){
                                        progressLoadingDialog.dismiss();
                                    }
                                    setBackActivityBundle();
                                    backPage();

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
            }
        };

        thread.start();
    }



}
