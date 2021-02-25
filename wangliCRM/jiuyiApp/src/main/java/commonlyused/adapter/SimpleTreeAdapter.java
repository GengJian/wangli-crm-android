package commonlyused.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.control.utils.Func;
import com.wanglicrm.android.R;

import java.util.List;

import commonlyused.treelist.Node;
import commonlyused.treelist.TreeListViewAdapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class SimpleTreeAdapter extends TreeListViewAdapter {

    private Context context;

    public SimpleTreeAdapter(ListView mTree, Context context, List<Node> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand) {
        super(mTree, context, datas, defaultExpandLevel, iconExpand, iconNoExpand);
        this.context = context;
    }

    @Override
    public View getConvertView(final Node node, int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_receiver_select_title, null);
            holder = new ViewHolder();
            holder.ivLeft = (ImageView) view.findViewById(R.id.iv_left);
            holder.tvIcon = (TextView) view.findViewById(R.id.tv_icon);
            holder.tvName = (TextView) view.findViewById(R.id.tv_name);
            holder.tvDuty = (TextView) view.findViewById(R.id.tv_duty);
            holder.tvCount = (TextView) view.findViewById(R.id.tv_count);
            holder.cb = (CheckBox) view.findViewById(R.id.check_box);
            holder.iv = (ImageView) view.findViewById(R.id.iv_right);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }



        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(node, holder.cb.isChecked());
            }
        });


        if (node.isChecked()){
            holder.cb.setChecked(true);
        }else {
            holder.cb.setChecked(false);
        }

        if (node.getIcon() == -1) {
            holder.ivLeft.setVisibility(View.INVISIBLE);
        } else {
            holder.ivLeft.setVisibility(View.VISIBLE);
            holder.ivLeft.setImageResource(node.getIcon());
        }
        if(node.getName().equals("中国王力集团")){
            holder.ivLeft.setImageResource(R.drawable.cn_address_book_company);
            holder.ivLeft.setVisibility(View.VISIBLE);
        }

        holder.tvName.setText(node.getName());
        if(!Func.IsStringEmpty(node.getName())&& node.getIsPeople()==1){
            String sname=node.getName();
            if(sname.length()>2){
//                holder.tvIcon.setText(sname.substring(sname.length()-2,sname.length()));
                if(sname.contains("(") && sname.indexOf("(")>0){
                    String sSubname=sname.substring(0,sname.indexOf("("));
                    if(sSubname.length()>1){
                        holder.tvIcon.setText(sSubname.substring(sSubname.length()-2,sSubname.length()));
                    }

                }else{
                    holder.tvIcon.setText(sname.substring(sname.length()-2,sname.length()));
                }
            }else if(sname.length()>0){
                holder.tvIcon.setText(sname.substring(0,sname.length()));
            }
        }

        if(!Func.IsStringEmpty(node.getDuty())){
            holder.tvDuty.setText(node.getDuty());
        }


        //不是员工，都显示当前分类下有多少人
        if (node.getIsPeople() != 1) {
            if( node.getCount()>0){
                holder.tvCount.setVisibility(View.VISIBLE);
            }else{
                holder.tvCount.setVisibility(View.GONE);
            }
            holder.tvIcon.setVisibility(View.GONE);
            holder.tvDuty.setVisibility(View.GONE);
            holder.tvCount.setText("(" + node.getCount() + ")");
        } else {
            holder.tvCount.setVisibility(View.GONE);
            holder.tvIcon.setVisibility(View.VISIBLE);
            holder.tvDuty.setVisibility(View.VISIBLE);
        }

        return view;
    }
    /**
     * 设置多选
     *
     * @param node
     * @param checked
     */
    @Override
    public void setChecked(final Node node, boolean checked) {
        for (int i = 0; i < mAllNodes.size(); i++) {
            Node node2 = mAllNodes.get(i);
            node2.setChecked(false);
        }
        node.setChecked(checked);
        setChildChecked(node, checked);
        if (node.getParent() != null) {
            setNodeParentChecked(node.getParent(), checked);
        }
        notifyDataSetChanged();
    }
    class ViewHolder {
        TextView tvIcon,tvName,tvDuty, tvCount;
        CheckBox cb;
        ImageView ivLeft;
        ImageView iv;
    }
}
