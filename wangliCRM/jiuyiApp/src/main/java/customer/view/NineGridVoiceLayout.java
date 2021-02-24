package customer.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.jiuyi.app.GlideApp;
import com.jiuyi.app.JiuyiMainApplication;

import java.util.List;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import customer.Utils.FileUtils;
import customer.entity.Media;
import customer.entity.UserViewInfo;
import messages.timchat.utils.FileUtil;

/**
 * 描述：
 */
public class NineGridVoiceLayout extends NineGridLayout {

    protected static final int MAX_W_H_RATIO = 3;

    public NineGridVoiceLayout(Context context) {
        super(context);
    }

    public NineGridVoiceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {

        GlideApp.with(mContext).load(url).placeholder(R.drawable.infomation_voice).into(imageView);
        return false;
    }

    @Override
    protected void displayImage(RatioImageView imageView, String url) {
//        GlideApp.with(mContext).load(url).placeholder(R.drawable.infomation_voice).into(imageView);
    }
    /**
     * @param imageView
     * @param url
     * @param showNumFlag 是否在最大值的图片上显示还有未显示的图片张数
     */
    @Override
    protected void layoutImageView(RatioImageView imageView, int i, UserViewInfo url, boolean showNumFlag) {
        final int singleWidth = (int) ((mTotalWidth - mSpacing * (3 - 1)) / 3);
        int singleHeight = singleWidth;

        int[] position = findPosition(i);
        int left = (int) ((singleWidth + mSpacing) * position[1]);
        int top = (int) ((singleHeight + mSpacing) * position[0]);
        int right = left + singleWidth;
        int bottom = top + singleHeight;
        imageView.setImageResource(R.drawable.infomation_voice);
        imageView.setPadding(100, 100, 100, 100);
        imageView.layout(left, top, right, bottom);
        addView(imageView);
        float textSize = 10;
        final TextView textView = new TextView(mContext);
        long fileTime=0;
        try {
            if(!Func.IsStringEmpty(url.getUser())){
                fileTime=Long.parseLong(url.getUser());
            }

        } catch (Exception e) {
            throw e;
        }
        textView.setText(FileUtils.getAudioDuration(fileTime));
        textView.setTextColor(Color.WHITE);
        textView.setPadding(0, singleHeight*2 / 3 +getFontHeight(textSize), 0, 0);
        textView.setTextSize(textSize);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.BLACK);
        textView.getBackground().setAlpha(120);

        textView.layout(left, top, right, bottom);
        addView(textView);


//
//        final ImageView iv_video = new ImageView(mContext);
//        iv_video.setImageResource(R.drawable.videoopen);
//        iv_video.setPadding(100, 100, 100, 100);
//        iv_video.layout(left, top, right, bottom);
//        addView(iv_video);
        displayImage(imageView, url.getUrl());
    }

    @Override
    protected void onClickImage(int i, String url, List<UserViewInfo> urlList) {
        if(urlList!=null && urlList.size()>0){
            Bundle mBundle=new Bundle();
            Media media=new Media();
            media.setFileTime(urlList.get(i).getUser());
            media.setUrl(urlList.get(i).getUrl());
            mBundle.putParcelable(JiuyiBundleKey.PARAM_COMMONBEAN,media);
            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_RecordPlay,true);
        }
    }
}
