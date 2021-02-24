package mine.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mine.adapter.MineScheduleAdapter;
import mine.bean.MineScheduleBean;

public class JiuyiMineScheduleActivity extends JiuyiActivityBase {
    private TextView tvDate,tvHeader;
    private CalendarView mCalendarView;
    private ListView listView;
    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_mineschedule_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        tvDate = (TextView) mBodyLayout.findViewById(R.id.tv_date);
        tvHeader = (TextView) mBodyLayout.findViewById(R.id.tv_header);
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月");
        String nowStr = df.format(now);
        tvDate.setText(nowStr);
        mCalendarView = (CalendarView) mBodyLayout.findViewById(R.id.calendarView);
        if (tvDate != null) {
            tvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiMineScheduleActivity.this);
                    //设置上下年分限制
                    dialog.setYearLimt(60);
                    //设置标题
                    dialog.setTitle("选择年月");
                    //设置类型
                    dialog.setType(DateType.TYPE_YM);
                    //设置消息体的显示格式，日期格式
                    dialog.setMessageFormat("yyyy年MM月");
                    //设置选择回调
                    dialog.setOnChangeLisener(null);
                    String startDateStr = tvDate.getText().toString();
                    try {
                        Date startDate = df.parse(startDateStr);
                        dialog.setStartDate(startDate);//设置日期弹出框初始化日期
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //设置点击确定按钮回调
                    dialog.setOnSureLisener(new OnSureLisener() {
                        @Override
                        public void onSure(Date date) {
                            tvDate.setText(df.format(date));
                            String datestr = new SimpleDateFormat("yyyy-MM").format(date);
                            String[] datestrs = datestr.split("-");
                            int year = Integer.valueOf(datestrs[0]);
                            int month = Integer.valueOf(datestrs[1]);
                            mCalendarView.scrollToCalendar(year,month,1);
                            tvHeader.setText(datestr + "-01");
                        }
                    });
                    dialog.show();
                }
            });
        }
        mCalendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                int year = calendar.getYear();
                int month = calendar.getMonth();
                int day = calendar.getDay();
                String yearMonth = String.valueOf(year) + "年" + String.valueOf(month) + "月";
                String yearMonthDay = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
                tvDate.setText(yearMonth);
                tvHeader.setText(yearMonthDay);
            }
        });
        getListViewData();
        ImmersionBar.with(this)
                .transparentBar()
                .init();

    }

    public void setTitle(){
        mTitle = "我的日程";
        setTitle(mTitle);
    }

    public void getListViewData(){
        List<MineScheduleBean> scheduleList = new ArrayList<>();
        MineScheduleBean data1 = new MineScheduleBean();
        MineScheduleBean data2 = new MineScheduleBean();
        data1.setName("前往晶科商务部");
        data1.setTime("09:00-10:00");
        data1.setState("已完成");
        data2.setName("前往晶科商务部技术部(沟通中)");
        data2.setTime("09:00-10:00");
        data2.setState("未完成");
        scheduleList.add(data1);
        scheduleList.add(data2);
        MineScheduleAdapter adapter = new MineScheduleAdapter(JiuyiMineScheduleActivity.this,R.layout.jiuyi_activity_mineschedule_listview_layout,scheduleList);
        listView = (ListView) mBodyLayout.findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
