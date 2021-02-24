package customer.shared;

import android.content.Context;

import com.control.shared.JiuyiSharedBase;
import com.control.utils.Func;
import com.control.utils.JiuyiJSONObject;
import com.control.utils.JiuyiLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import customer.entity.MemberchooseBean;

public class MemberchooseConditionShared extends JiuyiSharedBase {
    private final String SORTDATA_LIST = "sortdata_list";

    public List<MemberchooseBean.ContentBean> getmMemberchooseBeanList() {
        return mMemberchooseBeanList;
    }

    public void setmMemberchooseBeanList(List<MemberchooseBean.ContentBean> mMemberchooseBeanList) {
        this.mMemberchooseBeanList = mMemberchooseBeanList;
    }

    private final String QUICKSORTDATA_LIST = "quicksortdata_list";
    private List<MemberchooseBean.ContentBean> mMemberchooseBeanList ;
    private List<MemberchooseBean.ContentBean.MemberChooseBeansBean> sortlist;

    public List<MemberchooseBean.ContentBean.MemberChooseBeansBean> getSortlist() {
        return sortlist;
    }

    public void setSortlist(List<MemberchooseBean.ContentBean.MemberChooseBeansBean> sortlist) {
        this.sortlist = sortlist;
    }

    public List<MemberchooseBean.ContentBean.MemberChooseBeansBean> getQuicksortlist() {
        return quicksortlist;
    }

    public void setQuicksortlist(List<MemberchooseBean.ContentBean.MemberChooseBeansBean> quicksortlist) {
        this.quicksortlist = quicksortlist;
    }

    private List<MemberchooseBean.ContentBean.MemberChooseBeansBean> quicksortlist;
    private String[] sort;

    public String[] getSort() {
        return sort;
    }

    public void setSort(String[] sort) {
        this.sort = sort;
    }

    public String[] getQuicksort() {
        return quicksort;
    }

    public void setQuicksort(String[] quicksort) {
        this.quicksort = quicksort;
    }

    private String[] quicksort;

    public MemberchooseBean getmMemberchooseBean() {
        return mMemberchooseBean;
    }

    public void setmMemberchooseBean(MemberchooseBean mMemberchooseBean) {
        this.mMemberchooseBean = mMemberchooseBean;
    }

    private MemberchooseBean mMemberchooseBean;
    private static MemberchooseConditionShared ins;
    public static MemberchooseConditionShared getIns(){
        if(ins == null)
            ins = new MemberchooseConditionShared();
        return ins;
    }
    /**
     * 构造函数，并获取保存的数据
     */
    public MemberchooseConditionShared(){

    }
    /**
     * 保存信息
     * @param context
     */
    public void onSaveData(Context context)
    {
        if(context == null)
            return;

        super.onSaveData(context, jiuyiSharedStruct.MemberchooseCodition.name(), getJsonString());
    }
    @Override
    public void onSaveData(Context context, String sharednane, String data) {
        super.onSaveData(context, sharednane, data);
    }

    /**
     * 把参数转换为JSON格式保存
     * @return JSON格式数据
     */
    public String getJsonString(){
        try {
            JSONObject json = new JiuyiJSONObject();
            json.put("lasttradeaccount", "11");
            return json.toString();
        } catch (JSONException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            return "";
        }
    }
    /**
     * 获取信息
     * @param context
     */
    public void onGetData(Context context)
    {
        if(context == null)
            return;

        String data = super.onGetData(context, jiuyiSharedStruct.MemberchooseCodition.name());
        if(!Func.IsStringEmpty(data)){
            try {
                JSONObject json = new JiuyiJSONObject(data);
                String sAccountList=json.optString("accountlist");
            } catch (JSONException e) {
                JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
            }
        }
    }
    public static String converjsonStringFormList(List<MemberchooseBean.ContentBean> pLsit){
        if (pLsit == null || pLsit.size()<1)
            return "";
        Type listType = new TypeToken<ArrayList<MemberchooseBean.ContentBean>>() {}.getType();
        return new Gson().toJson(pLsit,listType);
    }

}
