package customer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.google.gson.internal.LinkedTreeMap;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import commonlyused.adapter.NormalRecentContactAdapter;
import commonlyused.adapter.SimpleTreeAdapter;
import commonlyused.bean.LinkmanBean;
import commonlyused.bean.NormalDeptBean;
import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.OperatorNodeBean;
import commonlyused.bean.QueryConditionBean;
import commonlyused.db.DbHelper;
import commonlyused.treelist.Node;
import commonlyused.treelist.OnTreeNodeClickListener;
import customer.adapter.OrgDeptSimpleTreeAdapter;
import customer.entity.MemberReadBean;
import customer.entity.OfficeBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiOrgDeptSelectActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 公司部门选择
 *****************************************************************
 */
public class JiuyiOrgDeptSelectActivity extends JiuyiActivityBase {
    private List<OfficeBean> SourceDateList;
    private ListView lv_companyaddresslist;
    private List<Node> mDatas = new ArrayList<>();
    private OrgDeptSimpleTreeAdapter mAdapter;
    private TextView tvComplete;
    private ImageView ivBack;
    private long Customerid=-1;
    private TextView tv_emptytext;
    private ImageView iv_empty;

    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_orgdeptselect_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
//        Customerid=199;
        setTitle();
        ivBack = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });
        tvComplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        tvComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackActivityBundle();
                backPage();
            }
        });

        lv_companyaddresslist= (ListView) mBodyLayout.findViewById(R.id.lv_companyaddresslist);
        tv_emptytext=(TextView)mBodyLayout.findViewById(R.id.tv_emptytext);
        iv_empty=(ImageView)mBodyLayout.findViewById(R.id.iv_empty);
       if(Customerid>0){
           getOfficeVoList();
           showProcessBar(0);
       }
    }

    private void getOfficeVoList() {
        JiuyiHttp.GET("link-man-office/officeVoList/"+Customerid)
                .tag("request_get_member")
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<List<OfficeBean>>() {
                    @Override
                    public void onSuccess(List<OfficeBean> data) {
                        if(data!=null && data.size()>0){
                            SourceDateList=data;
                            for(int i=0;i<SourceDateList.size();i++){
                                OfficeBean officeBean=SourceDateList.get(i);
                                mDatas.add(new Node(officeBean.getId()+"", officeBean.getParentOfficeId()+"", officeBean.getName().toString(), officeBean.getTotalCount()));
                            }
                            //给 ListView 设置 Adapter
                            mAdapter = new OrgDeptSimpleTreeAdapter(lv_companyaddresslist, JiuyiOrgDeptSelectActivity.this, mDatas, 1, R.drawable.cn_address_book_less, R.drawable.cn_address_book_plus);
                            lv_companyaddresslist.setAdapter(mAdapter);
                        }else {
                            tv_emptytext.setVisibility(View.VISIBLE);
                            iv_empty.setVisibility(View.VISIBLE);
                        }
                        showProcessBar(100);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        showProcessBar(100);
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

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
        mTitle = "部门选择";
        setTitle(mTitle);
    }

    @Override
    public void setBackActivityBundle() {
        Bundle bundle = new Bundle();
        if(mAdapter!=null ){
            bundle.putLong(JiuyiBundleKey.PARAM_COMMONID,mAdapter.getSelectID());
            bundle.putString(JiuyiBundleKey.PARAM_COMMONNAME,mAdapter.getSelectName());
        }
        Intent intent = new Intent();
        intent.putExtras(bundle);
        JiuyiOrgDeptSelectActivity.this.setResult(200, intent);
    }
}
