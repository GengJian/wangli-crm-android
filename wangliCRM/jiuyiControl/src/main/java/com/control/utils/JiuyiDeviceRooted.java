package com.control.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * ****************************************************************
 * 文件名称:JiuyiDeviceRooted.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:检查设备是否Root过了
 * 注意事项:参考http://blog.csdn.net/lintax/article/details/70988565
 *          1、通过isEmulatorDevice()判断是否是模拟器环境
 *          2、通过isDeviceRooted()判断是否是root过
 * ****************************************************************
 */

public class JiuyiDeviceRooted {
    private static String LOG_TAG = JiuyiDeviceRooted.class.getName();

    /**
     * 检测设备是否Root过
     * @return
     */
    public static boolean isDeviceRooted() {
        if (checkDeviceDebuggable()){return true;}//check buildTags
        if (checkSuperuserApk()){return true;}//Superuser.apk
        if (checkBusybox()){return true;}//find su use 'which'
        if (checkAccessRootData()){return true;}//find su use 'which'
        if (checkRootPathSU()){return true;}//find su in some path
        if (checkRootWhichSU()){return true;}//find su use 'which'
//        if (checkGetRootAuth()){return true;}//exec su
        return false;
    }

    /**
     * 查看系统是否测试版
     * 返回结果“release-keys”，代表此系统是正式发布版。
     * 说明：若是非官方发布版，很可能是完全root的版本，存在使用风险。
     *      可是在实际情况下，我遇到过某些厂家的正式发布版本，也是test-keys，可能大家对这个标识也不是特别注意吧。
     * @return
     */
    private static boolean checkDeviceDebuggable(){
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            Log.i(LOG_TAG,"buildTags="+buildTags);
            return true;
        }
        return false;
    }


    /**
     * 检查是否存在Superuser.apk
     * Superuser.apk是一个被广泛使用的用来root安卓设备的软件，所以可以检查这个app是否存在
     * @return
     */
    private static boolean checkSuperuserApk(){
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                Log.i(LOG_TAG,"/system/app/Superuser.apk exist");
                return true;
            }
        } catch (Exception e) { }
        return false;
    }

    /**
     * 执行busybox
     * Android是基于Linux系统的，可是在终端Terminal中操作，会发现一些基本的命令都找不到。这是由于Android系统为了安全，将可能带来风险的命令都去掉了，
     * 最典型的，例如su，还有find、mount等。对于一个已经获取了超级权限的人来讲，这是很不爽的事情，所以，便要想办法加上自己需要的命令了。
     * 一个个添加命令也麻烦，有一个很方便的方法，就是使用被称为“嵌入式Linux中的瑞士军刀”的Busybox。简单的说BusyBox就好像是个大工具箱，
     * 它集成压缩了 Linux 的许多工具和命令。
     * 所以若设备root了，很可能Busybox也被安装上了。这样我们运行busybox测试也是一个好的检测方法。
     * @return
     */
    private static synchronized boolean checkBusybox()
    {
        try
        {
            Log.i(LOG_TAG,"to exec busybox df");
            String[] strCmd = new String[] {"busybox","df"};
            ArrayList<String> execResult = executeCommand(strCmd);
            if (execResult != null){
                Log.i(LOG_TAG,"execResult="+execResult.toString());
                return true;
            }else{
                Log.i(LOG_TAG,"execResult=null");
                return false;
            }
        } catch (Exception e)
        {
            Log.i(LOG_TAG, "Unexpected error - Here is what I know: "
                    + e.getMessage());
            return false;
        }
    }
    /**
     * 访问/data目录，查看读写权限
     */
    private static synchronized boolean checkAccessRootData()
    {
        try
        {
            Log.i(LOG_TAG,"to write /data");
            String fileContent = "test_ok";
            Boolean writeFlag = writeFile("/data/su_test",fileContent);
            if (writeFlag){
                Log.i(LOG_TAG,"write ok");
            }else{
                Log.i(LOG_TAG,"write failed");
            }

            Log.i(LOG_TAG,"to read /data");
            String strRead = readFile("/data/su_test");
            Log.i(LOG_TAG,"strRead="+strRead);
            if(fileContent.equals(strRead)){
                return true;
            }else {
                return false;
            }
        } catch (Exception e)
        {
            Log.i(LOG_TAG, "Unexpected error - Here is what I know: "
                    + e.getMessage());
            return false;
        }
    }

    /**
     * 执行linux下的shell命令
     * @param shellCmd
     * @return
     */
    private static ArrayList<String> executeCommand(String[] shellCmd){
        String line = null;
        ArrayList<String> fullResponse = new ArrayList<String>();
        Process localProcess = null;
        try {
            Log.i(LOG_TAG,"to shell exec which for find su :");
            localProcess = Runtime.getRuntime().exec(shellCmd);
        } catch (Exception e) {
            return null;
        }
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(localProcess.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
        try {
            while ((line = in.readLine()) != null) {
                Log.i(LOG_TAG,"–> Line received: " + line);
                fullResponse.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(LOG_TAG,"–> Full response was: " + fullResponse);
        return fullResponse;
    }

    //写文件
    private static Boolean writeFile(String fileName,String message){
        try{
            FileOutputStream fout = new FileOutputStream(fileName);
            byte [] bytes = message.getBytes();
            fout.write(bytes);
            fout.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //读文件
    private static String readFile(String fileName){
        File file = new File(fileName);
        try {
            FileInputStream fis= new FileInputStream(file);
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            while((len=fis.read(bytes))>0){
                bos.write(bytes, 0, len);
            }
            String result = new String(bos.toByteArray());
            Log.i(LOG_TAG, result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 检测在常用目录下是否存在su
     * su是Linux下切换用户的命令，在使用时不带参数，就是切换到超级用户。通常我们获取root权限，就是使用su命令来实现的，所以可以检查这个命令是否存在。
     * 这个方法是检测常用目录，那么就有可能漏过不常用的目录。
     * @return
     */
    private static boolean checkRootPathSU()
    {
        File f=null;
        final String kSuSearchPaths[]={"/system/bin/","/system/xbin/","/system/sbin/","/sbin/","/vendor/bin/"};
        try{
            for (int i=0;i<kSuSearchPaths.length;i++)
            {
                f=new File(kSuSearchPaths[i]+"su");
                if(f!=null&&f.exists())
                {
                    Log.i(LOG_TAG,"find su in : "+kSuSearchPaths[i]);
                    return true;
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 使用which命令查看是否存在su
     * 然而，这个方法也存在一个缺陷，就是需要系统中存在which这个命令。我在测试过程中，就遇到有的Android系统中没有这个命令，所以，这也不是一个完全有保障的方法，
     * 倒是可以和上一个方法（在常用路径下查找）进行组合，能提升成功率。这种查找命令的方式，还有一种缺陷，就是可能系统中存在su，但是已经失效的情况。
     * 例如，我曾经root过，后来又取消了，就可能出现这种情况：有su这个文件，但是当前设备不是root的。
     * @return
     */
    private static boolean checkRootWhichSU() {
        String[] strCmd = new String[] {"/system/xbin/which","su"};
        ArrayList<String> execResult = executeCommand(strCmd);
        if (execResult != null){
            Log.i(LOG_TAG,"execResult="+execResult.toString());
            return true;
        }else{
            Log.i(LOG_TAG,"execResult=null");
            return false;
        }
    }

    /**
     * 执行su，看能否获取到root权限
     * 我们执行这个命令su。这样，系统就会在PATH路径中搜索su，如果找到，就会执行，执行成功后，就是获取到真正的超级权限了。
     * 这种检测su的方法，应该是最靠谱的，不过，也有个问题，就是在已经root的设备上，会弹出提示框，请求给app开启root权限。这个提示不太友好，可能用户会不喜欢。
     * 如果想安静的检测，可以用上两种方法的组合；如果需要尽量安全的检测到，还是执行su吧。
     * @return
     */
    private static synchronized boolean checkGetRootAuth()
    {
        Process process = null;
        DataOutputStream os = null;
        try
        {
            Log.i(LOG_TAG,"to exec su");
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();
            int exitValue = process.waitFor();
            Log.i(LOG_TAG, "exitValue="+exitValue);
            if (exitValue == 0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (Exception e)
        {
            Log.i(LOG_TAG, "Unexpected error - Here is what I know: "
                    + e.getMessage());
            return false;
        } finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                process.destroy();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }



    private static String[] known_device_ids = { "000000000000000"};
    private static String[] known_files = {
            "/system/lib/libc_malloc_debug_qemu.so",
            "/sys/qemu_trace",
            "/system/bin/qemu-props"
    };
    /**
     * 检测运行环境是否为模拟器
     * 当返回值为true时，禁止应用启动
     * @return
     */
    public static boolean isEmulatorDevice() {
        if(CheckDeviceIDS(Rc.getApplication())){return true;}
        if(CheckEmulatorFiles()){return true;}
        if(CheckEmulatorBuild(Rc.getApplication())){return true;}
        return false;
    }

    /**
     * 通过设备的deviceID检测运行环境是否为模拟器
     * 当CheckDeviceIDS()返回值为true时，禁止应用启动
     * @param context
     * @return
     */
    private static Boolean CheckDeviceIDS(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String device_ids = telephonyManager.getDeviceId();
        for (String know_deviceid : known_device_ids) {
            if (know_deviceid.equalsIgnoreCase(device_ids)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过设备文件特征检测运行环境是否为模拟器
     * 当CheckEmulatorFiles()返回值为true时，禁止应用启动
     * @return
     */
    private static Boolean CheckEmulatorFiles() {
        for (int i = 0; i < known_files.length; i++) {
            String file_name = known_files[i];
            File qemu_file = new File(file_name);
            if (qemu_file.exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过设备硬件特征信息检测运行环境是否为模拟器
     * 当CheckEmulatorBuild()返回值为true时，禁止应用启动
     * @param context
     * @return
     */
    private static Boolean CheckEmulatorBuild(Context context){
        String BOARD = android.os.Build.BOARD;
        String BOOTLOADER = android.os.Build.BOOTLOADER;
        String BRAND = android.os.Build.BRAND;
        String DEVICE = android.os.Build.DEVICE;
        String HARDWARE = android.os.Build.HARDWARE;
        String MODEL = android.os.Build.MODEL;
        String PRODUCT = android.os.Build.PRODUCT;
        if (BOARD == "unknown" || BOOTLOADER == "unknown"
                || BRAND == "generic" || DEVICE == "generic"
                || MODEL == "sdk" || PRODUCT == "sdk"
                || HARDWARE == "goldfish")
        {
            return true;
        }
        return false;
    }

}
