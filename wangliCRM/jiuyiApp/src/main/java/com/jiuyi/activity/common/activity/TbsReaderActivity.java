package com.jiuyi.activity.common.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.wanglicrm.android.R;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.model.FileVo;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dynamic.Utils.DownloadUtil;
import dynamic.Utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import messages.timchat.utils.FileUtil;


public class TbsReaderActivity extends JiuyiActivityBase implements
        TbsReaderView.ReaderCallback {

    DownloadUtil  downloadUtil;
    File file;
    TbsReaderView tbsReaderView;
    FrameLayout li_root;
    private String officeUrl="";
    private String officeSaveName="";
    private String tbsReaderTemp = Environment.getExternalStorageDirectory() +
            "/TbsReaderTemp";
    String pdfFileName;
    private String url,name;
    private String pdfName,fileType,titleName;
    private boolean havePermission = false;
    //    private PDFView pdfView;
    Uri uri;
    Integer pageNumber = 0;

    private static final String TAG = TbsReaderActivity.class.getSimpleName();

    public static final int PERMISSION_CODE = 42042;

    public static final String SAMPLE_FILE = "2018.pdf";


    private FrameLayout ll_content;



    @Override
    public void onInit() {
        url=mBundle.getString(JiuyiBundleKey.PARAM_HTTPPDF);
        if(url==null){
            url="";
        }
        fileType=mBundle.getString(JiuyiBundleKey.PARAM_FILETYPE);
        if(fileType==null){
            fileType="";
        }
        titleName=mBundle.getString(JiuyiBundleKey.PARAM_TITLE);
        if(titleName==null){
            titleName="";
        }
        if(!Func.IsStringEmpty(titleName)){
            pdfFileName=titleName;
        }
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "activity_tbs_reader"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        mBodyLayout.getTitleBar().mRightView.setVisibility(View.GONE);
        tbsReaderView = new TbsReaderView(this, this);
        setContentView(mBodyLayout);
        if(!Func.IsStringEmpty(pdfFileName)){
            if(pdfFileName.length()>15){
                pdfFileName= pdfFileName.substring(0,15)+"...";
            }
            setTitle(pdfFileName);
        }

        if(!havePermission) {
            //若未授权则申请授权
            if (!checkRecordPermission()){
                return;
            }
        }
        showLoadingDialog();
        tbsReaderView = new TbsReaderView(this, this);
        li_root=findViewById(R.id.li_root);
        li_root.addView(tbsReaderView);
        downloadUtil=DownloadUtil.get();
        downLoadFile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }




    /**
     * 下载文件
     */
    private  void   downLoadFile(){
        Observable.create(new ObservableOnSubscribe<FileVo>() {
            @Override
            public void subscribe(final ObservableEmitter<FileVo> e) throws Exception {
                final FileVo  fileVo=new FileVo();
                String path= FileUtil.getCachePath(TbsReaderActivity.this);
                downloadUtil.download(url, path,
                        titleName,
                        new DownloadUtil.OnDownloadListener() {
                            @Override
                            public void onDownloadSuccess(File file) {
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                                fileVo.setFile(file);
                                e.onNext(fileVo);
                                e.onComplete();
                            }
                            @Override
                            public void onDownloading(int progress) {
                                Log.d("当前下载的进度",""+progress);
//                                showProgress(progress);
                            }
                            @Override
                            public void onDownloadFailed(Exception e) {
                                if(progressLoadingDialog!=null){
                                    progressLoadingDialog.dismiss();
                                }
                            }
                        });
            }

        }).compose(RxUtils.schedulersTransformer()).subscribe(new Consumer<FileVo>() {
            @Override
            public void accept(FileVo fileVo) {
                showOffice(fileVo);
            }
        });

    }



    /**
     * 加载文件
     */
    private   void   showOffice(FileVo fileVo){
//         progressBar.setProgress(fileVo.getProgress());
        file=fileVo.getFile();
        String bsReaderTemp = tbsReaderTemp;
        File bsReaderTempFile =new File(bsReaderTemp);
        if (!bsReaderTempFile.exists()) {
            boolean mkdir = bsReaderTempFile.mkdir();
            if(!mkdir){
                Log.d("print","创建/TbsReaderTemp失败！！！！！");
            }
        }
        //加载文件
        Bundle localBundle = new Bundle();
        localBundle.putString("filePath", file.toString());
        localBundle.putString("tempPath",
                tbsReaderTemp);
        if (tbsReaderView == null){
            tbsReaderView = getTbsView();
        }
        li_root.setVisibility(View.VISIBLE);
        boolean result = tbsReaderView.preOpen(FileUtil.getFileType(file.toString()), false);
        if (result) {
            tbsReaderView.openFile(localBundle);
        }
    }



    private TbsReaderView getTbsView(){
        return  new TbsReaderView(this,this);

    }


    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tbsReaderView.onStop();
    }
    /**
     * 检查权限
     *
     * @return
     */
    private boolean checkRecordPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TbsReaderActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(TbsReaderActivity.this, permissions.toArray(new String[0]), 0);
                return false;
            }
        }
        return true;
    }


}
