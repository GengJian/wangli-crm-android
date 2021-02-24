package customer.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.Res;
import com.wanglicrm.android.R;
import com.control.widget.recyclerView.BaseQuickAdapter;
import com.control.widget.recyclerView.BaseViewHolder;
import com.jiuyi.tools.jiuyiViewUtil;

import java.util.List;

import customer.entity.MemberReadBean;


public class AcountinfoAdapter extends BaseQuickAdapter<MemberReadBean.ContentBean.DataBean,BaseViewHolder>  {
    private String viewType="";

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private int pos=-1;

    public AcountinfoAdapter(int layoutResId, List<MemberReadBean.ContentBean.DataBean> data) {
        super(layoutResId, data);
    }
    public AcountinfoAdapter(int layoutResId, List<MemberReadBean.ContentBean.DataBean> data,String viewType) {
        super(layoutResId, data);
        this.viewType=viewType;

    }

    @Override
    protected void convert(final BaseViewHolder holder,final MemberReadBean.ContentBean.DataBean item) {
        jiuyiViewUtil.initCutOff(holder,holder.getLayoutPosition());
        TextView leftContent = holder.getView(R.id.leftContent);
        TextView rightContent = holder.getView(R.id.rightContent);
        ImageButton imageUpdate = holder.getView(R.id.imageUpdate);
        if(!Func.IsStringEmpty(item.getLeftContent())){
            leftContent.setText(item.getLeftContent());
        }
        if(!Func.IsStringEmpty(item.getRightContent())){
            rightContent.setText(item.getRightContent());
        }else{
            if(item.isChange()){
                if(!Func.IsStringEmpty(item.getDictField())||item.getField().endsWith("Date")||item.getField().equals("brandNames")||item.getField().toLowerCase().equals("birthday")
                        ||item.getField().equals("operatorName")){
                    rightContent.setHint("请选择");
                }else{
                    rightContent.setHint("请填写");
                }
                rightContent.setText("");
            }
        }
        if(!item.isChange()){
            rightContent.setTextColor(Res.getColor(null, "jiuyi_info_color"));
        }else {
            if(imageUpdate!=null){
                imageUpdate.setImageResource(R.drawable.canupdate);
            }
            rightContent.setTextColor(Res.getColor(null, "jiuyi_title_color"));
        }
        if(viewType.equals("add")){
            if(!Func.IsStringEmpty(item.getDictField())||item.getField().endsWith("Date")||item.getField().equals("operatingPeriodFrom")||item.getField().toLowerCase().equals("birthday")
                    ||item.getField().equals("operatingPeriodTo")
                    ||item.getField().equals("memberLevelValue")
                    ||item.getField().equals("creditLevelValue")
                    ||item.getField().equals("cooperationTypeValue")
                    ||item.getField().equals("region")
                    ||item.getField().equals("firstDistributor")){
                if(item.getField().equals("memberLevelValue") && pos==-1){
                    item.setRightContent("普通");
                    rightContent.setText("普通");
                }else if(item.getField().equals("creditLevelValue")&& pos==-1){
                    item.setRightContent("A");
                    rightContent.setText("A");
                }else if(item.getField().equals("cooperationTypeValue")&& pos==-1){
                    item.setRightContent("二级分销商");
                    rightContent.setText("二级分销商");
                }else {
                    rightContent.setHint("请选择(必填项)");
                }

            }else{
                rightContent.setHint("请填写(必填项)");
            }
        }

        rightContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                {
                    listener.click(holder.getLayoutPosition(),"rightContent",v);
                }
            }
        });
    }



    private AcountinfoAdapter.onCLickItemListener listener;

    public void  setOnCLickItemListener(AcountinfoAdapter.onCLickItemListener listener)
    {
        this.listener=listener;
    }

    public interface  onCLickItemListener
    {
        void click(int position, String colname, View view);
    }

}
