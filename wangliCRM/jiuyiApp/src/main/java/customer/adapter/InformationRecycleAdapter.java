package customer.adapter;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import customer.entity.TradedeliveryBean;
import customer.view.DrawableTextView;
import customer.view.jiuyiRecycleViewDivider;
import lib.demo.spinner.MaterialSpinner;

/**
 */
public class InformationRecycleAdapter extends RecyclerView.Adapter<InformationRecycleAdapter.MyViewHolder> {
    private Context context;
//    private IntelligenceDetailVideoAdapter intelligenceDetailVideoAdapter;

    public List<TradedeliveryBean.ContentBean> getList() {
        return list;
    }

    public void setList(List<TradedeliveryBean.ContentBean> list) {
        this.list = list;
    }

    private List<TradedeliveryBean.ContentBean> list;

    public InformationRecycleAdapter(Context context, List<TradedeliveryBean.ContentBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.jiuyi_customerinfomationitem_layout, parent,
                false));
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.ck_select.setChecked(list.get(position).isSelect);
        holder.ck_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    list.get(position).setSelect(true);
                }else{
                    list.get(position).setSelect(false);
                }
            }
        });
//        MaterialSpinner spinner = holder.getView(R.id.type1);
        List<String> dataset1 = new LinkedList<>(Arrays.asList("10","11","12","13","14","15","16","17","18","19"));
        holder.type1.setItems(dataset1);
        holder.type1.setSelectedIndex(0);
        holder.type1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int pos, long id, String item) {
                Toast.makeText(JiuyiMainApplication.getIns(), "111", Toast.LENGTH_SHORT).show();
            }
        });

        holder.type1.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                spinner.getSelectedIndex();
                Toast.makeText(JiuyiMainApplication.getIns(), "222", Toast.LENGTH_SHORT).show();
            }
        });
        holder.intelligenceDetailVideoAdapter = new IntelligenceDetailVideoAdapter(JiuyiMainApplication.getIns(),list.get(holder.getLayoutPosition()).getVideoList());
        holder.rv_videolist.setAdapter(holder.intelligenceDetailVideoAdapter);
        holder.intelligenceDetailVideoAdapter.setOnRecyclerViewItemListener(new IntelligenceDetailVideoAdapter.OnRecyclerViewItemListener(){
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(JiuyiMainApplication.getIns(), "确认删除", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.intelligenceDetailVideoAdapter.setOnCLickItemListener(new IntelligenceDetailVideoAdapter.onCLickItemListener() {
//            @Override
//            public void click(int position, String colname, View view) {
//                if(colname.equals("img_delete")){
//                    Toast.makeText(JiuyiMainApplication.getIns(), "确认删除", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
        holder.tv_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_video",v);
                }
            }
        });
        holder.tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"tv_test",v);
                }
            }
        });
    }
    private InformationRecycleAdapter.onCLickItemListener listener;
    @Override
    public int getItemCount() {
        return list.size();
    }
    //  添加数据
    public void addData(int position) {
//      在list中添加数据，并通知条目加入一条
//        list.add(position, "我是商品" + position);
        //添加动画
        notifyItemInserted(position);
    }
    //  删除数据
    public void removeData(int position) {
        list.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TradedeliveryBean.ContentBean> addList) {
        //增加数据
//        int position = list.size();
        int position = 0;
        list.addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TradedeliveryBean.ContentBean> newList) {
        //刷新数据
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
    /**
     * ViewHolder的类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv, tv_test;
        CheckBox ck_select;
        MaterialSpinner type1;
        RecyclerView rv_videolist;
        DrawableTextView tv_video;
        IntelligenceDetailVideoAdapter intelligenceDetailVideoAdapter;
        //因为删除有可能会删除中间条目，然后会造成角标越界，所以必须整体刷新一下！
        public MyViewHolder(View view) {
            super(view);
            ck_select= (CheckBox) view.findViewById(R.id.ck_select);
//            tv_test= (TextView) view.findViewById(R.id.tv_test);
            type1= (MaterialSpinner) view.findViewById(R.id.type1);
            tv_video= (DrawableTextView) view.findViewById(R.id.tv_video);
            rv_videolist= (RecyclerView) view.findViewById(R.id.rv_videolist);
            GridLayoutManager mgr = new GridLayoutManager(JiuyiMainApplication.getIns(),5);
            rv_videolist.setLayoutManager(mgr);
            rv_videolist.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, JiuyiMainApplication.getIns().getResources().getColor(R.color.background)));
//            rv_videolist.addItemDecoration(new ListViewDecoration());
           /* intelligenceDetailVideoAdapter = new IntelligenceDetailVideoAdapter(JiuyiMainApplication.getIns(),list.get(getLayoutPosition()).getVideoList());
            rv_videolist.setAdapter(intelligenceDetailVideoAdapter);
            intelligenceDetailVideoAdapter.setOnCLickItemListener(new IntelligenceDetailVideoAdapter.onCLickItemListener() {
                @Override
                public void click(int position, String colname, View view) {
                    if(colname.equals("img_delete")){
                        Toast.makeText(JiuyiMainApplication.getIns(), "确认删除", Toast.LENGTH_SHORT).show();
                    }

                }
            });*/
        }
    }
    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }
    public void  setOnCLickItemListener(InformationRecycleAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }
}
