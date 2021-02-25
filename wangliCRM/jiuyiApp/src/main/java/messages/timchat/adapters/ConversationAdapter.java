package messages.timchat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.control.utils.Func;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tencent.qcloud.ui.CircleImageView;
import com.wanglicrm.android.R;

import java.util.List;

import messages.timchat.model.Conversation;
import messages.timchat.utils.TimHelperType;
import messages.timchat.utils.TimeUtil;

/**
 * 会话界面adapter
 */
public class ConversationAdapter extends ArrayAdapter<Conversation> {

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
    public ConversationAdapter(Context context, int resource, List<Conversation> objects) {
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
            viewHolder.avatar = (CircleImageView) view.findViewById(R.id.avatar);
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
            if(data.getIdentify().equals(TimHelperType.ORDER_NOTIFY) ||data.getIdentify().equals(TimHelperType.RECEIVE_NOTIFY)||data.getIdentify().equals(TimHelperType.PLAN_NOTIFY)||
                    data.getIdentify().equals(TimHelperType.AFTER_SALE_NOTIFY) ||data.getIdentify().equals(TimHelperType.MARKET_NOTIFY)||data.getIdentify().equals(TimHelperType.PERFORMANCE_NOTIFY)||
                    data.getIdentify().equals(TimHelperType.NOTICE_NOTIFY) ||data.getIdentify().equals(TimHelperType.TASK_NOTIFY)){
                viewHolder.lastMessage.setText(getsubtitle(data.getLastMessageSummary()));
            }else{
                viewHolder.lastMessage.setText(data.getLastMessageSummary());
            }
        }
        viewHolder.avatar.setImageResource(data.getAvatar());
//        viewHolder.lastMessage.setText(data.getLastMessageSummary());
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
            String summary=jsonObject.get("Summary").toString();
            if(!Func.IsStringEmpty(summary)){
                JsonObject josummary=new JsonParser().parse(summary).getAsJsonObject();
                JsonElement jsonObjectsummary=josummary.get("subTitle");
                if(jsonObjectsummary!=null){
                    return  josummary.get("subTitle").toString().replace("\"","");
                }else{
                    return  "";
                }
            }else{
                return  "";
            }
        }else{
            return  "";
        }
    }

    public class ViewHolder{
        public TextView tvName;
        public CircleImageView avatar;
        public TextView lastMessage;
        public TextView time;
        public TextView unread;

    }
}
