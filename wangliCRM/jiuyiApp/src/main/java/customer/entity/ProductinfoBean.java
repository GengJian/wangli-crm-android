package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.jiuyi.model.DictResultBean;

import java.util.List;

public class ProductinfoBean {

    /**
     * content : [{"createdBy":"admin","createdDate":"2018-04-23 15:00:06","lastModifiedBy":"admin","lastModifiedDate":"2018-04-23 15:00:06","id":1,"deleted":false,"sort":10,"fromClientType":null,"member":null,"category":{"createdBy":"admin","createdDate":"2018-04-09 18:33:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-09 18:33:56","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"name_1","desp":null,"key":"key_1","value":"value_1","modifiable":null,"builtIn":true,"remark":null,"orders":null},"name":"无纺布","ingredient":{"createdBy":"admin","createdDate":"2018-04-09 18:33:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-09 18:33:56","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"name_1","desp":null,"key":"key_1","value":"value_1","modifiable":null,"builtIn":true,"remark":null,"orders":null},"yarn":null,"weight":null,"dyeingTechnology":null,"qualityStandard":null,"popularElement":null,"applicableSession":null,"printStyle":null,"color":null,"marketExpectation":null,"customerGroup":"群众","marketReferencePrice":100},{"createdBy":"admin","createdDate":"2018-04-23 15:00:43","lastModifiedBy":"admin","lastModifiedDate":"2018-04-23 15:00:43","id":5,"deleted":false,"sort":10,"fromClientType":null,"member":null,"category":{"createdBy":"admin","createdDate":"2018-04-09 18:33:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-09 18:33:56","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"name_1","desp":null,"key":"key_1","value":"value_1","modifiable":null,"builtIn":true,"remark":null,"orders":null},"name":"无纺布","ingredient":{"createdBy":"admin","createdDate":"2018-04-09 18:33:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-09 18:33:56","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"name_1","desp":null,"key":"key_1","value":"value_1","modifiable":null,"builtIn":true,"remark":null,"orders":null},"yarn":null,"weight":null,"dyeingTechnology":null,"qualityStandard":null,"popularElement":null,"applicableSession":null,"printStyle":null,"color":null,"marketExpectation":null,"customerGroup":"群众","marketReferencePrice":100},{"createdBy":"admin","createdDate":"2018-04-25 10:01:23","lastModifiedBy":"admin","lastModifiedDate":"2018-04-25 10:01:23","id":6,"deleted":false,"sort":10,"fromClientType":null,"member":null,"category":{"createdBy":"admin","createdDate":"2018-04-09 18:33:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-09 18:33:56","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"name_1","desp":null,"key":"key_1","value":"value_1","modifiable":null,"builtIn":true,"remark":null,"orders":null},"name":"无纺布","ingredient":{"createdBy":"admin","createdDate":"2018-04-09 18:33:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-09 18:33:56","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"name_1","desp":null,"key":"key_1","value":"value_1","modifiable":null,"builtIn":true,"remark":null,"orders":null},"yarn":null,"weight":null,"dyeingTechnology":null,"qualityStandard":null,"popularElement":null,"applicableSession":null,"printStyle":null,"color":null,"marketExpectation":null,"customerGroup":"群众","marketReferencePrice":100}]
     * last : false
     * totalElements : 4
     * totalPages : 2
     * number : 0
     * size : 3
     * sort : [{"direction":"ASC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}]
     * numberOfElements : 3
     * first : true
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int number;
    private int size;
    private int numberOfElements;
    private boolean first;
    private List<ProductinfoBean.ContentBean> content;
    private List<ProductinfoBean.SortBean> sort;



    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
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

    public List<ProductinfoBean.ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ProductinfoBean.ContentBean> content) {
        this.content = content;
    }

    public List<ProductinfoBean.SortBean> getSort() {
        return sort;
    }

    public void setSort(List<ProductinfoBean.SortBean> sort) {
        this.sort = sort;
    }

    public static class ContentBean implements Parcelable {
        public List<AttachmentBean> getAttachmentBeansList() {
            return attachments;
        }

        public void setAttachmentBeansList(List<AttachmentBean> attachmentBeansList) {
            this.attachments = attachmentBeansList;
        }

        private List<AttachmentBean> attachments;
        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedBy() {
            return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getFromClientType() {
            return fromClientType;
        }

        public void setFromClientType(String fromClientType) {
            this.fromClientType = fromClientType;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public DictResultBean.ContentBean getCategory() {
            return category;
        }

        public void setCategory(DictResultBean.ContentBean category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public DictResultBean.ContentBean getIngredient() {
            return ingredient;
        }

        public void setIngredient(DictResultBean.ContentBean ingredient) {
            this.ingredient = ingredient;
        }

        public DictResultBean.ContentBean getYarn() {
            return yarn;
        }

        public void setYarn(DictResultBean.ContentBean yarn) {
            this.yarn = yarn;
        }

        public DictResultBean.ContentBean getWeight() {
            return weight;
        }

        public void setWeight(DictResultBean.ContentBean weight) {
            this.weight = weight;
        }

        public DictResultBean.ContentBean getDyeingTechnology() {
            return dyeingTechnology;
        }

        public void setDyeingTechnology(DictResultBean.ContentBean dyeingTechnology) {
            this.dyeingTechnology = dyeingTechnology;
        }

        public DictResultBean.ContentBean getQualityStandard() {
            return qualityStandard;
        }

        public void setQualityStandard(DictResultBean.ContentBean qualityStandard) {
            this.qualityStandard = qualityStandard;
        }

        public DictResultBean.ContentBean getPopularElement() {
            return popularElement;
        }

        public void setPopularElement(DictResultBean.ContentBean popularElement) {
            this.popularElement = popularElement;
        }

        public DictResultBean.ContentBean getApplicableSession() {
            return applicableSession;
        }

        public void setApplicableSession(DictResultBean.ContentBean applicableSession) {
            this.applicableSession = applicableSession;
        }

        public DictResultBean.ContentBean getPrintStyle() {
            return printStyle;
        }

        public void setPrintStyle(DictResultBean.ContentBean printStyle) {
            this.printStyle = printStyle;
        }

        public DictResultBean.ContentBean getColor() {
            return color;
        }

        public void setColor(DictResultBean.ContentBean color) {
            this.color = color;
        }

        public DictResultBean.ContentBean getMarketExpectation() {
            return marketExpectation;
        }

        public void setMarketExpectation(DictResultBean.ContentBean marketExpectation) {
            this.marketExpectation = marketExpectation;
        }

        public String getCustomerGroup() {
            return customerGroup;
        }

        public void setCustomerGroup(String customerGroup) {
            this.customerGroup = customerGroup;
        }

        public double getMarketReferencePrice() {
            return marketReferencePrice;
        }

        public void setMarketReferencePrice(double marketReferencePrice) {
            this.marketReferencePrice = marketReferencePrice;
        }

        /**
         * createdBy : admin
         * createdDate : 2018-04-23 15:00:06
         * lastModifiedBy : admin
         * lastModifiedDate : 2018-04-23 15:00:06
         * id : 1
         * deleted : false
         * sort : 10
         * fromClientType : null
         * member : null
         * category : {"createdBy":"admin","createdDate":"2018-04-09 18:33:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-09 18:33:56","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"name_1","desp":null,"key":"key_1","value":"value_1","modifiable":null,"builtIn":true,"remark":null,"orders":null}
         * name : 无纺布
         * ingredient : {"createdBy":"admin","createdDate":"2018-04-09 18:33:56","lastModifiedBy":"admin","lastModifiedDate":"2018-04-09 18:33:56","id":1,"deleted":false,"sort":10,"fromClientType":null,"name":"name_1","desp":null,"key":"key_1","value":"value_1","modifiable":null,"builtIn":true,"remark":null,"orders":null}
         * yarn : null
         * weight : null
         * dyeingTechnology : null
         * qualityStandard : null
         * popularElement : null
         * applicableSession : null
         * printStyle : null
         * color : null
         * marketExpectation : null
         * customerGroup : 群众
         * marketReferencePrice : 100
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String fromClientType;
        private MemberBean member;

        public FactoryBean getFactory() {
            return factory;
        }

        public void setFactory(FactoryBean factory) {
            this.factory = factory;
        }

        private FactoryBean factory;
        private DictResultBean.ContentBean category;
        private String name;

        public String getIngredientValue() {
            return ingredientValue;
        }

        public void setIngredientValue(String ingredientValue) {
            this.ingredientValue = ingredientValue;
        }

        public String getWeightValue() {
            return weightValue;
        }

        public void setWeightValue(String weightValue) {
            this.weightValue = weightValue;
        }

        public String getFinishedDoor() {
            return finishedDoor;
        }

        public void setFinishedDoor(String finishedDoor) {
            this.finishedDoor = finishedDoor;
        }

        public String getDyeingTechnologyValue() {
            return dyeingTechnologyValue;
        }

        public void setDyeingTechnologyValue(String dyeingTechnologyValue) {
            this.dyeingTechnologyValue = dyeingTechnologyValue;
        }

        public String getQualityStandardValue() {
            return qualityStandardValue;
        }

        public void setQualityStandardValue(String qualityStandardValue) {
            this.qualityStandardValue = qualityStandardValue;
        }

        public String getApplicableSessionValue() {
            return applicableSessionValue;
        }

        public void setApplicableSessionValue(String applicableSessionValue) {
            this.applicableSessionValue = applicableSessionValue;
        }

        private String ingredientValue;
        private String weightValue;
        private String finishedDoor;
        private String dyeingTechnologyValue;
        private String qualityStandardValue;
        private String applicableSessionValue;
        private DictResultBean.ContentBean ingredient;
        private DictResultBean.ContentBean yarn;
        private DictResultBean.ContentBean weight;
        private DictResultBean.ContentBean dyeingTechnology;
        private DictResultBean.ContentBean qualityStandard;
        private DictResultBean.ContentBean popularElement;
        private DictResultBean.ContentBean applicableSession;
        private DictResultBean.ContentBean printStyle;
        private DictResultBean.ContentBean color;
        private DictResultBean.ContentBean marketExpectation;
        private String customerGroup;
        private double marketReferencePrice;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.createdBy);
            dest.writeString(this.createdDate);
            dest.writeString(this.lastModifiedBy);
            dest.writeString(this.lastModifiedDate);
            dest.writeLong(this.id);
            dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
            dest.writeInt(this.sort);
            dest.writeString(this.fromClientType);
            dest.writeParcelable(this.category, flags);
            dest.writeString(this.name);
            dest.writeParcelable(this.ingredient, flags);
            dest.writeParcelable(this.yarn, flags);
            dest.writeParcelable(this.weight, flags);
            dest.writeParcelable(this.dyeingTechnology, flags);
            dest.writeParcelable(this.qualityStandard, flags);
            dest.writeParcelable(this.popularElement, flags);
            dest.writeParcelable(this.applicableSession, flags);
            dest.writeParcelable(this.printStyle, flags);
            dest.writeParcelable(this.color, flags);
            dest.writeParcelable(this.marketExpectation, flags);
            dest.writeString(this.customerGroup);
            dest.writeDouble(this.marketReferencePrice);
        }

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.createdBy = in.readString();
            this.createdDate = in.readString();
            this.lastModifiedBy = in.readString();
            this.lastModifiedDate = in.readString();
            this.id = in.readLong();
            this.deleted = in.readByte() != 0;
            this.sort = in.readInt();
            this.fromClientType = in.readString();
            this.member = in.readParcelable(MemberBean.class.getClassLoader());
            this.category = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.name = in.readString();
            this.ingredient = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.yarn = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.weight = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.dyeingTechnology = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.qualityStandard = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.popularElement = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.applicableSession = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.printStyle = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.color = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.marketExpectation = in.readParcelable(DictResultBean.ContentBean.class.getClassLoader());
            this.customerGroup = in.readString();
            this.marketReferencePrice = in.readDouble();
        }

        public static final Parcelable.Creator<ContentBean> CREATOR = new Parcelable.Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel source) {
                return new ContentBean(source);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };
    }

    public static class SortBean {
        /**
         * direction : ASC
         * property : id
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : true
         * descending : false
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean ascending;
        private boolean descending;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }
    }
    public static class MemberBean {
        /**
         * id : 20
         */

        private long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
}
