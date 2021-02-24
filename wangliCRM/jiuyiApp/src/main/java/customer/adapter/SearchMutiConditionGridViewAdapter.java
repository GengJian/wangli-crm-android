package customer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.control.utils.CustomerQueryConditionBean;
import com.control.utils.Func;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.wanglicrm.android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import customer.entity.MemberchooseBean;
import com.control.widget.NoScrollGridView;

public class SearchMutiConditionGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private MemberchooseBean.ContentBean.MemberChooseBeansBean  chooseBeansBean;
    private List<String> mList;
    private int selectorPosition=-1;
    private int lastPosition=-1;
    boolean bselect=false;
    List<MemberchooseBean.ContentBean.MemberChooseBeansBean.ChooseBeansBean> chooseBeanslist;

    public Vector<Boolean> getmSelects() {
        return mSelects;
    }

    public void setmSelects(Vector<Boolean> mSelects) {
        this.mSelects = mSelects;
    }

    public Vector<Boolean> mSelects = new Vector<Boolean>();  // 定义一个向量作为选中与否容器
    private boolean multiChoose=false;        //表示当前适配器是否允许多选

    public SearchMutiConditionGridViewAdapter(Context context, MemberchooseBean.ContentBean.MemberChooseBeansBean chooseBeansBean, boolean isMulti) {
        this.mContext = context;
        this.chooseBeansBean = chooseBeansBean;
        multiChoose = isMulti;
        bselect=false;
        mList = new ArrayList<>();
        chooseBeanslist=chooseBeansBean.getChooseBeans();
        if(chooseBeanslist!=null && chooseBeanslist.size()>0){
            for (int j = 0; j < chooseBeanslist.size(); j++) {
                MemberchooseBean.ContentBean.MemberChooseBeansBean.ChooseBeansBean chooseBeans=chooseBeanslist.get(j);
                mList.add(chooseBeans.getKey());
                mSelects.add(false);
            }
        }
    }

    public SearchMutiConditionGridViewAdapter(Context context, List<String> mList, boolean isMulti) {
        this.mContext = context;
        this.mList = mList;
        multiChoose = isMulti;
        bselect=false;
        for(int i=0; i<mList.size(); i++){
            mSelects.add(false);
        }
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return mList != null ? position : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_searchconditiongridview, null);
        Button button =(Button)convertView.findViewById(R.id.btn_agencynext) ;
        button.setText(mList.get(position));
        button.setId(R.id.mgridviewbutton);//设置 id
        if(((NoScrollGridView) parent).isOnMeasure){
            //如果是onMeasure调用的就立即返回
            return convertView;
        }
        if(multiChoose){
            if(!Func.IsStringEmpty(Rc.getIns().quicksortcondition)){
                Rc.getIns().quicksortcondition="";
            }
            if( mSelects.elementAt(position)){
                button.setBackgroundResource(Res.getDrawabelID(null,"jiuyi_v23_backbutton_bg_normal"));
                button.setTextColor(Res.getColor(null, "jiuyi_blue"));
                if(!Func.IsStringEmpty(Rc.getIns().quicksortcondition)){
                    if(Rc.getIns().queryConditionBean.getSpecialConditions().contains(Rc.getIns().quicksortcondition)){
                        Rc.getIns().queryConditionBean.getSpecialConditions().remove(Rc.getIns().quicksortcondition) ;
                        Rc.getIns().quicksortcondition="";
                    }
                }

                List<String> value = new ArrayList<String>();
                CustomerQueryConditionBean.RulesBean rulesBean=new CustomerQueryConditionBean.RulesBean();
                rulesBean.setField(chooseBeansBean.getMemberFeild());
                rulesBean.setOption("IN");
                if(chooseBeanslist!=null && chooseBeanslist.size()>0 && mSelects.size()>0){
                    for(int i=0;i<mSelects.size();i++){
                        if(mSelects.get(i)){
                            value.add(chooseBeanslist.get(i).getValue());
                        }
                    }
                }
                rulesBean.setValues(value);
                if(Rc.getIns().queryConditionBean.getRules().contains(rulesBean)){
                    Rc.getIns().queryConditionBean.getRules().remove(rulesBean);
                }
                if(value!=null && value.size()>0){
                    Rc.getIns().queryConditionBean.getRules().add(rulesBean);
                }


                if(chooseBeansBean!=null  && chooseBeansBean.getName()!=null){
                    if(!Rc.getIns().map_ekv.containsKey(chooseBeansBean.getMemberFeild())){
                        Rc.getIns().map_ekv.put(chooseBeansBean.getMemberFeild(),chooseBeansBean.getName());
                    }
                }

            }else{
                button.setBackgroundResource(Res.getDrawabelID(null,"jiuyi_customersearch_normal"));
                button.setTextColor(Res.getColor(null, "jiuyi_title_color"));
                if(Rc.getIns().queryConditionBean.getRules()!=null){
                    List<String> value = new ArrayList<String>();
                    CustomerQueryConditionBean.RulesBean rulesBean=new CustomerQueryConditionBean.RulesBean();
                    rulesBean.setField(chooseBeansBean.getMemberFeild());
                    rulesBean.setOption("IN");
                    if(chooseBeanslist!=null && chooseBeanslist.size()>0 && mSelects.size()>0){
                        for(int i=0;i<mSelects.size();i++){
                            if(mSelects.get(i)){
                                value.add(chooseBeanslist.get(i).getValue());
                            }
                        }
                    }
                    rulesBean.setValues(value);
                    if(Rc.getIns().queryConditionBean.getRules().contains(rulesBean)){
                        Rc.getIns().queryConditionBean.getRules().remove(rulesBean);
                    }
                    if(value!=null && value.size()>0){
                        Rc.getIns().queryConditionBean.getRules().add(rulesBean);

                    }

                }


            }


        }else {
            if (selectorPosition == position) {
                if(selectorPosition!=lastPosition){
                    bselect=false;
                }
                if(bselect){
                    button.setBackgroundResource(Res.getDrawabelID(null,"jiuyi_customersearch_normal"));
                    button.setTextColor(Res.getColor(null, "jiuyi_title_color"));
                    button.setSelected(false);
                    bselect=false;
                    CustomerQueryConditionBean.RulesBean rulesBean=new CustomerQueryConditionBean.RulesBean();
                    rulesBean.setField(chooseBeansBean.getMemberFeild());
                    rulesBean.setOption("EQ");
                    List<String> value = new ArrayList<String>();
                    value.add(chooseBeanslist.get(position).getValue());
                    rulesBean.setValues(value);
                    if(Rc.getIns().queryConditionBean.getRules().contains(rulesBean)){
                        Rc.getIns().queryConditionBean.getRules().remove(rulesBean);
                    }
                }else{
                    button.setBackgroundResource(Res.getDrawabelID(null,"jiuyi_v23_backbutton_bg_normal"));
                    button.setTextColor(Res.getColor(null, "jiuyi_blue"));
                    button.setSelected(true);
                    bselect=true;
                    lastPosition=selectorPosition;

                    CustomerQueryConditionBean.RulesBean rulesBean=new CustomerQueryConditionBean.RulesBean();
                    rulesBean.setField(chooseBeansBean.getMemberFeild());
                    rulesBean.setOption("EQ");
                    List<String> value = new ArrayList<String>();
                    value.add(chooseBeanslist.get(position).getValue());
                    rulesBean.setValues(value);
                    if(!Rc.getIns().queryConditionBean.getRules().contains(rulesBean)){
                        Rc.getIns().queryConditionBean.getRules().add(rulesBean);
                    }
                }
                if(chooseBeansBean!=null && chooseBeansBean.getMemberFeild()!=null && chooseBeansBean.getName()!=null){
                    if(!Rc.getIns().map_ekv.containsKey(chooseBeansBean.getMemberFeild())){
                        Rc.getIns().map_ekv.put(chooseBeansBean.getMemberFeild(),chooseBeansBean.getName());
                    }
                }

            } else {
                //其他的恢复原来的状态
                button.setBackgroundResource(Res.getDrawabelID(null,"jiuyi_customersearch_normal"));
                button.setTextColor(Res.getColor(null, "jiuyi_title_color"));
                button.setSelected(false);
                CustomerQueryConditionBean.RulesBean rulesBean=new CustomerQueryConditionBean.RulesBean();
                rulesBean.setField(chooseBeansBean.getMemberFeild());
                rulesBean.setOption("EQ");
                List<String> value = new ArrayList<String>();
                value.add(chooseBeanslist.get(position).getValue());
                rulesBean.setValues(value);
                if(Rc.getIns().queryConditionBean.getRules().contains(rulesBean)){
                    Rc.getIns().queryConditionBean.getRules().remove(rulesBean);
                }
            }

        }

        return convertView;
    }


    public void changeState(int pos) {

        if(multiChoose){
            mSelects.setElementAt(!mSelects.elementAt(pos), pos);   //直接取反即可
        }else{
//            mSelects.setElementAt(!mSelects.elementAt(pos), pos);   //直接取反即可
            selectorPosition = pos;
        }
        notifyDataSetChanged();

    }
}
