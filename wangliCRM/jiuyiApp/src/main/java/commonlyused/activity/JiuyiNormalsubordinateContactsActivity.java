package commonlyused.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.tools.NormalPinyinComparator;
import com.jiuyi.tools.PinyinUtils;
import com.jiuyi.tools.SideBar;
import com.wanglicrm.android.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import commonlyused.adapter.NormalDeptLinkmanSortAdapter;
import commonlyused.bean.LinkmanBean;
import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.QueryConditionBean;
import customer.view.ListViewDecoration;
/**
 * ****************************************************************
 * 文件名称 : JiuyiNormalsubordinateContactsActivity
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 常用我的下属
 *****************************************************************
 */
public class JiuyiNormalsubordinateContactsActivity extends JiuyiActivityBase {
    private RecyclerView mRecyclerView;
    private NormalDeptLinkmanSortAdapter adapter;
    LinearLayoutManager manager;
    private List<LinkmanBean> SourceDateList;
    private SideBar sideBar;
    private TextView dialog;
    private TextView tv_emptytext;
    private ImageView iv_empty;


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


        //设置右侧SideBar触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }

            }
        });

        mRecyclerView = (RecyclerView) mBodyLayout.findViewById(R.id.recyclerView);

        manager = new LinearLayoutManager(JiuyiNormalsubordinateContactsActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new ListViewDecoration());
        filledData();
        showProcessBar(0);

    }


    /**
     * 为RecyclerView填充数据
     *
     * @param date
     * @return
     */
    private List<LinkmanBean> filledData() {
        final List<LinkmanBean> mSortList = new ArrayList<LinkmanBean>();

        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        List<Long> value = new ArrayList<Long>();
        List<QueryConditionBean.RulesBean> rulesBeanList;
        rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("superiorOperator.id");
        rulesBean.setOption("EQ");
        rulesBeanList.add(rulesBean);
        value.add(Rc.id);
        rulesBean.setValues(value);
        queryConditionBean.setRules(rulesBeanList);
        JiuyiHttp.POST("operator/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<NormalOperatorBean>() {
                    @Override
                    public void onSuccess(NormalOperatorBean data) {
                        if(data!=null){
                            List<NormalOperatorBean.ContentBean> ContentBeanlist=data.getContent();
                            if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                                for(int i=0;i<ContentBeanlist.size();i++){
                                    NormalOperatorBean.ContentBean contentBean=ContentBeanlist.get(i);
                                    if(contentBean!=null){
                                        LinkmanBean sortModel = new LinkmanBean();
                                        sortModel.setName(contentBean.getName());
                                        if(!Func.IsStringEmpty(contentBean.getTelOne())){
                                            sortModel.setTelOne(contentBean.getTelOne());
                                        }
                                        sortModel.setOrg("中国王力集团");
                                        if(contentBean.getDepartment()!=null){
                                            if(!Func.IsStringEmpty(contentBean.getDepartment().getName())){
                                                sortModel.setDept(contentBean.getDepartment().getName());
                                            }
                                        }
                                        if(contentBean.getTitle()!=null){
                                            if(!Func.IsStringEmpty(contentBean.getTitle())){
                                                sortModel.setDuty(contentBean.getTitle());
                                            }
                                        }

                                        sortModel.setBirthday(contentBean.getBirthday());
                                        sortModel.setSex(contentBean.getSex());
                                        if(!Func.IsStringEmpty(contentBean.getAddress())){
                                            sortModel.setAddress(contentBean.getAddress());
                                        }
                                        if(!Func.IsStringEmpty(contentBean.getTimIdentifier())){
                                            sortModel.setTimIdentifier(contentBean.getTimIdentifier());
                                        }

                                        //汉字转换成拼音
                                        String pinyin = PinyinUtils.getPingYin(contentBean.getName());
                                        String sortString = pinyin.substring(0, 1).toUpperCase();

                                        // 正则表达式，判断首字母是否是英文字母
                                        if (sortString.matches("[A-Z]")) {
                                            sortModel.setLetters(sortString.toUpperCase());
                                        } else {
                                            sortModel.setLetters("#");
                                        }

                                        mSortList.add(sortModel);
                                    }
                                }
                                // 根据a-z进行排序源数据
                                Collections.sort(mSortList, pinyinComparator);
                                adapter = new NormalDeptLinkmanSortAdapter(JiuyiNormalsubordinateContactsActivity.this, mSortList);
                                adapter.setOnItemClickListener(new NormalDeptLinkmanSortAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        LinkmanBean linkmanBean=mSortList.get(position);
                                        mBundle.putParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN, linkmanBean);
                                        changePage(mBundle, Pub.Normal_ContactsInfo,true);
                                    }
                                });
                                mRecyclerView.setAdapter(adapter);
                            }else{
                                tv_emptytext.setVisibility(View.VISIBLE);
                                iv_empty.setVisibility(View.VISIBLE);
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
        return mSortList;
    }


    public void setTitle(){
        mTitle = "我的下属";
        setTitle(mTitle);
    }
}
