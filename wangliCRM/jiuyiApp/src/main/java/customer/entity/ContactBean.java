package customer.entity;

import java.util.List;

public class ContactBean {

    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * createdBy : admin
         * createdDate : 2018-04-25 09:58:10
         * lastModifiedBy : admin
         * lastModifiedDate : 2018-04-25 09:58:10
         * id : 5
         * deleted : false
         * sort : 10
         * type : MAINMEMBER
         * name : 赵柳
         * title : 联系人
         * member : null
         * phoneOne : 15057842389
         * phoneTwo : 12123456789
         * phoneThree : null
         * phoneFour : null
         * sex : 女
         * birthday : 2000-01-30T00:00:00.000+0000
         * email : www.email.com
         * address : 象山影视城
         * provinceNumber : null
         * provinceName : 浙江省
         * cityNumber : null
         * cityName : 宁波市
         * office : null
         * duty : null
         * headAddress : null
         * actualControllers : null
         */

        private String createdBy;
        private String createdDate;
        private String lastModifiedBy;
        private String lastModifiedDate;
        private long id;
        private boolean deleted;
        private int sort;
        private String type;
        private String name;
        private String title;
        private Object member;
        private String phoneOne;
        private String phoneTwo;
        private Object phoneThree;
        private Object phoneFour;
        private SexEnum sex;
        private String birthday;
        private String email;
        private String address;
        private Object provinceNumber;
        private String provinceName;
        private Object cityNumber;
        private String cityName;
        private String office;
        private String duty;
        private Object headAddress;
        private Object actualControllers;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public String getPhoneOne() {
            return phoneOne;
        }

        public void setPhoneOne(String phoneOne) {
            this.phoneOne = phoneOne;
        }

        public String getPhoneTwo() {
            return phoneTwo;
        }

        public void setPhoneTwo(String phoneTwo) {
            this.phoneTwo = phoneTwo;
        }

        public Object getPhoneThree() {
            return phoneThree;
        }

        public void setPhoneThree(Object phoneThree) {
            this.phoneThree = phoneThree;
        }

        public Object getPhoneFour() {
            return phoneFour;
        }

        public void setPhoneFour(Object phoneFour) {
            this.phoneFour = phoneFour;
        }

        public SexEnum getSex() {
            return sex;
        }

        public void setSex(SexEnum sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getProvinceNumber() {
            return provinceNumber;
        }

        public void setProvinceNumber(Object provinceNumber) {
            this.provinceNumber = provinceNumber;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public Object getCityNumber() {
            return cityNumber;
        }

        public void setCityNumber(Object cityNumber) {
            this.cityNumber = cityNumber;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public String getDuty() {
            return duty;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public Object getHeadAddress() {
            return headAddress;
        }

        public void setHeadAddress(Object headAddress) {
            this.headAddress = headAddress;
        }

        public Object getActualControllers() {
            return actualControllers;
        }

        public void setActualControllers(Object actualControllers) {
            this.actualControllers = actualControllers;
        }
    }
}
