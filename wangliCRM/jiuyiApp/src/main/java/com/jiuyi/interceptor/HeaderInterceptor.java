package com.jiuyi.interceptor;


import com.control.utils.Rc;
import com.jiuyi.app.JiuyiMainApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 添加公共请求header
 *
 * @author zhengss
 * @time 2018-05-17 15:48
 */
public final class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        Request request = original.newBuilder()
                //通过Interceptor来定义静态请求头
                //增加 Authorization
                .addHeader(JiuyiMainApplication.getIns().AUTHORIZATION_KEY, Rc.id_tokenparam/*PreferenceUtils.getAuthorization()*/)
                .build();

        return chain.proceed(request);
    }
}
