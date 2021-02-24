package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MemberListBean {

    /**
     * content : [{"id":21,"orgName":"中宁纺织","cnumber":"101552","sapNumber":"100088","transactionType":"123","salesmanId":47,"salesmanName":"沈佳铭","riskLevel":"危险","officeId":5,"officeName":"杭州办","headUrl":"http://img.jiuyisoft.com/cava3.jpg","lastTransactionDayCount":30,"release":false,"transfer":false,"claim":false},{"id":22,"orgName":"中宁纺织","cnumber":"102652","sapNumber":"100411","transactionType":"123","salesmanId":47,"salesmanName":"沈佳铭","riskLevel":"正常","officeId":5,"officeName":"杭州办","headUrl":"http://img.jiuyisoft.com/cava4.jpg","lastTransactionDayCount":30,"release":false,"transfer":false,"claim":false},{"id":23,"orgName":"中宁纺织","cnumber":"111642","sapNumber":"100216","transactionType":"123","salesmanId":47,"salesmanName":"沈佳铭","riskLevel":"危险","officeId":5,"officeName":"杭州办","headUrl":"http://img.jiuyisoft.com/cava6.jpg","lastTransactionDayCount":30,"release":false,"transfer":false,"claim":false}]
     * totalElements : 41
     * last : true
     * totalPages : 1
     * number : 0
     * size : 0
     * sort : null
     * numberOfElements : 3
     * first : true
     */

    private int totalElements;
    private boolean last;
    private int totalPages;
    private int number;
    private int size;
    private Object sort;
    private int numberOfElements;
    private boolean first;
    private List<ContentBean> content;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean implements Parcelable {
        private long id;


        protected ContentBean(Parcel in) {
            id = in.readLong();
            orgName = in.readString();
            abbreviation = in.readString();
            crmNumber = in.readString();
            statusKey = in.readString();
            statusValue = in.readString();
            cooperationTypeKey = in.readString();
            cooperationType = in.readString();
            memberLevelKey = in.readString();
            memberLevelValue = in.readString();
            straightShow = in.readString();
            operatorId = in.readLong();
            operatorName = in.readString();
            creditLevelKey = in.readString();
            creditLevelValue = in.readString();
            brand = in.readString();
            businessScope = in.readString();
            avatarUrl = in.readString();
            stras = in.readString();
            legalName = in.readString();
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel in) {
                return new ContentBean(in);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getAbbreviation() {
            return abbreviation;
        }

        public void setAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getCrmNumber() {
            return crmNumber;
        }

        public void setCrmNumber(String crmNumber) {
            this.crmNumber = crmNumber;
        }

        public String getStatusKey() {
            return statusKey;
        }

        public void setStatusKey(String statusKey) {
            this.statusKey = statusKey;
        }

        public String getStatusValue() {
            return statusValue;
        }

        public void setStatusValue(String statusValue) {
            this.statusValue = statusValue;
        }

        public String getCooperationTypeKey() {
            return cooperationTypeKey;
        }

        public void setCooperationTypeKey(String cooperationTypeKey) {
            this.cooperationTypeKey = cooperationTypeKey;
        }

        public String getCooperationType() {
            return cooperationType;
        }

        public void setCooperationType(String cooperationType) {
            this.cooperationType = cooperationType;
        }

        public String getMemberLevelKey() {
            return memberLevelKey;
        }

        public void setMemberLevelKey(String memberLevelKey) {
            this.memberLevelKey = memberLevelKey;
        }

        public String getMemberLevelValue() {
            return memberLevelValue;
        }

        public void setMemberLevelValue(String memberLevelValue) {
            this.memberLevelValue = memberLevelValue;
        }

        public String getStraightShow() {
            return straightShow;
        }

        public void setStraightShow(String straightShow) {
            this.straightShow = straightShow;
        }

        public long getOperatorId() {
            return operatorId;
        }

        public void setOperatorId(long operatorId) {
            this.operatorId = operatorId;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public void setOperatorName(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getCreditLevelKey() {
            return creditLevelKey;
        }

        public void setCreditLevelKey(String creditLevelKey) {
            this.creditLevelKey = creditLevelKey;
        }

        public String getCreditLevelValue() {
            return creditLevelValue;
        }

        public void setCreditLevelValue(String creditLevelValue) {
            this.creditLevelValue = creditLevelValue;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getBusinessScope() {
            return businessScope;
        }

        public void setBusinessScope(String businessScope) {
            this.businessScope = businessScope;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getStras() {
            return stras;
        }

        public void setStras(String stras) {
            this.stras = stras;
        }

        private String orgName;
        private String abbreviation;
        private String crmNumber;
        private String statusKey;
        private String statusValue;
        private String cooperationTypeKey;
        private String cooperationType;
        private String memberLevelKey;
        private String memberLevelValue;
        private String straightShow;
        private long operatorId;
        private String operatorName;
        private String creditLevelKey;
        private String creditLevelValue;
        private String brand;
        private String businessScope;
        private String avatarUrl;
        private String stras;

        public String getLegalName() {
            return legalName;
        }

        public void setLegalName(String legalName) {
            this.legalName = legalName;
        }

        private String legalName;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(orgName);
            dest.writeString(abbreviation);
            dest.writeString(crmNumber);
            dest.writeString(statusKey);
            dest.writeString(statusValue);
            dest.writeString(cooperationTypeKey);
            dest.writeString(cooperationType);
            dest.writeString(memberLevelKey);
            dest.writeString(memberLevelValue);
            dest.writeString(straightShow);
            dest.writeLong(operatorId);
            dest.writeString(operatorName);
            dest.writeString(creditLevelKey);
            dest.writeString(creditLevelValue);
            dest.writeString(brand);
            dest.writeString(businessScope);
            dest.writeString(avatarUrl);
            dest.writeString(stras);
            dest.writeString(legalName);
        }
    }
}
