package com.control.utils;

import android.view.Choreographer;

/**
 * ****************************************************************
 * 文件名称:JiuyiFrameCallback.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:使用Choreographer帧率检测
 * 注意事项:1、我们用反射的方法把SKIPPED_FRAME_WARNING_LIMIT的值设置成1，这样可以保证只要有丢帧，就会有上面的log输出来
 *          2、在App内部观测当前App的流畅度了。并且在丢帧的地方打印，就可以知道丢帧的大概原因，大概位置，定位代码问题
 *          3、Choreographer就是一个消息处理器，根据vsync 信号 来计算frame，而计算frame的方式就是处理三种回调，
 *              包括事件回调、动画回调、绘制回调。这三种事件在消息输入、加入动画、准备绘图layout 等动作时均会发给Choreographer。
 *              一句话，我们只要捕获这个log提取出skippedFrames 就可以知道界面是否卡顿。
 *          4、在需要检测的Activity中调用 JiuyiFrameCallback.getInstance().start()即可。
 *              一般优化一下，可以在BaseActivity去调用或者Activitylifecyclecallbacks中去调用
 * 参考文献:Android性能优化，正确评测流畅度：http://www.jianshu.com/p/d126640eccb1
 * ****************************************************************
 */

public class JiuyiFrameCallback implements Choreographer.FrameCallback {

    public static JiuyiFrameCallback ins;

    private String TAG = "JiuyiFrameCallback";

    public static final float deviceRefreshRateMs = 16.6f;

    public static long lastFrameTimeNanos = 0;//纳秒为单位

    public static long currentFrameTimeNanos = 0;

    public void start() {
        Choreographer.getInstance().postFrameCallback(JiuyiFrameCallback.getIns());
    }

    public static JiuyiFrameCallback getIns() {
        if (ins == null) {
            ins = new JiuyiFrameCallback();
        }
        return ins;
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (lastFrameTimeNanos == 0) {
            lastFrameTimeNanos = frameTimeNanos;
            Choreographer.getInstance().postFrameCallback(this);
            return;
        }
        currentFrameTimeNanos = frameTimeNanos;
        float value = (currentFrameTimeNanos - lastFrameTimeNanos) / 1000000.0f;

        final int skipFrameCount = skipFrameCount(lastFrameTimeNanos, currentFrameTimeNanos, deviceRefreshRateMs);
        if(skipFrameCount > 0)
            JiuyiLog.e(TAG, "两次绘制时间间隔value=" + value + "  frameTimeNanos=" + frameTimeNanos + "  currentFrameTimeNanos=" + currentFrameTimeNanos + "  skipFrameCount=" + skipFrameCount + "");
        lastFrameTimeNanos = currentFrameTimeNanos;
        Choreographer.getInstance().postFrameCallback(this);
    }


    /**
     * 计算跳过多少帧
     *
     * @param start
     * @param end
     * @param devicefreshRate
     * @return
     */
    private int skipFrameCount(long start, long end, float devicefreshRate) {
        int count = 0;
        long diffNs = end - start;
        long diffMs = Math.round(diffNs / 1000000.0f);
        long dev = Math.round(devicefreshRate);
        if (diffMs > dev) {
            long skipCount = diffMs / dev;
            count = (int) skipCount;
        }
        return count;
    }
}
