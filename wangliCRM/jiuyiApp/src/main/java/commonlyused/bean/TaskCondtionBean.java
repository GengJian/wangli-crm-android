package commonlyused.bean;

import java.util.List;

public class TaskCondtionBean {

    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * typeName : 我的
         * memberChooseBeans : [{"name":"我的","chooseBeans":null,"memberFeild":"mine","hidden":false,"multiSelect":false},{"name":"我的下属","chooseBeans":null,"memberFeild":"branch","hidden":false,"multiSelect":false}]
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
             * name : 我的
             * chooseBeans : null
             * memberFeild : mine
             * hidden : false
             * multiSelect : false
             */

            private String name;
            private Object chooseBeans;
            private String memberFeild;
            private boolean hidden;
            private boolean multiSelect;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getChooseBeans() {
                return chooseBeans;
            }

            public void setChooseBeans(Object chooseBeans) {
                this.chooseBeans = chooseBeans;
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
        }
    }
}
