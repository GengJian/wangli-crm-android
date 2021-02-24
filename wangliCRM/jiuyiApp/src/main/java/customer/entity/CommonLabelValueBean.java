package customer.entity;

/**
 * ****************************************************************
 * 文件名称:CommonLabelValueBean.java
 * 作    者:Created by zhengss
 * 创建时间:2018/12/7 15:18
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/12/7 1.00 初始版本
 * ****************************************************************
 */
public class CommonLabelValueBean {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String name;
    private String value;
}
