package customer.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiLog;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.JiuyiFragmentBase;
import com.google.gson.Gson;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.recyclerview.swipe.Closeable;
import com.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.recyclerview.swipe.SwipeMenu;
import com.recyclerview.swipe.SwipeMenuCreator;
import com.recyclerview.swipe.SwipeMenuItem;
import com.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.KeyBoardUtils;
import customer.Utils.SerachUtils;
import customer.adapter.CustomerListAdapter;
import customer.adapter.TagAdapter;
import customer.adapter.TagunSelectAdapter;
import customer.entity.LabelBean;
import customer.entity.MemberBean;
import customer.entity.MemberCenterBean;
import customer.entity.MemberListBean;
import customer.entity.ResultBean;
import customer.listener.OnItemClickListener;
import customer.listener.OnTagClickListener;
import customer.view.FlowTagLayout;
import customer.view.jiuyiRecycleViewDivider;

import static com.control.utils.Rc.getApplication;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerSearchFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 风险跟踪
 *****************************************************************
 */
public class JiuyiCustomerSearchFragment extends JiuyiFragmentBase {
    SwipeMenuRecyclerView mRecyclerView = null;
    /**
     * 右上角语音关闭按钮
     */
    private ImageView jiuyi_searchcustomer_pop_speek_ivback;

//    /**
//     * 数据适配器
//     */
    private CustomerListAdapter mDragAdapter = null;
    private List<MemberBean.ContentBean> mViewTypeBeanList;
    private List<MemberListBean.ContentBean> mCustomerListBeanList;
    private JiuyiEditText mEditText;
    private boolean havePermission = false;//初始化时判断是否有录音权限
    private TextView tv_searchresult;
    private LinearLayout ll_searchhistory,ll_searchresult,ll_clear_history,jiuyi_mainbodyview_layout,ll_labelsearch;
    FlowTagLayout flowTagLayout,ftl_labelsearch;

    private List<String> searchHistory;//搜索历史
    private TagAdapter tagAdapter;
    private TagunSelectAdapter tagLabelAdapter;
    private Gson mGson;
    private long labelid=0;
    private String searchType="";
    private List<MemberCenterBean.LabelsBean> labelList;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //讯飞语音初始化
        Rc.cfg.InitSpeech(getApplication(), "appid=" + getApplication().getString(Res.getStringID(getApplication(), "app_id")));
        havePermission = checkRecordPermission();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_fragment_searchcustomer_layout"), null);
            onInit();
        } else {
            checkRootViewParent();
        }

        return mRootView;
    }
    @Override
    public void onInit(){
        mGson = new Gson();
        getLabelList();
        mEditText = (JiuyiEditText) mRootView.findViewById(Res.getViewID(null, "jiuyi_searchcustomer_edit"));
        SetTextChanged(mEditText);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyBoardUtils.hideSoftInput(mEditText);
                    if (mEditText.getText().toString().equals("")) {
                        Toast.makeText(getCallBack().getActivity(), "请输入关键字", Toast.LENGTH_SHORT).show();
                    } else {
                        searchType="customer";
                        labelid=0;
                        SerachUtils.getInstance().saveSearch(mEditText.getText().toString());
                        tagAdapter.onlyAdd(mEditText.getText().toString());
                        tagAdapter.notifyDataSetChanged();
                        getCustomerList();
                        ll_searchhistory.setVisibility(View.GONE);
                    }
                    return true;
                }
                return false;
            }
        });
        //返回
        TextView lableTextView = (TextView) mRootView.findViewById(Res.getViewID(null, "jiuyi_searchcustomer_backup"));
        lableTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SerachUtils.getInstance().saveFile(searchHistory);
                backPage();
            }
        });
        ll_searchhistory= (LinearLayout) mRootView.findViewById(Res.getViewID(null, "ll_searchhistory"));
        //获得搜索历史
        searchHistory = SerachUtils.getInstance().getSearchList();
        tagAdapter = new TagAdapter(JiuyiMainApplication.getIns());
        flowTagLayout=(FlowTagLayout) mRootView.findViewById(Res.getViewID(null, "flowTagLayout"));
        if(flowTagLayout!=null){
            //设置这个模式意思是处理流标签点击事件
            flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
            flowTagLayout.setAdapter(tagAdapter);
        }

        tagAdapter.onlyAddAll(searchHistory);

        if(searchHistory==null ||searchHistory.size()==0){
            ll_searchhistory.setVisibility(View.GONE);
        }else {
            ll_searchhistory.setVisibility(View.VISIBLE);
        }

        //点击流标签让历史文字出现在EditText上,并执行搜索
        flowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                View view1 = parent.getAdapter().getView(position, null, null);
                String tag = (String) view1.getTag();
                mEditText.setText(tag);
                searchType="customer";
                labelid=0;
                getCustomerList();
                ll_searchhistory.setVisibility(View.GONE);
                ll_labelsearch.setVisibility(View.GONE);
            }
        });
        ll_clear_history= (LinearLayout) mRootView.findViewById(Res.getViewID(null, "ll_clear_history"));
        ll_clear_history.setClickable(true);
        ll_clear_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SerachUtils.getInstance().clear();
                searchHistory.clear();
                tagAdapter.clear();
                tagAdapter.notifyDataSetChanged();
                ll_searchhistory.setVisibility(View.GONE);
            }
        });

        //标签搜索
        ll_labelsearch= (LinearLayout) mRootView.findViewById(Res.getViewID(null, "ll_labelsearch"));
        ftl_labelsearch=(FlowTagLayout) mRootView.findViewById(Res.getViewID(null, "ftl_labelsearch"));
        tagLabelAdapter = new TagunSelectAdapter(JiuyiMainApplication.getIns());
        if(ftl_labelsearch!=null){
            //设置这个模式意思是处理流标签点击事件
            ftl_labelsearch.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
            ftl_labelsearch.setAdapter(tagLabelAdapter);
        }
        ftl_labelsearch.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                View view1 = parent.getAdapter().getView(position, null, null);
                MemberCenterBean.LabelsBean tag = (MemberCenterBean.LabelsBean) parent.getAdapter().getItem(position);
                mEditText.setText(tag.getDesp());
                labelid=tag.getId();
                searchType="Label";
                getCustomerList();
                ll_searchhistory.setVisibility(View.GONE);
                ll_labelsearch.setVisibility(View.GONE);
            }
        });
        ll_searchresult= (LinearLayout) mRootView.findViewById(Res.getViewID(null, "ll_searchresult"));
        ll_searchresult.setVisibility(View.GONE);
        tv_searchresult= (TextView) mRootView.findViewById(Res.getViewID(null, "tv_searchresult"));

        mRecyclerView = (SwipeMenuRecyclerView) mRootView.findViewById(Res.getViewID(null, "jiuyi_searchcustomer_recycleview"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(JiuyiMainApplication.getIns()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiMainApplication.getIns(), LinearLayoutManager.VERTICAL, 0, getResources().getColor(R.color.background)));

        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);


        // 语音提示主界面
        final RelativeLayout jiuyi_searchcustomer_pop_speek = (RelativeLayout) mRootView.findViewById(Res.getViewID(getContext(), "jiuyi_searchcustomer_pop_speek"));
        // 主界面第一行的文字
        final TextView jiuyi_searchcustomer_pop_speek_tv1 = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "jiuyi_searchcustomer_pop_speek_tv1"));
        // 主界面第四行的文字
        final TextView jiuyi_searchcustomer_pop_speek_tv4 = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "jiuyi_searchcustomer_pop_speek_tv4"));
        // 主界面第五行的文字
        final TextView jiuyi_searchcustomer_pop_speek_tv5 = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "jiuyi_searchcustomer_pop_speek_tv5"));
        // 中间语音开始按钮
        final ImageView jiuyi_searchcustomer_pop_speek_ivstart = (ImageView) mRootView.findViewById(Res.getViewID(getContext(), "jiuyi_searchcustomer_pop_speek_ivstart"));
        // 标题栏语音搜索按钮
        ImageView jiuyi_searchcustomer_mic = (ImageView) mRootView.findViewById(Res.getViewID(getContext(), "jiuyi_searchcustomer_mic"));

        jiuyi_searchcustomer_pop_speek.setVisibility(View.GONE);
        jiuyi_searchcustomer_mic.setVisibility(View.VISIBLE);


            // 右上角关闭按钮
        jiuyi_searchcustomer_pop_speek_ivback = (ImageView) mRootView.findViewById(Res.getViewID(getContext(), "jiuyi_searchcustomer_pop_speek_ivback"));

        View.OnClickListener startSpeek = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //判断语音权限
                if(!havePermission) {
                    //若未授权则申请授权
                    if (!checkRecordPermission()){
                        return;
                    }
                }
                ll_searchhistory.setVisibility(View.GONE);
                ll_searchresult.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);

                //1.创建RecognizerDialog对象
                RecognizerDialog mDialog = new RecognizerDialog(getCallBack().getActivity(), null);
                //2.设置accent、 language等参数
                mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
                mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
                //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
                //结果
                // mDialog.setParameter("asr_sch", "1");
                // mDialog.setParameter("nlp_version", "2.0");
                //3.设置回调接口
                mDialog.setListener(mRecognizerDialogListener);
                //4.显示dialog，接收语音输入
                mDialog.show();
            }
        };
        jiuyi_searchcustomer_pop_speek_ivstart.setOnClickListener(startSpeek);
        jiuyi_searchcustomer_mic.setOnClickListener(startSpeek);

    }
    private RecognizerDialogListener mRecognizerDialogListener =  new RecognizerDialogListener() {

        /**
         *
         * @param recognizerResult 语音识别结果
         * @param b true表示是标点符号
         */
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            // Toast.makeText(MainActivity.this, recognizerResult.getResultString(), Toast.LENGTH_LONG).show();
            if (b) {
                return;
            }
            ResultBean resultBean = mGson.fromJson(recognizerResult.getResultString(), ResultBean.class);
            List<ResultBean.WsBean> ws = resultBean.getWs();
            String w = "";
            for (int i = 0; i < ws.size(); i++) {
                List<ResultBean.WsBean.CwBean> cw = ws.get(i).getCw();
                for (int j = 0; j < cw.size(); j++) {
                    w += cw.get(j).getW();
                }
            }
            mEditText.setText(w);
            mRecyclerView.setVisibility(View.VISIBLE);

            SerachUtils.getInstance().saveSearch(mEditText.getText().toString());
            tagAdapter.onlyAdd(mEditText.getText().toString());
            tagAdapter.notifyDataSetChanged();
            getCustomerList();
        }

        @Override
        public void onError(SpeechError speechError) {

        }
    };


    public void SetTextChanged(final JiuyiEditText edittext) {
        edittext.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable v) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                closeSpeek();
                String str = mEditText.getText().toString();
                if(!Func.IsStringEmpty(str)){

                    ll_searchresult.setVisibility(View.GONE);
                    ll_searchhistory.setVisibility(View.GONE);
                }else {
                    if(mDragAdapter!=null){
                        mDragAdapter.clear();
                        mRecyclerView.setAdapter(mDragAdapter);
                    }
                    ll_searchresult.setVisibility(View.GONE);
                    if(searchHistory==null ||searchHistory.size()==0){
                        ll_searchhistory.setVisibility(View.GONE);
                    }else {
                        ll_searchhistory.setVisibility(View.VISIBLE);
                    }
                    if(labelList!=null && labelList.size()>0){
                        ll_labelsearch.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
    }

    /**
     * 关闭
     */
     public void closeSpeek() {
        if(jiuyi_searchcustomer_pop_speek_ivback != null)
            jiuyi_searchcustomer_pop_speek_ivback.performClick();
    }

    public  List<MemberBean>  getCustomerList(){
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList;
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
//        queryConditionBean.setModuleNumber("002");
        if(labelid>0 ){
            List<String> specialConditionslist=new ArrayList<>();
            specialConditionslist.add("LABELSEARCH_"+labelid);
            queryConditionBean.setSpecialConditions(specialConditionslist);
        }else{
            if(!Func.IsStringEmpty(mEditText.getText().toString().trim()) ){
                List<String> specialConditionslist=new ArrayList<>();
                specialConditionslist.add(mEditText.getText().toString().trim());
                queryConditionBean.setSpecialConditions(specialConditionslist);
            }
        }
        JiuyiHttp.POST("member/find-page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                 .request(new ACallback<MemberListBean>() {
                    @Override
                    public void onSuccess(MemberListBean data) {
                        if(data!=null){
                            List<MemberListBean.ContentBean> ContentBeanlist =data.getContent();
                            mCustomerListBeanList =data.getContent();
                            mDragAdapter = new CustomerListAdapter(mCustomerListBeanList);
                            mRecyclerView.setAdapter(mDragAdapter);
                            mDragAdapter.setOnItemClickListener(onItemClickListener);
                            String scontent="";
                            if(searchType.equals("Label")){
                                scontent="共找到"+ContentBeanlist.size()+"个和标签“"+mEditText.getText().toString()+"”有关的客户";
                            }else{
                                scontent="共找到"+ContentBeanlist.size()+"个和关键词“"+mEditText.getText().toString()+"”有关的客户";
                            }

                            ll_searchresult.setVisibility(View.VISIBLE);
                            tv_searchresult.setText(scontent);
                            ll_labelsearch.setVisibility(View.GONE);


                        }
                        JiuyiLog.e("httplogin","request onSuccess!" + data);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
        return null;
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

                                tagLabelAdapter.onlyAddAll(labelList);
                                ll_labelsearch.setVisibility(View.VISIBLE);
                            }

                        }

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(mDragAdapter.mViewTypeBeanList!=null && mDragAdapter.mViewTypeBeanList.size()>0){
                long customerids[]=new long[mDragAdapter.mViewTypeBeanList.size()];
                for(int i=0;i<mDragAdapter.mViewTypeBeanList.size();i++){
                    MemberListBean.ContentBean contentBean=mDragAdapter.mViewTypeBeanList.get(i);
                    if(contentBean!=null){
                        customerids[i]=contentBean.getId();
                    }
                }
                mBundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerids);
                mBundle.putInt(JiuyiBundleKey.PARAM_CUSTOMERPOSITION, position);
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, mDragAdapter.mViewTypeBeanList.get(position).getId());
            }
            changePage(mBundle, Pub.Customer_main,true);
        }
    };

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerSearchFragment newInstance(int nPageType) {
        JiuyiCustomerSearchFragment f = new JiuyiCustomerSearchFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }

    /**
     * 检查权限
     *
     * @return
     */
    private boolean checkRecordPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(getActivity(), permissions.toArray(new String[0]), 0);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SerachUtils.getInstance().saveFile(searchHistory);
    }
    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_width);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//            if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_MULTI) { // 是需要添加多个菜单的Item。
//                SwipeMenuItem wechatItem = new SwipeMenuItem(mCallBack.getActivity())
//                        .setBackgroundDrawable(R.drawable.tzt_line_background)
//                        .setText("转移")
//                        .setTextColor(Res.getColor(null, "jiuyi_info2_color"))
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(wechatItem);
//                SwipeMenuItem viewLine = new SwipeMenuItem(mCallBack.getActivity())
//                        .setBackgroundDrawable(R.drawable.tzt_line_background)
//                        .setText("|")
//                        .setTextColor(Res.getColor(null, "color_line"))
//                        .setWidth(Res.Dip2Pix(2))
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(viewLine);
//
//                SwipeMenuItem addItem = new SwipeMenuItem(mCallBack.getActivity())
//                        .setBackgroundDrawable(R.drawable.tzt_line_background)
//                        .setText("释放")
//                        .setTextColor(Res.getColor(null, "jiuyi_red_color"))
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(addItem);
//            }else if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_NODIRECT) { // 是需要添加多个菜单的Item。
//                SwipeMenuItem wechatItem = new SwipeMenuItem(mCallBack.getActivity())
//                        .setBackgroundDrawable(R.drawable.tzt_line_background)
//                        .setText("认领")
//                        .setTextColor(Res.getColor(null, "jiuyi_blue"))
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(wechatItem);
//            }else if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_TRANSFER) { // 是需要添加多个菜单的Item。
//                SwipeMenuItem wechatItem = new SwipeMenuItem(mCallBack.getActivity())
//                        .setBackgroundDrawable(R.drawable.tzt_line_background)
//                        .setText("转移")
//                        .setTextColor(Res.getColor(null, "jiuyi_info2_color"))
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(wechatItem);
//            }else if (viewType == CustomerListAdapter.VIEW_TYPE_MENU_RELEASE) { // 是需要添加多个菜单的Item。
//                SwipeMenuItem addItem = new SwipeMenuItem(mCallBack.getActivity())
//                        .setBackgroundDrawable(R.drawable.tzt_line_background)
//                        .setText("释放")
//                        .setTextColor(Res.getColor(null, "jiuyi_red_color"))
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(addItem);
//            }
        }
    };

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                if(mDragAdapter!=null){
                    MemberListBean.ContentBean  contentBean=mDragAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        mBundle.putString(JiuyiBundleKey.PARAM_CUSTOMERNAME, contentBean.getOrgName());
                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,contentBean.getId());
//                        if(contentBean.isClaim()){
//                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_claim);
//                            changePage(mBundle, Pub.Customer_claim,true);
//                        }else{
//                            mBundle.putString(JiuyiBundleKey.PARAM_INCHARGENAME, contentBean.getSalesmanName());
//                            mBundle.putLong(JiuyiBundleKey.PARAM_INCHARGEID, contentBean.getSalesmanId());
//                            if(menuPosition==1){
//                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_release);
//                                changePage(mBundle, Pub.Customer_release,true);
//                            }else if(menuPosition==0){
//                                mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_transfer);
//                                changePage(mBundle, Pub.Customer_transfer,true);
//                            }
//                        }
                    }
                }
            }
        }
    };

}
