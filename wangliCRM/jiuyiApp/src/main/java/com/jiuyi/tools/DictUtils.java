package com.jiuyi.tools;

import com.jiuyi.model.DictBean;

import java.util.List;

import commonlyused.db.DbHelper;
import commonlyused.db.DictBeanDao;

public class DictUtils {
    public static List<DictBean> getDictList(String typename) {
        List<DictBean> dictBeansList = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                .where(DictBeanDao.Properties.Name.eq(typename)).build().list();
        return dictBeansList;
    }
    public static List<DictBean> getDictKeyList(String typename,String key) {
        List<DictBean> dictBeansList = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                .where(DictBeanDao.Properties.Name.eq(typename))
                .where(DictBeanDao.Properties.Key.eq(key)).build().list();
        return dictBeansList;
    }
    public static List<DictBean> getDictList(String typename,String remark) {
        List<DictBean> dictBeansList = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                .where(DictBeanDao.Properties.Name.eq(typename))
                .where(DictBeanDao.Properties.Remark.eq(remark)).build().list();
        return dictBeansList;
    }
    public static List<DictBean> getDictLikeList(String typename,String remark) {
        List<DictBean> dictBeansList = DbHelper.getInstance().dictBeanLongDBManager().queryBuilder()
                .where(DictBeanDao.Properties.Name.eq(typename))
                .where(DictBeanDao.Properties.Remark.like(remark)).build().list();
        return dictBeansList;
    }
}
