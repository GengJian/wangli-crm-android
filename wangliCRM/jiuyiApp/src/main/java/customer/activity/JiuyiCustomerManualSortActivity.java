package customer.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;

public class JiuyiCustomerManualSortActivity extends JiuyiActivityBase {
    Button mbtnagency;
    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customermanualsort_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        mbtnagency=(Button)mBodyLayout.findViewById(Res.getViewID(null,"btn_agency"));
        mbtnagency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtnagency.setBackgroundResource(Res.getDrawabelID(null,"jiuyi_v23_backbutton_bg_normal"));
                mbtnagency.setTextColor(Res.getColor(null, "jiuyi_blue"));
            }
        });
    }
}
