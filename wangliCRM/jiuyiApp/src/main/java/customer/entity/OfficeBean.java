package customer.entity;

/**
 * ****************************************************************
 * 文件名称:OfficeBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/4 14:50
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/4 1.00 初始版本
 * ****************************************************************
 */
public class OfficeBean {

    /**
     * id : 8
     * name : 总裁办
     * totalCount : 5
     * memberId : 199
     * parentOfficeId : null
     */

    private long id;
    private String name;
    private int totalCount;
    private long memberId;
    private long parentOfficeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getParentOfficeId() {
        return parentOfficeId;
    }

    public void setParentOfficeId(long parentOfficeId) {
        this.parentOfficeId = parentOfficeId;
    }
}
