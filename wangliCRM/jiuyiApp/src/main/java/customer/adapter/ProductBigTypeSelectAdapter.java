package customer.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.ArrayList;
import java.util.List;

import customer.entity.BrandBean;
import customer.entity.MaterialTypeBean;


public class ProductBigTypeSelectAdapter extends BaseQuickAdapter<BrandBean.ContentBean,BaseViewHolder>  {
    private List<BrandBean.ContentBean> mList;

    public List<BrandBean.ContentBean> getmProductTypeBeanSelectList() {
        return mProductTypeBeanSelectList;
    }

    public void setmProductTypeBeanSelectList(List<BrandBean.ContentBean> mProductTypeBeanSelectList) {
        this.mProductTypeBeanSelectList = mProductTypeBeanSelectList;
    }

    public List<BrandBean.ContentBean> mProductTypeBeanSelectList;

    public ProductBigTypeSelectAdapter(int layoutResId, List<BrandBean.ContentBean> data, List<BrandBean.ContentBean> dataSelect) {
        super(layoutResId, data);
        this.mList=data;
        this.mProductTypeBeanSelectList =dataSelect;
    }
    //重点 设置数据时把点击那里item获取
    public void setList(List<BrandBean.ContentBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();

    }
    @Override
    protected void convert(final BaseViewHolder holder,final BrandBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        final TextView listview_popwind_tv = holder.getView(R.id.listview_popwind_tv);
        final ImageView imageViewSelect = holder.getView(R.id.iv_client_selected);
        String title="";
        if(item.getBrandName()!=null ){
            title=item.getBrandName();

        }
        listview_popwind_tv.setText(title);
        imageViewSelect.setImageResource(R.drawable.client_selected);

        if(mProductTypeBeanSelectList !=null && mProductTypeBeanSelectList.size()>0){
            boolean find=false;
            for(int j = 0; j< mProductTypeBeanSelectList.size(); j++){
                if(mProductTypeBeanSelectList.get(j).getId()==item.getId()){
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
                    if (mProductTypeBeanSelectList != null && mProductTypeBeanSelectList.size() > 0) {
                        for (int j = 0; j < mProductTypeBeanSelectList.size(); j++) {
                            if (mProductTypeBeanSelectList.get(j).getId() == mList.get(holder.getLayoutPosition()).getId()) {
                                find = true;
                                mProductTypeBeanSelectList.remove(j);
                                break;
                            }
                        }
                    }
                } else {
                    mList.get(holder.getLayoutPosition()).setCheck(true);
                    listview_popwind_tv.setSelected(true);
                    imageViewSelect.setVisibility(View.VISIBLE);
                    boolean find = false;
                    if (mProductTypeBeanSelectList != null && mProductTypeBeanSelectList.size() > 0) {
                        for (int j = 0; j < mProductTypeBeanSelectList.size(); j++) {
                            if (mProductTypeBeanSelectList.get(j).getId() == mList.get(holder.getLayoutPosition()).getId()) {
                                find = true;
                                break;
                            }
                        }
                    }else{
                        mProductTypeBeanSelectList =new ArrayList<>();
                    }
                    if(!find){
                        mProductTypeBeanSelectList.add(mList.get(holder.getLayoutPosition()));
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

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<BrandBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<BrandBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }

}
