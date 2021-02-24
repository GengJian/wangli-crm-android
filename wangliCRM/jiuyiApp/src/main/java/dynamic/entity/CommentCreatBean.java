package dynamic.entity;

/**
 * ****************************************************************
 * 文件名称:CommentCreatBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/3/12 15:27
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/3/12 1.00 初始版本
 * ****************************************************************
 */
public class CommentCreatBean {

    /**
     * fkType : INTELLIGENCE_ITEM
     * fkId : 11131
     * content : 666
     */

    private String fkType;
    private String fkId;
    private String content;

    public String getFkType() {
        return fkType;
    }

    public void setFkType(String fkType) {
        this.fkType = fkType;
    }

    public String getFkId() {
        return fkId;
    }

    public void setFkId(String fkId) {
        this.fkId = fkId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
