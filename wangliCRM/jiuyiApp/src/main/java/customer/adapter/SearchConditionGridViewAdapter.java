package customer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.control.utils.Res;
import com.wanglicrm.android.R;

import java.util.List;

import static com.tencent.qalsdk.service.QalService.context;

public class SearchConditionGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private int selectorPosition;

    public SearchConditionGridViewAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;

    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return mList != null ? position : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_searchconditiongridview, null);
//        RelativeLayout mRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.ll);
        Button button =(Button)convertView.findViewById(R.id.btn_agencynext) ;
        button.setText(mList.get(position));
        //如果当前的position等于传过来点击的position,就去改变他的状态
        if (selectorPosition == position) {
            button.setBackgroundResource(Res.getDrawabelID(null,"jiuyi_v23_backbutton_bg_normal"));
            button.setTextColor(Res.getColor(null, "jiuyi_blue"));
        } else {
            //其他的恢复原来的状态
            button.setBackgroundResource(Res.getDrawabelID(null,"jiuyi_customersearch_normal"));
            button.setTextColor(Res.getColor(null, "jiuyi_title_color"));

        }
        return convertView;
    }


    public void changeState(int pos) {
        selectorPosition = pos;
        notifyDataSetChanged();

    }
}
