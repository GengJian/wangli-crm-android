package customer.adapter;

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

import java.util.ArrayList;
import java.util.List;

import commonlyused.treelist.Node;
import commonlyused.treelist.TreeListViewAdapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class OrgDeptSimpleTreeAdapter extends TreeListViewAdapter {

    private Context context;

    public long getSelectID() {
        return selectID;
    }

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    private String selectName;

    private long selectID;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type="";

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    private boolean multi=false;

    public OrgDeptSimpleTreeAdapter(ListView mTree, Context context, List<Node> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand) {
        super(mTree, context, datas, defaultExpandLevel, iconExpand, iconNoExpand);
        this.context = context;
    }

    @Override
    public View getConvertView(final Node node, int position, View convertView, ViewGroup parent) {
        View view;
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_orgdept_select_title, null);
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
                if(selectIDs==null){
                    selectIDs=new ArrayList<>();
                }
                setChecked(node, holder.cb.isChecked(),multi);
                selectName=node.getName();
                selectID=Long.parseLong(node.getId().toString());

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
                holder.tvIcon.setText(sname.substring(sname.length()-2,sname.length()));
            }else if(sname.length()>0){
                holder.tvIcon.setText(sname.substring(1,sname.length()));
            }
        }

        if(!Func.IsStringEmpty(node.getDuty())){
            holder.tvDuty.setText(node.getDuty());
        }


        //不是员工，都显示当前分类下有多少人
        if (node.getIsPeople() != 1) {
            holder.tvCount.setVisibility(View.VISIBLE);
            holder.tvIcon.setVisibility(View.GONE);
            holder.tvDuty.setVisibility(View.GONE);
            holder.tvCount.setText("(" + node.getCount() + ")");
        } else {
            holder.tvCount.setVisibility(View.GONE);
            holder.tvIcon.setVisibility(View.VISIBLE);
            holder.tvDuty.setVisibility(View.VISIBLE);
        }
        if(type.equals("NOSHOW")){
            holder.tvCount.setVisibility(View.GONE);
        }

        return view;
    }

    class ViewHolder {
        TextView tvIcon,tvName,tvDuty, tvCount;
        CheckBox cb;
        ImageView ivLeft;
        ImageView iv;
    }
}
