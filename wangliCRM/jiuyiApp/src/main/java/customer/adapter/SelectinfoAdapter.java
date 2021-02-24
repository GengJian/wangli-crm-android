package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;
import java.util.Map;


public class SelectinfoAdapter extends BaseQuickAdapter<Map<String, Object>,BaseViewHolder>  {
    private String viewType="";

    public SelectinfoAdapter(int layoutResId, List<Map<String, Object>> data) {
        super(layoutResId, data);
    }
    public SelectinfoAdapter(int layoutResId, List<Map<String, Object>> data, String viewType) {
        super(layoutResId, data);
        this.viewType=viewType;

    }

    @Override
    protected void convert(final BaseViewHolder holder,final Map<String, Object> item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
        listview_popwind_tv.setText(item.get("name").toString());
        imageViewSelect.setImageResource(R.drawable.client_selected);

    }



    private SelectinfoAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(SelectinfoAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

}
