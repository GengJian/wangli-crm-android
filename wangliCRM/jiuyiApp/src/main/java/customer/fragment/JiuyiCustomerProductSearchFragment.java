package customer.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiEditText;
import com.control.widget.keyboard.JiuyiKeyBoardManager;
import com.control.widget.recyclerView.JiuyiRecyclerView;
import com.google.gson.Gson;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiMainApplication;
import com.control.widget.recyclerView.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.QueryConditionBean;
import customer.Utils.KeyBoardUtils;
import customer.Utils.ProductSerachUtils;
import customer.Utils.ViewOperatorType;
import customer.adapter.NeedPlanAdapter;
import customer.adapter.ReceiptPlanAdapter;
import customer.adapter.ReceiveAddressAdapter;
import customer.adapter.TagAdapter;
import customer.entity.DemandPlanBean;
import customer.entity.GatheringPlanBean;
import customer.entity.GatheringplanChartBean;
import customer.entity.ResultBean;
import customer.listener.OnTagClickListener;
import customer.view.FlowTagLayout;
import mine.Utils.ReceiptPlanSerachUtils;
import mine.bean.MineDeliveryPlanBean;
import mine.bean.PlanFindDetailCondition;

import static com.control.utils.Rc.getApplication;
/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerProductSearchFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 产品搜索
 *****************************************************************
 */
public class JiuyiCustomerProductSearchFragment extends JiuyiFragmentBase {
    JiuyiRecyclerView mRecyclerView = null;
    /**
     * 右上角语音关闭按钮
     */
    private ImageView jiuyi_searchcustomer_pop_speek_ivback;

//    /**
//     * 数据适配器
//     */
    private NeedPlanAdapter mDragAdapter = null;
    private JiuyiEditText mEditText;
    private boolean havePermission = false;//初始化时判断是否有录音权限
    private TextView tv_searchresult;
    private LinearLayout ll_searchhistory,ll_searchresult,ll_clear_history;
    FlowTagLayout flowTagLayout;
    private List<String> searchHistory;//搜索历史
    private TagAdapter tagAdapter;
    private long customerid=-1;
    private Gson mGson;
    private String billType="";
    List<GatheringplanChartBean.ListBean> listBeanList;
    private ReceiptPlanAdapter adapter;
    MineDeliveryPlanBean.ContentBean contentBean;
    PlanFindDetailCondition planFindDetailCondition;

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
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_fragment_searchcustomerproduct_layout"), null);
            onInit();
        } else {
            checkRootViewParent();
        }

        return mRootView;
    }
    @Override
    public void onInit(){
        mGson = new Gson();
        customerid= mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        billType= mBundle.getString(JiuyiBundleKey.PARAM_BILLTYPE);
        contentBean= mBundle.getParcelable(JiuyiBundleKey.PARAM_PLANEAN);
        planFindDetailCondition=new PlanFindDetailCondition();
        if(contentBean!=null){
            planFindDetailCondition.setOperatorCollectBean(contentBean);
        }
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
                        if(billType.equals("ReceiptPlan")){
                            ReceiptPlanSerachUtils.getInstance().saveSearch(mEditText.getText().toString());
                        }else {
                            ProductSerachUtils.getInstance().saveSearch(mEditText.getText().toString());
                        }
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
                if(billType.equals("ReceiptPlan")){
                    ReceiptPlanSerachUtils.getInstance().saveFile(searchHistory);
                }else {
                    ProductSerachUtils.getInstance().saveFile(searchHistory);
                }
                backPage();
            }
        });
        ll_searchhistory= (LinearLayout) mRootView.findViewById(Res.getViewID(null, "ll_searchhistory"));
        //获得搜索历史

        if(billType.equals("ReceiptPlan")){
            searchHistory = ReceiptPlanSerachUtils.getInstance().getSearchList();
        }else {
            searchHistory = ProductSerachUtils.getInstance().getSearchList();
        }
        tagAdapter = new TagAdapter(JiuyiMainApplication.getIns());
        flowTagLayout=(FlowTagLayout) mRootView.findViewById(Res.getViewID(null, "flowTagLayout"));
        if(flowTagLayout!=null){
            //设置这个模式意思是处理流标签点击事件
            flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_NONE);
            flowTagLayout.setAdapter(tagAdapter);
        }

        tagAdapter.onlyAddAll(searchHistory);

        //点击流标签让历史文字出现在EditText上,并执行搜索
        flowTagLayout.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                View view1 = parent.getAdapter().getView(position, null, null);
                String tag = (String) view1.getTag();
                mEditText.setText(tag);
                getCustomerList();
                ll_searchhistory.setVisibility(View.GONE);
            }
        });
        ll_clear_history= (LinearLayout) mRootView.findViewById(Res.getViewID(null, "ll_clear_history"));
        ll_clear_history.setClickable(true);
        ll_clear_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(billType.equals("ReceiptPlan")){
                    ReceiptPlanSerachUtils.getInstance().clear();
                }else {
                    ProductSerachUtils.getInstance().clear();
                }
                searchHistory.clear();
                tagAdapter.clear();
                tagAdapter.notifyDataSetChanged();
            }
        });


        ll_searchresult= (LinearLayout) mRootView.findViewById(Res.getViewID(null, "ll_searchresult"));
        ll_searchresult.setVisibility(View.GONE);
        tv_searchresult= (TextView) mRootView.findViewById(Res.getViewID(null, "tv_searchresult"));

        mRecyclerView = (JiuyiRecyclerView) mRootView.findViewById(Res.getViewID(null, "jiuyi_searchcustomer_recycleview"));
        mRecyclerView.setVerticalFadingEdgeEnabled(false);// 顶部渐变色
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    //RecyclerView滑动，判断键盘是否开启，开启就默认关闭
                    if (!JiuyiKeyBoardManager.getIns().IsKeyBoardEmpty(getKeyBoardView())) {
                        if (getKeyBoardView().isShowing()) {
                            getKeyBoardView().hideKeyboard();
                        }
                    }
                }
                return false;
            }
        });

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
//                        jiuyi_searchcustomer_pop_speek_tv1.setText(Res.getString(getContext(), "jiuyi_customer_pop_speak_nopermission"));
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
//            ll_searchresult.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);

            ProductSerachUtils.getInstance().saveSearch(mEditText.getText().toString());
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
                    ll_searchhistory.setVisibility(View.VISIBLE);
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

    public  void getCustomerList(){
        if(billType.equals("Mine")){
            getDemand_planDetailList();
            return;
        }else if(billType.equals("ReceiptPlan")){
            getGatheringPlanList();
            return;
        }
        List<Long> value = new ArrayList<Long>();
        List<QueryConditionBean.RulesBean> rulesBeanList;
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        List<String> specialConditionslist=new ArrayList<>();
        if(!Func.IsStringEmpty(mEditText.getText().toString().trim())){
            specialConditionslist.add(mEditText.getText().toString().trim());
            queryConditionBean.setSpecialConditions(specialConditionslist);
        }

        if(billType.equals("Customer")){
            rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("member.id");
            rulesBean.setOption("EQ");
            rulesBeanList.add(rulesBean);
            value.add(customerid);
            rulesBean.setValues(value);
            queryConditionBean.setRules(rulesBeanList);
        }else if(billType.equals("Mine")){
            rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();
            QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
            rulesBean.setField("salesMan.id");
            rulesBean.setOption("EQ");
            rulesBeanList.add(rulesBean);
            value.add(Rc.id);
            rulesBean.setValues(value);
            queryConditionBean.setRules(rulesBeanList);
        }
        JiuyiHttp.POST("demand_plan/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.getIns().id_tokenparam)
                 .request(new ACallback<DemandPlanBean>() {
                    @Override
                    public void onSuccess(DemandPlanBean data) {
                        if(data!=null){
                            List<DemandPlanBean.ContentBean> contentBeanList=data.getContent();

                            mDragAdapter = new NeedPlanAdapter(R.layout.customer_item_needplan, contentBeanList);
                            mRecyclerView.setAdapter(mDragAdapter);
                            mDragAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newReceiptplan);
                                    DemandPlanBean.ContentBean contentBean=(DemandPlanBean.ContentBean)adapter.getData().get(position);
                                    if(contentBean.getStatus().equals("APPROVALED")){
                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                    }else{
                                        mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                    }
                                    mBundle.putLong(JiuyiBundleKey.PARAM_NEEDPLANID, contentBean.getId());
                                    changePage(mBundle, Pub.Customer_newneedplan,true);
                                }
                            });
                            String scontent="";
                            scontent="共找到"+contentBeanList.size()+"个和关键词“"+mEditText.getText().toString()+"”有关的批号";
                            tv_searchresult.setText(scontent);
                            ll_searchresult.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }

    public  void  getDemand_planDetailList(){
        PlanFindDetailCondition.PagerBean pager =new PlanFindDetailCondition.PagerBean();
        pager.setSize(10000);
        pager.setDirection("DESC");
        pager.setProperty("id");
        pager.setNumber(0);
        List<String> specialConditions=new ArrayList<>();
        if(!Func.IsStringEmpty(mEditText.getText().toString().trim())){
            specialConditions.add(mEditText.getText().toString().trim());
            pager.setSpecialConditions(specialConditions);
        }
        planFindDetailCondition.setPager(pager);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("demand_plan/find_detail_plan?")
                        .setJson(GsonUtil.gson().toJson(planFindDetailCondition))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<DemandPlanBean>() {
                            @Override
                            public void onSuccess(DemandPlanBean data) {
                                if(data!=null){
                                    List<DemandPlanBean.ContentBean> contentBeanList=data.getContent();

                                    mDragAdapter = new NeedPlanAdapter(R.layout.customer_item_needplan, contentBeanList);
                                    mRecyclerView.setAdapter(mDragAdapter);
                                    mDragAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newReceiptplan);
                                            DemandPlanBean.ContentBean contentBean=(DemandPlanBean.ContentBean)adapter.getData().get(position);
                                            if(contentBean.getStatus().equals("APPROVALED")){
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                            }else{
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                            }
                                            mBundle.putLong(JiuyiBundleKey.PARAM_NEEDPLANID, contentBean.getId());
                                            changePage(mBundle, Pub.Customer_newneedplan,true);
                                        }
                                    });
                                    String scontent="";
                                    scontent="共找到"+contentBeanList.size()+"个和关键词“"+mEditText.getText().toString()+"”有关的批号";
                                    tv_searchresult.setText(scontent);
                                    ll_searchresult.setVisibility(View.VISIBLE);


                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });


            }
        };
        thread.start();

    }

    public  void  getGatheringPlanList(){

        final QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("DESC");
        queryConditionBean.setProperty("createdDate");
        queryConditionBean.setFromClientType("android");
        List<String> value = new ArrayList<String>();
        List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
        QueryConditionBean.RulesBean rulesBean=new QueryConditionBean.RulesBean();
        rulesBean.setField("salesMan.id");
        rulesBean.setOption("EQ");
        value.add(Rc.id+"");
        rulesBean.setValues(value);
        queryConditionBean.setRules(rulesBeanList);
        List<String> specialConditionslist=new ArrayList<>();
        if(!Func.IsStringEmpty(mEditText.getText().toString().trim())){
            specialConditionslist.add(mEditText.getText().toString().trim());
            queryConditionBean.setSpecialConditions(specialConditionslist);
        }
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                JiuyiHttp.POST("gathering_plan/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
                        .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<GatheringPlanBean>() {
                            @Override
                            public void onSuccess(GatheringPlanBean data) {
                                if(data!=null){
                                    List<GatheringPlanBean.ContentBean> contentBeanList=data.getContent();
                                    adapter = new ReceiptPlanAdapter(R.layout.customer_item_receiptplan, contentBeanList);
                                    adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
                                    mRecyclerView.setAdapter(adapter);
                                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            GatheringPlanBean.ContentBean contentBean=(GatheringPlanBean.ContentBean)adapter.getData().get(position);
                                            mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newReceiptplan);
                                            mBundle.putLong(JiuyiBundleKey.PARAM_BILLID,contentBean.getId());
                                            if(contentBean.getStatus().equals("APPROVALED")){
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.VIEW);
                                            }else{
                                                mBundle.putString(JiuyiBundleKey.PARAM_OPERATORTYPE, ViewOperatorType.EDIT);
                                            }
                                            String msOwetotal="",msDuetotal="",customerName,customerId;
                                            changePage(mBundle, Pub.Customer_newReceiptplan,true);
                                        }
                                    });
                                    String scontent="";
                                    scontent="共找到"+contentBeanList.size()+"个和关键词“"+mEditText.getText().toString()+"”有关的计划";
                                    tv_searchresult.setText(scontent);

                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                            }
                        });

            }
        };
        thread.start();

    }
    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerProductSearchFragment newInstance(int nPageType) {
        JiuyiCustomerProductSearchFragment f = new JiuyiCustomerProductSearchFragment();
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
        if(billType.equals("ReceiptPlan")){
            ReceiptPlanSerachUtils.getInstance().saveFile(searchHistory);
        }else {
            ProductSerachUtils.getInstance().saveFile(searchHistory);
        }
    }
}
