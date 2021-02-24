package commonlyused.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import commonlyused.bean.TaskBean;


public class TaskListAdapter extends BaseQuickAdapter<TaskBean.ContentBean,BaseViewHolder>  {

    public TaskListAdapter(int layoutResId, List<TaskBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder holder,final TaskBean.ContentBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        ImageView iv_icon= holder.getView(R.id.iv_icon);
        if(item.getTypeKey()!=null){
            if(item.getTypeKey().equals("assingn")){
                iv_icon.setImageResource(R.drawable.task_assign);
            }else if(item.getTypeKey().equals("remind")){
                iv_icon.setImageResource(R.drawable.task_remind);
            }
        }

        TextView tv_createdate = holder.getView(R.id.tv_createdate);
        if(item.getCreatedDate()!=null){
            tv_createdate.setText(item.getCreatedDate().substring(0,10));
        }

        TextView tv_enddate = holder.getView(R.id.tv_enddate);
        if(item.getEndTime()!=null){
            tv_enddate.setText(item.getEndTime());
        }
        TextView tv_status = holder.getView(R.id.tv_status);
        if(item.getStatusValue()!=null){
            tv_status.setText(item.getStatusValue().toString());
        }
        TextView tv_title = holder.getView(R.id.tv_title);
        if(item.getTitle()!=null){
            tv_title.setText(item.getTitle());
        }

        TextView tv_inchargeman = holder.getView(R.id.tv_inchargeman);
        if(item.getReceiver()!=null){
            tv_inchargeman.setText(" 责任人:"+item.getReceiver().getName());
        }
        TextView tv_coordinator = holder.getView(R.id.tv_coordinator);
        String sCollaborators="";
        if(item.getCollaboratorSet()!=null && item.getCollaboratorSet().size()>0){
            for(int i=0;i<item.getCollaboratorSet().size();i++){
                if(item.getCollaboratorSet().get(i).getName()!=null){
                    sCollaborators+=item.getCollaboratorSet().get(i).getName();
                    if(i!=item.getCollaboratorSet().size()-1){
                        sCollaborators+=",";
                    }
                }
            }
        }else{
            sCollaborators="暂无";
        }
        tv_coordinator.setText("协办人:"+sCollaborators);
        String sMessage="";
        TextView tv_creator = holder.getView(R.id.tv_creator);
        if(item.getFounder()!=null){
            tv_creator.setText("创建人:"+item.getFounder().getName());
        }
    }

    private TaskListAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(TaskListAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, TaskListAdapter receiveAddress, View view);
    }

    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<TaskBean.ContentBean> addList) {
        //增加数据
        int position = getData().size();
        getData().addAll(position, addList);
        notifyItemInserted(position);
    }

    public void refresh(List<TaskBean.ContentBean> newList) {
        //刷新数据
        getData().removeAll(getData());
        getData().addAll(newList);
        notifyDataSetChanged();
    }
    public void clear(){
        getData().clear();
        notifyDataSetChanged();
    }
}
