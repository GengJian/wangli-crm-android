package customer.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.control.utils.DialogID;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.wanglicrm.android.R;
import com.czt.mp3recorder.MP3Recorder;
import com.jiuyi.app.JiuyiActivityBase;
import com.shuyu.waveview.AudioPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import customer.Utils.FileUtils;
import customer.Utils.ViewOperatorType;
import customer.adapter.MAdapter;
import customer.entity.Media;
import customer.entity.MemberUpdateBean;
import customer.listener.PickerConfig;
import customer.view.DrawableTextView;

/**
 * ****************************************************************
 * 文件名称 : JiuyiCommonInputInfoActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 录入界面
 *****************************************************************
 */
public class JiuyiRecordActivity extends JiuyiActivityBase implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    private TextView mtvcomplete;
    private String operatorType,sTitle,s_returnvalue;
    private  long customerid=-1;
    private ImageView   iv_back;
    // 语音文件
    private String fileName = null;
    // 音频文件保存的路径
    private String path = "";
    // 界面控件z
    private Button startRecord;// 开始录音
    private Button startPlay;// 开始播放
    private Button stopRecord;// 完成录音
    private Button stopPlay;// 停止播放
    private TextView time;// 计时显示
    private ListView mListView;// 音频文件列表
    private MAdapter mAdapter;
    private DrawableTextView delete;// 删除按钮
    private Button pausePlay;// 暂停播放

    // 语音操作对象
//    private MediaPlayer mPlayer = null;// 播放器
//    private MediaRecorder mRecorder = null;// 录音器
    MP3Recorder mRecorder;
    MediaPlayer mPlayer;
    private boolean isPause = false;// 当前录音是否处于暂停状态
    private boolean isPausePlay = false;// 当前播放器是否处于暂停状态
    private ArrayList<String> mList = new ArrayList<String>();// 待合成的录音片段
    private ArrayList<String> list = new ArrayList<String>();// 已合成的录音片段
    private String deleteStr = null; // 列表中要删除的文件名
    private Timer timer;
    private String playFileName = null;// 选中的播放文件
    // 相关变量
    private int second = 0;
    private int minute = 0;
    private int hour = 0;
    private View whichSelecte = null;// 记录被选中的Item
    private long limitTime = 0;// 录音文件最短事件1秒
    private int selectPosition=-1;
    Intent argsIntent;
    ArrayList<Media> selects;
    private boolean havePermission = false;//初始化时判断是否有录音权限


    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_record_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        argsIntent = getIntent();
        selects = argsIntent.getParcelableArrayListExtra(PickerConfig.DEFAULT_SELECTED_LIST);
        // 初始化录音列表
        initList();
        havePermission = checkRecordPermission();

        // 初始化控件
        initView();
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }

        setTitle();
        iv_back = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackActivityBundle();
                backPage();
            }
        });

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectPosition<0){
                        Toast.makeText(JiuyiRecordActivity.this, "请选择录音文件", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    setBackActivityBundle();
                    backPage();
                }
            });

        }

    }

    @Override
    public void setBackActivityBundle() {
//        Bundle bundle = new Bundle();
//        bundle.putString(JiuyiBundleKey.PARAM_CUSTOMERCOLVALUE,playFileName);
        if(playFileName!=null){
            Media media = new Media();
            media.setExtension(".mp3");
            media.setMediaType(2);
            media.setPath(playFileName);
            long fileTime=0;
            File file1 = new File(playFileName);
            fileTime= FileUtils.getDuration(file1);
            if(fileTime>=0){
                media.setTime(fileTime);
                media.setFileTime(fileTime+"");
            }
            if(selects==null){
                selects=new ArrayList<>();
            }
            selects.add(media);
        }

        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(PickerConfig.EXTRA_RESULT, selects);



        JiuyiRecordActivity.this.setResult(150, intent);
    }
    @Override
    public void dealDialogAction(int nAction, int nKeyCode, String url, Dialog pDialog) {
        if(nAction==DialogID.DialogTrue){
            backPage();
        }else if(nAction== DialogID.DialogDoNothing)
        {
            if(nKeyCode == KeyEvent.KEYCODE_BACK ){
                return;
            }
        }else if(nAction== DialogID.DialogDeleteTrue){
            if(nKeyCode == KeyEvent.KEYCODE_ENTER){
                deleteRecord();
            }
        }
    }

    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else{
            mTitle = "录音信息";
        }
        setTitle(mTitle);
    }

    // 初始化界面
    private void initView() {
        delete = (DrawableTextView) findViewById(R.id.delete);
        delete.setOnClickListener(this);
        // 对按钮的可点击事件的控制是保证不出现空指针的重点！！
        delete.setEnabled(false);
        pausePlay = (Button) findViewById(R.id.pausePlay);
        pausePlay.setOnClickListener(this);
        pausePlay.setEnabled(false);
        startRecord = (Button) findViewById(R.id.startRecord);
        startRecord.setOnClickListener(this);
        stopRecord = (Button) findViewById(R.id.stopRecord);
        stopRecord.setOnClickListener(this);
        stopRecord.setEnabled(false);
        startPlay = (Button) findViewById(R.id.startPlay);
        startPlay.setOnClickListener(this);
        startPlay.setEnabled(false);
        stopPlay = (Button) findViewById(R.id.stopPlay);
        stopPlay.setOnClickListener(this);
        stopPlay.setEnabled(false);
        time = (TextView) findViewById(R.id.time);
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new MAdapter(this, list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    // 初始化录音列表
    private void initList() {
        path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/jiuyivoice/" + getPackageName() + "/Record";
        // 判断SD卡是否存在
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD卡状态异常，无法获取录音列表！", Toast.LENGTH_LONG).show();
        } else {
            // 根据后缀名进行判断、获取文件夹中的音频文件
            File file = new File(path);
            File files[] = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getName().indexOf(".") >= 0) {
                        // 只取.mp3 .mp3
                        // .mp4 文件
                        String fileStr = files[i].getName().substring(
                                files[i].getName().indexOf("."));
                        if (fileStr.toLowerCase().equals(".mp3")
                                || fileStr.toLowerCase().equals(".mp3")
                                || fileStr.toLowerCase().equals(".mp4"))
                            list.add(files[i].getName());
                    }
                }
            }
        }
    }

    // 设置点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startRecord:// 开始录音
                if(!havePermission) {
                    //若未授权则申请授权
                    if (!checkRecordPermission()){
                        return;
                    }
                }
                // 判断SD卡是否存在
                if (!Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(this, "SD卡状态异常，请检查后重试！", Toast.LENGTH_LONG)
                            .show();
                    break;
                }
                // 开始录音
                startRecord();
                // 录音计时
                recordTime();
                break;
            case R.id.stopRecord:// 完成录音
                if (isPause) {
                    // 完成录音
                    stopRecord();
                } else {
                    // 暂停录音
                    try {
                        pauseRecord();
                    } catch (InterruptedException e) {
                        // 当一个线程处于等待，睡眠，或者占用，也就是说阻塞状态，而这时线程被中断就会抛出这类错误
                        // 上百次测试还未发现这个异常，但是需要捕获
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.startPlay:
                // 播放录音
                playRecord();
                break;
            case R.id.stopPlay:
                // 停止播放
                startPlay.setEnabled(true);
                stopPlay.setEnabled(false);
                startRecord.setEnabled(true);
                pausePlay.setEnabled(false);
                if (mPlayer != null) {
                    // 释放资源
                    // 对MediaPlayer多次使用而不释放资源就会出现MediaPlayer create faild 的异常
                    mPlayer.release();
                    mPlayer = null;
                }
                delete.setEnabled(true);
                break;
            case R.id.delete:
                // 删除录音文件
                if(Func.IsStringEmpty(deleteStr)){
                    Toast.makeText(JiuyiRecordActivity.this, "请选择要删除录音文件", Toast.LENGTH_SHORT).show();
                    return;
                }
                startDialog(DialogID.DialogDeleteTrue, "", "确认删除该条录音？", JiuyiDialogBase.Dialog_Type_YesNo, null);
                break;
            case R.id.pausePlay:
                // 暂停播放
                if (isPausePlay) {
                    pausePlay.setText("暂停播放");
                    pausePlay.setEnabled(true);
                    isPausePlay = false;
                    mPlayer.start();
                } else {
                    if (mPlayer != null) {
                        mPlayer.pause();
                    }
                    pausePlay.setText("继续播放");
                    pausePlay.setEnabled(true);
                    isPausePlay = true;
                }
                break;
            default:
                break;
        }
    }


    // 判断点击事件的时间间隔
    // 点击速度过快，比如在同一秒中点击三次，只会产生一个录音文件，因为命名一样。
    private boolean limitTime() {
        limitTime = System.currentTimeMillis() - limitTime;
        if (limitTime >= 1100) {
            limitTime = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }

    // 删除录音文件
    private void deleteRecord() {
        // 删除所选中的录音文件
        File file = new File(playFileName);
        if (file.exists()) {
            file.delete();
            list.remove(deleteStr);
            mAdapter.notifyDataSetChanged();
            time.setText("");
        } else {
            list.remove(deleteStr);
            mAdapter.notifyDataSetChanged();
        }
        startPlay.setEnabled(false);
        playFileName = null;
        delete.setEnabled(false);
        startRecord.setEnabled(true);
        time.setText("您本次的录音时长为：       00:00:00");
    }

    // 播放录音
    private void playRecord() {
        // 对按钮的可点击事件的控制是保证不出现空指针的重点！！
        startRecord.setEnabled(false);
        delete.setEnabled(false);
        stopPlay.setEnabled(true);
        startPlay.setEnabled(false);
        pausePlay.setEnabled(true);
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
        mPlayer = new MediaPlayer();
        // 播放完毕的监听
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                // 播放完毕改变状态，释放资源
                mPlayer.release();
                mPlayer = null;
                startRecord.setEnabled(true);
                startPlay.setEnabled(true);
                stopPlay.setEnabled(false);
                delete.setEnabled(true);
                pausePlay.setEnabled(false);
            }
        });
        try {
            // 播放所选中的录音
            mPlayer.setDataSource(playFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (Exception e) {
            // 若出现异常被捕获后，同样要释放掉资源
            // 否则程序会不稳定，不适合正式项目上使用
            if (mPlayer != null) {
                mPlayer.release();
                mPlayer = null;
            }
            Toast.makeText(this, "播放失败,可返回重试！", Toast.LENGTH_LONG).show();
            stopPlay.setEnabled(false);
            delete.setEnabled(true);
            pausePlay.setEnabled(false);
        }
    }

    // 完成录音
    private void stopRecord() {
//        mRecorder.release();
        mRecorder = null;
        isPause = false;
        startRecord.setEnabled(true);
        startRecord.setText("开始录音");
        stopRecord.setEnabled(false);
        timer.cancel();
        // 最后合成的音频文件
//        fileName = path + "/" + getTime() + ".mp3";
//        String fileName1 = getTime() + ".mp3";

        fileName = path + "/" + getTime() + ".mp3";
        String fileName1 = getTime() + ".mp3";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream fileInputStream = null;
        try {
            for (int i = 0; i < mList.size(); i++) {
                File file = new File(mList.get(i));
                // 把因为暂停所录出的多段录音进行读取
                fileInputStream = new FileInputStream(file);
                byte[] mByte = new byte[fileInputStream.available()];
                int length = mByte.length;
                // 第一个录音文件的前六位是不需要删除的
                if (i == 0) {
                    while (fileInputStream.read(mByte) != -1) {
                        fileOutputStream.write(mByte, 0, length);
                    }
                }
                // 之后的文件，去掉前六位
                else {
                    while (fileInputStream.read(mByte) != -1) {

                        fileOutputStream.write(mByte, 6, length - 6);
                    }
                }
            }
            list.add(fileName1);
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            // 这里捕获流的IO异常，万一系统错误需要提示用户
            e.printStackTrace();
            Toast.makeText(this, "录音合成出错，请重试！", Toast.LENGTH_LONG).show();
        } finally {
            try {
                fileOutputStream.flush();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 录音结束 、时间归零
            minute = 0;
            hour = 0;
            second = 0;
        }
        // 不管合成是否成功、删除录音片段
        for (int i = 0; i < mList.size(); i++) {
            File file = new File(mList.get(i));
            if (file.exists()) {
                file.delete();
            }
        }

    }

    // 暂停录音
    private void pauseRecord() throws InterruptedException {
        if (System.currentTimeMillis()-limitTime<1100) {
            //录音文件不得低于一秒钟
            Toast.makeText(this, "录音时间长度不得低于1秒钟！", Toast.LENGTH_SHORT).show();
            return ;
        }
        stopRecord.setEnabled(true);
        mRecorder.stop();
//        mRecorder.release();
        timer.cancel();
        isPause = true;
        // 将录音片段加入列表
        mList.add(fileName);
        startRecord.setEnabled(true);
        startRecord.setText("继续录音");
        stopRecord.setText("完成录音");
    }

    // 开始录音
    private void startRecord() {
        stopRecord.setText("暂停录音");
        startRecord.setText("录音中...");
        startRecord.setEnabled(false);
        startPlay.setEnabled(false);
        stopRecord.setEnabled(true);
        delete.setEnabled(false);
        if (!isPause) {
            // 新录音清空列表
            mList.clear();
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        fileName = path + "/" + getTime() + ".mp3";
        isPause = false;
        mRecorder =new MP3Recorder(new File(fileName));
//        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        // 选择mp3格式
//        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//        mRecorder.setOutputFile(fileName);
//        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//        try {
//            mRecorder.start();
//        } catch (Exception e) {
//            // 若录音器启动失败就需要重启应用，屏蔽掉按钮的点击事件。 否则会出现各种异常。
//            Toast.makeText(this, "录音器启动失败，请返回重试！", Toast.LENGTH_LONG).show();
//            startPlay.setEnabled(false);
//            stopPlay.setEnabled(false);
//            delete.setEnabled(false);
//            startRecord.setEnabled(false);
//            stopRecord.setEnabled(false);
////            mRecorder.release();
//            mRecorder = null;
//            this.finish();
//        }
        if (mRecorder != null) {
            try {
                mRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "录音出现异常", Toast.LENGTH_SHORT).show();
                return;
            }

            limitTime = System.currentTimeMillis();
        }

    }

    // 计时器异步更新界面
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            time.setText("您本次的录音时长为：       "
                    + String.format("%1$02d:%2$02d:%3$02d", hour, minute,
                    second));
            super.handleMessage(msg);
        }
    };

    // 录音计时
    private void recordTime() {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                second++;
                if (second >= 60) {
                    second = 0;
                    minute++;
                    if (minute >= 60) {
                        minute = 0;
                        hour++;
                    }
                }
                handler.sendEmptyMessage(1);
            }

        };
        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);
    }

    // 获得当前时间
    private String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH：mm：ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = formatter.format(curDate);
        return time;
    }

    // 录音列表被点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        selectPosition=position;
        // 屏蔽点击事件的一种方式
        if (mRecorder == null) {
            startPlay.setEnabled(true);
            if (mPlayer == null || !mPlayer.isPlaying()) {
                delete.setEnabled(true);
            } else {
                delete.setEnabled(false);
            }
        }
        startPlay.setText("播放录音");
        // 列表文件的选中效果
        if (whichSelecte != null) {
            whichSelecte
                    .setBackgroundColor(getResources().getColor(R.color.jiuyi_backgroud_color));
        }
        view.setBackgroundColor(getResources().getColor(R.color.jiuyi_blue));
        // 要播放文件的路径
        playFileName = path + "/" + list.get(position);
        // 要删除文件的名称
        deleteStr = list.get(position);
        whichSelecte = view;
        time.setText(list.get(position));
    }

    // Activity被销毁的时候 释放资源
    @Override
    public void onDestroy() {
        // 删除片段
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                File file = new File(mList.get(i));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        if (null != mRecorder) {
            mRecorder.stop();
//            mRecorder.release();
            mRecorder = null;
        }
        if (null != mPlayer) {
            mPlayer.stop();
//            mPlayer.release();
            mPlayer = null;
        }
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }

    // 来电暂停
    @Override
    public void onPause() {
        if (mRecorder != null) {
            // 暂停录音
            try {
                pauseRecord();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (mPlayer != null) {
            // 暂停播放
            mPlayer.pause();
            isPausePlay = true;
            pausePlay.setText("继续播放");
            pausePlay.setEnabled(true);
        }
        super.onPause();
    }
    private boolean checkRecordPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(JiuyiRecordActivity.this, Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(JiuyiRecordActivity.this, permissions.toArray(new String[0]), 0);
                return false;
            }
        }
        return true;
    }
}
