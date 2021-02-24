package mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wanglicrm.android.R;

import java.util.List;

import mine.bean.MineScheduleBean;

public class MineScheduleAdapter extends ArrayAdapter<MineScheduleBean> {
    private int resourceId;
    public MineScheduleAdapter(@NonNull Context context, int resource, @NonNull List<MineScheduleBean> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MineScheduleBean mineSchudule = getItem(position);
        View scheduleListView = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView scheduleName = (TextView) scheduleListView.findViewById(R.id.listViewName);
        TextView scheduleTime = (TextView) scheduleListView.findViewById(R.id.listViewTime);
        TextView scheduleState = (TextView) scheduleListView.findViewById(R.id.state);
        scheduleName.setText(mineSchudule.getName());
        scheduleTime.setText(mineSchudule.getTime());
        scheduleState.setText(mineSchudule.getState());
        return scheduleListView;
    }
}
