package customer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.control.utils.Res;
import com.wanglicrm.android.R;

public class customerProducttypeAdapter extends BaseAdapter {
    private Context context;
    private String[] strings;
    public static int mPosition;
    //当前Item被点击的位置
    private int currentItem = -1;
    public customerProducttypeAdapter(Context context, String[] strings){
        this.context =context;
        this.strings = strings;
    }
    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }


    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.customer_producttype_item, null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv_productitemtype);
        mPosition = position;
        tv.setText(strings[position]);

        if (currentItem == position) {
            //如果被点击，设置当前TextView被选中
            convertView.setBackgroundResource(R.drawable.customer_selected_bg);
            tv.setTextColor(Res.getColor(null, "jiuyi_blue"));
        } else {
            //如果没有被点击，设置当前TextView未被选中
            convertView.setBackgroundColor(Res.getColor(null, "jiuyi_dividebackgroud_color"));
        }
        return convertView;
    }

}
