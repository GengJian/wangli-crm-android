package messages.timchat.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiMainApplication;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.imsdk.ext.message.TIMMessageDraft;
import com.tencent.imsdk.ext.message.TIMMessageExt;
import com.tencent.imsdk.ext.message.TIMMessageLocator;
import com.tencent.qcloud.presentation.presenter.ChatPresenter;
import com.tencent.qcloud.presentation.viewfeatures.ChatView;
import com.tencent.qcloud.ui.VoiceSendingView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import customer.activity.JiuyiCustomerSelectActivity;
import messages.timchat.adapters.ChatAdapter;
import messages.timchat.model.CustomMessage;
import messages.timchat.model.FileMessage;
import messages.timchat.model.FriendProfile;
import messages.timchat.model.FriendshipInfo;
import messages.timchat.model.ImageMessage;
import messages.timchat.model.Message;
import messages.timchat.model.MessageFactory;
import messages.timchat.model.TextMessage;
import messages.timchat.model.UGCMessage;
import messages.timchat.model.VideoMessage;
import messages.timchat.model.VoiceMessage;
import messages.timchat.utils.FileUtil;
import messages.timchat.utils.MediaUtil;
import messages.timchat.utils.RecorderUtil;
/**
 * ****************************************************************
 * 文件名称 : JiuyiChatActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 :聊天界面
 *****************************************************************
 */
public class JiuyiChatActivity extends JiuyiActivityBase implements ChatView {

    private static final String TAG = "ChatActivity";

    private List<Message> messageList = new ArrayList<>();
    private ChatAdapter adapter;
    private ListView listView;
    private ChatPresenter presenter;
    private JiuyiChatInput input;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int IMAGE_STORE = 200;
    private static final int FILE_CODE = 300;
    private static final int IMAGE_PREVIEW = 400;
    private static final int VIDEO_RECORD = 500;
    private static final int CLIENT_CODE = 600;
    private static final int CONTACT_CODE = 700;
    private static final int ORDER_CODE = 800;
    private static final int TASK_CODE = 900;
    private Uri fileUri;
    private VoiceSendingView voiceSendingView;
    private String identify;
    private RecorderUtil recorder = new RecorderUtil();
    private TIMConversationType type;
    private String titleStr;
    private Handler handler = new Handler();
    private final int TYPE_CLIENT=1;
    private final int TYPE_CONTACT=2;
    private final int TYPE_ORDER=3;
    private final int TYPE_TASK=4;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_chat"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        setContentView(mBodyLayout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        identify = getIntent().getStringExtra("identify");
        identify=mBundle.getString(JiuyiBundleKey.PARAM_IDENTIFY);
        type = TIMConversationType.C2C;
        FriendProfile profile = FriendshipInfo.getInstance().getProfile(identify);
        mTitle=profile == null?identify:profile.getName();
        titleStr=mTitle;
        setTitle();
        presenter = new ChatPresenter(this, identify, type);
        input = (JiuyiChatInput) findViewById(R.id.input_panel);
        input.setChatView(this);
        adapter = new ChatAdapter(this, R.layout.jiuyi_item_message, messageList,identify);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        input.setInputMode(JiuyiChatInput.InputMode.NONE);
                        break;
                }
                return false;
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int firstItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && firstItem == 0) {
                    //如果拉到顶端读取更多消息
                    presenter.getMessage(messageList.size() > 0 ? messageList.get(0).getMessage() : null);

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                firstItem = firstVisibleItem;
            }
        });
        registerForContextMenu(listView);
        setTitle();
//        TemplateTitle title = (TemplateTitle) findViewById(R.id.chat_title);
//        TextView title = (TextView) findViewById(R.id.jiuyi_titlebar_textview);
//
//        switch (type) {
//            case C2C:
//                title.setText(identify);
//                break;
//        }
        voiceSendingView = (VoiceSendingView) findViewById(R.id.voice_sending);
        presenter.start();
    }
    public void setTitle(){
//        mTitle=identify;
        super.setTitle(mTitle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onPause(){
        super.onPause();
        //退出聊天界面时输入框有内容，保存草稿
        if (input.getText().length() > 0){
            TextMessage message = new TextMessage(input.getText());
            presenter.saveDraft(message.getMessage());
        }else{
            presenter.saveDraft(null);
        }
//        RefreshEvent.getInstance().onRefresh();
        presenter.readMessages();
        MediaUtil.getInstance().stop();
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
                    CustomMessage.Type messageType = ((CustomMessage) mMessage).getType();
                    switch (messageType){
                        case TYPING:
//                            TemplateTitle title = (TemplateTitle) findViewById(R.id.chat_title);
//                            title.setTitleText(getString(R.string.chat_typing));
                            super.setTitle(getString(R.string.chat_typing));
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(!Func.IsStringEmpty(titleStr)){
                                        setTitle(titleStr);
                                    }

                                }
                            }, 2000);
                            break;
                        case CLIENT:
                        case CONTACT:
                        case ORDER:
                        case TASK:
                            if (messageList.size()==0){
                                mMessage.setHasTime(null);
                            }else{
                                mMessage.setHasTime(messageList.get(messageList.size()-1).getMessage());
                            }
                            messageList.add(mMessage);
                            adapter.notifyDataSetChanged();
                            listView.setSelection(adapter.getCount()-1);
                            break;
                        default:
                            break;
                    }
                }else{
                    if (messageList.size()==0){
                        mMessage.setHasTime(null);
                    }else{
                        mMessage.setHasTime(messageList.get(messageList.size()-1).getMessage());
                    }
                    messageList.add(mMessage);
                    adapter.notifyDataSetChanged();
                    listView.setSelection(adapter.getCount()-1);
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
        for (int i = 0; i < messages.size(); ++i){
            Message mMessage = MessageFactory.getMessage(messages.get(i));
            if (mMessage == null || messages.get(i).status() == TIMMessageStatus.HasDeleted) continue;
            if (mMessage instanceof CustomMessage && (((CustomMessage) mMessage).getType() == CustomMessage.Type.TYPING ))/*||
                    ((CustomMessage) mMessage).getType() == CustomMessage.Type.INVALID))*/ continue;
            ++newMsgNum;
            if (i != messages.size() - 1){
                mMessage.setHasTime(messages.get(i+1));
                messageList.add(0, mMessage);
            }else{
                mMessage.setHasTime(null);
                messageList.add(0, mMessage);
            }
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(newMsgNum);
    }

    @Override
    public void showRevokeMessage(TIMMessageLocator timMessageLocator) {
        for (Message msg : messageList) {
            TIMMessageExt ext = new TIMMessageExt(msg.getMessage());
            if (ext.checkEquals(timMessageLocator)) {
                adapter.notifyDataSetChanged();
            }
        }
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

    /**
     * 发送消息失败
     *
     * @param code 返回码
     * @param desc 返回描述
     */
    @Override
    public void onSendMessageFail(int code, String desc, TIMMessage message) {
        long id = message.getMsgUniqueId();
        for (Message msg : messageList){
            if (msg.getMessage().getMsgUniqueId() == id){
                switch (code){
                    case 80001:
                        //发送内容包含敏感词
                        msg.setDesc(getString(R.string.chat_content_bad));
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        }

        adapter.notifyDataSetChanged();

    }

    /**
     * 发送图片消息
     */
    @Override
    public void sendImage() {
        JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
        Intent intent_album = new Intent("android.intent.action.GET_CONTENT");
        intent_album.setType("image/*");
        startActivityForResult(intent_album, IMAGE_STORE);
    }

    /**
     * 发送照片消息
     */
    @Override
    public void sendPhoto() {
        JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
        Intent intent_photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent_photo.resolveActivity(getPackageManager()) != null) {
            String filePath="";

            File tempFile = FileUtil.getTempFile(FileUtil.FileType.IMG);
            filePath=tempFile.getPath();
            Rc.picVideoUrl =filePath;
//            if (tempFile != null) {
//                fileUri = Uri.fromFile(tempFile);
//            }
            if (Build.VERSION.SDK_INT < 24) {
                // 从文件中创建uri
                fileUri = Uri.fromFile(tempFile);
            } else {
                //兼容android7.0 使用共享文件的形式
                fileUri = FileProvider.getUriForFile(JiuyiMainApplication.getIns(), "com.wanglicrm.android.fileProvider", tempFile );
            }
            intent_photo.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent_photo, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    /**
     * 发送文本消息
     */
    @Override
    public void sendText() {
        Message message = new TextMessage(input.getText());
        presenter.sendMessage(message.getMessage());
        input.setText("");
    }

    /**
     * 发送文件
     */
    @Override
    public void sendFile() {
        JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, FILE_CODE);
    }


    /**
     * 发送客户
     */
    @Override
    public void sendClient() {
        //跳转客户选择窗口
        Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString(JiuyiBundleKey.PARAM_IDENTIFY,identify);
        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CLIENT");
        intent.putExtras(mBundle);
        startActivityForResult(intent, CLIENT_CODE);
    }

    @Override
    public void sendContacts() {
        //跳转联系人选择窗口
        Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString(JiuyiBundleKey.PARAM_IDENTIFY,identify);
        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"CONTACTS");
        intent.putExtras(mBundle);
        startActivityForResult(intent, CONTACT_CODE);
    }

    @Override
    public void sendOrder() {
        //跳转订单选择窗口
        Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString(JiuyiBundleKey.PARAM_IDENTIFY,identify);
        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"ORDER");
        intent.putExtras(mBundle);
        startActivityForResult(intent, ORDER_CODE);
    }

    @Override
    public void sendTask() {
        //跳转订单选择窗口
        Intent intent = new Intent(getApplicationContext(), JiuyiCustomerSelectActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString(JiuyiBundleKey.PARAM_IDENTIFY,identify);
        mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"TASK");
        intent.putExtras(mBundle);
        startActivityForResult(intent, TASK_CODE);
    }


    /**
     * 开始发送语音消息
     */
    @Override
    public void startSendVoice() {
        voiceSendingView.setVisibility(View.VISIBLE);
        voiceSendingView.showRecording();
        recorder.startRecording();

    }

    /**
     * 结束发送语音消息
     */
    @Override
    public void endSendVoice() {
        voiceSendingView.release();
        voiceSendingView.setVisibility(View.GONE);
        recorder.stopRecording();
        if (recorder.getTimeInterval() < 1) {
            Toast.makeText(this, getResources().getString(R.string.chat_audio_too_short), Toast.LENGTH_SHORT).show();
        } else if (recorder.getTimeInterval() > 60) {
            Toast.makeText(this, getResources().getString(R.string.chat_audio_too_long), Toast.LENGTH_SHORT).show();
        } else {
            Message message = new VoiceMessage(recorder.getTimeInterval(), recorder.getFilePath());
            presenter.sendMessage(message.getMessage());
        }
    }

    /**
     * 发送小视频消息
     *
     * @param fileName 文件名
     */
    @Override
    public void sendVideo(String fileName) {
        JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
        Message message = new VideoMessage(fileName);
        presenter.sendMessage(message.getMessage());
    }


    /**
     * 结束发送语音消息
     */
    @Override
    public void cancelSendVoice() {

    }

    /**
     * 正在发送
     */
    @Override
    public void sending() {
        if (type == TIMConversationType.C2C){
            Message message = new CustomMessage(CustomMessage.Type.TYPING);
            presenter.sendOnlineMessage(message.getMessage());
        }
    }

    /**
     * 显示草稿
     */
    @Override
    public void showDraft(TIMMessageDraft draft) {
        input.getText().append(TextMessage.getString(draft.getElems(), this));
    }

    @Override
    public void videoAction() {
        Intent intent = new Intent(this, TCVideoRecordActivity.class);
        startActivityForResult(intent, VIDEO_RECORD);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Message message = messageList.get(info.position);
        menu.add(0, 1, Menu.NONE, getString(R.string.chat_del));
        if (message.isSendFail()){
            menu.add(0, 2, Menu.NONE, getString(R.string.chat_resend));
        }else if (message.getMessage().isSelf()){
            menu.add(0, 4, Menu.NONE, getString(R.string.chat_pullback));
        }
        if (message instanceof ImageMessage || message instanceof FileMessage){
            menu.add(0, 3, Menu.NONE, getString(R.string.chat_save));
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Message message = messageList.get(info.position);
        switch (item.getItemId()) {
            case 1:
                message.remove();
                messageList.remove(info.position);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                messageList.remove(message);
                presenter.sendMessage(message.getMessage());
                break;
            case 3:
                message.save();
                break;
            case 4:
                presenter.revokeMessage(message.getMessage());
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && fileUri != null) {
                showImagePreview(fileUri.getPath());
            }
        } else if (requestCode == IMAGE_STORE) {
            if (resultCode == RESULT_OK && data != null) {
                showImagePreview(FileUtil.getFilePath(this, data.getData()));
            }

        } else if (requestCode == FILE_CODE) {
            if (resultCode == RESULT_OK) {
                sendFile(FileUtil.getFilePath(this, data.getData()));
            }
        } else if (requestCode == IMAGE_PREVIEW){
            if (resultCode == RESULT_OK) {
                boolean isOri = data.getBooleanExtra("isOri",false);
                String path = data.getStringExtra("path");
                File file = new File(path);
                if (file.exists()){
                    final BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(path, options);
                    if (file.length() == 0 && options.outWidth == 0) {
                        Toast.makeText(this, getString(R.string.chat_file_not_exist),Toast.LENGTH_SHORT).show();
                    }else {
                        if (file.length() > 1024 * 1024 * 10){
                            Toast.makeText(this, getString(R.string.chat_file_too_large),Toast.LENGTH_SHORT).show();
                        }else{
                            Message message = new ImageMessage(path,isOri);
                            presenter.sendMessage(message.getMessage());
                        }
                    }
                }else{
                    Toast.makeText(this, getString(R.string.chat_file_not_exist),Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == VIDEO_RECORD) {
            if (resultCode == RESULT_OK) {
                String videoPath = data.getStringExtra("videoPath");
                String coverPath = data.getStringExtra("coverPath");
                long duration = data.getLongExtra("duration", 0);
                Message message = new UGCMessage(videoPath, coverPath, duration);
                presenter.sendMessage(message.getMessage());
            }
        } else if (requestCode == CLIENT_CODE) {
            if(data == null || data.getExtras() == null) {
                return;
            }
            Bundle bundle = data.getExtras();
            long Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
            String CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
            Message message = new CustomMessage(TYPE_CLIENT,TYPE_CLIENT,"客户信息",CustomerName,Customerid);
            presenter.sendMessage(message.getMessage());
        }else if (requestCode == CONTACT_CODE) {
            if(data == null || data.getExtras() == null) {
                return;
            }
            Bundle bundle = data.getExtras();
            long Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
            String CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
            Message message = new CustomMessage(TYPE_CONTACT,TYPE_CONTACT,"联系人信息",CustomerName,Customerid);
            presenter.sendMessage(message.getMessage());
        }else if (requestCode == ORDER_CODE) {
            if(data == null || data.getExtras() == null) {
                return;
            }
            Bundle bundle = data.getExtras();
            long Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
            String CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
            Message message = new CustomMessage(TYPE_ORDER,TYPE_ORDER,"订单信息","订单号:"+CustomerName,Customerid);
            presenter.sendMessage(message.getMessage());
        }else if (requestCode == TASK_CODE) {
            if(data == null || data.getExtras() == null) {
                return;
            }
            Bundle bundle = data.getExtras();
            Long Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
            String CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
            Message message = new CustomMessage(TYPE_TASK,TYPE_TASK,"任务信息","任务名称:"+CustomerName,Customerid);
            presenter.sendMessage(message.getMessage());
        }



    }


    private void showImagePreview(String path){
        JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
        if (path == null) return;

        if(!Func.IsStringEmpty(Rc.picVideoUrl)){
            path=Rc.picVideoUrl;
            Rc.picVideoUrl="";
        }
        Intent intent = new Intent(this, ImagePreviewActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent, IMAGE_PREVIEW);
    }

    private void sendFile(String path){
        if (path == null) return;
        File file = new File(path);
        if (file.exists()){
            if (file.length() > 1024 * 1024 * 10){
                Toast.makeText(this, getString(R.string.chat_file_too_large),Toast.LENGTH_SHORT).show();
            }else{
                Message message = new FileMessage(path);
                presenter.sendMessage(message.getMessage());
            }
        }else{
            Toast.makeText(this, getString(R.string.chat_file_not_exist),Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 将标题设置为对象名称
     */
    private Runnable resetTitle = new Runnable() {
        @Override
        public void run() {
//            TemplateTitle title = (TemplateTitle) findViewById(R.id.chat_title);
//            title.setTitleText(titleStr);
            setTitle();
        }
    };


}
