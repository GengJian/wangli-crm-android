package customer.entity;

import java.util.List;

public class MemberchooseBean {


    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * typeName : 手工筛选
         * memberChooseBeans : [{"name":"交易类型","chooseBeans":[{"key":"经销商","value":"dealer","option":"EQ"}],"memberFeild":"transactionType","hidden":false,"multiSelect":false},{"name":"负责人","chooseBeans":[{"key":"我的","value":"MINE","option":null},{"key":"我下属的","value":"UNDERMINE","option":null},{"key":"不属于任何人的","value":"NOBELONG","option":null}],"memberFeild":null,"hidden":false,"multiSelect":true},{"name":"部门","chooseBeans":[{"key":"总裁办","value":"2","option":"EQ"},{"key":"漳州办","value":"4","option":"EQ"},{"key":"上海办","value":"6","option":"EQ"},{"key":"北京办","value":"7","option":"EQ"},{"key":"西南大区","value":"8","option":"EQ"},{"key":"东北大区","value":"9","option":"EQ"},{"key":"综合办","value":"10","option":"EQ"},{"key":"浙江大区","value":"28","option":"EQ"}],"memberFeild":"office.id","hidden":true,"multiSelect":false},{"name":"领域","chooseBeans":[{"key":"精编","value":"W/K","option":"EQ"}],"memberFeild":"field","hidden":false,"multiSelect":false},{"name":"用途","chooseBeans":[{"key":"单面机","value":"singleMachine","option":"EQ"}],"memberFeild":"application","hidden":false,"multiSelect":false},{"name":"账期","chooseBeans":[{"key":"0","value":"0","option":"EQ"},{"key":"30","value":"30","option":"EQ"},{"key":"45","value":"45","option":"EQ"},{"key":"60","value":"60","option":"EQ"},{"key":"90","value":"90","option":"EQ"},{"key":"90","value":"90","option":"GT"}],"memberFeild":"creditModifyDate","hidden":true,"multiSelect":false},{"name":"额度","chooseBeans":[{"key":"10~30万","value":"10,30","option":"BT"},{"key":"30~50万","value":"30,50","option":"BT"},{"key":"50~100万","value":"50,100","option":"BT"},{"key":"100~200万","value":"100,200","option":"BT"},{"key":"200~500万","value":"200,500","option":"BT"},{"key":"500万+","value":"500","option":"GT"}],"memberFeild":"creditRiskTotalAmount","hidden":true,"multiSelect":false},{"name":"风险级别","chooseBeans":[],"memberFeild":"riskLevel","hidden":true,"multiSelect":false},{"name":"最后一天成交日期","chooseBeans":[{"key":"超出九十天未成交","value":"2018-02-21 13:29:05","option":"LEDATE"}],"memberFeild":"lastTransactionDays","hidden":false,"multiSelect":false}]
         */

        private String typeName;
        private List<MemberChooseBeansBean> memberChooseBeans;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public List<MemberChooseBeansBean> getMemberChooseBeans() {
            return memberChooseBeans;
        }

        public void setMemberChooseBeans(List<MemberChooseBeansBean> memberChooseBeans) {
            this.memberChooseBeans = memberChooseBeans;
        }

        public static class MemberChooseBeansBean {
            /**
             * name : 交易类型
             * chooseBeans : [{"key":"经销商","value":"dealer","option":"EQ"}]
             * memberFeild : transactionType
             * hidden : false
             * multiSelect : false
             */

            private String name;
            private String memberFeild;
            private boolean hidden;
            private boolean multiSelect;

            public boolean isSpecial() {
                return special;
            }

            public void setSpecial(boolean special) {
                this.special = special;
            }

            private boolean special;
            private List<ChooseBeansBean> chooseBeans;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMemberFeild() {
                return memberFeild;
            }

            public void setMemberFeild(String memberFeild) {
                this.memberFeild = memberFeild;
            }

            public boolean isHidden() {
                return hidden;
            }

            public void setHidden(boolean hidden) {
                this.hidden = hidden;
            }

            public boolean isMultiSelect() {
                return multiSelect;
            }

            public void setMultiSelect(boolean multiSelect) {
                this.multiSelect = multiSelect;
            }

            public List<ChooseBeansBean> getChooseBeans() {
                return chooseBeans;
            }

            public void setChooseBeans(List<ChooseBeansBean> chooseBeans) {
                this.chooseBeans = chooseBeans;
            }

            public static class ChooseBeansBean {
                /**
                 * key : 经销商
                 * value : dealer
                 * option : EQ
                 */

                private String key;
                private String value;
                private String option;

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

                public String getOption() {
                    return option;
                }

                public void setOption(String option) {
                    this.option = option;
                }
            }
        }
    }
}
