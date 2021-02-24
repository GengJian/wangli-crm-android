package com.http.subscriber;

import com.http.callback.ACallback;

/**
 * 文件描述: 包含下载进度回调的订阅者
 */
public class DownCallbackSubscriber<T> extends ApiCallbackSubscriber<T> {
    public DownCallbackSubscriber(ACallback<T> callBack) {
        super(callBack);
    }

    @Override
    public void onComplete() {
        super.onComplete();
        callBack.onSuccess(super.data);
    }
}
