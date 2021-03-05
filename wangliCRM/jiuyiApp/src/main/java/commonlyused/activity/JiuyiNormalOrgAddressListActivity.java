package commonlyused.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.Pub;
import com.control.utils.Res;
import com.control.utils.JiuyiBundleKey;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.tools.PinyinComparator;
import com.control.widget.recyclerView.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import commonlyused.adapter.NormalRecentContactAdapter;
import commonlyused.bean.LinkmanBean;
import commonlyused.bean.LinkmanGreenBean;
import commonlyused.db.DbHelper;
import commonlyused.db.LinkmanGreenBeanDao;
import customer.entity.SexEnum;
/**
 * ****************************************************************
 * 文件名称 : JiuyiNormalOrgAddressListActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用机构通讯录
 *****************************************************************
 */
public class JiuyiNormalOrgAddressListActivity extends JiuyiActivityBase {
    private RecyclerView mRecyclerView;
    private NormalRecentContactAdapter adapter;
    LinearLayoutManager manager;
    private List<LinkmanBean> SourceDateList;
    private LinearLayout ll_searchplan;
    private TextView tv_emptytext;
    private ImageView iv_empty;

    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private TextView tv_department,tv_company,tv_subordinate,tv_important,tv_client;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_normalorgaddresslist_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        setContentView(mBodyLayout);
        setTitle();
        tv_emptytext=(TextView)mBodyLayout.findViewById(R.id.tv_emptytext);
        iv_empty=(ImageView)mBodyLayout.findViewById(R.id.iv_empty);

        mRecyclerView = (RecyclerView) mBodyLayout.findViewById(R.id.rv_recentcontact);

        manager = new LinearLayoutManager(JiuyiNormalOrgAddressListActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
        ll_searchplan=(LinearLayout) mBodyLayout.findViewById(R.id.ll_search);
        if(ll_searchplan!=null){
            ll_searchplan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Normal_ContactSearch);
                    changePage(mBundle,Pub.Normal_ContactSearch,true);
                }
            });
        }
        tv_department=(TextView) mBodyLayout.findViewById(R.id.tv_department);
        if(tv_department!=null){
            tv_department.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null,Pub.Normal_DeptAddresslist,true);
                }
            });
        }
        tv_subordinate=(TextView) mBodyLayout.findViewById(R.id.tv_subordinate);
        if(tv_subordinate!=null){
            tv_subordinate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null,Pub.Normal_subordinateContacts,true);
                }
            });
        }
//        tv_important=(TextView) mBodyLayout.findViewById(R.id.tv_important);
//        if(tv_important!=null){
//            tv_important.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    changePage(null,Pub.Normal_ImportantContacts,true);
//                }
//            });
//        }
        tv_client=(TextView) mBodyLayout.findViewById(R.id.tv_client);
        if(tv_client!=null){
            tv_client.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null,Pub.Normal_CustomerContacts,true);
                }
            });
        }
        tv_company=(TextView) mBodyLayout.findViewById(R.id.tv_company);
        if(tv_company!=null){
            tv_company.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(null,Pub.Normal_CompanyAddresslist,true);
                }
            });
        }

    }
   private void initData(){
       SourceDateList=new ArrayList<>();
       List<LinkmanGreenBean> appItemBeanList = DbHelper.getInstance().linkmanGreenBeanLongDBManager().queryBuilder().orderDesc(LinkmanGreenBeanDao.Properties.Id).build().list();
       if(appItemBeanList!=null && appItemBeanList.size()>0){
           for(int i=0;i<appItemBeanList.size();i++){
               LinkmanGreenBean linkmanBean=appItemBeanList.get(i);
               if(linkmanBean!=null){
                   LinkmanBean sortModel = new LinkmanBean();
                   if(linkmanBean.getName()!=null){
                       sortModel.setName(linkmanBean.getName());
                   }
                   if(linkmanBean.getSex()!=null){
                       if(linkmanBean.getSex().equals("女")){
                           sortModel.setSex(SexEnum.FEMALE);
                       }else if(linkmanBean.getSex().equals("男")){
                           sortModel.setSex(SexEnum.MALE);
                       }
                   }
                   if(linkmanBean.getBirthday()!=null){
                       sortModel.setBirthday(linkmanBean.getBirthday());
                   }
                   if(linkmanBean.getTelOne()!=null){
                       sortModel.setTelOne(linkmanBean.getTelOne());
                   }
                   if(linkmanBean.getEmail()!=null){
                       sortModel.setEmail(linkmanBean.getEmail());
                   }
                   if(linkmanBean.getAddress()!=null){
                       sortModel.setAddress(linkmanBean.getAddress());
                   }
                   if(linkmanBean.getDuty()!=null){
                       sortModel.setDuty(linkmanBean.getDuty());
                   }
                   if(linkmanBean.getTimIdentifier()!=null){
                       sortModel.setTimIdentifier(linkmanBean.getTimIdentifier());
                   }
                   SourceDateList.add(sortModel);
                   if(i>=29){
                       break;
                   }
               }

           }
           adapter = new NormalRecentContactAdapter(R.layout.normal_recentcontact_item, SourceDateList);
           adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
               @Override
               public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                   LinkmanBean linkmanBean=SourceDateList.get(position);
                   mBundle.putParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN, linkmanBean);
                   changePage(mBundle, Pub.Normal_ContactsInfo,true);
               }
           });
           mRecyclerView.setAdapter(adapter);
       }else{
           mRecyclerView.setVisibility(View.GONE);
           tv_emptytext.setVisibility(View.VISIBLE);
           iv_empty.setVisibility(View.VISIBLE);
       }
   }

    public void setTitle(){
        mTitle = "机构通讯录";
        setTitle(mTitle);
    }
}
