package customer.activity;

import android.app.Dialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import customer.adapter.TradeDetailInfoAdapter;
import customer.entity.AddAttachmentBean;
import customer.entity.AttachmentBean;
import customer.entity.CustomerTradeDetailBean;
import customer.entity.ImageBean;
import customer.entity.UploadTokenBean;
import customer.view.Bimp;
import customer.view.MultiItemDivider;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerTradeDetailInfo
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 发货发票详细信息页面
 *****************************************************************
 */
public class JiuyiCustomerTradeDetailInfo extends JiuyiNewIncludeAttachActivity {
    TextView tv_title,mtvcomplete,tv_name;
    RecyclerView rv_content;
    private CustomerTradeDetailBean customerTradeDetailBean;
    private List<CustomerTradeDetailBean.ContentBean.DataBean> dataBeanList;
    private TradeDetailInfoAdapter tradeDetailInfoAdapter;
    private String billTitle,billType;
    private long billID=-1;
    private String customerName;
    private AddAttachmentBean addAttachmentBean;
    private List<AttachmentBean> attachmentsBeanslist;

    @Override
    public void onInit() {
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customertradedetailinfo_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        billTitle=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        billType=mBundle.getString(JiuyiBundleKey.PARAM_BILLTYPE);
        billID=mBundle.getLong(JiuyiBundleKey.PARAM_BILLID);
        customerName=mBundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
        setTitle();
        initViews();

        tv_title=(TextView)mBodyLayout.findViewById(Res.getViewID(this, "tv_title"));
        tv_name=(TextView)mBodyLayout.findViewById(Res.getViewID(this, "tv_name"));
        if(!Func.IsStringEmpty(customerName)){
            tv_name.setText(customerName);
        }
        rv_content=(RecyclerView)mBodyLayout.findViewById(Res.getViewID(this, "rv_content"));
        rv_content.setLayoutManager(new LinearLayoutManager(JiuyiCustomerTradeDetailInfo.this));
        rv_content.setHasFixedSize(true);
        //分割线
        MultiItemDivider itemDivider = new MultiItemDivider(JiuyiCustomerTradeDetailInfo.this, MultiItemDivider.VERTICAL_LIST, R.drawable.divider_recycler);
        itemDivider.setDividerMode(MultiItemDivider.END);//最后一个item下有分割线
        rv_content.addItemDecoration(itemDivider);
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(this, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    addAttachmentBean =new AddAttachmentBean();
                    if(billID>0){
                        addAttachmentBean.setId(billID);
                    }
                    showDialog();
                    if(Bimp.tempSelectBitmap.size()>0){
                        upload();
                    }else{
                        attachmentsBeanslist=new ArrayList<AttachmentBean>();
                        addAttachmentBean.setAttachmentList(attachmentsBeanslist);
                        updateAttachmentInfo();
                    }

                }
            });
        }
        showProcessBar(0);
        if(billType.equals("Tradedelivery")){
            if(billID>0){
                getTradedeliveryList(billID);
            }
        }else if(billType.equals("Tradeinvoice")){
            if(billID>0){
                getInvoiceInfoList(billID);
            }
        }else if(billType.equals("Tradetelemoney")){
            if(billID>0){
                getTradetelemoneyInfoList(billID);
            }
        }else if(billType.equals("Tradebankstatement")){
            if(billID>0){
                getTradebankstatementInfoList(billID);
            }
        }

    }

    public void setTitle(){
        if(!Func.IsStringEmpty(billTitle)){
            mTitle = billTitle;
        }

        setTitle(mTitle);
    }
    private void getTradedeliveryList(final long id) {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("sap-invoice/find_read_bean/"+id)
                        .tag("request_get_waybill")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<CustomerTradeDetailBean>() {
                            @Override
                            public void onSuccess(CustomerTradeDetailBean data) {
                                JiuyiLog.e("getsig","request onSuccess!");
                                if(data!=null){
                                    dataBeanList=data.getContent().get(0).getData();
                                    customerTradeDetailBean=data;
                                    tradeDetailInfoAdapter = new TradeDetailInfoAdapter(R.layout.customer_tradedetailinfo, dataBeanList);
                                    List<AttachmentBean> attachmentBeanList=data.getContent().get(0).getAttachmentList();
                                    if(attachmentBeanList!=null && attachmentBeanList.size()>0){
                                        Bimp.tempSelectBitmap.clear();
                                        for(int i=0;i<attachmentBeanList.size();i++){
                                            AttachmentBean attachmentBean=attachmentBeanList.get(i);
                                            ImageBean returnPhoto = new ImageBean();
                                            if(!Func.IsStringEmpty(attachmentBean.getThumbnail())){
                                                returnPhoto.setThumbnailPath(attachmentBean.getThumbnail());
                                            }
                                            if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                                returnPhoto.setQiniuKey(attachmentBean.getQiniuKey());
                                            }
                                            if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                                                returnPhoto.setImgUrl(attachmentBean.getUrl());
                                            }
                                            Bimp.tempSelectBitmap.add(returnPhoto);
                                        }
                                        adapter.notifyDataSetChanged();
                                        if(mtvcount!=null){
                                            mtvcount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
                                        }
                                    }
                                    tradeDetailInfoAdapter.setOnCLickItemListener(new TradeDetailInfoAdapter.onCLickItemListener() {
                                        @Override
                                        public void click(int position, String colname, View view) {
                                            if(colname.equals("rightContent")){
                                                if(dataBeanList!=null && dataBeanList.size()>0 && dataBeanList.get(position).getUrl()!=null){

                                                    String url=dataBeanList.get(position).getUrl();
                                                    if(!Func.IsStringEmpty(url)){
                                                        url=url+"&token="+ Rc.id_tokenparam;
                                                    }
                                                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"销售订单");
                                                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                                                }

                                            }
                                        }
                                    });
                                    rv_content.setAdapter(tradeDetailInfoAdapter);
                                }

                                showProcessBar(100);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                                showProcessBar(100);
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });
            }
        };
        thread.start();
    }
    private void getTradetelemoneyInfoList(final long id) {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("receipt-tracking/find_read_bean/"+id)
                        .tag("request_get_sales-billinl")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<CustomerTradeDetailBean>() {
                            @Override
                            public void onSuccess(CustomerTradeDetailBean data) {
                                JiuyiLog.e("getsig","request onSuccess!");
                                if(data!=null){
                                    dataBeanList=data.getContent().get(0).getData();
                                    customerTradeDetailBean=data;
                                    tradeDetailInfoAdapter = new TradeDetailInfoAdapter(R.layout.customer_tradedetailinfo, dataBeanList);
                                    List<AttachmentBean> attachmentBeanList=data.getContent().get(0).getAttachmentList();
                                    if(attachmentBeanList!=null && attachmentBeanList.size()>0){
                                        Bimp.tempSelectBitmap.clear();
                                        for(int i=0;i<attachmentBeanList.size();i++){
                                            AttachmentBean attachmentBean=attachmentBeanList.get(i);
                                            ImageBean returnPhoto = new ImageBean();
                                            if(!Func.IsStringEmpty(attachmentBean.getThumbnail())){
                                                returnPhoto.setThumbnailPath(attachmentBean.getThumbnail());
                                            }
                                            if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                                returnPhoto.setQiniuKey(attachmentBean.getQiniuKey());
                                            }
                                            if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                                                returnPhoto.setImgUrl(attachmentBean.getUrl());
                                            }
                                            Bimp.tempSelectBitmap.add(returnPhoto);
                                        }
                                        adapter.notifyDataSetChanged();
                                        if(mtvcount!=null){
                                            mtvcount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
                                        }
                                    }


                                    rv_content.setAdapter(tradeDetailInfoAdapter);
                                }

                                showProcessBar(100);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                                showProcessBar(100);
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });
            }
        };
        thread.start();
    }

    private void getInvoiceInfoList(final long id) {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("sales-billing/find_read_bean/"+id)
                        .tag("request_get_sales-billinl")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<CustomerTradeDetailBean>() {
                            @Override
                            public void onSuccess(CustomerTradeDetailBean data) {
                                JiuyiLog.e("getsig","request onSuccess!");
                                if(data!=null){
                                    dataBeanList=data.getContent().get(0).getData();
                                    List<AttachmentBean> attachmentBeanList=data.getContent().get(0).getAttachmentList();
                                    customerTradeDetailBean=data;
                                    tradeDetailInfoAdapter = new TradeDetailInfoAdapter(R.layout.customer_tradedetailinfo, dataBeanList);
                                    if(attachmentBeanList!=null && attachmentBeanList.size()>0){
                                        Bimp.tempSelectBitmap.clear();
                                        for(int i=0;i<attachmentBeanList.size();i++){
                                            AttachmentBean attachmentBean=attachmentBeanList.get(i);
                                            ImageBean returnPhoto = new ImageBean();
                                            if(!Func.IsStringEmpty(attachmentBean.getThumbnail())){
                                                returnPhoto.setThumbnailPath(attachmentBean.getThumbnail());
                                            }
                                            if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                                returnPhoto.setQiniuKey(attachmentBean.getQiniuKey());
                                            }
                                            if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                                                returnPhoto.setImgUrl(attachmentBean.getUrl());
                                            }
                                            Bimp.tempSelectBitmap.add(returnPhoto);
                                        }
                                        adapter.notifyDataSetChanged();
                                        if(mtvcount!=null){
                                            mtvcount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
                                        }
                                    }

                                    rv_content.setAdapter(tradeDetailInfoAdapter);
                                }

                                showProcessBar(100);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                showProcessBar(100);
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });
            }
        };
        thread.start();
    }

    private void getTradebankstatementInfoList(final long id) {
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.GET("balance-account/detail-mobile/"+id)
                        .tag("request_get_sales-billinl")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<CustomerTradeDetailBean>() {
                            @Override
                            public void onSuccess(CustomerTradeDetailBean data) {
                                JiuyiLog.e("getsig","request onSuccess!");
                                if(data!=null){
                                    dataBeanList=data.getContent().get(0).getData();
                                    customerTradeDetailBean=data;
                                    tradeDetailInfoAdapter = new TradeDetailInfoAdapter(R.layout.customer_tradedetailinfo, dataBeanList);
                                    List<AttachmentBean> attachmentBeanList=data.getContent().get(0).getAttachmentList();
                                    if(attachmentBeanList!=null && attachmentBeanList.size()>0){

                                        Bimp.tempSelectBitmap.clear();
                                        for(int i=0;i<attachmentBeanList.size();i++){
                                            AttachmentBean attachmentBean=attachmentBeanList.get(i);
                                            ImageBean returnPhoto = new ImageBean();
                                            if(!Func.IsStringEmpty(attachmentBean.getThumbnail())){
                                                returnPhoto.setThumbnailPath(attachmentBean.getThumbnail());
                                            }
                                            if(!Func.IsStringEmpty(attachmentBean.getQiniuKey())){
                                                returnPhoto.setQiniuKey(attachmentBean.getQiniuKey());
                                            }
                                            if(!Func.IsStringEmpty(attachmentBean.getUrl())){
                                                returnPhoto.setImgUrl(attachmentBean.getUrl());
                                            }
                                            Bimp.tempSelectBitmap.add(returnPhoto);
                                        }
                                        adapter.notifyDataSetChanged();
                                        if(mtvcount!=null){
                                            mtvcount.setText(Bimp.tempSelectBitmap.size()+ "/" + 9 );
                                        }
                                    }
                                    rv_content.setAdapter(tradeDetailInfoAdapter);
                                }

                                showProcessBar(100);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                showProcessBar(100);
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
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
                JiuyiHttp.GET("qiniu/upload-token")
                        .tag("request_upload-token_1")
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<UploadTokenBean>() {
                            @Override
                            public void onSuccess(UploadTokenBean returndata) {
                                String qimiuToken="";
                                if(returndata!=null){
                                    qimiuToken=returndata.getContent();
                                }
                                if (Func.IsStringEmpty(qimiuToken)) {
                                    return;
                                }
                                if(qimiuToken.contains("\"")){
                                    qimiuToken=qimiuToken.replace("\"","");
                                }
                                attachmentsBeanslist=new ArrayList<AttachmentBean>();
                                for(int j=0;j<Bimp.tempSelectBitmap.size();j++){
                                    ImageBean imageBean=Bimp.tempSelectBitmap.get(j);
                                    String data="";
                                    String key = null;
                                    if(!Func.IsStringEmpty(imageBean.getQiniuKey())){
                                        AttachmentBean attachmentsBean=new AttachmentBean();
                                        attachmentsBean.setQiniuKey(imageBean.getQiniuKey());
                                        attachmentsBeanslist.add(attachmentsBean);
                                        if(attachmentsBeanslist!=null && attachmentsBeanslist.size()>0){
                                            if(attachmentsBeanslist.size()==Bimp.tempSelectBitmap.size()){
                                                addAttachmentBean.setAttachmentList(attachmentsBeanslist);
                                                updateAttachmentInfo();
                                            }
                                        }
                                        continue;
                                    }else {
                                        if(!Func.IsStringEmpty(imageBean.getPath())) {
                                            data = imageBean.getPath();
                                            uploadManager.put(data, key, qimiuToken,
                                                    new UpCompletionHandler() {
                                                        @Override
                                                        public void complete(String key, ResponseInfo info, JSONObject res) {
                                                            //res包含hash、key等信息，具体字段取决于上传策略的设置
                                                            if(info.isOK()) {
                                                                AttachmentBean attachmentsBean=new AttachmentBean();
                                                                String qiniukey="";
                                                                if(res!=null){
                                                                    qiniukey=((JSONObject) res).opt("key").toString();
                                                                    attachmentsBean.setQiniuKey(qiniukey);
                                                                    attachmentsBeanslist.add(attachmentsBean);
                                                                }
                                                                //附件上传成功
                                                                if(attachmentsBeanslist!=null && attachmentsBeanslist.size()>0){
                                                                    if(attachmentsBeanslist.size()==Bimp.tempSelectBitmap.size()){
                                                                        addAttachmentBean.setAttachmentList(attachmentsBeanslist);
                                                                        updateAttachmentInfo();
                                                                    }
                                                                }
                                                            } else {
                                                                if(progressDialog!=null){
                                                                    progressDialog.dismiss();
                                                                }
                                                                Log.i("qiniu", "Upload Fail");
                                                                //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                                            }
                                                            Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                                                        }
                                                    }, null);

                                        }
                                    }

                                }


                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                if(progressDialog!=null){
                                    progressDialog.dismiss();
                                }
                                JiuyiLog.e("getsig","request errorCode:" + errCode + ",errorMsg:" + errMsg);
                            }
                        });

            }
        };
        thread.start();
    }
    public void updateAttachmentInfo(){
        if(billType.equals("Tradedelivery")){
            if(billID>0){
               tradedeliverySubmit();
            }
        }else if(billType.equals("Tradeinvoice")){
            if(billID>0){
                invoiceSubmit();
            }
        }else if(billType.equals("Tradetelemoney")){
            if(billID>0){
                tradetelemoneySubmit();
            }
        }else if(billType.equals("Tradebankstatement")){
            if(billID>0){
                tradebankstatementSubmit();
            }
        }
    }

    public void tradedeliverySubmit(){
        JiuyiHttp.POST("sap-invoice/add")
                .setJson(GsonUtil.gson().toJson(addAttachmentBean))
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
    public void invoiceSubmit(){
        JiuyiHttp.POST("sales-billing/add")
                .setJson(GsonUtil.gson().toJson(addAttachmentBean))
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
    public void tradetelemoneySubmit(){
        JiuyiHttp.POST("receipt-tracking/add")
                .setJson(GsonUtil.gson().toJson(addAttachmentBean))
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
    public void tradebankstatementSubmit(){
        JiuyiHttp.POST("balance-account/add")
                .setJson(GsonUtil.gson().toJson(addAttachmentBean))
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

    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction== DialogID.DialogTrue)
        {
            backPage();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // 刷新数据
        if(mtvcomplete!=null){
            mtvcomplete.setVisibility(View.VISIBLE);
        }


    }

}
