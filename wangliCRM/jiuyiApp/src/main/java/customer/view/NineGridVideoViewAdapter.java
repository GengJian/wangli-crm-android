package customer.view;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jaeger.ninegridimageview.GridImageView;

import java.util.List;
/**
 * ****************************************************************
 * 文件名称:NineGridVideoViewAdapter.java
 * 作    者:Created by zhengss
 * 创建时间:2019/2/27 15:32
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/2/27 1.00 初始版本
 * ****************************************************************
 */
public abstract class NineGridVideoViewAdapter<T> {
    protected abstract void onDisplayImage(Context context, ImageView imageView, T t);

    protected void onItemImageClick(Context context, ImageView imageView, int index, List<T> list) {
    }

    protected ImageView generateImageView(Context context) {
        GridImageView imageView = new GridImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}