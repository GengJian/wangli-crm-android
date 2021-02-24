package customer.activity;


import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Res;

import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;
import com.piterwilson.audio.MP3RadioStreamDelegate;
import com.piterwilson.audio.MP3RadioStreamPlayer;
import com.shuyu.waveview.AudioWaveView;


import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;


import customer.Utils.FileUtils;
import customer.Utils.ScreenUtils;
import customer.entity.Media;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCommonInputInfoActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 录入界面
 *****************************************************************
 */
public class JiuyiRecordPlayActivity extends JiuyiActivityBase implements MP3RadioStreamDelegate {
    private TextView mtvcomplete,music_time;
    private String sTitle;
    private ImageView   iv_back;
    AudioWaveView audioWave;
    Button playBtn;
    SeekBar seekBar;
    MP3RadioStreamPlayer player;
    Timer timer;
    boolean playeEnd;
    boolean seekBarTouch;
    private Media media;




    @Override
    public void onInit() {
        mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_activity_recordplay_layout"), null);
        mBodyLayout.findTitleToolBars(this, this);//必不可少
        setContentView(mBodyLayout);
        if(Func.IsStringEmpty(sTitle)){
            sTitle="";
        }
        media = mBundle.getParcelable(JiuyiBundleKey.PARAM_COMMONBEAN);
        setTitle();
        audioWave = (AudioWaveView) mBodyLayout.findViewById(Res.getViewID(null, "audioWave"));
        playBtn = (Button) mBodyLayout.findViewById(Res.getViewID(null, "playBtn"));
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playeEnd) {
                    stop();
                    playBtn.setText("暂停");
                    seekBar.setEnabled(true);
                    play();
                    return;
                }

                if (player.isPause()) {
                    playBtn.setText("暂停");
                    player.setPause(false);
                    seekBar.setEnabled(false);
                } else {
                    playBtn.setText("播放");
                    player.setPause(true);
                    seekBar.setEnabled(true);
                }
            }
        });
        seekBar = (SeekBar) mBodyLayout.findViewById(Res.getViewID(null, "seekBar"));
        iv_back = (ImageView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_navbarbackbg"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPage();
            }
        });

        mtvcomplete = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "jiuyi_titlebar_complete"));
        if (mtvcomplete != null) {
            mtvcomplete.setVisibility(View.INVISIBLE);
        }
        music_time = (TextView) mBodyLayout.findViewById(Res.getViewID(null, "music_time"));
        if(!Func.IsStringEmpty(media.getFileTime())){
            music_time.setText(FileUtils.durationFormat(Long.parseLong(media.getFileTime())*1000));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                play();
            }
        }, 1000);
        playBtn.setEnabled(false);
        seekBar.setEnabled(false);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarTouch = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarTouch = false;
                if (!playeEnd) {
                    player.seekTo(seekBar.getProgress());
                }
            }
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (playeEnd || player == null || !seekBar.isEnabled()) {
                    return;
                }
                long position = player.getCurPosition();
                if (position > 0 && !seekBarTouch) {
                    seekBar.setProgress((int) position);
                }
            }
        }, 1000, 1000);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        audioWave.stopView();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        stop();
    }
    private void play() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        player = new MP3RadioStreamPlayer();
        if(media!=null){
            if(!Func.IsStringEmpty(media.getUrl())){
                player.setUrlString(this, true, media.getUrl());
            }else if(!Func.IsStringEmpty(media.getPath())){
                player.setUrlString(media.getPath());
            }
        }
//        player.setUrlString(this, true, "http://nos.netease.com/test-open-audio/nos/mp3/2017/12/04/AT08IH87FHUG1LNA_sd.mp3");
//        player.setUrlString(this, true, "http://crm-test-api.aikosolar.net/api/file/b10e41dbfd314eeb8a2517e82f02df3a");
//        player.setUrlString(getIntent().getStringExtra("uri"));
        player.setDelegate(this);
        int size = ScreenUtils.getScreenWidth(JiuyiRecordPlayActivity.this) /Res.Dip2Pix(1);//控件默认的间隔是1
        player.setDataList(audioWave.getRecList(), size);
        //player.setWaveSpeed(1100);
        //mRecorder.setDataList(audioWave.getRecList(), size);
        //player.setStartWaveTime(5000);
        //audioWave.setDrawBase(false);
        audioWave.setBaseRecorder(player);
        audioWave.startView();
        try {
            player.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void stop() {
        player.stop();
    }
    public void setTitle(){
        if(!Func.IsStringEmpty(sTitle)){
            mTitle=sTitle;
        }else{
            mTitle = "播放录音";
        }
        setTitle(mTitle);
    }


    @Override
    public void onRadioPlayerPlaybackStarted(final MP3RadioStreamPlayer player) {
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                playeEnd = false;
                playBtn.setEnabled(true);
                seekBar.setMax((int) player.getDuration());
                seekBar.setEnabled(true);
            }
        });
    }

    @Override
    public void onRadioPlayerStopped(MP3RadioStreamPlayer player) {
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                playeEnd = true;
                playBtn.setText("播放");
                playBtn.setEnabled(true);
                seekBar.setEnabled(false);
            }
        });

    }

    @Override
    public void onRadioPlayerError(MP3RadioStreamPlayer player) {
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                playeEnd = false;
                playBtn.setEnabled(true);
                seekBar.setEnabled(false);
            }
        });

    }

    @Override
    public void onRadioPlayerBuffering(MP3RadioStreamPlayer player) {
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                playBtn.setEnabled(false);
                seekBar.setEnabled(false);
            }
        });

    }

}
