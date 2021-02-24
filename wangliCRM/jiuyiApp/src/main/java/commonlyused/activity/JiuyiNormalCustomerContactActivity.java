package commonlyused.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.GsonUtil;
import com.control.tools.JiuyiEventBusEvent;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.tools.NormalPinyinComparator;
import com.jiuyi.tools.PinyinUtils;
import com.jiuyi.tools.SideBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import commonlyused.adapter.NormalDeptLinkmanSortAdapter;
import commonlyused.bean.ContactBean;
import commonlyused.bean.LinkmanBean;
import commonlyused.bean.QueryConditionBean;
import customer.view.ListViewDecoration;
/**
 * ****************************************************************
 * 文件名称 : JiuyiNormalCustomerContactActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用客户联系人
 *****************************************************************
 */
public class JiuyiNormalCustomerContactActivity extends JiuyiActivityBase {
    private RecyclerView mRecyclerView;
    private NormalDeptLinkmanSortAdapter adapter;
    LinearLayoutManager manager;
    private List<LinkmanBean> SourceDateList;
    private SideBar sideBar;
    private TextView dialog;
    private TextView tv_emptytext;
    private ImageView iv_empty;

    RefreshLayout refreshLayout;
    private int pageIndex = 1;
    private int pageSize = 20;
    private int totalPage=0;


    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private NormalPinyinComparator pinyinComparator;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_normaldeptaddresslist_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        setContentView(mBodyLayout);
        setTitle();
        pinyinComparator = new NormalPinyinComparator();
        sideBar = (SideBar) mBodyLayout.findViewById(R.id.sideBar);
        dialog = (TextView) mBodyLayout.findViewById(R.id.dialog);
        sideBar.setTextView(dialog);
        tv_emptytext=(TextView)mBodyLayout.findViewById(R.id.tv_emptytext);
        iv_empty=(ImageView)mBodyLayout.findViewById(R.id.iv_empty);

        RefreshLayout refreshLayout = (RefreshLayout)mBodyLayout.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                getContactList(0);
                refreshlayout.finishRefresh(2000);
                refreshlayout.setLoadmoreFinished(false);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(pageIndex>=totalPage){
                    refreshlayout.finishLoadmore(500);
                    refreshlayout.setLoadmoreFinished(true);

                }else{
                    getContactList(pageIndex);
                    refreshlayout.finishLoadmore(2000);
                }
                pageIndex++;
            }
        });

        mRecyclerView = (RecyclerView) mBodyLayout.findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(JiuyiNormalCustomerContactActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new ListViewDecoration());
        getContactList(0);
        showProcessBar(0);
    }
    public void setTitle(){
        mTitle = "客户联系人";
        setTitle(mTitle);
    }
    public  void  getContactList(final int page){
        if(page==0){
            pageIndex=1;
        }
        final QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(page);
        queryConditionBean.setSize(20);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("id");
        queryConditionBean.setFromClientType("android");
        new Thread(new Runnable() {
            @Override
            public void run() {
                JiuyiHttp.POST("link-man/page")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<ContactBean>() {
                            @Override
                            public void onSuccess(ContactBean data) {
                                if(data!=null){
                                    totalPage=data.getTotalPages();
                                    if(data.getContent()!=null && data.getContent().size()>0){
                                         SourceDateList = new ArrayList<>();
                                        for(int i=0;i<data.getContent().size();i++){
                                            ContactBean.ContentBean contentBean=data.getContent().get(i);
                                            if(contentBean!=null){
                                                LinkmanBean sortModel = new LinkmanBean();
                                                sortModel.setId(contentBean.getId());
                                                if(contentBean.getName()!=null){
                                                    sortModel.setName(contentBean.getName());
                                                }
                                                if(contentBean.getPhone()!=null){
                                                    sortModel.setTelOne(contentBean.getPhone());
                                                }
                                                if(contentBean.getMember()!=null &&contentBean.getMember().getOrgName()!=null){
                                                    sortModel.setOrg(contentBean.getMember().getOrgName());
                                                }
//                                                if(contentBean.getMember().getAbbreviation()!=null){
//                                                    sortModel.setOrg(contentBean.getMember().getAbbreviation());
//                                                }
                                                if(contentBean.getOffice()!=null && contentBean.getOffice().getName()!=null ){
                                                    sortModel.setDept(contentBean.getOffice().getName().toString());
                                                }
                                                if(contentBean.getDuty()!=null){
                                                    sortModel.setDuty(contentBean.getDuty().toString());
                                                }



                                                if(!Func.IsStringEmpty(contentBean.getSimpleSpell())){
                                                    String pinyin = contentBean.getSimpleSpell();
                                                    String sortString = pinyin.substring(0, 1).toUpperCase();

                                                    // 正则表达式，判断首字母是否是英文字母
                                                    if (sortString.matches("[A-Z]")) {
                                                        sortModel.setLetters(sortString.toUpperCase());
                                                    } else {
                                                        sortModel.setLetters("#");
                                                    }
                                                }else{
                                                    if(contentBean.getName()!=null){
                                                        String pinyin = PinyinUtils.getPingYin(contentBean.getName());
                                                        String sortString = pinyin.substring(0, 1).toUpperCase();

                                                        // 正则表达式，判断首字母是否是英文字母
                                                        if (sortString.matches("[A-Z]")) {
                                                            sortModel.setLetters(sortString.toUpperCase());
                                                        } else {
                                                            sortModel.setLetters("#");
                                                        }
                                                    }
                                                }

                                                if(contentBean.getName()!=null){
                                                    SourceDateList.add(sortModel);
                                                }
                                            }
                                        }
                                        // 根据a-z进行排序源数据
//                                        Collections.sort(SourceDateList, pinyinComparator);

                                        if(page==0){
                                            adapter = new NormalDeptLinkmanSortAdapter(JiuyiNormalCustomerContactActivity.this, SourceDateList,false);
                                            mRecyclerView.setAdapter(adapter);
                                            adapter.setOnItemClickListener(new NormalDeptLinkmanSortAdapter.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(View view, int position) {
                                                    LinkmanBean linkmanBean=(LinkmanBean)adapter.getItem(position);
                                                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Contact");
                                                    mBundle.putString(JiuyiBundleKey.PARAM_LINKMANBEANID,linkmanBean.getId()+"");
                                                    changePage(mBundle, Pub.Normal_ContactsInfo,true);
                                                }
                                            });
                                            if(SourceDateList.size()==0||SourceDateList==null){
                                                tv_emptytext.setVisibility(View.VISIBLE);
                                                iv_empty.setVisibility(View.VISIBLE);
                                            }else{
                                                tv_emptytext.setVisibility(View.GONE);
                                                iv_empty.setVisibility(View.GONE);
                                            }

                                        }else{
                                            adapter.add(SourceDateList);
                                        }

                                    }else{
                                        if(page==0){
                                            tv_emptytext.setVisibility(View.VISIBLE);
                                            iv_empty.setVisibility(View.VISIBLE);
                                        }
                                    }


                                    showProcessBar(100);

                                }
                                JiuyiLog.e("httplogin","request onSuccess!" + data);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                showProcessBar(100);
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });

            }
        }).start();

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onUserEvent(JiuyiEventBusEvent event) {
        if (event == null)
            return;

        switch (event.getEventType()){
            default:
                super.onUserEvent(event);
                break;
        }
    }
    public void createReq(final boolean IsBg) {
        getContactList(0);
    }
}
