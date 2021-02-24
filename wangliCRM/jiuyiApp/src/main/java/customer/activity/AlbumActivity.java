package customer.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.control.utils.Func;
import com.wanglicrm.android.R;
import com.nanchen.compresshelper.CompressHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import customer.Utils.AlbumHelper;
import customer.adapter.AlbumGridViewAdapter;
import customer.adapter.BucketAdapter;
import customer.entity.ImageBean;
import customer.entity.ImageBrowseBean;
import customer.view.Bimp;
import customer.view.ImageBucket;
import customer.view.ListViewDecoration;

/**
 * 这个是进入相册显示所有图片的界面
 */
public class AlbumActivity extends Activity implements BucketAdapter.OnRecyclerViewItemClickListener{
    //显示手机里的所有图片的列表控件
    private GridView gridView;
    //当手机里没有图片时，提示用户没有图片的控件
    private TextView tv,mTvFolderName;
    //gridView的adapter
    private AlbumGridViewAdapter gridImageAdapter;
    //完成按钮
    private Button okButton;
    // 返回按钮
    private Button back;
    // 取消按钮
    private Button cancel;
    private Intent intent;
    // 预览按钮
    private Button preview;
    private Context mContext;
    private ArrayList<ImageBean> dataList;
    private AlbumHelper helper;
    public static List<ImageBucket> contentList;
    public static Bitmap bitmap;
    private RecyclerView mRvBucket;
    private BucketAdapter mBucketAdapter;
    private RelativeLayout mRlBucektOverview;
    public static List<ImageBucket> imageBucketList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plugin_camera_album);
        mContext = this;
        //注册一个广播，这个广播主要是用于在GalleryActivity进行预览时，防止当所有图片都删除完后，再回到该页面时被取消选中的图片仍处于选中状态
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        registerReceiver(broadcastReceiver, filter);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plugin_camera_no_pictures);
        init();
        initListener();
        //这个函数主要用来控制预览和完成按钮的状态
        isShowOkBt();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            //mContext.unregisterReceiver(this);
            // TODO Auto-generated method stub  
            gridImageAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onItemClick(View view, int position) {

        dataList = new ArrayList<ImageBean>();
        dataList.addAll(imageBucketList.get(position).imageList);
        gridView.setVisibility(View.VISIBLE);
        gridImageAdapter = new AlbumGridViewAdapter(mContext, dataList, Bimp.tempSelectBitmap);
        gridView.setAdapter(gridImageAdapter);
        mRlBucektOverview.setVisibility(View.INVISIBLE);
        mTvFolderName.setText(imageBucketList.get(position).bucketName);
        mBucketAdapter.setSelectedBucket(imageBucketList.get(position));
        initListener();

    }

    // 预览按钮的监听
    private class PreviewListener implements OnClickListener {
        public void onClick(View v) {
            if (Bimp.tempSelectBitmap.size() > 0) {
                ArrayList<ImageBrowseBean> imageList = new ArrayList<>();
                for(int j=0;j<Bimp.getTempSelectBitmap().size();j++){
                    if(!Func.IsStringEmpty(Bimp.getTempSelectBitmap().get(j).getImgUrl())){
                        ImageBrowseBean imageBrowseBean =new ImageBrowseBean();
                        imageBrowseBean.setType("Net");
                        imageBrowseBean.setUrl(Bimp.getTempSelectBitmap().get(j).getImgUrl());
                        imageList.add(imageBrowseBean);
                    }else if(Bimp.getTempSelectBitmap().get(j).getPath()!=null){
                        ImageBrowseBean imageBrowseBean =new ImageBrowseBean();
                        imageBrowseBean.setType("Local");
                        imageBrowseBean.setFilePath(Bimp.getTempSelectBitmap().get(j).getPath());
                        imageList.add(imageBrowseBean);
                    }

                }
                Intent intent = new Intent(AlbumActivity.this,ImageBrowseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("param_flag_enum",ImageBrowseActivity.FLAG_ENUM[4]);
                bundle.putInt("param_position",0);
                bundle.putParcelableArrayList("image_url_group",imageList);
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                AlbumActivity.this.startActivity(intent);
            }
        }

    }

    // 完成按钮的监听
    private class AlbumSendListener implements OnClickListener {
        public void onClick(View v) {
            finish();
        }

    }

    // 返回按钮监听
    private class BackListener implements OnClickListener {
        public void onClick(View v) {
            finish();
        }
    }

    // 取消按钮的监听
    private class CancelListener implements OnClickListener {
        public void onClick(View v) {
            Bimp.tempSelectBitmap.clear();
            finish();
        }
    }


    // 初始化，给一些对象赋值
    private void init() {
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        contentList = helper.getImagesBucketList(true);
        dataList = new ArrayList<ImageBean>();
        imageBucketList= new ArrayList<ImageBucket>();
        for (int i = 0; i < contentList.size(); i++) {
            dataList.addAll(contentList.get(i).imageList);
        }
        mRvBucket = (RecyclerView) findViewById(R.id.rv_bucket);
        mRlBucektOverview = (RelativeLayout) findViewById(R.id.rl_bucket_overview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRvBucket.addItemDecoration(new ListViewDecoration());
        mRvBucket.setLayoutManager(linearLayoutManager);
        ImageBucket imageBucketTotal=new ImageBucket();
        imageBucketTotal.setBucketName("所有图片");
        imageBucketTotal.setImageList(dataList);
        if(dataList.size()>0){
            imageBucketTotal.setCount(dataList.size());
        }
        imageBucketList.add(imageBucketTotal);
        if(contentList!=null && contentList.size()>0 ){
            for(int i=0;i<contentList.size();i++){
               imageBucketList.add(contentList.get(i));
            }
        }
        mBucketAdapter = new BucketAdapter(imageBucketList, ContextCompat.getColor(getApplicationContext(), R.color.gallery_bucket_list_item_normal_color));
        mBucketAdapter.setSelectedBucket(imageBucketList.get(0));
        mRvBucket.setAdapter(mBucketAdapter);
        mBucketAdapter.setOnRecyclerViewItemClickListener(this);

        back = (Button) findViewById(R.id.back);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new CancelListener());
        back.setOnClickListener(new BackListener());
        mTvFolderName = (TextView) findViewById(R.id.tv_folder_name);
        mTvFolderName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRlBucektOverview.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.INVISIBLE);
            }
        });
        preview = (Button) findViewById(R.id.preview);
        preview.setOnClickListener(new PreviewListener());
        intent = getIntent();
        Bundle bundle = intent.getExtras();
        gridView = (GridView) findViewById(R.id.myGrid);
        gridImageAdapter = new AlbumGridViewAdapter(mContext, dataList, Bimp.tempSelectBitmap);
        gridView.setAdapter(gridImageAdapter);
        tv = (TextView) findViewById(R.id.myText);
        gridView.setEmptyView(tv);
        okButton = (Button) findViewById(R.id.ok_button);
        okButton.setText("完成" + "(" + Bimp.tempSelectBitmap.size()
                + "/" + 9 + ")");
    }

    private void initListener() {

        gridImageAdapter
                .setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(final ToggleButton toggleButton,
                                            int position, boolean isChecked, Button chooseBt) {
                        if (Bimp.tempSelectBitmap.size() >= 9) {
                            toggleButton.setChecked(false);
                            chooseBt.setVisibility(View.GONE);
                            if (!removeOneData(dataList.get(position))) {
                                Toast.makeText(AlbumActivity.this, "超出可选图片张数", Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                        if (isChecked) {
                            chooseBt.setVisibility(View.VISIBLE);
                            if(dataList.get(position).getPath()!=null){
                                Uri uri = null;
                                File file1 = new File(dataList.get(position).getPath());
//                                if (Build.VERSION.SDK_INT < 24) {
//                                    uri = Uri.fromFile(file1);
//                                } else {
//                                    uri = FileProvider.getUriForFile(AlbumActivity.this, "com.wanglicrm.android.fileProvider", file1 );
//                                }
//                                File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(uri);
                                File newFile = CompressHelper.getDefault(getApplicationContext()).compressToFile(file1);
                                dataList.get(position).setPath(newFile.getPath());
                            }
                            Bimp.tempSelectBitmap.add(dataList.get(position));
                            okButton.setText("完成" + "(" + Bimp.tempSelectBitmap.size()
                                    + "/" + 9 + ")");
                        } else {
                            Bimp.tempSelectBitmap.remove(dataList.get(position));
                            chooseBt.setVisibility(View.GONE);
                            okButton.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + "/" + 9 + ")");
                        }
                        isShowOkBt();
                    }
                });

        okButton.setOnClickListener(new AlbumSendListener());

    }

    private boolean removeOneData(ImageBean ImageBean) {
        if (Bimp.tempSelectBitmap.contains(ImageBean)) {
            Bimp.tempSelectBitmap.remove(ImageBean);
            okButton.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + "/" + 9 + ")");
            return true;
        }
        return false;
    }

    public void isShowOkBt() {
        if (Bimp.tempSelectBitmap.size() > 0) {
            okButton.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + "/" + 9 + ")");
            preview.setPressed(true);
            okButton.setPressed(true);
            preview.setClickable(true);
            okButton.setClickable(true);
            okButton.setTextColor(Color.WHITE);
            preview.setTextColor(Color.WHITE);
        } else {
            okButton.setText("完成" + "(" + Bimp.tempSelectBitmap.size() + "/" + 9 + ")");
            preview.setPressed(false);
            preview.setClickable(false);
            okButton.setPressed(false);
            okButton.setClickable(false);
            okButton.setTextColor(Color.parseColor("#E1E0DE"));
            preview.setTextColor(Color.parseColor("#E1E0DE"));
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;

    }

    @Override
    protected void onRestart() {
        isShowOkBt();
        super.onRestart();
    }
}
