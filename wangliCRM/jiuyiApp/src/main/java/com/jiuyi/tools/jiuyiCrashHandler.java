package com.jiuyi.tools;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;

import com.control.permission.JiuyiHiPermissionUtil;
import com.control.utils.JiuyiHardNo;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.jiuyi.app.JiuyiActivityBase;
import com.jiuyi.app.JiuyiActivityManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * UncaughtExceptionHandler：线程未捕获异常控制器是用来处理未捕获异常的。 如果程序出现了未捕获异常默认情况下则会出现强行关闭对话框
 * 实现该接口并注册为程序中的默认未捕获异常处理 这样当未捕获异常发生时，就可以做些异常处理操作 例如：收集异常信息，发送错误报告 等。
 * <p>
 * UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.
 */
public class jiuyiCrashHandler implements UncaughtExceptionHandler {
    /**
     * Debug JiuyiLog Tag
     */
    public static final String TAG = "uncaughtException";
    /**
     * 是否开启日志输出, 在Debug状态下开启, 在Release状态下关闭以提升程序性能
     */
    public static final boolean DEBUG = true;
    /**
     * CrashHandler实例
     */
    private static jiuyiCrashHandler INSTANCE;
    /**
     * 程序的Context对象
     */
    private Context mContext;

    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /** 使用Properties来保存设备的信息和错误堆栈信息 */
    //private Properties mDeviceCrashInfo = new Properties();
    //private static final String VERSION_NAME = "versionName";
    //private static final String VERSION_CODE = "versionCode";
    //private static final String STACK_TRACE = "STACK_TRACE";
    /** 错误报告文件的扩展名 */
    //private static final String CRASH_REPORTER_EXTENSION = ".cr";
    /**
     * 错误日志
     */
    private static String mErrorMessage;

    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    //用于格式化日期,作为日志文件名的一部分  
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private jiuyiCrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static jiuyiCrashHandler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new jiuyiCrashHandler();
        return INSTANCE;
    }

    public static boolean isNull() {
        return INSTANCE == null;
    }

    /**
     * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
     *
     * @param ctx
     */
    public void init(Context ctx) {
        mContext = ctx;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //主线程异常拦截
                while (true) {
                    try {
                        Looper.loop();//主线程的异常会从这里抛出
                    } catch (Throwable e) {
                        uncaughtException(null, e);
                    }
                }
            }
        });

        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            if (JiuyiActivityManager.getCurrentActivity() != null) {
                if (JiuyiActivityManager.getCurrentActivity() instanceof JiuyiActivityBase) {
                    ((JiuyiActivityBase) JiuyiActivityManager.getCurrentActivity()).backPage();
                } else {
                    JiuyiActivityManager.popActivity(JiuyiActivityManager.getCurrentActivity(), false);
                }
            }
            mErrorMessage = JiuyiLog.getStackTraceString(ex);
            //只是打印出异常即可
            JiuyiLog.e("uncaughtException", mErrorMessage);

            handleException(ex);
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }


        // // 收集设备信息
        collectCrashDeviceInfo(mContext);
        // // 保存错误报告文件
        String crashFileName = saveCrashInfoToFile(ex);
        return true;
    }


    /**
     * 收集程序崩溃的设备信息
     *
     * @param ctx
     */
    public void collectCrashDeviceInfo(Context ctx) {
        infos.clear();
        JiuyiHardNo mHardNo = JiuyiHardNo.getIns();

        // 获取设备IMEI 移动设备身份码 全世界唯一   A000004E10DFE42
        infos.put("imei", mHardNo.getmImei());
        // 获取手机名称 NX507J
        infos.put("phonename", mHardNo.getmPhoneBuildModel());
        // 获取手机系统版本号 4.4.2
        infos.put("systemversion", mHardNo.getmPhoneSysVersion());

        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            JiuyiLog.e(TAG, e.getLocalizedMessage());
        }

        // 升级版本号 upVersion 1.01.000
        String upVersion = Rc.cfg.getUpVersion();
        if(!TextUtils.isEmpty(upVersion))
            infos.put("upversion", upVersion);

    }



    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfoToFile(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);

        saveInfoToFile(sb.toString(), "crash");
        return null;
    }

    /**
     * 保存网页请求错误信息到文件中
     *
     * @param log         文件内容
     * @param prefilename 文件名的前缀，奔溃的是crash，网页日志文件为webfailed
     * @return
     */
    public String saveInfoToFile(final String log, String prefilename) {
        String time = formatter.format(new Date());
        final String fileName = mContext.getPackageName().replace(".", "-") + "-" + prefilename + "-" + time + "-log.txt";
        new JiuyiHiPermissionUtil(JiuyiActivityManager.getCurrentActivity()).checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, new JiuyiHiPermissionUtil.onGuaranteeCallBack(){
            @Override
            public void onGuarantee(String permission, int position) {
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/jiuyilog/";
                        File dir = new File(path);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        File saveFile = new File(path, fileName);
                        FileOutputStream fos = new FileOutputStream(saveFile);
                        fos.write(log.getBytes());
                        fos.close();
                    }
                } catch (Exception e) {
                    JiuyiLog.e(TAG, e.getLocalizedMessage());
                }
               /* new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/jiuyilog/";
                                File dir = new File(path);
                                if (!dir.exists()) {
                                    dir.mkdirs();
                                }
                                File saveFile = new File(path, fileName);
                                FileOutputStream fos = new FileOutputStream(saveFile);
                                fos.write(log.getBytes());
                                fos.close();
                            }
                        } catch (Exception e) {
                            JiuyiLog.e(TAG, e.getLocalizedMessage());
                        }
                    }
                }).start();*/
            }
        });
        return fileName;
    }

    /**
     * 保存错误报告信息
     *
     * @param Str
     */
//	public void WriteErrorMessage(String Str) {
//		try {
//			CYlsFileBase InitInfoFile = new CYlsFileBase(CYlsFileBase.ErrorReportMsg, false);
//			InitInfoFile.wrireBytes(Str.getBytes());
//			InitInfoFile.close();
//		} catch (Exception e) {
//			JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
//		}
//	}

    /**
     *  崩溃信息入库
     *  add by zhengss
     * @param ex
     * @return
     */
    /**
     * 崩溃信息入库
     * add by zhengss
     * @param ex
     */
    private void saveCrashInfoToDB(Throwable ex) {
        //用来存储设备信息和错误日志
        //为防止属性值中出现特殊字符导致解析错误,有时候需要对值进行编码, 编码规则如下B64+xxxxxx 表示用 Base64-utf8 进行编码
        HashMap<String, String> infos2DB = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
//            String value = "B64+" + Base64.encodeToString(entry.getValue().getBytes(), Base64.DEFAULT);
            infos2DB.put(key, value);
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        result = "B64+" + Base64.encodeToString(result.getBytes(), Base64.NO_WRAP);//Base64.NO_WRAP避免每76个字符，加一个换行符
        infos2DB.put("crashlog",result);

    }
}
