package customer.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ****************************************************************
 * 文件名称:CustomerLinkmanBean.java
 * 作    者:Created by zhengss
 * 创建时间:2019/1/9 10:52
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2019/1/9 1.00 初始版本
 * ****************************************************************
 */
public class CustomerLinkmanBean {

    /**
     * content : [{"createdBy":"1022768","createdDate":"2019-01-08","lastModifiedBy":"1022768","lastModifiedDate":"2019-01-08","id":28,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":"田七","type":"MAINMEMBER","phone":"12345678910","email":"24556162@qq.com","member":null,"duty":"科长","favorite":"游泳，健身","avatralUrl":null,"simpleSpell":null,"sex":"女","provinceNumber":null,"provinceName":"浙江","cityNumber":null,"cityName":"杭州","areaNumber":null,"areaName":null,"address":"杭州","office":null,"memberReadBean":null},{"createdBy":"1022768","createdDate":"2019-01-08","lastModifiedBy":"1022768","lastModifiedDate":"2019-01-08","id":27,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":"李四","type":"SHAREHOLDER","phone":"12345678910","email":"24556162@qq.com","member":null,"duty":"科长","favorite":"游泳，健身","avatralUrl":null,"simpleSpell":null,"sex":"男","provinceNumber":null,"provinceName":"浙江","cityNumber":null,"cityName":"杭州","areaNumber":null,"areaName":null,"address":"杭州","office":null,"memberReadBean":null},{"createdBy":"17721180295","createdDate":"2018-12-20","lastModifiedBy":"1022768","lastModifiedDate":"2019-01-08","id":10,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"钱","type":"CONTACTPERSON","phone":"12345678910","email":"24556162@qq.com","member":null,"duty":"科长","favorite":null,"avatralUrl":"","simpleSpell":null,"sex":"女","provinceNumber":null,"provinceName":"浙江","cityNumber":null,"cityName":"杭州","areaNumber":null,"areaName":null,"address":"杭州","office":null,"memberReadBean":null},{"createdBy":"17721180295","createdDate":"2018-12-20","lastModifiedBy":"1022768","lastModifiedDate":"2019-01-08","id":9,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"赵","type":"SHAREHOLDER","phone":"12345678910","email":"24556162@qq.com","member":null,"duty":"部长","favorite":null,"avatralUrl":"","simpleSpell":null,"sex":"男","provinceNumber":null,"provinceName":"浙江","cityNumber":null,"cityName":"杭州","areaNumber":null,"areaName":null,"address":"杭州","office":null,"memberReadBean":null},{"createdBy":"1022654","createdDate":"2019-01-05","lastModifiedBy":"1022654","lastModifiedDate":"2019-01-05","id":24,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":"利斯土","type":"MAINMEMBER","phone":"18106542180","email":"","member":null,"duty":"采购部长","favorite":"","avatralUrl":null,"simpleSpell":null,"sex":"男","provinceNumber":null,"provinceName":"安徽","cityNumber":null,"cityName":"合肥","areaNumber":null,"areaName":null,"address":"","office":null,"memberReadBean":null},{"createdBy":"18888996022","createdDate":"2019-01-03","lastModifiedBy":"18888996022","lastModifiedDate":"2019-01-03","id":23,"deleted":false,"sort":10,"fromClientType":null,"optionGroup":[],"name":"张三","type":"CONTACTPERSON","phone":"12345678910","email":"24556162@qq.com","member":null,"duty":"部长","favorite":"游泳，健身","avatralUrl":"","simpleSpell":null,"sex":"男","provinceNumber":null,"provinceName":"浙江","cityNumber":null,"cityName":"杭州","areaNumber":null,"areaName":null,"address":"杭州","office":null,"memberReadBean":null},{"createdBy":"18888996001","createdDate":"2018-12-29","lastModifiedBy":"18888996001","lastModifiedDate":"2018-12-29","id":18,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"李晓宇","type":"MAINMEMBER","phone":"18106542182","email":"","member":null,"duty":"采购总监","favorite":"","avatralUrl":null,"simpleSpell":null,"sex":"男","provinceNumber":null,"provinceName":"浙江","cityNumber":null,"cityName":"杭州","areaNumber":null,"areaName":null,"address":"","office":null,"memberReadBean":null},{"createdBy":"17721180295","createdDate":"2018-12-28","lastModifiedBy":"17721180295","lastModifiedDate":"2018-12-28","id":17,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"王五","type":"CONTACTPERSON","phone":"15832471540","email":"24556162@qq.com","member":null,"duty":"科长","favorite":"游泳，健身","avatralUrl":null,"simpleSpell":null,"sex":"女","provinceNumber":null,"provinceName":"浙江","cityNumber":null,"cityName":"杭州","areaNumber":null,"areaName":null,"address":"杭州","office":null,"memberReadBean":null},{"createdBy":"17721180295","createdDate":"2018-12-28","lastModifiedBy":"17721180295","lastModifiedDate":"2018-12-28","id":16,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"王","type":"CONTACTPERSON","phone":"12345678910","email":"24556162@qq.com","member":null,"duty":"科长","favorite":"游泳，健身","avatralUrl":null,"simpleSpell":null,"sex":"男","provinceNumber":null,"provinceName":"浙江","cityNumber":null,"cityName":"杭州","areaNumber":null,"areaName":null,"address":"杭州","office":null,"memberReadBean":null},{"createdBy":"17721180295","createdDate":"2018-12-20","lastModifiedBy":"17721180295","lastModifiedDate":"2018-12-20","id":12,"deleted":false,"sort":1,"fromClientType":null,"optionGroup":[],"name":"周","type":"SHAREHOLDER","phone":"12345678910","email":"24556162@qq.com","member":null,"duty":"科长","favorite":"游泳，健身","avatralUrl":null,"simpleSpell":null,"sex":"男","provinceNumber":null,"provinceName":"浙江","cityNumber":null,"cityName":"杭州","areaNumber":null,"areaName":null,"address":"杭州","office":null,"memberReadBean":null}]
     * last : false
     * totalPages : 2
     * totalElements : 11
     * number : 0
     * size : 10
     * sort : [{"direction":"DESC","property":"lastModifiedDate","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * first : true
     * numberOfElements : 10
     */

    private boolean last;
    private int totalPages;
    private int totalElements;
    private int number;
    private int size;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;
    private List<SortBean> sort;

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

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
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

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public static class ContentBean implements Parcelable {
        protected ContentBean(Parcel in) {
            id = in.readLong();
            name = in.readString();
            type = in.readString();
            phone = in.readString();
            email = in.readString();
            duty = in.readString();
            favorite = in.readString();
            avatralUrl = in.readString();
            simpleSpell = in.readString();
            sex = in.readString();
            provinceNumber = in.readString();
            provinceName = in.readString();
            cityNumber = in.readString();
            cityName = in.readString();
            areaNumber = in.readString();
            areaName = in.readString();
            address = in.readString();
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

        public ContentBean() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public MemberBeanID getMember() {
            return member;
        }

        public void setMember(MemberBeanID member) {
            this.member = member;
        }

        public String getDuty() {
            return duty;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }

        public String getAvatralUrl() {
            return avatralUrl;
        }

        public void setAvatralUrl(String avatralUrl) {
            this.avatralUrl = avatralUrl;
        }

        public String getSimpleSpell() {
            return simpleSpell;
        }

        public void setSimpleSpell(String simpleSpell) {
            this.simpleSpell = simpleSpell;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getProvinceNumber() {
            return provinceNumber;
        }

        public void setProvinceNumber(String provinceNumber) {
            this.provinceNumber = provinceNumber;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityNumber() {
            return cityNumber;
        }

        public void setCityNumber(String cityNumber) {
            this.cityNumber = cityNumber;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaNumber() {
            return areaNumber;
        }

        public void setAreaNumber(String areaNumber) {
            this.areaNumber = areaNumber;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getOffice() {
            return office;
        }

        public void setOffice(Object office) {
            this.office = office;
        }

        /**
         * createdBy : 1022768
         * createdDate : 2019-01-08
         * lastModifiedBy : 1022768
         * lastModifiedDate : 2019-01-08
         * id : 28
         * deleted : false
         * sort : 10
         * fromClientType : null
         * optionGroup : []
         * name : 田七
         * type : MAINMEMBER
         * phone : 12345678910
         * email : 24556162@qq.com
         * member : null
         * duty : 科长
         * favorite : 游泳，健身
         * avatralUrl : null
         * simpleSpell : null
         * sex : 女
         * provinceNumber : null
         * provinceName : 浙江
         * cityNumber : null
         * cityName : 杭州
         * areaNumber : null
         * areaName : null
         * address : 杭州
         * office : null
         * memberReadBean : null
         */

        private long id;
        private String name;
        private String type;
        private String phone;
        private String email;
        private MemberBeanID member;
        private String duty;
        private String favorite;
        private String avatralUrl;
        private String simpleSpell;
        private String sex;
        private String provinceNumber;
        private String provinceName;
        private String cityNumber;
        private String cityName;
        private String areaNumber;
        private String areaName;
        private String address;
        private Object office;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(name);
            dest.writeString(type);
            dest.writeString(phone);
            dest.writeString(email);
            dest.writeString(duty);
            dest.writeString(favorite);
            dest.writeString(avatralUrl);
            dest.writeString(simpleSpell);
            dest.writeString(sex);
            dest.writeString(provinceNumber);
            dest.writeString(provinceName);
            dest.writeString(cityNumber);
            dest.writeString(cityName);
            dest.writeString(areaNumber);
            dest.writeString(areaName);
            dest.writeString(address);
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : lastModifiedDate
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : false
         * descending : true
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
}
