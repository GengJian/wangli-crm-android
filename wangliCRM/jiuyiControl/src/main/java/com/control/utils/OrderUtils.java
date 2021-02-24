package com.control.utils;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Administrator 已查看信息
 */
public class OrderUtils {

    private static OrderUtils single = null;

    private List<String> searchHistory = new ArrayList<String>();
    ;


    private final String KEY = "orderLooked_history";

    // 静态工厂方法
    public static OrderUtils getInstance() {
        if (single == null) {
            single = new OrderUtils();
        }
        return single;
    }

    /**
     * 搜索历史
     *
     * @return
     */
    public List<String> getSearchList() {
        String history = SharedPreferencesUtil.get(Rc.getApplication(), KEY);

        if (null != history && !"".equals(history)) {
            searchHistory = JSON.parseArray(history, String.class);
        }
        return searchHistory;

    }

    /**
     * 添加搜索结果  考虑到效率不会存到到文件
     */
    public List<String> saveSearch(String str) {
        if(searchHistory.contains(str)){
            searchHistory.remove(str);
        }
        searchHistory.add(0, str);
        return searchHistory;
    }

    /**
     * 删除一个
     */
    public List<String> deleteOneSearch(String str) {
        searchHistory.remove(str);
        return searchHistory;
    }

    /**
     * 添加搜索结果  存到到文件 ondestory 调用
     */
    public void saveFile(List<String> list) {

        if (list != null && list.size() > 0) {
            String history = JSON.toJSONString(list);
            SharedPreferencesUtil.add(Rc.getApplication(), KEY, history);
        }
    }
    /**
     * 添加搜索结果  存到到文件 ondestory 调用
     */
    public void saveFile(String str) {
        getSearchList();
        if(searchHistory!=null && searchHistory.contains(str)){
            searchHistory.remove(str);
        }
        searchHistory.add(0, str);
        if (searchHistory != null && searchHistory.size() > 0) {
            String history = JSON.toJSONString(searchHistory);
            SharedPreferencesUtil.add(Rc.getApplication(), KEY, history);
        }
    }

    /**
     * 清空
     */
    public void clear() {
        searchHistory.clear();
        SharedPreferencesUtil.delete(Rc.getApplication(), KEY);
    }
}