package com.http.exception;

/**
 * 服务器端异常信息
 */
public class JiuyiServerException extends RuntimeException {
    private String exception;
    private String code;
    private String message;

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
