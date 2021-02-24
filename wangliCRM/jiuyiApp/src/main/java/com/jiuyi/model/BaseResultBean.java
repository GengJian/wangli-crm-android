package com.jiuyi.model;

/**
 * ****************************************************************
 * 文件名称:BaseResultBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/7/24 17:07
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/7/24 1.00 初始版本
 * ****************************************************************
 */
public class BaseResultBean {

    public String getErrMsgDesp() {
        return errMsgDesp;
    }

    public void setErrMsgDesp(String errMsgDesp) {
        this.errMsgDesp = errMsgDesp;
    }

    public String getErrReason() {
        return errReason;
    }

    public void setErrReason(String errReason) {
        this.errReason = errReason;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }

    /**
     * errMsgDesp : null
     * errReason : null
     * success : true
     * result : 1
     * failure : false
     */

    private String errMsgDesp;
    private String errReason;
    private boolean success;
    private int result;
    private boolean failure;


}
