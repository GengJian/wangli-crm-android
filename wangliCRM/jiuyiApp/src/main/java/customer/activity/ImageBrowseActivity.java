package customer.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.control.shared.JiuyiPasswordLockShared;
import com.github.chrisbanes.photoview.PhotoView;
//import com.jaeger.library.StatusBarUtil;
import com.wanglicrm.android.R;
//import com.kevin.photo_browse.ImageBrowseIntent;
//import com.kevin.photo_browse.adapter.MyPagerAdapter;
//import com.kevin.photo_browse.utils.GlideHelper;

import java.util.ArrayList;
import java.util.List;

import customer.Utils.GlideHelper;
import customer.Utils.ImageBrowseIntent;
import customer.adapter.MyPagerAdapter;
import customer.entity.ImageBrowseBean;
import me.relex.circleindicator.CircleIndicator;

//import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Kevin on 2017/8/29.
 */

public class ImageBrowseActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> views;
    private RelativeLayout container;
    private CircleIndicator indicator;

    //类型枚举标志
    public static int[] FLAG_ENUM = new int[]{0,1,2,3,4};
    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_browse);
        container = findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
        JiuyiPasswordLockShared.getIns().setM_bLockNeed(false);
    }

    private void initData(){
        indicator = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewPager);
        // 1.设置幕后item的缓存数目
        viewPager.setOffscreenPageLimit(3);
        // 2.设置页与页之间的间距
        viewPager.setPageMargin(10);

        views = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        switch (bundle.getInt(ImageBrowseIntent.PARAM_FLAG_ENUM)){
            case 0://Url组
                ArrayList<String> imageList = (ArrayList<String>) bundle.get(ImageBrowseIntent.PARAM_URL_GROUP);
                //动态添加View
                for (int i = 0; i < imageList.size(); i++) {
                    View view = LayoutInflater.from(this).inflate(R.layout.adapter_image, null);
                    PhotoView photo_view = view.findViewById(R.id.photo_view);
                    GlideHelper.load(this,imageList.get(i),photo_view,true);
                    views.add(view);
                }
                indicator.setVisibility(View.VISIBLE);
                position = bundle.getInt(ImageBrowseIntent.PARAM_POSITION);
                break;
            case 1://Url单
                View urlView = LayoutInflater.from(this).inflate(R.layout.adapter_image, null);
                PhotoView url_photo_view = urlView.findViewById(R.id.photo_view);
                GlideHelper.load(this,bundle.get(ImageBrowseIntent.PARAM_URL_SINGLE),url_photo_view,true);
                views.add(urlView);
                indicator.setVisibility(View.GONE);
                break;
            case 2://本地资源组
                ArrayList<Integer> imageResIds = bundle.getIntegerArrayList(ImageBrowseIntent.PARAM_RES_ID_GROUP);
                //动态添加View
                for (int i = 0; i < imageResIds.size(); i++) {
                    View view = LayoutInflater.from(this).inflate(R.layout.adapter_image, null);
                    PhotoView photo_view = view.findViewById(R.id.photo_view);
                    GlideHelper.load(this,imageResIds.get(i),photo_view,true);
                    views.add(view);
                }
//                indicator.setVisibility(View.VISIBLE);
                position = bundle.getInt(ImageBrowseIntent.PARAM_POSITION);
                break;
            case 3://本地资源单
                View resIdView = LayoutInflater.from(this).inflate(R.layout.adapter_image, null);
                PhotoView res_id_photo_view = resIdView.findViewById(R.id.photo_view);
                GlideHelper.load(this,bundle.get(ImageBrowseIntent.PARAM_RES_ID_SINGLE),res_id_photo_view,true);
                views.add(resIdView);
//                indicator.setVisibility(View.GONE);
                break;
            case 4://Url组混合本地
                ArrayList<ImageBrowseBean> imageList2 = bundle.getParcelableArrayList(ImageBrowseIntent.PARAM_URL_GROUP);
                //动态添加View
                for (int i = 0; i < imageList2.size(); i++) {
                    View view = LayoutInflater.from(this).inflate(R.layout.adapter_image, null);
                    PhotoView photo_view = view.findViewById(R.id.photo_view);
                    if(imageList2.get(i).getType().equals("Net")){
                        GlideHelper.load(this,imageList2.get(i).getUrl(),photo_view,true);
                    }else if(imageList2.get(i).getType().equals("Local")){
                        GlideHelper.load(this,imageList2.get(i).getFilePath(),photo_view,true);
                    }
                    views.add(view);
                }
                indicator.setVisibility(View.VISIBLE);
                position = bundle.getInt(ImageBrowseIntent.PARAM_POSITION);
                break;
            default:break;
        }
        viewPager.setAdapter(new MyPagerAdapter(views)); // 为viewpager设置adapter
        indicator.setViewPager(viewPager);
        container = findViewById(R.id.container);
        viewPager.setCurrentItem(position);
        // 3.将父类的touch事件分发至viewPgaer，否则只能滑动中间的一个view对象
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
    }
}