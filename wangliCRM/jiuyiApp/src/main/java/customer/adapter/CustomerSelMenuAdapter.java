package customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.utils.Res;
import com.wanglicrm.android.R;

import java.util.List;
import java.util.Map;

public class CustomerSelMenuAdapter extends BaseAdapter {
    //当前Item被点击的位置
    private Context mContext;
    private List<Map<String, Object>> mList;

    public CustomerSelMenuAdapter(Context context, List<Map<String, Object>> list) {
        this.mContext = context;
        this.mList = list;
    }

    //当前Item被点击的位置
    private int currentItem = -1;

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_popwin, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置文字 内容
        holder.mTextView.setText(mList.get(position).get("name").toString());
        holder.mImageView.setImageResource(R.drawable.client_selected);
        if (currentItem == position) {
            //如果被点击，设置当前TextView被选中
            holder.mTextView.setSelected(true);
            holder.mTextView.setTextColor(Res.getColor(null, "jiuyi_blue"));
            holder.mImageView.setVisibility(View.VISIBLE);
        } else {
            //如果没有被点击，设置当前TextView未被选中
            holder.mTextView.setSelected(false);
            holder.mTextView.setTextColor(Res.getColor(null,"jiuyi_searchtitle_color"));
            holder.mImageView.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    class ViewHolder {
        TextView mTextView;
        ImageView mImageView;

        public ViewHolder(View convertView) {
            mTextView = (TextView) convertView.findViewById(R.id.listview_popwind_tv);
            mImageView = (ImageView) convertView.findViewById(R.id.iv_client_selected);
        }
    }
}
