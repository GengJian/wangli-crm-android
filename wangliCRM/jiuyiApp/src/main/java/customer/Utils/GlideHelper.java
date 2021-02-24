package customer.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.wanglicrm.android.R;
import com.jiuyi.app.GlideApp;

/**
 * Created by zhengss on 2017/8/22.
 */

public final class GlideHelper {
    public static void load(Context context, Object url, ImageView imageView){
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_error)
                .into(imageView);
    }
    public static void load(Activity activity,Object url,ImageView imageView){
        load(activity,url,imageView,true);
    }
    public static void load(final Activity activity, Object url, ImageView imageView, boolean isClickFinish){
        GlideApp.with(activity)
                .load(url)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_error)
                .into(imageView);
        if(isClickFinish){
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
        }
    }
}
