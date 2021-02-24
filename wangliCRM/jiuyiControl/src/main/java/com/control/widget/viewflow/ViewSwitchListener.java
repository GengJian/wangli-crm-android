package com.control.widget.viewflow;

import android.view.View;

/**
 * ****************************************************************
 * 文件名称:ViewSwitchListener.java
 * 作    者:Created by zhengss
 * 文件描述:
 * 注意事项:
 * (C)
 * ****************************************************************
 */

public interface ViewSwitchListener {
    /**
     * This method is called when a new View has been scrolled to.
     *
     * @param view
     *            the {@link View} currently in focus.
     * @param position
     *            The position in the adapter of the {@link View} currently in focus.
     */
    void onSwitched(View view, int position);
}
