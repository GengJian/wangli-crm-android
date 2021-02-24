package customer.adapter;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.Res;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CommonLabelValueBean;


public class BusinessTransferAdapter extends BaseQuickAdapter<CommonLabelValueBean,BaseViewHolder>  {
    private int TOTAL_NUM = 100;

    public BusinessTransferAdapter(int layoutResId, List<CommonLabelValueBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final CommonLabelValueBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(item.getName());
        TextView tvValue = holder.getView(R.id.tv_value);
        tvValue.setText(item.getValue());
    }



    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }



    private void setWidth(View view, int width)
    {
        startAnimation(view,width);
//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        layoutParams.width  = width;
//        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//        view.setLayoutParams(layoutParams);
    }
    private void startAnimation(View view, int width)
    {
        ViewWrapper viewWrapper = new ViewWrapper(view);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewWrapper,"width",width);
        objectAnimator.setDuration(1000).start();
    }

    public class ViewWrapper
    {
        View view;
        ViewWrapper(View button)
        {
            this.view = button;
        }

        public void setWidth(int width)
        {
            view.getLayoutParams().width = width;
            view.requestLayout();
        }

        public int getWidth()
        {
            return  view.getLayoutParams().width;
        }

    }
}
