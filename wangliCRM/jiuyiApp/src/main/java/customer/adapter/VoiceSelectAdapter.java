package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.wanglicrm.android.R;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.ArrayList;
import java.util.List;

import customer.entity.PersonSelectBean;
import customer.entity.VoiceSelectBean;


public class VoiceSelectAdapter extends BaseQuickAdapter<VoiceSelectBean,BaseViewHolder>  {
    private List<VoiceSelectBean> mList;

    public List<VoiceSelectBean> getmPersonBeanSelectList() {
        return mPersonBeanSelectList;
    }

    public void setmPersonBeanSelectList(List<VoiceSelectBean> mPersonBeanSelectList) {
        this.mPersonBeanSelectList = mPersonBeanSelectList;
    }

    public List<VoiceSelectBean> mPersonBeanSelectList;

    public VoiceSelectAdapter(int layoutResId, List<VoiceSelectBean> data, List<VoiceSelectBean> dataSelect) {
        super(layoutResId, data);
        this.mList=data;
        this.mPersonBeanSelectList=dataSelect;
    }
    //重点 设置数据时把点击那里item获取
    public void setList(List<VoiceSelectBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();

    }
    @Override
    protected void convert(final BaseViewHolder holder,final VoiceSelectBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        final TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        final ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
        if(item.getName()!=null ){
            listview_popwind_tv.setText(item.getName());
        }
        imageViewSelect.setImageResource(R.drawable.client_selected);

        if(mPersonBeanSelectList!=null && mPersonBeanSelectList.size()>0){
            boolean find=false;
            for(int j=0;j<mPersonBeanSelectList.size();j++){
                if(mPersonBeanSelectList.get(j).getName()==item.getName()){
                    find=true;
                    break;
                }                                                   }
            item.setCheck(find);
        }
        //点击的item和对应所有数据对应item一致就设置状态
        listview_popwind_tv.setSelected(item.isCheck());
        if(item.isCheck()){
            imageViewSelect.setVisibility(View.VISIBLE);
        }else{
            imageViewSelect.setVisibility(View.INVISIBLE);
        }

        listview_popwind_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listview_popwind_tv.isSelected()) {
                    mList.get(holder.getLayoutPosition()).setCheck(false);
                    imageViewSelect.setVisibility(View.INVISIBLE);
                    listview_popwind_tv.setSelected(false);
                    boolean find = false;
                    if (mPersonBeanSelectList != null && mPersonBeanSelectList.size() > 0) {
                        for (int j = 0; j < mPersonBeanSelectList.size(); j++) {
                            if (mPersonBeanSelectList.get(j).getName() == mList.get(holder.getLayoutPosition()).getName()) {
                                find = true;
                                mPersonBeanSelectList.remove(j);
                                break;
                            }
                        }
                    }
                } else {
                    mList.get(holder.getLayoutPosition()).setCheck(true);
                    listview_popwind_tv.setSelected(true);
                    imageViewSelect.setVisibility(View.VISIBLE);
                    boolean find = false;
                    if (mPersonBeanSelectList != null && mPersonBeanSelectList.size() > 0) {
                        for (int j = 0; j < mPersonBeanSelectList.size(); j++) {
                            if (mPersonBeanSelectList.get(j).getName() == mList.get(holder.getLayoutPosition()).getName()) {
                                find = true;
                                break;
                            }
                        }
                    }else{
                        mPersonBeanSelectList=new ArrayList<>();
                    }

                }

            }
        });


    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

    public  OnItemListener onItemListener;

    public  void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

}
