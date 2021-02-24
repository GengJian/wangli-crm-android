package customer.fragment;


import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.utils.DialogID;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;
import com.control.widget.dialog.JiuyiDialogBase;
import com.facebook.drawee.view.SimpleDraweeView;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;
import com.wanglicrm.android.R;
import com.loader.ILoader;
import com.loader.LoaderManager;

import customer.entity.CustomerLinkmanBean;
import customer.entity.MemberReadBean;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCustomerContactViewPageFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 人事基本信息
 *****************************************************************
 */

public class JiuyiCustomerContactViewPageFragment extends JiuyiFragmentBase {
    private long Customerid=-1;
    private LinearLayout ll_content;
    private TextView tv_no_authoritytext;
    private ImageView iv_no_authority;
    private CardView cvContactInfo;
    private SimpleDraweeView ivAvatar;
    private TextView tvName;
    private TextView tvPostion;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvHobby;
    CustomerLinkmanBean.ContentBean linkmanBean;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Res.getLayoutID(null, "jiuyi_customercontactviewpagefragment_layout"), null);
            setOnRefreshListener();
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
    public static JiuyiCustomerContactViewPageFragment newInstance(CustomerLinkmanBean.ContentBean bean) {
        JiuyiCustomerContactViewPageFragment f = new JiuyiCustomerContactViewPageFragment();
        Bundle args = new Bundle();
        args.putParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN, bean);
        f.setArguments(args);
        return f;
    }
    /**
     * 初始化
     */
    @Override
    public void onInit() {
        linkmanBean = mBundle.getParcelable(JiuyiBundleKey.PARAM_LINKMANBEAN);
//        Customerid=mBundle.getLong(JiuyiBundleKey.PARAM_CUSTOMERID);
        tv_no_authoritytext=(TextView)mRootView.findViewById(Res.getViewID(getContext(), "tv_no_authoritytext"));
        iv_no_authority=(ImageView)mRootView.findViewById(Res.getViewID(getContext(), "iv_no_authority"));
        cvContactInfo = (CardView) mRootView.findViewById(R.id.cvclientInfo);
        cvContactInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(linkmanBean!=null){
                    mBundle.putString(JiuyiBundleKey.PARAM_LINKMANBEANID,linkmanBean.getId()+"");
                    mBundle.putString(JiuyiBundleKey.PARAM_BILLTYPE,"Contact");
                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_ContactsInfo,true);
                }

            }
        });
        ivAvatar = (SimpleDraweeView) mRootView.findViewById(R.id.ivAvatar);
        ivAvatar.setImageResource(R.drawable.m_avatar_def);
//        LoaderManager.getLoader().loadNet(ivAvatar, msAvatar, new ILoader.Options(R.drawable.m_avatar_def, R.drawable.m_avatar_def));
        tvName = (TextView) mRootView.findViewById(R.id.tv_name);
        tvPostion = (TextView) mRootView.findViewById(R.id.tv_postion);
        tvPhone = (TextView) mRootView.findViewById(R.id.tv_phone);
        tvEmail = (TextView) mRootView.findViewById(R.id.tv_email);
        tvHobby = (TextView) mRootView.findViewById(R.id.tv_hobby);
        if(linkmanBean!=null){
            if(linkmanBean.getName()!=null){
                tvName.setText(linkmanBean.getName());
            }
            if(linkmanBean.getDuty()!=null){
                tvPostion.setText(linkmanBean.getDuty());
            }
            if(linkmanBean.getPhone()!=null){
                tvPhone.setText(linkmanBean.getPhone());
            }
            if(linkmanBean.getEmail()!=null){
                tvEmail.setText(linkmanBean.getEmail());
            }
            if(linkmanBean.getFavorite()!=null){
                tvHobby.setText(linkmanBean.getFavorite());
            }
            if(linkmanBean.getAvatralUrl()!=null){
                LoaderManager.getLoader().loadNet(ivAvatar, linkmanBean.getAvatralUrl(), new ILoader.Options(R.drawable.m_avatar_def, R.drawable.m_avatar_def));
            }


        }



    }
    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerContactViewPageFragment newInstance(int nPageType) {
        JiuyiCustomerContactViewPageFragment f = new JiuyiCustomerContactViewPageFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiCustomerContactViewPageFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiCustomerContactViewPageFragment f = new JiuyiCustomerContactViewPageFragment();
        Bundle args;
        if(mBundle != null){
            args = (Bundle) mBundle.clone();
        }else {
            args = new Bundle();
        }        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }


}
