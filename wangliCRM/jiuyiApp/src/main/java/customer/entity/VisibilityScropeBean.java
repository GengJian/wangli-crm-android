package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:VisibilityScropeBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/3/4 10:31
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/3/4 1.00 初始版本
 * ****************************************************************
 */
public class VisibilityScropeBean {
    public String getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(String visibleType) {
        this.visibleType = visibleType;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public String getFrSrAr() {
        return frSrAr;
    }

    public void setFrSrAr(String frSrAr) {
        this.frSrAr = frSrAr;
    }

    private String visibleType;
    private long departmentId;
    private long positionId;
    private long operatorId;
    private String frSrAr;


}
