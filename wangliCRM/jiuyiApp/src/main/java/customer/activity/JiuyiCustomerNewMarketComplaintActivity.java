package customer.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;

import customer.entity.MarketDynamicBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewMarketComplaintActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 市场动态新增页面
 *****************************************************************
 */

public class JiuyiCustomerNewMarketComplaintActivity extends JiuyiNewIncludeAttachActivity {
    private TextView mtvcomplete,mtvcancel,mtvdate,tv_producttype;
    private MarketDynamicBean marketDynamicBean;

    @Override
    public void onInit() {
        super.onInit();
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewmarketcomplaint_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        initViews();
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        mtvcancel = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_cancel"));
        if (mtvcancel != null) {
            mtvcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
                }
            });

        }
        mtvcount= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_count"));
    }
    public void setTitle(){
        mTitle = "新建市场动态";
        setTitle(mTitle);
    }



}
