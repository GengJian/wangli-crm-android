package messages.timchat.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.wanglicrm.android.R;

import java.util.List;

import messages.timchat.model.FriendFuture;

/**
 * 好友关系链管理消息adapter
 */
public class jiuyiMessageAdapter extends ArrayAdapter<FriendFuture> implements View.OnClickListener {

    private int resourceId;
    private View view;
    private MessageViewHolder viewHolder;
    private InnerItemOnclickListener mListener;


    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public jiuyiMessageAdapter(Context context, int resource, List<FriendFuture> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            view = convertView;
            viewHolder = (MessageViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new MessageViewHolder();
//            viewHolder.avatar = (CircleImageView) view.findViewById(R.id.avatar);
            viewHolder.title = (TextView) view.findViewById(R.id.tvTitle);
            viewHolder.date = (TextView) view.findViewById(R.id.tvDate);
            viewHolder.content = (TextView) view.findViewById(R.id.tvContent1);
            viewHolder.content2 = (TextView) view.findViewById(R.id.tvContent2);
            viewHolder.detailLink = (TextView) view.findViewById(R.id.tvDetailLink);
            view.setTag(viewHolder);
        }
        Resources res = getContext().getResources();
//        final FriendFuture data = getItem(position);
//        viewHolder.avatar.setImageResource(R.drawable.head_other);
//        viewHolder.name.setText(data.getName());
//        viewHolder.des.setText(data.getMessage());

        SpannableString scolor = new SpannableString("22222222");
        //用颜色标记
        scolor.setSpan(new ForegroundColorSpan(Color.RED), 0, scolor.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.content.setText(scolor);

        SpannableString ss = new SpannableString("点击本条信息查看详细信息");
        //用下划线标记文本
        ss.setSpan(new UnderlineSpan(), 0, ss.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        viewHolder.detailLink.setText(ss);
        viewHolder.content.setTextColor(res.getColor(R.color.text_gray1));
        /*viewHolder.detailLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mBundle = new Bundle();
                String url="https://www.baidu.com/";
                mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);

                Intent intent = new Intent(JiuyiMainApplication.getIns(), jiuyiWebviewActivity.class);
//        Intent intent = new Intent(context, jiuyiLoginActivitytest.class);
                JiuyiMainApplication.getIns().startActivity(intent);
//                changePage(mBundle, 10061,true);
            }
        });*/
//        switch (data.getType()){
//            case TIM_FUTURE_FRIEND_PENDENCY_IN_TYPE:
//                viewHolder.status.setText(res.getString(R.string.newfri_agree));
//                viewHolder.status.setTextColor(res.getColor(R.color.text_blue1));
//                viewHolder.status.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        FriendshipManagerPresenter.acceptFriendRequest(data.getIdentify(), new TIMValueCallBack<TIMFriendResult>() {
//                            @Override
//                            public void onError(int i, String s) {
//
//                            }
//
//                            @Override
//                            public void onSuccess(TIMFriendResult timFriendResult) {
//                                data.setType(TIMFutureFriendType.TIM_FUTURE_FRIEND_DECIDE_TYPE);
//                                notifyDataSetChanged();
//                            }
//                        });
//                    }
//                });
//                break;
//            case TIM_FUTURE_FRIEND_PENDENCY_OUT_TYPE:
//                viewHolder.status.setText(res.getString(R.string.newfri_wait));
//                break;
//            case TIM_FUTURE_FRIEND_DECIDE_TYPE:
//                viewHolder.status.setText(res.getString(R.string.newfri_accept));
//                break;
//        }
        return view;
    }


    public class ViewHolder{
        ImageView avatar;
        TextView name;
        TextView des;
        TextView status;
    }
    public class MessageViewHolder{
        TextView title;
        TextView date;
        TextView content;
        TextView content2;
        TextView detailLink;
    }
    public interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
}
