package com.event;

/**
 * 文件描述: 事件总线接口
 */
public interface IBus {
    void register(Object object);

    void unregister(Object object);

    void post(IEvent event);

    void postSticky(IEvent event);
}
