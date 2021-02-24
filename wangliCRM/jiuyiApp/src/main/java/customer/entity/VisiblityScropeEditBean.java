package customer.entity;

import java.util.List;

import commonlyused.bean.NormalOperatorBean;

/**
 * ****************************************************************
 * 文件名称:VisiblityScropeEditBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/3/7 17:54
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/3/7 1.00 初始版本
 * ****************************************************************
 */
public class VisiblityScropeEditBean {

    /**
     * visibleType : PUBLIC
     * visibleTypeValue : 公开
     * dictVo : {"id":null,"key":"PUBLIC","value":"公开"}
     * departmentTree : null
     * frSrArBeanList : null
     * someoneList : null
     */

    private String visibleType;

    public String getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(String visibleType) {
        this.visibleType = visibleType;
    }

    public String getVisibleTypeValue() {
        return visibleTypeValue;
    }

    public void setVisibleTypeValue(String visibleTypeValue) {
        this.visibleTypeValue = visibleTypeValue;
    }

    public DictVoBean getDictVo() {
        return dictVo;
    }

    public void setDictVo(DictVoBean dictVo) {
        this.dictVo = dictVo;
    }

    public List<VisibleTypeBean> getDepartmentTree() {
        return departmentTree;
    }

    public void setDepartmentTree(List<VisibleTypeBean> departmentTree) {
        this.departmentTree = departmentTree;
    }

    public List<FrSrArBeanListBean> getFrSrArBeanList() {
        return frSrArBeanList;
    }

    public void setFrSrArBeanList(List<FrSrArBeanListBean> frSrArBeanList) {
        this.frSrArBeanList = frSrArBeanList;
    }

    public List<NormalOperatorBean.OperatorBeanID> getSomeoneList() {
        return someoneList;
    }

    public void setSomeoneList(List<NormalOperatorBean.OperatorBeanID> someoneList) {
        this.someoneList = someoneList;
    }

    private String visibleTypeValue;
    private DictVoBean dictVo;
    private List<VisibleTypeBean> departmentTree;
    private List<FrSrArBeanListBean> frSrArBeanList;
    private List<NormalOperatorBean.OperatorBeanID> someoneList;

    public List<Long> getDepartmentIdList() {
        return departmentIdList;
    }

    public void setDepartmentIdList(List<Long> departmentIdList) {
        this.departmentIdList = departmentIdList;
    }

    private List<Long> departmentIdList;


    public static class DictVoBean {
        /**
         * id : null
         * key : PUBLIC
         * value : 公开
         */

        private Object id;
        private String key;
        private String value;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class FrSrArBeanListBean {


        private String frSrAr;

        public String getFrSrAr() {
            return frSrAr;
        }

        public void setFrSrAr(String frSrAr) {
            this.frSrAr = frSrAr;
        }

        public NormalOperatorBean.OperatorBeanID getOperator() {
            return operator;
        }

        public void setOperator(NormalOperatorBean.OperatorBeanID operator) {
            this.operator = operator;
        }

        private NormalOperatorBean.OperatorBeanID operator;

    }
}
