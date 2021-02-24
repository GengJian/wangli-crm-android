package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ****************************************************************
 * 文件名称:VoiceSelectBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/8/26 11:12
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/8/26 1.00 初始版本
 * ****************************************************************
 */
public class VoiceSelectBean  {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private String name;
    private boolean isCheck=false;

}
