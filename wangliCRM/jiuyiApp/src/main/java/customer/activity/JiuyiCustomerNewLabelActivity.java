package customer.activity;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiMainApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.FastUtils;
import customer.Utils.ViewOperatorType;
import customer.adapter.TagSelectAdapter;
import customer.adapter.TagunSelectAdapter;
import customer.entity.LabelBean;
import customer.entity.MemberCenterBean;
import customer.listener.OnTagClickListener;
import customer.view.FlowTagLayout;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerNewLabelActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 标签
 *****************************************************************
 */

public class JiuyiCustomerNewLabelActivity extends JiuyiActivityBase {
    private TextView mtvcomplete,mtvcancel,tv_add;
    private long Customerid=-1;
    private String viewOperatortype="",CustomerName="";
    private long beanid=-1;
    private LinearLayout ll_content;
    private JiuyiEditText et_newlabel;
    private List<MemberCenterBean.LabelsBean> labelList;

    private FlowTagLayout tfl_normallabels,mFlowLayout;
    private TagunSelectAdapter tagAdapter;
    private TagSelectAdapter tagSelectAdapter;
    private List<String> nomalList,selectList;
    List<MemberCenterBean.LabelsBean> labelsBeanList;

    @Override
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        viewOperatortype=mBundle.getString(JiuyiBundleKey.PARAM_OPERATORTYPE);
        if(viewOperatortype==null){
            viewOperatortype="";
        }
        labelsBeanList=new ArrayList<>();
        labelsBeanList=mBundle.getParcelableArrayList(JiuyiBundleKey.PARAM_DARRAY);
        beanid=mBundle.getLong(JiuyiBundleKey.PARAM_NEEDPLANID);
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_customernewlabel_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        setTitle();
        mFlowLayout = (FlowTagLayout) mBodyLayout.findViewById(R.id.id_flowlayout);
        tagSelectAdapter = new TagSelectAdapter(JiuyiMainApplication.getIns());
        //设置这个模式意思是处理流标签点击事件
        mFlowLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
        mFlowLayout.setAdapter(tagSelectAdapter);
        if(labelsBeanList!=null && labelsBeanList.size()>0 ){
            tagSelectAdapter.onlyAddAll(labelsBeanList);
        }
        mFlowLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                View view1 = parent.getAdapter().getView(position, null, null);
                MemberCenterBean.LabelsBean tag = (MemberCenterBean.LabelsBean) parent.getAdapter().getItem(position);
                deleteLabel(tag);

            }
        });

        tfl_normallabels = (FlowTagLayout) mBodyLayout.findViewById(R.id.tfl_normallabels);

        tagAdapter = new TagunSelectAdapter(JiuyiMainApplication.getIns());
        //设置这个模式意思是处理流标签点击事件
        tfl_normallabels.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
        tfl_normallabels.setAdapter(tagAdapter);
        getLabelList();

        ll_content= (LinearLayout) mBodyLayout.findViewById(Res.getViewID(null, "ll_content"));
        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        mtvcancel = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_cancel"));
        if (mtvcancel != null) {
            mtvcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backPage();
                }
            });

        }
        et_newlabel = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_newlabel"));
        tv_add = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_add"));
        if (tv_add != null) {
            tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (FastUtils.isFastClick()) {
                        if(!Func.IsStringEmpty(et_newlabel.getText().toString())){
                            addLabel(et_newlabel.getText().toString());
                        }
                    }
                }
            });

        }
    }
    public void addLabel(final String label){
        Map<String, String> labelMap= new HashMap<String, String>();
        labelMap.put("desp",label);

        JiuyiHttp.POST("member/label/add/"+Customerid)
                .setJson(GsonUtil.gson().toJson(labelMap))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback< List<MemberCenterBean.LabelsBean>>() {
                    @Override
                    public void onSuccess( List<MemberCenterBean.LabelsBean> data ) {
                        if(data!=null && data.size()>0){
                            for(int i=0;i<data.size();i++){
                                if(data.get(i).getDesp().equals(label)){
                                    tagSelectAdapter.onlyAdd(data.get(i));
                                }
                            }
                            Rc.mBackfresh=true;
                            Toast.makeText(JiuyiCustomerNewLabelActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    public void deleteLabel(final MemberCenterBean.LabelsBean labelsBean){

        JiuyiHttp.DELETE("member/label/remove/"+Customerid+"/"+labelsBean.getId()+"")
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(data!=null){
                            tagSelectAdapter.onlyDelete(labelsBean);
                            tagSelectAdapter.notifyDataSetChanged();
                            tagAdapter.onlyAdd(labelsBean);
                            tagAdapter.notifyDataSetChanged();
                            Rc.mBackfresh=true;
                            Log.d("success", "response:" );
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }


    public boolean check(){
        return true;
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            Rc.mBackfresh=true;
            backPage();
        }
    }
    public void setTitle(){
        if(viewOperatortype!=null){
            if(viewOperatortype.equals(ViewOperatorType.VIEW)){
                mTitle = "标签信息";
            }else if(viewOperatortype.equals(ViewOperatorType.EDIT)){
                mTitle = "编辑标签信息";
            }else{
                mTitle = "标签信息";
            }
        }else{
            mTitle = "标签信息";
        }

        setTitle(mTitle);
    }

    public void  getLabelList(){
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("hotNumber");
        JiuyiHttp.POST("label/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                .request(new ACallback<LabelBean>() {
                    @Override
                    public void onSuccess(LabelBean data) {
                        if(data!=null){
                            List<LabelBean.ContentBean> ContentBeanlist =data.getContent();
                            labelList=new ArrayList<>();
                            if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                                for(int i=0;i<ContentBeanlist.size();i++){
                                    MemberCenterBean.LabelsBean labelsBean=new MemberCenterBean.LabelsBean();
                                    if(ContentBeanlist.get(i).getDesp()!=null){
                                        labelsBean.setDesp(ContentBeanlist.get(i).getDesp());
                                    }
                                    if(ContentBeanlist.get(i).getName()!=null){
                                        labelsBean.setName(ContentBeanlist.get(i).getName());
                                    }
                                    labelsBean.setId(ContentBeanlist.get(i).getId());
                                    labelList.add(labelsBean);
                                }

                                tagAdapter.onlyAddAll(labelList);
                                tfl_normallabels.setOnTagClickListener(new OnTagClickListener() {
                                    @Override
                                    public void onItemClick(FlowTagLayout parent, View view, int position) {
                                        View view1 = parent.getAdapter().getView(position, null, null);
                                        MemberCenterBean.LabelsBean tag = (MemberCenterBean.LabelsBean) parent.getAdapter().getItem(position);
                                        addLabel(tag.getDesp());
                                        tagAdapter.onlyDelete(tag);
                                    }
                                });
                            }

                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    /**
     * 界面回退
     */
    @Override
    public void backPage()
    {
        Rc.mBackfresh=true;
        super.backPage();
    }

}
