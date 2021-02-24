package messages.timchat.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.utils.Func;
import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.google.gson.Gson;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.imsdk.ext.message.TIMMessageDraft;
import com.tencent.imsdk.ext.message.TIMMessageLocator;
import com.tencent.qcloud.presentation.presenter.ChatPresenter;
import com.tencent.qcloud.presentation.viewfeatures.ChatView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import customer.Utils.ViewOperatorType;
import customer.entity.MemberAuthorityBean;
import messages.timchat.adapters.jiuyiMessageDetailAdapter;
import messages.timchat.model.CustomMessage;
import messages.timchat.model.FriendProfile;
import messages.timchat.model.FriendshipInfo;
import messages.timchat.model.Message;
import messages.timchat.model.MessageFactory;
import messages.timchat.model.jiuyiCustomerMessageResult;
import messages.timchat.utils.AfterSaleUtils;
import com.control.utils.MarketUtils;
import messages.timchat.utils.NoticeUtils;
import com.control.utils.OrderUtils;
import messages.timchat.utils.PerformaceUtils;
import messages.timchat.utils.PlanUtils;
import com.control.utils.ReceiveUtils;
import messages.timchat.utils.TaskUtils;
import messages.timchat.utils.TimHelperType;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMessageDetailActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 :会话明细界面
 *****************************************************************
 */
public class JiuyiMessageDetailActivity extends JiuyiActivityBase implements ChatView {
    private static final String TAG = "ChatActivity";
    private List<Message> messageList = new ArrayList<>();
    private List<jiuyiCustomerMessageResult> messageResultList = new ArrayList<>();

    private ChatPresenter presenter;
    private String identify;
    private TIMConversationType type;
    private String titleStr;
    private Handler handler = new Handler();
    private RecyclerView mrvlist;
    private jiuyiMessageDetailAdapter adapter;
    private List<String> lookedHistory;
    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_messagedetail"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        identify = getIntent().getStringExtra("identify");
        type = (TIMConversationType) getIntent().getSerializableExtra("type");
        setTitle();
        presenter = new ChatPresenter(this, identify, type);
        mrvlist=(RecyclerView)mBodyLayout.findViewById(Res.getViewID(JiuyiMessageDetailActivity.this,"rv_messagelist"));
        adapter = new jiuyiMessageDetailAdapter(messageResultList);
        mrvlist.setLayoutManager(new LinearLayoutManager(JiuyiMessageDetailActivity.this, 1, false));
        mrvlist.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle mBundle = new Bundle();
                String url="";
//                String url="https://www.baidu.com/";
                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,mTitle);
                changePage(mBundle, 10061,true);
            }
        });

        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showPopMenu(view,position);
                return true;
            }
        });


        adapter.openLoadAnimation(adapter.SLIDEIN_LEFT);

        setContentView(mBodyLayout);
        presenter.start();
    }
    public void setTitle(){
//        if(identify.equals(TimHelperType.ORDER_NOTIFY)){
//            mTitle=TimHelperType.HUAFON_ORDER_NOTIFY_CN;
//            lookedHistory = OrderUtils.getInstance().getSearchList();
//        }else if(identify.equals(TimHelperType.MARKET_NOTIFY)){
//            mTitle=TimHelperType.HUAFON_MARKET_NOTIFY_CN;
//            lookedHistory = MarketUtils.getInstance().getSearchList();
//        }else if(identify.equals(TimHelperType.NOTICE_NOTIFY)){
//            mTitle=TimHelperType.HUAFON_NOTICE_NOTIFY_CN;
//            lookedHistory = NoticeUtils.getInstance().getSearchList();
//        }else if(identify.equals(TimHelperType.AFTER_SALE_NOTIFY)){
//            mTitle=TimHelperType.HUAFON_AFTER_SALE_NOTIFY_CN;
//            lookedHistory = AfterSaleUtils.getInstance().getSearchList();
//        }else if(identify.equals(TimHelperType.PERFORMANCE_NOTIFY)){
//            mTitle=TimHelperType.HUAFON_PERFORMANCE_NOTIFY_CN;
//            lookedHistory = PerformaceUtils.getInstance().getSearchList();
//        }else if(identify.equals(TimHelperType.PLAN_NOTIFY)){
//            mTitle=TimHelperType.HUAFON_PLAN_NOTIFY_CN;
//            lookedHistory = PlanUtils.getInstance().getSearchList();
//        }else if(identify.equals(TimHelperType.RECEIVE_NOTIFY)){
//            mTitle=TimHelperType.HUAFON_RECEIVE_NOTIFY_CN;
//            lookedHistory = ReceiveUtils.getInstance().getSearchList();
//        }else if(identify.equals(TimHelperType.TASK_NOTIFY)){
//            mTitle=TimHelperType.HUAFON_TASK_NOTIFY_CN;
//            lookedHistory = TaskUtils.getInstance().getSearchList();
//        }
        lookedHistory = TaskUtils.getInstance().getSearchList();
        FriendProfile profile = FriendshipInfo.getInstance().getProfile(identify);
        if(profile!=null){
            String name=profile.getName();
            if(!Func.IsStringEmpty(name)){
                mTitle=name;
            }
        }else{
            mTitle=identify;
        }

        super.setTitle(mTitle);
    }

    public void showPopMenu(View view,final int pos){
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_item,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Message message = messageList.get(pos);
                message.remove();
                messageList.remove(pos);
                messageResultList.remove(pos);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }
    @Override
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onPause(){
        super.onPause();
        presenter.readMessages();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    /**
     * 显示消息
     *
     * @param message
     */
    @Override
    public void showMessage(TIMMessage message) {
        if (message == null) {
            adapter.notifyDataSetChanged();
        } else {
            Message mMessage = MessageFactory.getMessage(message);
            if (mMessage != null) {
                if (mMessage instanceof CustomMessage){
                    String messageresult=mMessage.getSummary();/////
                    if(Func.IsStringEmpty(messageresult)||messageresult.length()<20){
                        return;
                    }
                    try {
                        jiuyiCustomerMessageResult resultBean = new Gson().fromJson(messageresult,jiuyiCustomerMessageResult.class);
                        if(resultBean.getFormatType()!=null){
                            if(resultBean.getFormatType().equals(TimHelperType.TYPE_CUSTOM_LIST)){
                                resultBean.setItemType(1);
                            }else if(resultBean.getFormatType().equals(TimHelperType.TYPE_CUSTOM_TEXT)){
                                resultBean.setItemType(2);
                            }else if(resultBean.getFormatType().equals(TimHelperType.TYPE_CUSTOM_CONTENTONLY)){
                                resultBean.setItemType(3);
                            }
                        }else{
                            resultBean.setItemType(2);
                        }
                        messageResultList.add(resultBean);

                    } catch (Exception e) {
                        JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
                    }
                    if (messageList.size()==0){
                        mMessage.setHasTime(null);
                    }else{
                        mMessage.setHasTime(messageList.get(messageList.size()-1).getMessage());
                    }
                    messageList.add((CustomMessage)mMessage);
                    adapter.notifyDataSetChanged();
                    mrvlist.smoothScrollToPosition(adapter.getItemCount()-1);
                }

            }
        }

    }
    /**
     * 显示消息
     *
     * @param messages
     */
    @Override
    public void showMessage(List<TIMMessage> messages) {
        int newMsgNum = 0;
        for (int i = 0; i < messages.size(); i++){
            Message mMessage = MessageFactory.getMessage(messages.get(messages.size()-1-i));
            if (mMessage == null || messages.get(i).status() == TIMMessageStatus.HasDeleted) continue;
            if (mMessage instanceof CustomMessage && (((CustomMessage) mMessage).getType() == CustomMessage.Type.TYPING ))/*||
                    ((CustomMessage) mMessage).getType() == CustomMessage.Type.INVALID))*/ continue;
            ++newMsgNum;
            String messageresult=mMessage.getSummary();/////
            if(Func.IsStringEmpty(messageresult)||messageresult.length()<20){
                continue;
            }
            try {
                jiuyiCustomerMessageResult resultBean = new Gson().fromJson(messageresult,jiuyiCustomerMessageResult.class);
                if(resultBean.getFormatType()!=null){
                    if(resultBean.getFormatType().equals(TimHelperType.TYPE_CUSTOM_LIST)){
                        resultBean.setItemType(1);
                    }else if(resultBean.getFormatType().equals(TimHelperType.TYPE_CUSTOM_TEXT)){
                        resultBean.setItemType(2);
                    }else if(resultBean.getFormatType().equals(TimHelperType.TYPE_CUSTOM_CONTENTONLY)){
                        resultBean.setItemType(3);
                    }
                }else{
                    resultBean.setItemType(2);
                }
                resultBean.setInfoSeq(messages.get(messages.size()-1-i).getSeq());
                messageResultList.add(resultBean);
                if (i != messages.size() - 1){
                    mMessage.setHasTime(messages.get(i+1));
                    messageList.add(i, mMessage);

                }else{
                    mMessage.setHasTime(null);
                    messageList.add(i, mMessage);
                }
            } catch (Exception e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
            }


        }
        adapter = new jiuyiMessageDetailAdapter(messageResultList);
        mrvlist.setLayoutManager(new LinearLayoutManager(JiuyiMessageDetailActivity.this, 1, false));
        mrvlist.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String url="";
                jiuyiCustomerMessageResult CustomerMessageResult=new jiuyiCustomerMessageResult();
                Bundle mBundle = new Bundle();
                if(messageResultList!=null&&messageResultList.size()>position)

                 CustomerMessageResult=(jiuyiCustomerMessageResult)messageResultList.get(position);
                if(CustomerMessageResult!=null){
                    url=CustomerMessageResult.getUrl();
                }
                if(!lookedHistory.contains(CustomerMessageResult.getInfoSeq()+"")){
                    lookedHistory.add(CustomerMessageResult.getInfoSeq()+"");
                }
                TaskUtils.getInstance().saveFile(lookedHistory);
                if(!Func.IsStringEmpty(url)){
                    if(!url.toLowerCase().startsWith("http://action:")){
                        url+="&officeName="+ Rc.deptName;
                        if(CustomerMessageResult.getHelperType().equals(TimHelperType.TASK)){
                            url+="&loginid="+ Rc.id;
                        }
                        url+="&token="+ Rc.id_tokenparam;
                        mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                        mBundle.putString(JiuyiBundleKey.PARAM_TITLE,mTitle);
                        if(!Func.IsStringEmpty(url)){
                            changePage(mBundle, 10061,true);
                        }
                    }else if(url.toLowerCase().startsWith("http://action:2013")){
                        long[] customerID=new long[1];
                        if(Func.getValueByUrl(url,"customreid")!=null) {
                            customerID[0]=Long.parseLong(Func.getValueByUrl(url,"customreid"));
                        }
                        Bundle bundle = new Bundle();
                        bundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerID);
                        if(Func.getValueByUrl(url,"pagetype")!=null && !Func.IsStringEmpty(Func.getValueByUrl(url,"pagetype"))){
                            bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, customerID[0]);
                            bundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Integer.parseInt(Func.getValueByUrl(url,"pagetype").toString()));
                            MemberAuthorityBean.base=true;
                            MemberAuthorityBean.financialRisk=true;
                            MemberAuthorityBean.productionStatus=true;
                            changePage(bundle, Integer.parseInt(Func.getValueByUrl(url,"pagetype").toString()),true);
                        }else{
                            changePage(bundle, Pub.Customer_main,true);
                        }


                    }else if(url.toLowerCase().startsWith("http://action:2631")){
                        if (!Func.IsStringEmpty(url)) {
                            if(Func.getValueByUrl(url,"id")!=null && !Func.IsStringEmpty(Func.getValueByUrl(url,"id"))){
                                mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,Long.parseLong(Func.getValueByUrl(url,"id")));
                            }
                            if(Func.getValueByUrl(url,"viewType")!=null && !Func.IsStringEmpty(Func.getValueByUrl(url,"viewType"))){
                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,Func.getValueByUrl(url,"viewType").toUpperCase());
                            }
                            if(Func.getValueByUrl(url,"backPageType")!=null && !Func.IsStringEmpty(Func.getValueByUrl(url,"backPageType"))){
                                mBundle.putString(JiuyiBundleKey.PARAM_BACKPAGETYPE,Func.getValueByUrl(url,"backPageType"));
                            }
                            if(Func.getValueByUrl(url,"title")!=null && !Func.IsStringEmpty(Func.getValueByUrl(url,"title"))){
                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,Func.getValueByUrl(url,"title").toUpperCase());
                            }
                            changePage(mBundle, Pub.Customer_newDynamicForm, true);
                        }

                    }else{
                        IsActionPageType(url);
                    }

                }else{
                    adapter.notifyDataSetChanged();
                }


            }
        });


        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showPopMenu(view,position);
                return true;
            }
        });
        mrvlist.scrollToPosition(adapter.getItemCount()-1);
//        adapter.notifyDataSetChanged();

    }

    public boolean IsActionPageType(String strUrl)
    {
        if(Func.IsStringEmpty(strUrl)){
            return false;
        }

        if (strUrl != null && strUrl.length() > 0)
        {
            String strUrlStart = "http://action:";
            if(!Func.IsStringEmpty(strUrlStart) && strUrl.toLowerCase().startsWith(strUrlStart)){
                if (strUrl.endsWith("/"))
                {
                    strUrl = strUrl.substring(0, strUrl.length() - 1);
                }

                strUrl = strUrl.substring(strUrlStart.length(), strUrl.length());

                int nWenHaoPos = strUrl.indexOf("?");
                String strAction = null;

                if (nWenHaoPos > 0)
                {
                    strAction = strUrl.substring(0, nWenHaoPos);
                    strUrl = strUrl.substring(nWenHaoPos + 1, strUrl.length());
                }
                else
                {
                    strAction = strUrl;
                }

                if (strAction.indexOf("/") > 0)
                {
                    strAction = strAction.substring(0, strAction.indexOf("/"));
                }

                int nAction = 0;
                strAction = strAction.trim();
                if (strAction != null && strAction.length() > 0)
                {
                    nAction = Func.parseInt(strAction);
                }
                if (nAction > 0)
                {
                    if (!Func.IsStringEmpty(strUrl)) {
                        if( !Func.IsStringEmpty(Func.getValueByUrl(strUrl,"id"))){
                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,Long.parseLong(Func.getValueByUrl(strUrl,"id")));
                        }
                        if(!Func.IsStringEmpty(Func.getValueByUrl(strUrl,"viewType"))){
                            mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE,Func.getValueByUrl(strUrl,"viewType").toUpperCase());
                        }
                        if( !Func.IsStringEmpty(Func.getValueByUrl(strUrl,"title"))){
                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE,Func.getValueByUrl(strUrl,"title").toUpperCase());
                        }
                        changePage(mBundle, nAction, true);
                    }

                }

                return true;
            }
        }

        return false;
    }
    @Override
    public void showRevokeMessage(TIMMessageLocator timMessageLocator) {
//        for (Message msg : messageList) {
//            TIMMessageExt ext = new TIMMessageExt(msg.getMessage());
//            if (ext.checkEquals(timMessageLocator)) {
//                adapter.notifyDataSetChanged();
//            }
//        }
    }


    /**
     * 清除所有消息，等待刷新
     */
    @Override
    public void clearAllMessage() {
        messageList.clear();
    }

    /**
     * 发送消息成功
     *
     * @param message 返回的消息
     */
    @Override
    public void onSendMessageSuccess(TIMMessage message) {
        showMessage(message);
    }

    @Override
    public void onSendMessageFail(int code, String desc, TIMMessage message) {

    }

    @Override
    public void sendImage() {

    }

    @Override
    public void sendPhoto() {

    }

    @Override
    public void sendText() {

    }

    @Override
    public void sendFile() {

    }

    @Override
    public void sendClient() {

    }

    @Override
    public void sendContacts() {

    }

    @Override
    public void sendOrder() {

    }

    @Override
    public void sendTask() {

    }

    @Override
    public void startSendVoice() {

    }

    @Override
    public void endSendVoice() {

    }

    @Override
    public void sendVideo(String fileName) {

    }

    @Override
    public void cancelSendVoice() {

    }

    @Override
    public void sending() {

    }

    @Override
    public void showDraft(TIMMessageDraft draft) {

    }

    @Override
    public void videoAction() {

    }

    @Override
    public void showToast(String msg) {

    }
}
