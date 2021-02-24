package com.http.subscriber;

import com.http.exception.ApiException;
import com.http.mode.ApiCode;

import io.reactivex.observers.DisposableObserver;

/**
 * 文件描述: API统一订阅者
 */
abstract class ApiSubscriber<T> extends DisposableObserver<T> {

    ApiSubscriber() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e, ApiCode.Request.UNKNOWN));
        }
    }

    protected abstract void onError(ApiException e);
}
