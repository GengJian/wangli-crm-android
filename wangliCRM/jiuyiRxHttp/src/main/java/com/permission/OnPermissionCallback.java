package com.permission;

/**
 * 文件描述: 申请权限回调
 */
public interface OnPermissionCallback {
    //允许
    void onRequestAllow(String permissionName);

    //拒绝
    void onRequestRefuse(String permissionName);

    //不在询问
    void onRequestNoAsk(String permissionName);
}
