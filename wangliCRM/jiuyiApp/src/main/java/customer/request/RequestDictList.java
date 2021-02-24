package customer.request;

import com.common.GsonUtil;
import com.control.utils.Rc;
import com.http.JiuyiHttp;
import com.http.callback.ACallback;
import com.jiuyi.model.DictBean;
import com.jiuyi.model.DictResultBean;
import java.util.ArrayList;
import java.util.List;
import commonlyused.bean.QueryConditionBean;
import commonlyused.db.DbHelper;


public class RequestDictList {
    public static RequestDictList Instance;

    public static RequestDictList getIns() {
        if (Instance == null)
            Instance = new RequestDictList();
        return Instance;
    }
    public  void  getDictList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                QueryConditionBean queryConditionBean=new QueryConditionBean();
                queryConditionBean.setNumber(0);
                queryConditionBean.setSize(10000);
                queryConditionBean.setDirection("ASC");
                queryConditionBean.setProperty("id");
                List<QueryConditionBean.RulesBean> rulesBeanList=new ArrayList<QueryConditionBean.RulesBean>();;
                List<Integer> value2 = new ArrayList<Integer>();
                QueryConditionBean.RulesBean rulesBean2=new QueryConditionBean.RulesBean();
                rulesBean2.setField("cached");
                rulesBean2.setOption("EQ");
                value2.add(1);
                rulesBean2.setValues(value2);
                rulesBeanList.add(rulesBean2);
                queryConditionBean.setRules(rulesBeanList);
                JiuyiHttp.POST("dict/page?")
                        .setJson(GsonUtil.gson().toJson(queryConditionBean))
//                .addHeader("Authorization", Rc.id_tokenparam)
                        .request(new ACallback<DictResultBean>() {
                            @Override
                            public void onSuccess(DictResultBean data) {
                                if(data!=null){
                                    List<DictResultBean.ContentBean> ContentBeanlist=data.getContent();
                                    List<DictBean> dictBeanList=new ArrayList<>();
                                    if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                                        for(DictResultBean.ContentBean contentBean:ContentBeanlist){
                                            DictBean dictBean =new DictBean();
                                            dictBean.setOriginalid(contentBean.getId());
                                            dictBean.setDesp(contentBean.getDesp());
                                            dictBean.setName(contentBean.getName());
                                            dictBean.setKey(contentBean.getKey());
                                            dictBean.setRemark(contentBean.getRemark());
                                            dictBean.setValue(contentBean.getValue());
                                            dictBeanList.add(dictBean);
                                        }

                                    }
                                    if(dictBeanList!=null && dictBeanList.size()>0){
                                        DbHelper.getInstance().dictBeanLongDBManager().deleteAll();
                                        DbHelper.getInstance().dictBeanLongDBManager().insertInTx(dictBeanList);
                                    }
                                    Rc.mCheckInitDict=true;
                                }

                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                            }
                        });


            }
        }).start();

    }
}
