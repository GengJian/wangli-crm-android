package mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.control.utils.JiuyiBundleKey;
import com.control.utils.Res;
import com.control.widget.JiuyiFragmentBase;

/**
 * ****************************************************************
 * 文件名称 : JiuyiMineCollectionProductFragment
 * 作       者 : zhengss
 * 创建时间:2018/7/03 14:43
 * 文件描述 : 我的产品收藏
 *****************************************************************
 */
public class JiuyiMineCollectionProductFragment extends JiuyiFragmentBase {

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
    public static JiuyiMineCollectionProductFragment newInstance(int nPageType) {
        JiuyiMineCollectionProductFragment f = new JiuyiMineCollectionProductFragment();
        Bundle args = new Bundle();
        args.putInt(JiuyiBundleKey.PARAM_PAGETYPE, nPageType);
        f.setArguments(args);
        return f;
    }
    /**
     * 静态工厂方法需要一个1();nt型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static JiuyiMineCollectionProductFragment newInstance(int nPageType, Bundle mBundle) {
        JiuyiMineCollectionProductFragment f = new JiuyiMineCollectionProductFragment();
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

    }
}
