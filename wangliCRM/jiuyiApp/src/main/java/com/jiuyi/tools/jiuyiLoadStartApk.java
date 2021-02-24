package com.jiuyi.tools;

import android.Manifest;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.widget.ProgressBar;

import com.control.utils.Func;
import com.control.utils.JiuyiLog;
import com.jiuyi.activity.common.activity.JiuyiHeadPageActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class jiuyiLoadStartApk {
    /** 上下文*/
    private Context mContext;
    /** 提示文字  */
    private String mContent = "";
    /** 要下载的安装包url*/
    protected String mApkUrl = "";
    /** 第三方应用调用url(scheme或包名)*/
    private String mPackageName = "";
    /** Activity名字*/
    private String mActivityName = "";
    /** 当opentype等于0或1时activityurl为空，当为2时activityurl为要打开的activity值*/
    private int nOpenType = 0;//
    /** 下载确认提示的dialog*/
    private Dialog mNoticeDialog;
    /** 下载进度的dialog*/
    private Dialog mDownloadDialog;
     /* 下载包安装路径 */  
    private static final String savePath = "/sdcard/updatekhapk/";
    /** 下载包安装路径和名字*/
    private static final String saveFileName = savePath + "kh.apk";  
  
    /* 进度条与通知ui刷新的handler和msg常量 */  
    private ProgressBar mProgress;
    /** 下载进度*/
    private int progress;
    /** Handler类型 正在下载*/
    private static final int DOWN_UPDATE = 1;
    /** Handler类型 下载完成*/
    private static final int DOWN_OVER = 2;

    /** 下载的线程*/
    private Thread mDownLoadThread;
    /** 下载过程中是否点击了取消*/
    private boolean mInterceptFlag = false;
    private boolean havePermission = false;
      
    private Handler mHandler = new Handler(){  
        public void handleMessage(Message msg) {  
            switch (msg.what) {  
            case DOWN_UPDATE:  
                mProgress.setProgress(progress);
                break;  
            case DOWN_OVER:  
            	mDownloadDialog.dismiss();
            	
            	setUpVersionDialogAllowChangePage();
            	
                installApk();  
                break;  
            default:  
                break;  
            }  
        }
    };


    /**
     * 构造函数
     * @param context
     * @param url
     */
    public jiuyiLoadStartApk(Context context, Object url) {
        this.mContext = context;  

        
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.clear();
		Func.getMapValue((String)url, null, reqMap, "&&", true);

		mContent = reqMap.get("mContent".toUpperCase());
        mApkUrl = reqMap.get("downloadurl".toUpperCase());//下载
        mPackageName = reqMap.get("appurl".toUpperCase());//第三方应用调用url(scheme或包名)
        mActivityName = reqMap.get("activityurl".toUpperCase());//要打开的第三方应用的activity值
        nOpenType = Math.max(0, Func.parseInt(reqMap.get("opentype".toUpperCase())));//当opentype等于0或1时activityurl为空，当为2时activityurl为要打开的activity值
    }  

    /**
     * 打开软件，如果不存在就下载
     * bDownInWebView 如果不存在就打开webview
     */
    public String checkUpdateInfo(boolean bDownInWebView){ 
		try {
			Intent launchApp = null;
			switch(nOpenType){
				default:
				case 0://scheme
					Uri url = Uri.parse(mPackageName);
					launchApp = new Intent(Intent.ACTION_VIEW, url); 
					break;
				case 1://包名
					launchApp = mContext.getPackageManager().getLaunchIntentForPackage(mPackageName);  
					break;
				case 2://activity
					launchApp = new Intent();
					ComponentName componetName = new ComponentName(mPackageName, mActivityName);  
					launchApp.setComponent(componetName);  
					break;
			}
			
			launchApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
			mContext.startActivity(launchApp);
			return "";
		} catch (Exception e) {
			if(!bDownInWebView){
				showNoticeDialog(mApkUrl);
				return "";
			}else{
				return mApkUrl;
			}
		}
    }
    public void checkUpdateInfo(){  
    	checkUpdateInfo(false);
    }  
	
    /**
     * 检查apk是否安装
     */
    public boolean checkApkInstalled(String packageName){  
        final PackageManager packageManager = mContext.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for ( int i = 0; i < pinfo.size(); i++ )
        {
            if(pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    /**
     * 弹出下载确认对话框
     * @param packageName
     */
    private void showNoticeDialog(final
    		String packageName){  
        AlertDialog.Builder builder = new Builder(mContext);  
        builder.setTitle("下载软件");  
        builder.setMessage(Func.IsStringEmpty(mContent) ? ("是否下载"+packageName) : mContent);
        builder.setPositiveButton("确定", new OnClickListener() {           
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                showDownloadDialog(packageName);             
            }  
        });  
        builder.setNegativeButton("以后再说", new OnClickListener() {             
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();
                
                setUpVersionDialogAllowChangePage();
            }  
        });  
        mNoticeDialog = builder.create();
        mNoticeDialog.show();
        
        setUpVersionDialogNotAllowChangePage();
    }

    /**
     * 弹出下载对话框
     * @param packageName
     */
    private void showDownloadDialog(String packageName){  
        AlertDialog.Builder builder = new Builder(mContext);  
        builder.setTitle("正在下载中");  
          
        mProgress = new ProgressBar(mContext);

        builder.setView(mProgress);  
        builder.setNegativeButton("取消", new OnClickListener() {   
            @Override  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss();  
                mInterceptFlag = true;
                
                setUpVersionDialogAllowChangePage();
            }  
        });  
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
          
        downloadApk();  
    }

    /**
     * 下载apk
     */
    private Runnable mdownApkRunnable = new Runnable() {      
        @Override  
        public void run() {  
            try {  
                URL url = new URL(mApkUrl);
              
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
                conn.connect();  
                int length = conn.getContentLength();  
                InputStream is = conn.getInputStream();  
                  
                File file = new File(savePath);  
                if(!file.exists()){  
                    file.mkdir();  
                }  
                String apkFile = saveFileName;  
                File ApkFile = new File(apkFile);  
                FileOutputStream fos = new FileOutputStream(ApkFile);  
                  
                int count = 0;  
                byte buf[] = new byte[1024];  
                  
                do{                   
                    int numread = is.read(buf);  
                    count += numread;  
                    progress =(int)(((float)count / length) * 100);
                    //更新进度  
                    mHandler.sendEmptyMessage(DOWN_UPDATE);  
                    if(numread <= 0){      
                        //下载完成通知安装  
                        mHandler.sendEmptyMessage(DOWN_OVER);  
                        break;  
                    }  
                    fos.write(buf,0,numread);  
                }while(!mInterceptFlag);//点击取消就停止下载.
                  
                fos.close();  
                is.close();  
            } catch (MalformedURLException e) {  
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            } catch(IOException e){  
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            }  
              
        }  
    };  
      
     /** 
     * 下载apk线程
     */
    private void downloadApk(){  
        mDownLoadThread = new Thread(mdownApkRunnable);
        mDownLoadThread.start();
    }  
     /** 
     * 安装apk
     */  
    private void installApk(){  
        File apkfile = new File(saveFileName);  
        if (!apkfile.exists()) {  
            return;  
        }
        havePermission = checkInstallPermission();
        if(!havePermission){
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //此处涉及到的android7.0的适配,后面欧文给出了具体方案
            Uri fileUri = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".fileprovider", apkfile);
            i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            i.setDataAndType(fileUri, "application/vnd.android.package-archive");
        } else {
            i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        }

        mContext.startActivity(i);//下载
      
    }  
    
    
    
	/**
	 * 正在弹出升级对话框或者其他对话框供用户选择，待选择后再跳转
	 * 按确定键时是不允许在跳转到首页去的，直接退出软件
	 * 安返回建，如果是强制升级则退出软件；如果是建议升级则继续请求并正常跳转
	 * false表示当前没有升级dialog或已经关闭，true表示已经弹出了升级dialog但是尚没有关闭
	 */
	public void setUpVersionDialogAllowChangePage() {
        // zhengss 如果在headPage界面，弹出了软件下载的对话框则设置软件不能跳转，否则dialog可能会被关闭
        //当关闭dialog时，要通知headPage进行跳转
        if(mContext!=null && mContext instanceof JiuyiHeadPageActivity){
        	((JiuyiHeadPageActivity)mContext).setUpVersionDialogForbiddenChangePage();
        }
	}
	public void setUpVersionDialogNotAllowChangePage() {
        // zhengss 如果在headPage界面，弹出了软件下载的对话框则设置软件不能跳转，否则dialog可能会被关闭
        //详细接收请见setUpVersionDialogForbiddenChangePage
        if(mContext!=null && mContext instanceof JiuyiHeadPageActivity){
        	((JiuyiHeadPageActivity)mContext).setUpVersionDialogNotAllowChangePage();
        }
	}

    /**
     * 检查权限
     *
     * @return
     */
    private boolean checkInstallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(mContext, Manifest.permission.REQUEST_INSTALL_PACKAGES)) {
                permissions.add(Manifest.permission.REQUEST_INSTALL_PACKAGES);
            }
            if (permissions.size() != 0) {
                if(mContext!=null && mContext instanceof JiuyiHeadPageActivity){
                    ActivityCompat.requestPermissions((JiuyiHeadPageActivity)mContext, permissions.toArray(new String[0]), 0);
                }

                return false;
            }
        }
        return true;
    }
}