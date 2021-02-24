package customer.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.common.GsonUtil;
import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.JiuyiDateUtil;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.JiuyiButton;
import com.control.widget.JiuyiEditText;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.DictBean;
import com.recyclerview.swipe.Closeable;
import com.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.recyclerview.swipe.SwipeMenu;
import com.recyclerview.swipe.SwipeMenuCreator;
import com.recyclerview.swipe.SwipeMenuItem;
import com.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import commonlyused.db.DbHelper;
import commonlyused.db.DictBeanDao;
import customer.Utils.FastUtils;
import customer.adapter.NewSpecialProductAdapter;
import customer.entity.NewSpecialPriceBean;
import customer.entity.NewSpecialProductBean;
import customer.listener.OnItemClickListener;
import customer.view.jiuyiRecycleViewDivider;

import static customer.Utils.ViewOperatorType.EDIT;
import static customer.adapter.NewSpecialProductAdapter.VIEW_TYPE_MENU_DELETE;

/**
 * ****************************************************************
 * 文件名称 : JiuyiNewSpecialPriceActivity
 * 作       者 : zhengss
 * 创建时间:2018/8/9 14:43
 * 文件描述 : 特价
 *****************************************************************
 */
public class JiuyiNewSpecialPriceActivity extends JiuyiActivityBase {
    private TextView tv_client,mtvcomplete;
    private TextView tvStartday;
    private TextView tvEndday;
    private TextView tvReason;
    private JiuyiEditText et_remark;
    private LinearLayout llAdd;
    private TextView tvAdd;
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    NewSpecialProductAdapter menuAdapter;
    private JiuyiButton mbtnsave;
    private long reasonID=0;
    private List<NewSpecialProductBean> beanList;
    private static final int PRODUCT_CODE = 100;
    private  NewSpecialProductBean newSpecialProductBean;
    private NewSpecialPriceBean newSpecialPriceBean;
    long Customerid =0;
    String CustomerName="";
    private Date dateStart, dateEnd,dateNow;
    private String operType="";
    private  NewSpecialProductBean modifyBean;


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_newspecialprice_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
         setContentView(mBodyLayout);
        setTitle();

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        newSpecialPriceBean=new NewSpecialPriceBean();
        mbtnsave= (JiuyiButton) mBodyLayout.findViewById(Res.getViewID(null, "btn_save"));
        if(mbtnsave!=null){
            mbtnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (FastUtils.isFastClick()) {
                        if(newSpecialPriceBean==null){
                            newSpecialPriceBean=new NewSpecialPriceBean();
                        }
                        if(Customerid>0){
                            newSpecialPriceBean.setMemberId(Customerid);
                        }
                        newSpecialPriceBean.setBeginDate(tvStartday.getText().toString());
                        newSpecialPriceBean.setEndDate(tvEndday.getText().toString());
                        List<NewSpecialPriceBean.SpecialRecordItemsBean> specialRecordItems=new ArrayList<>();
                        if(beanList!=null && beanList.size()>0){
                            for(int i=0;i<beanList.size();i++){
                                NewSpecialProductBean bean= beanList.get(i);
                                NewSpecialPriceBean.SpecialRecordItemsBean specialRecordItemsBean=new NewSpecialPriceBean.SpecialRecordItemsBean();
                                specialRecordItemsBean.setBatchNumber(bean.getBatchNum());
                                specialRecordItemsBean.setGrade(bean.getLevelCode());
                                specialRecordItemsBean.setPrice(bean.getSpecialPrice()+"");
                                specialRecordItems.add(specialRecordItemsBean);
                            }
                            newSpecialPriceBean.setSpecialRecordItems(specialRecordItems);
                        }
                        newSpecialPriceBean.setRemark(et_remark.getText().toString());
                        boolean mbcheck=false;
                        mbcheck=check();
                        if(!mbcheck){
                            return;
                        }
                        showDialog();
                        submit();
                    }

                }
            });
        }
        tv_client = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_client"));
        tv_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FastUtils.isFastClick()) {
                    operType="CLIENT";
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"plan");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE, Pub.CustomerSelect);
                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                    changePage(mBundle,Pub.CustomerSelect,true);
                }
            }
        });

        et_remark = (JiuyiEditText) mBodyLayout.findViewById(Res.getViewID(null, "et_remark"));
        tvStartday = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_startday"));
        if (tvStartday != null) {
            tvStartday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiNewSpecialPriceActivity.this);
                    //设置上下年分限制
                    dialog.setYearLimt(60);
                    //设置标题
                    dialog.setTitle("选择时间");
                    //设置类型
                    dialog.setType(DateType.TYPE_YMD);
                    //设置消息体的显示格式，日期格式
                    dialog.setMessageFormat("yyyy-MM-dd");
                    //设置选择回调
                    dialog.setOnChangeLisener(null);
                    //设置点击确定按钮回调
                    dialog.setOnSureLisener(new OnSureLisener() {
                        @Override
                        public void onSure(Date date) {
                            tvStartday.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        }
                    });
                    dialog.show();
                }
            });

        }

        tvEndday = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_endday"));
        if (tvEndday != null) {
            tvEndday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickDialog dialog = new DatePickDialog(JiuyiNewSpecialPriceActivity.this);
                    //设置上下年分限制
                    dialog.setYearLimt(60);
                    //设置标题
                    dialog.setTitle("选择时间");
                    //设置类型
                    dialog.setType(DateType.TYPE_YMD);
                    //设置消息体的显示格式，日期格式
                    dialog.setMessageFormat("yyyy-MM-dd");
                    //设置选择回调
                    dialog.setOnChangeLisener(null);
                    //设置点击确定按钮回调
                    dialog.setOnSureLisener(new OnSureLisener() {
                        @Override
                        public void onSure(Date date) {
                            tvEndday.setText( new SimpleDateFormat("yyyy-MM-dd").format(date));
                        }
                    });
                    dialog.show();
                }
            });

        }

        tvReason= (TextView) mBodyLayout.findViewById(Res.getViewID(null, "tv_reason"));
        tvReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder buidler = new AlertDialog.Builder(JiuyiNewSpecialPriceActivity.this);
                buidler.setTitle("低价原因");
                final String[] data;
                final List<DictBean> dictBeansList = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                        .where(DictBeanDao.Properties.Name.eq("low_price_reason")).list();
                if(dictBeansList!=null &&dictBeansList.size()>0){
                    data=new String[dictBeansList.size()];
                    for(int i=0;i<dictBeansList.size();i++){
                        data[i]=dictBeansList.get(i).getValue();
                    }
                    buidler.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tvReason.setText(data[which].trim());
                            reasonID =dictBeansList.get(which).getOriginalid();
                            newSpecialPriceBean.setLowReason(dictBeansList.get(which).getValue());
                            dialog.dismiss();
                        }
                    });
                    buidler.show();
                }

            }
        });

        llAdd = (LinearLayout) mBodyLayout.findViewById(R.id.ll_add);
        llAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JiuyiAddProductActivity.class);
                startActivityForResult(intent, PRODUCT_CODE);
            }
        });

        swipeMenuRecyclerView = (SwipeMenuRecyclerView) mBodyLayout.findViewById(R.id.recycler_view);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(JiuyiNewSpecialPriceActivity.this));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(JiuyiNewSpecialPriceActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));

        swipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        swipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        beanList=new ArrayList();
    }


    public void setTitle(){
        mTitle = "新建特价申请";
        setTitle(mTitle);
    }
    public void submit(){
        JiuyiHttp.POST("special-record/save")
                .setJson(GsonUtil.gson().toJson(newSpecialPriceBean))
                .addHeader("Authorization", Rc.getIns().id_tokenparam)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        if(data!=null){
                            startDialog(DialogID.DialogTrue, "", "提交成功！", JiuyiDialogBase.Dialog_Type_Yes, null);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }
                        startDialog(DialogID.DialogDoNothing, "", errMsg, JiuyiDialogBase.Dialog_Type_Yes, null);
                    }
                });

    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue)
        {
            Rc.mBackfresh=true;
            backPage();
        }
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
            if (viewType == VIEW_TYPE_MENU_DELETE) { // 是需要添加多个菜单的Item。
                SwipeMenuItem wechatItem = new SwipeMenuItem(JiuyiNewSpecialPriceActivity.this)
                        .setBackgroundDrawable(R.drawable.tzt_red)
                        .setText("删除")
                        .setTextColor(Res.getColor(null, "jiuyi_white_color"))
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(wechatItem);

            }
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
                if(menuAdapter!=null){
                    NewSpecialProductBean contentBean=menuAdapter.mViewTypeBeanList.get(adapterPosition);
                    if(contentBean!=null){
                        menuAdapter.mViewTypeBeanList.remove(adapterPosition);
                        menuAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    };

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            if(menuAdapter!=null){
                NewSpecialProductBean contentBean=menuAdapter.mViewTypeBeanList.get(position);
                if(contentBean!=null){
                    operType="MODIFY";
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE, EDIT);
                    mBundle.putParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN, contentBean);
                    modifyBean=contentBean;
                    mBundle.putString(JiuyiBundleKey.PARAM_DOSTARTNEXTACTIVITYFORRESULT, "1");
                    mBundle.putInt(JiuyiBundleKey.PARAM_PAGETYPE,Pub.Customer_newspecialpriceproduct);
                    changePage(mBundle, Pub.Customer_newspecialpriceproduct,true);


                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode == PRODUCT_CODE) {
            if(data == null || data.getExtras() == null) {
                return;
            }
            if(newSpecialProductBean==null){
                newSpecialProductBean=new NewSpecialProductBean();
            }
            Bundle bundle = data.getExtras();
            newSpecialProductBean=bundle.getParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN);

            if(newSpecialProductBean!=null){

                if(newSpecialProductBean!=null){
                    if(beanList!=null && beanList.size()>0){
                        for(int i=0;i<beanList.size();i++){
                            if(newSpecialProductBean.getBatchNum().equals(beanList.get(i).getBatchNum()) && newSpecialProductBean.getLevelName().equals(beanList.get(i).getLevelName()) ){
                                beanList.remove(i);
                            }
                        }
                    }
                    beanList.add(newSpecialProductBean);
                    if(menuAdapter==null){
                        menuAdapter = new NewSpecialProductAdapter(beanList);
                        menuAdapter.setOnItemClickListener(onItemClickListener);
                        swipeMenuRecyclerView.setAdapter(menuAdapter);
                    }
                    if(menuAdapter!=null){
                        menuAdapter.notifyDataSetChanged();
                    }
                }
            }

        }else if(requestCode == 0){
             if(data == null || data.getExtras() == null) {
                 return;
             }
             Bundle bundle = data.getExtras();
             if(operType.equals("MODIFY")){
                 newSpecialProductBean=bundle.getParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN);

                 if(newSpecialProductBean!=null){

                     if(modifyBean!=null){
                         if(beanList!=null && beanList.size()>0){
                             for(int i=0;i<beanList.size();i++){
                                 if(modifyBean.getBatchNum().equals(beanList.get(i).getBatchNum()) && modifyBean.getLevelName().equals(beanList.get(i).getLevelName()) ){
                                     beanList.remove(i);
                                 }
                             }
                         }
                         modifyBean=null;

                     }
                     if(beanList!=null && beanList.size()>0){
                         for(int i=0;i<beanList.size();i++){
                             if(newSpecialProductBean.getBatchNum().equals(beanList.get(i).getBatchNum()) && newSpecialProductBean.getLevelName().equals(beanList.get(i).getLevelName()) ){
                                 beanList.remove(i);
                             }
                         }
                     }
                     beanList.add(newSpecialProductBean);
                     if(menuAdapter==null){
                         menuAdapter = new NewSpecialProductAdapter(beanList);
                         menuAdapter.setOnItemClickListener(onItemClickListener);
                         swipeMenuRecyclerView.setAdapter(menuAdapter);
                     }
                     if(menuAdapter!=null){
                         menuAdapter.notifyDataSetChanged();
                     }
                 }






             }else{
                 Customerid = bundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
                 String CustomerName= bundle.getString(JiuyiBundleKey.PARAM_CUSTOMERNAME);
                 tv_client.setText(CustomerName);
             }

         }
    }

    public boolean check(){
        if(TextUtils.isEmpty(tv_client.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择客户！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvStartday.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入特价开始日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(TextUtils.isEmpty(tvEndday.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请输入特价结束日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            try {
                dateStart = dateFormat.parse(tvStartday.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dateEnd = dateFormat.parse(tvEndday.getText().toString());
            dateNow= dateFormat.parse(JiuyiDateUtil.getNowTime3());
            if (dateStart.getTime() > dateEnd.getTime()) {
                startDialog(DialogID.DialogDoNothing, "", "开始日期需要小于结束日期！", JiuyiDialogBase.Dialog_Type_Yes, null);
                return false;
            }
            if (dateNow.getTime() > dateEnd.getTime()) {
                startDialog(DialogID.DialogDoNothing, "", "结束时间需要晚于当前时间！", JiuyiDialogBase.Dialog_Type_Yes, null);
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(JiuyiNewSpecialPriceActivity.this, "数据格式有误！", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if(TextUtils.isEmpty(tvReason.getText().toString().trim())){
            startDialog(DialogID.DialogDoNothing, "", "请选择申请原因！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        if(beanList==null ||beanList.size()==0){
            startDialog(DialogID.DialogDoNothing, "", "请添加产品！", JiuyiDialogBase.Dialog_Type_Yes, null);
            return false;
        }
        return true;

    }

}
