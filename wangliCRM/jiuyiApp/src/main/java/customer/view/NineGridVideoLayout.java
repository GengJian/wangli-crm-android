package customer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.wanglicrm.android.R;
import com.control.utils.Rc;
import com.jiuyi.app.GlideApp;

import java.util.List;

import cn.jzvd.JzvdStd;
import customer.entity.UserViewInfo;

/**
 * 描述：
 */
public class NineGridVideoLayout extends NineGridLayout {

    protected static final int MAX_W_H_RATIO = 3;

    public NineGridVideoLayout(Context context) {
        super(context);
    }

    public NineGridVideoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {

        GlideApp.with(mContext).load(url).placeholder(R.drawable.ic_default_image).into(imageView);
        return false;
    }

    @Override
    protected void displayImage(RatioImageView imageView, String url) {
        GlideApp.with(mContext).load(url).placeholder(R.drawable.ic_default_image).into(imageView);
    }

    @Override
    protected void onClickImage(int i, String url, List<UserViewInfo> urlList) {
        JzvdStd.startFullscreen(Rc.getIns().getBaseCallTopCallBack().getCurrentActivity(), JzvdStd.class, url,urlList.get(i).getUser());
//        JzvdStd.startFullscreen(mContext, JzvdStd.class, url,urlList.get(i).getUser());
    }
}
