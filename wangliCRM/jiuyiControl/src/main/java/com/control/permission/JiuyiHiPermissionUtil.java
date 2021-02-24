package com.control.permission;

import android.content.Context;
import com.control.utils.Res;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;

/**
 * ****************************************************************
 * 文件名称:JiuyiHiPermissionUtil.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:HiPermission的二次封装
 * ****************************************************************
 */

public class JiuyiHiPermissionUtil {
    private final String TITLE = "开启权限";
    private final String MESSAGE = "为了您能正常使用本应用，需要以下权限";
    private final int STYLEID = Res.getStyleID(null, "jiuyiPermissionStyle");
    private Context mContext;


    public JiuyiHiPermissionUtil(Context context){
        this.mContext = context;
    }

    /**
     * 检查单个默认风格的权限
     * @param permission 权限
     * @param callback 回调
     */
    public void checkPermission(String permission, onGuaranteeCallBack callback){
        checkPermission(TITLE, MESSAGE, STYLEID, permission, callback);
    }
    /**
     * 检查多个权限
     * @param permissions 权限
     * @param callback 回调
     */
    public void checkPermission(String permissions[], onGuaranteeCallBack callback){
        checkPermission(permissions, 0,  callback);
    }

    /**
     * 检查多个权限
     * @param permissions 权限
     * @param index 当前权限的索引
     * @param callback 回调
     */
    private void checkPermission(final String permissions[], final int index,  final onGuaranteeCallBack callback){
        if(permissions == null || index < 0 || index >= permissions.length)
            return;

        if(index == permissions.length - 1){
            checkPermission(TITLE, MESSAGE, STYLEID, permissions[index], callback);
        }else{
            checkPermission(TITLE, MESSAGE, STYLEID, permissions[index], new onGuaranteeCallBack(){
                @Override
                public void onGuarantee(String permission, int position) {
                    checkPermission(permissions, index+1,  callback);
                }
            });
        }
    }

    /**
     * 检查单个权限，可设置标题，内容，风格和回调
     * @param title 标题
     * @param message 内容
     * @param styleId 风格
     * @param permission 权限
     * @param callback 回调
     */
    public void checkPermission(String title, String message, int styleId, String permission, final onGuaranteeCallBack callback){
        //有可能activity已经被销毁了 导致mContext为null，出现空指针异常；
        if(mContext == null){
            return;
        }
        HiPermission.create(mContext)
            .title(title)
            .msg(message)
//            .style(styleId)
            .checkSinglePermission(permission, new PermissionCallback() {
                @Override
                public void onClose() {
                }

                @Override
                public void onFinish() {
                }

                @Override
                public void onDeny(String permission, int position) {

                }

                @Override
                public void onGuarantee(String permission, int position) {
                    if(callback != null){
                        callback.onGuarantee(permission, position);
                    }
                }
            });
    }

    /**
     * 权限调用回调
     */
    public interface onGuaranteeCallBack{
        public void onGuarantee(String permission, int position);
    }
}
