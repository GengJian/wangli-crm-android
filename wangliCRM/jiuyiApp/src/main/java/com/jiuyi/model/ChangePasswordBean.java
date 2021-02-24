package com.jiuyi.model;

/**
 * ****************************************************************
 * 文件名称:ChangePasswordBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/24 12:11
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/24 1.00 初始版本
 * ****************************************************************
 */
public class ChangePasswordBean {

    /**
     * oldPwd : 1234567
     * newPwd : 123456
     */

    private String oldPwd;
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
