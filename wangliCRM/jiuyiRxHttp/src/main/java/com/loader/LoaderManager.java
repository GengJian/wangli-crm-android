package com.loader;

/**
 * 文件描述: 图片加载管理，可定制图片加载框架
 */
public class LoaderManager {
    private static ILoader innerLoader;
    private static ILoader externalLoader;

    public static void setLoader(ILoader loader) {
        if (externalLoader == null && loader != null) {
            externalLoader = loader;
        }
    }

    public static ILoader getLoader() {
        if (innerLoader == null) {
            synchronized (LoaderManager.class) {
                if (innerLoader == null) {
                    if (externalLoader != null) {
                        innerLoader = externalLoader;
                    }
                }
            }
        }
        return innerLoader;
    }
}