package com.jiuyi.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

@Entity
public class DictBean {
    @Id(autoincrement = true)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String name;
    private String desp;
    private String key;
    private String value;
    private String remark;

    public long getOriginalid() {
        return originalid;
    }

    public void setOriginalid(long originalid) {
        this.originalid = originalid;
    }

    private long originalid;

    @Keep
    public DictBean(Long id, String name, String desp, String key, String value,
            String remark, long originalid) {
        this.id = id;
        this.name = name;
        this.desp = desp;
        this.key = key;
        this.value = value;
        this.remark = remark;
        this.originalid = originalid;
    }

    @Generated(hash = 435078066)
    public DictBean() {
    }
}
