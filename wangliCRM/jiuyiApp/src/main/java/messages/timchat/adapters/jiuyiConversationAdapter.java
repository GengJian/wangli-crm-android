package messages.timchat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.JiuyiLog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.wanglicrm.android.R;

import java.util.List;

import messages.timchat.model.Conversation;
import messages.timchat.model.FriendProfile;
import messages.timchat.model.FriendshipInfo;
import messages.timchat.utils.TimHelperType;
import messages.timchat.utils.TimeUtil;

/**
 * 会话界面adapter
 */
public class jiuyiConversationAdapter extends ArrayAdapter<Conversation> {

    private int resourceId;
    private View view;
    private ViewHolder viewHolder;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public jiuyiConversationAdapter(Context context, int resource, List<Conversation> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null){
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }else{
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) view.findViewById(R.id.name);
            viewHolder.avatar = (SimpleDraweeView) view.findViewById(R.id.avatar);
            viewHolder.lastMessage = (TextView) view.findViewById(R.id.last_message);
            viewHolder.time = (TextView) view.findViewById(R.id.message_time);
            viewHolder.unread = (TextView) view.findViewById(R.id.unread_num);
            view.setTag(viewHolder);
        }
        final Conversation data = getItem(position);
        if(!Func.IsStringEmpty(data.getName())){
            viewHolder.tvName.setText(data.getName());
        }
        if(!Func.IsStringEmpty(data.getIdentify()) && !Func.IsStringEmpty(data.getLastMessageSummary())){
            if(TimHelperType.isHelp(data.getIdentify())){
                viewHolder.lastMessage.setText(getsubtitle(data.getLastMessageSummary()));
            }else{
                viewHolder.lastMessage.setText(data.getLastMessageSummary());
            }
        }

        if(data.getIdentify()!=null){
            FriendProfile profile = FriendshipInfo.getInstance().getProfile(data.getIdentify());
            if(profile!=null){
                String headUrl=profile.getAvatarUrl();
                if(!Func.IsStringEmpty(headUrl)){
                    LoaderManager.getLoader().loadNet(viewHolder.avatar,headUrl, new ILoader.Options(R.drawable.head_other, R.drawable.head_other));
                }else{
                    viewHolder.avatar.setImageResource(R.drawable.head_other);
                }
            }else{
                viewHolder.avatar.setImageResource(R.drawable.head_other);
            }
        }
        viewHolder.time.setText(TimeUtil.getTimeStr(data.getLastMessageTime()));
        long unRead = data.getUnreadNum();
        if (unRead <= 0){
            viewHolder.unread.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.unread.setVisibility(View.VISIBLE);
            String unReadStr = String.valueOf(unRead);
            if (unRead < 10){
                viewHolder.unread.setBackground(getContext().getResources().getDrawable(R.drawable.point1));
            }else{
                viewHolder.unread.setBackground(getContext().getResources().getDrawable(R.drawable.point2));
                if (unRead > 99){
                    unReadStr = getContext().getResources().getString(R.string.time_more);
                }
            }
            viewHolder.unread.setText(unReadStr);
        }
        return view;
    }
    private  String getsubtitle(String messageSummary){
        JsonObject jsonObject = new JsonParser().parse(messageSummary).getAsJsonObject();
        if(jsonObject!=null){
            if(jsonObject.get("Summary")!=null){
                String summary=jsonObject.get("Summary").toString();
                if(summary!=null && !Func.IsStringEmpty(summary)){
                    try {
                        JsonObject josummary=new JsonParser().parse(summary).getAsJsonObject();
                        JsonElement jsonObjectsummary=josummary.get("subTitle");
                        if(jsonObjectsummary!=null){
                            return  josummary.get("subTitle").toString().replace("\"","");
                        }else{
                            return  "";
                        }
                    }catch(Exception e){
                        JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
                    }

                }else{
                    return  "";
                }
            }
            return  "";
        }else{
            return  "";
        }
    }

    public class ViewHolder{
        public TextView tvName;
        public SimpleDraweeView avatar;
        public TextView lastMessage;
        public TextView time;
        public TextView unread;

    }
}
