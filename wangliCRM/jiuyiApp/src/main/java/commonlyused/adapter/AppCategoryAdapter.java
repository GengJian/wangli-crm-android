package commonlyused.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.app.JiuyiMainApplication;
import com.jiuyi.tools.jiuyiViewUtil;
import com.wanglicrm.android.R;

import java.util.List;

import commonlyused.bean.AppCategoryBean;
import customer.view.ListViewDecoration;


public class AppCategoryAdapter extends BaseQuickAdapter<AppCategoryBean,BaseViewHolder>  {
    AppCategoryDetailAdapter detailAdapter;
      public AppCategoryAdapter(int layoutResId, List<AppCategoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final AppCategoryBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());

        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getCategory()!=null){
            tv_title.setText(item.getCategory());
        }


        RecyclerView rv_debtdetail = holder.getView(R.id.rv_categorylist);

        GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(), 4);
        rv_debtdetail.setLayoutManager(mgr);
        rv_debtdetail.addItemDecoration(new ListViewDecoration());

        detailAdapter = new AppCategoryDetailAdapter(JiuyiMainApplication.getIns(), item.getItems());
        rv_debtdetail.setAdapter(detailAdapter);
        onRecyclerItemClickListener2();
    }

    private AppCategoryAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(AppCategoryAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, AppCategoryAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<AppCategoryBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<AppCategoryBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
    public void clear(){
        getData().clear();
        notifyDataSetChanged();
    }

    private void onRecyclerItemClickListener2() {
        detailAdapter.setOnRecyclerViewItemListener(new AppCategoryDetailAdapter.OnRecyclerViewItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {

            }

            @Override
            public void onItemLongClickListener(View view, int position) {
//                Toast.makeText(mCallBack.getActivity(), "onLongClick:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
