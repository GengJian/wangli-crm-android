package commonlyused.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wanglicrm.android.R;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.utils.JiuyiLog;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.google.gson.internal.LinkedTreeMap;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;

import commonlyused.bean.OperatorNodeBean;
import commonlyused.db.DbHelper;
import commonlyused.treelist.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import commonlyused.adapter.NormalRecentContactAdapter;
import commonlyused.adapter.SimpleTreeAdapter;
import commonlyused.bean.LinkmanBean;
import commonlyused.bean.NormalDeptBean;
import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.QueryConditionBean;
import commonlyused.treelist.OnTreeNodeClickListener;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNormalCompanyAddressListActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用公司通讯录
 *****************************************************************
 */
public class JiuyiNormalCompanyAddressListActivity extends JiuyiActivityBase {
    private ListView lv_companyaddresslist;
    private List<Node> mDatas = new ArrayList<>();
    private SimpleTreeAdapter mAdapter;
    //所有人数
    int allTotal = 0;

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_companyaddresslist_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        setContentView(mBodyLayout);
        setTitle();

        lv_companyaddresslist= (ListView) mBodyLayout.findViewById(R.id.lv_companyaddresslist);

        List<OperatorNodeBean> userList =DbHelper.getInstance().operatorNode().loadAll();
        if(userList!=null&&userList.size()>0){
            for(OperatorNodeBean operatorNodeBean :userList){
                if(operatorNodeBean.getIsPeople()==1){
                    mDatas.add(new Node(operatorNodeBean.getNodeid(), operatorNodeBean.getNodepid(), operatorNodeBean.getName(),operatorNodeBean.getDuty(), 1, 1));
                }else{
                    mDatas.add(new Node(operatorNodeBean.getNodeid(), operatorNodeBean.getNodepid(), operatorNodeBean.getName(), operatorNodeBean.getCount()));
                }
            }
            //给 ListView 设置 Adapter
            mAdapter = new SimpleTreeAdapter(lv_companyaddresslist, JiuyiNormalCompanyAddressListActivity.this, mDatas, 1, R.drawable.cn_address_book_less, R.drawable.cn_address_book_plus);
            lv_companyaddresslist.setAdapter(mAdapter);
            mAdapter.setOnTreeNodeClickListener(new OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if(!Func.IsStringEmpty(node.getId().toString()) && !Func.IsIntFormat(node.getId().toString(),true)){
                        return;
                    }
                    Bundle mBundle=new Bundle();
                    mBundle.putString(JiuyiBundleKey.PARAM_LINKMANBEANID, node.getId().toString());
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.Normal_ContactsInfo);
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_ContactsInfo,true);
                }
            });

        }else{
            getDeptAddressList();
        }
        showProcessBar(0);



    }

    public  List<Node>  getCompanyAddressList(){
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        JiuyiHttp.POST("operator/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<NormalOperatorBean>() {
            @Override
            public void onSuccess(NormalOperatorBean data) {
                if(data!=null){
                    List<NormalOperatorBean.ContentBean> ContentBeanlist=data.getContent();
                    if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                        //创建第一级菜单
                        for(NormalOperatorBean.ContentBean contentBean :ContentBeanlist){
                            String parentid="";
                            if(contentBean.getDepartment()!=null){
                                parentid=String.valueOf(contentBean.getDepartment().getId());
                            }else{
                                parentid="all";
                            }
                            if(!parentid.equals("all")){
                                parentid="d"+parentid;
                            }
                            mDatas.add(new Node(contentBean.getId(), parentid, contentBean.getName(), 1, 1));
                        }
                    }
                    List<Node> mDatas2 = new ArrayList<>();
                    mDatas2=deleteRepeat(mDatas);
                    //给 ListView 设置 Adapter
                    mAdapter = new SimpleTreeAdapter(lv_companyaddresslist, JiuyiNormalCompanyAddressListActivity.this, mDatas2, 1, R.drawable.cn_address_book_less, R.drawable.cn_address_book_plus);


                    lv_companyaddresslist.setAdapter(mAdapter);
                    lv_companyaddresslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                            JiuyiLog.e("httplogin","request onSuccess!" );
                        }
                    });
                }
                showProcessBar(100);
                JiuyiLog.e("httplogin","request onSuccess!" + data);
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                showProcessBar(100);
                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
            }
        });
        return null;
    }

    public  List<Node>  getDeptAddressList(){
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        JiuyiHttp.POST("department/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<NormalDeptBean>() {
                    @Override
                    public void onSuccess(NormalDeptBean data) {
                        if(data!=null){
                            DbHelper.getInstance().operatorNode().deleteAll();
                            allTotal=0;
                            List<NormalDeptBean.ContentBean> ContentBeanlist=data.getContent();
                            if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                                for(NormalDeptBean.ContentBean contentBean :ContentBeanlist){
                                    //二级菜单
                                    String parentid="all";
                                    if(contentBean.getParent()!=null){
                                        String parentid2=contentBean.getId()+"";
                                        if(parentid2.contains(".")){
                                            parentid2=parentid2.substring(0, parentid2.indexOf("."));
                                        }
                                        mDatas.add(new Node("d"+contentBean.getId()+"", "d"+parentid2, contentBean.getName(), contentBean.getTotalCount()));
                                    }else{
                                        mDatas.add(new Node("d"+contentBean.getId()+"", parentid, contentBean.getName(), contentBean.getTotalCount()));
                                        allTotal+=contentBean.getTotalCount();
                                    }
                                    OperatorNodeBean operatorNodeBean=new OperatorNodeBean();
                                    operatorNodeBean.setNodeid("1");
                                    operatorNodeBean.setName("wangli"+allTotal);

                                    DbHelper.getInstance().operatorNode().insert(operatorNodeBean);
                                }
                                //创建第一级菜单
                                mDatas.add(new Node("all","-1", "中国王力集团", allTotal));

                            }
                            List<OperatorNodeBean> userList =DbHelper.getInstance().operatorNode().loadAll();
                            JiuyiLog.e("httplogin","request onSuccess!" + DbHelper.getInstance().operatorNode().loadAll().toString());
                            getCompanyAddressList();
                        }
                        JiuyiLog.e("httplogin","request onSuccess!" + data);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
        return null;
    }


    /**
     * 去除list集合中的重复元素
     * @param list 要去重的 list
     * @return 返回去重后的 list
     */
    public static List deleteRepeat(List list){
        List newList = new ArrayList<>();
        Set set = new HashSet();
        for (Object obj : list) {
            if(set.add(obj)){
                newList.add(obj);
            }
        }
        return newList;
    }


    public void setTitle(){
        mTitle = "公司通讯录";
        setTitle(mTitle);
    }
}
