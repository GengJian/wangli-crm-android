package customer.adapter;

import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.MemberCenterBean;


public class CompanyDynamicAdapter extends BaseQuickAdapter<MemberCenterBean.IntelligenceListBean,BaseViewHolder>  {

    public CompanyDynamicAdapter(int layoutResId, List<MemberCenterBean.IntelligenceListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final MemberCenterBean.IntelligenceListBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView leftContent = holder.getView(R.id.tv_dyname);
        TextView rightContent = holder.getView(R.id.tv_dyvalue);
        if(!Func.IsStringEmpty(item.getIntelligenceType())){
            leftContent.setText(item.getIntelligenceType()+":");
        }
        if(!Func.IsStringEmpty(item.getContent())){
            rightContent.setText(item.getContent());
        }
        leftContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_dyname",v);
                }
            }
        });
        rightContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_dyvalue",v);
                }
            }
        });
    }



    private CompanyDynamicAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(CompanyDynamicAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

}
