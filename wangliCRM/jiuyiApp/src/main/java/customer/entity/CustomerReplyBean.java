package customer.entity;

/**
 * ****************************************************************
 * 文件名称:CustomerReplyBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/22 16:59
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/22 1.00 初始版本
 * ****************************************************************
 */
public class CustomerReplyBean {

    /**
     * id : 30
     * value : 这是回复
     * field : reply
     */

    private long id;
    private String value;
    private String field;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
