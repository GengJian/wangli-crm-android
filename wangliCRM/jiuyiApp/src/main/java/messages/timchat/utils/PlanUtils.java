package messages.timchat.utils;

import com.alibaba.fastjson.JSON;
import com.jiuyi.app.JiuyiMainApplication;

import java.util.ArrayList;
import java.util.List;

import com.control.utils.SharedPreferencesUtil;

/**
 * @author Administrator 已查看信息
 */
public class PlanUtils {

    private static PlanUtils single = null;

    private List<String> searchHistory = new ArrayList<String>();
    ;


    private final String KEY = "planLooked_history";

    // 静态工厂方法
    public static PlanUtils getInstance() {
        if (single == null) {
            single = new PlanUtils();
        }
        return single;
    }

    /**
     * 搜索历史
     *
     * @return
     */
    public List<String> getSearchList() {
        String history = SharedPreferencesUtil.get(JiuyiMainApplication.getIns(), KEY);

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
            SharedPreferencesUtil.add(JiuyiMainApplication.getIns(), KEY, history);
        }
    }

    /**
     * 清空
     */
    public void clear() {
        searchHistory.clear();
        SharedPreferencesUtil.delete(JiuyiMainApplication.getIns(), KEY);
    }
}
