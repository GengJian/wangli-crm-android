package customer.entity;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:IntelligenceDetailCreateBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/12 11:43
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/12 1.00 初始版本
 * ****************************************************************
 */
public class IntelligenceDetailCreateBean {


    private String bigCategoryKey;
    private String bigCategoryValue;
    private String businessTypeKey;
    private String businessTypeValue;
    private MemberBeanID member;
    private String collectDate;

    public List<VisibilityScropeBean> getVisibleRangeList() {
        return visibleRangeList;
    }

    public void setVisibleRangeList(List<VisibilityScropeBean> visibleRangeList) {
        this.visibleRangeList = visibleRangeList;
    }

    private List<VisibilityScropeBean> visibleRangeList;
    private List<IntelligenceItemSetBean> intelligenceItemSet;

    public String getBigCategoryKey() {
        return bigCategoryKey;
    }

    public void setBigCategoryKey(String bigCategoryKey) {
        this.bigCategoryKey = bigCategoryKey;
    }

    public String getBigCategoryValue() {
        return bigCategoryValue;
    }

    public void setBigCategoryValue(String bigCategoryValue) {
        this.bigCategoryValue = bigCategoryValue;
    }

    public String getBusinessTypeKey() {
        return businessTypeKey;
    }

    public void setBusinessTypeKey(String businessTypeKey) {
        this.businessTypeKey = businessTypeKey;
    }

    public String getBusinessTypeValue() {
        return businessTypeValue;
    }

    public void setBusinessTypeValue(String businessTypeValue) {
        this.businessTypeValue = businessTypeValue;
    }

    public MemberBeanID getMember() {
        return member;
    }

    public void setMember(MemberBeanID member) {
        this.member = member;
    }

    public String getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(String collectDate) {
        this.collectDate = collectDate;
    }

    public List<IntelligenceItemSetBean> getIntelligenceItemSet() {
        return intelligenceItemSet;
    }

    public void setIntelligenceItemSet(List<IntelligenceItemSetBean> intelligenceItemSet) {
        this.intelligenceItemSet = intelligenceItemSet;
    }


    public static class IntelligenceItemSetBean {
        /**
         * intelligenceTypeKey : 	情报类型key
         * intelligenceTypeValue : 情报类型的value
         * intelligenceInfoKey : 信息类型key
         * intelligenceInfoValue : 信息类型value
         * content : 内容
         * attachmentList : []
         */

        private String intelligenceTypeKey;
        private String intelligenceTypeValue;
        private String intelligenceInfoKey;
        private String intelligenceInfoValue;
        private String content;
        private List<AttachmentBean> attachmentList;

        public String getIntelligenceTypeKey() {
            return intelligenceTypeKey;
        }

        public void setIntelligenceTypeKey(String intelligenceTypeKey) {
            this.intelligenceTypeKey = intelligenceTypeKey;
        }

        public String getIntelligenceTypeValue() {
            return intelligenceTypeValue;
        }

        public void setIntelligenceTypeValue(String intelligenceTypeValue) {
            this.intelligenceTypeValue = intelligenceTypeValue;
        }

        public String getIntelligenceInfoKey() {
            return intelligenceInfoKey;
        }

        public void setIntelligenceInfoKey(String intelligenceInfoKey) {
            this.intelligenceInfoKey = intelligenceInfoKey;
        }

        public String getIntelligenceInfoValue() {
            return intelligenceInfoValue;
        }

        public void setIntelligenceInfoValue(String intelligenceInfoValue) {
            this.intelligenceInfoValue = intelligenceInfoValue;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(List<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }
    }
}
