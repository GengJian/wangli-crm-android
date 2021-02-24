package mine.fragment;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.control.utils.JiuyiBundleKey;
import com.control.utils.Res;
import com.control.widget.JiuyiEditText;
import com.control.widget.JiuyiFragmentBase;
import com.wanglicrm.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customer.adapter.CustomerSelMenuAdapter;
import customer.adapter.ReceiveAddressAdapter;
import customer.view.SFPopupWindow;
import customer.view.jiuyiRecycleViewDivider;
import dynamic.adapter.OrderListAdapter;
import dynamic.entity.OrderBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineCollectionAllFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的收藏
 *****************************************************************
 */
public class JiuyiMineCollectionAllFragment extends JiuyiFragmentBase implements
        View.OnClickListener {
    private ListView popListView;
    private List<Map<String, Object>> menuData1, menuData2, menuData3;
    private SFPopupWindow popMenu;
    private CustomerSelMenuAdapter menuAdapter1, menuAdapter2, menuAdapter3;

    private LinearLayout mLinemonthsort, mLinebirthplacesort, mLinecount;
    private TextView mTvcharsort, mTvquicksort, mTvmanual, mTvaddplan;

    private String currentProduct, currentSort, currentActivity;
    private int menuIndex = 0;
    private List<OrderBean.ContentBean> mViewTypeBeanList;
    private int[] imageids = { R.drawable.client_selected };
    private OrderListAdapter adapter;
    private JiuyiEditText medsearchplan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_collectionallfragment_layout"), null);
            onInit();
        } else {
            checkRootViewParent();
        }
        return mRootView;
    }


    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineCollectionAllFragment newInstance(int nPageType) {
        JiuyiMineCollectionAllFragment f = new JiuyiMineCollectionAllFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineCollectionAllFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineCollectionAllFragment f = new JiuyiMineCollectionAllFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    @Override
    public void onInit() {
        findView();
        initMenuData();
        initPopMenu();

        // 这里只是模拟数据，模拟Item的ViewType，根据ViewType决定显示什么菜单，到时候你可以根据你的数据来决定ViewType。
        mViewTypeBeanList = new ArrayList<>();

        RecyclerView swipeMenuRecyclerView = (RecyclerView) mRootView.findViewById(R.id.rv_orderlist);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(mCallBack.getActivity()));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new jiuyiRecycleViewDivider(mCallBack.getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.background)));
        adapter = new OrderListAdapter(R.layout.jiuyi_orders_item, mViewTypeBeanList);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getCallBack().getActivity(), 1, false));
        swipeMenuRecyclerView.setAdapter(adapter);
        adapter.openLoadAnimation(ReceiveAddressAdapter.SLIDEIN_LEFT);
        adapter.setOnCLickItemListener(new OrderListAdapter.onCLickItemListener() {
            @Override
            public void click(int position, OrderListAdapter receiveAddress, View view) {

            }
        });
    }

    private void initMenuData() {
        menuData1 =  new ArrayList<Map<String, Object>>();
        String[] menuStr1 = new String[] { "400D/T289"};
        Map<String, Object>  map1;
        for (int i = 0, len = menuStr1.length; i < len; ++i) {
            map1 = new HashMap<String, Object>();
            map1.put("name", menuStr1[i]);
            map1.put("select", imageids[0]);
            menuData1.add(map1);
        }

        menuData2 = new ArrayList<Map<String, Object>>();
        String[] menuStr2 = new String[] { "一等品", "二等品","三等品" };
        Map<String, Object>map2;
        for (int i = 0, len = menuStr2.length; i < len; ++i) {
            map2 = new HashMap<String, Object>();
            map2.put("name", menuStr2[i]);
            map2.put("select", imageids[0]);
            menuData2.add(map2);
        }

        menuData3 = new ArrayList<Map<String, Object>>();
        String[] menuStr3 = new String[] { "10月19" };
        Map<String, Object> map3;
        for (int i = 0, len = menuStr3.length; i < len; ++i) {
            map3 = new HashMap<String, Object>();
            map3.put("name", menuStr3[i]);
            map3.put("select", imageids[0]);
            menuData3.add(map3);
        }
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ll_date:
                mTvcharsort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter1);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 0;
                break;
            case R.id.ll_place:
                mTvquicksort.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter2);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 1;
                break;
            case R.id.ll_status:
                mTvmanual.setTextColor(Res.getColor(null, "jiuyi_blue"));
                popListView.setAdapter(menuAdapter3);
                popMenu.showAsDropDown(mLinemonthsort, 0, 2);
                menuIndex = 2;
                break;

        }
    }
    protected void findView() {
        mLinemonthsort = (LinearLayout) mRootView.findViewById(Res.getViewID(getContext(), "ll_monthsort"));
        mLinebirthplacesort = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_birthplace_sort"));
        mLinecount = (LinearLayout)  mRootView.findViewById(Res.getViewID(getContext(), "ll_countsort"));
        mTvcharsort = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_monthsort"));
        mTvquicksort = (TextView)  mRootView.findViewById(Res.getViewID(getContext(), "tv_birthplacesort"));
        mTvmanual = (TextView) mRootView.findViewById(Res.getViewID(getContext(), "tv_countsort"));
        mLinemonthsort.setOnClickListener(this);
        mLinebirthplacesort.setOnClickListener(this);
        mLinecount.setOnClickListener(this);


    }
    private void initPopMenu() {
        initMenuData();
        View contentView = View.inflate(mCallBack.getActivity(), R.layout.popwin_supplier_list,
                null);
        popMenu = new SFPopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                mTvcharsort.setTextColor(Color.parseColor("#5a5959"));
                mTvquicksort.setTextColor(Color.parseColor("#5a5959"));
                mTvmanual.setTextColor(Color.parseColor("#5a5959"));
            }
        });

        popListView = (ListView) contentView.findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                popMenu.dismiss();
            }
        });



        menuAdapter1=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData1);
        menuAdapter2=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData2);
        menuAdapter3=new CustomerSelMenuAdapter(mCallBack.getActivity(), menuData3);
        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                if (menuIndex == 0) {
                    //改变选中状态
                    menuAdapter1.setCurrentItem(pos);
                    //通知ListView改变状态
                    menuAdapter1.notifyDataSetChanged();
                } else if (menuIndex == 1) {
                    //改变选中状态
                    menuAdapter2.setCurrentItem(pos);
                    //通知ListView改变状态
                    menuAdapter2.notifyDataSetChanged();
                } else {
                    //改变选中状态
                    menuAdapter3.setCurrentItem(pos);
                    //通知ListView改变状态
                    menuAdapter3.notifyDataSetChanged();
                }
                popMenu.dismiss();
                if (menuIndex == 0) {
                    currentProduct = menuData1.get(pos).get("name").toString();
                    mTvcharsort.setText(currentProduct);
                    Toast.makeText(mCallBack.getActivity(), currentProduct, Toast.LENGTH_SHORT).show();
                } else if (menuIndex == 1) {
                    currentSort = menuData2.get(pos).get("name").toString();
                    mTvquicksort.setText(currentSort);
                    Toast.makeText(mCallBack.getActivity(), currentSort, Toast.LENGTH_SHORT).show();
                } else {
                    currentActivity = menuData3.get(pos).get("name").toString();
                    mTvmanual.setText(currentActivity);
                    Toast.makeText(mCallBack.getActivity(), currentActivity, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
