package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MemberReadBean {
    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * title : 账户信息
         * data : [{"leftContent":"客户名称","rightContent":"宁波凤翔纺织有限公司1","field":"orgName"},{"leftContent":"客户编码","rightContent":"1004821","field":"cnumber"},{"leftContent":"SAP编码","rightContent":null,"field":"sapNumber"},{"leftContent":"客户简称","rightContent":"九翊","field":"abbreviation"},{"leftContent":"客户状态","rightContent":"潜在的","field":"status"},{"leftContent":"领域","rightContent":"经编","field":"field"},{"leftContent":"用途","rightContent":"不倒绒","field":"application"},{"leftContent":"简拼","rightContent":"jiuyi","field":"simpleSpell"},{"leftContent":"客户等级","rightContent":"3","field":"level"},{"leftContent":"交易类型","rightContent":"正式客户","field":"transactionType"},{"leftContent":"客户负责人","rightContent":"yeq","field":"salesman.username"},{"leftContent":"风险等级","rightContent":"高危  ","field":"riskLevel"},{"leftContent":"所属区域","rightContent":"杭州办","field":"office.name"}]
         */

        private String title;
        private List<DataBean> data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Parcelable {
            /**
             * leftContent : 客户名称
             * rightContent : 宁波凤翔纺织有限公司1
             * field : orgName
             */

            private String leftContent;
            private String rightContent;
            private String field;
            private boolean change;

            public boolean isChange() {
                return change;
            }

            public void setChange(boolean change) {
                this.change = change;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDictField() {
                return dictField;
            }

            public void setDictField(String dictField) {
                this.dictField = dictField;
            }

            private String url;
            private String dictField;


            public String getLeftContent() {
                return leftContent;
            }

            public void setLeftContent(String leftContent) {
                this.leftContent = leftContent;
            }

            public String getRightContent() {
                return rightContent;
            }

            public void setRightContent(String rightContent) {
                this.rightContent = rightContent;
            }

            public String getField() {
                return field;
            }

            public void setField(String field) {
                this.field = field;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.leftContent);
                dest.writeString(this.rightContent);
                dest.writeString(this.field);
                dest.writeByte(this.change ? (byte) 1 : (byte) 0);
                dest.writeString(this.url);
                dest.writeString(this.dictField);
            }

            public DataBean() {
            }

            protected DataBean(Parcel in) {
                this.leftContent = in.readString();
                this.rightContent = in.readString();
                this.field = in.readString();
                this.change = in.readByte() != 0;
                this.url = in.readString();
                this.dictField = in.readString();
            }

            public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel source) {
                    return new DataBean(source);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };
        }
    }
}
