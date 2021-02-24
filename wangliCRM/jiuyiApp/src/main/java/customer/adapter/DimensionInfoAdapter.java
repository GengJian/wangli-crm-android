package customer.adapter;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.Res;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.CommonLabelValueBean;
import customer.entity.MemberReadBean;
import customer.entity.WorthBean;
import customer.view.CircleBars;


public class DimensionInfoAdapter extends BaseQuickAdapter<WorthBean.DataBean,BaseViewHolder>  {
    private int TOTAL_NUM = 100;

    public DimensionInfoAdapter(int layoutResId, List<WorthBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final WorthBean.DataBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(item.getLeftContent());
        TextView tvValue2 = holder.getView(R.id.tv_value2);
        tvValue2.setText(item.getRightContent()+"");
        TextView tvValue = holder.getView(R.id.tv_value);
        tvValue.setText(item.getRightContent()+"");
        LinearLayout ll_bar=holder.getView(R.id.ll_bar);
        View firstBar = holder.getView(R.id.first_bar);
        int first=0;
        if(!Func.IsStringEmpty(item.getRightContent()) && item.isType() ){
            if(item.getRightContent().indexOf(".")>=0){
                first= Integer.parseInt(item.getRightContent().substring(0,item.getRightContent().indexOf(".")));
            }else{
                first= Integer.parseInt(item.getRightContent());
            }
        }

        int length = Res.Dip2Pix(170);
        float count1 = (float)first/TOTAL_NUM * (float)length;
        setWidth(firstBar,(int)count1);
        if(item.isType()){
            tvValue2.setVisibility(View.GONE);
            ll_bar.setVisibility(View.VISIBLE);
            tvValue.setVisibility(View.VISIBLE);
        }else{
            tvValue2.setVisibility(View.VISIBLE);
            ll_bar.setVisibility(View.GONE);
            tvValue.setText("--");
            tvValue.setVisibility(View.VISIBLE);
        }
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
