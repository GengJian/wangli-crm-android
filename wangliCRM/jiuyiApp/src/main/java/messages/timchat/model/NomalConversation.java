package messages.timchat.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.ext.message.TIMConversationExt;

import messages.timchat.ui.ChatActivity;
import messages.timchat.ui.JiuyiMessageDetailActivity;
import messages.timchat.utils.TimHelperType;


/**
 * 好友或群聊的会话
 */
public class NomalConversation extends Conversation {


    private TIMConversation conversation;



    //最后一条消息
    private Message lastMessage;

    public NomalConversation(TIMConversation conversation){
        this.conversation = conversation;
        type = conversation.getType();
        identify = conversation.getPeer();
    }


    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }


    @Override
    public int getAvatar() {
        switch (type){
            case C2C:
                if(identify.equals(TimHelperType.ORDER_NOTIFY)){
                    return R.drawable.news_orderassistant;
                }else if(identify.equals(TimHelperType.MARKET_NOTIFY)){
                    return R.drawable.news_marketingassistant;
                }else if(identify.equals(TimHelperType.NOTICE_NOTIFY)){
                    return R.drawable.news_announcementassistant;
                }else if(identify.equals(TimHelperType.AFTER_SALE_NOTIFY)){
                    return R.drawable.news_aftersales;
                }else if(identify.equals(TimHelperType.PERFORMANCE_NOTIFY)){
                    return R.drawable.news_performanceassistant;
                }else if(identify.equals(TimHelperType.PLAN_NOTIFY)){
                    return R.drawable.news_planassistant;
                }else if(identify.equals(TimHelperType.RECEIVE_NOTIFY)){
                    return R.drawable.news_dunningassistant;
                }else if(identify.equals(TimHelperType.TASK_NOTIFY)){
                    return R.drawable.news_taskassistant;
                }else{
                    return R.drawable.head_other;
                }
            case Group:
                return R.drawable.head_group;
        }
        return 0;
    }

    /**
     * 跳转到聊天界面或会话详情
     *
     * @param context 跳转上下文
     */
    @Override
    public void navToDetail(Context context) {
        if(TimHelperType.isHelp(identify)){
            Intent intent = new Intent(context, JiuyiMessageDetailActivity.class);
            intent.putExtra("identify", identify);
            intent.putExtra("type", type);
            context.startActivity(intent);
        }else{
            if(type==TIMConversationType.C2C){
                Bundle mBundle = new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_IDENTIFY,identify);
                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.MSG_chat);
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,Pub.MSG_chat,true);
            }else {
                ChatActivity.navToChat(context,identify,type);
            }

        }

    }

    /**
     * 获取最后一条消息摘要
     */
    @Override
    public String getLastMessageSummary(){
        TIMConversationExt ext = new TIMConversationExt(conversation);
        if (ext.hasDraft()){
            TextMessage textMessage = new TextMessage(ext.getDraft());
            if (lastMessage == null || lastMessage.getMessage().timestamp() < ext.getDraft().getTimestamp()){
                return JiuyiMainApplication.getIns().getString(R.string.conversation_draft) + textMessage.getSummary();
            }else{
                return lastMessage.getSummary();
            }
        }else{
            if (lastMessage == null) return "";
            return lastMessage.getSummary();
        }
    }

    /**
     * 获取名称
     */
    @Override
    public String getName() {
        if (type == TIMConversationType.Group){
            name=GroupInfo.getInstance().getGroupName(identify);
            if (name.equals("")) name = identify;
        }else{
            FriendProfile profile = FriendshipInfo.getInstance().getProfile(identify);
            name=profile == null?identify:profile.getName();
/*            TIMFriendshipManager.getInstance().getUsersProfile(Collections.singletonList(identify), new TIMValueCallBack<List<TIMUserProfile>>() {
                @Override
                public void onError(int i, String s) {

                }

                @Override
                public void onSuccess(List<TIMUserProfile> profile) {
//                    friendInfoView.showUserInfo(profile);
                    name=profile == null?identify:profile.get(0).getNickName();
                }
            });*/
        }
        return name;
    }


    /**
     * 获取未读消息数量
     */
    @Override
    public long getUnreadNum(){
        if (conversation == null) return 0;
        TIMConversationExt ext = new TIMConversationExt(conversation);
        return ext.getUnreadMessageNum();
    }

    /**
     * 将所有消息标记为已读
     */
    @Override
    public void readAllMessage(){
        if (conversation != null){
            TIMConversationExt ext = new TIMConversationExt(conversation);
            ext.setReadMessage(null, null);
        }
    }


    /**
     * 获取最后一条消息的时间
     */
    @Override
    public long getLastMessageTime() {
        TIMConversationExt ext = new TIMConversationExt(conversation);
        if (ext.hasDraft()){
            if (lastMessage == null || lastMessage.getMessage().timestamp() < ext.getDraft().getTimestamp()){
                return ext.getDraft().getTimestamp();
            }else{
                return lastMessage.getMessage().timestamp();
            }
        }
        if (lastMessage == null) return 0;
        return lastMessage.getMessage().timestamp();
    }

    /**
     * 获取会话类型
     */
    public TIMConversationType getType(){
        return conversation.getType();
    }
}
