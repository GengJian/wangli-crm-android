package customer.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.wanglicrm.android.R;

import customer.adapter.customerProducttypeAdapter;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerProductinfoFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 客户生产状况
 *****************************************************************
 */
public class JiuyiCustomerProductinfoFragment extends JiuyiFragmentBase implements
        AdapterView.OnItemClickListener {
    private String[] strs = { "产品信息", "工厂设备", "生产动态", "原料信息", "竞品信息", "工厂招工", "生产许可",
            "采购招标", "进出口信息","税务评级","抽查检查","债券信息","购地信息" };
    private ListView listView;
    private customerProducttypeAdapter adapter;
    public static int mPosition;
    private JiuyiCustomerProductinfoRightFragment rightFragment;
    private int productpagetype=0;
    private long Customerid=-1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customerproductinfofragment_layout"), null);
            onInit();
        } else {
            checkRootViewParent();
        }
        return mRootView;
    }
    /**
     * 初始化
     */
    public void onInit() {
        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        mBundle = getArguments();
        if(mBundle == null) {
            Intent intent = getActivity().getIntent();
            if(intent != null) {
                mBundle = intent.getExtras();
            }
        }
        if(mBundle != null){
            productpagetype = mBundle.getInt(JiuyiBundleKey.PARAM_PAGETYPE);
        }else{
            mBundle = new Bundle();
        }
        listView = (ListView) mRootView.findViewById(R.id.listview);
        listView.setDivider(new ColorDrawable(Color.WHITE));
        listView.setDividerHeight(1);
        adapter = new customerProducttypeAdapter(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), strs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        int click_position=0;
        switch (productpagetype) {
            case Pub.Customer_productinfo:
                click_position=0;
                break;
            case Pub.Customer_productequipment:
                click_position=1;
                break;
            case Pub.Customer_productdynamic:
                click_position=2;
                break;
            case Pub.Customer_productmaterial:
                click_position=3;
                break;
            case Pub.Customer_productcompetition:
                click_position=4;
                break;
            case Pub.Customer_productrecruitmentts:
                click_position=5;
                break;
            case Pub.Customer_productlicense:
                click_position=6;
                break;
            case Pub.Customer_productpurchase:
                click_position=7;
                break;
            case Pub.Customer_productimport:
                click_position=8;
                break;
            case Pub.Customer_productrating:
                click_position=9;
                break;
            case Pub.Customer_productcheck:
                click_position=10;
                break;
            case Pub.Customer_productbond:
                click_position=11;
                break;
            case Pub.Customer_productland:
                click_position=12;
                break;

        }
        listView.performItemClick(listView.getChildAt(click_position), click_position, listView.getItemIdAtPosition(click_position));

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //拿到当前位置
        mPosition = position;
        adapter.setCurrentItem(position);
        //及时刷新adapter
        adapter.notifyDataSetChanged();
        rightFragment = new JiuyiCustomerProductinfoRightFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, rightFragment);
        Bundle bundle = new Bundle();
        bundle.putString(JiuyiBundleKey.PARAM_PRODUCTTYPE, strs[position]);
        bundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID,Customerid);
        rightFragment.setArguments(bundle);
        fragmentTransaction.commit();
    }




    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerProductinfoFragment newInstance(int nPageType) {
        JiuyiCustomerProductinfoFragment f = new JiuyiCustomerProductinfoFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerProductinfoFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerProductinfoFragment f = new JiuyiCustomerProductinfoFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
}
