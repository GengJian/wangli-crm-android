package com.http.request;

/**
 * 文件描述: 传入自定义Retrofit接口的请求类型
 */
public class RetrofitRequest extends BaseRequest<RetrofitRequest> {

    public RetrofitRequest() {

    }

    public <T> T create(Class<T> cls) {
        generateGlobalConfig();
        generateLocalConfig();
        return retrofit.create(cls);
    }

}
