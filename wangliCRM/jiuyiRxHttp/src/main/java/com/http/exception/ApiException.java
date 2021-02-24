package com.http.exception;

import android.net.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.http.JiuyiHttp;
import com.http.mode.ApiCode;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;


import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 文件描述: API异常统一管理
 */
public class ApiException extends Exception {

    private final int code;
    private String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ApiException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDisplayMessage() {
        return message + "(code:" + code + ")";
    }

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ApiCode.Request.HTTP_ERROR);
            switch (httpException.code()) {
                case ApiCode.Http.jiuyiServer:
                    ResponseBody body = ((HttpException) e).response().errorBody();
                    try {
                        JiuyiServerException exceptionBean = new Gson().fromJson(body.string(),JiuyiServerException.class);
                        if(exceptionBean!=null){
                            ex.message=exceptionBean.getMessage();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case ApiCode.Http.UNAUTHORIZED:
                    ex.message = "未认证用户";
                    break;
                case ApiCode.Http.FORBIDDEN:
                    ex.message = "拒绝访问";
                    break;
                case ApiCode.Http.NOT_FOUND:
//                    ex.message = "未找到该网页";
                    ex.message = "服务器在升级，请稍后再试！";
                    break;
                case ApiCode.Http.REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case ApiCode.Http.GATEWAY_TIMEOUT:
                    ex.message = "网关超时";
                    break;
                case ApiCode.Http.INTERNAL_SERVER_ERROR:
                    ex.message = "内部服务错误";
                    break;
                case ApiCode.Http.BAD_GATEWAY:
                    ex.message = "无效网关";
                    break;
                case ApiCode.Http.SERVICE_UNAVAILABLE:
                    ex.message = "服务不可用";
                    break;
                default:
                    ex.message = "网络错误";
                    break;
            }
            return ex;
        }
        else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            ex = new ApiException(e, ApiCode.Request.PARSE_ERROR);
            ex.message = "数据解析失败";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, ApiCode.Request.NETWORK_ERROR);
            ex.message = "网络错误";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ApiException(e, ApiCode.Request.SSL_ERROR);
            ex.message = "SSL_ERROR";
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            ex = new ApiException(e, ApiCode.Request.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else {
            ex = new ApiException(e, ApiCode.Request.UNKNOWN);
            ex.message = "网络不给力，请刷新重试！";
            JiuyiHttp.cancelAll();
            JiuyiHttp.clearCache();
            JiuyiHttp.reset(JiuyiHttp.getContext());
            return ex;
        }
    }

}
