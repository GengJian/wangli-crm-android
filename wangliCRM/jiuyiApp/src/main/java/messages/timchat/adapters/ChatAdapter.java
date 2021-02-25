package messages.timchat.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jiuyi.app.JiuyiMainApplication;
import com.loader.ILoader;
import com.loader.LoaderManager;
import com.tencent.qcloud.sdk.Constant;
import com.wanglicrm.android.R;

import java.util.List;

import messages.timchat.model.CustomMessage;
import messages.timchat.model.FriendProfile;
import messages.timchat.model.FriendshipInfo;
import messages.timchat.model.Message;

/**
 * 聊天界面adapter
 */
public class ChatAdapter extends ArrayAdapter<Message> {

    private final String TAG = "ChatAdapter";

    private int resourceId;
    private View view;
    private ViewHolder viewHolder;
    private String identifyID;

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int position) {
        return view != null ? view.getId() : position;
    }

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public ChatAdapter(Context context, int resource, List<Message> objects,String identify) {
        super(context, resource, objects);
        resourceId = resource;
        identifyID=identify;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null){
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }else{
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.leftMessage = (RelativeLayout) view.findViewById(R.id.leftMessage);
            viewHolder.rightMessage = (RelativeLayout) view.findViewById(R.id.rightMessage);
            viewHolder.leftPanel = (RelativeLayout) view.findViewById(R.id.leftPanel);
            viewHolder.rightPanel = (RelativeLayout) view.findViewById(R.id.rightPanel);
            viewHolder.sending = (ProgressBar) view.findViewById(R.id.sending);
            viewHolder.error = (ImageView) view.findViewById(R.id.sendError);
            viewHolder.sender = (TextView) view.findViewById(R.id.sender);
            viewHolder.rightDesc = (TextView) view.findViewById(R.id.rightDesc);
            viewHolder.systemMessage = (TextView) view.findViewById(R.id.systemMessage);
            viewHolder.leftAvatar = (SimpleDraweeView) view.findViewById(R.id.leftAvatar);
            viewHolder.rightAvatar = (SimpleDraweeView) view.findViewById(R.id.rightAvatar);
            viewHolder.leftMessage2 = (LinearLayout) view.findViewById(R.id.leftMessage2);
            viewHolder.rightMessage2 = (LinearLayout) view.findViewById(R.id.rightMessage2);
            viewHolder.tv_titleValueleft = (TextView) view.findViewById(R.id.tv_titleValueleft);
            viewHolder.tv_titleValueright = (TextView) view.findViewById(R.id.tv_titleValueright);
            viewHolder.iv_iconleft = (ImageView) view.findViewById(R.id.iv_iconleft);
            viewHolder.iv_iconright = (ImageView) view.findViewById(R.id.iv_iconright);
            viewHolder.tv_typeValueleft = (TextView) view.findViewById(R.id.tv_typeValueleft);
            viewHolder.tv_typeValueright = (TextView) view.findViewById(R.id.tv_typeValueright);
            viewHolder.sender = (TextView) view.findViewById(R.id.sender);
            view.setTag(viewHolder);
        }
        if (position < getCount()){
            final Message data = getItem(position);
            data.showMessage(viewHolder, getContext());
            if(data instanceof CustomMessage){
                if(((CustomMessage) data).getType().name().equals("CLIENT")){
                    if(data.isSelf()){
                        viewHolder.rightMessage2.setVisibility(View.VISIBLE);
                        viewHolder.tv_titleValueright.setText(data.getSummary());
                        viewHolder.rightMessage.setVisibility(View.GONE);
                        viewHolder.tv_typeValueright.setText(((CustomMessage) data).getTypeValue());
                        viewHolder.iv_iconright.setImageResource(R.drawable.news_client_small);
                        viewHolder.rightMessage2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                long[] customerID=new long[1];
                                customerID[0]= ((CustomMessage) data).getTitleId();
                                Bundle bundle = new Bundle();
                                bundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerID);
                                bundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.Customer_main);
                                Rc.getIns().getBaseCallTopCallBack().changePage(bundle,2013,true);
                            }
                        });
                    }else{
                        viewHolder.leftMessage2.setVisibility(View.VISIBLE);
                        viewHolder.tv_titleValueleft.setText(data.getSummary());
                        viewHolder.tv_typeValueleft.setText(((CustomMessage) data).getTypeValue());
                        viewHolder.iv_iconleft.setImageResource(R.drawable.news_client_small);
                        viewHolder.leftMessage2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                long[] customerID=new long[1];
                                customerID[0]= ((CustomMessage) data).getTitleId();
                                Bundle bundle = new Bundle();
                                bundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerID);
                                bundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.Customer_main);
                                Rc.getIns().getBaseCallTopCallBack().changePage(bundle,2013,true);
                            }
                        });
                        viewHolder.leftMessage.setVisibility(View.GONE);
                    }
                }else if(((CustomMessage) data).getType().name().equals("CONTACT")){
                    if(data.isSelf()){
                        viewHolder.rightMessage2.setVisibility(View.VISIBLE);
                        viewHolder.tv_titleValueright.setText(data.getSummary());
                        viewHolder.iv_iconright.setImageResource(R.drawable.news_contacts_small);
                        viewHolder.tv_typeValueright.setText(((CustomMessage) data).getTypeValue());
                        viewHolder.rightMessage.setVisibility(View.GONE);
                        viewHolder.rightMessage2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Contact");
                                bundle.putString(JiuyiBundleKey.PARAM_LINKMANBEANID,((CustomMessage) data).getTitleId()+"");
                                bundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Normal_ContactsInfo);
                                Rc.getIns().getBaseCallTopCallBack().changePage(bundle, Pub.Normal_ContactsInfo,true);
                            }
                        });
                    }else{
                        viewHolder.leftMessage2.setVisibility(View.VISIBLE);
                        viewHolder.tv_titleValueleft.setText(data.getSummary());
                        viewHolder.tv_typeValueleft.setText(((CustomMessage) data).getTypeValue());
                        viewHolder.iv_iconleft.setImageResource(R.drawable.news_contacts_small);
                        viewHolder.leftMessage2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Contact");
                                bundle.putString(JiuyiBundleKey.PARAM_LINKMANBEANID,((CustomMessage) data).getTitleId()+"");
                                bundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Normal_ContactsInfo);
                                Rc.getIns().getBaseCallTopCallBack().changePage(bundle, Pub.Normal_ContactsInfo,true);
                            }
                        });
                        viewHolder.leftMessage.setVisibility(View.GONE);
                    }
                }else if(((CustomMessage) data).getType().name().equals("ORDER")){
                    if(data.isSelf()){
                        viewHolder.rightMessage2.setVisibility(View.VISIBLE);
                        viewHolder.tv_titleValueright.setText(/*"订单号:"+*/data.getSummary());
                        viewHolder.rightMessage.setVisibility(View.GONE);
                        viewHolder.tv_typeValueright.setText(((CustomMessage) data).getTypeValue());
                        viewHolder.iv_iconright.setImageResource(R.drawable.news_order_small);
                        viewHolder.rightMessage2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle mBundle = new Bundle();
                                String urladd=Res.getString(null, "jiuyiorderdetail");
                                String url="";

                                if(!Func.IsStringEmpty(urladd)){
                                    url=Constant.BaseH5Url+urladd+"?orderID="+((CustomMessage) data).getTitleId()+"&token="+ Rc.id_tokenparam;
                                }
                                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"销售订单");
                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                            }
                        });
                    }else{
                        viewHolder.leftMessage2.setVisibility(View.VISIBLE);
                        viewHolder.tv_titleValueleft.setText(/*"订单号:"+*/data.getSummary());
                        viewHolder.tv_typeValueleft.setText(((CustomMessage) data).getTypeValue());
                        viewHolder.iv_iconleft.setImageResource(R.drawable.news_order_small);
                        viewHolder.leftMessage2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle mBundle = new Bundle();
                                String urladd=Res.getString(null, "jiuyiorderdetail");
                                String url="";

                                if(!Func.IsStringEmpty(urladd)){
                                    url=Constant.BaseH5Url+urladd+"?orderID="+((CustomMessage) data).getTitleId()+"&token="+ Rc.id_tokenparam;
                                }
                                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"销售订单");
                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                            }
                        });
                        viewHolder.leftMessage.setVisibility(View.GONE);
                    }
                }else if(((CustomMessage) data).getType().name().equals("TASK")){
                    if(data.isSelf()){
                        viewHolder.rightMessage2.setVisibility(View.VISIBLE);
                        viewHolder.tv_titleValueright.setText(data.getSummary());
                        viewHolder.rightMessage.setVisibility(View.GONE);
                        viewHolder.tv_typeValueright.setText(((CustomMessage) data).getTypeValue());
                        viewHolder.iv_iconright.setImageResource(R.drawable.news_task_small);
                        viewHolder.rightMessage2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle mBundle = new Bundle();
                                String urladd=Res.getString(null, "jiuyitaskdetail");
                                String url="";
                                if(!Func.IsStringEmpty(urladd)){
                                    url= Constant.BaseH5Url+urladd+"?loginid="+Rc.id+"&id="+((CustomMessage) data).getTitleId()+"&token="+ Rc.id_tokenparam;
                                }
                                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"任务详情");
                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                            }
                        });
                    }else{
                        viewHolder.leftMessage2.setVisibility(View.VISIBLE);
                        viewHolder.tv_titleValueleft.setText(data.getSummary());
                        viewHolder.iv_iconleft.setImageResource(R.drawable.news_task_small);
                        viewHolder.tv_typeValueleft.setText(((CustomMessage) data).getTypeValue());
                        viewHolder.leftMessage2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle mBundle = new Bundle();
                                String urladd=Res.getString(null, "jiuyitaskdetail");
                                String url="";
                                if(!Func.IsStringEmpty(urladd)){
                                    url=Constant.BaseH5Url+urladd+"?loginid="+Rc.id+"&id="+((CustomMessage) data).getTitleId()+"&token="+ Rc.id_tokenparam;
                                }
                                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                mBundle.putString(JiuyiBundleKey.PARAM_TITLE,"任务详情");
                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,10061);
                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle,10061,true);
                            }
                        });
                        viewHolder.leftMessage.setVisibility(View.GONE);
                    }
                }
            }else {
                if(data.isSelf()){
                    viewHolder.rightMessage2.setVisibility(View.GONE);
                    viewHolder.rightMessage.setVisibility(View.VISIBLE);
                    viewHolder.rightMessage.setBackgroundResource(Res.getDrawabelID(JiuyiMainApplication.getIns(), "bg_bubble_blue"));
                }else{
                    viewHolder.leftMessage2.setVisibility(View.GONE);
                    viewHolder.leftMessage.setVisibility(View.VISIBLE);
                    viewHolder.leftMessage.setBackgroundResource(Res.getDrawabelID(JiuyiMainApplication.getIns(), "bg_bubble_gray"));
                }
            }
            String peer="";

//             peer=((TIMConversation)((TIMMessage)((TextMessage)data).getMessage()).getConversation()).getPeer();
            if(!Func.IsStringEmpty(Rc.tim_identifier)){
                FriendProfile profile = FriendshipInfo.getInstance().getProfile(Rc.tim_identifier);
                if(profile!=null){
                    String headUrl=profile.getAvatarUrl();
                    if(!Func.IsStringEmpty(headUrl)){
                        LoaderManager.getLoader().loadNet(viewHolder.rightAvatar,headUrl, new ILoader.Options(R.drawable.head_me, R.drawable.head_me));
                    }
                }

            }
            if(!Func.IsStringEmpty(identifyID)){
                FriendProfile profile = FriendshipInfo.getInstance().getProfile(identifyID);
                if(profile!=null){
                    String headUrl=profile.getAvatarUrl();
                    if(!Func.IsStringEmpty(headUrl)){
                        LoaderManager.getLoader().loadNet(viewHolder.leftAvatar,headUrl, new ILoader.Options(R.drawable.head_other, R.drawable.head_other));
                    }
                }

            }
        }

        return view;
    }


    public class ViewHolder{
        public RelativeLayout leftMessage;
        public RelativeLayout rightMessage;
        public RelativeLayout leftPanel;
        public RelativeLayout rightPanel;
        public ProgressBar sending;
        public ImageView error;
        public TextView sender;
        public TextView systemMessage;
        public TextView rightDesc;
        public SimpleDraweeView leftAvatar;
        public SimpleDraweeView rightAvatar;
        public LinearLayout leftMessage2;
        public LinearLayout rightMessage2;
        public TextView tv_titleValueleft;
        public TextView tv_titleValueright;
        public ImageView iv_iconleft,iv_iconright;
        public TextView tv_typeValueleft,tv_typeValueright;

    }
}
