package dynamic.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:DyViewBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/30 15:52
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/30 1.00 初始版本
 * ****************************************************************
 */
public class DyViewBean {

    /**
     * fkType : PATENT_CERTIFICATION
     * fkIds : [1,2,3,5]
     */

    private String fkType;
    private List<Long> fkIds;

    public String getFkType() {
        return fkType;
    }

    public void setFkType(String fkType) {
        this.fkType = fkType;
    }

    public List<Long> getFkIds() {
        return fkIds;
    }

    public void setFkIds(List<Long> fkIds) {
        this.fkIds = fkIds;
    }
}
