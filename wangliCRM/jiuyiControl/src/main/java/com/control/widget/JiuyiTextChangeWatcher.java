package com.control.widget;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * ****************************************************************
 * 文件名称:JiuyiTextChangeWatcher.java
 * 作    者:Created by zhengss
 * 创建时间:2018/11/27 11:50
 * 文件描述:Edittext输入内容改变的观察器
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/11/27 1.00 初始版本
 * ****************************************************************
 */

public abstract class JiuyiTextChangeWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        
    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}
