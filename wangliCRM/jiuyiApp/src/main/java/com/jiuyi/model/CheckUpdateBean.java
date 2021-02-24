package com.jiuyi.model;

/**
 * ****************************************************************
 * 文件名称:CheckUpdateBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/8/15 11:14
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/8/15 1.00 初始版本
 * ****************************************************************
 */
public class CheckUpdateBean {

    /**
     * fromClientType : null
     * version : null
     * message : 该版本不需要升级
     * remark : 0
     * address : 192.168.103.01
     */

    private String fromClientType;
    private String version;
    private String message;
    private String remark;
    private String address;

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    private String desp;

    public String getFromClientType() {
        return fromClientType;
    }

    public void setFromClientType(String fromClientType) {
        this.fromClientType = fromClientType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
