package com.jiuyi.interceptor;

import com.control.utils.Pub;
import com.control.utils.Rc;
import com.jiuyi.app.JiuyiMainApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * token 刷新拦截器
 *
 * @author zhengss
 *
 */

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        //判断是response code 是否是401，如果不是401,则直接返回response
        if (response.code() != JiuyiMainApplication.getIns().ERROR_UNAUTHORIZED) {
            return response;
        }else{
            Rc.getIns().getBaseCallTopCallBack().changePage(null, Pub.ResetLogin,false);
            return response;
        }
    }

    /**
     *
     * 登录
     *
     */
    public void login(){
        //实现方法
    }


    /**
     * 创建刷新token刷新失败 Response
     *
     * @param response
     * @return
     */
    public Response createRefreshTokenFailedResponse(Response response){
        return response.newBuilder()
                .code(JiuyiMainApplication.getIns().ERROR_REFRESH_TOKEN_FAILED)
                .build();
    }


    /**
     * 刷新token
     *
     * @return
     */
    private String refreshToken() throws IOException {

        //根据自己接口实现刷新token,请求必须是同步的，返回请求回来的token,如果出异常则返回null。

        return null;
    }
}
