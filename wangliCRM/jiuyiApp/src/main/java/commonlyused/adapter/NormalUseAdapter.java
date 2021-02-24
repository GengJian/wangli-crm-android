package commonlyused.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import commonlyused.bean.NormalItemBean;


public class NormalUseAdapter extends BaseQuickAdapter<NormalItemBean,BaseViewHolder>  {

    public NormalUseAdapter(int layoutResId, List<NormalItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final NormalItemBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView tv_item = holder.getView(R.id.tv_item);
        ImageView iv_itemImage=holder.getView(R.id.iv_itemImage);
        tv_item.setText(item.getItem());
    }

    private NormalUseAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(NormalUseAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, NormalUseAdapter receiveAddress, View view);
    }

}
