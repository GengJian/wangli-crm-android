package com.http.request;

import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.http.core.ApiManager;
import com.http.mode.CacheResult;
import com.http.mode.MediaTypes;
import com.http.subscriber.ApiCallbackSubscriber;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 文件描述: Delete请求
 */
public class DeleteRequest extends BaseHttpRequest<DeleteRequest> {
    protected RequestBody requestBody;
    protected MediaType mediaType;
    protected String content;
    public DeleteRequest(String suffixUrl) {
        super(suffixUrl);
    }
    public DeleteRequest setJson(String json) {
        this.content = json;
        this.mediaType = MediaTypes.APPLICATION_JSON_TYPE;
        return this;
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
//        if (content != null && mediaType != null) {
//            requestBody = RequestBody.create(mediaType, content);
//            return apiService.delete(suffixUrl, requestBody).compose(this.<T>norTransformer(type));
//        }
        return apiService.delete(suffixUrl, params).compose(this.<T>norTransformer(type));
    }

    @Override
    protected <T> Observable<CacheResult<T>> cacheExecute(Type type) {
        return this.<T>execute(type).compose(JiuyiHttp.getApiCache().<T>transformer(cacheMode, type));
    }

    @Override
    protected <T> void execute(ACallback<T> callback) {
        DisposableObserver disposableObserver = new ApiCallbackSubscriber(callback);
        if (super.tag != null) {
            ApiManager.get().add(super.tag, disposableObserver);
        }
        if (isLocalCache) {
            this.cacheExecute(getSubType(callback)).subscribe(disposableObserver);
        } else {
            this.execute(getType(callback)).subscribe(disposableObserver);
        }
    }
}
