package commonlyused.request;

import com.common.GsonUtil;
import com.control.utils.JiuyiLog;
import com.control.utils.Rc;
import com.google.gson.internal.LinkedTreeMap;
import com.http.callback.ACallback;
import com.http.JiuyiHttp;

import java.util.ArrayList;
import java.util.List;

import commonlyused.bean.AppItemBean;
import commonlyused.bean.NormalDeptBean;
import commonlyused.bean.NormalOperatorBean;
import commonlyused.bean.OperatorNodeBean;
import commonlyused.bean.QueryConditionBean;
import commonlyused.db.AppItemBeanDao;
import commonlyused.db.DbHelper;
import commonlyused.db.OperatorNodeBeanDao;
import commonlyused.treelist.Node;

public class RequestCompanyAddressList {
    public static RequestCompanyAddressList Instance;

    public static RequestCompanyAddressList getIns() {
        if (Instance == null)
            Instance = new RequestCompanyAddressList();
        return Instance;
    }
    public  void  getDeptAddressList(){
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(1000);
        queryConditionBean.setDirection("ASC");
        queryConditionBean.setProperty("id");
        JiuyiHttp.POST("department/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization",Rc.id_tokenparam)
                .request(new ACallback<NormalDeptBean>() {
                    @Override
                    public void onSuccess(NormalDeptBean data) {
                        if(data!=null){
                            DbHelper.getInstance().operatorNode().deleteAll();
                            int allTotal=0;
                            List<NormalDeptBean.ContentBean> ContentBeanlist=data.getContent();
                            List<OperatorNodeBean> operatorNodeBeanList=new ArrayList<>();
                            if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                                for(NormalDeptBean.ContentBean contentBean :ContentBeanlist){
                                    //二级菜单
                                    String parentid="all";
                                    if(contentBean.getParent()!=null){
                                        String parentid2=((LinkedTreeMap) contentBean.getParent()).get("id")+"";;
                                        if(parentid2.contains(".")) {
                                            parentid2=parentid2.substring(0, parentid2.indexOf("."));
                                        }
                                        OperatorNodeBean operatorNodeBean=new OperatorNodeBean();
                                        operatorNodeBean.setNodeid("d"+contentBean.getId());
                                        operatorNodeBean.setNodepid("d"+parentid2);
                                        operatorNodeBean.setName(contentBean.getName());
                                        operatorNodeBean.setCount(contentBean.getTotalCount());
                                        operatorNodeBeanList.add(operatorNodeBean);
                                    }else{
                                        OperatorNodeBean operatorNodeBean=new OperatorNodeBean();
                                        operatorNodeBean.setNodeid("d"+contentBean.getId());
                                        operatorNodeBean.setNodepid("d"+parentid);
                                        operatorNodeBean.setName(contentBean.getName());
                                        operatorNodeBean.setCount(contentBean.getTotalCount());
                                        operatorNodeBeanList.add(operatorNodeBean);
                                        allTotal+=contentBean.getTotalCount();
                                    }

                                }
                                //创建第一级菜单
                                OperatorNodeBean operatorNodeBean=new OperatorNodeBean();
                                operatorNodeBean.setNodeid("dall");
                                operatorNodeBean.setNodepid("-1");
                                operatorNodeBean.setName("中国王力集团");
                                operatorNodeBean.setCount(allTotal);
                                operatorNodeBeanList.add(operatorNodeBean);
                            }
                            if(operatorNodeBeanList!=null && operatorNodeBeanList.size()>0){
                                DbHelper.getInstance().operatorNode().insertInTx(operatorNodeBeanList);
                            }
                            getCompanyAddressList();

                        }
                        JiuyiLog.e("httplogin","request onSuccess!" + data);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
//                JiuyiLog.e("httplogin","request errorCode:" + errCode + ",errorMsg:" + errMsg);
                    }
                });

    }

    public  List<Node>  getCompanyAddressList(){
        QueryConditionBean queryConditionBean=new QueryConditionBean();
        queryConditionBean.setNumber(0);
        queryConditionBean.setSize(2000);
        queryConditionBean.setDirection("ASC");
        queryConditionBean.setProperty("id");
        JiuyiHttp.POST("operator/page?")
                .setJson(GsonUtil.gson().toJson(queryConditionBean))
                .addHeader("Authorization", Rc.id_tokenparam)
                .request(new ACallback<NormalOperatorBean>() {
                    @Override
                    public void onSuccess(NormalOperatorBean data) {
                        if(data!=null){
                            //创建第一级菜单
                            List<NormalOperatorBean.ContentBean> ContentBeanlist=data.getContent();
                            List<OperatorNodeBean> operatorNodeBeanList=new ArrayList<>();
                            if(ContentBeanlist!=null && ContentBeanlist.size()>0){
                                //创建第一级菜单
                                for(NormalOperatorBean.ContentBean contentBean :ContentBeanlist){
                                    String parentid="";
                                    if(contentBean.getDepartment()!=null){
                                        parentid=String.valueOf(contentBean.getDepartment().getId());
                                    }else{
                                        parentid="all";
                                    }
                                    if(!parentid.equals("all")){
                                        parentid="d"+parentid;
                                    }

                                    OperatorNodeBean operatorNodeBean=new OperatorNodeBean();
                                    operatorNodeBean.setNodeid(contentBean.getId()+"");
                                    operatorNodeBean.setNodepid(parentid);
                                    operatorNodeBean.setName(contentBean.getName());
                                    operatorNodeBean.setCount(1);
                                    if(contentBean.getTitle()!=null){
                                        operatorNodeBean.setDuty(contentBean.getTitle());
                                    }
                                    operatorNodeBean.setIsPeople(1);
                                    operatorNodeBeanList.add(operatorNodeBean);
                                }
                            }
                            if(operatorNodeBeanList!=null && operatorNodeBeanList.size()>0){
                                DbHelper.getInstance().operatorNode().insertInTx(operatorNodeBeanList);
                            }

                            OperatorNodeBean itemBean = DbHelper.getInstance().operatorNode().queryBuilder()
                                    .where(OperatorNodeBeanDao.Properties.Nodeid.eq("dall")).build().unique();
                            if(itemBean!=null){
                                itemBean.setCount(data.getTotalElements());
                                DbHelper.getInstance().operatorNode().update(itemBean);
                            }
                        }
                        JiuyiLog.e("httplogin","request onSuccess!" + data);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                    }
                });
        return null;
    }
}
