package customer.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * 文件名称:MultiInputFileBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/22 11:33
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/22 1.00 初始版本
 * ****************************************************************
 */
public class MultiOutputFileBean {
    public MultiOutputFileBean(int pos) {
        this.pos = pos;
    }

    public MultiOutputFileBean() {
    }

    public int getPos() {
        return pos;

    }

    public void setPos(int pos) {
        this.pos = pos;
    }


    private int pos=-1;

    public List<AttachmentBean> getAttachmentBeanList() {
        return attachmentBeanList;
    }

    public void setAttachmentBeanList(List<AttachmentBean> attachmentBeanList) {
        this.attachmentBeanList = attachmentBeanList;
    }

    private List<AttachmentBean> attachmentBeanList;

}