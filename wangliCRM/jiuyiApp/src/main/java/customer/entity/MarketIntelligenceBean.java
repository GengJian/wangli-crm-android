package customer.entity;

import com.jiuyi.model.DictBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * 文件名称:MarketIntelligenceBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/11 17:48
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/11 1.00 初始版本
 * ****************************************************************
 */
public class MarketIntelligenceBean {

    /**
     * bigCategoryKey : 情报大类key
     * bigCategoryValue : 情报大类Value
     * businessTypeKey : 情报业务类型key
     * businessTypeValue : 情报业务类型value
     * member : {"id":3}
     * collectDate : 搜集日期
     * intelligenceItemSet : [{"intelligenceTypeKey":"\t情报类型key","intelligenceTypeValue":"情报类型的value","intelligenceInfoKey":"信息类型key","intelligenceInfoValue":"信息类型value","content":"内容","attachmentList":[]}]
     */

    private String bigCategoryKey;
    private String bigCategoryValue;
    private String businessTypeKey;
    private String businessTypeValue;
    private MemberBeanID member;
    private String collectDate;
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

    public MarketIntelligenceBean() {
    }

    public static class IntelligenceItemSetBean {
        public IntelligenceItemSetBean() {
        }

        public IntelligenceItemSetBean(String intelligenceTypeKey, String intelligenceTypeValue, String intelligenceInfoKey, String intelligenceInfoValue, String content, ArrayList<AttachmentBean> attachmentList) {
            this.intelligenceTypeKey = intelligenceTypeKey;
            this.intelligenceTypeValue = intelligenceTypeValue;
            this.intelligenceInfoKey = intelligenceInfoKey;

            this.intelligenceInfoValue = intelligenceInfoValue;
            this.content = content;
            this.attachmentList = attachmentList;
        }

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
        private List<DictBean> dictBeansList,dictBeansList2;

        public List<DictBean> getDictBeansList() {
            return dictBeansList;
        }

        public void setDictBeansList(List<DictBean> dictBeansList) {
            this.dictBeansList = dictBeansList;
        }

        public List<DictBean> getDictBeansList2() {
            return dictBeansList2;
        }

        public void setDictBeansList2(List<DictBean> dictBeansList2) {
            this.dictBeansList2 = dictBeansList2;
        }

        private String content;

        public String getOrginalContent() {
            return orginalContent;
        }

        public void setOrginalContent(String orginalContent) {
            this.orginalContent = orginalContent;
        }

        private String orginalContent;
        private  int position=-1;
        public boolean isSelect;
        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }


        private ArrayList<AttachmentBean> attachmentList;

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

        public ArrayList<AttachmentBean> getAttachmentList() {
            return attachmentList;
        }

        public void setAttachmentList(ArrayList<AttachmentBean> attachmentList) {
            this.attachmentList = attachmentList;
        }
    }
}
